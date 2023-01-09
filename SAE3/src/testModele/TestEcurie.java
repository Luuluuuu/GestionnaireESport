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
	private Joueur joueur;
	private Jeu jeu;
	private Equipe equipe;
	
	@Before
	public void setUp() throws Exception{
		this.ecurie = new Ecurie(1,"Kcorp");
		this.jeu = new Jeu(1,"league of legends", 5);
		this.equipe = new Equipe(2, "DRX",12, "allemand", jeu, ecurie);
		this.joueur = new Joueur(2,"maltra","léo","léo123","06/02/98","Allemagne",this.equipe,null);
		this.equipe.ajouterJoueur(joueur);
	}
	
	@After
	public void tearDown() throws Exception{
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
		assertTrue(this.ecurie.getEquipes().isEmpty());
	}
	
	@Test
	public void testAjouterEquipe() {
		this.ecurie.ajouterEquipe(this.equipe);
	}
	
	@Test
	public void testGetEquipe() {
		this.ecurie.ajouterEquipe(this.equipe);
		assertTrue(this.ecurie.getEquipes().contains(this.equipe));
	}
	
	@Test
	public void testGetListeEquipe() {
		this.ecurie.ajouterEquipe(this.equipe);
		List<Equipe> equipes = new ArrayList<Equipe>();
		equipes.add(this.equipe);
		assertEquals(this.ecurie.getEquipes(), equipes);
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
