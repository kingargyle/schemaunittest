<?xml version="1.0" encoding="UTF-8"?>
<!--
    /***************************************************************************
    *  This code is licensed under the Eclipse Public License 1.0.  See
    *  License.txt for more information.
    *   
    *   Contributor(s):
    *   David Carver - Standards for Technology in Automotive Retail - Initial Code, 2007.
    ****************************************************************************/
    
 -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                version="1.0">
    <xsl:output method="text"/>
    
    <xsl:variable name="package">org.oagi.tests.resources.components.common.fields</xsl:variable>
    <xsl:variable name="className">TestOAGiFields</xsl:variable>

    <xsl:template match="/xsd:schema">
        <xsl:text>package </xsl:text>
        <xsl:value-of select="$package"/>
        <xsl:text>
                        
            import org.starstandard.junit.test.resources.common.TestFieldsBase;
            
            public class </xsl:text>
         <xsl:value-of select="$className"/>
         <xsl:text> extends TestFieldsBase {
            
            public static void main(String[] args) {
            junit.textui.TestRunner.run(TestFieldsA.class);
            }
        </xsl:text>
        
        <xsl:apply-templates select="xsd:element">
            <xsl:sort select="@name"/>
        </xsl:apply-templates>
        <xsl:text>
            }
        </xsl:text>
    </xsl:template>
    
    <xsl:template match="xsd:element">
        <xsl:text>
            /**
             *
             */
            public void test</xsl:text>
            <xsl:value-of select="@name"/>
            <xsl:text>Test() throws Exception {
                String fieldName = "</xsl:text>
                <xsl:value-of select="@name"/>
                <xsl:text>";
                String type = "</xsl:text>
                <xsl:value-of select="@type"/>
                <xsl:text>";
            
                field.setFieldName(fieldName);
                field.setDataType(type);
                field.validate();
            }
            
        </xsl:text>        
    </xsl:template>
</xsl:stylesheet>