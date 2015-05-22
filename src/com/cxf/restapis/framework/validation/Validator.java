package com.cxf.restapis.framework.validation;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: Validator.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: Validator.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-7-7		dylan.liang		Initial.
 * 
 * </pre>
 */
public interface Validator
{
	public Response validate(Message message, ClassResourceInfo classResourceInfo) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */