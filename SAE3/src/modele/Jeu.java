package modele;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
	private int ID;
    private String nom;
    private int nbJoueurs;
    private List<Equipe> equipes;
    
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
	public void inscrire(Equipe equipe) {
		this.equipes.add(equipe);
	}

}
