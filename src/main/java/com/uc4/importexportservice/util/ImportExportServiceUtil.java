package com.uc4.importexportservice.util;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.uc4.ara.common.exception.DataNotFoundException;
import com.uc4.ara.common.exception.DataNotUniqueException;
import com.uc4.ara.common.exception.ImportExportServiceException;
import com.uc4.importexportservice.ArrayOfString;
import com.uc4.importexportservice.ImportExportServiceSoap;
import com.uc4.importexportservice.Result;
import com.uc4.importexportservice.structure.MainType;
import com.uc4.importexportservice.structure.Sync;

public class ImportExportServiceUtil {

	private ImportExportServiceSoap service;
	private String username;
	private String password;

	public ImportExportServiceUtil(ImportExportServiceSoap service,
			String username, String password) {
		this.service = service;
		this.username = username;
		this.password = password;
	}

	public Result handleImportExportResult(Result result) throws Exception {
		
		if (result != null) {
			// waiting until result is available
			while (result.getStatus() == 0) {
				TimeUnit.MILLISECONDS.sleep(100);
				result = service.getStatus(result.getToken());

			}
		}
		
		// throw exception if has errors
		if (result.getErrors() != null
				&& result.getErrors().getError().size() > 0) {		
		
			for (com.uc4.importexportservice.Error e : result.getErrors()
					.getError()) {			
				throw new ImportExportServiceException( e.getErrorCode() );
			}

		}
		return result;

	}

	public String getSystemIdFromNameOrId(MainType mainType, String name) throws Exception {
		
		ArrayOfString conditions = new ArrayOfString();
		ArrayOfString properties = new ArrayOfString();
		
		properties.getString().add("system_id");		
		
		try {
			
			long lid = Long.parseLong(name, 10);
			conditions.getString().add("system_id eq '" + lid + "'");
			Sync sync = exportEntities(mainType, properties, conditions);
			if (sync.getEntity().size() == 1) {
				return sync.getEntity().get(0).getProperty().get(0).getValue();
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			conditions.getString().add("system_name eq '" + name + "'");
		}
		

		Sync sync = exportEntities(mainType, properties, conditions);
		int size = sync.getEntity().size();
		
		if (size == 0) {
			throw new DataNotFoundException("Couldn't find system_id with system_name '" + name + "' in " + mainType.value());
		} else if (size > 1) {
			throw new DataNotUniqueException("There was more than one system_id with system_name '" + name + "' in " + mainType.value());
		}

		return sync.getEntity().get(0).getProperty().get(0).getValue();
		
	}
	public Sync exportEntities(MainType mainType, ArrayOfString properties,
			ArrayOfString conditions) throws Exception {

		Result result = service.export(username, password, mainType.value(),
				"", 0, 100, "XML", properties, "", "", conditions);

		result = handleImportExportResult(result);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();

		Document doc = builder.parse(new InputSource(new StringReader(result
				.getData())));

		JAXBContext context = JAXBContext.newInstance(Sync.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		JAXBElement<Sync> jaxbSync = unmarshaller.unmarshal(
				doc.getDocumentElement(), Sync.class);

		return jaxbSync.getValue();

	}

	public void deleteEntities(Sync sync) throws Exception {

		Result result = service.delete(username, password, null,
				"XML", true, prepareData(sync));

		result = handleImportExportResult(result);

	}

	public void importEntities(Sync sync, MainType mainType) throws Exception {

		Result result = service.importFunc(username, password,
				mainType.value(), "XML", true, prepareData(sync));

		result = handleImportExportResult(result);

	}
	
	public void SyncToFile(Sync sync, File f ) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Sync.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
				"sync.xsd");
	
		marshaller.marshal(new JAXBElement<Sync>(new QName("", "Sync"),
				Sync.class, sync), f);
	}
	
	public Sync FileToSync( File f) throws Exception {		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();

		Document doc = builder.parse(f);

		JAXBContext context = JAXBContext.newInstance(Sync.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		JAXBElement<Sync> jaxbSync = unmarshaller.unmarshal(
				doc.getDocumentElement(), Sync.class);
		
		return jaxbSync.getValue();
	}
	
	private String prepareData(Sync sync) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Sync.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
				"sync.xsd");

		StringWriter sw = new StringWriter();
		marshaller.marshal(new JAXBElement<Sync>(new QName("", "Sync"),
				Sync.class, sync), sw);
		return sw.toString();
	}

}
