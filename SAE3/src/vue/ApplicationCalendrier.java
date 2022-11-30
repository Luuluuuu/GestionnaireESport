package vue;

import java.awt.EventQueue;

public class ApplicationCalendrier {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueCalendrier window = new VueCalendrier();
					window.fenetreCalendrier.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
