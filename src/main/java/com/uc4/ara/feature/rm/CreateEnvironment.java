package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.feature.utils.ResultHandler;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ErrorCode;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class CreateEnvironment extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> environmentName;
	private CmdLineParser.Option<String> owner;
	private CmdLineParser.Option<String> folder;
	private CmdLineParser.Option<String> type;
	private CmdLineParser.Option<Boolean> failIfExists;

	private ImportExportServiceSoap service;

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);

		String environmentNameValue = parser.getOptionValue(environmentName);
		String ownerValue = parser.getOptionValue(owner);
		if ((ownerValue == null) || ownerValue.equals("")) {
			String[] tempUserArr = usernameValue.split("/");
			if (tempUserArr.length == 3) {
				ownerValue = usernameValue;
			} else {
				int i = usernameValue.indexOf("/");
				ownerValue = usernameValue.substring(i + 1);
			}

		}
		String folderValue = parser.getOptionValue(folder);
		String typeValue = parser.getOptionValue(type);
		Boolean failIfExistsValue = parser.getOptionValue(failIfExists) == null ? false :parser.getOptionValue(failIfExists);

		service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);

		RmImportExportStructure structure = new RmImportExportStructure();
		RmEntity entity = structure.addEntity("Environment");
		entity.setCustomType(typeValue);
		entity.addProperty("system_name", environmentNameValue);
		entity.addProperty("system_owner.system_name", ownerValue);
		entity.addProperty("system_folder.system_name", folderValue.toUpperCase());
		entity.addProperty("system_description", "created via DMTool");

		Result result = null;
		try
		{
			result = service.importFunc(usernameValue, passwordValue, "Environment", "XML", true, structure.createXml().toString());

			result = ResultHandler.handleImportExportResult(result, service);

			Logger.log("Environment-ID: " + result.getStatus(), this.loglevelValue);

			return ErrorCodes.OK;
		}
		catch(Exception ex) {
			if(result != null) {
				if(result.getErrors().getError().size() > 0) {
					for(com.uc4.importexportservice.Error error : result.getErrors().getError()) {
						if(error.getErrorCode() == ErrorCode.OBJECT_IDENTITY_ALREADY_EXISTS && !failIfExistsValue) {
							Logger.log("Environment already exists", this.loglevelValue);

							Logger.log("Environment-ID: " + getIdFromMainType(service, "Environment", environmentNameValue, typeValue), loglevelValue);

							return ErrorCodes.OK;
						}
					}
				}
			}

			throw ex;
		}
	}


	@Override
	public void initialize() {
		super.initialize();
		parser.setDescription("Creates an environment with a specified name on a given ReleaseManager Instance.");
		parser.setExamples(
				"java -jar dm-tool.jar rm CreateEnvironment --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test environment\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\" --failifexists" +
						"\r\njava -jar dm-tool.jar rm CreateEnvironment -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test environment\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -fe" +
						"\r\njava -jar dm-tool.jar rm CreateEnvironment -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test environment\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -fe" +
						"\r\njava -jar dm-tool.jar rm CreateEnvironment --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test environment\" --owner \"1/AC/UC\" --folder \"TESTFOLDER\" --type \"TESTTYPE\" --failifexists"
				);



		environmentName = parser.addHelp(parser.addStringOption("n", "name", true), "The name of the environment to create.");
		owner = parser.addHelp(parser.addStringOption("o", "owner", false), "The owner of the environment to create - if omitted the connecting username will be taken.");
		folder = parser.addHelp(parser.addStringOption("f", "folder", true), "The folder of the environment to create.");
		type = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the environment to create.");
		failIfExists = parser.addHelp(parser.addBooleanOption("fe", "failifexists", false), "If specified, the function will fail if the environment already exists.");
	}
}
