package modele;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Utilisateur.Profil;

public class Arbitre {
	//Declaration des variables
	private int ID;
    private String nom;
    private String prenom;
    private String pseudo;
    private int anneesExperience;
    private List<Tournoi> tournois = new ArrayList<Tournoi> ();
    
    /*Constructeur d'un Arbitre
     * Entrées : 
     * 		un nom de type String
     * 		un prenom de type String
     * 		un pseudo de type String
     * 		ses anneeExp de type int
     * 		un mdp de type String (généré par le gestionnaire d'E-Sporter)
    */
    public Arbitre(int ID, String nom, String prenom) {
    	this.ID = ID;
    	this.nom = nom;
    	this.prenom = prenom;
    }

	//compose un login sous forme nom.prenom_compose et l'enregistre dans le tableau des Utilisateurs
    public void creerLogin(String mdp) {
    	Utilisateur.ajouterUtilisateur((nom+"."+prenom), mdp, Profil.ARBITRE, this.ID);
    }
    
    /*Retourne la liste des Tournois assignees à l'arbitre*/
    public List<Tournoi> getTournois() {
        return this.tournois;
    }
    
    /*Assigne un nouveau tournoi à l'arbitre
     * Entrée :
     * 		Un tournoi de type Tournoi
     * Pré-condition : le tournoi n'est pas déjà assigné à l'arbitre
    */
    public void ajouterTournoi(Tournoi tournoi) throws IllegalArgumentException{
    	if (this.tournois.contains(tournoi)) {
    		throw new IllegalArgumentException("L'arbitre est déjà assigné au tournoi.");
    	}
    	this.tournois.add(tournoi);
    }
    
    /*Supprime un tournoi à l'arbitre
     * Pré-conditions : le tournoi est assigné à l'arbitre
     * Entree :
     * 		un tournoi de type Tournoi*/
    public void supprimerTournoi(Tournoi tournoi) throws IllegalArgumentException{
    	if (!(this.tournois.contains(tournoi))) {
    		throw new IllegalArgumentException("L'arbitre n'est pas assigné à ce tournoi.");
    	}
    	this.tournois.remove(tournoi);
    }
    //GETTERS
    /*Retourne le nom l'arbitre*/
    public int getID() {
        return this.ID;
    }
    
    /*Retourne le nom l'arbitre*/
    public String getNom() {
        return this.nom;
    }

    /*Modifie le nom l'arbitre*/
    public void setNom(String nom) {
        this.nom = nom;
    }

    /*Retourne le prenom l'arbitre*/
    public String getPrenom() {
        return this.prenom;
    }

    /*Change le prenom l'arbitre*/
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /*Retourne le pseudo l'arbitre*/
    public String getPseudo() {
        return this.pseudo;
    }

    /*Change le pseudo l'arbitre*/
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /*Retourne les annees d'experience de l'arbitre*/
    public int getanneesExperience() {
        return this.anneesExperience;
    }

    /*Change les annees d'experience de l'arbitre
     * Entree :
     * 		les annees d'experience de type int*/
    public void setanneesExperience(int anneesExperience) {
        this.anneesExperience = anneesExperience;
    }

	public String getPrenomNom() {
		return this.prenom + " " + this.nom;
	}
}
