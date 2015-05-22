package com.cxf.restapis.docsgenerator;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cxf.restapis.docsgenerator.model.Documentation;



// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JaxrsApiReader.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: JaxrsApiReader.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-11-5		dylan.liang		Initial.
 * 
 * </pre>
 */
public class JaxrsApiReader
{
	/** The Constant endpointsCache. */
	private final static Map<Class<?>, Documentation> endpointsCache = new HashMap<Class<?>, Documentation>();

	 private static final Map<String, Set<String>> REFERENCED_CLASSES_CACHE = new HashMap<String, Set<String>>();
	
//	/**
//	 * Read Documentation from endpoint method.
//	 * 
//	 * @param hostClass the host class
//	 * @param apiVersion the api version
//	 * @param swaggerVersion the swagger version
//	 * @param basePath the base path
//	 * @param resourcePath the resource path
//	 * @return the documentation
//	 */
//	public static Map<String, Documentation> read(Class<?> hostClass, String apiVersion, String swaggerVersion, String basePath,
//			String resourcePath)
//	{
//		System.out.println("reading path " + resourcePath);
//		Documentation returnDoc = null;
//		Documentation cachedDoc = endpointsCache.get(hostClass);
//
//		if (cachedDoc == null)
//		{
//			JaxrsApiSpecParser apiParser = new JaxrsApiSpecParser(hostClass, apiVersion, swaggerVersion, basePath,
//					resourcePath);
//			Documentation doc = apiParser.parse();
//			
//		}
//		else
//		{
//			returnDoc = (Documentation) cachedDoc.clone();
//			loadModel(returnDoc);
//		}
//
//		return returnDoc;
//	}

//	/**
//	 * Load model.
//	 * 
//	 * @param d the d
//	 */
//	private static void loadModel(Documentation d)
//	{
//		List<String> directTypes = getExpectedTypes(d);
//		Collection<String> types = null;
//		try
//		{
//			types = TypeUtil.getReferencedClasses(directTypes);
//		}
//		catch (ClassNotFoundException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		appendModel4CustomerJSONNameMapping(types);
//		
//		for (String t : types)
//		{
//			try
//			{
//				if (isPrimitiveType(t))
//				{
//					continue;
//				}
//
//				Class<?> c = BeanContext.loadClass(t);
//				DocumentationObject n = ApiPropertiesReader.read(c);
//				if (n != null && ValidationUtil.isNotEmpty(n.getFields()))
//				{
//					d.addModel(n.getName(), n.toDocumentationSchema());
//				}
//				else
//				{
//					if (n == null)
//					{
//						System.out.println("Skipping model " + t + ". Could not load the model.");
//					}
//					else if (ValidationUtil.isEmpty(n.getFields()))
//					{
//						System.out.println("Skipping model " + t
//								+ ". Did not find any public fields or bean-properties with JsonView in this model.");
//					}
//				}
//			}
//			catch (Exception e)
//			{
//				System.out.println("Unable to load model documentation for " + t);
//				e.printStackTrace();
//			}
//		}
//	}
//
//	
//	
//	/**
//	 * Append new model to types in jsonNameMapping of custom convertor.
//	 * Because the model is not define in primary model, it only used construct model structure. so we need generic document also.
//	 * for example: //RefOwnerModelCustomConvertor - com.accela.restapis.owner.model.OwnerAddressModel
//	 * @param newTypes
//	 */
//	private static void appendModel4CustomerJSONNameMapping(Collection<String> newTypes)
//	{
//		int len = newTypes.size();
//		for(int i=0; i<len; i++)
//		{
//			String t = (String) ((Set<String>)newTypes).toArray()[i];
//			if (isPrimitiveType(t))
//			{
//				continue;
//			}
//			try
//			{
//				Class<?> c = BeanContext.loadClass(t);
//				
//				Map<Object, Object> customMap = CustomConvertorDefine.getCustomMap();
//				if(customMap.containsKey(c.getName()))
//				{
//					String convertClassName = (String) customMap.get(c.getName());
//					Class<?> clazz = BeanContext.loadClass(convertClassName);
//					ICustomConvertor customConvertor =(ICustomConvertor)clazz.newInstance();
//					Map<String, String> customJsonMapping = customConvertor.getCustomJsonMapping();
//					if(ValidationUtil.isNotEmpty(customJsonMapping))
//					{
//						Iterator<Entry<String, String>> jsonMaps =customJsonMapping.entrySet().iterator();
//						while(jsonMaps.hasNext())
//						{
//							Entry<String, String> jsonMap =jsonMaps.next();
//							String dataType = jsonMap.getValue();
//							String newDataType = null;
//							if(dataType.indexOf("<")>0)
//							{
//								String typePrefix = dataType.substring(0, dataType.indexOf("<"));
//								newDataType = typePrefix.substring(typePrefix.lastIndexOf(".")+1);
//							}
//							else
//							{
//								newDataType = dataType.substring(1);
//							}
//							
//							if(newDataType.equalsIgnoreCase("List"))
//							{
//								newDataType = dataType.substring(dataType.indexOf("<")+1, dataType.indexOf(">"));
//							}
//							else if(newDataType.equalsIgnoreCase("Map"))
//							{
//								//TODO:
//							}
//							else if(newDataType.equalsIgnoreCase("Set"))
//							{
//								//TODO:
//							}
//							
//							if(newDataType.startsWith("com.accela") && !newTypes.contains(newDataType))
//							{
//								newTypes.add(newDataType);
//							}
//						}
//					}
//				}
//			}
//			catch(Exception e)
//			{
//				System.out.println("Append model issue: " + t);
//				e.printStackTrace();
//			}
//		}
//	}
//
//	
//	
//	/**
//	 * Checks if is primitive type.
//	 *
//	 * @param type the type
//	 * @return true, if is primitive type
//	 */
//	private static boolean isPrimitiveType(String type)
//	{
//		boolean isPrimitiveType = false;
//
//		if (ValidationUtil.isNotEmpty(type))
//		{
//			if (Character.TYPE.toString().equalsIgnoreCase(type) || Byte.TYPE.toString().equalsIgnoreCase(type)
//					|| Boolean.TYPE.toString().equalsIgnoreCase(type) || Integer.TYPE.toString().equalsIgnoreCase(type)
//					|| Short.TYPE.toString().equalsIgnoreCase(type) || Long.TYPE.toString().equalsIgnoreCase(type)
//					|| Double.TYPE.toString().equalsIgnoreCase(type) || Float.TYPE.toString().equalsIgnoreCase(type)
//					|| Void.TYPE.toString().equalsIgnoreCase(type))
//			{
//				isPrimitiveType = true;
//			}
//		}
//
//		return isPrimitiveType;
//
//	}
//
//	/**
//	 * Gets the expected types.
//	 * 
//	 * @param d the d
//	 * @return the expected types
//	 */
//	private static List<String> getExpectedTypes(Documentation d)
//	{
//		Set<String> set = new HashSet<String>();
//		if (d != null && ValidationUtil.isNotEmpty(d.getApis()))
//		{
//			for (DocumentationEndPoint endpoint : d.getApis())
//			{
//				if (endpoint == null || ValidationUtil.isEmpty(endpoint.getOps()))
//				{
//					continue;
//				}
//
//				for (DocumentationOperation op : endpoint.getOps())
//				{
//					if (ValidationUtil.isNotEmpty(op.getResponseTypeInternal()))
//					{
//						set.add(op.getResponseTypeInternal().replaceAll("\\[\\]", ""));
//					}
//
//					if (ValidationUtil.isNotEmpty(op.getParameters()))
//					{
//						for (DocumentationParameter pa : op.getParameters())
//						{
//							if (ValidationUtil.isNotEmpty(pa.getValueTypeInternal()))
//							{
//								set.add(pa.getValueTypeInternal().replaceAll("\\[\\]", ""));
//							}
//						}
//					}
//				}
//			}
//		}
//
//		return new ArrayList<String>(set);
//	}
}

/*
 * $Log: av-env.bat,v $
 */
