package com.cxf.restapis.framework.jaxrs.providers;

import static javax.servlet.http.HttpServletResponse.SC_OK;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.ResponseHandler;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;

import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.model.ResponseStreamWrapper;
import com.cxf.restapis.framework.model.RestThreadLocal;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: RESTResponseHandler.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: RESTResponseHandler.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  May 21, 2012		evan.cai		Initial.
 *  
 * </pre>
 */
public class RESTResponseHandler implements ResponseHandler
{
	/** The Constant logger. */
//	private static final AVLogger logger = AVLogger.getLogger(RESTResponseHandler.class);
	
	@Override
	public Response handleResponse(Message paramMessage, OperationResourceInfo paramOperationResourceInfo,
			Response paramResponse)
	{
		Object entity = paramResponse.getEntity();

		if (entity instanceof ResponseStreamWrapper)
		{
			ResponseStreamWrapper wrapper = ((ResponseStreamWrapper) entity);
			ResponseBuilder responseBuidler = Response.fromResponse(paramResponse);
			responseBuidler.entity(wrapper.getInputStream());
			paramResponse = responseBuidler.build();
			MultivaluedMap<String, Object> map = paramResponse.getMetadata();

			if (map != null)
			{
				try
				{
					map.putSingle("Content-Type", wrapper.getFileType());
					map.putSingle("Content-Length", wrapper.getInputStream().available() + "");
					String fileName = URLEncoder.encode(wrapper.getFileName(), "UTF-8");
					map.putSingle("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				}
				catch (IOException e)
				{
//					logger.error(e.getMessage(), e);
				}
			}
		}
		else if (!(entity instanceof ResponseModel) && !(entity instanceof InputStream))
		{
			ResponseModel response = getResponseModel(entity);

			ResponseBuilder responseBuidler = Response.fromResponse(paramResponse);
			responseBuidler.entity(response);
			paramResponse = responseBuidler.build();
		}
		else if (entity instanceof ResponseModel  && entity!=null)
		{
			ResponseModel  response= (ResponseModel)entity;
			if(response.getStatus()!=null && response.getStatus()!=SC_OK)
			{
				ResponseBuilder responseBuidler = Response.fromResponse(paramResponse);
				responseBuidler.status(response.getStatus());
				paramResponse = responseBuidler.build();
			}
		}
		//LogUtil.clean();
		return paramResponse;
	}

	private ResponseModel getResponseModel(Object entity)
	{
		ResponseModel response = new ResponseModel();
		if(RestThreadLocal.getVersion()>0)
		{
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else
		{
//			ResponseStatusModel responseStatus = new ResponseStatusModel();
//			responseStatus.setStatus(HttpServletResponse.SC_OK);
//			response.setResponseStatus(responseStatus);
		}
		

		response.setResult(entity);
		return response;
	}
}

/*
*$Log: av-env.bat,v $
*/
