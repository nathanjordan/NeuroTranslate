//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.morphml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Definition of a parameter which varies along a cable group.
 * 
 * <p>Java class for InhomogeneousParam complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InhomogeneousParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metric" type="{http://morphml.org/morphml/schema}Metric"/>
 *         &lt;element name="proximal" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="translationStart" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="distal" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="normalizationEnd" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="variable" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InhomogeneousParam", propOrder = {
    "metric",
    "proximal",
    "distal"
})
public class InhomogeneousParam {

    @XmlElement(required = true)
    protected String metric;
    protected InhomogeneousParam.Proximal proximal;
    protected InhomogeneousParam.Distal distal;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute(required = true)
    protected String variable;

    /**
     * Gets the value of the metric property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetric() {
        return metric;
    }

    /**
     * Sets the value of the metric property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetric(String value) {
        this.metric = value;
    }

    /**
     * Gets the value of the proximal property.
     * 
     * @return
     *     possible object is
     *     {@link InhomogeneousParam.Proximal }
     *     
     */
    public InhomogeneousParam.Proximal getProximal() {
        return proximal;
    }

    /**
     * Sets the value of the proximal property.
     * 
     * @param value
     *     allowed object is
     *     {@link InhomogeneousParam.Proximal }
     *     
     */
    public void setProximal(InhomogeneousParam.Proximal value) {
        this.proximal = value;
    }

    /**
     * Gets the value of the distal property.
     * 
     * @return
     *     possible object is
     *     {@link InhomogeneousParam.Distal }
     *     
     */
    public InhomogeneousParam.Distal getDistal() {
        return distal;
    }

    /**
     * Sets the value of the distal property.
     * 
     * @param value
     *     allowed object is
     *     {@link InhomogeneousParam.Distal }
     *     
     */
    public void setDistal(InhomogeneousParam.Distal value) {
        this.distal = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the variable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariable() {
        return variable;
    }

    /**
     * Sets the value of the variable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariable(String value) {
        this.variable = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="normalizationEnd" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Distal {

        @XmlAttribute(required = true)
        protected double normalizationEnd;

        /**
         * Gets the value of the normalizationEnd property.
         * 
         */
        public double getNormalizationEnd() {
            return normalizationEnd;
        }

        /**
         * Sets the value of the normalizationEnd property.
         * 
         */
        public void setNormalizationEnd(double value) {
            this.normalizationEnd = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="translationStart" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Proximal {

        @XmlAttribute(required = true)
        protected double translationStart;

        /**
         * Gets the value of the translationStart property.
         * 
         */
        public double getTranslationStart() {
            return translationStart;
        }

        /**
         * Sets the value of the translationStart property.
         * 
         */
        public void setTranslationStart(double value) {
            this.translationStart = value;
        }

    }

}
