package com.cxf.restapis.framework.util;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ClassUtil.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2010
 * 
 *  Description:
 *  Provide java class related common method.
 * 
 *  Notes:
 * 	$Id: ClassUtil.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2010-8-4		michael.mao		Initial.
 *  
 * </pre>
 */
public class ClassUtil
{
	/**
	 * Return class new instance with class name
	 * 
	 * @param className		The class name
	 * @return
	 */
	public static Class<?> loadClass(String className)
	{
		try
		{
			// Look up the class loader to be used
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			if (classLoader == null)
			{
				classLoader = ClassUtil.class.getClassLoader();
			}
			Class<?> classz = classLoader.loadClass(className);
			return classz;
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(className + " class not found", e);
		}
	}
	
	/**
	 * New instance.
	 *
	 * @param clazz	java class type 
	 * @return
	 */
	public static Object newInstance(Class<?> clazz)
	{
		
		try
		{
			return clazz.newInstance();
		}
		catch (InstantiationException e)
		{
			throw new RuntimeException("Cannot instantiate class : " + clazz, e);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException("Cannot access class : " + clazz, e);
		}
	}
	/**
	 * New instance.
	 *
	 * @param className	java class name 
	 * @return
	 */
	public static Object newInstance(String className)
	{
		return newInstance(loadClass(className));
	}

}

/*
*$Log: av-env.bat,v $
*/