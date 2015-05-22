package com.cxf.restapis.framework.jaxrs.interceptors;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.JAXRSServiceImpl;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.jaxrs.impl.RequestPreprocessor;
import org.apache.cxf.jaxrs.impl.UriInfoImpl;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.model.ProviderInfo;
import org.apache.cxf.jaxrs.model.URITemplate;
import org.apache.cxf.jaxrs.provider.ProviderFactory;
import org.apache.cxf.jaxrs.utils.HttpUtils;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.Service;

import com.cxf.restapis.framework.constants.MessageConstants;
import com.cxf.restapis.framework.exception.DataValidateException;
import com.cxf.restapis.framework.exception.ObjectNotFoundException;
import com.cxf.restapis.framework.json.impl.CustomJSONProvider;
import com.cxf.restapis.framework.json.service.ApiDataSourceProvider;
import com.cxf.restapis.framework.model.RestThreadLocal;
import com.cxf.restapis.framework.security.annotations.IgnoreBody;
import com.cxf.restapis.framework.util.UpdateFactory;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.cxf.restapis.framework.util.WebThreadLocal;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JAXRSInInterceptor.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: JAXRSInInterceptor.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Apr 13, 2013		evan.cai		Initial.
 *  
 * </pre>
 */
public class JAXRSInInterceptor extends AbstractPhaseInterceptor<org.apache.cxf.message.Message>
{
//	private static final Logger logger = Logger.getLogger(JAXRSInInterceptor.class);
	
    public static final String RELATIVE_PATH = "relative.path";
    public static final String ROOT_RESOURCE_CLASS = "root.resource.class";
    public static final String PUT = "PUT";
    public static final String POST = "POST";
    public static final String PACKAGEPATH = "com.bryant";
    public static final String BODY = "body";

    public JAXRSInInterceptor() {
        super(Phase.PRE_STREAM);
    }

	public void handleMessage(Message message) throws Fault
	{

		try
		{
			/** Clean WebThreadLocal before every API invoking */
			WebThreadLocal.clean();

			processRequest(message); 
		}
		catch (Fault fault)
		{
			throw fault;
		}
		catch (RuntimeException ex)
		{
			Response excResponse = JAXRSUtils.convertFaultToResponse(ex, message);
			if (excResponse == null)
			{
				ProviderFactory.getInstance(message).clearThreadLocalProxies();
				throw ex;
			}
			message.getExchange().put(Response.class, excResponse);
			Fault fault = new Fault(new Exception(String.format(String.valueOf(excResponse.getStatus()))));
			throw fault;
		}
	}
    
