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
 * Classe contr�leur de la configuration des donn�es de transport de
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
	 * Initialise la sc�ne de configuration des donn�es de transport de
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
	 * M�thode appel�e lorsque l'utilisateur clique sur le bouton "Retour".
	 * 
	 * @param event l'�v�nement de souris qui a d�clench� l'appel de cette m�thode
	 */
	@FXML
	public void onClickRetour(ActionEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("BienConso.fxml");
	}

	/**
	 * G�re le clic sur le bouton "Suivant" et enregistre les donn�es de transport
	 * de l'utilisateur.
	 * 
	 * @param event l'�v�nement de clic sur le bouton
	 */
	@FXML
	public void onClickSuivant(ActionEvent event) {
		try {
			// V�rifier que l'option hasCarYesButton est s�lectionn�e et que l'option
			// hasCarNoButton n'est pas s�lectionn�e
			if (hasCarYesButton.isSelected() && !hasCarNoButton.isSelected()) {
				// R�cup�rer les valeurs des champs de saisie
				Taille taille = Taille.valueOf(carSizeButton.getText());
				int kilom_annee = Integer.parseInt(carKilometersField.getText());
				int amortissement = Integer.parseInt(carDurationField.getText());
				// Cr�er une instance de la classe Transport
				Transport transport = new Transport(true, taille, kilom_annee, amortissement);
				// Enregistrer l'objet dans un fichier
				String fileName = "base.json";
				transport.saveToFile(fileName);
			} else if (hasCarNoButton.isSelected()) {
				// Si l'option hasCarNoButton est s�lectionn�e, enregistrer les donn�es de
				// transport avec des valeurs par d�faut
				Transport transport = new Transport(false);
				String fileName = "base.json";
				transport.saveToFile(fileName);
			} else {
				// Afficher un message d'erreur si aucune option n'est s�lectionn�e
				System.out.println(
						"Vous devez s�lectionner l'option 'Oui' ou 'Non' pour pouvoir enregistrer les donn�es de transport.");
			}
			// Changer de fen�tre
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