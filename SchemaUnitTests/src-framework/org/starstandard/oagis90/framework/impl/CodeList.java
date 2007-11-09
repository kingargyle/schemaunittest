/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - STAR
 ****************************************************************************/

package org.starstandard.oagis90.framework.impl;
 
import org.custommonkey.xmlunit.XMLTestCase;
import org.starstandards.xmlunit.help.XPathCodeListHelper;
import org.starstandard.oagis90.framework.impl.NamingDesignRules;
import org.starstandard.schemastest.framework.ICodeList;
import org.custommonkey.xmlunit.XMLAssert;
import java.util.*;

public class CodeList extends OAGISResource implements ICodeList {

	private ArrayList enumeratedValues = new ArrayList();
	private ArrayList codeLists = new ArrayList();
	private String codeListName = null;
	private String baseName = null;
	
	
	
	public CodeList() {
		super();
		// TODO Auto-generated constructor stub
	}


	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#reset()
	 */
	public void reset() {
		this.codeLists = null;
		this.codeListName = null;
		this.baseName = null;
		this.enumeratedValues = null;
		
		codeLists = new ArrayList();
		enumeratedValues = new ArrayList();
		
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#addEnumeration(java.lang.String)
	 */
	public void addEnumeration(String enumValue) {
		if (enumValue != null) {
			this.enumeratedValues.add(enumValue);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#addCodeList(java.lang.String)
	 */
	public void addCodeList(String codeListValue) {
		if (codeListValue != null) {
			this.codeLists.add(codeListValue);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#validate()
	 */
	public void validate() throws Exception {

		if (this.codeListName == null) {
			XMLTestCase.fail("Code list name is NULL");
		}
		
		if (NamingDesignRules.checkComponentName(this.codeListName) == false) {
			XMLTestCase.fail("Component name does not end with the word Type");
		}
		
		if (codeLists.isEmpty() && this.enumeratedValues.isEmpty()) {
			XMLTestCase.fail("A set of Code List or Enumerated Values must be specified.");
		}
		
		if (codeLists.size() > 0) {
			this.checkUnions();
		} else {
			this.checkEnumerations();
		}
	}

	/**
	 * Evaluate the list of unions and make sure that the value exists.
	 * @throws Exception
	 */
	private void checkUnions() throws Exception {
		XPathCodeListHelper xpathHelper = new XPathCodeListHelper();
		String xpath = null;

	    // Check all the fields.
		for (Iterator codeListValue = this.codeLists.iterator(); codeListValue.hasNext();) {
			String unionValue = (String) codeListValue.next();
			
			if (unionValue != null) {
				xpath = xpathHelper.xpathUnionValueExists(this.getName(), unionValue);
				XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
			}
		}		
	}

	private void checkEnumerations() throws Exception {
		XPathCodeListHelper xpathHelper = new XPathCodeListHelper();
		String xpath = null;
		
		if (this.baseName == null) {
			XMLTestCase.fail("Restriction base must be specified.");
		}

	    // Check all the fields.
		for (Iterator enumListValue = this.enumeratedValues.iterator(); enumListValue.hasNext();) {
			String enumValue = (String) enumListValue.next();
			
			if (enumValue != null) {
				xpath = xpathHelper.xpathEnumerationValueExists(this.getName(), enumValue);
				XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
			}
		}		
	}
	
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#getBaseName()
	 */
	public String getBaseName() {
		return baseName;
	}



	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#setBase(java.lang.String)
	 */
	public void setBase(String baseName) {
		this.baseName = baseName;
	}



	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#getName()
	 */
	public String getName() {
		return codeListName;
	}



	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#setName(java.lang.String)
	 */
	public void setName(String codeListName) {
		this.codeListName = codeListName;
	}



	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#getCodeLists()
	 */
	public ArrayList getCodeLists() {
		return codeLists;
	}



	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#setCodeLists(java.util.ArrayList)
	 */
	public void setCodeLists(ArrayList codeLists) {
		this.codeLists = codeLists;
	}



	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#getEnumeratedValues()
	 */
	public ArrayList getEnumeratedValues() {
		return enumeratedValues;
	}



	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.ICodeList#setEnumeratedValues(java.util.ArrayList)
	 */
	public void setEnumeratedValues(ArrayList enumeratedValues) {
		this.enumeratedValues = enumeratedValues;
	}

}
