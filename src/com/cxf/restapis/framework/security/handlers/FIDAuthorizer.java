package com.cxf.restapis.framework.security.handlers;

import java.util.Collection;
import java.util.Iterator;

import com.cxf.restapis.framework.util.ValidationUtil;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: AbstractSecurityAuthorizer
 * 
 * Accela, Inc.
 * Copyright (C): 2013-2014
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: RecordSecurityAuthorizer.java 72642 2009-01-01 20:01:57Z ACHIEVO\aaa $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Jun 06, 2013		dylan.liang		Initial.
 * 
 * </pre>
 */
public class FIDAuthorizer
{

	/** The instance of logger . */
//	private static final AVLogger logger = AVLogger.getLogger(FIDAuthorizer.class);

	/**
	 * Checks if is fID enabled.
	 * 
	 * @param servProvCode the service provider code
	 * @param module the module
	 * @param callerId the caller id
	 * @param fid the Function ID
	 * 
	 * @return true, if is fID enabled
	 */
	public static boolean isFIDEnabled(String servProvCode, String module, String callerId, String fid)
	{
		boolean authorized = false;
		return authorized;
	}
}
