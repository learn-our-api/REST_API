package com.cxf.restapis.framework.complier;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomClassLoader.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  Provide dynamic class loader.
 * 
 *  Notes:
 * 	$Id: CustomClassLoader.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-29		michael.mao		Initial.
 *  
 * </pre>
 */
public class CustomClassLoader extends URLClassLoader
{
	private String[] packageNames;

	private String rootPath;

	private Map<String, Class<?>> cachedClasses = new HashMap<String, Class<?>>();
	
	/**
	 * Construct Dynamic class loader.
	 * @param parent
	 * @param rootPath
	 * @param packageNames
	 */
	public CustomClassLoader(ClassLoader parent, String rootPath, String[] packageNames) 
	{
		super(new URL[]{}, parent);
		this.rootPath = rootPath;
		this.packageNames = packageNames;
	}

	@Override
	public InputStream getResourceAsStream(String name)
	{
		return super.getResourceAsStream(name);
	}
	
	/**
	 * The only caller of this method should be the VM initiated
	 * loadClassInternal() method. This method attempts to acquire the
	 * UnifiedLoaderRepository2 lock and then asks the repository to load the
	 * class.
	 * 
	 */
	@Override
	public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
	{
		Class<?> clazz = cachedClasses.get(name);
		if (clazz != null) 
		{
			return clazz;
		}
		if (isDynamicClass(name, packageNames)) 
		{
			File classFile = getCustomClassFile(name, rootPath);
			//Check to see if class file exists.
			if (!classFile.exists())
			{
				return super.loadClass(name, resolve);
			}
			return loadInternalClass(name, classFile);
		}
		else 
		{
			return super.loadClass(name, resolve);
		}
	}

	public static File getCustomClassFile(String fullClassName, String rootPath)
	{
		String classFileName;
		if (fullClassName.endsWith(".class"))
		{
			fullClassName = fullClassName.substring(0, fullClassName.length() - 6);
			classFileName = fullClassName.replaceAll("\\.", "\\/") + ".class";
		}
		else
		{
			classFileName = fullClassName.replaceAll("\\.", "\\/") + ".class";
		}
		
		File classFile = new File(rootPath + "/" + classFileName);
		return classFile;
	}
	/**
	 * @param name
	 * @param classFile
	 * @return
	 * @throws ClassFormatError
	 */
	private synchronized Class<?> loadInternalClass(String name, File classFile)
			throws ClassFormatError 
	{
		Class<?> clazz = cachedClasses.get(name);
		if (clazz != null) 
		{
			return clazz;
		}
		FileInputStream input = null;
		try 
		{
			input = new FileInputStream(classFile);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int data = input.read();
			while (data != -1) 
			{
				buffer.write(data);
				data = input.read();
			}
			byte[] classData = buffer.toByteArray();
			URL codeSourceUrl = getResource(classFile);
			ProtectionDomain protectionDomain = getProtectionDomain(codeSourceUrl, classFile);
			clazz = defineClass(name, classData, 0, classData.length, protectionDomain);
			this.cachedClasses.put(name, clazz);
			return clazz;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			close(input);
		}

		return null;
	}
	
	private static boolean isDynamicClass(String name, String[] packageNames)
	{
		for (String packageName : packageNames)
		{
			if (name.startsWith(packageName))
			{
				return true;
			}
		}
		return false;
	}
	
	private static void close(InputStream input)
	{
		try
		{
			if (input != null)
			{
				input.close();
			}
			
		}
		catch (Throwable e)
		{
			
		}
		
	}
	
	/**
	 * Called by loadClassLocally to find the requested class within this class
	 * loaders class path.
	 * 
	 * @param name
	 *            the name of the class
	 * @return the resulting class
	 * @exception ClassNotFoundException
	 *                if the class could not be found
	 */
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException 
	{
		Class<?> clazz = cachedClasses.get(name);
		if (clazz == null)
		{
			return super.findClass(name);
		}
		return clazz;
	}

	/**
	 * Determine the protection domain. If we are a copy of the original
	 * deployment, use the original url as the codebase.
	 * 
	 * @return the protection domain
	 * @todo certificates and principles?
	 */
	private static ProtectionDomain getProtectionDomain(URL codesourceUrl, File file) 
	{
		Certificate certs[] = null;
		CodeSource cs = new CodeSource(codesourceUrl, certs);
		return new ProtectionDomain(cs, null);
	}

	private static URL getResource(File file) 
	{
		try 
		{
			return file.toURL();
		} 
		catch (Exception e) 
		{

		}
		return null;
	}
	
}
