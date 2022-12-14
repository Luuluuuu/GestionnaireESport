package controleur;
import vue.VueCalendrier;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Arbitre;
import modele.Connexion;
import modele.Jeu;
import modele.Responsable;
import modele.Tournoi;

public class ControleurCalendrier implements ActionListener, ListSelectionListener {
	public enum Etat {
		CREER, MODIFIER, ANNULER, DECONNECTER, SUPPRIMER, VALIDER, ECURIE, CLASSEMENT, EQUIPES, JOUEURS, CALENDRIER,
	}
	
	private VueCalendrier vue;
	private Etat etat;
	
	public ControleurCalendrier(VueCalendrier vue) {
		this.vue = vue;
		this.initialiserListes();
		this.etat = Etat.CREER;
	}
	
	// Initialiser les listes
	public void initialiserListes() {
		this.initialiserListeResponsables();
		this.initialiserListeArbitres();
		this.initialiserListeJeux();
		this.initialiserListeTournois();
	}
	
	public void initialiserListeTournois() {
		/*ControleurCalendrier.listeTournois = new HashMap<String,Tournoi>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT sae_tournoi.idtournoi, sae_tournoi.nomtournoi, to_char(sae_tournoi.datetournoi,'DD-MM-YYYY'), "
				+ "sae_tournoi.heuredebut, sae_tournoi.idarbitre, sae_tournoi.idresponsable, sae_tournoi.echelletournoi, "
				+ "sae_definir.idJeu FROM sae_definir JOIN sae_tournoi ON sae_definir.idTournoi = sae_tournoi.idTournoi");
		try {
			// INITIALISER TOURNOI //
			while (rs.next()) {
				if (ControleurCalendrier.listeTournois.containsKey(rs.getString(2))) {
					ControleurCalendrier.listeTournois.get(rs.getString(2)).ajouterJeu(ControleurCalendrier.listeJeuxID.get(rs.getInt("IDJEU")));
				} else {
					this.vue.ajouterTournoi(rs.getString("NOMTOURNOI"));
					
					Tournoi t = new Tournoi(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(7));
					t.setArbitre(this.listeArbitresID.get(rs.getInt(5)));
					t.setResponsable(this.listeResponsablesID.get(rs.getInt(6)));
					t.ajouterJeu(ControleurCalendrier.listeJeuxID.get(rs.getInt("IDJEU")));
					listeTournois.put(t.getNom(), t);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		for (String nomTournoi : ControleurConnexion.listeTournois.keySet()) {
			this.vue.ajouterTournoi(nomTournoi);
		}
	}

	public void initialiserListeResponsables() {
		/*ControleurCalendrier.listeResponsables = new HashMap<String, Responsable>();
		this.listeResponsablesID = new HashMap<Integer,Responsable>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_RESPONSABLE");
		try {
			while (rs.next()) {
				Responsable r = new Responsable(rs.getInt(1),rs.getString(2), rs.getString(3));
				r.setAnneesExperience(rs.getInt(4));
				ControleurCalendrier.listeResponsables.put(r.getPrenomNom(),r);
				this.listeResponsablesID.put(r.getID(), r);
				this.vue.ajouterResponsable(r.getPrenomNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		for (String nomResponsable : ControleurConnexion.listeResponsables.keySet()) {
			this.vue.ajouterResponsable(nomResponsable);
		}
	}
	
	public void initialiserListeArbitres() {
		/*ControleurCalendrier.listeArbitres = new HashMap<String, Arbitre>();
		this.listeArbitresID = new HashMap<Integer,Arbitre>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_ARBITRE");
		try {
			while (rs.next()) {
				Arbitre a = new Arbitre(rs.getInt(5),rs.getString(1),rs.getString(2));
				a.setPseudo(rs.getString(3));
				a.setanneesExperience(rs.getInt(4));
				ControleurCalendrier.listeArbitres.put(a.getPrenomNom(),a);
				this.listeArbitresID.put(a.getID(), a);
				this.vue.ajouterArbitre(a.getPrenomNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		for (String nomArbitre : ControleurConnexion.listeArbitres.keySet()) {
			this.vue.ajouterArbitre(nomArbitre);
		}
	}
	
	public void initialiserListeJeux() {
		/*ControleurCalendrier.listeJeux = new HashMap<String, Jeu>();
		ControleurCalendrier.listeJeuxID = new HashMap<Integer,Jeu>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_JEU");
		try {
			while (rs.next()) {
				Jeu j = new Jeu(rs.getInt(1),rs.getString(2),rs.getInt(3));
				ControleurCalendrier.listeJeux.put(j.getNom(),j);
				ControleurCalendrier.listeJeuxID.put(j.getID(), j);
				VueCalendrier.ajouterJeu(j.getNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		for (String nomJeu : ControleurConnexion.listeJeux.keySet()) {
			this.vue.ajouterJeu(nomJeu);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case ECURIE :
			VueERA fenEcurie = new VueERA();
			fenEcurie.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		
		case EQUIPES :
			VueEquipe fenEquipe = new VueEquipe();
			fenEquipe.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		case JOUEURS :
			VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
/*		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break; */
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		case SUPPRIMER :
			if ((this.vue.getTournoiSelectionne()!=null && this.vue.confirmerSuppression()==0)) {
				Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
				this.vue.supprimerTournoi();
				ControleurConnexion.listeTournois.remove(t.getNom());
				Connexion.getInstance().executerRequete("DELETE SAE_DEFINIR WHERE IDTOURNOI ="+t.getID());
				Connexion.getInstance().executerRequete("DELETE SAE_TOURNOI WHERE IDTOURNOI = "+t.getID());
				this.vue.creerTournoi();
			}
			break;
		case VALIDER:
			if (this.vue.estRemplie()) {
				// Instancie un tournoi
				Tournoi t = new Tournoi(2,this.vue.entreeNom.getText(),this.vue.entreeDate.getText(),
						this.vue.entreeHeure.getText(), this.vue.getEchelle());
				t.setArbitre(ControleurConnexion.listeArbitres.get(this.vue.getArbitre()));
				t.setResponsable(ControleurConnexion.listeResponsables.get(this.vue.getResponsable()));

				//Vérifie si c'est une creation ou une modification
				if (this.vue.titreModif.getText().equals("Créer un tournoi")) {
					if (!(ControleurConnexion.listeTournois.containsKey(t.getNom()))) {
						// En cas de creation, on recupere la prochaine valeur de la sequence, pour l'attribuer au tournoi
						ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_tournoiId.nextval FROM dual");
						try {
							if (rs.next()) {t.setID(rs.getInt(1));}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						try {
							rs.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						Connexion.getInstance().executerRequete("INSERT INTO SAE_TOURNOI VALUES ("+t.getID()+", '"+t.getNom()+"', TO_DATE('"+t.getDate()+
								"','DD-MM-YYYY'), '"+ t.getHeureDebut()+"', "+t.getArbitre().getID()+", "+t.getResponsable().getID()+", '"+t.getEchelle()+"')");;
						for (String nomJeu : this.vue.getJeux()) {
							Jeu j = ControleurConnexion.listeJeux.get(nomJeu);
							t.ajouterJeu(j);
							Connexion.getInstance().executerRequete("INSERT INTO SAE_DEFINIR VALUES ("+t.getID()+","+j.getID()+")");
						}
						ControleurConnexion.listeTournois.put(t.getNom(), t);
						this.vue.ajouterTournoi(t.getNom());
					}else {
							this.vue.tournoiExiste();
					}
				} else {
					t.setID(ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_DEFINIR WHERE IDTOURNOI ="+t.getID());
					for (String nomJeu : this.vue.getJeux()) {
						Jeu j = ControleurConnexion.listeJeux.get(nomJeu);
						t.ajouterJeu(j);
						Connexion.getInstance().executerRequete("INSERT INTO SAE_DEFINIR VALUES ("+t.getID()+","+j.getID()+")");
					}
					
					Connexion.getInstance().executerRequete("UPDATE SAE_TOURNOI SET NOMTOURNOI = '"+t.getNom()+"',"
							+ "DATETOURNOI = TO_DATE('"+t.getDate()+"','DD-MM-YYYY'), HEUREDEBUT='"+t.getHeureDebut()+"', ECHELLETOURNOI ='"+t.getEchelle()+"',"
									+ "IDARBITRE = "+t.getArbitre().getID()+", IDRESPONSABLE = "+t.getResponsable().getID()+
									"WHERE IDTOURNOI = "+t.getID());
					ControleurConnexion.listeTournois.remove(this.vue.getTournoiSelectionne());
					ControleurConnexion.listeTournois.put(t.getNom(), t);
					this.vue.modifierTournoi();
					}
				this.vue.creerTournoi();
			}
			break;
			case ANNULER :
			case CREER :
				this.vue.creerTournoi();
			break;
		default:
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch (this.etat) {
		case SUPPRIMER:
			this.etat = Etat.CREER;
			VueCalendrier.afficherPanel(this.vue.panelModif);
			VueCalendrier.afficherTexte(this.vue.titreModif, "Créer un tournoi");
			VueCalendrier.supprimerTexte(this.vue.entreeNom);
			VueCalendrier.supprimerTexte(this.vue.entreeDate);
			VueCalendrier.supprimerTexte(this.vue.entreeHeure);
			break;
		default:
			@SuppressWarnings
			("unchecked") JList<String> list = (JList<String>) e.getSource();
			if (!(list.isSelectionEmpty())) {
				this.etat = Etat.MODIFIER;
				VueCalendrier.afficherPanel(this.vue.panelModif);
				VueCalendrier.afficherTexte(this.vue.titreModif, "Modifier un tournoi");
				
				Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
				this.vue.entreeNom.setText(t.getNom());
				this.vue.entreeDate.setText(t.getDate().toString());
				this.vue.entreeHeure.setText(t.getHeureDebut());
				this.vue.setEchelle(t.getEchelle());
				this.vue.setArbitre(t.getArbitre().getPrenomNom());
				this.vue.setResponsable(t.getResponsable().getPrenomNom());
				this.vue.setJeux(t.getNomJeux());
			}
		}
	}
}