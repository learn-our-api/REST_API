package com.cxf.restapis.framework.security.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.util.ResponseHelper;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: SecurityException.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: SecurityException.java 72642 2009-01-01 20:01:57Z ACHIEVO\Moss.Li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  May 22, 2014		Moss.Li		Initial.
 *  
 * </pre>
 */
public class APISecurityException extends WebApplicationException
{

	
	private static final long serialVersionUID = 1583369272493710187L;


	public APISecurityException(Throwable cause, int status)
	{
		super(cause, status);
	}

	public APISecurityException(Throwable cause, Status status)
	{
		super(cause, status);
	}

	public APISecurityException(Throwable cause)
	{
		super(cause);
	}

	public APISecurityException(Response response)
	{
		super(response);
	}

	public APISecurityException(Throwable cause, Response response)
	{
		super(cause, response);
	}
	
	public ResponseModel getResponseModel()
	{
		return (ResponseModel)getResponse().getEntity();
	}
	
	public APISecurityException(String message)
	{
		super(ResponseHelper.build(ResponseHelper.validationFailure( message)));
	}
	

}

/*
*$Log: av-env.bat,v $
*/