package consoCarbone;

/**
 * Classe représentant un logement à la campagne.
 */
public class LogementCampagne extends Logement {

	/**
	 * Constructeur de la classe LogementCampagne.
	 * 
	 * @param superficie la superficie du logement
	 * @param ce         la classe énergétique du logement
	 */
	public LogementCampagne(int superficie, CE ce) {
		super();
		this.localisation = "campagne";
		this.superficie = superficie;
		this.ce = ce;
		this.impact = this.calculerImpact();
	}

	/**
	 * Calcule l'impact de la consommation de carbone du logement à la campagne.
	 * 
	 * @return l'impact de la consommation de carbone du logement à la campagne
	 */
	@Override
	public double calculerImpact() {
		// Le 0.29 correspond à la contribution d'emission pour un logement à la
		// campagne
		return this.ce.getAlpha_ce() * this.superficie * 0.29;
	}
}