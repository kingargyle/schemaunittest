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

    <xsl:template match="/xsd:schema">
        <xsl:apply-templates select="xsd:element">
            <xsl:sort select="@name"/>
        </xsl:apply-templates>
        <xsl:apply-templates select="xsd:complexType"/>
    </xsl:template>
    
    <!-- Setup the Global Element Tests for the Components. -->
    <xsl:template match="xsd:element">
        <xsl:text>
            public void test</xsl:text>
            <xsl:value-of select="@name"/><xsl:text>ASBIE() throws Exception {
            component.reset();
            component.setField("</xsl:text>
            <xsl:value-of select="@name"/>
            <xsl:text>", "</xsl:text>
            <xsl:value-of select="@type"/>
            <xsl:text>");
            component.getField().validate();
            }
        </xsl:text>        
    </xsl:template>
    
    <xsl:template match="xsd:complexType">
        <xsl:choose>
            <xsl:when test="xsd:complexContent">
                <xsl:call-template name="ComplexTest"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="SimpleTest"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template name="ComplexTest">
        <xsl:text>public void test</xsl:text>
        <xsl:value-of select="@name"/>
        <xsl:text>Test() throws Exception {
        // Initialize the component
        component.reset();
        
        // Set the Component Name
        component.setComponentName("</xsl:text>
        <xsl:value-of select="@name"/>
        <xsl:text>");
        
        component.setExtension("</xsl:text>
        <xsl:value-of select="xsd:complexContent/xsd:extension/@base"/>
        <xsl:text>");
        
        // Add the field requirements
        </xsl:text>
        <xsl:for-each select="xsd:complexContent/xsd:extension/xsd:sequence/xsd:element">
            <xsl:text>component.addSequenceField("</xsl:text>
            <xsl:value-of select="@name | @ref"/>
            <xsl:text>", </xsl:text>
            <xsl:choose>
                <xsl:when test="@type">
                    <xsl:text>"</xsl:text>
                    <xsl:value-of select="@type"/>
                    <xsl:text>"</xsl:text>
                </xsl:when>
                <xsl:otherwise><xsl:text>null</xsl:text></xsl:otherwise>
            </xsl:choose>
            <xsl:text>, </xsl:text>
            <xsl:choose>
                <xsl:when test="@minOccurs">
                    <xsl:text>"</xsl:text>
                    <xsl:value-of select="@minOccurs"/>
                    <xsl:text>"</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>null</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:text>, </xsl:text>
            <xsl:choose>
                <xsl:when test="@maxOccurs">
                    <xsl:text>"</xsl:text>
                    <xsl:value-of select="@maxOccurs"/>
                    <xsl:text>"</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>null</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:text>);
            </xsl:text>
        </xsl:for-each>
        <xsl:text>       
        
        // Validate with parameters tells the type of validation to perform
        component.validate(Component.COMPLEX);
        
        }
       </xsl:text> 
    </xsl:template>
    
    <xsl:template name="SimpleTest">
        <xsl:text>public void test</xsl:text>
        <xsl:value-of select="@name"/><xsl:text>() throws Exception {
        // Initialize the component
        component.reset();
        
        // Set the Component Name
        component.setComponentName("</xsl:text>
        <xsl:value-of select="@name"/><xsl:text>");
        
        // Add the field requirements
        </xsl:text>
            <xsl:for-each select="xsd:sequence/xsd:element">
                <xsl:text>component.addSequenceField("</xsl:text>
                <xsl:value-of select="@name | @ref"/>
                <xsl:text>", </xsl:text>
                <xsl:choose>
                    <xsl:when test="@type">
                        <xsl:text>"</xsl:text>
                        <xsl:value-of select="@type"/>
                        <xsl:text>"</xsl:text>
                    </xsl:when>
                    <xsl:otherwise><xsl:text>null</xsl:text></xsl:otherwise>
                </xsl:choose>
                <xsl:text>, </xsl:text>
                <xsl:choose>
                    <xsl:when test="@minOccurs">
                        <xsl:text>"</xsl:text>
                        <xsl:value-of select="@minOccurs"/>
                        <xsl:text>"</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>null</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:text>, </xsl:text>
                <xsl:choose>
                    <xsl:when test="@maxOccurs">
                        <xsl:text>"</xsl:text>
                        <xsl:value-of select="@maxOccurs"/>
                        <xsl:text>"</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>null</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:text>);
                </xsl:text>
            </xsl:for-each>

        <!-- Generate Choice -->
        <xsl:if test="xsd:sequence/xsd:choice">
            <xsl:text>// Choice Requirements
            </xsl:text>
            <xsl:for-each select="xsd:sequence/xsd:choice/xsd:sequence/xsd:element">
                component.addChoiceField("RequiredOptionGroup", null, "0", null);
                
                <xsl:text>component.addChoiceField("</xsl:text>
                <xsl:value-of select="@name | @ref"/>
                <xsl:text>", </xsl:text>
                <xsl:choose>
                    <xsl:when test="@type">
                        <xsl:text>"</xsl:text>
                        <xsl:value-of select="@type"/>
                        <xsl:text>"</xsl:text>
                    </xsl:when>
                    <xsl:otherwise><xsl:text>null</xsl:text></xsl:otherwise>
                </xsl:choose>
                <xsl:text>, </xsl:text>
                <xsl:choose>
                    <xsl:when test="@minOccurs">
                        <xsl:text>"</xsl:text>
                        <xsl:value-of select="@minOccurs"/>
                        <xsl:text>"</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>null</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:text>, </xsl:text>
                <xsl:choose>
                    <xsl:when test="@maxOccurs">
                        <xsl:text>"</xsl:text>
                        <xsl:value-of select="@maxOccurs"/>
                        <xsl:text>"</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>null</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:text>);
                </xsl:text>
            </xsl:for-each>
        </xsl:if>
        
        <xsl:text>        
        // Validate with parameters tells the type of validation to perform
        component.validate(Component.SIMPLE);
        
        }
        </xsl:text>
    </xsl:template>
</xsl:stylesheet>