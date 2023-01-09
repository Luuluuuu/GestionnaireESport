package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Connexion;
import modele.Joueur;
import modele.Utilisateur;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueClassementJoueur;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueEquipesJoueur;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;
import vue.VueProfilJoueur;
import vue.VueTournoisJoueur;

public class ControleurProfilJoueur implements ActionListener{
	public enum Etat{DECONNECTER, EQUIPES, TOURNOIS, CLASSEMENT}
	
	private VueProfilJoueur vue;
	private Etat etat;
	private Joueur joueur;
	
	public ControleurProfilJoueur(VueProfilJoueur vue) {
		this.vue = vue;
		
		// INITIALISE LE JOUEUR CONNECTE
		for (Joueur j : ControleurConnexion.listeJoueurs.values()) {
			if (j.getID() == Utilisateur.IDCourant) {
				this.joueur = j;
			}
		}
		
		this.vue.setInfosJoueur(joueur.getPhoto(), joueur.getPseudo(), joueur.getEquipe().getNom());
		this.initialiserListeJoueurs();
	}
	
	public void initialiserListeJoueurs() {
		for (Joueur j : ControleurConnexion.listeJoueurs.values()) {
			if (j.getEquipe().equals(this.joueur.getEquipe()) && !(j.equals(this.joueur))) {
				this.vue.ajouterJoueur(j.getPrenomPseudoNom());
			}
		}
	}
	
	public void intialiserListePoules() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case EQUIPES :
			VueEquipesJoueur fenEquipe = new VueEquipesJoueur();
			fenEquipe.getFrame().setVisible(true);
			VueProfilJoueur.fermerFenetre(this.vue.fenetreProfilJoueur);
		break;
		case TOURNOIS :
			VueTournoisJoueur fenTournois = new VueTournoisJoueur();
			fenTournois.getFrame().setVisible(true);
			VueProfilJoueur.fermerFenetre(this.vue.fenetreProfilJoueur);
		break;
		case CLASSEMENT :
			VueClassementJoueur fenClassement = new VueClassementJoueur();
			fenClassement.getFrame().setVisible(true);
			VueProfilJoueur.fermerFenetre(this.vue.fenetreProfilJoueur);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueProfilJoueur.fermerFenetre(this.vue.fenetreProfilJoueur);
		break;
		default:
		}
	}
	
}
