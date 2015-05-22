package com.cxf.restapis.framework.jaxrs.providers;

import static com.cxf.restapis.framework.constants.MessageConstants.SSO_FAILED;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.cxf.restapis.framework.constants.ParamConstants;
import com.cxf.restapis.framework.model.RestThreadLocal;
import com.cxf.restapis.framework.security.annotations.ApiModelCustomConvertor;
import com.cxf.restapis.framework.security.annotations.IgnoreAuthentication;
import com.cxf.restapis.framework.util.Constants;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.cxf.restapis.framework.util.WebThreadLocal;

/**
 * <pre>
 * 
 * bryant Automation
 * File: AuthenticateHandler.java
 * 
 * bryant, Inc.
 * Copyright (C): 2012-2015
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: AuthenticateHandler.java 72642 2009-01-01 20:01:57Z ACHIEVO\aaa $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Apr 16, 2012		dylan.liang		Initial.
 * 
 * </pre>
 */
public class AuthenticationHandler implements RequestHandler
{

	private static final String HTTP_HEADER_TRACE_ID = "x-bryant-traceid";

	public AuthenticationHandler()
	{
	}

	@Override
	public Response handleRequest(Message message, ClassResourceInfo paramClassResourceInfo) throws Fault
	{
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
//		String requestURI = request.getRequestURI().toLowerCase();

		// Retrieve Log trace id from invoker.
		String traceId = request.getHeader(HTTP_HEADER_TRACE_ID);
		if (ValidationUtil.isEmpty(traceId))
		{
			// Generate sequence as trace id
			traceId = "rest-" + UUID.randomUUID().toString();
		}
		WebThreadLocal.setTraceId(traceId); // In case clean sequence when invoke multiple EJB.

		OperationResourceInfo ori = message.getExchange().get(OperationResourceInfo.class);
		Method method = ori.getMethodToInvoke();
		IgnoreAuthentication ignoreAuth = method.getAnnotation(IgnoreAuthentication.class);

		ApiModelCustomConvertor customConvertor = method.getAnnotation(ApiModelCustomConvertor.class);
		if (customConvertor != null)
		{
			Map<String, String> modelConvertor = new HashMap<String, String>();
			modelConvertor.put("request", customConvertor.request());
			modelConvertor.put("response", customConvertor.response());
			RestThreadLocal.setRootModelConvertor(modelConvertor);

			Map<String, String[]> modelConvertors = new HashMap<String, String[]>();
			modelConvertors.put(Constants.CONVERTOR_REQUESTS, customConvertor.requests());
			modelConvertors.put(Constants.CONVERTOR_RESPONSES, customConvertor.responses());
			RestThreadLocal.setModelConvertors(modelConvertors);

		}
		Exception error = null;
		boolean authenticated = false;
		String token = request.getParameter(ParamConstants.TOKEN);
		String lang = request.getParameter(ParamConstants.LANG);

		lang = ValidationUtil.isEmpty(lang) ? "en_US" : lang.trim();
		WebThreadLocal.setLanguage(lang);

		/**
		 * Ignore authentication checking for 1. Retrieve access token operation 2. Create citizen user account
		 * operation
		 */
		if (ignoreAuth != null)
		{
			authenticated = true;
		}

		if (!authenticated && !ValidationUtil.isEmpty(token))
		{
			token = token.trim();

			// Authenticate with SSO server. validate token.
		}

		handleAuthenticatedResult(authenticated, message, error);

		return null;
	}

	private void handleAuthenticatedResult(boolean authenticated, Message message, Exception error)
	{
		if (!authenticated)
		{
			if (message.getExchange().get(Response.class) == null)
			{
				Response response = ResponseHelper.notAuthorized(SSO_FAILED, error, null);
				message.getExchange().put(Response.class, response);
			}

			if (error instanceof Fault)
			{
				throw (Fault) error;
			}
			else
			{
				throw new Fault(new Exception(SSO_FAILED, error));
			}
		}
	}

}