package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class SetOwner extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> objectTypeArg;
	private CmdLineParser.Option<String> objectNameArg;
	private CmdLineParser.Option<String> ownerArg;

	private ImportExportServiceSoap service;
	@Override
	public int run(String[] args) throws Exception {

		super.run(args);
		service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);

		String objectType = parser.getOptionValue(objectTypeArg);
		String objectNameId = parser.getOptionValue(objectNameArg);
		String owner = parser.getOptionValue(ownerArg);

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

		RmImportExportStructure structure = new RmImportExportStructure();
		RmEntity entity = structure.addEntity(objectType);
		entity.addIdentityProperty("system_id", id);
		entity.addProperty("system_owner.system_name", owner);

		// System.out.println(structure.createXml().toString());

		Result result = importFunc(service, objectType, structure.createXml().toString());

		Logger.log("Server reported '" + result.getDescription() + "'", loglevelValue);

		Logger.log("Owner of object " + objectNameId + " set to "
				+ owner, loglevelValue);
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Sets the Owner of a specified object by name and type");
		parser.setExamples(
				"java -jar dm-tool.jar rm SetOwner --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -o \"ALL\"" +
						"\r\njava -jar dm-tool.jar rm SetOwner --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Test Type\" --name \"1234\" --owner \"ALL\" "
				);

		objectTypeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the object.");
		objectNameArg = parser.addHelp(parser.addStringOption("n", "name", true), "The name or ID of the object.");
		ownerArg = parser.addHelp(parser.addStringOption("o", "owner", true), "The name of the owner.");
	}
}
