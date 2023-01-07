package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Equipe;
import modele.Joueur;
import modele.Tournoi;
import vue.VueConnexion;
import vue.VueEquipe;
import vue.VueJoueur;
import vue.VueRentrerPoints;

public class ControleurRentrerPoints implements ActionListener, ListSelectionListener{
	public enum Etat {DECONNECTER}
	
	private VueRentrerPoints vue;
	private Etat etat;
	
	public ControleurRentrerPoints(VueRentrerPoints vue) {
		this.vue = vue;
		this.initialiserListeTournois();
	}
	
	public void initialiserListeTournois() {
		for (String nomTournoi : ControleurConnexion.listeTournois.keySet()) {
			this.vue.ajouterTournoi(nomTournoi);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.etat = this.vue.getEtat((JButton) e.getSource());
		switch (this.etat) {
		case DECONNECTER:
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreRentrerPoints);
		break;
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		JList<String> list = (JList<String>) e.getSource();
	    if (!list.getValueIsAdjusting()) {	// gere les doubles clics
			Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			this.vue.setJeux(t.getNomJeux());
	    }
	}
}
