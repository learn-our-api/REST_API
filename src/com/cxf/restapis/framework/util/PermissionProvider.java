package com.cxf.restapis.framework.util;

import com.cxf.restapis.framework.model.PermissionModel;
import com.cxf.restapis.framework.security.exceptions.FIDSecurityException;
import com.cxf.restapis.framework.security.handlers.ResourceBasic;
import com.cxf.restapis.framework.security.handlers.SecurityAuthorizer;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: PermissionUtil.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: PermissionUtil.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Sep 3, 2014		bryant.tu		Initial.
 *  
 * </pre>
 */
public class PermissionProvider
{
	
	public static final String INSPECTION_CONDITIONS = "inspectionConditions";
	
	public static PermissionModel getPermissionModelBySecurityAuthorizer(String callerId, ResourceBasic resource, SecurityAuthorizer securityAuthorizer)
	{
		PermissionModel permissionModel = new PermissionModel();
		try
		{
			permissionModel.setCreatable(securityAuthorizer.isCreateAllowed(callerId, resource));	
		}
		catch(FIDSecurityException e)
		{
			permissionModel.setCreatable(false);
		}
		try
		{
			permissionModel.setViewable(securityAuthorizer.isGetAllowed(callerId, resource));
		}
		catch(FIDSecurityException e)
		{
			permissionModel.setViewable(false);
		}
		try
		{
			permissionModel.setEditable(securityAuthorizer.isUpdateAllowed(callerId, resource));
		}
		catch(FIDSecurityException e)
		{
			permissionModel.setEditable(false);
		}
		try
		{
			permissionModel.setDeletable(securityAuthorizer.isDeleteAllowed(callerId, resource));
		}
		catch(FIDSecurityException e)
		{
			permissionModel.setDeletable(false);
		}
		return permissionModel;
	}
}

/*
*$Log: av-env.bat,v $
*/