package com.cxf.restapis.framework.jaxrs.interceptors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.cxf.restapis.framework.constants.BasicActionType;
import com.cxf.restapis.framework.constants.MessageConstants;
import com.cxf.restapis.framework.model.RestThreadLocal;
import com.cxf.restapis.framework.security.annotations.ApiSecurity;
import com.cxf.restapis.framework.security.annotations.FID;
import com.cxf.restapis.framework.security.annotations.IgnoreAuthentication;
import com.cxf.restapis.framework.security.exceptions.APISecurityException;
import com.cxf.restapis.framework.security.handlers.FIDSecurityHandler;
import com.cxf.restapis.framework.security.handlers.RESTRequestHandler;
import com.cxf.restapis.framework.security.handlers.ResourceBasic;
import com.cxf.restapis.framework.security.handlers.SecurityAuthorizer;
import com.cxf.restapis.framework.util.ParameterHelper;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.cxf.restapis.framework.util.WebThreadLocal;

/**
 * <pre>
 * 
 * bryant Automation
 * File: AuthorizationInterceptor.java
 * 
 * bryant, Inc.
 * Copyright (C): 2012-2015
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: AuthorizationInterceptor.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * 2012-5-28		dylan.liang		Initial.
 * 
 * </pre>
 */
public class AuthorizationInterceptor extends AbstractPhaseInterceptor<Message>
{

	/** The logger for this class. */
//	private static final AVLogger logger = AVLogger.getLogger(AuthorizationInterceptor.class);

	/** The security controller map. */
	private static Map<Class<?>, RESTRequestHandler> securityHandlerMap = new HashMap<Class<?>, RESTRequestHandler>();

	// map from resource name to resource security handler
	private static Map<String, SecurityAuthorizer> standardSecurityHandlerMap = new HashMap<String, SecurityAuthorizer>();

	static
	{
		/** Initialize different security handler instance into map */
		securityHandlerMap.put(FID.class, new FIDSecurityHandler());

		// TODO: use configuration instead of adding dependency here
//		standardSecurityHandlerMap.put("", new RecordSecurityAuthorizer());
	}
	
	

