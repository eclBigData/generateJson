package com.mobility.generateJson.object;

import java.util.List;

public class PredictionStation {
	private Integer id;
	private Integer localisationId;
	private Boolean prediction;
	private List<PredictionDetail> details;

	public PredictionStation() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLocalisationId() {
		return localisationId;
	}

	public void setLocalisationId(Integer localisationId) {
		this.localisationId = localisationId;
	}

	public Boolean getPrediction() {
		return prediction;
	}

	public void setPrediction(Boolean prediction) {
		this.prediction = prediction;
	}

	public List<PredictionDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PredictionDetail> details) {
		this.details = details;
	}

}
