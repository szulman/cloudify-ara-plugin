package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ImportExportServiceSoap;

public class GetActivityState extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> activityIdArg;
	private ImportExportServiceSoap service = null;

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);
		service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);
		String activityId = parser.getOptionValue(activityIdArg);

		String oldState = searchOneProperty(service, "Activity", "system_id",
				activityId, "system_status");

		Logger.log("Current State is " + oldState, loglevelValue);
		Logger.log("RESULT: " + oldState, loglevelValue);

		return ErrorCodes.OK;
	}


	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Get the state of an Activity specified by ID or name.");
		parser.setExamples(
				"java -jar dm-tool.jar rm GetActivityState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -a \"Test Activity\"" +
						"\r\njava -jar dm-tool.jar rm GetActivityState -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -a \"1234\" " +
						"\r\njava -jar dm-tool.jar rm GetActivityState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --activity \"Test Activity\""
				);

		activityIdArg = parser.addHelp(parser.addStringOption("a", "activity", true), "The name or id of the activity to get the state from.");
	}

}
