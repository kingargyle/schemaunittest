/**
 * 
 */
package org.custommonkey.xmlunit;

import javax.xml.validation.*;
import org.custommonkey.xmlunit.jaxp13.*;
import javax.xml.transform.stream.*;
import java.io.*;


/**
 * Will take in a W3C XSD and make sure it compiles with out error.
 * @author dcarver
 *
 */
public class SchemaValidator {
	
	private SchemaFactory factory = null;
	private Schema schema = null;
	
	public SchemaValidator(String schemaIn) throws Exception {
		compileSchema(schemaIn);
	}

	private void compileSchema(String schemaIn) throws Exception {
		factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		factory.setErrorHandler(new SchemaErrorHandler());
		schema = factory.newSchema(new StreamSource(new File(schemaIn)));
	}
	
	/*
	 * Sets the W3C XML Schema Parser to use. If not set it will defaul to the system
	 * default parser which is the Xerces2 parser.
	 * 
	 * To set SAXON-SA as the parser pass it com.saxonica.schema.SchemaFactoryImpl
	 * 
	 */
	public static void setSchemaParserProperty(String schemaParserURL) {
		System.setProperty("javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema", schemaParserURL);
	}
	
	public static String getSchemaParserProperty() {
		return System.getProperty("javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema");
	}
}
