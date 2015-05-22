package com.cxf.restapis.framework.event;

import java.util.Hashtable;
import java.util.Map;

import org.apache.cxf.message.Message;

import com.cxf.restapis.framework.model.MultipleObjectResultModel;
import com.cxf.restapis.framework.util.ClassUtil;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.cxf.restapis.framework.util.WebThreadLocal;


// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: AccelaEventUtil.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2014
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: AccelaEventUtil.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * 2012-6-4		dylan.liang		Initial.
 * 
 * </pre>
 */
public class AccelaEventUtil
{

	/** LOGGER instance. */
//	private static final AVLogger logger = AVLogger.getLogger(AccelaEventUtil.class);

	public static boolean getEMSEResult(MultipleObjectResultModel objectResult, Object classObject,
			String event, Class<?> pretreatClass, Object id)
	{
		String servProvCode = WebThreadLocal.getServiceProviderCode();
		String callerID = WebThreadLocal.getCurrentUser();
		return getEMSEResult(servProvCode, callerID, objectResult, classObject, event, pretreatClass, id);
	}
	
	public static boolean getEMSEResult(String servProvCode, String callerID, MultipleObjectResultModel objectResult,
			Object classObject, String event, Class<?> pretreatClass, Object id)
	{
		Map<String, String> responseMap = new Hashtable<String, String>();
		boolean emseResult = triggerEvent(servProvCode, classObject, event, pretreatClass, callerID, responseMap);
//		String code = responseMap.get(EMSEClient.SCRIPT_RESULT_CODE);
//		String message  = responseMap.get(EMSEClient.SCRIPT_RETURN_MESSAGE);
//		if(!ValidationUtil.isEmpty(responseMap))
//		{
//			if(emseResult || event.endsWith("After"))
//			{
//				MultipleObjectResultModel.buildSuccessResultModel(objectResult, id, code, message);
//			}
//			else
//			{
//				MultipleObjectResultModel.buildFailedResultModel(objectResult, id, code, message);
//			}
//		}
		return emseResult;
	}
	
	
	/**
	 * Trigger event.
	 *
	 * @param servProvCode the serv prov code
	 * @param message the message
	 * @param eventName the event name
	 * @param pretreatClass the pretreat class
	 * @param callerID the caller id
	 * @return the Hashtable<?, ?>
	 * 
	 * @throws Exception the exception
	 */
	public static boolean triggerEvent(String servProvCode, Object classObject, String eventName,
			Class<?> pretreatClass, String callerID, Map<String, String> responseMap)
	{
		Hashtable<?, ?> params = generateParameters(classObject, pretreatClass);
		
		if ( params == null ) {
			return true;	// Jing: TEMP solution to unblock API usage because of EMSE parameter not handled in API
			// TODO: Jing: If API trigger events, it should enable the following error message and false result
			// If API trigger events, it should implement parameters or EMSE will fail with
			// java.lang.NullPointerException at com.accela.aa.emse.emse.EMSEBusiness.handleEventBiz(EMSEBusiness.java:1081)
			/*
			logger.error("EMSE error on " + eventName + ": invalid parameter");
			responseMap.put(EMSEClient.SCRIPT_RETURN_MESSAGE, "Invalid EMSE parameters");
			responseMap.put(EMSEClient.SCRIPT_RESULT_CODE, ErrorCodeConstants.EMSE_500);
			return false;
			*/
		}return false;
		
//		I18NContext.setI18NModel(BusinessHelper.createI18NModel());
//		
//		boolean result = false;
//		EMSEClient emseClient = EMSEClient.getEMSEClient();
//		try
//		{
//			Hashtable rs = emseClient.handleEvent(eventName, servProvCode, params, callerID);	
//			String code = (String)rs.get(EMSEClient.SCRIPT_RESULT_CODE);
//			if(code!= null && !code.equals("0"))
//			{
//				responseMap.put(EMSEClient.SCRIPT_RESULT_CODE, ErrorCodeConstants.EMSE_500);
//			}	
//			String message = (String) rs.get(EMSEClient.SCRIPT_RETURN_MESSAGE);
//			if(message != null)
//			{
//				responseMap.put(EMSEClient.SCRIPT_RETURN_MESSAGE,  message);
//			}
//			result = true;
//		}
//		catch (EMSEException e)
//		{
//			logger.info("EMSE exception on " + eventName, e.getCause());
//			responseMap.put(EMSEClient.SCRIPT_RETURN_MESSAGE, BizKeyUtil.getText(e.getMessage()));
//			responseMap.put(EMSEClient.SCRIPT_RESULT_CODE, ErrorCodeConstants.EMSE_500);
//			result = false;
//		}
//		finally
//		{
//			// Clear Thread local.
//			I18NContext.setI18NModel(null);
//		}

//		return result;
	}

