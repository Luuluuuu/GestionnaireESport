package testModele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;

public class TestJoueur {

	private Joueur joueur;

	@Before
	public void setUp() throws Exception {
		this.joueur = new Joueur(2,"maltra","léo","léo123","06/02/98","Allemagne",null,null);
	}

	@After
	public void tearDown() throws Exception {
		this.joueur = null;
	}
	
	@Test
	public void testConstructeur() {
		assertTrue(this.joueur instanceof Joueur);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(this.joueur.getNom(), "maltra");
	}
	
	@Test
	public void testGetPrenom() {
		assertEquals(this.joueur.getPrenom(), "léo");
	}
	
	@Test
	public void testGetPseudo() {
		assertEquals(this.joueur.getPseudo(), "léo123");
	}
	
	@Test
	public void testGetDateDeNaissance() {
		assertEquals(this.joueur.getDateNaissance(), "06/02/98");
	}
	
	@Test
	public void testGetNationalite() {
		assertEquals(this.joueur.getNationalite(), "Allemagne");
	}
	
	@Test
	public void testSetNom() {
		this.joueur.setNom("vidal");
		assertEquals(this.joueur.getNom(), "vidal");
	}
	
	@Test
	public void testSetPrenom() {
		this.joueur.setPrenom("martin");
		assertEquals(this.joueur.getPrenom(), "martin");
	}
	
	@Test
	public void testSetPseudo() {
		this.joueur.setPseudo("téo986");
		assertEquals(this.joueur.getPseudo(), "téo986");
	}
	
	@Test
	public void testSetDateDeNaissance() {
		this.joueur.setDateNaissance("06/03/99");
		assertEquals(this.joueur.getDateNaissance(), "06/03/99");
	}
	
	@Test
	public void testSetNationalite() {
		this.joueur.setNationalite("france");
		assertEquals(this.joueur.getNationalite(), "france");
	}
}
