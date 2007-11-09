/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
 
package org.starstandard.oagis90.framework.impl;

import java.io.File; 

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.SimpleNamespaceContext;
import org.starstandard.schemastest.framework.IXSDResource;
import org.w3c.dom.*;
import java.util.*;

public class OAGISResource implements IXSDResource {

	private Document xsdSourceDocument;
	
	public static String NAMESPACE_OAGI_9 = "http://www.openapplications.org/oagi/9";
	public static String NAMESPACE_STAR_5 = "http://www.starstandard.org/STAR/5";
	public static String NAMESPACE_XSD = "http://www.w3.org/2001/XMLSchema";
	
	public OAGISResource() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IXSDResource#getXsdSourceDocument()
	 */
	public Document getXsdSourceDocument() {
		return xsdSourceDocument;
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IXSDResource#getSchemaFile(java.lang.String)
	 */
	public void getSchemaFile(String inputFile) throws Exception {
		XMLUnit
				.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit
				.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit
				.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
		XMLUnit
				.setTransformerFactory("org.apache.xalan.processor.TransformerFactoryImpl");
		
		Map namespaceMap = new HashMap();
		namespaceMap.put("oagi", NAMESPACE_OAGI_9);
		namespaceMap.put("star", NAMESPACE_STAR_5);
		namespaceMap.put("xsd", NAMESPACE_XSD);
	
		XMLUnit.setXpathNamespaceContext(new SimpleNamespaceContext(namespaceMap));

		xsdSourceDocument = null;
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		dbfactory.setNamespaceAware(true);

		try {
			DocumentBuilder builder = dbfactory.newDocumentBuilder();
			xsdSourceDocument = builder.parse(new File(inputFile));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IXSDResource#setXsdSourceDocument(org.w3c.dom.Document)
	 */
	public void setXsdSourceDocument(Document xsdSourceDocument) {
		this.xsdSourceDocument = xsdSourceDocument;
	}

}
