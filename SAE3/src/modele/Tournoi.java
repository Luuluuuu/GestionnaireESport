package modele;
import java.util.ArrayList;
import java.util.List;

public class Tournoi {
	//Attributs
	private int ID;
    private String nom;
    private String date;
    private String heureDebut;
    private String echelle;
    private Arbitre arbitre;
    private Responsable responsable;
    private List<Jeu> jeux;

    /*Constructeur d'un Tournoi
     * Entrees :
     * 	nom 	String 		nom du tournoi
     * 	date 	Date 		date du tournoi
     * 	heure 	Timestamp 	heure de debut du tournoi
     * 	lieu 	String 		adresse complete ou se produit le tournoi
     * 	format 	Format 		si le tournoi est en distanciel, présentiel ou hybride
    */
    public Tournoi(int ID, String nom, String date, String heure, String echelle){
    	this.ID = ID;
    	this.nom = nom;
    	this.date = date;
    	this.heureDebut = heure;
    	this.echelle = echelle;
    	this.jeux = new ArrayList<Jeu>();
    }

    /*Retourne l'ID du tournoi*/
    public int getID() {
    	return this.ID;
    }
    
    public String getNom() {
    	return this.nom;
    }

    /*Retourne la date du tournoi*/
    public String getDate() {
    	return this.date;
    }
    
    /*Retourne l'heure de debut du tournoi*/
    public String getHeureDebut() {
        return this.heureDebut;
    }
    
    /*Retourne l'heure de debut du tournoi*/
    public String getEchelle() {
        return this.echelle;
    }
    
    public Arbitre getArbitre() {
    	return this.arbitre;
    }
    
    public Responsable getResponsable() {
    	return this.responsable;
    }
    
    public List<String> getNomJeux(){
    	List <String> nomJeux = new ArrayList<String>();
    	for (Jeu j : this.jeux) {
    		nomJeux.add(j.getNom());
    	}
    	return nomJeux;
    }
    
    public List<Jeu> getJeux(){
    	return this.jeux;
    }
    
    public Jeu getJeu(Jeu jeu) {
    	for (Jeu j : this.jeux) {
    		if (j.equals(jeu)){
    			return j;
    		}
    	}
		return null;
    }
    
    // SETTERS //
    public void setID(int ID) {
    	this.ID=ID;
    }
    
    /*Modifie le nom du tournoi
     * Entree :
     * 	nom	String	nouveau nom de tournoi*/
    public void setNom(String nom) {
    	this.nom = nom;
    }
    
    /*Modifie la date d'un tournoi
     * Entree :
     * 	date	Date	nouvelle date de tournoi*/
    public void setDate(String date) {
    	this.date = date;
    }
    
    /*Modifie l'heure de debut d'un tournoi
     * Entree :
     * 	heureDebut	Timestamp	nouvelle heure de debut
    */
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
    
    public void setArbitre(Arbitre a) {
    	this.arbitre = a;
    }
    
    public void setResponsable(Responsable r) {
    	this.responsable = r;
    }
    
    /* Ajouter un jeu au tournoi
     * Entree :
     * 	Jeu jeu a ajouter au tournoi
    */
    public void ajouterJeu(Jeu jeu) {
    	this.jeux.add(jeu);
    }
    
    /* Inscrire une équipe au tournoi
     * Entree :
     * 	Jeu jeu dans lequel inscrire l'équipe
     * 	Equipe equipe à inscrire au tournoi
     */
    public void inscrireEquipe(Jeu jeu, Equipe equipe) {
    	Jeu j = this.getJeu(jeu);
    	j.inscrire(equipe);
    }
}
