package com.uc4.ara.feature.rm;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.feature.utils.ResultHandler;
import com.uc4.ara.feature.utils.XmlUtil;
import com.uc4.ara.util.Logger;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public class ArchiveEntity extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> name;
	private CmdLineParser.Option<String> owner;
	private CmdLineParser.Option<String> folder;
	private CmdLineParser.Option<String> mainType;
	private CmdLineParser.Option<String> customType;
	private CmdLineParser.Option<String> startDate;
	private CmdLineParser.Option<String> endDate;
	private CmdLineParser.Option<String> conditions;
		
	private ImportExportServiceSoap service;
	
	@Override
	public int run(String[] args) throws Exception {
		initialize();
	    super.run(args);
	    
	    String nameValue = parser.getOptionValue(name);
	    String ownerValue = parser.getOptionValue(owner);
	    String folderValue = parser.getOptionValue(folder);
	    String mainTypeValue = parser.getOptionValue(mainType);
	    String customTypeValue = parser.getOptionValue(customType);
	    String startDateValue = parser.getOptionValue(startDate);
	    String endDateValue = parser.getOptionValue(endDate);
	    String conditionsValue = parser.getOptionValue(conditions);
		ArrayOfString addConditions = new ArrayOfString();     
	    if(conditionsValue != null) {
	    	for(String condition : conditionsValue.split(",")) {
	    		addConditions.getString().add(condition);
	    	}
	    }
	    service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);
	    
	    ArrayOfString properties = new ArrayOfString();
	    properties.getString().add("system_id");
	    properties.getString().add("system_archived");
	    
	    ArrayOfString conditions = new ArrayOfString();
	    conditions.getString().add("system_archived eq 'false'");
	    if(addConditions.getString().size() > 0)
	    	conditions.getString().addAll(addConditions.getString());
	    if(nameValue != null) {
	    	if(mainTypeValue != null) {
	    		nameValue = getIdFromNameOrId(service, mainTypeValue, nameValue);
	    		conditions.getString().add("system_id eq '" + nameValue + "'");
	    	} else {
	    		conditions.getString().add("system_name eq '" + nameValue + "'");
	    	}
	    }
	    if(ownerValue != null)
	    	conditions.getString().add("system_owner.system_name eq '" + ownerValue + "'");
	    if(folderValue != null)
	    	conditions.getString().add("system_folder.system_name eq '" + folderValue + "'");
		Result result = null;
		
		result = service.export(usernameValue, passwordValue, mainTypeValue, customTypeValue, 0, 1000, "XML", properties, startDateValue, endDateValue, conditions);
	    result = ResultHandler.handleImportExportResult(result, service);
		
	    Document resultXml = XmlUtil.transformStringToXML(result.getData());
	    Node[] nodes = XmlUtil.findNodesByTagName(resultXml, "Property");
	    
	    XmlUtil.setAllIdentityAttribute(resultXml, "system_id");
	    
	    for(Node node : nodes) {
	    	String name = XmlUtil.getNodeAttribute(node, "name");
	    	
	    	if(name.equals("system_archived")) {
	    		Node[] valueNodes = XmlUtil.findNodesByTagName(node, "Value");
	    		if(valueNodes.length > 0) {
	    			valueNodes[0].setTextContent("True");	    			
	    		}
	    	}
	    }
	    
	    String xmlContent = XmlUtil.transformXmlToString(resultXml);
	    Logger.log("XML-Content: " + xmlContent, loglevelValue);
	    
	    result = service.importFunc(usernameValue, passwordValue, mainTypeValue, "XML", true, xmlContent);
	    result = ResultHandler.handleImportExportResult(result, service);
	    
	    Logger.log("Finished archiving successfully", loglevelValue);
	    
		return ErrorCodes.OK;
	}


	@Override
	public void initialize() {
		super.initialize();
	    parser.setDescription("Archives a single entity or a set of entities specified by given filter-criterias by setting the system_archived flag.");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm ArchiveEntity --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -n \"test entity\" -o \"1/AC/UC\" -f \"TESTFOLDER\" -t \"TESTTYPE\" -c \"MY_CUSTOM_TYPE\" -s \"01-01-2013T00:00:00Z\" -e \"31-12-2013T23:59:59Z\"" +
	    				"\r\njava -jar dm-tool.jar rm ArchiveEntity -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\" -n 1234 " +
	    				"\r\njava -jar dm-tool.jar rm ArchiveEntity -u \"http://localhost/RM\" -un \"1/UC/UC\" -p \"--101234567890\"  -n \"test entity\" -t \"TESTTYPE\" " +
	    				"\r\njava -jar dm-tool.jar rm ArchiveEntity --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --name \"test entity\" --owner \"1/AC/UC\" --folder \"TESTFOLDER\" --type \"TESTTYPE\" --customtype \"MY_CUSTOM_TYPE\" --start \"01-01-2013T00:00:00Z\" --end \"31-12-2013T23:59:59Z\""
	    );
	    
	    
	    
	    name = parser.addHelp(parser.addStringOption("n", "name", false), "The name or ID of the entity to archive.");
	    owner = parser.addHelp(parser.addStringOption("o", "owner", false), "The owner of  the entity to archive.");
	    folder = parser.addHelp(parser.addStringOption("f", "folder", false), "The folder of the entity to archive.");
	    mainType = parser.addHelp(parser.addStringOption("t", "type", true), "The type of the the entity to archive.");
	    customType = parser.addHelp(parser.addStringOption("c", "customtype", false), "The custom type of the entity to archive.");
	    startDate = parser.addHelp(parser.addStringOption("s", "startdate", false), "The start date after that all entities will be archived.");
	    endDate = parser.addHelp(parser.addStringOption("e", "enddate", false), "The end date before that all entities will be archived.");
	    conditions = parser.addHelp(parser.addStringOption("co", "conditions", false), "Additional comma separated conditions to add in the form of: property eq 'value' i.e.: system_id eq '1234',system_name eq 'test name'");
	}
}
