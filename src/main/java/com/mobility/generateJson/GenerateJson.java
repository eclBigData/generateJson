package com.mobility.generateJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mobility.generateJson.object.DataPublication;
import com.mobility.generateJson.object.HistoriquePublication;
import com.mobility.generateJson.object.Mesure;
import com.mobility.generateJson.object.Station;

public class GenerateJson {
	private static GenerateJson generateJson;

	private Config config;
	private Connection connection = null;

	private DataPublication publication = new DataPublication();
	private HistoriquePublication historiquePublication = new HistoriquePublication();

	private static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	private void init() throws ClassNotFoundException, SQLException {
		try {
			config = new Config("/generateJson.properties");
		} catch (Exception e) {
			System.err.println("Impossible de charger le fichier generateJson.properties");
			System.exit(0);
		}
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(config.getUrlBd(), config.getUserBd(), config.getPassBd());

		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select * " + config.getQueryMesures());
		while (rs.next()) {
			Mesure mesure = new Mesure();
			mesure.setId(rs.getInt("id"));
			mesure.setLocalisationId(rs.getInt("localisation_id"));
			mesure.setType(rs.getInt("typemesure_id"));
			Integer stationId = rs.getInt("station_id");
			publication.getHashMesures().put(mesure.getId(), mesure);

			Station station = null;
			if (publication.getTableStations().containsKey(stationId)) {
				station = publication.getTableStations().get(stationId);
			} else {
				station = new Station();
				station.setId(stationId);
				publication.getTableStations().put(stationId, station);
			}
			station.getMesures().add(mesure);
		}
		rs.close();
		stmt.close();
	}

	private void majMesures(Date datemaj, DataPublication publicationToMaj) throws SQLException {
		publicationToMaj.setDate(df.format(datemaj));
		publicationToMaj.razMesures();

		// Récupération des mesures actuelles
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select * " + config.getQueryData().replaceAll("#date#", df.format(datemaj)));
		while (rs.next()) {
			Integer idMesure = rs.getInt("mesure_mesure_id");
			if (publicationToMaj.getHashMesures().containsKey(idMesure)) {
				Mesure mesure = publicationToMaj.getHashMesures().get(idMesure);
				mesure.setQualif(rs.getInt("qualif"));
				mesure.setValeur(rs.getInt("valeur"));
			}
		}
		rs.close();
		stmt.close();
	}

	private void genereJson(DataPublication publicationToMaj) throws IOException {
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(publicationToMaj);

		if (config.isSortieConsole()) {
			System.out.println(json);
		}

		if (config.isSortieWs()) {
			try {
				URL url = new URL(config.getUrlWsData());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				OutputStream os = conn.getOutputStream();
				os.write(json.getBytes());
				os.flush();
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					System.err.println("Failed : HTTP error code : " + conn.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
				conn.disconnect();
			} catch (Exception e) {
				System.err.println("Erreur dans l'envoi des données : " + e);
			}
		}
	}

	private void genereHistorique() throws SQLException, IOException {
		Calendar c = Calendar.getInstance();
		for (int i = config.getProfondeurHisto(); i > 0; i--) {
			c.setTime(config.getDateDebut());
			c.add(Calendar.MINUTE, -1 * i * config.getCyclePasEnMinutes());
			DataPublication publicationHisto = publication.clone();
			majMesures(c.getTime(), publicationHisto);
			historiquePublication.getListePublications().add(publicationHisto);
		}
		genereJsonHistorique();
	}

	private void genereJsonHistorique() throws IOException {
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(historiquePublication);

		if (config.isSortieConsole()) {
			System.out.println(json);
		}

		if (config.isSortieWs()) {
			try {
				URL url = new URL(config.getUrlWsHisto());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				OutputStream os = conn.getOutputStream();
				os.write(json.getBytes());
				os.flush();
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					System.err.println("Failed : HTTP error code : " + conn.getResponseCode());
				}
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
				conn.disconnect();
			} catch (Exception e) {
				System.err.println("Erreur dans l'envoi des données : " + e);
			}
		}
	}

	private void run() throws InterruptedException, SQLException, IOException {
		Date date = config.getDateDebut();

		for (;;) {
			generateJson.majMesures(date, publication);
			generateJson.genereJson(publication);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MINUTE, config.getCyclePasEnMinutes());
			date = c.getTime();
			if ((config.getDateFin() != null) && date.after(config.getDateFin())) {
				break;
			}
			Thread.sleep(config.getCyclePeriodeEnSecondes() * 1000);
		}
	}

	private void end() throws SQLException {
		connection.close();
	}

	public static void main(String[] args) {
		generateJson = new GenerateJson();
		try {
			generateJson.init();
			generateJson.genereHistorique();
			generateJson.run();
			generateJson.end();
		} catch (Exception e) {
			System.err.println("Exception " + e);
		}
	}
}
