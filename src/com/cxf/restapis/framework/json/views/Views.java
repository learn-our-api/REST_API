package com.cxf.restapis.framework.json.views;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: Views.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2013
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: Views.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-8-27		dylan.liang		Initial.
 * 
 * </pre>
 */
public class Views
{

	/**
	 * Basic View is for fields that need to be exposed every user.
	 */
	public static interface BasicView
	{

	}

	/**
	 * Public View is for fields that need to be exposed for external user.
	 */
	public static interface PublicView extends BasicView
	{

	}

	/**
	 * Citizen View is for fields that only need to be exposed for citizen user.
	 */
	public static interface CitizenView extends PublicView
	{

	}

	/**
	 * Agency View is Semi-public for fields that need to be exposed for agency user.
	 */
	public static interface AgencyView extends PublicView
	{

	}

	/**
	 * Internal View is for fields that need to be exposed for internal system.
	 */
	public static interface InternalView extends AgencyView, CitizenView
	{

	}
}

/*
 * $Log: av-env.bat,v $
 */