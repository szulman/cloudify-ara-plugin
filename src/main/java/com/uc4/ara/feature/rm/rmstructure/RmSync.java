package com.uc4.ara.feature.rm.rmstructure;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Sync")
public class RmSync {
	private List<RmEntity> entities = new LinkedList<RmEntity>();

	@XmlElement(name = "Entity")
	public List<RmEntity> getEntities() {
		return this.entities;
	}
	
}
