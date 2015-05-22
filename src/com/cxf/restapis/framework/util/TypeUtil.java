package com.cxf.restapis.framework.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.cxf.restapis.docsgenerator.BeanContext;
import com.cxf.restapis.framework.json.impl.ICustomConvertor;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: TypeUtil.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: TypeUtil.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jan 3, 2014		bruce.deng		Initial.
 * 
 * </pre>
 */
public class TypeUtil
{
//	private static final Logger LOGGER = LoggerFactory.getLogger(TypeUtil.class.getName());

	private static final String WORDNIK_PACKAGES = "com.accela.";

	private static Map<String, Set<String>> REFERENCED_CLASSES_CACHE = null;
	
	private static boolean requestAlikeResponse = false;

	/**
	 * @return true if the passed type represents a paramterized list
	 */
	public static boolean isParameterizedList(Type genericType)
	{
		boolean isList = false; // List.class.isAssignableFrom(type.getClass()) ||
								// Collection.class.isAssignableFrom(type.getClass());
		final boolean isTypeParameterized = ParameterizedType.class.isAssignableFrom(genericType.getClass());
		if (isTypeParameterized)
		{
			final ParameterizedType parameterizedType = (ParameterizedType) genericType;
			isList = parameterizedType.getRawType().equals(List.class);
		}

		return isList && isTypeParameterized;
	}

	/**
	 * @return true if the passed type represents a paramterized set
	 */
	public static boolean isParameterizedSet(Type genericType)
	{
		boolean isSet = false; // List.class.isAssignableFrom(type.getClass()) ||
								// Collection.class.isAssignableFrom(type.getClass());
		final boolean isTypeParameterized = ParameterizedType.class.isAssignableFrom(genericType.getClass());
		if (isTypeParameterized)
		{
			final ParameterizedType parameterizedType = (ParameterizedType) genericType;
			isSet = parameterizedType.getRawType().equals(Set.class);
		}

		return isSet && isTypeParameterized;
	}

	/**
	 * @return true if the passed type represents a paramterized map
	 */
	public static boolean isParameterizedMap(Type genericType)
	{
		boolean isList = false; // List.class.isAssignableFrom(type.getClass()) ||
								// Collection.class.isAssignableFrom(type.getClass());
		final boolean isTypeParameterized = ParameterizedType.class.isAssignableFrom(genericType.getClass());
		if (isTypeParameterized)
		{
			final ParameterizedType parameterizedType = (ParameterizedType) genericType;
			isList = parameterizedType.getRawType().equals(Map.class);
		}

		return isList && isTypeParameterized;
	}

