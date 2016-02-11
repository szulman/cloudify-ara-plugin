/**
 * DeploymentService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.uc4.deploymentservice;

public interface DeploymentService extends java.rmi.Remote {
    public java.lang.String getWebserviceVersion() throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ExecutionWorkflowResult executeApplicationWorkflow(java.lang.String user, java.lang.String pwd, java.lang.String workflowName, java.lang.String appName, java.lang.String packageName, java.lang.String profileName, java.util.Calendar startDate, java.lang.String queueName, java.lang.Boolean needsManualStart, java.lang.String manualConfirmer, com.uc4.deploymentservice.InstallationMode installationMode, com.uc4.deploymentservice.DynamicProperty[] properties) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ExecutionWorkflowResult executeGeneralWorkflow(java.lang.String user, java.lang.String pwd, java.lang.String workflowName, java.util.Calendar startDate, java.lang.String queueName, java.lang.Boolean needsManualStart, java.lang.String manualConfirmer, com.uc4.deploymentservice.DynamicProperty[] properties) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.RunQueueResult runQueue(java.lang.String triggerAuthenticationKey, java.lang.String queueName) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ObjectsCreationResult createActivitiesFromTemplate(java.lang.String user, java.lang.String pwd, java.lang.String activityTemplate, java.lang.String templateActivity, java.util.Calendar plannedStart, java.lang.String owner, java.lang.String folder) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ObjectsCreationResult createDeploymentTargetsFromTemplate(java.lang.String user, java.lang.String pwd, java.lang.String templateTarget, java.lang.String[] targetNames, java.lang.String owner, java.lang.String folder) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.TypePermissionsResult getTypePermissions(java.lang.String user, java.lang.String secret, java.lang.String technicalName) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.GetPropertiesResult getComponentProperties(java.lang.String user, java.lang.String secret, java.lang.String applicationName, java.lang.String componentName) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ActionResult addDeploymentTargetSnapshot(java.lang.String user, java.lang.String secret, java.lang.String environmentSnapshotGuid, java.lang.Integer processRunId, java.lang.String guid, java.lang.String hash, java.lang.String variables, java.lang.Long componentId, java.lang.Long deploymentTargetId, java.lang.String snapshotType) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ActionResult addDeploymentTargetSnapshotReport(java.lang.String user, java.lang.String secret, java.lang.Long reportId, java.lang.Integer processRunId, java.lang.String snapshotGuid, java.lang.String reportType, java.lang.String format, java.lang.String compression, java.lang.String xsltName, java.lang.String status, java.lang.String data) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ActionResult uploadDetailCompareFile(java.lang.String user, java.lang.String secret, java.lang.Integer processRunId, java.lang.String data) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.EnvironmentSnapshotValidationResult validateEnvironmentSnapshots(java.lang.String user, java.lang.String secret, long[] environmentSnapshotIds) throws java.rmi.RemoteException;
    public java.lang.Long getClientSettings() throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.DeploymentDescriptorResult getDeploymentDescriptor(java.lang.String user, java.lang.String secret, java.lang.Integer runID) throws java.rmi.RemoteException;
    public com.uc4.deploymentservice.ApprovalRuleDefinitionsResult getApprovalRuleDefinitions(java.lang.String language) throws java.rmi.RemoteException;
}