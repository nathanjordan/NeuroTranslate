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
 * <p>Java class for LengthUnits.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LengthUnits">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="micron"/>
 *     &lt;enumeration value="micrometer"/>
 *     &lt;enumeration value="millimeter"/>
 *     &lt;enumeration value="meter"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LengthUnits")
@XmlEnum
public enum LengthUnits {


    /**
     * Note: micrometer is preferred to micron from v1.8.1
     * 
     */
    @XmlEnumValue("micron")
    MICRON("micron"),

    /**
     * Note: micrometer is preferred to micron from v1.8.1
     * 
     */
    @XmlEnumValue("micrometer")
    MICROMETER("micrometer"),
    @XmlEnumValue("millimeter")
    MILLIMETER("millimeter"),
    @XmlEnumValue("meter")
    METER("meter");
    private final String value;

    LengthUnits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LengthUnits fromValue(String v) {
        for (LengthUnits c: LengthUnits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
