//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.metadata.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A reference to an entity in NeuroMorpho.org 
 *                             Note: This element will possibly change when a new set of schema 
 *                             files is adopted for adding references, authors, citations, etc. See 
 *                             mailing lists for latest status
 * 
 * <p>Java class for NeuroMorphoRef complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NeuroMorphoRef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="morphologyRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NeuroMorphoRef", propOrder = {

})
public class NeuroMorphoRef {

    @XmlElement(required = true)
    protected String morphologyRef;
    @XmlElement(required = true)
    protected String uri;
    protected String comment;

    /**
     * Gets the value of the morphologyRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMorphologyRef() {
        return morphologyRef;
    }

    /**
     * Sets the value of the morphologyRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMorphologyRef(String value) {
        this.morphologyRef = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

}
