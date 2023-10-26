package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Classe contr�leur de l'�cran de d�marrage de l'application.
 */
public class ControleurStart extends Controleur {

	@FXML
	public Button calcul;

	/**
	 * G�re le clic sur le bouton "Calculer" et affiche la fen�tre de configuration
	 * de logement.
	 * 
	 * @param event l'�v�nement de clic sur le bouton
	 */
	@FXML
	public void onClickCalcul(MouseEvent event) {
		app.setModeConfiguration(true);
		app.setFenetre("Logement.fxml");
	}
}