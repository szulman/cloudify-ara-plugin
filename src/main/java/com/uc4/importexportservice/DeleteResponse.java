
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
 *         &lt;element name="DeleteResult" type="{http://uc4.com/}Result" minOccurs="0"/>
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
    "deleteResult"
})
@XmlRootElement(name = "DeleteResponse")
public class DeleteResponse {

    @XmlElement(name = "DeleteResult")
    protected Result deleteResult;

    /**
     * Gets the value of the deleteResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getDeleteResult() {
        return deleteResult;
    }

    /**
     * Sets the value of the deleteResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setDeleteResult(Result value) {
        this.deleteResult = value;
    }

}
