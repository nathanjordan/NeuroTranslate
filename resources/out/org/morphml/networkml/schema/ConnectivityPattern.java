//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.networkml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Information on the number of target cells connected to source cells, etc.
 * 
 * <p>Java class for ConnectivityPattern complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConnectivityPattern">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="all_to_all">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fixed_probability">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="probability" type="{http://morphml.org/metadata/schema}ZeroToOne" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="per_cell_connection" type="{http://morphml.org/networkml/schema}PerCellConnection"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConnectivityPattern", propOrder = {
    "perCellConnection",
    "fixedProbability",
    "allToAll"
})
public class ConnectivityPattern {

    @XmlElement(name = "per_cell_connection")
    protected PerCellConnection perCellConnection;
    @XmlElement(name = "fixed_probability")
    protected ConnectivityPattern.FixedProbability fixedProbability;
    @XmlElement(name = "all_to_all")
    protected ConnectivityPattern.AllToAll allToAll;

    /**
     * Gets the value of the perCellConnection property.
     * 
     * @return
     *     possible object is
     *     {@link PerCellConnection }
     *     
     */
    public PerCellConnection getPerCellConnection() {
        return perCellConnection;
    }

    /**
     * Sets the value of the perCellConnection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerCellConnection }
     *     
     */
    public void setPerCellConnection(PerCellConnection value) {
        this.perCellConnection = value;
    }

    /**
     * Gets the value of the fixedProbability property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectivityPattern.FixedProbability }
     *     
     */
    public ConnectivityPattern.FixedProbability getFixedProbability() {
        return fixedProbability;
    }

    /**
     * Sets the value of the fixedProbability property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectivityPattern.FixedProbability }
     *     
     */
    public void setFixedProbability(ConnectivityPattern.FixedProbability value) {
        this.fixedProbability = value;
    }

    /**
     * Gets the value of the allToAll property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectivityPattern.AllToAll }
     *     
     */
    public ConnectivityPattern.AllToAll getAllToAll() {
        return allToAll;
    }

    /**
     * Sets the value of the allToAll property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectivityPattern.AllToAll }
     *     
     */
    public void setAllToAll(ConnectivityPattern.AllToAll value) {
        this.allToAll = value;
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
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class AllToAll {


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
     *       &lt;attribute name="probability" type="{http://morphml.org/metadata/schema}ZeroToOne" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class FixedProbability {

        @XmlAttribute
        protected Double probability;

        /**
         * Gets the value of the probability property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getProbability() {
            return probability;
        }

        /**
         * Sets the value of the probability property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setProbability(Double value) {
            this.probability = value;
        }

    }

}
