package vue;

import java.awt.EventQueue;

public class ApplicationERA {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueERA window = new VueERA();
					window.fenetreERA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}