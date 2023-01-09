package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Connexion;
import vue.VueClassementJoueur;
import vue.VueConnexion;
import vue.VueEquipesJoueur;
import vue.VueProfilJoueur;
import vue.VueTournoisJoueur;

public class ControleurEquipesJoueur implements ActionListener {
	public enum Etat{DECONNECTER, PROFIL, TOURNOIS, CLASSEMENT}
	
	private VueEquipesJoueur vue;
	private Etat etat;
	
	public ControleurEquipesJoueur(VueEquipesJoueur vue) {
		this.vue = vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case PROFIL :
			VueProfilJoueur fenEquipe = new VueProfilJoueur();
			fenEquipe.getFrame().setVisible(true);
			VueEquipesJoueur.fermerFenetre(this.vue.fenetreEquipesJoueur);
		break;
		case TOURNOIS :
			VueTournoisJoueur fenTournois = new VueTournoisJoueur();
			fenTournois.getFrame().setVisible(true);
			VueEquipesJoueur.fermerFenetre(this.vue.fenetreEquipesJoueur);
		break;
		case CLASSEMENT :
			VueClassementJoueur fenClassement = new VueClassementJoueur();
			fenClassement.getFrame().setVisible(true);
			VueEquipesJoueur.fermerFenetre(this.vue.fenetreEquipesJoueur);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueEquipesJoueur.fermerFenetre(this.vue.fenetreEquipesJoueur);
		break;
		default:
		}
		
	}

}
