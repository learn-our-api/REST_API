package com.cxf.restapis.framework.json;

import java.util.List;

import com.cxf.restapis.framework.util.ValidationUtil;


/**
 * <pre>
 * 
 * Accela Automation
 * File: JSonTool.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2013
 * 
 * Description:
 * JSONTool for convert between JSON and object;
 * 
 * Notes:
 * $Id: JSonTool.java 72642 2009-01-01 20:01:57Z ACHIEVO\ray.zhang $
 * 
 * ########################################################################################
 * Be notice!!!!!! For better performance, please use Jackson JSON to convert for new calls. 
 * ########################################################################################
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * 2010-9-16		ray.zhang		Initial.
 * 
 * </pre>
 */
public final class JSONTool
{
	private static JSONService service = JSONFactory.buildJSONService(JSONFactory.JSONTOOLS);;
	
	/**
	 * convert Model string to JSON String.
	 * 
	 * @param obj model
	 * 
	 * @return string
	 */
	public static <T> String toJSON(JSONService service,T obj) throws Exception
	{
		if (ValidationUtil.isEmpty(obj))
		{
			return "{}";
		}
		try
		{
			return service.toJson(obj);
		}
		catch (JSONException e)
		{
			throw new Exception(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * Convert Model Sting to JSON String using common JSONTools.
	 *
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> String toJSON(T obj) throws Exception
	{
		return toJSON(service, obj);
	}
	
	/**
	 * 
	 * Convert Model String to JSON String using Jackson tool. 
	 *
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> String toJSONIgnoreAnno(T obj) throws Exception
	{
		return toJSON(JSONFactory.buildJSONService(JSONFactory.JACKSON_NO_ANNOTATION), obj);
	}
	
	/**
	 * 
	 * Convert Model String to JSON String using Jackson tool. 
	 *
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> String toJSONPublic(T obj) throws Exception
	{
		return toJSON(JSONFactory.buildJSONService(JSONFactory.JACKSON), obj);
	}
	
	/**
	 * Convert the List<?> to JSON String.
	 * 
	 * @param objs 		the object list;
	 * 
	 * @return String 	JSON String
	 * 
	 * @throws Exception 	 
	 * */
	public static String toJSON(List<?> objs) throws Exception
	{
		if (objs == null || objs.isEmpty())
		{
			return "[]";
		}

		try
		{
			return service.toJson(objs);
		}
		catch (JSONException e)
		{
			throw new Exception(e.getMessage(), e);
		}
	}
	
	/**
	 * convert JSON String to assign Model.
	 * 
	 * @param json JSON
	 * @param clazz class
	 * 
	 * @return string
	 */
	public static <T> T toModel(JSONService service, String json, Class<T> clazz) throws Exception
	{
		if (ValidationUtil.isEmpty(clazz) || ValidationUtil.isEmpty(json))
		{
			return null;
		}

		try
		{
			return service.toModel(json, clazz);
		}
		catch (JSONException e)
		{
			throw new Exception(e.getMessage(), e);
		}
	}
	
	public static <T> T toModel(String json, Class<T> clazz) throws Exception
	{
		return toModel(service, json, clazz);
	}
	
	public static <T> T toModelIgnoreAnno(String json, Class<T> clazz) throws Exception
	{
		return toModel(JSONFactory.buildJSONService(JSONFactory.JACKSON_NO_ANNOTATION), json, clazz);
	}
	
	/**
	 * To model list.
	 * 
	 * @param json 		the JSON string
	 * @param clazz 	the class type
	 * 
	 * @return the list<T>
	 * 
	 * @throws Exception the AA exception
	 */
	public static <T> List<T> toModelList(String json, Class<T> clazz) throws Exception
	{
		if (ValidationUtil.isEmpty(clazz) || ValidationUtil.isEmpty(json))
		{
			return null;
		}

		try
		{
			return service.toModelList(json, clazz);
		}
		catch (JSONException e)
		{
			throw new Exception(e.getMessage(), e);
		}
	}
	
}

/*
*$Log: av-env.bat,v $
*/
