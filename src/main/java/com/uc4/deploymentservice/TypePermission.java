/**
 * TypePermission.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice;

public class TypePermission  implements java.io.Serializable {
    private java.lang.String maintype;

    private java.lang.String customType;

    private java.lang.Boolean create;

    public TypePermission() {
    }

    public TypePermission(
           java.lang.String maintype,
           java.lang.String customType,
           java.lang.Boolean create) {
           this.maintype = maintype;
           this.customType = customType;
           this.create = create;
    }


    /**
     * Gets the maintype value for this TypePermission.
     * 
     * @return maintype
     */
    public java.lang.String getMaintype() {
        return maintype;
    }


    /**
     * Sets the maintype value for this TypePermission.
     * 
     * @param maintype
     */
    public void setMaintype(java.lang.String maintype) {
        this.maintype = maintype;
    }


    /**
     * Gets the customType value for this TypePermission.
     * 
     * @return customType
     */
    public java.lang.String getCustomType() {
        return customType;
    }


    /**
     * Sets the customType value for this TypePermission.
     * 
     * @param customType
     */
    public void setCustomType(java.lang.String customType) {
        this.customType = customType;
    }


    /**
     * Gets the create value for this TypePermission.
     * 
     * @return create
     */
    public java.lang.Boolean getCreate() {
        return create;
    }


    /**
     * Sets the create value for this TypePermission.
     * 
     * @param create
     */
    public void setCreate(java.lang.Boolean create) {
        this.create = create;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TypePermission)) return false;
        TypePermission other = (TypePermission) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.maintype==null && other.getMaintype()==null) || 
             (this.maintype!=null &&
              this.maintype.equals(other.getMaintype()))) &&
            ((this.customType==null && other.getCustomType()==null) || 
             (this.customType!=null &&
              this.customType.equals(other.getCustomType()))) &&
            ((this.create==null && other.getCreate()==null) || 
             (this.create!=null &&
              this.create.equals(other.getCreate())));
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
        if (getMaintype() != null) {
            _hashCode += getMaintype().hashCode();
        }
        if (getCustomType() != null) {
            _hashCode += getCustomType().hashCode();
        }
        if (getCreate() != null) {
            _hashCode += getCreate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TypePermission.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "TypePermission"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maintype");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Maintype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "CustomType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("create");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Create"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
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