    private void processRequest(Message message) {
        
        if (message.getExchange().get(OperationResourceInfo.class) != null) {
            // it's a suspended invocation;
            return;
        }
        
        RequestPreprocessor rp = ProviderFactory.getInstance(message).getRequestPreprocessor();
        if (rp != null) {
            rp.preprocess(message, new UriInfoImpl(message, null));
        }
        
        String requestContentType = (String)message.get(Message.CONTENT_TYPE);
        if (requestContentType == null) {
            requestContentType = "*/*";
        }
        
        String rawPath = HttpUtils.getPathToMatch(message, true);

        //1. Matching target resource class
        Service service = message.getExchange().get(Service.class);
        List<ClassResourceInfo> resources = ((JAXRSServiceImpl)service).getClassResourceInfos();

        String acceptTypes = (String)message.get(Message.ACCEPT_CONTENT_TYPE);
        if (acceptTypes == null) {
            acceptTypes = "*/*";
            message.put(Message.ACCEPT_CONTENT_TYPE, acceptTypes);
        }
        List<MediaType> acceptContentTypes = JAXRSUtils.sortMediaTypes(acceptTypes, "q");
        message.getExchange().put(Message.ACCEPT_CONTENT_TYPE, acceptContentTypes);

        MultivaluedMap<String, String> values = new MetadataMap<String, String>();
        
        ClassResourceInfo resource = getClassResource(message, rawPath, resources, values);
       
        if (resource == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        message.getExchange().put(ROOT_RESOURCE_CLASS, resource);

        String httpMethod = (String)message.get(Message.HTTP_REQUEST_METHOD);
        OperationResourceInfo ori = null;     
        
        List<ProviderInfo<RequestHandler>> shs = ProviderFactory.getInstance(message).getRequestHandlers();
        for (ProviderInfo<RequestHandler> sh : shs) {
            String newAcceptTypes = (String)message.get(Message.ACCEPT_CONTENT_TYPE);
            if (!acceptTypes.equals(newAcceptTypes) || ori == null) {
                acceptTypes = newAcceptTypes;
                acceptContentTypes = JAXRSUtils.sortMediaTypes(newAcceptTypes, "q");
                message.getExchange().put(Message.ACCEPT_CONTENT_TYPE, acceptContentTypes);
                
                if (ori != null) {
                    values = new MetadataMap<String, String>();
                    resource = JAXRSUtils.selectResourceClass(resources, 
                                                              rawPath, 
                                                              values,
                                                              message);
                }
                
                ori = JAXRSUtils.findTargetMethod(resource, message,// values.getFirst(URITemplate.FINAL_MATCH_GROUP), 
                                                  httpMethod, values, requestContentType, acceptContentTypes, true);
                message.getExchange().put(OperationResourceInfo.class, ori);
            }
            Response response = sh.getProvider().handleRequest(message, resource);
            if (response != null) {
                message.getExchange().put(Response.class, response);
                return;
            }
        }
        
        String newAcceptTypes = (String)message.get(Message.ACCEPT_CONTENT_TYPE);
        if (!acceptTypes.equals(newAcceptTypes) || ori == null) {
            acceptTypes = newAcceptTypes;
            acceptContentTypes = JAXRSUtils.sortMediaTypes(acceptTypes, "q");
            message.getExchange().put(Message.ACCEPT_CONTENT_TYPE, acceptContentTypes);
            if (ori != null) {
                values = new MetadataMap<String, String>();
                resource = JAXRSUtils.selectResourceClass(resources, 
                                                          rawPath, 
                                                          values,
                                                          message);
            }
            ori = JAXRSUtils.findTargetMethod(resource, message,//values.getFirst(URITemplate.FINAL_MATCH_GROUP), 
                                              httpMethod, values, requestContentType, acceptContentTypes, true);
            message.getExchange().put(OperationResourceInfo.class, ori);
        }

        
//        logger.info("Request path is: " + rawPath);
//        logger.info("Request HTTP method is: " + httpMethod);
//        logger.info("Request contentType is: " + requestContentType);
//        logger.info("Accept contentType is: " + acceptTypes);
//        
//        logger.info("Found operation: " + ori.getMethodToInvoke().getName());
        
        message.getExchange().put(OperationResourceInfo.class, ori);
        message.put(RELATIVE_PATH, values.getFirst(URITemplate.FINAL_MATCH_GROUP));
        message.put(URITemplate.TEMPLATE_PARAMETERS, values);
        
      
        if(httpMethod.equals(PUT) || httpMethod.equals(POST))
        {
        	boolean bodyRequired = isBodyRequired(ori);
        	if(bodyRequired)
        	{
        		Object body = getBodyFromMessage(message, ori, values);
	        	if(body == null)
	        	{
	        		throw new RuntimeException(new DataValidateException("Request body is required."));
	        	}
	        	else 
	        	{
	        		// check the class name of request body wither matched with the parameter in method.
	        		if(body != null)
	            	{
	        			validateRequestType(ori, body);
	            	}
	        		if(httpMethod.equals(PUT))
	        		{
	        			// Set original data to thread local.
	        			setOriginalDataToContext(message, values, httpMethod, ori, rawPath,body);
	        		}
	        	}
        	}
        }
        //2. Process parameters
        List<Object> params = null;
		try
		{
			params = JAXRSUtils.processParameters(ori, values, message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
        message.setContent(List.class, params);
    }
    
    private boolean ignore(String rawPath,OperationResourceInfo ori)
    {
    	boolean result = false;
    	if( rawPath.indexOf("/documents") > 0 || rawPath.indexOf("/finalize") > 0) 
    		result = true;
    	return result;
    }
    
    private void validateRequestType(OperationResourceInfo ori,Object body)
    {
    	Method method = ori.getMethodToInvoke();
		
		Class<?>[] classes = method.getParameterTypes();
    	
    	int count = classes.length;
    	for (int i = 0; i < count; i++)
		{

    		if(List.class.isAssignableFrom(classes[i]) && (!List.class.isAssignableFrom(body.getClass())))
    		{
      			throw new RuntimeException(new DataValidateException("Request type not match. Accept object type:" + List.class.getName() +"."));
    		}
    		else if(Map.class.isAssignableFrom(classes[i]) && (!Map.class.isAssignableFrom(body.getClass())))
    		{
    			throw new RuntimeException(new DataValidateException("Request type not match. Accept object type:" + Map.class.getName() +"."));
    		}	
      		else if(classes[i].getName().startsWith(PACKAGEPATH) && (!classes[i].getName().equals(body.getClass().getName())) )
    		{
    			throw new RuntimeException(new DataValidateException("Request type not match. Accept object type:" + classes[i].getName() +"."));

    		}
    		
		}
    }
    private boolean isBodyRequired(OperationResourceInfo ori)
    {
    	Method method = ori.getMethodToInvoke();
    	
    	Set<Annotation> annotations = new HashSet<Annotation>();
    	annotations.addAll(Arrays.asList(method.getAnnotations()));

    	for (Annotation annotation : annotations)
		{
    		if(annotation instanceof IgnoreBody)
    		{
    			return false;
    		}
		}
    	return true;
    }
    private Object getBodyFromMessage(Message message, OperationResourceInfo ori, MultivaluedMap<String, String> values)
    {
    	 String sBody = null;
         Object oBody = null;
         if(message.get("Content-Type") != null && message.get("Content-Type").toString().indexOf("multipart/form-data") >=0 )
         {
        	 return null;
         }
         try
 		{
        	 
 			InputStream is = message.getContent(InputStream.class);
 			sBody = IOUtils.toString(is);
 		}
 		catch (IOException e)
 		{
 			e.printStackTrace();
 		}
 		if (null != sBody && !ValidationUtil.isEmpty(sBody)) { 
 			message.setContent(InputStream.class, new ByteArrayInputStream(sBody.getBytes(Charset.forName("UTF-8"))));
 			List<Object> params = null;
			try
			{
				params = JAXRSUtils.processParameters(ori, values, message);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
 			oBody = getBodyFromParam(params);
 			message.setContent(InputStream.class, new ByteArrayInputStream(sBody.getBytes(Charset.forName("UTF-8"))));
 		}
 		return oBody;
    }
    
    private Object getBodyFromParam(List<Object> params)
    {
    	//find body
    	for (Object param : params)
    	{
    		if (null != param && (param.getClass().getName().startsWith(PACKAGEPATH) || param instanceof List<?>) || param instanceof Map<?,?>)
    		{
    			return param;
    			
    		}
    	}
    	return null;
    }

	private void setOriginalDataToContext(Message message, MultivaluedMap<String, String> values, String httpMethod,
			OperationResourceInfo ori, String rawPath,Object bodyObject) 
	{
		// Set original model to thread local.
		if (httpMethod.equals(PUT))
		{
			Map<String, Object> parameter = new HashMap<String, Object>();
			Iterator<Entry<String, List<String>>> mapIterator = values.entrySet().iterator();
			while (mapIterator.hasNext())
			{
				Entry<String, List<String>> map = mapIterator.next();
				if(!ValidationUtil.isEmpty(map.getValue()))
				{
					String parameterValue = map.getValue().get(0);
					parameter.put(map.getKey(), parameterValue);
				}
				
			}
			
			//add body object to parameter
		
			parameter.put(BODY,bodyObject);

			Class<?>[] parameTypes = ori.getAnnotatedMethod().getParameterTypes();
			int len = parameTypes.length;
			String className = null;
			for (int i = 0; i < len; i++)
			{
				String name = parameTypes[i].getName();
				if (name.startsWith(PACKAGEPATH))
				{
					className = name;
					break;
				}
			}
			if (null == className)
			{
				className = ori.getAnnotatedMethod().getDeclaringClass().getName() + "." + ori.getAnnotatedMethod().getName();
			}
			 
			ApiDataSourceProvider dataSource = UpdateFactory.createInstance(className);
			if (dataSource != null)
			{
				Object object = dataSource.query(parameter);
				if(ValidationUtil.isEmpty(object))
				{
					
					int index = rawPath.lastIndexOf("/");
					String lastItem = rawPath.substring(index+1);
					if(!parameter.containsValue(lastItem))
					{
						rawPath = rawPath.substring(0,index);
					}
					rawPath = rawPath.substring(4);
					rawPath = rawPath.replace("/", " ");
					rawPath = rawPath.substring(0,1).toUpperCase() + rawPath.substring(1);
					String errorMessage = rawPath + " " + MessageConstants.MESSAGE_CANNOT_BEEN_FOUND;
					
					throw new WebApplicationException(new ObjectNotFoundException(errorMessage));
				}
				CustomJSONProvider.setOriginalModel(object);
			}
		}
	}

	
	private ClassResourceInfo getClassResource(Message message, String rawPath, List<ClassResourceInfo> resources,
			MultivaluedMap<String, String> values)
	{
		//1.0 Get version information from the url.
		String[] pathResources = rawPath.split("/");
        int version = 0;
        
        //2.0 Check version name for the APIs which don't have versions
		if(pathResources[1].startsWith("v") || pathResources[1].startsWith("V"))
        {
			version = Integer.valueOf(pathResources[1].substring(1,pathResources[1].length()));
        }
		
		//set version to thread.
		RestThreadLocal.setVersion(version);
		
		//3.0 if there is not current version, then get the lower version.
		ClassResourceInfo resource = null;
		if(version==0)
		{
			resource = JAXRSUtils.selectResourceClass(resources, rawPath, values, message);
		}
		else
		{
			String acceptTypes = (String)message.get(Message.ACCEPT_CONTENT_TYPE);
	        if (acceptTypes == null) {
	            acceptTypes = "*/*";
	            message.put(Message.ACCEPT_CONTENT_TYPE, acceptTypes);
	        }
			List<MediaType> acceptContentTypes = JAXRSUtils.sortMediaTypes(acceptTypes, "q");
			String httpMethod = (String)message.get(Message.HTTP_REQUEST_METHOD);
			 String requestContentType = (String)message.get(Message.CONTENT_TYPE);
		        if (requestContentType == null) {
		            requestContentType = "*/*";
		        }
			for (int i =version; i>=0; i--)
			{
				String rawVersion ="";
				if(i>0)
				{
					rawVersion ="/v"+i;
				}
				String path = rawVersion+rawPath.substring(rawPath.indexOf("/",2), rawPath.length());
				
				resource = JAXRSUtils.selectResourceClass(resources, path, values, message);
				MultivaluedMap<String, String> tempValues = cloneMap(values);
				if(resource!=null)
				{
					try
					{
						///Check if the method exist in high version.
						JAXRSUtils.findTargetMethod(resource, message,//values.getFirst(URITemplate.FINAL_MATCH_GROUP), 
	                        httpMethod, tempValues, requestContentType, acceptContentTypes, true);
					}
					catch(Exception e)
					{
						continue;
					}
					//update the request path.
					HttpUtils.updatePath(message, path);
					break;
				}
			}
		}
		
		return resource;
	}
	
	  private static <K, V> MultivaluedMap<K, V> cloneMap(MultivaluedMap<K, V> map1) {
	        
	        MultivaluedMap<K, V> map2 = new MetadataMap<K, V>();
	        for (Map.Entry<K, List<V>> entry : map1.entrySet()) {
	            map2.put(entry.getKey(), new ArrayList<V>(entry.getValue()));
	        }
	        return map2;
	        
	    }
}
/*
*$Log: av-env.bat,v $
*/
