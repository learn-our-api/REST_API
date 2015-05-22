package com.cxf.restapis.resource.dog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxf.restapis.framework.json.impl.CustomJSONConvertor;
import com.cxf.restapis.framework.json.impl.ICustomConvertor;
import com.fasterxml.jackson.core.JsonGenerator;

public class DogModelCustomConvertor extends CustomJSONConvertor implements
		ICustomConvertor {

	protected static List<String> customFields = new ArrayList<String>(32);

	protected static Map<String, String> jsonNameMapping = new HashMap<String, String>(16);

	protected static Map<String, String> jsonAliasNameMapping = new HashMap<String, String>();

	static
	{
		// hide fields
		customFields.add("dogNumber");

		// name mapping
		jsonAliasNameMapping.put("name", "dogName");
		

		// type mapping
//		jsonNameMapping.put("status", "@com.accela.i18n.IdentifierModel");

	}

	@Override
	public void toJson(JsonGenerator generator, Object object) throws IOException
	{

	}

	@Override
	public Class<?> getCustomClass()
	{
		return DogModel.class;
	}

	@Override
	public List<String> getCustomFields()
	{
		return customFields;
	}

	@Override
	public Map<String, String> getCustomJsonAliasMapping()
	{
		return jsonAliasNameMapping;
	}

	@Override
	public Map<String, String> getCustomJsonMapping()
	{
		return jsonNameMapping;
	}

	@Override
	public void toObject(Object object, String key, Object value)
	{

	}

	@Override
	public List<String> getShowFields()
	{
		return null;
	}

	@Override
	public Map<String, String[]> getCustomValidValue()
	{
		return null;
	}

}
