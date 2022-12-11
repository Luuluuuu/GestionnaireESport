package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.VueCalendrier;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueJoueur;

public class ControleurEquipe implements ActionListener {
	
	public enum Etat{RECHERCHER,VALIDER,ANNULER,CREER,SUPPRIMER,DECONNECTER,ECURIE,CALENDRIER,JOUEURS}
	private VueEquipe vue;
	private Etat etat;
	public ControleurEquipe(VueEquipe vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case ECURIE :
			VueERA fenEcurie = new VueERA();
			fenEcurie.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		break;
		case JOUEURS:
			VueJoueur fenJoueur= new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);

		case RECHERCHER:
			break;
		case VALIDER:
			break;
		default:
		}
	}

}
