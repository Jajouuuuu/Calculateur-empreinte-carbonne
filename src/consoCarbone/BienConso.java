package consoCarbone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Classe de du poste de consommation BienConso.
 */
public class BienConso extends ConsoCarbone {

	/** Montant des dépenses annuelles */
	private double montant;

	/**
	 * Constructeur par défaut de la classe BienConso
	 */
	public BienConso() {
		super();
	}

	/**
	 * Constructeur de la classe BienConso
	 * 
	 * @param montant montant des dépenses annuelles
	 * @throws IllegalArgumentException si le montant des dépenses est négatif
	 */
	public BienConso(double montant) {
		if (montant < 0) {
			throw new IllegalArgumentException("Le montant des dépenses doit être positif");
		}
		this.montant = montant;
		this.impact = this.calculerImpact();
	}

	/**
	 * Set le montant des dépenses.
	 * 
	 * @param montant le montant à set.
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

	/**
	 * Get le montant.
	 * 
	 * @return montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * Méthode statique détaillant l'empreinte carbone d'une français moyen
	 * concernant les bien conso.
	 * 
	 * @return Un double correspondant à l'empreinte carbone des bien de
	 *         consommations d'un français moyen.
	 */
	public static double bienConsoMoyenneFr() {
		return 2625;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createObject(double i) {
		setTypePoste("Bien Consommation");
		setImpact(i);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double calculerImpact() {
		if (montant < 0) {
			throw new IllegalArgumentException("Le montant des dépenses doit être positif");
		}
		return montant / 1750;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveToFile(String fileName) throws IOException {
		String json = new String(Files.readAllBytes(Paths.get(fileName)));
		Gson gson = new Gson();
		JsonObject obj = gson.fromJson(json, JsonObject.class);
		obj.addProperty("montant", this.getMontant());
		json = gson.toJson(obj);
		Files.write(Paths.get(fileName), json.getBytes());
	}
}
