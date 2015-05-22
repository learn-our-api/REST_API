package com.cxf.restapis.docsgenerator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: Documentation.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: Documentation.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-18		dylan.liang		Initial.
 * 
 * </pre>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({"apiVersion", "swaggerVersion", "basePath", "resourcePath", "apis"})
@XmlRootElement(name = "ApiDocumentation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Documentation
{

	/** The path. */
	private String path;

	/** The description. */
	private String description;
	
	/** The http method. */
	private String httpMethod;

	/** The summary. */
	private String summary;

	/** The response class. */
	private String responseType;

	/** The nickname. */
	private String nickname;
	
	@XmlTransient
	@JsonIgnore
	private String responseTypeInternal = null;

	/** The parameters. */
	@XmlElement
	private List<DocumentationParameter> parameters = new ArrayList<DocumentationParameter>();

	/** The models. */
	/** The apis. */
	@XmlElement(name = "models")
	@JsonProperty(value = "models")
	private Map<String, DocumentationSchema> models = null;
	
	

	/**
	 * Gets the models.
	 * 
	 * @return the models
	 */
	public Map<String, DocumentationSchema> getModels()
	{
		return models;
	}

	/**
	 * Sets the models.
	 * 
	 * @param models the models
	 */
	public void setModels(Map<String, DocumentationSchema> models)
	{
		this.models = models;
	}

	/**
	 * Adds the model.
	 * 
	 * @param propertyName the property name
	 * @param obj the obj
	 */
	public void addModel(String propertyName, DocumentationSchema obj)
	{
		if (this.models == null)
		{
			this.models = new HashMap<String, DocumentationSchema>();
		}

		if (propertyName != null && obj != null)
		{
			this.models.put(propertyName, obj);
		}
	}

	/**
	 * Add the models.
	 * 
	 * @param models the models
	 */
	public void addModels(Map<String, DocumentationSchema> models)
	{
		if (this.models == null && models != null && models.size() > 0)
		{
			this.models = new HashMap<String, DocumentationSchema>();
		}

		if (models != null && models.size() > 0)
		{
			for (String propertyName : models.keySet())
			{
				DocumentationSchema objSchema = models.get(propertyName);
				if (objSchema != null)
				{
					this.models.put(propertyName, objSchema);
				}
			}
		}
	}
	

	/**
	 * @return the path
	 */
	
	public String getPath()
	{
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path)
	{
		this.path = path;
	}

	/**
	 * @return the description
	 */
	
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the httpMethod
	 */
	
	public String getHttpMethod()
	{
		return httpMethod;
	}

	/**
	 * @param httpMethod the httpMethod to set
	 */
	public void setHttpMethod(String httpMethod)
	{
		this.httpMethod = httpMethod;
	}

	/**
	 * @return the summary
	 */
	
	public String getSummary()
	{
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	

	/**
	 * @return the responseType
	 */
	
	public String getResponseType()
	{
		return responseType;
	}

	/**
	 * @param responseType the responseType to set
	 */
	public void setResponseType(String responseType)
	{
		this.responseType = responseType;
	}

	/**
	 * @return the nickname
	 */
	
	public String getNickname()
	{
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	/**
	 * @return the parameters
	 */
	
	public List<DocumentationParameter> getParameters()
	{
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<DocumentationParameter> parameters)
	{
		this.parameters = parameters;
	}

	/**
	 * @return the responseTypeInternal
	 */
	
	public String getResponseTypeInternal()
	{
		return responseTypeInternal;
	}

	/**
	 * @param responseTypeInternal the responseTypeInternal to set
	 */
	public void setResponseTypeInternal(String responseTypeInternal)
	{
		this.responseTypeInternal = responseTypeInternal;
	}

	public void addParameter(DocumentationParameter parameter)
	{
		if (this.parameters != null)
		{
			this.parameters.add(parameter);
		}
		else
		{
			this.parameters = new ArrayList<DocumentationParameter>();
			this.parameters.add(parameter);
		}
	}
	
	/*
	 * (non-Jsdoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Object clone()
	{
		Documentation doc = new Documentation();
		

		for (String name : this.models.keySet())
		{
			doc.addModel(name, (DocumentationSchema) models.get(name).clone());
		}

		return doc;
	}
}

/*
 * $Log: av-env.bat,v $
 */
