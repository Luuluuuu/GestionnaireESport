package testModele;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.Tournoi;

public class TestTournoi {
	private Tournoi t;
	
	@Before
	public void setUp() {
		this.t = new Tournoi(1,"Tournoi E-Sport","23/11/2022","14:00","internationale");
	}
	
	@After
	public void tearDown() {
		this.t = null;
	}
	
	@Test
	public void testConstructeurTournoi() {
		assertTrue(t instanceof Tournoi);
	}

	@Test
	public void testGetID() {
		assertEquals(t.getID(),1);
	}
	
	@Test
	public void testGetNom() {
		assertEquals(t.getNom(),"Tournoi E-Sport");
	}
	
	@Test
	public void testGetDate() {
		assertEquals(t.getDate(),"23/11/2022");
	}
	
	@Test
	public void testGetEchelle() {
		assertEquals(t.getEchelle(),"internationale");
	}
	
	@Test
	public void testGetHeureDebut() {
		assertEquals(t.getHeureDebut(),"14:00");
	}
	
	@Test
	public void testSetNom() {
		t.setNom("Tournoi LoL");
		assertEquals(t.getNom(),"Tournoi LoL");
	}
	
	@Test
	public void testSetDate() {
		t.setDate("22/12/2010");
		assertEquals(t.getDate(),"22/12/2010");
	}
	
	@Test
	public void testSetHeureDebut() {
		t.setHeureDebut("15:00");
		assertEquals(t.getHeureDebut(),"15:00");
	}
}
