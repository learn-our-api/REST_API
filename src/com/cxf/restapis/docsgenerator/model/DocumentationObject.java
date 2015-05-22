package com.cxf.restapis.docsgenerator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.cxf.restapis.framework.util.ValidationUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DocumentationObject.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DocumentationObject.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-23		dylan.liang		Initial.
 * 
 * </pre>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement(name = "docObject")
public class DocumentationObject
{
	/** The name. */
	private String name = null;

	/** The unique field name. */
	private String uniqueFieldName = null;

	/** The fields. */
	private List<DocumentationParameter> fields = new ArrayList<DocumentationParameter>();

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
	 * Gets the unique field name.
	 *
	 * @return the uniqueFieldName
	 */
	public String getUniqueFieldName()
	{
		return uniqueFieldName;
	}

	/**
	 * Sets the unique field name.
	 *
	 * @param uniqueFieldName the uniqueFieldName to set
	 */
	public void setUniqueFieldName(String uniqueFieldName)
	{
		this.uniqueFieldName = uniqueFieldName;
	}

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	public List<DocumentationParameter> getFields()
	{
		return fields;
	}

	/**
	 * Sets the fields.
	 *
	 * @param fields the fields to set
	 */
	public void setFields(List<DocumentationParameter> fields)
	{
		this.fields = fields;
	}

	/**
	 * Adds the field.
	 *
	 * @param field the field
	 */
	public void addField(DocumentationParameter field)
	{
		if (fields != null)
		{
			this.fields.add(field);
		}
		else
		{
			fields = new ArrayList<DocumentationParameter>();
			fields.add(field);
		}
	}

	/* (non-Jsdoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone()
	{
		DocumentationObject cloned = new DocumentationObject();
		cloned.name = this.name;
		cloned.uniqueFieldName = this.uniqueFieldName;

		if (!ValidationUtil.isEmpty(this.fields))
		{
			for (DocumentationParameter f : this.fields)
			{
				cloned.addField((DocumentationParameter)f.clone());
			}
		}

		return cloned;
	}

	/**
	 * To documentation schema.
	 *
	 * @return the documentation schema
	 */
	public DocumentationSchema toDocumentationSchema(String propertyName)
	{
		DocumentationSchema schemaObject = new DocumentationSchema();
		if(ValidationUtil.isEmpty(propertyName))
		{
			schemaObject.setId(this.name);
		}
		else
		{
			schemaObject.setId(propertyName);
		}

		if (this.fields.size() > 0)
		{
			schemaObject.setProperties(new HashMap<String, DocumentationSchema>());
		}
		else
		{
			return null;
		}

		for (DocumentationParameter currentField : this.fields)
		{
			if (null != currentField.getParamType())
			{
				DocumentationSchema fieldSchema = new DocumentationSchema();
				setSchemaTypeDef(currentField, fieldSchema);
				fieldSchema.setRequired(currentField.getRequired());
				fieldSchema.setDescription(currentField.getDescription());
				fieldSchema.setEnum(currentField.getEnum());
				fieldSchema.setNotes(currentField.getNotes());
				fieldSchema.setAccess(currentField.getParamAccess());

				boolean useWrapperName = currentField.getWrapperName() != null
						&& currentField.getWrapperName().trim().length() > 0;
				if (useWrapperName)
				{
					schemaObject.getProperties().put(currentField.getWrapperName(), fieldSchema);
				}
				else
				{
					schemaObject.getProperties().put(currentField.getName(), fieldSchema);
				}
			}
		}

		return schemaObject;
	}

	/**
	 * Sets the schema type def.
	 *
	 * @param currentField the current field
	 * @param currentSchema the current schema
	 */
	private void setSchemaTypeDef(DocumentationParameter currentField, DocumentationSchema currentSchema)
	{
		boolean isList = currentField.getParamType().startsWith("List[");
		boolean isSet = currentField.getParamType().startsWith("Set[");

		if (isList || isSet)
		{
			currentSchema.setType("Array");
			currentSchema.setUniqueItems(isSet);
			String arrayElementType = currentField.getParamType().substring(
				currentField.getParamType().indexOf("[") + 1, currentField.getParamType().indexOf("]"));
			DocumentationSchema arrayItem = new DocumentationSchema();
			if (currentSchema.getSimpleTypeList().contains(arrayElementType))
			{
				arrayItem.setType(arrayElementType);
			}
			else
			{
				arrayItem.setRef(arrayElementType);
			}
			currentSchema.setItems(arrayItem);
		}
		else
		{
			currentSchema.setType(currentField.getParamType());
		}
	}

}

/*
 * $Log: av-env.bat,v $
 */
