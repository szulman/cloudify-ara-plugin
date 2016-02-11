package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class RmFullExport extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> mainTypeArg;
	private CmdLineParser.Option<String> customTypeArg;
	private CmdLineParser.Option<Integer> beginArg;
	private CmdLineParser.Option<Integer> countArg;
	private CmdLineParser.Option<String> formatArg;
	private CmdLineParser.Option<String> startDateArg;
	private CmdLineParser.Option<String> endDateArg;
	private CmdLineParser.Option<String> xmlFilenameArg;
	private CmdLineParser.Option<String> conditionsArg;
	private CmdLineParser.Option<String> propertiesArg;



	@Override
	public int run(String[] args) throws Exception {

		super.run(args);



		String mainType = parser.getOptionValue(mainTypeArg);
		String customType = parser.getOptionValue(customTypeArg);
		Integer begin = parser.getOptionValue(beginArg);
		Integer count = parser.getOptionValue(countArg);
		String format = parser.getOptionValue(formatArg);
		String startDate = parser.getOptionValue(startDateArg);
		String endDate = parser.getOptionValue(endDateArg);
		String xmlFilename = parser.getOptionValue(xmlFilenameArg);
		String properties = parser.getOptionValue(propertiesArg);
		String conditions = parser.getOptionValue(conditionsArg);

		ArrayOfString propertiesArray = new ArrayOfString();
		ArrayOfString conditionsArray = new ArrayOfString();

		if (properties != null)
			for (String property : properties.split(",") )
			{
				propertiesArray.getString().add(property.trim());
			}

		if (conditions != null)
			for (String condition : conditions.split(",") )
			{
				conditionsArray.getString().add(condition.trim());
			}

		if (begin == null)
			begin = 0;
		if (count == null)
			count = 1000;
		if (customType == null)
			customType = "";
		if (format == null)
			format = "XML";

		Result result = service.export(usernameValue, passwordValue,
				mainType, customType, begin, count, format, propertiesArray,
				startDate, endDate, conditionsArray);

		result = handleResult(result, service);

		if (result.getData() == null)
		{
			Logger.log("Could not get result from the webservice.", loglevelValue);
			return ErrorCodes.ERROR;
		}

		FeatureUtil.writeFile(result.getData(), xmlFilename);

		Logger.log("File '" + xmlFilename + "' is successfully exported", loglevelValue);

		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();

		parser.setDescription("Exports an object into an xml file.");
		parser.setExamples(
				"java -jar dm-tool.jar rm RmFullExport --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -t \"Test Type\" -n \"test object\" -f \"C:\\temp\\result.xml\"");

		mainTypeArg = parser.addHelp(parser.addStringOption("mt", "maintype", true), "The main type of the object.");
		xmlFilenameArg = parser.addHelp(parser.addStringOption("fi", "file", true), "The name of the xml file to export to.");

		customTypeArg = parser.addHelp(parser.addStringOption("ct", "customtype", false), "The custom type of the object.");

		beginArg = parser.addHelp(parser.addIntegerOption("begin", false), "The first element which gets returned (index 0).");
		countArg = parser.addHelp(parser.addIntegerOption("count", false), "The number of elements to return. Can be within 0 and 1000");

		startDateArg = parser.addHelp(parser.addStringOption("sD", "startDate", false), "Export all data which was created or changed since this date. If empty all data will be exported.");
		endDateArg = parser.addHelp(parser.addStringOption("eD", "endDate", false), "Export all data which were created or changed until this date. If empty all data will be exported");

		propertiesArg = parser.addHelp(parser.addStringOption("prop", "properties", false), "Properties to export. Separated by commas. Ex: system_name, system_application.system_id.");
		conditionsArg = parser.addHelp(parser.addStringOption("where", false), "Conditions used to filter the data. Separated by commas. Ex: system_name eq 'TestApp',system_application.system_id eq '1234'.");

		formatArg = parser.addHelp(parser.addStringOption("format", false), "Format to export to. Can be XML or CSV");

	}
}