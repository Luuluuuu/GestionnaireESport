package vue;

import javax.swing.*;

public class HeaderEcurie extends HeaderVueGestionEcurie implements Vue {

	public HeaderEcurie(JFrame fen) {
		super(fen);
		//super.setUpButton();
		JButton btnTournois = creerBouton(super.getPanelMenu(), "Tournois", Couleur.BLEU2, 15);
	}
}

