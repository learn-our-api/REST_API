package com.cxf.restapis.resource.dog;

import static com.cxf.restapis.framework.constants.APIConstants.Applicability.BOTH;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.cxf.restapis.docsgenerator.annotations.Resource;
import com.cxf.restapis.docsgenerator.ResourceName;
import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.security.annotations.ApiModelCustomConvertor;
import com.cxf.restapis.framework.security.annotations.ApiSecurity;
import com.cxf.restapis.framework.security.annotations.IgnoreAuthentication;
import com.cxf.restapis.framework.util.ResponseHelper;
import com.cxf.restapis.framework.validation.Validations;
import com.cxf.restapis.framework.validation.Validation;
import com.cxf.restapis.framework.validation.ValidationType;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.ws.rs.core.MediaType;

@Resource(ResourceName.dog)
@Path("/v4/dogs")
@Api(value = "/v4/dogs", description = "Operations about owners associated with specified record")
@Produces( {MediaType.APPLICATION_JSON})
public class DogResourceV4 {

	@GET
	@Path("/{id}")
	@IgnoreAuthentication
	@ApiSecurity(resource = "dog", operation = "R", applicability = BOTH)
//	@Validations(value = {@Validation(paramName = "id", type = ValidationType.Number)})
	@ApiOperation(value = "Find Owners by Record ID", notes = "Find Owners by Record ID", responseClass = "com.cxf.restapis.resource.dog.DogModel", multiValueResponse = false)
	@ApiErrors(value = {@ApiError(code = SC_BAD_REQUEST, reason = "Invalid input parameters"),
			@ApiError(code = SC_NOT_FOUND, reason = "Owner not been found")})
	@ApiModelCustomConvertor(response="com.cxf.restapis.resource.dog.DogModelCustomConvertor")
	public ResponseModel getDogById(
			@ApiParam(value = "ID of record that needs to be fetched", required = true) @PathParam("id") Long dogId)
			throws Exception
	{
		DogModel dog = new DogService().getDog(dogId);
		

		return ResponseHelper
				.buildResponseModel(dog, DogModel.class, null, true, "", false, true);
		
	}
}
