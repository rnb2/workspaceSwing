package com.azovstal.core.example2;

import java.io.Serializable;

public class UnloadPlace implements Serializable {
	private Integer id=1;
	private Path path;
	private Front front;
	private Station station;
	
	public UnloadPlace() {
	}

	public UnloadPlace(Path path, Front front, Station station) {
		super();
		this.id++;
		this.path = path;
		this.front = front;
		this.station = station;
	}

	@Override
	public String toString() {
		return "UnloadPlace [front=" + front + ", id=" + id + ", path=" + path
				+ ", station=" + station + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Front getFront() {
		return front;
	}

	public void setFront(Front front) {
		this.front = front;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	
}
