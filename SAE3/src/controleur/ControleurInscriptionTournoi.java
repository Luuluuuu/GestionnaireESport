package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
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
	public enum Etat{DECONNECTER, EQUIPES, JOUEURS, CLASSEMENT, TOURNOI, JEU};
	
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
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
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
			for (Equipe equipe : ControleurConnexion.listeEquipes.values()) {
				if (equipe.getEcurie().getID() == Utilisateur.IDCourant && equipe.getJeu().getNom().equals(this.vue.getJeuSelectionne())) {
					this.vue.ajouterEquipe(equipe.getNom());
				}
			}
			break;
		default:
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch (this.etat) {
		case TOURNOI:
			// On récupère le tournoi sélectionné
			Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			// On récupère les jeux du tournoi
			List<Jeu> j = t.getJeux();
			for (Jeu jeu : j) {
				this.vue.ajouterJeu(jeu.getNom());
			}
			break;
		}
	}
}
