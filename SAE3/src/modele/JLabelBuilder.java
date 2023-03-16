package modele;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JLabelBuilder {
	private final JLabel label;
	   
	   public JLabelBuilder(JPanel panel) {
	       this.label = new JLabel();
	       panel.add(label);
	   }
	  
	   public JLabelBuilder setCustomLabel(String text, Font font, Color foreground) {
	        label.setText(text);
	        label.setForeground(foreground);
	        label.setFont(font);
	        return this;
}

	public JLabel build() {
		return label;
	}
}