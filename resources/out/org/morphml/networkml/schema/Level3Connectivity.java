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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Biophysics of Level3 cell, including specification for allowable synaptic locations.
 *                 Note: from v1.7.1 the preferred way to specify a potential synaptic location is with a potential_syn_loc element under connectivity under cell, as
 *                 opposed to the potentialSynapticLocation under biophysics under cell. The former will be the only option from v2.0
 * 
 * <p>Java class for Level3Connectivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Level3Connectivity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="potential_syn_loc" type="{http://morphml.org/networkml/schema}PotentialSynLoc" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Level3Connectivity", propOrder = {
    "potentialSynLocs"
})
public class Level3Connectivity {

    @XmlElement(name = "potential_syn_loc")
    protected List<PotentialSynLoc> potentialSynLocs;

    /**
     * Gets the value of the potentialSynLocs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the potentialSynLocs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPotentialSynLocs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PotentialSynLoc }
     * 
     * 
     */
    public List<PotentialSynLoc> getPotentialSynLocs() {
        if (potentialSynLocs == null) {
            potentialSynLocs = new ArrayList<PotentialSynLoc>();
        }
        return this.potentialSynLocs;
    }

}
