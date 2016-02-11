package com.uc4.ara.feature.rm;

import java.io.File;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.structure.MainType;
import com.uc4.importexportservice.structure.Sync;

public class RollbackDeploymentProfileTarget extends
		AbstractDeploymentProfileFeature {

	private CmdLineParser.Option<String> fileArg;

	@Override
	public int run(String[] args) throws Exception {
		super.run(args);

		String fileName = parser.getOptionValue(fileArg);

		deleteDeploymentProfileTarge();

		File f = new File(fileName);
		Sync sync = wsUtil.FileToSync(f);
		if (sync.getEntity().size() > 0) {
			wsUtil.importEntities(sync, MainType.DEPLOYMENT_PROFILE_TARGET);
		}
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();
		fileArg = parser
				.addHelp(parser.addStringOption("f", "file", true),
						"The file contains data to import to table DeploymentProfileTarget.");
	}

	private void deleteDeploymentProfileTarge() throws Exception {

		Sync sync = backupDeploymentProfileTarget();
		if (sync.getEntity().size() > 0) {
			wsUtil.deleteEntities(sync);
		}
	}

}
