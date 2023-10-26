package consoCarbone;

/**
 * Classe de Singleton repr�sentant les services publics.
 */
public class ServicesPublics {

	/**
	 * Constructeur priv� de la classe ServicesPublics. La pr�sence d'un
	 * constructeur priv� supprime le constructeur public par d�faut.
	 */
	private ServicesPublics() {

	}

	/**
	 * Classe interne charg�e de la cr�ation de l'instance de ServicesPublics.
	 * SingletonHolder est charg� � la premi�re ex�cution de Singleton.getInstance()
	 * ou au premier acc�s � SingletonHolder.instance, pas avant.
	 */
	private static class SingletonHolder {
		private final static ServicesPublics instance = new ServicesPublics();
	}

	/**
	 * M�thode permettant d'obtenir l'instance unique de ServicesPublics.
	 * 
	 * @return l'instance unique de ServicesPublics
	 */
	public static ServicesPublics getInstance() {
		return SingletonHolder.instance;
	}
}