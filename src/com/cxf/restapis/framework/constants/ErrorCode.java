package com.cxf.restapis.framework.constants;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ErrorCode.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ErrorCode.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-11-13		dylan.liang		Initial.
 * 
 * </pre>
 */
@Deprecated
public enum ErrorCode
{
	/** The App success code. */
	APP_SUCCESS(0),

	/** App Error Code. */
	APP_ERROR_CODE(1005),

	/** The data validation error. */
	DATA_VALIDATION_ERROR(-1),

	/** The emse error. */
	EMSE_ERROR(-2),

	/** The configuration error */
	CONFIG_ERROR(-3);

	/**
	 * Instantiates a new error code.
	 * 
	 * @param errorCode the error code
	 */
	ErrorCode(Integer code)
	{
		this.code = code;
	}

	/** The error code. */
	private Integer code;

	/**
	 * Gets the error code.
	 * 
	 * @return the error code
	 */
	public Integer getCode()
	{
		return code;
	}

	/**
	 * Sets the error code.
	 * 
	 * @param errorCode the new error code
	 */
	public void setCode(Integer code)
	{
		this.code = code;
	}

}

/*
 * $Log: av-env.bat,v $
 */
