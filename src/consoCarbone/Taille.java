package consoCarbone;

/**
 * Enum�ration des tailles de voiture.
 */
public enum Taille {
	P("petite", 4.2), G("grande", 19);

	private final String nom;
	private final double emissionProd;

	/**
	 * Constructeur de l'�num�ration Taille.
	 * 
	 * @param nom          le nom de la taille de voiture
	 * @param emissionProd les �missions lors de la production de la voiture
	 */
	private Taille(String nom, double emissionProd) {
		this.nom = nom;
		this.emissionProd = emissionProd;
	}

	/**
	 * Get le nom de la taille de voiture.
	 * 
	 * @return le nom de la taille de voiture
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Get les �missions produites lors de la production de la voiture.
	 * 
	 * @return les �missions produites lors de la production de la voiture
	 */
	public double getEmissionProd() {
		return emissionProd;
	}
}