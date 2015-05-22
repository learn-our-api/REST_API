package com.cxf.restapis.framework.json.service;

import java.util.Map;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ApiDataSourceProvider.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ApiDataSourceProvider.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Mar 31, 2015		bryant.tu		Initial.
 *  
 * </pre>
 */
public interface ApiDataSourceProvider
{
	public Object query(Map<String, Object> parameter);
}

/*
*$Log: av-env.bat,v $
*/