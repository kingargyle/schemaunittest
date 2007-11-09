/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandard.schemastest.framework;

public interface IElement {

	/**
	 * @return Returns the dataType.
	 */
	public abstract String getDataType();

	/**
	 * @param dataType The dataType to set.  This should be in the format of
	 *                 nsp:dataType.  An example would be udt:CodeType
	 */
	public abstract void setDataType(String dataType);

	/**
	 * @return Returns the fieldName.
	 */
	public abstract String getFieldName();

	/**
	 * @param fieldName The fieldName to set.
	 */
	public abstract void setFieldName(String fieldName);

	public abstract void validate() throws Exception;

}