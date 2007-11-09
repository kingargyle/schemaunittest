/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/

package org.starstandards.xmlunit.help;
 
import java.io.File; // Touch

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.custommonkey.xmlunit.XMLUnit;
import org.w3c.dom.Document;

/**
 * @author dcarver
 * 
 */
public class XPathComponentHelper {

	private String componentsFileName = null;

	/**
	 * The XPathComponent Helper Class.
	 * 
	 */
	public XPathComponentHelper() {
		super();
	}

	/**
	 * Builds the XPath Statement to retrieve a Component by Name
	 * 
	 * @param compName
	 *            The name of the component to return
	 * @return The XPath string
	 */
	public String xpathComponentName(String compName) {
		return "*/xsd:complexType[@name='" + compName + "']";
	}

	/**
	 * Builds the XPath Statement to retrieve the Component Documentation
	 * 
	 * @param compName
	 * @return The XPath string
	 */
	public String xpathComponentDoc(String compName) {
		return xpathComponentName(compName) + "/xsd:annotation/xsd:documentation";
	}

	/**
	 * Builds the XPath Statement to retrieve an element by name from a
	 * component
	 * 
	 * @param compName
	 *            The component name to that contains the element
	 * @param elementName
	 *            The element name
	 * @return The XPath string
	 */
	public String xpathCompElement(String compName, String elementName) {
		return xpathComponentName(compName)
				+ "/xsd:sequence/xsd:element[@name = '" + elementName + "' or @ref = '" + elementName + "']";
	}

