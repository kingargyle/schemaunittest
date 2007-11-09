/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandard.schemastest.framework;


public interface IComplexType {

	public static final int SIMPLE = 0;
	public static final int COMPLEX = 1;
	public static final int SIMPLECONTENT = 2;

	public abstract void addSequenceField(String fieldName, String type);

	public abstract void addChoiceField(String fieldName, String type);

	public abstract void addSequenceField(String fieldName, String type,
			String minOccurs, String maxOccurs);

	/**
	 * Adds the ability to define a field with just name, minOccurs, and MaxOccurs
	 * @
	 */
	public abstract void addSequenceField(String fieldName, String minOccurs,
			String maxOccurs);

	public abstract void addChoiceField(String fieldName, String type,
			String minOccurs, String maxOccurs);

	/**
	 * This is use to set a global field element that references the Component.
	 * This is equivalant to the ATG2 ASBIE but with a qualifer.
	 * @param fieldName
	 * @param type
	 */
	public abstract void setField(String fieldName, String type);

	public abstract void validate() throws Exception;

	public abstract void validate(int testType) throws Exception;

	/**
	 * @return Returns the componentName.
	 */
	public abstract String getComponentName();

	/**
	 * @param componentName The componentName to set.
	 */
	public abstract void setComponentName(String componentName);

	public abstract void reset();

	/**
	 * @return Returns the extension.
	 */
	public abstract String getExtension();

	/**
	 * @param extension The extension to set.
	 */
	public abstract void setExtension(String extension);

	/**
	 * @return Returns the field.
	 */
	public abstract IElement getField();

}