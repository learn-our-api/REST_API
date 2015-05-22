package com.cxf.restapis.framework.diagnosis.security;

import com.cxf.restapis.framework.security.handlers.AbstractSecurityAuthorizer;
import com.cxf.restapis.framework.security.handlers.ResourceBasic;
import com.cxf.restapis.framework.security.handlers.SecurityAuthorizer;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DiagnosisSecurityAuthorizer.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DiagnosisSecurityAuthorizer.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jul 2, 2014		tony.li		Initial.
 *  
 * </pre>
 */
public class DiagnosisSecurityAuthorizer extends AbstractSecurityAuthorizer implements SecurityAuthorizer
{

	@Override
	public boolean isCreateAllowed(String callerId, ResourceBasic resource)
	{
		if("logs".equals(resource.getOperation()))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean isDeleteAllowed(String callerId, ResourceBasic resource)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGetAllowed(String callerId, ResourceBasic resource)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isListAllowed(String callerId, ResourceBasic resource)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUpdateAllowed(String callerId, ResourceBasic resource)
	{
		// TODO Auto-generated method stub
		return false;
	}

}

/*
*$Log: av-env.bat,v $
*/