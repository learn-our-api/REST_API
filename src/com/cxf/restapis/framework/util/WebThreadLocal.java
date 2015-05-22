package com.cxf.restapis.framework.util;

import java.util.HashMap;
import java.util.Map;

import com.cxf.restapis.framework.util.ThreadLocalModel;

public class WebThreadLocal
{
	private static ThreadLocal<ThreadLocalModel> threadLocal = new ThreadLocal<ThreadLocalModel>();

	public static ThreadLocalModel getThreadLocal()
	{
		return threadLocal.get();
	}

	public static void setThreadLocal(ThreadLocalModel threadLocalModel)
	{
		threadLocal.set(threadLocalModel);
	}

	public static String getLanguage()
	{
		if (threadLocal.get() == null)
		{
			return Constants.LANGUAGE_EN_US;
		}
		else
		{
			return threadLocal.get().getLanguage();
		}
	}

	public static String getServiceProviderCode()
	{
		if (threadLocal.get() == null)
		{
			return null;
		}
		else
		{
			return threadLocal.get().getServiceProviderCode();
		}
	}

	public static String getTraceId()
	{
		if (threadLocal.get() == null)
		{
			return null;
		}
		else
		{
			return threadLocal.get().getTraceId();
		}
	}

	
	public static String getPortletLanguage()
	{
		if (threadLocal.get() == null)
		{
			return Constants.LANGUAGE_EN_US;
		}
		else
		{
			return threadLocal.get().getPortletLanguage();
		}
	}

	public static String getCurrentUser()
	{
		if (threadLocal.get() == null)
		{
			return "";
		}
		else
		{
			return threadLocal.get().getCurrentUser();
		}
	}

	
	public static String getModule()
	{
		if (threadLocal.get() == null)
		{
			return null;
		}
		else
		{
			return threadLocal.get().getModule();
		}
	}
	
	/**
	 * Gets the invoker.
	 * 
	 * @return the invoker
	 */
	public static String getInvoker()
	{
		if (threadLocal.get() == null)
		{
			return "";
		}
		else
		{
			return threadLocal.get().getInvoker();
		}
	}

	public static void setLanguage(String language)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setLanguage(language);
		threadLocal.set(model);
	}

	public static void setServiceProviderCode(String serviceProviderCode)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setServiceProviderCode(serviceProviderCode);
		threadLocal.set(model);
	}

	public static void setTraceId(String traceId)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setTraceId(traceId);
		threadLocal.set(model);
	}
	
	public static void setPortletLanguage(String portletLanguage)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setPortletLanguage(portletLanguage);
		threadLocal.set(model);
	}

	public static void setCurrentUser(String currentUser)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setCurrentUser(currentUser);
		threadLocal.set(model);
	}
	
	//set the I18N button signal
	public static void setI18NEnable(boolean isEnable)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setEnableI18N(isEnable);
		threadLocal.set(model);
	}
	
	public static void setModule(String module)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setModule(module);
		threadLocal.set(model);
	}
	
	/**
	 * Sets the invoker.
	 * 
	 * @param invoker the invoker to set
	 */
	public static void setInvoker(String invoker)
	{
		ThreadLocalModel model = threadLocal.get();
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		model.setInvoker(invoker);
		threadLocal.set(model);
	}

	// get the I18N button signal
	public static boolean getI18NEnable()
	{
		if (threadLocal.get() == null)
		{
			return false;
		}
		else
		{
			return threadLocal.get().isEnableI18N();
		}
	}
	/**
	 * Reset all value.
	 */
	public static void clean()
	{
		ThreadLocalModel model = threadLocal.get();
		if (model != null)
		{
			model.clean();
		}
	}
	// 
	// For local cache
	// 
	public static String getLocalCache(String method, String key)
	{
		Map<String, Map<String, String>> threadCache = localCacheMap.get();

		if (threadCache == null)
		{
			return null;
		}

		Map<String, String> cache = threadCache.get(method);

		if (cache == null)
		{
			return null;
		}
		return cache.get(key);
	}

	public static String putLocalCache(String method, String key, String value)
	{
		Map<String, Map<String, String>> threadCache = localCacheMap.get();

		if (threadCache == null)
		{
			threadCache = new HashMap<String, Map<String, String>>();
			localCacheMap.set(threadCache);
		}

		Map<String, String> cache = threadCache.get(method);
		if (cache == null)
		{
			cache = new HashMap<String, String>();
			threadCache.put(method, cache);
		}
		cache.put(key, value);
		return value;
	}

	public static void clearLocalCache()
	{
		if (localCacheMap.get() != null)
		{
			localCacheMap.get().clear();
		}
	}
	
	/**
	 * 
	 */
	private static ThreadLocal<Map<String, Map<String, String>>> localCacheMap = new ThreadLocal<Map<String, Map<String, String>>>();

	/**
	 * 
	 * Set whether enable soundex search
	 *
	 * @param isEnableSoundexSearch
	 */
	public static void setEnableSoundexSearch(boolean isEnableSoundexSearch)
	{
		ThreadLocalModel model = threadLocal.get();
		
		if (model == null)
		{
			model = new ThreadLocalModel();
		}
		
		model.setEnableSoundexSearch(isEnableSoundexSearch);
		threadLocal.set(model);
	}
	
	/**
	 * 
	 * Get whether enable soundex search
	 *
	 * @return
	 */
	public static boolean getSoundexSearchEnable()
	{
		if (threadLocal.get() == null)
		{
			return false;
		}
		else
		{
			return threadLocal.get().isEnableSoundexSearch();
		}
	}
}


/*
*$Log: av-env.bat,v $
*/
