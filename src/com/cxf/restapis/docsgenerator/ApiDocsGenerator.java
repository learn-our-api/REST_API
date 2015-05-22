package com.cxf.restapis.docsgenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cxf.restapis.docsgenerator.annotations.Resource;
import com.cxf.restapis.docsgenerator.model.Documentation;
import com.cxf.restapis.framework.util.ValidationUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wordnik.swagger.annotations.Api;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: APIDocsGenerator.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2015
 * 
 *  Description:
 *  This class be implemented for REST API documentation json schema generation.
 * 
 *  Notes:
 * 	$Id: APIDocsGenerator.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-17		dylan.liang		Initial.
 * 
 * </pre>
 */
public class ApiDocsGenerator
{

	/** The api version. */
	private static String apiVersion = "1.0";

	/** The swagger version. */
	private static String swaggerVersion = "1.1";

	/** The base path. */
	private static String basePath = null;// http://10.50.70.126:3080/apis

	/** The help base path. */
	private static String helpBasePath = null;// http://10.50.70.126:3080/apis-help

	/** The auto generated docs services for global. */
	private static List<String> globalServices = new ArrayList<String>();
	
	/** The auto generated docs services for agency. */
	private static List<String> agencyServices = new ArrayList<String>();

	/** The auto generated docs services for citizen. */
	private static List<String> citizenServices = new ArrayList<String>();

