//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.neuroml.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Set of Level 3 cells
 * 
 * <p>Java class for Level3Cells complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Level3Cells">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cell" type="{http://morphml.org/neuroml/schema}Level3Cell" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Level3Cells", propOrder = {
    "cells"
})
public class Level3Cells {

    @XmlElement(name = "cell", required = true)
    protected List<Level3Cell> cells;

    /**
     * Gets the value of the cells property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cells property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCells().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Level3Cell }
     * 
     * 
     */
    public List<Level3Cell> getCells() {
        if (cells == null) {
            cells = new ArrayList<Level3Cell>();
        }
        return this.cells;
    }

}
