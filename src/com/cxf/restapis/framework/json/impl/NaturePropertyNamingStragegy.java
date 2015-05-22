package com.cxf.restapis.framework.json.impl;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: NaturePropertyNamingStragegy.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: NaturePropertyNamingStragegy.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-8-24		michael.mao		Initial.
 *  
 * </pre>
 */
public class NaturePropertyNamingStragegy extends PropertyNamingStrategy
{
	public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName)
	{
		return field.getName();
	}

	public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
	{
		return translate(method.getName());
	}

	public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName)
	{
		return translate(method.getName());
	}

	public String nameForConstructorParameter(MapperConfig<?> config, AnnotatedParameter ctorParam, String defaultName)
	{
		return defaultName;
	}

	public String translate(String input)
	{
		int len = input.length();
		if (len <= 3)
		{
			return input;
		}
		if (input.charAt(2) == 't')
		{
			char firstChar = input.charAt(3);
			if (Character.isUpperCase(firstChar))
			{
				if (len > 4)
				{
					StringBuilder sb = new StringBuilder();
					if (Character.isUpperCase(input.charAt(4)))
					{
						sb.append(input.substring(3));
					}
					else
					{
						sb.append(Character.toLowerCase(firstChar));
						sb.append(input.substring(4));
					}
					return sb.toString();
				}
				else
				{
					return new String(new char[]{Character.toLowerCase(firstChar)});
				}
				
			}
			
		}
		return input;
	}
}

/*
*$Log: av-env.bat,v $
*/