package consoCarbone;

/**
 * Classe de Singleton pour générer des identifiants uniques.
 */
public class GenerateurID {

	/** Instance courante de la classe */
	private static GenerateurID instance;

	/** Compteur d'identifiants */
	private int compteur;

	/**
	 * Constructeur de la classe GenerateurID.
	 */
	private GenerateurID() {
		compteur = 0;
	}

	/**
	 * Retourne l'instance courante de la classe.
	 * 
	 * @return l'instance courante de la classe
	 */
	public static GenerateurID getInstance() {
		if (instance == null)
			instance = new GenerateurID();
		return instance;
	}

	/**
	 * Retourne un identifiant unique.
	 * 
	 * @return l'identifiant unique
	 */
	public int getID() {
		compteur++;
		return compteur;
	}
}