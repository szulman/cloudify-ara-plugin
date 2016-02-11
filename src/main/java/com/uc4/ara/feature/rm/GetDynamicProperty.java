package com.uc4.ara.feature.rm;

import java.util.List;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ArrayOfString;

public class GetDynamicProperty extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> objectType;
    private CmdLineParser.Option<String> objectName;
    private CmdLineParser.Option<String> dynProp;
    private CmdLineParser.Option<String> namespace;
    private CmdLineParser.Option<Boolean> failIfMissing;


    @Override
    public void initialize() {
        super.initialize();
        parser.setDescription("Gets the value of a dynamic property with a specified name of a specified object on a given ReleaseManager Instance.");
        parser.setExamples(
                "java -jar dm-tool.jar rm GetDynamicProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test-property\" --objectname \"my object\" --objecttype \"TESTTYPE\" --namespace \"my/name/space\" --failifmissing" +
                        "\r\njava -jar dm-tool.jar rm GetDynamicProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test-property\" -on \"my object\" -ot \"TESTTYPE\" -ns \"my/name/space\" -fm" +
                        "\r\njava -jar dm-tool.jar rm GetDynamicProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test-property\" -on 1234  -ns \"/my/name/space\""
                );


        objectType = parser.addHelp(
                parser.addStringOption("ot", "objecttype", false),
                "The type of the object of the dynamic property to get. Can be omitted if an Id is specified for the object and not a name.");

        objectName = parser.addHelp(
                parser.addStringOption("on", "objectname", true),
                "The id or name of the object of the dynamic property to get.");

        dynProp = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name of the dynamic property to get.");

        namespace = parser.addHelp(
                parser.addStringOption("ns", "namespace", false),
                "The namespace of the dynamic property to get.");

        failIfMissing = parser.addHelp(
                parser.addBooleanOption("fm", "failifmissing", false),
                "If specified, the function will fail if the dynamic property does not exist.");
    }


    @Override
    public int run(String[] args) throws Exception {
        super.run(args);

        String objectTypeValue = parser.getOptionValue(objectType);
        String objectNameValue = parser.getOptionValue(objectName);
        String dynPropValue = parser.getOptionValue(dynProp);
        String namespaceValue = parser.getOptionValue(namespace);
        boolean failIfMissingValue = parser.getOptionValue(failIfMissing) != null ? true : false;

        if (objectTypeValue != null) {
            try {
                objectNameValue = getIdFromNameOrIdv2(objectTypeValue, objectNameValue);
            } catch(Exception e) {
                FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
                if (failIfMissingValue) {
                    return ErrorCodes.ERROR;
                } else {
                	return ErrorCodes.OK;
                }
            }
        } else {
            try {
                Long.parseLong(objectNameValue);
            } catch (NumberFormatException e) {
                FeatureUtil.logMsg("Object Type is not specified, Object Name must be a valid ID.", MsgTypes.ERROR);
                return ErrorCodes.ERROR;
            }
        }

        ArrayOfString properties = new ArrayOfString();
        properties.getString().add("system_value");

        ArrayOfString conditions = new ArrayOfString();
        conditions.getString().add("system_on_entity.system_id eq '" + objectNameValue + "'");

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
        conditions.getString().add("system_full_name eq '" + dynPropValue + "'");


        String resultData = null;
        try {
            resultData = export("DynamicProperty", null, properties, conditions);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        List<String[]> resultValue = getTextContentFromXpath(resultData,
                "//Entity",
                "./Property[@name='system_value']/Value/text()");

        if (resultValue == null) {
            return ErrorCodes.ERROR;
        }

        if (resultValue.size() == 1) {
            FeatureUtil.logMsg("Property-Value: " + resultValue.get(0)[0]);

        } else if (resultValue.size() > 1) {
            FeatureUtil.logMsg("Data is not unique. Please check your Object Name or Type.", MsgTypes.ERROR);
            return ErrorCodes.ERROR;

        } else {
            FeatureUtil.logMsg("Dynamic Property '" + dynPropValue +
                    "' does not exist.", MsgTypes.ERROR);

            if(failIfMissingValue)
                return ErrorCodes.ERROR;
        }

        return ErrorCodes.OK;
    }


}
