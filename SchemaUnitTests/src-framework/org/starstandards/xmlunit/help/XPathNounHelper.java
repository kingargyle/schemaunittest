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
 * This is an XML Unit Helper class for the Nouns in the STAR Standards. It Can
 * be used to help further validate, the STAR schemas to make sure that they are
 * being setup correctly.
 * 
 */
public class XPathNounHelper {
	// This is a test change
	protected String nounFileName = null;

	/**
	 * Noun Helper constructor
	 */
	public XPathNounHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retrieves the noun's FileName
	 * 
	 * @return Returns the nounFileName.
	 */
	public String getNounFileName() {
		return nounFileName;
	}

	/**
	 * Sets the noun filename.
	 * 
	 * @param nounFileName
	 *            The nounFileName to set.
	 */
	public void setNounFileName(String nounFileName) {
		this.nounFileName = nounFileName;
	}

	/**
	 * Gets a DOM representation of the Noun.
	 * 
	 * @return Returns a DOM representation of a Noun.
	 */

	public Document getNounsXML() {
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
			returnValue = builder.parse(new File(this.nounFileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnValue;
	}

	/**
	 * Checks to see if a Noun/Bod exists.
	 * 
	 * @param compName
	 *            The Noun Name to find.
	 * @return The XPath String
	 */
	public String xpathNounComplex(String compName) {
		return "*/xsd:complexType[@name='" + compName + "']";
	}

	/**
	 * Does the Noun/Bod have documentation
	 * 
	 * @param compName
	 *            The Noun/Bod component name
	 * @return The XPath String
	 */
	
	public String xpathNounChoiceSequenceElement(String compName, String elementName, String type) {
		return xpathNounComplex(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
		    "' and @type = '" + type + "']";
	}
	
	public String xpathNounChoiceSequenceElementOptional(String compName, String elementName, String type) {
		return xpathNounComplex(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @minOccurs = '0']";
	}

	public String xpathNounChoiceSequenceElementMaxOccurs(String compName, String elementName, String type, String occurs) {
		return xpathNounComplex(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @maxOccurs = '" + occurs + "']";
	}

	public String xpathNounChoiceSequenceElementMinOccurs(String compName, String elementName, String type, String occurs) {
		return xpathNounComplex(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @minOccurs = '" + occurs + "']";
	}
	
	public String xpathNounChoiceSequenceElementRequired(String compName, String elementName, String type) {
		return xpathNounComplex(compName) + "/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and (string-length(@minOccurs) = 0 or @minOccurs > 0)]";
	}

	public String xpathNounSequenceChoiceSequenceElement(String compName, String elementName, String type) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
		    "' and @type = '" + type + "']";
	}
	
	public String xpathNounSequenceChoiceSequenceElementOptional(String compName, String elementName, String type) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @minOccurs = '0']";
	}
	