	/**
	 * Generate parameters.
	 *
	 * @param message the message
	 * @param pretreatClass the pretreating class
	 * @return the Hashtable<?, ?>
	 * 
	 * @throws Exception the exception
	 */
	private static Hashtable<?, ?> generateParameters(Object classObject, Class<?> pretreatClass)
	{
		Hashtable<?, ?> parameters = null;
		Object pretreatclass = ClassUtil.newInstance(pretreatClass);

		if (pretreatclass instanceof EMSEParameterHandler)
		{
			try
			{
				parameters = ((EMSEParameterHandler) pretreatclass).pretreat(classObject);
			}
			catch (Exception e)
			{
//				logger.info("You are meeting EMSE script parameter exception. class: "+pretreatClass, e.getCause());
			}
		}
		else
		{
//			logger.error("Cannot find related EMSEParameter pretreating class: " + pretreatClass);
		}

		return parameters;
	}
	
	/**
	 * Trigger event.
	 *
	 * @param servProvCode the serv prov code
	 * @param message the message
	 * @param eventName the event name
	 * @param pretreatClass the pretreat class
	 * @param callerID the caller id
	 * @return the Hashtable<?, ?>
	 * 
	 * @throws Exception the exception
	 */
	public static Hashtable<?, ?> triggerEvent(String servProvCode, Message message, String eventName,
			Class<?> pretreatClass, String callerID) throws Exception
	{
		if (ValidationUtil.isEmpty(servProvCode) || message == null || ValidationUtil.isEmpty(eventName)
				|| ValidationUtil.isEmpty(pretreatClass))
		{
			return null;
		}
		Hashtable<?, ?> result = new Hashtable();
//		I18NContext.setI18NModel(BusinessHelper.createI18NModel());
//		try
//		{
//			Hashtable<?, ?> params = generateParameters(message, pretreatClass);
//
//			EMSEClient emseClient = EMSEClient.getEMSEClient();
//			result = emseClient.handleEvent(eventName, servProvCode, params, callerID);
//		}
//		finally
//		{
//			// Clear Thread local.
//			I18NContext.setI18NModel(null);
//		}

		return result;
	}

	/**
	 * Generate parameters.
	 *
	 * @param message the message
	 * @param pretreatClass the pretreating class
	 * @return the Hashtable<?, ?>
	 * 
	 * @throws Exception the exception
	 */
	private static Hashtable<?, ?> generateParameters(Message message, Class<?> pretreatClass) throws Exception
	{
		Hashtable<?, ?> parameters = null;
		Object pretreatclass = ClassUtil.newInstance(pretreatClass);

		if (pretreatclass instanceof EMSEParameterHandler)
		{
			parameters = ((EMSEParameterHandler) pretreatclass).pretreat(message);
		}
		else
		{
//			logger.error("Cannot find related EMSEParameter pretreating class: " + pretreatClass);
		}

		return parameters;
	}

}

/*
 * $Log: av-env.bat,v $
 */
