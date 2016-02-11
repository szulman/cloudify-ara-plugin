package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ImportExportServiceSoap;

public class GetPackageState extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> packageIdArg;


	@Override
	public int run(String[] args) throws Exception {

		super.run(args);

		String packageId = parser.getOptionValue(packageIdArg);
        try {
            packageId = getIdFromNameOrIdv2("Package", packageId);

        } catch (Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }
		String oldState = searchOneProperty(service, "Package", "system_id", packageId,
				"system_status");

		Logger.log("Package state of package '" + packageId + "' is '"
				+ oldState + "'", loglevelValue);
		Logger.log("RESULT: " + oldState, loglevelValue);

		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Get the state of a Package specified by ID or name.");
		parser.setExamples(
				"java -jar dm-tool.jar rm GetPackageState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -pkg \"Test Package\"" +
						"\r\njava -jar dm-tool.jar rm GetPackageState -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -pkg \"1234\" " +
						"\r\njava -jar dm-tool.jar rm GetPackageState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --package \"Test Activity\""
				);

		packageIdArg = parser.addHelp(parser.addStringOption("pkg", "package", true), "The name or id of the package to get the state from.");
	}

}
