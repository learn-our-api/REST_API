package com.cxf.restapis.framework.diagnosis.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cxf.restapis.framework.util.ValidationUtil;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: LogManager.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: LogManager.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jul 2, 2014		tony.li		Initial.
 *  
 * </pre>
 */
public abstract class LogManager implements LogReader
{

	public static String AAHome = null;
	public static String AALog = null; 
	public final static  String fileSplitor = "\\"; 
	static{
//		AAHome = AVProperties.getAAHome();
	}
//	private static AVLogger m_logger = AVLogger.getLogger(LogManager.class);
	/**
	 * 
	 * getLogReader
	 *
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static LogReader getLogReader(String type) throws Exception
	{
		if("BIZ".equals(type))
		{
			LogReader lr = new BizLogReader();
			return lr;
		}
		else
		{
			 throw new Exception("");
		}
	}

	@Override
	public List<String> getMatchedLog(String date,String traceID,int chunkSize) throws Exception
	{
		
		List<File> files = this.getLog(date);
		if(!ValidationUtil.isEmpty(files))
		{
			return this.filterLog(files.get(0), traceID,chunkSize);
		}
		else
		{
			return null;
		}
	}
	
	
	private  boolean isWholeLine(String detail)
	{
		return detail.indexOf(" DEBUG ")>=0 ||detail.indexOf(" INFO ")>=0||detail.indexOf(" ERROR ")>=0||detail.indexOf(" WARN ")>=0 ;
	}

	private  boolean matched(String intiLine, String traceID)
	{
		if (intiLine.indexOf(traceID) >= 0)
		{
			return true;
		}
		
		return false;
	}
	private  List<String> filterLog(File file, String traceID,int chunkSize) throws IOException
	{
		List<String> list = new ArrayList<String>();
		if(chunkSize <=0 || chunkSize > 500)
		{
			chunkSize = 100;
		}
		if(file.exists())
		{
			StringBuilder intiLine = null;
			FileReader reader = null;
			BufferedReader br = null;
			try
			{
				Long start = System.nanoTime() ;
				
				intiLine = new StringBuilder("");
				reader = new FileReader(file);
				br = new BufferedReader(reader);
				
				String detail;
				boolean matched = false;
				int lineCount = 0;
				int total = 0;
				while ((detail = br.readLine()) != null)
				{
					total ++ ;
					
					if (isWholeLine(detail))
					{
						if(matched(intiLine.toString(),traceID))
						{
							list.add(intiLine.toString());
						}
						intiLine = new StringBuilder(detail);
						
					}
					else
					{
						intiLine.append("\r\n");
						intiLine.append(detail);
					}
					
					if (matched(intiLine.toString(), traceID) || matched)
					{
						if(lineCount >= chunkSize)
						{
							break;
						}
						if(!matched)
						{
							matched = true;
						}
						list.add(intiLine.toString());
						lineCount ++;
					
					}
				}
//				m_logger.info("Search traceId from biz log costed :" + (System.nanoTime()-start));
//				m_logger.info("Searched rows :"+total );
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				if(reader != null)
				{
					reader.close();
				}
				if(br != null)
				{
					br.close();
				}
				
				intiLine = null;
				reader = null;
				br = null;
			}
		}
		
		return list;
	}

}

/*
*$Log: av-env.bat,v $
*/