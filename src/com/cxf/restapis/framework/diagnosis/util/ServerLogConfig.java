package com.cxf.restapis.framework.diagnosis.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.apache.log4j.xml.SAXErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Properties;


/**
 * 
 * <pre>
 * 
 *  Accela Automation
 *  File: ServerLogConfig.java
 * 
 *  Accela, Inc.
 *  Copyright (C): 2014
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: ServerLogConfig.java 72642 2009-01-01 20:01:57Z ACHIEVO\tony.li $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  Jul 3, 2014		tony.li		Initial.
 *
 * </pre>
 */
public class ServerLogConfig
{
	static final String APPENDER_TAG = "appender";

	static final String APPENDER_REF_TAG = "appender-ref";

	static final String PARAM_TAG = "param";

	static final String LAYOUT_TAG = "layout";

	static final String NAME_ATTR = "name";

	static final String CLASS_ATTR = "class";

	static final String VALUE_ATTR = "value";

	static final String ROOT_TAG = "root";

	static final String ROOT_REF = "root-ref";

	static final String FILTER_TAG = "filter";

	static final String ERROR_HANDLER_TAG = "errorHandler";

	static final String REF_ATTR = "ref";


	static final String THRESHOLD_ATTR = "threshold";

	static final Class[] ONE_STRING_PARAM = new Class[] {String.class};

	final static String dbfKey = "javax.xml.parsers.DocumentBuilderFactory";

	public static Category logger = Category.getInstance(ServerLogConfig.class);
	
	 public static final String INHERITED = "inherited";
	 public static final String NULL = "null";

	// key: appenderName, value: appender
	Hashtable appenderBag;
	
	
	public ServerLogConfig()
	{
		 this.appenderBag = new Hashtable();
	}
	
	/**
	 * Used internally to parse appenders by IDREF name.
	 */
	protected Appender findAppenderByName(Document doc, String appenderName)
	{
		Appender appender = (Appender) appenderBag.get(appenderName);

		if (appender != null)
		{
			return appender;
		}
		else
		{
			Element element = null;
			NodeList list = doc.getElementsByTagName("appender");
			for (int t = 0; t < list.getLength(); t++)
			{
				Node node = list.item(t);
				NamedNodeMap map = node.getAttributes();
				Node attrNode = map.getNamedItem("name");
				if (appenderName.equals(attrNode.getNodeValue()))
				{
					element = (Element) node;
					break;
				}
			}
			if (element == null)
			{
				logger.error("No appender named [" + appenderName + "] could be found.");
				return null;
			}
			else
			{
				appender = parseAppender(element);
				appenderBag.put(appenderName, appender);
				return appender;
			}
		}
	}

	/**
	 * Used internally to parse appenders by IDREF element.
	 */
	public Appender findAppenderByReference(Element appenderRef)
	{
		String appenderName = subst(appenderRef.getAttribute(REF_ATTR));
		Document doc = appenderRef.getOwnerDocument();
		return findAppenderByName(doc, appenderName);
	}
	
	
	private static Properties props = new Properties();	
	private String subst(String value)
	{
		try
		{
			return OptionConverter.substVars(value, props);
		}
		catch (IllegalArgumentException e)
		{
			logger.error("Could not perform variable substitution.", e);
			return value;
		}
	}

