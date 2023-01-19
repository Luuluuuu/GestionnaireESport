package modele;

import java.util.ArrayList;
import java.util.List;

public class Jeu implements Cloneable{
	private int ID;
    private String nom;
    private int nbJoueurs;
    private List<Equipe> equipes;
    private Poule[] poules;
    private int indiceCourant;
    
	/* Constructeur de Jeu
	 * Entrees :
	 * 	nom			String	nom du jeu
	 * 	nbJoueurs	int		nombre de joueurs pour jouer au jeu
	 */
    public Jeu(int ID, String nom, int nbJoueurs) {
    	this.ID = ID;
    	this.nom = nom;
    	this.nbJoueurs = nbJoueurs;
    	this.equipes = new ArrayList<Equipe>();
    	this.poules = new Poule[5];
    	this.indiceCourant = 0;
    }
    
    //Getters
    //Retourne l'ID du jeu
    public int getID() {
        return this.ID;
    }
    
    //Retourne le nom du jeu
    public String getNom() {
        return this.nom;
    }
    
    //Retourne le nombre de joueurs pour le jeu
    public int getNbJoueurs() {
        return this.nbJoueurs;
    }
    
    //Setters   
    /*Modifie le nom du jeu
     * Entree :
     * 	nom	String	nouveau nom du jeu
    */
    public void setNom(String nom) {
    	this.nom = nom;
    }
    
    /*Modifie le nombre de joueurs du jeu
     * Entree :
     * 	nbJoueurs	int	nouveau nombre de joueurs au jeu
    */
    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

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
		Jeu j = (Jeu) obj;
		return this.getNbJoueurs()==j.getNbJoueurs() && this.getNom().equals(j.getNom());
	}

	/* Inscrit une équipe au jeu
	 * Entree :
	 * 	Equipe equipe à inscrire 
	*/
	public void inscrire(Equipe equipe) throws IllegalArgumentException {
		if (this.equipes.contains(equipe)) {
			throw new IllegalArgumentException("Cette équipe est déjà inscrite !");
		}
		this.equipes.add(equipe);
	}

	public boolean contient(Equipe equipe) {
		return this.equipes.contains(equipe);
	}

	@Override
	public Jeu clone() {
		Jeu cloned = null;
		try {
			cloned = (Jeu) super.clone();
			cloned.equipes = new ArrayList<Equipe>();
			for (Equipe equipe : this.equipes) {
				cloned.inscrire(equipe.clone());
			}
			cloned.poules = this.poules.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloned;
		
	}

	public void ajouterPoule(Poule poule) {
		this.poules[indiceCourant] = poule;
		indiceCourant++;
	}

	public Equipe[] getEquipePouleI(int i) {
		if (this.poules[i-1] == null) {
			return new Equipe[0];
		}
		return this.poules[i-1].getEquipes();
	}

}
