package com.uc4.ara.feature.rm;

import com.uc4.ara.common.exception.DataNotFoundException;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.Result;

public class CreateDeploymentTarget extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> targetName;
    private CmdLineParser.Option<String> owner;
    private CmdLineParser.Option<String> folder;
    private CmdLineParser.Option<String> type;
    private CmdLineParser.Option<String> environmentId;
    private CmdLineParser.Option<String> agentName;
    private CmdLineParser.Option<Boolean> failIfExists;


    @Override
    public void initialize() {
        super.initialize();
        parser.setDescription("Creates a deploymenttarget with a specified name on a given ReleaseManager Instance. Optionally it can assign a given agent and assign the created target to an existing environment.");
        parser.setExamples(
                "java -jar dm-tool.jar rm CreateDeploymentTarget --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test target\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -e \"test environment\" -a \"test_agent\" --failifexists" +
                        "\r\njava -jar dm-tool.jar rm CreateDeploymentTarget -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test environment\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -e 1234 -a \"test_agent\" -fe" +
                        "\r\njava -jar dm-tool.jar rm CreateDeploymentTarget -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test environment\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -fe" +
                        "\r\njava -jar dm-tool.jar rm CreateDeploymentTarget --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test environment\" --owner \"1/AC/UC\" --folder \"TESTFOLDER\" --type \"TESTTYPE\"  --environment \"test environment\" --agent \"test_agent\" --failifexists"
                );

        targetName = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name of the target to create.");

        owner = parser.addHelp(
                parser.addStringOption("o", "owner", false),
                "The owner of the target to create - if omitted the connecting username will be taken.");

        folder = parser.addHelp(
                parser.addStringOption("f", "folder", true),
                "The folder of the target to create.");

        type = parser.addHelp(
                parser.addStringOption("t", "type", true),
                "The type of the target to create.");

        environmentId = parser.addHelp(
                parser.addStringOption("e", "environment", false),
                "The name or id of the environment to assign the target to create to.");

        agentName = parser.addHelp(
                parser.addStringOption("a", "agent", false),
                "The name of the agent to assign to the target to create.");

        failIfExists = parser.addHelp(
                parser.addBooleanOption("fe", "failifexists", false),
                "If specified, the function will fail if the target already exists. Otherwise update all settings.");
    }


    @Override
    public int run(String[] args) throws Exception {

        super.run(args);

        String targetNameValue = parser.getOptionValue(targetName);
        String ownerValue = parser.getOptionValue(owner);
        String folderValue = parser.getOptionValue(folder);
        String typeValue = parser.getOptionValue(type);
        String environmentIdValue = parser.getOptionValue(environmentId);
        String agentNameValue = parser.getOptionValue(agentName);
        boolean failIfExistsValue = parser.getOptionValue(failIfExists) == null ? false : true;

    	if ((ownerValue == null) || ownerValue.equals("")) {
			String[] tempUserArr = usernameValue.split("/");
			if (tempUserArr.length == 3) {
				ownerValue = usernameValue;
			} else {
				int i = usernameValue.indexOf("/");
				ownerValue = usernameValue.substring(i + 1);
			}

		}

        try {
            environmentIdValue = getIdFromNameOrIdv2("Environment", environmentIdValue);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.EXCEPTION;
        }

        boolean isTargetExist = true;

        try {
            getIdFromNameOrIdv2("DeploymentTarget", targetNameValue);
        } catch (Exception e) {
            if (e instanceof DataNotFoundException) {
                isTargetExist = false;
            } else {
                FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
                return ErrorCodes.ERROR;
            }
        }

        if (isTargetExist) {
            FeatureUtil.logMsg("Target '" + targetNameValue + "' already exists.", MsgTypes.WARNING);
            if (failIfExistsValue)
                return ErrorCodes.ERROR;
        }

        // Create XML to import new Deployment Target
        RmImportExportStructure structure = new RmImportExportStructure();
        RmEntity entity = structure.addEntity("DeploymentTarget");
        entity.setCustomType(typeValue);
        entity.addIdentityProperty("system_name", targetNameValue);
        entity.addProperty("system_owner.system_name", ownerValue);
        entity.addProperty("system_folder.system_name", folderValue.toUpperCase());
        // entity.addProperty("system_is_active", "True");

        if(agentNameValue != null)
            entity.addProperty("system_deployment_agent_name", agentNameValue);

        try {
            FeatureUtil.logMsg("Creating/Updating Target '" + targetNameValue + "' ...", MsgTypes.INFO);
            Result result = importFunc(service, "DeploymentTarget", structure.createXml().toString());
            FeatureUtil.logMsg("Target-ID: " + result.getStatus());

        } catch (Exception e) {
            FeatureUtil.logMsg("Failed to create/update Target. Message: " + e.getMessage(), MsgTypes.ERROR);

            return ErrorCodes.ERROR;
        }

        // Assign Deployment Target to environment
        if (environmentIdValue != null) {
            FeatureUtil.logMsg("Assigning Environment to Target ...", MsgTypes.INFO);

            structure = new RmImportExportStructure();
            entity = structure.addEntity("EnvironmentDeploymentTargetRelation");
            entity.addIdentityProperty("system_environment.system_id", environmentIdValue);
            entity.addIdentityProperty("system_deployment_target.system_name", targetNameValue);

            try {
                importFunc(service, "EnvironmentDeploymentTargetRelation", structure.createXml().toString());
            } catch (Exception e) {
                FeatureUtil.logMsg("Failed to assign Target to Environment." +
                        " Message: " + e.getMessage(), MsgTypes.ERROR);
                return ErrorCodes.EXCEPTION;
            }

            FeatureUtil.logMsg("Assign Environment to Target successfully.", MsgTypes.INFO);
        }

        return ErrorCodes.OK;
    }

}

