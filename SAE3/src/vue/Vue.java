package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public interface Vue {
	
	/* Crée un JButton
	 * Entrées :
	 * 	texteBouton, String, texte à l'intérieur du JButton
	 * 	panel, JPanel, panel auquel appartient le bouton
	 */
	public default JButton creerBouton(JPanel panel, String texteBouton, Color couleur, int taillePolice) {
		JButton bouton = new JButton(texteBouton);
		
		panel.add(bouton);
		bouton.setForeground(Color.WHITE);
		bouton.setFont(new Font("Roboto", Font.BOLD, taillePolice));
		bouton.setBackground(couleur);
		
		return bouton;
	}
	
	/* Crée un JTextField
	 * Entrées :
	 * 	panel, JPanel, panel contenant le JTextField
	 * 	taillePolice, int, taille de la police du texte
	 * 	columns, int, nombre de colonnes du texte
	 */
	public default JTextField creerJTextField(JPanel panel, int taillePolice, int columns) {
		JTextField texte = new JTextField();
		
		panel.add(texte);
		texte.setFont(new Font("Roboto",Font.PLAIN, taillePolice));
		texte.setColumns(columns);
		
		return texte;
	}
	
	/* Retourne un JLabel ajouté à son panel
	 * Entrées :
	 * 	panel, JPanel, panel contenant le JLabel
	 * 	texteLabel, String, texte du JLabel
	 * 	taillePolice, int, taille de la police du label
	 */
	public default JLabel creerJLabel(JPanel panel, String texteLabel, int taillePolice) {
		JLabel label = new JLabel(texteLabel);
		
		panel.add(label);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Roboto", Font.BOLD, taillePolice));
		
		return label;
	}
	
	/* Retourne un JPanel ajouté à son panel
	 * Entrées :
	 * 	panelContenant, JPanel, panel contenant le nouveau JPanel
	 * 	couleur, Color, couleur du JPanel créé
	 */
	public default JPanel creerJPanel(JPanel panelContenant, Color couleur) {
		JPanel panel = new JPanel();
		
		panelContenant.add(panel);
		panel.setBackground(couleur);
		
		return panel;
	}
	
	public default GridBagLayout creerGridBagLayout(int[] columnWidth, int[] rowHeight, double[] columnWeight, double[] rowWeight) {
		GridBagLayout layout = new GridBagLayout();
		
		layout.columnWidths = columnWidth;
		layout.rowHeights = rowHeight;
		layout.columnWeights = columnWeight;
		layout.rowWeights = rowWeight;
		
		return layout;
	}
	
	public default GridBagConstraints creerGridBagConstraints(int placement, int orientation, Insets inset, int x, int y) {
		GridBagConstraints layout = new GridBagConstraints();
		
		layout.anchor = placement;
		layout.fill = orientation;
		layout.insets = inset;
		layout.gridx = x;
		layout.gridy = y;
		
		return layout;
	}
	
	// Active un bouton donné
	public static void activerBouton(JButton btn) {
		btn.setEnabled(true);
	}
	
	// Désactive un bouton donné
	public static void desactiverBouton(JButton btn) {
		btn.setEnabled(false);
	}
	
	// MESSAGE //
	public static void estVide() {		
        JOptionPane.showMessageDialog(null, "Veuillez compléter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
	
	/* Vérifie si un champs a été rempli */
	public static boolean estSaisiRempli(String compare, String comparaison, JLabel nom, JComponent entree) {
		if (compare.equals(comparaison)) {
			nom.setForeground(Color.RED);
			entree.setForeground(Color.RED);
			return false;
			
		} else {
			nom.setForeground(Color.WHITE);
			entree.setForeground(Color.BLACK);
			return true;
			
		}
	}
}
