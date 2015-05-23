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
@Api(value = "/v4/dogs", description = "Operations about dogs")
@Produces( {MediaType.APPLICATION_JSON})
public class DogResourceV1 {

	@GET
	@Path("/{id}")
	@IgnoreAuthentication
	@ApiOperation(value = "Find Dog by Dog ID", notes = "Find Dog by Dog ID", responseClass = "com.cxf.restapis.resource.dog.DogModel", multiValueResponse = true)
	public ResponseModel getDogById(
			@ApiParam(value = "ID of Dog that needs to be fetched", required = true) @PathParam("id") Long dogId)
			throws Exception
	{
		DogModel dog = new DogService().getDog(dogId);
		

		return ResponseHelper.buildResponseModel(dog, DogModel.class, null, true, "");
		
	}
}
