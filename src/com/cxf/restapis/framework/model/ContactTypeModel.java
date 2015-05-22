package com.cxf.restapis.framework.model;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: contactTypeModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: contactTypeModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Feb 27, 2014		bruce.deng		Initial.
 *  
 * </pre>
 */
public class ContactTypeModel
{
	private String value;
	
	private Long maxOccurance;
	
	private Long minOccurance;

	/**
	 * @return the value
	 */
	@JsonView(Views.PublicView.class)
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
	 * @return the maxOccurance
	 */
	@JsonView(Views.PublicView.class)
	public Long getMaxOccurance()
	{
		return maxOccurance;
	}

	/**
	 * @param maxOccurance the maxOccurance to set
	 */
	public void setMaxOccurance(Long maxOccurance)
	{
		this.maxOccurance = maxOccurance;
	}

	/**
	 * @return the minOccurance
	 */
	@JsonView(Views.PublicView.class)
	public Long getMinOccurance()
	{
		return minOccurance;
	}

	/**
	 * @param minOccurance the minOccurance to set
	 */
	public void setMinOccurance(Long minOccurance)
	{
		this.minOccurance = minOccurance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactTypeModel other = (ContactTypeModel) obj;
		if (value == null)
		{
			if (other.value != null)
				return false;
		}
		else if (!value.equals(other.value))
			return false;
		return true;
	}

	
	
	
}

/*
*$Log: av-env.bat,v $
*/