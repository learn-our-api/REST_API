package com.cxf.restapis.resource.dog;

import com.cxf.restapis.framework.security.handlers.AbstractSecurityAuthorizer;
import com.cxf.restapis.framework.security.handlers.ResourceBasic;
import com.cxf.restapis.framework.security.handlers.SecurityAuthorizer;

public class DogSecurityAuthorizer extends AbstractSecurityAuthorizer implements
		SecurityAuthorizer {

	@Override
	public boolean isListAllowed(String callerId, ResourceBasic resource) {
		return isGetAllowed(callerId, resource);
	}

	@Override
	public boolean isGetAllowed(String callerId, ResourceBasic resource) {
		return true;
	}

	@Override
	public boolean isCreateAllowed(String callerId, ResourceBasic resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUpdateAllowed(String callerId, ResourceBasic resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDeleteAllowed(String callerId, ResourceBasic resource) {
		// TODO Auto-generated method stub
		return false;
	}

}
