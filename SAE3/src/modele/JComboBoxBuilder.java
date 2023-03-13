package modele;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class JComboBoxBuilder<T> {
    private final JComboBox<T> comboBox;
    private JPanel panel;

    public JComboBoxBuilder(JPanel panel) {
        comboBox = new JComboBox<>();
        panel.add(comboBox);
    }

    public JComboBoxBuilder<T> setPolice(Font font) {
        comboBox.setFont(font);
        return this;
    }

    public JComboBoxBuilder<T> setTaille(int width, int height) {
        comboBox.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public JComboBoxBuilder<T> addElement(T item) {
        comboBox.addItem(item);
        return this;
    }
    
    public JComboBox<T> build() {
        return comboBox;
    }
}