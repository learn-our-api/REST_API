package com.cxf.restapis.framework.model;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: TypeModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: TypeModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Oct 10, 2013		bruce.deng		Initial.
 *  
 * </pre>
 */
public class DocumentTypeModel
{
	private String id;
	
	private String value;
	
	private String text;
	
	private boolean downloadable;
	
	/**
	 * upload document role  
	 */
	private boolean uploadable;
	
	/**
	 *  delete document role
	 */
	private boolean deletable;
	
	/**
	 * view title document role
	 */
	private boolean viewable;
	
	private IdentifierModel group;
	
	
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	
	/**
	 * @return the group
	 */
	
	public IdentifierModel getGroup()
	{
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(IdentifierModel group)
	{
		this.group = group;
	}

	/**
	 * @return the downloadable
	 */
	
	public boolean isDownloadable()
	{
		return downloadable;
	}

	/**
	 * @param downloadable the downloadable to set
	 */
	public void setDownloadable(boolean downloadable)
	{
		this.downloadable = downloadable;
	}

	/**
	 * @return the uploadable
	 */
	
	public boolean isUploadable()
	{
		return uploadable;
	}

	/**
	 * @param uploadable the uploadable to set
	 */
	public void setUploadable(boolean uploadable)
	{
		this.uploadable = uploadable;
	}

	/**
	 * @return the deletable
	 */
	
	public boolean isDeletable()
	{
		return deletable;
	}

	/**
	 * @param deletable the deletable to set
	 */
	public void setDeletable(boolean deletable)
	{
		this.deletable = deletable;
	}

	/**
	 * @return the viewable
	 */
	
	public boolean isViewable()
	{
		return viewable;
	}

	/**
	 * @param viewable the viewable to set
	 */
	public void setViewable(boolean viewable)
	{
		this.viewable = viewable;
	}
	
	
}

/*
*$Log: av-env.bat,v $
*/