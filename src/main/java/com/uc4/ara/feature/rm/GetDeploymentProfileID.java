package com.uc4.ara.feature.rm;

import java.util.List;

import com.uc4.ara.common.exception.DataNotUniqueException;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ArrayOfString;

public class GetDeploymentProfileID extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> deploymentProfileArg;
    private CmdLineParser.Option<String> applicationArg;



    @Override
    public int run(String[] args) throws Exception {

        super.run(args);

        String deploymentProfile = parser.getOptionValue(deploymentProfileArg);
        String application = parser.getOptionValue(applicationArg);

        String profileID = deploymentProfile;

        ArrayOfString properties = new ArrayOfString();
        properties.getString().add("system_id");
        ArrayOfString conditions = new ArrayOfString();

        // ******************************* Get Deployment Profile ID from Application and Profile *******************************

        try {
            profileID = getIdFromNameOrIdv2("DeploymentProfile", deploymentProfile);
            FeatureUtil.logMsg("PROFILE-ID: " + profileID);
            return ErrorCodes.OK;

        } catch (DataNotUniqueException e) {
            conditions.getString().add("system_name eq '" + profileID + "'");

        } catch (Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.OK;
        }

        if (application != null && !application.equals("")) {
            try {
                application = getIdFromNameOrIdv2("Application", application);
            } catch(Exception e) {
                FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
                return ErrorCodes.OK;
            }

            conditions.getString().add("system_application.system_id eq '" + application + "'");

            String resultData = null;
            try {
                resultData = export("DeploymentProfile", null, properties, conditions);
            } catch(Exception e) {
                FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
                return ErrorCodes.OK;
            }

            List<String[]> resultValue = getTextContentFromXpath(resultData,
                    "//Entity",
                    "./Property[@name='system_id']/Value/text()");

            if (resultValue == null) {
                return ErrorCodes.OK;
            }

            if (resultValue.size() == 1) {
                profileID = resultValue.get(0)[0];

            } else if (resultValue.size() > 1) {
                FeatureUtil.logMsg("Data is not unique. Please check your Object Name or Type.", MsgTypes.ERROR);
                return ErrorCodes.OK;

            } else {
                FeatureUtil.logMsg("Profile '" + deploymentProfile +
                        "' does not exist.", MsgTypes.ERROR);
                return ErrorCodes.OK;
            }

        } else {
            return ErrorCodes.OK;
        }

        FeatureUtil.logMsg("PROFILE-ID: " + profileID);
        return ErrorCodes.OK;
    }

    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Get Deployment Profile ID from Application name(ID) and profile name(ID).");

        deploymentProfileArg = parser.addHelp(
                parser.addStringOption("pfl", "deploymentProfile", true),
                "The name or ID of the Deployment Profile to add the Target.");

        applicationArg = parser.addHelp(
                parser.addStringOption("app", "application", false),
                "The name or ID of the application. Required if Deployment Profile is not an ID.");

    }
}