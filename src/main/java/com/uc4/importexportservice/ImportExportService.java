
package com.uc4.importexportservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * Data-API
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ImportExportService", targetNamespace = "http://uc4.com/", wsdlLocation = "file:/E:/temp/uc4/wsimport/bin/ImportExportService.asmx")
public class ImportExportService
    extends Service
{

    private final static URL IMPORTEXPORTSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.uc4.importexportservice.ImportExportService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.uc4.importexportservice.ImportExportService.class.getResource(".");
            url = new URL(baseUrl, "file:/E:/temp/uc4/wsimport/bin/ImportExportService.asmx");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/E:/temp/uc4/wsimport/bin/ImportExportService.asmx', retrying as a local file");
            logger.warning(e.getMessage());
        }
        IMPORTEXPORTSERVICE_WSDL_LOCATION = url;
    }

    public ImportExportService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ImportExportService() {
        super(IMPORTEXPORTSERVICE_WSDL_LOCATION, new QName("http://uc4.com/", "ImportExportService"));
    }

    /**
     * 
     * @return
     *     returns ImportExportServiceSoap
     */
    @WebEndpoint(name = "ImportExportServiceSoap")
    public ImportExportServiceSoap getImportExportServiceSoap() {
        return super.getPort(new QName("http://uc4.com/", "ImportExportServiceSoap"), ImportExportServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ImportExportServiceSoap
     */
    @WebEndpoint(name = "ImportExportServiceSoap")
    public ImportExportServiceSoap getImportExportServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://uc4.com/", "ImportExportServiceSoap"), ImportExportServiceSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns ImportExportServiceSoap
     */
    @WebEndpoint(name = "ImportExportServiceSoap12")
    public ImportExportServiceSoap getImportExportServiceSoap12() {
        return super.getPort(new QName("http://uc4.com/", "ImportExportServiceSoap12"), ImportExportServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ImportExportServiceSoap
     */
    @WebEndpoint(name = "ImportExportServiceSoap12")
    public ImportExportServiceSoap getImportExportServiceSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://uc4.com/", "ImportExportServiceSoap12"), ImportExportServiceSoap.class, features);
    }

}
