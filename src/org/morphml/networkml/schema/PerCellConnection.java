//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.networkml.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Connection built iteratively from each pre (or post) cell based on a number of parameters
 * 
 * <p>Java class for PerCellConnection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PerCellConnection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="direction" default="PreToPost">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="PreToPost"/>
 *             &lt;enumeration value="PostToPre"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="num_per_source" use="required" type="{http://morphml.org/metadata/schema}PositiveDouble" />
 *       &lt;attribute name="max_per_target" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerCellConnection")
public class PerCellConnection {

    @XmlAttribute
    protected String direction;
    @XmlAttribute(name = "num_per_source", required = true)
    protected double numPerSource;
    @XmlAttribute(name = "max_per_target")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxPerTarget;

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirection() {
        if (direction == null) {
            return "PreToPost";
        } else {
            return direction;
        }
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirection(String value) {
        this.direction = value;
    }

    /**
     * Gets the value of the numPerSource property.
     * 
     */
    public double getNumPerSource() {
        return numPerSource;
    }

    /**
     * Sets the value of the numPerSource property.
     * 
     */
    public void setNumPerSource(double value) {
        this.numPerSource = value;
    }

    /**
     * Gets the value of the maxPerTarget property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxPerTarget() {
        return maxPerTarget;
    }

    /**
     * Sets the value of the maxPerTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxPerTarget(BigInteger value) {
        this.maxPerTarget = value;
    }

}
