package com.uc4.ara.feature.rm;

import java.util.List;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ArrayOfString;

public class GetDynamicPropertyID extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> objectType;
    private CmdLineParser.Option<String> objectName;
    private CmdLineParser.Option<String> dynProp;
    private CmdLineParser.Option<String> namespace;



    @Override
    public void initialize() {
        super.initialize();
        parser.setDescription("Gets the ID of a dynamic property with a specified name of a specified object on a given ReleaseManager Instance.");


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
    }


    @Override
    public int run(String[] args) throws Exception {
        super.run(args);

        String objectTypeValue = parser.getOptionValue(objectType);
        String objectNameValue = parser.getOptionValue(objectName);
        String dynPropValue = parser.getOptionValue(dynProp);
        String namespaceValue = parser.getOptionValue(namespace);


        if (objectTypeValue != null) {
            try {
                objectNameValue = getIdFromNameOrIdv2(objectTypeValue, objectNameValue);
            } catch(Exception e) {
                FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
                return ErrorCodes.OK;
            }
        } else {
            try {
                Long.parseLong(objectNameValue);
            } catch (NumberFormatException e) {
                FeatureUtil.logMsg("Object Type is not specified, Object Name must be a valid ID.", MsgTypes.ERROR);
                return ErrorCodes.OK;
            }
        }

        ArrayOfString properties = new ArrayOfString();
        properties.getString().add("system_id");

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
            return ErrorCodes.OK;
        }

        List<String[]> resultValue = getTextContentFromXpath(resultData,
                "//Entity",
                "./Property[@name='system_id']/Value/text()");

        if (resultValue == null) {
            return ErrorCodes.OK;
        }

        if (resultValue.size() == 1) {
            FeatureUtil.logMsg("Property-ID: " + resultValue.get(0)[0]);

        } else if (resultValue.size() > 1) {
            FeatureUtil.logMsg("Data is not unique. Please check your Object Name or Type.", MsgTypes.ERROR);
            return ErrorCodes.OK;

        } else {
            FeatureUtil.logMsg("Dynamic Property '" + dynPropValue +
                    "' does not exist.", MsgTypes.INFO);

        }

        return ErrorCodes.OK;
    }


}
