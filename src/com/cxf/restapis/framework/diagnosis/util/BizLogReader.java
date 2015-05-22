package com.cxf.restapis.framework.diagnosis.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cxf.restapis.framework.exception.DataValidateException;
import com.cxf.restapis.framework.util.ValidationUtil;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: BizLogReader.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: BizLogReader.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jul 2, 2014		tony.li		Initial.
 *  
 * </pre>
 */
public class BizLogReader extends LogManager
{
	private static String logPath = "";
	private static final String filePrefix = "server.log";

	public BizLogReader()
	{
		if(ValidationUtil.isEmpty(logPath))
		{
			logPath = "";
		}
	}
	@Override
	public List<File> getLog(String date) throws Exception
	{
		List<File> files = new ArrayList<File>();
		String fileName = null;
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(now);
		
		if(strDate.equals(date) || ValidationUtil.isEmpty(date))
		{
			fileName = this.getLogPath() + fileSplitor + filePrefix; 
		}
		else
		{
			fileName = this.getLogPath() + fileSplitor + filePrefix +"."+ date + ".log";
		}
		
		File file = new File(fileName);
		if(file.exists())
		{
			files.add(file);
		}
		else
		{
			throw new DataValidateException("File : "+ filePrefix +"."+ date + ".log" +" not found.");
		}
		return files;
	}

	@Override
	public String getLogPath()
	{
		return logPath;

	}
}

/*
*$Log: av-env.bat,v $
*/