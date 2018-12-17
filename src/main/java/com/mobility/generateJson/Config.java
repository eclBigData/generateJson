package com.mobility.generateJson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Config {
	private static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private String userBd;
	private String passBd;
	private String urlBd;

	private String queryMesures;
	private String queryData;

	private int cyclePeriodeEnSecondes;
	private int cyclePasEnMinutes;

	private Date dateDebut;
	private Date dateFin;

	private boolean sortieConsole;
	private boolean sortieWs;
	private String urlWsData;
	private String urlWsHisto;

	private int profondeurHisto;

	public Config(String fichierProperties) throws IOException, ParseException {
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(fichierProperties));

		// BD
		this.userBd = properties.getProperty("bd.user");
		this.passBd = properties.getProperty("bd.pwd");
		this.urlBd = properties.getProperty("bd.url");

		// Query
		this.queryMesures = properties.getProperty("query.mesures");
		this.queryData = properties.getProperty("query.data");
		this.queryData = this.queryData.replaceAll("#queryMesure#", this.queryMesures);

		// Cycle
		this.cyclePeriodeEnSecondes = Integer.parseInt(properties.getProperty("cycle.periodeEnSecondes"));
		this.cyclePasEnMinutes = Integer.parseInt(properties.getProperty("cycle.pasEnMinutes"));

		// Dates
		this.dateDebut = df.parse(properties.getProperty("date.debut"));
		try {
			this.dateFin = df.parse(properties.getProperty("date.fin"));
		} catch (Exception e) {
			this.dateFin = null;
		}

		// Sorties
		this.sortieConsole = "1".equals(properties.getProperty("sortie.console"));
		this.sortieWs = "1".equals(properties.getProperty("sortie.ws"));
		this.urlWsData = properties.getProperty("sortie.ws.url.data");
		this.urlWsHisto = properties.getProperty("sortie.ws.url.histo");

		// Histo
		this.profondeurHisto = Integer.parseInt(properties.getProperty("histo.profondeur"));
	}

	public String getUserBd() {
		return userBd;
	}

	public String getPassBd() {
		return passBd;
	}

	public String getUrlBd() {
		return urlBd;
	}

	public String getQueryMesures() {
		return queryMesures;
	}

	public String getQueryData() {
		return queryData;
	}

	public int getCyclePeriodeEnSecondes() {
		return cyclePeriodeEnSecondes;
	}

	public int getCyclePasEnMinutes() {
		return cyclePasEnMinutes;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public boolean isSortieConsole() {
		return sortieConsole;
	}

	public boolean isSortieWs() {
		return sortieWs;
	}

	public String getUrlWsData() {
		return urlWsData;
	}

	public String getUrlWsHisto() {
		return urlWsHisto;
	}

	public int getProfondeurHisto() {
		return profondeurHisto;
	}

}
