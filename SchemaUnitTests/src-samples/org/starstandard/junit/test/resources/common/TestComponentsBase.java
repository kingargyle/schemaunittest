/***************************************************************************
 *     Version: MPL 1.1
 *   
 *   The contents of this file are subject to the Mozilla Public License Version 
 *   1.1 (the "License"); you may not use this file except in compliance with 
 *   the License. You may obtain a copy of the License at 
 *   http://www.mozilla.org/MPL/
 *   
 *   Software distributed under the License is distributed on an "AS IS" basis,
 *   WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *   for the specific language governing rights and limitations under the
 *   License.
 *   
 *   The Original Code is  org.starstandard.junit.test.resources.common.TestComponentsBase.java
 *   
 *   The Initial Developer of the Original Code is
 *   Standards for Technology in Automotive Retail.
 *   Portions created by the Initial Developer are Copyright (C) 2006.
 *   Standards for Technology in Automotive Retail. All Rights Reserved.
 *   
 *   Contributor(s):
 *   David Carver - STAR
 *   ========================================================================
 *   DC     20061221     Corrected Path Seperator for platform independence
 ****************************************************************************/

package org.starstandard.junit.test.resources.common;

import org.custommonkey.xmlunit.XMLTestCase;
import org.starstandards.xmlunit.help.StarPropertiesLoader;
import org.starstandard.oagis90.framework.impl.Component;
import java.io.File; // Touch

public class TestComponentsBase extends XMLTestCase {

	protected String componentFileName = StarPropertiesLoader
			.getStarPath("MainSTARPath")
			+ "Resources" + File.separator
			+ "Components" + File.separator
			+ "Common" + File.separator 
			+ "Components.xsd";

	protected Component component = new Component();

	public TestComponentsBase() {
		super();
	}

	public TestComponentsBase(String name) {
		super(name);
	}

	protected void setUp() {
		try {
			component.getSchemaFile(componentFileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void tearDown() {
		component = null;
	}
	
}
