package testModele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.GestionnaireESporter;

public class TestGestionnaireEsporter {

	private GestionnaireESporter gestionnaireESporter;

	@Before
	public void setUp() throws Exception {
		this.gestionnaireESporter = new GestionnaireESporter("lopez","patrice",10);
	}

	@After
	public void tearDown() throws Exception {
		this.gestionnaireESporter = null;
	}

	@Test
	public void testConstructeur() {
		assertTrue(this.gestionnaireESporter instanceof GestionnaireESporter);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(this.gestionnaireESporter.getNom(), "lopez");
	}
	
	@Test
	public void testSetNom() {
		this.gestionnaireESporter.setNom("jean");
		assertEquals(this.gestionnaireESporter.getNom(), "jean");
	}
	
	@Test
	public void testGetPrenom() {
		assertEquals(this.gestionnaireESporter.getPrenom(), "patrice");
	}
	
	@Test
	public void testSetPrenom() {
		this.gestionnaireESporter.setPrenom("luc");
		assertEquals(this.gestionnaireESporter.getPrenom(), "luc");
	}
	
	@Test
	public void testGetAnneesExperiences() {
		assertEquals(this.gestionnaireESporter.getAnneesExp(), 10);
	}
	
	@Test
	public void testSetAnneesExperiences() {
		this.gestionnaireESporter.setAnneesExp(12);
		assertEquals(this.gestionnaireESporter.getAnneesExp(), 12);
	}
}
