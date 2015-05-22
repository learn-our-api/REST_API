package com.cxf.restapis.docsgenerator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.cxf.restapis.docsgenerator.model.DocumentationObject;
import com.cxf.restapis.framework.json.impl.ICustomConvertor;
import com.wordnik.swagger.core.util.TypeUtil;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ApiPropertyReader.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ApiPropertyReader.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-11-5		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ApiPropertiesReader
{
	
	/** The Constant modelsCache. */
	private final static Map<Class<?>, DocumentationObject> modelsCache = new HashMap<Class<?>, DocumentationObject>();

	/**
	 * Read.
	 *
	 * @param hostClass the host class
	 * @return the documentation object
	 */
	public static DocumentationObject read(Class<?> hostClass, ICustomConvertor customConvertor, 
			Map<String, ICustomConvertor> customConvertorDefine)
	{
		if (hostClass.isEnum() || hostClass.getName().startsWith("java.lang."))
		{
			return null;
		}

		ApiModelParser  apiModelParser= new ApiModelParser(hostClass);
		apiModelParser.setRootCustomConvertor(customConvertor);
		apiModelParser.setCustomConvertorDefine(customConvertorDefine);
		DocumentationObject docObj = apiModelParser.parse();
		return docObj;
	}

	/**
	 * Read name.
	 *
	 * @param hostClass the host class
	 * @return the string
	 */
	public static String readName(Class<?> hostClass)
	{
		return new ApiModelParser(hostClass).readName(hostClass, new Boolean(true));
	}

	/**
	 * Read name.
	 *
	 * @param hostClass the host class
	 * @param isSimple the is simple
	 * @return the string
	 */
	public static String readName(Class<?> hostClass, Boolean isSimple)
	{
		return new ApiModelParser(hostClass).readName(hostClass, isSimple);
	}

	/**
	 * Gets the data type.
	 *
	 * @param genericReturnType the generic return type
	 * @param returnType the return type
	 * @return the data type
	 */
	public static String getDataType(Type genericReturnType, Type returnType)
	{
		String paramType = null;
		if (TypeUtil.isParameterizedList(genericReturnType))
		{
			ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
			Type valueType = parameterizedType.getActualTypeArguments()[0];
			paramType = "List<" + getDataType(valueType, valueType) + ">";
		}
		else if (TypeUtil.isParameterizedSet(genericReturnType))
		{
			ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
			Type valueType = parameterizedType.getActualTypeArguments().length > 0 ? parameterizedType
					.getActualTypeArguments()[0] : null;
			paramType = "Set<" + getDataType(valueType, valueType) + ">";
		}
		else if (TypeUtil.isParameterizedMap(genericReturnType))
		{
			ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
			Type[] typeArgs = parameterizedType.getActualTypeArguments();

			Type keyType = typeArgs[0];
			Type valueType = typeArgs[0];

			String keyName = getDataType(keyType, keyType);
			String valueName = getDataType(valueType, valueType);
			paramType = "Map<" + keyName + "," + valueName + ">";

		}
		else if (!returnType.getClass().isAssignableFrom(ParameterizedTypeImpl.class)
				&& ((Class<?>) returnType).isArray())
		{
			Class<?> arrayClass = ((Class<?>) returnType).getComponentType();
			paramType = arrayClass.getSimpleName() + "[]";
		}
		else
		{
			if (!genericReturnType.getClass().isAssignableFrom(ParameterizedTypeImpl.class))
			{
//				paramType = readName((Class<?>) genericReturnType);
				String name = ((Class<?>) genericReturnType).getSimpleName();
				paramType = name.substring(0,1).toLowerCase()+name.substring(1);
				paramType = ApiModelParser.convertDataType(paramType);
			}
		}
		return paramType;
	}

	/**
	 * Gets the generic type param.
	 *
	 * @param genericReturnType the generic return type
	 * @param returnType the return type
	 * @return the generic type param
	 */
	public static String getGenericTypeParam(Type genericReturnType, Type returnType)
	{
		String typeParam = null;

		if (TypeUtil.isParameterizedList(genericReturnType) || TypeUtil.isParameterizedSet(genericReturnType))
		{
			ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
			Type valueType = parameterizedType.getActualTypeArguments()[0];
			typeParam = readName((Class<?>) valueType, false);
		}
		else if (TypeUtil.isParameterizedMap(genericReturnType))
		{
			ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
			Type[] typeArgs = parameterizedType.getActualTypeArguments();
			Type keyType = typeArgs[0];
			Type valueType = typeArgs[1];
			String keyName = readName((Class<?>) keyType, false);
			String valueName = readName((Class<?>) valueType, false);
			typeParam = "Map<" + keyName + "," + valueName + ">";
		}
		else if (!returnType.getClass().isAssignableFrom(ParameterizedTypeImpl.class)
				&& ((Class<?>) returnType).isArray())
		{
			Class<?> arrayClass = ((Class<?>) returnType).getComponentType();
			typeParam = arrayClass.getName();
		}
		else
		{
			if (!genericReturnType.getClass().isAssignableFrom(ParameterizedTypeImpl.class))
			{
				typeParam = readName((Class<?>) genericReturnType, false);
			}
		}
		return typeParam;
	}
}

/*
 * $Log: av-env.bat,v $
 */
