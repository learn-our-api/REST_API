package com.cxf.restapis.framework.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 * Accela Automation
 * File: ResponseModel.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2014
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: ResponseModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Apr 13, 2012		evan.cai		Initial.
 * 
 * </pre>
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "response")
@XmlType(name = "ResponseModel", namespace = "http://model.webservice.accela.com")
public class ResponseModel implements java.io.Serializable
{

	/** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;. */
	private static final long serialVersionUID = -7364388526532232545L;

	/** The status. */
	@JsonView(Views.BasicView.class)
	private Integer status;
	
	/** The code. */
	@JsonView(Views.BasicView.class)
	private Object code;
	
	/** The message. */
	@JsonView(Views.BasicView.class)
	private String message;
	
	/** The more. */
	@JsonView(Views.BasicView.class)
	private String more;
	
	/** The trace id. */
	@JsonView(Views.BasicView.class)
	private String traceId;
	
	/**
	 * The system. The field is retired from API V1 version, 
	 * separated fields (status, code, message, more and traceId) are replacement
	 */
	@Deprecated
	@JsonView(Views.BasicView.class)
	private ResponseStatusModel responseStatus;

	/** The page. */
	@JsonView(Views.BasicView.class)
	private PageModel page;

	/** The result. */
	@JsonView(Views.BasicView.class)
	private Object result;

	/** The fields. */
	private Set<String> fields;

	/** The result type. */
	private Class<?> resultType;
	
	/** The store current user for json view identifying. */
	private String currentUser;

	private boolean writeNullValue;
	
	/**
	 * Gets the current user.
	 *
	 * @return the current user
	 */
	@JsonIgnore
	@XmlTransient
	public String getCurrentUser()
	{
		return currentUser;
	}

	/**
	 * Sets the current user.
	 *
	 * @param currentUser the new current user
	 */
	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
	}

	/**
	 * Gets the result type.
	 *
	 * @return the resultType
	 */
	@JsonIgnore
	@XmlTransient	
	public Class<?> getResultType()
	{
		return resultType;
	}

	/**
	 * Sets the result type.
	 *
	 * @param resultType the resultType to set
	 */
	public void setResultType(Class<?> resultType)
	{
		this.resultType = resultType;
	}

	/**
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	@JsonIgnore
	@XmlTransient
	public Set<String> getFields()
	{
		return fields;
	}

	/**
	 * Sets the fields.
	 *
	 * @param fields the fields to set
	 */
	public void setFields(Set<String> fields)
	{
		this.fields = fields;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Integer getStatus()
	{
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status the new status
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public Object getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code the new code
	 */
	public void setCode(Object code)
	{
		this.code = code;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message the new message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Gets the more.
	 * 
	 * @return the more
	 */
	@XmlElement(name = "more", nillable = false, required = true)
	public String getMore()
	{
		return more;
	}

	/**
	 * Sets the more.
	 * 
	 * @param more the new more
	 */
	public void setMore(String more)
	{
		this.more = more;
	}

	/**
	 * Gets the trace id.
	 * 
	 * @return the trace id
	 */
	public String getTraceId()
	{
		return traceId;
	}

	/**
	 * Sets the trace id.
	 * 
	 * @param traceId the new trace id
	 */
	public void setTraceId(String traceId)
	{
		this.traceId = traceId;
	}
	
	/**
	 * Gets the responseStatus.
	 * The method is retired from API V1 version 
	 * @return the responseStatus
	 */
	@Deprecated
	public ResponseStatusModel getResponseStatus()
	{
		return responseStatus;
	}

	/**
	 * Sets the responseStatus.
	 * The method is retired from API V1 version 
	 * @param responseStatus the new responseStatus
	 */
	@Deprecated
	public void setResponseStatus(ResponseStatusModel responseStatus)
	{
		this.responseStatus = responseStatus;
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	public PageModel getPage()
	{
		return page;
	}

	/**
	 * Sets the page.
	 * 
	 * @param page the new page
	 */
	public void setPage(PageModel page)
	{
		this.page = page;
	}

	/**
	 * Gets the result.
	 * 
	 * @return the result
	 */
	public Object getResult()
	{
		return result;
	}

	/**
	 * Sets the result.
	 * 
	 * @param result the new result
	 */
	public void setResult(Object result)
	{
		this.result = result;
	}

	/**
	 * @return the writeNullValue
	 */
	@JsonIgnore
	@XmlTransient
	public boolean isWriteNullValue()
	{
		return writeNullValue;
	}

	/**
	 * @param writeNullValue the writeNullValue to set
	 */
	public void setWriteNullValue(boolean writeNullValue)
	{
		this.writeNullValue = writeNullValue;
	}
}

/*
 * $Log: av-env.bat,v $
 */
