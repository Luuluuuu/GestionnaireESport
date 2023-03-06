package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public interface Vue {
	
	/* Crée un JButton
	 * Entrées :
	 * 	texteBouton, String, texte à l'intérieur du JButton
	 * 	panel, JPanel, panel auquel appartient le bouton
	 */
	public default JButton creerBouton(JPanel panel, String texteBouton, Color couleur) {
		JButton bouton = new JButton(texteBouton);
		
		panel.add(bouton);
		bouton.setForeground(Color.WHITE);
		bouton.setFont(new Font("Roboto", Font.BOLD, 13));
		bouton.setBackground(couleur);
		
		return bouton;
	}
	
	public default JTextField creerJTextField(JPanel panel, int taillePolice, int columns) {
		JTextField texte = new JTextField();
		
		panel.add(texte);
		texte.setFont(new Font("Roboto",Font.PLAIN, taillePolice));
		texte.setColumns(columns);
		
		return texte;
	}
}
