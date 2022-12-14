package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import modele.Arbitre;
import modele.Connexion;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Responsable;
import modele.Tournoi;
import modele.Utilisateur;
import vue.VueConnexion;
import vue.VueCalendrier;

public class ControleurConnexion implements ActionListener {	
	private VueConnexion vue;
	
	static	Map<String, Tournoi> 		listeTournois;
	static	Map<String, Responsable> 	listeResponsables;
	static	Map<String, Arbitre>		listeArbitres;
	static 	Map<String, Jeu>			listeJeux;
	static 	Map<String, Ecurie> 		listeEcuries;
	static	Map<String, Equipe> 		listeEquipes;
	static	Map<String, Joueur> 		listeJoueurs;
	
	private Map<Integer, Responsable> 	listeResponsablesID;
	private Map<Integer, Arbitre>		listeArbitresID;
	static 	Map<Integer, Jeu>			listeJeuxID;
	static	Map<Integer, Equipe> 		listeEquipesID;
	
	public ControleurConnexion(VueConnexion vue) {
		this.vue = vue;
		Connexion.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.vue.estRemplie()) {
			try {
				if (Utilisateur.mdpCorrect(this.vue.getLogin(), this.vue.getMotDePasse())) {
					// INITIALISER LES LISTES //
					initialiserListes();
					
					VueCalendrier fen = new VueCalendrier();
					fen.getFrame().setVisible(true);
					VueConnexion.fermerFenetre(this.vue.fenetreConnexion);					
				} else {
					this.vue.connexionEchoue();
				}
			} catch (SQLException ee) {
				ee.printStackTrace();
				this.vue.connexionEchoue();
			}
		} else {
			this.vue.connexionEchoue();	
		}
	}
	
	public void initialiserListes() {
		this.initialiserListeResponsables();
		this.initialiserListeArbitres();
		this.initialiserListeJeux();
		this.initialiserListeTournois();
		this.initialiserListeEcuries();
		this.initialiserListeEquipes();
		this.initialiserListeJoueurs();
	}

	private void initialiserListeTournois() {
		ControleurConnexion.listeTournois = new HashMap<String,Tournoi>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT sae_tournoi.idtournoi, sae_tournoi.nomtournoi, to_char(sae_tournoi.datetournoi,'DD-MM-YYYY'), "
				+ "sae_tournoi.heuredebut, sae_tournoi.idarbitre, sae_tournoi.idresponsable, sae_tournoi.echelletournoi, "
				+ "sae_definir.idJeu FROM sae_definir JOIN sae_tournoi ON sae_definir.idTournoi = sae_tournoi.idTournoi");
		try {
			// INITIALISER TOURNOI //
			while (rs.next()) {
				if (ControleurConnexion.listeTournois.containsKey(rs.getString(2))) {
					ControleurConnexion.listeTournois.get(rs.getString(2)).ajouterJeu(ControleurConnexion.listeJeuxID.get(rs.getInt("IDJEU")));
				} else {					
					Tournoi t = new Tournoi(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(7));
					t.setArbitre(this.listeArbitresID.get(rs.getInt(5)));
					t.setResponsable(this.listeResponsablesID.get(rs.getInt(6)));
					t.ajouterJeu(ControleurConnexion.listeJeuxID.get(rs.getInt("IDJEU")));
					listeTournois.put(t.getNom(), t);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void initialiserListeResponsables() {
		ControleurConnexion.listeResponsables = new HashMap<String, Responsable>();
		this.listeResponsablesID = new HashMap<Integer,Responsable>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_RESPONSABLE");
		try {
			while (rs.next()) {
				Responsable r = new Responsable(rs.getInt(1),rs.getString(2), rs.getString(3));
				r.setAnneesExperience(rs.getInt(4));
				ControleurConnexion.listeResponsables.put(r.getPrenomNom(),r);
				this.listeResponsablesID.put(r.getID(), r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialiserListeArbitres() {
		ControleurConnexion.listeArbitres = new HashMap<String, Arbitre>();
		this.listeArbitresID = new HashMap<Integer,Arbitre>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_ARBITRE");
		try {
			while (rs.next()) {
				Arbitre a = new Arbitre(rs.getInt(5),rs.getString(1),rs.getString(2));
				a.setPseudo(rs.getString(3));
				a.setanneesExperience(rs.getInt(4));
				ControleurConnexion.listeArbitres.put(a.getPrenomNom(),a);
				this.listeArbitresID.put(a.getID(), a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialiserListeJeux() {
		ControleurConnexion.listeJeux = new HashMap<String, Jeu>();
		ControleurConnexion.listeJeuxID = new HashMap<Integer,Jeu>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_JEU");
		try {
			while (rs.next()) {
				Jeu j = new Jeu(rs.getInt(1),rs.getString(2),rs.getInt(3));
				ControleurConnexion.listeJeux.put(j.getNom(),j);
				ControleurConnexion.listeJeuxID.put(j.getID(), j);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initialiserListeEcuries() {
		ControleurConnexion.listeEcuries = new HashMap<String,Ecurie>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT * FROM SAE_ECURIE");
			while (rs.next()) {
				Ecurie e = new Ecurie(rs.getInt(1),rs.getString(2));
				e.setAnneeDeCreation(rs.getInt(3));
				ControleurConnexion.listeEcuries.put(e.getNom(),e);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeEquipes() {
		ControleurConnexion.listeEquipes = new HashMap<String,Equipe>();
		ControleurConnexion.listeEquipesID = new HashMap<Integer,Equipe>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT * FROM SAE_EQUIPE");
			while (rs.next()) {
				Equipe e = new Equipe(rs.getString(2), rs.getInt(4), ControleurConnexion.listeJeuxID.get(rs.getInt(7)));
				ControleurConnexion.listeEquipesID.put(rs.getInt(1), e);
				ControleurConnexion.listeEquipes.put(e.getNom(),e);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeJoueurs() {
		ControleurConnexion.listeJoueurs = new HashMap<String,Joueur>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT * FROM SAE_JOUEUR");
			while (rs.next()) {
				Joueur j = new Joueur(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				ControleurConnexion.listeJoueurs.put(j.getNom(),j);
				
				Equipe e = ControleurConnexion.listeEquipesID.get(rs.getInt(7));
				ControleurConnexion.listeEquipesID.get(rs.getInt(7)).ajouterJoueur(j);;
				ControleurConnexion.listeEquipes.get(e.getNom()).ajouterJoueur(j);
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
