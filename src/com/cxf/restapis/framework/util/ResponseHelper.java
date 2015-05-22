package com.cxf.restapis.framework.util;

import static com.cxf.restapis.framework.constants.ErrorCode.APP_ERROR_CODE;
import static com.cxf.restapis.framework.constants.ErrorCode.APP_SUCCESS;
import static com.cxf.restapis.framework.constants.ErrorCode.CONFIG_ERROR;
import static com.cxf.restapis.framework.constants.ErrorCode.DATA_VALIDATION_ERROR;
import static com.cxf.restapis.framework.constants.ErrorCodeConstants.FORBIDDEN_403;
import static com.cxf.restapis.framework.constants.MessageConstants.OBJECT_NOTFOUND;
import static com.cxf.restapis.framework.constants.MessageConstants.PARTIAL_SUCCEED;
import static com.cxf.restapis.framework.constants.MessageConstants.SUCCEED;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.cxf.restapis.framework.constants.ErrorCode;
import com.cxf.restapis.framework.constants.ErrorCodeConstants;
import com.cxf.restapis.framework.model.MultipleObjectResultModel;
import com.cxf.restapis.framework.model.PageModel;
import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.model.ResponseStatusDetailModel;
import com.cxf.restapis.framework.model.ResponseStatusModel;
import com.cxf.restapis.framework.model.RestENVtEntityType;
import com.cxf.restapis.framework.model.RestThreadLocal;

/**
 * <pre>
 * 
 *  bryant Automation
 *  File: SilverlightHelper.java
 * 
 *  bryant, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  The helper class to return appropriate response status code and error message and stacktrace.
 *  For silverlight application, return 200 for all response code except 404.
 * 
 *  Notes:
 * 	$Id: ResponseHelper.java 139796 2009-07-20 05:45:08Z ACHIEVO\hikelee.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2009-7-17		steven.yan		Initial.
 * 
 * </pre>
 */
public class ResponseHelper
{
	/** The Constant logger. */
//	private final static AVLogger logger = AVLogger.getLogger(ResponseHelper.class);

	/**
	 * Wrap response for no authorized failures.
	 * 
	 * @param message the message
	 * @return the response model
	 */
	public static ResponseModel forbidden(String message)
	{
//		logger.error(message);
		ResponseModel response = new ResponseModel();
		response.setStatus(SC_FORBIDDEN);
		if(RestThreadLocal.getVersion()>3)
		{
			response.setCode(FORBIDDEN_403);
		}
		else
		{
			response.setCode(APP_ERROR_CODE);
		}
		response.setMessage(message);
		response.setTraceId(WebThreadLocal.getTraceId());
		return response;
	}

	/**
	 * Wrap response for not found validation failures.
	 * 
	 * @param message the message
	 * @return the response model
	 */
	public static ResponseModel notFound(String message)
	{
//		logger.error(message);
		ResponseModel response = new ResponseModel();
		
		if(RestThreadLocal.getVersion()>0)
		{
			if(RestThreadLocal.getVersion()>3)
			{
				response.setCode(ErrorCodeConstants.NOT_FOUND_404);
			}
			else
			{
				response.setCode(DATA_VALIDATION_ERROR.getCode());
			}
			response.setStatus(SC_NOT_FOUND);
			response.setMessage(message);
			response.setTraceId(WebThreadLocal.getTraceId());
		}
		else
		{
			ResponseStatusModel status = getResponseStatus(DATA_VALIDATION_ERROR, SC_NOT_FOUND, message, null);
			response.setResponseStatus(status);
		}
		return response;
	}

	/**
	 * Wrap response for validation failure.
	 * 
	 * @param message the message
	 * @return the response model
	 */
	public static ResponseModel validationFailure(String message)
	{
//		logger.error(message);
		ResponseModel response = new ResponseModel();
		if(RestThreadLocal.getVersion()>0)
		{
			if(RestThreadLocal.getVersion()>3)
			{
				response.setCode(ErrorCodeConstants.DATA_VALIDATION_ERROR_400);
			}
			else
			{
				response.setCode(DATA_VALIDATION_ERROR.getCode());
			}
			response.setStatus(SC_BAD_REQUEST);
			response.setMessage(message);
			response.setTraceId(WebThreadLocal.getTraceId());
		}
		else
		{
			ResponseStatusModel status = getResponseStatus(DATA_VALIDATION_ERROR, SC_BAD_REQUEST, message, null);
			response.setResponseStatus(status);
		}
		
		return response;
	}

