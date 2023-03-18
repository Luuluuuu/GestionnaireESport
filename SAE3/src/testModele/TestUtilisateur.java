package testModele;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import modele.Connexion;
import modele.Utilisateur;
import modele.Profil;

public class TestUtilisateur {
	@Test
	public void testAjouterUtilisateur() {
		Utilisateur.ajouterUtilisateur(("vidal"+"."+"jean"), "$iutinfo", Profil.ARBITRE, 1);
		Connexion c = Connexion.getInstance();
		//faire la requete par rapport a un utilisateur et tester tout les choix
		ResultSet rs = c.retournerRequete("SELECT * FROM sae_user,sae_arbitre WHERE sae_user.idarbitre=sae_arbitre.idarbitre and sae_arbitre.nomarbitre='duval'");
		try {
			while (rs.next()) {
				assertTrue(rs.getString(1).equals("vidal"));
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mdpCorrect() {
		
	}

}
