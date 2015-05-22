package com.cxf.restapis.framework.security.handlers;

import java.io.Serializable;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: ResourceBasic
 * 
 * Accela, Inc.
 * Copyright (C): 2013
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: RecordSecurityAuthorizer.java 72642 2009-01-01 20:01:57Z ACHIEVO\aaa $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Jun 06, 2013		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ResourceBasic implements Serializable, Cloneable
{

	/** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;. */
	private static final long serialVersionUID = 8837262600152642822L;

	/** The resource type. */
	private String resourceType;

	/** The ids. */
	private String ids;

	/** The parent resource type. */
	private String parentResourceType;

	/** The parent id. */
	private String parentId;

	/** The service provider code. */
	private String servProvCode;

	/** The module. */
	private String module;

	/** The type. */
	private String type;

	/** The content. */
	private Object content;

	/** The path info. */
	private String pathInfo;

	/** The operation. */
	private String operation;

	private Map<String, String> extraParameters;
	/**
	 * Gets the operation.
	 * 
	 * @return the operation
	 */
	public String getOperation()
	{
		return operation;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation the new operation
	 */
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	/**
	 * Gets the path info.
	 * 
	 * @return the path info
	 */
	public String getPathInfo()
	{
		return pathInfo;
	}

	/**
	 * Sets the path info.
	 * 
	 * @param pathInfo the new path info
	 */
	public void setPathInfo(String pathInfo)
	{
		this.pathInfo = pathInfo;
	}

	/**
	 * Gets the resource type.
	 * 
	 * @return the resource type
	 */
	public String getResourceType()
	{
		return resourceType;
	}

	/**
	 * Sets the resource type.
	 * 
	 * @param resource the new resource type
	 */
	public void setResourceType(String resource)
	{
		this.resourceType = resource;
	}

	/**
	 * Gets the ids.
	 * 
	 * @return the ids
	 */
	public String getIds()
	{
		return ids;
	}

	/**
	 * Sets the ids.
	 * 
	 * @param id the new ids
	 */
	public void setIds(String id)
	{
		this.ids = id;
	}

	/**
	 * Gets the parent resource type.
	 * 
	 * @return the parent resource type
	 */
	public String getParentResourceType()
	{
		return parentResourceType;
	}

	/**
	 * Sets the parent resource type.
	 * 
	 * @param parentResource the new parent resource type
	 */
	public void setParentResourceType(String parentResource)
	{
		this.parentResourceType = parentResource;
	}

	/**
	 * Gets the parent id.
	 * 
	 * @return the parent id
	 */
	public String getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the parent id.
	 * 
	 * @param parentId the new parent id
	 */
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	/**
	 * Gets the serv prov code.
	 * 
	 * @return the serv prov code
	 */
	public String getServProvCode()
	{
		return servProvCode;
	}

	/**
	 * Sets the serv prov code.
	 * 
	 * @param servProvCode the new serv prov code
	 */
	public void setServProvCode(String servProvCode)
	{
		this.servProvCode = servProvCode;
	}

	/**
	 * Gets the module.
	 * 
	 * @return the module
	 */
	public String getModule()
	{
		return module;
	}

	/**
	 * Sets the module.
	 * 
	 * @param module the new module
	 */
	public void setModule(String module)
	{
		this.module = module;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type the new type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public Object getContent()
	{
		return content;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content the new content
	 */
	public void setContent(Object content)
	{
		this.content = content;
	}

	/**
	 * @return the extraParameters
	 */
	
	public Map<String, String> getExtraParameters()
	{
		return extraParameters;
	}

	/**
	 * @param extraParameters the extraParameters to set
	 */
	public void setExtraParameters(Map<String, String> extraParameters)
	{
		this.extraParameters = extraParameters;
	}

	@Override
	public Object clone()
	{

		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}

	}

	
}
