package com.mobility.generateJson.object;

import java.util.ArrayList;
import java.util.List;

public class Station {
	private Integer id;
	private List<Mesure> mesures;

	public Station() {
		mesures = new ArrayList<Mesure>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Mesure> getMesures() {
		return mesures;
	}

	public void setMesures(List<Mesure> mesures) {
		this.mesures = mesures;
	}

	public Station clone() {
		Station clone = new Station();
		clone.setId(id);
		for (Mesure mesure : mesures) {
			Mesure cloneMesure = mesure.clone();
			clone.getMesures().add(cloneMesure);
		}
		return clone;
	}
}
