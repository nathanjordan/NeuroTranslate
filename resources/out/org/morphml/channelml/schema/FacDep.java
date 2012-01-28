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
 * Facilitating and depressing synaptic parameters. See mapping to NEURON mod file for implementation details.
 * 
 * <p>Java class for FacDep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FacDep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="init_release_prob" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="tau_rec" use="required" type="{http://morphml.org/biophysics/schema}TimeConstantValueIncZero" />
 *       &lt;attribute name="tau_fac" use="required" type="{http://morphml.org/biophysics/schema}TimeConstantValueIncZero" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacDep")
public class FacDep {

    @XmlAttribute(name = "init_release_prob", required = true)
    protected double initReleaseProb;
    @XmlAttribute(name = "tau_rec", required = true)
    protected double tauRec;
    @XmlAttribute(name = "tau_fac", required = true)
    protected double tauFac;

    /**
     * Gets the value of the initReleaseProb property.
     * 
     */
    public double getInitReleaseProb() {
        return initReleaseProb;
    }

    /**
     * Sets the value of the initReleaseProb property.
     * 
     */
    public void setInitReleaseProb(double value) {
        this.initReleaseProb = value;
    }

    /**
     * Gets the value of the tauRec property.
     * 
     */
    public double getTauRec() {
        return tauRec;
    }

    /**
     * Sets the value of the tauRec property.
     * 
     */
    public void setTauRec(double value) {
        this.tauRec = value;
    }

    /**
     * Gets the value of the tauFac property.
     * 
     */
    public double getTauFac() {
        return tauFac;
    }

    /**
     * Sets the value of the tauFac property.
     * 
     */
    public void setTauFac(double value) {
        this.tauFac = value;
    }

}
