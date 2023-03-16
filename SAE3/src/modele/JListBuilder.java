package modele;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class JListBuilder<T> {
    private final JList<T> liste;

    public JListBuilder(DefaultListModel<T> modele, JPanel panel) {
        liste = new JList<>(modele);
        panel.add(liste);
    }

 
	public JListBuilder<T> setNombreLigne(int count) {
        liste.setVisibleRowCount(12);
        return this;
    }

    public JListBuilder<T> setPolice(Font font) {
        liste.setFont(font);
        return this;
    }

    public JListBuilder<T> setHauteurCellule(int height) {
        liste.setFixedCellHeight(height);
        return this;
    }

    public JListBuilder<T> setLargeurCellule(int width) {
        liste.setFixedCellWidth(width);
        return this;
    }

    public JList<T> build() {
        return liste;
    }

    public JScrollPane buildAvecScrollPane() {
        return new JScrollPane(liste);
    }
}

