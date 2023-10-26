package consoCarbone;

import java.io.IOException;

/**
 * Classe repr�sentant un poste de consommation de carbone.
 */
public abstract class ConsoCarbone implements Comparable<ConsoCarbone> {

	/** Impact de la consommation de carbone */
	protected double impact;

	/** Identifiant unique du poste de consommation de carbone */
	private int id;

	/** G�n�rateur d'identifiants */
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
	 * @throws IllegalArgumentException si l'impact est n�gatif
	 */
	public void setImpact(double impact) {
		try {
			if (impact < 0) {
				throw new IllegalArgumentException("L'impact doit �tre positif");
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
	 * @param fileName le nom du fichier o� sauvegarder l'objet
	 * @throws IOException si une erreur d'entr�e/sortie se produit
	 */
	public abstract void saveToFile(String fileName) throws IOException;

	/**
	 * Calcule l'impact de la consommation de carbone.
	 * 
	 * @return l'impact de la consommation de carbone
	 */
	public abstract double calculerImpact();

	/**
	 * Cr�e un objet avec un impact sp�cifi�.
	 * 
	 * @param i l'impact de la consommation de carbone de l'objet � cr�er
	 */
	public abstract void createObject(double i);

	/**
	 * Compare deux postes de consommation de carbone selon leur impact.
	 * 
	 * @param conso le poste de consommation de carbone � comparer
	 * @return 1 si l'impact de ce poste est sup�rieur � celui de conso, -1 si
	 *         l'impact de ce poste est inf�rieur � celui de conso, 0 si les impacts
	 *         sont �gaux
	 * @throws Exception si une exception est lev�e lors du calcul de l'impact
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
	 * Retourne une cha�ne de caract�res repr�sentant le poste de consommation de
	 * carbone. La cha�ne contient l'identifiant du poste, son type et son impact.
	 * 
	 * @return une cha�ne de caract�res repr�sentant le poste de consommation de
	 *         carbone
	 */
	@Override
	public String toString() {
		return "Poste de consommation de carbone ID : " + this.id + " - " + this.typePoste + " impact : " + this.impact;
	}
}
