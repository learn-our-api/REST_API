package com.cxf.restapis.framework.util;

import java.io.Serializable;

public class ThreadLocalModel implements Serializable
{
	private static final long serialVersionUID = -1109755943671289800L;

	private String language;

	private String portletLanguage;

	private String serviceProviderCode;
	
	private String currentUser;
	
	private boolean isEnableI18N;

	private Object messageResources;
	
	private String module;
	
	/** The invoker. */
	private String invoker;
	
	/**
	 * After getting I18N enable information from Bizdomain, the data is fresh. 
	 */
	private boolean isFreshI18N;
	/**
	 * After getting primary language from Bizdomain, the data is fresh. 
	 */
	private boolean isFreshPrimaryLanguage;
	/**
	 * Only when the primary language is fresh, it just can be used. 
	 */
	private String primaryLanguage;
	
	/**
	 * Is enable soundex search
	 */
	private boolean isEnableSoundexSearch;
	
	private String traceId;

	/**
	 * @return the invoker
	 */
	public String getInvoker()
	{
		return invoker;
	}

	/**
	 * @param invoker the invoker to set
	 */
	public void setInvoker(String invoker)
	{
		this.invoker = invoker;
	}

	public String getModule()
	{
		return module;
	}

	public void setModule(String module)
	{
		this.module = module;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getServiceProviderCode()
	{
		return serviceProviderCode;
	}

	public void setServiceProviderCode(String serviceProviderCode)
	{
		this.serviceProviderCode = serviceProviderCode;
	}

	public String getPortletLanguage()
	{
		return portletLanguage;
	}

	public void setPortletLanguage(String portletLanguage)
	{
		this.portletLanguage = portletLanguage;
	}

	public Object getMessageResources()
	{
		return messageResources;
	}

	public void setMessageResources(Object messageResources)
	{
		this.messageResources = messageResources;
	}

	public String getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public boolean isEnableI18N()
	{
		return isEnableI18N;
	}

	public void setEnableI18N(boolean isEnableI18N)
	{
		this.isEnableI18N = isEnableI18N;
	}
	
	
	public boolean isFreshI18N()
	{
		return isFreshI18N;
	}

	public void setFreshI18N(boolean isFreshI18N)
	{
		this.isFreshI18N = isFreshI18N;
	}

	public boolean isFreshPrimaryLanguage()
	{
		return isFreshPrimaryLanguage;
	}

	public void setFreshPrimaryLanguage(boolean isFreshPrimaryLanguage)
	{
		this.isFreshPrimaryLanguage = isFreshPrimaryLanguage;
	}

	public String getPrimaryLanguage()
	{
		return primaryLanguage;
	}

	public void setPrimaryLanguage(String primaryLanguage)
	{
		this.primaryLanguage = primaryLanguage;
	}

	/**
	 * @return the isEnableSoundexSearch
	 */
	
	public boolean isEnableSoundexSearch()
	{
		return isEnableSoundexSearch;
	}

	/**
	 * @param isEnableSoundexSearch the isEnableSoundexSearch to set
	 */
	public void setEnableSoundexSearch(boolean isEnableSoundexSearch)
	{
		this.isEnableSoundexSearch = isEnableSoundexSearch;
	}

	/**
	 * @return the traceId
	 */
	
	public String getTraceId()
	{
		return traceId;
	}

	/**
	 * @param traceId the traceId to set
	 */
	public void setTraceId(String traceId)
	{
		this.traceId = traceId;
	}

	/**
	 * Reset all value.
	 */
	public void clean()
	{
		this.isEnableI18N = false;
		currentUser = null;
		messageResources = null;
		serviceProviderCode = null;
		language = null;
		portletLanguage = null;
		currentUser = null;
		module = null;
		isFreshI18N = false;
		isFreshPrimaryLanguage = false;
		primaryLanguage = null;
		this.invoker = null;
		this.isEnableSoundexSearch = false;
		traceId = null;
	}
}

/*
 * $Log: av-env.bat,v $
 */