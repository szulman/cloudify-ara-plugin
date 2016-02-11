package com.uc4.ara.feature.rm;

import java.util.List;

import com.uc4.ara.common.exception.DataNotFoundException;
import com.uc4.ara.common.exception.DataNotUniqueException;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.Result;

public class CreateProfile extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> profileName;
    private CmdLineParser.Option<String> environmentId;
    private CmdLineParser.Option<String> applicationId;
    private CmdLineParser.Option<String> login;
    private CmdLineParser.Option<String> owner;
    private CmdLineParser.Option<String> folder;
    private CmdLineParser.Option<Boolean> failIfExists;


    @Override
    public void initialize() {
        super.initialize();
        parser.setDescription("Creates a deployment profile with a specified name on a given ReleaseManager Instance.");
        parser.setExamples(
                "java -jar dm-tool.jar rm CreateProfile --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test profile\" -a 1234 -e 5678 -o \"1/AC/UC\" -f \"TESTFOLDER\"  --failifexists" +
                        "\r\njava -jar dm-tool.jar rm CreateProfile -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test profile\" -a \"My App\" -e 5678 -l \"MY_LOGIN\" -o \"1/AC/UC\" -f \"TESTFOLDER\"  -fe" +
                        "\r\njava -jar dm-tool.jar rm CreateProfile -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test profile\" -a 1234 -e \"My Env\" -f \"TESTFOLDER\"  -fe" +
                        "\r\njava -jar dm-tool.jar rm CreateProfile --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test profile\" --owner \"1/AC/UC\" --folder \"TESTFOLDER\" --login \"MY_LOGIN\" --application \"My App\" --environment \"My Env\" --failifexists"
                );

        profileName = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name of the deployment profile to create.");

        environmentId = parser.addHelp(
                parser.addStringOption("e", "environment", true),
                "The name or ID of the environment that will be assigned to the deployment profile to create.");

        applicationId = parser.addHelp(
                parser.addStringOption("a", "application", true),
                "The name or ID of the application that will be assigned to the deployment profile to create.");

        owner = parser.addHelp(
                parser.addStringOption("o", "owner", true),
                "The owner of the deployment profile to create.");

        folder = parser.addHelp(
                parser.addStringOption("f", "folder", true),
                "The folder of the deployment profile to create.");

        login = parser.addHelp(
                parser.addStringOption("l", "login", false),
                "The login object of the deployment profile to create.");

        failIfExists = parser.addHelp(
                parser.addBooleanOption("fe", "failifexists", false),
                "If specified, the function will fail if the deployment profile already exists. Otherwise update the object.");
    }


    @Override
    public int run(String[] args) throws Exception {

        super.run(args);

        String profileNameValue = parser.getOptionValue(profileName);
        String environmentIdValue = parser.getOptionValue(environmentId);
        String applicationIdValue = parser.getOptionValue(applicationId);
        String loginValue = parser.getOptionValue(login);
        String ownerValue = parser.getOptionValue(owner);
        String folderValue = parser.getOptionValue(folder);
        boolean failIfExistsValue = parser.getOptionValue(failIfExists) == null ? false : true;;

        try {
            environmentIdValue = getIdFromNameOrIdv2("Environment", environmentIdValue);
            applicationIdValue = getIdFromNameOrIdv2("Application", applicationIdValue);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        try {
            if (isProfileExist(profileNameValue, applicationIdValue)) {
                FeatureUtil.logMsg("Profile '" + profileNameValue +
                        "'[ApplicationID = " + applicationIdValue + "] already exists.", MsgTypes.WARNING);
                if (failIfExistsValue)
                    return ErrorCodes.ERROR;
            }
        } catch (Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }


        // Create new Deployment Profile
        RmImportExportStructure structure = new RmImportExportStructure();
        RmEntity entity = structure.addEntity("DeploymentProfile");
        entity.addIdentityProperty("system_name", profileNameValue);
        entity.addIdentityProperty("system_application.system_id", applicationIdValue);
        entity.addProperty("system_environment.system_id", environmentIdValue);
        entity.addProperty("system_owner.system_name", ownerValue);
        entity.addProperty("system_folder.system_name", folderValue);
        if (loginValue != null && !loginValue.equals(""))
            entity.addProperty("system_login.system_name", loginValue);

        Result result = null;
        try {
            FeatureUtil.logMsg("Creating/Updating Profile '" + profileNameValue + "' ...", MsgTypes.INFO);
            result = importFunc(service, "DeploymentProfile", structure.createXml().toString());
            FeatureUtil.logMsg("Profile-ID: " + result.getStatus());
            return ErrorCodes.OK;

        } catch (Exception e) {
            FeatureUtil.logMsg("Failed to create Profile. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

    }


    boolean isProfileExist(String profileName, String applicationID) throws Exception {
        ArrayOfString properties = new ArrayOfString();
        ArrayOfString conditions = new ArrayOfString();

        conditions.getString().add("system_name eq '" + profileName + "'");
        conditions.getString().add("system_application.system_id eq '" + applicationID + "'");
        properties.getString().add("system_id");
        String resultData = export("DeploymentProfile", null, properties, conditions);

        List<String[]> resultValue = getTextContentFromXpath(resultData,
                "//Entity",
                "./Property[@name='system_id']/Value/text()");

        if (resultValue == null)
            throw new DataNotFoundException("Could not get exported Data.");

        if (resultValue.size() == 1) {
            return true;

        } else if (resultValue.size() > 1) {
            throw new DataNotUniqueException("Profile '"  + profileName + "' [ApplicationID = "
                    + applicationID + "] is not unique");
        }

        return false;
    }

}
