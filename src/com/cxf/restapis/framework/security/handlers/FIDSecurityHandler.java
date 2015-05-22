package com.cxf.restapis.framework.security.handlers;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;

import com.cxf.restapis.framework.security.annotations.FID;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.cxf.restapis.framework.util.WebThreadLocal;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: FIDSecurityHandler.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  The handler to authorize access privilege based on FID Setting of AA Classic.
 * 
 *  Notes:
 * 	$Id: FIDSecurityHandler.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-5-11		dylan.liang		Initial.
 * 
 * </pre>
 */
public class FIDSecurityHandler implements RESTRequestHandler
{
	/**
	 * The logger for this class.
	 */
//	private static final AVLogger logger = AVLogger.getLogger(FIDSecurityHandler.class);

	@Override
	public Response handleRequest(Message paramMessage, ClassResourceInfo paramClassResourceInfo)
	{
		/** variables within WebThrealLocal should be on top of each method */
		String servProvCode = WebThreadLocal.getServiceProviderCode();
		String user = WebThreadLocal.getCurrentUser();
		String module = WebThreadLocal.getModule();

		/** Don't check FID permission for citizen user currently */
//		if (PublicUserHelper.isPublicUser(user))
//		{
//			return null;
//		}

		// Get the FID value.
		OperationResourceInfo ori = paramMessage.getExchange().get(OperationResourceInfo.class);
		Method method = ori.getMethodToInvoke();
		FID fidAnnotation = method.getAnnotation(FID.class);
		String fid = (fidAnnotation == null) ? null : fidAnnotation.value();

		if (fid == null)
		{
			Class<?> cls = paramClassResourceInfo.getServiceClass();
			fidAnnotation = cls.getAnnotation(FID.class);
			fid = (fidAnnotation == null) ? null : fidAnnotation.value();
		}

		if (fid != null)
		{
			Exception error = null;
			boolean authorized = false;
			try
			{

				if (!authorized && paramMessage.getExchange().get(Response.class) == null)
				{
					Response response = ResponseHelper.forbidden("FID_FAILED", error);
					paramMessage.getExchange().put(Response.class, response);
				}
			}
			catch (Exception e)
			{
				authorized = false;
				error = e;
//				logger.error(FID_FAILED, e);

				if (error instanceof Fault)
				{
					throw (Fault) error;
				}
				else
				{
					throw new Fault(new Exception("FID_FAILED", error));
				}
			}
		}
		
		return null;
	}
}

/*
 * $Log: av-env.bat,v $
 */
