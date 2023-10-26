package utilisateurs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import consoCarbone.Alimentation;
import consoCarbone.BienConso;
import consoCarbone.ConsoCarbone;
import consoCarbone.Logement;
import consoCarbone.LogementCampagne;
import consoCarbone.LogementVille;
import consoCarbone.ServicesPublics;
import consoCarbone.Transport;

/**
 * Classe représentant un utilisateur avec ses différents éléments de
 * consommation de carbone.
 */
public class Utilisateur {
	private Alimentation alimentation;
	private BienConso bienConso;
	private List<Logement> logements;
	private List<Transport> transports;
	private ServicesPublics services;

	/**
	 * Crée un nouvel utilisateur avec les éléments de consommation de carbone
	 * spécifiés.
	 * 
	 * @param alimentation l'alimentation de l'utilisateur
	 * @param bienConso    les biens de consommation de l'utilisateur
	 * @param logement     la liste des logements de l'utilisateur
	 * @param transport    la liste des transports de l'utilisateur
	 */
	public Utilisateur(Alimentation alimentation, BienConso bienConso, List<Logement> logement,
			List<Transport> transport) {
		this.alimentation = alimentation;
		this.bienConso = bienConso;
		this.logements = logement;
		this.transports = transport;
		this.services = services.getInstance();
	}

	/**
	 * Calcule l'empreinte carbone de l'utilisateur en additionnant l'empreinte
	 * carbone de chaque élément de consommation.
	 * 
	 * @return l'empreinte carbone de l'utilisateur
	 */
	public double calculImpact() {
		// Calculate the carbon footprint of the user by adding the carbon consumption
		// of each item
		return alimentation.getImpact() + bienConso.getImpact() + getLogementConsommation()
		+ getTransportConsommation();
	}

	/**
	 * Affiche une liste triée des éléments de consommation de carbone de
	 * l'utilisateur et des recommandations pour réduire cette consommation.
	 */
	public void recommandationEtImpact() {
		// Créer une liste des éléments de consommation de carbone
		List<ConsoCarbone> carbonConsumption = new ArrayList<>();

		alimentation.createObject(alimentation.getImpact());
		carbonConsumption.add(alimentation);
		bienConso.createObject(bienConso.getImpact());
		carbonConsumption.add(bienConso);
		for (Logement logement : logements) {
			logement.createObject(logement.getImpact());
		}
		carbonConsumption.addAll(logements);
		for (Transport transport : transports) {
			transport.createObject(transport.getImpact());
		}
		carbonConsumption.addAll(transports);

		// Imprimer la liste triée et faire des recommandations en fonction des éléments
		// ayant le plus grand impact carbone
		System.out.println("Votre consommation de carbone est la suivante : ");
		for (ConsoCarbone item : carbonConsumption) {
			System.out.println(item.toString());
		}
		if (alimentation.getImpact() > Alimentation.alimentationMoyenneFr()) {
			System.out.println(
					"- Pensez à réduire votre consommation de viande et à choisir des options à base de plantes.");
		}
		if (bienConso.getImpact() > BienConso.bienConsoMoyenneFr()) {
			System.out.println("- Pensez à acheter des produits plus durables et respectueux de l'environnement.");
		}
		if (getLogementConsommation() > Logement.logementMoyenneFr()) {
			System.out.println(
					"- Pensez à améliorer l'efficacité énergétique de votre maison, par exemple en installant des panneaux solaires ou en améliorant l'isolation.");
		}
		if (getTransportConsommation() > Transport.transportMoyenneFr()) {
			System.out.println(
					"- Pensez à utiliser des moyens de transport alternatifs, tels que les transports en commun ou le vélo, ou à acheter un véhicule électrique ou hybride.");
		}
	}

	/**
	 * Calcule la consommation en termes d'impact carbone des logements de
	 * l'utilisateur.
	 * 
	 * @return la consommation en termes d'impact carbone des logements de
	 *         l'utilisateur
	 */
	public double getLogementConsommation() {
		double logementConsommation = 0;
		for (Logement logement : logements) {
			logementConsommation += logement.getImpact();
		}
		return logementConsommation;
	}