	/**
	 * Wrap response for application error
	 * 
	 * @param message
	 * @return
	 */
	public static ResponseModel applicationError(String message)
	{
//		logger.error(message);
		ResponseModel response = new ResponseModel();
		response.setStatus(SC_INTERNAL_SERVER_ERROR);
		//Change the code from int type to String
		if(RestThreadLocal.getVersion()>3)
		{
			response.setCode(ErrorCodeConstants.INTERNAL_SERVER_ERROR_500);
		}
		else
		{
			response.setCode(APP_ERROR_CODE.getCode());
		}
		
		response.setMessage(message);
		response.setTraceId(WebThreadLocal.getTraceId());
		return response;
	}

	
	/**
	 * Wrap response for configuration error
	 * 
	 * @param message the message
	 * @return the response model
	 */
	public static ResponseModel configError(String message)
	{
//		logger.error(message);
		ResponseModel response = new ResponseModel();
		if(RestThreadLocal.getVersion()>0)
		{
			if(RestThreadLocal.getVersion()>3)
			{
				response.setCode(ErrorCodeConstants.CONFIGURATION_ERROR_500);
			}
			else
			{
				response.setCode(CONFIG_ERROR.getCode());
			}
			
			response.setStatus(SC_INTERNAL_SERVER_ERROR);
			response.setMessage(message);
			response.setTraceId(WebThreadLocal.getTraceId());
		}
		else
		{
			ResponseStatusModel status = getResponseStatus(CONFIG_ERROR, SC_INTERNAL_SERVER_ERROR, message, null);
			response.setResponseStatus(status);
		}
		return response;
	}

