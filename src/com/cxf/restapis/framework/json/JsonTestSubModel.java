package com.cxf.restapis.framework.json;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JsonTestModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: JsonTestModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-8-24		michael.mao		Initial.
 *  
 * </pre>
 */
public class JsonTestSubModel
{
	private String stringValue;
	
	private Double doubleValue;
	
	private Float floatValue;
	
	private Long longValue;
	
	private BigDecimal bigDecimalValue;
	
	private Byte byteValue;
	
	private Boolean booleanValue;
	
	private Date dateValue;
	
	private Integer integerValue;
	
	private int intValue;
	
	private boolean booleanVal;
	
	private String[] stringArray;
	
	private List<String> listString;
	
	private StringBuffer stringBufferValue;
	
	private StringBuilder stringBuilderValue;

	@JsonProperty(value="tables")
	private JsonTestSubModel subModel2;
	/**
	 * @return the stringValue
	 */
	
	public String getStringValue()
	{
		return stringValue;
	}

	/**
	 * @param stringValue the stringValue to set
	 */
	public void setStringValue(String stringValue)
	{
		this.stringValue = stringValue;
	}

	/**
	 * @return the doubleValue
	 */
	
	public Double getDoubleValue()
	{
		return doubleValue;
	}

	/**
	 * @param doubleValue the doubleValue to set
	 */
	public void setDoubleValue(Double doubleValue)
	{
		this.doubleValue = doubleValue;
	}

	/**
	 * @return the floatValue
	 */
	
	public Float getFloatValue()
	{
		return floatValue;
	}

	/**
	 * @param floatValue the floatValue to set
	 */
	public void setFloatValue(Float floatValue)
	{
		this.floatValue = floatValue;
	}

	/**
	 * @return the longValue
	 */
	
	public Long getLongValue()
	{
		return longValue;
	}

	/**
	 * @param longValue the longValue to set
	 */
	public void setLongValue(Long longValue)
	{
		this.longValue = longValue;
	}

	/**
	 * @return the bigDecimalValue
	 */
	
	public BigDecimal getBigDecimalValue()
	{
		return bigDecimalValue;
	}

	/**
	 * @param bigDecimalValue the bigDecimalValue to set
	 */
	public void setBigDecimalValue(BigDecimal bigDecimalValue)
	{
		this.bigDecimalValue = bigDecimalValue;
	}

	/**
	 * @return the byteValue
	 */
	
	public Byte getByteValue()
	{
		return byteValue;
	}

	/**
	 * @param byteValue the byteValue to set
	 */
	public void setByteValue(Byte byteValue)
	{
		this.byteValue = byteValue;
	}

	/**
	 * @return the booleanValue
	 */
	
	public Boolean getBooleanValue()
	{
		return booleanValue;
	}

	/**
	 * @param booleanValue the booleanValue to set
	 */
	public void setBooleanValue(Boolean booleanValue)
	{
		this.booleanValue = booleanValue;
	}

	/**
	 * @return the dateValue
	 */
	
	public Date getDateValue()
	{
		return dateValue;
	}

	/**
	 * @param dateValue the dateValue to set
	 */
	public void setDateValue(Date dateValue)
	{
		this.dateValue = dateValue;
	}

	/**
	 * @return the integerValue
	 */
	
	public Integer getIntegerValue()
	{
		return integerValue;
	}

	/**
	 * @param integerValue the integerValue to set
	 */
	public void setIntegerValue(Integer integerValue)
	{
		this.integerValue = integerValue;
	}

	/**
	 * @return the intValue
	 */
	
	public int getIntValue()
	{
		return intValue;
	}

	/**
	 * @param intValue the intValue to set
	 */
	public void setIntValue(int intValue)
	{
		this.intValue = intValue;
	}

	/**
	 * @return the booleanVal
	 */
	
	public boolean isBooleanVal()
	{
		return booleanVal;
	}

	/**
	 * @param booleanVal the booleanVal to set
	 */
	public void setBooleanVal(boolean booleanVal)
	{
		this.booleanVal = booleanVal;
	}

	/**
	 * @return the stringArray
	 */
	
	public String[] getStringArray()
	{
		return stringArray;
	}

	/**
	 * @param stringArray the stringArray to set
	 */
	public void setStringArray(String[] stringArray)
	{
		this.stringArray = stringArray;
	}

	/**
	 * @return the listString
	 */
	
	public List<String> getListString()
	{
		return listString;
	}

	/**
	 * @param listString the listString to set
	 */
	public void setListString(List<String> listString)
	{
		this.listString = listString;
	}

	/**
	 * @return the stringBufferValue
	 */
	
	public StringBuffer getStringBufferValue()
	{
		return stringBufferValue;
	}

	/**
	 * @param stringBufferValue the stringBufferValue to set
	 */
	public void setStringBufferValue(StringBuffer stringBufferValue)
	{
		this.stringBufferValue = stringBufferValue;
	}

	/**
	 * @return the stringBuilderValue
	 */
	
	public StringBuilder getStringBuilderValue()
	{
		return stringBuilderValue;
	}

	/**
	 * @param stringBuilderValue the stringBuilderValue to set
	 */
	public void setStringBuilderValue(StringBuilder stringBuilderValue)
	{
		this.stringBuilderValue = stringBuilderValue;
	}
	
	
	
}

/*
*$Log: av-env.bat,v $
*/