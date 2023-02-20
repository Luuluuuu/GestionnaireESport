package modele;

import java.awt.AWTEvent;
import java.util.Date;

import modele.Utilisateur.Profil;

public class Joueur {
	//Déclaration d'attributs
    private int ID;
    private String nom;
    private String prenom;
    private String pseudo;
    private String dateNaissance;
    private String nationalite;
    private Equipe equipe;
    private String photo;
    
    /* Constructeur de Joueur
     * Entrees :
     * 	nom				String	nom du joueur
     * 	prenom			String	prenom du joueur
     * 	pseudo			String	pseudo du joueur
     * 	dateNaissance	String	date de naissance du joueur
     * 	nationalite		String	nationalite du joueur
    */
    public Joueur(int ID, String nom, String prenom, String pseudo, String string, String nationalite, Equipe equipe, String photo) {
    	this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.dateNaissance = string;
        this.nationalite = nationalite;
        this.equipe = equipe;
        this.photo = photo;
    }
    
    //Getters
    /*Retourne le nom du joueur*/
    public String getNom() {
        return this.nom;
    }

    /*Retourne le prenom du joueur*/
    public String getPrenom() {
        return this.prenom;
    }
    
    /*Retourne le prenom, le pseudo et le nom du joueur*/
    public String getPrenomPseudoNom() {
        return this.prenom + " (" + this.pseudo + ") " + this.nom;
    }

    /*Retourne le pseudo du joueur*/
    public String getPseudo() {
        return this.pseudo;
    }

    /*Retourne la date de naissance du joueur*/
    public String getDateNaissance() {
        return this.dateNaissance;
    }
    
    /*Retourne la nationalite du joueur*/
    public String getNationalite() {
        return this.nationalite;
    }
    
    //Setters
    /*Modifie le nom du joueur
     * Entree :
     * 	nom	String	nom du joueur
    */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /*Modifie le prenom du joueur
     * Entree :
     * 	prenom	String	prenom du joueur
    */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /*Modifie le pseudo du joueur
     * Entree :
     * 	pseudo	String	pseudo du joueur
    */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /*Modifie la date de naissance du joueur
     * Entree :
     * 	dateNaissance	String	date de naissance du joueur
    */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /*Modifie la nationalite du joueur
     * Entree :
     * 	nationalite	String	nationalite du joueur
    */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

	public void setID(int ID) {
		this.ID = ID;
	}

    /*Retourne VRAI si le joueur est egal a obj
     * FAUX sinon*/
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Jeu)) {
			return false;
		}
		Joueur j = (Joueur) obj;
		return this.getDateNaissance().equals(j.getDateNaissance()) &&
				this.getNationalite().equals(j.getNationalite()) &&
				this.getNom().equals(j.getNom()) &&
				this.getPrenom().equals(j.getPrenom()) &&
				this.getPseudo().equals(j.getPseudo());
	}
	
	@Override
	public String toString() {
		return this.getPrenomPseudoNom();
	}

	public int getID() {
		return this.ID;
	}

	public Equipe getEquipe() {
		return this.equipe;
	}

	public void creerLogin(String mdp) {
    	Utilisateur.ajouterUtilisateur(nom+"."+prenom, mdp, Profil.JOUEUR, this.ID);
	}

	public String getPhoto() {
		return this.photo;
	}
}
