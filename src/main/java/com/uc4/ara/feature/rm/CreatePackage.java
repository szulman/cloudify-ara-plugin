package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class CreatePackage extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> nameArg;
	private CmdLineParser.Option<String> customTypeArg;
	private CmdLineParser.Option<String> ownerArg;
	private CmdLineParser.Option<String> folderArg;
	
	private ImportExportServiceSoap service;
	
	@Override
	public int run(String[] args) throws Exception {
		super.run(args);
		service = ImportExportServiceFactory.getImportExportServiceFromUrl(urlValue);
		
		String customType = parser.getOptionValue(customTypeArg);
		String name = parser.getOptionValue(nameArg);
		String owner = parser.getOptionValue(ownerArg);
		String folder = parser.getOptionValue(folderArg);

		String id = getIdFromNameOrId(service, "Package", name);
		if (id != null) {
			FeatureUtil.logMsg("Package '" + name + "' already exists with id "
					+ id);
			return ErrorCodes.ERROR;
		}

		RmImportExportStructure structure = new RmImportExportStructure();
		RmEntity entity = structure.addEntity("Package");
		if (customType.length() > 0)
			entity.setCustomType(customType);
		entity.addProperty("system_name", name);
		entity.addProperty("system_owner.system_name", owner);
		entity.addProperty("system_folder.system_name", folder.toUpperCase());
		entity.addProperty("system_description", "created via DMTool");

		// System.out.println(structure.createXml().toString());

		Result result = importFunc(service, "Package", structure.createXml().toString());

		FeatureUtil.logMsg("Server reported '" + result.getDescription() + "'");

		id = "" + result.getStatus();

		FeatureUtil.logMsg("Package '" + name + "' created");
		FeatureUtil.logMsg("RESULT-ID: " + id);

		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();
		
		parser.setDescription("Creates a package with a specified name on a given ReleaseManager Instance.");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm CreateDeployPackage --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test package\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\"" +
	    				"\r\njava -jar dm-tool.jar rm CreateDeployPackage -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test package\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\"" +
	    				"\r\njava -jar dm-tool.jar rm CreateDeployPackage -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n \"test package\" -f \"TESTFOLDER\" -t \"TESTTYPE\"" +
	    				"\r\njava -jar dm-tool.jar rm CreateDeployPackage --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test package\" --owner \"1/AC/UC\" --folder \"TESTFOLDER\" --type \"TESTTYPE\""
	    );
	    
	    nameArg = parser.addHelp(parser.addStringOption("n", "name", true), "The name of the package to create.");
		ownerArg = parser.addHelp(parser.addStringOption("o", "owner", true), "The owner of the package to create.");
		folderArg = parser.addHelp(parser.addStringOption("f", "folder", true), "The folder of the package to create.");
		customTypeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the package to create.");
	}
}
