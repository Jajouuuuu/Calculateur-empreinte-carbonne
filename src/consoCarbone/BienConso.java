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

	/** Montant des d�penses annuelles */
	private double montant;

	/**
	 * Constructeur par d�faut de la classe BienConso
	 */
	public BienConso() {
		super();
	}

	/**
	 * Constructeur de la classe BienConso
	 * 
	 * @param montant montant des d�penses annuelles
	 * @throws IllegalArgumentException si le montant des d�penses est n�gatif
	 */
	public BienConso(double montant) {
		if (montant < 0) {
			throw new IllegalArgumentException("Le montant des d�penses doit �tre positif");
		}
		this.montant = montant;
		this.impact = this.calculerImpact();
	}

	/**
	 * Set le montant des d�penses.
	 * 
	 * @param montant le montant � set.
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
	 * M�thode statique d�taillant l'empreinte carbone d'une fran�ais moyen
	 * concernant les bien conso.
	 * 
	 * @return Un double correspondant � l'empreinte carbone des bien de
	 *         consommations d'un fran�ais moyen.
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
			throw new IllegalArgumentException("Le montant des d�penses doit �tre positif");
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
