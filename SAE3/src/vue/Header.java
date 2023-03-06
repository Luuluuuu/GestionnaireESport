package vue;

import javax.swing.*;

public class Header implements Vue{


    public JButton createDeconnexionButton(JPanel panel) {
        JButton btnDeconnexion = creerBouton(panel, "Se déconnecter", Couleur.ROUGE, 13);
        return btnDeconnexion;
    }
    
    public JButton createClassementButton(JPanel panel) {
    	JButton btnClassement = creerBouton(panel, "Classement", Couleur.BLEU2, 15);
		return btnClassement;
    }
    
    public JButton createJoueurButton(JPanel panel) {
		JButton btnJoueurs = creerBouton(panel, "Joueurs", Couleur.BLEU2, 15);
		return btnJoueurs;
    }
    
    public JButton createEquipesButton(JPanel panel) {
		JButton btnEquipes = creerBouton(panel, "Equipes", Couleur.BLEU2, 15);
		return btnEquipes;
	}
    public JButton createEcuriesButton(JPanel panel) {
		JButton btnEcuries = creerBouton(panel, "Ecuries / Responsables / Arbitres", Couleur.BLEU2, 15);
		return btnEcuries;
	}
    
    public JButton createCalendrierButton(JPanel panel) {
		JButton btnCalendrier = creerBouton(panel, "Calendrier", Couleur.BLEU2, 15);
		return btnCalendrier;
	}
    
    public JButton createTournoisButton(JPanel panel) {
		JButton btnTournois = creerBouton(panel, "Tournois", Couleur.BLEU2, 15);
		return btnTournois;
    }
    
}

