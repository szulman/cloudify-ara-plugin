/**
 * 
 */
package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.AbstractInternalFeature;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.deploymentservice.DeploymentServiceProxy;
//import com.uc4.deploymentservice.RunQueueResult;
import com.uc4.deploymentservice.RunQueueResult;

/**
 * @author extsts
 *
 */
public class RunQueue extends AbstractInternalFeature {

	private CmdLineParser.Option<String> url;
	private CmdLineParser.Option<String> authenticationKey;
	private CmdLineParser.Option<String> queueName;
	
	
	@Override
	public void initialize()
	{
		super.initialize();
		StringBuilder examples = new StringBuilder();
		StringBuilder description = new StringBuilder();
		
		//add description text
		description.append("This function triggers a predefined queue in a specified ReleaseManager instance.");
		
		//set parser parameters except loglevel
		url = parser.addHelp(
                parser.addStringOption("u", "url", true),
                "The url of the ReleaseManager instance that shall run the specified queue (i.e. http://localhost/RM). No trailing slash!");

		authenticationKey = parser.addHelp(
                parser.addPasswordOption("ak", "authenticationKey", true),
                "The trigger authentication key used to authenticate the caller against the ReleaseManager to trigger the specified queue.");	

		queueName = parser.addHelp(
                parser.addStringOption("qn", "queueName", true),
                "The name of the queue that shall be triggered. IMPORTANT: the name is case sensitive!");

		//add example text
		examples.append("java -jar dm-tool.jar rm RunQueue -u \"http://localhost/RM\" -ak \"--1012345678\" -qn \"TriggerQueue\"\r\n");
		examples.append("java -jar dm-tool.jar rm RunQueue --url \"http://localhost/RM\" --authenticationKey \"--1012345678\" -queueName \"TriggerQueue\" -ll ERROR\r\n");
		examples.append("java -jar dm-tool.jar rm RunQueue --url \"http://localhost/RM\" --authenticationKey \"--1012345678\" -queueName \"TriggerQueue\" --loglevel INFO\r\n");
		
		
		parser.setExamples(examples.toString());
		parser.setDescription(description.toString());
	}
	
	@Override
	public int run(String[] args) throws Exception
	{
		int errorCode = ErrorCodes.OK;
		
		super.run(args);
		
		Logger.logInfo("Setting parameter values", this.loglevelValue);
		
		//handle parameters
		String urlValue = parser.getOptionValue(url);
		String triggerAuthenticationKeyValue = parser.getOptionValue(authenticationKey);
		String queueNameValue = parser.getOptionValue(queueName);
		
		Logger.logDebug("url: " + urlValue, this.loglevelValue);
		Logger.logDebug("trigerAuthenticationKey: " + triggerAuthenticationKeyValue.charAt(0) + "****", this.loglevelValue);
		Logger.logDebug("queueName: " + queueNameValue, this.loglevelValue);
		
		//instantiate deploymentservice
		Logger.logInfo("Connecting to deplyomentservice", this.loglevelValue);
		DeploymentServiceProxy deploymentService = DeploymentServiceFactory.getDeploymentServiceFromURLWithoutSSL(urlValue);

		//call runqueue
		Logger.logInfo("Excuting deplyomentservice function runQueue for queue " + queueNameValue, this.loglevelValue);
		RunQueueResult result = deploymentService.runQueue(triggerAuthenticationKeyValue, queueNameValue);

		//handle result
		Logger.log("IsSuccess: " + result.isIsSuccess(), this.loglevelValue);
		
		if(!result.isIsSuccess()) {
			Logger.log("Error-Message: " + result.getMessage(), this.loglevelValue);
			errorCode = ErrorCodes.ERROR;
		}
		
		return errorCode;
	}
}