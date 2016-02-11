package com.uc4.ara.feature.rm;

import java.io.IOException;
import java.net.MalformedURLException;

import com.uc4.ara.feature.AbstractFeature;
import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.utils.Maxim;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;

/**
 * The Class AbstractRmFeature. To compile the wsdl to java code, perform the
 * following steps:
 * <ol>
 * <li>download all wsdls (AdminService.asmx, DeploymentService.svc,
 * ImportExportService.asmx) e.g. http://192.168.44.20/ApplicationReleaseBuilder
 * /Factory/v3/service</li>
 * <li>create AdminServices by calling: wsimport.exe -keep -p
 * com.uc4.adminservice -extension AdminService.asmx</li>
 * <li>create DeploymentServices by calling: wsimport.exe -keep -p
 * com.uc4.deploymentservice DeploymentService.svc</li>
 * <li>Edit ImportExportService.asmx and replace all occurences of "Import" to
 * ImportFunc" except the strings "soapAction="http://uc4.com/Import"" (2
 * occurences)</li>
 * <li>create ImportExportServices by calling: wsimport.exe -keep -p
 * com.uc4.importexportservice ImportExportService.asmx -extension</li>
 * <li>remove all *.class files from the generated directories</li>
 * <li>edit com\\uc4\\importexportservice\\ImportFunc.java and replace
 * ImportFunc to Import at the following locations:</li>
 * <li>@XmlRootElement(name = "ImportFunc")</li>
 * <li>edit com\\uc4\\importexportservice\\ImportExportServiceSoap.java and
 * replace ImportFunc to Import at the following locations:</li>
 * <li>@WebMethod(operationName = "ImportFunc", action =
 * "http://uc4.com/Import")</li>
 * <li>@RequestWrapper(localName = "ImportFunc", ...</li>
 * <li>replace the old java files with the new generated ones</li>
 * </ol>
 */
public abstract class AbstractRmFeature extends AbstractFeature {


	
	/**
	 * The Constant ADMINSERVICE.
	 */
	protected static final String ADMINSERVICE = "/service/AdminService.asmx";


	/**
	 * The rm host.
	 */
	protected String rmHost;

	/**
	 * The rm username.
	 */
	protected String rmUsername;

	/**
	 * The rm password.
	 */
	protected String rmPassword;

	

	/**
	 * Analyze default params.
	 * 
	 * @param args
	 *            the args
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void analyzeDefaultParams(String[] args) throws IOException {
		rmHost = args[0];
		rmUsername = args[1];
		String password = args[2];
		rmPassword = Maxim.deMaxim(password);
	}

	/**
	 * Search id by name.
	 * 
	 * @param mainType
	 *            the main type
	 * @param name
	 *            the name
	 * @return the string
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	protected String searchIdByName(String mainType, String name)
			throws MalformedURLException, InterruptedException {
		return searchOneProperty(mainType, "system_name", name, "system_id");
	}

	/**
	 * Search one property.
	 * 
	 * @param mainType
	 *            the main type
	 * @param property
	 *            the property
	 * @param name
	 *            the name
	 * @param propertyToSearch
	 *            the property to search
	 * @return the string
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	protected String searchOneProperty(String mainType, String property,
			String name, String propertyToSearch) throws MalformedURLException,
			InterruptedException {
		ArrayOfString properties = new ArrayOfString();
		ArrayOfString conditions = new ArrayOfString();

		conditions.getString().add(property + " eq '" + name + "'");
		properties.getString().add(propertyToSearch);

		String res = export(mainType, null, properties, conditions);
		// System.out.println("search for id got " + res);
		int idx = res.indexOf("<Value>");
		if (idx == -1)
			return null;

		res = res.substring(idx + 7);

		// check if there is another value. If it is so, throw an exception
		idx = res.indexOf("<Value>");
		if (idx != -1) {
			throw new RuntimeException("Property " + mainType + "/" + property
					+ "='" + name + "' not unique");
		}

		// now return the single value.
		idx = res.indexOf("</Value>");
		res = res.substring(0, idx);

		return res;
	}

	/**
	 * Prepare export all.
	 * 
	 * @param mainType
	 *            the main type
	 * @param customType
	 *            the custom type
	 * @param properties
	 *            the properties
	 * @param conditions
	 *            the conditions
	 * @return the string
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	protected String export(String mainType, String customType,
			ArrayOfString properties, ArrayOfString conditions)
			throws MalformedURLException, InterruptedException {

		int begin = 0;
		int count = 1000;
		String format = "XML";
		String startDate = null;
		String endDate = null;

		ImportExportServiceSoap importExportServiceSoap = ImportExportServiceFactory.getImportExportServiceFromUrl(rmHost);
		
		// Endpoint ep = Endpoint.publish(rmHost, importExportServiceSoap);
		// ep.getBinding().getHandlerChain().add(new SoapLoggingHandler());

		Result result = importExportServiceSoap.export(rmUsername, rmPassword,
				mainType, customType, begin, count, format, properties,
				startDate, endDate, conditions);

		result = handleResult(result, importExportServiceSoap);

		return result.getData();
	}

	/**
	 * Import func.
	 * 
	 * @param data
	 *            the data
	 * @return the string
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	protected Result importFunc(String mainType, String data) throws MalformedURLException,
			InterruptedException {
		ImportExportServiceSoap importExportServiceSoap = ImportExportServiceFactory.getImportExportServiceFromUrl(rmHost);
		Result result = importExportServiceSoap.importFunc(rmUsername,
				rmPassword, mainType, "XML", false, data);

		result = handleResult(result, importExportServiceSoap);
		return result;
	}

	/**
	 * Handle result.
	 * 
	 * @param result
	 *            the result
	 * @param service
	 *            the service
	 * @return the result
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	protected Result handleResult(Result result, ImportExportServiceSoap service)
			throws InterruptedException {

		while (result.getStatus() == 0) {
			// The request is getting processed, wait a bit and ask again
			Thread.sleep(500);
			result = service.getStatus(result.getToken());
		}

		if (result.getErrors() != null
				&& result.getErrors().getError().size() > 0) {
			for (com.uc4.importexportservice.Error error : result.getErrors()
					.getError())
				FeatureUtil.logMsg("ReleaseManager-Error: "
						+ error.getErrorCode() + ", " + error.getErrorDetail());
			// throw an exception with the first errorcode
			throw new RmErrorCodeException(result.getErrors().getError().get(0)
					.getErrorCode());
		}

		if (result.getStatus() > 1) {
			// an error occured or the status represents the id of the (single)
			// imported entry
			// throw new RmErrorCodeException(ErrorCode.UNEXPECTED_ERROR);
		}

		return result;
	}
	
	
}
