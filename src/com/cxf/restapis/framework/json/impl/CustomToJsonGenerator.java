package com.cxf.restapis.framework.json.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomToJsonGenerator.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: CustomToJsonGenerator.java 72642 2013-09-28 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-28		michael.mao		Initial.
 *  
 * </pre>
 */
public class CustomToJsonGenerator
{
	private static String PACKAGE_NAME = "package com.accela.dynamic.json;\r\n\r\n";
	
	private static String DEFAULT_IMPORT = "import java.io.IOException;\r\n"
			+ "import com.fasterxml.jackson.core.JsonGenerator;\r\n"
			//+ "import com.fasterxml.jackson.core.JsonProcessingException;\r\n"
			+ "import com.cxf.restapis.framework.json.impl.CustomJSONConvertor;\r\n"
			+ "import java.util.Set;\r\n";
	
	private static String TWO_TAB = 	"		";
	private static String THREE_TAB = 	"			";
	
	
	/**
	 * Generate source code of toJson class for each model. 
	 * Custom fields will be ignored. 
	 *
	 * @param className			new class simple name.
	 * @param modelClass		data model class type
	 * @param customFields		custom fields will be ignored.
	 * @return
	 */
	public static String generateCode(String className, Class<?> modelClass, List<String> customFields, Map<String, String> customJsonAliasMapping) 
	{
		Set<String> skippedList = converToSet(customFields);
		if(skippedList != null)
		{ 
			skippedList.add("hasResource");
			skippedList.add("resId");
			skippedList.add("enbaleI18N");
			skippedList.add("auditID");
			skippedList.add("auditDate");
			skippedList.add("clone");
			skippedList.add("toString");
			skippedList.add("resLangId");
			skippedList.add("hashCode");
			skippedList.add("i18N"); 
		}	
		StringBuilder template = buildTempClass(className, modelClass);
		Map<String, String> jsonAliasMapping = reversalJsonAliasNameMapping(customJsonAliasMapping);
		
		Method[] methodList = modelClass.getMethods();
		for (int index = 0; index < methodList.length; index++)
		{
			String methodName = methodList[index].getName();
			String getterName = getGetterName(methodList[index], methodName);
			if (getterName != null)
			{
				String key = getJsonName(getterName);
				
				if (skippedList != null && skippedList.contains(key))
				{
					continue;
				} 
				//Handle alias
				if(jsonAliasMapping.containsKey(key))
				{
					key = jsonAliasMapping.get(key);
				}			
				//isMethod
				if ('i' == methodName.charAt(0))
				{
					writeIsBooleanNode(template, key, getterName);
				}
				else
				{
					Class<?> returnType = methodList[index].getReturnType();
					//Skip class type, such as Class<?> getResultType()
					if (Class.class.equals(returnType))
					{
						continue;
					}
					String writeMethod = getJsonMethod(returnType);
					writeNode(template, key, getterName, writeMethod);
				}
			}
			
		}
		appendTempClass(template);
		return template.toString();
		
	}
	
	public static String generateCode4CustomAllFields(String className, Class<?> modelClass, List<String> showFields, Map<String, String> customJsonAliasMapping) 
	{
		Set<String> showFieldsList = converToSet(showFields);
		
		StringBuilder template = buildTempClass(className, modelClass);
		
		Map<String/*AA model attribute name*/, String/*JSON attribute name*/> jsonAliasMapping = reversalJsonAliasNameMapping(customJsonAliasMapping);
		
		Method[] methodList = modelClass.getMethods();
		for (int index = 0; index < methodList.length; index++)
		{
			String methodName = methodList[index].getName();
			String getterName = getGetterName(methodList[index], methodName);
			if (getterName != null)
			{
				String key = getJsonName(getterName);
				
				if ((showFieldsList != null && showFieldsList.contains(key)) || jsonAliasMapping.containsKey(key))
				{
					//Handle alias
					if(jsonAliasMapping.containsKey(key))
					{
						key = jsonAliasMapping.get(key);
					}			
					//isMethod
					if ('i' == methodName.charAt(0))
					{
						writeIsBooleanNode(template, key, getterName);
					}
					else
					{
						Class<?> returnType = methodList[index].getReturnType();
						//Skip class type, such as Class<?> getResultType()
						if (Class.class.equals(returnType))
						{
							continue;
						}
						String writeMethod = getJsonMethod(returnType);
						writeNode(template, key, getterName, writeMethod);
					}
				} 
			}
			
		}
		appendTempClass(template);
		return template.toString();
		
	}

	private static void appendTempClass(StringBuilder template)
	{
		template.append(TWO_TAB).append("if (customConvertor != null)\r\n");
		template.append(TWO_TAB).append("{\r\n");
		template.append(THREE_TAB).append("customConvertor.toJson(generator, object);\r\n");
		template.append(TWO_TAB).append("}\r\n");
		template.append("	}\r\n");
		template.append("}\r\n");
	}

