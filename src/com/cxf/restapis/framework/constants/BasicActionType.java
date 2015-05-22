package com.cxf.restapis.framework.constants;

/**
 * <pre>
 * 
 * Accela Automation
 * File: BasicActionType.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2013
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: BasicActionType.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Jul 27, 2012		evan.cai		Initial.
 * 
 * </pre>
 */
public enum BasicActionType
{
	/**
	 * Create Operation. It also is upload operation for document.
	 */
	C,

	/**
	 * Update Operation. Document doesn't support the operation.
	 */
	U,

	/**
	 * Retrieve Operation. It also is download operation for document.
	 */
	R,

	/**
	 * Delete Operation.
	 */
	D,

	/**
	 * Get List operation.
	 */
	L;
}

/*
 * $Log: av-env.bat,v $
 */