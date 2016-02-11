package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ImportExportServiceSoap;

public class GetFolder extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> objectTypeArg;
	private CmdLineParser.Option<String> objectNameArg;



	@Override
	public int run(String[] args) throws Exception {

		super.run(args);


		String objectType = parser.getOptionValue(objectTypeArg);
		String objectNameId = parser.getOptionValue(objectNameArg);

        try {
            objectNameId = getIdFromNameOrIdv2(objectType, objectNameId);
            String folder = searchOneProperty(service, objectType, "system_id", objectNameId,
                    "system_folder.system_name");
            Logger.log("Folder of object " + objectNameId + " is " + folder, loglevelValue);
            Logger.log("RESULT: " + folder, loglevelValue);
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

		return ErrorCodes.OK;
	}


	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Gets the Folder of a specified object by name and type");
		parser.setExamples(
				"java -jar dm-tool.jar rm GetFolder --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" " +
						"\r\njava -jar dm-tool.jar rm GetFolder --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"1234\" "
				);

		objectTypeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the object.");
		objectNameArg = parser.addHelp(parser.addStringOption("n", "name", true), "The name or ID of the object.");
	}

}
