package controleur;

import java.io.IOException;

import consoCarbone.Alimentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Classe contr�leur pour la gestion de l'alimentation dans l'application de
 * suivi de consommation de carbone.
 * 
 * @author Jeanne-Emma Lef�vre & Garance Raynaud
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
	 * G�re l'�v�nement de clic sur le bouton "Retour". Affiche la fen�tre de
	 * d�marrage de l'application.
	 * 
	 * @param event l'�v�nement de clic sur le bouton "Retour"
	 */
	@FXML
	void onClickRetour(ActionEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("Logement.fxml");
	}

	/**
	 * G�re l'�v�nement de clic sur le bouton "Suivant". Enregistre les donn�es de
	 * l'alimentation de l'utilisateur dans un fichier de donn�es. Affiche la
	 * fen�tre de bienvenue de l'application en mode de configuration.
	 * 
	 * @param event l'�v�nement de clic sur le bouton "Suivant"
	 */
	@FXML
	void onClickSuivant(ActionEvent event) {
		try {
			String viandeString = viandeField.getText();
			String legumesString = legumesField.getText();

			// V�rifie que les cha�nes de caract�res ne sont pas vides ou ne contiennent que
			// des espaces
			if (viandeString.trim().isEmpty() || legumesString.trim().isEmpty()) {
				throw new NumberFormatException("Les champs Viande et L�gumes doivent �tre remplis");
			}
			double viande = Double.parseDouble(viandeString);
			double legumes = Double.parseDouble(legumesString);

			// V�rifie que la somme de viande et legumes fait 1
			if (viande + legumes != 1) {
				throw new IllegalArgumentException("La somme de Viande et L�gumes doit �tre �gale � 1");
			}
			Alimentation alimentation = new Alimentation(viande, legumes);
			String fileName = "base.json";
			alimentation.saveToFile(fileName);
			app.setModeConfiguration(true);
			app.setFenetre("BienConso.fxml");
		} catch (NumberFormatException e) {
			// Affiche un message d'erreur si une exception de type NumberFormatException
			// est lev�e
			app.afficherErreur("Erreur de format de donn�es",
					"Les champs Viande et L�gumes doivent �tre remplis avec des nombres d�cimaux valides");
		} catch (IOException e) {
			// Affiche un message d'erreur si une exception de type IOException est lev�e
			app.afficherErreur("Erreur d'E/S", "Une erreur est survenue lors de la sauvegarde des donn�es");
		}
	}
}
