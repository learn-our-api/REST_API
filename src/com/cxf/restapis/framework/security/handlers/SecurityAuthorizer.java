package com.cxf.restapis.framework.security.handlers;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: SecurityAuthorizer
 * 
 * Accela, Inc.
 * Copyright (C): 2013
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
public interface SecurityAuthorizer
{

	/**
	 * Checks if is operation allowed.
	 * 
	 * @param operation the operation
	 * @param callerId the caller id
	 * @param resource the resource
	 * 
	 * @return true, if is operation allowed
	 */
	public boolean isOperationAllowed(String operation, String callerId, ResourceBasic resource);

	/**
	 * Checks if is list allowed.
	 * 
	 * @param callerId the caller id
	 * @param resource the resource
	 * 
	 * @return true, if is list allowed
	 */
	public boolean isListAllowed(String callerId, ResourceBasic resource);

	/**
	 * Checks if is gets the allowed.
	 * 
	 * @param callerId the caller id
	 * @param resource the resource
	 * 
	 * @return true, if is gets the allowed
	 */
	public boolean isGetAllowed(String callerId, ResourceBasic resource);

	/**
	 * Checks if is creates the allowed.
	 * 
	 * @param callerId the caller id
	 * @param resource the resource
	 * 
	 * @return true, if is creates the allowed
	 */
	public boolean isCreateAllowed(String callerId, ResourceBasic resource);

	/**
	 * Checks if is update allowed.
	 * 
	 * @param callerId the caller id
	 * @param resource the resource
	 * 
	 * @return true, if is update allowed
	 */
	public boolean isUpdateAllowed(String callerId, ResourceBasic resource);

	/**
	 * Checks if is delete allowed.
	 * 
	 * @param callerId the caller id
	 * @param resource the resource
	 * 
	 * @return true, if is delete allowed
	 */
	public boolean isDeleteAllowed(String callerId, ResourceBasic resource);

}
