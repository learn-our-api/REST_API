package com.cxf.restapis.framework.jaxrs.interceptors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.cxf.restapis.framework.constants.MessageConstants;
import com.cxf.restapis.framework.json.JSONException;
import com.cxf.restapis.framework.json.JSONFactory;
import com.cxf.restapis.framework.json.JSONService;
import com.cxf.restapis.framework.security.exceptions.APISecurityException;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.WebThreadLocal;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomOutFaultInterceptor.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: CustomOutFaultInterceptor.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-4-17		dylan.liang		Initial.
 * 
 * </pre>
 */
public class HandlingOutFaultInterceptor extends AbstractPhaseInterceptor<Message>
{
	/** The logger for this class. */
//	private static final AVLogger logger = AVLogger.getLogger(HandlingOutFaultInterceptor.class);
	
	
	
	private static Map<Integer, String> errorMessageMap = new HashMap<Integer, String>();
	static
	{
		errorMessageMap.put(400, MessageConstants.MESSAGE_400);
		errorMessageMap.put(401, MessageConstants.MESSAGE_401);
		errorMessageMap.put(403, MessageConstants.MESSAGE_403);
		errorMessageMap.put(404, MessageConstants.MESSAGE_404);
		errorMessageMap.put(500, MessageConstants.SERVER_ERROR);
	}
	
	/**
	 * Instantiates a new handling out fault interceptor.
	 */
	public HandlingOutFaultInterceptor()
	{
		this(Phase.PRE_STREAM);
	}

	/**
	 * Instantiates a new handling out fault interceptor.
	 *
	 * @param s the s
	 */
	public HandlingOutFaultInterceptor(String s)
	{
		super(Phase.MARSHAL);

	}

	/* (non-Javadoc)
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override
	public void handleMessage(Message message)
	{
		Exception ex = message.getContent(Exception.class);
//		logger.error("Handling exception", ex);
		
		Response response = null;
		HttpServletResponse httpResponse = (HttpServletResponse) message.getExchange().getInMessage()
		.get(AbstractHTTPDestination.HTTP_RESPONSE);
		
		if(null != ex && (ex.getCause() instanceof APISecurityException))
		{
			response = ((APISecurityException)ex.getCause()).getResponse();
			httpResponse.setStatus(response.getStatus());
		}
		else
		{

			if (ex instanceof Fault)
			{
				Fault f = (Fault) ex;
				ex = (Exception) f.getCause();
			}
			
			response = message.getExchange().get(Response.class);
			Integer status = 500;
	
			if (response == null)
			{
				response = ResponseHelper.internalServerError(MessageConstants.SERVER_ERROR, ex);
			}
			else if (response.getEntity() == null)
			{ 
				status = response.getStatus();
				/** 405: Method Not Allowed, for example, 
				 * using GET on a form which requires data to be presented via POST, or using PUT on a read-only resource
				 * we will deal with resource can not be found.
				 * */
				if(status == 405)	
				{
					status =404;
				}
				String messageStr = errorMessageMap.get(status);
				if(messageStr == null)
				{
					status =500;
					errorMessageMap.get(status);
				}
				if(status ==400)
				{
					response = ResponseHelper.badRequest(messageStr, ex);
				}
				else if (status ==404)
				{
					response = ResponseHelper.notFound(messageStr, ex);
				}
				else if (status ==403)
				{
					response = ResponseHelper.forbidden(messageStr, ex);
				}
				else if (status ==401)
				{
					response = ResponseHelper.notAuthorized(messageStr, ex,null);
				}
				else
				{
					response = ResponseHelper.internalServerError(messageStr, ex);
				}
			}
			else
			{
				status = response.getStatus();
				message.put(Message.RESPONSE_CODE, status);
			}
	
			// deal with the actual exception : fault.getCause()
//			httpResponse = (HttpServletResponse) message.getExchange().getInMessage()
//					.get(AbstractHTTPDestination.HTTP_RESPONSE);
			httpResponse.setStatus(status);
		}
		try
		{
			//only support json right now
			httpResponse.setContentType(MediaType.APPLICATION_JSON);
			
			JSONService service = JSONFactory.buildJSONService(JSONFactory.JACKSON);
			service.write(httpResponse.getOutputStream(), response.getEntity());
			httpResponse.getOutputStream().flush();
			message.getInterceptorChain().abort();
		}
		catch (IOException io)
		{
//			logger.error("Error writing the response", io);
			throw new RuntimeException("Error writing the response", io);
		}
		catch (JSONException e)
		{
//			logger.error("Error writing the response", e);
			throw new RuntimeException("Error writing the response", e);
		}
		finally
		{
			WebThreadLocal.clean();
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */
