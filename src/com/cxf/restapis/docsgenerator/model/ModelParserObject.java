package com.cxf.restapis.docsgenerator.model;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ModelParserObject.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ModelParserObject.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-11-5		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ModelParserObject
{
	/** The is json ignore. */
	private boolean isJsonIgnore = false;

	/** The is json property. */
	private boolean isJsonProperty = false;

	/** The is json view. */
	private boolean isJsonView = false;
	
	/** The json view type. */
	private Class<?>[] jsonViewType = null;

	/** The is documented. */
	private boolean isDocumented = false;
	
	/**
	 * Checks if is json ignore.
	 *
	 * @return the isJsonIgnore
	 */
	public boolean isJsonIgnore()
	{
		return isJsonIgnore;
	}

	/**
	 * Sets the json ignore.
	 *
	 * @param isJsonIgnore the isJsonIgnore to set
	 */
	public void setJsonIgnore(boolean isJsonIgnore)
	{
		this.isJsonIgnore = isJsonIgnore;
	}

	/**
	 * Checks if is json property.
	 *
	 * @return the isJsonProperty
	 */
	public boolean isJsonProperty()
	{
		return isJsonProperty;
	}

	/**
	 * Sets the json property.
	 *
	 * @param isJsonProperty the isJsonProperty to set
	 */
	public void setJsonProperty(boolean isJsonProperty)
	{
		this.isJsonProperty = isJsonProperty;
	}
	
	/**
	 * Checks if is json view.
	 *
	 * @return the isJsonView
	 */
	public boolean isJsonView()
	{
		return isJsonView;
	}

	/**
	 * Sets the json view.
	 *
	 * @param isJsonView the isJsonView to set
	 */
	public void setJsonView(boolean isJsonView)
	{
		this.isJsonView = isJsonView;
	}

	/**
	 * @return the jsonViewType
	 */
	public Class<?>[] getJsonViewType()
	{
		return jsonViewType;
	}

	/**
	 * @param jsonViewType the jsonViewType to set
	 */
	public void setJsonViewType(Class<?>[] jsonViewType)
	{
		this.jsonViewType = jsonViewType;
	}

	/**
	 * Checks if is documented.
	 *
	 * @return the isDocumented
	 */
	public boolean isDocumented()
	{
		return isDocumented;
	}

	/**
	 * Sets the documented.
	 *
	 * @param isDocumented the isDocumented to set
	 */
	public void setDocumented(boolean isDocumented)
	{
		this.isDocumented = isDocumented;
	}
}

/*
 * $Log: av-env.bat,v $
 */