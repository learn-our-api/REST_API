package com.cxf.restapis.framework.json.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: RestThreadLocalModel.java
 * 
 * Accela, Inc.
 * Copyright (C): 2014
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
public class CustomJsonThreadLocalModel implements Serializable
{

	/** Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;. */
	private static final long serialVersionUID = -6237296664652045754L;

	private Map<String, String> modelConvertorMap;
	
	private Set<String> fields;
	
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
	
	public Set<String> getFields()
	{
		return fields;
	}
	public void setFields(Set<String> fields)
	{
		this.fields = fields;
	}
	
	/**
	 * Reset all value.
	 */
	public void clean()
	{
		if(modelConvertorMap!=null)
		{
			modelConvertorMap.clear();
		}
		this.fields=null;
	}
}

/*
*$Log: av-env.bat,v $
*/
