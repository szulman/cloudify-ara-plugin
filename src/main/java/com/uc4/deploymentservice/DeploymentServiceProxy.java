package com.uc4.deploymentservice;

import java.rmi.RemoteException;
import java.util.Calendar;

import com.uc4.deploymentservice.binding.DeploymentServiceLocator;


public class DeploymentServiceProxy implements com.uc4.deploymentservice.DeploymentService {
  private String _endpoint = null;
  private com.uc4.deploymentservice.DeploymentService deploymentService = null;
  
  public DeploymentServiceProxy() {
    _initDeploymentServiceProxy();
  }
  
  public DeploymentServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDeploymentServiceProxy();
  }
  
  private void _initDeploymentServiceProxy() {
    try {
      deploymentService = (new DeploymentServiceLocator()).getBasicHttpBinding_DeploymentService();
      if (deploymentService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)deploymentService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)deploymentService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (deploymentService != null)
      ((javax.xml.rpc.Stub)deploymentService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.uc4.deploymentservice.DeploymentService getDeploymentService_PortType() {
    if (deploymentService == null)
      _initDeploymentServiceProxy();
    return deploymentService;
  }
	
	@Override
	public String getWebserviceVersion() throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.getWebserviceVersion();
	}	
	
	@Override
	public ExecutionWorkflowResult executeApplicationWorkflow(String user,
			String pwd, String workflowName, String appName, String packageName,
			String profileName, Calendar startDate, String queueName,
			Boolean needsManualStart, String manualConfirmer,
			InstallationMode installationMode, DynamicProperty[] properties)
			throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.executeApplicationWorkflow(user, pwd, workflowName, appName, packageName, profileName, startDate, queueName, needsManualStart, manualConfirmer, installationMode, properties);
	}

	@Override
	public ExecutionWorkflowResult executeGeneralWorkflow(String user, String pwd,
			String workflowName, Calendar startDate, String queueName,
			Boolean needsManualStart, String manualConfirmer,
			DynamicProperty[] properties) throws RemoteException {
	
		if (deploymentService == null)
		      _initDeploymentServiceProxy();		
		
		return deploymentService.executeGeneralWorkflow(user, pwd, workflowName, startDate, queueName, needsManualStart, manualConfirmer, properties);
	}	
	
	@Override
	public RunQueueResult runQueue(String triggerAuthenticationKey,
			String queueName) throws RemoteException {
		if (deploymentService == null)
			_initDeploymentServiceProxy();
		return deploymentService.runQueue(triggerAuthenticationKey, queueName);

	}
	
	@Override
	public ObjectsCreationResult createActivitiesFromTemplate(String user,
			String pwd, String activityTemplate, String templateActivity,
			Calendar plannedStart, String owner, String folder)
			throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.createActivitiesFromTemplate(user, pwd, activityTemplate, templateActivity, plannedStart, owner, folder);
	}
	
	@Override
	public ObjectsCreationResult createDeploymentTargetsFromTemplate(String user,
			String pwd, String templateTarget, String[] targetNames, String owner,
			String folder) throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.createDeploymentTargetsFromTemplate(user, pwd, templateTarget, targetNames, owner, folder);
	}
	
	@Override
	public TypePermissionsResult getTypePermissions(String user, String secret,
			String technicalName) throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.getTypePermissions(user, secret, technicalName);
	}
	
	@Override
	public GetPropertiesResult getComponentProperties(String user, String secret,
			String applicationName, String componentName) throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.getComponentProperties(user, secret, applicationName, componentName);
	}
	
	@Override
	public ActionResult addDeploymentTargetSnapshot(String user, String secret,
			String environmentSnapshotGuid, Integer processRunId, String guid,
			String hash, String variables, Long componentId,
			Long deploymentTargetId, String snapshotType) throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.addDeploymentTargetSnapshot(user, secret, environmentSnapshotGuid, processRunId, guid, hash, variables, componentId, deploymentTargetId, snapshotType);
	}
	
	@Override
	public ActionResult addDeploymentTargetSnapshotReport(String user,
			String secret, Long reportId, Integer processRunId,
			String snapshotGuid, String reportType, String format,
			String compression, String xsltName, String status, String data)
			throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.addDeploymentTargetSnapshotReport(user, secret, reportId, processRunId, snapshotGuid, reportType, format, compression, xsltName, status, data);
	}
	
	@Override
	public ActionResult uploadDetailCompareFile(String user, String secret,
			Integer processRunId, String data) throws RemoteException {
		// TODO Auto-generated method stub
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.uploadDetailCompareFile(user, secret, processRunId, data);
	}
	
	@Override
	public EnvironmentSnapshotValidationResult validateEnvironmentSnapshots(
			String user, String secret, long[] environmentSnapshotIds)
			throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.validateEnvironmentSnapshots(user, secret, environmentSnapshotIds);
	}
	
	@Override
	public Long getClientSettings() throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.getClientSettings();
	}
	
	@Override
	public DeploymentDescriptorResult getDeploymentDescriptor(String user,
			String secret, Integer runID) throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.getDeploymentDescriptor(user, secret, runID);
	}
	
	@Override
	public ApprovalRuleDefinitionsResult getApprovalRuleDefinitions(String language)
			throws RemoteException {
		if (deploymentService == null)
		      _initDeploymentServiceProxy();
		
		return deploymentService.getApprovalRuleDefinitions(language);
	}
}