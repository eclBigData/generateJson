package com.mobility.generateJson.object;

public class PredictionResultat {
	private Integer localisationId;
	private boolean svm;
	private boolean arima;

	public Integer getLocalisationId() {
		return localisationId;
	}

	public void setLocalisationId(Integer localisationId) {
		this.localisationId = localisationId;
	}

	public boolean isSvm() {
		return svm;
	}

	public void setSvm(boolean svm) {
		this.svm = svm;
	}

	public boolean isArima() {
		return arima;
	}

	public void setArima(boolean arima) {
		this.arima = arima;
	}

}
