package consoCarbone;

/**
 * Enumération des classes énergétiques d'un logement.
 */
public enum CE {
	/** Classe énergétique A */
	A(0.005),
	/** Classe énergétique B */
	B(0.01),
	/** Classe énergétique C */
	C(0.02),
	/** Classe énergétique D */
	D(0.035),
	/** Classe énergétique E */
	E(0.055),
	/** Classe énergétique F */
	F(0.08),
	/** Classe énergétique G */
	G(0.1);

	/** Coefficient attribué à une classe énergétique */
	private final double alpha_ce;

	/**
	 * Get le coefficient alpha
	 * 
	 * @return alpha_c
	 */
	public double getAlpha_ce() {
		return alpha_ce;
	}

	/**
	 * Constructeur de l'énumération CE
	 * 
	 * @param alpha_ce le coefficient des classes énergétiques
	 */
	private CE(double alpha_ce) {
		this.alpha_ce = alpha_ce;
	}
}