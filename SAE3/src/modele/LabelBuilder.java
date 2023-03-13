package modele;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelBuilder {
	   private String text;
	   private Color c;
	   private Font font;
	   
	   public LabelBuilder setTexte(String text) {
		      this.text = text;
		      return this;
		   }
	    
	    public LabelBuilder setPolice(Font font) {
	        this.font = font;
	        return this;
	    }
	    
	    public LabelBuilder setCouleurTexte(Color c) {
			   this.c = c;
			   return this;
		 }

	   public JLabel creer() {
		  JLabel label = new JLabel(text);
	      label.setFont(font);
	      label.setForeground(c);
	      return label;
	   }
	}