package com.uc4.cloudify;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import com.uc4.ara.feature.AraFileCmd;
import com.uc4.ara.feature.rm.AbstractRmFeatureNew;

public class ExportApps {

	
	public static final String EXPORT_APP_XML = "/tmp/export.xml";
	
	
	public ExportApps() {
	}
	
	
	public void apps2cloudify() {
		exportAppsIntoXML(); 
		getAppNames();
	}
	
	   
	public void exportAppsIntoXML() {
		String[] exportArgs = {"rm", "RmFullExport", "--url", "http://vviedev05/ARA", "--username", "100/API/API", "--password", "123", "-mt", "Application", "--file", EXPORT_APP_XML};
		AraFileCmd.main(exportArgs);
	}
	
	         
	public void getAppNames() {
	     System.out.println("");
		try {
			String xml = readFile(EXPORT_APP_XML);
	        List<String[]> resultValue = AbstractRmFeatureNew.getTextContentFromXpath(xml, "//Entity", "./Property[@name='system_name']/Value/text()");
	        System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
       
	public static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}
     
	
	public static final void main(String[] args) {
		ExportApps e = new ExportApps();
		e.apps2cloudify();
	}
	
	
}
