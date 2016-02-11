package com.uc4.ara.feature.rm;

import java.util.ArrayList;
import java.util.List;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.structure.Entity;
import com.uc4.importexportservice.structure.MainType;
import com.uc4.importexportservice.structure.Property;
import com.uc4.importexportservice.structure.Sync;

public class ChangeDeploymentProfileTarget extends
		AbstractDeploymentProfileFeature {

	private CmdLineParser.Option<String> deploymentTargetArg;
	private CmdLineParser.Option<String> componentArg;
	private CmdLineParser.Option<Boolean> deleteArg;

	private List<Component> cmpnts;
	private List<DeploymentTarget> deploymentTargets;

	private Property createProperty(String name, String value) {
		Property p = new Property();
		p.setName(name);
		p.setValue(value);
		p.setIsIdentity(true);
		return p;
	}

	private Sync buildEntitiesData() throws Exception {

		Sync sync = new Sync();

		for (Component cmpnt : cmpnts) {

			for (DeploymentTarget deploymentTarget : deploymentTargets) {

				if (!deploymentTarget.isArchive()) {

					if (cmpnt.getCustomType().equalsIgnoreCase(
							deploymentTarget.getCustomType())) {

						Entity entity = new Entity();
						entity.setMainType(MainType.DEPLOYMENT_PROFILE_TARGET);
						sync.getEntity().add(entity);

						entity.getProperty().add(
								createProperty(
										"system_deployment_profile.system_id",
										profileValue));

						entity.getProperty().add(
								createProperty("system_application.system_id",
										appValue));

						entity.getProperty().add(
								createProperty("system_component.system_id",
										Long.toString(cmpnt.getSystemId())));
						entity.getProperty().add(
								createProperty(
										"system_deployment_target.system_id",
										Long.toString(deploymentTarget
												.getSystemId())));

					}
				}
			}
		}
		return sync;

	}

	private List<DeploymentTarget> getDeploymentProfileTargets()
			throws Exception {

		List<DeploymentTarget> deploymentTarget = new ArrayList<DeploymentTarget>();

		ArrayOfString conditions = new ArrayOfString();
		ArrayOfString properties = new ArrayOfString();

		properties.getString().add("system_deployment_target.system_id");
		conditions.getString().add(
				"system_environment.system_id eq '" + environmentValue + "'");

		Sync sync = wsUtil.exportEntities(
				MainType.ENVIRONMENT_DEPLOYMENT_TARGET_RELATION, properties,
				conditions);

		List<String> target = new ArrayList<String>();
		for (Entity e : sync.getEntity()) {
			target.add(e.getProperty().get(0).getValue());
		}

		// only check if system_archived = true
		for (String t : target) {
			conditions.getString().clear();
			properties.getString().clear();

			properties.getString().add("system_id");
			properties.getString().add("system_name");
			properties.getString().add("system_archived");

			conditions.getString().add("system_id eq '" + t + "'");

			sync = wsUtil.exportEntities(MainType.DEPLOYMENT_TARGET,
					properties, conditions);

			Entity e = sync.getEntity().get(0);

			DeploymentTarget dtgt = new DeploymentTarget();
			dtgt.setSystemId(Long.parseLong(e.getProperty().get(0).getValue()));
			dtgt.setSystemName(e.getProperty().get(1).getValue());
			dtgt.setArchive(e.getProperty().get(2).getValue()
					.equalsIgnoreCase("true"));
			dtgt.setCustomType(e.getCustomType());
			deploymentTarget.add(dtgt);

		}
		return deploymentTarget;

	}

	private List<Component> getDeploymentProfileComponents() throws Exception {

		ArrayOfString conditions = new ArrayOfString();
		ArrayOfString properties = new ArrayOfString();

		properties.getString().add("system_id");
		properties.getString().add("system_name");

		conditions.getString().add(
				"system_application.system_id eq '" + appValue + "'");

		Sync sync = wsUtil.exportEntities(MainType.COMPONENT, properties,
				conditions);

		List<Component> cmpnt = new ArrayList<Component>();
		for (Entity e : sync.getEntity()) {
			Component c = new Component();
			long id = Long.parseLong(e.getProperty().get(0).getValue(), 10);
			c.setSystemId(id);
			c.setSystemName(e.getProperty().get(1).getValue());
			c.setCustomType(e.getCustomType());

			cmpnt.add(c);
		}

		return cmpnt;
	}

	@Override
	public int run(String[] args) throws Exception {
		super.run(args);

		String cmpntValue = parser.getOptionValue(componentArg);

		String deploymentTargetValue = parser
				.getOptionValue(deploymentTargetArg);

		Boolean isDelete = parser.getOptionValue(deleteArg) == null ? false
				: true;
		cmpnts = getDeploymentProfileComponents();
		deploymentTargets = getDeploymentProfileTargets();

		// validate deployment target name
		if ((deploymentTargetValue != null)
				&& (!deploymentTargetValue.isEmpty())) {

			boolean found = false;
			DeploymentTarget t = null;
			for (DeploymentTarget deploymentTarget : deploymentTargets) {
				if (deploymentTargetValue.equalsIgnoreCase(deploymentTarget
						.getSystemName())
						|| (deploymentTargetValue.equalsIgnoreCase(Long
								.toString(deploymentTarget.getSystemId())))) {
					found = true;
					t = deploymentTarget;
					break;

				}
			}

			if (!found) {
				throw new IllegalArgumentException("The deployment target "
						+ deploymentTargetValue
						+ " wasn't assigned to environment " + environmentValue);
			}

			if (t.isArchive()) {
				throw new IllegalArgumentException("The deployment target "
						+ deploymentTargetValue + " was archived");
			}

			deploymentTargets.clear();
			deploymentTargets.add(t);
		}

		// validate component names
		if ((cmpntValue != null) && (!cmpntValue.isEmpty())) {
			String[] cmpntName = cmpntValue.split(",");
			List<Component> lcmpnt = new ArrayList<Component>();
			for (int i = 0; i < cmpntName.length; i++) {
				cmpntName[i] = cmpntName[i].trim();
				boolean found = false;
				for (Component cmpnt : cmpnts) {
					if (cmpntName[i].equalsIgnoreCase(cmpnt.getSystemName())
							|| cmpntName[i].equalsIgnoreCase(Long
									.toString(cmpnt.getSystemId()))) {
						found = true;
						lcmpnt.add(cmpnt);
						break;
					}
				}

				if (!found) {
					throw new IllegalArgumentException("The component "
							+ cmpntName[i]
							+ " wasn't belong to the application " + appValue);
				}

			}

			cmpnts.clear();
			cmpnts = lcmpnt;
		}

		Sync sync = buildEntitiesData();
		Sync currentSync = backupDeploymentProfileTarget();
		if (isDelete) {

			sync.getEntity().retainAll(currentSync.getEntity());
			int size = sync.getEntity().size();
			System.out.println("UC4RB_OUT_REMOVED|" + size);
			if (size > 0) {
				wsUtil.deleteEntities(sync);
			}
		} else {
			sync.getEntity().removeAll(currentSync.getEntity());
			int size = sync.getEntity().size();
			System.out.println("UC4RB_OUT_ASSIGNED|" + size);
			if (size > 0) {
				wsUtil.importEntities(sync, MainType.DEPLOYMENT_PROFILE_TARGET);
			}
		}
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();
		deploymentTargetArg = parser
				.addHelp(
						parser.addStringOption("tgt", "deploymentTarget", false),
						"The name or ID of the target to be removed. If empty all targets will be assigned/removed for the given component.");

		componentArg = parser
				.addHelp(
						parser.addStringOption("cmpnt", "component", false),
						"Comma separated list of component names, if empty auto assignment/removment is done for all components");

		deleteArg = parser
				.addHelp(
						parser.addBooleanOption("d", "delete", false),
						"If specified, the function will remove targets from components else it will add");
	}

}
