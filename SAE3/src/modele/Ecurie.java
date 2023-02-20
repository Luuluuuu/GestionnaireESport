package modele;
import java.util.ArrayList;
import java.util.List;

import modele.Utilisateur.Profil;

public class Ecurie implements Cloneable {
	//Declaration de variables
    private String nom;
    private int anneeDeCreation;
    private List<Equipe> equipes = new ArrayList<Equipe> ();
	private int ID;
    
    /*Constructeur d'une Ecurie
     * Entrées : 
     * 		un nom de type String
     * 		une anneeDeCreation de type Date
    */
    public Ecurie(int ID, String nom) {
    	this.ID = ID;
    	this.nom = nom;
    }
    
    public void creerLogin(String mdp) {
    	Utilisateur.ajouterUtilisateur(nom, mdp, Profil.ECURIE, this.ID);
    }
    
    // GETTERS //

	public int getID() {
		return this.ID;
	}
	
    //retourne le nom de l'écurie
    public String getNom() {
        return this.nom;
    }
    
    //retourne la date de creation de l'écurie
    public int getAnneeDeCreation() {
        return this.anneeDeCreation;
    }
    
	public void setNom(String nom) {
		this.nom = nom;		
	}
    
    /*Change la date de creation de l'ecurie
     * Pre-conditions :
     * 		la date n'est pas supérieure à celle du jour*/
    public void setAnneeDeCreation(int anneeDeCreation) {
        this.anneeDeCreation = anneeDeCreation;
    }

    /*Supprime une equipe dans l'ecurie
     * Pre-conditions :
     * 		l'equipe existe dans l'ecurie
     * Entree :
     * 		une equipe de type Equipe*/
    public void supprimerEquipe(Equipe equipe) throws IllegalArgumentException{
    	if (!(this.equipes.contains(equipe))) {
    		throw new IllegalArgumentException("L'equipe n'appartient pas a l'ecurie");
    	}
    	this.equipes.remove(equipe);
    }

    /*Ajoute une equipe a l'ecurie
     * Pre-conditions :
     * 		l'equipe n'existe pas dans l'ecurie
     * Entree :
     * 		une equipe de type Equipe*/
    public void ajouterEquipe(Equipe equipe) throws IllegalArgumentException{
    	if (this.equipes.contains(equipe)) {
    		throw new IllegalArgumentException("Cette equipe appartient deja a l'ecurie");
    	}
        this.equipes.add(equipe);
    }

    /*Renvoie l'equipe a partir de son nom
     * Entree :
     * 		un nom de type String
     * Sortie :
     * 		une equipe de type Equipe*/
    public Equipe getEquipe(int indice) {
    	return this.equipes.get(indice);
    }

    /*Renvoie la liste des equipes de l'ecurie*/
    public List<Equipe> getEquipes() {
    	return this.equipes;
    }
    
    public Ecurie clone() {
    	Ecurie cloned = null;
    	try {
			cloned = (Ecurie) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return cloned;
    }
}
