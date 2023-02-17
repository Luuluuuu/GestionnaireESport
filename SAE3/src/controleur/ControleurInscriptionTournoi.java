package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Equipe;
import modele.Jeu;
import modele.Tournoi;
import modele.Utilisateur;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueEquipe;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;

public class ControleurInscriptionTournoi implements ActionListener, ListSelectionListener {
	public enum Etat{DECONNECTER, EQUIPES, JOUEURS, CLASSEMENT, TOURNOI, JEU, VALIDER, ETAT, EQUIPE};
	
	private VueInscriptionTournoi vue;
	private Etat etat;
	
	public ControleurInscriptionTournoi(VueInscriptionTournoi vue) {
		this.vue = vue;
		this.initialiserListeTournois();
	}
	
	public void initialiserListeTournois() {
		for (String nomTournoi : ControleurConnexion.listeTournois.keySet()) {
			this.vue.ajouterTournoi(nomTournoi);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			this.etat = this.vue.getEtat(b);			
		} else {
			this.etat = Etat.JEU;
		}
		switch (this.etat) {
		case EQUIPES :
			VueEquipe fenERA = new VueEquipe();
			fenERA.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreInscriptionTournoi);
		break;
		case JOUEURS :
			VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreInscriptionTournoi);
		break;
		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreInscriptionTournoi);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreInscriptionTournoi);
		break;
		case JEU:
			this.vue.viderEquipes();
			Jeu j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			Jeu j2 = t.getJeu(j);
			
			for (Equipe equipe : ControleurConnexion.listeEquipes.values()) {
				if (equipe.getEcurie().getID() == Utilisateur.IDCourant && 
						equipe.getJeu().getNom().equals(this.vue.getJeuSelectionne()) &&
						(!j2.contient(equipe))) {
					
					this.vue.ajouterEquipe(equipe.getNom());
				}
			}
			break;
		case VALIDER:
			// VERIFIE SI TOUS LES CHAMPS SONT REMPLIS //
			if (this.vue.estRemplie()) {
				
				// DEMANDE LA CONFIRMATION DE L'INSCRIPTION A L'ECURIE //
				int confirme = JOptionPane.showConfirmDialog(null, "Confirmez-vous l'inscription ?","Confirmation",JOptionPane.YES_NO_OPTION);
				if (confirme == 0) {
					
					Equipe eq = ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne());
					Jeu jeu = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
					Tournoi tournoi = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
					
					// INSERT L'INSCRIPTION //
					String req = "INSERT INTO SAE_INSCRIRE VALUES ("+eq.getID()+","+jeu.getID()+","+tournoi.getID()+")";
					Connexion.getInstance().executerRequete(req);
					
					// APPELLE LA FONCTION PL/SQL POUR GENERER DES POULES //
					req = "BEGIN GENERER_POULES("+jeu.getID()+","+tournoi.getID()+"); END;";
					Connexion.getInstance().executerRequete(req);
					
					tournoi.inscrireEquipe(jeu, eq);
					this.vue.deselectionner();
					
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Veuillez compl�ter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			break;
		default:
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		JList<String> l = (JList<String>) e.getSource();
		this.etat = this.vue.getEtat(l);
		if (!l.isSelectionEmpty()) {
			switch (this.etat) {
			case TOURNOI:
				this.vue.viderJeux();
				this.vue.ajouterJeu("- S�lectionnez un jeu -");
				// On récupère le tournoi sélectionné
				Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
				// On récupère les jeux du tournoi
				List<Jeu> j = t.getJeux();
				for (Jeu jeu : j) {
					this.vue.ajouterJeu(jeu.getNom());
				}
				break;
			default:
				break;
			}
		}
	}
}
