package com.cxf.restapis.framework.util;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: Constants.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: Constants.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Oct 9, 2013		bruce.deng		Initial.
 *  
 * </pre>
 */
public class Constants
{
	public static final String LANGUAGE_EN_US = "en_US";
	/** condition group standard choice*/
	public static final String CONDITION_GROUP = "CONDITION GROUP";
	
	/** condition type standard choice*/
	public static final String CONDITION_TYPE = "CONDITION TYPE";
	
	/** condition status standard choice*/
	public static final String CONDITION_STATUS = "CONDITION STATUS";
	
	/** condition priorities standard choice*/
	public static final String CONDITION_PRIORITIES = "CONDITION_PRIORITIES";
	
	/** Priority standard choice*/
	public static final String RECORD_PRIORITY = "PRIORITY"; 
	
	/** address state standard choice*/
	public static final String ADDRESS_STATES = "STATES"; 
	
	/** cost unit types standard choice*/
	public static final String COST_UNIT_TYPES = "COST_UNIT_TYPE"; 
	
	/** cost account standard choice*/
	public static final String COST_ACCOUNT = "COST_ACCOUNT"; 
	
	/** cost factor standard choice*/
	public static final String COST_FACTOR = "COST_FACTOR";
	
	/** cost type standard choice*/
	public static final String COST_TYPE = "COST_TYPE";
	
	/** address country standard choice*/
//	public static final String ADDRESS_COUNTRY = "COUNTRY OF ORIGIN"; 
	public static final String ADDRESS_COUNTRY = "COUNTRY"; 
	
	/** address street type  standard choice */
	public static final String STREET_TYPE  = "STREET SUFFIXES";
	
	/** address street direction standard choice */
	public static final String STREET_DIRECTION  = "STREET DIRECTIONS";
	
	/** address street fraction value. */
	public static final String STREET_FRACTIONS = "STREET FRACTIONS";
	
	/** address street fraction value. */
	public static final String STREET_UNITTYPES = "UNIT TYPES";
	
	/** license professional type fraction value. */
	public static final String LICENSED_PROFESSIONAL_TYPE = "LICENSED PROFESSIONAL TYPE";
	
	/** license board type fraction value. */
	public static final String LICENSED_BOARD = "Licensing Board";
	
	/** license professional salutation type fraction value. */
	public static final String PROFESSIONAL_SALUTATION = "SALUTATION";
	
	/** status of condition */
	public static final String APPLIED = "Applied";
	
	/** condition of approvals standard choice*/
	public static final String CONDITIONS_OF_APPROVALS = "CONDITIONS_OF_APPROVALS";
	
	/** Document Review Status Group */
	public static final String DOCUMENT_REVIEW_STATUS = "DocReviewStatus";
	
	/** Document Status Group */
	public static final String DOCUMENT_STATUS = "DocStatus";
	
	/** document standard choice */
	public static final String BIZDOMAIN_COL_EDMS = "EDMS";
	
	public static final String FILE_REVIEW_STATUS="FILE_REVIEW_STATUS";

	/** The Constant PARENT. */
	public static final String PARENT = "Parent";
	
	/** The Constant CHILD. */
	public static final String CHILD = "Child";

	public static final char SPLIT_FOR_STATUS_TYPE = 2;
	
	public static final String CLONE_REFERENCE_CONDITION = "CLONE_REFERENCE_CONDITION";
	
	public static final String ADD_ACTION = "Add";
	
	public static final String EDIT_ACTION = "Edit";
	
	public static final String DELETE_ACTION = "Delete";
	
	public static final String UNLOCK_ACTION = "Unlock";
	
	public static final String OVERRIDE_ACTION = "Override";
	
	public static final String STATUS_TYPE_APPLIED_STRING = "Applied";
	
	public static final String STATUS_TYPE_NOTAPPLIED_STRING = "Not Applied";
	
	public static final String key_condition_status_applied = "key.condition.status.applied";
	
	public static final String key_condition_status_notapplied = "key.condition.status.notapplied";
	
	public static final String STATUS_TYPE_MET_STRING = "Met";
	
	public static final String COMMON_NO = "No";
	
	public static final String RECORD_COMMENT_TYPE = "APP LEVEL COMMENT";
	
	/** contact salutation type standard choice */
	public static final String PEOPLE_SALUTATION = "SALUTATION";
	
	/** contact gender type standard choice */
	public static final String PEOPLE_GENDER = "GENDER";
	
	/** contact race type standard choice */
	public static final String STD_RACE = "RACE";
	
