package controleur;

import java.io.IOException;

import consoCarbone.Alimentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Classe contrôleur pour la gestion de l'alimentation dans l'application de
 * suivi de consommation de carbone.
 * 
 * @author Jeanne-Emma Lefèvre & Garance Raynaud
 */
public class ControleurAlimentation extends Controleur {

	@FXML
	private Button suivant;
	@FXML
	private Button retour;
	@FXML
	private TextField viandeField;
	@FXML
	private TextField legumesField;

	/**
	 * Gère l'événement de clic sur le bouton "Retour". Affiche la fenêtre de
	 * démarrage de l'application.
	 * 
	 * @param event l'événement de clic sur le bouton "Retour"
	 */
	@FXML
	void onClickRetour(ActionEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("Logement.fxml");
	}

	/**
	 * Gère l'événement de clic sur le bouton "Suivant". Enregistre les données de
	 * l'alimentation de l'utilisateur dans un fichier de données. Affiche la
	 * fenêtre de bienvenue de l'application en mode de configuration.
	 * 
	 * @param event l'événement de clic sur le bouton "Suivant"
	 */
	@FXML
	void onClickSuivant(ActionEvent event) {
		try {
			String viandeString = viandeField.getText();
			String legumesString = legumesField.getText();

			// Vérifie que les chaînes de caractères ne sont pas vides ou ne contiennent que
			// des espaces
			if (viandeString.trim().isEmpty() || legumesString.trim().isEmpty()) {
				throw new NumberFormatException("Les champs Viande et Légumes doivent être remplis");
			}
			double viande = Double.parseDouble(viandeString);
			double legumes = Double.parseDouble(legumesString);

			// Vérifie que la somme de viande et legumes fait 1
			if (viande + legumes != 1) {
				throw new IllegalArgumentException("La somme de Viande et Légumes doit être égale à 1");
			}
			Alimentation alimentation = new Alimentation(viande, legumes);
			String fileName = "base.json";
			alimentation.saveToFile(fileName);
			app.setModeConfiguration(true);
			app.setFenetre("BienConso.fxml");
		} catch (NumberFormatException e) {
			// Affiche un message d'erreur si une exception de type NumberFormatException
			// est levée
			app.afficherErreur("Erreur de format de données",
					"Les champs Viande et Légumes doivent être remplis avec des nombres décimaux valides");
		} catch (IOException e) {
			// Affiche un message d'erreur si une exception de type IOException est levée
			app.afficherErreur("Erreur d'E/S", "Une erreur est survenue lors de la sauvegarde des données");
		}
	}
}
