package vue;

import java.awt.*;
import javax.swing.*;

public class Header extends JPanel {


    public static JButton createDeconnexionButton() {
        JButton btnDeconnexion = new JButton("Se déconnecter");
        btnDeconnexion.setForeground(Color.WHITE);
        btnDeconnexion.setFont(new Font("Roboto", Font.BOLD, 13));
        btnDeconnexion.setBackground(Couleur.ROUGE);
        return btnDeconnexion;
    }
    
    public static JButton createClassementButton() {
    	JButton btnClassement = new JButton("Classement");
		btnClassement.setForeground(Color.WHITE);
		btnClassement.setFont(new Font("Roboto", Font.BOLD, 15));
		btnClassement.setBackground(Couleur.BLEU2);
		return btnClassement;
    }
    
    public static JButton createJoueurButton() {
		JButton btnJoueurs = new JButton("Joueurs");
		btnJoueurs.setForeground(Color.WHITE);
		btnJoueurs.setFont(new Font("Roboto", Font.BOLD, 15));
		btnJoueurs.setBackground(Couleur.BLEU2);
		return btnJoueurs;
    }
    
    public static JButton createEquipesButton() {
		JButton btnEquipes = new JButton("Equipes");
		btnEquipes.setForeground(Color.WHITE);
		btnEquipes.setFont(new Font("Roboto", Font.BOLD, 15));
		btnEquipes.setBackground(Couleur.BLEU2);
		return btnEquipes;
	}
    public static JButton createEcuriesButton() {
		JButton btnEcuries = new JButton("Ecuries / Responsables / Arbitres");
		btnEcuries.setForeground(Color.WHITE);
		btnEcuries.setFont(new Font("Roboto", Font.BOLD, 15));
		btnEcuries.setBackground(Couleur.BLEU2);
		return btnEcuries;
	}
    
    public static JButton createCalendrierButton() {
		JButton btnCalendrier = new JButton("Calendrier");
		btnCalendrier.setForeground(Color.WHITE);
		btnCalendrier.setFont(new Font("Roboto", Font.BOLD, 15));
		btnCalendrier.setBackground(Couleur.BLEU2);
		return btnCalendrier;
	}
    
    public static JButton createTournoisButton() {
		JButton btnTournois = new JButton("Tournois");
		btnTournois.setForeground(Color.WHITE);
		btnTournois.setFont(new Font("Roboto", Font.BOLD, 15));
		btnTournois.setBackground(Couleur.BLEU2);
		return btnTournois;
    }
    
}

