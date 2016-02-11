package com.uc4.ara.feature.rm;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.rm.rmstructure.RmEntity;
import com.uc4.ara.feature.rm.rmstructure.RmImportExportStructure;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class AssignToActivity extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> activityIdArg;
	private CmdLineParser.Option<String> releaseNameIdArg;
	private CmdLineParser.Option<String> packageIdArg;
	private CmdLineParser.Option<String> deploymentProfileNameIdArg;
	private CmdLineParser.Option<String> workflowNameIdArg;
	private CmdLineParser.Option<String> emailTemplateNameIdArg;
	
	private ImportExportServiceSoap service;
	
	@Override
	public int run(String[] args) throws Exception {
		super.run(args);
	    service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);		

		long activityId = Long.valueOf(parser.getOptionValue(activityIdArg));
		String releaseNameId = parser.getOptionValue(releaseNameIdArg);
		String packageId = parser.getOptionValue(packageIdArg);
		String deploymentProfileNameId = parser.getOptionValue(deploymentProfileNameIdArg);
		String workflowNameId = parser.getOptionValue(workflowNameIdArg);
		String emailTemplateNameId = parser.getOptionValue(emailTemplateNameIdArg);

		if (releaseNameId != null) {
			String id = getIdFromNameOrId(service, "Release", releaseNameId);
			if (id == null) {
				id = releaseNameId;
			} else {
				FeatureUtil.logMsg("Object '" + releaseNameId
						+ "' found with id " + id);
			}

			try { 
				Long.valueOf(id);
			} catch (NumberFormatException e) {
				FeatureUtil
						.logMsg("Object with name '" + id + "' not found");
				return ErrorCodes.PARAMSMISMATCH;
			}

			String oldValue = searchOneProperty(service, "Activity", "system_id", ""
					+ activityId, "system_release.system_id");
			FeatureUtil.logMsg("RESULT-OLD-RELEASE: " + oldValue);

			RmImportExportStructure structure = new RmImportExportStructure();
			RmEntity entity = structure.addEntity("Activity");
			entity.addIdentityProperty("system_id", "" + activityId);
			entity.addProperty("system_release.system_id", id);

			Result result = importFunc(service, "Activity", structure.createXml().toString());

			FeatureUtil.logMsg("Release set and Server reported '"
					+ result.getDescription() + "'");
		}

		if (packageId != null) {

			String oldValue = searchOneProperty(service, "Activity", "system_id", ""
					+ activityId, "system_deployment_package.system_id");
			FeatureUtil.logMsg("RESULT-OLD-DEPLOYMENTPACKAGE: " + oldValue);

			RmImportExportStructure structure = new RmImportExportStructure();
			RmEntity entity = structure.addEntity("Activity");
			entity.addIdentityProperty("system_id", "" + activityId);
			entity.addProperty("system_deployment_package.system_id", packageId);

			Result result = importFunc(service, "Activity", structure.createXml().toString());

			FeatureUtil.logMsg("Package set and Server reported '"
					+ result.getDescription() + "'");
		}

		if (deploymentProfileNameId != null) {
			String id = getIdFromNameOrId(service, "DeploymentProfile",
					deploymentProfileNameId);
			if (id == null) {
				id = deploymentProfileNameId;
			} else {
				FeatureUtil.logMsg("Object '" + deploymentProfileNameId
						+ "' found with id " + id);
			}

			try { // would be better to check with apache commons validator but
					// lib is not included
				Long.valueOf(id);
			} catch (NumberFormatException e) {
				FeatureUtil
						.logMsg("Object with name '" + id + "' not found");
				return ErrorCodes.PARAMSMISMATCH;
			}

			String oldValue = searchOneProperty(service, "Activity", "system_id", ""
					+ activityId, "system_deployment_profile.system_id");
			FeatureUtil.logMsg("RESULT-OLD-DEPLOYMENTPROFILE: " + oldValue);

			RmImportExportStructure structure = new RmImportExportStructure();
			RmEntity entity = structure.addEntity("Activity");
			entity.addIdentityProperty("system_id", "" + activityId);
			entity.addProperty("system_deployment_profile.system_id", id);

			Result result = importFunc(service, "Activity", structure.createXml().toString());

			FeatureUtil.logMsg("DeploymentProfile set and Server reported '"
					+ result.getDescription() + "'");
		}

		if (workflowNameId != null) {
			String id = getIdFromNameOrId(service, "Workflow", workflowNameId);
			if (id == null) {
				id = workflowNameId;
			} else {
				FeatureUtil.logMsg("Object '" + workflowNameId
						+ "' found with id " + id);
			}

			try { // would be better to check with apache commons validator but
					// lib is not included
				Long.valueOf(id);
			} catch (NumberFormatException e) {
				FeatureUtil
						.logMsg("Object with name '" + id + "' not found");
				return ErrorCodes.PARAMSMISMATCH;
			}

			String oldValue = searchOneProperty(service, "Activity", "system_id", ""
					+ activityId, "system_workflow.system_id");
			FeatureUtil.logMsg("RESULT-OLD-WORKFLOW: " + oldValue);

			RmImportExportStructure structure = new RmImportExportStructure();
			RmEntity entity = structure.addEntity("Activity");
			entity.addIdentityProperty("system_id", "" + activityId);
			entity.addProperty("system_workflow.system_id", id);

			Result result = importFunc(service, "Activity", structure.createXml().toString());

			FeatureUtil.logMsg("Workflow set and Server reported '"
					+ result.getDescription() + "'");
		}

		if (emailTemplateNameId != null) {
			String id = getIdFromNameOrId(service, "EmailTemplate", emailTemplateNameId);
			if (id == null) {
				id = emailTemplateNameId;
			} else {
				FeatureUtil.logMsg("Object '" + emailTemplateNameId
						+ "' found with id " + id);
			}

			try { // would be better to check with apache commons validator but
					// lib is not included
				Long.valueOf(id);
			} catch (NumberFormatException e) {
				FeatureUtil
						.logMsg("Object with name '" + id + "' not found");
				return ErrorCodes.PARAMSMISMATCH;
			}

			String oldValue = searchOneProperty(service, "Activity", "system_id", ""
					+ activityId, "system_email_template.system_id");
			FeatureUtil.logMsg("RESULT-OLD-EMAILTEMPLATE: " + oldValue);

			RmImportExportStructure structure = new RmImportExportStructure();
			RmEntity entity = structure.addEntity("Activity");
			entity.addIdentityProperty("system_id", "" + activityId);
			entity.addProperty("system_email_template.system_id", id);

			Result result = importFunc(service, "Activity", structure.createXml().toString());

			FeatureUtil.logMsg("EmailTemplate set and Server reported '"
					+ result.getDescription() + "'");
		}

		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();
		
		parser.setDescription("Assigns various optional entities to an Activity");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm AssignToActivity --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -a \"123\" -r \"test release\" -pk \"test package\" " +
	    				"\r\njava -jar dm-tool.jar rm AssignToActivity -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -a 1234 -dp 12345" +
	    				"\r\njava -jar dm-tool.jar rm AssignToActivity -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\"  -a 1234 -wf 12 -et 456 " +
	    				"\r\njava -jar dm-tool.jar rm AssignToActivity --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --activity 1234 --release \"test release\" --package \"test package\" --deploymentprofile 12345 --workflow 12 --emailtemplate 456"
	    );
	    
		activityIdArg = parser.addHelp(parser.addStringOption("a", "activity", true), "The ID of the Activity to assign to.");
		releaseNameIdArg = parser.addHelp(parser.addStringOption("r", "release", false), "The name or ID of the Release to assign to the Activity.");
		packageIdArg = parser.addHelp(parser.addStringOption("pk", "package", false), "The name or ID of the Package to assign to the Activity.");
		deploymentProfileNameIdArg = parser.addHelp(parser.addStringOption("dp", "deploymentprofile", false), "The name or ID of the DeploymentProfile to assign to the Activity.");
		workflowNameIdArg = parser.addHelp(parser.addStringOption("wf", "workflow", false), "The name or ID of the Workflow to assign to the Activity.");
		emailTemplateNameIdArg = parser.addHelp(parser.addStringOption("et", "emailtemplage", false), "The name or ID of the EmailTemplate to assign to the Activity.");
	}

}
