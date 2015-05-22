package com.cxf.restapis.framework.model;

import java.util.Map;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: RestThreadLocal.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2014
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
public class RestThreadLocal
{
	private static ThreadLocal<RestThreadLocalModel> threadLocal = new ThreadLocal<RestThreadLocalModel>();

	public static RestThreadLocalModel getThreadLocal()
	{
		return threadLocal.get();
	}

	public static void setThreadLocal(RestThreadLocalModel threadLocalModel)
	{
		threadLocal.set(threadLocalModel);
	}

	public static int getVersion()
	{
		if (threadLocal.get() == null)
		{
			return 0;
		}
		return threadLocal.get().getVersion();
	}

	public static void setVersion(int version)
	{
		RestThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new RestThreadLocalModel();
		}
		model.setVersion(version);
		threadLocal.set(model);
	}

	public static boolean isUseCustomConvertor()
	{
		if (threadLocal.get() == null)
		{
			return false;
		}
		return threadLocal.get().isUseCustomConvertor();
	}

	public static void setUseCustomConvertor(boolean useCustomConvertor)
	{
		RestThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new RestThreadLocalModel();
		}
		model.setUseCustomConvertor(useCustomConvertor);
		threadLocal.set(model);
	}

	/**
	 * Reset all value.
	 */
	public static void clean()
	{
		RestThreadLocalModel model = threadLocal.get();
		if (model != null)
		{
			model.clean();
		}
	}

	public static void setAttribute(RestENVtEntityType key, Object value)
	{
		RestThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new RestThreadLocalModel();
		}
		model.setAttribute(key, value);
		threadLocal.set(model);
	}

	public static Object getAttribute(RestENVtEntityType key)
	{
		RestThreadLocalModel model = threadLocal.get();
		if (model != null)
		{
			return model.getAttribute(key);
		}
		else
		{
			return null;
		}
	}

	public static Map<String, String> getRootModelConvertor()
	{
		if (threadLocal.get() == null)
		{
			return null;
		}
		return threadLocal.get().getModelConvertorMap();
	}

	public static void setRootModelConvertor(Map<String, String> modelConvertor)
	{
		RestThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new RestThreadLocalModel();
		}
		model.setModelConvertorMap(modelConvertor);
		threadLocal.set(model);
	}

	public static Map<String, String[]> getModelConvertors()
	{
		if (threadLocal.get() == null)
		{
			return null;
		}
		return threadLocal.get().getModelConvertors();
	}

	public static void setModelConvertors(Map<String, String[]> modelConvertors)
	{
		RestThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new RestThreadLocalModel();
		}
		model.setModelConvertors(modelConvertors);
		threadLocal.set(model);
	}
}

/*
 * $Log: av-env.bat,v $
 */
