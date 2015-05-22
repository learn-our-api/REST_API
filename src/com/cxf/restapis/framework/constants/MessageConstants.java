package com.cxf.restapis.framework.constants;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: MessgeConstants.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2015
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: MessgeConstants.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * 2012-11-13		dylan.liang		Initial.
 * 
 * </pre>
 */
public class MessageConstants
{

	/** SUCCEED Constant. */
	public static final String SUCCEED = "Succeed.";
	
	/** PARTIAL SUCCEED Constant. */
	public static final String PARTIAL_SUCCEED = "Partial succeed.";
	
	/** The error message for failed to do SSO authentication. */
	public static final String SSO_FAILED = "SSO authentication failed.";

	/** The Constant MESSAGE_DUPLICATED_EMAILID. */
	public static final String DUPLICATED_EMAILID = "The email address entered is already in use. Please enter a different email address.";

	/** The Constant MESSAGE_DUPLICATED_USERID. */
	public static final String DUPLICATED_USERID = "The username you have entered is already in use. Please enter a different username.";

	/** The Constant MESSAGE_LOGIN_FAILED. */
	public static final String LOGIN_FAILED = "Invalid user name or password.";

	public static final String AUTH_FAILED = "You are not authorized to operate on the requested resource";

	/** The error message for failed to do FID authorization. */
	public static final String FID_FAILED = "FID authorization failed.";

	/** The error message for failed on record security authorization. */
	public static final String RECORD_SECURITY = "Record security authorization failed";

	/** The error message for failed on record detail security authorization. */
	public static final String RECORD_DETAIL_SECURITY = "Record detail security authorization failed";

	/** The Constant OBJECT_NOTFOUND. */
	public static final String OBJECT_NOTFOUND = "The resource cannot be found.";
	
	/** The Constant MESSAGE_DATA_VALIDATION_FAILED. */
	public static final String DATA_VALIDATION_FAILED = "Data validation failed.";
	
	/** The Constant MESSAGE_DATA_VALIDATION_FAILED. */
	public static final String DATA_ADD_FAILED = "Data add failed.";

	/** The Constant MESSAGE_USER_ACCOUNT_UNAVAILABLE. */
	public static final String USER_ACCOUNT_UNAVAILABLE = "The User account is unavailable.";
	
	/** The Constant MESSAGE_EDMS_SERVER_UNAVAILABLE. */
	public static final String EDMS_SERVER_UNAVAILABLE = "The EDMS server is unavailable.";
	
	/** The Constant MESSAGE_USER_ACCOUNT_UNAVAILABLE. */
	public static final String USER_ACCOUNT_NO_MAPPING = "There is no corresponding AA mapping user.";
	
	/** The Constant MESSAGE_USER_ACCOUNT_NOT_EXIST. */
	public static final String USER_ACCOUNT_NOT_EXIST = "The user not exist.";
	
	/** The Constant MESSAGE_USER_ACCOUNT_NOT_ACTIVE. */
	public static final String USER_ACCOUNT_NOT_ACTIVE = "The User account is inactive.";
	
	/** The Constant MESSAGE_USER_ACCOUNT_DISABLE. */
	public static final String USER_ACCOUNT_DISABLED = "The User account is disabled.";

	/** The error message for the record condition is locked or hold. */
	public static final String RECORD_LOCKED_HOLD = "Record was locked or Hold by conditions.";
	
	/** The error message for the owner FullName is empty. */
	public static final String OWNER_VALIDATE_FULLNAME_EMPTY = "Field fullName is required.";

	/** App server Error message. */
	public static final String SERVER_ERROR = "We are experiencing internal server error. Please contact Agency Administrator for assistance.";

	/** Duplicated Error message. */
	public static final String DUPLICATE_ERROR = "This item may be duplicates.";

	/** The Constant EXISTING_USERACCOUNT_ERROR. */
	public static final String EXISTING_USERACCOUNT_ERROR = "The user account is already in use. Please enter a different user ID/email.";

	/** Operation forbidden. */
	public static final String OPERATION_FORBIDDEN_ACCESS = "You do not have permission to access this feature.";
	
	/** The Constant MESSAGE_TYPE_NOT_NULL. */
	public static final String MESSAGE_TYPE_NOT_NULL="The search type cannot be empty.";
	
	/** The Constant QUERY_FORBIDDEN_ACCESS. */
	public static final String QUERY_FORBIDDEN_ACCESS = "You do not have permission to access this query.";
	
