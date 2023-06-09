package modele;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {
    public static int idCourant;

    private Utilisateur() {
	    throw new IllegalStateException("Classe sans construction");
    }
    
    public static void ajouterUtilisateur(String nom, String mdp,Profil profil, int ID) {
    	String identifiant = (nom.replaceAll("\\s+", "_")).toLowerCase();
    	switch (profil) {
    	case RESPONSABLE:
        	Connexion.getInstance().executerRequete("INSERT INTO SAE_USER (IDUSER,LOGIN,MOTDEPASSE,IDRESPONSABLE) VALUES (SEQ_USERID.NEXTVAL,'"
        			+ identifiant + "','" + mdp.hashCode() +"',"+ ID+")");
    		break;
    	case ECURIE:
    		Connexion.getInstance().executerRequete("INSERT INTO SAE_USER (IDUSER,LOGIN,MOTDEPASSE,IDECURIE) VALUES (SEQ_USERID.NEXTVAL,'"
        			+ identifiant + "','" + mdp.hashCode() +"',"+ ID+")");
    		break;
    	case JOUEUR:
    		Connexion.getInstance().executerRequete("INSERT INTO SAE_USER (IDUSER,LOGIN,MOTDEPASSE,IDJOUEUR) VALUES (SEQ_USERID.NEXTVAL,'"
        			+ identifiant + "','" + mdp.hashCode() +"',"+ ID+")");
    		break;
    	case ARBITRE:
    		Connexion.getInstance().executerRequete("INSERT INTO SAE_USER (IDUSER,LOGIN,MOTDEPASSE,IDARBITRE) VALUES (SEQ_USERID.NEXTVAL,'"
        			+ identifiant + "','" + mdp.hashCode() +"',"+ ID+")");
    		break;
		default:
			break;
    	}
    }

    public static Profil mdpCorrect(String identifiantSaisi, String mdpSaisi) throws SQLException {
		ResultSet rs = Connexion.getInstance().retournerRequete("SELECT * FROM SAE_USER WHERE LOGIN = '"+identifiantSaisi+"'");
    	if (rs.next()) {
			String resultat = rs.getString(3);
	    	if (resultat.equals(String.valueOf(mdpSaisi.hashCode()))) {
	    		// Vérification du profil utilisateur
	    		if (identifiantSaisi.equals("admin")) {
	    	    	rs.close();
	    			return Profil.GESTIONNAIRE;
	    		}
	    		if (rs.getInt("IDRESPONSABLE") != 0) {
	    			idCourant = rs.getInt("IDRESPONSABLE");
	    	    	rs.close();
	    			return Profil.RESPONSABLE;
	    		}
	    		if (rs.getInt("IDARBITRE") != 0) {
	    			idCourant = rs.getInt("IDARBITRE");
	    	    	rs.close();
	    			return Profil.ARBITRE;
	    		}
	    		if (rs.getInt("IDECURIE") != 0) {
	    			idCourant = rs.getInt("IDECURIE");
	    	    	rs.close();
	    			return Profil.ECURIE;
	    		}
	    		if (rs.getInt("IDJOUEUR") != 0) {
	    			idCourant = rs.getInt("IDJOUEUR");
	    	    	rs.close();
	    			return Profil.JOUEUR;
	    		}
	    	};
	    	rs.close();
	    }
	    return null;
    }
}