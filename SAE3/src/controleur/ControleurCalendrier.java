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

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Jeu;
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
	
	// INTIALISER LES LISTES
	public void initialiserListes() {
		this.initialiserListeResponsables();
		this.initialiserListeArbitres();
		this.initialiserListeJeux();
		this.initialiserListeTournois();
	}
	
	public void initialiserListeTournois() {
		for (String nomTournoi : ControleurConnexion.listeTournois.keySet()) {
			this.vue.ajouterTournoi(nomTournoi);
		}
	}
	
	public void initialiserListeResponsables() {
		for (String nomResponsable : ControleurConnexion.listeResponsables.keySet()) {
			this.vue.ajouterResponsable(nomResponsable);
		}
	}
	
	public void initialiserListeArbitres() {
		for (String nomArbitre : ControleurConnexion.listeArbitres.keySet()) {
			this.vue.ajouterArbitre(nomArbitre);
		}
	}
	
	public void initialiserListeJeux() {
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