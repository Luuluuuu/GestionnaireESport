package modele;
import java.util.ArrayList;
import java.util.List;

public class Poule {
    private boolean finale;
    private Equipe gagnant;
    private List<Equipe> equipes = new ArrayList<Equipe> ();

    public Poule(List<Equipe> equipes,boolean finale) {
    	this.equipes = equipes;
    	this.finale = finale;
    }
    
    public Equipe getGagnant() {
        return this.gagnant;
    }

    public void setGagnant(Equipe value) {
        this.gagnant = value;
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
    
}
