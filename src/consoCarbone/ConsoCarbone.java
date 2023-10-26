package consoCarbone;

import java.io.IOException;

/**
 * Classe représentant un poste de consommation de carbone.
 */
public abstract class ConsoCarbone implements Comparable<ConsoCarbone> {

	/** Impact de la consommation de carbone */
	protected double impact;

	/** Identifiant unique du poste de consommation de carbone */
	private int id;

	/** Générateur d'identifiants */
	private GenerateurID gen = GenerateurID.getInstance();

	/** Type de poste de consommation de carbone */
	private String typePoste;

	/**
	 * Modifie le type de poste de consommation de carbone.
	 * 
	 * @param typePoste le nouveau type de poste de consommation de carbone
	 */
	public void setTypePoste(String typePoste) {
		this.typePoste = typePoste;
	}

	/**
	 * Modifie l'impact de la consommation de carbone.
	 * 
	 * @param impact le nouvel impact de la consommation de carbone
	 * @throws IllegalArgumentException si l'impact est négatif
	 */
	public void setImpact(double impact) {
		try {
			if (impact < 0) {
				throw new IllegalArgumentException("L'impact doit être positif");
			}
			this.impact = impact;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Constructeur de la classe ConsoCarbone.
	 */
	public ConsoCarbone() {
		super();
		this.id = gen.getID();
	}

	/**
	 * Sauvegarde l'objet dans un fichier.
	 * 
	 * @param fileName le nom du fichier où sauvegarder l'objet
	 * @throws IOException si une erreur d'entrée/sortie se produit
	 */
	public abstract void saveToFile(String fileName) throws IOException;

	/**
	 * Calcule l'impact de la consommation de carbone.
	 * 
	 * @return l'impact de la consommation de carbone
	 */
	public abstract double calculerImpact();

	/**
	 * Crée un objet avec un impact spécifié.
	 * 
	 * @param i l'impact de la consommation de carbone de l'objet à créer
	 */
	public abstract void createObject(double i);

	/**
	 * Compare deux postes de consommation de carbone selon leur impact.
	 * 
	 * @param conso le poste de consommation de carbone à comparer
	 * @return 1 si l'impact de ce poste est supérieur à celui de conso, -1 si
	 *         l'impact de ce poste est inférieur à celui de conso, 0 si les impacts
	 *         sont égaux
	 * @throws Exception si une exception est levée lors du calcul de l'impact
	 */
	public int compareTo(ConsoCarbone conso) {
		try {
			if (this.calculerImpact() > conso.calculerImpact()) {
				return 1;
			} else if (this.calculerImpact() < conso.calculerImpact()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	/**
	 * Retourne l'impact de la consommation de carbone.
	 * 
	 * @return l'impact de la consommation de carbone
	 */
	public double getImpact() {
		return impact;
	}

	/**
	 * Retourne une chaîne de caractères représentant le poste de consommation de
	 * carbone. La chaîne contient l'identifiant du poste, son type et son impact.
	 * 
	 * @return une chaîne de caractères représentant le poste de consommation de
	 *         carbone
	 */
	@Override
	public String toString() {
		return "Poste de consommation de carbone ID : " + this.id + " - " + this.typePoste + " impact : " + this.impact;
	}
}
