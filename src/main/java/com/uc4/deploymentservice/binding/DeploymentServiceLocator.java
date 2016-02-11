/**
 * DeploymentServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice.binding;

public class DeploymentServiceLocator extends org.apache.axis.client.Service implements com.uc4.deploymentservice.binding.DeploymentService {

    public DeploymentServiceLocator() {
    }


    public DeploymentServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DeploymentServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_DeploymentService
    private java.lang.String BasicHttpBinding_DeploymentService_address = "https://vvnarademo01.sbb01.spoc.global/RM219/Service/DeploymentService.svc";

    public java.lang.String getBasicHttpBinding_DeploymentServiceAddress() {
        return BasicHttpBinding_DeploymentService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_DeploymentServiceWSDDServiceName = "BasicHttpBinding_DeploymentService";

    public java.lang.String getBasicHttpBinding_DeploymentServiceWSDDServiceName() {
        return BasicHttpBinding_DeploymentServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_DeploymentServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_DeploymentServiceWSDDServiceName = name;
    }

    public com.uc4.deploymentservice.DeploymentService getBasicHttpBinding_DeploymentService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_DeploymentService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_DeploymentService(endpoint);
    }

    public com.uc4.deploymentservice.DeploymentService getBasicHttpBinding_DeploymentService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentServiceStub _stub = new com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_DeploymentServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_DeploymentServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_DeploymentService_address = address;
    }


    // Use to get a proxy class for BasicHttpBinding_DeploymentService1
    private java.lang.String BasicHttpBinding_DeploymentService1_address = "http://vvnarademo01.sbb01.spoc.global/RM219/Service/DeploymentService.svc";

    public java.lang.String getBasicHttpBinding_DeploymentService1Address() {
        return BasicHttpBinding_DeploymentService1_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_DeploymentService1WSDDServiceName = "BasicHttpBinding_DeploymentService1";

    public java.lang.String getBasicHttpBinding_DeploymentService1WSDDServiceName() {
        return BasicHttpBinding_DeploymentService1WSDDServiceName;
    }

    public void setBasicHttpBinding_DeploymentService1WSDDServiceName(java.lang.String name) {
        BasicHttpBinding_DeploymentService1WSDDServiceName = name;
    }

    public com.uc4.deploymentservice.DeploymentService getBasicHttpBinding_DeploymentService1() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_DeploymentService1_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_DeploymentService1(endpoint);
    }

    public com.uc4.deploymentservice.DeploymentService getBasicHttpBinding_DeploymentService1(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentService1Stub _stub = new com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentService1Stub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_DeploymentService1WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_DeploymentService1EndpointAddress(java.lang.String address) {
        BasicHttpBinding_DeploymentService1_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.uc4.deploymentservice.DeploymentService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentServiceStub _stub = new com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentServiceStub(new java.net.URL(BasicHttpBinding_DeploymentService_address), this);
                _stub.setPortName(getBasicHttpBinding_DeploymentServiceWSDDServiceName());
                return _stub;
            }
            if (com.uc4.deploymentservice.DeploymentService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentService1Stub _stub = new com.uc4.deploymentservice.binding.BasicHttpBinding_DeploymentService1Stub(new java.net.URL(BasicHttpBinding_DeploymentService1_address), this);
                _stub.setPortName(getBasicHttpBinding_DeploymentService1WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_DeploymentService".equals(inputPortName)) {
            return getBasicHttpBinding_DeploymentService();
        }
        else if ("BasicHttpBinding_DeploymentService1".equals(inputPortName)) {
            return getBasicHttpBinding_DeploymentService1();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "DeploymentService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_DeploymentService"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_DeploymentService1"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_DeploymentService".equals(portName)) {
            setBasicHttpBinding_DeploymentServiceEndpointAddress(address);
        }
        else 
if ("BasicHttpBinding_DeploymentService1".equals(portName)) {
            setBasicHttpBinding_DeploymentService1EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
