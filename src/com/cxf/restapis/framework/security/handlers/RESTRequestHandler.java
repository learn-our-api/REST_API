package com.cxf.restapis.framework.security.handlers;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: RestRequestHandler.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: RestRequestHandler.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jun 20, 2012		evan.cai		Initial.
 *  
 * </pre>
 */
public interface RESTRequestHandler
{
	public Response handleRequest(Message paramMessage, ClassResourceInfo paramClassResourceInfo) throws Exception;
}

/*
*$Log: av-env.bat,v $
*/