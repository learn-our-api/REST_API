package com.cxf.restapis.json.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxf.restapis.framework.json.impl.CustomJSONConvertor;
import com.cxf.restapis.framework.json.impl.ICustomConvertor;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: WorkflowTaskCommentModelCustomConvertor.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: WorkflowTaskCommentModelCustomConvertor.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jun 18, 2014		bryant.tu		Initial.
 *  
 * </pre>
 */
public class WorkflowTaskCommentModelCustomConvertor extends CustomJSONConvertor implements ICustomConvertor
{
	protected static Map<String, String> jsonNameMapping = new HashMap<String, String>();
	
	static
	{
		jsonNameMapping.put("recordId", "@com.accela.aa.aamain.cap.CapIDModel");
	}

	@Override
	public void toJson(JsonGenerator generator, Object object) throws IOException
	{
		
	}

	@Override
	public Class<?> getCustomClass()
	{
//		return WorkflowTaskCommentModel.class;
		return null;
	}

	@Override
	public List<String> getCustomFields()
	{
		return null;
	}

	@Override
	public Map<String, String> getCustomJsonAliasMapping()
	{
		return null;
	}

	@Override
	public Map<String, String> getCustomJsonMapping()
	{
		return jsonNameMapping;
	}

	@Override
	public List<String> getShowFields()
	{
		return null;
	}

	@Override
	public void toObject(Object object, String key, Object value)
	{
		
	}

	@Override
	public Map<String, String[]> getCustomValidValue()
	{
		return null;
	}

}

/*
*$Log: av-env.bat,v $
*/