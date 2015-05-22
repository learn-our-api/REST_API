package com.cxf.restapis.framework.json.impl;

import java.util.Map;
import java.util.Set;




/**
 * <pre>
 * 
 *  Accela Automation
 *  File: RestThreadLocal.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: RestThreadLocal.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Apr 26, 2013		evan.cai		Initial.
 *  
 * </pre>
 */
public class CustomJsonThreadLocal
{
	private static ThreadLocal<CustomJsonThreadLocalModel> threadLocal = new ThreadLocal<CustomJsonThreadLocalModel>();
	
	/**
	 * the index of counter
	 */
	private static ThreadLocal<Integer> threadLocalIndex = new ThreadLocal<Integer>();
	

	public static Integer getThreadLocalIndex()
	{
		if(null == threadLocalIndex.get())
		{
			threadLocalIndex.set(-1);
		}
		return threadLocalIndex.get();
	}
	
	public static void incrementThreadLocalIndex()
	{
		if (null == threadLocalIndex.get())
		{
			threadLocalIndex.set(0);
		}
		else 
		{
			threadLocalIndex.set(threadLocalIndex.get() + 1);
			
		}
	}
	
	public static void cleanThreadLocalIndex()
	{
		threadLocalIndex.set(null);
	}

	public static CustomJsonThreadLocalModel getThreadLocal()
	{
		return threadLocal.get();
	}

	public static void setThreadLocal(CustomJsonThreadLocalModel threadLocalModel)
	{
		threadLocal.set(threadLocalModel);
	}
	
	/**
	 * Reset all value.
	 */
	public static void clean()
	{
		CustomJsonThreadLocalModel model = threadLocal.get();
		if (model != null)
		{
			model.clean();
		}
	}
	
	public static Map<String, String> getRootModelConvertor()
	{
		if(threadLocal ==null)
		{
			return null;
		}
		return threadLocal.get().getModelConvertorMap();
	}
	
	public static void setRootModelConvertor(Map<String, String> modelConvertor)
	{
		CustomJsonThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new CustomJsonThreadLocalModel();
		}
		model.setModelConvertorMap(modelConvertor);
		threadLocal.set(model);
	}
	

	public static Set<String> getFields()
	{
		if (threadLocal == null || threadLocal.get() == null)
		{
			return null;
		}
		return threadLocal.get().getFields();
	}
	
	public static void setFields(Set<String> fields)
	{
		CustomJsonThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new CustomJsonThreadLocalModel();
		}
		model.setFields(fields);
		threadLocal.set(model);
	}
	
}

/*
*$Log: av-env.bat,v $
*/
