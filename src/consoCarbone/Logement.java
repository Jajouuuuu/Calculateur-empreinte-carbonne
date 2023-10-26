package consoCarbone;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonObject;

/**
 * Classe représentant un logement.
 */
public abstract class Logement extends ConsoCarbone {

	/** Superficie du logement */
	protected int superficie;
	/** Classe énergétique du logement */
	protected CE ce;
	/** Localisation du logement */
	protected String localisation;

	/**
	 * Retourne la localisation du logement.
	 * 
	 * @return la localisation du logement
	 */
	public String getLocalisation() {
		return localisation;
	}

	/**
	 * Modifie la localisation du logement.
	 * 
	 * @param localisation la nouvelle localisation du logement
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**
	 * Constructeur de la classe Logement.
	 */
	public Logement() {
		super();
	}

	/**
	 * Retourne la superficie du logement.
	 * 
	 * @return la superficie du logement
	 */
	public int getSuperficie() {
		return superficie;
	}

	/**
	 * Modifie la superficie du logement.
	 * 
	 * @param superficie la nouvelle superficie du logement
	 */
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	/**
	 * Retourne la classe énergétique du logement.
	 * 
	 * @return la classe énergétique du logement
	 */
	public CE getCe() {
		return ce;
	}

	/**
	 * Modifie la classe énergétique du logement.
	 * 
	 * @param ce la nouvelle classe énergétique du logement
	 */
	public void setCe(CE ce) {
		this.ce = ce;
	}

	/**
	 * Méthode statique retournant l'empreinte carbone d'un logement moyen en
	 * France.
	 * 
	 * @return l'empreinte carbone d'un logement moyen en France
	 */
	public static double logementMoyenneFr() {
		return 2706;
	}

	/**
	 * Crée un objet Logement avec un impact spécifié.
	 * 
	 * @param i l'impact de la consommation de carbone du logement à créer
	 */
	@Override
	public void createObject(double i) {
		setTypePoste("Logement");
		setImpact(i);
		System.out.println(i);
	}

	/**
	 * Sauvegarde l'objet dans un fichier au format JSON.
	 * 
	 * @param fileName le nom du fichier dans lequel sauvegarder l'objet
	 * @throws IOException si une erreur d'entrée/sortie est rencontrée lors de
	 *                     l'écriture du fichier
	 */
	@Override
	public void saveToFile(String fileName) throws IOException {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("localisation", this.getLocalisation());
		jsonObject.addProperty("superficie", this.getSuperficie());
		jsonObject.addProperty("CE", this.getCe().name());

		try (FileWriter writer = new FileWriter(fileName)) {
			writer.write(jsonObject.toString());
		}
	}

	/**
	 * Calcule l'impact de la consommation de carbone du logement.
	 * 
	 * @return l'impact de la consommation de carbone du logement
	 */
	@Override
	public abstract double calculerImpact();
}
