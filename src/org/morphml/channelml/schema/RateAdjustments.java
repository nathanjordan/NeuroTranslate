//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.channelml.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Adjustments necessary to all the rate equations, e.g 
 *             temperature dependencies, voltage offsets introduced when moving 
 *             between species, etc. See the XSL mappings for more information on the 
 *             meaning of these adjustments.
 * 
 * <p>Java class for RateAdjustments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateAdjustments">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="q10_settings" type="{http://morphml.org/channelml/schema}Q10Settings" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="offset" type="{http://morphml.org/channelml/schema}Offset" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateAdjustments", propOrder = {
    "q10Settings",
    "offset"
})
public class RateAdjustments {

    @XmlElement(name = "q10_settings")
    protected List<Q10Settings> q10Settings;
    protected Offset offset;

    /**
     * Gets the value of the q10Settings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the q10Settings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQ10Settings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Q10Settings }
     * 
     * 
     */
    public List<Q10Settings> getQ10Settings() {
        if (q10Settings == null) {
            q10Settings = new ArrayList<Q10Settings>();
        }
        return this.q10Settings;
    }

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setOffset(Offset value) {
        this.offset = value;
    }

}
