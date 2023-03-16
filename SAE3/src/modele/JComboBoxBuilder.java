package modele;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class JComboBoxBuilder<T> {
    private final JComboBox<T> comboBox;

    public JComboBoxBuilder(JPanel panel) {
        comboBox = new JComboBox<T>();
        panel.add(comboBox);
    }
    
    public JComboBoxBuilder<T> setCustomComboBox(Font font, Dimension dimension) {
    	comboBox.setFont(font);
    	comboBox.setPreferredSize(dimension);
        return this;
    }
    
    public JComboBox<T> build() {
        return comboBox;
    }
}