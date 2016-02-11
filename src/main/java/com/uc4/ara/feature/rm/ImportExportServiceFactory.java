package com.uc4.ara.feature.rm;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.uc4.importexportservice.ImportExportService;
import com.uc4.importexportservice.ImportExportServiceSoap;

public class ImportExportServiceFactory {
	
	/**
	 * The Constant IMPORTEXPORTSERVICE.
	 */
	public static final String IMPORTEXPORTSERVICE = "/service/ImportExportService.asmx?wsdl";
	
	public static ImportExportServiceSoap getImportExportServiceFromUrl(String url) throws MalformedURLException {
		ImportExportService importExportService = new ImportExportService(
				new URL(url + IMPORTEXPORTSERVICE), new QName(
						"http://uc4.com/", "ImportExportService"));

		ImportExportServiceSoap importExportServiceSoap = importExportService
				.getImportExportServiceSoap();
		
		//update endpoint url for import / export
		String correctedEndpoint = url + IMPORTEXPORTSERVICE;
		correctedEndpoint = correctedEndpoint.replace("?wsdl", "");
		BindingProvider bindingProvider = (BindingProvider) importExportServiceSoap;
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, correctedEndpoint);	
		
		return importExportServiceSoap;
	}
	
	public static ImportExportServiceSoap getImportExportServiceFromUrlWithoutSSL(
			String url) throws MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException {
		DeploymentServiceFactory.circumventSslProblems();
		
		ImportExportServiceSoap importExportService = getImportExportServiceFromUrl(url);

		return importExportService;
	}
}
