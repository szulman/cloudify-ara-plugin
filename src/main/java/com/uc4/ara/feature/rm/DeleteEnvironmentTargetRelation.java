package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.Result;

public class DeleteEnvironmentTargetRelation extends AbstractRmFeatureNew {


	private CmdLineParser.Option<String> environmentNameIdArg;
	private CmdLineParser.Option<String> targetNameIdArg;

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);

		String environmentNameId = parser.getOptionValue(environmentNameIdArg);
		String targetNameId = parser.getOptionValue(targetNameIdArg);

        try {
            environmentNameId = getIdFromNameOrIdv2("Environment", environmentNameId);
            targetNameId = getIdFromNameOrIdv2("DeploymentTarget", targetNameId);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.EXCEPTION;
        }
        
		RmImportExportStructure structure = new RmImportExportStructure();
		RmEntity entity = structure.addEntity("EnvironmentDeploymentTargetRelation");
		entity.addIdentityProperty("system_environment.system_id", environmentNameId);
		entity.addIdentityProperty("system_deployment_target.system_id", targetNameId);

		Result result = service.delete(usernameValue, passwordValue,
				null, "XML", true, structure.createXml().toString());

		try {
			result = handleResult(result, service);
			FeatureUtil.logMsg("Object is successfully deleted", MsgTypes.INFO);

		} catch (Exception e) {
			FeatureUtil.logMsg("Fail to delete object. Message: " + e.getMessage(), MsgTypes.ERROR);
			return ErrorCodes.ERROR;
		}
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		targetNameIdArg = parser.addHelp(
		        parser.addStringOption("t", "target", true), 
		        "The name or id of target.");

		environmentNameIdArg = parser.addHelp(
                parser.addStringOption("e", "environment", false),
                "The name or id of the environment to assign the target to create to.");
	}
}
