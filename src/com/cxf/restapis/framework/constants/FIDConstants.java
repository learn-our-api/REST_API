package com.cxf.restapis.framework.constants;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: FIDConstants.java
 * 
 * Accela, Inc.
 * Copyright (C): 2013-2015
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: FIDConstants.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * Jun 6, 2013		dylan.liang		Initial.
 * 
 * </pre>
 */
public class FIDConstants
{

	/** The Constant RECORD_CREATE. */
	public static final String RECORD_CREATE = "8034F";

	/** The Constant RECORD_VIEW indicates if user has permission to view record detail or summary. */
	public static final String RECORD_VIEW = "8411R|8395R|8209R";

	/** The Constant RECORD_EDIT indicates if user has permission to edit record . */
	public static final String RECORD_EDIT = "8209F";

	/** The Constant RECORD_DELETE indicates if user has permission to delete records. */
	public static final String RECORD_DELETE = "8410F";

	/** The Constant RECORD_FEE_VIEW indicates if user has permission to view fees associated with record. */
	public static final String RECORD_FEE_VIEW = "8104R";
	
	/** The Constant RECORD_FEE_VIEW indicates if user has permission to operate fees associated with record. */
	public static final String RECORD_FEE_OPERATE = "8104F";

	/** The Constant RECORD_ADDRESS_VIEW indicates if user has permission to view addresses associated with record. */
	public static final String RECORD_ADDRESS_VIEW = "8028R";

	/** The Constant RECORD_PARCEL_VIEW indicates if user has permission to view parcels associated with record. */
	public static final String RECORD_PARCEL_VIEW = "8029R";
	
	/** The Constant RECORD_PARCEL_VIEW indicates if user has permission to operate parcels associated with record. */
	public static final String RECORD_PARCEL_OPERATE = "8029F";

	/** The Constant RECORD_OWNER_VIEW indicates if user has permission to view owners associated with record. */
	public static final String RECORD_OWNER_VIEW = "8030R";
	
	/** The Constant RECORD_OWNER_OPERATE indicates if user has permission to edit owners associated with record. */
	public static final String RECORD_OWNER_OPERATE = "8030F";
	
	/** The Constant RECORD_RELATED_DELETE indicates if user has permission to delete related record associated with record. */
	public static final String RECORD_RELATED_DELETE = "8460F";
	
	/** The Constant RECORD_RELATED_CREATE indicates if user has permission to create related record associated with record. */
	public static final String RECORD_RELATED_CREATE = "8236F";
	
	/** The Constant RECORD_RELATED_VIEW indicates if user has permission to view related record associated with record. */
	public static final String RECORD_RELATED_VIEW = "8154R";
	
	/** The Constant CHECK_REFERENCE_OBJECT_CONDITION indicates if user has permission to check reference object condition. */
	public static final String CHECK_REFERENCE_OBJECT_CONDITION = "8266";
	
	/** The Constant RECORD_OWNER_OPERATE indicates if user has permission to edit owners associated with record. */
	public static final String REFERENCE_OWNER_VIEW = "0089R";

	/** The Constant RECORD_CONTACT_VIEW indicates if user has permission to view contacts associated with record. */
	public static final String RECORD_CONTACT_VIEW = "8031R";
	
	/** The Constant RECORD_CONTACT_VIEW indicates if user has permission to edit contacts associated with record. */
	public static final String RECORD_CONTACT_OPERATE = "8031F";

	/**
	 * The Constant RECORD_PROFESSIONAL_VIEW indicates if user has permission to view professionals associated with
	 * record.
	 */
	public static final String RECORD_PROFESSIONAL_VIEW = "8032R";
	
	/**
	 * The Constant RECORD_PROFESSIONAL_CREATE_UPDATE_DEL indicates if user has permission to CUD professionals 
	 * associated with record.
	 */
	public static final String RECORD_PROFESSIONAL_CREATE_UPDATE_DEL = "8032F";
	
	/**
	 * The Constant REFERENCE_PROFESSIONAL_VIEW indicates if user has permission to view reference professionals 
	 */
	public static final String REFERENCE_PROFESSIONAL_VIEW = "0008R";
	
