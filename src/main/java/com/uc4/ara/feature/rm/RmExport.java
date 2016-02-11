package com.uc4.ara.feature.rm;

import java.io.File;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ArrayOfString;


public class RmExport extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> mainTypeArg;
    private CmdLineParser.Option<String> customTypeArg;
    private CmdLineParser.Option<String> nameIdArg;
    private CmdLineParser.Option<String> xmlFilenameArg;



    @Override
    public int run(String[] args) throws Exception {

        super.run(args);


        String mainType = parser.getOptionValue(mainTypeArg);
        String customType = parser.getOptionValue(customTypeArg);
        String nameId = parser.getOptionValue(nameIdArg);
        String xmlFilename = parser.getOptionValue(xmlFilenameArg);

        try {
            nameId = getIdFromNameOrIdv2(mainType, nameId);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        ArrayOfString properties = new ArrayOfString();
        ArrayOfString conditions = new ArrayOfString();

        conditions.getString().add("system_id eq '" + nameId + "'");

        try {
            String content = export(mainType, customType, properties, conditions);
            File file = new File(xmlFilename).getParentFile();
            if (!file.exists())
                file.mkdirs();

            FeatureUtil.writeFile(content, xmlFilename);
            Logger.log("file " + xmlFilename + " successfully exported", loglevelValue);
        } catch(Exception e) {
            FeatureUtil.logMsg("Fail to export Property. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

        return ErrorCodes.OK;
    }

    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Exports an object into an xml file.");
        parser.setExamples(
                "java -jar dm-tool.jar rm RmExport --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -f \"C:\\temp\\result.xml\" " +
                        "\r\njava -jar dm-tool.jar rm RmExport --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -ct \"test custom type\" -f \"C:\\temp\\result.xml\" " +
                        "\r\njava -jar dm-tool.jar rm RmExport --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --customtype \"test custom type\" --name \"1234\" --file \"C:\\temp\\result.xml\" "
                );

        mainTypeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The main type of the object.");
        customTypeArg = parser.addHelp(parser.addStringOption("ct", "customtype", false), "The custom type of the object.");
        nameIdArg = parser.addHelp(parser.addStringOption("n", "name", true), "The name or ID of the object.");
        xmlFilenameArg = parser.addHelp(parser.addStringOption("f", "file", true), "The name of the xml file to export to.");
    }
}