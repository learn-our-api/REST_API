package com.cxf.restapis.framework.json.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomJsonDeserializer.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: CustomJsonDeserializer.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-23		michael.mao		Initial.
 *  
 * </pre>
 */
public class CustomJsonDeserializer extends BeanDeserializer
{

	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = -5862811829915837037L;

	public CustomJsonDeserializer(BeanDeserializerBase src)
	{
		super(src);
	}

	/**
	 * Deserialize JSON to java object, and it can support custom deserializing process.
	 */
	@Override
	public Object deserializeFromObject(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException
	{
		Object bean = this._valueInstantiator.createUsingDefault(ctxt);
		final Class<?> modelClass = bean.getClass();
		CustomJSONConvertor convertor = CustomJSONProvider.getCustomJSONConvertor(modelClass);
		Map<String, String> customJsonMapping = null;
		Map<String, String> customJsonAliasMapping = null;
		
		ICustomConvertor customConvertor = null;
		boolean isCustomAllFields = false;
		if (convertor instanceof ICustomAllFields)
		{
			isCustomAllFields = true;
			customConvertor = (ICustomConvertor) convertor;
			customJsonMapping = customConvertor.getCustomJsonMapping();
			customJsonAliasMapping = customConvertor.getCustomJsonAliasMapping();
		}
		else if (convertor != null && convertor.getCustomConvertor() != null)
		{
			customJsonMapping = convertor.getCustomConvertor().getCustomJsonMapping();
			customJsonAliasMapping = convertor.getCustomConvertor().getCustomJsonAliasMapping();
			if (customJsonMapping != null && !customJsonMapping.isEmpty())
			{
				customConvertor = convertor.getCustomConvertor();
			}
			else
			{
				customJsonMapping = null;
			}
		}
		//Search result by PK and instead of bean.
		Object model = CustomJSONProvider.getOriginalModel();
		if(model != null && model.getClass().getName().equals(bean.getClass().getName()))
		{
			bean = model;
		}
		//handle array
		else if (model != null && (model instanceof List<?>)
				&& null != ((List<?>)model).get(CustomJsonThreadLocal.getThreadLocalIndex())
				&& ((List<?>)model).get(CustomJsonThreadLocal.getThreadLocalIndex()).getClass().getName().equals(bean.getClass().getName()))
		{
			bean = ((List<?>)model).get(CustomJsonThreadLocal.getThreadLocalIndex());
		}
		parseAndSet(jp, ctxt, bean, customJsonMapping, customJsonAliasMapping, customConvertor, isCustomAllFields);
		return bean;
	}

	private void parseAndSet(JsonParser jp, DeserializationContext ctxt, Object bean,
			Map<String, String> customJsonMapping, Map<String, String> customJsonAliasMapping, ICustomConvertor customConvertor, boolean isCustomAllFields)
			throws IOException, JsonParseException, JsonProcessingException
	{
		
		for (; jp.getCurrentToken() != JsonToken.END_OBJECT; jp.nextToken())
		{
			String propName = jp.getCurrentName();
			boolean isHandleJsonAlias = false;
			String origPropName = null;
			jp.nextToken();
			//Check to see if all fields were customized.
			if (isCustomAllFields)
			{
				origPropName = null;
				//If there exist any customized JSON key.
				if (customJsonMapping != null)
				{
					origPropName = customJsonMapping.get(propName);
				}
				if(origPropName == null && customJsonAliasMapping != null)
				{
					origPropName = customJsonAliasMapping.get(propName);
					if(origPropName != null)
					{
						isHandleJsonAlias = true;
					}
				}
				//By default, JSON key name is equal to model property name.
				if (origPropName == null)
				{
					origPropName = propName;
				}
				customDeserializeAndSet(jp, ctxt, bean, customConvertor, origPropName, propName, isHandleJsonAlias);
				continue;
			}
			//Only some fields were customized.
			else if (customJsonMapping != null || customJsonAliasMapping != null)
			{
				if (customJsonMapping != null)
				{
					origPropName = customJsonMapping.get(propName);
				}
				if(origPropName == null && customJsonAliasMapping != null)
				{
					origPropName = customJsonAliasMapping.get(propName);
					if(origPropName != null)
					{
						isHandleJsonAlias = true;
					}
				}
				if (origPropName != null)
				{
					customDeserializeAndSet(jp, ctxt, bean, customConvertor, origPropName, propName, isHandleJsonAlias);
					continue;
				}
			}
			beanDeserializeAndSet(jp, ctxt, bean, propName);
		}
	}

	private void beanDeserializeAndSet(JsonParser jp, DeserializationContext ctxt, Object bean, String propName)
			throws IOException, JsonProcessingException
	{
		SettableBeanProperty prop = this._beanProperties.find(propName);
		if (prop != null)
		{
			try
			{
				//Convert string to StringBuffer type.
				Object value = prop.deserialize(jp, ctxt);
				//Object value = deserializeValue1(jp, ctxt);
				prop.set(bean, value);
				//prop.deserializeAndSet(jp, ctxt, bean);
			}
			catch (Exception e)
			{
				wrapAndThrow(e, bean, propName, ctxt);
			}

		}
		else
		{
			handleUnknownProperty(jp, ctxt, bean, propName);
		}
	}

	private void customDeserializeAndSet(JsonParser jp, DeserializationContext ctxt, Object bean,
			ICustomConvertor customConvertor, String origPropName, String propName, boolean isHandleJsonAlias) throws IOException
	{
		//Object value = deserializeValue1(jp, ctxt, dataType);
		//If original property name does not exist in java bean. 
		if (origPropName.length() == 0 || origPropName.charAt(0) == ' ')
		{
			//Get value of simple data type.
			Object value = deserializeValue1(jp, ctxt);
			customConvertor.toObject(bean, propName, value);
		}
		//Field type is simple model object.
		else if (origPropName.charAt(0) == '@')
		{
			String type = null;
			String classType = null;
			if(origPropName.indexOf("<")>0)
			{
				String typePrefix = origPropName.substring(1, origPropName.indexOf("<"));
				type = typePrefix.substring(typePrefix.lastIndexOf(".")+1);
			}
			//java.util.List<com.accela.aa.inspection.guidesheet.RGuideSheetItemStatusGroupModel>
			if(type!=null && type.equalsIgnoreCase("List"))
			{ 
			      String subDataType = origPropName.substring(origPropName.indexOf("<")+1, origPropName.indexOf(">"));
			      classType = subDataType;
			}
			else
			{
			      classType = origPropName.substring(1);
			}
			Class<?> classObject = null;
			try
			{
				classObject = Class.forName(classType);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			Object value = CustomJSONProvider.getObjectMapper().readValue(jp, classObject);
			
			customConvertor.toObject(bean, propName, value);
			
		}
		//Original property name exists in java bean.
		else
		{
			//Handle alias
			if(isHandleJsonAlias)
			{
				beanDeserializeAndSet(jp, ctxt, bean, origPropName);
			}
			else
			{
				setSimpleField(jp, ctxt, bean, customConvertor, origPropName, propName);
			}
		}
	}
	
	

	private void setSimpleField(JsonParser jp, DeserializationContext ctxt, Object bean,
			ICustomConvertor customConvertor, String origPropName, String propName) throws IOException,
			JsonProcessingException
	{
		SettableBeanProperty prop = this._beanProperties.find(origPropName);
		//Original property name exists in java bean.
		if (prop != null)
		{
			try
			{
				Object value = prop.deserialize(jp, ctxt);
				customConvertor.toObject(bean, propName, value);
			}
			catch (Exception e)
			{
				wrapAndThrow(e, bean, origPropName, ctxt);
			}

		}
		else
		{
			//Handle extra unknown JSON key.
			handleUnknownProperty(jp, ctxt, bean, propName);
		}
	}	

    /**
     * This method is needed by some specialized bean deserializers,
     * and also called by some {@link #deserializeAndSet} implementations.
     *<p>
     * Pre-condition is that passed parser must point to the first token
     * that should be consumed to produce the value (the only value for
     * scalars, multiple for Objects and Arrays).
     *<p> 
     * Note that this method is final for performance reasons: to override
     * functionality you must override other methods that call this method;
     * this method should also not be called directly unless you really know
     * what you are doing (and probably not even then).
     */
	public Object deserializeValue1(JsonParser jp, DeserializationContext ctxt) throws IOException,
			JsonProcessingException
	{
		JsonToken t = jp.getCurrentToken();

        if (t == null) 
        {
        	throw ctxt.mappingException(getBeanClass());
        }
		if (t == JsonToken.VALUE_NULL)
		{
			return null;
		}
        // and then others, generally requiring use of @JsonCreator
        switch (t) 
        {
        case VALUE_STRING:
        	return jp.getText();
            //return deserializeFromString(jp, ctxt);
        case VALUE_NUMBER_INT:
            return jp.getIntValue();
        case VALUE_NUMBER_FLOAT:
        	return jp.getFloatValue();
        case VALUE_EMBEDDED_OBJECT:
            return jp.getEmbeddedObject();
        case VALUE_TRUE:
        case VALUE_FALSE:
            return deserializeFromBoolean(jp, ctxt);
        case START_ARRAY:
            // these only work if there's a (delegating) creator...
            return deserializeFromArray(jp, ctxt);
        case FIELD_NAME:
        default:
            throw ctxt.mappingException(getBeanClass());
        }
	}
	
	
}

/*
*$Log: av-env.bat,v $
*/
