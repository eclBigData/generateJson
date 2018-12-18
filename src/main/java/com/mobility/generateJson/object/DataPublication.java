package com.mobility.generateJson.object;

import java.beans.Transient;
import java.util.Collection;
import java.util.Hashtable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DataPublication {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private String date;
	private Hashtable<Integer, Station> stations;
	private Hashtable<Integer, Mesure> hashMesures;

	public DataPublication() {
		stations = new Hashtable<Integer, Station>();
		hashMesures = new Hashtable<Integer, Mesure>();
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

	@Transient
	public Hashtable<Integer, Mesure> getHashMesures() {
		return hashMesures;
	}

	public void razMesures() {
		for (Mesure m : hashMesures.values()) {
			m.raz();
		}
	}

	public DataPublication clone() {
		DataPublication clone = new DataPublication();
		clone.setDate(date);
		for (Integer key : stations.keySet()) {
			Station cloneStation = stations.get(key).clone();
			clone.getTableStations().put(key, cloneStation);
			for (Mesure mes : cloneStation.getMesures()) {
				clone.getHashMesures().put(mes.getId(), mes);
			}
		}
		return clone;
	}
}
