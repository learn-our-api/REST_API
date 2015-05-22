package com.cxf.restapis.docsgenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wordnik.swagger.core.DocumentationAllowableListValues;
import com.wordnik.swagger.core.DocumentationAllowableRangeValues;
import com.wordnik.swagger.core.DocumentationAllowableValues;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: JaxrsApiReader.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: JaxrsApiReader.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-18		dylan.liang		Initial.
 * 
 * </pre>
 */
public class BaseApiParser
{

	/** The positive infinity string. */
	private static String POSITIVE_INFINITY_STRING = "Infinity";

	/** The negative infinity string. */
	private static String NEGATIVE_INFINITY_STRING = "-Infinity";

	/**
	 * Read string.
	 * 
	 * @param s the s
	 * @return the string
	 */
	protected String readString(String s)
	{
		return readString(s, null, null);
	}

	/**
	 * Read string.
	 * 
	 * @param s the s
	 * @param existingValue the existing value
	 * @return the string
	 */
	protected String readString(String s, String existingValue)
	{
		return readString(s, existingValue, null);
	}

	/**
	 * Read string.
	 * 
	 * @param s the s
	 * @param existingValue the existing value
	 * @param ignoreValue the ignore value
	 * @return the string
	 */
	protected String readString(String s, String existingValue, String ignoreValue)
	{
		if (existingValue != null && existingValue.trim().length() > 0)
		{
			return existingValue;
		}
		else if (s == null || s.trim().length() == 0)
		{
			return null;
		}
		else if (ignoreValue != null && s.equals(ignoreValue))
		{
			return null;
		}
		else
		{
			return s.trim();
		}
	}

	/**
	 * To object list.
	 * 
	 * @param csvString the csv string
	 * @return the list
	 */
	protected List<String> toObjectList(String csvString)
	{
		return toObjectList(csvString, null);
	}

	/**
	 * To object list.
	 * 
	 * @param csvString the csv string
	 * @param paramType the param type
	 * @return the list
	 */
	protected List<String> toObjectList(String csvString, String paramType)
	{
		List<String> params = new ArrayList<String>();

		if (csvString == null || csvString.length() == 0)
		{
			params = new ArrayList<String>();
		}
		else if (paramType == null || paramType.equalsIgnoreCase("string"))
		{
			if (csvString.indexOf("range[") >= 0)
			{
				params.add(csvString);
			}
			else 
			{
				params = Arrays.asList(csvString.split(","));
			}
			
		}

		return params;
	}

	/**
	 * Convert to allowable values.
	 * 
	 * @param csvString the csv string
	 * @return the documentation allowable values
	 */
	protected DocumentationAllowableValues convertToAllowableValues(String csvString)
	{
		return convertToAllowableValues(csvString, null);
	}

	/**
	 * Convert to allowable values.
	 * 
	 * @param csvString the csv string
	 * @param paramType the param type
	 * @return the documentation allowable values
	 */
	protected DocumentationAllowableValues convertToAllowableValues(String csvString, String paramType)
	{
		DocumentationAllowableValues allowableValues = null;
		if (csvString.toLowerCase().startsWith("range["))
		{
			String[] ranges = csvString.substring(6, csvString.length() - 1).split(",");
			allowableValues = buildAllowableRangeValues(ranges, csvString);
		}
		else if (csvString.toLowerCase().startsWith("rangeexclusive["))
		{
			String[] ranges = csvString.substring(15, csvString.length() - 1).split(",");
			allowableValues = buildAllowableRangeValues(ranges, csvString);
		}
		else
		{
			if (csvString != null && csvString.length() > 0)
			{
				String[] params = csvString.split(",");
				List<String> paramList = Arrays.asList(params);

				if (paramType == null || paramType.equalsIgnoreCase("string"))
				{
					allowableValues = new DocumentationAllowableListValues(paramList);
				}
			}
		}

		return allowableValues;
	}

	/**
	 * Builds the allowable range values.
	 * 
	 * @param ranges the ranges
	 * @param inputStr the input str
	 * @return the documentation allowable range values
	 */
	private DocumentationAllowableRangeValues buildAllowableRangeValues(String[] ranges, String inputStr)
	{
		java.lang.Float min = new Float(0);
		java.lang.Float max = new Float(0);

		if (ranges.length < 2)
		{
			throw new RuntimeException("Allowable values format " + inputStr + "is incorrect");
		}
		if (ranges[0].equalsIgnoreCase(POSITIVE_INFINITY_STRING))
		{
			min = Float.POSITIVE_INFINITY;
		}
		else if (ranges[0].equalsIgnoreCase(NEGATIVE_INFINITY_STRING))
		{
			min = Float.NEGATIVE_INFINITY;
		}
		else
		{
			min = Float.valueOf(ranges[0]);
		}
		if (ranges[1].equalsIgnoreCase(POSITIVE_INFINITY_STRING))
		{
			max = Float.POSITIVE_INFINITY;
		}
		else if (ranges[1].equalsIgnoreCase(NEGATIVE_INFINITY_STRING))
		{
			max = Float.NEGATIVE_INFINITY;
		}
		else
		{
			max = Float.valueOf(ranges[1]);
		}

		DocumentationAllowableRangeValues allowableValues = new DocumentationAllowableRangeValues(min, max);
		return allowableValues;
	}
}

/*
 * $Log: av-env.bat,v $
 */
