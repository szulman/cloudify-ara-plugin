/**
 * ApprovalPropertyDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice;

public class ApprovalPropertyDefinition  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String label;

    private com.uc4.deploymentservice.ApprovalOperatorDefinition[] operators;

    private com.uc4.deploymentservice.ApprovalValueDefinition[] allowedValues;

    public ApprovalPropertyDefinition() {
    }

    public ApprovalPropertyDefinition(
           java.lang.String name,
           java.lang.String label,
           com.uc4.deploymentservice.ApprovalOperatorDefinition[] operators,
           com.uc4.deploymentservice.ApprovalValueDefinition[] allowedValues) {
           this.name = name;
           this.label = label;
           this.operators = operators;
           this.allowedValues = allowedValues;
    }


    /**
     * Gets the name value for this ApprovalPropertyDefinition.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ApprovalPropertyDefinition.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the label value for this ApprovalPropertyDefinition.
     * 
     * @return label
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this ApprovalPropertyDefinition.
     * 
     * @param label
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the operators value for this ApprovalPropertyDefinition.
     * 
     * @return operators
     */
    public com.uc4.deploymentservice.ApprovalOperatorDefinition[] getOperators() {
        return operators;
    }


    /**
     * Sets the operators value for this ApprovalPropertyDefinition.
     * 
     * @param operators
     */
    public void setOperators(com.uc4.deploymentservice.ApprovalOperatorDefinition[] operators) {
        this.operators = operators;
    }


    /**
     * Gets the allowedValues value for this ApprovalPropertyDefinition.
     * 
     * @return allowedValues
     */
    public com.uc4.deploymentservice.ApprovalValueDefinition[] getAllowedValues() {
        return allowedValues;
    }


    /**
     * Sets the allowedValues value for this ApprovalPropertyDefinition.
     * 
     * @param allowedValues
     */
    public void setAllowedValues(com.uc4.deploymentservice.ApprovalValueDefinition[] allowedValues) {
        this.allowedValues = allowedValues;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ApprovalPropertyDefinition)) return false;
        ApprovalPropertyDefinition other = (ApprovalPropertyDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.operators==null && other.getOperators()==null) || 
             (this.operators!=null &&
              java.util.Arrays.equals(this.operators, other.getOperators()))) &&
            ((this.allowedValues==null && other.getAllowedValues()==null) || 
             (this.allowedValues!=null &&
              java.util.Arrays.equals(this.allowedValues, other.getAllowedValues())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getOperators() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOperators());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOperators(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAllowedValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAllowedValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAllowedValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ApprovalPropertyDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalPropertyDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operators");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Operators"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalOperatorDefinition"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalOperatorDefinition"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowedValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "AllowedValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalValueDefinition"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalValueDefinition"));
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
