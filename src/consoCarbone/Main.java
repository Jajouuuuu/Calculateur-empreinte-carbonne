package consoCarbone;

/**
 * Point d'entr�e du programme, testant les quelques fonctionalit�s impl�ment�s.
 * @author Jeanne-Emma Lef�vre & Garance Raynaud
 */
public class Main {
	
	// Les valeurs de tests renseign�es ici sont arbitraires 
	// peuvent �tre chang�es sans impacter le fonctionnement du programme
	public static void main(String[] args) {
		

		// Cr�ation et test de la classe Alimentation
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
