package modele;

import modele.Utilisateur.Profil;

public class Responsable {
	private int ID;
    private String nom;
    private String prenom;
    private int anneesExperience;

    public Responsable(int ID, String nom, String prenom) {
    	this.ID = ID;
    	this.nom = nom;
    	this.prenom = prenom;
    }

    public void creerLogin(String mdp) {
    	Utilisateur.ajouterUtilisateur(nom+"."+prenom, mdp, Profil.ECURIE, this.ID);
    }
    
    // GETTERS //
    public int getID() {
        return this.ID;
    }
    
    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public int getAnneesExperience() {
        return this.anneesExperience;
    }

    // SETTERS //
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAnneesExperience(int anneesExperience) {
        this.anneesExperience = anneesExperience;
    }

	public String getPrenomNom() {
		return this.prenom + " " + this.nom;
	}

}