	public static Response jsonPaseError(String message, Throwable e)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			return errorV4(ErrorCodeConstants.BAD_REQUEST_400, SC_BAD_REQUEST, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_BAD_REQUEST, message, e);
		}
	}
	
	public static Response notFound(String message, Throwable e)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			return errorV4(ErrorCodeConstants.NOT_FOUND_404, SC_NOT_FOUND, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_NOT_FOUND, message, e);
		}
	}
	
	public static Response duplicateError(String message, Throwable e)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			return errorV4(ErrorCodeConstants.DUPLICATE_409, SC_CONFLICT, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_CONFLICT, message, e);
		}
	}
	
	/**
	 * Wrap response for validation failure.
	 * 
	 * @param message the message
	 * @return the response model
	 */
	public static ResponseModel authorizedFailure(String code,String message)
	{
//		logger.error(code);
		ResponseModel response = new ResponseModel();
		if(RestThreadLocal.getVersion()>0)
		{
			if(RestThreadLocal.getVersion()>3)
			{
				response.setCode(code);
			}
			else
			{
				response.setCode(DATA_VALIDATION_ERROR.getCode());
			}
			response.setMessage(message);
			response.setStatus(SC_UNAUTHORIZED);
			response.setTraceId(WebThreadLocal.getTraceId());
		}
		else
		{
			ResponseStatusModel status = getResponseStatus(DATA_VALIDATION_ERROR, SC_UNAUTHORIZED, message, null);
			response.setResponseStatus(status);
		}
		
		return response;
	}
	
	public static Response notAuthorized(String message, Throwable e,String errorCode)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			if(ValidationUtil.isEmpty(errorCode))
			{
				errorCode = ErrorCodeConstants.UNAUTHORIZED_401;
			}
			return errorV4(errorCode, SC_UNAUTHORIZED, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_UNAUTHORIZED, message, e);
		}
	}
	
	public static Response forbidden(String message, Throwable e)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			return errorV4(ErrorCodeConstants.FORBIDDEN_403, SC_FORBIDDEN, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_FORBIDDEN, message, e);
		}
	}
	
	public static Response internalServerError(String message, Throwable e)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			return errorV4(ErrorCodeConstants.INTERNAL_SERVER_ERROR_500, SC_INTERNAL_SERVER_ERROR, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_INTERNAL_SERVER_ERROR, message, e);
		}
	}
	
	public static Response emseError(String message, Throwable e)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			return errorV4(ErrorCodeConstants.EMSE_500, SC_INTERNAL_SERVER_ERROR, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_INTERNAL_SERVER_ERROR, message, e);
		}
	}
	
	public static Response badRequest(String message, Throwable e)
	{
		if (RestThreadLocal.getVersion() > 3)
		{
			return errorV4(ErrorCodeConstants.BAD_REQUEST_400, SC_BAD_REQUEST, message, e);
		}
		else
		{
			return error(APP_ERROR_CODE, SC_BAD_REQUEST, message, e);
		}
	}
	
	public static Response build(ResponseModel responseModel)
	{
		ResponseBuilder responseBuilder = Response.status(responseModel.getStatus());
		responseBuilder.entity(responseModel);

		// 4.0 Build Response.
		return responseBuilder.build();
	}

	
	/**
	 * Return the response giving status code and error message.
	 * 
	 * @param statusCode the response status code.
	 * @param message the error message.
	 * @param message the error exception.
	 * @return the response giving status code, error message and exception.
	 */
	private static Response error(ResponseModel responseModel, ErrorCode errorCode, Integer statusCode, String message,
			Throwable e)
	{
//		logger.error(message);
		// 1.0 get response status.
		setResponseStatus(responseModel, errorCode, statusCode, message, e);
		ResponseBuilder responseBuilder = Response.status(statusCode);
		responseBuilder.entity(responseModel);

		// 2.0 Build Response.
		return responseBuilder.build();
	}
	
	/**
	 * Return the response giving status code and error message.
	 * 
	 * @param statusCode the response status code.
	 * @param message the error message.
	 * @param message the error exception.
	 * @return the response giving status code, error message and exception.
	 */
	private static Response errorV4(ResponseModel responseModel, String errorCode, Integer statusCode, String message,
			Throwable e)
	{
//		logger.error(message);
		// 1.0 get response status.
		setStatusInformationV4(responseModel, errorCode, statusCode, message, e);
		ResponseBuilder responseBuilder = Response.status(statusCode);
		responseBuilder.entity(responseModel);
		
		// 2.0 Build Response.
		return responseBuilder.build();
	}

	public static void setResponseStatus(ResponseModel responseModel, ErrorCode errorCode, Integer statusCode,
			String message, Throwable e)
	{
		if(RestThreadLocal.getVersion()>0)
		{
			setStatusInformation(responseModel,errorCode, statusCode, message, e);	
		}
		else
		{	
			ResponseStatusModel responseStatus = getResponseStatus(errorCode, statusCode, message, e);
			responseModel.setResponseStatus(responseStatus);
			// Set response result, eg, when emse event after throw exception, it need throw result.
			if (!ValidationUtil.isEmpty(RestThreadLocal.getAttribute(RestENVtEntityType.RefContactId)))
			{
				responseModel.setResult(RestThreadLocal.getAttribute(RestENVtEntityType.RefContactId));
			}

		}
	}

	/**
	 * Return the response giving status code and error message.
	 * 
	 * @param statusCode the response status code.
	 * @param message the error message.
	 * @param message the error exception.
	 * @return the response giving status code, error message and exception.
	 */
	private static Response error(ErrorCode errorCode, Integer statusCode, String message, Throwable e)
	{
//		logger.error(message);

		// 1.0 set error information the the response builder.
		ResponseBuilder responseBuilder = Response.status(statusCode);
		
		//2.0 Get resonseModel.
		ResponseModel responseModel = new ResponseModel();
		setResponseStatus(responseModel, errorCode, statusCode, message, e);

		responseBuilder.entity(responseModel);

		// 3.0 Build Response.
		return responseBuilder.build();
	}
	
	private static Response errorV4(String errorCode, Integer statusCode, String message, Throwable e)
	{
//		logger.error(message);

		// 1.0 set error information the the response builder.
		ResponseBuilder responseBuilder = Response.status(statusCode);
		
		//2.0 Get resonseModel.
		ResponseModel responseModel = new ResponseModel();
		setStatusInformationV4(responseModel, errorCode, statusCode, message, e);

		responseBuilder.entity(responseModel);

		// 3.0 Build Response.
		return responseBuilder.build();
	}

	/**
	 * The method is retired from API V1 version, because ResponseStatusModel be retired.
	 */
	@Deprecated
	public static ResponseStatusModel getResponseStatus(ErrorCode errorCode, Integer statusCode, String message,
			Throwable e)
	{
		// 1.0 if the message is null, then get the error message from the exception.
		if (message == null && e != null)
		{
			message = e.getMessage();
			if (message == null)
			{
				message = e.toString();
			}
		}

		// 2.0 get the exception trance.
		String stackTrace = null;
		if (e != null)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			printWriter.flush();
			printWriter.close();
			stackTrace = writer.toString();
		}

		ResponseStatusDetailModel details = null;
		if (SC_OK != statusCode.intValue() || !APP_SUCCESS.equals(errorCode))
		{
			// 3.0 generate ResponseStatusModel.
			details = new ResponseStatusDetailModel();
			details.setCode(errorCode != null ? errorCode.getCode() : null);
			details.setMessage(message);
			details.setMore(stackTrace);
			details.setTraceId(WebThreadLocal.getTraceId());
		}

		ResponseStatusModel responseStatus = new ResponseStatusModel();
		responseStatus.setStatus(statusCode);
		responseStatus.setDetail(details);

		// 4.0 return ResponseStatusModel;
		return responseStatus;
	}
	
	private static void setStatusInformation(ResponseModel resposne, ErrorCode errorCode, Integer statusCode,
			String message, Throwable e)
	{
		//1.0 Get message.
		if (message == null && e != null)
		{
			message = e.getMessage();
			if (message == null)
			{
				message = e.toString();
			}
		}

		// 2.0 get the exception trance.
		String stackTrace = null;
		if (e != null)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			printWriter.flush();
			printWriter.close();
			stackTrace = writer.toString();
//			logger.error(stackTrace);
		}

		//3.0 Set the error information to responseModel.
		if (SC_OK != statusCode.intValue() || !APP_SUCCESS.equals(errorCode))
		{
			resposne.setCode(errorCode != null ? errorCode.getCode() : null);
			resposne.setMessage(message);			
			resposne.setTraceId(WebThreadLocal.getTraceId());
		}
		
		//4.0 Set reponse Status.
		resposne.setStatus(statusCode);
	}
	
	public static void setStatusInformationV4(ResponseModel resposne, String errorCode, Integer statusCode,
			String message, Throwable e)
	{
		//1.0 Get message.
		if (message == null && e != null)
		{
			message = e.getMessage();
			if (message == null)
			{
				message = e.toString();
			}
		}

		// 2.0 get the exception trance.
		String stackTrace = null;
		if (e != null)
		{
			StringWriter writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			printWriter.flush();
			printWriter.close();
			stackTrace = writer.toString();
//			logger.error(stackTrace);
		}

		//3.0 Set the error information to responseModel.
		if (SC_OK != statusCode.intValue())
		{
			resposne.setCode(errorCode);
			resposne.setMessage(message);
			resposne.setTraceId(WebThreadLocal.getTraceId());
		}
		
		//4.0 Set reponse Status.
		resposne.setStatus(statusCode);
	}

	/**
	 * 
	 * Set Result to Response for the search APIs. the pagination be handled with sublist in memory by this method.
	 * 
	 * @param response the search response
	 * @param result the return result
	 * @param offset the offset of page
	 * @param limit the limit of page
	 * @param hasTotal the size of the result.
	 */
	public static ResponseModel buildResponseModelForSearch(List<?> result, Class<?> resultType, String fields,
			int offset, int limit, boolean hasTotal, String callerID)
	{
		ResponseModel response = new ResponseModel();

		ResponseHelper.error(response, APP_SUCCESS, SC_OK, SUCCEED, null);
		if (!ValidationUtil.isEmpty(result))
		{
			response.setResult(result);
			setPageInformation(response, result, offset, limit, hasTotal);
			response.setResultType(resultType);
//			response.setFields(ConvertHelper.getURLParamToSet(fields));;
			response.setCurrentUser(callerID);
		}
		else
		{
			response.setResult(null);
			response.setCurrentUser(callerID);
		}
		
		return response;
	}
	
	public static ResponseModel buildResponseModelForSearch(List<?> result, Class<?> resultType, String fields,
			int offset, int limit, boolean hasTotal, String callerID, boolean isWriteNullValue, boolean isUseCustomConvertor)
	{
		ResponseModel response = buildResponseModelForSearch(result, resultType, fields, offset, limit, hasTotal, callerID);
		response.setWriteNullValue(isWriteNullValue);
		RestThreadLocal.setUseCustomConvertor(isUseCustomConvertor);
		return response;
	}
	
	public static ResponseModel buildResponseModelForSearch(List<?> result, Class<?> resultType, String fields,
			int offset, int limit, int totalNumber, String callerID)
	{
		ResponseModel response = buildResponseModelForSearch(result, resultType, fields,
					offset, limit, false, callerID);
		if(response!=null && response.getPage()!=null)
		{
			response.getPage().setTotal(totalNumber);
		}
		return response;
	}
	
	/**
	 * Builds the response model for search, the pagination be handled by DB.
	 *
	 * @param result the search result
	 * @param resultType the result type
	 * @param fields the fields filter fields
	 * @param offset the offset of page
	 * @param limit the limit of page
	 * @return the response model
	 */
	public static ResponseModel buildResponseModelForSearch(List<?> result, Class<?> resultType, String fields,
			int offset, int limit)
	{
		ResponseModel response = new ResponseModel();
		ResponseHelper.error(response, APP_SUCCESS, SC_OK, SUCCEED, null);

		if (!ValidationUtil.isEmpty(result))
		{
			/* Validate and reset default value */
			offset = ParameterHelper.validatePageOffset(offset);
			limit = ParameterHelper.validatePageLimit(limit);

			PageModel page = new PageModel();
			page.setOffset(offset);
			page.setLimit(limit);

			if (result.size() > limit)
			{// if the result bigger than the end row. It means the result has more data.
				page.setHasmore(true);
				response.setResult(result.subList(0, limit));
			}
			else
			{
				page.setHasmore(false);
				response.setResult(result);
			}

			response.setPage(page);
			response.setResultType(resultType);
//			response.setFields(ConvertHelper.getURLParamToSet(fields));
		}
		else if (RestThreadLocal.getVersion()<3)
		{
			response.setResult(new ArrayList());
		}

		return response;
	}


	
	/**
	 * 
	 * Set result to response with page.
	 * 
	 * @param response ResponseModel
	 * @param result the result model;
	 * @param isSearchSubResourc the flag determine whether the operation is search subResource
	 * @param qf the query format.
	 */
	public static ResponseModel buildResponseModel(Object result, Class<?> resultType, String fields,
			boolean ignoreNotFound, String callerID)
	{
		ResponseModel response = new ResponseModel();

		if (ValidationUtil.isEmpty(result) && !ignoreNotFound)
		{
			if(RestThreadLocal.getVersion()>3)
			{
				ResponseHelper.notFound(OBJECT_NOTFOUND);
			}
			else
			{
				ResponseHelper.error(response, APP_SUCCESS, SC_NOT_FOUND, OBJECT_NOTFOUND, null);
			}
			
		}
		else if(!ValidationUtil.isEmpty(result) && result instanceof List && resultType == MultipleObjectResultModel.class)
		{
			try
			{
				List resultList = (List)result;
				int failedCount = 0;

				for (int i = 0 ; i < resultList.size() ; i++)
				{
					try
					{
						MultipleObjectResultModel multipleObjectResultModel = (MultipleObjectResultModel)resultList.get(i);
						if (!ValidationUtil.isEmpty(multipleObjectResultModel)
								&& !ValidationUtil.isEmpty(multipleObjectResultModel.getIsSuccess())
								&& !multipleObjectResultModel.getIsSuccess())
						{
							failedCount ++ ;
						}
							
					}
					catch (Exception e)
					{
//						logger.error("BuildResponseModel failed : object in result can not convert to MultipleObjectResultModel");
//						logger.error(e.getMessage());
					}
					
				}
				if (failedCount == 0)
				{
					ResponseHelper.errorV4(response, null, SC_OK, SUCCEED, null);
				}
				else if (failedCount > 0 && failedCount < resultList.size())
				{
					ResponseHelper.errorV4(response, ErrorCodeConstants.OPERATE_PART_SUCCESS_206, SC_PARTIAL_CONTENT, PARTIAL_SUCCEED, null);
				}
				else if (failedCount == resultList.size())
				{
					ResponseHelper.errorV4(response, ErrorCodeConstants.INTERNAL_SERVER_ERROR_500, SC_INTERNAL_SERVER_ERROR, null, null);
				}
				response.setResult(result);
				response.setResultType(resultType);
//				response.setFields(ConvertHelper.getURLParamToSet(fields));
				response.setCurrentUser(callerID);
			}
			catch (Exception e)
			{
//				logger.error("BuildResponseModel failed :");
//				logger.error(e.getMessage());
			}

		}
		else if(!ValidationUtil.isEmpty(result) && result instanceof ResponseModel && ErrorCodeConstants.EMSE_500.equals(((ResponseModel)result).getCode()))
		{
			ResponseModel responseModel = (ResponseModel)result;
			response.setCode(responseModel.getCode());
			response.setMessage(responseModel.getMessage());
			response.setStatus(SC_INTERNAL_SERVER_ERROR);
		}
		else
		{
			ResponseHelper.error(response, APP_SUCCESS, SC_OK, SUCCEED, null);

			if(!ValidationUtil.isEmpty(result))
			{
				response.setResult(result);
			}
			
			response.setResultType(resultType);
//			response.setFields(ConvertHelper.getURLParamToSet(fields));
			response.setCurrentUser(callerID);
		}

		return response;
	}

	/**
	 * 
	 * Set result to response with page.
	 * 
	 * @param response ResponseModel
	 * @param result the result model;
	 * @param isSearchSubResourc the flag determine whether the operation is search subResource
	 * @param qf the query format.
	 */
	public static ResponseModel buildResponseModel(Object result, Class<?> resultType, String fields,
			boolean ignoreNotFound, String callerID,boolean isWriteNullValue)
	{
		ResponseModel response = buildResponseModel(result, resultType, fields,
				ignoreNotFound, callerID);

		response.setWriteNullValue(isWriteNullValue);
		
		return response;
	}

	/**
	 * 
	 * Set result to response with page.
	 * 
	 * @param response ResponseModel
	 * @param result the result model;
	 * @param isSearchSubResourc the flag determine whether the operation is search subResource
	 * @param qf the query format.
	 */
	public static ResponseModel buildResponseModel(Object result, Class<?> resultType, String fields,
			boolean ignoreNotFound, String callerID,boolean isWriteNullValue, boolean isUseCustomConvertor)
	{
		ResponseModel response = buildResponseModel(result, resultType, fields,
				ignoreNotFound, callerID);
		response.setWriteNullValue(isWriteNullValue);
		RestThreadLocal.setUseCustomConvertor(isUseCustomConvertor);
		RestThreadLocal.setAttribute(RestENVtEntityType.fields, response.getFields());
		
		return response;
	}
	
	public static ResponseModel buildResponseModel(Object result, Class<?> resultType, String fields,
			boolean ignoreNotFound, String callerID, boolean isWriteNullValue, boolean isUseCustomConvertor, Object code, String message)
	{
		ResponseModel response = buildResponseModel(result, resultType, fields, ignoreNotFound, callerID, isWriteNullValue, isUseCustomConvertor);
		if(!ValidationUtil.isEmpty(code))
		{
			response.setCode(code);
		}
		if(!ValidationUtil.isEmpty(message))
		{
			response.setMessage(message);
		}
		return response;
	}
	
	public static ResponseModel buildResponseModel(Object result, Class<?> resultType, String fields,
			boolean ignoreNotFound, String callerID, Object code, String message)
	{
		ResponseModel response = buildResponseModel(result, resultType, fields, ignoreNotFound, callerID);
		response.setCode(code);
		response.setMessage(message);

		return response;
	}
	
	/**
	 * 
	 * Set page information to the response.
	 * 
	 * @param response the response mode.
	 * @param items the response result.
	 * @param offset the offset of page
	 * @param limit the limit of page
	 * @param total the size of the response model list
	 */
	public static void setPageInformation(ResponseModel response, List<?> items, int offset, int limit, boolean hasTotal)
	{
		/* Validate and reset default value */
		offset = ParameterHelper.validatePageOffset(offset);
		limit = ParameterHelper.validatePageLimit(limit);

		PageModel page = new PageModel();
		page.setOffset(offset);
		page.setLimit(limit);

		// the size of the response model list
		if (hasTotal)
		{
			page.setTotal(items.size());
		}

		int startRow = offset;// start from 0
		int endRow = offset + limit;

		if (items.size() <= startRow)
		{// if the result less than the start row. It means we haven't found new result for current search.
			page.setHasmore(false);
			response.setResult(null);
		}
		else if (items.size() > endRow)
		{// if the result bigger than the end row. It means the result has more data.
			page.setHasmore(true);
			response.setResult(items.subList(startRow, endRow));
		}
		else
		{
			page.setHasmore(false);
			response.setResult(items.subList(startRow, items.size()));
		}

		response.setPage(page);
	}
	
	/**
	 * 
	 * Set page information to the response.
	 * 
	 * @param response the response mode.
	 * @param items the response result.
	 * @param offset the offset of page
	 * @param limit the limit of page
	 * @param total the size of the response model list truncate
	 */
	public static void setPageInformationNotTruncate(ResponseModel response, List<?> items, int offset, int limit, boolean hasTotal)
	{
		/* Validate and reset default value */
		offset = ParameterHelper.validatePageOffset(offset);
		limit = ParameterHelper.validatePageLimit(limit);

		PageModel page = new PageModel();
		page.setOffset(offset);
		page.setLimit(limit);

		// the size of the response model list
		if (hasTotal)
		{
			page.setTotal(items.size());
		}

		if (items.size() <= limit)
		{
			page.setHasmore(false);
			response.setResult(items);
		}
		else
		{
			page.setHasmore(true);
			response.setResult(items.subList(0, limit));
		}

		response.setPage(page);
	}
	
	/**
	 * 
	 * Set Result to Response for the search APIs. the pagination be handled with sublist in memory by this method.
	 * 
	 * @param response the search response
	 * @param result the return result
	 * @param offset the offset of page
	 * @param limit the limit of page
	 * @param hasTotal the size of the result.
	 */
	public static ResponseModel buildResponseModelNotTruncate(List<?> result, Class<?> resultType, String fields,
			int offset, int limit, boolean hasTotal, String callerID)
	{
		ResponseModel response = new ResponseModel();

		ResponseHelper.error(response, APP_SUCCESS, SC_OK, SUCCEED, null);
		if (!ValidationUtil.isEmpty(result))
		{
			response.setResult(result);
			setPageInformationNotTruncate(response, result, offset, limit, hasTotal);
			response.setResultType(resultType);
//			response.setFields(ConvertHelper.getURLParamToSet(fields));;
			response.setCurrentUser(callerID);
		}
		else
		{
			response.setResult(null);
			response.setCurrentUser(callerID);
		}
		
		return response;
	}
	
	public static ResponseModel buildResponseModelNotTruncate(List<?> result, Class<?> resultType, String fields,
			int offset, int limit, boolean hasTotal, String callerID, boolean isWriteNullValue, boolean isUseCustomConvertor)
	{
		ResponseModel response = buildResponseModelNotTruncate(result, resultType, fields, offset, limit, hasTotal, callerID);
		response.setWriteNullValue(isWriteNullValue);
		RestThreadLocal.setUseCustomConvertor(isUseCustomConvertor);
		return response;
	}
	
	public static ResponseModel buildResponseModelNotTruncate(List<?> result, Class<?> resultType, String fields,
			int offset, int limit, int totalNumber, String callerID)
	{
		ResponseModel response = buildResponseModelNotTruncate(result, resultType, fields,
					offset, limit, false, callerID);
		if(response!=null && response.getPage()!=null)
		{
			response.getPage().setTotal(totalNumber);
		}
		return response;
	}

	/**
	 * Wrap response for duplicate error validation failures.
	 * 
	 * @param message the message
	 * 
	 * @return the response model
	 */
	public static ResponseModel duplicateError(String message)
	{
//		logger.error(message);
		ResponseModel response = new ResponseModel();

		if (RestThreadLocal.getVersion() > 0)
		{
			if (RestThreadLocal.getVersion() > 3)
			{
				response.setCode(ErrorCodeConstants.DUPLICATE_409);
			}
			else
			{
				response.setCode(DATA_VALIDATION_ERROR.getCode());
			}
			response.setStatus(SC_CONFLICT);
			response.setMessage(message);
			response.setTraceId(WebThreadLocal.getTraceId());
		}
		else
		{
			ResponseStatusModel status = getResponseStatus(DATA_VALIDATION_ERROR, SC_CONFLICT, message, null);
			response.setResponseStatus(status);
		}

		return response;
	}

}

/*
 * $Log: av-env.bat,v $
 */
