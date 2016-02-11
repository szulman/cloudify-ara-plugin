package com.uc4.ara.feature.rm;

import java.io.File;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.importexportservice.structure.Sync;

public class BackupDeploymentProfileTarget extends
		AbstractDeploymentProfileFeature {

	private CmdLineParser.Option<String> fileArg;

	@Override
	public int run(String[] args) throws Exception {
		super.run(args);

		String fileName = parser.getOptionValue(fileArg);
		File f = new File(fileName);
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}

		Sync sync = backupDeploymentProfileTarget();
		wsUtil.SyncToFile(sync, f);
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();
		fileArg = parser
				.addHelp(parser.addStringOption("f", "file", true),
						"The file to store exported data from table DeploymentProfileTarget.");
	}

}
