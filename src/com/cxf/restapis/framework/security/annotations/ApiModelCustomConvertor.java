package com.cxf.restapis.framework.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ApiModelCustomConvertor.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ApiModelCustomConvertor.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jan 8, 2014		bruce.deng		Initial.
 * 
 * </pre>
 */
@Target( {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiModelCustomConvertor
{
	public abstract String request() default "";

	public abstract String response() default "";

	public abstract String responseMultipleObjectResultModelIdType() default "";

	/**
	 * Request custom convertor define for document.
	 * 
	 * @return the string[]
	 */
	public abstract String[] requestCustomConvertorDefine() default {};

	/**
	 * Response custom convertor define for document.
	 * 
	 * @return the string[]
	 */
	public abstract String[] responseCustomConvertorDefine() default {};

	public abstract String[] requests() default {};

	public abstract String[] responses() default {};
}

/*
 * $Log: av-env.bat,v $
 */