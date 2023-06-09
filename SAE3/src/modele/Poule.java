package modele;

public class Poule implements Cloneable{
	private int id;
    private boolean finale;
    private Equipe gagnant;
    private Equipe[] equipes;
    private int indiceCourant;

    public Poule(int id) {
    	this.id = id;
    	this.equipes = new Equipe[4];
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

	@Override
	public int hashCode() {
		return this.id * this.equipes.hashCode();
	}
	
	public int getID() {
		return this.id;
	}

	public Equipe[] getEquipes() {
		return this.equipes;
	}
	
	// Vérifie si la poule est remplie d'équipes
	public boolean estRemplie(){
		for (Equipe equipe : this.equipes) {
			if (equipe == null) {
				return false;
			}
		} 
		return true;
	}
    
	@Override
	public Poule clone() {
		Poule cloned = null;
		try {
			cloned = (Poule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloned;
	}
}
