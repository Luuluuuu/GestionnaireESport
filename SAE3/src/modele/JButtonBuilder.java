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
	   
	   public JButtonBuilder setText(String text) {
	      bouton.setText(text);
	      return this;
	   }
	   
	   public JButtonBuilder setX(int x) {
	      bouton.setAlignmentX(x);
	      return this;
	   }

	   public JButtonBuilder setY(int y) {
		   bouton.setAlignmentY(y);
	      return this;
	   }

	   public JButtonBuilder setTaille(int width, int height) {
		   bouton.setPreferredSize(new Dimension(width, height));
	       return this;
	   }
	      

	   public JButtonBuilder setPremierPlan(Color c) {
		   bouton.setForeground(c);
		   return this;
	   }
	   
	   public JButtonBuilder setPolice(Font font) {
	        bouton.setFont(font);
	        return this;
	    }

	    public JButtonBuilder setFondEcran(Color background) {
	        bouton.setBackground(background);
	        return this;
	    }

	   public JButton build() {
	      return bouton;
	   }
	}