	/**
	 * The Constant REFERENCE_PROFESSIONAL_CREATE_UPDATE_DEL indicates if user has permission to CUD reference professionals 
	 */
	public static final String REFERENCE_PROFESSIONAL_CREATE_UPDATE_DEL = "0008F";

	/** The Constant DOCUMENT_VIEW 8214-View EDMS Attachment List - 6.1.1. */
	public static final String DOCUMENT_LIST_VIEW = "8214R";

	/** The Constant DOCUMENT_UPLOAD 8212-Upload EDMS Attachment - 6.1.1. */
	public static final String DOCUMENT_UPLOAD = "8212F";

	/** The Constant RECORD_DOCUMENT_DOWNLOAD 8213-Download EDMS Attachment - 6.1.1. */
	public static final String DOCUMENT_DOWNLOAD = "8213F";

	/** The Constant DOCUMENT_DELETE 8240-Delete EDMS Attachment - 6.1.1. */
	public static final String DOCUMENT_DELETE = "8240F";
	
	/** The Constant CONDITION_LIST_VIEW. */
	public static final String CONDITION_LIST_VIEW = "8020R";
	
	/** The Constant INSPECTION_CONDITION_APPROVAL_LIST_VIEW. */
	public static final String CONDITION_APPROVAL_LIST_VIEW = "8480R";
	
	/** The Constant INSPECTION_CONDITION_APPROVAL_CREATE_UPDATE_DEL. */
	public static final String CONDITION_APPROVAL_CREATE_UPDATE_DEL = "8480F";
	
	/** The Constant CONDITION_CREATE_UPDATE_DEL. */
	public static final String CONDITION_CREATE_UPDATE_DEL = "8020F";
	
	/** The department*/
	public static final String DEPARTMENT = "8138R";
	
	/** The Constant RECORD_INSPECTION_VIEW. */
	public final static String RECORD_INSPECTION_VIEW = "8249R";

	/** The Constant INSPECTION_SCHEDULING. */
	public final static String INSPECTION_SCHEDULING = "8143R";
	
	/** The Constant INSPECTION_SCHEDULED. */
	public final static String INSPECTION_SCHEDULED = "8143";
	
	/** The Constant APPLICATION_APP_SPEC_INFO. */
	public final static String APPLICATION_APP_SPEC_INFO = "8073R";
	
	/** The Constant APPLICATION_APP_SPEC_INFO. */
	public final static String APPLICATION_APP_SPEC_INFO_FULL = "8073F";
	/**
	 * The constant record ASIT readonly
	 */
	public final static String APPLICATION_APP_SPEC_INFO_TABLE = "8270R";
	/**
	 * The constant record ASIT full
	 */
	public final static String APPLICATION_APP_SPEC_INFO_TABLE_FULL = "8270F";
	/**The constant CONDITION_GROUP_FID_READ_ONLY */
	public static final String CONDITION_GROUP_FID_READ_ONLY = "0275R";
	
	/** The Constant INSPECTION_UPDATE. */
	public final static String INSPECTION_UPDATE = "8144F";
	
	/** The Constant INSPECTION_UPDATE_FULL. */
	public final static String INSPECTION_RESULT_FULL = "8145F";
	
	/** The Constant INSPECTION_UPDATE. */
	public final static String INSPECTION_RESULT = "8145";
	
	/** The Constant INSPECTION_UPDATE. */
	public final static String INSPECTION_ASSIGN = "8147F";
	
	/** The Constant APPLICATION_STATUS_SECURITY. */
	public final static String APPLICATION_STATUS_SECURITY = "8335";
	
	/** The Constant TIME_ACCOUNTING_SUPERVISOR. */
	public final static String TIME_ACCOUNTING_SUPERVISOR ="8376";
	
	/** The Constant TIME_ACCOUNTING_ADMINISTRATOR. */
	public final static String TIME_ACCOUNTING_ADMINISTRATOR="8375";
	

