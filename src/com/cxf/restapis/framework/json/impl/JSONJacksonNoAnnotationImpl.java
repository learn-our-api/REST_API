package com.cxf.restapis.framework.json.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cxf.restapis.framework.json.JSONException;
import com.cxf.restapis.framework.json.JSONService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JSONJacksonNoAnnotationImpl.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  New JSON service based on Jackson implementation will be used to replace JSONTOOLS.
 *  1. Any annotation will be ignored, setter and getter method name will be convert as JSON key. 
 * 
 *  Notes:
 * 	$Id: JSONJacksonNoAnnotationImpl.java 72642 2013-08-24 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-8-24		michael.mao		Initial.
 * 
 * </pre>
 */
public class JSONJacksonNoAnnotationImpl implements JSONService
{

	
	/** The Constant MAPPER. */
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static
	{
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:s.SSS");
		MAPPER.setDateFormat(df);
		MAPPER.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
		MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		//disable USE_ANNOTATIONS
		MAPPER.disable(MapperFeature.USE_ANNOTATIONS);
		MAPPER.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
		MAPPER.setPropertyNamingStrategy(new NaturePropertyNamingStragegy());
		
	}
	
	/* (non-Javadoc)
	 * @see com.accela.json.JSONService#registerSubtypes(java.lang.Class)
	 */
	@Override
	public void registerSubtypes(Class<?> clazz)
	{
		MAPPER.registerSubtypes(clazz);
	}

	/* (non-Javadoc)
	 * @see com.accela.json.JSONService#write(java.io.OutputStream, java.lang.Object)
	 */
	@Override
	public void write(OutputStream out, Object object) throws JSONException
	{
		try
		{
			MAPPER.writeValue(out, object);
		}
		catch (IOException e)
		{
			throw new JSONException("Write the object to output sream failed.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accela.json.JSONService#toJson(java.lang.Object)
	 */
	@Override
	public <T> String toJson(T obj) throws JSONException
	{
		String json = "";

		try
		{
			if (obj != null)
			{
				json = MAPPER.writeValueAsString(obj);
			}
		}
		catch (JsonProcessingException e)
		{
			throw new JSONException("Convert model to JSON failed.", e);
		}

		return json;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accela.json.JSONService#toModel(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T toModel(String json, Class<T> clazz) throws JSONException
	{
		T instance = null;
		try
		{
			instance = MAPPER.readValue(json, clazz);
		}
		catch (Exception e)
		{
			throw new JSONException("Convert JSON to model failed.", e);
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accela.json.JSONService#toModelList(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> List<T> toModelList(String json, Class<T> clazz) throws JSONException
	{
		return (List<T>) toModel(json, clazz);
	}

	
	
}

/*
 * $Log: av-env.bat,v $
 */