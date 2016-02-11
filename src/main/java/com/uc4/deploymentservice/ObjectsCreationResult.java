/**
 * ObjectsCreationResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice;

public class ObjectsCreationResult  implements java.io.Serializable {
    private boolean isSuccess;

    private long[] objectsId;

    private java.lang.String message;

    public ObjectsCreationResult() {
    }

    public ObjectsCreationResult(
           boolean isSuccess,
           long[] objectsId,
           java.lang.String message) {
           this.isSuccess = isSuccess;
           this.objectsId = objectsId;
           this.message = message;
    }


    /**
     * Gets the isSuccess value for this ObjectsCreationResult.
     * 
     * @return isSuccess
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }


    /**
     * Sets the isSuccess value for this ObjectsCreationResult.
     * 
     * @param isSuccess
     */
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


    /**
     * Gets the objectsId value for this ObjectsCreationResult.
     * 
     * @return objectsId
     */
    public long[] getObjectsId() {
        return objectsId;
    }


    /**
     * Sets the objectsId value for this ObjectsCreationResult.
     * 
     * @param objectsId
     */
    public void setObjectsId(long[] objectsId) {
        this.objectsId = objectsId;
    }


    /**
     * Gets the message value for this ObjectsCreationResult.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ObjectsCreationResult.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObjectsCreationResult)) return false;
        ObjectsCreationResult other = (ObjectsCreationResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.isSuccess == other.isIsSuccess() &&
            ((this.objectsId==null && other.getObjectsId()==null) || 
             (this.objectsId!=null &&
              java.util.Arrays.equals(this.objectsId, other.getObjectsId()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage())));
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
        _hashCode += (isIsSuccess() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getObjectsId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjectsId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjectsId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObjectsCreationResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ObjectsCreationResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSuccess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "IsSuccess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectsId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ObjectsId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "long"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
