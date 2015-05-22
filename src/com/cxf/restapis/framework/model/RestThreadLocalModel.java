package com.cxf.restapis.framework.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: RestThreadLocalModel.java
 * 
 * Accela, Inc.
 * Copyright (C): 2013-2014
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: RestThreadLocalModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Apr 26, 2013		evan.cai		Initial.
 * 
 * </pre>
 */
public class RestThreadLocalModel implements Serializable
{

	/** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;. */
	private static final long serialVersionUID = -6237296664652045754L;
	
	/** The version. */
	private int version;
	
	/** The use custom convertor. */
	private boolean useCustomConvertor =false;
	
	private Map<RestENVtEntityType,Object> paraMap = new HashMap<RestENVtEntityType,Object>();

	private Map<String, String> modelConvertorMap;
	
	private Map<String, String[]> modelConvertors;
	
	/**
	 * @return the modelConvertors
	 */
	
	public Map<String, String[]> getModelConvertors()
	{
		return modelConvertors;
	}
	/**
	 * @param modelConvertors the modelConvertors to set
	 */
	public void setModelConvertors(Map<String, String[]> modelConvertors)
	{
		this.modelConvertors = modelConvertors;
	}
	/**
	 * Gets the version.
	 * 
	 * @return the version
	 */
	public int getVersion()
	{
		return version;
	}
	/**
	 * 
	 * setAttribute.
	 *
	 * @param key
	 * @param value
	 */
	public void setAttribute(RestENVtEntityType key,Object value)
	{
		paraMap.put(key, value);
	}
	
	/**
	 * 
	 * getAttribute.
	 *
	 * @param key
	 * @return value
	 */
	public Object getAttribute(RestENVtEntityType key)
	{
		return paraMap.get(key);
	}
	
	
	/**
	 * Sets the version.
	 * 
	 * @param version the version to set
	 */
	public void setVersion(int version)
	{
		this.version = version;
	}
	
	/**
	 * Checks if is use custom convertor.
	 * 
	 * @return the useCustomConvertor
	 */
	public boolean isUseCustomConvertor()
	{
		return useCustomConvertor;
	}

	/**
	 * Sets the use custom convertor.
	 * 
	 * @param useCustomConvertor the useCustomConvertor to set
	 */
	public void setUseCustomConvertor(boolean useCustomConvertor)
	{
		this.useCustomConvertor = useCustomConvertor;
	}
	
	/**
	 * @return the modelConvertorMap
	 */
	
	public Map<String, String> getModelConvertorMap()
	{
		return modelConvertorMap;
	}
	/**
	 * @param modelConvertorMap the modelConvertorMap to set
	 */
	public void setModelConvertorMap(Map<String, String> modelConvertorMap)
	{
		this.modelConvertorMap = modelConvertorMap;
	}
	/**
	 * Reset all value.
	 */
	public void clean()
	{
		this.useCustomConvertor = false;
		this.version = 1;
		this.paraMap.clear();
		if(modelConvertorMap!=null)
		{
			modelConvertorMap.clear();
		}
		if(modelConvertors!=null)
		{
			modelConvertors.clear();
		}
	}
}

/*
*$Log: av-env.bat,v $
*/
