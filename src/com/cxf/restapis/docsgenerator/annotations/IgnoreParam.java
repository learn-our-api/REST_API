package com.cxf.restapis.docsgenerator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: IgnoreParam.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: IgnoreParam.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-11-23		dylan.liang		Initial.
 *  
 * </pre>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreParam
{

}

/*
*$Log: av-env.bat,v $
*/