package com.mobility.generateJson.object;

import java.util.ArrayList;
import java.util.List;

public class Prediction {
	private String predictionDate;
	private List<PredictionStation> stations;

	public Prediction() {
		stations = new ArrayList<PredictionStation>();
	}

	public String getPredictionDate() {
		return predictionDate;
	}

	public void setPredictionDate(String predictionDate) {
		this.predictionDate = predictionDate;
	}

	public List<PredictionStation> getStations() {
		return stations;
	}

	public void setStations(List<PredictionStation> stations) {
		this.stations = stations;
	}

}
