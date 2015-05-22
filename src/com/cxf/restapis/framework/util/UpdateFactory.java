package com.cxf.restapis.framework.util;

import java.util.HashMap;
import java.util.Map;

import com.cxf.restapis.framework.json.service.ApiDataSourceProvider;


/**
 * <pre>
 * 
 *  Accela Automation
 *  File: UpdateFactory.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014-2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: UpdateFactory.java 72642 2009-01-01 20:01:57Z ACHIEVO\bruce.deng $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Apr 15, 2014		bruce.deng		Initial.
 *  
 * </pre>
 */
public class UpdateFactory
{
//	private static final AVLogger logger = AVLogger.getLogger(UpdateFactory.class);
	
	private static Map<String, ApiDataSourceProvider> cacheDataSourceMap = new HashMap<String, ApiDataSourceProvider>();
	
	private static Map<String, String> updateDataSourceMap = new HashMap<String, String>();
	static
	{
		updateDataSourceMap.put("com.accela.restapis.inspection.InspectionResourceV4.updateGuideSheetItems", "com.accela.restapis.json.service.impl.InspectionCheckListItemApiDataSourceImpl");
		updateDataSourceMap.put("com.accela.aa.aamain.owner.RefOwnerModel", "com.accela.restapis.json.service.impl.OwnerApiDataSourceImpl");
		updateDataSourceMap.put("com.accela.aa.aamain.people.LicenseProfessionalModel", "com.accela.restapis.json.service.impl.ProfessionalApiDataSourceImpl");
		updateDataSourceMap.put("com.accela.aa.aamain.parcel.CapParcelModel", "com.accela.restapis.json.service.impl.ParcelApiDataSourceImpl");
		updateDataSourceMap.put("com.accela.restapis.inspection.model.InspectionModel", "com.accela.restapis.json.service.impl.InspectionApiDataSourceImpl");
		updateDataSourceMap.put("com.accela.aa.inspection.inspection.InspectionConditionModel", "com.accela.restapis.json.service.impl.InspectionConditionApiDataSourceImp");
		updateDataSourceMap.put("com.accela.restapis.record.model.RecordModel", "com.accela.restapis.json.service.impl.RecordApiDataSourceImp");
		updateDataSourceMap.put("com.accela.aa.aamain.people.CapContactModel", "com.accela.restapis.json.service.impl.CapContactApiDataSourceImp");
		updateDataSourceMap.put("com.accela.aa.aamain.people.PeopleModel", "com.accela.restapis.json.service.impl.ContactApiDataSourceImp");
		
		updateDataSourceMap.put("com.accela.aa.aamain.cap.CapConditionModel", "com.accela.restapis.json.service.impl.RecordConditionApiDataSourceImp");
		updateDataSourceMap.put("com.accela.aa.aamain.cap.CapCommentModel", "com.accela.restapis.json.service.impl.RecordCommentApiDataSourceImp");
		updateDataSourceMap.put("com.accela.aa.aamain.address.AddressModel", "com.accela.restapis.json.service.impl.AddressApiDataSourceImp");
		updateDataSourceMap.put("com.accela.aa.workflow.workflow.TaskItemModel", "com.accela.restapis.json.service.impl.WorkflowTaskApiDataSourceImp");
		updateDataSourceMap.put("com.accela.orm.model.address.ContactAddressModel", "com.accela.restapis.json.service.impl.ContactAddressApiDataSourceImp");
		
		updateDataSourceMap.put("com.accela.aa.finance.timeAccount.TimeLogModel", "com.accela.restapis.json.service.impl.TimeLogApiDataSourceImp");
		updateDataSourceMap.put("com.accela.aa.aamain.cap.ShoppingCartItemModel", "com.accela.restapis.json.service.impl.ShoppingCartItemApiDataSourceImp");
	
		updateDataSourceMap.put("com.accela.aa.aamain.servicerequest.ActivityModel", "com.accela.restapis.json.service.impl.ActivityApiDataSourceImp");
		updateDataSourceMap.put("com.accela.restapis.finance.FeeItemBaseV4", "com.accela.restapis.json.service.impl.FeeItemBaseV4ApiDataSourceImp");
		updateDataSourceMap.put("com.accela.aa.ads.ads.DocumentModel", "com.accela.restapis.json.service.impl.DocumentApiDataSourceImp");
	}
	
	
	public static ApiDataSourceProvider createInstance(String className)
	{
		ApiDataSourceProvider	apiDataSourceProvider = cacheDataSourceMap.get(className);
		String implClass = updateDataSourceMap.get(className);
		try
		{
			if(implClass != null && apiDataSourceProvider == null)
			{
				Class<?> classObj = Class.forName(implClass);
				apiDataSourceProvider = (ApiDataSourceProvider) classObj.newInstance();
				cacheDataSourceMap.put(className, apiDataSourceProvider);
			}
		}
		catch (Exception e)
		{
//			logger.debug(e.getMessage());
		}
		
		return apiDataSourceProvider;
	}

}

/*
*$Log: av-env.bat,v $
*/
