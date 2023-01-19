package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;

public class ControleurClassement implements ActionListener, ListSelectionListener {
	public enum Etat {
		DECONNECTER, ECURIE, CLASSEMENT, EQUIPES, JOUEURS, CALENDRIER, TOURNOIS
	}
	
	private VueClassement vue;
	private Etat etat;
	
	public ControleurClassement(VueClassement vue) {
		this.vue = vue;
		//this.initialiserListeClassement();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case ECURIE :
			VueERA fenEcurie = new VueERA();
			fenEcurie.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case EQUIPES :
			VueEquipe fenEquipe = new VueEquipe();
			fenEquipe.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case JOUEURS :
			VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case TOURNOIS :
			VueInscriptionTournoi fenTournois = new VueInscriptionTournoi();
			fenTournois.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		default:
		}
		
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
