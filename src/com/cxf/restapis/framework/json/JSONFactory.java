package com.cxf.restapis.framework.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.cxf.restapis.framework.json.impl.JSONJacksonNoAnnotationImpl;
import com.cxf.restapis.framework.json.impl.JSONServiceImpl;
import com.cxf.restapis.framework.json.impl.JSONServiceJacksonImpl;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JSONFactory.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2015
 * 
 *  Description:
 *  Factory class for JSON tool;
 * 
 *  Notes:
 * 	$Id: JSONFactory.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruin.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jan 12, 2012		bruin.li		Initial.
 *  
 * </pre>
 */
public final class JSONFactory
{
	public static final String JSONTOOLS = "JSONTools";
	public static final String JACKSON = "Jackson";
	public static final String JACKSON_NO_ANNOTATION = "JacksonNoAnno";
	public static final String JSONLIB = "JSONLib";
	public static final String FASTJSON = "FastJSON";
	public static JSONService buildJSONService(String type)
	{
		if(JSONTOOLS.equals(type))
		{
			return new JSONServiceImpl();
		}
		else if(JACKSON.equals(type))
		{
			return new JSONServiceJacksonImpl();
		}
		else if(JACKSON_NO_ANNOTATION.equals(type))
		{
			return new JSONJacksonNoAnnotationImpl();
		}
		else if(JSONLIB.equals(type))
		{
			//TODO
		}
		else if(FASTJSON.equals(type))
		{
			//TODO
		}
		return new JSONServiceImpl();
	}
	
	
	private static JsonTestModel getTestModel()
	{
		JsonTestModel testModel = new JsonTestModel();
		testModel.setBigDecimalValue(new BigDecimal(10000));
		testModel.setBooleanVal(true);
		testModel.setBooleanValue(true);
		testModel.setByteValue((byte) 8);
		testModel.setDateValue(new Date());
		testModel.setTimestampValue(new java.sql.Timestamp((new Date()).getTime()));
		
		testModel.setDoubleValue(Double.valueOf(77));
		testModel.setFloatValue(Float.valueOf(88));
		testModel.setIntegerValue(10);
		testModel.setIntValue(12);
		testModel.setLongValue(Long.valueOf(200));
		testModel.setStringBufferValue(new StringBuffer("string buffer test"));
		testModel.setStringBuilderValue(new StringBuilder("string builder test"));
		testModel.setStringValue("this is a test");
		
		testModel.setStringArray(new String[] {"array1", "array2"});
		testModel.setImage("abcd".getBytes());
		testModel.setUIUID(100);
		
		testModel.setCharArray(new char[]{'a', 'b', 'c'});
		
		testModel.setSubModel1(getTestSubModel());
		testModel.setSubModel2(getTestSubModel());
		
		
		List<String> listString = new ArrayList<String>();
		listString.add("abc");
		listString.add("cde");
		testModel.setListString(listString);
		
		List<JsonTestSubModel> subModelList = new ArrayList<JsonTestSubModel>();
		Collection<JsonTestSubModel> subModelList2 = new ArrayList<JsonTestSubModel>();
		subModelList.add(getTestSubModel());
		subModelList2.add(getTestSubModel());
		testModel.setCollectionSubModel(subModelList2);
		testModel.setListSubModel(subModelList);
		testModel.setListSubModel2(subModelList);
		
		return testModel;
	}
	
	private static JsonTestSubModel getTestSubModel()
	{
		JsonTestSubModel testModel = new JsonTestSubModel();
		testModel.setBigDecimalValue(new BigDecimal(10000));
		testModel.setBooleanVal(true);
		testModel.setBooleanValue(true);
		testModel.setByteValue((byte) 8);
		testModel.setDateValue(new Date());
		testModel.setDoubleValue(Double.valueOf(77));
		testModel.setFloatValue(Float.valueOf(88));
		testModel.setIntegerValue(10);
		testModel.setIntValue(12);
		testModel.setLongValue(Long.valueOf(200));
		testModel.setStringBufferValue(new StringBuffer("string buffer test"));
		testModel.setStringBuilderValue(new StringBuilder("string builder test"));
		testModel.setStringValue("this is a test");
		return testModel;
	}
	
	
	public static void main(String[] argc) throws Exception
	{
		JSONService jsonTools =  buildJSONService(JSONTOOLS);
		
		JSONService jackson =  buildJSONService(JACKSON);
		
		JSONService jacksonNoAnno =  buildJSONService(JACKSON_NO_ANNOTATION);
		
		JsonTestModel testModel = getTestModel();
		String jsonToolsResult = jsonTools.toJson(testModel);
		String jacksonResult = jackson.toJson(testModel);
		String jacksonNoAnnoResult = jacksonNoAnno.toJson(testModel);
		System.out.println("jsonToolsResult=" + jsonToolsResult);
		System.out.println("jacksonResult=" + jacksonResult);
		System.out.println("jacksonNoAnnoResult=" + jacksonNoAnnoResult);
		
	}
}

/*
*$Log: av-env.bat,v $
*/
