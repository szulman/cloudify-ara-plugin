package com.uc4.ara.feature.rm;

import java.util.List;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.Result;

public class DeleteObjectMultiple extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> objectTypeArg;
	private CmdLineParser.Option<String> objectPropertyNameArg;
	private CmdLineParser.Option<String> objectPropertyValueArg;
	private CmdLineParser.Option<String> failIfMissingArg;

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);

		String objectType = parser.getOptionValue(objectTypeArg);
		String objectPropertyName = parser.getOptionValue(objectPropertyNameArg);
		String objectPropertyValue = parser.getOptionValue(objectPropertyValueArg);

		String failIfMissingStr = parser.getOptionValue(failIfMissingArg);

		boolean failIfMissing = failIfMissingStr != null ? 	failIfMissingStr.toLowerCase().equals("yes") : false;

		List<String> ids = findObjectIds(objectType, objectPropertyName, objectPropertyValue);
		
		RmImportExportStructure structure = new RmImportExportStructure();
		
		for (String id : ids) {
		   RmEntity entity = structure.addEntity(objectType);
		   entity.addIdentityProperty("system_id", id);
		}

		Result result = service.delete(usernameValue, passwordValue, null, "XML", true, structure.createXml().toString());

		try {
			result = handleResult(result, service);

			String resultIds = "";
			for (String id : ids) {
				FeatureUtil.logMsg("RESULT-ID: " + id);
				if (resultIds.length() > 0)
					resultIds += ",";
				resultIds += id;
			}
			FeatureUtil.logMsg("RESULT-IDS: " + resultIds);
			
		} catch (Exception e) {
		    FeatureUtil.logMsg(e.getMessage(), MsgTypes.ERROR);
		    if (failIfMissing) {
                return ErrorCodes.ERROR;
		    }		    
		}
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Deletes a specified object by custom filter by property/value");
		parser.setExamples(
				"java -jar dm-tool.jar rm DeleteObjectMultiple --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"system_name\" -v \"Test Object\" -fm \"yes\"" +
						"\r\njava -jar dm-tool.jar rm DeleteObject --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"system_name\" --value \"Test Object\" --failifmissing \"yes\""
				);

		objectTypeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the object.");
		objectPropertyNameArg = parser.addHelp(parser.addStringOption("n", "name", true), "The property name of the object.");
		objectPropertyValueArg = parser.addHelp(parser.addStringOption("v", "value", true), "The property value of the object.");
		failIfMissingArg = parser.addHelp(parser.addStringOption("fm", "failifmissing", true), "Indicates that the function shall fail, if the property does not exist - valid values YES/NO (case insensitive).");
	}
}
