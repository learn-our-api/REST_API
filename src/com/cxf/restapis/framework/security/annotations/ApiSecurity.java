package com.cxf.restapis.framework.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.cxf.restapis.framework.constants.APIConstants.Applicability;

/**
 * <pre>
 * 
 * Accela Automation
 * File: ApiSecurity
 * 
 * Accela, Inc.
 * Copyright (C): 2013
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: RecordSecurityAuthorizer.java 72642 2009-01-01 20:01:57Z ACHIEVO\aaa $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Jun 06, 2013		dylan.liang		Initial.
 * 
 * </pre>
 */
@Target( {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiSecurity
{
	String operation() default "";

	String parentResource() default "";

	String parentId() default "";

	String resource();

	String id() default "";

	String type() default "";

	Applicability applicability();
}
