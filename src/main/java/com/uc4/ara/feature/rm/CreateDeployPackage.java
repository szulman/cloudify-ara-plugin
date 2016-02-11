package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.Result;

public class CreateDeployPackage extends AbstractRmFeatureNew {

    private CmdLineParser.Option<String> nameArg;
    private CmdLineParser.Option<String> applicationNameIdArg;
    private CmdLineParser.Option<String> customTypeArg;
    private CmdLineParser.Option<String> ownerArg;
    private CmdLineParser.Option<String> folderArg;

    @Override
    public void initialize() {
        super.initialize();

        parser.setDescription("Creates a deployment package with a specified name on a given ReleaseManager Instance.");
        parser.setExamples(
                "java -jar dm-tool.jar rm CreateDeployPackage --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test package\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -a \"test application\"" +
                        "\r\njava -jar dm-tool.jar rm CreateDeployPackage -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test package\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\"  -a \"test application\"" +
                        "\r\njava -jar dm-tool.jar rm CreateDeployPackage -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test package\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -a 1234" +
                        "\r\njava -jar dm-tool.jar rm CreateDeployPackage --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test package\" --owner \"1/AC/UC\" --folder \"TESTFOLDER\" --type \"TESTTYPE\"  --applicaton 1234"
                );

        nameArg = parser.addHelp(
                parser.addStringOption("n", "name", true),
                "The name of the package to create.");

        ownerArg = parser.addHelp(
                parser.addStringOption("o", "owner", true),
                "The owner of the package to create.");

        folderArg = parser.addHelp(
                parser.addStringOption("f", "folder", true),
                "The folder of the package to create.");

        customTypeArg = parser.addHelp(
                parser.addStringOption("t", "type", true),
                "The type of the package to create.");

        applicationNameIdArg = parser.addHelp(
                parser.addStringOption("a", "application", true),
                "The name or ID of the application to assign the package to create to.");

    }


    @Override
    public int run(String[] args) throws Exception {
        super.run(args);

        String name = parser.getOptionValue(nameArg);
        String applicationNameId = parser.getOptionValue(applicationNameIdArg);
        String customType = parser.getOptionValue(customTypeArg);
        String owner = parser.getOptionValue(ownerArg);
        String folder = parser.getOptionValue(folderArg);

        try {
            applicationNameId = getIdFromNameOrIdv2("Application", applicationNameId);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.EXCEPTION;
        }

        // Create new Deployment Package
        RmImportExportStructure structure = new RmImportExportStructure();
        RmEntity entity = structure.addEntity("Package");
        entity.setCustomType(customType);
        entity.addProperty("system_name", name);
        entity.addProperty("system_owner.system_name", owner);
        entity.addProperty("system_folder.system_name", folder.toUpperCase());
        entity.addProperty("system_application.system_id", applicationNameId);

        Result result = null;
        try {
            FeatureUtil.logMsg("Creating Package '" + name + "' ...", MsgTypes.INFO);
            result = importFunc(service, "Package", structure.createXml().toString());
            FeatureUtil.logMsg("Package-ID: " + result.getStatus());
            return ErrorCodes.OK;

        } catch(Exception e) {
            FeatureUtil.logMsg("Failed to create Package. Message: " + e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

    }


}
