
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
 *         &lt;element name="GetStatusResult" type="{http://uc4.com/}Result" minOccurs="0"/>
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
    "getStatusResult"
})
@XmlRootElement(name = "GetStatusResponse")
public class GetStatusResponse {

    @XmlElement(name = "GetStatusResult")
    protected Result getStatusResult;

    /**
     * Gets the value of the getStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetStatusResult() {
        return getStatusResult;
    }

    /**
     * Sets the value of the getStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetStatusResult(Result value) {
        this.getStatusResult = value;
    }

}
