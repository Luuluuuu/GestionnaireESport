package testModele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Responsable;

public class TestResponsable {
	
	private Responsable responsable;

	@Before
	public void setUp() throws Exception {
		this.responsable = new Responsable(0, "perbost", "jean");
		this.responsable.setAnneesExperience(5);
	}

	@After
	public void tearDown() throws Exception {
		this.responsable = null;
	}

	@Test
	public void testConstructeur() {
		assertTrue(this.responsable instanceof Responsable);
	}
	
	@Test
	public void testGetId() {
		assertEquals(this.responsable.getID(), 0);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(this.responsable.getNom(), "perbost");
	}
	
	@Test
	public void testGetPrenom() {
		assertEquals(this.responsable.getPrenom(), "jean");
	}
	
	@Test
	public void testGetAnneesExperience() {
		assertEquals(this.responsable.getAnneesExperience(), 5);
	}
	
	@Test
	public void testSetPrenom() {
		this.responsable.setPrenom("martin");
		assertEquals(this.responsable.getPrenom(), "martin");
	}
	
	@Test
	public void testSetNom() {
		this.responsable.setNom("duval");
		assertEquals(this.responsable.getNom(), "duval");
	}
	
	@Test
	public void testSetAnnesExperience() {
		this.responsable.setAnneesExperience(1);;
		assertEquals(this.responsable.getAnneesExperience(), 1);
	}
	
	@Test
	public void testGetPrenomNom() {
		String prenomNom = this.responsable.getPrenom()+" "+this.responsable.getNom();
		assertEquals(this.responsable.getPrenomNom(), prenomNom);
	}

}
