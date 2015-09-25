package com.azovstal.core.example2;

import java.io.Serializable;

public class Path implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1777982520485741790L;
	private Integer id =1;
	private String fullName;
	private String shortName;
	private Front front;

	
	public Path(Front front, String fullName, String shortName) {
		super();
		this.id++;
		this.front = front;
		this.fullName = fullName;
		this.shortName = shortName;
	}
	public Path(String fullName, String shortName) {
		super();
		this.id++;
		this.fullName = fullName;
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "Path =" + fullName;
//		return "Path [front=" + front + ", fullName=" + fullName + ", id=" + id
//				+ ", shortName=" + shortName + "]";
	}
	public Path() {
		super();
		// TODO Auto-generated constructor stub
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


	public void setFront(Front front) {
		this.front = front;
	}


	public Front getFront() {
		return front;
	}
	
}
