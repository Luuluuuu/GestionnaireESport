package vue;

import javax.swing.*;

public class HeaderVueGestionEcurie extends Header implements Vue {
	private JButton  btnEquipes;
	private JButton btnJoueurs;
	private JButton btnClassement;
	
	public HeaderVueGestionEcurie(JFrame fen) {
		super(fen);
		btnEquipes = creerBouton(this.getPanelMenu(), "Equipes", Couleur.BLEU2, 15);
		btnJoueurs = creerBouton(this.getPanelMenu(), "Joueurs", Couleur.BLEU2, 15);
		btnClassement = creerBouton(this.getPanelMenu(), "Classement", Couleur.BLEU2, 15);
	}
	
    public JButton getBtnEquipes() {
    	return this.btnEquipes;
    }
    public JButton getBtnJoueurs() {
    	return this.btnJoueurs;
    }
    public JButton getBtnClassement() {
    	return this.btnClassement;
    }
    
}



    



