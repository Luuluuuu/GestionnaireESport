package modele;

public class GestionnaireESporter {
	//Declaration de variables
    private String nom;
    private String prenom;
    private int anneesExp;

    public GestionnaireESporter(String nom, String prenom, int anneesExp) {
    	this.nom = nom;
    	this.prenom = prenom;
    	this.anneesExp = anneesExp;
    }
    
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAnneesExp() {
        return this.anneesExp;
    }

    public void setAnneesExp(int anneesExp) {
        this.anneesExp = anneesExp;
    }

}
