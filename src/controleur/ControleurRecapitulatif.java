package controleur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import consoCarbone.Alimentation;
import consoCarbone.BienConso;
import consoCarbone.CE;
import consoCarbone.ConsoCarbone;
import consoCarbone.Logement;
import consoCarbone.LogementCampagne;
import consoCarbone.LogementVille;
import consoCarbone.Taille;
import consoCarbone.Transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe contrôleur du récapitulatif des émissions de carbone de l'utilisateur.
 */
public class ControleurRecapitulatif extends Controleur {

	@FXML
	private Stage currentStage;
	@FXML
	private PieChart chart;
	@FXML
	private Button fin;

	/**
	 * Ferme la fenêtre de l'application lorsqu'on appuie sur le bouton Fin.
	 * 
	 * @param event L'événement déclenché lorsqu'on appuie sur le bouton Fin.
	 */
	@FXML
	public void onClickFin(ActionEvent event) {
		Stage stage = (Stage) fin.getScene().getWindow();
		stage.close();
	}

	/**
	 * Initialise la scène et le diagramme à secteurs du récapitulatif des émissions
	 * de carbone de l'utilisateur.
	 * 
	 * @throws InterruptedException
	 */
	@FXML
	private void initialize() throws InterruptedException {
		try {
			// Lire le fichier JSON et parser le contenu en objet JsonObject
			String json = new String(Files.readAllBytes(Paths.get("base.json")));
			Gson gson = new Gson();
			JsonObject obj = gson.fromJson(json, JsonObject.class);

			// Créer une instance de la classe Logement
			Logement logement;
			if (obj.get("localisation").getAsString().contentEquals("ville")) {
				logement = new LogementVille(obj.get("superficie").getAsInt(), CE.valueOf(obj.get("CE").getAsString()));
				System.out.println(logement.getImpact());
			} else {
				logement = new LogementCampagne(obj.get("superficie").getAsInt(),
						CE.valueOf(obj.get("CE").getAsString()));
				System.out.println(logement.getImpact());
			}

			// Créer une instance de la classe Alimentation
			Alimentation alimentation = new Alimentation(obj.get("txBoeuf").getAsDouble(),
					obj.get("txVege").getAsDouble());

			// Créer une instance de la classe BienConso
			BienConso bienConso = new BienConso(obj.get("montant").getAsDouble());

			// Créer une instance de la classe Transport
			Transport transport;
			if (obj.get("possede").getAsBoolean() == false) {
				transport = new Transport(obj.get("possede").getAsBoolean());
			} else {
				transport = new Transport(obj.get("possede").getAsBoolean(),
						Taille.valueOf(obj.get("taille").getAsString()), obj.get("kilomAnnee").getAsInt(),
						obj.get("amortissement").getAsInt());
			}

			ArrayList<ConsoCarbone> consos = new ArrayList<ConsoCarbone>();
			consos.add(logement);
			consos.add(alimentation);
			consos.add(bienConso);
			consos.add(transport);

			// Créer une liste observable à partir de la liste ArrayList.
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

			for (ConsoCarbone value : consos) {
				value.createObject(value.getImpact());
				System.out.println(value);
				System.out.println(value.getImpact());
				pieChartData.add(new PieChart.Data(value.toString(), value.getImpact()));
			}
			;

			// Créer une nouvelle scène avec la pie chart
			this.chart = new PieChart(pieChartData);
			this.chart.setTitle("Récapitulatif de vos consommations");
			chart.setLegendVisible(true);
			VBox box = new VBox(this.chart);
			Scene scene = new Scene(box);
			currentStage = new Stage();
			currentStage.setTitle("Recapitulatif graphique");
			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			System.err.println(e);
		} catch (IllegalArgumentException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
