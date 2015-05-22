package com.cxf.restapis.framework.jaxrs.interceptors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.WebThreadLocal;
import com.cxf.restapis.framework.validation.CommonValidator;
import com.cxf.restapis.framework.validation.Validations;
import com.cxf.restapis.framework.validation.Validator;

/**
 * <pre>
 * 
 *  bryant Automation
 *  File: DataValidationInterceptor.java
 * 
 *  bryant, Inc.
 *  Copyright (C): 2012-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DataValidationInterceptor.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-7-7		dylan.liang		Initial.
 * 
 * </pre>
 */
public class DataValidationInterceptor extends AbstractPhaseInterceptor<Message>
{

	/** The logger for this class. */
//	private static final AVLogger logger = AVLogger.getLogger(DataValidationInterceptor.class);

	/** The validator controller map. */
	private static Map<Class<?>, Validator> validatorMap = new HashMap<Class<?>, Validator>();

	static
	{
		/** General data validation logic such as data format,data type. */
		validatorMap.put(Validations.class, new CommonValidator());
	}

	/**
	 * Instantiates a new data validation interceptor.
	 */
	public DataValidationInterceptor()
	{
		super(Phase.READ);
	}

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
		
		String servProvCode = WebThreadLocal.getServiceProviderCode();

		ClassResourceInfo resourceClass = ori.getClassResourceInfo();
		Method method = ori.getMethodToInvoke();
		Response response = null;

		/** Handle method level business logic validator annotations */
		Annotation[] annotations = method.getAnnotations();

		for (Annotation annotation : annotations)
		{
			if (!validatorMap.containsKey(annotation.annotationType()))
			{
				continue;
			}

			try
			{
				response = handleValidatorAnnotation(annotation, message, resourceClass);
			}
			catch (Exception e)
			{
				response = ResponseHelper.build(ResponseHelper.applicationError(e.getMessage()));
//				logger.error(e.getMessage(), e);
			}

			if (response != null)
			{
				break;
			}
		}

		if (response != null)
		{
			message.getExchange().put(Response.class, response);
			throw new Fault(new Exception("You are experiencing data validation exception"));
		}
	}

	/**
	 * Handle validation annotation.
	 * 
	 * @param annotation the annotation
	 * @param message the message
	 * @param resourceClass the resource class
	 * 
	 * @return the response
	 * 
	 * @throws Exception the exception
	 */
	private Response handleValidatorAnnotation(Annotation annotation, Message message, ClassResourceInfo resourceClass)
			throws Exception
	{
		Response response = null;

		/** Find Validator Instance by annotation type */
		Validator validator = validatorMap.get(annotation.annotationType());

		if (validator != null)
		{
			/** Process validation logic */
			response = validator.validate(message, resourceClass);
		}

		return response;
	}
}

/*
 * $Log: av-env.bat,v $
 */
