package com.cxf.restapis.framework.json.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxf.restapis.framework.complier.JavaComplier;
import com.cxf.restapis.framework.json.JSONException;
import com.cxf.restapis.framework.json.JSONFactory;
import com.cxf.restapis.framework.json.JSONService;
import com.cxf.restapis.framework.json.JsonTestModel;
import com.cxf.restapis.framework.json.JsonTestSubModel;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomJSONProvider.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: CustomJSONProvider.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-23		michael.mao		Initial.
 * 
 * </pre>
 */
public class CustomJSONProvider
{
	private static JsonFactory jsonFactory = new JsonFactory();

	private static CustomObjectMapper convert = new CustomObjectMapper();

	private static ThreadLocal<Map<Class<?>, ICustomConvertor>> convertorThreadLocal = new ThreadLocal<Map<Class<?>, ICustomConvertor>>();

	private static ThreadLocal<CustomObjectMapper> objectMapper = new ThreadLocal<CustomObjectMapper>();

	private static Map<String, CustomJSONConvertor> classJsonConvertor = new HashMap<String, CustomJSONConvertor>();

	private static Map<Class<?>, CustomJSONConvertor> jdkClassConvertor = new HashMap<Class<?>, CustomJSONConvertor>();

	private static ThreadLocal<String> modelConvertorThreadLocal = new ThreadLocal<String>();

	private static ThreadLocal<Object> jsonUpdatedThreadLocal = new ThreadLocal<Object>();

	private static ThreadLocal<Map<Class<?>, ICustomConvertor>> methodConvertorThreadLocal = new ThreadLocal<Map<Class<?>, ICustomConvertor>>();

	static
	{
		convert.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		convert.setDateFormat(df);
		convert.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
		convert.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		convert.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		convert.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		convert.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		// disable USE_ANNOTATIONS
		convert.disable(MapperFeature.USE_ANNOTATIONS);
		convert.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
		convert.setPropertyNamingStrategy(new NaturePropertyNamingStragegy());

		// JDK class converting
		jdkClassConvertor.put(String.class, new StringConvertor());
		jdkClassConvertor.put(Long.class, new LongConvertor());
		jdkClassConvertor.put(Integer.class, new IntegerConvertor());
		jdkClassConvertor.put(Double.class, new DoubleConvertor());
		jdkClassConvertor.put(Float.class, new FloatConvertor());
		jdkClassConvertor.put(BigDecimal.class, new BigDecimalConvertor());
		// jdkClassConvertor.put(JsonTestModel.class, new JsonTestModelConvertor());
		// jdkClassConvertor.put(JsonTestSubModel.class, new JsonTestSubModelConvertor());
	}

	/**
	 * Return ICustomConvertor from thread local.
	 * 
	 * @return
	 */
	public static Map<Class<?>, ICustomConvertor> getContext()
	{
		return convertorThreadLocal.get();
	}

	/**
	 * Set ICustomConvertor to thread local variable.
	 *
	 * @param i18nModel
	 */
	public static void setContext(List<ICustomConvertor> customConvertors)
	{
		if (customConvertors == null || customConvertors.isEmpty())
		{
			convertorThreadLocal.set(null);
		}
		else
		{
			Map<Class<?>, ICustomConvertor> map = new HashMap<Class<?>, ICustomConvertor>();
			int len = customConvertors.size();
			for (int index = 0; index < len; index++)
			{
				ICustomConvertor convertor = customConvertors.get(index);
				if (convertor.getCustomClass() != null)
				{
					map.put(convertor.getCustomClass(), convertor);
				}
			}
			convertorThreadLocal.set(map);
		}
	}

	/**
	 * @return the methodConvertorTreadLocal
	 */
	public static Map<Class<?>, ICustomConvertor> getMethodConvertorThreadLocal()
	{
		return methodConvertorThreadLocal.get();
	}