	/**
	 * Instantiates a new authorization interceptor.
	 */
	public AuthorizationInterceptor()
	{
		super(Phase.READ);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override
	public void handleMessage(Message message) throws Fault
	{
		if (message == null)
		{
			return;
		}

		OperationResourceInfo ori = message.getExchange().get(OperationResourceInfo.class);

		if (ori == null)
		{
			return;
		}

		ClassResourceInfo resourceClass = ori.getClassResourceInfo();
		Method method = ori.getMethodToInvoke();

		if (method.getAnnotation(IgnoreAuthentication.class) != null)
		{
			/** Update WebThreadLocal for some services which ignored authentication */
			updateWebThreadLocalForIgnoreAuth(message);
		}

		String servProvCode = WebThreadLocal.getServiceProviderCode();
		String currentUser = WebThreadLocal.getCurrentUser();

		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		String requestURI = request.getRequestURI().toLowerCase();
		if (!(requestURI.startsWith(String.format("/apis/v%1$s/citizens/validate", RestThreadLocal.getVersion())))
				&& !requestURI.equalsIgnoreCase("/apis/v4/serverProperties"))
		{
			/** Validate agency code */
//			ServiceProviderModel servProvModel = null;
//			try
//			{
//				servProvModel = EJBProxy.getServiceProviderService().getServiceProviderByPK(servProvCode, currentUser);
//			}
//			catch (Exception e)
//			{
//				logger.error(e.getMessage(), e);
//				servProvModel = null;
//			}

//			if (servProvModel == null)
//			{
//				String msg = "System cannot support passed agency code " + servProvCode;
//				Response response = ResponseHelper.build(ResponseHelper.validationFailure(msg));
//				message.getExchange().put(Response.class, response);
//				throw new Fault(new Exception(msg));
//			}
		}

		Class<?> clazz = resourceClass.getServiceClass();
		Class<?> superClass = resourceClass.getServiceClass().getSuperclass();

		Set<Annotation> annotations = new HashSet<Annotation>();
		/** 1. Handle method level security annotations */
		annotations.addAll(Arrays.asList(method.getAnnotations()));
		/** 2. Handle class level security annotations */
		annotations.addAll(Arrays.asList(clazz.getAnnotations()));
		/** 3. Handle super class level security annotations */
		annotations.addAll(Arrays.asList(superClass.getAnnotations()));

		for (Annotation annotation : annotations)
		{
			if (!securityHandlerMap.containsKey(annotation.annotationType()) && !(annotation instanceof ApiSecurity))
			{
				continue;
			}

			Response response;
			try
			{
				response = handleSecurityAnnotation(annotation, message, resourceClass);
			}
			catch (Exception e)
			{
				if(e instanceof APISecurityException)
				{
					throw (APISecurityException)e;
				}
				response = ResponseHelper.internalServerError(null, e);
//				logger.error(e.getMessage(), e);
			}

			if (response != null)
			{
				message.getExchange().put(Response.class, response);
				throw new Fault(new Exception("request not authorized"));
			}
		}

	}

	/**
	 * Handle security annotation.
	 * 
	 * @param annotation the annotation
	 * @param message the message
	 * @param resourceClass the resource class
	 * 
	 * @return the response
	 * 
	 * @throws Exception the exception
	 */
	private Response handleSecurityAnnotation(Annotation annotation, Message message, ClassResourceInfo resourceClass)
			throws Exception
	{
		Response response = null;

//		logger.info("Security: " + annotation);

		if (annotation instanceof ApiSecurity)
		{
			ApiSecurity security = (ApiSecurity) annotation;
//			logger.info("ApiSecurity: " + security.resource());
			SecurityAuthorizer securityHandler = standardSecurityHandlerMap.get(security.resource());
			if (securityHandler != null)
			{
//				logger.info("ApiSecurity handle: " + security.operation());
				response = validateRequestSecurity(securityHandler, security, message, resourceClass);
			}
		}

		if (response == null)
		{
			/** Find Security Handler Instance by annotation type */
			RESTRequestHandler handler = securityHandlerMap.get(annotation.annotationType());

			if (handler != null)
			{
				/** Process Specific Security */
				response = handler.handleRequest(message, resourceClass);
			}
		}


		return response;
	}

	/**
	 * Because Ignore authentication checking for some services below 1. Retrieve access token operation 2. Create
	 * citizen user account operation So update web thread local with info within message content.
	 * 
	 * @param message the message
	 */
	private void updateWebThreadLocalForIgnoreAuth(Message message)
	{
		HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		String requestURI = request.getRequestURI().toLowerCase();
		String servProvCode = null;
		String currentUser = null;

		if (requestURI.startsWith("/apis/agency/auth") || requestURI.startsWith("/apis/citizen/auth")
				|| requestURI.startsWith(String.format("/apis/v%1$s/agency/auth", RestThreadLocal.getVersion()))
				|| requestURI.startsWith(String.format("/apis/v%1$s/citizen/auth", RestThreadLocal.getVersion()))
				|| requestURI.startsWith(String.format("/apis/v%1$s/auth/agency", RestThreadLocal.getVersion()))
				|| requestURI.startsWith(String.format("/apis/v%1$s/auth/citizen", RestThreadLocal.getVersion())))
		{
			if (!ValidationUtil.isEmpty(message.getContent(List.class)))
			{
//				AuthRequestModel authRequest = (AuthRequestModel) message.getContent(List.class).get(0);
//
//				if (authRequest != null)
//				{
//					servProvCode = authRequest.getAgency();
//					currentUser = authRequest.getUserId();
//
//					if (servProvCode != null)
//					{
//						servProvCode = servProvCode.toUpperCase();
//						authRequest.setAgency(servProvCode);
//					}
//				}
			}
		}
		else if (requestURI.startsWith("/securedapis/auth") || requestURI.startsWith("/apis/auth"))
		{
			Map<String,String> authRequest = (Map<String,String>) message.getContent(List.class).get(0);
			if (authRequest != null)
			{
				servProvCode = authRequest.get("serviceProviderCode");
				currentUser = authRequest.get("userId");

				if (servProvCode != null)
				{
					servProvCode = servProvCode.toUpperCase();
					authRequest.put("serviceProviderCode", servProvCode);
					RestThreadLocal.setVersion(4);
				}
			}
		}
		else if (requestURI.startsWith("/apis/v1/citizens/register"))
		{
			if (!ValidationUtil.isEmpty(message.getContent(List.class)))
			{
//				CreateCitizenRequestModel createCitizenRequest = (CreateCitizenRequestModel) message.getContent(
//					List.class).get(0);
//
//				if (createCitizenRequest != null && createCitizenRequest.getPublicUser() != null)
//				{
//					servProvCode = createCitizenRequest.getPublicUser().getServProvCode();
//					if (servProvCode != null)
//					{
//						servProvCode = servProvCode.toUpperCase();
//						createCitizenRequest.getPublicUser().setServProvCode(servProvCode);
//					}
//
//					if (!ValidationUtil.isEmpty(createCitizenRequest.getPublicUser().getAuditID()))
//					{
//						currentUser = createCitizenRequest.getPublicUser().getAuditID();
//					}
//					else
//					{
//						currentUser = V360Constant.PUBLICUSER;
//					}
//				}
			}
		}  
		else if (requestURI.startsWith("/apis/v4/citizens/register"))
		{
//			if (!ValidationUtil.isEmpty(message.getContent(List.class)))
//			{
//				PublicUserRegisterModel createCitizenRequest = (PublicUserRegisterModel) message.getContent(
//					List.class).get(0);
//
//				if (createCitizenRequest != null)
//				{
//					servProvCode = createCitizenRequest.getServProvCode();
//					if (servProvCode != null)
//					{
//						servProvCode = servProvCode.toUpperCase();
//						createCitizenRequest.setServProvCode(servProvCode);
//					}
//
//					if (!ValidationUtil.isEmpty(createCitizenRequest.getAuditID()))
//					{
//						currentUser = createCitizenRequest.getAuditID();
//					}
//					else
//					{
//						currentUser = V360Constant.PUBLICUSER;
//					}
//				}
//			}
		}
				
		WebThreadLocal.setServiceProviderCode(servProvCode);
		WebThreadLocal.setCurrentUser(currentUser);
	}

	private Response validateRequestSecurity(SecurityAuthorizer securityHandler, ApiSecurity security,
			Message paramMessage, ClassResourceInfo resourceClass)
	{
		Response response = null;
		boolean authorized = true;
		Exception error = null;

		String servProvCode = WebThreadLocal.getServiceProviderCode();
		String callerID = WebThreadLocal.getCurrentUser();
		String module = WebThreadLocal.getModule();
		String operation = security.operation();

		try
		{
			String httpMethod = (String) paramMessage.get(Message.HTTP_REQUEST_METHOD);
			String pathInfo = (String) paramMessage.get(Message.PATH_INFO);
			String basePath = (String) paramMessage.get(Message.BASE_PATH);
			String resourceItemId = ParameterHelper.getPathParameter(paramMessage, security.id());
			String resourceItemType = ParameterHelper.getPathParameter(paramMessage, security.type());
			String parentResourceId = ParameterHelper.getPathParameter(paramMessage, security.parentId());
			Map<String, String> parameters = ParameterHelper.getQueryParametersFromRequest(paramMessage);

			ResourceBasic resource = new ResourceBasic();
			resource.setContent(paramMessage.getContent(List.class));
			resource.setIds(resourceItemId);
			resource.setResourceType(security.resource());
			resource.setParentId(parentResourceId);
			resource.setParentResourceType(security.parentResource());
			resource.setServProvCode(servProvCode);
			resource.setModule(module);
			resource.setType(resourceItemType);
			resource.setPathInfo(pathInfo);
			resource.setOperation(operation);
			resource.setExtraParameters(parameters);

//			logger.info("ApiSecurity: " + pathInfo + ", " + basePath + "\n" + paramMessage.get(Message.INBOUND_MESSAGE)
//					+ "\n" + paramMessage.get(Message.INVOCATION_CONTEXT) + "\n"
//					+ paramMessage.get(Message.REQUEST_URI) + "\n" + paramMessage.get(Message.QUERY_STRING));

			if (BasicActionType.L.toString().equals(operation))
			{
				// if LIST, let business layer to handle security; if nothing accessible, just return empty list
				authorized = securityHandler.isListAllowed(callerID, resource);
			}
			else if (BasicActionType.R.toString().equals(operation) || GET.class.getSimpleName().equals(httpMethod))
			{
				authorized = securityHandler.isGetAllowed(callerID, resource);
			}
			else if (BasicActionType.C.toString().equals(operation) || POST.class.getSimpleName().equals(httpMethod))
			{
				authorized = securityHandler.isCreateAllowed(callerID, resource);
			}
			else if (BasicActionType.U.toString().equals(operation) || PUT.class.getSimpleName().equals(httpMethod))
			{
				authorized = securityHandler.isUpdateAllowed(callerID, resource);
			}
			else if (BasicActionType.D.toString().equals(operation) || DELETE.class.getSimpleName().equals(httpMethod))
			{
				authorized = securityHandler.isDeleteAllowed(callerID, resource);
			}
			else
			{
				authorized = securityHandler.isOperationAllowed(operation, callerID, resource);
			}

//			logger.info("authorized: " + authorized);
		}
		catch (Exception e)
		{
			if(e instanceof APISecurityException)
			{
				throw (APISecurityException)e;
			}
			authorized = false;
			error = e;
//			logger.error("Not authorized", e);
		}

		if (!authorized)
		{
			response = ResponseHelper.forbidden(MessageConstants.AUTH_FAILED, error);
		}

		return response;
	}
}

/*
 * $Log: av-env.bat,v $
 */
