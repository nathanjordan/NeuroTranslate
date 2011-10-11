//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.channelml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Rate constant expressions allowed for voltage and conc dependent channels. Note, at this stage no
 *             Akd like expression for a generic voltage/conc dep experssion. Time will tell if there's an expression common enough 
 *             across different models to be expressed in such a way
 * 
 * <p>Java class for Deprecated_RateConstVoltConcDep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Deprecated_RateConstVoltConcDep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="generic_equation_hh" type="{http://morphml.org/channelml/schema}Deprecated_GenericEquation"/>
 *         &lt;element name="generic" type="{http://morphml.org/channelml/schema}Deprecated_GenericEquation"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Deprecated_RateConstVoltConcDep", propOrder = {
    "generic",
    "genericEquationHh"
})
public class DeprecatedRateConstVoltConcDep {

    protected DeprecatedGenericEquation generic;
    @XmlElement(name = "generic_equation_hh")
    protected DeprecatedGenericEquation genericEquationHh;

    /**
     * Gets the value of the generic property.
     * 
     * @return
     *     possible object is
     *     {@link DeprecatedGenericEquation }
     *     
     */
    public DeprecatedGenericEquation getGeneric() {
        return generic;
    }

    /**
     * Sets the value of the generic property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeprecatedGenericEquation }
     *     
     */
    public void setGeneric(DeprecatedGenericEquation value) {
        this.generic = value;
    }

    /**
     * Gets the value of the genericEquationHh property.
     * 
     * @return
     *     possible object is
     *     {@link DeprecatedGenericEquation }
     *     
     */
    public DeprecatedGenericEquation getGenericEquationHh() {
        return genericEquationHh;
    }

    /**
     * Sets the value of the genericEquationHh property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeprecatedGenericEquation }
     *     
     */
    public void setGenericEquationHh(DeprecatedGenericEquation value) {
        this.genericEquationHh = value;
    }

}