	/** contact type standard choice */
	public static final String CONTACT_TYPE = "CONTACT TYPE";
	
	public static final String CONTACT_BIRTHCITY = "CITY";
	
	/** contact state type standard choice  */
	public static final String STD_STATES = "STATES";
	
	/** contact country type standard choice: COUNTRY. */
	public static final String STD_COUNTRY = "COUNTRY";
	
	/** contact preferred type standard choice */
	public static final String CONTACT_PREFERRED_CHANNEL = "CONTACT_PREFERRED_CHANNEL";
	
	/** contact relationship type standard choice  */
	public static final String CONTACT_RELATIONSHIP = "CONTACT RELATIONSHIP";
	
	public static final String REQURED = "requred";
	
	public static final String ISREFERENCE = "isReference";
	
	public static final String ELEMENTD_DESCRIPTION = "elementDesc";
	
	public static final String CONTACTS = "contacts";
	
	/** The Constant CONSTRUCTION_AUDIT_HISTORY_GUIDETYPE. */
	public static final String CONSTRUCTION_AUDIT_HISTORY_GUIDETYPE  = "AUDIT_HISTORY_GUIDETYPE";
	
	public static final String EXPIRATION_STATUS = "EXPIRATION_STATUS"; 
	
	public static final String EXPIRATION_ACTIVE_STATUS = "EXPIRATION_ACTIVE_STATUS";
	
	public static final String EXPIRATION_TASK = "EXPIRATION_TASK";
	
	public static final String EXP_LIC_ISSUE_TASK = "EXP_LIC_ISSUE_TASK";
	
	public static final String EXP_LIC_ISSUE_NOT_ISS_STATUS = "EXP_LIC_ISSUE_NOT_ISS_STATUS";
	
	/** Parts **/
	public static final String PART_UNIT_OF_MEASURE = "PART_UNIT_OF_MEASURE";

	public static final String PART_STATUS = "PART_STATUS";
	
	public static final String PART_TYPE = "PART_TYPE";
	
	public static final String PART_BUDGET_ACCOUNT = "BUDGET_ACCOUNT";
	
	public static final String PART_CALCULATE_TYPE = "PART_CALCULATE_TYPE";
	
	/**time accounting locked status*/
	public static final String LOCK_STATUS_LOCKED = "L";
	
	/**time accounting unlocked status */
	public static final String LOCK_STATUS_UNLOCKED = "U";
	
	/**document view/down load role.*/
	public static final String DOCUMENT_VIEW_ROLE = "DOCUMENT_VIEW_ROLE";
	/** document delete role.*/
	public static final String DOCUMENT_DELETE_ROLE = "DOCUMENT_DELETE_ROLE";
	/**document view title role */
	public static final String DOCUMENT_VIEWTITLE_ROLE = "DOCUMENT_VIEWTITLE_ROLE";
	
	public static final Object DECOLLATOR = "::"; 
	
    /**  Payment refund **/
	public static final String REFUND_PAYMENT_REASON = "REFUND_PAYMENT_REASON";
	
	public static final String PAYMENT_RECEIVED_TYPE = "PAYMENT_RECEIVED_TYPE";
	
	public static final String PAYMENT_PROCESSING_METHOD = "PAYMENT_PROCESSING_METHOD";
	
	/** Standard choice VOID_INVOICE_REASEON */
	public static final String VOID_INVOICE_REASON = "VOID_INVOICE_REASON";
	
	/**  Standard choice VOID_PAYMENT_REASON  */
	public static final String VOID_PAYMENT_REASON = "VOID_PAYMENT_REASON";
	
	public static final String RECORD = "record";
	
	public static final String INSPECTION = "inspection";
	
	public static final String CONVERTOR_REQUESTS = "requests";
	
	public static final String CONVERTOR_RESPONSES = "responses";
	
	/** The Constant INACTIVE_SIMPLE. */
	public static final String INACTIVE_SIMPLE = "I";
	
	/** The Constant ACTIVE. */
	public static final String ACTIVE = "ACTIVE";
	
	/** The Constant PUBLICUSER. */
	public static final String PUBLICUSER = "PUBLICUSER";
	
	/** The Constant AC360AGENCY. */
	public static final String AC360AGENCY = "AC360Agency";
	
	/** The Constant AC360PUBLIC. */
	public static final String AC360PUBLIC = "AC360Public";
	
	/** The Constant N. */
	public static final String N = "N";
	
	/** The Constant Y. */
	public static final String Y = "Y";
}

/*
*$Log: av-env.bat,v $
*/
