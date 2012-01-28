//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.channelml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Deprecated since v1.7.3. What causes the gate to open and close. A dependence on potential difference, 
 *             or a voltage and (ion) concentration dependence
 * 
 * <p>Java class for Deprecated_Transition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Deprecated_Transition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="voltage_gate" type="{http://morphml.org/channelml/schema}Deprecated_VoltageGate"/>
 *         &lt;element name="voltage_conc_gate" type="{http://morphml.org/channelml/schema}Deprecated_VoltageConcGate"/>
 *       &lt;/choice>
 *       &lt;attribute name="source" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Deprecated_Transition", propOrder = {
    "voltageConcGate",
    "voltageGate"
})
public class DeprecatedTransition {

    @XmlElement(name = "voltage_conc_gate")
    protected DeprecatedVoltageConcGate voltageConcGate;
    @XmlElement(name = "voltage_gate")
    protected DeprecatedVoltageGate voltageGate;
    @XmlAttribute
    protected String source;
    @XmlAttribute
    protected String target;

    /**
     * Gets the value of the voltageConcGate property.
     * 
     * @return
     *     possible object is
     *     {@link DeprecatedVoltageConcGate }
     *     
     */
    public DeprecatedVoltageConcGate getVoltageConcGate() {
        return voltageConcGate;
    }

    /**
     * Sets the value of the voltageConcGate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeprecatedVoltageConcGate }
     *     
     */
    public void setVoltageConcGate(DeprecatedVoltageConcGate value) {
        this.voltageConcGate = value;
    }

    /**
     * Gets the value of the voltageGate property.
     * 
     * @return
     *     possible object is
     *     {@link DeprecatedVoltageGate }
     *     
     */
    public DeprecatedVoltageGate getVoltageGate() {
        return voltageGate;
    }

    /**
     * Sets the value of the voltageGate property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeprecatedVoltageGate }
     *     
     */
    public void setVoltageGate(DeprecatedVoltageGate value) {
        this.voltageGate = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

}