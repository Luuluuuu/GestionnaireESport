package testModele;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Equipe;
import modele.Poule;

public class TestPoule {

	private Poule poule;
	private Equipe gagnant;
	private List<Equipe> equipes;

	@Before
	public void setUp() throws Exception {
		this.poule = new Poule(1);
		this.equipes = new ArrayList<Equipe> ();
	}

	@After
	public void tearDown() throws Exception {
		this.poule = null;
		this.gagnant = null;
		this.equipes = null;
	}

	@Test
	public void testConstructeur() {
		assertTrue(this.poule instanceof Poule);
	}
	
	@Test
	public void testGetGagnant() {
		assertEquals(this.poule.getGagnant(), this.gagnant);
	}
	
	@Test
	public void testSetGagnant() {
		this.poule.setGagnant(gagnant);
		assertEquals(this.poule.getGagnant(),this.gagnant);
	}
	
	@Test
	public void testGetType() {
		assertEquals(this.poule.getType(), true);
	}
	
	@Test
	public void testSetFinale() {
		this.poule.setFinale(false);
		assertEquals(this.poule.getType(), false);
	}

}
