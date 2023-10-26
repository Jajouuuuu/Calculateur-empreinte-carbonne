package consoCarbone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Classe repr�sentant le transport et ses caract�ristiques en termes
 * d'empreinte carbone.
 */
public class Transport extends ConsoCarbone {

	/** Bool�en indiquant si le propri�taire poss�de un v�hicule ou non. */
	private boolean possede;
	/** Taille du v�hicule, de l'�num�ration {@link Taille}. */
	private Taille taille;
	/** Nombre de kilom�tres parcourus par ann�e par le v�hicule. */
	private int kilom_annee;
	/** Dur�e d'amortissement du v�hicule. */
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
	 * @param taille        Taille de la voiture de l'�num�ration
	 * @param kilom_annee   nombre de kilom�tres roul� par ann�es
	 * @param amortissement dur�e de conservation du v�hicule
	 * @throws IllegalArgumentException si la taille du v�hicule n'est pas
	 *                                  renseign�e ou si le nombre de kilom�tres par
	 *                                  an ou la dur�e d'amortissement sont
	 *                                  n�gatifs.
	 */
	public Transport(boolean possede, Taille taille, int kilom_annee, int amortissement)
			throws IllegalArgumentException {
		try {
			this.possede = possede;
			if (!possede) {
				throw new IllegalArgumentException(
						"Si vous renseigner une taille et des renseignements sur une voiture possede ne peux �tre false.");
			}
			if (taille == null) {
				throw new IllegalArgumentException("La taille du v�hicule doit �tre renseign�e.");
			}
			this.taille = taille;
			if (kilom_annee < 0) {
				throw new IllegalArgumentException("Le nombre de kilom�tres par an doit �tre sup�rieur ou �gal � 0.");
			}
			this.kilom_annee = kilom_annee;
			if (amortissement < 0) {
				throw new IllegalArgumentException(
						"La dur�e d'amortissement du v�hicule doit �tre sup�rieure ou �gale � 0.");
			}
			this.amortissement = amortissement;
			this.impact = this.calculerImpact();
		} catch (IllegalArgumentException e) {
			System.err.println(e);
		}
	}

	/**
	 * Constructeur de la classe Transport lorsque le propri�taire n'a pas de
	 * v�hicule.
	 * 
	 * @param possede boolean de possession de voiture
	 * @throws IllegalArgumentException si la valeur de possede est true.
	 */
	public Transport(boolean possede) throws IllegalArgumentException {
		if (possede) {
			throw new IllegalArgumentException("La valeur de possede ne peut pas �tre true.");
		}
		this.possede = possede;
		this.impact = 0;
	}

	/**
	 * M�thode permettant de savoir si le propri�taire poss�de un v�hicule ou non.
	 * 
	 * @return true si le propri�taire poss�de un v�hicule, false sinon.
	 */
	public boolean isPossede() {
		return possede;
	}

	/**
	 * M�thode permettant de d�finir si le propri�taire poss�de un v�hicule ou non.
	 * 
	 * @param possede boolean indiquant si le propri�taire poss�de un v�hicule ou
	 *                non.
	 */
	public void setPossede(boolean possede) {
		this.possede = possede;
	}

	/**
	 * M�thode permettant de r�cup�rer la taille du v�hicule.
	 * 
	 * @return la taille du v�hicule, de l'�num�ration {@link Taille}.
	 */
	public Taille getTaille() {
		return taille;
	}

	/**
	 * M�thode permettant de d�finir la taille du v�hicule.
	 * 
	 * @param taille la taille du v�hicule, de l'�num�ration {@link Taille}.
	 */
	public void setTaille(Taille taille) {
		this.taille = taille;
	}

	/**
	 * M�thode permettant de r�cup�rer le nombre de kilom�tres parcourus par ann�e
	 * par le v�hicule.
	 * 
	 * @return le nombre de kilom�tres parcourus par ann�e par le v�hicule.
	 */
	public int getKilom_annee() {
		return kilom_annee;
	}

	/**
	 * M�thode permettant de d�finir le nombre de kilom�tres parcourus par ann�e par
	 * le v�hicule.
	 * 
	 * @param kilom_annee le nombre de kilom�tres parcourus par ann�e par le
	 *                    v�hicule.
	 */
	public void setKilom_annee(int kilom_annee) {
		this.kilom_annee = kilom_annee;
	}

	/**
	 * M�thode permettant de r�cup�rer la dur�e d'amortissement du v�hicule.
	 * 
	 * @return la dur�e d'amortissement du v�hicule.
	 */
	public int getAmortissement() {
		return amortissement;
	}

	/**
	 * M�thode permettant de d�finir la dur�e d'amortissement du v�hicule.
	 * 
	 * @param amortissement la dur�e d'amortissement du v�hicule.
	 */
	public void setAmortissement(int amortissement) {
		this.amortissement = amortissement;
	}

	/**
	 * M�thode statique d�taillant l'empreinte carbone d'une fran�ais moyen
	 * concernant le transport.
	 * 
	 * @return une cha�ne de caract�re contenant l'empreinte carbone d'une fran�ais
	 *         moyen concernant le transport.
	 */
	public static double transportMoyenneFr() {
		return 2355;
	}

	/**
	 * Cr�e un objet Transport avec un impact sp�cifi�.
	 * 
	 * @param i l'impact de la consommation de carbone du transport � cr�er
	 */
	@Override
	public void createObject(double i) {
		setTypePoste("Transport");
		setImpact(i);
	}

	/**
	 * M�thode permettant de calculer l'impact carbone du transport du propri�taire.
	 * 
	 * @return l'impact carbone du transport du propri�taire.
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
	 * @throws IOException si une erreur d'entr�e/sortie est rencontr�e lors de
	 *                     l'�criture du fichier
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
