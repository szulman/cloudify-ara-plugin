package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ImportExportServiceSoap;

public class GetOwner extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> objectTypeArg;
	private CmdLineParser.Option<String> objectNameArg;

	private ImportExportServiceSoap service;

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);
		service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);

		String objectType = parser.getOptionValue(objectTypeArg);
		String objectNameId = parser.getOptionValue(objectNameArg);

		String id = getIdFromNameOrId(service, objectType, objectNameId);
		if (id == null) {
			id = objectNameId;
		} else {
			Logger.log("Object '" + objectNameId + "' found with id "
					+ id, loglevelValue);
		}

		try { // would be better to check with apache commons validator but
			// lib is not included
			Long.valueOf(id);
		} catch (NumberFormatException e) {
			Logger.log("Object with name '" + id + "' not found", loglevelValue);
			return ErrorCodes.PARAMSMISMATCH;
		}

		String owner = searchOneProperty(service, objectType, "system_id", id,
				"system_owner.system_name");

		Logger.log("Owner of object " + objectNameId + " is " + owner, loglevelValue);
		Logger.log("RESULT: " + owner, loglevelValue);
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Gets the Owner of a specified object by name and type");
		parser.setExamples(
				"java -jar dm-tool.jar rm GetOwner --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" " +
						"\r\njava -jar dm-tool.jar rm GetOwner --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"1234\" "
				);

		objectTypeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the object.");
		objectNameArg = parser.addHelp(parser.addStringOption("n", "name", true), "The name or ID of the object.");
	}

}
