package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modele.Arbitre;
import modele.Connexion;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Poule;
import modele.Responsable;
import modele.Tournoi;
import modele.Utilisateur;
import modele.Utilisateur.Profil;
import vue.VueConnexion;
import vue.VueConsulterEquipes;
import vue.VueEquipe;
import vue.VueProfilJoueur;
import vue.VueRentrerPoints;
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
	static	Map<Integer, Ecurie>		listeEcuriesID;
	static	Map<Integer, Poule>			listePoulesID;
	
	static 	List<String>				listeEquipesParEcurie;
	static 	List<String>				listeJoueursParEcurie;
	
	public 	static Utilisateur.Profil 	profilUtilisateur;
	
	public ControleurConnexion(VueConnexion vue) {
		this.vue = vue;
		Connexion.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.vue.estRemplie()) {
			try {
				profilUtilisateur = Utilisateur.mdpCorrect(this.vue.getLogin(), this.vue.getMotDePasse());
				if (profilUtilisateur == null) {
					this.vue.connexionEchoue();					
				} else {
					switch (profilUtilisateur) {
					case GESTIONNAIRE:
						initialiserListes();
						VueCalendrier fenAdmin = new VueCalendrier();
						fenAdmin.getFrame().setVisible(true);
						VueConnexion.fermerFenetre(this.vue.fenetreConnexion);		
						break;
					case RESPONSABLE:
						initialiserListes();
						VueConsulterEquipes fenResponsable = new VueConsulterEquipes();
						fenResponsable.getFrame().setVisible(true);
						VueConnexion.fermerFenetre(this.vue.fenetreConnexion);
						break;
					case ARBITRE:
						initialiserListes();
						VueRentrerPoints fenArbitre = new VueRentrerPoints();
						fenArbitre.getFrame().setVisible(true);
						VueConnexion.fermerFenetre(this.vue.fenetreConnexion);
						break;
					case ECURIE:
						initialiserListes();
						VueEquipe fenEquipe = new VueEquipe();
						fenEquipe.getFrame().setVisible(true);
						VueConnexion.fermerFenetre(this.vue.fenetreConnexion);
						break;
					case JOUEUR:
						initialiserListes();
						VueProfilJoueur fenJoueur = new VueProfilJoueur();
						fenJoueur.getFrame().setVisible(true);
						VueConnexion.fermerFenetre(this.vue.fenetreConnexion);
						break;
					}
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
		this.intialiserListePoules();
		this.initialiserListeEquipesPoulesJeu();
	}

	private void initialiserListeTournois() {
		ControleurConnexion.listeTournois = new HashMap<String,Tournoi>();
		Connexion c = Connexion.getInstance();
		ResultSet rs;
		if (ControleurConnexion.profilUtilisateur == Profil.ARBITRE) {
			rs = c.retournerRequete("SELECT sae_tournoi.idtournoi, sae_tournoi.nomtournoi, to_char(sae_tournoi.datetournoi,'DD/MM/YYYY'),"
					+ "sae_tournoi.heuredebut, sae_tournoi.idarbitre, sae_tournoi.idresponsable, sae_tournoi.echelletournoi,"
					+ "sae_definir.idJeu FROM sae_definir JOIN sae_tournoi ON sae_definir.idTournoi = sae_tournoi.idTournoi "
					+ "WHERE sae_tournoi.idarbitre = " + Utilisateur.IDCourant);
		} else if (ControleurConnexion.profilUtilisateur == Profil.RESPONSABLE){
			rs = c.retournerRequete("SELECT sae_tournoi.idtournoi, sae_tournoi.nomtournoi, to_char(sae_tournoi.datetournoi,'DD/MM/YYYY'),"
					+ "sae_tournoi.heuredebut, sae_tournoi.idarbitre, sae_tournoi.idresponsable, sae_tournoi.echelletournoi,"
					+ "sae_definir.idJeu FROM sae_definir JOIN sae_tournoi ON sae_definir.idTournoi = sae_tournoi.idTournoi "
					+ "WHERE sae_tournoi.idresponsable = " + Utilisateur.IDCourant);
		}else {
			rs = c.retournerRequete("SELECT sae_tournoi.idtournoi, sae_tournoi.nomtournoi, to_char(sae_tournoi.datetournoi,'DD/MM/YYYY'), "
					+ "sae_tournoi.heuredebut, sae_tournoi.idarbitre, sae_tournoi.idresponsable, sae_tournoi.echelletournoi, "
					+ "sae_definir.idJeu FROM sae_definir JOIN sae_tournoi ON sae_definir.idTournoi = sae_tournoi.idTournoi");
		}
		try {
			// INITIALISER TOURNOI //
			while (rs.next()) {
				// TODO
				if (ControleurConnexion.listeTournois.containsKey(rs.getString(2))) {
					Jeu j = ControleurConnexion.listeJeuxID.get(rs.getInt("IDJEU")).clone();
					Tournoi t = ControleurConnexion.listeTournois.get(rs.getString(2));
					t.ajouterJeu(j);
				} else {
					Tournoi t = new Tournoi(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(7));
					t.setArbitre(this.listeArbitresID.get(rs.getInt(5)));
					t.setResponsable(this.listeResponsablesID.get(rs.getInt(6)));
					t.ajouterJeu(ControleurConnexion.listeJeuxID.get(rs.getInt("IDJEU")).clone());
					listeTournois.put(t.getNom(), t);
				}
			}
		rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void intialiserListePoules() {
		ControleurConnexion.listePoulesID = new HashMap<Integer, Poule>();
		try {
			ResultSet rs = Connexion.getInstance().retournerRequete("SELECT * FROM SAE_POULE");
			while (rs.next()) {
				Poule poule = new Poule(rs.getInt(1));
				ControleurConnexion.listePoulesID.put(rs.getInt(1), poule);

				Statement st = Connexion.getInstance().getStatement();
				ResultSet rs2 = st.executeQuery("SELECT IDEQUIPE FROM SAE_COMPOSER WHERE IDPOULE = "+poule.getID());
				while (rs2.next()) {
					poule.ajouterEquipe(ControleurConnexion.listeEquipesID.get(rs2.getInt(1)));
				}
				st.close();
				rs2.close();
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialiserListeEquipesPoulesJeu() {
		for (Tournoi t : ControleurConnexion.listeTournois.values()) {
			for (Jeu j : t.getJeux()) {
				j.setIndiceCourant(0);
				try {
					ResultSet rs = Connexion.getInstance().retournerRequete("SELECT IDEQUIPE FROM SAE_INSCRIRE WHERE IDJEU = "+j.getID()+" AND IDTOURNOI = "+t.getID());
					while (rs.next()) {
						j.inscrire(ControleurConnexion.listeEquipesID.get(rs.getInt(1)));
					}

					String req = "SELECT IDPOULE FROM SAE_POULE WHERE IDJEU = "+j.getID()+" AND IDTOURNOI = "+t.getID()+" ORDER BY 1";
					rs = Connexion.getInstance().retournerRequete(req);
					while (rs.next()) {
						j.ajouterPoule(ControleurConnexion.listePoulesID.get(rs.getInt(1)));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		ControleurConnexion.listeEcuriesID = new HashMap<Integer,Ecurie>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT * FROM SAE_ECURIE");
			while (rs.next()) {
				Ecurie e = new Ecurie(rs.getInt(1),rs.getString(2));
				e.setAnneeDeCreation(rs.getInt(3));
				ControleurConnexion.listeEcuries.put(e.getNom(),e);
				ControleurConnexion.listeEcuriesID.put(e.getID(),e);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeEquipes() {
		ControleurConnexion.listeEquipes = new HashMap<String,Equipe>();
		ControleurConnexion.listeEquipesID = new HashMap<Integer,Equipe>();
		if (profilUtilisateur == Profil.ECURIE) {
			listeEquipesParEcurie = new ArrayList<String>();
		}
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT * FROM SAE_EQUIPE");
			while (rs.next()) {
				Equipe e = new Equipe(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getString(6), ControleurConnexion.listeJeuxID.get(rs.getInt(7)),
						ControleurConnexion.listeEcuriesID.get(rs.getInt(8)));
				ControleurConnexion.listeEquipesID.put(rs.getInt(1), e);
				ControleurConnexion.listeEquipes.put(e.getNom(),e);
				
				if ((profilUtilisateur == Profil.ECURIE) && rs.getInt(8) == Utilisateur.IDCourant) {
					listeEquipesParEcurie.add(e.getNom());
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeJoueurs() {
		ControleurConnexion.listeJoueurs = new HashMap<String,Joueur>();
		if (profilUtilisateur == Profil.ECURIE) {
			listeJoueursParEcurie = new ArrayList<String>();
		} 
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT IDJOUEUR, NOMJOUEUR,PRENOMJOUEUR,PSEUDOJOUEUR,to_char(DATENAISSANCE,'DD/MM/YYYY'),"
					+ "NATIONALITE,IDEQUIPE, PHOTOJOUEUR FROM SAE_JOUEUR");
			while (rs.next()) {
				Equipe equipe = ControleurConnexion.listeEquipesID.get(rs.getInt("IDEQUIPE"));
				Joueur j = new Joueur(rs.getInt("IDJOUEUR"),rs.getString("NOMJOUEUR"), rs.getString("PRENOMJOUEUR"), rs.getString("PSEUDOJOUEUR"), 
						rs.getString(5), rs.getString("NATIONALITE"), equipe, rs.getString("PHOTOJOUEUR"));
				ControleurConnexion.listeJoueurs.put(j.getPrenomPseudoNom(),j);
				ControleurConnexion.listeEquipesID.get(rs.getInt("IDEQUIPE")).ajouterJoueur(j);
				
				if (profilUtilisateur == Profil.ECURIE) {
					if (listeEquipesParEcurie.contains(equipe.getNom())) {
						listeJoueursParEcurie.add(j.getPrenomPseudoNom());
					}
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
