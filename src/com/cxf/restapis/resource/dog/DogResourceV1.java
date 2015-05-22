package com.cxf.restapis.resource.dog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cxf.restapis.docsgenerator.ResourceName;
import com.cxf.restapis.docsgenerator.annotations.Resource;
import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.security.annotations.IgnoreAuthentication;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Resource(ResourceName.dog)
@Path("/v1/dogs")
@Api(value = "/v4/dogs", description = "Operations about owners associated with specified record")
@Produces( {MediaType.APPLICATION_JSON})
public class DogResourceV1 {

	@GET
	@Path("/{id}")
	@IgnoreAuthentication
//	@ApiSecurity(parentResource = "record", parentId = ParamConstants.RECORD_ID, resource = "owner", applicability = BOTH)
//	@Validations(value = {@Validation(paramName = "recordId", type = ValidationType.recordID)})
	@ApiOperation(value = "Find Owners by Record ID", notes = "Find Owners by Record ID", responseClass = "com.cxf.restapis.resource.dog.DogModel", multiValueResponse = true)
//	@ApiErrors(value = {@ApiError(code = SC_BAD_REQUEST, reason = "Invalid input parameters"),
//			@ApiError(code = SC_NOT_FOUND, reason = "Owner not been found")})
//	@ApiModelCustomConvertor(response="com.accela.restapis.json.impl.RefOwnerModelCustomConvertor")
	public ResponseModel getDogById(
			@ApiParam(value = "ID of record that needs to be fetched", required = true) @PathParam("id") Long dogId)
			throws Exception
	{
		DogModel dog = new DogService().getDog(dogId);
		

//		return ResponseHelper
//				.buildResponseModel(dog, DogModel.class, null, true, "", false, false);
		return ResponseHelper.buildResponseModel(dog, DogModel.class, null, true, "");
		
	}
}
