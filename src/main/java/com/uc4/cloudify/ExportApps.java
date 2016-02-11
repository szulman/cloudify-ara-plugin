package com.uc4.cloudify;

import com.uc4.ara.feature.AraFileCmd;

public class ExportApps {

	            
	public static final void main(String[] args) {
		String[] exportArgs = {"rm", "RmFullExport", "--url", "http://vviedev05/ARA", "--username", "100/API/API", "--password", "123", "-mt", "Application", "--file", "/tmp/export.xml"};
		AraFileCmd.main(exportArgs);
	}
}
