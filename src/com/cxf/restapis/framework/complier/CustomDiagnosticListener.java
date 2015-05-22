package com.cxf.restapis.framework.complier;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: CustomDiagnosticListener.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2013
 * 
 *  Description:
 *  Provide custom diagnostic listener to get compile error message.
 * 
 *  Notes:
 * 	$Id: CustomDiagnosticListener.java 72642 2013-09-28 20:01:57Z ACHIEVO\michael.mao $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2013-9-28		michael.mao		Initial.
 *  
 * </pre>
 */
public class CustomDiagnosticListener implements DiagnosticListener<JavaFileObject>
{

	private String sourceCode;
	
	private List<String> errorMessages = new ArrayList<String>();
	
	public CustomDiagnosticListener(String sourceCode)
	{
		this.sourceCode = sourceCode;
	}
	@Override
	public void report(Diagnostic<? extends JavaFileObject> diagnostic) 
	{
		if (Kind.ERROR.equals(diagnostic.getKind()))
		{
			StringBuilder message = new StringBuilder();
			message.append("Error on line:" + diagnostic.getLineNumber());
			message.append(" ").append(getMessage(diagnostic.getMessage(Locale.ENGLISH)));
			diagnostic.getColumnNumber();
			message.append("\r\nSource Code (column:" + diagnostic.getColumnNumber() +") " + getSourceCode(sourceCode, (int)diagnostic.getPosition()));
			errorMessages.add(message.toString());
		}
	}
	/**
	 * Return compile error.
	 * @return
	 */
	public List<String> getErrorMessages()
	{
		if (errorMessages == null || errorMessages.size() ==0)
		{
			return null;
		}
		return errorMessages;
	}
	
	private static String getSourceCode(String sourceCode, int beginPos)
	{
		if (sourceCode.length() < beginPos || beginPos < 0)
		{
			return "";
		}
		StringBuilder code = new StringBuilder(100);
		//Find the position of line head.
		int lineBeginPos = 0;
		for (int index = beginPos; index > 0; index--)
		{
			if (sourceCode.charAt(index) == '\r' || sourceCode.charAt(index) == '\n' || sourceCode.charAt(index) == ';')
			{
				lineBeginPos = index + 1;
				break;
			}
		}
		//Find the position of line end.
		for (int index = lineBeginPos; index < sourceCode.length(); index++)
		{
			if (sourceCode.charAt(index) == '\r' || sourceCode.charAt(index) == '\n' || sourceCode.charAt(index) == ';')
			{
				break;
			}
			code.append(sourceCode.charAt(index));
		}
		return code.toString();
	}
	
	private static String getMessage(String message)
	{
		if (message == null)
		{
			return null;
		}
		if (message.indexOf("location: ") > 0)
		{
			return message.substring(0, message.indexOf("location: ") - 1);
		}
		return message;
	}


}
