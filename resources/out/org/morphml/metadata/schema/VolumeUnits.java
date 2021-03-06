//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.13 at 04:28:34 PM PDT 
//


package org.morphml.metadata.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VolumeUnits.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VolumeUnits">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="cubic_millimeter"/>
 *     &lt;enumeration value="millilitre"/>
 *     &lt;enumeration value="litre"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VolumeUnits")
@XmlEnum
public enum VolumeUnits {

    @XmlEnumValue("cubic_millimeter")
    CUBIC_MILLIMETER("cubic_millimeter"),
    @XmlEnumValue("millilitre")
    MILLILITRE("millilitre"),
    @XmlEnumValue("litre")
    LITRE("litre");
    private final String value;

    VolumeUnits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VolumeUnits fromValue(String v) {
        for (VolumeUnits c: VolumeUnits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