	static
	{
		/** Initialize Global Services */
		globalServices.add("com.accela.restapis.batch.BatchWebService");
		globalServices.add("com.accela.restapis.search.GlobalSearchWebService");
		globalServices.add("com.accela.restapis.search.GenericQueryWebService");
//		globalServices.add("com.accela.restapis.report.ReportResourceV1");
		globalServices.add("com.accela.restapis.v1.finance.payment.OnlinePaymentWebService");
//		globalServices.add("com.accela.restapis.search.ProfessionalSearchV1");
//		globalServices.add("com.accela.restapis.search.RecordSearchV1");
		globalServices.add("com.accela.restapis.system.RegionalSettingsWebService");
		globalServices.add("com.accela.restapis.system.DictionaryWebService");
		globalServices.add("com.accela.restapis.contact.ContactWebService"); 
		globalServices.add("com.accela.restapis.search.ContactSearchWebService");
		globalServices.add("com.accela.restapis.address.ContactAddressWebService");
//		globalServices.add("com.accela.restapis.search.InspectionSearchV1");
//		globalServices.add("com.accela.restapis.search.GlobalSearchV1");
//		globalServices.add("com.accela.restapis.batch.BatchRequestV4");
		globalServices.add("com.accela.restapis.auth.agency.AuthenticationWebService");
		globalServices.add("com.accela.restapis.record.agency.RecordWebService");
		globalServices.add("com.accela.restapis.address.agency.AddressWebService");
		globalServices.add("com.accela.restapis.parcel.agency.ParcelWebService");
		globalServices.add("com.accela.restapis.finance.agency.FeeWebService");
		globalServices.add("com.accela.restapis.finance.agency.TransactionWebService");
		globalServices.add("com.accela.restapis.system.agency.GenericViewWebService");
		globalServices.add("com.accela.restapis.v1.system.finance.fee.RefFeeWebService");
		globalServices.add("com.accela.restapis.professional.ProfessionalWebService");
		globalServices.add("com.accela.restapis.system.MerchantAccountWebService");
		
		/** Initialize Agency Services */
		
//		agencyServices.add("com.accela.restapis.professional.ProfessionalResourceV4");
//		agencyServices.add("com.accela.restapis.professional.ProfessionalSettingResourceV4");
//		agencyServices.add("com.accela.restapis.document.DocumentResourceV4");
//		agencyServices.add("com.accela.restapis.auth.AuthenticationResourceV4");
//		agencyServices.add("com.accela.restapis.search.ProfessionalSearchV4");
//		agencyServices.add("com.accela.restapis.professional.RefProfessionalResourceV4");
//		agencyServices.add("com.accela.restapis.search.RecordSearchV4");
//		agencyServices.add("com.accela.restapis.inspection.guidesheet.RefGuideSheetResourceV4");
//		agencyServices.add("com.accela.restapis.inspection.InspectionResourceV4");
//		agencyServices.add("com.accela.restapis.search.InspectionSearchV4");
//		agencyServices.add("com.accela.restapis.settings.CommonSettingsResourceV4");
//		agencyServices.add("com.accela.restapis.condition.StandardConditionResourceV4");
//		agencyServices.add("com.accela.restapis.condition.StandardConditionApprovalResourceV4");
//		agencyServices.add("com.accela.restapis.document.DocumentSettingResourceV4");
//		agencyServices.add("com.accela.restapis.workflow.WorkflowResourceV4");
//		agencyServices.add("com.accela.restapis.contact.RecordContactResourceV4");
//		agencyServices.add("com.accela.restapis.record.RecordSettingResourceV4");
//		agencyServices.add("com.accela.restapis.owner.OwnerResourceV4");
//		agencyServices.add("com.accela.restapis.search.OwnerSearchV4");
//		agencyServices.add("com.accela.restapis.related.RelatedResourceV4");
//		agencyServices.add("com.accela.restapis.owner.RefOwnerResourceV4");
//		agencyServices.add("com.accela.restapis.cost.CostResourceV4");
//		agencyServices.add("com.accela.restapis.cost.CostSettingResourceV4");
//		agencyServices.add("com.accela.restapis.timeaccounting.InspectionTimeAccountingResourceV4");
//		agencyServices.add("com.accela.restapis.address.AddressSettingResourceV4");
//		agencyServices.add("com.accela.restapis.inspection.guidesheet.ChecklistSettingResourceV4");
//		agencyServices.add("com.accela.restapis.batch.BatchRequestV4");
//		agencyServices.add("com.accela.restapis.asi.DrillDownResourceV4");
//		agencyServices.add("com.accela.restapis.address.RefAddressSearchResourceV4");
//		agencyServices.add("com.accela.restapis.parcel.RefParcelSearchResourceV4");
//		agencyServices.add("com.accela.restapis.parcel.RefParcelsResourceV4");
//		agencyServices.add("com.accela.restapis.parcel.ParcelResourceV4");
//		agencyServices.add("com.accela.restapis.address.AddressResourceV4");
//		agencyServices.add("com.accela.restapis.address.RefAddressResourceV4");
//		agencyServices.add("com.accela.restapis.record.RecordAdditionalResourceV4");
//		agencyServices.add("com.accela.restapis.record.RecordResourceV4");
//		agencyServices.add("com.accela.restapis.record.RecordCommentResourceV4");
//		agencyServices.add("com.accela.restapis.finance.FeeResourceV4");
//		agencyServices.add("com.accela.restapis.finance.FeeSettingResourceV4");
////		agencyServices.add("com.accela.restapis.finance.CashierPaymentResourceV4");
////		agencyServices.add("com.accela.restapis.finance.RecordPaymentResourceV4");
//		agencyServices.add("com.accela.restapis.workflow.MyWorkflowResourceV4");
//		agencyServices.add("com.accela.restapis.contact.ContactResourceV4");
//		agencyServices.add("com.accela.restapis.condition.ContactConditionResourceV4");
//		agencyServices.add("com.accela.restapis.search.ContactSearchResourceV4");
//		agencyServices.add("com.accela.restapis.contact.ContactsSettingResourceV4");
//		agencyServices.add("com.accela.restapis.contact.ConditionApprovalSettingResourceV4");
//		agencyServices.add("com.accela.restapis.finance.InvoiceRessourceV4");
//		agencyServices.add("com.accela.restapis.finance.RecordInvoiceRessourceV4");
//		agencyServices.add("com.accela.restapis.condition.RecordContactAddressResourceV4");
//		agencyServices.add("com.accela.restapis.inspection.InspectionSettingResourceV4");
////		agencyServices.add("com.accela.restapis.server.properties.ServerPropertiesV4");
//		agencyServices.add("com.accela.restapis.contact.RecordContactAttributeResourceV4");
//		agencyServices.add("com.accela.restapis.timeaccounting.TimeAccountingSettingsResourceV4");
////		agencyServices.add("com.accela.restapis.inspection.InspectionCommentResourceV4");
//		agencyServices.add("com.accela.restapis.shoppingcart.ShoppingCartResourceV4");
//		agencyServices.add("com.accela.restapis.announcement.AnnouncementResourceV4");
//		agencyServices.add("com.accela.restapis.activity.ActivityResourceV4");
//		agencyServices.add("com.accela.restapis.activity.ActivitySettingResourceV4");
//		agencyServices.add("com.accela.restapis.part.PartSettingResourceV4");
//		agencyServices.add("com.accela.restapis.search.PartSearchV4");
//		agencyServices.add("com.accela.restapis.search.CostSearchV4");
//		agencyServices.add("com.accela.restapis.timeaccounting.TimeAccountingResourceV4");
//		agencyServices.add("com.accela.restapis.part.RecordPartTransactionResourceV4");
//		agencyServices.add("com.accela.restapis.timeaccounting.MileageResourceV4");
////		agencyServices.add("com.accela.restapis.finance.PaymentSettingResourceV4");
//		agencyServices.add("com.accela.restapis.condition.InspectionConditionPermissionResourceV4");
////		agencyServices.add("com.accela.restapis.finance.PaymentResourceV4");
//		agencyServices.add("com.accela.restapis.finance.InvoiceSettingResourceV4");
//		agencyServices.add("com.accela.restapis.report.ReportResourceV1");
////		agencyServices.add("com.accela.restapis.citizen.PublicUserResourceV1");
//		agencyServices.add("com.accela.restapis.address.AddressResourceV1");
//		agencyServices.add("com.accela.restapis.parcel.ParcelResourceV1");
//		agencyServices.add("com.accela.restapis.owner.OwnerResourceV1");
//		agencyServices.add("com.accela.restapis.record.RecordResourceV1");
//		agencyServices.add("com.accela.restapis.contact.ContactResourceV1");
//		agencyServices.add("com.accela.restapis.professional.ProfessionalResourceV1");
//		agencyServices.add("com.accela.restapis.document.DocumentResourceV1");
//		agencyServices.add("com.accela.restapis.document.RecordDocumentResourceV1");
//		agencyServices.add("com.accela.restapis.finance.FeeResourceV1");
//		agencyServices.add("com.accela.restapis.script.ScriptResourceV1");
//		agencyServices.add("com.accela.restapis.auth.AuthenticationResourceV1");
//		agencyServices.add("com.accela.restapis.route.RouteResourceV1");
//		agencyServices.add("com.accela.restapis.search.ProfessionalSearchV1");
//		agencyServices.add("com.accela.restapis.professional.RefProfessionalResourceV1");
//		agencyServices.add("com.accela.restapis.search.RecordSearchV1");
//		agencyServices.add("com.accela.restapis.inspection.guidesheet.RefGuideSheetResourceV1");
//		agencyServices.add("com.accela.restapis.inspection.guidesheet.GuideSheetGroupResourceV1");
////		agencyServices.add("com.accela.restapis.inspection.InspectionResourceV1");
////		agencyServices.add("com.accela.restapis.inspection.InspectionCommentResourceV1");
////		agencyServices.add("com.accela.restapis.inspection.InspectionSettingResourceV1");
//		agencyServices.add("com.accela.restapis.search.InspectionSearchV1");
//		agencyServices.add("com.accela.restapis.inspector.InspectorResourceV1");
//		agencyServices.add("com.accela.restapis.search.GlobalSearchV1");
//		agencyServices.add("com.accela.restapis.condition.InspectionConditionResourceV1");
//		agencyServices.add("com.accela.restapis.user.UserProfileResourceV1");
//		agencyServices.add("com.accela.restapis.asi.RecordASIResourceV1");
//		agencyServices.add("com.accela.restapis.asi.RecordASITResourceV1");
//		agencyServices.add("com.accela.restapis.condition.ConditionSettingResourceV1");
//		agencyServices.add("com.accela.restapis.condition.InspectionConditionApprovalResourceV1");
//		agencyServices.add("com.accela.restapis.condition.RecordConditionApprovalResourceV1");
//		agencyServices.add("com.accela.restapis.settings.TimeAccountingUsersResourceV1");
//		agencyServices.add("com.accela.restapis.task.TaskResourceV1");
//		agencyServices.add("com.accela.restapis.inspection.guidesheet.GuideSheetItemASIResourceV1");
//		agencyServices.add("com.accela.restapis.inspection.guidesheet.GuideSheetItemASITResourceV1");
//		agencyServices.add("com.accela.restapis.document.DocumentSettingResourceV1");
////		agencyServices.add("com.accela.restapis.inspection.InspectionRelationsResourceV1");
//		agencyServices.add("com.accela.restapis.condition.RecordConditionResourceV1");
//		agencyServices.add("com.accela.restapis.part.AssetPartResourceV4");
//		
////		agencyServices.add("com.accela.restapis.finance.PaymentResourceV4");
//		agencyServices.add("com.accela.restapis.document.thirdparty.ThirdPartyDocumentResource");
//		agencyServices.add("com.accela.restapis.document.thirdparty.ThirdPartyRecordDocumentResource");
//		agencyServices.add("com.accela.restapis.contact.ContactASITResourceV4");
//		agencyServices.add("com.accela.restapis.trustaccount.TrustAccountResourceV4");
		/**Does not support this resources. */
		//agencyServices.add("com.accela.restapis.route.RouteResourceV1");
		//agencyServices.add("com.accela.restapis.auth.AuthenticationResourceV1");
		//agencyServices.add("com.accela.restapis.task.TaskResourceV1");
		
		/** Initialize Citizen Services */
//		citizenServices.add("com.accela.restapis.auth.citizen.AuthenticationWebService");
//		citizenServices.add("com.accela.restapis.citizen.PublicUserResourceV1");
//		citizenServices.add("com.accela.restapis.v1.system.finance.fee.RefFeeWebService");
//		citizenServices.add("com.accela.restapis.record.RecordResourceV1");
//		citizenServices.add("com.accela.restapis.address.AddressResourceV1");
//		citizenServices.add("com.accela.restapis.parcel.ParcelResourceV1");
//		citizenServices.add("com.accela.restapis.owner.OwnerResourceV1");
//		citizenServices.add("com.accela.restapis.owner.OwnerResourceV4");
//		citizenServices.add("com.accela.restapis.search.OwnerSearchV4");
//		citizenServices.add("com.accela.restapis.contact.ContactResourceV1");
//		citizenServices.add("com.accela.restapis.professional.ProfessionalResourceV1");
//		citizenServices.add("com.accela.restapis.document.RecordDocumentResourceV1");
//		citizenServices.add("com.accela.restapis.finance.FeeResourceV1");
//		citizenServices.add("com.accela.restapis.auth.AuthenticationResourceV1");
//		citizenServices.add("com.accela.restapis.professional.RefProfessionalResourceV1");
//		citizenServices.add("com.accela.restapis.document.DocumentResourceV1");
//		citizenServices.add("com.accela.restapis.inspection.guidesheet.RefGuideSheetResourceV1");
//		citizenServices.add("com.accela.restapis.inspection.guidesheet.GuideSheetGroupResourceV1");
//		citizenServices.add("com.accela.restapis.inspection.InspectionResourceV1");
//		citizenServices.add("com.accela.restapis.inspection.InspectionCommentResourceV1");
//		citizenServices.add("com.accela.restapis.inspection.InspectionSettingResourceV1");
//		citizenServices.add("com.accela.restapis.inspector.InspectorResourceV1");
//		citizenServices.add("com.accela.restapis.condition.InspectionConditionResourceV1");
//		citizenServices.add("com.accela.restapis.asi.RecordASIResourceV1");
//		citizenServices.add("com.accela.restapis.condition.InspectionConditionApprovalResourceV1");
//		citizenServices.add("com.accela.restapis.condition.RecordConditionApprovalResourceV1");
//		citizenServices.add("com.accela.restapis.condition.RecordConditionResourceV1");
//		citizenServices.add("com.accela.restapis.address.RefAddressSearchResourceV4");
//		citizenServices.add("com.accela.restapis.address.AddressResourceV4");
//		citizenServices.add("com.accela.restapis.inspection.InspectionRelationsResourceV1");
//		citizenServices.add("com.accela.restapis.workflow.WorkflowResourceV1");
//		citizenServices.add("com.accela.restapis.inspection.guidesheet.ChecklistSettingResourceV4");
//		citizenServices.add("com.accela.restapis.address.RefAddressResourceV4");
//		citizenServices.add("com.accela.restapis.record.RecordAdditionalResourceV4");
//		citizenServices.add("com.accela.restapis.record.RecordCommentResourceV4");
//		citizenServices.add("com.accela.restapis.finance.FeeResourceV4");
//		citizenServices.add("com.accela.restapis.finance.InvoiceRessourceV4");
//		citizenServices.add("com.accela.restapis.finance.RecordInvoiceRessourceV4");
//		citizenServices.add("com.accela.restapis.contact.RecordContactAddressResourceV4");
//		citizenServices.add("com.accela.restapis.timeaccounting.TimeAccountingSettingsResourceV4");
//		citizenServices.add("com.accela.restapis.shoppingcart.ShoppingCartResourceV4");
//		citizenServices.add("com.accela.restapis.part.PartSettingResourceV4");
//		citizenServices.add("com.accela.restapis.cost.CostSettingResourceV4");
//		citizenServices.add("com.accela.restapis.part.RecordPartTransactionResourceV4");
//		citizenServices.add("com.accela.restapis.condition.InspectionConditionPermissionResourceV4");
//		citizenServices.add("com.accela.restapis.document.thirdparty.ThirdPartyDocumentResource");
//		citizenServices.add("com.accela.restapis.document.thirdparty.ThirdPartyRecordDocumentResource");
		citizenServices.add("com.accela.restapis.citizen.PublicUserResourceV4");
		citizenServices.add("com.accela.restapis.contact.ContactASITResourceV4");
		citizenServices.add("com.accela.restapis.trustaccount.TrustAccountResourceV4");
	}

