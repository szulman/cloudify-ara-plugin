/**
 * EnvironmentSnapshotValidationResultItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice;

public class EnvironmentSnapshotValidationResultItem  implements java.io.Serializable {
    private long snapshotId;

    private java.lang.String message;

    private long reportId;

    public EnvironmentSnapshotValidationResultItem() {
    }

    public EnvironmentSnapshotValidationResultItem(
           long snapshotId,
           java.lang.String message,
           long reportId) {
           this.snapshotId = snapshotId;
           this.message = message;
           this.reportId = reportId;
    }


    /**
     * Gets the snapshotId value for this EnvironmentSnapshotValidationResultItem.
     * 
     * @return snapshotId
     */
    public long getSnapshotId() {
        return snapshotId;
    }


    /**
     * Sets the snapshotId value for this EnvironmentSnapshotValidationResultItem.
     * 
     * @param snapshotId
     */
    public void setSnapshotId(long snapshotId) {
        this.snapshotId = snapshotId;
    }


    /**
     * Gets the message value for this EnvironmentSnapshotValidationResultItem.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this EnvironmentSnapshotValidationResultItem.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the reportId value for this EnvironmentSnapshotValidationResultItem.
     * 
     * @return reportId
     */
    public long getReportId() {
        return reportId;
    }


    /**
     * Sets the reportId value for this EnvironmentSnapshotValidationResultItem.
     * 
     * @param reportId
     */
    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnvironmentSnapshotValidationResultItem)) return false;
        EnvironmentSnapshotValidationResultItem other = (EnvironmentSnapshotValidationResultItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.snapshotId == other.getSnapshotId() &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            this.reportId == other.getReportId();
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
        _hashCode += new Long(getSnapshotId()).hashCode();
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        _hashCode += new Long(getReportId()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnvironmentSnapshotValidationResultItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "EnvironmentSnapshotValidationResultItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("snapshotId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "SnapshotId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ReportId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
