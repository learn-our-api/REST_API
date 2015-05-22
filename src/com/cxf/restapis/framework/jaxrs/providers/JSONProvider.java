package com.cxf.restapis.framework.jaxrs.providers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.cxf.restapis.framework.json.impl.CustomJSONProvider;
import com.cxf.restapis.framework.json.impl.CustomJsonThreadLocal;
import com.cxf.restapis.framework.json.impl.CustomObjectMapper;
import com.cxf.restapis.framework.json.impl.NaturePropertyNamingStragegy;
import com.cxf.restapis.framework.json.views.Views;
import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.model.RestThreadLocal;
import com.cxf.restapis.framework.util.Constants;
import com.cxf.restapis.framework.util.CustomConvertorDefine;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.cxf.restapis.framework.util.WebThreadLocal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JSONProvider.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  This class be designed to serialization or deserialization between domain model and JSON object.
 * 
 *  Notes:
 * 	$Id: JSONProvider.java 72642 2012-04-06 20:01:57Z ACHIEVO\Evan.Cai $ 
 * 
 *  Revision History
 *  2013-1-9		dylan.liang		Initial.
 * 
 * </pre>
 */
public class JSONProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object>
{

	/**
	 * Return the size of body.
	 * 
	 * @param entry the object to return
	 * @param type the class of return parameter
	 * @param genericType the geniric type of return parameter
	 * @param annotations the method annotations
	 * @param mediaType the media type of current response
	 * @return the size of body
	 */
	@Override
	public long getSize(Object entry, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return -1;
	}

