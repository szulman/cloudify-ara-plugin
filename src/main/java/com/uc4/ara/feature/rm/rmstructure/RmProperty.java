package com.uc4.ara.feature.rm.rmstructure;

import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@XmlRootElement(name = "Property")
public class RmProperty {

    private String name;

    private boolean isIdentity = false;

    private String value;

    public RmProperty() {
        super();
        name = "";
        value = "";
    }

    public RmProperty(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }


    public void createXml(Element ele) {
        Document doc = ele.getOwnerDocument();
        Element property = doc.createElement("Property");
        property.setAttribute("name", name);

        if (isIdentity)
            property.setAttribute("isIdentity", "true");
        if (value != null) {
            Node node = doc.createElement("Value");
            node.setTextContent(value);
            property.appendChild(node);
        }

        ele.appendChild(property);
    }

    public void setIdentity(boolean isIdentity) {
        this.isIdentity = isIdentity;
    }

    public boolean isIdentity() {
        return isIdentity;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
