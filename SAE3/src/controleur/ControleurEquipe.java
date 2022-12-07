package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.VueEquipe;

public class ControleurEquipe implements ActionListener {
	
	public enum Etat{RECHERCHER,VALIDER,ANNULER,CREER,SUPPRIMER,DECONNECTER,ECURIE}
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
		case RECHERCHER:
			break;
		case VALIDER:
			break;
		default:
		}
	}

}
