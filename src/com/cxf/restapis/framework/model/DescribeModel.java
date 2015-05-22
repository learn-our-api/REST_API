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
 *  Copyright (C): 2014
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
public class DescribeModel
{
	private List<FieldModel> fields;
	
	private List<ElementModel> elements;

	/**
	 * @return the fields
	 */
	@JsonView(Views.PublicView.class)
	public List<FieldModel> getFields()
	{
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<FieldModel> fields)
	{
		this.fields = fields;
	}

	/**
	 * @return the elements
	 */
	@JsonView(Views.PublicView.class)
	public List<ElementModel> getElements()
	{
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<ElementModel> elements)
	{
		this.elements = elements;
	}
	
	
	
}

/*
*$Log: av-env.bat,v $
*/
