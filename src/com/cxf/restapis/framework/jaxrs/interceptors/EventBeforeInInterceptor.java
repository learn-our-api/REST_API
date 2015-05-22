package com.cxf.restapis.framework.jaxrs.interceptors;

import java.lang.reflect.Method;

import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.cxf.restapis.framework.event.annotations.EventBefore;
import com.cxf.restapis.framework.util.ResponseHelper;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  bryant Automation
 *  File: EventBeforeInInterceptor.java
 * 
 *  bryant, Inc.
 *  Copyright (C): 2012-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: EventBeforeInInterceptor.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-5-28		dylan.liang		Initial.
 * 
 * </pre>
 */
public class EventBeforeInInterceptor extends AbstractPhaseInterceptor<Message>
{

	/** LOGGER instance. */
	// private static final AVLogger logger = AVLogger.getLogger(EventBeforeInInterceptor.class);

	/**
	 * Instantiates a new event before in interceptor.
	 */
	public EventBeforeInInterceptor()
	{
		super(Phase.PRE_INVOKE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override
	public void handleMessage(Message message) throws Fault
	{
		OperationResourceInfo ori = message.getExchange().get(OperationResourceInfo.class);
		Method method = ori.getMethodToInvoke();
		EventBefore eventBeforeAnnotation = method.getAnnotation(EventBefore.class);
		Response response = null;
		String msg = null;

		if (eventBeforeAnnotation != null)
		{
			String eventName = eventBeforeAnnotation.eventName();
			Class<?> pretreatClass = eventBeforeAnnotation.pretreatClass();
			boolean isStopByError = eventBeforeAnnotation.stopByError();

			// logger.debug("pretreat the event parameter in class: " + pretreatClass.getName());
			// logger.debug("trigger event: " + eventName);

			try
			{
				// String servProvCode = WebThreadLocal.getServiceProviderCode();
				// String callerID = WebThreadLocal.getCurrentUser();
				// bryantEventUtil.triggerEvent(servProvCode, message, eventName, pretreatClass, callerID);
			}
			catch (Exception e)
			{
				/**
				 * According to default logic system need to abort the invoking, if system is encountering EMSE
				 * exception in before event.
				 */
				if (isStopByError)
				{
					msg = "You are meeting EMSE exception on " + eventName;
					response = ResponseHelper.emseError(msg, e);
				}

				// logger.error(e.getMessage(), e);
			}
		}

		if (response != null)
		{
			message.getExchange().put(Response.class, response);
			throw new Fault(new Exception(msg));
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */
