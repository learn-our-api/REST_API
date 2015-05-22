package com.cxf.restapis.framework.model;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: Field.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: Field.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Feb 27, 2014		bruce.deng		Initial.
 *  
 * </pre>
 */
public class FieldModel
{
	private String name;
	
	private Boolean isRequired;

	/**
	 * @return the name
	 */
	@JsonView(Views.PublicView.class)
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the isRequired
	 */
	@JsonView(Views.PublicView.class)
	public Boolean getIsRequired()
	{
		return isRequired;
	}

	/**
	 * @param isRequired the isRequired to set
	 */
	public void setIsRequired(Boolean isRequired)
	{
		this.isRequired = isRequired;
	}
	
	
}

/*
*$Log: av-env.bat,v $
*/