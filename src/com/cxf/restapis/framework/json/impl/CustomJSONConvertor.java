package com.cxf.restapis.framework.json.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomJSONConvertor.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: CustomJSONConvertor.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-23		michael.mao		Initial.
 *  
 * </pre>
 */
public abstract class CustomJSONConvertor
{
	protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	protected ICustomConvertor customConvertor;
	
	private Set<String> fields;
	
	public abstract void toJson(JsonGenerator generator, Object object) throws IOException;
//	{
//		//"JsonTestModel_field1_field2_field3Convertor"
//		//field 1-10
//		if (customConvertor != null)
//		{
//			customConvertor.toJson(generator, object);
//		}
//	}
	
	/**
	 * Set date format used for data converting.
	 *
	 * @param dateFormat
	 */
	public void setDateFormat(DateFormat dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	
	/**
	 * @return the customConvertor
	 */
	
	public ICustomConvertor getCustomConvertor()
	{
		return customConvertor;
	}

	/**
	 * @param customConvertor the customConvertor to set
	 */
	public void setCustomConvertor(ICustomConvertor customConvertor)
	{
		this.customConvertor = customConvertor;
	}

	/**
	 * Convert object to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 */
	protected void writeSimpleValue(JsonGenerator generator, String fieldName, Object value) throws IOException
	{
		if (this.fields==null || this.fields.contains(fieldName))
		{
			// String type
			if (value instanceof String)
			{
				generator.writeStringField(fieldName, (String) value);
			}
			else if (value instanceof StringBuffer || value instanceof StringBuilder)
			{
				generator.writeStringField(fieldName, value.toString());
			}
			// Long type
			else if (value instanceof Long)
			{
				generator.writeNumberField(fieldName, (Long) value);
			}
			// Integer and short type
			else if (value instanceof Integer || value instanceof Short)
			{
				generator.writeNumberField(fieldName, (Integer) value);
			}
			else if (value instanceof Double)
			{
				generator.writeNumberField(fieldName, (Double) value);
			}
			else if (value instanceof Float)
			{
				generator.writeNumberField(fieldName, (Float) value);
			}
			else if (value instanceof BigDecimal)
			{
				generator.writeNumberField(fieldName, (BigDecimal) value);
			}
			else if (value instanceof Byte)
			{
				generator.writeNumberField(fieldName, (Byte) value);
			}
			else if (value instanceof byte[])
			{
				generator.writeBinaryField(fieldName, (byte[]) value);
			}
			// Boolean type
			else if (value instanceof Boolean)
			{
				generator.writeBooleanField(fieldName, (Boolean) value);
			}
			// convert it as datetime.
			else if (value instanceof Date)
			{
				// "2012-02-02T17:05:07.864"
				generator.writeStringField(fieldName, dateFormat.format((Date) value));
			}
			// Only support string key/value.
			else if (value instanceof Map<?, ?>)
			{
				writeMap(generator, fieldName, (Map<String, Object>) value);
			}
			else if (value instanceof String[])
			{
				String[] stringArray = (String[]) value;
				generator.writeStartArray();
				for (String string : stringArray)
				{
					if (string == null)
					{
						generator.writeNull();
					}
					else
					{
						generator.writeString(string);
					}
				}
				generator.writeEndArray();

			}
			// Object type
			else
			{
				generator.writeObjectFieldStart(fieldName);
				CustomJSONConvertor convertor = CustomJSONProvider.getCustomJSONConvertor(value.getClass());
				convertor.toJson(generator, value);
				generator.writeEndObject();
			}
		}
	}

	
	protected void writeMap(JsonGenerator generator, String fieldName, Map stringMap) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (stringMap == null || stringMap.isEmpty())
			{
				return;
			}
			generator.writeObjectFieldStart(fieldName);
			Iterator<Map.Entry<Object, Object>> iter = stringMap.entrySet().iterator();
			// Convert Map to a object.
			while (iter.hasNext())
			{
				Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iter.next();
				// List object
				if (entry.getValue() instanceof List<?>)
				{
					writeList(generator, entry.getKey().toString(), (List<?>) entry.getValue());
				}
				// Null value
				else if (entry.getValue() == null)
				{
					generator.writeNullField(entry.getKey().toString());
				}
				// Others.
				else
				{
					writeSimpleValue(generator, entry.getKey().toString(), entry.getValue());
				}
			}
			generator.writeEndObject();
		}
		
	}

	protected void writeMap(JsonGenerator generator, Map stringMap) throws IOException
	{
		if (stringMap == null || stringMap.isEmpty())
		{
			return;
		}
		generator.writeStartObject();
		Iterator<Map.Entry<Object, Object>> iter = stringMap.entrySet().iterator();
		// Convert Map to a object.
		while (iter.hasNext())
		{
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iter.next();
			// List object
			if (entry.getValue() instanceof List<?>)
			{
				writeList(generator, entry.getKey().toString(), (List<?>) entry.getValue());
			}
			// Null value
			else if (entry.getValue() == null)
			{
				generator.writeNullField(entry.getKey().toString());
			}
			// Others.
			else
			{
				writeSimpleValue(generator, entry.getKey().toString(), entry.getValue());
			}
		}
		generator.writeEndObject();
	}

	
	/**
	 * Convert List to JSON stream.
	 * 
	 * @param generator
	 * @param nodeName
	 * @param objectList
	 * @throws IOException
	 */
	protected void writeList(JsonGenerator generator, String nodeName, Collection<?> objectList) throws IOException
	{
		if(this.fields==null || this.fields.contains(nodeName))
		{
			writeList(generator, nodeName, (List<?>) objectList);
		}
	}
	/**
	 * Convert List to JSON stream.
	 * 
	 * @param generator
	 * @param nodeName
	 * @param objectList
	 * @throws IOException
	 */
	protected void writeList(JsonGenerator generator, String nodeName, List<?> objectList) throws IOException
	{
		if(this.fields==null || this.fields.contains(nodeName))
		{
			if (objectList != null && !objectList.isEmpty())
			{
				generator.writeArrayFieldStart(nodeName);
				// As a array.
				Object firstObject = objectList.get(0);
				Class<?> listType = firstObject.getClass();
				CustomJSONConvertor convertor = CustomJSONProvider.getJDKObjectConvertor(listType);
				if (convertor == null)
				{
					if(firstObject instanceof Map)
					{
						int len = objectList.size();
						for (int index = 0; index < len; index++)
						{
							Map stringMap = (Map)objectList.get(index);
							writeMap(generator, stringMap);
						}
					}
					else
					{
						convertor = CustomJSONProvider.getCustomJSONConvertor(listType);
						int len = objectList.size();
						for (int index = 0; index < len; index++)
						{
							Object subObject = objectList.get(index);
							generator.writeStartObject();
							convertor.toJson(generator, subObject);
							generator.writeEndObject();
						}
					}
				}
				else
				{
					int len = objectList.size();
					for (int index = 0; index < len; index++)
					{
						Object subObject =  objectList.get(index);
						if (subObject == null)
						{
							generator.writeNull();
						}
						else
						{
							convertor.toJson(generator, subObject);
						}
					}
				}
				generator.writeEndArray();
			}
		}
	}

	/**
	 * Convert String array to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeStringArray(JsonGenerator generator, String fieldName, String[] value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null || value.length == 0)
			{
				return;
			}
			generator.writeArrayFieldStart(fieldName);
			for (String string : value)
			{
				if (string == null)
				{
					generator.writeNull();
				}
				else
				{
					generator.writeString(string);	
				}
			}
			generator.writeEndArray();
		}
	}
	
	
	/**
	 * Convert binary array to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeBinaryField(JsonGenerator generator, String fieldName, byte[] value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null || value.length == 0)
			{
				return;
			}
			generator.writeBinaryField(fieldName, value);
		}
	}
	/**
	 * Convert String/StringBuffer/StringBuilder to JSON stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeStringField(JsonGenerator generator, String fieldName, Object value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			String stringValue = null;
			if (value instanceof String)
			{
				stringValue = (String) value;
			}
			else if (value instanceof StringBuffer || value instanceof StringBuilder)
			{
				stringValue = value.toString();
			}
			generator.writeStringField(fieldName, stringValue);
		}
	}
	/**
	 * Convert Enum  to JSON stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeEnumField(JsonGenerator generator, String fieldName, Object value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			String stringValue = value.toString();
			generator.writeStringField(fieldName, stringValue);
		}
	}
	/**
	 * Convert Date to JSON stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeDateField(JsonGenerator generator, String fieldName, Date value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			String stringValue = dateFormat.format(value);
			generator.writeStringField(fieldName, stringValue);
		}
	}
	
	/**
	 * Convert Date to JSON stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeDateField(JsonGenerator generator, String fieldName, Date value, String formatString) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			DateFormat dateFormat = new SimpleDateFormat(formatString);
			String stringValue = dateFormat.format(value);
			generator.writeStringField(fieldName, stringValue);
		}
	}
	
	/**
	 * Convert Boolean to JSON stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeBooleanField(JsonGenerator generator, String fieldName, Boolean value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeBooleanField(fieldName, value);
		}
	}
	/**
	 * Convert Byte to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeNumberField(JsonGenerator generator, String fieldName, Byte value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeNumberField(fieldName, value);
		}
	}
	/**
	 * Convert Integer to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeNumberField(JsonGenerator generator, String fieldName, Integer value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeNumberField(fieldName, value);
		}
	}
	/**
	 * Convert Long to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeNumberField(JsonGenerator generator, String fieldName, Long value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeNumberField(fieldName, value);
		}
	}
	/**
	 * Convert Float to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeNumberField(JsonGenerator generator, String fieldName, Float value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeNumberField(fieldName, value);
		}
	}
	/**
	 * Convert Double to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeNumberField(JsonGenerator generator, String fieldName, Double value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeNumberField(fieldName, value);
		}
	}
	/**
	 * Convert BigDecimal to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeNumberField(JsonGenerator generator, String fieldName, BigDecimal value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeNumberField(fieldName, value);
		}
	}
	/**
	 * Convert Short to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeNumberField(JsonGenerator generator, String fieldName, Short value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			generator.writeNumberField(fieldName, value);
		}
		
	}
	/**
	 * Convert char array to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeCharArray(JsonGenerator generator, String fieldName, char[] value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null || value.length == 0)
			{
				return;
			}
			generator.writeArrayFieldStart(fieldName);
	        for (int i = 0, len = value.length; i < len; ++i) 
	        {
	        	generator.writeString(value, i, 1);
	        }
			generator.writeEndArray();
		}
	}
	/**
	 * Convert int array to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeIntArray(JsonGenerator generator, String fieldName, int[] value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			generator.writeArrayFieldStart(fieldName);
	        for (int i = 0, len = value.length; i < len; ++i) 
	        {
	        	generator.writeNumber(value[i]);
	        }
			generator.writeEndArray();
		}
	}
	/**
	 * Convert integer array to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeIntegerArray(JsonGenerator generator, String fieldName, Integer[] value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			generator.writeArrayFieldStart(fieldName);
	        for (int i = 0, len = value.length; i < len; ++i) 
	        {
	        	generator.writeNumber(value[i]);
	        }
			generator.writeEndArray();
		}
	}
	/**
	 * Convert long array to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeLongArray(JsonGenerator generator, String fieldName, Long[] value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			generator.writeArrayFieldStart(fieldName);
	        for (int i = 0, len = value.length; i < len; ++i) 
	        {
	        	generator.writeNumber(value[i]);
	        }
			generator.writeEndArray();
		}
	}
	/**
	 * Convert long array to JSON field for output stream.
	 *
	 * @param generator
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws JsonGenerationException
	 */
	protected void writeLongArray(JsonGenerator generator, String fieldName, long[] value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			generator.writeArrayFieldStart(fieldName);
	        for (int i = 0, len = value.length; i < len; ++i) 
	        {
	        	generator.writeNumber(value[i]);
	        }
			generator.writeEndArray();
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
	protected void writeObjectField(JsonGenerator generator, String fieldName, Object value) throws IOException
	{
		if(this.fields==null || this.fields.contains(fieldName))
		{
			if (value == null)
			{
				return;
			}
			if (value instanceof List<?>)
			{
				writeList(generator, fieldName, (List<?>) value);
			}
			else if (value instanceof Map<?, ?>)
			{
				writeMap(generator, fieldName, (Map<?, ?>) value);
			}
			else if (value instanceof Class<?>)
			{
				//Do nothing.
			}
			else
			{
				generator.writeObjectFieldStart(fieldName);
				CustomJSONConvertor convertor = CustomJSONProvider.getCustomJSONConvertor(value.getClass());
				convertor.toJson(generator, value);
				generator.writeEndObject();
			}
		}
	}
	

	public Set<String> getFields()
	{
		return fields;
	}

	public void setFields(Set<String> fields)
	{
		this.fields = fields;
	}
	
}
