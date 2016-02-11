package com.uc4.ara.feature.rm;

import java.io.RandomAccessFile;

import org.w3c.dom.Document;

import com.uc4.ara.common.xml.XmlParser;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.feature.utils.XmlUtil;
import com.uc4.ara.util.Logger;

import com.uc4.importexportservice.Result;

public class RmImport extends AbstractRmFeatureNew {

	private CmdLineParser.Option<String> xmlFilenameArg;
	

	@Override
	public int run(String[] args) throws Exception {		
	    super.run(args);

		
	    // maintype is not needed for XML imports
		String mainType = "";
		String xmlFilename = parser.getOptionValue(xmlFilenameArg);

		RandomAccessFile raf = null;
		String xmlContent;
		try {
			raf = new RandomAccessFile(xmlFilename, "r");
			XmlParser xmlParser = new XmlParser();
			Document doc = xmlParser.parse(raf);		
			xmlContent = XmlUtil.transformXmlToString(doc, xmlParser.getXmlEncoding());
		} finally {
			raf.close();
		}
	
		Result result = importFunc(service, mainType, xmlContent);

		Logger.log("Server reported '" + result.getDescription()
				+ "' and id " + result.getStatus(), loglevelValue);
		Logger.log("file " + xmlFilename + " successfully imported", loglevelValue);
		return ErrorCodes.OK;
	}

	@Override
	public void initialize() {
		super.initialize();
		
		parser.setDescription("Imports an object from an xml file.");
	    parser.setExamples(
	    		"java -jar dm-tool.jar rm RmImport --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" -f \"C:\\temp\\object.xml\" " +
	    				"\r\njava -jar dm-tool.jar rm RmImport --url \"http://localhost/RM\" --username \"1/UC/UC\" --password \"--101234567890\" --file \"C:\\temp\\object.xml\" "
	    );
	    
		xmlFilenameArg = parser.addHelp(parser.addStringOption("f", "file", true), "The name of the xml file to import.");
	}
}