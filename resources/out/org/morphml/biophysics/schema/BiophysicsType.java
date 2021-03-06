//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.biophysics.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.morphml.metadata.schema.Units;
import org.morphml.neuroml.schema.Level3Biophysics;


/**
 * Description of biophysical properties of a cell.
 * 
 * <p>Java class for Biophysics complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Biophysics">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mechanism" type="{http://morphml.org/biophysics/schema}Mechanism" maxOccurs="unbounded"/>
 *         &lt;choice>
 *           &lt;element name="specificCapacitance" type="{http://morphml.org/biophysics/schema}SpecCapacitance"/>
 *           &lt;element name="spec_capacitance" type="{http://morphml.org/biophysics/schema}SpecCapacitance"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="specificAxialResistance" type="{http://morphml.org/biophysics/schema}SpecAxialResistance"/>
 *           &lt;element name="spec_axial_resistance" type="{http://morphml.org/biophysics/schema}SpecAxialResistance"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="initialMembPotential" type="{http://morphml.org/biophysics/schema}InitialMembPotential" minOccurs="0"/>
 *           &lt;element name="init_memb_potential" type="{http://morphml.org/biophysics/schema}InitialMembPotential" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="ionProperties" type="{http://morphml.org/biophysics/schema}IonProperties" minOccurs="0"/>
 *           &lt;element name="ion_props" type="{http://morphml.org/biophysics/schema}IonProperties" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="units" use="required" type="{http://morphml.org/metadata/schema}Units" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Biophysics", propOrder = {
    "mechanisms",
    "specCapacitance",
    "specificCapacitance",
    "specAxialResistance",
    "specificAxialResistance",
    "initMembPotential",
    "initialMembPotential",
    "ionProps",
    "ionProperties"
})
@XmlSeeAlso({
    Level3Biophysics.class
})
public class BiophysicsType {

    @XmlElement(name = "mechanism", required = true)
    protected List<Mechanism> mechanisms;
    @XmlElement(name = "spec_capacitance")
    protected SpecCapacitance specCapacitance;
    protected SpecCapacitance specificCapacitance;
    @XmlElement(name = "spec_axial_resistance")
    protected SpecAxialResistance specAxialResistance;
    protected SpecAxialResistance specificAxialResistance;
    @XmlElement(name = "init_memb_potential")
    protected InitialMembPotential initMembPotential;
    protected InitialMembPotential initialMembPotential;
    @XmlElement(name = "ion_props")
    protected List<IonProperties> ionProps;
    protected IonProperties ionProperties;
    @XmlAttribute(required = true)
    protected Units units;

    /**
     * Gets the value of the mechanisms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mechanisms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMechanisms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Mechanism }
     * 
     * 
     */
    public List<Mechanism> getMechanisms() {
        if (mechanisms == null) {
            mechanisms = new ArrayList<Mechanism>();
        }
        return this.mechanisms;
    }

    /**
     * Gets the value of the specCapacitance property.
     * 
     * @return
     *     possible object is
     *     {@link SpecCapacitance }
     *     
     */
    public SpecCapacitance getSpecCapacitance() {
        return specCapacitance;
    }

    /**
     * Sets the value of the specCapacitance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecCapacitance }
     *     
     */
    public void setSpecCapacitance(SpecCapacitance value) {
        this.specCapacitance = value;
    }

    /**
     * Gets the value of the specificCapacitance property.
     * 
     * @return
     *     possible object is
     *     {@link SpecCapacitance }
     *     
     */
    public SpecCapacitance getSpecificCapacitance() {
        return specificCapacitance;
    }

    /**
     * Sets the value of the specificCapacitance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecCapacitance }
     *     
     */
    public void setSpecificCapacitance(SpecCapacitance value) {
        this.specificCapacitance = value;
    }

    /**
     * Gets the value of the specAxialResistance property.
     * 
     * @return
     *     possible object is
     *     {@link SpecAxialResistance }
     *     
     */
    public SpecAxialResistance getSpecAxialResistance() {
        return specAxialResistance;
    }

    /**
     * Sets the value of the specAxialResistance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecAxialResistance }
     *     
     */
    public void setSpecAxialResistance(SpecAxialResistance value) {
        this.specAxialResistance = value;
    }

    /**
     * Gets the value of the specificAxialResistance property.
     * 
     * @return
     *     possible object is
     *     {@link SpecAxialResistance }
     *     
     */
    public SpecAxialResistance getSpecificAxialResistance() {
        return specificAxialResistance;
    }

    /**
     * Sets the value of the specificAxialResistance property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpecAxialResistance }
     *     
     */
    public void setSpecificAxialResistance(SpecAxialResistance value) {
        this.specificAxialResistance = value;
    }

    /**
     * Gets the value of the initMembPotential property.
     * 
     * @return
     *     possible object is
     *     {@link InitialMembPotential }
     *     
     */
    public InitialMembPotential getInitMembPotential() {
        return initMembPotential;
    }

    /**
     * Sets the value of the initMembPotential property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitialMembPotential }
     *     
     */
    public void setInitMembPotential(InitialMembPotential value) {
        this.initMembPotential = value;
    }

    /**
     * Gets the value of the initialMembPotential property.
     * 
     * @return
     *     possible object is
     *     {@link InitialMembPotential }
     *     
     */
    public InitialMembPotential getInitialMembPotential() {
        return initialMembPotential;
    }

    /**
     * Sets the value of the initialMembPotential property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitialMembPotential }
     *     
     */
    public void setInitialMembPotential(InitialMembPotential value) {
        this.initialMembPotential = value;
    }

    /**
     * Gets the value of the ionProps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ionProps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIonProps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IonProperties }
     * 
     * 
     */
    public List<IonProperties> getIonProps() {
        if (ionProps == null) {
            ionProps = new ArrayList<IonProperties>();
        }
        return this.ionProps;
    }

    /**
     * Gets the value of the ionProperties property.
     * 
     * @return
     *     possible object is
     *     {@link IonProperties }
     *     
     */
    public IonProperties getIonProperties() {
        return ionProperties;
    }

    /**
     * Sets the value of the ionProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link IonProperties }
     *     
     */
    public void setIonProperties(IonProperties value) {
        this.ionProperties = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link Units }
     *     
     */
    public Units getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link Units }
     *     
     */
    public void setUnits(Units value) {
        this.units = value;
    }

}
