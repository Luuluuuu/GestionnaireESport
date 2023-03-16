package modele;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JListBuilder<T> {
    private final JList<T> liste;

    public JListBuilder(DefaultListModel<T> modele, JPanel panel) {
        liste = new JList<>(modele);
        panel.add(liste);
    }

    public JListBuilder<T> setCustomButton(int nbLigne, Font font, int hauteurCellule, int largeurCellule) {
        liste.setVisibleRowCount(nbLigne);
        liste.setFont(font);
        liste.setFixedCellHeight(hauteurCellule);
        liste.setFixedCellWidth(largeurCellule);
        return this;
    }

    public JList<T> build() {
        return liste;
    }

    public JScrollPane buildAvecScrollPane() {
        return new JScrollPane(liste);
    }
}