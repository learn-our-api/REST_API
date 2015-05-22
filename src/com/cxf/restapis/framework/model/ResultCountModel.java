package com.cxf.restapis.framework.model;

import java.util.ArrayList;
import java.util.List;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: ResultCountModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  This class be designed to return create, update and delete result count.
 * 
 *  Notes:
 * 	$Id: ResultCountModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-12-14		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ResultCountModel implements java.io.Serializable
{
	
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 1867939872854163876L;

	public ResultCountModel()
	{
		super();
	}

	public ResultCountModel(Integer successCount, Integer failedCount, Object failedIDs, Object successIDs)
	{
		super();
		this.successCount = successCount;
		this.failedCount = failedCount;
		this.failedIDs = failedIDs;
		this.successIDs = successIDs;
	}

	/** The success count. */
	@JsonView(Views.BasicView.class)
	private Integer successCount;

	/** The failed count. */
	@JsonView(Views.BasicView.class)
	private Integer failedCount;

	/** The result. */
	@JsonView(Views.BasicView.class)
	private Object failedIDs;
	
	/** The success result. */
	@JsonView(Views.BasicView.class)
	private Object successIDs;

	/**
	 * Gets the success count.
	 * 
	 * @return the success count
	 */
	public Integer getSuccessCount()
	{
		return successCount;
	}

	/**
	 * Sets the success count.
	 * 
	 * @param successCount the new success count
	 */
	public void setSuccessCount(Integer successCount)
	{
		this.successCount = successCount;
	}

	/**
	 * Gets the failed count.
	 * 
	 * @return the failed count
	 */
	public Integer getFailedCount()
	{
		return failedCount;
	}

	/**
	 * Sets the failed count.
	 * 
	 * @param failedCount the new failed count
	 */
	public void setFailedCount(Integer failedCount)
	{
		this.failedCount = failedCount;
	}

	/**
	 * Gets the failed i ds.
	 * 
	 * @return the failed i ds
	 */
	public Object getFailedIDs()
	{
		return failedIDs;
	}

	/**
	 * Sets the failed i ds.
	 * 
	 * @param failedIDs the new failed i ds
	 */
	public void setFailedIDs(Object failedIDs)
	{
		this.failedIDs = failedIDs;
	}

	/**
	 * @return the successIDs
	 */
	
	public Object getSuccessIDs()
	{
		return successIDs;
	}

	/**
	 * @param successIDs the successIDs to set
	 */
	public void setSuccessIDs(Object successIDs)
	{
		this.successIDs = successIDs;
	}
	
	public static ResultCountModel getInstance ()
	{
		return new ResultCountModel(0, 0, new ArrayList<String>(8), new ArrayList<String>(8));
	}
	
	@SuppressWarnings("unchecked")
	public void addSuccessInfo(String info)
	{
		successCount++;
		// why not use List instead of Object
		// if (successIDs instanceof List)
		// {
		// }
		((List<String>) successIDs).add(info);
	}
	
	@SuppressWarnings("unchecked")
	public void addFailedInfo (String info)
	{
		failedCount++;
		((List<String>)failedIDs).add(info);
	}
	
	
	
	
	
}

/*
 * $Log: av-env.bat,v $
 */
