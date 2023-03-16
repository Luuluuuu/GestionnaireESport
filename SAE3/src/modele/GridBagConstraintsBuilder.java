package modele;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagConstraintsBuilder {
	private final GridBagConstraints grid;
	   
	public GridBagConstraintsBuilder() {
       this.grid = new GridBagConstraints();
    }
    
    public GridBagConstraintsBuilder setCustomGridBagConstraints(int placement, int orientation, Insets inset, int x, int y) {
    	grid.anchor = placement;
    	grid.fill = orientation;
		grid.insets = inset;
		grid.gridx = x;
		grid.gridy = y;
        return this;
    }

   public GridBagConstraints build() {
      return grid;
   }
}
