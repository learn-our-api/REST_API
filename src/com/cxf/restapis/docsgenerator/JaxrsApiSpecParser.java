package com.cxf.restapis.docsgenerator;

import static com.wordnik.swagger.core.ApiValues.TYPE_BODY;
import static com.wordnik.swagger.core.ApiValues.TYPE_COOKIE;
import static com.wordnik.swagger.core.ApiValues.TYPE_FORM;
import static com.wordnik.swagger.core.ApiValues.TYPE_HEADER;
import static com.wordnik.swagger.core.ApiValues.TYPE_MATRIX;
import static com.wordnik.swagger.core.ApiValues.TYPE_PATH;
import static com.wordnik.swagger.core.ApiValues.TYPE_QUERY;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.cxf.restapis.docsgenerator.annotations.IgnoreParam;
import com.cxf.restapis.docsgenerator.model.Documentation;
import com.cxf.restapis.docsgenerator.model.DocumentationObject;
import com.cxf.restapis.docsgenerator.model.DocumentationParameter;
import com.cxf.restapis.docsgenerator.model.DocumentationSchema;
import com.cxf.restapis.framework.constants.APIConstants;
import com.cxf.restapis.framework.json.impl.ICustomConvertor;
import com.cxf.restapis.framework.security.annotations.ApiModelCustomConvertor;
import com.cxf.restapis.framework.util.CustomConvertorDefine;
import com.cxf.restapis.framework.util.TypeUtil;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiParamImplicit;
import com.wordnik.swagger.annotations.ApiParamsImplicit;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JaxrsApiSpecParser.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: JaxrsApiSpecParser.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-18		dylan.liang		Initial.
 * 
 * </pre>
 */
public class JaxrsApiSpecParser extends BaseApiParser
{

	/** The current class. */
	private Class<?> currentClass = null;

	/** The api version. */
	private String apiVersion = null;

	/** The swagger version. */
	private String swaggerVersion = null;

	/** The base path. */
	private String basePath = null;

	/** The resource path. */
	private String resourcePath = null;

	/** The api endpoint. */
	private Api apiEndpoint = null;

	/** The documentation. */
	private Documentation documentation = null;
	
	private String fileName = null;
	
	private static List<String> typeList = null;
	
	public static final String REQUEST = "request";
	
	public static final String RESPONSE = "response";
	
	/**
	 * Instantiates a new jaxrs api spec parser.
	 * 
	 * @param currentClass the current class
	 * @param apiVersion the api version
	 * @param swaggerVersion the swagger version
	 * @param basePath the base path
	 * @param resourcePath the resource path
	 */
	public JaxrsApiSpecParser(Class<?> currentClass, String apiVersion, String swaggerVersion, String basePath,
			String resourcePath)
	{
		super();
		this.currentClass = currentClass;
		this.apiVersion = apiVersion;
		this.swaggerVersion = swaggerVersion;
		this.basePath = basePath;
		this.resourcePath = resourcePath;
		this.apiEndpoint = currentClass.getAnnotation(Api.class);
	}

