/**
 * BasicHttpBinding_DeploymentService1Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice.binding;

public class BasicHttpBinding_DeploymentService1Stub extends org.apache.axis.client.Stub implements com.uc4.deploymentservice.DeploymentService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[15];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetWebserviceVersion");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetWebserviceVersionResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ExecuteApplicationWorkflow");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "workflowName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "appName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "packageName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "profileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "queueName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "needsManualStart"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "manualConfirmer"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "installationMode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "InstallationMode"), com.uc4.deploymentservice.InstallationMode.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "properties"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfDynamicProperty"), com.uc4.deploymentservice.DynamicProperty[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicProperty"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecutionWorkflowResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ExecutionWorkflowResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecuteApplicationWorkflowResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ExecuteGeneralWorkflow");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "workflowName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "queueName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "needsManualStart"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), java.lang.Boolean.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "manualConfirmer"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "properties"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfDynamicProperty"), com.uc4.deploymentservice.DynamicProperty[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicProperty"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecutionWorkflowResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ExecutionWorkflowResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecuteGeneralWorkflowResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RunQueue");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "triggerAuthenticationKey"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "queueName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "RunQueueResult"));
        oper.setReturnClass(com.uc4.deploymentservice.RunQueueResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "RunQueueResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateActivitiesFromTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "activityTemplate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "templateActivity"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "plannedStart"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "owner"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "folder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ObjectsCreationResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ObjectsCreationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "CreateActivitiesFromTemplateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CreateDeploymentTargetsFromTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "pwd"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "templateTarget"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "targetNames"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring"), java.lang.String[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "owner"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "folder"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ObjectsCreationResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ObjectsCreationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "CreateDeploymentTargetsFromTemplateResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetTypePermissions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "secret"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "technicalName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "TypePermissionsResult"));
        oper.setReturnClass(com.uc4.deploymentservice.TypePermissionsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetTypePermissionsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetComponentProperties");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "secret"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "applicationName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "componentName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetPropertiesResult"));
        oper.setReturnClass(com.uc4.deploymentservice.GetPropertiesResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetComponentPropertiesResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddDeploymentTargetSnapshot");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "secret"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "environmentSnapshotGuid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "processRunId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "guid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "hash"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "variables"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "componentId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "deploymentTargetId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "snapshotType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ActionResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ActionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "AddDeploymentTargetSnapshotResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AddDeploymentTargetSnapshotReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "secret"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "reportId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), java.lang.Long.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "processRunId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "snapshotGuid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "reportType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "format"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "compression"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "xsltName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "status"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "data"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ActionResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ActionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "AddDeploymentTargetSnapshotReportResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("UploadDetailCompareFile");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "secret"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "processRunId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "data"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ActionResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ActionResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "UploadDetailCompareFileResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ValidateEnvironmentSnapshots");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "secret"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "environmentSnapshotIds"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOflong"), long[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "long"));
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "EnvironmentSnapshotValidationResult"));
        oper.setReturnClass(com.uc4.deploymentservice.EnvironmentSnapshotValidationResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ValidateEnvironmentSnapshotsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetClientSettings");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        oper.setReturnClass(java.lang.Long.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetClientSettingsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetDeploymentDescriptor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "user"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "secret"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "runID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DeploymentDescriptorResult"));
        oper.setReturnClass(com.uc4.deploymentservice.DeploymentDescriptorResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetDeploymentDescriptorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetApprovalRuleDefinitions");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "language"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalRuleDefinitionsResult"));
        oper.setReturnClass(com.uc4.deploymentservice.ApprovalRuleDefinitionsResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetApprovalRuleDefinitionsResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

    }

    public BasicHttpBinding_DeploymentService1Stub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public BasicHttpBinding_DeploymentService1Stub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public BasicHttpBinding_DeploymentService1Stub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOflong");
            cachedSerQNames.add(qName);
            cls = long[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long");
            qName2 = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "long");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ActionResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ActionResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalEntityDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalEntityDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalOperatorDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalOperatorDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalPropertyDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalPropertyDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalRuleDefinitionsResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalRuleDefinitionsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalTypeDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalTypeDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalValueDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalValueDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfApprovalEntityDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalEntityDefinition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalEntityDefinition");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalEntityDefinition");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfApprovalOperatorDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalOperatorDefinition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalOperatorDefinition");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalOperatorDefinition");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfApprovalPropertyDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalPropertyDefinition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalPropertyDefinition");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalPropertyDefinition");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfApprovalTypeDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalTypeDefinition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalTypeDefinition");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalTypeDefinition");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfApprovalValueDefinition");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ApprovalValueDefinition[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalValueDefinition");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ApprovalValueDefinition");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfDynamicProperty");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.DynamicProperty[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicProperty");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicProperty");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfDynamicPropertyInfo");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.DynamicPropertyInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicPropertyInfo");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicPropertyInfo");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfEnvironmentSnapshotValidationResultItem");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.EnvironmentSnapshotValidationResultItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "EnvironmentSnapshotValidationResultItem");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "EnvironmentSnapshotValidationResultItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ArrayOfTypePermission");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.TypePermission[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "TypePermission");
            qName2 = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "TypePermission");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DeploymentDescriptorResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.DeploymentDescriptorResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicProperty");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.DynamicProperty.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "DynamicPropertyInfo");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.DynamicPropertyInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "EnvironmentSnapshotValidationResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.EnvironmentSnapshotValidationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "EnvironmentSnapshotValidationResultItem");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.EnvironmentSnapshotValidationResultItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecutionWorkflowResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ExecutionWorkflowResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetPropertiesResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.GetPropertiesResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "InstallationMode");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.InstallationMode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ObjectsCreationResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.ObjectsCreationResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "RunQueueResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.RunQueueResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "TypePermission");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.TypePermission.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "TypePermissionsResult");
            cachedSerQNames.add(qName);
            cls = com.uc4.deploymentservice.TypePermissionsResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String getWebserviceVersion() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/GetWebserviceVersion");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetWebserviceVersion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ExecutionWorkflowResult executeApplicationWorkflow(java.lang.String user, java.lang.String pwd, java.lang.String workflowName, java.lang.String appName, java.lang.String packageName, java.lang.String profileName, java.util.Calendar startDate, java.lang.String queueName, java.lang.Boolean needsManualStart, java.lang.String manualConfirmer, com.uc4.deploymentservice.InstallationMode installationMode, com.uc4.deploymentservice.DynamicProperty[] properties) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/ExecuteApplicationWorkflow");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecuteApplicationWorkflow"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, pwd, workflowName, appName, packageName, profileName, startDate, queueName, needsManualStart, manualConfirmer, installationMode, properties});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ExecutionWorkflowResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ExecutionWorkflowResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ExecutionWorkflowResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ExecutionWorkflowResult executeGeneralWorkflow(java.lang.String user, java.lang.String pwd, java.lang.String workflowName, java.util.Calendar startDate, java.lang.String queueName, java.lang.Boolean needsManualStart, java.lang.String manualConfirmer, com.uc4.deploymentservice.DynamicProperty[] properties) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/ExecuteGeneralWorkflow");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ExecuteGeneralWorkflow"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, pwd, workflowName, startDate, queueName, needsManualStart, manualConfirmer, properties});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ExecutionWorkflowResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ExecutionWorkflowResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ExecutionWorkflowResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.RunQueueResult runQueue(java.lang.String triggerAuthenticationKey, java.lang.String queueName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/RunQueue");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "RunQueue"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {triggerAuthenticationKey, queueName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.RunQueueResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.RunQueueResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.RunQueueResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ObjectsCreationResult createActivitiesFromTemplate(java.lang.String user, java.lang.String pwd, java.lang.String activityTemplate, java.lang.String templateActivity, java.util.Calendar plannedStart, java.lang.String owner, java.lang.String folder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/CreateActivitiesFromTemplate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "CreateActivitiesFromTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, pwd, activityTemplate, templateActivity, plannedStart, owner, folder});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ObjectsCreationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ObjectsCreationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ObjectsCreationResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ObjectsCreationResult createDeploymentTargetsFromTemplate(java.lang.String user, java.lang.String pwd, java.lang.String templateTarget, java.lang.String[] targetNames, java.lang.String owner, java.lang.String folder) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/CreateDeploymentTargetsFromTemplate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "CreateDeploymentTargetsFromTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, pwd, templateTarget, targetNames, owner, folder});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ObjectsCreationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ObjectsCreationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ObjectsCreationResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.TypePermissionsResult getTypePermissions(java.lang.String user, java.lang.String secret, java.lang.String technicalName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/GetTypePermissions");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetTypePermissions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, secret, technicalName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.TypePermissionsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.TypePermissionsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.TypePermissionsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.GetPropertiesResult getComponentProperties(java.lang.String user, java.lang.String secret, java.lang.String applicationName, java.lang.String componentName) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/GetComponentProperties");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetComponentProperties"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, secret, applicationName, componentName});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.GetPropertiesResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.GetPropertiesResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.GetPropertiesResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ActionResult addDeploymentTargetSnapshot(java.lang.String user, java.lang.String secret, java.lang.String environmentSnapshotGuid, java.lang.Integer processRunId, java.lang.String guid, java.lang.String hash, java.lang.String variables, java.lang.Long componentId, java.lang.Long deploymentTargetId, java.lang.String snapshotType) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/AddDeploymentTargetSnapshot");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "AddDeploymentTargetSnapshot"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, secret, environmentSnapshotGuid, processRunId, guid, hash, variables, componentId, deploymentTargetId, snapshotType});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ActionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ActionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ActionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ActionResult addDeploymentTargetSnapshotReport(java.lang.String user, java.lang.String secret, java.lang.Long reportId, java.lang.Integer processRunId, java.lang.String snapshotGuid, java.lang.String reportType, java.lang.String format, java.lang.String compression, java.lang.String xsltName, java.lang.String status, java.lang.String data) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/AddDeploymentTargetSnapshotReport");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "AddDeploymentTargetSnapshotReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, secret, reportId, processRunId, snapshotGuid, reportType, format, compression, xsltName, status, data});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ActionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ActionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ActionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ActionResult uploadDetailCompareFile(java.lang.String user, java.lang.String secret, java.lang.Integer processRunId, java.lang.String data) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/UploadDetailCompareFile");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "UploadDetailCompareFile"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, secret, processRunId, data});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ActionResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ActionResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ActionResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.EnvironmentSnapshotValidationResult validateEnvironmentSnapshots(java.lang.String user, java.lang.String secret, long[] environmentSnapshotIds) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/ValidateEnvironmentSnapshots");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "ValidateEnvironmentSnapshots"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, secret, environmentSnapshotIds});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.EnvironmentSnapshotValidationResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.EnvironmentSnapshotValidationResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.EnvironmentSnapshotValidationResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.Long getClientSettings() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/GetClientSettings");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetClientSettings"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.Long) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.Long) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.Long.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.DeploymentDescriptorResult getDeploymentDescriptor(java.lang.String user, java.lang.String secret, java.lang.Integer runID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/GetDeploymentDescriptor");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetDeploymentDescriptor"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {user, secret, runID});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.DeploymentDescriptorResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.DeploymentDescriptorResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.DeploymentDescriptorResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.uc4.deploymentservice.ApprovalRuleDefinitionsResult getApprovalRuleDefinitions(java.lang.String language) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.uc4.com/bond/2011-01/DeploymentService/DeploymentService/GetApprovalRuleDefinitions");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.uc4.com/bond/2011-01/DeploymentService", "GetApprovalRuleDefinitions"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {language});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.uc4.deploymentservice.ApprovalRuleDefinitionsResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.uc4.deploymentservice.ApprovalRuleDefinitionsResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.uc4.deploymentservice.ApprovalRuleDefinitionsResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
