package com.cxf.restapis.docsgenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;

import com.cxf.restapis.docsgenerator.model.DocumentationObject;
import com.cxf.restapis.docsgenerator.model.DocumentationParameter;
import com.cxf.restapis.docsgenerator.model.ModelParserObject;
import com.cxf.restapis.framework.json.impl.ICustomAllFields;
import com.cxf.restapis.framework.json.impl.ICustomConvertor;
import com.cxf.restapis.framework.util.CustomConvertorDefine;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.wordnik.swagger.annotations.ApiProperty;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ApiModelParser.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: ApiModelParser.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-11-1		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ApiModelParser extends BaseApiParser
{
	/** The documentation object. */
	private DocumentationObject documentationObject = null;

	/** The host class. */
	private Class<?> hostClass = null;

	/** The processed fields. */
	private List<String> processedFields = null;

	private ICustomConvertor customConvertor;
	
	private Map<String, ICustomConvertor> customConvertorDefine;
	
	/**
	 * Instantiates a new api model parser.
	 * 
	 * @param hostClass the host class
	 */
	public ApiModelParser(Class<?> hostClass)
	{
		super();
		this.hostClass = hostClass;
	}

	/**
	 * @return the customConvertor
	 */
	
	public ICustomConvertor getRootCustomConvertor()
	{
		return customConvertor;
	}

	/**
	 * @param customConvertor the customConvertor to set
	 */
	public void setRootCustomConvertor(ICustomConvertor customConvertor)
	{
		this.customConvertor = customConvertor;
	}

	/**
	 * Parses the.
	 * 
	 * @return the documentation object
	 */
	public DocumentationObject parse()
	{
		this.documentationObject = new DocumentationObject();
//		this.documentationObject.setName(readName(this.hostClass));
		String name = this.hostClass.getSimpleName();
		name = name.substring(0,1).toLowerCase()+name.substring(1);
		this.documentationObject.setName(name);
		processedFields = new ArrayList<String>();
		parseRecurrsive(this.hostClass);
		return this.documentationObject;
	}

	/**
	 * Read name.
	 * 
	 * @param hostClass the host class
	 * @return the string
	 */
	public String readName(Class<?> hostClass)
	{
		return readName(hostClass, true);
	}

	/**
	 * Read name.
	 * 
	 * @param hostClass the host class
	 * @param isSimple the is simple
	 * @return the string
	 */
	public String readName(Class<?> hostClass, Boolean isSimple)
	{
		XmlRootElement xmlRootElement = hostClass.getAnnotation(XmlRootElement.class);
		XmlEnum xmlEnum = hostClass.getAnnotation(XmlEnum.class);

		String name = null;

		if (xmlEnum != null && xmlEnum.value() != null)
		{
			name = readName(xmlEnum.value());
		}
		else if (xmlRootElement != null)
		{
			if ("##default".equals(xmlRootElement.name()))
			{
				if (isSimple)
				{
					name = hostClass.getSimpleName();
				}
				else
				{
					name = hostClass.getName();
				}
			}
			else
			{
				if (isSimple)
				{
					name = readString(xmlRootElement.name());
				}
				else
				{
					name = hostClass.getName();
				}
			}
		}
		else if (hostClass.getName().startsWith("java.lang."))
		{
			name = hostClass.getName().substring("java.lang.".length());
		}
		else if (hostClass.getName().indexOf(".") < 0)
		{
			name = hostClass.getName();
		}
		else
		{
			System.out.println("Class " + hostClass.getName()
					+ " is not annotated with a @XmlRootElement annotation, using " + hostClass.getSimpleName());

			if (isSimple)
			{
				name = hostClass.getSimpleName();
			}
			else
			{
				name = hostClass.getName();
			}
		}
		if(isSimple && name != null && !name.equals("") && name.length()>1)
		{
			name = name.substring(0,1).toLowerCase()+name.substring(1);
			name = convertDataType(name);
		}
		return name;
	}

	/**
	 * Parse methods from the class and all of its parent classes.
	 * 
	 * @param hostClass the host class
	 */
	private void parseRecurrsive(Class<?> hostClass)
	{ 
		if (null != hostClass)
		{
			
			Map<String, String> builderAliasNameMapping = null;
			Map<String, String[]> customValidValue = null;
			List<String> customFields = null;
			List<String> showFields = null;
			boolean isCustomAllFields =false;
			boolean isUseConvert =false;
			ICustomConvertor customConvertor = getCustomConvertor(hostClass);
			if(!ValidationUtil.isEmpty(customConvertor))
			{
				if(customConvertor instanceof ICustomAllFields)
				{
					isCustomAllFields = true;
					showFields = customConvertor.getShowFields();
				}
				builderAliasNameMapping = reversalJsonAliasNameMapping(customConvertor.getCustomJsonAliasMapping());
				customFields = customConvertor.getCustomFields();
				customValidValue = customConvertor.getCustomValidValue();
				
				//Handle defined data type.
				Map<String, String> customJsonMapping = customConvertor.getCustomJsonMapping();
				parseDefinedProperty(customJsonMapping);
				isUseConvert = true;
			}
			
			for (Method method : hostClass.getMethods())
			{
				if (Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()))
				{
					parseMethod(method, customFields, showFields, builderAliasNameMapping, customValidValue, isCustomAllFields, isUseConvert);
				}
			}

//			for (Field field : hostClass.getDeclaredFields())
//			{
//				parseField(field, customFields, showFields, builderAliasNameMapping, isCustomAllFields, isUseConvert);
//			}

			//parseRecurrsive(hostClass.getSuperclass());
		}
	}

	private ICustomConvertor getCustomConvertor(Class<?> hostClass)
	{
		ICustomConvertor customConvertor = null;
		ICustomConvertor rootCustomConvertor = getRootCustomConvertor();	
		if(!ValidationUtil.isEmpty(rootCustomConvertor))
		{
			if(hostClass.getName().equals(rootCustomConvertor.getCustomClass().getName()))
			{
				customConvertor = rootCustomConvertor;
			}
			else
			{
				if(this.customConvertorDefine != null && this.customConvertorDefine.containsKey(hostClass.getName()))
				{
					return this.customConvertorDefine.get(hostClass.getName());
				}
				Map<Object, Object> customMap = CustomConvertorDefine.getCustomMap();
				if(customMap.containsKey(hostClass.getName()))
				{
					try
					{
						String className = (String) customMap.get(hostClass.getName());
						Class<?> clazz = BeanContext.loadClass(className);
						ICustomConvertor subCustomConvertor =(ICustomConvertor)clazz.newInstance();
						customConvertor = subCustomConvertor;
					}
					catch(Exception e)
					{
						
					}
				}
			}
		}
		
		return customConvertor;
	}

	private void parseDefinedProperty(Map<String, String> customJsonMapping)
	{
		if(!ValidationUtil.isEmpty(customJsonMapping))
		{
			Iterator<Entry<String, String>> jsonMaps =customJsonMapping.entrySet().iterator();
			while(jsonMaps.hasNext())
			{
				Entry<String, String> jsonMap =jsonMaps.next();
				String name = jsonMap.getKey();
				String dataType = jsonMap.getValue();
				DocumentationParameter docParam = new DocumentationParameter();
				//docParam.setRequired(new Boolean(false));
				docParam.setName(name);
				//handle data type. change it @com.accela.i18n.IdentifierModel  -> identifierModel
				String type = null;
				String typePrefix = null;
				if(dataType.indexOf("<")>0)
				{
					typePrefix = dataType.substring(0, dataType.indexOf("<"));
					type = typePrefix.substring(typePrefix.lastIndexOf(".")+1);
				}
				else
				{
					type = dataType.substring(dataType.lastIndexOf(".")+1);
				}
				
				if(type.equalsIgnoreCase("List"))
				{
					String subType = dataType.substring(dataType.lastIndexOf(".")+1, dataType.indexOf(">"));
					String firstName = String.valueOf(subType.charAt(0));
					subType = firstName.toLowerCase()+ subType.substring(1);
					
					try
					{
						Class<?> clazz = BeanContext.loadClass(dataType.substring(dataType.indexOf("<") + 1, dataType
								.indexOf(">")));
//						subType = readName(clazz);
						subType = clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1); 
					}
					catch (ClassNotFoundException e)
					{
					}
					
					type = "List<"+ subType+">";
				}
				else if(type.equalsIgnoreCase("Map"))
				{
					//TODO:
				}
				else if(type.equalsIgnoreCase("Set"))
				{
					//TODO:
				}
				else if(!JaxrsApiSpecParser.isPrimitiveType(type))
				{
					String firstName = String.valueOf(type.charAt(0));
					type = firstName.toLowerCase()+ type.substring(1);
				}
				type = convertDataType(type);
				docParam.setParamType(type);
				if (!"void".equals(docParam.getParamType()) && null != docParam.getParamType()
						&& !processedFields.contains(name))
				{
					documentationObject.addField(docParam);
				}
				processedFields.add(name);
			}
		}
	}
	
	private  Map<String, String> reversalJsonAliasNameMapping(Map<String, String> customJsonAliasMapping)
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


	/**
	 * Parses the field.
	 * 
	 * @param field the field
	 */
	private void parseField(Field field, List<String> customFields,  List<String> showFields, Map<String, String> builderAliasNameMapping, boolean isCustomAllFields, boolean isUseConvert)
	{
		if(!isUseConvert)
		{
			parsePropertyAnnotations(field.getName(), field.getAnnotations(), field.getGenericType(), field.getType());
		}
	}

	/**
	 * Parses the method.
	 * 
	 * @param method the method
	 */
	private void parseMethod(Method method,  List<String> customFields, List<String> showFields, Map<String, String> builderAliasNameMapping, Map<String, String[]> customValidValue, boolean isCustomAllFields, boolean isUseConvert)
	{
		if (method.getParameterTypes() == null || method.getParameterTypes().length == 0)
		{
			if(!isUseConvert)
			{
				parsePropertyAnnotations(method.getName(), method.getAnnotations(), method.getGenericReturnType(),
					method.getReturnType());
			}
			else
			{
				parsePropertyWithoutAnnotations(method.getName(), method.getGenericReturnType(),
					method.getReturnType(), customFields, showFields, builderAliasNameMapping, customValidValue, isCustomAllFields);
			}
		}
	}

	/**
	 * Extract getter property.
	 * 
	 * @param methodFieldName the method field name
	 * @return the getter property
	 */
	private GetterProperty extractGetterProperty(String methodFieldName)
	{

		GetterProperty getterProperty = new GetterProperty();

		if (methodFieldName != null && (methodFieldName.startsWith("get")) && methodFieldName.length() > 3)
		{
			String fieldName = methodFieldName.substring(3);
			char firstChar = fieldName.charAt(0);
			char secondChar = fieldName.charAt(1);
			
			if (Character.isUpperCase(firstChar) && Character.isUpperCase(secondChar))	//for example, CapIDModel: ID1,ID2,ID3
			{
				getterProperty.setFieldName(fieldName);
			}
			else
			{
				getterProperty.setFieldName(methodFieldName.substring(3, 4).toLowerCase()
						+ methodFieldName.substring(4, methodFieldName.length()));
				
			}
			getterProperty.setGetter(true);
		}
		else if (methodFieldName != null && (methodFieldName.startsWith("is")) && methodFieldName.length() > 2)
		{
			getterProperty.setFieldName(methodFieldName.substring(2, 3).toLowerCase()
					+ methodFieldName.substring(3, methodFieldName.length()));
			getterProperty.setGetter(true);
		}
		else
		{
			getterProperty.setFieldName(methodFieldName);
			getterProperty.setGetter(false);
		}

		return getterProperty;
	}

	private void parsePropertyWithoutAnnotations(String methodFieldName,
			Type genericReturnType, Type returnType, List<String> customFields, List<String> showFields, Map<String, String> aliasNameMapping, Map<String, String[]> customValidValue, boolean isCustomAllFields)
	{
		GetterProperty getterProperty = extractGetterProperty(methodFieldName);
		String name = getterProperty.getFieldName();
		
		if(!isCustomAllFields)
		{
			DocumentationParameter docParam = new DocumentationParameter();
			//docParam.setRequired(new Boolean(false));
			
			//Handle language model's value.
			if(name!= null && (name.equals("hasResource") || name.equals("resId") ||name.equals("enbaleI18N") 
					||name.equals("auditID") ||name.equals("auditDate") ||name.equals("clone") 
					||name.equals("toString") ||name.equals("resLangId") ||name.equals("hashCode")
					||name.startsWith("i18N")))
			{
				return;
			}
			
			if(aliasNameMapping.containsKey(name))
			{
				name = aliasNameMapping.get(name);
			}
			docParam.setName(name);
			
			//valid value setting
			if(!ValidationUtil.isEmpty(customValidValue) && customValidValue.containsKey(name))
			{
				docParam.setEnum(customValidValue.get(name));
			}
			
			docParam.setParamType(ApiPropertiesReader.getDataType(genericReturnType, returnType));
			if(customFields == null)
			{
				customFields = new ArrayList<String>();
			}
			if (!"void".equals(docParam.getParamType()) && null != docParam.getParamType()
					&& !processedFields.contains(name)
					&& !customFields.contains(name))
			{
				documentationObject.addField(docParam);
			}
			processedFields.add(name);	
			
		}
		else
		{
			if(aliasNameMapping.containsKey(name) || (!ValidationUtil.isEmpty(showFields) && showFields.contains(name)))
			{
				DocumentationParameter docParam = new DocumentationParameter();
				docParam.setRequired(new Boolean(false));
				if(aliasNameMapping.containsKey(name))
				{
					name = aliasNameMapping.get(name);
				}
				docParam.setName(name);
				
				//valid value setting
				if(!ValidationUtil.isEmpty(customValidValue) && customValidValue.containsKey(name))
				{
					docParam.setEnum(customValidValue.get(name));
				}
				
				docParam.setParamType(ApiPropertiesReader.getDataType(genericReturnType, returnType));
		
				if (!"void".equals(docParam.getParamType()) && null != docParam.getParamType()
						&& !processedFields.contains(name))
				{
					documentationObject.addField(docParam);
				}
				processedFields.add(name);
			}
		}
		
		

		
	}
	/**
	 * Parses the property annotations.
	 * 
	 * @param methodFieldName the method field name
	 * @param methodAnnotations the method annotations
	 * @param genericReturnType the generic return type
	 * @param returnType the return type
	 */
	private void parsePropertyAnnotations(String methodFieldName, Annotation[] methodAnnotations,
			Type genericReturnType, Type returnType)
	{
		GetterProperty getterProperty = extractGetterProperty(methodFieldName);
		String name = getterProperty.getFieldName();
		boolean isGetter = getterProperty.isGetter();

		DocumentationParameter docParam = new DocumentationParameter(); 
		//docParam.setRequired(new Boolean(false));
		boolean isFieldExists = false;

		ModelParserObject methodAnnoOutput = processAnnotations(name, methodAnnotations, docParam);
		boolean isJsonIgnore = methodAnnoOutput.isJsonIgnore();
		boolean isJsonProperty = methodAnnoOutput.isJsonProperty();
		boolean isDocumented = methodAnnoOutput.isDocumented();
		boolean isJsonView = methodAnnoOutput.isJsonView();
		Class<?>[] jsonViews = methodAnnoOutput.getJsonViewType();

		try
		{
			Annotation[] propertyAnnotations = getDeclaredField(this.hostClass, name).getAnnotations();
			ModelParserObject propAnnoOutput = processAnnotations(name, propertyAnnotations, docParam);
			isFieldExists = true;
			if (!isJsonProperty)
			{
				isJsonProperty = propAnnoOutput.isJsonProperty();
			}
			if (!isJsonIgnore)
			{
				isJsonIgnore = propAnnoOutput.isJsonIgnore();
			}
			if (!isJsonView)
			{
				isJsonView = propAnnoOutput.isJsonView();
			}
			if (jsonViews == null)
			{
				jsonViews = propAnnoOutput.getJsonViewType();
			}
		}
		catch (NoSuchFieldException e)
		{
			// this means there is no field declared to look for field level annotations.
			isJsonIgnore = false;
		}

		if (docParam.getName() == null && name != null)
		{
			docParam.setName(name);
		}

		if (!isJsonView)
		{
			isJsonIgnore = true;
		}

		if (!(isJsonIgnore && !isJsonProperty) && docParam.getName() != null
				&& (isFieldExists || isGetter || isDocumented))
		{
			if (docParam.getParamType() == null)
			{
				docParam.setParamType(ApiPropertiesReader.getDataType(genericReturnType, returnType));
			}

			if (!"void".equals(docParam.getParamType()) && null != docParam.getParamType()
					&& !processedFields.contains(docParam.getName()))
			{
				documentationObject.addField(docParam);
			}

			processedFields.add(docParam.getName());
		}
	}

	/**
	 * Incase of subclass and superclass scenario, for properties defined at base class we need to get the super class
	 * and find the fields.
	 * 
	 * @param inputClass the input class
	 * @param fieldName the field name
	 * @return the declared field
	 * @throws NoSuchFieldException the no such field exception
	 */
	private Field getDeclaredField(Class<?> inputClass, String fieldName) throws NoSuchFieldException
	{
		try
		{
			return inputClass.getDeclaredField(fieldName);
		}
		catch (NoSuchFieldException e)
		{
			if (inputClass.getSuperclass() != null && inputClass.getSuperclass().getName() != "Object")
			{
				return getDeclaredField(inputClass.getSuperclass(), fieldName);
			}
			else
			{
				throw e;
			}
		}
	}

	/**
	 * Process annotations.
	 * 
	 * @param name the name
	 * @param annotations the annotations
	 * @param docParam the doc param
	 * @return ModelParserObject
	 */
	private ModelParserObject processAnnotations(String name, Annotation[] annotations, DocumentationParameter docParam)
	{
		ModelParserObject mpObj = new ModelParserObject();

		for (Annotation ma : annotations)
		{
			if (ma instanceof JsonIgnore)
			{
				mpObj.setJsonIgnore(true);
			}
			else if (ma instanceof ApiProperty)
			{
				ApiProperty apiProperty = (ApiProperty) ma;
				docParam.setDescription(readString(apiProperty.value()));
				docParam.setNotes(readString(apiProperty.notes()));
				docParam.setParamType(readString(apiProperty.dataType()));
				mpObj.setDocumented(true);
				docParam.setParamAccess(readString(apiProperty.access()));
				
				//handle allowableValues
				List<String> lAllowableValues = toObjectList(apiProperty.allowableValues());
				if (!ValidationUtil.isEmpty(lAllowableValues))
				{
					String[] allowableValues = (String[]) lAllowableValues.toArray(new String[lAllowableValues.size()]);
					docParam.setEnum(allowableValues);
				}

			}
			else if (ma instanceof JsonView)
			{
				JsonView jsonView = (JsonView) ma;
				mpObj.setJsonView(true);
				mpObj.setJsonViewType(jsonView.value());
			}
			else if (ma instanceof JsonProperty)
			{
				JsonProperty jsonProperty = (JsonProperty) ma;
				docParam.setName(readString(jsonProperty.value(), docParam.getName()));
				docParam.setName(readString(name, docParam.getName()));
				mpObj.setJsonProperty(true);
			}
		}

		return mpObj;
	}
	
	public static String convertDataType(String dataType)
	{
		if("int".equals(dataType) || "integer".equals(dataType))
		{
			dataType = "long";
		}
		else if("date".equals(dataType))
		{
			dataType = "dateTime";
		}
		return dataType;
	}

	/**
	 * @return the customConvertorDefine
	 */
	
	public Map<String, ICustomConvertor> getCustomConvertorDefine()
	{
		return customConvertorDefine;
	}

	/**
	 * @param customConvertorDefine the customConvertorDefine to set
	 */
	public void setCustomConvertorDefine(Map<String, ICustomConvertor> customConvertorDefine)
	{
		this.customConvertorDefine = customConvertorDefine;
	}

}

class GetterProperty
{
	/** The field name. */
	private String fieldName;

	/** The is getter. */
	private boolean isGetter;

	/**
	 * @return the fieldName
	 */

	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 * @return the isGetter
	 */
	public boolean isGetter()
	{
		return isGetter;
	}

	/**
	 * @param isGetter the isGetter to set
	 */
	public void setGetter(boolean isGetter)
	{
		this.isGetter = isGetter;
	}
}

/*
 * $Log: av-env.bat,v $
 */
