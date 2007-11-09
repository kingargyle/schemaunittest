/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandard.oagis90.framework.impl;
 
import org.custommonkey.xmlunit.XMLTestCase;
import org.starstandards.xmlunit.help.XPathComponentHelper;
import org.starstandard.oagis90.framework.impl.NamingDesignRules;
import org.starstandard.schemastest.framework.IComplexType;
import org.starstandard.schemastest.framework.IElement;
import org.custommonkey.xmlunit.XMLAssert;

import java.util.*;



public class Component extends OAGISResource implements IComplexType {

	private Field field = null;
	private ArrayList sequenceFields = new ArrayList();
	private ArrayList choiceSequenceFields = new ArrayList();
	private String componentName = "";
	private String extension = "";
	
	
	public Component() {
		super();
		// TODO Auto-generated constructor stub
		field = new Field();
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#addSequenceField(java.lang.String, java.lang.String)
	 */
	public void addSequenceField(String fieldName, String type) {
		ComponentField addField = new ComponentField();
		
			addField.setFieldName(fieldName);
			addField.setDataType(type);
			addField.setMaxOccurs(null);
			addField.setMinOccurs(null);
		
		sequenceFields.add(addField);
		
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#addChoiceField(java.lang.String, java.lang.String)
	 */
	public void addChoiceField(String fieldName, String type) {
		ComponentField addField = new ComponentField();
		
			addField.setFieldName(fieldName);
			addField.setDataType(type);
			addField.setMaxOccurs(null);
			addField.setMinOccurs(null);
		
		choiceSequenceFields.add(addField);
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#addSequenceField(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addSequenceField(String fieldName, String type, String minOccurs, String maxOccurs) {
		ComponentField addField = new ComponentField();
	
			addField.setFieldName(fieldName);
			addField.setDataType(type);
			addField.setMaxOccurs(maxOccurs);
			addField.setMinOccurs(minOccurs);
		
		sequenceFields.add(addField);
		
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#addSequenceField(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addSequenceField(String fieldName, String minOccurs, String maxOccurs) {
		ComponentField addField = new ComponentField();
		
		addField.setFieldName(fieldName);
		addField.setDataType(null);
		addField.setMaxOccurs(maxOccurs);
		addField.setMinOccurs(minOccurs);
	
		sequenceFields.add(addField);
		
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#addChoiceField(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addChoiceField(String fieldName, String type, String minOccurs, String maxOccurs) {
		ComponentField addField = new ComponentField();
	
			addField.setFieldName(fieldName);
			addField.setDataType(type);
			addField.setMaxOccurs(maxOccurs);
			addField.setMinOccurs(minOccurs);
		
		choiceSequenceFields.add(addField);
		
	}


	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#setField(java.lang.String, java.lang.String)
	 */
	public void setField(String fieldName, String type) {
		if (field == null) {
			field = new Field();
			field.setXsdSourceDocument(this.getXsdSourceDocument());
		}
		this.field.setFieldName(fieldName);
		this.field.setDataType(type);
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#validate()
	 */
	public void validate() throws Exception {
		this.checkSequence();
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#validate(int)
	 */
	public void validate(int testType) throws Exception {

		if (this.componentName == null) {
			XMLTestCase.fail("Component Name is NULL");
		}
		
		if (NamingDesignRules.checkComponentName(this.componentName) == false) {
			XMLTestCase.fail("Component name does not end with the word Type");
		}
		
		
		switch (testType) {
			case SIMPLE: { checkSequence();
				break;
			}
			case COMPLEX: {
				checkComplex();
				break;
			}
			
			case SIMPLECONTENT: {
				checkSimpleContent();
				break;
			}
			default:
				XMLTestCase.fail("Unknown Testing type provided.");
		}
	}
	
	private void checkSequence() throws Exception {
		XPathComponentHelper xpathHelper = new XPathComponentHelper();

		if (this.sequenceFields.isEmpty() == true && this.choiceSequenceFields.isEmpty() == true) {
			XMLTestCase.fail("Component has no fields");
		}

		String xpath = xpathHelper.xpathComponentName(this.componentName);
		XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
		
	    // Check all the fields.
		for (Iterator fields = sequenceFields.iterator(); fields.hasNext();) {
		    ComponentField seqField = (ComponentField) fields.next();
		    xpath = xpathHelper.xpathCompElement(this.componentName, seqField.getFieldName());
		    XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
		    //xpath = xpathHelper.xpathCompElementDoc(this.componentName, seqField.getFieldName());
		    //xmlTest.assertXpathExists(xpath, this.getXsdSourceDocument());
		    if (seqField.getMinOccurs() != null) {
		    	if (seqField.getMinOccurs().equalsIgnoreCase("0")) {
			    	xpath = xpathHelper.xpathCompElementOptional(this.componentName, seqField.getFieldName());
		    	} else {
		    		xpath = xpathHelper.xpathCompElementMinOccurs(this.componentName, seqField.getFieldName(), seqField.getMinOccurs());
		    	}
		    	XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());	
		    }
		    if (seqField.getMaxOccurs() != null) {
		    	xpath = xpathHelper.xpathCompElementMaxOccurs(this.componentName, seqField.getFieldName(), seqField.getMaxOccurs());
		    	XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
		    }
		}
		
		// Check all Choices
		
		if (choiceSequenceFields.isEmpty() == false) {
			for (Iterator fields = choiceSequenceFields.iterator(); fields.hasNext();) {
			    ComponentField choiceField = (ComponentField) fields.next();
			    xpath = xpathHelper.xpathSequenceChoiceSequenceElement(this.componentName, choiceField.getFieldName(), choiceField.getDataType());
			    XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
			    //xpath = xpathHelper.xpathSequenceChoiceSequenceElementDoc(this.componentName, choiceField.getFieldName());
			    //xmlTest.assertXpathExists(xpath, this.getXsdSourceDocument());
			    if (choiceField.getMinOccurs() != null) {
			    	if (choiceField.getMinOccurs().equalsIgnoreCase("0")) {
			    		xpath = xpathHelper.xpathSequenceChoiceSequenceElementOptional(this.componentName, choiceField.getFieldName(), choiceField.getDataType());
			    	} else {
			    		xpath = xpathHelper.xpathSequenceChoiceSequenceElementMinOccurs(this.componentName, choiceField.getFieldName(), choiceField.getDataType(), choiceField.getMinOccurs());
			    	}
			    	XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());	
			    }
			    if (choiceField.getMaxOccurs() != null) {
			    	xpath = xpathHelper.xpathSequenceChoiceSequenceElementMaxOccurs(this.componentName, choiceField.getFieldName(), choiceField.getDataType(), choiceField.getMaxOccurs());
			    	XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
			    }
			}
		}
	}

	/**
	 * Check the Complex Items.
	 * @throws Exception
	 */
	private void checkComplex() throws Exception {
		XPathComponentHelper xpathHelper = new XPathComponentHelper();


		if (this.extension == null) {
			XMLTestCase.fail("Extension base name is not set.");
		}
		
		String xpath = xpathHelper.xpathComponentName(this.componentName);
		XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
		
		xpath = xpathHelper.xpathCompExtenBase(this.componentName, this.extension);
		XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());

		if (this.sequenceFields.isEmpty() == true && this.choiceSequenceFields.isEmpty() == true ) {
			XMLTestCase.assertTrue(true);
		} else {
			
		    // Check all the fields.
			for (Iterator fields = sequenceFields.iterator(); fields.hasNext();) {
			    ComponentField seqField = (ComponentField) fields.next();
			    xpath = xpathHelper.xpathCompExtElements(this.componentName, this.extension, seqField.getFieldName());
			    XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
			    
			   // xpath = xpathHelper.xpathCompExtElementDoc(this.componentName, this.extension, seqField.getFieldName());
			   // xmlTest.assertXpathExists(xpath, this.getXsdSourceDocument());
			    
			    if (seqField.getMinOccurs() != null) {
			    	if (seqField.getMinOccurs().equalsIgnoreCase("0")) {
				    	xpath = xpathHelper.xpathCompExtElementOptional(this.componentName, this.extension, seqField.getFieldName());
			    	} else {
			    		xpath = xpathHelper.xpathCompExtElementMinOccurs(this.componentName, this.extension, seqField.getFieldName(), seqField.getMinOccurs());
			    	}
			    	XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());	
			    }
			    
			    if (seqField.getMaxOccurs() != null) {
			    	xpath = xpathHelper.xpathCompExtElementMaxOccurs(this.componentName, this.extension, seqField.getFieldName(), seqField.getMaxOccurs());
			    	XMLAssert.assertXpathExists(xpath, this.getXsdSourceDocument());
			    }
			}
		}
	}
	
	private void checkSimpleContent() throws Exception {
		if (this.extension == null) {
			XMLTestCase.fail("Extension base name is not set.");
		}
		
		// This will always pass as the NDR validation isn't coded yet.
		XMLTestCase.assertTrue(true);
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#getComponentName()
	 */
	public String getComponentName() {
		return componentName;
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#setComponentName(java.lang.String)
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#reset()
	 */
	public void reset() {
		this.componentName = "";
		this.field = null;
		this.sequenceFields = null;
		this.sequenceFields = new ArrayList();
		this.extension = null;
		this.choiceSequenceFields = null;
		this.choiceSequenceFields = new ArrayList();
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#getExtension()
	 */
	public String getExtension() {
		return extension;
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#setExtension(java.lang.String)
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/* (non-Javadoc)
	 * @see org.starstandard.oagis90.framework.impl.IComplexType#getField()
	 */
	public IElement getField() {
		return field;
	}
}
