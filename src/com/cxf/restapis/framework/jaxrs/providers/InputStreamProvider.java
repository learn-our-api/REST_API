package com.cxf.restapis.framework.jaxrs.providers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JSONProvider.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
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
@Consumes("application/octet-stream")
@Provider
public class InputStreamProvider implements MessageBodyWriter<InputStream>, MessageBodyReader<InputStream>
{

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mt)
	{
		return InputStream.class.isAssignableFrom(type);
	}

	@Override
	public InputStream readFrom(Class<InputStream> clazz, Type t, Annotation[] a, MediaType mt,
			MultivaluedMap<String, String> headers, InputStream is) throws IOException
	{
		return new BufferedInputStream(is);
	}

	@Override
	public long getSize(InputStream arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4)
	{
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mt)
	{
		return InputStream.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(InputStream is, Class<?> clazz, Type t, Annotation[] a, MediaType mt,
			MultivaluedMap<String, Object> headers, OutputStream output) throws IOException, WebApplicationException
	{
		try
		{
			byte[] buffer = new byte[4096];
			int len = 0;
			while ((len = is.read(buffer)) > 0)
			{
				output.write(buffer, 0, len);
			}
			output.flush();
		}
		finally
		{
			is.close();
		}
	}

}

/*
 * $Log: av-env.bat,v $
 */