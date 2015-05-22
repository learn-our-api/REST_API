package com.cxf.restapis.docsgenerator.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * 
 *  Accela Automation
 *  File: DocumentationEndPoint.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2012-2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: DocumentationEndPoint.java 72642 2009-01-01 20:01:57Z ACHIEVO\dylan.liang $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2012-10-18		dylan.liang		Initial.
 * 
 * </pre>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({"path", "description", "operations"})
@XmlRootElement(name = "api")
public class DocumentationEndPoint
{
//	/** The path. */
//	private String path;
//
//	/** The description. */
//	private String description;
//
//	/** The ops. */
//	@XmlElement(name = "operations")
//	@JsonProperty(value = "operations")
//	private List<DocumentationOperation> ops = null;
//
//	/**
//	 * Gets the path.
//	 * 
//	 * @return the path
//	 */
//	public String getPath()
//	{
//		return path;
//	}
//
//	/**
//	 * Sets the path.
//	 * 
//	 * @param path the path to set
//	 */
//	public void setPath(String path)
//	{
//		this.path = path;
//	}
//
//	/**
//	 * Gets the description.
//	 * 
//	 * @return the description
//	 */
//	public String getDescription()
//	{
//		return description;
//	}
//
//	/**
//	 * Sets the description.
//	 * 
//	 * @param description the description to set
//	 */
//	public void setDescription(String description)
//	{
//		this.description = description;
//	}
//
//	/**
//	 * Gets the ops.
//	 * 
//	 * @return the ops
//	 */
//	public List<DocumentationOperation> getOps()
//	{
//		return ops;
//	}
//
//	/**
//	 * Sets the ops.
//	 * 
//	 * @param ops the ops to set
//	 */
//	@JsonProperty(value = "operations")
//	@XmlElement(name = "operations")
//	public void setOps(List<DocumentationOperation> ops)
//	{
//		this.ops = ops;
//	}
//
//	/**
//	 * Adds the operation.
//	 * 
//	 * @param op the operation
//	 */
//	public void addOperation(DocumentationOperation op)
//	{
//		if (this.ops != null)
//		{
//			this.ops.add(op);
//		}
//		else
//		{
//			this.ops = new ArrayList<DocumentationOperation>();
//			ops.add(op);
//		}
//	}
//
//	/* (non-Jsdoc)
//	 * @see java.lang.Object#clone()
//	 */
//	public Object clone()
//	{
//		DocumentationEndPoint ep = new DocumentationEndPoint();
//		ep.setPath(this.path);
//		ep.setDescription(this.description);
//
//		for (DocumentationOperation op : this.ops)
//		{
//			ep.addOperation((DocumentationOperation) op.clone());
//		}
//		return ep;
//	}
}

/*
 * $Log: av-env.bat,v $
 */
