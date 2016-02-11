package com.uc4.ara.feature.rm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.uc4.ara.feature.AbstractFeature;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.deploymentservice.DeploymentServiceProxy;
import com.uc4.deploymentservice.ObjectsCreationResult;

public class CreateActivityFromTemplate extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> templateNameArg;
	private CmdLineParser.Option<String> ownerArg;
	private CmdLineParser.Option<String> folderArg;
	private CmdLineParser.Option<String> startStringArg;
	
	@Override
	public int run(String[] args) throws Exception {
		super.run(args);

		String templateName = parser.getOptionValue(templateNameArg);
		String owner = parser.getOptionValue(ownerArg);
		String folder = parser.getOptionValue(folderArg);
		String startString = parser.getOptionValue(startStringArg);

		String templateActivity = "";

		XMLGregorianCalendar start = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar();

		if (startString != null) {
			GregorianCalendar calendar = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat(AbstractFeature.AUTOMATION_ENGINE_DATE_FORMAT);
			calendar.setTime(sdf.parse(startString));
			start = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					BigInteger.valueOf(calendar.get(Calendar.YEAR)),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND), BigDecimal.valueOf(0), 0);
		} else {
			GregorianCalendar calendar = new GregorianCalendar();
			start = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					BigInteger.valueOf(calendar.get(Calendar.YEAR)),
					calendar.get(Calendar.MONTH) + 1,
					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.HOUR_OF_DAY),
					calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND), BigDecimal.valueOf(0), 0);
		}

		DeploymentServiceProxy deploymentService = DeploymentServiceFactory
				.getDeploymentServiceFromURLWithoutSSL(urlValue);

		ObjectsCreationResult result = deploymentService
				.createActivitiesFromTemplate(usernameValue, passwordValue,
						templateName, templateActivity, start.toGregorianCalendar(), owner, folder.toUpperCase());

		if (!result.isIsSuccess()) {
			FeatureUtil.logMsg("error from Releasemanager: '"
					+ result.getMessage() + "'");
			return ErrorCodes.ERROR;
		}

		FeatureUtil.logMsg("Activity from Template '" + templateName
				+ "' created");

		String ids = "";
		long[] objectIds = result.getObjectsId();
		for (Long id : objectIds) {
			FeatureUtil.logMsg("RESULT-ID: " + id);
			if (ids.length() > 0)
				ids += ",";
			ids += id;
		}
		FeatureUtil.logMsg("RESULT-IDS: " + ids);
		FeatureUtil.logMsg("RESULT-COUNT: " + objectIds.length);

		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();
		
		parser.setDescription("Creates an Activity from an ActivityTemplate setting the Owner and Folder and optionally the start date");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm CreateActivityFromTemplate --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -tn \"My Activity Template\" -o \"Test\" -f \"ALL\" -s \"2012-01-01 01:01:01\"" +
	    				"\r\njava -jar dm-tool.jar rm CreateActivityFromTemplate -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -tn \"My Activity Template\" -o \"Test\" -f \"ALL\"" +
	    				"\r\njava -jar dm-tool.jar rm CreateActivityFromTemplate --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --templatename \"My Activity Template\" --owner \"Test\" --folder \"ALL\" --startstring \"2012-01-01 01:01:01\""
	    );
	    
		templateNameArg = parser.addHelp(parser.addStringOption("tn", "templatename", true), "The name of the ActivityTemplate from which the Activity should be created.");
		ownerArg = parser.addHelp(parser.addStringOption("o", "owner", true), "The name of the Owner of the created Activity.");
		folderArg = parser.addHelp(parser.addStringOption("f", "folder", true), "The name of the Folder of the created Activity.");
		startStringArg = parser.addHelp(parser.addStringOption("s", "startstring", false), "The start date of the created Activity - default is now - Format: yyyy-MM-dd HH:mm:ss");
	}
}