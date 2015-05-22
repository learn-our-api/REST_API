package com.cxf.restapis.framework.json.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomJSONObjectCodec.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: CustomJSONObjectCodec.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-11		michael.mao		Initial.
 *  
 * </pre>
 */
public class CustomObjectMapper extends ObjectMapper
{
  
	@Override
	public <T> T readValue(JsonParser jp, Class<T> valueType) throws IOException, JsonProcessingException
	{
		return (T) _readValue(getDeserializationConfig(), jp, _typeFactory.constructType(valueType));
	}

 	
    /**
     * Actual implementation of value reading+binding operation.
     */
	protected Object _readValue(DeserializationConfig cfg, JsonParser jp, JavaType valueType)
		throws IOException, JsonParseException, JsonMappingException
	{
		/* First: may need to read the next token, to initialize
		 * state (either before first read from parser, or after
		 * previous token has been cleared)
		 */
		Object result;
		JsonToken t = _initForReading(jp);
		if (t == JsonToken.VALUE_NULL) 
		{
			// [JACKSON-643]: Ask JsonDeserializer what 'null value' to use:
			DeserializationContext ctxt = createDeserializationContext(jp, cfg);
			result = _findRootDeserializer(ctxt, valueType).getNullValue();
		} 
		else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) 
		{
            result = null;
        } 
		else 
		{ // pointing to event other than null
			DeserializationContext ctxt = createDeserializationContext(jp, cfg);
			JsonDeserializer<Object> deser = _findRootDeserializer(ctxt, valueType);
			// ok, let's get the value
			if (cfg.useRootWrapping()) 
			{
				result = _unwrapAndDeserialize(jp, ctxt, cfg, valueType, deser);
			} 
			else 
			{
				result = readObjectOrArray(jp, t, ctxt, deser);
			}
		}
		// Need to consume the token too
		jp.clearCurrentToken();
		return result;
	}


	private Object readObjectOrArray(JsonParser jp, JsonToken t, DeserializationContext ctxt,
			JsonDeserializer<Object> deser) throws IOException, JsonParseException, JsonProcessingException
	{
		Object result = null;
		if (t == JsonToken.START_ARRAY)
		{
			List<Object> resultArray = new ArrayList<Object>();
			JsonToken token = jp.nextToken();
			//Get first object
			while (true)
			{
				CustomJsonThreadLocal.incrementThreadLocalIndex();
				if (token == JsonToken.START_OBJECT) 
				{
					//Get first FieldName in object
					jp.nextToken();
					Object newResult = ((CustomJsonDeserializer) deser).deserializeFromObject(jp, ctxt);
					resultArray.add(newResult);
				}
				else if (token == JsonToken.END_ARRAY)
				{
					result = resultArray;
					CustomJsonThreadLocal.cleanThreadLocalIndex();
					break;
				}
				else	// parser simple data type in array.
				{
					resultArray.add(deser.deserialize(jp, ctxt));
				}
				token = jp.nextToken();
			}
			if (resultArray.isEmpty())
			{
				result = null;	
			}
		}
		else
		{
			result = deser.deserialize(jp, ctxt);
		}
		return result;
	}
	
    /**
     * Method called to locate deserializer for the passed root-level value.
     */
	protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext ctxt, JavaType valueType)
		throws JsonMappingException
	{
		// First: have we already seen it?
		JsonDeserializer<Object> deser = _rootDeserializers.get(valueType);
		if (deser != null) 
		{
			return deser;
		}
		// Nope: need to ask provider to resolve it
		deser = ctxt.findRootValueDeserializer(valueType);
		if (deser == null) 
		{ // can this happen?
			throw new JsonMappingException("Can not find a deserializer for type "+valueType);
		}
		if (deser instanceof BeanDeserializer)
		{
			deser = new CustomJsonDeserializer((BeanDeserializerBase) deser);
		}
		_rootDeserializers.put(valueType, deser);
		return deser;
	}
    
    /**
     * Method called to ensure that given parser is ready for reading
     * content for data binding.
     *
     * @return First token to be used for data binding after this call:
     *  can never be null as exception will be thrown if parser can not
     *  provide more tokens.
     *
     * @throws IOException if the underlying input source has problems during
     *   parsing
     * @throws JsonParseException if parser has problems parsing content
     * @throws JsonMappingException if the parser does not have any more
     *   content to map (note: Json "null" value is considered content;
     *   enf-of-stream not)
     */
    protected JsonToken _initForReading(JsonParser jp)
        throws IOException, JsonParseException, JsonMappingException
    {
        /* First: must point to a token; if not pointing to one, advance.
         * This occurs before first read from JsonParser, as well as
         * after clearing of current token.
         */
        JsonToken token = jp.getCurrentToken();
        if (token == null) 
        {
            // and then we must get something...
        	token = jp.nextToken();
            if (token == null) 
            {
                /* [JACKSON-546] Throw mapping exception, since it's failure to map,
                 *   not an actual parsing problem
                 */
                throw JsonMappingException.from(jp, "No content to map due to end-of-input");
            }
        }
        return token;
    }
	
	@Override
	public <T> T readValue(JsonParser parser, TypeReference<?> arg1) throws IOException, JsonProcessingException
	{
		return null;
	}


	@Override
	public void writeValue(JsonGenerator generator, Object object) throws IOException, JsonProcessingException
	{
		if (object == null)
		{
			return;
		}
		final Class<?> modelClass = object.getClass();
		CustomJSONConvertor convertor = CustomJSONProvider.getCustomJSONConvertor(modelClass);
		convertor.toJson(generator, object);
		
	}

}

/*
*$Log: av-env.bat,v $
*/
