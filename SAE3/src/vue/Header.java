package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public abstract class Header implements Vue{

	private  JPanel panelHeader;
	private  JPanel panelDeco;
	private JPanel panelMenu;
	private JButton btnDeconnexion;
	
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
		btnDeconnexion = creerBouton(panelDeconnexion, "Se déconnecter", Couleur.ROUGE, 13);
        this.panelMenu = panelMenu;
    }
    
    public JPanel getPanelMenu() {
    	return panelMenu;
    }
    public JButton getBtnDeconnexion() {
    	return this.btnDeconnexion;
    }
}

