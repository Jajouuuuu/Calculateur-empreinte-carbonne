package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import consoCarbone.Taille;
import consoCarbone.Transport;

/**
 * Classe de tests unitaires pour la classe {@link Transport}.
 */
class TransportTest {

	/**
	 * Teste les constructeurs de la classe {@link Transport}.
	 */
	@Test
	void testTransport() {
		Transport t1 = new Transport(true, Taille.P, 10000, 10);
		assertTrue(t1.isPossede());
		assertEquals(Taille.P, t1.getTaille());
		assertEquals(10000, t1.getKilom_annee());
		assertEquals(10, t1.getAmortissement());

		Transport t2 = new Transport(false);
		assertFalse(t2.isPossede());
		assertEquals(0, t2.getImpact());
	}

	/**
	 * Teste la méthode {@link Transport#calculerImpact()} avec un utilisateur
	 * n'ayant pas de voiture.
	 */
	@Test
	void testCalculerImpactNoCar() {
		Transport transport = new Transport(false, null, 0, 0);
		assertEquals(0, transport.calculerImpact(), 0.0001);
	}

	/**
	 * Teste la méthode {@link Transport#calculerImpact()} avec un utilisateur ayant
	 * une voiture.
	 */
	@Test
	void testCalculerImpactWithCar() {
		Transport transport = new Transport(true, Taille.P, 10000, 5);
		double expectedImpact = 10000 * 1.93 * Math.pow(10, -4) + (Taille.P.getEmissionProd() / 5);
		assertEquals(expectedImpact, transport.calculerImpact(), 0.0001);
	}

	/**
	 * Teste la méthode {@link Transport#getImpact()}.
	 */
	@Test
	void testGetImpact() {
		Transport transport = new Transport(false, null, 0, 0);
		transport.setImpact(123.45);
		assertEquals(123.45, transport.getImpact(), 0.0001);
	}

}