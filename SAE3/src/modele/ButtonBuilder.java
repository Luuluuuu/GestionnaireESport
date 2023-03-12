package modele;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonBuilder {
	   private String text;
	   private int x;
	   private int y;
	   private int width;
	   private int height;
	   private Color c;
	   private Font font;
	   private Color background;

	   public ButtonBuilder setText(String text) {
	      this.text = text;
	      return this;
	   }

	   public ButtonBuilder setX(int x) {
	      this.x = x;
	      return this;
	   }

	   public ButtonBuilder setY(int y) {
	      this.y = y;
	      return this;
	   }

	   public ButtonBuilder setWidth(int width) {
	      this.width = width;
	      return this;
	   }

	   public ButtonBuilder setHeight(int height) {
	      this.height = height;
	      return this;
	   }
	   
	   public ButtonBuilder setForeground(Color c) {
		   this.c = Color.WHITE;
		   return this;
	   }
	   
	   public ButtonBuilder setFont(Font font) {
	        this.font = font;
	        return this;
	    }

	    public ButtonBuilder setBackground(Color background) {
	        this.background = background;
	        return this;
	    }

	   public JButton build() {
	      JButton button = new JButton(text);
	      button.setBounds(x, y, width, height);
	      button.setForeground(c);
	      button.setFont(font);
	      button.setBackground(background);
	      return button;
	   }
	}
