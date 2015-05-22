package com.cxf.restapis.framework.json;
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JSONException.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2013
 * 
 *  Description:
 *  Exception class for JSON tool;
 * 
 *  Notes:
 * 	$Id: JSONException.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruin.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jan 12, 2012		bruin.li		Initial.
 *  
 * </pre>
 */
public class JSONException extends Exception
{
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = -1031491231635892596L;

	public JSONException(String msg)
	{
		super(msg);
	}
	
	public JSONException(String msg, Throwable e)
	{
		super(msg, e);
	}
}

/*
*$Log: av-env.bat,v $
*/