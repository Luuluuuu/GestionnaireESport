package modele;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class JTextFieldBuilder {

        private final JTextField text;

        public JTextFieldBuilder(JPanel panel) {
            this.text = new JTextField();
            panel.add(text);
        }

       public JTextFieldBuilder setCustomTextField(Font font, int columns) {
           text.setFont(font);
           text.setColumns(columns);
           return this;
        }

       public JTextField build() {
           return text;
       }
}