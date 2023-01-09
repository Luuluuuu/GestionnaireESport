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

public class ControleurClassementJoueur implements ActionListener {
public enum Etat{DECONNECTER, PROFIL, TOURNOIS, EQUIPES}
	
	private VueClassementJoueur vue;
	private Etat etat;
	
	public ControleurClassementJoueur(VueClassementJoueur vue) {
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
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		case TOURNOIS :
			VueTournoisJoueur fenTournois = new VueTournoisJoueur();
			fenTournois.getFrame().setVisible(true);
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		case EQUIPES :
			VueEquipesJoueur fenClassement = new VueEquipesJoueur();
			fenClassement.getFrame().setVisible(true);
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		default:
		}
		
	}
}
