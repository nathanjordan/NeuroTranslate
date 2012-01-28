//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.morphml.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.morphml.metadata.schema.Point;


/**
 * A spine with location, shape, and direction.
 * 
 * <p>Java class for Spine complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Spine">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proximal" type="{http://morphml.org/metadata/schema}Point"/>
 *         &lt;element name="distal" type="{http://morphml.org/metadata/schema}Point" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="parent" type="{http://morphml.org/metadata/schema}SegmentIdInCell" />
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="volume" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="shape" type="{http://morphml.org/morphml/schema}SpineShape" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Spine", propOrder = {
    "proximal",
    "distal"
})
public class Spine {

    @XmlElement(required = true)
    protected Point proximal;
    protected Point distal;
    @XmlAttribute
    protected BigInteger parent;
    @XmlAttribute
    protected Double length;
    @XmlAttribute
    protected Double volume;
    @XmlAttribute
    protected SpineShape shape;

    /**
     * Gets the value of the proximal property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getProximal() {
        return proximal;
    }

    /**
     * Sets the value of the proximal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setProximal(Point value) {
        this.proximal = value;
    }

    /**
     * Gets the value of the distal property.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getDistal() {
        return distal;
    }

    /**
     * Sets the value of the distal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setDistal(Point value) {
        this.distal = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setParent(BigInteger value) {
        this.parent = value;
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLength(Double value) {
        this.length = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVolume(Double value) {
        this.volume = value;
    }

    /**
     * Gets the value of the shape property.
     * 
     * @return
     *     possible object is
     *     {@link SpineShape }
     *     
     */
    public SpineShape getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpineShape }
     *     
     */
    public void setShape(SpineShape value) {
        this.shape = value;
    }

}