	/**
	 * Parses the.
	 * 
	 * @return the documentation
	 */
	public Map<String, Documentation> parse(String generatorVersion, Set<String> methods)
	{
		System.out.println("====== Start to parse documentation for " + currentClass + " ======");
		System.out.println("apiVersion: " + apiVersion + ", swaggerVersion: " + swaggerVersion + ", basePath: "
				+ basePath + ", resourcePath: " + resourcePath);

		Map<String, Documentation> documentMap = new HashMap<String, Documentation>();
		
		//TODO: Base on each method to generate document.
		for (Method method : currentClass.getMethods())
		{
			if (Modifier.isPublic(method.getModifiers()) && !method.getDeclaringClass().equals(Object.class))
			{
				ApiModelCustomConvertor modelCustomConvertor = method.getAnnotation(ApiModelCustomConvertor.class);
				Map<String, ICustomConvertor> newCustomConvertorMap = new HashMap<String, ICustomConvertor>();
				Map<String, ICustomConvertor> requestCustomConvertorDefine = new HashMap<String, ICustomConvertor>();
				Map<String, ICustomConvertor> responseCustomConvertorDefine = new HashMap<String, ICustomConvertor>();
				try
				{
					documentation = parseMethod(method, false, generatorVersion, methods);
					if (null == documentation)
					{
						continue;
					}
					
					if(!ValidationUtil.isEmpty(modelCustomConvertor))
					{
						if(ValidationUtil.isEmpty(modelCustomConvertor.requests()))
						{
							putCustomConvertor(newCustomConvertorMap, modelCustomConvertor.request(), REQUEST);
							
							putCustomConvertorArray(modelCustomConvertor.requestCustomConvertorDefine(), requestCustomConvertorDefine);
						}
						else
						{
							putCustomConvertorArray(modelCustomConvertor.requests(), requestCustomConvertorDefine);
						}
						
						if(ValidationUtil.isEmpty(modelCustomConvertor.responses()))
						{
							putCustomConvertor(newCustomConvertorMap, modelCustomConvertor.response(), RESPONSE);

							putCustomConvertorArray(modelCustomConvertor.responseCustomConvertorDefine(), responseCustomConvertorDefine);
						}
						else
						{
							putCustomConvertorArray(modelCustomConvertor.responses(), responseCustomConvertorDefine);
						}
						
						setCustomConvertorFromDefine(newCustomConvertorMap, requestCustomConvertorDefine, responseCustomConvertorDefine);
					}
					
					loadModel(documentation, newCustomConvertorMap, requestCustomConvertorDefine, responseCustomConvertorDefine, method);
					
					handleMultipleObjectResponseIdType(modelCustomConvertor);
					
					documentMap.put(fileName,documentation);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		System.out.println("====== End to parse the documentation ======");

		return documentMap;
	}
	
	//get root request convertor from requestCustomConvertorDefine, then get from request 
	private void setCustomConvertorFromDefine(Map<String, ICustomConvertor> customConvertorMap, 
			Map<String, ICustomConvertor> requestCustomConvertorMap,
			Map<String, ICustomConvertor> responseCustomConvertorMap)
	{
		String responseClassName = this.getResponseClassName();
		String requestClassName = this.getRequestClassName();
		
		if(!ValidationUtil.isEmpty(requestClassName))
		{
			if(requestCustomConvertorMap.containsKey(requestClassName))
			{
				customConvertorMap.put(REQUEST, requestCustomConvertorMap.get(requestClassName));
			}
			else
			{
				requestCustomConvertorMap.put(requestClassName, customConvertorMap.get(REQUEST));
			}
		}
		
		if(!ValidationUtil.isEmpty(responseClassName))
		{
			if(responseCustomConvertorMap.containsKey(responseClassName))
			{
				customConvertorMap.put(RESPONSE, responseCustomConvertorMap.get(responseClassName));
			}
			else
			{
				responseCustomConvertorMap.put(responseClassName, customConvertorMap.get(RESPONSE));
			}
		}
	}
	
	private String getRequestClassName()
	{
		if(!ValidationUtil.isEmpty(this.documentation.getParameters()))
		{
			for(DocumentationParameter param : this.documentation.getParameters())
			{
				if(TYPE_BODY.equals(param.getParamType()))
				{
					return param.getValueTypeInternal();
				}
			}
		}
		return null;
	}
	
	private String getResponseClassName()
	{
		return this.documentation.getResponseTypeInternal();
	}
	
	private void handleMultipleObjectResponseIdType (ApiModelCustomConvertor  customConvertor)
	{
		if (!ValidationUtil.isEmpty(customConvertor)
				&& !ValidationUtil.isEmpty(customConvertor.responseMultipleObjectResultModelIdType()) 
				&& APIConstants.MULTIPLEOBJECT_CLASSNAME.equals(documentation.getResponseTypeInternal()))
		{
			DocumentationSchema multipleObjectResultModel =  documentation.getModels().get(APIConstants.MULTIPLEOBJECT_MODELNAME);
			if (!ValidationUtil.isEmpty(multipleObjectResultModel))
			{
				DocumentationSchema idProperty = multipleObjectResultModel.getProperties().get(APIConstants.MULTIPLEOBJECT_IDNAME);
				idProperty.setType(customConvertor.responseMultipleObjectResultModelIdType());
			}
			
			
		}
	}
	
	private void putCustomConvertor(Map<String, ICustomConvertor> customConvertorDefine, String className, String key) throws Exception
	{
		if(!ValidationUtil.isEmpty(className))
		{
			Class<?> clazz = Class.forName(className);
			ICustomConvertor customConvertor = (ICustomConvertor)clazz.newInstance();
			if(key == null)
			{
				customConvertorDefine.put(customConvertor.getCustomClass().getName(), customConvertor);
			}
			else
			{
				customConvertorDefine.put(key, customConvertor);
			}
		}
	}
	
	private void putCustomConvertorArray(String[] customConvertors, Map<String, ICustomConvertor> customConvertorDefine) throws Exception
	{
		if(!ValidationUtil.isEmpty(customConvertors))
		{
			for(String customConvertorStr : customConvertors)
			{
				if(!ValidationUtil.isEmpty(customConvertorStr))
				{
					putCustomConvertor(customConvertorDefine, customConvertorStr, null);
				}
			}
		}
	}
	

	/**
	 * Parses the method.
	 * 
	 * @param method the method
	 */
	private Documentation parseMethod(Method method, boolean requestAlikeResponse, String generatorVersion, Set<String> methods)
	{
		Documentation documentation = new Documentation();	
		ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
		Path path = method.getAnnotation(Path.class);
		
		String changeVersionResourcePath = resourcePath;
		if ("V4".equals(generatorVersion))
		{
			changeVersionResourcePath = resourcePath.replaceFirst("[Vv][0-9]+", generatorVersion);
		}
		
		String newResourcePath = replaceSpecialChar(changeVersionResourcePath);
		if(newResourcePath.startsWith("_"))
		{
			fileName = parseHttpMethod(method, apiOperation)+"_"+apiVersion+newResourcePath;
		}
		else
		{
			fileName = parseHttpMethod(method, apiOperation)+"_"+apiVersion+"_"+newResourcePath;
		}
		if(path != null && path.value() != null)
		{
			String newPath = replaceSpecialChar(path.value().toString());
			if(!newPath.equals("_"))
			{
				if(newPath.startsWith("_"))
				{
					fileName = fileName + newPath;
				}
				else
				{
					fileName = fileName + "_" + newPath;
				}
			}
		}
		
		if (!methods.add(fileName))
		{
			return null;
		}
		
		
		if (apiOperation != null)
		{
			System.out.println("parsing " + currentClass + " method " + method.getName() + "...");


			if (apiOperation != null)
			{
				documentation.setHttpMethod(parseHttpMethod(method, apiOperation));
				documentation.setSummary(readString(apiOperation.value()));
				documentation.setNickname(method.getName());
				String apiResponseValue = readString(apiOperation.responseClass());
				boolean isResponseMultiValue = apiOperation.multiValueResponse();
				documentation.setResponseTypeInternal(apiResponseValue);
				try
				{
					Class<?> cls = BeanContext.loadClass(apiResponseValue);
//					String annotatedName = ApiPropertiesReader.readName(cls);
//					if(requestAlikeResponse)
//					{
//						annotatedName = annotatedName+"_response";
//					} 
					String clazzName = cls.getSimpleName();
					clazzName = clazzName.substring(0,1).toLowerCase()+clazzName.substring(1);
					documentation.setResponseType(isResponseMultiValue ? "List<" + clazzName + ">" : clazzName);
				}
				catch (ClassNotFoundException e)
				{
					documentation.setResponseType(isResponseMultiValue ? "List<" + apiResponseValue + ">"
							: apiResponseValue);
				}
			}

			// Read method annotations for implicit api parameters which are not declared as actual arguments to the
			// method
			// Essentially ApiParamImplicit annotations on method
			ApiParamsImplicit ma = method.getAnnotation(ApiParamsImplicit.class);
			if (ma != null)
			{
				for (ApiParamImplicit p : ma.value())
				{
					DocumentationParameter docParam = new DocumentationParameter();
					docParam.setParamType(TYPE_QUERY);
					docParam.setName(readString(p.name()));
					docParam.setDescription(readString(p.value()));
					docParam.setDefaultValue(readString(p.defaultValue()));
					docParam.setRequired(p.required());
					docParam.setIsArray(p.allowMultiple());
					docParam.setParamAccess(readString(p.access()));
					docParam.setInternalDescription(readString(p.internalDescription()));
					if(requestAlikeResponse)
					{
//						docParam.setDataType(readString(p.dataType())+"_request");
						docParam.setDataType(getParamTypeRequestName(readString(p.dataType())));
					}
					else
					{
						docParam.setDataType(readString(p.dataType()));
					}
					docParam.setParamType(readString(p.paramType()));
					docParam.setParamType(docParam.getParamType() == null ? TYPE_QUERY : docParam.getParamType());
					documentation.addParameter(docParam);
				}
			}

			// Read the parameters and add to Operation
			Annotation[][] paramAnnotationDoubleArray = method.getParameterAnnotations();
			Class<?>[] paramTypes = method.getParameterTypes();
			Type[] genericParamTypes = method.getGenericParameterTypes();
			int counter = 0;
			boolean ignoreParam = false;

			for (Annotation[] paramAnnotations : paramAnnotationDoubleArray)
			{
				DocumentationParameter docParam = new DocumentationParameter();
				docParam.setRequired(true);

				boolean isArray = false;
				// determine value type
				try
				{
					Class<?> paramTypeClass = paramTypes[counter];
					if(paramTypeClass.isArray() || Collection.class.isAssignableFrom(paramTypeClass))
					{
						isArray = true;
					}
					String paramTypeName = ApiPropertiesReader.getDataType(genericParamTypes[counter], paramTypeClass);
					if(requestAlikeResponse)
					{
						paramTypeName = getParamTypeRequestName(paramTypeName); 
					}
					docParam.setDataType(paramTypeName);

					if (!paramTypeClass.isPrimitive() && !paramTypeClass.getName().contains("java.lang"))
					{
						docParam.setValueTypeInternal(ApiPropertiesReader.getGenericTypeParam(
							genericParamTypes[counter], paramTypeClass));
					}
				}
				catch (Exception e)
				{
					System.out.println("Unable to determine datatype for param " + counter + " in method " + method);
					e.printStackTrace();
				}

				// paramAnnotationDoubleArray.foreach(paramAnnotations => {
				ignoreParam = processParamAnnotations(docParam, paramAnnotations, method);
				if(isArray)
				{
					docParam.setIsArray(isArray);
				}

				if (paramAnnotations.length == 0)
				{
					ignoreParam = true;
				}

				counter = counter + 1;

				// Set the default paramType, if nothing is assigned
				docParam.setParamType(readString(TYPE_BODY, docParam.getParamType()));
				if (!ignoreParam)
				{
					documentation.addParameter(docParam);
				}
			}
			
			// Append specific parameter access language for every method.
			DocumentationParameter docParam2 = new DocumentationParameter();
			docParam2.setName("lang");
			docParam2.setParamType("query");
			docParam2.setDataType("string");
			docParam2.setDescription("Language parameter to support I18N. Default language is en_US");
			docParam2.setRequired(new Boolean(false));
			docParam2.setIsArray(new Boolean(false));
			documentation.addParameter(docParam2);

			// Get Endpoint
			documentation.setPath(getPath(method, generatorVersion));
			documentation.setDescription(this.apiEndpoint.description());

			System.out.println("added operation " + documentation.getHttpMethod() + " from method " + method.getName());
		}
		return documentation;
	}

	private String getParamTypeRequestName(String paramTypeName)
	{
		if(paramTypeName.endsWith("Model>"))
		{
			paramTypeName = paramTypeName.replace(">", "_request>");
		}
		else if(paramTypeName.endsWith("Model[]"))
		{
			paramTypeName = paramTypeName.replace("[]", "_request[]");
		}
		else
		{
			paramTypeName = paramTypeName+"_request";
		}
		return paramTypeName;
	}

	private String replaceSpecialChar(String path)
	{
		String newResourcePath = path.replaceAll("/", "_");
		if(newResourcePath.indexOf("{")>0)
		{
			newResourcePath = newResourcePath.replaceAll("\\{", "");
		}
		if(newResourcePath.indexOf("}")>0)
		{
			newResourcePath = newResourcePath.replaceAll("\\}", "");
		}
		return newResourcePath;
	}

	/**
	 * Gets the path.
	 * 
	 * @param method the method
	 * @return the path
	 */
	private String getPath(Method method, String generatorVersion)
	{
		javax.ws.rs.Path wsPath = method.getAnnotation(javax.ws.rs.Path.class);
		
		String resourcePath = apiEndpoint.value();
		
		if ("V4".equals(generatorVersion))
		{
			resourcePath = apiEndpoint.value().replaceFirst("[Vv][0-9]+", generatorVersion);
		}
		
		String path = resourcePath + (wsPath == null ? "" : wsPath.value());
		return path;
	}

	/**
	 * Parses the api param.
	 * 
	 * @param docParam the doc param
	 * @param apiParam the api param
	 * @param method the method
	 */
	private void parseApiParam(DocumentationParameter docParam, ApiParam apiParam, Method method)
	{
		docParam.setName(readString(apiParam.name(), docParam.getName()));
		docParam.setDescription(readString(apiParam.value()));
		docParam.setDefaultValue(readString(apiParam.defaultValue()));
		docParam.setRequired(apiParam.required());
		docParam.setIsArray(apiParam.allowMultiple());
		docParam.setParamAccess(readString(apiParam.access()));
		//handle allowableValues
		List<String> lAllowableValues = toObjectList(apiParam.allowableValues());
		if (!ValidationUtil.isEmpty(lAllowableValues))
		{
			String[] allowableValues = (String[]) lAllowableValues.toArray(new String[lAllowableValues.size()]);
			docParam.setEnum(allowableValues);
		}
		
	}

	/**
	 * Process param annotations.
	 * 
	 * @param docParam the doc param
	 * @param paramAnnotations the param annotations
	 * @param method the method
	 * @return true, if successful
	 */
	private boolean processParamAnnotations(DocumentationParameter docParam, Annotation[] paramAnnotations,
			Method method)
	{
		boolean ignoreParam = false;

		for (Annotation pa : paramAnnotations)
		{
			if (pa instanceof ApiParam)
			{
				ApiParam apiParam = (ApiParam) pa;
				parseApiParam(docParam, apiParam, method);
			}
			else if (pa instanceof QueryParam)
			{
				QueryParam wsParam = (QueryParam) pa;
				docParam.setName(readString(wsParam.value(), docParam.getName()));
				docParam.setParamType(readString(TYPE_QUERY, docParam.getParamType()));
			}
			else if (pa instanceof PathParam)
			{
				PathParam wsParam = (PathParam) pa;
				docParam.setName(readString(wsParam.value(), docParam.getName()));
				docParam.setRequired(true);
				docParam.setParamType(readString(TYPE_PATH, docParam.getParamType()));
			}
			else if (pa instanceof MatrixParam)
			{
				MatrixParam wsParam = (MatrixParam) pa;
				docParam.setName(readString(wsParam.value(), docParam.getName()));
				docParam.setParamType(readString(TYPE_MATRIX, docParam.getParamType()));
			}
			else if (pa instanceof HeaderParam)
			{
				HeaderParam wsParam = (HeaderParam) pa;
				docParam.setName(readString(wsParam.value(), docParam.getName()));
				docParam.setParamType(readString(TYPE_HEADER, docParam.getParamType()));

			}
			else if (pa instanceof FormParam)
			{
				FormParam wsParam = (FormParam) pa;
				docParam.setName(readString(wsParam.value(), docParam.getName()));
				docParam.setParamType(readString(TYPE_FORM, docParam.getParamType()));
			}
			else if (pa instanceof CookieParam)
			{
				CookieParam ws = (CookieParam) pa;
				docParam.setName(readString(ws.value(), docParam.getName()));
				docParam.setParamType(readString(TYPE_COOKIE, docParam.getParamType()));
			}
			else if (pa instanceof Context)
			{
				ignoreParam = true;
			}
			
			if (pa instanceof IgnoreParam)
			{
				ignoreParam = true;
				break;
			}
		}

		return ignoreParam;
	}

	/**
	 * Parses the http method.
	 * 
	 * @param method the method
	 * @param apiOperation the api operation
	 * @return the string
	 */
	private String parseHttpMethod(Method method, ApiOperation apiOperation)
	{
		String httpMethod = null;

		if (apiOperation != null && apiOperation.httpMethod() != null && apiOperation.httpMethod().trim().length() > 0)
		{
			httpMethod = apiOperation.httpMethod().trim();
		}
		else
		{
			GET wsGet = method.getAnnotation(GET.class);
			DELETE wsDelete = method.getAnnotation(DELETE.class);
			POST wsPost = method.getAnnotation(POST.class);
			PUT wsPut = method.getAnnotation(PUT.class);
			HEAD wsHead = method.getAnnotation(HEAD.class);

			if (wsGet != null)
				httpMethod = GET.class.getSimpleName();
			else if (wsDelete != null)
				httpMethod = DELETE.class.getSimpleName();
			else if (wsPost != null)
				httpMethod = POST.class.getSimpleName();
			else if (wsPut != null)
				httpMethod = PUT.class.getSimpleName();
			else if (wsHead != null)
				httpMethod = HEAD.class.getSimpleName();
		}

		return httpMethod;
	}
	
	/**
	 * Load model.
	 * 
	 * @param d the d
	 */
	private static void loadModel(Documentation d, Map<String, ICustomConvertor> customConvertorMap,
			Map<String, ICustomConvertor> requestCustomConvertorDefine, Map<String, ICustomConvertor> responseCustomConvertorDefine, Method method)
	{
		List<String> directTypes = getExpectedTypes(d);
//		Collection<String> types = null;
		ICustomConvertor requestCustomConvertor = customConvertorMap.get(REQUEST);
		ICustomConvertor responseCustomConvertor = customConvertorMap.get(RESPONSE);
		Collection<String> requestTypes = null;
		Collection<String> responseTypes = null;
		try
		{
//			types = TypeUtil.getReferencedClasses(directTypes, customConvertorMap);
			requestTypes = TypeUtil.getReferencedClasses(directTypes, customConvertorMap, true, requestCustomConvertorDefine, responseCustomConvertorDefine);
			
			directTypes.clear();
			String responseModelName = null;
			if (!ValidationUtil.isEmpty(d.getResponseTypeInternal()))
			{
				responseModelName = d.getResponseTypeInternal().replaceAll("\\[\\]", "");
				directTypes.add(responseModelName);
			}
			responseTypes = TypeUtil.getReferencedClasses(directTypes, customConvertorMap, false, requestCustomConvertorDefine, responseCustomConvertorDefine);
		}
		catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		if(requestCustomConvertor != null)
		{
			Map<String, String> customJsonMapping = new HashMap<String, String>();
			if (!ValidationUtil.isEmpty(requestCustomConvertor.getCustomJsonMapping()))
			{
				customJsonMapping.putAll(requestCustomConvertor.getCustomJsonMapping());
			}
			typeList = new ArrayList<String>();
			appendModel4CustomerJSONNameMapping(requestTypes, customJsonMapping, requestCustomConvertorDefine, true);
		}
		if(responseCustomConvertor != null)
		{
			Map<String, String> customJsonMapping = new HashMap<String, String>();
			if (!ValidationUtil.isEmpty(responseCustomConvertor.getCustomJsonMapping()))
			{
				customJsonMapping.putAll(responseCustomConvertor.getCustomJsonMapping());
			}
			typeList = new ArrayList<String>();
			appendModel4CustomerJSONNameMapping(responseTypes, customJsonMapping, responseCustomConvertorDefine, false);
		}
		loopTypes(d, requestTypes, responseTypes, requestCustomConvertor, requestCustomConvertorDefine, responseCustomConvertorDefine, true, method);
		loopTypes(d, requestTypes, responseTypes, responseCustomConvertor, requestCustomConvertorDefine, responseCustomConvertorDefine, false, method);
	}

	private static void loopTypes(Documentation d, Collection<String> requestTypes, Collection<String> responseTypes, ICustomConvertor rootCustomConvertor,  
			Map<String, ICustomConvertor> requestCustomConvertorDefine, Map<String, ICustomConvertor> responseCustomConvertorDefine, boolean isRequest, Method method)
	{
		Collection<String> types = isRequest ? requestTypes : responseTypes;
		Map<String, ICustomConvertor> customConvertorDefine = isRequest ? requestCustomConvertorDefine : responseCustomConvertorDefine;
		for (String t : types)
		{
			try
			{
				if (isPrimitiveType(t))
				{
					continue;
				}
				String newClass = t;
				
				Class<?> c = BeanContext.loadClass(newClass);
				DocumentationObject n = ApiPropertiesReader.read(c, rootCustomConvertor, customConvertorDefine);
				
				if (n != null && !ValidationUtil.isEmpty(n.getFields()))
				{
					String propertyName = n.getName();
					if(requestTypes.contains(t) && responseTypes.contains(t))
					{
						if(requestCustomConvertorDefine.containsKey(c.getName()) || responseCustomConvertorDefine.containsKey(c.getName()))
						{
							if(isRequest)
							{
								propertyName +="_request";
							}
							else
							{
								propertyName +="_response";
							}
						}
					}
					if(!isRequest && n.getName().equals(d.getResponseType()) && !propertyName.equals(d.getResponseType()))
					{
						d.setResponseType(propertyName);
					}
					if(isRequest)
					{
						for(DocumentationParameter param : d.getParameters())
						{
							if(param!=null && n.getName().equals(param.getDataType()) && !propertyName.equals(param.getDataType()))
							{
								param.setDataType(propertyName);
							}
						}
					}
					for(DocumentationParameter param : n.getFields())
					{
						String paramType = param.getParamType();
						if(containsParamType(requestTypes, paramType) && containsParamType(responseTypes, paramType))
						{
							if(containsParamType(requestCustomConvertorDefine.keySet(), paramType) || containsParamType(responseCustomConvertorDefine.keySet(), paramType))
							{
								if(isRequest)
								{
									paramType +="_request";
								}
								else
								{
									paramType +="_response";
								}
								param.setParamType(paramType);
							}
						}
					}
					d.addModel(propertyName, n.toDocumentationSchema(propertyName));
				}
				else
				{
					if (n == null)
					{
						System.out.println("Skipping model " + t + ". Could not load the model.");
					}
					else if (ValidationUtil.isEmpty(n.getFields()))
					{
						System.out.println("Skipping model " + t
								+ ". Did not find any public fields or bean-properties with JsonView in this model.");
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Unable to load model documentation for " + t);
				e.printStackTrace();
			}
		}
	}
	
	private static boolean containsParamType(Collection<String> types, String paramType)
	{
		paramType = paramType.substring(0, 1).toUpperCase() + paramType.substring(1);
		for(String type : types)
		{
			if(type.endsWith(paramType))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Append new model to types in jsonNameMapping of custom convertor.
	 * Because the model is not define in primary model, it only used construct model structure. so we need generic document also.
	 * for example: //RefOwnerModelCustomConvertor - com.accela.restapis.owner.model.OwnerAddressModel
	 * @param newTypes
	 */
	private static void appendModel4CustomerJSONNameMapping(Collection<String> newTypes, Map<String, String> customJsonMapping,
			Map<String, ICustomConvertor> customConvertorDefine, boolean isRequest)
	{
		
		//Handle sub convert model.
		if(ValidationUtil.isEmpty(customJsonMapping))
		{
			customJsonMapping = new HashMap<String, String>();
		}
		
		for(String typeClass : newTypes)
		{
			if(customConvertorDefine.containsKey(typeClass))
			{
				ICustomConvertor subCustomConvertor = customConvertorDefine.get(typeClass);
				Map<String, String> subCustomJsonMapping = subCustomConvertor.getCustomJsonMapping();
				if(subCustomJsonMapping != null)
				{
					customJsonMapping.putAll(subCustomJsonMapping);
				}
			}
			else
			{
				Map<Object, Object> customMap = CustomConvertorDefine.getCustomMap();
				if(customMap.containsKey(typeClass))
				{
					try
					{
						String className = (String) customMap.get(typeClass);
						Class<?> clazz = BeanContext.loadClass(className);
						ICustomConvertor subCustomConvertor =(ICustomConvertor)clazz.newInstance();
						Map<String, String> subCustomJsonMapping = subCustomConvertor.getCustomJsonMapping();
						customJsonMapping.putAll(subCustomJsonMapping);
					}
					catch(Exception e)
					{
						
					}
				}
			}
		}
		
		if(!ValidationUtil.isEmpty(customJsonMapping))
		{
			Iterator<Entry<String, String>> jsonMaps =customJsonMapping.entrySet().iterator();
			while(jsonMaps.hasNext())
			{
				Entry<String, String> jsonMap =jsonMaps.next();
				String dataType = jsonMap.getValue();
				String newDataType = null;
				if(dataType != null && dataType.indexOf("<")>0)
				{
					String typePrefix = dataType.substring(0, dataType.indexOf("<"));
					newDataType = typePrefix.substring(typePrefix.lastIndexOf(".")+1);
				}
				else
				{
					newDataType = dataType.substring(1);
				}
				
				if(newDataType.equalsIgnoreCase("List"))
				{
					newDataType = dataType.substring(dataType.indexOf("<")+1, dataType.indexOf(">"));
				}
				else if(newDataType.equalsIgnoreCase("Map"))
				{
					//TODO:
				}
				else if(newDataType.equalsIgnoreCase("Set"))
				{
					//TODO:
				}
				
				if(newDataType.startsWith("com.accela") && !newTypes.contains(newDataType))
				{
					if(typeList.contains(newDataType))
					{
						continue;
					}
					typeList.add(newDataType);

					List<String> directTypes = new ArrayList<String>();
					directTypes.add(newDataType);
					Collection<String> types = null;
					try
					{
						if(isRequest)
						{
							types = TypeUtil.getReferencedClasses(directTypes, null, isRequest, customConvertorDefine, null);
						}
						else
						{
							types = TypeUtil.getReferencedClasses(directTypes, null, isRequest, null, customConvertorDefine);
						}
					}
					catch (ClassNotFoundException e1)
					{
						e1.printStackTrace();
					}
					appendModel4CustomerJSONNameMapping(types, null, customConvertorDefine, isRequest);
					newTypes.addAll(typeList);
				}
				
			}
		}
		
	}

	
	
	/**
	 * Checks if is primitive type.
	 *
	 * @param type the type
	 * @return true, if is primitive type
	 */
	public static boolean isPrimitiveType(String type)
	{
		boolean isPrimitiveType = false;

		if (!ValidationUtil.isEmpty(type))
		{
			if (Character.TYPE.toString().equalsIgnoreCase(type) || Byte.TYPE.toString().equalsIgnoreCase(type)
					|| Boolean.TYPE.toString().equalsIgnoreCase(type) || Integer.TYPE.toString().equalsIgnoreCase(type)
					|| Short.TYPE.toString().equalsIgnoreCase(type) || Long.TYPE.toString().equalsIgnoreCase(type)
					|| Double.TYPE.toString().equalsIgnoreCase(type) || Float.TYPE.toString().equalsIgnoreCase(type)
					|| Void.TYPE.toString().equalsIgnoreCase(type))
			{
				isPrimitiveType = true;
			}
		}

		return isPrimitiveType;

	}

	/**
	 * Gets the expected types.
	 * 
	 * @param d the d
	 * @return the expected types
	 */
	private static List<String> getExpectedTypes(Documentation d)
	{
		Set<String> set = new HashSet<String>();
		if (d != null)
		{
//			if (!ValidationUtil.isEmpty(d.getResponseTypeInternal()))
//			{
//				set.add(d.getResponseTypeInternal().replaceAll("\\[\\]", ""));
//			}

			if (!ValidationUtil.isEmpty(d.getParameters()))
			{
				for (DocumentationParameter pa : d.getParameters())
				{
					if (!ValidationUtil.isEmpty(pa.getValueTypeInternal()))
					{
						set.add(pa.getValueTypeInternal().replaceAll("\\[\\]", ""));
					}
				}
			}
		}

		return new ArrayList<String>(set);
	}
}

/*
 * $Log: av-env.bat,v $
 */
