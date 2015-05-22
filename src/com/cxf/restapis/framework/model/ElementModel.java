package com.cxf.restapis.framework.model;

import java.util.Set;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ElementModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ElementModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Feb 27, 2014		bruce.deng		Initial.
 *  
 * </pre>
 */
public class ElementModel
{
	private String name;
	
	private Boolean isRequired;
	
	private Boolean isReference;
	
	private Set<ContactTypeModel> types;

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

	/**
	 * @return the isReference
	 */
	@JsonView(Views.PublicView.class)
	public Boolean getIsReference()
	{
		return isReference;
	}

	/**
	 * @param isReference the isReference to set
	 */
	public void setIsReference(Boolean isReference)
	{
		this.isReference = isReference;
	}

	/**
	 * @return the types
	 */
	@JsonView(Views.PublicView.class)
	public Set<ContactTypeModel> getTypes()
	{
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(Set<ContactTypeModel> types)
	{
		this.types = types;
	}
	
}

/*
*$Log: av-env.bat,v $
*/