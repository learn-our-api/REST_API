package com.cxf.restapis.docsgenerator.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DocumentationOperation.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DocumentationOperation.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-18		dylan.liang		Initial.
 * 
 * </pre>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement(name = "operation")
public class DocumentationOperation
{
//	/** The http method. */
//	private String httpMethod;
//
//	/** The summary. */
//	private String summary;
//
//	/** The notes. */
//	private String notes;
//
//	/** The deprecated. */
//	private Boolean deprecated;
//
//	/** The response class. */
//	private String responseClass;
//
//	/** The nickname. */
//	private String nickname;
//
//	/** The tags. */
//	private List<String> tags = null;
//
//	/** The response type internal. */
//	@XmlTransient
//	@JsonIgnore
//	private String responseTypeInternal = null;
//
//	/** The parameters. */
//	@XmlElement
//	private List<DocumentationParameter> parameters = new ArrayList<DocumentationParameter>();
//
//	/** The error responses. */
//	@XmlElement
//	private List<DocumentationError> errorResponses = new ArrayList<DocumentationError>();
//
//	/**
//	 * Gets the http method.
//	 * 
//	 * @return the httpMethod
//	 */
//	public String getHttpMethod()
//	{
//		return httpMethod;
//	}
//
//	/**
//	 * Sets the http method.
//	 * 
//	 * @param httpMethod the httpMethod to set
//	 */
//	public void setHttpMethod(String httpMethod)
//	{
//		this.httpMethod = httpMethod;
//	}
//
//	/**
//	 * Gets the summary.
//	 * 
//	 * @return the summary
//	 */
//	public String getSummary()
//	{
//		return summary;
//	}
//
//	/**
//	 * Sets the summary.
//	 * 
//	 * @param summary the summary to set
//	 */
//	public void setSummary(String summary)
//	{
//		this.summary = summary;
//	}
//
//	/**
//	 * Gets the notes.
//	 * 
//	 * @return the notes
//	 */
//	public String getNotes()
//	{
//		return notes;
//	}
//
//	/**
//	 * Sets the notes.
//	 * 
//	 * @param notes the notes to set
//	 */
//	public void setNotes(String notes)
//	{
//		this.notes = notes;
//	}
//
//	/**
//	 * Gets the deprecated.
//	 * 
//	 * @return the deprecated
//	 */
//	public Boolean getDeprecated()
//	{
//		return deprecated;
//	}
//
//	/**
//	 * Sets the deprecated.
//	 * 
//	 * @param deprecated the deprecated to set
//	 */
//	public void setDeprecated(Boolean deprecated)
//	{
//		this.deprecated = deprecated;
//	}
//
//	/**
//	 * Gets the response class.
//	 * 
//	 * @return the responseClass
//	 */
//	public String getResponseClass()
//	{
//		return responseClass;
//	}
//
//	/**
//	 * Sets the response class.
//	 * 
//	 * @param responseClass the responseClass to set
//	 */
//	public void setResponseClass(String responseClass)
//	{
//		this.responseClass = responseClass;
//	}
//
//	/**
//	 * Gets the nickname.
//	 * 
//	 * @return the nickname
//	 */
//	public String getNickname()
//	{
//		return nickname;
//	}
//
//	/**
//	 * Sets the nickname.
//	 * 
//	 * @param nickname the nickname to set
//	 */
//	public void setNickname(String nickname)
//	{
//		this.nickname = nickname;
//	}
//
//	/**
//	 * Gets the tags.
//	 * 
//	 * @return the tags
//	 */
//	public List<String> getTags()
//	{
//		return tags;
//	}
//
//	/**
//	 * Sets the tags.
//	 * 
//	 * @param tags the tags to set
//	 */
//	public void setTags(List<String> tags)
//	{
//		this.tags = tags;
//	}
//
//	/**
//	 * Gets the response type internal.
//	 * 
//	 * @return the responseTypeInternal
//	 */
//	public String getResponseTypeInternal()
//	{
//		return responseTypeInternal;
//	}
//
//	/**
//	 * Sets the response type internal.
//	 * 
//	 * @param responseTypeInternal the responseTypeInternal to set
//	 */
//	public void setResponseTypeInternal(String responseTypeInternal)
//	{
//		this.responseTypeInternal = responseTypeInternal;
//	}
//
//	/**
//	 * Gets the parameters.
//	 * 
//	 * @return the parameters
//	 */
//	public List<DocumentationParameter> getParameters()
//	{
//		return parameters;
//	}
//
//	/**
//	 * Sets the parameters.
//	 * 
//	 * @param parameters the parameters to set
//	 */
//	public void setParameters(List<DocumentationParameter> parameters)
//	{
//		this.parameters = parameters;
//	}
//
//	/**
//	 * Adds the parameter.
//	 * 
//	 * @param parameter the parameter
//	 */
//	public void addParameter(DocumentationParameter parameter)
//	{
//		if (this.parameters != null)
//		{
//			this.parameters.add(parameter);
//		}
//		else
//		{
//			this.parameters = new ArrayList<DocumentationParameter>();
//			this.parameters.add(parameter);
//		}
//	}
//
//	/**
//	 * Gets the error responses.
//	 * 
//	 * @return the errorResponses
//	 */
//	public List<DocumentationError> getErrorResponses()
//	{
//		return errorResponses;
//	}
//
//	/**
//	 * Sets the error responses.
//	 * 
//	 * @param errorResponses the errorResponses to set
//	 */
//	public void setErrorResponses(List<DocumentationError> errorResponses)
//	{
//		this.errorResponses = errorResponses;
//	}
//
//	/**
//	 * Adds the error response.
//	 * 
//	 * @param errorResponse the error response
//	 */
//	public void addErrorResponse(DocumentationError errorResponse)
//	{
//		if (this.errorResponses != null)
//		{
//			this.errorResponses.add(errorResponse);
//		}
//		else
//		{
//			this.errorResponses = new ArrayList<DocumentationError>();
//			this.errorResponses.add(errorResponse);
//		}
//	}
//
//	/*
//	 * (non-Jsdoc)
//	 * 
//	 * @see java.lang.Object#clone()
//	 */
//	public Object clone()
//	{
//		DocumentationOperation cloned = new DocumentationOperation();
//		cloned.setHttpMethod(this.httpMethod);
//		cloned.setSummary(this.summary);
//		cloned.setNotes(this.notes);
//		cloned.setDeprecated(this.deprecated);
//		cloned.setNickname(this.nickname);
//		cloned.setResponseClass(this.responseClass);
//		cloned.setResponseTypeInternal(this.responseTypeInternal);
//		cloned.setTags(this.tags);
//
//		for (DocumentationParameter p : this.parameters)
//		{
//			cloned.addParameter((DocumentationParameter) p.clone());
//		}
//
//		for (DocumentationError er : this.errorResponses)
//		{
//			cloned.addErrorResponse((DocumentationError) er.clone());
//		}
//
//		return cloned;
//	}
}

/*
 * $Log: av-env.bat,v $
 */
