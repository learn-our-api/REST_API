package com.cxf.restapis.framework.security.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cxf.restapis.framework.util.ResponseHelper;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DataSecurityException.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DataSecurityException.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jun 20, 2014		tony.li		Initial.
 *  
 * </pre>
 */
public class DataSecurityException extends APISecurityException
{	
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 1L;

	public DataSecurityException(Throwable cause, int status)
	{
		super(cause, status);
	}

	public DataSecurityException(Throwable cause, Status status)
	{
		super(cause, status);
	}

	public DataSecurityException(Throwable cause)
	{
		super(cause);
	}

	public DataSecurityException(Response response)
	{
		super(response);
	}
	public DataSecurityException(String code,String message)
	{
		super(ResponseHelper.build(ResponseHelper.authorizedFailure(code,message)));
	}


}

/*
*$Log: av-env.bat,v $
*/