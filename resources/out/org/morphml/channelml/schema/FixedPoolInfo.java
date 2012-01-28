//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.channelml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * (IN PROGRESS, not stable!!!!) In this case the parameter which determines how quickly the internal pool 'fills' is given as a fixed value. Note this is a far from ideal 
 *         way to express this value, but needed to be included as this was the parameter which was all that was present in a number of models, e.g. Traub et al. 2003 Layer 2/3 cell.
 * 
 * <p>Java class for FixedPoolInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FixedPoolInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phi" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FixedPoolInfo", propOrder = {
    "phi"
})
public class FixedPoolInfo {

    protected double phi;

    /**
     * Gets the value of the phi property.
     * 
     */
    public double getPhi() {
        return phi;
    }

    /**
     * Sets the value of the phi property.
     * 
     */
    public void setPhi(double value) {
        this.phi = value;
    }

}
