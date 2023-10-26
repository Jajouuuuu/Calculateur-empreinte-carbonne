package controleur;

import vue.App;

/**
 * Classe abstraite représentant un contrôleur dans l'application.
 * 
 * @author Jeanne-Emma Lefèvre & Garance Raynaud
 */
public abstract class Controleur {
	/** L'application associée au contrôleur */
	protected App app;

	/**
	 * Constructeur par défaut de la classe Controleur. Initialise l'application
	 * associée au contrôleur.
	 */
	public Controleur() {
		app = App.getInstance();
	}
}
