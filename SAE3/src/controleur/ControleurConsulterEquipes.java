package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import vue.VueConnexion;
import vue.VueConsulterEquipes;

public class ControleurConsulterEquipes implements ActionListener, ListSelectionListener {

	public enum Etat {DECONNECTER}
	
	private VueConsulterEquipes vue;
	private Etat etat;
	
	public ControleurConsulterEquipes(VueConsulterEquipes vue) {
		this.vue = vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case DECONNECTER:
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueConsulterEquipes.fermerFenetre(this.vue.fenetreConsulterEquipes);
		break;
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}
	
}
