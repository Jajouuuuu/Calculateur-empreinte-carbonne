package consoCarbone;

/**
 * Enumération des tailles de voiture.
 */
public enum Taille {
	P("petite", 4.2), G("grande", 19);

	private final String nom;
	private final double emissionProd;

	/**
	 * Constructeur de l'énumération Taille.
	 * 
	 * @param nom          le nom de la taille de voiture
	 * @param emissionProd les émissions lors de la production de la voiture
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
	 * Get les émissions produites lors de la production de la voiture.
	 * 
	 * @return les émissions produites lors de la production de la voiture
	 */
	public double getEmissionProd() {
		return emissionProd;
	}
}