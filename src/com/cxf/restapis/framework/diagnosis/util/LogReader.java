package com.cxf.restapis.framework.diagnosis.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: LogReader.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: LogReader.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jul 2, 2014		tony.li		Initial.
 *  
 * </pre>
 */
public interface LogReader
{
	/**
	 * 
	 * getLogPath.
	 *
	 * @return
	 */
	public abstract String getLogPath() throws Exception;
	/**
	 * 
	 * getLog.
	 *
	 * @return
	 */
	public abstract List<File> getLog(String date) throws Exception;
	
	/**
	 * 
	 * getMatchedLog.
	 *
	 * @param date
	 * @param traceID
	 * @param chunkSize
	 * @return
	 * @throws Exception
	 */
	public List<String> getMatchedLog(String date,String traceID,int chunkSize) throws Exception;
	
}

/*
*$Log: av-env.bat,v $
*/