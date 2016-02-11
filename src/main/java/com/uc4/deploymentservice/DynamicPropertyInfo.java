/**
 * DynamicPropertyInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice;

public class DynamicPropertyInfo  implements java.io.Serializable {
    private java.lang.String variableName;

    private java.lang.String description;

    private boolean _protected;

    private boolean highlighted;

    public DynamicPropertyInfo() {
    }

    public DynamicPropertyInfo(
           java.lang.String variableName,
           java.lang.String description,
           boolean _protected,
           boolean highlighted) {
           this.variableName = variableName;
           this.description = description;
           this._protected = _protected;
           this.highlighted = highlighted;
    }


    /**
     * Gets the variableName value for this DynamicPropertyInfo.
     * 
     * @return variableName
     */
    public java.lang.String getVariableName() {
        return variableName;
    }


    /**
     * Sets the variableName value for this DynamicPropertyInfo.
     * 
     * @param variableName
     */
    public void setVariableName(java.lang.String variableName) {
        this.variableName = variableName;
    }


    /**
     * Gets the description value for this DynamicPropertyInfo.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DynamicPropertyInfo.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the _protected value for this DynamicPropertyInfo.
     * 
     * @return _protected
     */
    public boolean is_protected() {
        return _protected;
    }


    /**
     * Sets the _protected value for this DynamicPropertyInfo.
     * 
     * @param _protected
     */
    public void set_protected(boolean _protected) {
        this._protected = _protected;
    }


    /**
     * Gets the highlighted value for this DynamicPropertyInfo.
     * 
     * @return highlighted
     */
    public boolean isHighlighted() {
        return highlighted;
    }


    /**
     * Sets the highlighted value for this DynamicPropertyInfo.
     * 
     * @param highlighted
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DynamicPropertyInfo)) return false;
        DynamicPropertyInfo other = (DynamicPropertyInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.variableName==null && other.getVariableName()==null) || 
             (this.variableName!=null &&
              this.variableName.equals(other.getVariableName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this._protected == other.is_protected() &&
            this.highlighted == other.isHighlighted();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getVariableName() != null) {
            _hashCode += getVariableName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += (is_protected() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isHighlighted() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DynamicPropertyInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicPropertyInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("variableName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "VariableName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_protected");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Protected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("highlighted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Highlighted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
