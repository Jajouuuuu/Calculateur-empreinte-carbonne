package consoCarbone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Classe représentant le transport et ses caractéristiques en termes
 * d'empreinte carbone.
 */
public class Transport extends ConsoCarbone {

	/** Booléen indiquant si le propriétaire possède un véhicule ou non. */
	private boolean possede;
	/** Taille du véhicule, de l'énumération {@link Taille}. */
	private Taille taille;
	/** Nombre de kilomètres parcourus par année par le véhicule. */
	private int kilom_annee;
	/** Durée d'amortissement du véhicule. */
	private int amortissement;

	/**
	 * Constructeur vide de la classe Transport.
	 */
	public Transport() {
		super();
	}

	/**
	 * Constructeur de la classe Transport
	 * 
	 * @param possede       boolean de possession de voiture
	 * @param taille        Taille de la voiture de l'énumération
	 * @param kilom_annee   nombre de kilomètres roulé par années
	 * @param amortissement durée de conservation du véhicule
	 * @throws IllegalArgumentException si la taille du véhicule n'est pas
	 *                                  renseignée ou si le nombre de kilomètres par
	 *                                  an ou la durée d'amortissement sont
	 *                                  négatifs.
	 */
	public Transport(boolean possede, Taille taille, int kilom_annee, int amortissement)
			throws IllegalArgumentException {
		try {
			this.possede = possede;
			if (!possede) {
				throw new IllegalArgumentException(
						"Si vous renseigner une taille et des renseignements sur une voiture possede ne peux être false.");
			}
			if (taille == null) {
				throw new IllegalArgumentException("La taille du véhicule doit être renseignée.");
			}
			this.taille = taille;
			if (kilom_annee < 0) {
				throw new IllegalArgumentException("Le nombre de kilomètres par an doit être supérieur ou égal à 0.");
			}
			this.kilom_annee = kilom_annee;
			if (amortissement < 0) {
				throw new IllegalArgumentException(
						"La durée d'amortissement du véhicule doit être supérieure ou égale à 0.");
			}
			this.amortissement = amortissement;
			this.impact = this.calculerImpact();
		} catch (IllegalArgumentException e) {
			System.err.println(e);
		}
	}

	/**
	 * Constructeur de la classe Transport lorsque le propriétaire n'a pas de
	 * véhicule.
	 * 
	 * @param possede boolean de possession de voiture
	 * @throws IllegalArgumentException si la valeur de possede est true.
	 */
	public Transport(boolean possede) throws IllegalArgumentException {
		if (possede) {
			throw new IllegalArgumentException("La valeur de possede ne peut pas être true.");
		}
		this.possede = possede;
		this.impact = 0;
	}

	/**
	 * Méthode permettant de savoir si le propriétaire possède un véhicule ou non.
	 * 
	 * @return true si le propriétaire possède un véhicule, false sinon.
	 */
	public boolean isPossede() {
		return possede;
	}

	/**
	 * Méthode permettant de définir si le propriétaire possède un véhicule ou non.
	 * 
	 * @param possede boolean indiquant si le propriétaire possède un véhicule ou
	 *                non.
	 */
	public void setPossede(boolean possede) {
		this.possede = possede;
	}

	/**
	 * Méthode permettant de récupérer la taille du véhicule.
	 * 
	 * @return la taille du véhicule, de l'énumération {@link Taille}.
	 */
	public Taille getTaille() {
		return taille;
	}

	/**
	 * Méthode permettant de définir la taille du véhicule.
	 * 
	 * @param taille la taille du véhicule, de l'énumération {@link Taille}.
	 */
	public void setTaille(Taille taille) {
		this.taille = taille;
	}

	/**
	 * Méthode permettant de récupérer le nombre de kilomètres parcourus par année
	 * par le véhicule.
	 * 
	 * @return le nombre de kilomètres parcourus par année par le véhicule.
	 */
	public int getKilom_annee() {
		return kilom_annee;
	}

	/**
	 * Méthode permettant de définir le nombre de kilomètres parcourus par année par
	 * le véhicule.
	 * 
	 * @param kilom_annee le nombre de kilomètres parcourus par année par le
	 *                    véhicule.
	 */
	public void setKilom_annee(int kilom_annee) {
		this.kilom_annee = kilom_annee;
	}

	/**
	 * Méthode permettant de récupérer la durée d'amortissement du véhicule.
	 * 
	 * @return la durée d'amortissement du véhicule.
	 */
	public int getAmortissement() {
		return amortissement;
	}

	/**
	 * Méthode permettant de définir la durée d'amortissement du véhicule.
	 * 
	 * @param amortissement la durée d'amortissement du véhicule.
	 */
	public void setAmortissement(int amortissement) {
		this.amortissement = amortissement;
	}

	/**
	 * Méthode statique détaillant l'empreinte carbone d'une français moyen
	 * concernant le transport.
	 * 
	 * @return une chaîne de caractère contenant l'empreinte carbone d'une français
	 *         moyen concernant le transport.
	 */
	public static double transportMoyenneFr() {
		return 2355;
	}

	/**
	 * Crée un objet Transport avec un impact spécifié.
	 * 
	 * @param i l'impact de la consommation de carbone du transport à créer
	 */
	@Override
	public void createObject(double i) {
		setTypePoste("Transport");
		setImpact(i);
	}

	/**
	 * Méthode permettant de calculer l'impact carbone du transport du propriétaire.
	 * 
	 * @return l'impact carbone du transport du propriétaire.
	 */
	@Override
	public double calculerImpact() {
		try {
			if (this.possede == false) {
				return 0;
			} else {
				return this.kilom_annee * 1.93 * Math.pow(10, -4) + (this.taille.getEmissionProd() / amortissement);
			}
		} catch (Exception e) {
			System.err.println(e);
			return 0;
		}
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
		String json = new String(Files.readAllBytes(Paths.get(fileName)));
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(json, JsonObject.class);
		if (this.isPossede()) {
			obj.addProperty("possede", this.isPossede());
			obj.addProperty("taille", this.getTaille().name());
			obj.addProperty("kilomAnnee", this.getKilom_annee());
			obj.addProperty("amortissement", this.getAmortissement());
		} else {
			obj.addProperty("possede", this.isPossede());
		}
		json = gson.toJson(obj);
		System.out.println(json);
		Files.write(Paths.get(fileName), json.getBytes());
	}
}
