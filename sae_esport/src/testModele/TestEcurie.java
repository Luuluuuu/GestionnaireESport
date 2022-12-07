package testModele;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Connexion;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;

public class TestEcurie {

	private Ecurie ecurie;
	private List<Joueur> joueurs;
	private List<Equipe> equipes;
	private Joueur joueur;
	private Jeu jeu;
	private Equipe equipe;
	
	@Before
	public void setUp() throws Exception{
		this.joueurs = new ArrayList<Joueur> ();
		this.equipes = new ArrayList<Equipe> ();
		this.ecurie = new Ecurie(1,"Kcorp");
		this.joueur = new Joueur("maltra","léo","léo123","06/02/98","Allemagne");
		this.jeu = new Jeu(1,"league of legends", 5);
		this.equipe = new Equipe("DRX",12, jeu, joueurs);
	}
	
	@After
	public void tearDown() throws Exception{
		this.joueurs = null;
		this.equipes = null;
		this.ecurie = null;
		this.joueur = null;
		this.equipe = null;
	}
	
	@Test
	public void testConstructeur() {
		assertTrue(this.ecurie instanceof Ecurie);
	}
	
	@Test
	public void testCreeLogin() {
		Ecurie ec = new Ecurie(1, "T1");
		ec.creerLogin("$iutinfo");
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM sae_user,sae_ecurie WHERE sae_user.idecurie=sae_ecurie.idecurie and sae_ecurie.nomecurie='T1'");
		try {
			while (rs.next()) {
				assertTrue(rs.getString(2).equals("T1"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetId() {
		assertEquals(this.ecurie.getID(), 1);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(this.ecurie.getNom(), "Kcorp");
	}
	
	@Test
	public void testSetAnneeDeCreation() {
		this.ecurie.setAnneeDeCreation(2022);
		assertEquals(this.ecurie.getAnneeDeCreation(), 2022);
	}
	
	@Test
	public void testSupprimerEquipe() {
		this.ecurie.ajouterEquipe(this.equipe);
		this.ecurie.supprimerEquipe(this.equipe);
		assertTrue(this.equipes.isEmpty());
	}
	
	@Test
	public void testAjouterEquipe() {
		this.ecurie.ajouterEquipe(this.equipe);
	}
	
	@Test
	public void testGetEquipe() {
		this.joueurs.add(this.joueur);
		this.ecurie.ajouterEquipe(this.equipe);
		assertEquals(this.ecurie.getEquipe(0), this.equipe);
	}
	
	@Test
	public void testGetListeEquipe() {
		assertEquals(this.ecurie.getEquipes(), this.equipes);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSupressionEquipeAbscente() {
		this.ecurie.supprimerEquipe(this.equipe);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAjoutEquipePresente() {
		this.ecurie.ajouterEquipe(this.equipe);
		this.ecurie.ajouterEquipe(this.equipe);
	}
	


}
