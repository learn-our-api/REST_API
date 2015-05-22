package com.cxf.restapis.framework.complier;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JavaComplier.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  Provide the ability to compile Java code.
 * 
 *  Notes:
 * 	$Id: JavaComplier.java 72642 2013-09-28 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-28		michael.mao		Initial.
 *  
 * </pre>
 */
public class JavaComplier
{
	private static String CLASS_OUTPUT_PATH = null;
	
	private static CustomClassLoader classLoader = null; 
	
	private static Map<String, String> JARS = new HashMap<String, String>();
	
	/**
	 * Return class output path.
	 *
	 * @return
	 */
	public static String getClassOutputPath()
	{
		if (CLASS_OUTPUT_PATH == null)
		{
			CLASS_OUTPUT_PATH = getInternalClassOutputPath();
		}
		return CLASS_OUTPUT_PATH;
	}
	/**
	 * Return custom class loader for generated class.
	 *
	 * @return
	 */
	public static ClassLoader getCustomClassLoader()
	{
		if (classLoader == null)
		{
			classLoader = getInternalCustomClassLoader();
		}
		return classLoader;
	}
	
	private static synchronized CustomClassLoader getInternalCustomClassLoader()
	{
		if (classLoader == null)
		{
			String rootPath = getClassOutputPath();
			ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
			if (classLoader1 == null)
			{
				classLoader1 = JavaComplier.class.getClassLoader();
			}
			classLoader = new CustomClassLoader(classLoader1,rootPath, new String[]{"com.accela.dynamic"});
		}
		return classLoader;
	}
	
	private static synchronized String getInternalClassOutputPath()
	{
		String path = null;
		if (CLASS_OUTPUT_PATH == null)
		{
			try
			{
				path =  ""
						+ File.separatorChar + "log" + File.separatorChar  + "classes";
			}
			catch (Throwable e)
			{
				path = getTempDir() + File.separatorChar  + "classes";
			}
			createPath(path);
			CLASS_OUTPUT_PATH = path;
		}
		return CLASS_OUTPUT_PATH;
	}
	
