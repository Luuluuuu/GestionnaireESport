package modele;

public enum Profil{
	ARBITRE("Arbitre"),
	RESPONSABLE("Responsable"),
	GESTIONNAIRE("Gestionnaire"),
	JOUEUR("Joueur"),
	ECURIE("Ecurie");

	private String nom;
	
	private Profil(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
	
}