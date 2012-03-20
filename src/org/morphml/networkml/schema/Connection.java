//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.networkml.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.morphml.metadata.schema.Annotation;
import org.morphml.metadata.schema.Properties;


/**
 * A single synaptic connection
 * 
 * <p>Java class for Connection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Connection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://morphml.org/metadata/schema}metadata"/>
 *         &lt;element name="pre" type="{http://morphml.org/networkml/schema}SynapticLocation" minOccurs="0"/>
 *         &lt;element name="post" type="{http://morphml.org/networkml/schema}SynapticLocation" minOccurs="0"/>
 *         &lt;element name="properties" type="{http://morphml.org/networkml/schema}LocalSynapticProperties" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="pre_cell_id" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="pre_segment_id" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="pre_fraction_along" type="{http://morphml.org/metadata/schema}ZeroToOne" default="0.5" />
 *       &lt;attribute name="post_cell_id" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="post_segment_id" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="post_fraction_along" type="{http://morphml.org/metadata/schema}ZeroToOne" default="0.5" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Connection", propOrder = {
    "notes",
    "properties",
    "annotation",
    "groups",
    "pre",
    "post",
    "synapticProperties"
})
public class Connection {

    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected String notes;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Properties properties;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Annotation annotation;
    @XmlElement(name = "group", namespace = "http://morphml.org/metadata/schema")
    protected List<String> groups;
    protected SynapticLocation pre;
    protected SynapticLocation post;
    @XmlElement(name = "properties")
    protected List<LocalSynapticProperties> synapticProperties;
    @XmlAttribute(required = true)
    protected BigInteger id;
    @XmlAttribute(name = "pre_cell_id")
    protected BigInteger preCellId;
    @XmlAttribute(name = "pre_segment_id")
    protected BigInteger preSegmentId;
    @XmlAttribute(name = "pre_fraction_along")
    protected Double preFractionAlong;
    @XmlAttribute(name = "post_cell_id")
    protected BigInteger postCellId;
    @XmlAttribute(name = "post_segment_id")
    protected BigInteger postSegmentId;
    @XmlAttribute(name = "post_fraction_along")
    protected Double postFractionAlong;

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link Properties }
     *     
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Properties }
     *     
     */
    public void setProperties(Properties value) {
        this.properties = value;
    }

    /**
     * Gets the value of the annotation property.
     * 
     * @return
     *     possible object is
     *     {@link Annotation }
     *     
     */
    public Annotation getAnnotation() {
        return annotation;
    }

    /**
     * Sets the value of the annotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Annotation }
     *     
     */
    public void setAnnotation(Annotation value) {
        this.annotation = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGroups() {
        if (groups == null) {
            groups = new ArrayList<String>();
        }
        return this.groups;
    }

    /**
     * Gets the value of the pre property.
     * 
     * @return
     *     possible object is
     *     {@link SynapticLocation }
     *     
     */
    public SynapticLocation getPre() {
        return pre;
    }

    /**
     * Sets the value of the pre property.
     * 
     * @param value
     *     allowed object is
     *     {@link SynapticLocation }
     *     
     */
    public void setPre(SynapticLocation value) {
        this.pre = value;
    }

    /**
     * Gets the value of the post property.
     * 
     * @return
     *     possible object is
     *     {@link SynapticLocation }
     *     
     */
    public SynapticLocation getPost() {
        return post;
    }

    /**
     * Sets the value of the post property.
     * 
     * @param value
     *     allowed object is
     *     {@link SynapticLocation }
     *     
     */
    public void setPost(SynapticLocation value) {
        this.post = value;
    }

    /**
     * Gets the value of the synapticProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the synapticProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSynapticProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalSynapticProperties }
     * 
     * 
     */
    public List<LocalSynapticProperties> getSynapticProperties() {
        if (synapticProperties == null) {
            synapticProperties = new ArrayList<LocalSynapticProperties>();
        }
        return this.synapticProperties;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the preCellId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPreCellId() {
        return preCellId;
    }

    /**
     * Sets the value of the preCellId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPreCellId(BigInteger value) {
        this.preCellId = value;
    }

    /**
     * Gets the value of the preSegmentId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPreSegmentId() {
        if (preSegmentId == null) {
            return new BigInteger("0");
        } else {
            return preSegmentId;
        }
    }

    /**
     * Sets the value of the preSegmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPreSegmentId(BigInteger value) {
        this.preSegmentId = value;
    }

    /**
     * Gets the value of the preFractionAlong property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getPreFractionAlong() {
        if (preFractionAlong == null) {
            return  0.5D;
        } else {
            return preFractionAlong;
        }
    }

    /**
     * Sets the value of the preFractionAlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPreFractionAlong(Double value) {
        this.preFractionAlong = value;
    }

    /**
     * Gets the value of the postCellId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPostCellId() {
        return postCellId;
    }

    /**
     * Sets the value of the postCellId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPostCellId(BigInteger value) {
        this.postCellId = value;
    }

    /**
     * Gets the value of the postSegmentId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPostSegmentId() {
        if (postSegmentId == null) {
            return new BigInteger("0");
        } else {
            return postSegmentId;
        }
    }

    /**
     * Sets the value of the postSegmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPostSegmentId(BigInteger value) {
        this.postSegmentId = value;
    }

    /**
     * Gets the value of the postFractionAlong property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public double getPostFractionAlong() {
        if (postFractionAlong == null) {
            return  0.5D;
        } else {
            return postFractionAlong;
        }
    }

    /**
     * Sets the value of the postFractionAlong property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPostFractionAlong(Double value) {
        this.postFractionAlong = value;
    }

}