	private static String getTempDir()
	{
		String tempFilePath = null;
		try
		{
			File temp = File.createTempFile("temp-file-name", ".tmp");
			String absolutePath = temp.getAbsolutePath();
			tempFilePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));
			temp.delete();
		}
		catch (Throwable e)
		{
			
		}
		return tempFilePath;
	}
	
	private static void createPath(String path)
	{
		try
		{
			File file = new File(path);
			if (file.exists())
			{
				return;
			}
			else
			{
				String tempFilePath = path.substring(0,path.lastIndexOf(File.separator));
				File parentPath = new File(tempFilePath);
				if (parentPath.exists())
				{
					file.mkdir();
					return;
				}
				if (!parentPath.exists())
				{
					file.mkdirs();
					return;
				}
				createPath(tempFilePath);
			}
		}
		catch (Throwable e)
		{	
		}
	}
	/**
	 * Compile the source code, and return error messages if the compile fail.
	 * 
	 * @param javaFileName	full file name of new java code
	 * @param javaCode		new java code.
	 * @return
	 */
	public static List<String> compile(String javaFileName, final String javaCode, List<Class<?>> classList)
	{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		URI javaFileUri = null;
		try
		{
			String fielName1 = javaFileName.substring(javaFileName.lastIndexOf(".") + 1);
			javaFileUri = new URI(fielName1 + ".java");
		}
		catch (Exception e)
		{
			
		}
		JavaFileObject javaFile1 = new SimpleJavaFileObject(javaFileUri, Kind.SOURCE)
		{
			@Override
			public CharSequence getCharContent(boolean ignoreEncodingErrors)
			{
				return javaCode;
			}
		};
		String outPath = getClassOutputPath();
		String compileClassPath = getClassPath4Compile(classList);
		CustomDiagnosticListener listener1 = new CustomDiagnosticListener(javaCode);
		Iterable<String> options = Arrays.asList("-g", "-d", outPath, "-classpath", compileClassPath);
		CompilationTask task2 = compiler.getTask(null, null, listener1, options ,null, Arrays.asList(javaFile1));
		Boolean result2 =  task2.call();
		if(result2) 
		{
			return null;
			
		}
		else
		{
			return listener1.getErrorMessages();
		}
	}
	
	private static String getClassPath4Compile(List<Class<?>> classList)
	{
		StringBuilder classPath = new StringBuilder();
		String classpathInRuningtime = System.getProperty("java.class.path");
		if (classpathInRuningtime != null)
		{
			classPath.append(classpathInRuningtime);
		}
		
		URL url = JavaComplier.class.getProtectionDomain().getCodeSource().getLocation();
		if (url != null)
		{
			String urlPath = url.getPath();
			if (urlPath.indexOf(".ear") > 0 || urlPath.indexOf(".war") > 0 || urlPath.indexOf(".sar") > 0)
			{
				scanJarInFolder(classPath, urlPath);
			}
			int lastPos1 = urlPath.lastIndexOf('/');
			int lastPos2 = urlPath.lastIndexOf('/', lastPos1 - 1);
			urlPath.substring(lastPos2, lastPos1);
			String classPath1 = ";" + getClassPath(urlPath);
			String classPath2 =  getClassPath(urlPath) + ";";
			if (classPath.indexOf(classPath1) < 0 && classPath.indexOf(classPath2) < 0)
			{
				classPath.append(classPath1);
			}
		}
		if (classList == null)
		{
			return classPath.toString();
		}
		for (int index = 0; index < classList.size(); index++)
		{
			Class<?> clazz = classList.get(index);
			url = clazz.getProtectionDomain().getCodeSource().getLocation();
			if (url != null)
			{
				String classPath1 = ";" + getClassPath(url.getPath());
				String classPath2 =  getClassPath(url.getPath()) + ";";
				if (classPath.indexOf(classPath1) < 0 && classPath.indexOf(classPath2) < 0)
				{
					classPath.append(classPath1);
				}
			}
		}
		return classPath.toString();
	}
	
	private static String getClassPath(String path)
	{
		if (path != null)
		{
			if (path.charAt(0) == '/')
			{
				path = path.substring(1);
			}
			if (path.lastIndexOf(".jar") > 0)
			{
				int pos = path.lastIndexOf(".jar");
				if (pos < path.length() - 4)
				{
					path = path.substring(0, pos + 4);
				}
			}
		}
		return path;
	}
	
	private static void scanJarInFolder(StringBuilder classPath, String urlPath)
	{
		String lastFolder = getLastFolderFromUrl(urlPath);
		//Not found right folder.
		if (lastFolder == null)
		{
			return;
		}
		String folderName = getFolderNameFromUrl(urlPath);
		String jarFiles = JARS.get(folderName);
		if (jarFiles == null)
		{
			File folder = new File(folderName);
			StringBuilder classPath2 = new StringBuilder();
			if (lastFolder.equals("lib"))
			{
				//scan all jar files.
				scanLibFolder(folderName, folder, classPath2);
			}
			else if (lastFolder.indexOf(".ear-") >0 || lastFolder.indexOf(".ear") >0)
			{
				//scan current folder to see if JAR file exist
				scanLibFolder(folderName, folder, classPath2);
				//scan lib folder
				String libFolderName = folderName + "/lib";
				File libFolder = new File(libFolderName);
				scanLibFolder(libFolderName, libFolder, classPath2);
				//scan other sub folder
				scanSubFolder(folderName, folder, classPath2);
			}
			jarFiles = classPath2.toString();
			JARS.put(folderName, jarFiles);
		}
		if (jarFiles.length() > 5)
		{
			classPath.append(";").append(jarFiles);
		}
	}
	private static void scanLibFolder(String folderName, File folder, StringBuilder classPath2)
	{
		if (folder.isDirectory())
		{
			String[] fileList = folder.list();
			if (fileList != null)
			{
				for (int index = 0; index < fileList.length; index++)
				{
					String jarFile = fileList[index];
					String pathName = folderName + "/" + jarFile;
					File path = new File(pathName);
					if (jarFile.endsWith(".jar") && path.isFile())
					{
						if (folderName.charAt(0) == '/')
						{
							folderName = folderName.substring(1);
						}
						if (classPath2.length() > 0 && classPath2.charAt(classPath2.length() - 1) != ';')
						{
							classPath2.append(";");	
						}
						classPath2.append(folderName).append('/').append(jarFile);
					}
				}
			}
		}
	}

	private static void scanSubFolder(String folderName, File folder, StringBuilder classPath2)
	{
		//Check to see if it exist and is a directory.
		if (folder.exists() && folder.isDirectory())
		{
			String[] fileList = folder.list();
			//Scan all files.
			if (fileList != null)
			{
				//For each
				for (int index = 0; index < fileList.length; index++)
				{
					String subFolder = fileList[index];
					String pathName = folderName + "/" + subFolder;
					File path = new File(pathName);
					//Check to see if sub folder exist
					if (subFolder.indexOf(".war") > 0 && path.isDirectory())
					{
						String subPathName = pathName + "/WEB-INF/lib";
						File subPath = new File(subPathName);
						//Check to see if the war deploy folder exist.
						if (subPath.exists())
						{
							scanLibFolder(subPathName, subPath, classPath2);
						}
					}
				}
			}
		}
	}
	
	private static String getLastFolderFromUrl(String urlPath)
	{
		int lastPos1 = urlPath.lastIndexOf('/');
		if (lastPos1 < 0)
		{
			return null;
		}
		int lastPos2 = urlPath.lastIndexOf('/', lastPos1 - 1);
		if (lastPos2 < 0)
		{
			return null;
		}
		return urlPath.substring(lastPos2 + 1, lastPos1);
	}
	private static String getFolderNameFromUrl(String urlPath)
	{
		int lastPos1 = urlPath.lastIndexOf('/');
		if (lastPos1 < 0)
		{
			return null;
		}
		return urlPath.substring(0, lastPos1);
	}
	/**
	 * 
	 * Load class to be generated, and create new instance.
	 *
	 * @param fullClassName
	 * @return
	 */
	public static Object newInstance(String fullClassName)
	{
		Object instance = null;
		try
		{
			ClassLoader loader = getCustomClassLoader();
			Class<?> customClass = loader.loadClass(fullClassName);
			instance = customClass.newInstance();
			//Delete custom class file.
			String rootPath = getClassOutputPath();
			File classFile = CustomClassLoader.getCustomClassFile(fullClassName, rootPath);
			classFile.delete();
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(fullClassName + " class not found", e);
		}
		return instance;
	}
	
//	
//	public static void main(String[] argc) throws Exception
//	{
//		String path = "/D:/AA7.2.0/av.7.2.0/av.biz/tmp/deploy/tmp6194536084952549675av-biz.ear-contents/av-calendar.jar";
//		StringBuilder abc = new StringBuilder();
//		scanJarInFolder(abc, path);
//	}
	
}
