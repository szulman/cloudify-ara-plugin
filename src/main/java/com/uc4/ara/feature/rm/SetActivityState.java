package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class SetActivityState extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> activityIdArg;
	private CmdLineParser.Option<String> newStateArg;
	private CmdLineParser.Option<String> currentStateArg;
	private CmdLineParser.Option<String> ifCurrentStateNotMatchingArg;

	private ImportExportServiceSoap service = null;

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);
		service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);
		String activityId = parser.getOptionValue(activityIdArg);
		String newState = parser.getOptionValue(newStateArg);
		String currentState = parser.getOptionValue(currentStateArg);
		String ifCurrentStateNotMatching = parser.getOptionValue(ifCurrentStateNotMatchingArg);

		String oldState = searchOneProperty(service, "Activity", "system_id",
				activityId, "system_status");

		Logger.log("Current State is " + oldState, loglevelValue);
		if (currentState.length() > 0
				&& ifCurrentStateNotMatching.equalsIgnoreCase("FAIL")
				&& !oldState.equals(currentState)) {
			Logger.log("Current State not matching, aborting", loglevelValue);
			Logger.log("RESULT-ID: " + oldState, loglevelValue);
			return ErrorCodes.ERROR;
		}
		if (currentState.length() > 0
				&& ifCurrentStateNotMatching.equalsIgnoreCase("SKIP")
				&& !oldState.equals(currentState)) {
			Logger.log("Current State not matching, skipping", loglevelValue);
			Logger.log("RESULT-ID: " + oldState, loglevelValue);
			return ErrorCodes.OK;
		}

		if (oldState.equals(newState)) {
			Logger.log("Current State already matching", loglevelValue);
			Logger.log("RESULT-ID: " + newState, loglevelValue);
			return ErrorCodes.OK;
		}

		RmImportExportStructure structure = new RmImportExportStructure();
		RmEntity entity = structure.addEntity("Activity");
		entity.addIdentityProperty("system_id", activityId);
		entity.addProperty("system_status", newState);

		// System.out.println(structure.createXml().toString());

		Result result = importFunc(service, "Activity", structure.createXml().toString());

		Logger.log("Server reported '" + result.getDescription() + "'", loglevelValue);
		Logger.log("Activity state of package " + activityId
				+ " set to " + newState, loglevelValue);
		Logger.log("RESULT-ID: " + newState, loglevelValue);

		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Set the state of an Activity specified by ID or name.");
		parser.setExamples(
				"java -jar dm-tool.jar rm SetActivityState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -a \"Test Activity\" -n \"RUNNING\" -c \"PLANNED\" -nm \"FAIL\"" +
						"\r\njava -jar dm-tool.jar rm SetActivityState -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -a \"1234\" -n \"RUNNING\" -c \"PLANNED\" -nm \"SKIP\"" +
						"\r\njava -jar dm-tool.jar rm SetActivityState --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --activity \"Test Activity\" -newstate \"RUNNING\" -currentstate \"PLANNED\" -notmatching \"FAIL\""
				);

		activityIdArg = parser.addHelp(parser.addStringOption("a", "activity", true), "The name or id of the activity where to set the state.");
		newStateArg = parser.addHelp(parser.addStringOption("n", "newstate", true), "The new state.");
		currentStateArg = parser.addHelp(parser.addStringOption("c", "currentstate", true), "The current state.");
		ifCurrentStateNotMatchingArg = parser.addHelp(parser.addStringOption("nm", "notmatching", true), "Indicates what happens if the current state does not match - valid values SKIP/FAIL (case insensitive).");
	}
}

