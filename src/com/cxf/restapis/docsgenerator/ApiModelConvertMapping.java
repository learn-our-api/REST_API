package com.cxf.restapis.docsgenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ApiModelConvertMapping.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ApiModelConvertMapping.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Dec 25, 2013		bruce.deng		Initial.
 *  
 * </pre>
 */
public class ApiModelConvertMapping
{
	public static Map<String, String> convertMapping = new HashMap<String, String>();
	
	static
	{
		convertMapping.put("com.accela.aa.aamain.cap.CapConditionModel", "com.accela.restapis.json.impl.CapConditionModelCustomConvertor");
		convertMapping.put("com.accela.aa.workflow.workflow.TaskItemModel", "com.accela.restapis.json.impl.TaskItemModelCustomConvertor");
		
		
	}
}

/*
*$Log: av-env.bat,v $
*/