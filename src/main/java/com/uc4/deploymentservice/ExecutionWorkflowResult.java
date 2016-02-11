/**
 * ExecutionWorkflowResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice;

public class ExecutionWorkflowResult  implements java.io.Serializable {
    private boolean isSuccess;

    private java.lang.String message;

    private java.lang.Long executionId;

    public ExecutionWorkflowResult() {
    }

    public ExecutionWorkflowResult(
           boolean isSuccess,
           java.lang.String message,
           java.lang.Long executionId) {
           this.isSuccess = isSuccess;
           this.message = message;
           this.executionId = executionId;
    }


    /**
     * Gets the isSuccess value for this ExecutionWorkflowResult.
     * 
     * @return isSuccess
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }


    /**
     * Sets the isSuccess value for this ExecutionWorkflowResult.
     * 
     * @param isSuccess
     */
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


    /**
     * Gets the message value for this ExecutionWorkflowResult.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ExecutionWorkflowResult.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the executionId value for this ExecutionWorkflowResult.
     * 
     * @return executionId
     */
    public java.lang.Long getExecutionId() {
        return executionId;
    }


    /**
     * Sets the executionId value for this ExecutionWorkflowResult.
     * 
     * @param executionId
     */
    public void setExecutionId(java.lang.Long executionId) {
        this.executionId = executionId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecutionWorkflowResult)) return false;
        ExecutionWorkflowResult other = (ExecutionWorkflowResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.isSuccess == other.isIsSuccess() &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.executionId==null && other.getExecutionId()==null) || 
             (this.executionId!=null &&
              this.executionId.equals(other.getExecutionId())));
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
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getExecutionId() != null) {
            _hashCode += getExecutionId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecutionWorkflowResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecutionWorkflowResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSuccess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "IsSuccess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("executionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecutionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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