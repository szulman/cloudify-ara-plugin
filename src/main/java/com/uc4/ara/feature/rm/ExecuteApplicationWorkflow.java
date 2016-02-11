package com.uc4.ara.feature.rm;

import java.util.Calendar;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.deploymentservice.DeploymentServiceProxy;
import com.uc4.deploymentservice.DynamicProperty;
import com.uc4.deploymentservice.ExecutionWorkflowResult;
import com.uc4.deploymentservice.InstallationMode;

public class ExecuteApplicationWorkflow extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> worflowNameArg;
	private CmdLineParser.Option<String> startDateArg;
	private CmdLineParser.Option<String> needsManualStartArg;
	private CmdLineParser.Option<String> manualExecutorArg;
	private CmdLineParser.Option<String> queueArg;
	private CmdLineParser.Option<String> appNameArg;
	private CmdLineParser.Option<String> packageNameArg;
	private CmdLineParser.Option<String> profileNameArg;
	private CmdLineParser.Option<String> skipIfInstalledArg;
	
	@Override
	public int run(String[] args) throws Exception {

		super.run(args);
		
		String workflowName = parser.getOptionValue(worflowNameArg);
		String startDateString = parser.getOptionValue(startDateArg);
		String needsManualStartString = parser.getOptionValue(needsManualStartArg);
		boolean needsManualStart = needsManualStartString != null ? 
				needsManualStartString.toLowerCase().equals("yes") : 
				false;
		String manualExecutor = parser.getOptionValue(manualExecutorArg);
		String queue = parser.getOptionValue(queueArg); 
		String appName = parser.getOptionValue(appNameArg);
		String packageName = parser.getOptionValue(packageNameArg);
		String profileName = parser.getOptionValue(profileNameArg);
		String skipIfInstalledString = parser.getOptionValue(skipIfInstalledArg);
		boolean skipIfInstalled = skipIfInstalledString != null ? 
				skipIfInstalledString.toLowerCase().equals("yes") : 
				false;
		
		DynamicProperty[] properties = new DynamicProperty[]{};
		
		
		Calendar startDate = getCalendarFromString(startDateString);
		
		DeploymentServiceProxy deploymentService = DeploymentServiceFactory.getDeploymentServiceFromURLWithoutSSL(urlValue);
		
		ExecutionWorkflowResult result = null;
		if(skipIfInstalled)
			result = deploymentService.executeApplicationWorkflow(usernameValue, passwordValue, workflowName, appName, packageName, profileName, 
				startDate, queue, needsManualStart, manualExecutor, InstallationMode.Skip, properties);
		else
			result = deploymentService.executeApplicationWorkflow(usernameValue, passwordValue, workflowName, appName, packageName, profileName, 
					startDate, queue, needsManualStart, manualExecutor, InstallationMode.Overwrite, properties);
	
		if(!result.isIsSuccess()) {
			Logger.log("ERROR: " + result.getMessage(), loglevelValue);
			return ErrorCodes.ERROR;
		}
		
		Logger.log("ExecutionId: " + result.getExecutionId(), loglevelValue);
		return ErrorCodes.OK;
	}


	@Override
	public void initialize() {
		super.initialize();
		
		parser.setDescription("Executes a specified application workflow with certain parameters.");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm ExecuteApplicationWorkflow --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"Test Workflow\" -a \"Test Application\" -pkg \"Test Package\" -prf \"Test Profile\" -s \"YES\" -d \"31-12-2013T23:59:59Z\" -m \"YES\" -e \"1/AC/UC\" -q \"TEST_QUEUE\" " +
	    				"\r\njava -jar dm-tool.jar rm ExecuteApplicationWorkflow -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"Test Workflow\" -a \"Test Application\" -pkg \"Test Package\" -prf \"Test Profile\" " +
	    				"\r\njava -jar dm-tool.jar rm ExecuteApplicationWorkflow -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\"  -n \"Test Workflow\" -d \"31-12-2013T23:59:59Z\" -m \"NO\" -q \"TEST_QUEUE\" -a \"Test Application\" -pkg \"Test Package\" -prf \"Test Profile\"" +
	    				"\r\njava -jar dm-tool.jar rm ExecuteApplicationWorkflow --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --workflowname \"Test Workflow\" --application \"Test Application\" --package \"Test Package\" --profile \"Test Profile\" --startdate \"31-12-2013T23:59:59Z\" --manualstart \"YES\" --executor \"1/AC/UC\" --queue \"TEST_QUEUE\" --skipifinstalled \"NO\""
	    );	    
	    worflowNameArg = parser.addHelp(parser.addStringOption("n", "workflowname", true), "The name of the workflow to start.");
	    startDateArg = parser.addHelp(parser.addStringOption("d", "startdate", false), "The start date of the workflow to start.");
	    needsManualStartArg = parser.addHelp(parser.addStringOption("m", "manualstart", false), "Indicates if the workflow must be started manually - valid values YES/NO (case insensitive).");
	    manualExecutorArg = parser.addHelp(parser.addStringOption("e", "executor", false), "The name of the manual executor.");
	    queueArg = parser.addHelp(parser.addStringOption("q", "queue", false), "The queue in which the workflow will be started.");
	    appNameArg = parser.addHelp(parser.addStringOption("a", "application", true), "The application assigned to the workflow.");
	    packageNameArg = parser.addHelp(parser.addStringOption("pkg", "package", true), "The package assigned to the workflow.");
	    profileNameArg = parser.addHelp(parser.addStringOption("prf", "profile", true), "The profile assigned to the workflow.");
	    skipIfInstalledArg = parser.addHelp(parser.addStringOption("s", "skipifinstalled", false), "indicates if the workflow will be skipped if it is already installed - valid values YES/NO (case insensitive).");
	}

}