	/**
	 * Used internally to parse an appender element.
	 */
	protected Appender parseAppender(Element appenderElement)
	{
		String className = subst(appenderElement.getAttribute(CLASS_ATTR));
		logger.debug("Class name: [" + className + ']');
		try
		{
			Object instance = Loader.loadClass(className).newInstance();
			Appender appender = (Appender) instance;
			PropertySetter propSetter = new PropertySetter(appender);

			appender.setName(subst(appenderElement.getAttribute(NAME_ATTR)));

			NodeList children = appenderElement.getChildNodes();
			final int length = children.getLength();

			for (int loop = 0; loop < length; loop++)
			{
				Node currentNode = children.item(loop);

				/* We're only interested in Elements */
				if (currentNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element currentElement = (Element) currentNode;

					// Parse appender parameters
					if (currentElement.getTagName().equals(PARAM_TAG))
					{
						setParameter(currentElement, propSetter);
					}
					// Set appender layout
					else if (currentElement.getTagName().equals(LAYOUT_TAG))
					{
						appender.setLayout(parseLayout(currentElement));
					}
					// Add filters
					else if (currentElement.getTagName().equals(FILTER_TAG))
					{
						parseFilters(currentElement, appender);
					}
					else if (currentElement.getTagName().equals(ERROR_HANDLER_TAG))
					{
						//parseErrorHandler(currentElement, appender);
					}
					else if (currentElement.getTagName().equals(APPENDER_REF_TAG))
					{
						String refName = subst(currentElement.getAttribute(REF_ATTR));
						if (appender instanceof AppenderAttachable)
						{
							AppenderAttachable aa = (AppenderAttachable) appender;
							logger.debug("Attaching appender named [" + refName + "] to appender named ["
									+ appender.getName() + "].");
							aa.addAppender(findAppenderByReference(currentElement));
						}
						else
						{
							logger.error("Requesting attachment of appender named [" + refName
									+ "] to appender named [" + appender.getName()
									+ "] which does not implement org.apache.log4j.spi.AppenderAttachable.");
						}
					}
				}
			}
			propSetter.activate();
			return appender;
		}
		/*
		 * Yes, it's ugly. But all of these exceptions point to the same problem: we can't create an Appender
		 */
		catch (Exception oops)
		{
			logger.error("Could not create an Appender. Reported error follows.", oops);
			return null;
		}
	}

	
	/**
	 * Used internally to parse a filter element.
	 */
	protected void parseFilters(Element element, Appender appender)
	{
		String clazz = subst(element.getAttribute(CLASS_ATTR));
		Filter filter = (Filter) OptionConverter.instantiateByClassName(clazz, Filter.class, null);

		if (filter != null)
		{
			PropertySetter propSetter = new PropertySetter(filter);
			NodeList children = element.getChildNodes();
			final int length = children.getLength();

			for (int loop = 0; loop < length; loop++)
			{
				Node currentNode = children.item(loop);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element currentElement = (Element) currentNode;
					String tagName = currentElement.getTagName();
					if (tagName.equals(PARAM_TAG))
					{
						setParameter(currentElement, propSetter);
					}
				}
			}
			propSetter.activate();
			logger.debug("Adding filter of type [" + filter.getClass() + "] to appender named [" + appender.getName()
					+ "].");
			appender.addFilter(filter);
		}
	}


	/**
	 * Used internally to parse a layout element.
	 */
	protected Layout parseLayout(Element layout_element)
	{
		String className = subst(layout_element.getAttribute(CLASS_ATTR));
		logger.debug("Parsing layout of class: \"" + className + "\"");
		try
		{
			Object instance = Loader.loadClass(className).newInstance();
			Layout layout = (Layout) instance;
			PropertySetter propSetter = new PropertySetter(layout);

			NodeList params = layout_element.getChildNodes();
			final int length = params.getLength();

			for (int loop = 0; loop < length; loop++)
			{
				Node currentNode = (Node) params.item(loop);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element currentElement = (Element) currentNode;
					String tagName = currentElement.getTagName();
					if (tagName.equals(PARAM_TAG))
					{
						setParameter(currentElement, propSetter);
					}
				}
			}

			propSetter.activate();
			return layout;
		}
		catch (Exception oops)
		{
			logger.error("Could not create the Layout. Reported error follows.", oops);
			return null;
		}
	}

	protected void setParameter(Element elem, PropertySetter propSetter)
	{
		String name = subst(elem.getAttribute(NAME_ATTR));
		String value = (elem.getAttribute(VALUE_ATTR));
		value = subst(OptionConverter.convertSpecialChars(value));
		propSetter.setProperty(name, value);
	}

	/**
	 * Read the configuration file <code>configFilename</code> if it exists. Moreover, a thread will be created that
	 * will periodically check if <code>configFilename</code> has been created or modified. The period is determined by
	 * the <code>delay</code> argument. If a change or file creation is detected, then <code>configFilename</code> is
	 * read to configure log4j.
	 * 
	 * @param configFilename A log4j configuration file in XML format.
	 * @param delay The delay in milliseconds to wait between each check.
	 */
	private interface ParseAction
	{
		Document parse(final DocumentBuilder parser) throws SAXException, IOException;
	}

	private Document doConfigure(final String filename)
	{
		ParseAction action = new ParseAction()
		{
			public Document parse(final DocumentBuilder parser) throws SAXException, IOException
			{
				return parser.parse(new File(filename));
			}

			public String toString()
			{
				return "file [" + filename + "]";
			}
		};
		return doConfigure(action);
	}

	private final Document doConfigure(final ParseAction action)
			throws FactoryConfigurationError
	{
		DocumentBuilderFactory dbf = null;
		try
		{
			logger.debug("System property is :" + OptionConverter.getSystemProperty(dbfKey, null));
			dbf = DocumentBuilderFactory.newInstance();
			logger.debug("Standard DocumentBuilderFactory search succeded.");
			logger.debug("DocumentBuilderFactory is: " + dbf.getClass().getName());
		}
		catch (FactoryConfigurationError fce)
		{
			Exception e = fce.getException();
			logger.debug("Could not instantiate a DocumentBuilderFactory.", e);
			throw fce;
		}

		try
		{
			dbf.setValidating(true);

			DocumentBuilder docBuilder = dbf.newDocumentBuilder();

			docBuilder.setErrorHandler(new SAXErrorHandler());
			docBuilder.setEntityResolver(new Log4jEntityResolver());

			Document doc = action.parse(docBuilder);
			//parse(doc.getDocumentElement());
			return doc;
		}
		catch (Exception e)
		{
			// I know this is miserable...
			logger.error("Could not parse " + action.toString() + ".", e);
		}
		return null;
	}


	private Element getRoot(Element element)
	{
		String tagName = null;
		Element currentElement = null;
		Node currentNode = null;
		NodeList children = element.getChildNodes();
		final int length = children.getLength();

		for (int loop = 0; loop < length; loop++)
		{
			currentNode = children.item(loop);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE)
			{
				currentElement = (Element) currentNode;
				tagName = currentElement.getTagName();

				if (tagName.equals(ROOT_TAG))
				{
					break;
				}

			}
		}

		return currentElement;
	}
	
	private List<Appender> getRootAppenerList(Element root)
	{
		List<Appender> appenderList = new ArrayList<Appender>();
		NodeList children = root.getChildNodes();
		final int length = children.getLength();

		for (int loop = 0; loop < length; loop++)
		{
			Node currentNode = children.item(loop);

			if (currentNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element currentElement = (Element) currentNode;
				String tagName = currentElement.getTagName();

				if (tagName.equals(APPENDER_REF_TAG))
				{
					Element appenderRef = (Element) currentNode;
					Appender appender = findAppenderByReference(appenderRef);
					String refName = subst(appenderRef.getAttribute(REF_ATTR));
					if (appender != null)
						logger.debug("get appender named [" + refName + "].");
					else
						logger.debug("Appender named [" + refName + "] not found.");

					appenderList.add(appender);

				}
			}
		}
		
		return appenderList;
	}
	
	public static DailyRollingFileAppender getRootAppender(String fileName)
	{
		ServerLogConfig configurator = new ServerLogConfig();
		Document doc = configurator.doConfigure(fileName);
		Element root = configurator.getRoot(doc.getDocumentElement());
		if (root != null)
		{
			List<Appender> list = configurator.getRootAppenerList(root);
			for (Appender appender : list)
			{
				if (appender instanceof DailyRollingFileAppender)
				{

					DailyRollingFileAppender rollingAppender = (DailyRollingFileAppender) appender;

					return rollingAppender;
				}
			}
		}

		return null;
	}
	


}

/*
*$Log: av-env.bat,v $
*/
