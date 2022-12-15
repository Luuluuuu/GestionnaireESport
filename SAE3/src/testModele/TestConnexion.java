package testModele;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modele.Connexion;

public class TestConnexion {
	private Connexion c;
	@Before
	public void setUp() throws SQLException {
		try {
			c = Connexion.getInstance();
		} catch(Exception e){
			Assert.fail("La connexion a echoue.");
		}
	}
	
	@After
	public void tearDown() {
		try {
			Connexion.fermerConnexion();
			this.c = null;
		} catch(Exception e) {
			Assert.fail("La fermeture de la connexion a echoue.");
		}
	}
	
	@Test
	public void testConstructeur() throws SQLException {
		assertTrue(c instanceof Connexion);
	}
}