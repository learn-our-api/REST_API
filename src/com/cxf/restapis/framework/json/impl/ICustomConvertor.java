package com.cxf.restapis.framework.json.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ICustomConvertor.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ICustomConvertor.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-23		michael.mao		Initial.
 *  
 * </pre>
 */
public interface ICustomConvertor
{
	/**
	 * Convert customized fields of the object into JSON stream.
	 *
	 * @param generator			JsonGenerator
	 * @param object			the object to be converted to JSON.
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException;
	
	/**
	 * Return all customized fields, they will be used when converting to JSON.
	 *
	 * @return
	 */
	public List<String> getCustomFields();
	
	
	public List<String> getShowFields();
	
	/**
	 * The object type to be converted to JSON.
	 *
	 * @return
	 */
	public Class<?> getCustomClass();
	/**
	 * Return JSON mapping, key is custom JSON key, value is original property name in java bean.
	 * Map<key, value> ==> Map<custom key, original property name>
	 *
	 * @return
	 */
	public Map<String, String> getCustomJsonMapping();
	
	/**
	 * Return all valid value, they will be used generator API DOC.
	 *
	 * @return
	 */
	public Map<String, String[]> getCustomValidValue();
	
	/**
	 * Return JSON mapping, key is custom JSON alias key, value is original property name in java bean.
	 * Map<key, value> ==> Map<custom alias key, original property name>
	 *
	 * @return
	 */
	public Map<String, String> getCustomJsonAliasMapping();
	
	/**
	 * Parse JSON, then convert it to Java object.
	 *
	 * @param object
	 * @param key
	 * @param value
	 */
	public void toObject(Object object, String key, Object value);
}

/*
*$Log: av-env.bat,v $
*/
