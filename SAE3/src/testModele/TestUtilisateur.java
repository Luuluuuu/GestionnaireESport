package testModele;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Utilisateur;

public class TestUtilisateur {
	private Utilisateur u ;
	
	@Before
	public void setUp() {
		u = new Utilisateur();
	}
	
	@After
	public void tearDown() {
		u = null;
	}
	/*
	@Test
	public void testMotDePasseCorrect() throws SQLException {
		assertTrue(u.mdpCorrect("admin", "$esporter")); 
	}*/

}
