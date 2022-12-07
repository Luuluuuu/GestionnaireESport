package vue;

import java.awt.EventQueue;

public class ApplicationEcurie {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueEcurie window = new VueEcurie();
					window.fenetreEcurie.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
