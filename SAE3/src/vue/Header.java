package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public abstract class Header implements Vue{

	private  JPanel panelHeader;
	private  JPanel panelDeco;
	private JPanel panelMenu;
	private JButton  btnEquipes;
	private JButton btnJoueurs;
	private JButton btnClassement;
	
    public Header(JFrame fen) {
    	JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fen.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
    	
    	JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelHeader.add(panelMenu);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelDeconnexion);
		
		
        @SuppressWarnings("unused")
		JButton btnDeconnexion = creerBouton(panelDeconnexion, "Se déconnecter", Couleur.ROUGE, 13);
        this.panelMenu = panelMenu;
    }
    
    public JPanel getPanelMenu() {
    	return panelMenu;
    }
    
	//Set up les boutons Equipes, Joueurs et Classement
    @SuppressWarnings("unused")
	public void setUpButton() {
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

