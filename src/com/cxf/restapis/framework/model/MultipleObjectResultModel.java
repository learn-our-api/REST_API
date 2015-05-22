package com.cxf.restapis.framework.model;

import com.cxf.restapis.framework.json.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: MultipleObjectResultModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: MultipleObjectResultModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\evan.cai $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Feb 24, 2014		evan.cai		Initial.
 *  
 * </pre>
 */
public class MultipleObjectResultModel implements java.io.Serializable
{

	/**
	 * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
	 */
	private static final long serialVersionUID = 7920105597816391003L;

	private Object id;
	
	private String code;
	
	private String message;
	
	private Boolean isSuccess;

	public MultipleObjectResultModel()
	{
		super();
	}
	
	public MultipleObjectResultModel(Object id, String code, String message, Boolean isSuccess)
	{
		super();
		this.id = id;
		this.code = code;
		this.message = message;
		this.isSuccess = isSuccess;
	}

	@JsonView(Views.PublicView.class)
	public Object getId()
	{
		return id;
	}

	public void setId(Object id)
	{
		this.id = id;
	}

	@JsonView(Views.PublicView.class)
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	@JsonView(Views.PublicView.class)
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@JsonView(Views.PublicView.class)
	public Boolean getIsSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(Boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}
	
	public static MultipleObjectResultModel getFailedResultModel(String code, String message)
	{
		return getResultModel(null, code, message, false);
	}
	
	public static MultipleObjectResultModel getFailedResultModel(Object id,String code, String message)
	{
		return getResultModel(id, code, message, false);
	}
	
	public static MultipleObjectResultModel getSuccessResultModel(Object id)
	{
		
		return getResultModel(id, null,null,null);
	}
	public static MultipleObjectResultModel getSuccessResultModelMore(Object id, String code, String message)
	{
		return getResultModel(id, code, message, true);
	}
	
	public static MultipleObjectResultModel getResultModel(Object id,String code, String message, Boolean isSuccess)
	{
		return new MultipleObjectResultModel(id, code, message, isSuccess);
	}
	
	/**
	 * Used for create.
	 *
	 * @param multipleObjectResultModel
	 * @param code
	 * @param message 
	 */
	public static void buildFailedResultModel(MultipleObjectResultModel multipleObjectResultModel, String code, String message)
	{
		multipleObjectResultModel.setCode(code);
		multipleObjectResultModel.setMessage(message);
		multipleObjectResultModel.setSuccess(false);
	}
	
	/**
	 * Used for delete
	 * Builds the failed result model.
	 * 
	 * @param multipleObjectResultModel the multiple object result model
	 * @param id the id
	 * @param code the code
	 * @param message the message
	 */
	public static void buildFailedResultModel(MultipleObjectResultModel multipleObjectResultModel, Object id, String code, String message)
	{
		multipleObjectResultModel.setCode(code);
		multipleObjectResultModel.setId(id);
		multipleObjectResultModel.setMessage(message);
		multipleObjectResultModel.setSuccess(false);
	}
	
	public static void buildSuccessResultModel(MultipleObjectResultModel multipleObjectResultModel, Object id, String code, String message)
	{
		multipleObjectResultModel.setCode(code);
		multipleObjectResultModel.setId(id);
		multipleObjectResultModel.setMessage(message);
		multipleObjectResultModel.setSuccess(true);
	}
	
	public static void buildSuccessResultModel(MultipleObjectResultModel multipleObjectResultModel, Object id)
	{
		multipleObjectResultModel.setId(id);
		multipleObjectResultModel.setSuccess(true);
	}
	
}

/*
*$Log: av-env.bat,v $
*/