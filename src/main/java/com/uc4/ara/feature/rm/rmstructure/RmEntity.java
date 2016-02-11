package com.uc4.ara.feature.rm.rmstructure;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

@XmlRootElement(name = "Entity")
public class RmEntity {

    private String mainType;

    private String customType;

    private List<RmProperty> properties = new LinkedList<RmProperty>();

    public RmEntity() {
    }

    public RmEntity(String mainType) {
        this.mainType = mainType;
    }

    public RmProperty addIdentityProperty(String name, String value) {
        RmProperty rmProperty = new RmProperty(name, value);
        rmProperty.setIdentity(true);
        properties.add(rmProperty);
        return rmProperty;
    }

    public RmProperty addProperty(String name, String value) {
        RmProperty rmProperty = new RmProperty(name, value);
        properties.add(rmProperty);
        return rmProperty;
    }


    public void createXml(Element ele) {
        Document doc = ele.getOwnerDocument();
        Element entity = doc.createElement("Entity");
        if (mainType != null)
            entity.setAttribute("mainType", mainType);
        if (customType != null)
            entity.setAttribute("customType", customType);

        for (RmProperty rmProperty : properties)
            rmProperty.createXml(entity);
        ele.appendChild(entity);

    }


    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public String getMainType() {
        return mainType;
    }

    public String getCustomType() {
        return customType;
    }


    public void setMainType(String mainType) {
        this.mainType = mainType;
    }


    @XmlElement(name = "Property")
    public List<RmProperty> getProperties() {
        return this.properties;
    }

}
