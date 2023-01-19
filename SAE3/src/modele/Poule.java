package modele;
import java.util.ArrayList;
import java.util.List;

public class Poule {
	private int ID;
    private boolean finale;
    private Equipe gagnant;
    private Equipe[] equipes;
    private int indiceCourant;

    public Poule(int ID) {
    	this.ID = ID;
    	this.equipes = new Equipe[5];
    	this.indiceCourant = 0;
    }
    
    public void ajouterEquipe(Equipe e) {
    	this.equipes[indiceCourant] = e;
    	indiceCourant++;
    }
    
    public Equipe getGagnant() {
        return this.gagnant;
    }

    public void setGagnant(Equipe e) {
        this.gagnant = e;
    }

    public boolean getType() {
    	return this.finale;
    }

    public void setFinale(boolean finale) {
    	this.finale = finale;
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
		Poule p = (Poule) obj;
		return this.getType()==p.getType() && this.getGagnant().equals(p.getGagnant());
	}

	public int getID() {
		return this.ID;
	}
    
}
