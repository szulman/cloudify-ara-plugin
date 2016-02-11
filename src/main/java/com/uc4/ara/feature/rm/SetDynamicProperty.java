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

public class SetDynamicProperty extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> objectType;
    private CmdLineParser.Option<String> objectName;
    private CmdLineParser.Option<String> dynProp;
    private CmdLineParser.Option<String> namespace;
    private CmdLineParser.Option<String> value;
    private CmdLineParser.Option<String> valueType;
    private CmdLineParser.Option<String> dynPropType;

    private CmdLineParser.Option<Boolean> highlighted;
    private CmdLineParser.Option<Boolean> failIfTypeDiffers;
    private CmdLineParser.Option<Boolean> failIfExists;

    private String propertyType; // Options: Number, Protected, Short Text, Single Line Text, List...

    @Override
    public void initialize() {
        super.initialize();
        parser.setDescription("Sets a dynamic property with a specified name of a specified object on a given ReleaseManager Instance.");
        parser.setExamples(
                "java -jar dm-tool.jar rm SetDynamicProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test property\" -ns \"my/name/space\" -f \"TESTFOLDER\" -on \"My Object\" -ot \"TESTTYPE\" -v \"test value\" -vt \"Static\" -pt \"SingleLineText\" --failifexists" +
                        "\r\njava -jar dm-tool.jar rm SetDynamicProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test property\" --namespace \"my/name/space\" --folder \"TESTFOLDER\" --objectname \"My Object\" --objecttype \"TESTTYPE\" --value \"test value\" --valuetype \"Static\" --propertytype \"SingleLineText\" --failifexists" +
                        "\r\njava -jar dm-tool.jar rm SetDynamicProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test property\" -ns \"my/name/space\" -f \"TESTFOLDER\" -on 1234  -v \"test value\" -vt \"Static\" -pt \"SingleLineText\" -fe" +
                        "\r\njava -jar dm-tool.jar rm SetDynamicProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test property\" -ns \"my/name/space\" -f \"TESTFOLDER\" -on \"My Object\" -ot \"TESTTYPE\" -v \"test value\" -vt \"Static\" -pt \"SingleLineText\" "
                );

        objectType = parser.addHelp(
                parser.addStringOption("ot", "objecttype", false),
                "The type of the object onto the dynamic property will be created. " +
                "Can be omitted if an Id is specified for the object and not a name.");

        objectName = parser.addHelp(
                parser.addStringOption("on", "objectname", true),
                "The id or name of the object onto the dynamic property will be created.");

        dynProp = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name of the dynamic property to create.");

        namespace = parser.addHelp(
                parser.addStringOption("ns", "namespace", false),
                "The namespace of the dynamic property to create.");

        value = parser.addHelp(
                parser.addStringOption("v", "value", true),
                "The value of the dynamic property to create.");

        valueType = parser.addHelp(
                parser.addStringOption("vt", "valuetype", true),
                "The value type of the dynamic property to create. Options: Static, Expression, Prompt");

        dynPropType = parser.addHelp(
                parser.addStringOption("pt", "propertytype", true),
                "The dynamic property type of the dynamic property to create." +
                " Options: Number, Protected, Short Text, Single Line Text, List");

        highlighted = parser.addHelp(
                parser.addBooleanOption("h", "highlighted", false),
                "If specified, the dynamic property will be highlighted.");

        failIfTypeDiffers = parser.addHelp(
                parser.addBooleanOption("fd", "failiftypediffers", false),
                "If specified, the dynamic property will fail if the type differs to an already existing instance.");

        failIfExists = parser.addHelp(
                parser.addBooleanOption("fe", "failifexists", false),
                "If specified, the function will fail if the dynamic property already exists.");
    }


    @Override
    public int run(String[] args) throws Exception {
        super.run(args);

        String objectTypeValue = parser.getOptionValue(objectType);
        String objectNameValue = parser.getOptionValue(objectName);
        String dynPropValue = parser.getOptionValue(dynProp);
        String namespaceValue = parser.getOptionValue(namespace);
        String valueValue = parser.getOptionValue(value);
        String valueTypeValue = parser.getOptionValue(valueType);
        String dynPropTypeValue = parser.getOptionValue(dynPropType);

        boolean highlightedValue = parser.getOptionValue(highlighted) == null ? false : true;
        boolean failIfExistsValue = parser.getOptionValue(failIfExists) == null ? false : true;
        boolean failIfTypeDiffersValue = parser.getOptionValue(failIfTypeDiffers) == null ? false : true;

        if (objectTypeValue != null) {
            try {
                objectNameValue = getIdFromNameOrIdv2(objectTypeValue, objectNameValue);
            } catch(Exception e) {
                FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
                return ErrorCodes.ERROR;
            }
        } else {
            try {
                Long.parseLong(objectNameValue);
            } catch (NumberFormatException e) {
                FeatureUtil.logMsg("Object Type is not specified, Object Name must be a valid ID.", MsgTypes.ERROR);
                return ErrorCodes.ERROR;
            }
        }

        if (namespaceValue != null) {
            while (namespaceValue.endsWith("/")) {
                namespaceValue = namespaceValue.substring(0, namespaceValue.lastIndexOf('/'));
            }
            if (!namespaceValue.startsWith("/") && namespaceValue.length() > 0) {
                namespaceValue = "/" + namespaceValue;
            }
            dynPropValue = namespaceValue + "/" + dynPropValue;
        } else {
            dynPropValue = "/" + dynPropValue;
        }

        try {
            if (isPropertyExist(dynPropValue, objectNameValue)) {
                FeatureUtil.logMsg("Property '" + dynPropValue +
                        "'[ObjectID = " + objectNameValue + "] already exists.", MsgTypes.WARNING);

                if (failIfExistsValue) {
                    return ErrorCodes.ERROR;
                } else {
                    if (!propertyType.equals(dynPropTypeValue)) {
                        FeatureUtil.logMsg("Property '" + dynPropValue + "'[ObjectID = " + objectNameValue
                                + "] already exists with different Type '" + propertyType + "'", MsgTypes.WARNING);
                        if (failIfTypeDiffersValue)
                            return ErrorCodes.ERROR;
                        else
                            return ErrorCodes.OK;
                    }
                }
            }
        } catch (Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        // Create new Dynamic Property
        RmImportExportStructure structure = new RmImportExportStructure();
        RmEntity entity = structure.addEntity("DynamicProperty");
        entity.addIdentityProperty("system_full_name", dynPropValue);
        entity.addIdentityProperty("system_on_entity.system_id", objectNameValue);
        entity.addProperty("system_value", valueValue);
        entity.addProperty("system_mode", valueTypeValue);
        entity.addProperty("system_type", dynPropTypeValue);

        if(objectTypeValue != null)
            entity.addProperty("system_on_maintype", objectTypeValue);
        if(highlightedValue)
            entity.addProperty("system_is_highlighted", "true");

        try {
            FeatureUtil.logMsg("Creating/Updating Property '" + dynPropValue + "' ...", MsgTypes.INFO);
            importFunc(service, "DynamicProperty", structure.createXml().toString());
            FeatureUtil.logMsg("Property '" + dynPropValue + "' is created successfully.", MsgTypes.INFO);
            return ErrorCodes.OK;

        } catch(Exception e) {
            FeatureUtil.logMsg("Failed to set Dynamic Property. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }
    }


    private boolean isPropertyExist(String propName, String objectID) throws Exception {
        ArrayOfString properties = new ArrayOfString();
        ArrayOfString conditions = new ArrayOfString();

        conditions.getString().add("system_full_name eq '" + propName + "'");
        conditions.getString().add("system_on_entity.system_id eq '" + objectID + "'");
        properties.getString().add("system_type");
        String resultData = export("DynamicProperty", null, properties, conditions);

        List<String[]> resultValue = getTextContentFromXpath(resultData,
                "//Entity",
                "./Property[@name='system_type']/Value/text()");

        if (resultValue == null)
            throw new DataNotFoundException("Could not get exported Data.");

        if (resultValue.size() == 1) {
            propertyType = resultValue.get(0)[0];
            return true;

        } else if (resultValue.size() > 1) {
            throw new DataNotUniqueException("Property '"  + propName + "' [ObjectID = "
                    + objectID + "] is not unique");
        }

        return false;
    }

}

