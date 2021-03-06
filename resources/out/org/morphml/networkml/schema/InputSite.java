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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Specifies a location on a cell where input is received
 * 
 * <p>Java class for InputSite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InputSite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="pulse_input_instance" type="{http://morphml.org/networkml/schema}PulseInput" minOccurs="0"/>
 *         &lt;element name="random_stim_instance" type="{http://morphml.org/networkml/schema}RandomStimInstance" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="cell_id" use="required" type="{http://morphml.org/networkml/schema}CellIdInNetwork" />
 *       &lt;attribute name="segment_id" type="{http://morphml.org/metadata/schema}SegmentIdInCell" default="0" />
 *       &lt;attribute name="fraction_along" type="{http://morphml.org/metadata/schema}ZeroToOne" default="0.5" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputSite", propOrder = {
    "randomStimInstance",
    "pulseInputInstance"
})
public class InputSite {

    @XmlElement(name = "random_stim_instance")
    protected RandomStimInstance randomStimInstance;
    @XmlElement(name = "pulse_input_instance")
    protected PulseInput pulseInputInstance;
    @XmlAttribute(name = "cell_id", required = true)
    protected BigInteger cellId;
    @XmlAttribute(name = "segment_id")
    protected BigInteger segmentId;
    @XmlAttribute(name = "fraction_along")
    protected Double fractionAlong;

    /**
     * Gets the value of the randomStimInstance property.
     * 
     * @return
     *     possible object is
     *     {@link RandomStimInstance }
     *     
     */
    public RandomStimInstance getRandomStimInstance() {
        return randomStimInstance;
    }

    /**
     * Sets the value of the randomStimInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link RandomStimInstance }
     *     
     */
    public void setRandomStimInstance(RandomStimInstance value) {
        this.randomStimInstance = value;
    }

    /**
     * Gets the value of the pulseInputInstance property.
     * 
     * @return
     *     possible object is
     *     {@link PulseInput }
     *     
     */
    public PulseInput getPulseInputInstance() {
        return pulseInputInstance;
    }

    /**
     * Sets the value of the pulseInputInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link PulseInput }
     *     
     */
    public void setPulseInputInstance(PulseInput value) {
        this.pulseInputInstance = value;
    }

    /**
     * Gets the value of the cellId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCellId() {
        return cellId;
    }

    /**
     * Sets the value of the cellId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCellId(BigInteger value) {
        this.cellId = value;
    }

    /**
     * Gets the value of the segmentId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSegmentId() {
        if (segmentId == null) {
            return new BigInteger("0");
        } else {
            return segmentId;
        }
    }

    /**
     * Sets the value of the segmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSegmentId(BigInteger value) {
        this.segmentId = value;
    }

    /**
     * Gets the value of the fractionAlong property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getFractionAlong() {
        if (fractionAlong == null) {
            return  0.5D;
        } else {
            return fractionAlong;
        }
    }

    /**
     * Sets the value of the fractionAlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFractionAlong(Double value) {
        this.fractionAlong = value;
    }

}
