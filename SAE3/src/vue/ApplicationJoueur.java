package vue;

import java.awt.EventQueue;

public class ApplicationJoueur {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueJoueur window = new VueJoueur();
					window.fenetreJoueur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
