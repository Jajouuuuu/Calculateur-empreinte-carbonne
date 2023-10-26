package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Classe contrôleur de l'écran de démarrage de l'application.
 */
public class ControleurStart extends Controleur {

	@FXML
	public Button calcul;

	/**
	 * Gère le clic sur le bouton "Calculer" et affiche la fenêtre de configuration
	 * de logement.
	 * 
	 * @param event l'événement de clic sur le bouton
	 */
	@FXML
	public void onClickCalcul(MouseEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("Logement.fxml");
	}
}