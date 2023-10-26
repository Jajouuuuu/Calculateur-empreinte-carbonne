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
 * Classe principale qui permet � l'utilisateur de saisir ses informations de
 * consommation de carbone, et calcule son empreinte carbone en fonction de ces
 * informations.
 */
public class Main {

	/**
	 * M�thode principale qui ex�cute le programme.
	 * 
	 * @param args Les arguments de la ligne de commande.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		/*
		// Lecture d'un fichier texte pour instancier un utilisateur
		Utilisateur utilisateurFromFichier = null;
		// Attention � bien mettre le path correspondant au chemin sur votre machine pour tester le fichier donn� en exemple (ici il s'agit du chemin sur mon ordinateur)
		// Attention aussi � bien respect� la syntaxe du path
		utilisateurFromFichier.readUtilisateurFromFile("C://Users//Jajou//git//repository//empreinte_c//src//exempleUtilisateur.txt");
		System.out.println(utilisateurFromFichier); 
		*/

		Scanner sc = new Scanner(System.in);
		// Cr�ez une liste vide de Logement
		List<Logement> logements = new ArrayList<>();
		// Demandez � l'utilisateur s'il poss�de plusieurs logements
		System.out.println("Poss�dez-vous un logement (o/n) ?");
		String reponse = sc.next();
		// Tant que l'utilisateur poss�de un autre logement, demandez ses informations
		// et ajoutez-le � la liste
		while (reponse.equals("o")) {
			// Demandez � l'utilisateur de saisir la superficie du logement
			System.out.println("Veuillez saisir la superficie du logement :");
			int superficie = 0;
			try {
				superficie = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("La superficie doit �tre un nombre entier, veuillez r�essayer.");
				sc.nextLine(); // Pour �viter de boucler sur la m�me erreur
				continue;
			}

			// Demandez � l'utilisateur de saisir la classe �nerg�tique du logement
			System.out.println("Veuillez saisir la classe �nerg�tique du logement (A, B, C, D, E ou F) :");
			String classeEnergetique = sc.next();

			// Demandez � l'utilisateur s'il habite en ville ou � la campagne
			System.out.println("Habitez-vous en ville ou � la campagne (ville/campagne) ?");
			String emplacement = sc.next();

			try {
				// Cr�ez une instance de LogementVille ou LogementCampagne en fonction de la
				// r�ponse de l'utilisateur
				Logement logement;
				if (emplacement.equals("ville")) {
					logement = new LogementVille(superficie, CE.valueOf(classeEnergetique));
				} else {
					logement = new LogementCampagne(superficie, CE.valueOf(classeEnergetique));
				}
				// Ajoutez le logement � la liste
				logements.add(logement);
			} catch (IllegalArgumentException e) {
				// Si la classe �nerg�tique est incorrecte, affichez un message d'erreur
				System.out.println("La classe �nerg�tique est incorrecte, veuillez r�essayer.");
				continue;
			}

			// Demandez � l'utilisateur s'il poss�de un autre logement
			System.out.println("Poss�dez-vous un autre logement (o/n) ?");
			reponse = sc.next();
		}

		// Demandez � l'utilisateur de saisir le pourcentage de viande dans son
		// alimentation
		System.out.println("Veuillez saisir le pourcentage de viande dans votre alimentation :");
		double txBoeuf = 0;
		try {
			txBoeuf = sc.nextDouble();
			if (txBoeuf < 0 || txBoeuf > 1) {
				throw new IllegalArgumentException(
						"Le pourcentage de viande doit �tre compris entre 0 et 1, veuillez r�essayer.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Le pourcentage de viande doit �tre un nombre d�cimal, veuillez r�essayer.");
			sc.nextLine(); // Pour �viter de boucler sur la m�me erreur
		}

		// Demandez � l'utilisateur de saisir le pourcentage de l�gumes dans son
		// alimentation
		System.out.println("Veuillez saisir le pourcentage de l�gumes dans votre alimentation :");
		double txVege = 0;
		try {
			txVege = sc.nextDouble();
			if (txVege < 0 || txVege > 1) {
				throw new IllegalArgumentException(
						"Le pourcentage de l�gumes doit �tre compris entre 0 et 1, veuillez r�essayer.");
			}
			if (txVege + txBoeuf != 1) {
				throw new IllegalArgumentException(
						"La somme des pourcentages de viande et de l�gumes doit �tre �gale � 1, veuillez r�essayer.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Le pourcentage de l�gumes doit �tre un nombre d�cimal, veuillez r�essayer.");
			sc.nextLine(); // Pour �viter de boucler sur la m�me erreur
		}

		// Cr�ez une instance de Alimentation avec les donn�es saisies par l'utilisateur
		Alimentation alimentation = new Alimentation(txBoeuf, txVege);

		// Demandez � l'utilisateur de saisir le montant de ses biens de consommation
		System.out.println("Veuillez saisir le montant de vos biens de consommation :");
		int montant = 0;
		try {
			montant = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out
					.println("Le montant de vos biens de consommation doit �tre un nombre entier, veuillez r�essayer.");
			sc.nextLine(); // Pour �viter de boucler sur la m�me erreur
		}

		// Cr�ez une instance de BienDeConsommation avec les donn�es saisies par
		// l'utilisateur
		BienConso bienDeConsommation = new BienConso(montant);

		ArrayList<Transport> transports = new ArrayList<>();

		// Demandez � l'utilisateur s'il poss�de un v�hicule
		System.out.println("Poss�dez-vous un v�hicule (O/N) ?");
		char possede = sc.next().charAt(0);

		while (possede == 'O') {
			// Demandez � l'utilisateur la taille de son v�hicule
			System.out.println("Quelle est la taille de votre v�hicule (PETIT/MOYEN/GRAND) ?");
			String tailleStr = sc.next();
			Taille taille = Taille.valueOf(tailleStr.toUpperCase());

			// Demandez � l'utilisateur le nombre de kilom�tres parcourus par an
			System.out.println("Combien de kilom�tres parcourez-vous en moyenne par an avec votre v�hicule ?");
			int kilom_annee = 0;
			try {
				kilom_annee = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"Le nombre de kilom�tres parcourus par an doit �tre un nombre entier, veuillez r�essayer.");
				sc.nextLine(); // Pour �viter de boucler sur la m�me erreur
			}

			// Demandez � l'utilisateur le nombre d'ann�es avant l'amortissement de son
			// v�hicule
			System.out.println("Combien d'ann�es avant l'amortissement de votre v�hicule ?");
			int amortissement = 0;
			try {
				amortissement = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"Le nombre d'ann�es avant l'amortissement doit �tre un nombre entier, veuillez r�essayer.");
				sc.nextLine(); // Pour �viter de boucler sur la m�me erreur
			}

			// Cr�ez une instance de Transport avec les donn�es saisies par l'utilisateur
			Transport transport = new Transport(true, taille, kilom_annee, amortissement);
			transports.add(transport);

			// Demandez � l'utilisateur s'il poss�de un autre v�hicule
			System.out.println("Poss�dez-vous un autre v�hicule (O/N) ?");
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
