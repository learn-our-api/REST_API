package com.cxf.restapis.framework.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxf.restapis.framework.json.impl.ICustomConvertor;
import com.cxf.restapis.resource.dog.DogModelCustomConvertor;



/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomConvertorDefine.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: CustomConvertorDefine.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Dec 31, 2013		bruce.deng		Initial.
 *  
 * </pre>
 */
public class CustomConvertorDefine
{
	private static List<ICustomConvertor> customConvertors = new ArrayList<ICustomConvertor>();
	
	static{
		
		customConvertors.add(new DogModelCustomConvertor());
	}
	
	private static Map<Object, Object> customMap = new HashMap<Object, Object>();
	static
	{
		
		for(ICustomConvertor customConvertor : customConvertors)
		{
			String className = customConvertor.getCustomClass().getName();
			String clazz = customConvertor.getClass().getName();
			customMap.put(className, clazz);
		}
	}
	
	public static List<ICustomConvertor> getCustomConvertor()
	{
		return customConvertors;
	}
	
	public static Map<Object, Object> getCustomMap()
	{
		return customMap;
	}
}

/*
*$Log: av-env.bat,v $
*/
