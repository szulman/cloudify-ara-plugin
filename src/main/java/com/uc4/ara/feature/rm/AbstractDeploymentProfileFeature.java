package com.uc4.ara.feature.rm;

import com.uc4.ara.common.exception.DataNotFoundException;
import com.uc4.ara.common.exception.DataNotUniqueException;
import com.uc4.ara.feature.AbstractPublicFeature;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.structure.Entity;
import com.uc4.importexportservice.structure.MainType;
import com.uc4.importexportservice.structure.Sync;
import com.uc4.importexportservice.util.ImportExportServiceUtil;

public abstract class AbstractDeploymentProfileFeature extends AbstractPublicFeature {
	
	class DeploymentTarget {

		private long systemId;
		private String systemName;
		private String customType;
		private boolean isArchive;

		public long getSystemId() {
			return systemId;
		}

		public void setSystemId(long systemId) {
			this.systemId = systemId;
		}

		public String getSystemName() {
			return systemName;
		}

		public void setSystemName(String systemName) {
			this.systemName = systemName;
		}

		public String getCustomType() {
			return customType;
		}

		public void setCustomType(String customType) {
			this.customType = customType;
		}

		public boolean isArchive() {
			return isArchive;
		}

		public void setArchive(boolean isArchive) {
			this.isArchive = isArchive;
		}
	}

	class Component {

		private long systemId;
		private String systemName;
		private String customType;

		public long getSystemId() {
			return systemId;
		}

		public void setSystemId(long systemId) {
			this.systemId = systemId;
		}

		public String getSystemName() {
			return systemName;
		}

		public void setSystemName(String systemName) {
			this.systemName = systemName;
		}

		public String getCustomType() {
			return customType;
		}

		public void setCustomType(String customType) {
			this.customType = customType;
		}

	}

	private CmdLineParser.Option<String> url;
	private CmdLineParser.Option<String> username;
	private CmdLineParser.Option<String> password;
	private CmdLineParser.Option<String> profileArg;
	private CmdLineParser.Option<String> appArg;
	
	protected String profileValue;
	protected String appValue;
	protected String environmentValue;	

	protected ImportExportServiceUtil wsUtil = null;
	
	@Override
	public void initialize() {
		super.initialize();
		url = parser.addHelp(parser.addStringOption("u", "url", true),
				"The url to the ReleaseManager instance that is used.");

		username = parser
				.addHelp(parser.addStringOption("un", "username", false),
						"The username that is used to authenticate against the ReleaseManager.");

		password = parser
				.addHelp(parser.addPasswordOption("p", "password", false),
						"The password/token to authenticate against the ReleaseManager.");

		profileArg = parser.addHelp(
				parser.addStringOption("pf", "profileName", true),
				"The ID or name of the profile.");

		appArg = parser
				.addHelp(
						parser.addStringOption("an", "applicationName", false),
						"The name or ID of the application, required if DeploymentProfile is not an ID.");

	}
	
	@Override
	public int run(String[] args) throws Exception {

		super.run(args);

		String urlValue = parser.getOptionValue(url);
		String usernameValue = parser.getOptionValue(username);
		String passwordValue = parser.getOptionValue(password);

		profileValue = parser.getOptionValue(profileArg);
		appValue = parser.getOptionValue(appArg);		

		ImportExportServiceSoap service = ImportExportServiceFactory
				.getImportExportServiceFromUrlWithoutSSL(urlValue);

		wsUtil = new ImportExportServiceUtil(service, usernameValue,
				passwordValue);

		// validate profile name and application name
		validateProfileAndApplication();
		
		return ErrorCodes.OK;
	}
	
	private void validateProfileAndApplication() throws Exception {
		String tmpProfile = "";
		try {
			tmpProfile = wsUtil.getSystemIdFromNameOrId(
					MainType.DEPLOYMENT_PROFILE, profileValue);
		} catch (DataNotUniqueException e) {
		}

		ArrayOfString properties = new ArrayOfString();
		ArrayOfString conditions = new ArrayOfString();
		properties.getString().add("system_id");
		properties.getString().add("system_application.system_id");
		properties.getString().add("system_environment.system_id");

		if ( !tmpProfile.equalsIgnoreCase(profileValue) ) {

			if ((appValue == null) || (appValue.isEmpty())) {
				throw new IllegalArgumentException(
						"The application name must be mandatory because profile isn't an Id");

			}

			appValue = wsUtil.getSystemIdFromNameOrId(MainType.APPLICATION,
					appValue);

			// find profile id by profile name and application id
			conditions.getString().add("system_name eq '" + profileValue + "'");
			conditions.getString().add(
					"system_application.system_id eq '" + appValue + "'");

		} else {
			conditions.getString().add("system_id eq '" + profileValue + "'");
		}

		Sync sync = wsUtil.exportEntities(MainType.DEPLOYMENT_PROFILE,
				properties, conditions);
		if (sync.getEntity().size() == 0) {
			throw new DataNotFoundException(
					"The key[profile/application] doesn't exist in table DeploymentProfile");
		}

		Entity e = sync.getEntity().get(0);
		profileValue = e.getProperty().get(0).getValue();
		appValue = e.getProperty().get(1).getValue();
		environmentValue = e.getProperty().get(2).getValue();

	}
	
	protected Sync backupDeploymentProfileTarget() throws Exception {
		Sync sync = null;
		ArrayOfString properties = new ArrayOfString();
		ArrayOfString conditions = new ArrayOfString();

		properties.getString().add("system_deployment_profile.system_id");
		properties.getString().add("system_application.system_id");
		properties.getString().add("system_component.system_id");
		properties.getString().add("system_deployment_target.system_id");

		conditions.getString()
				.add("system_deployment_profile.system_id eq '" + profileValue
						+ "'");

		sync = wsUtil.exportEntities(MainType.DEPLOYMENT_PROFILE_TARGET,
				properties, conditions);

		return sync;
	}

}
