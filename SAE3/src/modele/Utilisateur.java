package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Utilisateur {
	public enum Profil{ARBITRE,RESPONSABLE,GESTIONNAIRE,JOUEUR,ECURIE}
    private static HashMap<String,Integer> liste = new HashMap<String,Integer>();

    public Utilisateur() {
    }
    
    public static void ajouterUtilisateur(String nom, String mdp,Profil profil, int ID) {
    	String identifiant = nom.replaceAll("\\s+", "_").toLowerCase();
    	Utilisateur.liste.put(identifiant,mdp.hashCode());
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
		default:
			break;
    	}
    }

    public static boolean mdpCorrect(String identifiantSaisi, String mdpSaisi) throws SQLException {
		ResultSet rs = Connexion.getInstance().retournerRequete("SELECT * FROM SAE_USER WHERE LOGIN = '"+identifiantSaisi+"'");
    	if (rs.next()) {
			String resultat = rs.getString(3);
			rs.close();
	    	return resultat.equals(String.valueOf(mdpSaisi.hashCode()));}
	    else {return false;}
    }
}