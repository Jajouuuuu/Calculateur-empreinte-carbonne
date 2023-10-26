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
 * Classe contrôleur pour la vue de saisie des informations sur le logement.
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
	 * Méthode appelée lorsque la vue est initialisée. Elle remplit le menu
	 * déroulant de la classe énergétique avec les valeurs de l'énumération CE.
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
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton "Retour".
	 * 
	 * @param event l'événement de souris qui a déclenché l'appel de cette méthode
	 */
	@FXML
	public void onClickRetour(MouseEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("Start.fxml");
	}

	/**
	 * Méthode appelée lorsque l'utilisateur clique sur le bouton "Suivant".
	 * 
	 * @param event l'événement de souris qui a déclenché l'appel de cette méthode
	 */
	@FXML
	public void onClickSuivant(MouseEvent event) {
		try {
			// Vérifie que le champ de superficie n'est pas vide
			if (superficieField.getText().trim().isEmpty()) {
				throw new IllegalArgumentException("Le champ de superficie ne peut pas être vide");
			}

			// Vérifie que la valeur dans le champ de superficie est bien un entier
			int superficie = Integer.parseInt(superficieField.getText());

			CE classeEnergetique = CE.valueOf(classeEnergetiqueButton.getText());

			// Vérifie que l'une des checkboxes est cochée
			if (!villeCheckbox.isSelected() && !campagneCheckbox.isSelected()) {
				throw new IllegalArgumentException("Vous devez sélectionner une ville ou une campagne");
			}

			Logement logement;
			// Si la checkbox ville est cochée, créez une instance de LogementVille
			if (villeCheckbox.isSelected()) {
				logement = new LogementVille(superficie, classeEnergetique);
			} else { // Sinon, créez une instance de LogementCampagne
				logement = new LogementCampagne(superficie, classeEnergetique);
			}

			String fileName = "base.json";
			logement.saveToFile(fileName);

			app.setModeConfiguration(true);
			app.setFenetre("Alimentation.fxml");
		} catch (NumberFormatException e) {
			// Affiche un message d'erreur si une exception de type NumberFormatException
			// est levée
			app.afficherErreur("Erreur de format de données",
					"Le champ de superficie doit être rempli avec un entier valide");
		} catch (IllegalArgumentException e) {
			// Affiche un message d'erreur si une exception de type IllegalArgumentException
			// est levée
			app.afficherErreur("Erreur de saisie de données", e.getMessage());
		} catch (IOException e) {
			System.out.println("Une erreur s'est produite lors de la sauvegarde du fichier : " + e.getMessage());
		}
	}
}