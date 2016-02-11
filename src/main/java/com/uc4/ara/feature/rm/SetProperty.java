package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;

public class SetProperty extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> objectTypeArg;
    private CmdLineParser.Option<String> objectNameArg;
    private CmdLineParser.Option<String> propertyArg;
    private CmdLineParser.Option<String> valueArg;

    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Sets a Property on a specified object by name and type");
        parser.setExamples(
                "java -jar dm-tool.jar rm SetProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -prp \"test property\" -v \"Test value\"" +
                        "\r\njava -jar dm-tool.jar rm SetProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"1234\" --property \"test property\" --value \"test value\""
                );

        objectTypeArg = parser.addHelp(
                parser.addStringOption("t", "type", true),
                "The type of the object.");

        objectNameArg = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name or ID of the object.");

        propertyArg = parser.addHelp(
                parser.addStringOption("prp", "property", true),
                "The name of the property on the object to set the value to.");

        valueArg = parser.addHelp(
                parser.addStringOption("v", "value", true),
                "The value of the property");
    }

    @Override
    public int run(String[] args) throws Exception {

        super.run(args);

        String objectType = parser.getOptionValue(objectTypeArg);
        String objectNameId = parser.getOptionValue(objectNameArg);
        String property = parser.getOptionValue(propertyArg);
        String value = parser.getOptionValue(valueArg);

        try {
            objectNameId = getIdFromNameOrIdv2(objectType, objectNameId);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.EXCEPTION;
        }

        // Set Property
        RmImportExportStructure structure = new RmImportExportStructure();
        RmEntity entity = structure.addEntity(objectType);
        entity.addIdentityProperty("system_id", objectNameId);
        entity.addProperty(property, value);

        try {
            FeatureUtil.logMsg("Setting Property '" + property + " = " + value
                    + "' [ObjectID = " + objectNameId + "] ...", MsgTypes.INFO);

            importFunc(service, objectType, structure.createXml().toString());
            FeatureUtil.logMsg("Property '" + property + " = " + value
                    + "' [ObjectID = " + objectNameId + "] is set successfully.", MsgTypes.INFO);
            return ErrorCodes.OK;

        } catch(Exception e) {
            FeatureUtil.logMsg("Failed to set Property. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;

        }
    }

}
