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
 * Classe principale de l'application, qui gère l'affichage de chaque fenêtre de
 * l'application.
 */
public class App extends Application {

	private static App instance; // Instance de la classe App
	private static Stage primaryStage; // Stage principal de l'application
	private Controleur controleurCourant; // Contrôleur courant de l'application
	private boolean modeConfiguration; // Booléen indiquant si l'application est en mode de configuration ou non

	/**
	 * Modifie la fenêtre actuelle de l'application en chargeant une nouvelle
	 * fenêtre à partir d'un fichier fxml.
	 * 
	 * @param fxmlName Le nom du fichier fxml à charger.
	 * @return Le contrôleur de la nouvelle fenêtre chargée.
	 */
	public Controleur setFenetre(String fxmlName) {
		Controleur c = null;
		try {
			// Charge la nouvelle fenêtre à partir du fichier fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
			Parent root = loader.load();
			c = ((Controleur) loader.getController());
			// Met à jour la scène et l'affiche
			primaryStage.setScene(new Scene(root));
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Met à jour le contrôleur courant de l'application
		this.setControleurCourant(c);
		return c;
	}

	/**
	 * Méthode de démarrage de l'application, qui initialise l'instance de la classe
	 * App et le stage principal de l'application, définit le titre de la fenêtre et
	 * désactive la possibilité de redimensionner la fenêtre, puis affiche la
	 * fenêtre de démarrage de l'application.
	 * 
	 * @param primaryStage Le stage principal de l'application.
	 * @throws IOException Si une erreur d'entrée/sortie se produit lors du
	 *                     chargement de la fenêtre de démarrage.
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		instance = this; // Initialise l'instance de la classe App
		this.primaryStage = primaryStage; // Initialise le stage principal de l'application
		primaryStage.setTitle("Calculateur d'empreinte carbone"); // Définit le titre de la fenêtre
		primaryStage.setResizable(false); // Désactive la possibilité de redimensionner la fenêtre
		primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
		setFenetre("Start.fxml");
	}

	/**
	 * Définit le mode de configuration de l'application.
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
	 * Gestionnaire d'événement qui gère la fermeture de la fenêtre. Si
	 * l'application est en mode de configuration, affiche une boîte de dialogue de
	 * confirmation avant de fermer la fenêtre.
	 * 
	 * @param event L'événement de fermeture de la fenêtre.
	 */
	private void closeWindowEvent(WindowEvent event) {
		if (isModeConfiguration()) {
			// Affiche une boîte de dialogue de confirmation avant de fermer la fenêtre
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Quitter l'application");
			alert.setContentText("Vous êtes sur le point de quiter l'application, voulez-vous continuer ?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.CANCEL) {
				event.consume();
			}
		}
	}

	/**
	 * Méthode principale de l'application, qui lance l'application.
	 * 
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Affiche un message d'erreur à l'utilisateur.
	 * 
	 * @param titre   Le titre du message d'erreur.
	 * @param message Le message d'erreur à afficher.
	 */
	public void afficherErreur(String titre, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(titre);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Obtient le contrôleur courant de l'application.
	 * 
	 * @return Le contrôleur courant de l'application.
	 */
	public Controleur getControleurCourant() {
		return controleurCourant;
	}

	/**
	 * Définit le contrôleur courant de l'application.
	 * 
	 * @param controleurCourant Le nouveau contrôleur courant de l'application.
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
