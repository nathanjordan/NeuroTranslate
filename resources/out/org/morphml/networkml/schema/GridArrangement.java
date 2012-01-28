//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.networkml.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.morphml.metadata.schema.Annotation;
import org.morphml.metadata.schema.NonSpatialGrid;
import org.morphml.metadata.schema.Properties;
import org.morphml.metadata.schema.RectangularBox;


/**
 * A regular placement of the cells in a number of dimensions
 * 
 * <p>Java class for GridArrangement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GridArrangement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://morphml.org/metadata/schema}metadata"/>
 *         &lt;choice>
 *           &lt;group ref="{http://morphml.org/networkml/schema}SpatialLayout"/>
 *           &lt;element name="non_spatial_grid" type="{http://morphml.org/metadata/schema}NonSpatialGrid"/>
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
@XmlType(name = "GridArrangement", propOrder = {
    "notes",
    "properties",
    "annotation",
    "groups",
    "nonSpatialGrid",
    "rectangularLocation",
    "spacing"
})
public class GridArrangement {

    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected String notes;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Properties properties;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Annotation annotation;
    @XmlElement(name = "group", namespace = "http://morphml.org/metadata/schema")
    protected List<String> groups;
    @XmlElement(name = "non_spatial_grid")
    protected NonSpatialGrid nonSpatialGrid;
    @XmlElement(name = "rectangular_location")
    protected RectangularBox rectangularLocation;
    protected GridArrangement.Spacing spacing;

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
     * Gets the value of the nonSpatialGrid property.
     * 
     * @return
     *     possible object is
     *     {@link NonSpatialGrid }
     *     
     */
    public NonSpatialGrid getNonSpatialGrid() {
        return nonSpatialGrid;
    }

    /**
     * Sets the value of the nonSpatialGrid property.
     * 
     * @param value
     *     allowed object is
     *     {@link NonSpatialGrid }
     *     
     */
    public void setNonSpatialGrid(NonSpatialGrid value) {
        this.nonSpatialGrid = value;
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
     * Gets the value of the spacing property.
     * 
     * @return
     *     possible object is
     *     {@link GridArrangement.Spacing }
     *     
     */
    public GridArrangement.Spacing getSpacing() {
        return spacing;
    }

    /**
     * Sets the value of the spacing property.
     * 
     * @param value
     *     allowed object is
     *     {@link GridArrangement.Spacing }
     *     
     */
    public void setSpacing(GridArrangement.Spacing value) {
        this.spacing = value;
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
     *       &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}double" />
     *       &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}double" />
     *       &lt;attribute name="z" type="{http://www.w3.org/2001/XMLSchema}double" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Spacing {

        @XmlAttribute
        protected Double x;
        @XmlAttribute
        protected Double y;
        @XmlAttribute
        protected Double z;

        /**
         * Gets the value of the x property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getX() {
            return x;
        }

        /**
         * Sets the value of the x property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setX(Double value) {
            this.x = value;
        }

        /**
         * Gets the value of the y property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getY() {
            return y;
        }

        /**
         * Sets the value of the y property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setY(Double value) {
            this.y = value;
        }

        /**
         * Gets the value of the z property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getZ() {
            return z;
        }

        /**
         * Sets the value of the z property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setZ(Double value) {
            this.z = value;
        }

    }

}