package consoCarbone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Classe de du poste de consommation Alimentation.
 */
public class Alimentation extends ConsoCarbone {

	/** Taux de boeuf */
	private static double txBoeuf;
	/** Taux de végé */
	private static double txVege;
	/** Coefficient 1 */
	final static double coeff_1 = 8;
	/** Coefficient 2 */
	final static double coeff_2 = 0.9;
	/** Coefficient 3 */
	final static double coeff_3 = 1.6;

	/**
	 * Constructeur par défaut de la classe Alimentation
	 */
	public Alimentation() {
		super();
	}

	/**
	 * Constructeur de la classe Alimentation
	 * 
	 * @param txBoeuf taux de boeuf
	 * @param txVege  taux de végé
	 * @throws IllegalArgumentException si le taux de boeuf et le taux de végé sont
	 *                                  négatifs ou si ils dépassent 1
	 */
	public Alimentation(double txBoeuf, double txVege) {
		if (txBoeuf < 0 || txVege < 0 || txBoeuf > 1 || txVege > 1) {
			throw new IllegalArgumentException("Taux de boeuf et taux de végé doivent être positifs");
		}
		Alimentation.txBoeuf = txBoeuf;
		Alimentation.txVege = txVege;
		this.impact = this.calculerImpact();
	}

	/**
	 * Get le coefficient txBoeuf.
	 * 
	 * @return txBoeuf
	 */
	public double getTxBoeuf() {
		return txBoeuf;
	}

	/**
	 * Set le coefficient txBoeuf.
	 * 
	 * @param txBoeuf le coefficient à set.
	 */
	public void setTxBoeuf(double txBoeuf) {
		Alimentation.txBoeuf = txBoeuf;
	}

	/**
	 * Get le coefficient txVege.
	 * 
	 * @return txVege
	 */
	public double getTxVege() {
		return txVege;
	}

	/**
	 * Set le coefficient txVege.
	 * 
	 * @param txVege le coefficient à set.
	 */
	public void setTxVege(double txVege) {
		Alimentation.txVege = txVege;
	}

	/**
	 * Méthode statique détaillant l'empreinte carbone d'une français moyen
	 * concernant l'alimentation
	 * 
	 * @return Une chaîne de caractère.
	 */
	public static double alimentationMoyenneFr() {
		return 1144;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createObject(double i) {
		setTypePoste("Alimentation");
		setImpact(i);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double calculerImpact() {
		if (txBoeuf + txVege > 1) {
			throw new IllegalArgumentException("Le taux de boeuf et le taux de végé ne peuvent pas dépasser 1");
		}
		return Alimentation.coeff_1 * getTxBoeuf() + Alimentation.coeff_3 * getTxVege() * Alimentation.coeff_2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveToFile(String fileName) throws IOException {
		String json = new String(Files.readAllBytes(Paths.get(fileName)));
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(json, JsonObject.class);
		obj.addProperty("txBoeuf", this.getTxBoeuf());
		obj.addProperty("txVege", this.getTxVege());
		json = gson.toJson(obj);
		Files.write(Paths.get(fileName), json.getBytes());
	}
}