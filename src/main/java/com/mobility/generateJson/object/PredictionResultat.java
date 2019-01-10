package com.mobility.generateJson.object;

import java.util.ArrayList;
import java.util.List;

public class PredictionResultat {
	private Integer localisationId;
	private Boolean result;
	private List<PredictionDetail> detail;

	public PredictionResultat() {
		detail = new ArrayList<PredictionDetail>();
	}

	public Integer getLocalisationId() {
		return localisationId;
	}

	public void setLocalisationId(Integer localisationId) {
		this.localisationId = localisationId;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public List<PredictionDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<PredictionDetail> detail) {
		this.detail = detail;
	}

}
