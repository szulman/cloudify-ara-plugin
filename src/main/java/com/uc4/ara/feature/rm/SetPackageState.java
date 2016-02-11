package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;

public class SetPackageState extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> packageIdArg;
    private CmdLineParser.Option<String> newStateArg;
    private CmdLineParser.Option<String> currentStateArg;
    private CmdLineParser.Option<String> ifCurrentStateNotMatchingArg;


    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Set the state of a Package specified by ID or name.");
        parser.setExamples(
                "java -jar dm-tool.jar rm SetPackageState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -pkg \"Test Activity\" -n \"RUNNING\" -c \"PLANNED\" -nm \"FAIL\"" +
                        "\r\njava -jar dm-tool.jar rm SetPackageState -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -pkg \"1234\" -n \"RUNNING\" -c \"PLANNED\" -nm \"SKIP\"" +
                        "\r\njava -jar dm-tool.jar rm SetPackageState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --package \"Test Activity\" -newstate \"RUNNING\" -currentstate \"PLANNED\" -notmatching \"FAIL\""
                );

        packageIdArg = parser.addHelp(
                parser.addStringOption("pkg", "package", true),
                "The name or id of the package where to set the state.");

        newStateArg = parser.addHelp(
                parser.addStringOption("n", "newstate", true),
                "The new state.");

        currentStateArg = parser.addHelp(
                parser.addStringOption("c", "currentstate", false),
                "The current state.");

        ifCurrentStateNotMatchingArg = parser.addHelp(
                parser.addStringOption("nm", "notmatching", true),
                "Indicates what happens if the current state does not match - valid values SKIP/FAIL (case insensitive).");
    }


    @Override
    public int run(String[] args) throws Exception {

        super.run(args);

        String packageId = parser.getOptionValue(packageIdArg);
        String newState = parser.getOptionValue(newStateArg);
        String currentState = parser.getOptionValue(currentStateArg);
        boolean failIfNotMatch = parser.getOptionValue(ifCurrentStateNotMatchingArg)
                .equalsIgnoreCase("FAIL") ? true : false;

        try {
            packageId = getIdFromNameOrIdv2("Package", packageId);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.EXCEPTION;
        }

        String oldState = null;
        try {
            oldState = searchOneProperty(service, "Package", "system_id", packageId,
                    "system_status");
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        if (currentState != null && currentState.length() > 0) {
            if (!oldState.equals(currentState)) {

                FeatureUtil.logMsg("Current State '" + currentState +
                        "' does not match [" + oldState + " <=>" + currentState + "]", MsgTypes.ERROR);
                FeatureUtil.logMsg("RESULT-STATUS: " + oldState);
                if (failIfNotMatch)
                    return ErrorCodes.ERROR;
                else
                    return ErrorCodes.OK;
            } else {
                FeatureUtil.logMsg("Current State '" +  currentState + "' is matching.", MsgTypes.INFO);
            }
        }

        // Set new Package State
        RmImportExportStructure structure = new RmImportExportStructure();
        RmEntity entity = structure.addEntity("Package");
        entity.addIdentityProperty("system_id", packageId);
        entity.addProperty("system_status", newState);

        try {
            FeatureUtil.logMsg("Setting State '" + newState
                    + "' [PackageID = " + packageId + "] ...", MsgTypes.INFO);

            importFunc(service, "Package", structure.createXml().toString());
            FeatureUtil.logMsg("RESULT-STATUS: " + newState);
            return ErrorCodes.OK;

        } catch(Exception e) {
            FeatureUtil.logMsg("Failed to set Property. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

    }


}
