/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandards.xmlunit.help;

import java.util.HashMap;
import java.util.Map;

import org.custommonkey.xmlunit.SimpleNamespaceContext;
import org.custommonkey.xmlunit.XMLUnit;

/**
 * XMLUnitSetup is a helper class for setting up the namespaces and other requirements
 * for initializing XMLUnit.  This sets items like the XML Parser to use, the
 * Namespace contexts for STAR 4 and STAR 5, etc.
 * @author dcarver
 *
 */
public class XMLUnitSetup {
	public static String NAMESPACE_OAGI_9 = "http://www.openapplications.org/oagi/9";

	public static String NAMESPACE_STAR_4 = "http://www.starstandards.org/STAR";

	public static String NAMESPACE_XSD = "http://www.w3.org/2001/XMLSchema";

	public static void setupSTAR4() {
		XMLUnit
				.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit
				.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit
				.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
		XMLUnit
				.setTransformerFactory("org.apache.xalan.processor.TransformerFactoryImpl");

		Map namespaceMap = new HashMap();
		namespaceMap.put("star", XMLUnitSetup.NAMESPACE_STAR_4);
		namespaceMap.put("xsd", XMLUnitSetup.NAMESPACE_XSD);

		XMLUnit.setXpathNamespaceContext(new SimpleNamespaceContext(
				namespaceMap));

	}
}
