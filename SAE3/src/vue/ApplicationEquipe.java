package vue;

import java.awt.EventQueue;

public class ApplicationEquipe {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueEquipe window = new VueEquipe();
					window.fenetreEquipe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}