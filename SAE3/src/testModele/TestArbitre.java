package testModele;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Arbitre;
import modele.Connexion;
import modele.Tournoi;

public class TestArbitre {
	
	private Arbitre arbitre;
	private Tournoi tournoi;
	private List<Tournoi> tournois;

	@Before
	public void setUp() throws Exception {
		this.arbitre = new Arbitre(0,"lopez","téo");
		this.arbitre.setPseudo("téo123");
		this.arbitre.setanneesExperience(7);
		this.tournoi = new Tournoi(0,"Tournoi E-Sport","23/11/2022","14:00","internationale");
		this.tournois = new ArrayList<Tournoi> ();
	}

	@After
	public void tearDown() throws Exception {
		this.arbitre = null;
		this.tournoi = null;
		this.tournois = null;
	}

	@Test
	public void testConstructeur() {
		assertTrue(this.arbitre instanceof Arbitre);
	}
	
	@Test
	public void testCreeLogin() throws SQLException {
		Arbitre ab = new Arbitre(1,"duval","jean");
		ab.setPseudo("jean123");
		ab.setanneesExperience(7);
		ab.creerLogin("$iutinfo");
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM sae_user,sae_arbitre WHERE sae_user.idarbitre=sae_arbitre.idarbitre and sae_arbitre.nomarbitre='duval'");
		try {
			while (rs.next()) {
				assertTrue(rs.getString(2).equals("duval"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTournoi() {
		assertEquals(this.arbitre.getTournois(), this.tournois);
	}
	
	@Test
	public void testAjouterTournoi() {
		this.arbitre.ajouterTournoi(this.tournoi);	
	}
	
	@Test
	public void testSupprimerTournoi() {
		this.arbitre.ajouterTournoi(this.tournoi);
		this.arbitre.supprimerTournoi(this.tournoi);
	}
	
	@Test
	public void testGetId() {
		assertEquals(this.arbitre.getID(), 0);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(this.arbitre.getNom(),"lopez");
	}
	
	@Test
	public void testSetNom() {
		this.arbitre.setNom("vidal");
		assertEquals(this.arbitre.getNom(),"vidal");
	}
	
	@Test
	public void testGetPrenom() {
		assertEquals(this.arbitre.getPrenom(),"téo");
	}
	
	@Test
	public void testSetPrenom() {
		this.arbitre.setPrenom("vidal");
		assertEquals(this.arbitre.getPrenom(),"vidal");
	}
	
	@Test
	public void testGetPseudo() {
		assertEquals(this.arbitre.getPseudo(), "téo123");
	}
	
	@Test
	public void testSetPseudo() {
		this.arbitre.setPseudo("téo987");
		assertEquals(this.arbitre.getPseudo(), "téo987");
	}
	
	@Test
	public void testGetAnneeExperience() {
		assertEquals(this.arbitre.getanneesExperience(), 7);
	}
	
	@Test
	public void testGetPrenomNom() {
		String prenomNom = this.arbitre.getPrenom()+" "+this.arbitre.getNom();
		assertEquals(this.arbitre.getPrenomNom(), prenomNom);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSupressionTournoiAbscent() {
		this.arbitre.supprimerTournoi(this.tournoi);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAjoutTournoiPresent() {
		this.arbitre.ajouterTournoi(this.tournoi);
		this.arbitre.ajouterTournoi(this.tournoi);
	}

}