	/** The Constant INSPECTION_HISTORY. */
	public final static String INSPECTION_HISTORY = "8442";
	
	/** The Constant TIMEACCOUNTINGPROFILE_VIEW. */
	public final static String TIMEACCOUNTINGPROFILE_VIEW = "0265";
	
	/** The Constant INSPECTION_RELATIONS_FULL. */
	public final static String INSPECTION_RELATIONS_FULL = "8467F";
	
	/** The Constant INSPECTION_RELATIONS_READ_ONLY. */
	public final static String INSPECTION_RELATIONS_READ_ONLY = "8467R";
	
	/** The Constant DELETE_CONDITION_OF_APPROVAL. */
	public final static String DELETE_CONDITION_OF_APPROVAL = "8481F";
	
	/** The Constant GUIDE_SHEET_VIEW. */
	public final static String GUIDE_SHEET_VIEW = "8149F";
	
	/** The Constant WORKFLOWTASKITEMS_READ_ONLY. */
	public final static String WORKFLOWTASKITEMS_READ_ONLY = "8132R";
	
	/** The Constant INSPECTION_TIMEACCOUNTING_VIEW. */
	public final static String INSPECTION_TIME_ACCOUNTING_VIEW = "8462";

	/** The Constant DAILY_TIME_ACCOUNTING_VIEW. */
	public final static String DAILY_TIME_ACCOUNTING_VIEW = "8369";
	
	/** The Constant CONDITION_HISTORY_VIEW. */
	public final static String CONDITION_HISTORY_VIEW = "8272R";
	
	/** The Constant CHECKLISTSETTING_VIEW. */
	public final static String CHECKLISTSETTING_VIEW = "0124R";
	
	/** The Constant REF PARCEL_VIEW. */
	public final static String REF_PARCEL_VIEW = "0005R";
	
	/** The Constant REF_ADDRESS_VIEW. */
	public final static String REF_ADDRESS_VIEW = "0006R";
	
	/** The Constant RECORD_ADDRESS_FULL. */
	public final static String RECORD_ADDRESS_FULL = "8028F";
	
	/** The Constant RECORD_COST_VIEW . */
	public static final String RECORD_COST_VIEW = "8191R";
	
	/** The Constant RECORD_COST_OPERATE . */
	public static final String RECORD_COST_OPERATE = "8191F";
	
	/** The Constant RECORD_ADD_EDIT. */
	public final static String RECORD_ADDITIONAL_FULL = "8033F";
	
	/** The Constant RECORD_ADD_EDIT. */
	public final static String RECORD_ADDITIONAL_VIEW = "8033R";
	
	/** The Constant RECORD_ACTIVITY_FULL */
	public final static String RECORD_ACTIVITY_FULL = "8014R";

	/** The Constant RECORD_COMMENT_VIEW. */
	public final static String RECORD_COMMENT_VIEW = "8110R";
	
	/** The Constant RECORD_COMMENT_CREATE. */
	public final static String RECORD_COMMENT_CREATE = "8110F";
	
	/** The Constant RECORD_COMMENT_EDIT. */
	public final static String RECORD_COMMENT_EDIT = "8340F";
	
	/** The Constant WORKFLOWTASKITEMS_FULL. */
	public final static String WORKFLOWTASKITEMS_FULL = "8132F"; 
	
	/** The Constant REF_CONTACT_VIEW. */
	public final static String REF_CONTACT_VIEW = "0084R";
	
	/** The Constant REF_CONTACT_CREATE. */
	public final static String REF_CONTACT_OPERATE= "0084F";
	
	/** The Constant WORKFLOW_HISTORY. */
	public final static String WORKFLOW_HISTORY = "8135";
	
	/** The Constant 0349-Standard Choices. */
	public final static String STANDARD_CHOICES = "0349F";

	/** The Constant PART_SEARCH. */
	public final static String PART_SEARCH = "8273R|8192R";

	/** The Constant COST_SEARCH. */
	public final static String COST_SEARCH = "0161R|8191R";

	/** The Constant APPLY_TRANSACTION. */
	public final static String APPLY_PAYMENT = "8108F|8436F";
	
