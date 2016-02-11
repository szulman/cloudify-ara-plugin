package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;

public class SetFolder extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> objectTypeArg;
    private CmdLineParser.Option<String> objectNameArg;
    private CmdLineParser.Option<String> folderArg;


    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Sets the Folder of a specified object by name and type");
        parser.setExamples(
                "java -jar dm-tool.jar rm SetFolder --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -f \"test_folder\" " +
                        "\r\njava -jar dm-tool.jar rm SetFolder --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"1234\" --folder \"test_folder\" "
                );

        objectTypeArg = parser.addHelp(
                parser.addStringOption("t", "type", true),
                "The type of the object.");

        objectNameArg = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name or ID of the object.");

        folderArg = parser.addHelp(
                parser.addStringOption("f", "folder", true),
                "The name of the folder.");
    }


    @Override
    public int run(String[] args) throws Exception {

        super.run(args);

        String objectType = parser.getOptionValue(objectTypeArg);
        String objectNameId = parser.getOptionValue(objectNameArg);
        String folder = parser.getOptionValue(folderArg);

        try {
            objectNameId = getIdFromNameOrIdv2(objectType, objectNameId);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        RmImportExportStructure structure = new RmImportExportStructure();
        RmEntity entity = structure.addEntity(objectType);
        entity.addIdentityProperty("system_id", objectNameId);
        entity.addProperty("system_folder.system_name", folder.toUpperCase());

        try {
            FeatureUtil.logMsg("Setting Folder '" + folder
                    + "' [ObjectID = " + objectNameId + "] ...", MsgTypes.INFO);

            importFunc(service, objectType, structure.createXml().toString());
            FeatureUtil.logMsg("Folder '" + folder
                    + "' [ObjectID = " + objectNameId + "] is set successfully.", MsgTypes.INFO);
            return ErrorCodes.OK;

        } catch(Exception e) {
            FeatureUtil.logMsg("Failed to set Folder. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;

        }

    }


}

