package com.uc4.cloudify;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import com.uc4.ara.feature.AraFileCmd;
import com.uc4.ara.feature.rm.AbstractRmFeatureNew;

public class ExportApps {

	
	public static final String EXPORT_APP_XML = "/tmp/export.xml";
	public static final String PLUGIN_YAML = "plugin.yaml";
	
	public ExportApps() {
	}
	
	
	public void apps2cloudify() {
		exportAppsIntoXML(); 
		ComponentList componentList = getAppNames();
		exportYAML(componentList);
	}
	
	 
	public void exportYAML(ComponentList componentList) {
		String yaml = "";
		yaml += "plugins:" + "\n";
		yaml += "    ara:" + "\n";
		yaml += "        executor: host_agent" + "\n";
		yaml += "        source: https://github.com/szulman/cloudify-ara-plugin/archive/master.zip" + "\n";
		yaml += "        package_name: cloudify-ara-plugin" + "\n";
		yaml += "        package_version: 'master'" + "\n";
		yaml += "" + "\n";
		yaml += "node_types:" + "\n";
		yaml += "    # An app module configured with ara" + "\n";
		      
		for (Component component : componentList) {
			yaml += "    cloudify.ara.nodes." + component.getName() + ":" + "\n";
			yaml += "        derived_from: cloudify.nodes.ApplicationModule" + "\n";
			yaml += "        interfaces:" + "\n";
			yaml += "            cloudify.interfaces.lifecycle:" + "\n";
			yaml += "                create:" + "\n";
			yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
			yaml += "                    inputs: {}" + "\n";
			yaml += "                configure:" + "\n";
			yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
			yaml += "                    inputs: {}" + "\n";
			yaml += "                start:" + "\n";
			yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
			yaml += "                    inputs: {}" + "\n";
			yaml += "                stop:" + "\n";
			yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
			yaml += "                    inputs: {}" + "\n";
			yaml += "                delete:" + "\n";
			yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
			yaml += "                    inputs: {}" + "\n";
			yaml += "        properties:" + "\n";
			yaml += "            ara_config: {}" + "\n";
			yaml += "\n";
			yaml += "\n";
			yaml += "\n";
		}

		
		yaml += "relationships:" + "\n";
		yaml += "    cloudify.ara.depends_on:" + "\n";
		yaml += "        derived_from: cloudify.relationships.depends_on" + "\n";
		yaml += "        source_interfaces:" + "\n";
		yaml += "            cloudify.interfaces.relationship_lifecycle:" + "\n";
		yaml += "                preconfigure:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                postconfigure:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                establish:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                unlink:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		
		yaml += "" + "\n";
		yaml += "" + "\n";
		yaml += "    cloudify.ara.connected_to:" + "\n";
		yaml += "        derived_from: cloudify.relationships.connected_to" + "\n";
		yaml += "        source_interfaces:" + "\n";
		yaml += "            # The comment under types.interfaces applies here too" + "\n";
		yaml += "            cloudify.interfaces.relationship_lifecycle:" + "\n";
		yaml += "                preconfigure:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                postconfigure:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                establish:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                unlink:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";

		yaml += "" + "\n";
		yaml += "" + "\n";
		yaml += "    cloudify.ara.contained_in:" + "\n";
		yaml += "        derived_from: cloudify.relationships.connected_to" + "\n";
		yaml += "        source_interfaces:" + "\n";
		yaml += "            # The comment under types.interfaces applies here too" + "\n";
		yaml += "            cloudify.interfaces.relationship_lifecycle:" + "\n";
		yaml += "                preconfigure:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                postconfigure:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                establish:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "                unlink:" + "\n";
		yaml += "                    implementation: ara.ara_plugin.operations.operation" + "\n";
		yaml += "                    inputs: {}" + "\n";
		yaml += "" + "\n";
		
		try {
			PrintWriter plugin_yaml = new PrintWriter(PLUGIN_YAML);
			plugin_yaml.print(yaml);
			plugin_yaml.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	/**public void exportYAML(ComponentList componentList) {
		try {
			YamlWriter writer = new YamlWriter(new FileWriter("output.yml"));
			writer.getConfig().setClassTag("contact", ComponentList.class);
			writer.write(componentList);
			writer.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}**/
	
	
	public void exportAppsIntoXML() {
		String[] exportArgs = {"rm", "RmFullExport", "--url", "http://vviedev05/ARA", "--username", "100/API/API", "--password", "123", "-mt", "Application", "--file", EXPORT_APP_XML};
		AraFileCmd.main(exportArgs);
	}
	
	         
	public ComponentList getAppNames() {
		System.out.println("");
		ComponentList componentList = new ComponentList();
		try {
			String xml = readFile(EXPORT_APP_XML);
	        List<String[]> resultValue = AbstractRmFeatureNew.getTextContentFromXpath(xml, "//Entity", "./Property[@name='system_name']/Value/text()");
	        for (String[] result : resultValue) {
	        	for (String appName : result) {
	        		System.out.println("APP --> " + appName);
	        		Component component = new Component(appName, "cloudify.nodes.ApplicationModule");
	        		componentList.add(component);
	        	}
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return componentList;
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