	public String xpathNounSequenceChoiceSequenceElementMaxOccurs(String compName, String elementName, String type, String occurs) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @maxOccurs = '" + occurs + "']";
	}
	
	public String xpathNounSequenceChoiceSequenceElementMinOccurs(String compName, String elementName, String type, String occurs) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and @minOccurs = '" + occurs + "']";
	}
	
	public String xpathNounSequenceChoiceSequenceElementRequired(String compName, String elementName, String type) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:choice/xsd:sequence/xsd:element[@name = '" + elementName +
	    "' and @type = '" + type + "' and (string-length(@minOccurs) = 0 or @minOccurs > 0)]";
	}

	
	
	public String xpathComponentDoc(String compName) {
		return xpathNounComplex(compName) + "/xsd:annotation/xsd:documentation";
	}

	/**
	 * Does the Element exist in the Noun/Bod
	 * 
	 * @param elementName
	 *            The Element Name
	 * @return The XPath String
	 */
	public String xpathNounElement(String elementName) {
		return "*/xsd:element[@name = '" + elementName + "']";
	}

	/**
	 * Does the Element's Type attribute exist and defined.
	 * 
	 * @param elementName
	 *            The Element Name
	 * @param type
	 *            The Element's Type
	 * @return The XPath String
	 */
	public String xpathNounElement(String elementName, String type) {
		return "*/xsd:element[@name = '" + elementName + "' and @type = '"
				+ type + "']";
	}

	/**
	 * Does the Element Documentation exist.
	 * 
	 * @param elementName
	 *            The Element Name
	 * @param type
	 *            The Element's Type
	 * @return The XPath String
	 */
	public String xpathNounElementDoc(String elementName, String type) {
		return "*/xsd:element[@name = '" + elementName + "' and @type = '"
				+ type + "']/xsd:annotation/xsd:documentation";
	}

	/**
	 * Does the Complext Extension Base exist.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Base Name
	 * 
	 * @return The XPath String
	 */
	public String xpathCompExtenBase(String compName, String baseName) {
		return xpathNounComplex(compName) + "/xsd:complexContent/xsd:extension"
				+ "[@base = '" + baseName + "']";
	}

	/**
	 * Does the component element name.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Base Name
	 * 
	 * @return The XPath String.
	 */
	public String xpathCompExtElement(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[@name = '" + elementName
				+ "' or @ref='" + elementName + "']";
	}

	/**
	 * Does the component element type exist.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Base Name
	 * @param type
	 *            Type
	 * @return The XPath String
	 */
	public String xpathCompExtElementType(String compName, String baseName,
			String type) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[@type = '" + type + "']";
	}

	/**
	 * Is the Element Required
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Base Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompExtElementRequired(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName
				+ "' or @ref='" + elementName + "') "
				+ " and @minOccurs != '0']";
	}

	/**
	 * The Element is optional.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Base Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompExtElementOptional(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName
				+ "' or @ref='" + elementName + "') "
				+ " and @minOccurs = '0']";
	}

	/**
	 * Element - Max Occurs
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Base Name
	 * @param elementName
	 *            Element Name
	 * @param maxOccurs
	 *            Max Occurs (i.e. "unbounded", "1", "10")
	 * @return The XPath String
	 */
	public String xpathCompExtElementMaxOccurs(String compName,
			String baseName, String elementName, String maxOccurs) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName
				+ "' or @ref='" + elementName + "') and " + "@maxOccurs = '"
				+ maxOccurs + "']";
	}

	/**
	 * Does the Element Documentation exist.
	 * 
	 * @param compName
	 *            Component Name
	 * @param baseName
	 *            Base Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompExtElementDoc(String compName, String baseName,
			String elementName) {
		return xpathCompExtenBase(compName, baseName)
				+ "/xsd:sequence/xsd:element[@name = '" + elementName
				+ "' or @ref='" + elementName
				+ "']/xsd:annotation/xsd:documentation";
	}

	/**
	 * Component Element - no extension.
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompElement(String compName, String elementName) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:element[@name = '"
				+ elementName + "' or @ref='" + elementName + "']";
	}

	/**
	 * Component Element Type (no-extension).
	 * 
	 * @param compName
	 *            Component Name
	 * @param type
	 *            The Element Type
	 * @return The XPath String
	 */
	public String xpathCompElementType(String compName, String type) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:element[@type = '"
				+ type + "']";
	}

	/**
	 * Component Element Required (no-extension).
	 * 
	 * @param compName
	 *            The Component Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompElementRequired(String compName, String elementName) {
		return xpathNounComplex(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName
				+ "' or @ref='" + elementName + "') "
				+ " and @minOccurs != '0']";
	}

	/**
	 * Component Element Optional.
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompElementOptional(String compName, String elementName) {
		return xpathNounComplex(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName
				+ "' or @ref='" + elementName + "') "
				+ " and @minOccurs = '0']";

	}

	/**
	 * Component Element Max Occurs (no-extension)
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @param maxOccurs
	 *            Max Occurs
	 * @return The XPath String
	 */
	public String xpathCompElementMaxOccurs(String compName,
			String elementName, String maxOccurs) {
		return xpathNounComplex(compName)
				+ "/xsd:sequence/xsd:element[(@name = '" + elementName
				+ "' or @ref='" + elementName + "') and " + "@maxOccurs = '"
				+ maxOccurs + "']";
	}

	/**
	 * Component Element Doc (no-extension).
	 * 
	 * @param compName
	 *            Component Name
	 * @param elementName
	 *            Element Name
	 * @return The XPath String
	 */
	public String xpathCompElementDoc(String compName, String elementName) {
		return xpathNounComplex(compName) + "/xsd:sequence/xsd:element[@name = '"
				+ elementName + "' or @ref='" + elementName
				+ "']/xsd:annotation/xsd:documentation";
	}

	/**
	 * Does the element have a key defined.
	 * 
	 * @param elementName
	 *            Element Name
	 * @param keyName
	 *            The Key Name
	 * @return The XPath String
	 */
	public String xpathNounElementKey(String elementName, String keyName) {
		return xpathNounElement(elementName) + "/xsd:key[@name = '" + keyName
				+ "']";
	}

	/**
	 * Check the Key Selctor XPath.
	 * 
	 * @param elementName
	 *            Element Name
	 * @param keyName
	 *            The Key Name
	 * @return The XPath String
	 */
	public String xpathNounElementKeySelector(String elementName,
			String keyName, String selector) {
		return xpathNounElement(elementName) + "/xsd:key[@name = '" + keyName
				+ "']/xsd:selector[@xpath = '" + selector + "']";
	}

	/**
	 * Check the the Key Field.
	 * 
	 * @param elementName
	 *            Element Name
	 * @param keyName
	 *            The Key Name
	 * @return The XPath String
	 */
	public String xpathNounElementKeyField(String elementName, String keyName,
			String fieldName) {
		return xpathNounElement(elementName) + "/xsd:key[@name = '" + keyName
				+ "']/xsd:field[@xpath = '" + fieldName + "']";
	}

	/**
	 * Does the element have the keyref defined.
	 * 
	 * @param elementName
	 *            Element Name
	 * @param keyRefName
	 *            The Key Reference
	 * @return The XPath String
	 */
	public String xpathNounElementKeyRef(String elementName, String keyRefName) {
		return xpathNounElement(elementName) + "/xsd:keyref[@name = '"
				+ keyRefName + "']";
	}

	/**
	 * Locate the KeyReference and make sure the Refering field exists
	 * 
	 * @param elementName
	 *            Element Name
	 * @param keyRefName
	 *            The Key Reference
	 * @param refersTo
	 *            The Key that is being refered to
	 */
	public String xpathNounElementKeyRefRefers(String elementName,
			String keyRefName, String refersTo) {
		return xpathNounElement(elementName) + "/xsd:keyref[@name = '"
				+ keyRefName + "' and @refer = '" + refersTo + "']";
	}

	/**
	 * Check the Key Ref Selctor XPath.
	 * 
	 * @param elementName
	 *            Element Name
	 * @param keyRefName
	 *            The Key Reference
	 * @param selector
	 *            The XPATH Selector
	 * @return The XPath String
	 */
	public String xpathNounElementKeyRefSelector(String elementName,
			String keyRefName, String selector) {
		return xpathNounElement(elementName) + "/xsd:keyref[@name = '"
				+ keyRefName + "']/xsd:selector[@xpath = '" + selector + "']";
	}

	/**
	 * Check the the Key Ref Field.
	 * 
	 * @param keyRefName
	 *            The Key Reference Name
	 * @return The XPath String
	 */
	public String xpathNounElementKeyRefField(String elementName,
			String keyRefName, String fieldName) {
		return xpathNounElement(elementName) + "/xsd:keyref[@name = '"
				+ keyRefName + "']/xsd:field[@xpath = '" + fieldName + "']";
	}
}
