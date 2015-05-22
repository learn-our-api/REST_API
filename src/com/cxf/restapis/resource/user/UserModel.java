package com.cxf.restapis.resource.user;
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: UserModel.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2015
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: UserModel.java 72642 2009-01-01 20:01:57Z ACHIEVO\bryant.tu $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Apr 2, 2015		bryant.tu		Initial.
 *  
 * </pre>
 */
public class UserModel
{
	private int id;
	private String name;

	private int age;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}
}

/*
*$Log: av-env.bat,v $
*/