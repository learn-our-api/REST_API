package com.cxf.restapis.framework.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: FID.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012
 * 
 *  Description:
 *  The FID annotation which is to be used on REST service class or method.
 * 
 *  Notes:
 * 	$Id: FID.java 136692 2009-06-29 09:48:08Z ACHIEVO\hikelee.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2009-6-28		steven.yan		Initial.
 *  
 * </pre>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FID
{
	/**
	 * The FID value to set.	
	 * 
	 * @return the FID value set.
	 */
	String value();
}

/*
*$Log: av-env.bat,v $
*/