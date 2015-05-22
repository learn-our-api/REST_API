package com.cxf.restapis.framework.validation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;

import com.cxf.restapis.framework.util.ConvertHelper;
import com.cxf.restapis.framework.util.ParameterHelper;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.ValidationUtil;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: GenericValidator.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: GenericValidator.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-5-10		dylan.liang		Initial.
 * 
 * </pre>
 */
public class CommonValidator implements Validator
{
	/** The logger for this class. */
//	private static final AVLogger logger = AVLogger.getLogger(CommonValidator.class);
	
	/** The Regular Expression Constant for Record ID. */
	private static final String REGEX_RECORDID = "[A-Za-z0-9]{5}\\-[A-Za-z0-9]{5}\\-[A-Za-z0-9]{5}";
	
	/** The Regular Expression Constant for Record ID which prefix is agency code. */
	private static final String REGEX_RECORDID_WITH_AGENCY = "[A-Za-z0-9_]{1,15}\\-[A-Za-z0-9]{5}\\-[A-Za-z0-9]{5}\\-[A-Za-z0-9]{5}";

	/** The Constant MAX_SIZE. */
	private static final int MAX_SIZE = 50;

	/**
	 * Validate record id.
	 *
	 * @param recordId the record id
	 * @return true, if successful
	 */
	public static boolean validateRecordID(String recordId)
	{
		boolean result = false;

		if (!ValidationUtil.isEmpty(recordId))
		{
			if(recordId.split("-").length ==3)
			{
				result = recordId.matches(REGEX_RECORDID);
			}
			else if(recordId.split("-").length ==4)
			{
				result = recordId.matches(REGEX_RECORDID_WITH_AGENCY);
			}			
		}

		return result;
	}
	
	
	/**
	 * 
	 * validate numbers 
	 *
	 * @param paramValue
	 * @return invalidNumbers
	 */
	public static List<String> validateNumbers(String paramValue)
	{
		List<String> paramValues = ConvertHelper.getURLParamToList(paramValue);
		List<String> invalidNumbers = null;
		if(paramValues!=null)
		{						
			invalidNumbers = new ArrayList<String>();
			for (String value : paramValues)
			{
				if (!ValidationUtil.isNumber(value))
				{
					invalidNumbers.add(value);
				}
			}
		}
		return invalidNumbers;
	}

	/* (non-Javadoc)
	 * @see com.accela.restapis.framework.validation.Validator#validate(org.apache.cxf.message.Message, org.apache.cxf.jaxrs.model.ClassResourceInfo)
	 */
	@Override
	public Response validate(Message message, ClassResourceInfo classResourceInfo) throws Exception
	{
		/** Handle method level input parameter format and type validator annotations */
		OperationResourceInfo ori = message.getExchange().get(OperationResourceInfo.class);
		Method method = ori.getMethodToInvoke();
		Validations validations = method.getAnnotation(Validations.class);

		Response response = null;

		if (!ValidationUtil.isEmpty(validations))
		{
			for (Validation validation : validations.value())
			{
				String paramName = validation.paramName();
				String paramValue = ParameterHelper.getPathParameter(message, paramName);
				ValidationType paramType = validation.type();

				if (ValidationType.Number.equals(paramType))
				{
					if (!ValidationUtil.isNumber(paramValue))
					{
						String msg = "Invalid request parameter " + paramName + " should be a number.";
						response = ResponseHelper.build(ResponseHelper.validationFailure(msg));
//						logger.error(msg + " value: " + paramValue);
					}
				}
				else if (ValidationType.Numbers.equals(paramType))
				{
					List<String> invalidNumbers = CommonValidator.validateNumbers(paramValue);
					if (!ValidationUtil.isEmpty(invalidNumbers))
					{
						String msg = "Invalid request parameter " + invalidNumbers.toString() + " should be a number.";
						response = ResponseHelper.build(ResponseHelper.validationFailure(msg));
//						logger.error(msg);
					}
				}
			}
		}
		
		return response;
	}
}

/*
 * $Log: av-env.bat,v $
 */
