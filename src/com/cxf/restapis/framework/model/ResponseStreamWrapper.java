package com.cxf.restapis.framework.model;

import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: ResponseStreamWrapper.java
 * 
 * Accela, Inc.
 * Copyright (C): 2013
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: ResponseStreamWrapper.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Jun 25, 2013		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ResponseStreamWrapper
{
	/** The input stream. */
	private InputStream inputStream;

	/** The file name. */
	private String fileName;

	/** The file type. */
	private String fileType;

	/**
	 * Gets the input stream.
	 * 
	 * @return the input stream
	 */
	public InputStream getInputStream()
	{
		return inputStream;
	}

	/**
	 * Sets the input stream.
	 * 
	 * @param inputStream the new input stream
	 */
	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * @return the fileType
	 */

	public String getFileType()
	{
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}
}

/*
 * $Log: av-env.bat,v $
 */