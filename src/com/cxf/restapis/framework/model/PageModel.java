package com.cxf.restapis.framework.model;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 * Accela Automation
 * File: PageModel.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2013
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: PageModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Apr 13, 2012		evan.cai		Initial.
 * 
 * </pre>
 */
public class PageModel implements java.io.Serializable
{
	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 113628729956741528L;

	/** The start. */
	@JsonView(Views.BasicView.class)
	private Integer offset;

	/** The end. */
	@JsonView(Views.BasicView.class)
	private Integer limit;

	/** The total. */
	@JsonView(Views.BasicView.class)
	private Integer total;

	/** The hasmore. */
	@JsonView(Views.BasicView.class)
	private boolean hasmore;

	/**
	 * Gets the offset.
	 * 
	 * @return the offset
	 */
	public Integer getOffset()
	{
		return offset;
	}

	/**
	 * Sets the offset.
	 * 
	 * @param offset the offset
	 */
	public void setOffset(Integer offset)
	{
		this.offset = offset;
	}

	/**
	 * Gets the limit.
	 * 
	 * @return the limit
	 */
	public Integer getLimit()
	{
		return limit;
	}

	/**
	 * Sets the limit.
	 * 
	 * @param limit the limit
	 */
	public void setLimit(Integer limit)
	{
		this.limit = limit;
	}

	/**
	 * Gets the total.
	 * 
	 * @return the total
	 */
	public Integer getTotal()
	{
		return total;
	}

	/**
	 * Sets the total.
	 * 
	 * @param total the new total
	 */
	public void setTotal(Integer total)
	{
		this.total = total;
	}

	/**
	 * Checks if is hasmore.
	 * 
	 * @return true, if is hasmore
	 */
	public boolean isHasmore()
	{
		return hasmore;
	}

	/**
	 * Sets the hasmore.
	 * 
	 * @param hasmore the new hasmore
	 */
	public void setHasmore(boolean hasmore)
	{
		this.hasmore = hasmore;
	}

}

/*
 * $Log: av-env.bat,v $
 */