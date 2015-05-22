package com.cxf.restapis.docsgenerator.model;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wordnik.swagger.core.DocumentationAllowableValues;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DocumentationSchema.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DocumentationSchema.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-23		dylan.liang		Initial.
 * 
 * </pre>
 */
@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentationSchema
{
	
	/** The object type. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private String type = null;

	/** The required. */
	private Boolean required = null;

	private Boolean isValidate = null;
	
	/** The name. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private String name = null;

	/** The id. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private String id = null;

	/** The properties. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private java.util.Map<String, DocumentationSchema> properties = null;

	/** The allowable values. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private DocumentationAllowableValues allowableValues = null;

	/** The description. */
	private String description = null;
	
	/** The enum. */
	private String[] _enum = null;

	/** The notes. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private String notes = null;

	/** The access. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private String access = null;

	/** The additional properties. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private DocumentationSchema additionalProperties = null;

	/** The items. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private DocumentationSchema items = null;

	/*
	 * If type is array this property sets if the 'items' are to be unique - together the three properties determine if
	 * it's a List or a Set
	 */
	/** The unique items. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	private Boolean uniqueItems = null;
	
	/** The ref. */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
	@JsonProperty(value = "$ref")
	@XmlElement(name = "$ref")
	private String ref = null;

	/** The simple type list. */
	@XmlTransient
	@JsonIgnore
	private List<String> simpleTypeList = Arrays.asList(new String[] {"String", "number", "int", "boolean", "Object",
			"Array", "null"});
	
	/**
	 * Gets the object type.
	 *
	 * @return the objectType
	 */
	@JsonProperty(value = "type")
	@XmlElement(name = "type")
	public String getType()
	{
		return this.type;
	}

	/**
	 * Sets the object type.
	 *
	 * @param objectType the objectType to set
	 */
	public void setType(String objectType)
	{
		this.type = objectType;
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
	 * @return the isValidate
	 */
	
	public Boolean getIsValidate()
	{
		return isValidate;
	}

	/**
	 * @param isValidate the isValidate to set
	 */
	public void setIsValidate(Boolean isValidate)
	{
		this.isValidate = isValidate;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public java.util.Map<String, DocumentationSchema> getProperties()
	{
		return properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties the properties to set
	 */
	public void setProperties(java.util.Map<String, DocumentationSchema> properties)
	{
		this.properties = properties;
	}

	/**
	 * Gets the allowable values.
	 *
	 * @return the allowableValues
	 */

	public DocumentationAllowableValues getAllowableValues()
	{
		return allowableValues;
	}

	/**
	 * Sets the allowable values.
	 *
	 * @param allowableValues the allowableValues to set
	 */
	public void setAllowableValues(DocumentationAllowableValues allowableValues)
	{
		this.allowableValues = allowableValues;
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
	 * Gets the access.
	 *
	 * @return the access
	 */
	public String getAccess()
	{
		return access;
	}

	/**
	 * Sets the access.
	 *
	 * @param access the access to set
	 */
	public void setAccess(String access)
	{
		this.access = access;
	}

	/**
	 * Gets the additional properties.
	 *
	 * @return the additionalProperties
	 */
	public DocumentationSchema getAdditionalProperties()
	{
		return additionalProperties;
	}

	/**
	 * Sets the additional properties.
	 *
	 * @param additionalProperties the additionalProperties to set
	 */
	public void setAdditionalProperties(DocumentationSchema additionalProperties)
	{
		this.additionalProperties = additionalProperties;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public DocumentationSchema getItems()
	{
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items the items to set
	 */
	public void setItems(DocumentationSchema items)
	{
		this.items = items;
	}

	/**
	 * Gets the unique items.
	 *
	 * @return the uniqueItems
	 */
	public Boolean getUniqueItems()
	{
		return uniqueItems;
	}

	/**
	 * Sets the unique items.
	 *
	 * @param uniqueItems the uniqueItems to set
	 */
	public void setUniqueItems(Boolean uniqueItems)
	{
		this.uniqueItems = uniqueItems;
	}

	/**
	 * Gets the ref.
	 *
	 * @return the ref
	 */
	public String getRef()
	{
		return ref;
	}

	/**
	 * Sets the ref.
	 *
	 * @param ref the ref to set
	 */
	public void setRef(String ref)
	{
		this.ref = ref;
	}

	/**
	 * Gets the simple type list.
	 *
	 * @return the simpleTypeList
	 */
	public List<String> getSimpleTypeList()
	{
		return simpleTypeList;
	}

	/**
	 * Sets the simple type list.
	 *
	 * @param simpleTypeList the simpleTypeList to set
	 */
	public void setSimpleTypeList(List<String> simpleTypeList)
	{
		this.simpleTypeList = simpleTypeList;
	}

	/**
	 * @return the _enum
	 */
	
	public String[] getEnum()
	{
		return _enum;
	}

	/**
	 * @param enum1 the _enum to set
	 */
	public void setEnum(String[] enum1)
	{
		_enum = enum1;
	}

	/* (non-Jsdoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone()
	{
		DocumentationSchema schema = new DocumentationSchema();
		schema.type = this.type;
		schema.required = this.required;
		schema.name = this.name;
		schema.id = this.id;
		schema.properties = new java.util.HashMap<String, DocumentationSchema>();
		schema.allowableValues = this.allowableValues;
		schema.description = this.description;
		schema.notes = this.notes;
		schema.access = this.access;
		schema.additionalProperties = this.additionalProperties;
		schema.items = this.items;
		schema.uniqueItems = this.uniqueItems;
		schema.ref = this.ref;
		schema._enum = this._enum;
		return schema;
	}
}

/*
 * $Log: av-env.bat,v $
 */
