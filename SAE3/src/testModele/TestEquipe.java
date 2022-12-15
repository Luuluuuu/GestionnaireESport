package testModele;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Poule;

public class TestEquipe {

	private Equipe equipe;
	private List<Joueur> joueurs= new ArrayList<Joueur> ();
    private Jeu jeu;
    private List<Poule> poules;
    private List<Equipe> equipes;
    private Poule poule;
    private Joueur joueur;
    
	@Before
	public void setUp() throws Exception {
		this.equipe = new Equipe("fnatic", 32, jeu);
		this.poules = new ArrayList<Poule> ();
		this.equipes = new ArrayList<Equipe> ();
		this.poule = new Poule(this.equipes, true);
		this.joueur = new Joueur("maltra","léo","léo123","06/02/98","Allemagne");
	}

	@After
	public void tearDown() throws Exception {
		this.equipe = null;
		this.joueurs = null;
		this.jeu = null;
		this.poule = null;
	}

	@Test
	public void testConstructeur() {
		assertTrue(this.equipe instanceof Equipe);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(this.equipe.getNom(), "fnatic");
	}
	
	@Test
	public void testSetNom() {
		this.equipe.setNom("t1");
		assertEquals(this.equipe.getNom(), "t1");
	}
	
	@Test
	public void testGetjoueur() {
		assertEquals(this.equipe.getJoueurs(),this.joueurs);
	}
	
	@Test
	public void testAjouterJoueur() {
		this.equipe.ajouterJoueur(this.joueur);
		assertTrue(this.equipe.getJoueurs().contains(this.joueur));
	}
	
	@Test
	public void testGetJeu() {
		assertEquals(this.equipe.getJeu(), this.jeu);
	}
	
	@Test
	public void testSetJeu() {
		Jeu j = new Jeu(2,"Rc",12);
		this.equipe.setJeu(j);
		assertEquals(this.equipe.getJeu(),j);
	}
	
	@Test
	public void testGetPointsChampionnat() {
		assertEquals(this.equipe.getPointsChampionnat(), 32);
	}
	
	@Test
	public void testGetPoule() {
		assertEquals(this.equipe.getPoules(), this.poules);
	}
	
	@Test
	public void testAjouterPoule() {
		this.equipe.ajouterPoule(this.poule);
		assertTrue(this.equipe.getPoules().contains(this.poule));
		
	}
	
	@Test
	public void testAjouterPointsChampionnat() {
		this.equipe.ajouterPointsChampionnat(5);
		assertEquals(this.equipe.getPointsChampionnat(), 37);
	}

}
