package com.cxf.restapis.framework.exception;
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ObjectNotFoundException.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ObjectNotFoundException.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Mar 31, 2015		bryant.tu		Initial.
 *  
 * </pre>
 */
public class ObjectNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException()
	{
		super();
	}

	public ObjectNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ObjectNotFoundException(String message)
	{
		super(message);
	}
}

/*
*$Log: av-env.bat,v $
*/