	/**
	 * @param methodConvertorTreadLocal the methodConvertorTreadLocal to set
	 */
	public static void setMethodConvertorThreadLocal(String[] methodConvertors)
	{
		Map<Class<?>, ICustomConvertor> customConvertors = new HashMap<Class<?>, ICustomConvertor>();

		if (null != methodConvertors)
		{
			for (String methodConvertor : methodConvertors)
			{
				try
				{
					Class<?> clazz = Class.forName(methodConvertor);
					ICustomConvertor customConvertor = (ICustomConvertor) clazz.newInstance();
					customConvertors.put(customConvertor.getCustomClass(), customConvertor);

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}
		CustomJSONProvider.methodConvertorThreadLocal.set(customConvertors);
	}

	/**
	 * Return ICustomConvertor from thread local.
	 * 
	 * @return
	 */
	public static Object getOriginalModel()
	{
		return jsonUpdatedThreadLocal.get();
	}

	/**
	 * Set ICustomConvertor to thread local variable.
	 *
	 * @param i18nModel
	 */
	public static void setOriginalModel(Object model)
	{
		jsonUpdatedThreadLocal.set(model);
	}

	public static String getRootModelConvertor()
	{
		return modelConvertorThreadLocal.get();
	}

	public static void setRootModelConvertor(String modelConvertor)
	{
		modelConvertorThreadLocal.set(modelConvertor);
	}

	/**
	 * Set agency specific ObjectMapper object for JSON. After it was used, please set null to it.
	 *
	 * @param objectMapper1
	 */
	public static void setObjectMapperThreadLocal(CustomObjectMapper objectMapper1)
	{
		objectMapper.set(objectMapper1);
	}

	/**
	 * Get current ObjectMapper for JSON. If the thread local variable exist, return it first. Otherwise, return global
	 * object.
	 *
	 * @return
	 */
	public static CustomObjectMapper getObjectMapper()
	{
		CustomObjectMapper returnObj = objectMapper.get();
		if (returnObj == null)
		{
			returnObj = convert;
		}
		return returnObj;
	}

	/**
	 * Return customized JSON converting object by JDK standard object.
	 *
	 * @param valueType
	 * @return
	 */
	public static CustomJSONConvertor getJDKObjectConvertor(Class<?> valueType)
	{
		CustomJSONConvertor customClassConvertor = jdkClassConvertor.get(valueType);
		return customClassConvertor;
	}

	/**
	 * Return customized JSON convertor object.
	 *
	 * @param valueType
	 * @return
	 */
	public static CustomJSONConvertor getCustomJSONConvertor(Class<?> valueType)
	{
		ICustomConvertor customConvertor = null;

		Map<Class<?>, ICustomConvertor> classConvertors = getContext();
		if (classConvertors != null)
		{
			customConvertor = classConvertors.get(valueType);
		}
		boolean isRootConvertor = false;
		// Handle apimodelconvert annotation.
		String modelConvertor = getRootModelConvertor();
		if (!ValidationUtil.isEmpty(modelConvertor))
		{
			try
			{
				Class<?> clazz = Class.forName(modelConvertor);

				ICustomConvertor rootCustomConvertor = (ICustomConvertor) clazz.newInstance();
				if (valueType.getName().equals(rootCustomConvertor.getCustomClass().getName()))
				{
					customConvertor = rootCustomConvertor;
					isRootConvertor = true;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		// method convertor is the first priority
		ICustomConvertor methodCustomConvertor = getMethodConvertorThreadLocal().get(valueType);
		if (!ValidationUtil.isEmpty(methodCustomConvertor))
		{
			customConvertor = methodCustomConvertor;
		}

		String classConvertorName = getCustomClassName(valueType, customConvertor);
		CustomJSONConvertor customClassConvertor = classJsonConvertor.get(classConvertorName);
		if (customClassConvertor == null)
		{
			customClassConvertor = getCustomJSONConvertor(classConvertorName, valueType, customConvertor);
		}
		if (isRootConvertor && customClassConvertor != null
				&& !"com.accela.restapis.framework.model.ResponseModel".equals(valueType.getName()))
		{
			customClassConvertor.setFields(CustomJsonThreadLocal.getFields());
		}

		return customClassConvertor;
	}

	private static String getCustomClassName(Class<?> valueType, ICustomConvertor customConvertor)
	{
		StringBuilder name = new StringBuilder();
		name.append("com.accela.dynamic.json.Convert_");
		String className = null;
		if (customConvertor == null)
		{
			// Default convertor
			className = valueType.getName();
		}
		else
		{
			// Custom convertor
			className = customConvertor.getClass().getName();
		}
		className = className.substring(11);
		className = className.replaceAll("\\.", "_");
		// name.append(className);
		// String uniqueClassName = getUniequeName(className, customFields);
		name.append(className);
		// name.append("Convertor");
		return name.toString();
	}

	private static synchronized CustomJSONConvertor getCustomJSONConvertor(String classConvertorName,
			Class<?> valueType, ICustomConvertor customConvertor)
	{
		CustomJSONConvertor customClassConvertor = classJsonConvertor.get(classConvertorName);
		if (customClassConvertor == null)
		{
			String javaCode = null;
			List<String> showFields = null;
			List<String> customFields = null;
			Map<String, String> customJsonAliasMapping = null;
			if (customConvertor != null)
			{
				showFields = customConvertor.getShowFields();
				customFields = customConvertor.getCustomFields();
				customJsonAliasMapping = customConvertor.getCustomJsonAliasMapping();
			}

			if (customConvertor instanceof ICustomAllFields)
			{
				javaCode = CustomToJsonGenerator.generateCode4CustomAllFields(classConvertorName, valueType,
					showFields, customJsonAliasMapping);
			}
			else
			{
				// 1. Generate toJson java code
				javaCode = CustomToJsonGenerator.generateCode(classConvertorName, valueType, customFields,
					customJsonAliasMapping);
			}

			// 2. Compile new java code for computed field.
			List<Class<?>> classList = new ArrayList<Class<?>>();
			classList.add(JsonInclude.class);
			classList.add(JsonGenerator.class);
			classList.add(MapperFeature.class);
			List<String> errorMessages = JavaComplier.compile(classConvertorName, javaCode, classList);
			if (errorMessages != null && errorMessages.size() > 0)
			{
				for (String errorMessage : errorMessages)
				{
					System.out.println(errorMessage);
				}
				throw new IllegalArgumentException("Fail to generate ToJson class of " + valueType.getName());
			}
			customClassConvertor = (CustomJSONConvertor) JavaComplier.newInstance(classConvertorName);
			if (customConvertor != null)
			{
				customClassConvertor.setCustomConvertor(customConvertor);
			}
			classJsonConvertor.put(classConvertorName, customClassConvertor);
		}
		return customClassConvertor;
	}

	/**
	 * Convert object to JSON stream.
	 *
	 * @param outputStream
	 * @param entry
	 * @throws IOException
	 */
	public static void ToJson(OutputStream outputStream, Object entry, List<ICustomConvertor> customConvertors)
			throws IOException
	{
		jsonFactory.setCodec(convert);
		JsonGenerator generator = null;
		try
		{
			generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
			setContext(customConvertors);
			if (entry instanceof List<?>)
			{
				generator.writeStartArray();
				List<?> objectList = (List<?>) entry;
				for (Object object : objectList)
				{
					generator.writeStartObject();
					generator.writeObject(object);
					generator.writeEndObject();
				}
				generator.writeEndArray();
			}
			else if (entry.getClass().isArray())
			{
				generator.writeStartArray();
				int len = Array.getLength(entry);

				for (int i = 0; i < len; i++)
				{
					generator.writeStartObject();
					generator.writeObject(Array.get(entry, i));
					generator.writeEndObject();
				}
				generator.writeEndArray();
			}
			else
			{
				generator.writeStartObject();
				generator.writeObject(entry);
				generator.writeEndObject();
			}
			setContext(null);
			generator.flush();
		}
		finally
		{
			if (generator != null)
			{
				generator.close();
			}
		}
	}

	public static <T> T toModel(InputStream inputStream, Class<T> valueType, List<ICustomConvertor> customConvertors)
			throws JSONException
	{
		T instance = null;
		JsonParser parser = null;
		try
		{
			jsonFactory.setCodec(convert);
			parser = jsonFactory.createParser(inputStream);
			setContext(customConvertors);
			instance = parser.readValueAs(valueType);
			setContext(null);
		}
		catch (Exception e)
		{
			throw new JSONException("Convert JSON to model failed.", e);
		}
		finally
		{
			closeParser(parser);
		}
		return instance;
	}

	public static List<?> toModels(InputStream inputStream, Class<?> valueType, List<ICustomConvertor> customConvertors)
			throws JSONException
	{
		List<?> instance = null;
		JsonParser parser = null;
		try
		{
			jsonFactory.setCodec(convert);
			parser = jsonFactory.createParser(inputStream);
			setContext(customConvertors);
			instance = (List<?>) parser.readValueAs(valueType);
			setContext(null);
		}
		catch (Exception e)
		{
			throw new JSONException("Convert JSON to model failed.", e);
		}
		finally
		{
			closeParser(parser);
		}
		return instance;
	}

	private static void closeParser(JsonParser parser)
	{
		try
		{
			if (parser != null)
			{
				parser.close();
			}
		}
		catch (Throwable e)
		{
		}
	}

	private static class StringConvertor extends CustomJSONConvertor
	{
		public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
		{
			if (object == null)
			{
				generator.writeNull();
			}
			else
			{
				generator.writeString((String) object);
			}
		}
	}

	private static class LongConvertor extends CustomJSONConvertor
	{
		public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
		{
			if (object == null)
			{
				generator.writeNull();
			}
			else
			{
				generator.writeNumber((Long) object);
			}
		}
	}

	private static class IntegerConvertor extends CustomJSONConvertor
	{
		public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
		{
			if (object == null)
			{
				generator.writeNull();
			}
			else
			{
				generator.writeNumber((Integer) object);
			}
		}
	}

	private static class DoubleConvertor extends CustomJSONConvertor
	{
		public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
		{
			if (object == null)
			{
				generator.writeNull();
			}
			else
			{
				generator.writeNumber((Double) object);
			}
		}
	}

	private static class FloatConvertor extends CustomJSONConvertor
	{
		public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
		{
			if (object == null)
			{
				generator.writeNull();
			}
			else
			{
				generator.writeNumber((Float) object);
			}
		}
	}

	private static class BigDecimalConvertor extends CustomJSONConvertor
	{
		public void toJson(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
		{
			if (object == null)
			{
				generator.writeNull();
			}
			else
			{
				generator.writeNumber((BigDecimal) object);
			}
		}
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

		testModel.setCharArray(new char[] {'a', 'b', 'c'});

		testModel.setSubModel1(getTestSubModel(5));
		testModel.setSubModel2(getTestSubModel(8));

		List<String> listString = new ArrayList<String>();
		listString.add("abc");
		listString.add("cde");
		testModel.setListString(listString);

		List<JsonTestSubModel> subModelList = new ArrayList<JsonTestSubModel>();
		Collection<JsonTestSubModel> subModelList2 = new ArrayList<JsonTestSubModel>();
		subModelList.add(getTestSubModel(0));
		subModelList2.add(getTestSubModel(10));
		testModel.setCollectionSubModel(subModelList2);
		testModel.setListSubModel(subModelList);
		testModel.setListSubModel2(subModelList);

		Map<String, String> mapString = new HashMap<String, String>();
		mapString.put("key1", "key1");
		mapString.put("key2", "key2");
		testModel.setMapString(mapString);
		testModel.setBooleanVal(true);
		return testModel;
	}

	private static JsonTestSubModel getTestSubModel(int id)
	{
		JsonTestSubModel testModel = new JsonTestSubModel();
		testModel.setBigDecimalValue(new BigDecimal(10000 + id));
		testModel.setBooleanVal(true);
		testModel.setBooleanValue(true);
		testModel.setByteValue((byte) 8);
		testModel.setDateValue(new Date());
		testModel.setDoubleValue(Double.valueOf(77 + id));
		testModel.setFloatValue(Float.valueOf(88 + id));
		testModel.setIntegerValue(10);
		testModel.setIntValue(12);
		testModel.setLongValue(Long.valueOf(200 + id));
		testModel.setStringBufferValue(new StringBuffer("string buffer test"));
		testModel.setStringBuilderValue(new StringBuilder("string builder test"));
		testModel.setStringValue("this is a test");
		return testModel;
	}

	public static void main(String[] argc) throws Exception
	{
		JsonTestModel testModel = getTestModel();
		JSONService jacksonNoAnno = JSONFactory.buildJSONService(JSONFactory.JACKSON_NO_ANNOTATION);
		String jacksonNoAnnoResult = jacksonNoAnno.toJson(testModel);
		System.out.println("jacksonNoAnnoResult=" + jacksonNoAnnoResult);

		JSONService jackson = JSONFactory.buildJSONService(JSONFactory.JACKSON);
		String jacksonResult = jackson.toJson(testModel);
		System.out.println("jacksonResult=" + jacksonResult);

		// printParameterAndThreadDump("SELECT * FROM DUAL", new Object[]{"1"});

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		List<ICustomConvertor> customConvertors = new ArrayList<ICustomConvertor>();
		customConvertors.add(new JsonTestModelCustomConvertor());
		ToJson(output, testModel, customConvertors);
		String json1 = output.toString();
		System.out.println(json1);
		ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());

		JsonTestModel newModel = toModel(input, JsonTestModel.class, customConvertors);
		output = new ByteArrayOutputStream();
		ToJson(output, newModel, customConvertors);
		String json2 = output.toString();
		System.out.println(json2);
		System.out.println(json1.equals(json2));

		List<JsonTestModel> values = new ArrayList<JsonTestModel>();
		values.add(testModel);
		values.add(testModel);
		jacksonNoAnnoResult = jacksonNoAnno.toJson(values);
		System.out.println(jacksonNoAnnoResult);
		input = new ByteArrayInputStream(jacksonNoAnnoResult.getBytes());

		Object list = toModels(input, JsonTestModel.class, null);
		jacksonNoAnnoResult = jacksonNoAnno.toJson(list);
		System.out.println(jacksonNoAnnoResult);
	}
}