	/**
	 * Builds the XPath string to retrieve the Elements Type
	 * 
	 * @param compName
	 *            The component name
	 * @param elementName
	 *            Element Name
	 * @param baseTypeName
	 *            Type Name to retrieve
	 * @return The XPath String
	 */
	public String xpathCompElementType(String compName, String elementName,
			String baseTypeName) {
		return xpathComponentName(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "') and "
				+ "@type = '" + baseTypeName + "']";

	}

	/**
	 * Is the Component Element Required
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompElementRequired(String compName, String elementName) {
		return xpathComponentName(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "') and "
				+ " @minOccurs != '0']";
	}
	
	/**
	 * Is the minimum occurence value defined.
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @param minOccurs
	 *            Minumum Occurence
	 * @return The XPath String
	 */
	public String xpathCompElementMinOccurs(String compName, String elementName, String minOccurs) {
		return xpathComponentName(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "') and "
				+ " @minOccurs = '" + minOccurs + "']";
	}
	

	/**
	 * The Component Element is Optional
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */

	public String xpathCompElementOptional(String compName, String elementName) {
		return xpathComponentName(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "') and "
				+ " @minOccurs = '0']";
	}

	/**
	 * Element has a Max Occurs element equal to the value passed.
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @param maxOccurs
	 *            The max occurs value expected (i.e. unbounded)
	 * @return The XPath String
	 */
	public String xpathCompElementMaxOccurs(String compName,
			String elementName, String maxOccurs) {
		return xpathComponentName(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "') and "
				+ "@maxOccurs = '" + maxOccurs + "']";
	}

	/**
	 * Does the element have the documentation element defined.
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompElementDoc(String compName, String elementName) {
		return xpathComponentName(compName)
				+ "/xsd:sequence/xsd:element[@name = '" + elementName
				+ "' or @ref = '" + elementName + "']/xsd:annotation/xsd:documentation";
	}

	/**
	 * The Component Extends another component.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            The component that is being used as the base for the extension
	 * @return The XPath string
	 */
	public String xpathCompExtenBase(String compName, String baseName) {
		return xpathComponentName(compName) + "/xsd:complexContent/xsd:extension"
				+ "[@base = '" + baseName + "']";
	}

	/**
	 * Extension: Does the Element Name exist in the Component
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Extension BaseName
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompExtElements(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[@name = '" + elementName + "' or @ref = '" + elementName + "']";
	}

	/**
	 * Extension: Does the Element specify a Type
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Extension BaseName
	 * @param type
	 *            The Complex or SimpleType
	 * @return The XPath String
	 */
	public String xpathCompExtElementType(String compName, String baseName,
			String type) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[@type = '" + type + "']";
	}

	/**
	 * Extension: Is the Element Required
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Extension BaseName
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompExtElementRequired(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "')"
				+ " and @minOccurs != '0']";
	}

	/**
	 * Extension: Is the Element Optional
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Extension BaseName
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompExtElementOptional(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "')"
				+ " and @minOccurs = '0']";
	}

	/**
	 * Extension: Element has a Max Occurs element equal to the value passed.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Extension BaseName
	 * @param elementName
	 *            Element Name
	 * @param maxOccurs
	 *            The MaxOccurs value.
	 * @return The XPath String
	 */
	public String xpathCompExtElementMaxOccurs(String compName,
			String baseName, String elementName, String maxOccurs) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "') and "
				+ "@maxOccurs = '" + maxOccurs + "']";
	}

	/**
	 * Extension: Element has a Max Occurs element equal to the value passed.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Extension BaseName
	 * @param elementName
	 *            Element Name
	 * @param maxOccurs
	 *            The MaxOccurs value.
	 * @return The XPath String
	 */
	public String xpathCompExtElementMinOccurs(String compName,
			String baseName, String elementName, String minOccurs) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName + "' or @ref = '" + elementName + "') and "
				+ "@minOccurs = '" + minOccurs + "']";
	}
	
	/**
	 * Extension: Does the Element contain the Documentation
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Extension BaseName
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompExtElementDoc(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[@name = '" + elementName + "' or @ref = '" + elementName 
				+ "']/xsd:annotation/xsd:documentation";
	}

	/**
	 * Load the Schema for Unit Testing.
	 * 
	 * @return Returns a DOM Document.
	 */
	public Document getComponentsXML() {
		XMLUnit
				.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit
				.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit
				.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
		XMLUnit
				.setTransformerFactory("org.apache.xalan.processor.TransformerFactoryImpl");

		Document returnValue = null;
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		dbfactory.setNamespaceAware(true);

		try {
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			returnValue = builder.parse(new File(this.componentsFileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnValue;
	}

	/**
	 * 
	 * @return Returns the componentsFileName.
	 */
	public String getComponentsFileName() {
		return componentsFileName;
	}

	/**
	 * @param componentsFileName
	 *            The componentsFileName to set.
	 */
	public void setComponentsFileName(String componentsFileName) {
		this.componentsFileName = componentsFileName;
	}

	public String xpathChoiceSequenceElement(String compName, String elementName, String type) {
		return xpathComponentName(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
		    "' and @type = '" + type + "']";
	}
	
	public String xpathChoiceSequenceElementOptional(String compName, String elementName, String type) {
		return xpathComponentName(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @minOccurs = '0']";
	}

	public String xpathChoiceSequenceElementMaxOccurs(String compName, String elementName, String type, String occurs) {
		return xpathComponentName(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @maxOccurs = '" + occurs + "']";
	}

	public String xpathChoiceSequenceElementMinOccurs(String compName, String elementName, String type, String occurs) {
		String xpath = null;
		xpath = xpathComponentName(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName + "'";
		if (type != null) {
			xpath = xpath + " and @type = '" + type + "'";
		}
		xpath = xpath + " and @minOccurs = '" + occurs + "']";
		return xpath;
	}

	
	public String xpathChoiceSequenceElementRequired(String compName, String elementName, String type) {
		return xpathComponentName(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and (string-length(@minOccurs) = 0 or @minOccurs > 0)]";
	}

	public String xpathSequenceChoiceSequenceElement(String compName, String elementName, String type) {
		String xpath = null;
		xpath = xpathComponentName(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[(@name = '" + elementName +
	    "' or @ref = '" + elementName + "')";
		if (type != null) {
		   xpath = xpath + " and @type = '" + type + "'";
		}
		xpath = xpath + "]";
		
		return xpath;
	}
	
	public String xpathSequenceChoiceSequenceElementOptional(String compName, String elementName, String type) {
		String xpath = null;
		xpath = xpathComponentName(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[(@name = '" + elementName +
	    "' or @ref = '" + elementName + "')";
		if (type != null) {
		   xpath = xpath + " and @type = '" + type + "'";
		}
		xpath = xpath + " and  @minOccurs = '0']";
		
		return xpath;
	}
	
	public String xpathSequenceChoiceSequenceElementMaxOccurs(String compName, String elementName, String type, String occurs) {
		String xpath = null;
		xpath = xpathComponentName(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[(@name = '" + elementName +
	    "' or @ref = '" + elementName + "')";
		if (type != null) {
		   xpath = xpath + " and @type = '" + type + "'";
		}
		xpath = xpath + " and @maxOccurs = '" + occurs + "']";
		
		return xpath;
	}
	
	public String xpathSequenceChoiceSequenceElementMinOccurs(String compName, String elementName, String type, String occurs) {
		String xpath = null;
		xpath = xpathComponentName(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[(@name = '" + elementName +
	    "' or @ref = '" + elementName + "')";
		if (type != null) {
		   xpath = xpath + " and @type = '" + type + "'";
		}
		xpath = xpath + " and @minOccurs = '" + occurs + "']";
		
		return xpath;
	}
	
	public String xpathSequenceChoiceSequenceElementRequired(String compName, String elementName, String type) {
		return xpathComponentName(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[(@name = '" + elementName +
		    "' or @ref = '" + elementName + "') and @type = '" + type + "' and (string-length(@minOccurs) = 0 or @minOccurs > 0)]";
	}

	public String xpathSequenceChoiceSequenceElementDoc(String compName, String elementName) {
		return xpathComponentName(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
		    "' or @ref = '" + elementName + "']/xsd:annotation/xsd:documentation";
	}
	
}
