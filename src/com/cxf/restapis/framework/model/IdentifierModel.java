package com.cxf.restapis.framework.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: IdentifierModel.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2013
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: IdentifierModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * May 16, 2012		evan.cai		Initial.
 * 
 * </pre>
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "identifierModel")
@XmlType(name = "IdentifierModel", namespace = "http://model.webservice.accela.com")
public class IdentifierModel implements Serializable
{

	/** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;. */
	private static final long serialVersionUID = -612366117339121710L;
	
	/** The id. */
	private String value;

	/** I18N value of the "value" property. */
	private String text;
	
	public IdentifierModel(){}
	
	public IdentifierModel(String text, String value)
	{
		this.text = text;
		this.value = value;
	}
	
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@JsonView(Views.PublicView.class)
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the key.
	 * 
	 * @param id the new key
	 */	
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@JsonView(Views.PublicView.class)
	public String getText()
	{
		return text;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setText(String text)
	{
		this.text = text;
	}
}

/*
*$Log: av-env.bat,v $
*/