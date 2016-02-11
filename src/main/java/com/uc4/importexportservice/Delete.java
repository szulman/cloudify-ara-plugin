
package com.uc4.importexportservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mainType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fomat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="failOnError" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "username",
    "password",
    "mainType",
    "fomat",
    "failOnError",
    "data"
})
@XmlRootElement(name = "Delete")
public class Delete {

    protected String username;
    protected String password;
    protected String mainType;
    protected String fomat;
    protected boolean failOnError;
    protected String data;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the mainType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainType() {
        return mainType;
    }

    /**
     * Sets the value of the mainType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainType(String value) {
        this.mainType = value;
    }

    /**
     * Gets the value of the fomat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFomat() {
        return fomat;
    }

    /**
     * Sets the value of the fomat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFomat(String value) {
        this.fomat = value;
    }

    /**
     * Gets the value of the failOnError property.
     * 
     */
    public boolean isFailOnError() {
        return failOnError;
    }

    /**
     * Sets the value of the failOnError property.
     * 
     */
    public void setFailOnError(boolean value) {
        this.failOnError = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
    }

}
