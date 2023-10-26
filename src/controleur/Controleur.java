package controleur;

import vue.App;

/**
 * Classe abstraite repr�sentant un contr�leur dans l'application.
 * 
 * @author Jeanne-Emma Lef�vre & Garance Raynaud
 */
public abstract class Controleur {
	/** L'application associ�e au contr�leur */
	protected App app;

	/**
	 * Constructeur par d�faut de la classe Controleur. Initialise l'application
	 * associ�e au contr�leur.
	 */
	public Controleur() {
		app = App.getInstance();
	}
}
