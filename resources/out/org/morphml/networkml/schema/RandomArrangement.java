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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.morphml.metadata.schema.Annotation;
import org.morphml.metadata.schema.Properties;
import org.morphml.metadata.schema.RectangularBox;
import org.morphml.metadata.schema.Sphere;


/**
 * A random arrangement of cells in a 3D location. Note other 3D regions besides spheres and rectangles can be added if required.
 * 
 * <p>Java class for RandomArrangement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RandomArrangement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://morphml.org/metadata/schema}metadata"/>
 *         &lt;element name="population_size" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;choice>
 *           &lt;element name="spherical_location" type="{http://morphml.org/metadata/schema}Sphere"/>
 *           &lt;element name="rectangular_location" type="{http://morphml.org/metadata/schema}RectangularBox"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RandomArrangement", propOrder = {
    "notes",
    "properties",
    "annotation",
    "groups",
    "populationSize",
    "rectangularLocation",
    "sphericalLocation"
})
public class RandomArrangement {

    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected String notes;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Properties properties;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Annotation annotation;
    @XmlElement(name = "group", namespace = "http://morphml.org/metadata/schema")
    protected List<String> groups;
    @XmlElement(name = "population_size", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger populationSize;
    @XmlElement(name = "rectangular_location")
    protected RectangularBox rectangularLocation;
    @XmlElement(name = "spherical_location")
    protected Sphere sphericalLocation;

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
     * Gets the value of the populationSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPopulationSize() {
        return populationSize;
    }

    /**
     * Sets the value of the populationSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPopulationSize(BigInteger value) {
        this.populationSize = value;
    }

    /**
     * Gets the value of the rectangularLocation property.
     * 
     * @return
     *     possible object is
     *     {@link RectangularBox }
     *     
     */
    public RectangularBox getRectangularLocation() {
        return rectangularLocation;
    }

    /**
     * Sets the value of the rectangularLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RectangularBox }
     *     
     */
    public void setRectangularLocation(RectangularBox value) {
        this.rectangularLocation = value;
    }

    /**
     * Gets the value of the sphericalLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Sphere }
     *     
     */
    public Sphere getSphericalLocation() {
        return sphericalLocation;
    }

    /**
     * Sets the value of the sphericalLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sphere }
     *     
     */
    public void setSphericalLocation(Sphere value) {
        this.sphericalLocation = value;
    }

}