	/**
	 * Calcule la consommation en termes d'impact carbone des transports de
	 * l'utilisateur.
	 * 
	 * @return la consommation en termes d'impact carbone des transports de
	 *         l'utilisateur
	 */
	public double getTransportConsommation() {
		double transportConsommation = 0;
		for (Transport transport : transports) {
			transportConsommation += transport.getImpact();
		}
		return transportConsommation;
	}

	/**
	 * Affiche une description détaillée de l'empreinte carbone de l'utilisateur.
	 */
	public void detaillerFootprint() {
		// Affiche une description détaillée de l'empreinte carbone de l'utilisateur
		System.out.println("Alimentation: " + alimentation.getImpact() + " kg de CO2");
		System.out.println("Bien de consommation: " + bienConso.getImpact() + " kg de CO2");
		System.out.println("Hébergement: " + getLogementConsommation() + " kg de CO2");
		System.out.println("Transport: " + getTransportConsommation() + " kg de CO2");
	}

	/**
	 * Instancie un objet Utilisateur à partir d'un fichier texte.
	 * 
	 * @param fileName Le nom du fichier à lire.
	 * @return L'objet Utilisateur instancié à partir du fichier.
	 * @throws IOException Si une erreur d'entrée/sortie survient lors de la lecture
	 *                     du fichier.
	 */
	public static Utilisateur readUtilisateurFromFile(String fileName) throws IOException {
		// Créez une liste vide de logements et de transports
		List<Logement> logements = new ArrayList<>();
		List<Transport> transports = new ArrayList<>();

		// Créer un objet BufferedReader pour lire le fichier
		BufferedReader reader = new BufferedReader(new FileReader(fileName));

		// Créer une variable pour stocker la ligne courante lue dans le fichier
		String line;

		// Initialisez les variables qui seront utilisées pour instancier un objet
		// Utilisateur
		Alimentation alimentation = null;
		BienConso bienConso = null;
		try {
			// Tant que le BufferedReader a encore des lignes à lire...
			while ((line = reader.readLine()) != null) {
				// Séparez la ligne en tokens séparés par un espace
				String[] tokens = line.split(" ");
				Logement logement = null;
				if (tokens[0].equals("ville")) {
					logement = new LogementVille(Integer.parseInt(tokens[1]), consoCarbone.CE.valueOf(tokens[2]));
				} else if (tokens[0].equals("campagne")) {
					logement = new LogementCampagne(Integer.parseInt(tokens[1]), consoCarbone.CE.valueOf(tokens[2]));
				}
				logements.add(logement);
				if (tokens[0].equals("Transport")) {
					// Instanciez un objet Transport avec les tokens suivants
					Transport transport = new Transport(Boolean.parseBoolean(tokens[1]),
							consoCarbone.Taille.valueOf(tokens[2]), Integer.parseInt(tokens[3]),
							Integer.parseInt(tokens[4]));
					// Ajoutez le transport à la liste
					transports.add(transport);
				} else if (tokens[0].equals("Alimentation")) {
					// Instanciez un objet Alimentation avec les tokens suivants
					alimentation = new Alimentation(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
				} else if (tokens[0].equals("BienConso")) {
					// Instanciez un objet BienConso avec les tokens suivants
					double montant = Double.parseDouble(tokens[1]);
					bienConso = new BienConso(montant);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		// Fermez le BufferedReader
		reader.close();
		// Instanciez et retournez un objet Utilisateur avec les listes de logements et
		// de transports ainsi que les objets Alimentation et BienConso
		return new Utilisateur(alimentation, bienConso, logements, transports);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Utilisateur avec :\n");
		sb.append("- Alimentation : ").append(alimentation).append("\n");
		sb.append("- Bien de consommation : ").append(bienConso).append("\n");
		sb.append("- Logements : ").append(logements).append("\n");
		sb.append("- Transports : ").append(transports).append("\n");
		sb.append("- Services publics : ").append(services).append("\n");
		return sb.toString();
	}
}
