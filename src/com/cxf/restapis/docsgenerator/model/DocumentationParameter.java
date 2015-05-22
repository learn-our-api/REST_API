package com.cxf.restapis.docsgenerator.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wordnik.swagger.core.DocumentationAllowableValues;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DocumentationParameter.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DocumentationParameter.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-18		dylan.liang		Initial.
 * 
 * </pre>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement(name = "parameter")
public class DocumentationParameter
{
	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The notes. */
	private String notes;

	/** The param type. */
	private String paramType;

	/** The default value. */
	private String defaultValue;

	/** The required. */
	private Boolean required;

	/** The allow multiple. */
	private Boolean isArray;

	/** The param access. */
	private String paramAccess;

	/** The internal description. */
	private String internalDescription;

	/** The wrapper name. */
	private String wrapperName;

	/** The data type. */
	private String dataType;
	
	private String[] _enum;

	/** The value type internal. */
	@XmlTransient
	@JsonIgnore
	private String valueTypeInternal;
	
	/**
	 * @return the validValue
	 */
	
	public String[] getEnum()
	{
		return _enum;
	}
	
	/**
	 * @param validValue the validValue to set
	 */
	public void setEnum(String[] _enum)
	{
		this._enum = _enum;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the notes.
	 * 
	 * @return the notes
	 */

	public String getNotes()
	{
		return notes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes the notes to set
	 */
	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	/**
	 * Gets the param type.
	 * 
	 * @return the paramType
	 */

	public String getParamType()
	{
		return paramType;
	}

	/**
	 * Sets the param type.
	 * 
	 * @param paramType the paramType to set
	 */
	public void setParamType(String paramType)
	{
		this.paramType = paramType;
	}

	/**
	 * Gets the default value.
	 * 
	 * @return the defaultValue
	 */

	public String getDefaultValue()
	{
		return defaultValue;
	}

	/**
	 * Sets the default value.
	 * 
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	

	/**
	 * Gets the required.
	 * 
	 * @return the required
	 */
	public Boolean getRequired()
	{
		return required;
	}

	/**
	 * Sets the required.
	 * 
	 * @param required the required to set
	 */
	public void setRequired(Boolean required)
	{
		this.required = required;
	}

	
	/**
	 * @return the isArray
	 */
	
	public Boolean getIsArray()
	{
		return isArray;
	}

	/**
	 * @param isArray the isArray to set
	 */
	public void setIsArray(Boolean isArray)
	{
		this.isArray = isArray;
	}

	/**
	 * Gets the param access.
	 * 
	 * @return the paramAccess
	 */
	public String getParamAccess()
	{
		return paramAccess;
	}

	/**
	 * Sets the param access.
	 * 
	 * @param paramAccess the paramAccess to set
	 */
	public void setParamAccess(String paramAccess)
	{
		this.paramAccess = paramAccess;
	}

	/**
	 * Gets the internal description.
	 * 
	 * @return the internalDescription
	 */
	public String getInternalDescription()
	{
		return internalDescription;
	}

	/**
	 * Sets the internal description.
	 * 
	 * @param internalDescription the internalDescription to set
	 */
	public void setInternalDescription(String internalDescription)
	{
		this.internalDescription = internalDescription;
	}

	/**
	 * Gets the wrapper name.
	 * 
	 * @return the wrapperName
	 */
	public String getWrapperName()
	{
		return wrapperName;
	}

	/**
	 * Sets the wrapper name.
	 * 
	 * @param wrapperName the wrapperName to set
	 */
	public void setWrapperName(String wrapperName)
	{
		this.wrapperName = wrapperName;
	}

	/**
	 * Gets the data type.
	 * 
	 * @return the dataType
	 */
	public String getDataType()
	{
		return dataType;
	}

	/**
	 * Sets the data type.
	 * 
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	/**
	 * Gets the value type internal.
	 * 
	 * @return the valueTypeInternal
	 */
	public String getValueTypeInternal()
	{
		return valueTypeInternal;
	}

	/**
	 * Sets the value type internal.
	 * 
	 * @param valueTypeInternal the valueTypeInternal to set
	 */
	public void setValueTypeInternal(String valueTypeInternal)
	{
		this.valueTypeInternal = valueTypeInternal;
	}

	/*
	 * (non-Jsdoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone()
	{
		DocumentationAllowableValues clonedAllowValues = null;

		DocumentationParameter cloned = new DocumentationParameter();
		cloned.setName(this.name);
		cloned.setDescription(this.description);
		cloned.setNotes(this.notes);
		cloned.setParamType(this.paramType);
		cloned.setDefaultValue(this.defaultValue);
		cloned.setRequired(this.required);
		cloned.setIsArray(this.isArray);
		cloned.setParamAccess(this.paramAccess);
		cloned.setInternalDescription(this.internalDescription);
		cloned.setWrapperName(this.wrapperName);
		cloned.setDataType(this.dataType);
		cloned.setValueTypeInternal(this.valueTypeInternal);

		return cloned;
	}
}

/*
 * $Log: av-env.bat,v $
 */
