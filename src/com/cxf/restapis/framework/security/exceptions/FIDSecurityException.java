package com.cxf.restapis.framework.security.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cxf.restapis.framework.util.ResponseHelper;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: FIDSecurityException.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: FIDSecurityException.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jun 20, 2014		tony.li		Initial.
 *  
 * </pre>
 */
public class FIDSecurityException extends APISecurityException
{
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 1L;

	public FIDSecurityException(Throwable cause, int status)
	{
		super(cause, status);
	}

	public FIDSecurityException(Throwable cause, Status status)
	{
		super(cause, status);
	}

	public FIDSecurityException(Throwable cause)
	{
		super(cause);
	}

	public FIDSecurityException(Response response)
	{
		super(response);
	}
	
	public FIDSecurityException(String code,String message)
	{
		super(ResponseHelper.build(ResponseHelper.authorizedFailure(code,message)));
	}
	


}

/*
*$Log: av-env.bat,v $
*/