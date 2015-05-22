package com.cxf.restapis.framework.event.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: EventAfter.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2013
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: EventAfter.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-6-1		dylan.liang		Initial.
 * 
 * </pre>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventAfter
{
	String eventName();

	Class<?> pretreatClass();

	/** After event doesn't be stop by default */
	boolean stopByError() default false;
}

/*
 * $Log: av-env.bat,v $
 */