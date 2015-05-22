package com.cxf.restapis.framework.model;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: SearchSortUtil.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: SearchSortUtil.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Mar 4, 2014		bruce.deng		Initial.
 *  
 * </pre>
 */
public class SearchSortUtil
{
	private static Map<String, String> recordSortMap = new HashMap<String, String>();
	
	public static Map<String, String> getRecordSearchSortFields()
	{
		recordSortMap.put("id", "B1_PER_ID1, B1_PER_ID2, B1_PER_ID3");
		recordSortMap.put("type", "A.B1_PER_GROUP, A.B1_PER_TYPE, A.B1_PER_SUB_TYPE, A.B1_PER_CATEGORY");
		recordSortMap.put("status", "A.B1_APPL_STATUS");
		recordSortMap.put("customId", "A.B1_ALT_ID");
		recordSortMap.put("openedDate", "A.B1_FILE_DD");
		recordSortMap.put("recordClass", "A.B1_APPL_CLASS");
		recordSortMap.put("createdBy", "A.B1_CREATED_BY");
		
		return recordSortMap;
	}
	
}

/*
*$Log: av-env.bat,v $
*/