	/** The Constant DUPLICATE_REFCONTACT_ERROR. */
	public static final String DUPLICATE_REFCONTACT_ERROR="apis.error.refcontact.identifierfileds.duplicate";
	
	/** The Constant CONNECT_ERROR. */
	public static final String CONNECTION_ERROR = " You are encountering connection error, please try again later. ";
	
	/** The Constant NOT_VALIDATE_MESSAGE. */
	public static final String NOT_VALIDATE_ADDRESS_MESSAGE = " The Contact address no need to validate. ";
	
	/** The Constant JSON_PASER_ERROR**/
	public static final String JSON_PASER_ERROR = " JSON parse failure.Please check the post data. ";
	
	/** The Constant USER_LOCKED. */
	public static final String USER_LOCKED = "signon.prompt.user.locked.message";
	
	public static final String MESSAGE_400 = "Bad Request(The request cannot be fulfilled due to bad syntax).";
	
	public static final String MESSAGE_401 = "Unauthorized(The requested resource requires authentication).";
	
	public static final String MESSAGE_403 = "Forbidden(The server refuses to fulfill the request).";
	
	public static final String MESSAGE_404 = "Not Found(The requested resource could not be found).";
	
	public static final String MESSAGE_500 = "Internal server error.";
	
	public static final String MESSAGE_OWNER_CONDITION_LOCKED = "Exist lock condition.";
	
	public static final String MESSAGE_OWNER_REFOWNER_NOT_EXIST = "Can not find the reference owner.";
	
	public static final String MESSAGE_OWNER_CREATE_SUCCESS = "Create owner success.";
	
	public static final String MESSAGE_OWNER_CREATE_FAILED = "Create owner failed.";
	
	public static final String MESSAGE_OWNER_DELETE_SUCCESS = "Delete owner success.";
	
	public static final String MESSAGE_OWNER_DELETE_FAILED = "Delete owner failed.";
	
	public static final String MESSAGE_PROFESSIONAL_REFPROFESSIONAL_NOT_EXIST = "Can not find the reference professional.";
	
	public static final String MESSAGE_PROFESSIONAL_CREATE_SUCCESS = "Create professional success.";
	
	public static final String MESSAGE_PROFESSIONAL_CREATE_FAILED = "Create professional failed.";
	
	public static final String MESSAGE_PROFESSIONAL_DELETE_SUCCESS = "Delete professional success.";
	
	public static final String MESSAGE_PROFESSIONAL_DELETE_FAILED = "Delete professional failed.";
	
	public static final String MESSAGE_PROFESSIONAL_DELETE_FAILED_PRIMARY = "Can not delete primary professional.";
	
	public static final String MESSAGE_INSPECTION_CANCEL_VALIDATE_FAILED = "The inspection is not allowed to be cancelled in current status : ";
	
	public static final String MESSAGE_INSPECTION_CANCEL_SUCCESS = "Cancel inspection success.";
	
	public static final String MESSAGE_INSPECTION_ASSIGN_SUCCESS = "Assign inspection success.";
	
	public static final String MESSAGE_RELATED_RECORD_NOT_EXIST = "Can not find the related record.";
	
	public static final String MESSAGE_RELATED_CREATE_SUCCESS = "Create record relate success.";
	
	public static final String MESSAGE_RELATED_DELETE_SUCCESS = "Delete record relate success.";
	
	public static final String MESSAGE_INSPECTION_CONDITION_CREATE_SUCCESS = "Create inspection condition success.";
	
	public static final String MESSAGE_INSPECTION_CONDITION_CREATE_FAILED = "Create inspection condition failed.";
	
	public static final String MESSAGE_INSPECTION_CONDITION_APPROVAL_CREATE_SUCCESS = "Create inspection condition approval success.";
	
	public static final String MESSAGE_INSPECTION_CONDITION_APPROVAL_CREATE_FAILED = "Create inspection condition approval failed.";
	
	public static final String MESSAGE_RECORD_CONDITION_CREATE_SUCCESS = "Create record condition success.";
	
	public static final String MESSAGE_RECORD_CONDITION_CREATE_FAILED = "Create record condition failed.";
	
	public static final String MESSAGE_RECORD_CONDITION_APPROVAL_CREATE_SUCCESS = "Create record condition approval success.";
	
	public static final String MESSAGE_RECORD_CONDITION_APPROVAL_CREATE_FAILED = "Create record condition approval failed.";
	
	public static final String MESSAGE_CONDITION_DELETE_SUCCESS = "Delete condition success.";
	
	public static final String MESSAGE_CONDITION_NOT_EXIST = "Can not find the condition id ";
	
