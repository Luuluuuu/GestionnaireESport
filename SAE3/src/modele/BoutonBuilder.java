package modele;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class BoutonBuilder {
	   private String text;
	   private int x;
	   private int y;
	   private int width;
	   private int height;
	   private Color c;
	   private Font font;
	   private Color background;

	   public BoutonBuilder setTexte(String text) {
	      this.text = text;
	      return this;
	   }

	   public BoutonBuilder setX(int x) {
	      this.x = x;
	      return this;
	   }

	   public BoutonBuilder setY(int y) {
	      this.y = y;
	      return this;
	   }

	   public BoutonBuilder setLargeur(int width) {
	      this.width = width;
	      return this;
	   }

	   public BoutonBuilder setHauteur(int height) {
	      this.height = height;
	      return this;
	   }
	   
	   public BoutonBuilder setCouleurTexte(Color c) {
		   this.c = c;
		   return this;
	   }
	   
	   public BoutonBuilder setPolice(Font font) {
	        this.font = font;
	        return this;
	    }

	    public BoutonBuilder setCouleurFond(Color background) {
	        this.background = background;
	        return this;
	    }

	   public JButton creer() {
	      JButton button = new JButton(text);
	      button.setBounds(x, y, width, height);
	      button.setForeground(c);
	      button.setFont(font);
	      button.setBackground(background);
	      return button;
	   }
	}
