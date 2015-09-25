package com.azovstal.core.example2;

import java.io.Serializable;

public class Front implements Serializable {

	private Integer id =1;
	private String fullName;
	private String shortName;
	private Station station;

	
	public Station getStation() {
		return station;
	}


	public void setStation(Station station) {
		this.station = station;
	}

	public Front(Station station,String fullName, String shortName) {
		super();
		this.id++;
		this.station = station;
		this.fullName = fullName;
		this.shortName = shortName;
	}
	
	public Front(String fullName, String shortName) {
		super();
		this.id++;
		this.fullName = fullName;
		this.shortName = shortName;
	}


	public Front() {
		super();
	}

	@Override
	public String toString() {
		return "Front [fullName=" + fullName;
//		return "Front [fullName=" + fullName + ", id=" + id + ", shortName="
//				+ shortName + ", station=" + station + "]";
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
