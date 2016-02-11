package com.uc4.ara.feature.rm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jsoup.helper.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.uc4.ara.common.exception.DataNotFoundException;
import com.uc4.ara.common.exception.DataNotUniqueException;
import com.uc4.ara.common.exception.ImportExportServiceException;
import com.uc4.ara.feature.AbstractFeature;
import com.uc4.ara.feature.AbstractPublicFeature;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.globalcodes.ErrorCodes;
import com.uc4.ara.feature.utils.CmdLineParser;
import com.uc4.ara.feature.utils.ResultHandler;
import com.uc4.ara.feature.utils.XmlUtil;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.Error;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

public abstract class AbstractRmFeatureNew extends AbstractPublicFeature {

	protected CmdLineParser.Option<String> url;
	protected CmdLineParser.Option<String> username;
	protected CmdLineParser.Option<String> password;

	protected String urlValue;
	protected String usernameValue;
	protected String passwordValue;
	protected ImportExportServiceSoap service;

	@Override
	public void initialize() {
		super.initialize();
		url = parser.addHelp(parser.addStringOption("u", "url", true),
				"The url to the ARA instance that is used.");

		username = parser.addHelp(parser.addStringOption("un", "username", true),
				"The username that is used to authenticate against the ARA.");

		password = parser.addHelp(parser.addPasswordOption("p", "password", true),
				"The password/token to authenticate against the ARA.");
	}

	@Override
	public int run(String[] args) throws Exception {

		super.run(args);
		urlValue = parser.getOptionValue(url);
		usernameValue = parser.getOptionValue(username);
		passwordValue = parser.getOptionValue(password);

		service = ImportExportServiceFactory.getImportExportServiceFromUrlWithoutSSL(urlValue);

		return ErrorCodes.OK;
	}


	protected String getValueFromXmlResult(String result) throws Exception {
		// System.out.println("search for id got " + res);
		int idx = result.indexOf("<Value>");
		if (idx == -1)
			return null;

		result = result.substring(idx + 7);

		// now return the single value.
		idx = result.indexOf("</Value>");
		result = result.substring(0, idx);

		return result;
	}


	protected String getIdFromMainType(ImportExportServiceSoap service, String mainType, String environmentNameValue, String typeValue) throws Exception {

		ArrayOfString conditions = new ArrayOfString();
		conditions.getString().add("system_name eq '" + environmentNameValue + "'");
		ArrayOfString properties = new ArrayOfString();
		properties.getString().add("system_id");
		Result result = service.export(usernameValue, passwordValue, mainType, typeValue, 0, 1, "XML", properties, "", "", conditions);

		result = ResultHandler.handleImportExportResult(result, service);

		return this.getValueFromXmlResult(result.getData());
	}


	
	protected String getIdFromNameOrId(ImportExportServiceSoap service, String mainType, String nameOrId, String... conditionsString) throws Exception {
		if(nameOrId != null) {
			ArrayOfString properties = new ArrayOfString();
			properties.getString().add("system_id");
			ArrayOfString conditions = new ArrayOfString();
			for (String condition : conditionsString)
				conditions.getString().add(condition);		
			
			conditions.getString().add("system_id eq '" + nameOrId + "'");
			if(StringUtil.isNumeric(nameOrId)){
				//numeric value, try to get by id first
				try {
						Result result = null;
						result = service.export(usernameValue, passwordValue, mainType, "", 0, 1000, "XML", properties, "", "", conditions);
						result = ResultHandler.handleImportExportResult(result, service);
						return this.getValueFromXmlResult(result.getData());
					} catch(Exception ex) {
						return this.getIdFromMainType(service, mainType, nameOrId, "");
					}			
			}else{
				//non numeric value, try with as name
				return this.getIdFromMainType(service, mainType, nameOrId, "");
			}
		}
		
		return nameOrId;
	}
	

	/** Get Object ID from name or ID of main type.
     * @param mainType
     * @param nameOrId
     * @return List ID or the matched objects
     * @throws ImportExportServiceException
     * @throws DataNotUniqueException   If name is not unique for the given main type
     * @throws DataNotFoundException    If name or ID is not found for the given main type
     * @throws XPathExpressionException Not likely to happen
     * @author lqt
     */
    protected List<String> findObjectIds(String mainType, String propertyName, String propertyValue)
            throws ImportExportServiceException, DataNotUniqueException, DataNotFoundException, XPathExpressionException {

        if (propertyName == null)
            return null;

        return searchOnePropertyReturnAll(service, mainType, propertyName, propertyValue, "system_id");
    }
	
	
    /** Get Object ID from name or ID of main type.
     * @param mainType
     * @param nameOrId
     * @return  ID or the object
     * @throws ImportExportServiceException
     * @throws DataNotUniqueException   If name is not unique for the given main type
     * @throws DataNotFoundException    If name or ID is not found for the given main type
     * @throws XPathExpressionException Not likely to happen
     * @author dtn
     */
    protected String getIdFromNameOrIdv2(String mainType, String nameOrId)
            throws ImportExportServiceException, DataNotUniqueException, DataNotFoundException, XPathExpressionException {

        if (nameOrId == null)
            return null;

        String resultID = nameOrId;

        try {
            Long.parseLong(nameOrId, 10);
            resultID = searchOneProperty(service, mainType, "system_id", nameOrId, "system_id");

            if (nameOrId.equals(resultID)) {
                return resultID;
            } else
                throw new Exception();

        } catch (Exception e) {
            resultID = searchOneProperty(service, mainType, "system_name", nameOrId, "system_id");
        }

        return resultID;
    }