	/**
	 * Judge whether this provider supports the returned type.
	 * 
	 * @param type the class of return parameter
	 * @param genericType the geniric type of return parameter
	 * @param annotations the method annotations
	 * @param mediaType the media type of current response
	 * @return the value to judge whether this provider supports the returned type
	 */
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return (!MultipartBody.class.isAssignableFrom(type) && !InputStream.class.isAssignableFrom(type));
	}

	/**
	 * Process request with json format for array or collection object.
	 * 
	 * @param type the class of method parameter
	 * @param genericType the geniric type of method parameter
	 * @param annotations the method annotations
	 * @param mediaType the media type of current request
	 * @param httpHeaders the request http headers
	 * @param entityStream the http request stream
	 * @return the collection object for current request
	 */
	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException,
			WebApplicationException, JsonParseException
	{
		String currentUser = WebThreadLocal.getCurrentUser();
		Class<?> jsonView = findView(annotations);

		if (jsonView == null)
		{
			jsonView = assignJsonView(currentUser);
		}
		Object result = null;
		try
		{

			Class<?> modelType = type;
			if (List.class.isAssignableFrom(type))
			{
				ParameterizedTypeImpl contentType = (ParameterizedTypeImpl) genericType;
				Type[] actualContentTypes = contentType.getActualTypeArguments();
				if (actualContentTypes != null)
				{
					if (actualContentTypes[0] instanceof ParameterizedTypeImpl)
					{
						ParameterizedTypeImpl rawType = (ParameterizedTypeImpl) actualContentTypes[0];
						modelType = rawType.getRawType();
					}
					else
					{
						modelType = (Class<?>) actualContentTypes[0];
					}
				}
			}

			if (!ValidationUtil.isEmpty(RestThreadLocal.getRootModelConvertor())
					&& (!ValidationUtil.isEmpty(RestThreadLocal.getRootModelConvertor().get("request")) || (!ValidationUtil
							.isEmpty(RestThreadLocal.getModelConvertors().get(Constants.CONVERTOR_REQUESTS)))))
			{
				result = toModel(entityStream, modelType);
			}
			else
			{
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setTimeZone(getTimeZone(WebThreadLocal.getServiceProviderCode()));
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JavaType entityType = TypeFactory.defaultInstance().constructType(genericType);
				if (jsonView != null)
				{
					result = objectMapper.reader(entityType).withView(jsonView).readValue(entityStream);
				}
				else
				{
					result = objectMapper.reader(entityType).readValue(entityStream);
				}
			}
		}
		catch (Exception e)
		{
			if (e instanceof com.fasterxml.jackson.core.JsonParseException)
			{

				throw (JsonParseException) e;
			}
			else if (e instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException)
			{
				throw (com.fasterxml.jackson.databind.exc.InvalidFormatException) e;
			}
			// else if(e instanceof IllegalArgumentException)
			// {
			// throw (IllegalArgumentException)e;
			// }
			else
			{
			}
		}
		return result;
	}

	public Object toModel(InputStream entityStream, Class<?> modelType) throws IOException, JsonParseException,
			JsonProcessingException
	{

		// if(entityStream instanceof org.apache.catalina.connector.CoyoteInputStream && entityStream.available() ==0)
		// {
		// throw new IllegalArgumentException("The request cannot be fulfilled due to bad syntax.");
		// }
		JsonFactory jsonFactory = new JsonFactory();
		CustomObjectMapper convert = builderCustomObject();
		CustomJSONProvider.setObjectMapperThreadLocal(convert);
		jsonFactory.setCodec(convert);
		JsonParser parser = jsonFactory.createParser(entityStream);
		CustomJSONProvider.setRootModelConvertor(RestThreadLocal.getRootModelConvertor().get("request"));
		CustomJSONProvider.setMethodConvertorThreadLocal(RestThreadLocal.getModelConvertors().get(
			Constants.CONVERTOR_REQUESTS));
		CustomJSONProvider.setContext(CustomConvertorDefine.getCustomConvertor());
		Object result = null;
		try
		{
			result = parser.readValueAs(modelType);
		}
		finally
		{
			CustomJSONProvider.setContext(null);
			CustomJSONProvider.setObjectMapperThreadLocal(null);
			CustomJSONProvider.setRootModelConvertor(null);
			CustomJSONProvider.setMethodConvertorThreadLocal(null);
			CustomJSONProvider.setOriginalModel(null);
		}
		return result;
	}

	private void toJson(OutputStream outputStream, Object entry, ObjectMapper convert, Set<String> fields)
			throws IOException
	{
		JsonFactory jsonFactory = new JsonFactory();
		CustomJSONProvider.setObjectMapperThreadLocal((CustomObjectMapper) convert);
		jsonFactory.setCodec(convert);
		JsonGenerator generator = null;
		try
		{
			generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
			CustomJSONProvider.setRootModelConvertor(RestThreadLocal.getRootModelConvertor().get("response"));
			CustomJSONProvider.setMethodConvertorThreadLocal(RestThreadLocal.getModelConvertors().get(
				Constants.CONVERTOR_RESPONSES));
			CustomJSONProvider.setContext(CustomConvertorDefine.getCustomConvertor());
			CustomJsonThreadLocal.setFields(fields);
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
			CustomJSONProvider.setContext(null);
			CustomJSONProvider.setObjectMapperThreadLocal(null);
			CustomJSONProvider.setRootModelConvertor(null);
			CustomJSONProvider.setMethodConvertorThreadLocal(null);
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

	public CustomObjectMapper builderCustomObject()
	{
		CustomObjectMapper convert = new CustomObjectMapper();
		convert.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		convert.setTimeZone(getTimeZone(WebThreadLocal.getServiceProviderCode()));
		convert.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
		convert.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		convert.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		convert.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		convert.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		// disable USE_ANNOTATIONS
		convert.disable(MapperFeature.USE_ANNOTATIONS);
		convert.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
		convert.setPropertyNamingStrategy(new NaturePropertyNamingStragegy());
		return convert;
	}

	/**
	 * Judge whether this provider supports the body parameter type.
	 * 
	 * @param type the class of return parameter
	 * @param genericType the generic type of return parameter
	 * @param annotations the method annotations
	 * @param mediaType the media type of current response
	 * @return the value to judge whether this provider supports the returned type
	 */
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return (!type.equals(MultipartBody.class) && !type.equals(InputStream.class));
	}

	/**
	 * Parse the entry to JSON and write to outstream
	 */
	@Override
	public void writeTo(Object entry, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException,
			WebApplicationException
	{
		try
		{
			if (RestThreadLocal.isUseCustomConvertor())
			{
				ObjectMapper objectMapper = write2OutputStream(entry, annotations, entityStream, true);
				if (entry instanceof ResponseModel)
				{
					ResponseModel result = (ResponseModel) entry;
					ByteArrayOutputStream output = new ByteArrayOutputStream();
					toJson(output, result, objectMapper, result.getFields());
					entityStream.write(output.toString().getBytes());
				}
				else
				{
					ByteArrayOutputStream output = new ByteArrayOutputStream();
					toJson(output, entry, objectMapper, null);
					entityStream.write(output.toString().getBytes());
				}
			}
			else
			{
				write2OutputStream(entry, annotations, entityStream, false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			RestThreadLocal.clean();
			CustomJsonThreadLocal.clean();
		}

	}

	/**
	 * write to outputStream.
	 * 
	 * @param entry
	 * @param annotations
	 * @param entityStream
	 * @throws IOException
	 */
	private ObjectMapper write2OutputStream(Object entry, Annotation[] annotations, OutputStream entityStream,
			boolean isCustomConvertor) throws IOException
	{
		Class<?> jsonView = findView(annotations);
		ObjectMapper objectMapper = null;
		if (isCustomConvertor)
		{
			objectMapper = new CustomObjectMapper();
		}
		else
		{
			objectMapper = new ObjectMapper();
		}

		objectMapper.setDateFormat(new ISO8601DateFormat());
		objectMapper.setTimeZone(getTimeZone(WebThreadLocal.getServiceProviderCode()));
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		if (entry instanceof ResponseModel)
		{
			Set<String> fields = ((ResponseModel) entry).getFields();
			Class<?> resultType = ((ResponseModel) entry).getResultType();
			String currentUser = ((ResponseModel) entry).getCurrentUser();
			boolean isWriteNullValue = ((ResponseModel) entry).isWriteNullValue();

			if (!ValidationUtil.isEmpty(currentUser))
			{
				jsonView = assignJsonView(currentUser);
			}

			if (!ValidationUtil.isEmpty(fields))
			{
				objectMapper.addMixInAnnotations(resultType, PropertyFilterMixIn.class);
				fields.add("id");// Resource id always be returned.
				FilterProvider filters = new SimpleFilterProvider().addFilter("filter properties by name",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
				objectMapper.setFilters(filters);
			}
			if (isWriteNullValue)
			{
				objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
			}
		}
		if (!isCustomConvertor)
		{
			if (jsonView != null)
			{
				objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
				objectMapper.writerWithView(jsonView).writeValue(entityStream, entry);
			}
			else
			{
				objectMapper.writeValue(entityStream, entry);
			}
		}
		return objectMapper;
	}

	private TimeZone getTimeZone(String servProvCode)
	{
		TimeZone timeZone = TimeZone.getTimeZone("CTT");
		return timeZone;
	}

	private Class<?> assignJsonView(String callerID)
	{
		Class<?> jsonView = Views.CitizenView.class; // default API view

		if (!"".equals(callerID))
		{
			jsonView = Views.AgencyView.class;
		}

		return jsonView;
	}

	private Class<?> findView(Annotation[] annotations)
	{
		Class<?> view = null;

		for (Annotation annotation : annotations)
		{
			if (annotation instanceof JsonView)
			{
				JsonView jsonView = (JsonView) annotation;
				Class<?>[] views = jsonView.value();

				if (views.length > 1)
				{
					StringBuilder s = new StringBuilder("Multiple @JsonView's can not be used on a JAX-RS method. Got ");
					s.append(views.length).append(" views: ");
					for (int i = 0; i < views.length; i++)
					{
						if (i > 0)
						{
							s.append(", ");
						}
						s.append(views[i].getName());
					}

				}

				view = views[0];
			}
		}

		return view;
	}

	@JsonFilter("filter properties by name")
	class PropertyFilterMixIn
	{

	}
}

/*
 * $Log: av-env.bat,v $
 */
