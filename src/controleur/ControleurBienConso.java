package controleur;

import java.io.IOException;

import consoCarbone.BienConso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Classe contrôleur pour la vue de saisie des dépenses de biens de
 * consommation.
 */
public class ControleurBienConso extends Controleur {

	@FXML
	private Button retour;
	@FXML
	private Button suivant;
	@FXML
	private TextField depenses;

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton "Retour".
	 * 
	 * @param event l'événement de souris qui a déclenché l'appel de cette méthode
	 */
	@FXML
	void onClickRetour(MouseEvent event) {
		app.setFenetre("Alimentation.fxml");
	}

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton "Suivant".
	 * 
	 * @param event l'événement d'action qui a déclenché l'appel de cette méthode
	 */
	@FXML
	void onClickSuivant(ActionEvent event) {
		try {
			String montantString = depenses.getText();
			// Vérifie que la chaîne de caractères n'est pas vide ou ne contient que des
			// espaces
			if (montantString.trim().isEmpty()) {
				throw new NumberFormatException("Le champ Dépenses doit être rempli");
			}
			// Vérifie que la chaîne de caractères peut être convertie en type double
			try {
				Double.parseDouble(montantString);
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Le champ Dépenses doit être rempli avec un nombre décimal valide");
			}
			double montant = Double.parseDouble(montantString);
			BienConso bienconso = new BienConso(montant);
			String fileName = "base.json";
			bienconso.saveToFile(fileName);
			app.setModeConfiguration(true);
			app.setFenetre("Transport.fxml");
		} catch (NumberFormatException e) {
			// Affiche un message d'erreur si une exception de type NumberFormatException
			// est levée
			app.afficherErreur("Erreur de format de données",
					"Le champ Dépenses doit être rempli avec un nombre décimal valide");
		} catch (IOException e) {
			// Affiche un message d'erreur si une exception de type IOException est levée
			app.afficherErreur("Erreur d'E/S", "Une erreur est survenue lors de la sauvegarde des données");
		}
	}
}
