package consoCarbone;

/**
 * Enum�ration des classes �nerg�tiques d'un logement.
 */
public enum CE {
	/** Classe �nerg�tique A */
	A(0.005),
	/** Classe �nerg�tique B */
	B(0.01),
	/** Classe �nerg�tique C */
	C(0.02),
	/** Classe �nerg�tique D */
	D(0.035),
	/** Classe �nerg�tique E */
	E(0.055),
	/** Classe �nerg�tique F */
	F(0.08),
	/** Classe �nerg�tique G */
	G(0.1);

	/** Coefficient attribu� � une classe �nerg�tique */
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
	 * Constructeur de l'�num�ration CE
	 * 
	 * @param alpha_ce le coefficient des classes �nerg�tiques
	 */
	private CE(double alpha_ce) {
		this.alpha_ce = alpha_ce;
	}
}