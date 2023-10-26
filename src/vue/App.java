package vue;

import java.io.IOException;
import java.util.Optional;

import controleur.Controleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Classe principale de l'application, qui g�re l'affichage de chaque fen�tre de
 * l'application.
 */
public class App extends Application {

	private static App instance; // Instance de la classe App
	private static Stage primaryStage; // Stage principal de l'application
	private Controleur controleurCourant; // Contr�leur courant de l'application
	private boolean modeConfiguration; // Bool�en indiquant si l'application est en mode de configuration ou non

	/**
	 * Modifie la fen�tre actuelle de l'application en chargeant une nouvelle
	 * fen�tre � partir d'un fichier fxml.
	 * 
	 * @param fxmlName Le nom du fichier fxml � charger.
	 * @return Le contr�leur de la nouvelle fen�tre charg�e.
	 */
	public Controleur setFenetre(String fxmlName) {
		Controleur c = null;
		try {
			// Charge la nouvelle fen�tre � partir du fichier fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
			Parent root = loader.load();
			c = ((Controleur) loader.getController());
			// Met � jour la sc�ne et l'affiche
			primaryStage.setScene(new Scene(root));
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Met � jour le contr�leur courant de l'application
		this.setControleurCourant(c);
		return c;
	}

	/**
	 * M�thode de d�marrage de l'application, qui initialise l'instance de la classe
	 * App et le stage principal de l'application, d�finit le titre de la fen�tre et
	 * d�sactive la possibilit� de redimensionner la fen�tre, puis affiche la
	 * fen�tre de d�marrage de l'application.
	 * 
	 * @param primaryStage Le stage principal de l'application.
	 * @throws IOException Si une erreur d'entr�e/sortie se produit lors du
	 *                     chargement de la fen�tre de d�marrage.
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		instance = this; // Initialise l'instance de la classe App
		this.primaryStage = primaryStage; // Initialise le stage principal de l'application
		primaryStage.setTitle("Calculateur d'empreinte carbone"); // D�finit le titre de la fen�tre
		primaryStage.setResizable(false); // D�sactive la possibilit� de redimensionner la fen�tre
		primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
		setFenetre("Start.fxml");
	}

	/**
	 * D�finit le mode de configuration de l'application.
	 * 
	 * @param mode Le nouveau mode de configuration de l'application.
	 */
	public void setModeConfiguration(boolean mode) {
		this.modeConfiguration = mode;
	}

	/**
	 * Indique si l'application est en mode de configuration.
	 * 
	 * @return true si l'application est en mode de configuration, false sinon.
	 */
	public boolean isModeConfiguration() {
		return modeConfiguration;
	}

	/**
	 * Obtient le stage principal de l'application.
	 * 
	 * @return Le stage principal de l'application.
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Gestionnaire d'�v�nement qui g�re la fermeture de la fen�tre. Si
	 * l'application est en mode de configuration, affiche une bo�te de dialogue de
	 * confirmation avant de fermer la fen�tre.
	 * 
	 * @param event L'�v�nement de fermeture de la fen�tre.
	 */
	private void closeWindowEvent(WindowEvent event) {
		if (isModeConfiguration()) {
			// Affiche une bo�te de dialogue de confirmation avant de fermer la fen�tre
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Quitter l'application");
			alert.setContentText("Vous �tes sur le point de quiter l'application, voulez-vous continuer ?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.CANCEL) {
				event.consume();
			}
		}
	}

	/**
	 * M�thode principale de l'application, qui lance l'application.
	 * 
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Affiche un message d'erreur � l'utilisateur.
	 * 
	 * @param titre   Le titre du message d'erreur.
	 * @param message Le message d'erreur � afficher.
	 */
	public void afficherErreur(String titre, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titre);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Obtient le contr�leur courant de l'application.
	 * 
	 * @return Le contr�leur courant de l'application.
	 */
	public Controleur getControleurCourant() {
		return controleurCourant;
	}

	/**
	 * D�finit le contr�leur courant de l'application.
	 * 
	 * @param controleurCourant Le nouveau contr�leur courant de l'application.
	 */
	public void setControleurCourant(Controleur controleurCourant) {
		this.controleurCourant = controleurCourant;
	}

	/**
	 * Obtient l'instance de la classe App.
	 * 
	 * @return L'instance de la classe App.
	 */
	public static App getInstance() {
		return instance;
	}

}
