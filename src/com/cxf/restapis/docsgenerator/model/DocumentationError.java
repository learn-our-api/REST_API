package com.cxf.restapis.docsgenerator.model;

import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DocumentationError.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DocumentationError.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-18		dylan.liang		Initial.
 * 
 * </pre>
 */
@XmlRootElement(name = "error")
public class DocumentationError
{
	/** The code. */
	private Integer code;

	/** The reason. */
	private String reason;

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public Integer getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code the code to set
	 */
	public void setCode(Integer code)
	{
		this.code = code;
	}

	/**
	 * Gets the reason.
	 * 
	 * @return the reason
	 */
	public String getReason()
	{
		return reason;
	}

	/**
	 * Sets the reason.
	 * 
	 * @param reason the reason to set
	 */
	public void setReason(String reason)
	{
		this.reason = reason;
	}

	/* (non-Jsdoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone()
	{
		DocumentationError error = new DocumentationError();
		error.setCode(this.code);
		error.setReason(this.reason);

		return error;
	}
}

/*
 * $Log: av-env.bat,v $
 */