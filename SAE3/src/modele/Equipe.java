package modele;
import java.util.ArrayList;
import java.util.List;

public class Equipe implements Comparable<Equipe>, Cloneable {
	//Declaration d'attributs
	private int ID;
    private String nom;
    private int pointsChampionnat;
    private List<Joueur> joueurs = new ArrayList<Joueur> ();
    private Jeu jeu;
    private Ecurie ecurie;
    private List<Poule> poules = new ArrayList<Poule> ();
    private String nationalite;

    /*Constructeur d'Equipe
     * Entree :
     * 		un nom de type String
     * 		les points de championnat de type int
     * 		un jeu de type Jeu
     * 		une liste de Joueurs de type List<Joueur>*/
    public Equipe(int ID, String nom, int pointsChampionnat,String nationalite, Jeu jeu, Ecurie ecurie) {
    	this.ID = ID;
    	this.nom = nom;
    	this.pointsChampionnat = pointsChampionnat;
    	this.nationalite = nationalite;
    	this.jeu = jeu;
    	this.ecurie = ecurie;
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

    //Retourne le jeu de l'equipe
    public Ecurie getEcurie() {
    	return this.ecurie;
    }
    
    
    //Retourne le jeu de l'equipe
    public String getNomJeu() {
    	return this.jeu.getNom();
    }
    
	public String getNationalite() {
		return this.nationalite;
	}
 
	public int getID() {
		return this.ID;
	}
    
	 //Retourne les points de l'equipe au championnat
    public int getPointsChampionnat() {
        return this.pointsChampionnat;
    }

    //Retourne la liste des poules auquelles est inscrites l'equipe
    public List<Poule> getPoules() {
        return this.poules;
    }
    
    //Modifie le jeu de l'equipe
    public void setJeu(Jeu jeu) {
    	this.jeu = jeu;
    }

	public void setID(int ID) {
		this.ID = ID;
	}
    
	// Modifie l'écurie
	private void setEcurie(Ecurie ecurie) {
		this.ecurie = ecurie;
	}
	
    //Ajoute une poule à la liste des poules de l'equipe
    public void ajouterPoule(Poule poule) {
        this.poules.add(poule);
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
	public int compareTo(Equipe e) {
		if (this.pointsChampionnat==e.getPointsChampionnat()) {
			return 0;
		} else if(this.pointsChampionnat>e.getPointsChampionnat()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	@Override
	public Equipe clone() throws CloneNotSupportedException {
		Equipe cloned = (Equipe) super.clone();
		
		cloned.setEcurie(this.ecurie.clone());
		
		cloned.joueurs = new ArrayList<Joueur>();
		for (Joueur j : this.joueurs) {
			cloned.ajouterJoueur(j);
		}
		
		cloned.poules = new ArrayList<Poule>();
		for (Poule p : this.poules) {
			cloned.ajouterPoule(p);
		}
		
		return cloned;
	}

}
