package com.cxf.restapis.framework.model;

import javax.xml.bind.annotation.XmlElement;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 * Accela Automation
 * File: SystemDetailModel.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2013
 * 
 * Description:
 * In order to simplify ResponseModel schema, the ResponseStatusDetailModel is retired from API V1 version
 * Separated fields (status, code, message, more and traceId) of ResponseModel are replacement. 
 * 
 * Notes:
 * $Id: SystemDetailModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Apr 13, 2012		evan.cai		Initial.
 * 
 * </pre>
 */
@Deprecated
public class ResponseStatusDetailModel implements java.io.Serializable
{

	/** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;. */
	private static final long serialVersionUID = -7164507699104512714L;

	/** The code. */
	@JsonView(Views.BasicView.class)
	private Integer code;
	
	/** The message. */
	@JsonView(Views.BasicView.class)
	private String message;
	
	/** The more. */
	@JsonIgnore	
	private String more;
	
	/** The trace id. */
	@JsonView(Views.BasicView.class)
	private String traceId;

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
	 * @param code the new code
	 */
	public void setCode(Integer code)
	{
		this.code = code;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message the new message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Gets the more.
	 * 
	 * @return the more
	 */
	@XmlElement(name = "more", nillable = false, required = true)
	public String getMore()
	{
		return more;
	}

	/**
	 * Sets the more.
	 * 
	 * @param more the new more
	 */
	public void setMore(String more)
	{
		this.more = more;
	}

	/**
	 * Gets the trace id.
	 * 
	 * @return the trace id
	 */
	public String getTraceId()
	{
		return traceId;
	}

	/**
	 * Sets the trace id.
	 * 
	 * @param traceId the new trace id
	 */
	public void setTraceId(String traceId)
	{
		this.traceId = traceId;
	}
	
}

/*
*$Log: av-env.bat,v $
*/