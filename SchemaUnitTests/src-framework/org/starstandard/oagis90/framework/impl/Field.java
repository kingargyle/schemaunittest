/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
 
package org.starstandard.oagis90.framework.impl;

import org.starstandard.schemastest.framework.IElement;
import org.starstandards.xmlunit.help.*;
import org.custommonkey.xmlunit.*;


/**
 * This Class represents a OAGIS 9.0 field.
 * @author David Carver
 * @version 1.0
 * 
 *
 */
public class Field extends OAGISResource implements IElement {

	private String fieldName;
	private String dataType;
	
	/**
	 * 
	 */
	public Field() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IElement#getDataType()
	 */
	public String getDataType() {
		return dataType;
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IElement#setDataType(java.lang.String)
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IElement#getFieldName()
	 */
	public String getFieldName() {
		return fieldName;
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IElement#setFieldName(java.lang.String)
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IElement#validate()
	 */
	public void validate() throws Exception {
		XPathFieldHelper xpath = new XPathFieldHelper();
		String xpString = "";

			// Check for the Field
			xpString = xpath.elementName(this.fieldName);
			XMLAssert.assertXpathExists(xpString, this.getXsdSourceDocument());
			
			// Check for Data Type
			xpString = xpath.elementDataType(this.fieldName, this.dataType);
			XMLAssert.assertXpathExists(xpString, this.getXsdSourceDocument());

			// Make sure Documentation Exists
			xpString = xpath.elementDocs(this.fieldName);
			XMLAssert.assertXpathExists(xpString, this.getXsdSourceDocument());
		
	}
}
