
package com.uc4.importexportservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExportResult" type="{http://uc4.com/}Result" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "exportResult"
})
@XmlRootElement(name = "ExportResponse")
public class ExportResponse {

    @XmlElement(name = "ExportResult")
    protected Result exportResult;

    /**
     * Gets the value of the exportResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getExportResult() {
        return exportResult;
    }

    /**
     * Sets the value of the exportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setExportResult(Result value) {
        this.exportResult = value;
    }

}
