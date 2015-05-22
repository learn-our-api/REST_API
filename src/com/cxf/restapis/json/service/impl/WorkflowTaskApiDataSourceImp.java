package com.cxf.restapis.json.service.impl;

import java.util.Map;

import com.cxf.restapis.framework.json.service.ApiDataSourceProvider;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: WorkflowTaskApiDataSourceImp.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: WorkflowTaskApiDataSourceImp.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  May 5, 2014		bryant.tu		Initial.
 * 
 * </pre>
 */
public class WorkflowTaskApiDataSourceImp implements ApiDataSourceProvider
{
//	private static final AVLogger logger = AVLogger.getLogger(WorkflowTaskApiDataSourceImp.class);

	@Override
	public Object query(Map<String, Object> parameter)
	{
		String recordId = (String)parameter.get("recordId");
		String id = (String)parameter.get("id");
		return null;
	}

}

/*
 * $Log: av-env.bat,v $
 */