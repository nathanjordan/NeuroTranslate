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
import javax.xml.bind.annotation.XmlType;


/**
 * Definition of placement of a single electrophysiological mechanism (e.g. channel mechanism)
 *                         on a group of cables of a cell
 * 
 * <p>Java class for Mechanism complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Mechanism">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameter" type="{http://morphml.org/biophysics/schema}NamedParameter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="variableParameter" type="{http://morphml.org/biophysics/schema}VariableNamedParameter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="variable_parameter" type="{http://morphml.org/biophysics/schema}VariableNamedParameter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://morphml.org/biophysics/schema}MechanismType" />
 *       &lt;attribute name="passive_conductance" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="passiveConductance" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mechanism", propOrder = {
    "parameters",
    "variableParameters",
    "mechanismVariableParameter"
})
public class Mechanism {

    @XmlElement(name = "parameter")
    protected List<NamedParameter> parameters;
    @XmlElement(name = "variableParameter")
    protected List<VariableNamedParameter> variableParameters;
    @XmlElement(name = "variable_parameter")
    protected List<VariableNamedParameter> mechanismVariableParameter;
    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute(required = true)
    protected MechanismType type;
    @XmlAttribute(name = "passive_conductance")
    protected Boolean passiveConductance;
    @XmlAttribute(name = "passiveConductance")
    protected Boolean passiveConductanceDeprecated;

    /**
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedParameter }
     * 
     * 
     */
    public List<NamedParameter> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<NamedParameter>();
        }
        return this.parameters;
    }

    /**
     * Gets the value of the variableParameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variableParameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariableParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VariableNamedParameter }
     * 
     * 
     */
    public List<VariableNamedParameter> getVariableParameters() {
        if (variableParameters == null) {
            variableParameters = new ArrayList<VariableNamedParameter>();
        }
        return this.variableParameters;
    }

    /**
     * Gets the value of the mechanismVariableParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mechanismVariableParameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMechanismVariableParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VariableNamedParameter }
     * 
     * 
     */
    public List<VariableNamedParameter> getMechanismVariableParameter() {
        if (mechanismVariableParameter == null) {
            mechanismVariableParameter = new ArrayList<VariableNamedParameter>();
        }
        return this.mechanismVariableParameter;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link MechanismType }
     *     
     */
    public MechanismType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link MechanismType }
     *     
     */
    public void setType(MechanismType value) {
        this.type = value;
    }

    /**
     * Gets the value of the passiveConductance property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPassiveConductance() {
        if (passiveConductance == null) {
            return false;
        } else {
            return passiveConductance;
        }
    }

    /**
     * Sets the value of the passiveConductance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPassiveConductance(Boolean value) {
        this.passiveConductance = value;
    }

    /**
     * Gets the value of the passiveConductanceDeprecated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPassiveConductanceDeprecated() {
        if (passiveConductanceDeprecated == null) {
            return false;
        } else {
            return passiveConductanceDeprecated;
        }
    }

    /**
     * Sets the value of the passiveConductanceDeprecated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPassiveConductanceDeprecated(Boolean value) {
        this.passiveConductanceDeprecated = value;
    }

}