	public static final String MESSAGE_CREATE_SUCCESS = "Create Success";
	
	public static final String MESSAGE_CREATE_FAILED = "Create failed";
	
	public static final String MESSAGE_DELETE_SUCCESS = "Delete Success";
	
	public static final String MESSAGE_RECORD_NOT_EXIST = "The record cannot be found.";
	
	public static final String MESSAGE_CONTACT_NOT_EXIST = "The contact cannot be found.";

	public static final String MESSAGE_CONTACT_ADDRESS_NULL_ERRROR = "Contact address can not be null.";

	public static final String MESSAGE_CONTACT_ADDRESS_DUPLICATE = "Contact address duplicate.";
	
	public static final String MESSAGE_CONTACT_CREATE_FAILED = "Create contact failid.";
	
	public static final String MESSAGE_CONTACT_ADDRESS_CREATE_FAILED = "Create contact address failed.";
	
	public static final String MESSAGE_CONTACT_ADDRESS_UPDATE_FAILED = "Update contact address failed.";
	
	public static final String MESSAGE_MAX_DATE_RANGE = "The maximum date range supported in this query is 90 days.";
	
	public static final String MESSAGE_START_DATE_REQUIRED = "Start date required.";
	
	public static final String MESSAGE_CRITERIA_REQUIRED = "Criteria is required.";
	
	public static final String MESSAGE_POST_BODY_NOT_MEET_URL = "The post body is not meet the url.";
	
	public static final String MESSAGE_EMSE_BEFORE_FAILED = "Some EMSE before event trigger failed.";
	
	public static final String MESSAGE_EMSE_AFTER_FAILED = "Some EMSE after event trigger failed.";
	
	public static final String MESSAGE_INSPECTION_CHECKLIST_ITEM_UPDATE_FAILED = "Update inspection checklist item failed.";
	
	public static final String MESSAGE_INSPECTION_CHECKLIST_ITEM_UPDATE_SUCCESS = "Update inspection checklist item success.";
	
	public static final String MESSAGE_UPDATE_SUCCESS= "Update Success";
	
	public static final String MESSAGE_CONDITION_NUMBER = "Condition Number";
	
	public static final String MESSAGE_DOCUMENT_NOT_EXIST = "Can not find the Document id ";
	
	public static final String MESSAGE_DATA_NOT_FOUND = "The resource cannot be found for update";
	
	public static final String MESSAGE_CANNOT_BEEN_FOUND = "cannot be found.";
	
	public static final String MESSAGE_DESABLED = "disabled";

	/** The Constant MESSAGE_CONTACT_NOT_FOUND. */
	public static final String MESSAGE_CONTACT_NOT_FOUND = "The contact has not found. Please check the contact id.";
	
	public static final String MESSAGE_RECORD_CONTACT_CREATE_FAILED = "Create record contact failed.";

	/** The Constant MESSAGE_UNAUTH_CONTACT_TYPE. */
	public static final String MESSAGE_UNAUTH_CONTACT_TYPE = "You are not authorized to update the current contact type of contact data.";

	/** The Constant MESSAGE_CONTACT_ATTRIBUTE_NOT_FOUND. */
	public static final String MESSAGE_CONTACT_ATTRIBUTE_NOT_FOUND = " SubGroup: %1$s , Field: %2$s Can not found custom form setting on request record contact.\r\n";

	/** The Constant MESSAGE_SHOPPING_CART_ITEM_NOT_FOUND. */
	public static final String MESSAGE_SHOPPING_CART_ITEM_NOT_FOUND = "The shopping cart item has not found. Please check the id.";

	/** The Constant MESSAGE_SHOPPING_CART_ITEMS_NOT_FOUND. */
	public static final String MESSAGE_SHOPPING_CART_ITEMS_NOT_FOUND = "These shopping cart items has not found. Please check these ids.";

	/** The Constant MESSAGE_SHOPPING_CART_ITEM_FOUND. */
	public static final String MESSAGE_SHOPPING_CART_ITEM_FOUND = "The shopping cart item already exist.";
	
	public static final String MESSAGE_REVIEW_STATUS_NOT_ASSOCIATED = "The document has not associated review status.";
	
	public static final String MESSAGE_DOC_STATUS_NOT_ASSOCIATED = "The document has not associated document s.";
	
	public static final String MESSAGE_DOC_COMMENT_NOT_FOUND = "The document comment is not found.";

	/** The Constant MESSAGE_SHOPPING_CART_ITEMS_FOUND. */
	public static final String MESSAGE_SHOPPING_CART_ITEMS_FOUND = "These shopping cart items already exist.";

