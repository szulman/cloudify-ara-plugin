package com.uc4.ara.feature.rm.rmstructure;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.uc4.ara.feature.FeatureUtil;
import com.uc4.ara.feature.FeatureUtil.MsgTypes;
import com.uc4.ara.feature.utils.XmlUtil;

public class RmImportExportStructure {

    private List<RmEntity> entities = new LinkedList<RmEntity>();

    public RmEntity addEntityForDelete() {
        RmEntity rmEntity = new RmEntity();
        entities.add(rmEntity);
        return rmEntity;
    }

    public RmEntity addEntity(String mainType) {
        RmEntity rmEntity = new RmEntity(mainType);
        entities.add(rmEntity);
        return rmEntity;
    }

    public StringBuffer createXml() {

        Document doc = null;
        try {
            doc = FeatureUtil.createDocument("<Sync/>");
        } catch (Exception e) {
            FeatureUtil.logMsg("Unable to create import XML.", MsgTypes.ERROR);
            return new StringBuffer();
        }

        Element root = doc.getDocumentElement();
        root.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
                "xsi:noNamespaceSchemaLocation", "sync.xsd");

        for (RmEntity rmEntity : entities) {
            rmEntity.createXml(root);
        }

        StringBuffer res = null;
        try {
            res = new StringBuffer(XmlUtil.transformXmlToString(doc));
        } catch (Exception e) {
            FeatureUtil.logMsg("Unable to create import XML.", MsgTypes.ERROR);
            return new StringBuffer();
        }
        return res;
    }

}