	/**
	 * Gets a parameterized lists types if they are in com.wordnik.* packages
	 */
	private static List<String> getWordnikParameterTypes(Type genericType)
	{
		List<String> list = new ArrayList<String>();

		if (isParameterizedList(genericType) || isParameterizedSet(genericType))
		{
			final ParameterizedType parameterizedType = (ParameterizedType) genericType;
			for (Type _listType : parameterizedType.getActualTypeArguments())
			{
				try
				{
					if(_listType instanceof ParameterizedTypeImpl)
					{
						continue;
					}
					final Class listType = (Class) _listType;
					if (listType.getName().startsWith(WORDNIK_PACKAGES))
					{
						list.add(listType.getName());
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	/**
	 * Get all classes references by a given list of classes. This includes types of method params and fields
	 */
	public static Collection<String> getReferencedClasses(List<String> classNameList, Map<String, ICustomConvertor> customConvertorMap, boolean isRequest,
		Map<String, ICustomConvertor> requestCustomConvertorDefine, Map<String, ICustomConvertor> responseCustomConvertorDefine) throws ClassNotFoundException
	{
		final Set<String> referencedClasses = new HashSet<String>();
		REFERENCED_CLASSES_CACHE  = new HashMap<String, Set<String>>();
		String requestClassName = null;
		String responseClassName = null;
		List<String> requestCustomFields = null;
		List<String> requestShowFields = null;
		List<String> responseCustomFields = null;
		List<String> responseShowFields = null;
		if(!ValidationUtil.isEmpty(customConvertorMap))
		{
			ICustomConvertor requestCustomConvertor = customConvertorMap.get("request");
			ICustomConvertor responseCustomConvertor = customConvertorMap.get("response");
			
			if(!ValidationUtil.isEmpty(requestCustomConvertor))
			{
				requestCustomFields = requestCustomConvertor.getCustomFields();
				requestShowFields = requestCustomConvertor.getShowFields();
				requestClassName = requestCustomConvertor.getCustomClass().getName();
			}
			if(!ValidationUtil.isEmpty(responseCustomConvertor))
			{
				responseCustomFields = responseCustomConvertor.getCustomFields();
				responseShowFields = responseCustomConvertor.getShowFields();	
				responseClassName = responseCustomConvertor.getCustomClass().getName();
			}
			requestAlikeResponse = false;
			if(requestClassName != null && requestClassName.equals(responseClassName))
			{
				requestAlikeResponse = true;
			}
		}
		if(requestAlikeResponse)
		{
//			classNameList.add(requestClassName+"_request");
//			classNameList.add(responseClassName+"_response");
//			classNameList.remove(requestClassName);
		}
		for (String className : classNameList)
		{	
			if(isRequest)
			{
				referencedClasses.addAll(getReferencedClasses(className, requestCustomFields, requestShowFields, requestCustomConvertorDefine));
			}
			else
			{
				referencedClasses.addAll(getReferencedClasses(className, responseCustomFields, responseShowFields, responseCustomConvertorDefine));
			}
		}

		return referencedClasses;
	}

	/**
	 * Get all classes references by a given class. This includes types of method params and fields
	 */
	public static Collection<String> getReferencedClasses(String className, List<String> customFields, List<String> showFields, 
		Map<String, ICustomConvertor> customConvertorDefine) throws ClassNotFoundException
	{
		if (REFERENCED_CLASSES_CACHE.containsKey(className))
		{
			return REFERENCED_CLASSES_CACHE.get(className);
		}
		else
		{
			final Set<String> referencedClasses = new HashSet<String>();

			if (className.indexOf(".") > 0)
			{
				
				referencedClasses.add(className);
				
				Class clazz = null;
				Class superClazz = null;
				try
				{
					String newClassName = className;
//					if(className.lastIndexOf("_request")>0)
//					{
//						newClassName = className.substring(0, className.lastIndexOf("_request"));
//					}
//					else if(className.lastIndexOf("_response")>0)
//					{
//						newClassName = className.substring(0, className.lastIndexOf("_response"));
//					}
					clazz = BeanContext.loadClass(newClassName);//Class.forName(newClassName);
					superClazz = clazz.getSuperclass();
				}
				catch (Exception e)
				{
//					LOGGER.error("Unable to load class " + className);
				}

				if (clazz != null)
				{
					addModelClass(customFields, showFields, referencedClasses, clazz);
				}
				
				if (superClazz != null)
				{
					addModelClass(customFields, showFields, referencedClasses, superClazz);
				}
			}

			REFERENCED_CLASSES_CACHE.put(className, referencedClasses);

			final Set<String> additionalClasses = new HashSet<String>();
			for (String referencedClass : referencedClasses)
			{
				if (!REFERENCED_CLASSES_CACHE.containsKey(referencedClass))
				{
					ICustomConvertor subCustomConvertor = null;
					customFields = null;
					showFields = null;
					Map<String, String> jsonNameMapping = null;
					if(customConvertorDefine.containsKey(referencedClass))
					{
						subCustomConvertor = customConvertorDefine.get(referencedClass);
						customFields = subCustomConvertor.getCustomFields();
						showFields = subCustomConvertor.getShowFields();
					}
					else
					{
						Map<Object, Object> customMap = CustomConvertorDefine.getCustomMap();
						if(customMap.containsKey(referencedClass))
						{
							try
							{
								String referencedClassName = (String) customMap.get(referencedClass);
								Class<?> clazz = BeanContext.loadClass(referencedClassName);
								subCustomConvertor =(ICustomConvertor)clazz.newInstance();
								customFields = subCustomConvertor.getCustomFields();
								showFields = subCustomConvertor.getShowFields();
								
							}
							catch(Exception e)
							{
								
							}
						}
					}
					
					additionalClasses.addAll(getReferencedClasses(referencedClass, customFields, showFields, customConvertorDefine));
					if(subCustomConvertor != null)
					{
						jsonNameMapping = subCustomConvertor.getCustomJsonMapping();
						if(!ValidationUtil.isEmpty(jsonNameMapping))
						{
							Set<String> objectNames = new HashSet<String>();
							Collection<String> customObjects = jsonNameMapping.values();
							for(String customObject : customObjects)
							{
								if(customObject !=null && customObject.startsWith("@"))
								{
									int index = customObject.indexOf('<');
									String objectName = null;
									if(index > -1)
									{
										objectName = customObject.substring(index + 1, customObject.indexOf('>')); 
									}
									else
									{
										objectName = customObject.substring(1);
									}
									if(objectName != null && objectName.startsWith(WORDNIK_PACKAGES))
									{
										objectNames.add(objectName);
									}
								}
							}
							additionalClasses.addAll(objectNames);
						}
					}
				}
			}
			referencedClasses.addAll(additionalClasses);

			return referencedClasses;
		}
	}

	private static void addModelClass(List<String> customFields, List<String> showFields, final Set<String> referencedClasses, Class superClazz)
	{
		for (Field field : superClazz.getDeclaredFields())
		{
			String fieldClass = field.getType().getName();
			if (fieldClass.startsWith(WORDNIK_PACKAGES))
			{
				addFieldClass(customFields, showFields,
					referencedClasses, field, fieldClass);
			}
			else
			{
				List<String> typeList = getWordnikParameterTypes(field.getGenericType());
				if(typeList != null && typeList.size()>0)
				{
					for(String subFieldClass : typeList)
					{
						addFieldClass(customFields, showFields,
							referencedClasses, field, subFieldClass);
					}
				}
			}
		}
		for (Method method : superClazz.getMethods())
		{
			if (Modifier.isPublic(method.getModifiers()))
			{
				String methodReturnClass = method.getReturnType().getName();
				method.getGenericReturnType().toString();
				
				if (methodReturnClass.startsWith(WORDNIK_PACKAGES))
				{
					addMethodClass(customFields, showFields, referencedClasses, method, methodReturnClass);
				}
				else
				{
					List<String> typeList = getWordnikParameterTypes(method.getGenericReturnType());
					if(typeList != null && typeList.size()>0)
					{
						for(String subMethodReturnClass : typeList)
						{
							addMethodClass(customFields, showFields, referencedClasses, method, subMethodReturnClass);
						}
						
					}
				}
			}
		}
	}

	private static void addMethodClass(List<String> customFields, List<String> showFields,
			final Set<String> referencedClasses,
			Method method, String methodReturnClass)
	{
		//Handle new API with custom model convert.
		if ((!ValidationUtil.isEmpty(customFields) && !customFields.contains(getGetterName(method.getName())))
				|| (showFields != null && showFields.contains(getGetterName(method.getName())))
				)
		{
			referencedClasses.add(methodReturnClass);
		}
		else	//Handle old API with JSONView sign whether response.
		{
			for (Annotation ma : method.getAnnotations())
			{
				if(ma instanceof JsonView)
				{
					referencedClasses.add(methodReturnClass);
				}
			}
		}
	}

	private static void addFieldClass(List<String> customFields, List<String> showFields,
			final Set<String> referencedClasses,
			Field field, String fieldClass)
	{
		if ((!ValidationUtil.isEmpty(customFields) && !customFields.contains(getGetterName(field.getName())))
				|| (showFields != null && showFields.contains(getGetterName(field.getName()))))
		{
			referencedClasses.add(fieldClass);
		}
		else
		{	
			for (Annotation ma : field.getAnnotations())
			{
				if(ma instanceof JsonView)
				{
					referencedClasses.add(fieldClass);
				}
			}
		}
	}

	private static String getGetterName(String methodName)
	{
		String getterName = null;
		char char1 = 0;
		char char2 = 0;
		char char3 = 0;
		if(methodName.length()>1)
		{
			char1 = methodName.charAt(0);
		}
		if(methodName.length()>2)
		{
			char2 = methodName.charAt(1);
		}
		if(methodName.length()>3)
		{
			char3 = methodName.charAt(2);
		}
		//Is it isMethod?
		if (char1 == 'i' && char2 == 's')
		{
			getterName = methodName.substring(2);
		}
		//Getter method
		else if (char1 == 'g' && char2== 'e' && char3 == 't')
		{
			getterName = methodName.substring(3);
			//Skip getClass() method.
			if ("Class".equals(getterName))
			{
				getterName = null;
			}
		}
		else
		{
			getterName = methodName;
		}
		if(getterName != null)
		{
			getterName = getterName.substring(0,1).toLowerCase()+getterName.substring(1);
		}
		return getterName;
		
	}
}

/*
 * $Log: av-env.bat,v $
 */