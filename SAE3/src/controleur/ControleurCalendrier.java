package controleur;
import vue.VueCalendrier;
import vue.VueConnexion;
import vue.VueERA;

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
		CREER, MODIFIER, ANNULER, DECONNECTER, SUPPRIMER, VALIDER, ECURIE
	}
	
	private VueCalendrier vue;
	private Etat etat;
	private Map<String, Tournoi> 		listeTournois;
	private Map<String, Responsable> 	listeResponsables;
	private Map<String, Arbitre>		listeArbitres;
	private Map<String, Jeu>			listeJeux;
	
	private Map<Integer, Responsable> 	listeResponsablesID;
	private Map<Integer, Arbitre>		listeArbitresID;
	private Map<Integer, Jeu>			listeJeuxID;
	
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
		this.listeTournois = new HashMap<String,Tournoi>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT sae_tournoi.idtournoi, sae_tournoi.nomtournoi, to_char(sae_tournoi.datetournoi,'DD-MM-YYYY'), "
				+ "sae_tournoi.heuredebut, sae_tournoi.idarbitre, sae_tournoi.idresponsable, sae_tournoi.echelletournoi, "
				+ "sae_definir.idJeu FROM sae_definir JOIN sae_tournoi ON sae_definir.idTournoi = sae_tournoi.idTournoi");
		try {
			// INITIALISER TOURNOI //
			while (rs.next()) {
				if (this.listeTournois.containsKey(rs.getString(2))) {
					this.listeTournois.get(rs.getString(2)).ajouterJeu(this.listeJeuxID.get(rs.getInt("IDJEU")));
				} else {
					this.vue.ajouterTournoi(rs.getString("NOMTOURNOI"));
					
					Tournoi t = new Tournoi(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(7));
					t.setArbitre(this.listeArbitresID.get(rs.getInt(5)));
					t.setResponsable(this.listeResponsablesID.get(rs.getInt(6)));
					t.ajouterJeu(this.listeJeuxID.get(rs.getInt("IDJEU")));
					listeTournois.put(t.getNom(), t);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void initialiserListeResponsables() {
		this.listeResponsables = new HashMap<String, Responsable>();
		this.listeResponsablesID = new HashMap<Integer,Responsable>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_RESPONSABLE");
		try {
			while (rs.next()) {
				Responsable r = new Responsable(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
				this.listeResponsables.put(r.getPrenomNom(),r);
				this.listeResponsablesID.put(r.getID(), r);
				this.vue.ajouterResponsable(r.getPrenomNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeArbitres() {
		this.listeArbitres = new HashMap<String, Arbitre>();
		this.listeArbitresID = new HashMap<Integer,Arbitre>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_ARBITRE");
		try {
			while (rs.next()) {
				Arbitre a = new Arbitre(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				this.listeArbitres.put(a.getPrenomNom(),a);
				this.listeArbitresID.put(a.getID(), a);
				this.vue.ajouterArbitre(a.getPrenomNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeJeux() {
		this.listeJeux = new HashMap<String, Jeu>();
		this.listeJeuxID = new HashMap<Integer,Jeu>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_JEU");
		try {
			while (rs.next()) {
				Jeu j = new Jeu(rs.getInt(1),rs.getString(2),rs.getInt(3));
				this.listeJeux.put(j.getNom(),j);
				this.listeJeuxID.put(j.getID(), j);
				VueCalendrier.ajouterJeu(j.getNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		case SUPPRIMER :
			if ((this.vue.getTournoiSelectionne()!=null && this.vue.confirmerSuppression()==0)) {
				Tournoi t = this.listeTournois.get(this.vue.getTournoiSelectionne());
				this.vue.supprimerTournoi();
				this.listeTournois.remove(t.getNom());
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
				t.setArbitre(this.listeArbitres.get(this.vue.getArbitre()));
				t.setResponsable(this.listeResponsables.get(this.vue.getResponsable()));

				//Vérifie si c'est une creation ou une modification
				if (this.vue.titreModif.getText().equals("Créer un tournoi")) {
					if (!(this.listeTournois.containsKey(t.getNom()))) {
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
							Jeu j = this.listeJeux.get(nomJeu);
							t.ajouterJeu(j);
							Connexion.getInstance().executerRequete("INSERT INTO SAE_DEFINIR VALUES ("+t.getID()+","+j.getID()+")");
						}
						this.listeTournois.put(t.getNom(), t);
						this.vue.ajouterTournoi(t.getNom());
					}else {
							this.vue.tournoiExiste();
					}
				} else {
					t.setID(this.listeTournois.get(this.vue.getTournoiSelectionne()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_DEFINIR WHERE IDTOURNOI ="+t.getID());
					for (String nomJeu : this.vue.getJeux()) {
						Jeu j = this.listeJeux.get(nomJeu);
						t.ajouterJeu(j);
						Connexion.getInstance().executerRequete("INSERT INTO SAE_DEFINIR VALUES ("+t.getID()+","+j.getID()+")");
					}
					
					Connexion.getInstance().executerRequete("UPDATE SAE_TOURNOI SET NOMTOURNOI = '"+t.getNom()+"',"
							+ "DATETOURNOI = TO_DATE('"+t.getDate()+"','DD-MM-YYYY'), HEUREDEBUT='"+t.getHeureDebut()+"', ECHELLETOURNOI ='"+t.getEchelle()+"',"
									+ "IDARBITRE = "+t.getArbitre().getID()+", IDRESPONSABLE = "+t.getResponsable().getID()+
									"WHERE IDTOURNOI = "+t.getID());
					this.listeTournois.remove(this.vue.getTournoiSelectionne());
					this.listeTournois.put(t.getNom(), t);
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
			JList list = (JList) e.getSource();
			if (!(list.isSelectionEmpty())) {
				this.etat = Etat.MODIFIER;
				VueCalendrier.afficherPanel(this.vue.panelModif);
				VueCalendrier.afficherTexte(this.vue.titreModif, "Modifier un tournoi");
				
				Tournoi t = this.listeTournois.get(this.vue.getTournoiSelectionne());
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