    /**
     * @param xmlContent    Content of XML file
     * @param parentXpath   Used to search for each Entity
     * @param childXpaths   Unique property of each Entity
     * @return  List of Entities that contains values parsed from childXpaths
     */
    public static List<String[]> getTextContentFromXpath(String xmlContent,
            String parentXpath, String...childXpaths) throws XPathExpressionException {

        if (xmlContent == null)
            return null;

        List<String[]> strings = new ArrayList<String[]>();
        Document doc = null;
        try {
            doc = XmlUtil.createDocumentFromString(xmlContent);
        } catch (Exception e) {
            FeatureUtil.logMsg("Unable to parse exported result. Message: " + e.getMessage(), MsgTypes.ERROR);
            return null;
        }

        XPath xpath = XPathFactory.newInstance().newXPath();

        XPathExpression expr =  xpath.compile(parentXpath);
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        outer:for (int i = 0; i < nodes.getLength(); i++) {

            String[] values = new String[childXpaths.length];

            for (int j = 0; j < childXpaths.length; j++) {

                XPathExpression childExpr =  xpath.compile(childXpaths[j]);
                values[j] = (String) childExpr.evaluate(nodes.item(i), XPathConstants.STRING);

                if (values[j] == null) {
                    continue outer;
                }
            }
            strings.add(values);
        }

        return strings;
    }


    /**
     * @param service
     * @param mainType
     * @param propName
     * @param propValue Condition: propName eq propValue
     * @param searchProp    Property to search for. Ex: system_id
     * @return
     * @throws ImportExportServiceException
     * @throws DataNotUniqueException
     * @throws DataNotFoundException
     * @throws XPathExpressionException
     */
    protected String searchOneProperty(ImportExportServiceSoap service, String mainType,
            String propName, String propValue, String searchProp)
                    throws ImportExportServiceException, DataNotUniqueException, DataNotFoundException, XPathExpressionException {

        ArrayOfString properties = new ArrayOfString();
        ArrayOfString conditions = new ArrayOfString();

        conditions.getString().add(propName + " eq '" + propValue + "'");
        properties.getString().add(searchProp);

        String resultData = export(mainType, null, properties, conditions);

        List<String[]> resultValue = getTextContentFromXpath(resultData,
                "//Entity",
                "./Property[@name='" + searchProp + "']/Value/text()");

        if (resultValue == null)
            return null;

        if (resultValue.size() == 1) {
            return resultValue.get(0)[0];
        } else if (resultValue.size() > 1) {
            throw new DataNotUniqueException("Property " + mainType + "/" + propName
                    + "='" + propValue + "' is not unique");
        } else {
            throw new DataNotFoundException("Property " + mainType + "/" + propName
                    + "='" + propValue + "' could not be found");
        }
    }

    protected List<String> searchOnePropertyReturnAll(ImportExportServiceSoap service, String mainType,
            String propName, String propValue, String searchProp)
                    throws ImportExportServiceException, DataNotUniqueException, DataNotFoundException, XPathExpressionException {

        ArrayOfString properties = new ArrayOfString();
        ArrayOfString conditions = new ArrayOfString();

        conditions.getString().add(propName + " eq '" + propValue + "'");
        properties.getString().add(searchProp);

        String resultData = export(mainType, null, properties, conditions);

        List<String[]> resultValue = getTextContentFromXpath(resultData,
                "//Entity",
                "./Property[@name='" + searchProp + "']/Value/text()");

        if (resultValue == null) {
            return null;
        }
        
        List<String> result = new ArrayList<String>();
        
        for (String[] row : resultValue) {
        	if (row != null && row.length > 0) {
        		result.add(row[0]);
        	}
        }
        
        return result;
    }


    protected String export(String mainType, String customType, ArrayOfString properties,
            ArrayOfString conditions) throws ImportExportServiceException {

        int begin = 0;
        int count = 1000;
        String format = "XML";
        String startDate = null;
        String endDate = null;

        Result result = service.export(usernameValue, passwordValue,
                mainType, customType, begin, count, format, properties,
                startDate, endDate, conditions);

        result = handleResult(result, service);

        return result.getData();
    }


    protected Result importFunc(ImportExportServiceSoap service, String mainType, String data)
            throws ImportExportServiceException {

        Result result = service.importFunc(usernameValue, passwordValue, mainType, "XML", false, data);

        result = handleResult(result, service);
        return result;
    }

	
    protected Result handleResult(Result result, ImportExportServiceSoap service)
            throws  ImportExportServiceException {

        if (result != null) {
            while (result.getStatus() == 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                }
                result = service.getStatus(result.getToken());
            }
        }

        if (result != null && result.getErrors() != null
                && result.getErrors().getError().size() > 0) {

            List<String> errors = new ArrayList<String>();
            for (Error error : result.getErrors().getError()) {
                errors.add(error.getErrorTitle() + ": " + error.getErrorDetail());
            }
            throw new ImportExportServiceException(errors.toString());
        }

        return result;
    }

	/**
	 * 
	 * @param startDateString
	 * @return
	 * @throws ParseException
	 */
	protected Calendar getCalendarFromString(String startDateString)
			throws ParseException {
		if(startDateString != null && startDateString.length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(AbstractFeature.AUTOMATION_ENGINE_DATE_FORMAT);
			Calendar startDate = Calendar.getInstance();
			startDate.setTime(sdf.parse(startDateString));

			return startDate;
		}

		return null;
	}
}
