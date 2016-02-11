package com.uc4.ara.feature.rm;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.uc4.ara.feature.AbstractFeature;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class CreateDeployActivity extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> typeArg;
	private CmdLineParser.Option<String> nameArg;
	private CmdLineParser.Option<String> ownerArg;
	private CmdLineParser.Option<String> folderArg;
	private CmdLineParser.Option<String> startStringArg;
	private CmdLineParser.Option<String> durationStringArg;
	
	private ImportExportServiceSoap service;
	
	@Override
	public int run(String[] args) throws Exception {
		super.run(args);

		service = ImportExportServiceFactory.getImportExportServiceFromUrl(urlValue);
		
		String type = parser.getOptionValue(typeArg);
		String name = parser.getOptionValue(nameArg);
		String owner = parser.getOptionValue(ownerArg);
		String folder = parser.getOptionValue(folderArg);
		String startString = parser.getOptionValue(startStringArg);
		String durationString = parser.getOptionValue(durationStringArg);

		if (startString != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(
					AbstractFeature.AUTOMATION_ENGINE_DATE_FORMAT);
			startString = sdf.format(new Date());
		}

		if (!durationString.startsWith("P"))
			// prefix with (P)eriod - (T)ime. See iso8601
			durationString = "P" + durationString;
		durationString = durationString.replace("d", "D");
		if ((durationString.contains("D") && durationString.contains("h"))
				|| (durationString.contains("D") && durationString
						.contains("m"))) {
			int idx = durationString.indexOf("D");
			durationString = durationString.substring(0, idx + 1) + "T"
					+ durationString.substring(idx + 1);
		} else if (durationString.contains("m")) {
			durationString = durationString.substring(0, 1) + "T"
					+ durationString.substring(1);
		}
		durationString = durationString.replace("h", "H");
		durationString = durationString.replace("m", "M");

		RmImportExportStructure structure = new RmImportExportStructure();
		RmEntity entity = structure.addEntity("Activity");
		entity.setCustomType(type);
		entity.addProperty("system_name", name);
		entity.addProperty("system_owner.system_name", owner);
		entity.addProperty("system_folder.system_name", folder.toUpperCase());
		entity.addProperty("system_description", "created via DMTool");
		entity.addProperty("system_planned_from", startString);
		entity.addProperty("system_planned_duration", durationString);
		// System.out.println(structure.createXml().toString());

		Result result = importFunc(service, "Activity", structure.createXml().toString());

		FeatureUtil.logMsg("Server reported '" + result.getDescription() + "'");

		String id = "" + result.getStatus();

		FeatureUtil.logMsg("Deploy activity '" + name + "' created");

		FeatureUtil.logMsg("RESULT-ID: " + id);

		return ErrorCodes.OK;
	}


	@Override
	public void initialize() {
		super.initialize();
		
		parser.setDescription("Creates an Activity setting various attributes.");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm CreateDeployActivity --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Deployment\" -n \"Test Activity\" -o \"Test\" -f \"ALL\" -s \"2012-01-01 01:01:01\" -d \"2d\"" +
	    				"\r\njava -jar dm-tool.jar rm CreateDeployActivity -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -t \"Deployment\" -n \"Test Activity\" -o \"Test\" -f \"ALL\" -d \"4h\"" +
	    				"\r\njava -jar dm-tool.jar rm CreateDeployActivity --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --type \"Deployment\" --name \"Test Activity\" --owner \"Test\" --folder \"ALL\" --startstring \"2012-01-01 01:01:01\" --duration \"P4dT2h\""
	    );
	    
	    typeArg = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the Activity to create.");
	    nameArg = parser.addHelp(parser.addStringOption("n", "name", true), "The name of the Activity to create.");
		ownerArg = parser.addHelp(parser.addStringOption("o", "owner", true), "The name of the Owner of the Activity to create.");
		folderArg = parser.addHelp(parser.addStringOption("f", "folder", true), "The name of the Folder of the Activity to create.");
		startStringArg = parser.addHelp(parser.addStringOption("s", "startstring", false), "The start date of the created Activity - default is now - Format: yyyy-MM-dd HH:mm:ss");
		durationStringArg = parser.addHelp(parser.addStringOption("d", "duration", true), "The duration of the created Activity - i.e.: 2d");
	}

}
