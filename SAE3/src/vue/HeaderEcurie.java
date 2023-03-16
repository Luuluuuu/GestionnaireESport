package vue;

import javax.swing.*;

public class HeaderEcurie extends HeaderVueGestionEcurie implements Vue {
	private JButton btnTournois;

	public HeaderEcurie(JFrame fen) {
		super(fen);
		//super.setUpButton();
		btnTournois = creerBouton(super.getPanelMenu(), "Tournois", Couleur.BLEU2, 15);
	}
    public JButton getBtnTournois() {
    	return this.btnTournois;
    }
}

