package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import javax.swing.JButton;

import modele.Connexion;
import modele.Equipe;
import modele.Utilisateur.Profil;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;

public class ControleurInscriptionTournoi implements ActionListener {
	

	public enum Etat{DECONNECTER,JOUEURS,EQUIPE,CLASSEMENT}
	private VueInscriptionTournoi vue;
	private Etat etat;
	
	public ControleurInscriptionTournoi(VueInscriptionTournoi vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case EQUIPE :
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
		default:
		}
	}

}
