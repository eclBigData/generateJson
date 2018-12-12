package com.mobility.generateJson.object;

import java.beans.Transient;
import java.util.Collection;
import java.util.Hashtable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataPublication {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private String date;
	private Hashtable<Integer, Station> stations;

	public DataPublication() {
		stations = new Hashtable<Integer, Station>();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Collection<Station> getStations() {
		return stations.values();
	}

	@Transient
	public Hashtable<Integer, Station> getTableStations() {
		return stations;
	}

}
