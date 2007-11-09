/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandard.oagis90.framework.impl;
 

public class NamingDesignRules {

	static public boolean checkComponentName(String componentName) {
		boolean returnValue = false;
		if (componentName != null && componentName.length() > 0) {
			if (componentName.endsWith("Type")) {
				returnValue = true;
			}
		}
		
		return returnValue;
	}
}
