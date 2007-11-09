/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandards.xmlunit.help;

public class XPathCodeListHelper extends XPathFieldHelper {

	public XPathCodeListHelper() {
		super();
	}
	 
	public String xpathUnionValueExists(String codeListName, String unionValue) {
		return super.xpathSimpleTypeExists(codeListName)
				+ "/xsd:union[contains(@memberTypes, '" + unionValue + "')]";
	}
	
}
