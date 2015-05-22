package com.cxf.restapis.framework.json.impl;

import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.cxf.restapis.framework.json.JSONException;
import com.cxf.restapis.framework.json.JSONService;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.mapper.helper.impl.ArrayMapper;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JSONToolsImpl.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2015
 * 
 *  Description:
 *  JSONService implement use jsontools-core;
 * 
 *  Notes:
 * 	$Id: JSONToolsImpl.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruin.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jan 12, 2012		bruin.li		Initial.
 * 
 * </pre>
 */
public class JSONServiceImpl implements JSONService
{
	
	private static ArrayMapper arryMapper = new ArrayMapper();
	/**
	 * change model to JSON
	 * 
	 * @param clazz class object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T toModel(String json, Class<T> clazz) throws JSONException
	{
		if (ValidationUtil.isEmpty(json))
		{
			return null;
		}
		JSONParser parser = new JSONParser(new StringReader(json));
		
		T instance = null;
		try
		{
			instance = (T) JSONMapper.toJava(parser.nextValue(), clazz);
		}
		catch (Exception e)
		{
			throw new JSONException("Convert JSON to model failed.", e);
		}
		return instance;
	}

	/**
	 * change JSON to model
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public <T> String toJson(T obj) throws JSONException
	{
		if (obj == null)
		{
			return "";
		}
		
		String json = "";
		
		try
		{
			JSONValue jsonValue = JSONMapper.toJSON(obj);
			if (jsonValue != null)
			{
				json = jsonValue.render(false);
			}				
		}
		catch (MapperException e)
		{
			throw new JSONException("Convert model to JSON failed.", e);
		}

		return json;
	}
	
	/**
	 * To model list.
	 * 
	 * @param json 	the JSON string
	 * @param clazz the class type
	 * 
	 * @return the list<T>
	 */
	public <T> List<T> toModelList(String json, Class<T> clazz) throws JSONException
	{
		if (ValidationUtil.isEmpty(json))
		{
			return null;
		}
		JSONParser parser = new JSONParser(new StringReader(json));

		List<T> instance = null;
		try
		{
			T[] result = (T[]) arryMapper.toJava(parser.nextValue(), clazz);
			if (result != null)
			{
				instance = new ArrayList<T>();
				for (T t : result)
				{
					instance.add(t);
				}
			}
		}
		catch (Exception e)
		{
			throw new JSONException("Convert JSON to model failed.", e);
		}
		return instance;
	}
	
	public static void main(String[] argc) throws Exception
	{
		String jsonString = "[{\"id\":1,\"label\":\"Plan Review\"},{\"id\":2,\"label\":\"Fire Review\"}]";
		JSONServiceImpl tools = new JSONServiceImpl();
//		System.out.println(tools.toModelList(jsonString, SkippedJSONModel.class));		
	}

	/* (non-Javadoc)
	 * @see com.accela.json.JSONService#registerSubtypes(java.lang.Class)
	 */
	/**
	 * Currently JsonTools doesn't support the method.
	 */ 
	@Override
	public void registerSubtypes(Class<?> clazz) throws JSONException
	{
		throw new JSONException("JsonTools doesn't support the method.");
	}

	/* (non-Javadoc)
	 * @see com.accela.json.JSONService#write(java.io.OutputStream, java.lang.Object)
	 */
	/**
	 * Currently JsonTools doesn't support the method.
	 */ 
	@Override
	public void write(OutputStream out, Object object) throws JSONException
	{
		throw new JSONException("JsonTools doesn't support the method.");
	}
}

/*
 * $Log: av-env.bat,v $
 */
