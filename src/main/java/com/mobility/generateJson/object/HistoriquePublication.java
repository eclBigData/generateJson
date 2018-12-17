package com.mobility.generateJson.object;

import java.util.ArrayList;
import java.util.List;

public class HistoriquePublication {
	private List<DataPublication> listePublications;

	public HistoriquePublication() {
		listePublications = new ArrayList<DataPublication>();
	}

	public List<DataPublication> getListePublications() {
		return listePublications;
	}

	public void setListePublications(List<DataPublication> listePublications) {
		this.listePublications = listePublications;
	}

}
