package vue;

import javax.swing.*;

public class HeaderAdmin extends HeaderVueGestionEcurie implements Vue {
	private JButton  btnCalendrier;
	private JButton btnEcuries;
	
	public HeaderAdmin(JFrame fen) {
		super(fen);
		btnCalendrier = creerBouton(super.getPanelMenu(), "Calendrier", Couleur.BLEU2, 15);
		btnEcuries = creerBouton(super.getPanelMenu(), "Ecuries / Responsables / Arbitres", Couleur.BLEU2, 15);
	}
    public JButton getBtnCalendrier() {
    	return this.btnCalendrier;
    }
    public JButton getBtnEcuries() {
    	return this.btnEcuries;
    }
    

}



    



