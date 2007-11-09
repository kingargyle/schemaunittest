/***************************************************************************
 *  This code is licensed under the Eclipse Public License 1.0.  See
 *  License.txt for more information.
 *   
 *   Contributor(s):
 *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
 ****************************************************************************/
package org.starstandard.schemastest.framework;

import java.util.ArrayList;

public interface ICodeList {

	/**
	 * Rest the code list values.
	 *
	 */
	public abstract void reset();

	/**
	 * Add enumerated values
	 * @param enumValue
	 */
	public abstract void addEnumeration(String enumValue);

	/**
	 * Add code list names to check on unions
	 * @param codeListValue
	 */
	public abstract void addCodeList(String codeListValue);

	public abstract void validate() throws Exception;

	/**
	 * @return the baseName
	 */
	public abstract String getBaseName();

	/**
	 * @param baseName the baseName to set
	 */
	public abstract void setBase(String baseName);

	/**
	 * @return the codeListName
	 */
	public abstract String getName();

	/**
	 * @param codeListName the codeListName to set
	 */
	public abstract void setName(String codeListName);

	/**
	 * @return the codeLists
	 */
	public abstract ArrayList getCodeLists();

	/**
	 * @param codeLists the codeLists to set
	 */
	public abstract void setCodeLists(ArrayList codeLists);

	/**
	 * @return the enumeratedValues
	 */
	public abstract ArrayList getEnumeratedValues();

	/**
	 * @param enumeratedValues the enumeratedValues to set
	 */
	public abstract void setEnumeratedValues(ArrayList enumeratedValues);

}