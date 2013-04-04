package org.mediasport.matchcenter.docs;

import java.io.Serializable;

public class Team implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7591543386522676957L;
	String name;
	private String shortName; 
	
	public Team(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
