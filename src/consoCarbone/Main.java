package consoCarbone;

/**
 * Point d'entrée du programme, testant les quelques fonctionalités implémentés.
 * @author Jeanne-Emma Lefèvre & Garance Raynaud
 */
public class Main {
	
	// Les valeurs de tests renseignées ici sont arbitraires 
	// peuvent être changées sans impacter le fonctionnement du programme
	public static void main(String[] args) {
		

		// Création et test de la classe Alimentation
		Alimentation alimentation = new Alimentation(0.81, 0.19);
		alimentation.createObject(alimentation.calculerImpact());
		System.out.println(alimentation);
		System.out.println(Alimentation.alimentationMoyenneFr());
	
		System.out.println("\n ----------------------- \n");
		
		Alimentation alimentation1 = new Alimentation(0.51, 0.49);
		alimentation1.createObject(alimentation1.calculerImpact());
		System.out.println(alimentation1);
		System.out.println(Alimentation.alimentationMoyenneFr());
	
		System.out.println("\n ----------------------- \n");
		
		System.out.println(alimentation.compareTo(alimentation1));
		
			
	}
}
