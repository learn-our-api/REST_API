package com.cxf.restapis.framework.model;

import java.io.Serializable;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: PermissionModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: PermissionModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Sep 3, 2014		bryant.tu		Initial.
 *  
 * </pre>
 */
public class PermissionModel implements Serializable
{

	private static final long serialVersionUID = -1547167839678306991L;

	private boolean viewable;
	
	private boolean creatable;
	
	private boolean editable;
	
	private boolean deletable;
	
	private String entityType;

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

	/**
	 * @return the creatable
	 */
	
	public boolean isCreatable()
	{
		return creatable;
	}

	/**
	 * @param creatable the creatable to set
	 */
	public void setCreatable(boolean creatable)
	{
		this.creatable = creatable;
	}

	/**
	 * @return the editable
	 */
	
	public boolean isEditable()
	{
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable)
	{
		this.editable = editable;
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
	 * @return the entityType
	 */
	
	public String getEntityType()
	{
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType)
	{
		this.entityType = entityType;
	}
}

/*
*$Log: av-env.bat,v $
*/