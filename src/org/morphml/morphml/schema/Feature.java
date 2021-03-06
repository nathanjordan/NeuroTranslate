//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.morphml.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.morphml.metadata.schema.Annotation;
import org.morphml.metadata.schema.Manifold;
import org.morphml.metadata.schema.Polygon;
import org.morphml.metadata.schema.Polyhedron;
import org.morphml.metadata.schema.Properties;
import org.morphml.metadata.schema.Sphere;


/**
 * The group of things allowed in features.
 * 
 * <p>Java class for Feature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Feature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://morphml.org/metadata/schema}metadata"/>
 *         &lt;element name="path" type="{http://morphml.org/morphml/schema}Path" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="freePoints" type="{http://morphml.org/morphml/schema}FreePoints" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="manifold" type="{http://morphml.org/metadata/schema}Manifold" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="polygon" type="{http://morphml.org/metadata/schema}Polygon" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="polyhedron" type="{http://morphml.org/metadata/schema}Polyhedron" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sphere" type="{http://morphml.org/metadata/schema}Sphere" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Feature", propOrder = {
    "notes",
    "properties",
    "annotation",
    "groups",
    "paths",
    "freePoints",
    "manifolds",
    "polygons",
    "polyhedrons",
    "spheres"
})
public class Feature {

    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected String notes;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Properties properties;
    @XmlElement(namespace = "http://morphml.org/metadata/schema")
    protected Annotation annotation;
    @XmlElement(name = "group", namespace = "http://morphml.org/metadata/schema")
    protected List<String> groups;
    @XmlElement(name = "path")
    protected List<Path> paths;
    protected List<FreePoints> freePoints;
    @XmlElement(name = "manifold")
    protected List<Manifold> manifolds;
    @XmlElement(name = "polygon")
    protected List<Polygon> polygons;
    @XmlElement(name = "polyhedron")
    protected List<Polyhedron> polyhedrons;
    @XmlElement(name = "sphere")
    protected List<Sphere> spheres;
    @XmlAttribute
    protected String name;

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
     * Gets the value of the paths property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paths property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaths().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Path }
     * 
     * 
     */
    public List<Path> getPaths() {
        if (paths == null) {
            paths = new ArrayList<Path>();
        }
        return this.paths;
    }

    /**
     * Gets the value of the freePoints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the freePoints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFreePoints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FreePoints }
     * 
     * 
     */
    public List<FreePoints> getFreePoints() {
        if (freePoints == null) {
            freePoints = new ArrayList<FreePoints>();
        }
        return this.freePoints;
    }

    /**
     * Gets the value of the manifolds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the manifolds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getManifolds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Manifold }
     * 
     * 
     */
    public List<Manifold> getManifolds() {
        if (manifolds == null) {
            manifolds = new ArrayList<Manifold>();
        }
        return this.manifolds;
    }

    /**
     * Gets the value of the polygons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the polygons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolygons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Polygon }
     * 
     * 
     */
    public List<Polygon> getPolygons() {
        if (polygons == null) {
            polygons = new ArrayList<Polygon>();
        }
        return this.polygons;
    }

    /**
     * Gets the value of the polyhedrons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the polyhedrons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolyhedrons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Polyhedron }
     * 
     * 
     */
    public List<Polyhedron> getPolyhedrons() {
        if (polyhedrons == null) {
            polyhedrons = new ArrayList<Polyhedron>();
        }
        return this.polyhedrons;
    }

    /**
     * Gets the value of the spheres property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spheres property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpheres().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sphere }
     * 
     * 
     */
    public List<Sphere> getSpheres() {
        if (spheres == null) {
            spheres = new ArrayList<Sphere>();
        }
        return this.spheres;
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

}
