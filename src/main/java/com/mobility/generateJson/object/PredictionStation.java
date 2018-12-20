package com.mobility.generateJson.object;

import java.util.ArrayList;
import java.util.List;

public class PredictionStation {
	private Integer id;
	private List<PredictionResultat> results;

	public PredictionStation() {
		results = new ArrayList<PredictionResultat>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PredictionResultat> getResults() {
		return results;
	}

	public void setResults(List<PredictionResultat> results) {
		this.results = results;
	}

}
