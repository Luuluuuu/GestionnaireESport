package vue;

import java.awt.EventQueue;

public class Application {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueConnexion window = new VueConnexion();
					window.fenetreConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}