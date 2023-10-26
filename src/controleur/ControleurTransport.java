package controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import consoCarbone.Taille;
import consoCarbone.Transport;

/**
 * Classe contrôleur de la configuration des données de transport de
 * l'utilisateur.
 */
public class ControleurTransport extends Controleur {

	@FXML
	private TextField carKilometersField;
	@FXML
	private TextField carDurationField;
	@FXML
	private RadioButton hasCarYesButton;
	@FXML
	private RadioButton hasCarNoButton;
	@FXML
	private SplitMenuButton carSizeButton;
	@FXML
	private Button suivant;
	@FXML
	private Button retour;

	/**
	 * Initialise la scène de configuration des données de transport de
	 * l'utilisateur.
	 */
	@FXML
	private void initialize() {
		try {
			for (Taille value : Taille.values()) {
				MenuItem menuItem = new MenuItem(value.name());
				menuItem.setOnAction(event -> {
					carSizeButton.setText(value.name());
				});
				carSizeButton.getItems().add(menuItem);
			}
			carSizeButton.setText(Taille.values()[0].name());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton "Retour".
	 * 
	 * @param event l'événement de souris qui a déclenché l'appel de cette méthode
	 */
	@FXML
	public void onClickRetour(ActionEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("BienConso.fxml");
	}

	/**
	 * Gère le clic sur le bouton "Suivant" et enregistre les données de transport
	 * de l'utilisateur.
	 * 
	 * @param event l'événement de clic sur le bouton
	 */
	@FXML
	public void onClickSuivant(ActionEvent event) {
		try {
			// Vérifier que l'option hasCarYesButton est sélectionnée et que l'option
			// hasCarNoButton n'est pas sélectionnée
			if (hasCarYesButton.isSelected() && !hasCarNoButton.isSelected()) {
				// Récupérer les valeurs des champs de saisie
				Taille taille = Taille.valueOf(carSizeButton.getText());
				int kilom_annee = Integer.parseInt(carKilometersField.getText());
				int amortissement = Integer.parseInt(carDurationField.getText());
				// Créer une instance de la classe Transport
				Transport transport = new Transport(true, taille, kilom_annee, amortissement);
				// Enregistrer l'objet dans un fichier
				String fileName = "base.json";
				transport.saveToFile(fileName);
			} else if (hasCarNoButton.isSelected()) {
				// Si l'option hasCarNoButton est sélectionnée, enregistrer les données de
				// transport avec des valeurs par défaut
				Transport transport = new Transport(false);
				String fileName = "base.json";
				transport.saveToFile(fileName);
			} else {
				// Afficher un message d'erreur si aucune option n'est sélectionnée
				System.out.println(
						"Vous devez sélectionner l'option 'Oui' ou 'Non' pour pouvoir enregistrer les données de transport.");
			}
			// Changer de fenêtre
			app.setModeConfiguration(true);
			app.setFenetre("Recap.fxml");
		} catch (NumberFormatException e) {
			System.err.println(e);
		} catch (IllegalArgumentException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}