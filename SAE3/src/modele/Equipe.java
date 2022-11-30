package modele;
import java.util.ArrayList;
import java.util.List;

public class Equipe implements Comparable {
	//Declaration d'attributs
    private String nom;
    private int pointsChampionnat;
    private List<Joueur> joueurs = new ArrayList<Joueur> ();
    private Jeu jeu;
    private List<Poule> poule = new ArrayList<Poule> ();

    /*Constructeur d'Equipe
     * Entree :
     * 		un nom de type String
     * 		les points de championnat de type int
     * 		un jeu de type Jeu
     * 		une liste de Joueurs de type List<Joueur>*/
    public Equipe(String nom, int pointsChampionnat, Jeu jeu, List<Joueur> joueurs) {
    	this.nom = nom;
    	this.pointsChampionnat = pointsChampionnat;
    	this.joueurs = joueurs;
    }
    
    /*Retourne le nom de l'equipe de type String*/
    public String getNom() {
        return this.nom;
    }

    /*Modifie le nom de l'equipe*/
    public void setNom(String nom) {
        this.nom = nom;
    }

    /*Retourne la liste des joueurs de l'equipe*/
    public List<Joueur> getJoueurs() {
    	return this.joueurs;
    }

    /*Ajoute un joueur à l'equipe
     * Pre-condition :
     * 		le joueur n'est pas dans l'ecurie
     * Entree :
     * 		un joueur de type Joueur*/
    public void ajouterJoueur(Joueur joueur) {
    	this.joueurs.add(joueur);
    }

    //Retourne le jeu de l'equipe
    public Jeu getJeu() {
    	return this.jeu;
    }
    
    //Modifie le jeu de l'equipe
    public void setJeu(Jeu jeu) {
    	this.jeu = jeu;
    }

    //Retourne les points de l'equipe au championnat
    public int getPointsChampionnat() {
        return this.pointsChampionnat;
    }

    //Retourne la liste des poules auquelles est inscrites l'equipe
    public List<Poule> getPoule() {
        return this.poule;
    }
    
    //Ajoute une poule à la liste des poules de l'equipe
    public void ajouterPoule(Poule poule) {
        this.poule.add(poule);
    }

    /*Ajoute de nouveaux points aux points de l'équipe
     * Pre-conditions :
     * 		les points ne sont pas inférieurs ou égaux à 0
     * Entrée : 
     *		les points en int*/
    public void ajouterPointsChampionnat(int points) {
    	this.pointsChampionnat+=points;
    }
    
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Equipe)) {
			return false;
		}
		Equipe e = (Equipe) obj;
		return this.getNom().equals(e.getNom()) &&	this.getJeu().equals(e.getJeu()) && 
				this.getJoueurs().equals(e.getJoueurs()) && this.getPointsChampionnat()==e.getPointsChampionnat();
	}

	@Override
	public int compareTo(Object o) {
		Equipe e = (Equipe) o;
		if (this.pointsChampionnat==e.getPointsChampionnat()) {
			return 0;
		} else if(this.pointsChampionnat>e.getPointsChampionnat()) {
			return 1;
		} else {
			return -1;
		}
	}

}
