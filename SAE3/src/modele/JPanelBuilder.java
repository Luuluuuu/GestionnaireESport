package modele;

import java.awt.Color;

import javax.swing.JPanel;

public class JPanelBuilder {
	private final JPanel monPanel;
	   
	public JPanelBuilder(JPanel panel) {
       this.monPanel = new JPanel();
       panel.add(monPanel);
    }
    
    public JPanelBuilder setCustomPanel(Color background) {
    	monPanel.setBackground(background);
        return this;
    }

   public JPanel build() {
      return monPanel;
   }
}