	/** The Constant CASHIER_SESSION. */
	public final static String CASHIER_SESSION = "8251";
	
	/** The Constant TIMEACCOUNTING_VIEW. */
	public final static String TIMEACCOUNTING_VIEW = "8369R";
	
	/** The Constant TIMEACCOUNTING_FULL. */
	public final static String TIMEACCOUNTING_FULL = "8369F";
	
	/** The Constant RECORD_PART_OPERATE . */
	public static final String RECORD_PART_OPERATE = "8192F";
	
	/** The Constant RECORD_PART_VIEW  .*/
	public static final String RECORD_PART_VIEW = "8192R";
	
		/** The Constant RECORD_FEE_VOID_INVOICED_OPERATE , Void Invoiced But Unpaid Fee */
	public static final String RECORD_FEE_VOID_INVOICED_OPERATE = "8333F";
	
	/** The Constant RECORD_FEE_VOID_INVOICED_VIEW , Void Invoiced But Unpaid Fee .*/
	public static final String RECORD_FEE_VOID_INVOICED_VIEW = "8333R";
	
	/** The Constant RECORD_FEE_VOID_PAID_OPERATE , Void Paid Fee */
	public static final String RECORD_FEE_VOID_PAID_OPERATE = "8348F";
	
	/** The Constant RECORD_FEE_VOID_PAID_VIEW , Void Paid Fee .*/
	public static final String RECORD_FEE_VOID_PAID_VIEW = "8348R";
	
	/** The Constant RECORD_PAYMENT_VOID_OPERATE , Void Payment */
	public static final String PAYMENT_HISTORY_OPERATE = "8394F";
	
	/** The Constant RECORD_PAYMENT_VOID_VIEW , Void Payment .*/
	public static final String PAYMENT_HISTORY_VIEW = "8394R";
	
	/** The Constant RECORD_PAYMENT_VOID_OPERATE , Void Payment */
	public static final String RECORD_PAYMENT_VOID_OPERATE = "8366F";
	
	public static final String RECORD_PAYMENT_LIST_VIEW = "8108R|8435R|8436R|8437R|8438R|8439R|8440R";
	
	/** The Constant RECORD_PAYMENT_VOID_VIEW , Void Payment .*/
	public static final String RECORD_PAYMENT_VOID_VIEW = "8366R";

	/** The Constant GET_PAYMENT. */
	public static final String GET_PAYMENT = "8108R|8435R|8436R|8437R|8438R|8439R|8440R";

	/** The Constant PAY_PAYMENT. */
	public static final String PAY_PAYMENT = "8108F|8435F|8445F";

	/** The Constant VOID_PAYMENT. */
	public static final String VOID_PAYMENT = "8438F|8414F";

	/** The Constant REFUND_PAYMENT. */
	public static final String REFUND_PAYMENT = "8108F|8437F|8415F";

	/** The Constant VOID_INVOICE. */
	public static final String VOID_INVOICE = "8333F|8348F|8416F";
	
	/** The Constant ASSET_PART_F. */
	public static final String ASSET_PART_F = "8402F";
	
	/** The Constant ASSET_PART_R. */
	public static final String ASSET_PART_R = "8402R";

	/** The Constant REF_CONTACT_CONDITION_VIEW. */
	public final static String REF_CONTACT_CONDITION_HISTORY  = "0317";
		
	/** The Constant REF_CONTACT_CONDITION_OPERATE. */
	public final static String REF_CONTACT_CONDITION_OPERATE = "0316F";

	/** The Constant CONTACT_CONDITION_VIEW. */
	public static final String CONTACT_CONDITION_VIEW = "0316R";
	
	public static final String CITIZEN_ADMIN_VIEW = "8223R";
	
	public static final String CITIZEN_ADMIN_SUPERVISOR_FULL = "8224F";
	
	/**The Constant RECORD_TRUST_ACCOUNT_VIEW.*/
	public static final String RECORD_TRUST_ACCOUNT_VIEW = "0190R";

}

/*
 * $Log: av-env.bat,v $
 */
