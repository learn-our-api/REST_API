package com.cxf.restapis.framework.json;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

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
public class JsonTestModel
{
	private String stringValue;

	private String stringValue2;
	
	private Double doubleValue;
	
	private Float floatValue;
	
	private Long longValue;
	
	private BigDecimal bigDecimalValue;
	
	private Byte byteValue;
	
	private Boolean booleanValue;
	
	private Date dateValue;
	
	private java.sql.Timestamp timestampValue;
	
	private Integer integerValue;
	
	private int intValue;
	
	private boolean booleanVal;
	
	private Integer UIUID;
	
	private byte[] image;
	
	private char[] charArray;
	
	private String[] stringArray;
	
	private List<String> listString; 
	
	private List<Long> listLong; 
	
	private StringBuffer stringBufferValue;
	
	private StringBuilder stringBuilderValue;
	
	private List<JsonTestSubModel> listSubModel;
	
	private List<JsonTestSubModel> nllListSubModel;

	private List<JsonTestSubModel> listSubModel2;

	
	private Collection<JsonTestSubModel> collectionSubModel;
	
	private JsonTestSubModel subModel1;
	
	private Map<String, String> mapString;
	
	
	@JsonProperty(value="tables")
	private JsonTestSubModel subModel2;


	private JsonTestSubModel nullSubModel3;
	
	private Boolean isBoolean;
	
	private String product;
	
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


	/**
	 * @return the listSubModel
	 */
	
	public List<JsonTestSubModel> getListSubModel()
	{
		return listSubModel;
	}


	/**
	 * @param listSubModel the listSubModel to set
	 */
	public void setListSubModel(List<JsonTestSubModel> listSubModel)
	{
		this.listSubModel = listSubModel;
	}


	/**
	 * @return the collectionSubModel
	 */
	
	public Collection<JsonTestSubModel> getCollectionSubModel()
	{
		return collectionSubModel;
	}


	/**
	 * @param collectionSubModel the collectionSubModel to set
	 */
	public void setCollectionSubModel(Collection<JsonTestSubModel> collectionSubModel)
	{
		this.collectionSubModel = collectionSubModel;
	}


	/**
	 * @return the subModel1
	 */
	
	public JsonTestSubModel getSubModel1()
	{
		return subModel1;
	}


	/**
	 * @param subModel1 the subModel1 to set
	 */
	public void setSubModel1(JsonTestSubModel subModel1)
	{
		this.subModel1 = subModel1;
	}


	/**
	 * @return the subModel2
	 */
	
	public JsonTestSubModel getSubModel2()
	{
		return subModel2;
	}


	/**
	 * @param subModel2 the subModel2 to set
	 */
	public void setSubModel2(JsonTestSubModel subModel2)
	{
		this.subModel2 = subModel2;
	}


	/**
	 * @return the stringValue2
	 */
	
	public String getStringValue2()
	{
		return stringValue2;
	}


	/**
	 * @param stringValue2 the stringValue2 to set
	 */
	public void setStringValue2(String stringValue2)
	{
		this.stringValue2 = stringValue2;
	}


	/**
	 * @return the mapString
	 */
	
	public Map<String, String> getMapString()
	{
		return mapString;
	}


	/**
	 * @param mapString the mapString to set
	 */
	public void setMapString(Map<String, String> mapString)
	{
		this.mapString = mapString;
	}


	/**
	 * @return the timestampValue
	 */
	
	public java.sql.Timestamp getTimestampValue()
	{
		return timestampValue;
	}


	/**
	 * @param timestampValue the timestampValue to set
	 */
	public void setTimestampValue(java.sql.Timestamp timestampValue)
	{
		this.timestampValue = timestampValue;
	}


	/**
	 * @return the listSubModel2
	 */
	@XmlElementWrapper(name = "refFeeScheduleModels")
	@XmlElement(name = "refFeeSchedule")
	public List<JsonTestSubModel> getListSubModel2()
	{
		return listSubModel2;
	}


	/**
	 * @param listSubModel2 the listSubModel2 to set
	 */
	public void setListSubModel2(List<JsonTestSubModel> listSubModel2)
	{
		this.listSubModel2 = listSubModel2;
	}


	/**
	 * @return the nllListSubModel
	 */
	
	public List<JsonTestSubModel> getNllListSubModel()
	{
		return nllListSubModel;
	}


	/**
	 * @param nllListSubModel the nllListSubModel to set
	 */
	public void setNllListSubModel(List<JsonTestSubModel> nllListSubModel)
	{
		this.nllListSubModel = nllListSubModel;
	}


	/**
	 * @return the nullSubModel3
	 */
	
	public JsonTestSubModel getNullSubModel3()
	{
		return nullSubModel3;
	}


	/**
	 * @param nullSubModel3 the nullSubModel3 to set
	 */
	public void setNullSubModel3(JsonTestSubModel nullSubModel3)
	{
		this.nullSubModel3 = nullSubModel3;
	}


	/**
	 * @return the image
	 */
	
	public byte[] getImage()
	{
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image)
	{
		this.image = image;
	}


	/**
	 * @return the charArray
	 */
	
	public char[] getCharArray()
	{
		return charArray;
	}


	/**
	 * @param charArray the charArray to set
	 */
	public void setCharArray(char[] charArray)
	{
		this.charArray = charArray;
	}
	
	/**
	 * 
	 * get UI UID.
	 *
	 * @return uiUID
	 */
	public Integer getUIUID()
	{
		return this.UIUID;
	}

	/**
	 * 
	 * set UI UID.
	 *
	 * @param uiUID
	 */
	public void setUIUID(Integer uiUID)
	{
		this.UIUID = uiUID;
	}


	/**
	 * @return the listLong
	 */
	
	public List<Long> getListLong()
	{
		return listLong;
	}


	/**
	 * @param listLong the listLong to set
	 */
	public void setListLong(List<Long> listLong)
	{
		this.listLong = listLong;
	}
	
	public void setIsBoolean(Boolean isBoolean)
	{
		this.isBoolean = isBoolean;
	}
	
	public Boolean isIsBoolean()
	{
		return this.isBoolean;
	}


	/**
	 * @return the product
	 */
	
	public String getProduct()
	{
		return product;
	}


	/**
	 * @param product the product to set
	 */
	public void setProduct(String product)
	{
		this.product = product;
	}
	
	
}

/*
*$Log: av-env.bat,v $
*/