	/** The Constant MESSAGE_RECORD_FEE_ITEMS_FOUND. */
	public static final String MESSAGE_RECORD_FEE_ITEMS_FOUND = "These record fee items has not been found. Please check the ids.";

	/** The Constant MESSAGE_LIMIT_FEE_ESTIMATE. */
	public static final String MESSAGE_LIMIT_FEE_ESTIMATE = "The record has been created, which can not be used to estimate fee.";
	
	public static final String MESSAGE_FOLDER_ID_REQUIRED = "Folder id is required.";
	
	public static final String MESSAGE_FOLDER_IDORNAME_REQUIRED = "Folder id or folder name is required.";
	
	public static final String MESSAGE_RECORD_ISSUE_PART_FAILED = "Issue part failed.";

	/** The Constant MESSAGE_PAYMENT_NOT_FOUND. */
	public static final String MESSAGE_PAYMENT_NOT_FOUND = "The payment has not found. Please check the payment id.";

	/** The Constant MESSAGE_PAYMENT_VOIDED. */
	public static final String MESSAGE_PAYMENT_VOIDED = "The payment has been voided";

	/** The Constant MESSAGE_FEE_NOT_MODIFIED. */
	public static final String MESSAGE_FEE_NOT_MODIFIED = "The fee is not 'NEW' status can not be modified";
	
	/** The Constant MESSAGE_DOCUMENT_UPDATE_FAILED. */
	public static final String MESSAGE_DOCUMENT_UPDATE_FAILED = "Document update failed";

	/** The Constant MESSAGE_FEE_NOT_FOUND. */
	public static final String MESSAGE_FEE_NOT_FOUND = "The record fee has not found.";

	/** The Constant MESSAGE_FEE_NOT_PAID. */
	public static final String MESSAGE_FEE_NOT_PAID = "The record fee has not paid.";

	/** The Constant MESSAGE_FEE_DUPLICATE_REFUND. */
	public static final String MESSAGE_FEE_DUPLICATE_REFUND = "The record fee is a duplicate refund.";

	/** The Constant MESSAGE_FEE_INVALID. */
	public static final String MESSAGE_FEE_INVALID = "The record fee is invalid.";

	/** The Constant MESSAGE_TRUST_ACCOUNT_NOT_FOUND. */
	public static final String MESSAGE_TRUST_ACCOUNT_NOT_FOUND = "The trust account has not found.";

	/** The Constant MESSAGE_PROVIDE_TRUST_ACCOUNT. */
	public static final String MESSAGE_PROVIDE_TRUST_ACCOUNT = "Please provide the trust account id.";

	/** The Constant MESSAGE_INVOICE_FEE_NOT_FOUND. */
	public static final String MESSAGE_INVOICE_FEE_NOT_FOUND = "The record fee has not found in the invoice.";

	/** The Constant MESSAGE_INVOICE_FEE_VOIDED. */
	public static final String MESSAGE_INVOICE_FEE_VOIDED = "The record fee has been voided";

	/** The Constant MESSAGE_INVOICE_NOT_FOUND. */
	public static final String MESSAGE_INVOICE_NOT_FOUND = "The invoice has not found. Please check the invoice id.";

	/** The Constant MESSAGE_FEE_NOT_VOIDED. */
	public static final String MESSAGE_FEE_NOT_VOIDED = "The record fee can not be voided, because fee status is 'NEW'.";

	/** The Constant MESSAGE_FEE_DUPLICATE_VOID. */
	public static final String MESSAGE_FEE_DUPLICATE_VOID = "The record fee has been duplicate.";

	/** The Constant MESSAGE_VOID_FEE_SUCCESSFULLY. */
	public static final String MESSAGE_VOID_FEE_SUCCESSFULLY = "Void the invoice fee successfully";
	/** The Constant NO_PERMISSION_ACCESS_RESOURCE. */
	public static final String NO_PERMISSION_ACCESS_RESOURCE = "You have not permission to access the resource.";
	
	/**The Constant MESSAGE_FIELD_REQUIRED**/
	public static final String MESSAGE_FIELD_REQUIRED = "The field %1$s is required.";
		
	/** The error message for failed on contact detail security authorization. */
	public static final String CONTACT_DETAIL_SECURITY = "Contact detail security authorization failed";

	public static final String MESSAGE_CONTACT_CONDITION_CREATE_FAILED = "Create contact condition failed.";
}

/*
 * $Log: av-env.bat,v $
 */
