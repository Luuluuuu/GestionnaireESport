package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.VueJoueur;

public class ControleurJoueur implements ActionListener {

	public ControleurJoueur(VueJoueur vueJoueur) {
		// TODO Auto-generated constructor stub
	}
	public enum Etat {
		CREER, MODIFIER, SUPPRIMER, DECONNECTER, CALENDRIER, JOUEURS, CLASSEMENT, 
		RECHERCHER, VALIDER, ANNULER, EQUIPES, ECURIE 
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
