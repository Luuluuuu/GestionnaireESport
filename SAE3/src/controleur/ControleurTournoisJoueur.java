package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controleur.ControleurEquipesJoueur.Etat;
import modele.Connexion;
import vue.VueClassementJoueur;
import vue.VueConnexion;
import vue.VueEquipesJoueur;
import vue.VueProfilJoueur;
import vue.VueTournoisJoueur;

public class ControleurTournoisJoueur implements ActionListener{
public enum Etat{DECONNECTER, PROFIL, EQUIPES, CLASSEMENT}
	
	private VueTournoisJoueur vue;
	private Etat etat;
	
	public ControleurTournoisJoueur(VueTournoisJoueur vue) {
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
			VueTournoisJoueur.fermerFenetre(this.vue.fenetreTournoisJoueur);
		break;
		case EQUIPES :
			VueEquipesJoueur fenTournois = new VueEquipesJoueur();
			fenTournois.getFrame().setVisible(true);
			VueTournoisJoueur.fermerFenetre(this.vue.fenetreTournoisJoueur);
		break;
		case CLASSEMENT :
			VueClassementJoueur fenClassement = new VueClassementJoueur();
			fenClassement.getFrame().setVisible(true);
			VueTournoisJoueur.fermerFenetre(this.vue.fenetreTournoisJoueur);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueTournoisJoueur.fermerFenetre(this.vue.fenetreTournoisJoueur);
		break;
		default:
		}
		
	}
}
