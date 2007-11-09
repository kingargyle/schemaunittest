/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandard.schemastest.framework;

import org.w3c.dom.Document;

public interface IXSDResource {

	/**
	 * @return Returns the xsdSourceDocument.
	 */
	public abstract Document getXsdSourceDocument();

	public abstract void getSchemaFile(String inputFile) throws Exception;

	/**
	 * Allows a DOM object to be passed for validation.
	 * @param xsdSourceDocument The xsdSourceDocument to set.
	 */
	public abstract void setXsdSourceDocument(Document xsdSourceDocument);

}