	/**
	 * Main method of API generator.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		 String generatorVersion = "";
		
		if (!ValidationUtil.isEmpty(args))
		{
			for (String arg : args)
			{
				System.out.println("argument: " + arg);
			}
			
			if (!ValidationUtil.isEmpty(args[0]))
			{
				generatorVersion = args[0];
			}
			
		}
		String testPath = "C:/aa/source/biz/modules/rest-apis/";
		try
		{
			testPath = new File(".").getCanonicalPath()+"/";
		}
		catch (IOException e)
		{			
			e.printStackTrace();
		}
		/** Auto generate documentation for agency services */
		String agencyPath = "docs/agency/";
		String serviceBeanPath = "java/com/accela/restapis";
		
//		Collections.sort(agencyServices);
//		Collections.reverse(agencyServices);
//		
//		List<Class<?>> agencyClassList = BeanContext.loadClasses(agencyServices, serviceBeanPath);	
//		ApiDocsGenerator.generate(agencyClassList, testPath+agencyPath, generatorVersion);
	
		/** Auto generate documentation for citizen services */
		String citizenPath = "docs/citizen/";
		List<Class<?>> citizenClassList = BeanContext.loadClasses(citizenServices, serviceBeanPath);
		ApiDocsGenerator.generate(citizenClassList, testPath+citizenPath,generatorVersion);
//
		/** Auto generate documentation for global services */
//		String globalPath = "docs/";
//		List<Class<?>> globalClassList = BeanContext.loadClasses(globalServices, serviceBeanPath);
//		ApiDocsGenerator.generate(globalClassList, testPath+globalPath, generatorVersion);
	}
	
	/**
	 * API Docs Generate.
	 */
	private static void generate(List<Class<?>> serviceClassList, String path, String generatorVersion)
	{
		/** The resource docs map. */
		Map<ResourceName,  List<Map<String, Documentation>>> resourceDocsMap = new LinkedHashMap<ResourceName,  List<Map<String, Documentation>>>();

		List<Map<String, Documentation>> docList = null;
		
		//method file name cache
		Set<String> methods = new HashSet<String>();
		
		//1. Get documents by resource.
		for (Class<?> clazz : serviceClassList)
		{
			Api currentApiEndPoint = clazz.getAnnotation(Api.class);
			Resource resource = clazz.getAnnotation(Resource.class);

			if (currentApiEndPoint != null && resource != null)
			{
				String resourcePath = currentApiEndPoint.value();
				ResourceName resourceName = resource.value();
				JaxrsApiSpecParser apiParser = new JaxrsApiSpecParser(clazz, apiVersion, swaggerVersion, basePath,
					resourcePath);
				Map<String, Documentation> earchApidocMap = apiParser.parse(generatorVersion, methods);
				
				if(resourceDocsMap.get(resourceName) == null)
				{
					docList = new ArrayList<Map<String, Documentation>>();
					resourceDocsMap.put(resourceName, docList);
				}
				else
				{
					docList = resourceDocsMap.get(resourceName);
				}
				docList.add(earchApidocMap);
			}
		}
		
		//2. Write out documents 
		
		List<Documentation> resourcePath = new ArrayList<Documentation>();
		for (ResourceName resourceName : resourceDocsMap.keySet())
		{
			List<Map<String, Documentation>> apiDocList = resourceDocsMap.get(resourceName);

			String subPath = resourceName.toString();
			if(!ValidationUtil.isEmpty(apiDocList))
			{
				for(Map<String, Documentation> earchApidocMap : apiDocList)
				{
					Iterator<Entry<String, Documentation>>  docIterator= earchApidocMap.entrySet().iterator();
					while(docIterator.hasNext())
					{
						Entry<String, Documentation>  docMap = docIterator.next();
						String fileName = docMap.getKey();
						Documentation doc = docMap.getValue();
						if(fileName.startsWith("null_"))
						{
							continue;
						}
						outputJsonResourceFile(path, subPath, fileName, doc);	
						//Add file name to resource path.
						Documentation apiDoc = new Documentation();
						apiDoc.setPath(resourceName.toString() + "/"+fileName+".json");
						apiDoc.setParameters(null);
						resourcePath.add(apiDoc);
					}
				}
			}
		}
		// Generate resources file.
		outputJsonResourceFile(path, "", "resources", resourcePath);

	}

	/**
	 * Output json resource file.
	 * 
	 * @param fileName the file name
	 * @param entry the entry
	 */
	private static void outputJsonResourceFile(String path, String subPath, String fileName, Object entry)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		File fileDir = new File(path +"/"+subPath);
		if(!fileDir.exists())
		{
			fileDir.mkdirs();
		}
		File file = new File(path +"/"+subPath, fileName + ".json");
		
		String jsonString;
		try
		{
			jsonString = objectMapper.writeValueAsString(entry);
			System.out.println(jsonString);
			objectMapper.writer().withDefaultPrettyPrinter().writeValue(file, entry);

			System.out.println("Absolute path = " + file.getAbsolutePath());
		}
		catch (JsonGenerationException e)
		{
			System.out.println("Write Resource Json file failed " + e.getMessage());
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			System.out.println("Write Resource Json file failed " + e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("Write Resource Json file failed " + e.getMessage());
			e.printStackTrace();
		}
	}
}

/*
 * $Log: av-env.bat,v $
 */
