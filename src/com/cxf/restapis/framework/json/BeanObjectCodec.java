package com.cxf.restapis.framework.json;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: BeanObjectCodec.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012
 * 
 *  Description:
 *  Only provide support that convert java bean to JSON.
 * 
 *  Notes:
 * 	$Id: BeanObjectCodec.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Feb 2, 2012		michael.mao		Initial.
 *  
 * </pre>
 */
public class BeanObjectCodec extends ObjectCodec
{
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	
	@Override
	public JsonNode createArrayNode()
	{
		return null;
	}

	@Override
	public JsonNode createObjectNode()
	{
		return null;
	}

	@Override
	public JsonNode readTree(JsonParser paramJsonParser) throws IOException, JsonProcessingException
	{
		return null;
	}

	@Override
	public <T> T readValue(JsonParser paramJsonParser, Class<T> paramClass) throws IOException, JsonProcessingException
	{
		return null;
	}

	@Override
	public <T> T readValue(JsonParser paramJsonParser, TypeReference<?> paramTypeReference) throws IOException,
			JsonProcessingException
	{
		return null;
	}

	@Override
	public <T> T readValue(JsonParser paramJsonParser, JavaType paramJavaType) throws IOException,
			JsonProcessingException
	{
		return null;
	}

	@Override
	public <T> Iterator<T> readValues(JsonParser paramJsonParser, Class<T> paramClass) throws IOException,
			JsonProcessingException
	{
		return null;
	}

	@Override
	public <T> Iterator<T> readValues(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
			throws IOException, JsonProcessingException
	{
		return null;
	}

	@Override
	public <T> Iterator<T> readValues(JsonParser paramJsonParser, JavaType paramJavaType) throws IOException,
			JsonProcessingException
	{
		return null;
	}

	@Override
	public JsonParser treeAsTokens(JsonNode paramJsonNode)
	{
		return null;
	}

	@Override
	public <T> T treeToValue(JsonNode paramJsonNode, Class<T> paramClass) throws IOException, JsonProcessingException
	{
		return null;
	}

	@Override
	public void writeTree(JsonGenerator paramJsonGenerator, JsonNode paramJsonNode) throws IOException,
			JsonProcessingException
	{
		paramJsonGenerator.writeTree(paramJsonNode);
	}

	/**
	 * Convert Java bean to JSON stream.
	 * 
	 * @param generator		JsonGenerator support output stream.
	 * @param paramObject	java bean instance 
	 * 
	 */
	@Override
	public void writeValue(JsonGenerator generator, Object paramObject) throws IOException,
			JsonProcessingException
	{
		if (paramObject != null)
		{
			final Class<?> modelClass = paramObject.getClass();
			BeanInfo beanInfo = null;
			try
			{
				beanInfo = Introspector.getBeanInfo(modelClass);
				PropertyDescriptor[] propertyList = beanInfo.getPropertyDescriptors();
				//generator.writeStartObject();
				for (PropertyDescriptor descriptor : propertyList)
				{
					Method getterMethod = descriptor.getReadMethod();
					Method setterMethod = descriptor.getWriteMethod();
					Class<?>[] parameterClass = getterMethod.getParameterTypes();
					//Only support getter and setter pair.
					if (!"class".equals(descriptor.getName()) && getterMethod != null 
							&& (parameterClass == null || parameterClass.length == 0)
							&& setterMethod != null)
					{
						Object value = getterMethod.invoke(paramObject);
						//Support list type as Array
						if (value instanceof List<?>)
						{
							listToJSON(generator, descriptor.getName(), (List<?>) value);
						}
						//Handle Simple data type or object type.
						else if (value != null)
						{
							writeSimpleValue(generator, descriptor.getName(), value);	
						}
					}
				}
				//generator.writeEndObject();
			}
			catch (IntrospectionException e)
			{
				throw new IOException(e);
			}
			catch (IllegalAccessException e)
			{
				throw new IOException(e);
			}
			catch (InvocationTargetException e)
			{
				throw new IOException(((InvocationTargetException) e).getTargetException());
			}
			catch (IllegalArgumentException e)
			{
				throw new IOException(e);
			}
		}
	}

	/**
	 * Convert object to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	private void writeSimpleValue(JsonGenerator generator, String fieldName, Object value) throws IOException,
			JsonGenerationException
	{
		//String type
		if (value instanceof String)
		{
			generator.writeStringField(fieldName, (String)value);
		}
		//Long type
		else if (value instanceof Long)
		{
			generator.writeNumberField(fieldName, (Long)value);
		}
		//Integer and short type
		else if (value instanceof Integer || value instanceof Short)
		{
			generator.writeNumberField(fieldName, (Integer)value);
		}
		else if (value instanceof Double)
		{
			generator.writeNumberField(fieldName, (Double)value);
		}
		else if (value instanceof Float)
		{
			generator.writeNumberField(fieldName, (Float)value);
		}						
		else if (value instanceof BigDecimal)
		{
			generator.writeNumberField(fieldName, (BigDecimal)value);
		}	
		else if (value instanceof byte[])
		{
			generator.writeBinaryField(fieldName, (byte[])value);
		}	
		//Boolean type
		else if (value instanceof Boolean)
		{
			generator.writeBooleanField(fieldName, (Boolean)value);
		}
		//convert it as datetime.
		else if (value instanceof Date)
		{
			//"2012-02-02T17:05:07.864"
			generator.writeStringField(fieldName, formatter.format((Date) value));
		}
		//Only support string key/value.
		else if (value instanceof Map<?,?>)
		{
			Map<String, Object> stringMap = (Map<String, Object>) value;
			generator.writeObjectFieldStart(fieldName);
			Iterator<Map.Entry<String, Object>> iter = stringMap.entrySet().iterator();
			//Convert Map to a object.
			while(iter.hasNext())
			{
				Map.Entry<String, Object> entry = iter.next();
				//List object
				if (entry.getValue() instanceof List<?>)
				{
					listToJSON(generator, entry.getKey(), (List<?>)entry.getValue());
				}
				//Null value
				else if (entry.getValue() == null)
				{
					generator.writeNullField(entry.getKey());
				}
				//Others.
				else
				{
					writeSimpleValue(generator, entry.getKey(), entry.getValue());
				}
			}
			generator.writeEndObject();
		}
		//Object type
		else
		{
			generator.writeObjectFieldStart(fieldName);
			writeValue(generator, value);
			generator.writeEndObject();
		}
	}
	/**
	 * Convert List to JSON stream.
	 *
	 * @param generator
	 * @param nodeName
	 * @param objectList
	 * @throws IOException
	 * @throws JsonGenerationException
	 * @throws JsonProcessingException
	 */
	private void listToJSON(JsonGenerator generator, String nodeName, List<?> objectList) throws IOException,
			JsonGenerationException, JsonProcessingException
	{
		generator.writeArrayFieldStart(nodeName);
		//As a array.
		for (Object subObject : objectList)
		{
			generator.writeStartObject();
			writeValue(generator, subObject);
			generator.writeEndObject();								
		}
		generator.writeEndArray();
	}

}

/*
*$Log: av-env.bat,v $
*/