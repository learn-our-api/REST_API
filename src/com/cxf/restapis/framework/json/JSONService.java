package com.cxf.restapis.framework.json;

import java.io.OutputStream;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JSONService.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2015
 * 
 *  Description:
 *  IJSONService interface;
 * 
 *  Notes:
 * 	$Id: JSONService.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruin.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jan 12, 2012		bruin.li		Initial.
 *  
 * </pre>
 */
public interface JSONService
{
	/**
	 * Register subtypes.
	 *
	 * @param clazz the clazz
	 */
	public void registerSubtypes(Class<?> clazz) throws JSONException;

	/**
	 * Write.
	 *
	 * @param out the out
	 * @param object the object
	 * @throws JSONException the jSON exception
	 */
	public void write(OutputStream out, Object object) throws JSONException;
	
	/**
	 * change model  to JSON.
	 *
	 * @param <T> the generic type
	 * @param obj the obj
	 * @return the string
	 * @throws JSONException the jSON exception
	 */
	public <T> String toJson(T obj) throws JSONException;
	
	/**
	 * change JSON to model.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @param clazz the clazz
	 * @return the t
	 * @throws JSONException the jSON exception
	 */
	public <T> T toModel(String json, Class<T> clazz) throws JSONException;
	
	/**
	 * To model list.
	 *
	 * @param <T> the generic type
	 * @param json 	the JSON string
	 * @param clazz the class type
	 * @return the list<T>
	 * @throws JSONException the jSON exception
	 */
	public <T> List<T> toModelList(String json, Class<T> clazz) throws JSONException;
}

/*
*$Log: av-env.bat,v $
*/
