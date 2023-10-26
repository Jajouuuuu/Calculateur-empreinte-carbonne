package utilisateurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import consoCarbone.Alimentation;
import consoCarbone.BienConso;
import consoCarbone.CE;
import consoCarbone.Logement;
import consoCarbone.LogementCampagne;
import consoCarbone.LogementVille;
import consoCarbone.Taille;
import consoCarbone.Transport;

/**
 * Classe principale qui permet à l'utilisateur de saisir ses informations de
 * consommation de carbone, et calcule son empreinte carbone en fonction de ces
 * informations.
 */
public class Main {

	/**
	 * Méthode principale qui exécute le programme.
	 * 
	 * @param args Les arguments de la ligne de commande.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		/*
		// Lecture d'un fichier texte pour instancier un utilisateur
		Utilisateur utilisateurFromFichier = null;
		// Attention à bien mettre le path correspondant au chemin sur votre machine pour tester le fichier donné en exemple (ici il s'agit du chemin sur mon ordinateur)
		// Attention aussi à bien respecté la syntaxe du path
		utilisateurFromFichier.readUtilisateurFromFile("C://Users//Jajou//git//repository//empreinte_c//src//exempleUtilisateur.txt");
		System.out.println(utilisateurFromFichier); 
		*/

		Scanner sc = new Scanner(System.in);
		// Créez une liste vide de Logement
		List<Logement> logements = new ArrayList<>();
		// Demandez à l'utilisateur s'il possède plusieurs logements
		System.out.println("Possédez-vous un logement (o/n) ?");
		String reponse = sc.next();
		// Tant que l'utilisateur possède un autre logement, demandez ses informations
		// et ajoutez-le à la liste
		while (reponse.equals("o")) {
			// Demandez à l'utilisateur de saisir la superficie du logement
			System.out.println("Veuillez saisir la superficie du logement :");
			int superficie = 0;
			try {
				superficie = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("La superficie doit être un nombre entier, veuillez réessayer.");
				sc.nextLine(); // Pour éviter de boucler sur la même erreur
				continue;
			}

			// Demandez à l'utilisateur de saisir la classe énergétique du logement
			System.out.println("Veuillez saisir la classe énergétique du logement (A, B, C, D, E ou F) :");
			String classeEnergetique = sc.next();

			// Demandez à l'utilisateur s'il habite en ville ou à la campagne
			System.out.println("Habitez-vous en ville ou à la campagne (ville/campagne) ?");
			String emplacement = sc.next();

			try {
				// Créez une instance de LogementVille ou LogementCampagne en fonction de la
				// réponse de l'utilisateur
				Logement logement;
				if (emplacement.equals("ville")) {
					logement = new LogementVille(superficie, CE.valueOf(classeEnergetique));
				} else {
					logement = new LogementCampagne(superficie, CE.valueOf(classeEnergetique));
				}
				// Ajoutez le logement à la liste
				logements.add(logement);
			} catch (IllegalArgumentException e) {
				// Si la classe énergétique est incorrecte, affichez un message d'erreur
				System.out.println("La classe énergétique est incorrecte, veuillez réessayer.");
				continue;
			}

			// Demandez à l'utilisateur s'il possède un autre logement
			System.out.println("Possédez-vous un autre logement (o/n) ?");
			reponse = sc.next();
		}

		// Demandez à l'utilisateur de saisir le pourcentage de viande dans son
		// alimentation
		System.out.println("Veuillez saisir le pourcentage de viande dans votre alimentation :");
		double txBoeuf = 0;
		try {
			txBoeuf = sc.nextDouble();
			if (txBoeuf < 0 || txBoeuf > 1) {
				throw new IllegalArgumentException(
						"Le pourcentage de viande doit être compris entre 0 et 1, veuillez réessayer.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Le pourcentage de viande doit être un nombre décimal, veuillez réessayer.");
			sc.nextLine(); // Pour éviter de boucler sur la même erreur
		}

		// Demandez à l'utilisateur de saisir le pourcentage de légumes dans son
		// alimentation
		System.out.println("Veuillez saisir le pourcentage de légumes dans votre alimentation :");
		double txVege = 0;
		try {
			txVege = sc.nextDouble();
			if (txVege < 0 || txVege > 1) {
				throw new IllegalArgumentException(
						"Le pourcentage de légumes doit être compris entre 0 et 1, veuillez réessayer.");
			}
			if (txVege + txBoeuf != 1) {
				throw new IllegalArgumentException(
						"La somme des pourcentages de viande et de légumes doit être égale à 1, veuillez réessayer.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Le pourcentage de légumes doit être un nombre décimal, veuillez réessayer.");
			sc.nextLine(); // Pour éviter de boucler sur la même erreur
		}

		// Créez une instance de Alimentation avec les données saisies par l'utilisateur
		Alimentation alimentation = new Alimentation(txBoeuf, txVege);

		// Demandez à l'utilisateur de saisir le montant de ses biens de consommation
		System.out.println("Veuillez saisir le montant de vos biens de consommation :");
		int montant = 0;
		try {
			montant = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out
					.println("Le montant de vos biens de consommation doit être un nombre entier, veuillez réessayer.");
			sc.nextLine(); // Pour éviter de boucler sur la même erreur
		}

		// Créez une instance de BienDeConsommation avec les données saisies par
		// l'utilisateur
		BienConso bienDeConsommation = new BienConso(montant);

		ArrayList<Transport> transports = new ArrayList<>();

		// Demandez à l'utilisateur s'il possède un véhicule
		System.out.println("Possédez-vous un véhicule (O/N) ?");
		char possede = sc.next().charAt(0);

		while (possede == 'O') {
			// Demandez à l'utilisateur la taille de son véhicule
			System.out.println("Quelle est la taille de votre véhicule (PETIT/MOYEN/GRAND) ?");
			String tailleStr = sc.next();
			Taille taille = Taille.valueOf(tailleStr.toUpperCase());

			// Demandez à l'utilisateur le nombre de kilomètres parcourus par an
			System.out.println("Combien de kilomètres parcourez-vous en moyenne par an avec votre véhicule ?");
			int kilom_annee = 0;
			try {
				kilom_annee = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"Le nombre de kilomètres parcourus par an doit être un nombre entier, veuillez réessayer.");
				sc.nextLine(); // Pour éviter de boucler sur la même erreur
			}

			// Demandez à l'utilisateur le nombre d'années avant l'amortissement de son
			// véhicule
			System.out.println("Combien d'années avant l'amortissement de votre véhicule ?");
			int amortissement = 0;
			try {
				amortissement = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"Le nombre d'années avant l'amortissement doit être un nombre entier, veuillez réessayer.");
				sc.nextLine(); // Pour éviter de boucler sur la même erreur
			}

			// Créez une instance de Transport avec les données saisies par l'utilisateur
			Transport transport = new Transport(true, taille, kilom_annee, amortissement);
			transports.add(transport);

			// Demandez à l'utilisateur s'il possède un autre véhicule
			System.out.println("Possédez-vous un autre véhicule (O/N) ?");
			possede = sc.next().charAt(0);
		}

		Utilisateur utilisateur = new Utilisateur(alimentation, bienDeConsommation, logements, transports);
		System.out.println(utilisateur.calculImpact());
		utilisateur = utilisateur.readUtilisateurFromFile("C://Users//Jajou//git//repository//empreinte_c//src//exempleUtilisateur.txt");
		System.out.println(utilisateur);
		utilisateur.detaillerFootprint();
		utilisateur.recommandationEtImpact();
	}
}
