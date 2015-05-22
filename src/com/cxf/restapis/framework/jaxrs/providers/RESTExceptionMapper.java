package com.cxf.restapis.framework.jaxrs.providers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.cxf.restapis.framework.constants.ErrorCodeConstants;
import com.cxf.restapis.framework.exception.DataValidateException;
import com.cxf.restapis.framework.exception.ObjectNotFoundException;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.ValidationUtil;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: RESTExceptionMapper.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  The exception mapper to log exception and send error response to client.
 * 
 *  Notes:
 * 	$Id: RESTExceptionMapper.java 139796 2009-07-20 05:45:08Z ACHIEVO\hikelee.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2009-7-17		steven.yan		Initial.
 * 
 * </pre>
 */
public class RESTExceptionMapper implements ExceptionMapper<Throwable>
{

	/**
	 * Log exception and send error response to client.
	 * 
	 * @param e the exception to process.
	 */

	public Response toResponse(Throwable e)
	{
		Response response = null;
		String message = e.getMessage();
		
		if (e instanceof WebApplicationException)
		{
			if(e.getMessage() != null && e.getMessage().contains("com.fasterxml.jackson.core.JsonParseException"))
			{
				response = ResponseHelper.jsonPaseError("JSON_PASER_ERROR", e);
			}
			else if (e.getCause()!= null && e.getCause() instanceof ObjectNotFoundException)
			{
				response = ResponseHelper.notFound(e.getCause().getMessage(), null);
				
			}
			else if(e.getMessage() != null && e.getMessage().contains("com.fasterxml.jackson.databind.exc.InvalidFormatException"))
			{
				response = ResponseHelper.jsonPaseError("JSON field data type error.Please check the post body.", e);
			}
//			else if(e.getCause()!= null && e.getCause() instanceof IllegalArgumentException)
//			{
//				response = ResponseHelper.badRequest(e.getCause().getMessage(), null);
//			}
			else
			{
				response = ((WebApplicationException) e).getResponse();
			}
		}
		else if (e instanceof ObjectNotFoundException)
		{
			response = ResponseHelper.notFound("OBJECT_NOTFOUND", e);
		}
		else if (e instanceof DataValidateException)
		{
			response = ResponseHelper.badRequest(e.getMessage(), e);
		}
		else if (e instanceof java.net.ConnectException )
		{
			String msg = e.getMessage();
			if (ValidationUtil.isEmpty(msg) && "EDMS".equals(msg))
			{
				msg = "EDMS_SERVER_UNAVAILABLE";
			}
			response = ResponseHelper.notAuthorized(msg, e.getCause(),ErrorCodeConstants.EDMS_SERVER_UNAVAILABLE_401);
		}
		else if(e instanceof RuntimeException && e.getCause() != null && e.getCause() instanceof DataValidateException)
		{
			response = ResponseHelper.badRequest(e.getCause().getMessage(), null);
		}
		else
		{
			response = ResponseHelper.internalServerError("SERVER_ERROR", e);
		}

//		logger.error(message, e);

		return response;
	}

}

/*
 * $Log: av-env.bat,v $
 */
