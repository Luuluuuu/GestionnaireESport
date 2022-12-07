package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import modele.Connexion;
import modele.Utilisateur;
import vue.VueConnexion;
import vue.VueCalendrier;

public class ControleurConnexion implements ActionListener {
	private enum Etat{}
	
	private VueConnexion vue;
	
	public ControleurConnexion(VueConnexion vue) {
		this.vue = vue;
		Connexion.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.vue.estRemplie()) {
			try {
				if (Utilisateur.mdpCorrect(this.vue.getLogin(), this.vue.getMotDePasse())) {
					VueCalendrier fen = new VueCalendrier();
					fen.getFrame().setVisible(true);
					VueConnexion.fermerFenetre(this.vue.fenetreConnexion);
				} else {
					this.vue.connexionEchoue();
				}
			} catch (SQLException ee) {
				ee.printStackTrace();
				this.vue.connexionEchoue();
			}
		} else {
			this.vue.connexionEchoue();	
		}
	}

}
