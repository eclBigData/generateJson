package com.mobility.generateJson.object;

public class Mesure {
	private Integer id;
	private Integer localisationId;
	private Integer qualif;
	private Integer valeur;
	private Integer type;

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

	public Integer getQualif() {
		return qualif;
	}

	public void setQualif(Integer qualif) {
		this.qualif = qualif;
	}

	public Integer getValeur() {
		return valeur;
	}

	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void raz() {
		qualif = 2;
		valeur = -1;
	}

	public Mesure clone() {
		Mesure clone = new Mesure();

		clone.setId(id);
		clone.setLocalisationId(localisationId);
		clone.setQualif(qualif);
		clone.setValeur(valeur);
		clone.setType(type);
		clone.setId(id);

		return clone;
	}
}
