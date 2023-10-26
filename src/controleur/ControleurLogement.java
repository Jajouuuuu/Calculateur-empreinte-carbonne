package controleur;

import java.io.IOException;

import consoCarbone.CE;
import consoCarbone.Logement;
import consoCarbone.LogementCampagne;
import consoCarbone.LogementVille;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Classe contr�leur pour la vue de saisie des informations sur le logement.
 */
public class ControleurLogement extends Controleur {

	@FXML
	private TextField superficieField;
	@FXML
	private SplitMenuButton classeEnergetiqueButton;
	@FXML
	private CheckBox villeCheckbox;
	@FXML
	private CheckBox campagneCheckbox;

	/**
	 * M�thode appel�e lorsque la vue est initialis�e. Elle remplit le menu
	 * d�roulant de la classe �nerg�tique avec les valeurs de l'�num�ration CE.
	 */
	@FXML
	private void initialize() {
		try {
			for (CE value : CE.values()) {
				MenuItem menuItem = new MenuItem(value.name());
				menuItem.setOnAction(event -> {
					try {
						classeEnergetiqueButton.setText(value.name());
					} catch (Exception e) {
						System.err.println(e);
					}
				});
				classeEnergetiqueButton.getItems().add(menuItem);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		try {
			classeEnergetiqueButton.setText(CE.values()[0].name());
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * M�thode appel�e lorsque l'utilisateur clique sur le bouton "Retour".
	 * 
	 * @param event l'�v�nement de souris qui a d�clench� l'appel de cette m�thode
	 */
	@FXML
	public void onClickRetour(MouseEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("Start.fxml");
	}

	/**
	 * M�thode appel�e lorsque l'utilisateur clique sur le bouton "Suivant".
	 * 
	 * @param event l'�v�nement de souris qui a d�clench� l'appel de cette m�thode
	 */
	@FXML
	public void onClickSuivant(MouseEvent event) {
		try {
			// V�rifie que le champ de superficie n'est pas vide
			if (superficieField.getText().trim().isEmpty()) {
				throw new IllegalArgumentException("Le champ de superficie ne peut pas �tre vide");
			}

			// V�rifie que la valeur dans le champ de superficie est bien un entier
			int superficie = Integer.parseInt(superficieField.getText());

			CE classeEnergetique = CE.valueOf(classeEnergetiqueButton.getText());

			// V�rifie que l'une des checkboxes est coch�e
			if (!villeCheckbox.isSelected() && !campagneCheckbox.isSelected()) {
				throw new IllegalArgumentException("Vous devez s�lectionner une ville ou une campagne");
			}

			Logement logement;
			// Si la checkbox ville est coch�e, cr�ez une instance de LogementVille
			if (villeCheckbox.isSelected()) {
				logement = new LogementVille(superficie, classeEnergetique);
			} else { // Sinon, cr�ez une instance de LogementCampagne
				logement = new LogementCampagne(superficie, classeEnergetique);
			}

			String fileName = "base.json";
			logement.saveToFile(fileName);

			app.setModeConfiguration(true);
			app.setFenetre("Alimentation.fxml");
		} catch (NumberFormatException e) {
			// Affiche un message d'erreur si une exception de type NumberFormatException
			// est lev�e
			app.afficherErreur("Erreur de format de donn�es",
					"Le champ de superficie doit �tre rempli avec un entier valide");
		} catch (IllegalArgumentException e) {
			// Affiche un message d'erreur si une exception de type IllegalArgumentException
			// est lev�e
			app.afficherErreur("Erreur de saisie de donn�es", e.getMessage());
		} catch (IOException e) {
			System.out.println("Une erreur s'est produite lors de la sauvegarde du fichier : " + e.getMessage());
		}
	}
}