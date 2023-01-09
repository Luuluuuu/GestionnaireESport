package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Joueur;
import modele.Utilisateur;
import vue.VueConnexion;
import vue.VueProfilJoueur;

public class ControleurProfilJoueur implements ActionListener{
	public enum Etat{DECONNECTER}
	
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
	}
	
	public void initialiserListeJoueurs() {
		for (Joueur j : ControleurConnexion.listeJoueurs.values()) {
			if (j.getEquipe().equals(this.joueur.getEquipe())) {
				this.vue.ajouterJoueur(j.getNom());
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.etat = this.vue.getEtat((JButton) e.getSource());
		switch (this.etat) {
		case DECONNECTER:
			VueConnexion fenConnexion = new VueConnexion();
			fenConnexion.getFrame().setVisible(true);
			this.vue.getFrame().setVisible(false);
			break;
		}
	}
	
}
