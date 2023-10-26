package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import consoCarbone.Alimentation;

/**
 * Classe de test pour la classe {@link Alimentation}.
 */
class AlimentationTest {

	/**
	 * Teste la m�thode {@link Alimentation#Alimentation()}.
	 */
	@Test
	public void testConstructeurParDefaut() {
		Alimentation alimentation = new Alimentation();
		assertNotNull(alimentation);
	}

	/**
	 * Test pour v�rifier le constructeur avec deux param�tres de la classe
	 * Alimentation :
	 */
	@Test
	public void testConstructeurAvecParametres() {
		Alimentation alimentation = new Alimentation(0.5, 0.5);
		assertNotNull(alimentation);
		assertEquals(0.5, alimentation.getTxBoeuf(), 0.001);
		assertEquals(0.5, alimentation.getTxVege(), 0.001);
	}

	/**
	 * Test pour v�rifier le constructeur avec deux param�tres de la classe
	 * Alimentation lorsque le taux de boeuf et le taux de v�g� sont n�gatifs :
	 */
	@Test
	public void testConstructeurAvecParametresTauxNegatifs() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Alimentation(-0.5, -0.5);
		});
	}

	/**
	 * Test pour v�rifier le constructeur avec deux param�tres de la classe
	 * Alimentation lorsque le taux de boeuf et le taux de v�g� d�passent 1 :
	 */
	@Test
	public void testConstructeurAvecParametresTauxDepassent1() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Alimentation(1.5, 1.5);
		});
	}

	/**
	 * Test pour v�rifier la m�thode setTxBoeuf de la classe Alimentation :
	 */
	@Test
	public void testSetTxBoeuf() {
		Alimentation alimentation = new Alimentation();
		alimentation.setTxBoeuf(0.6);
		assertEquals(0.6, alimentation.getTxBoeuf(), 0.001);
	}

	/**
	 * Test pour v�rifier la m�thode setTxVege de la classe Alimentation :
	 */
	@Test
	public void testSetTxVege() {
		Alimentation alimentation = new Alimentation();
		alimentation.setTxVege(0.4);
		assertEquals(0.4, alimentation.getTxVege(), 0.001);
	}

	/**
	 * Test pour v�rifier la m�thode alimentationMoyenneFr de la classe Alimentation
	 * :
	 */
	@Test
	public void testAlimentationMoyenneFr() {
		assertEquals(1144, Alimentation.alimentationMoyenneFr(), 0.001);
	}

	/**
	 * Test pour v�rifier la m�thode calculerImpact de la classe Alimentation :
	 */
	@Test
	public void testCalculerImpact() {
		Alimentation alimentation = new Alimentation(0.5, 0.5);
		assertEquals(8 * 0.5 + 0.9 * 1.6 * 0.5, alimentation.calculerImpact(), 0.001);
	}

	/**
	 * Test pour v�rifier la m�thode createObject de la classe Alimentation :
	 */
	@Test
	public void testCreateObject() {
		Alimentation alimentation = new Alimentation();
		alimentation.createObject(200);
		assertEquals(200, alimentation.getImpact(), 0.001);
	}
}