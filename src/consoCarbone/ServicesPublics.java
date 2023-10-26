package consoCarbone;

/**
 * Classe de Singleton représentant les services publics.
 */
public class ServicesPublics {

	/**
	 * Constructeur privé de la classe ServicesPublics. La présence d'un
	 * constructeur privé supprime le constructeur public par défaut.
	 */
	private ServicesPublics() {

	}

	/**
	 * Classe interne chargée de la création de l'instance de ServicesPublics.
	 * SingletonHolder est chargé à la première exécution de Singleton.getInstance()
	 * ou au premier accès à SingletonHolder.instance, pas avant.
	 */
	private static class SingletonHolder {
		private final static ServicesPublics instance = new ServicesPublics();
	}

	/**
	 * Méthode permettant d'obtenir l'instance unique de ServicesPublics.
	 * 
	 * @return l'instance unique de ServicesPublics
	 */
	public static ServicesPublics getInstance() {
		return SingletonHolder.instance;
	}
}