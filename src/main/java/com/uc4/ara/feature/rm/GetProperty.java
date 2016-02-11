package com.uc4.ara.feature.rm;

import javax.xml.xpath.XPathExpressionException;

import com.uc4.ara.common.exception.DataNotFoundException;
import com.uc4.ara.common.exception.DataNotUniqueException;
import com.uc4.ara.common.exception.ImportExportServiceException;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;

public class GetProperty extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> objectTypeArg;
    private CmdLineParser.Option<String> objectNameArg;
    private CmdLineParser.Option<String> propertyArg;
    private CmdLineParser.Option<Boolean> failIfMissingArg;

    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Gets a Property from a specified object by name and type");
        parser.setExamples(
                "java -jar dm-tool.jar rm GetProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -prp \"test property\""
                + "\r\njava -jar dm-tool.jar rm GetProperty --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"1234\" --property \"test property\""
        );

        objectTypeArg = parser.addHelp(
                parser.addStringOption("t", "type", true),
                "The type of the object.");

        objectNameArg = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name or ID of the object.");

        propertyArg = parser.addHelp(
                parser.addStringOption("prp", "property", true),
                "The name of the property on the object to get the value from.");

        failIfMissingArg = parser.addHelp(
                parser.addBooleanOption("fm", "failifmissing", false),
                "Indicates that the function shall fail, if the property does not exist - valid values YES/NO (case insensitive).");
    }

    @Override
    public int run(String[] args) throws Exception {
        super.run(args);

        String objectType = parser.getOptionValue(objectTypeArg);
        String objectNameId = parser.getOptionValue(objectNameArg);
        String property = parser.getOptionValue(propertyArg);
        boolean failIfMissing = parser.getOptionValue(failIfMissingArg) != null ? true : false;

        try {
            objectNameId = getIdFromNameOrIdv2(objectType, objectNameId);
        } catch (Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            if (failIfMissing) {
                return ErrorCodes.ERROR;
            } else {
                return ErrorCodes.OK;
            }
        }

        try {
            String propValue = searchOneProperty(service, objectType, "system_id", objectNameId, property);
            FeatureUtil.logMsg("RESULT-PAIR: " + property + " = " + propValue);

        } catch (ImportExportServiceException e) {
            FeatureUtil.logMsg("Property does not exist. Message: " + e.getMessage(), MsgTypes.ERROR);
            if (failIfMissing) {
                return ErrorCodes.ERROR;
            } else {
                return ErrorCodes.OK;
            }
        } catch (DataNotUniqueException e) {
            FeatureUtil.logMsg("Property does not exist. Message: " + e.getMessage(), MsgTypes.ERROR);
            if (failIfMissing) {
                return ErrorCodes.ERROR;
            } else {
                return ErrorCodes.OK;
            }
        } catch (DataNotFoundException e) {
            FeatureUtil.logMsg("Property does not exist. Message: " + e.getMessage(), MsgTypes.ERROR);
            if (failIfMissing) {
                return ErrorCodes.ERROR;
            } else {
                return ErrorCodes.OK;
            }
        } catch (XPathExpressionException e) {
            FeatureUtil.logMsg("Property does not exist. Message: " + e.getMessage(), MsgTypes.ERROR);
            if (failIfMissing) {
                return ErrorCodes.ERROR;
            } else {
                return ErrorCodes.OK;
            }
        } catch (Exception e) {
            FeatureUtil.logMsg("Failed to get Property. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        return ErrorCodes.OK;
    }

}
