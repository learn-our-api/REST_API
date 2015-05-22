package com.cxf.restapis.framework.json.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxf.restapis.framework.json.JsonTestModel;
import com.cxf.restapis.framework.json.JsonTestSubModel;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JsonTestModelCustomConvertor.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: JsonTestModelCustomConvertor.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-24		michael.mao		Initial.
 *  
 * </pre>
 */
public class JsonTestModelCustomConvertor extends CustomJSONConvertor implements ICustomConvertor
{
	
	private static List<String> customFields = new ArrayList<String>();
	
	private static Map<String, String> jsonNameMapping = new HashMap<String, String>();
	
	private static Map<String, String> jsonAliasNameMapping = new HashMap<String, String>();
	
	static
	{
		customFields.add("doubleValue");
		customFields.add("subModel1");
		//convert JSON id element to doubleValue.
		
		jsonAliasNameMapping.put("alias", "name");
		jsonNameMapping.put("id", "");
		jsonNameMapping.put("sub", "@java.util.List<com.accela.aa.inspection.guidesheet.GuideSheetHistoryModel>");
		
	}
	
	@Override
	public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
	{
		JsonTestModel model = (JsonTestModel) object;
		
		writeObjectField(generator, "sub", model.getSubModel1());
		
		writeNumberField(generator, "id", model.getDoubleValue());
		
	}

	@Override
	public List<String> getCustomFields()
	{
		return customFields;
	}

	@Override
	public Class<?> getCustomClass()
	{
		return JsonTestModel.class;
	}

	@Override
	public Map<String, String> getCustomJsonMapping()
	{
		return jsonNameMapping;
	}

	@Override
	public void toObject(Object object, String key, Object value)
	{
		JsonTestModel model = (JsonTestModel) object;
		if ("id".equals(key))
		{
			model.setDoubleValue(Double.valueOf(value.toString()));
		}
		else if ("sub".equals(key))
		{
			model.setSubModel1((JsonTestSubModel)value);
		}
	}

	@Override
	public Map<String, String> getCustomJsonAliasMapping()
	{
		return jsonAliasNameMapping;
	}

	@Override
	public List<String> getShowFields()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String[]> getCustomValidValue()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

/*
*$Log: av-env.bat,v $
*/
