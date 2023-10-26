package consoCarbone;

/**
 * Classe repr�sentant un logement en ville.
 */
public class LogementVille extends Logement {

	/**
	 * Constructeur de la classe LogementVille.
	 * 
	 * @param superficie la superficie du logement
	 * @param ce         la classe �nerg�tique du logement
	 */
	public LogementVille(int superficie, CE ce) {
		super();
		this.localisation = "ville";
		this.superficie = superficie;
		this.ce = ce;
		this.impact = this.calculerImpact();
	}

	/**
	 * Calcule l'impact de la consommation de carbone du logement en ville.
	 * 
	 * @return l'impact de la consommation de carbone du logement en ville
	 */
	@Override
	public double calculerImpact() {
		// Le 0.33 correspond � la contribution d'emission pour un logement � la ville
		return this.ce.getAlpha_ce() * this.superficie * 0.33;
	}
}