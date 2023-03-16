package modele;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class JButtonBuilder {
		private final JButton bouton;
	   
		public JButtonBuilder(JPanel panel) {
	       this.bouton = new JButton();
	       panel.add(bouton);
		}
	    
	    public JButtonBuilder setCustomButton(String text, Color foreground, Font font, Color background) {
	        bouton.setText(text);
	        bouton.setForeground(foreground);
	        bouton.setFont(font);
	        bouton.setBackground(background);
	        return this;
	    }

	   public JButton build() {
	      return bouton;
	   }
	}
