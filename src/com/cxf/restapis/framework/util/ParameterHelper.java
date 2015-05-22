package com.cxf.restapis.framework.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.jaxrs.impl.UriInfoImpl;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.model.URITemplate;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.cxf.restapis.framework.constants.APIConstants;

/**
 * <pre>
 * 
 * Accela Automation
 * File: ParameterHelper.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2014
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: ParameterHelper.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * 2012-6-4		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ParameterHelper
{

	/**
	 * Convert Query Parameter to Map<String, String> from request
	 * 
	 * @param message
	 * @return Map<String, String>
	 */
	public static Map<String, String> getQueryParametersFromRequest(Message message)
	{
		Map<String, String> parameters = new HashMap<String, String>();
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);

		Iterator<String> itr = request.getParameterMap().keySet().iterator();

		while (itr.hasNext())
		{
			String key = itr.next();
			String value = request.getParameter(key);
			parameters.put(key, value);
		}

		return parameters;
	}

	/**
	 * Gets the path parameter.
	 * 
	 * @param message the message
	 * @param pathTemplate the path template
	 * 
	 * @return the path parameter
	 */
	public static String getPathParameter(Message message, String pathTemplate)
	{
		String pathParamValue = null;

		if (!ValidationUtil.isEmpty(pathTemplate))
		{
			OperationResourceInfo ori = message.getExchange().get(OperationResourceInfo.class);
			URITemplate t1 = ori.getClassResourceInfo().getURITemplate();
			URITemplate t2 = ori.getURITemplate();

			UriInfo uriInfo = new UriInfoImpl(message, null);
			MultivaluedMap<String, String> map = new MetadataMap<String, String>();

			t1.match(uriInfo.getPath(), map);
			String str = map.get(URITemplate.FINAL_MATCH_GROUP).get(0);
			t2.match(str, map);

			if (map.containsKey(pathTemplate) && !ValidationUtil.isEmpty(map.get(pathTemplate)))
			{
				pathParamValue = map.get(pathTemplate).get(0);
			}
		}

		return pathParamValue;
	}

	/**
	 * Validate and reset default offset.
	 *
	 * @param offset the offset
	 * @return the int
	 */
	public static int validatePageOffset(int offset)
	{
		offset = offset < 0 ? APIConstants.DEFAULT_OFFSET : offset;

		return offset;
	}
	
	/**
	 * Validate and reset default or max limit.
	 *
	 * @param limit the limit
	 * @return the int
	 */
	public static int validatePageLimit(int limit)
	{
		// the minimized default limit is 25
		if (limit <= 0)
		{
			limit = APIConstants.DEFAULT_LIMIT;
		}
		else if (limit > APIConstants.MAX_LIMIT)
		{// the max limit is 1000
			limit = APIConstants.MAX_LIMIT;
		}
		return limit;
	}
	
	public static String setErrorMessageParameter(String errorMsg, String[] parameters)
	{
		if(!ValidationUtil.isEmpty(errorMsg) && !ValidationUtil.isEmpty(parameters))
		{
			for(int i=0; i<parameters.length; i++)
			{
				errorMsg = errorMsg.replace("{"+i+"}", parameters[i]);
			}
		}
		return errorMsg;
	}
}

/*
 * $Log: av-env.bat,v $
 */
