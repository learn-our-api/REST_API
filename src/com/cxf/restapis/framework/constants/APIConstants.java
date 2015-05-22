package com.cxf.restapis.framework.constants;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: Constants.java
 * 
 * Accela, Inc.
 * Copyright (C): 2012-2014
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: Constants.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * May 8, 2012		evan.cai		Initial.
 * 
 * </pre>
 */
public class APIConstants
{
	/** The Constant DEFAULT_OFFSET for Search. */
	public static final int DEFAULT_OFFSET = 0;

	/** The Constant DEFAULT_LIMIT for Search. */
	public static final int DEFAULT_LIMIT = 25;

	/** The Constant MAX_LIMIT for Search. */
	public static final int MAX_LIMIT = 1000;
	
	/** The Constant MAX_LIMIT for Search. */
	public static final int DEFAULT_EXPIRATION_DAY = 30;

	/** TRUE. */
	public static final String TRUE = "true";

	/** Delete document button name. */
	public static final String DELETE_DOCUMENT_BUTTON_NAME = "DeleteDocument";

	/** default document role. */
	public static final String DEFAULT_DOCUMENT_ROLE = "0111100000";
	
	/** name of Json Schema within upload document form */
	public static final String FILE_INFO = "fileInfo";
	
	/** The Constant MASKS. */
	public static final String MASKS = "MASKS";

	/** The Constant SSN. */
	public static final String SSN = "SSN";
	
	/** The Constant ADMINISTRATOR. */
	public static final String ADMINISTRATOR ="Administrator";

	/** The Constant SUPERVISOR. */
	public static final String SUPERVISOR ="Supervisor";
	
	/** The Constant CONTACT_DEFAULT_SSN_MASK. */
	public final static String CONTACT_DEFAULT_SSN_MASK = "###-##-####";
	
	/** The Constant INSPECTION. */
	public final static String INSPECTION ="INSPECTION";
	
	/** The Constant CONTACT. */
	public final static String CONTACT ="CONTACT";
	
	/** The Constant ACTIVE. */
	public final static String ACTIVE ="A";
	
	/** The Constant FEDERAL_EMPLOYER_NUMBER. */
	public final static String FEDERAL_EMPLOYER_NUMBER ="FEIN";
	
	/**
	 * The Enum Applicability of API.
	 */
	public enum Applicability
	{
		BOTH, AGENCY, CITIZEN;
	}
	
	/** The Constant MULTIPLEOBJECT_CLASSNAME. */
	public final static String MULTIPLEOBJECT_CLASSNAME ="com.accela.restapis.framework.model.MultipleObjectResultModel";
	
	/** The Constant MULTIPLEOBJECT_MODELNAME. */
	public final static String MULTIPLEOBJECT_MODELNAME ="multipleObjectResultModel";
	
	/** The Constant MULTIPLEOBJECT_IDNAME. */
	public final static String MULTIPLEOBJECT_IDNAME ="id";
}

/*
 * $Log: av-env.bat,v $
 */