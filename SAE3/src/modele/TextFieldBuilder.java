package modele;

import java.awt.Font;

import javax.swing.JTextField;

public class TextFieldBuilder {
	   private Font font;
	   private int columns;
	   
	   public TextFieldBuilder setPolice(Font font) {
	        this.font = font;
	        return this;
	    }

	    public TextFieldBuilder setColonnes(int columns) {
	        this.columns = columns;
	        return this;
	    }

	   public JTextField creer() {
	      JTextField textfield = new JTextField();
	      textfield.setFont(font);
	      textfield.setColumns(columns);
	      return textfield;
	   }
	}
