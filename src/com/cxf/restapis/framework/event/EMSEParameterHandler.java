package com.cxf.restapis.framework.event;

import java.util.Hashtable;

import org.apache.cxf.message.Message;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: EMSEParameterHandler.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: EMSEParameterHandler.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-6-4		dylan.liang		Initial.
 * 
 * </pre>
 */
public interface EMSEParameterHandler
{

	public Hashtable<?, ?> pretreat(Object classObject) throws Exception;
	
	@Deprecated
	public Hashtable<?, ?> pretreat(final Message message) throws Exception;
}

/*
 * $Log: av-env.bat,v $
 */
