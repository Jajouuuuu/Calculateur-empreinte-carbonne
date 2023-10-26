package controleur;

import java.io.IOException;

import consoCarbone.BienConso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Classe contr�leur pour la vue de saisie des d�penses de biens de
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
	 * M�thode appel�e lorsque l'utilisateur clique sur le bouton "Retour".
	 * 
	 * @param event l'�v�nement de souris qui a d�clench� l'appel de cette m�thode
	 */
	@FXML
	void onClickRetour(MouseEvent event) {
		app.setFenetre("Alimentation.fxml");
	}

	/**
	 * M�thode appel�e lorsque l'utilisateur clique sur le bouton "Suivant".
	 * 
	 * @param event l'�v�nement d'action qui a d�clench� l'appel de cette m�thode
	 */
	@FXML
	void onClickSuivant(ActionEvent event) {
		try {
			String montantString = depenses.getText();
			// V�rifie que la cha�ne de caract�res n'est pas vide ou ne contient que des
			// espaces
			if (montantString.trim().isEmpty()) {
				throw new NumberFormatException("Le champ D�penses doit �tre rempli");
			}
			// V�rifie que la cha�ne de caract�res peut �tre convertie en type double
			try {
				Double.parseDouble(montantString);
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Le champ D�penses doit �tre rempli avec un nombre d�cimal valide");
			}
			double montant = Double.parseDouble(montantString);
			BienConso bienconso = new BienConso(montant);
			String fileName = "base.json";
			bienconso.saveToFile(fileName);
			app.setModeConfiguration(true);
			app.setFenetre("Transport.fxml");
		} catch (NumberFormatException e) {
			// Affiche un message d'erreur si une exception de type NumberFormatException
			// est lev�e
			app.afficherErreur("Erreur de format de donn�es",
					"Le champ D�penses doit �tre rempli avec un nombre d�cimal valide");
		} catch (IOException e) {
			// Affiche un message d'erreur si une exception de type IOException est lev�e
			app.afficherErreur("Erreur d'E/S", "Une erreur est survenue lors de la sauvegarde des donn�es");
		}
	}
}
