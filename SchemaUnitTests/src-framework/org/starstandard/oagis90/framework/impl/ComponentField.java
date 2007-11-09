/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandard.oagis90.framework.impl;
 
/**
 * @author dcarver
 *
 */
public class ComponentField extends Field {

	private String minOccurs = null;
	private String maxOccurs = null;
	
	/**
	 * 
	 */
	public ComponentField() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the maxOccurs.
	 */
	public String getMaxOccurs() {
		return maxOccurs;
	}

	/**
	 * @param maxOccurs The maxOccurs to set.
	 */
	public void setMaxOccurs(String maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	/**
	 * @return Returns the minOccurs.
	 */
	public String getMinOccurs() {
		return minOccurs;
	}

	/**
	 * @param minOccurs The minOccurs to set.
	 */
	public void setMinOccurs(String minOccurs) {
		this.minOccurs = minOccurs;
	}

}