	private static StringBuilder buildTempClass(String className, Class<?> modelClass)
	{
		String fullClassName = modelClass.getName();
		StringBuilder template = new StringBuilder();
		template.append(PACKAGE_NAME);
		template.append(DEFAULT_IMPORT);
		//Add import class 
		String simpleName = className.substring(className.lastIndexOf('.') + 1);
		//add class name
		template.append("\r\npublic class ").append(simpleName).append(" extends CustomJSONConvertor\r\n{\r\n");
		template.append("	@Override\r\n");
		//add method 
		template.append("	public void toJson(JsonGenerator generator, Object object) throws IOException\r\n");
		template.append("	{\r\n");
		//JsonTestModel model = (JsonTestModel) object;
		template.append(TWO_TAB).append(fullClassName).append(" model = (").append(fullClassName).append(") object;\r\n");
		template.append(TWO_TAB).append(" Set<String> fields =this.getFields();\r\n");
		return template;
	}

	private static Map<String, String> reversalJsonAliasNameMapping(Map<String, String> customJsonAliasMapping)
	{
		Map<String, String> jsonAliasMapping = new HashMap<String, String>();
		if(customJsonAliasMapping != null)
		{
			Iterator<Entry<String, String>> jsonMaps = customJsonAliasMapping.entrySet().iterator();
			while(jsonMaps.hasNext())
			{
				Entry<String, String> jsonMap = jsonMaps.next();
				jsonAliasMapping.put(jsonMap.getValue()/*Attribute*/, jsonMap.getKey()/*JSON*/);
			}
		}
		return jsonAliasMapping;
	}
	
	private static Set<String> converToSet(List<String> customFields)
	{
		if (customFields == null || customFields.isEmpty())
		{
			return null;
		}
		Set<String> skippedMethods = new HashSet<String>();
		int len = customFields.size();
		for (int index = 0; index < len; index++)
		{
			String skippedMethod = customFields.get(index);
			skippedMethods.add(skippedMethod);
		}
		return skippedMethods;
	}
	/**
	 *  Get right JSON method name for converting to JSON.
	 * 1. writeStringField (String/StringBuilder/StringBuffer/Date)
	 * 2. writeNumberField (int, Short, Integer, Long, Float, Double, BigDecimal, Byte)
	 * 3. writeBooleanField (bool, Boolean)
	 * 4. writeBinaryField (byte[])
	 * 5. writeCharArray (char[])
	 * 6. writeStringArray (String[])
	 * 7. writeList (List<String>, List<Long>, List<CustomObject>, Collection<String>, Collection<Object>)
	 * 8. writeMap (Map<String, Object>)
	 * 9. writeObjectField (Sub object such as CapModel.getCapDetailModel) 
	 * .
	 *
	 * @param returnType
	 * @return
	 */
	private static String getJsonMethod(Class<?> returnType)
	{
		String methodName = "writeObjectField";
		//String, StringBuffer, StringBuilder, 
		if (CharSequence.class.isAssignableFrom(returnType))
		{
			return "writeStringField";
		}
		//writeNumberField (int, Short, Integer, Long, Float, Double, BigDecimal, Byte)
		if (Number.class.isAssignableFrom(returnType) || Byte.class.equals(returnType)
				|| int.class.equals(returnType) || short.class.equals(returnType) 
				|| long.class.equals(returnType) || float.class.equals(returnType)
				|| double.class.equals(returnType) || byte.class.equals(returnType))
		{
			return "writeNumberField";
		}
		//writeBooleanField (bool, Boolean)
		if (Boolean.class.equals(returnType) || boolean.class.equals(returnType))
		{
			return "writeBooleanField";
		}
		//writeBinaryField (byte[])
		if (byte[].class.equals(returnType))
		{
			return "writeBinaryField";
		}
		//writeCharArray (char[])
		if (char[].class.equals(returnType))
		{
			return "writeCharArray";
		}
		//writeStringArray (String[])
		if (String[].class.equals(returnType))
		{
			return "writeStringArray";
		}
		//writeList (List<String>, List<Long>, List<CustomObject>, Collection<String>, Collection<Object>)
		if (List.class.isAssignableFrom(returnType) || Collection.class.equals(returnType))
		{
			return "writeList";
		}
		// writeMap(generator, "mapString", model.getMapString());
		if (Map.class.isAssignableFrom(returnType))
		{
			return "writeMap";
		}
		//enum type
		if (returnType.isEnum())
		{
			return "writeEnumField";
		}
		//java.util.Date, java.sql.Date, java.sql.TimeStamp
		if (Date.class.isAssignableFrom(returnType))
		{
			return "writeDateField";
		}
		//writeObjectField (Sub object such as CapModel.getCapDetailModel) 
		return methodName;
	}
	/**
	 * Generate JSON node. 
	 * WirteMethod may be the following json method.
	 * 1. writeStringField (String/StringBuilder/StringBuffer/Date)
	 * 2. writeNumberField (int, Short, Integer, Long, Float, Double, BigDecimal, Byte)
	 * 3. writeBooleanField (bool, Boolean)
	 * 4. writeBinaryField (byte[])
	 * 5. writeCharArray (char[])
	 * 6. writeStringArray (String[])
	 * 7. writeList (List<String>, List<Long>, List<CustomObject>, Collection<String>, Collection<Object>)
	 * 8. writeMap (Map<String, Object>)
	 * 9. writeObjectField (Sub object such as CapModel.getCapDetailModel) 
	 *
	 * @param template
	 * @param method
	 * @param writeMethod
	 */
	private static void writeNode(StringBuilder template, String jsonKey, String method, String writeMethod)
	{
		//writeList(generator, "collectionSubModel", model.getCollectionSubModel());
		//writeMap(generator, "mapString", model.getMapString());
		//template.append(TWO_TAB).append(writeMethod).append("(generator, \"").append(jsonKey).append("\", model.get");
		//template.append(method).append("());\r\n");
		
		template.append(TWO_TAB).append("if(fields==null || fields.contains(\"").append(jsonKey)
		.append("\")){").append("\r\n").append(TWO_TAB).append(TWO_TAB).append(writeMethod).append("(generator, \"").append(jsonKey).append("\", model.get");
		template.append(method).append("());\r\n").append(TWO_TAB).append("}\r\n");
	}
	/**
	 * Generate JSON node from isBoolean method. 
	 * For example,
	 * 	writeBooleanField(generator, "booleanVal", model.isBooleanVal());
	 *
	 * @param template
	 * @param method
	 * @param writeMethod
	 */
	protected static void writeIsBooleanNode(StringBuilder template, String jsonKey, String method)
	{
		//writeBooleanField(generator, "booleanVal", model.isBooleanVal());
		
		template.append(TWO_TAB).append("if(fields==null || fields.contains(\"").append(jsonKey).append("\")){")
				.append("\r\n").append(TWO_TAB).append(TWO_TAB).append("writeBooleanField(generator, \"").append(
					jsonKey).append("\", model.is");
		template.append(method).append("());\r\n").append(TWO_TAB).append("}\r\n");
		;
	}
	
