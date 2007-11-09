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
 * @author David Carver
 * 
 */
/**
 * @author dcarver
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class XPathFieldHelper {

	private String fieldsFileName = null;

	private String dataTypesFileName = null;

	private String devBODsFileName = null;

	private String nounFileName = null;

	public String SOURCE_URI = "http://www.starstandard.org";

	/**
	 * Constructor.
	 */

	public XPathFieldHelper() {
		super();
	}

	public String elementName(String fieldName) {
		return "/xsd:schema/xsd:element[@name='" + fieldName + "']";
	}
	
	public String elementDataType(String fieldName, String dataType) {
		return "/xsd:schema/xsd:element[@name='" + fieldName + "' and @type='" + dataType + "']"; 
	}
	
	public String elementDocs(String fieldName) {
		return this.elementName(fieldName) + "/xsd:annotation/xsd:documentation";
	}
	
	/**
	 * Does the simple Field Exist.
	 * 
	 * @param fieldName
	 *            The Field Name
	 * @return The XPath Statement
	 */

	public String xpathSimpleTypeExists(String fieldName) {
		return "*/xsd:simpleType[@name='" + fieldName + "']";
	}

	/**
	 * Does the complex Field Exist.
	 * 
	 * @return The XPath String
	 */
	public String xpathComplexTypeExists(String fieldName) {
		return "*/xsd:complexType[@name='" + fieldName + "']";
	}

	/**
	 * Does the simple Field Documentation exist.
	 * 
	 * @param fieldName
	 *            The Field Name
	 * @return The XPath String
	 */
	public String xpathSimpleDocExists(String fieldName) {
		return xpathSimpleTypeExists(fieldName)
				+ "/xsd:annotation/xsd:documentation";
	}

	/**
	 * Does the complex Field Documentation Exist
	 * 
	 * @param fieldName
	 *            The Field Name
	 * @return The XPath String
	 */
	public String xpathComplexDocExists(String fieldName) {
		return xpathComplexTypeExists(fieldName)
				+ "/xsd:annotation/xsd:documentation";
	}

	/**
	 * Does the simple field Document Source Exist.
	 * 
	 * @param fieldName
	 *            The Field Name
	 * @return The XPath String
	 */
	public String xpathSimpleDocSource(String fieldName) {
		return xpathSimpleDocExists(fieldName) + "/@source";
	}

	/**
	 * Does the complex field Document Source Exist.
	 * 
	 * @param fieldName
	 *            The Field Name
	 * @return The XPath String
	 */
	public String xpathComplexDocSource(String fieldName) {
		return xpathComplexDocExists(fieldName) + "/@source";
	}

	/**
	 * Complex Simple Extension Field
	 * 
	 * @param fieldName
	 *            Field Name
	 * @param base
	 *            Base that it Extends
	 * @return The XPath String
	 */
	public String xpathComplexSimpExtensionExists(String fieldName, String base) {
		return xpathComplexTypeExists(fieldName)
				+ "/xsd:simpleContent/xsd:extension[@base = '" + base + "']";
	}

	/**
	 * Complex Simple Extension Field
	 * 
	 * @param fieldName
	 *            Field Name
	 * @param base
	 *            Base that it Extends
	 * @return The XPath String
	 */
	public String xpathComplexSimpRestrictionExists(String fieldName,
			String base) {
		return xpathComplexTypeExists(fieldName)
				+ "/xsd:simpleContent/xsd:restriction[@base = '" + base + "']";
	}

	/**
	 * Check for the Attribute that was added by Extension Checking for an
	 * attribute requires: 1. The fieldName 2. The Datatype being extended 3.
	 * The attribute name being created 4. The attribute Type 5. Whether the
	 * Attribute is required, true or false
	 * 
	 * @param fieldName
	 *            The Field Name
	 * @param base
	 *            Extension Nme
	 * @param name
	 *            Attribute Name
	 * @param type
	 *            Attribute Type
	 * @param required
	 *            True if the attribute use is required.
	 * @return The XPath String
	 */
	public String xpathComplexSimpExtAttrExists(String fieldName, String base,
			String name, String type, boolean required) {
		String xpath = null;
		xpath = xpathComplexSimpExtensionExists(fieldName, base);
		xpath = xpath + "/xsd:attribute[@name = '" + name + "' and @type = '"
				+ type + "'";
		if (required) {
			xpath = xpath + " and @use='required'";
		}
		xpath = xpath + "]";

		return xpathComplexTypeExists(fieldName)
				+ "/xsd:simpleContent/xsd:extension[@base = '" + base + "']";
	}

	/**
	 * Does the Datatype specified have a base element
	 * 
	 * @param fieldName
	 *            The Datatype Name
	 * @return The XPath String
	 */
	public String xpathDataType(String fieldName) {
		return xpathSimpleTypeExists(fieldName) + "/xsd:restriction/@base";
	}

	/**
	 * What is the restriction for the base DataType
	 * 
	 * @param fieldName
	 *            Datatype Name
	 * @param base
	 *            Base Name being restricted
	 * @return The XPath String
	 */
	public String xpathDataType(String fieldName, String base) {
		return xpathSimpleTypeExists(fieldName) + "/xsd:restriction[@base = '"
				+ base + "']";
	}

	/**
	 * Does the specified enumeration value exist.
	 * 
	 * @param fieldName
	 *            The Field or Datatype
	 * @param enumValue
	 *            The enumeration value
	 * @return The XPath String
	 */
	public String xpathEnumerationValueExists(String fieldName, String enumValue) {
		return this.xpathSimpleTypeExists(fieldName)
				+ "/xsd:restriction[xsd:enumeration/@value='" + enumValue + "']";
	}

	/**
	 * Does the specified enumeration value have documentation
	 * 
	 * @param fieldName
	 *            The Datatype or Field
	 * @param enumValue
	 *            The enumeration value
	 * @return The XPath String
	 */
	public String xpathEnumDocExists(String fieldName, String enumValue) {
		return this.xpathSimpleTypeExists(fieldName)
				+ "/xsd:restriction/xsd:enumeration[@value='" + enumValue
				+ "']/xsd:annotation/xsd:documentation";
	}

	/**
	 * Loads the XML Document
	 * 
	 * @return Returns a DOM representation of the Document
	 */
	public Document getFieldsXML() {
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
			returnValue = builder.parse(new File(fieldsFileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnValue;
	}

	/**
	 * Loads the DataTypes XSD
	 * 
	 * @return Returns a DOM representation of the DataTypes
	 */
	public Document getDataTypesXML() {
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
			returnValue = builder.parse(new File(this.dataTypesFileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnValue;
	}

	public Document getDevBodsXML() {
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
			returnValue = builder.parse(new File(this.devBODsFileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnValue;
	}

	/**
	 * @return Returns the dataTypesFileName.
	 */
	public String getDataTypesFileName() {
		return dataTypesFileName;
	}

	/**
	 * @param dataTypesFileName
	 *            The dataTypesFileName to set.
	 */
	public void setDataTypesFileName(String dataTypesFileName) {
		this.dataTypesFileName = dataTypesFileName;
	}

	/**
	 * @return Returns the devBODsFileName.
	 */
	public String getDevBODsFileName() {
		return devBODsFileName;
	}

	/**
	 * @param devBODsFileName
	 *            The devBODsFileName to set.
	 */
	public void setDevBODsFileName(String devBODsFileName) {
		this.devBODsFileName = devBODsFileName;
	}

	/**
	 * @return Returns the fieldsFileName.
	 */
	public String getFieldsFileName() {
		return fieldsFileName;
	}

	/**
	 * @param fieldsFileName
	 *            The fieldsFileName to set.
	 */
	public void setFieldsFileName(String fieldsFileName) {
		this.fieldsFileName = fieldsFileName;
	}

	/**
	 * @return Returns the nounFileName.
	 */
	public String getNounFileName() {
		return nounFileName;
	}

	/**
	 * @param nounFileName
	 *            The nounFileName to set.
	 */
	public void setNounFileName(String nounFileName) {
		this.nounFileName = nounFileName;
	}

}
