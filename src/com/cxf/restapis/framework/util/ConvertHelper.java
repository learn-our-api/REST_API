package com.cxf.restapis.framework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ConvertHelper.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ConvertHelper.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Apr 2, 2015		bryant.tu		Initial.
 *  
 * </pre>
 */
public class ConvertHelper
{
	public static List<String> getURLParamToList(String param)
	{
		List<String> paramList = null;

		if (!ValidationUtil.isEmpty(param))
		{
			String[] params = param.split(",");
			paramList = new ArrayList<String>(params.length);

			for (int i = 0; i < params.length; i++)
			{
				paramList.add(params[i]);
			}
		}

		return paramList;
	}
}

/*
*$Log: av-env.bat,v $
*/