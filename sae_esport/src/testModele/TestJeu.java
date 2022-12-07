package testModele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Jeu;

public class TestJeu {
	private Jeu j;
	
	@Before
	public void setUp() {
		this.j = new Jeu(1,"LoL",10);
	}
	
	@After
	public void tearDown() {
		this.j = null;
	}
	
	@Test
	public void testConstructeur() {
		assertTrue(j instanceof Jeu);
	}

	@Test
	public void testGetID() {
		assertEquals(j.getID(),1);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(j.getNom(),"LoL");
	}
	
	@Test
	public void testGetNbJoueurs() {
		assertEquals(j.getNbJoueurs(),10);
	}
	
	@Test
	public void testSetNom() {
		j.setNom("League of Legends");
		assertEquals(j.getNom(),"League of Legends");
	}
	
	@Test
	public void testSetNbJoueurs() {
		j.setNbJoueurs(8);
		assertEquals(j.getNbJoueurs(),8);
	}
}
