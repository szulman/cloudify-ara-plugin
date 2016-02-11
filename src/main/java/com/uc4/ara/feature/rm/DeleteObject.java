package com.uc4.ara.feature.rm;

import com.uc4.ara.common.exception.DataNotFoundException;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ErrorCode;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class DeleteObject extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> objectTypeArg;
	private CmdLineParser.Option<String> objectNameArg;
	private CmdLineParser.Option<String> failIfMissingArg;

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);


		String objectType = parser.getOptionValue(objectTypeArg);
		String objectNameId = parser.getOptionValue(objectNameArg);

		String failIfMissingStr = parser.getOptionValue(failIfMissingArg);

		boolean failIfMissing = failIfMissingStr != null ? 	failIfMissingStr.toLowerCase().equals("yes") : false;

        try {
            objectNameId = getIdFromNameOrIdv2(objectType, objectNameId);
        } catch(DataNotFoundException e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            if (failIfMissing) {
                return ErrorCodes.ERROR;
            } else {
            	return ErrorCodes.OK;
            }        
        } catch(Exception e) {
            FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
            return ErrorCodes.ERROR;
        }

		RmImportExportStructure structure = new RmImportExportStructure();
		RmEntity entity = structure.addEntity(objectType);
		entity.addIdentityProperty("system_id", objectNameId);

		Result result = service.delete(usernameValue, passwordValue,
				null, "XML", true, structure.createXml().toString());

		try {
			result = handleResult(result, service);

			Logger.log("Object '" + objectNameId + "' deleted", loglevelValue);
			Logger.log("RESULT-ID: " + objectNameId, loglevelValue);
		} catch (Exception e) {
		    FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
		    return ErrorCodes.ERROR;
		}
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Deletes a specified object by name and type");
		parser.setExamples(
				"java -jar dm-tool.jar rm DeleteObject --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -fm \"yes\"" +
						"\r\njava -jar dm-tool.jar rm DeleteObject --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"1234\" --failifmissing \"yes\""

				);

		objectTypeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the object.");
		objectNameArg = parser.addHelp(parser.addStringOption("n", "name", true), "The name or ID of the object.");
		failIfMissingArg = parser.addHelp(parser.addStringOption("fm", "failifmissing", true), "Indicates that the function shall fail, if the property does not exist - valid values YES/NO (case insensitive).");
	}
}
