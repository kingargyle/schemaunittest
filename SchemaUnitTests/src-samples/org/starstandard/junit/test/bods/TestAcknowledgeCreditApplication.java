package org.starstandard.junit.test.bods;

import org.starstandard.junit.test.resources.common.TestBODSBase;
import org.starstandard.oagis90.framework.impl.*;
import org.starstandards.xmlunit.help.StarPropertiesLoader;
import java.io.File; 

public class TestAcknowledgeCreditApplication extends TestBODSBase {

	private String bodFileName = StarPropertiesLoader.getStarPath("MainSTARPath")
			+ "BODs" + File.separator
			+ "Developer" + File.separator
			+ "AcknowledgeCreditApplication.xsd";

	protected void setUp() {
		try {
			component.getSchemaFile(bodFileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void tearDown() {
		component = null;
	}

	public void testProcessCreditApplicationASBIE() throws Exception {
		component.reset();
		component.setField("AcknowledgeCreditApplication",
				"AcknowledgeCreditApplicationType");
		component.getField().validate();
	}

	public void testProcessCreditApplicationDataAreaASBIE() throws Exception {
		component.reset();
		component.setField("AcknowledgeCreditApplicationDataArea",
				"AcknowledgeCreditApplicationDataAreaType");
		component.getField().validate();
	}

	public void testProcessCreditApplicationTypeTest() throws Exception {
		// Initialize the component
		component.reset();

		// Set the Component Name
		component.setComponentName("AcknowledgeCreditApplicationType");

		component.setExtension("BusinessObjectDocumentType");

		// Add the field requirements
		component.addSequenceField("AcknowledgeCreditApplicationDataArea", null,
				null, null);

		// Validate with parameters tells the type of validation to perform
		component.validate(Component.COMPLEX);

	}

	public void testAcknowledgeCreditApplicationDataAreaType() throws Exception {
		// Initialize the component
		component.reset();

		// Set the Component Name
		component.setComponentName("AcknowledgeCreditApplicationDataAreaType");

		// Add the field requirements
		component.addSequenceField("Acknowledge", null, null, null);
		component
				.addSequenceField("CreditApplication", null, null, "unbounded");

		// Validate with parameters tells the type of validation to perform
		component.validate(Component.SIMPLE);

	}
}