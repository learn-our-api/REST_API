package com.cxf.restapis.framework.model;

import javax.xml.bind.annotation.XmlElement;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 * Accela Automation
 * File: SystemModel.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2013
 * 
 * Description:
 * In order to simplify ResponseModel schema, the ResponseStatusModel is retired from API V1 version.
 * Separated fields (status, code, message, more and traceId) of ResponseModel are replacement.
 * 
 * Notes:
 * $Id: SystemModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Apr 13, 2012		evan.cai		Initial.
 * 
 * </pre>
 */
@Deprecated
public class ResponseStatusModel implements java.io.Serializable
{

	/** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;. */
	private static final long serialVersionUID = -3139659909987924472L;

	/** The status. */
	@JsonView(Views.BasicView.class)
	private Integer status;

	/** The detail. */
	@JsonView(Views.BasicView.class)
	private ResponseStatusDetailModel detail;

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Integer getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the new status
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	/**
	 * Gets the detail.
	 * 
	 * @return the detail
	 */
	@XmlElement(name = "detail", nillable = false, required = true)
	public ResponseStatusDetailModel getDetail()
	{
		return detail;
	}

	/**
	 * Sets the detail.
	 * 
	 * @param detail the new detail
	 */
	public void setDetail(ResponseStatusDetailModel detail)
	{
		this.detail = detail;
	}
}

/*
 * $Log: av-env.bat,v $
 */