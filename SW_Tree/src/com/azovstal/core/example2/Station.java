package com.azovstal.core.example2;

import java.io.Serializable;

public class Station implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7941518599328986899L;
	private Integer id =1;
	private String fullName;
	private String shortName;
	

	
	public Station(String fullName, String shortName) {
		super();
		this.id++;
		this.fullName = fullName;
		this.shortName = shortName;
	}


	public Station() {
		super();
	}


	@Override
	public String toString() {
		return "Station=" + fullName;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
}
