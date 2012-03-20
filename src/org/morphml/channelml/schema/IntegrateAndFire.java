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
import javax.xml.bind.annotation.XmlType;


/**
 * Signifies a current which will cause the cell to behave like an integrate and fire neuron. There are many ways to describe an
 *         Integrate and Fire mechanism, this one is based on the implementation in NEURON of the COBA IandF cell as described in Brette et al (2007)
 * 
 * <p>Java class for IntegrateAndFire complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IntegrateAndFire">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="threshold" use="required" type="{http://morphml.org/biophysics/schema}VoltageValue" />
 *       &lt;attribute name="t_refrac" use="required" type="{http://morphml.org/biophysics/schema}TimeValue" />
 *       &lt;attribute name="v_reset" use="required" type="{http://morphml.org/biophysics/schema}VoltageValue" />
 *       &lt;attribute name="g_refrac" use="required" type="{http://morphml.org/biophysics/schema}ConductanceValue" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntegrateAndFire")
public class IntegrateAndFire {

    @XmlAttribute(required = true)
    protected double threshold;
    @XmlAttribute(name = "t_refrac", required = true)
    protected double tRefrac;
    @XmlAttribute(name = "v_reset", required = true)
    protected double vReset;
    @XmlAttribute(name = "g_refrac", required = true)
    protected double gRefrac;

    /**
     * Gets the value of the threshold property.
     * 
     */
    public double getThreshold() {
        return threshold;
    }

    /**
     * Sets the value of the threshold property.
     * 
     */
    public void setThreshold(double value) {
        this.threshold = value;
    }

    /**
     * Gets the value of the tRefrac property.
     * 
     */
    public double getTRefrac() {
        return tRefrac;
    }

    /**
     * Sets the value of the tRefrac property.
     * 
     */
    public void setTRefrac(double value) {
        this.tRefrac = value;
    }

    /**
     * Gets the value of the vReset property.
     * 
     */
    public double getVReset() {
        return vReset;
    }

    /**
     * Sets the value of the vReset property.
     * 
     */
    public void setVReset(double value) {
        this.vReset = value;
    }

    /**
     * Gets the value of the gRefrac property.
     * 
     */
    public double getGRefrac() {
        return gRefrac;
    }

    /**
     * Sets the value of the gRefrac property.
     * 
     */
    public void setGRefrac(double value) {
        this.gRefrac = value;
    }

}
