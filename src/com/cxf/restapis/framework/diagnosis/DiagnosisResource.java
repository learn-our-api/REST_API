package com.cxf.restapis.framework.diagnosis;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cxf.restapis.framework.constants.APIConstants.Applicability;
import com.cxf.restapis.framework.diagnosis.util.LogManager;
import com.cxf.restapis.framework.diagnosis.util.LogReader;
import com.cxf.restapis.framework.exception.DataValidateException;
import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.security.annotations.ApiSecurity;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.cxf.restapis.framework.util.WebThreadLocal;
import com.wordnik.swagger.annotations.ApiParam;



/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DiagnosisResource.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DiagnosisResource.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jul 2, 2014		tony.li		Initial.
 *  
 * </pre>
 */
//@Resource("diagnosis")
@Path("/diagnosis")
@Produces({MediaType.APPLICATION_JSON})
public class DiagnosisResource
{
//	private static AVLogger m_logger = AVLogger.getLogger(DiagnosisResource.class);
	@POST
	@Path("/logs")
	@ApiSecurity(resource = "diagnosis",  operation = "logs", applicability = Applicability.BOTH)
	public ResponseModel getLogs(@ApiParam(value = "Query parameter.", required = true) Map<String,String> request)
			throws Exception
	{
		/** variables within WebThrealLocal should be on top of each method */
		String userId = WebThreadLocal.getCurrentUser();
		String traceId = request.get("traceId");
		//String strDate = request.get("queryDate");
		
		LogReader lr =  LogManager.getLogReader("BIZ");
		int size = 100;
		try
		{
			size = Integer.parseInt(request.get("limit"));
		}
		catch (Exception e)
		{
			size = 100;
		}
		List<String> result = null;
		
		String strDate = transferTraceIdtoDateStr(getDateStrFromTraceId(traceId));
//		m_logger.info("Searched Log *********");
//		m_logger.info("Searched Trace ID:"+traceId.replace("-", "="));
//		m_logger.info("Searched Date:"+strDate);
//		m_logger.info("Searched By:"+userId);
//		m_logger.info("Searched Log *********");
		if(!ValidationUtil.isEmpty(traceId) && !ValidationUtil.isEmpty(userId))
		{
			result = lr.getMatchedLog(strDate, traceId,size);
		}
		return ResponseHelper.buildResponseModel(result,List.class, null, true, userId);
	}
	private String getDateStrFromTraceId(String traceId) throws Exception
	{
		String dateStr = null;
		if(traceId != null)
		{
			String[] args = traceId.split("-");
			if(args.length == 3 && args[0].equals("rest") && args[2].length() == 8)
			{
				dateStr = args[1];
			}
			else
			{
				throw new DataValidateException("Trace ID not valid.");
			}
		}
		else
		{
			throw new DataValidateException("Trace ID is required.");
		}
		return dateStr;
	}
	private String transferTraceIdtoDateStr(String time) throws Exception
	{
		String strTime = "";
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmssms");  
	       
	        Date date = new Date();
	        
	        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	        
	        strTime = formatter2.format(date);
	      
		}
		catch (Exception e)
		{
			throw new DataValidateException("Trace ID format failed.");
		}
		return strTime;
	}
	

	
}

/*
*$Log: av-env.bat,v $
*/
