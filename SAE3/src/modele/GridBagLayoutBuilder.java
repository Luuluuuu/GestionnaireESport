package modele;

import java.awt.GridBagLayout;

public class GridBagLayoutBuilder {
	private final GridBagLayout grid;
	   
	public GridBagLayoutBuilder() {
       this.grid = new GridBagLayout();
    }
    
    public GridBagLayoutBuilder setCustomGridBagLayout(int[] columnWidth, int[] rowHeight, double[] columnWeight, double[] rowWeight) {
    	grid.columnWidths = columnWidth;
    	grid.rowHeights = rowHeight;
    	grid.columnWeights = columnWeight;
    	grid.rowWeights = rowWeight;
        return this;
    }

   public GridBagLayout build() {
      return grid;
   }
}
