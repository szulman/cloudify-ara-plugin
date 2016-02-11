package com.uc4.cloudify;

public class Component {

	public String name;
	public String derived_from;

	
	public Component() {
	}

	   
	public Component(String name, String derived_from) {
		super();
		this.name = name;
		this.derived_from = derived_from;
	}


	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDerived_from() {
		return derived_from;
	}

	
	public void setDerived_from(String derived_from) {
		this.derived_from = derived_from;
	}
}
