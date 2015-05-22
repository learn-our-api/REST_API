package com.cxf.restapis.docsgenerator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: BeanContext.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: BeanContext.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-11-5		dylan.liang		Initial.
 * 
 * </pre>
 */
public class BeanContext
{
	/**
	 * Load service classes.
	 * 
	 * @return the list
	 */
	public static List<Class<?>> loadClasses(List<String> beanNames, String filepath)
	{
		// Create a File object on the root of the directory containing the class file
		File file = new File(filepath);
		List<Class<?>> classes = new ArrayList<Class<?>>();

		try
		{
			// Convert File to a URL
			URL url = file.toURL();
			URL[] urls = new URL[] {url};

			// Create a new class loader with the directory
			ClassLoader classLoader = new URLClassLoader(urls);
			System.out.println("Start to load class from path: " + file.getAbsolutePath());
			for (String beanName : beanNames)
			{
				Class<?> clazz = classLoader.loadClass(beanName);

				if (clazz != null)
				{
					System.out.println("Loading " + clazz);
					classes.add(clazz);
				}
			}
			System.out.println("End to load class");
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return classes;
	}
	
	/**
	 * Load class.
	 *
	 * @param name the name
	 * @return the class
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static Class<?> loadClass(String name) throws ClassNotFoundException
	{
		Class<?> clazz = null;
		ClassLoader classLoader = BeanContext.class.getClassLoader();

		try
		{
			clazz = Class.forName(name, true, classLoader);
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(name + " Class not found in classLoader " + classLoader);
		}

		if (clazz == null)
		{
			throw new ClassNotFoundException("class " + name + " not found");
		}

		return clazz;
	}
}

/*
 * $Log: av-env.bat,v $
 */
