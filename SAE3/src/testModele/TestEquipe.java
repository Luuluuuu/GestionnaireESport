package testModele;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Poule;

public class TestEquipe {

	private Ecurie ecurie;
	private Equipe equipe;
	private List<Joueur> joueurs= new ArrayList<Joueur> ();
    private Jeu jeu;
    private List<Poule> poules;
    private List<Equipe> equipes;
    private Poule poule;
    private Joueur joueur;
    
	@Before
	public void setUp() throws Exception {
		this.ecurie = new Ecurie(1,"Kcorp");
		this.equipe = new Equipe(2, "fnatic", 32, "allemand", jeu, ecurie);
		this.poule = new Poule(1);
		this.joueur = new Joueur(2,"maltra","léo","léo123","06/02/98","Allemagne",this.equipe,null);
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