	/**
	 * Get JSON key name by method name.
	 *
	 * @param methodName
	 * @return
	 */
	private static String getJsonName(String methodName)
	{
		int len = methodName.length();
		if (len <= 1)
		{
			return methodName.toLowerCase();
		}
		char firstChar = methodName.charAt(0);
		char secondChar = methodName.charAt(1);
		
		if (Character.isUpperCase(firstChar))	//for example, CapIDModel: ID1,ID2,ID3
		{
			if (Character.isUpperCase(secondChar))
			{
				return methodName;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toLowerCase(firstChar));
		sb.append(methodName.substring(1));
		return sb.toString();
	}
	
	/**
	 * Get getter name following Nature Property Naming Strategy.
	 *
	 * @param getterMethod
	 * @param methodName
	 * @return
	 */
	private static String getGetterName(Method getterMethod, String methodName)
	{
		if (methodName.length() < 5 || Modifier.isStatic(getterMethod.getModifiers()))
		{
			return null;
		}
		String getterName = null;
		char char1 = methodName.charAt(0);
		char char2 = methodName.charAt(1);
		char char3 = methodName.charAt(2);
		Class<?>[] parameters = getterMethod.getParameterTypes();
		//getter method does not have any parameter.
		if ((parameters == null || parameters.length == 0))
		{
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
		}
		return getterName;
		
	}
	
	public static void main(String[] argc) throws Exception
	{
		
//		String javaCode = generateCode("JsonTestModelConvertor", JsonTestModel.class, null);
//		System.out.println(javaCode);
//		BeanInfo beanInfo = Introspector.getBeanInfo(JsonTestModel.class);
//		PropertyDescriptor[] propertyList = beanInfo.getPropertyDescriptors();
//		//generator.writeStartObject();
//		for (PropertyDescriptor descriptor : propertyList)
//		{
//			Method getterMethod = descriptor.getReadMethod();
//			Method setterMethod = descriptor.getWriteMethod();
//			if (getterMethod == null)
//			{
//				continue;
//			}
//			String methodName = getterMethod.getName();
//			String getterName = getGetterName(getterMethod, methodName);
//			if (getterName != null)
//			{
//				System.out.println(getterMethod + " json type=" + getJsonMethod(getterMethod.getReturnType()));
//			}
//		}	
//		System.out.println("=================================================");
//		
//		Method[] methodList = JsonTestModel.class.getDeclaredMethods();
//		for (int index = 0; index < methodList.length; index++)
//		{
//			Method method = methodList[index];
//			String methodName = methodList[index].getName();
//			String getterName = getGetterName(methodList[index], methodName);
//			if (getterName != null)
//			{
//				System.out.println(method + " json type=" + getJsonMethod(method.getReturnType()));
//			}
//			
//		}
//		
	}
}
