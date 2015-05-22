package com.cxf.restapis.framework.security.handlers;

import com.cxf.restapis.framework.constants.BasicActionType;
import com.cxf.restapis.framework.constants.ErrorCodeConstants;
import com.cxf.restapis.framework.security.exceptions.FIDSecurityException;

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
public abstract class AbstractSecurityAuthorizer implements SecurityAuthorizer
{

	/** The instance of fid authorizer. */
	//private static FIDAuthorizer fidAuthorizer = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accela.restapis.framework.security.handlers.SecurityAuthorizer#isOperationAllowed(java.lang.String,
	 * java.lang.String, com.accela.restapis.framework.security.handlers.ResourceBasic)
	 */
	public boolean isOperationAllowed(String operation, String callerId, ResourceBasic resource)
	{
		boolean authorized = false;
		if (BasicActionType.L.toString().equals(operation))
		{
			// if LIST, let business layer to handle security; if nothing accessible, just return empty list
			authorized = isListAllowed(callerId, resource);
		}
		else if (BasicActionType.R.toString().equals(operation))
		{
			authorized = isGetAllowed(callerId, resource);
		}
		else if (BasicActionType.C.toString().equals(operation))
		{
			authorized = isCreateAllowed(callerId, resource);
		}
		else if (BasicActionType.U.toString().equals(operation))
		{
			authorized = isUpdateAllowed(callerId, resource);
		}
		else if (BasicActionType.D.toString().equals(operation))
		{
			authorized = isDeleteAllowed(callerId, resource);
		}
		return authorized;
	}

	/**
	 * Checks if is fID enabled.
	 * 
	 * @param servProvCode the service provider code
	 * @param module the module
	 * @param callerId the caller id
	 * @param fid the function id
	 * 
	 * @return true, if is fID enabled
	 */
	public boolean isFIDEnabled(String servProvCode, String module, String callerId, String fid)
	{
//		if (fidAuthorizer == null)
//		{
//			fidAuthorizer = new FIDAuthorizer();
//		}
		boolean authorized = FIDAuthorizer.isFIDEnabled(servProvCode, module, callerId, fid);
		if(!authorized)
		{
			throw new FIDSecurityException(ErrorCodeConstants.FID_AUTH_FAILD_401 ,fid);
		}
		return authorized;
	}
}
