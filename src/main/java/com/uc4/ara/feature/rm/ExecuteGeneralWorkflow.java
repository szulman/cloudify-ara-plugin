package com.uc4.ara.feature.rm;

import java.util.ArrayList;
import java.util.Calendar;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.deploymentservice.DeploymentServiceProxy;
import com.uc4.deploymentservice.DynamicProperty;
import com.uc4.deploymentservice.ExecutionWorkflowResult;

public class ExecuteGeneralWorkflow extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> worflowNameArg;
	private CmdLineParser.Option<String> startDateArg;
	private CmdLineParser.Option<String> needsManualStartArg;
	private CmdLineParser.Option<String> manualExecutorArg;
	private CmdLineParser.Option<String> queueArg;
	private CmdLineParser.Option<String> dynpropsArg;
	
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
		String dynprops = parser.getOptionValue(dynpropsArg);
		ArrayList<DynamicProperty> properties = new ArrayList<DynamicProperty>();

		if(dynprops != null && !dynprops.isEmpty())
		{
			String[] pairedDynProps = dynprops.split(",");
			
			for(String pairDynProp : pairedDynProps)
			{
				String[] tupple = pairDynProp.split("=",2);
				if(tupple.length == 2)
					properties.add(new DynamicProperty("Workflow", workflowName, tupple[0], tupple[1]));
				else
					Logger.log("No separator '=' found in DynamicProperty '" + pairDynProp + "'. Ignored!", loglevelValue);
			}
		}
		
		Calendar startDate = getCalendarFromString(startDateString);
		
		DeploymentServiceProxy deploymentService = DeploymentServiceFactory.getDeploymentServiceFromURLWithoutSSL(urlValue);
		
		ExecutionWorkflowResult result = deploymentService.executeGeneralWorkflow(usernameValue, passwordValue, workflowName, startDate, queue, needsManualStart, manualExecutor, properties.toArray(new DynamicProperty[]{})); 
	
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
		
		parser.setDescription("Executes a specified general workflow with certain parameters.");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm ExecuteGeneralWorkflow --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"Test Workflow\" -d \"31-12-2013T23:59:59Z\" -m \"YES\" -e \"1/AC/UC\" -q \"TEST_QUEUE\"" +
	    				"\r\njava -jar dm-tool.jar rm ExecuteGeneralWorkflow -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"Test Workflow\" " +
	    				"\r\njava -jar dm-tool.jar rm ExecuteGeneralWorkflow -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\"  -n \"Test Workflow\" -d \"31-12-2013T23:59:59Z\" -m \"NO\" -q \"TEST_QUEUE\"" +
	    				"\r\njava -jar dm-tool.jar rm ExecuteGeneralWorkflow --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --workflowname \"Test Workflow\" --startdate \"31-12-2013T23:59:59Z\" --manualstart \"YES\" --executor \"1/AC/UC\" --queue \"TEST_QUEUE\""
	    );	    
	    worflowNameArg = parser.addHelp(parser.addStringOption("n", "workflowname", true), "The name of the workflow to start.");
	    startDateArg = parser.addHelp(parser.addStringOption("d", "startdate", false), "The start date of the workflow to start.");
	    needsManualStartArg = parser.addHelp(parser.addStringOption("m", "manualstart", false), "Indicates if the workflow must be started manually - valid values YES/NO (case insensitive)");
	    manualExecutorArg = parser.addHelp(parser.addStringOption("e", "executor", false), "The name of the manual executor");
	    queueArg = parser.addHelp(parser.addStringOption("q", "queue", false), "The queue in which the workflow will be started.");
	    dynpropsArg = parser.addHelp(parser.addStringOption("dp",  "dynamicproperties", false), "The dynamicproperties to set for the workflow execution");
	}
}
