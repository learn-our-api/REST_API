package com.cxf.restapis.docsgenerator;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 * Accela Automation
 * File: ResourceName.java
 * 
 * Accela, Inc.
 * Copyright (C): 2014-2015
 * 
 * Description:
 * TODO
 * 
 * Notes:
 * $Id: ResourceName.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $
 * 
 * Revision History
 * &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 * 2012-10-20		dylan.liang		Initial.
 * 
 * </pre>
 */
public enum ResourceName
{

	/** The authentication. */
	authentication("Operations about authentication"),

	/** The citizen. */
	citizen("Operations about citizen user"),

	/** The transaction. */
	transaction("Operations about payment transactions"),

	/** The record. */
	record("Operations about records and associated entity"),

	/** The route. */
	route("Operations about route and associated entity"),

	/** The batch request. */
	batch("Batch Request Operations about multiple HTTP requests for every based resource APIs"),

	/** The report. */
	report("Operations about report"),

	/** The search. */
	search("Operations about various search APIs"),

	/** The contact. */
	contact("Operations about contacts and associated entity"),
	
	/** The record payment. */
	recordPayment("Operations record payment for specific resource"),

	/** The payment. */
	payment("Operations payment for specific resource"),
	
	/** The payment. */
	paymentProcessing("Operations payment for specific resource"),

	/** The region. */
	region("Operations about regional settings information"),

	/** The dictionary. */
	dictionary("Operations about dictionary information"),

	/** The generic view. */
	genericView("Operations about various view's information"),

	/** The address. */
	address("Operation about address information"),

	/** The parcel. */
	parcel("Operation about parcel associated with record information"),

	/** The owner. */
	owner("Operation about owner associated with record information"),
	
	/** The owner. */
	cost("Operation about cost associated with record information"),
	
	/** The part transaction.*/
	partTransaction("Operation about part transaction associated with record information"),
	
	/** The reference owner. */
	refOwner("Operation about reference owner associated"),
	
	/** The related. */
	related("Operation about related associated with record information"),
	
	/** The owner search. */
	ownerSearch("Operation about owner search"),
	
	/** The owner search. */
	refProfessional("Operation about reference professional information"),

	/** The professional. */
	professional("Operation about professional information"),

	/** The fee. */
	fee("Operation about fee associated with record information"),

	/** The document. */
	document("Operation about documents"),
	
	/** The system record. */
	recordType("Operation about reference record information"),

	/** The dictionary. */
	merchantAccount("Operations about merchant account information"),
	
	/** The inspection. */
	inspection("Operations about inspection information"),
	
	/** The inspection history. */
	inspectionHistory("Operations about inspection history information"),
	
	/** The guidesheet. */
	checklist("Operations about reference checklist information"),
	
	/** The guidesheetgroup. */
	checklistGroup("Operations about checklist group information"),	
	
	/** The settings. */
	settings("Operations about system config information"),
	
	/** The inspection condition. */
	inspectionConditions("Operations about inspection Conditions information"),

	/** The condition of approval. */
	conditionApproval("Operations about inspection condition approval"),
	
	/** The inspection condition approval. */
	capConditionApproval("Operations about inspection condition approval"),
	
	/** THe inspector. */
	inspector("Operations about inspector"),
	
	/** THe user. */
	user("Operations about user"),
	
	/** The record asi. */
	recordASI("Operations about record asi"),
	
	/** The standard condition. */
	standardCondition("Operations about standard condition"),
	
	/** The time accounting. */
	timeAccounting("Operations about time accounting"),

	/** The task. */
	task("Operations about task"),
	
	/** The time accounting profile. */
	timeAccountingProfile("Operations about timeAccountingProfile"),
	
	/** The record condition. */
	recordConditions("Operations about record Conditions information"),
	
	/** The inspection comment. */
	comments("Operations about inspection comment"),
	
	/** The inspection relations. */
	inspectionRelations("Operations about inspectionRelations"),
	
	/**The checklist custom fields and custom tables*/
	checklistCustomFields("Operations about checklist custom fields and custom tables"),
	
	/** The workflow task items. */
	workflowTasks("Operations about workflowTasks"),
	
	/** The inspection relations. */
	inspectionTimeAccounting("Operations about inspection TimeAccounting"),
	
	/** The inspection condition history. */
	inspectionConditionHistory("Operations about inspection Condition History"),
	
	/** The checklistsetting. */
	checklistsetting("Operations about Checklist Setting"),
	

	/** The checklistsetting. */
	refparcel("Operations about reference parcel"),
	
	/** The ref address. */
	refAddress("Operations about reference Address"),
	
	/** The record additional. */
	recordAdditional("Operations about record additional"),
	
	/** The record comments. */
	recordComments("Operations about record comments"),
	/** The my workflow task items. */
	myWorkflowTasks("Operations about my workflow tasks"),

	/** The reference contact search. */
	refContactSearch("Operations about reference contact search"),
	
	/** The Announcements. */
	announcement("Operations about announcement"),
	
	/** The activity. */
	activity("Operations about activity"),
	
	/** The reference address. */
	refContact("Operations about reference contact"),
	
	/** The reference contact address */
	refContactAddress("Operations about reference contact address"),
	
	/** The reference contact condition. */
	contactCondition("Operations about reference contact condition"),	

	/** The shopping cart. */
	shoppingCart("Operations about shopping cart"),

	diagnosis("Operations about diagnosis"),
	
	serverProperties("Operations about Server Properties"),
	
	mileage("Operations about Mileage"),

	/** The invoice. */
	invoice("Operations invoice for specific resource"),
	
	asset("Operations about specified asset"),

	document3rdparty("Operations about document 3rdparty"),
	
	userAccount("Operations about user account"),
	
	/** The reference trust account. */
	trustAccount("Operations about trust account"),
	
	dog("Operations about dog"),
	
	/** The reference record trust account. */
	recordTrustAccount("Operations about record trust account");
	
	
	/**
	 * Instantiates a new resource name.
	 * 
	 * @param desc the desc
	 */
	ResourceName(String desc)
	{
		this.description = desc;
	}

	/** The description. */
	private String description;

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */

	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
}

/*
 * $Log: av-env.bat,v $
 */
