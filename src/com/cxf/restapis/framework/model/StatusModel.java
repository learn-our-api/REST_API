package com.cxf.restapis.framework.model;

import java.util.List;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: TypeModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: TypeModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Oct 10, 2013		bruce.deng		Initial.
 *  
 * </pre>
 */
public class StatusModel
{
	private String id;
	
	private String value;
	
	private String text;
	
	private List<IdentifierModel> severity;

	/**
	 * @return the id
	 */
	@JsonView(Views.PublicView.class)
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the value
	 */
	@JsonView(Views.PublicView.class)
	public String getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * @return the text
	 */
	@JsonView(Views.PublicView.class)
	public String getText()
	{
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * @return the severity
	 */
	@JsonView(Views.PublicView.class)
	public List<IdentifierModel> getSeverity()
	{
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(List<IdentifierModel> severity)
	{
		this.severity = severity;
	}
	
	
	
}

/*
*$Log: av-env.bat,v $
*/