package com.cxf.restapis.resource.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cxf.restapis.framework.model.ResponseModel;
import com.cxf.restapis.framework.security.annotations.IgnoreAuthentication;
import com.cxf.restapis.framework.util.ResponseHelper;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: UserResource.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: UserResource.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Apr 2, 2015		bryant.tu		Initial.
 * 
 * </pre>
 */
@Path("/v1/users")
@Produces({MediaType.APPLICATION_JSON})
public class UserResource
{
	@Path("/{id}")
	@GET
	@IgnoreAuthentication
	public ResponseModel getUserById(@PathParam("id") int id)
	{
		UserModel userModel = new UserModel();
		userModel.setId(id);
		userModel.setName("bryant");
		userModel.setAge(15);
		
		return ResponseHelper.buildResponseModel(userModel, UserModel.class, null, true, "");
	}
}

/*
 * $Log: av-env.bat,v $
 */