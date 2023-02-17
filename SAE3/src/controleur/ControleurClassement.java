package controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;

public class ControleurClassement implements ActionListener {
	public enum Etat {
		DECONNECTER, ECURIE, CLASSEMENT, EQUIPES, JOUEURS, CALENDRIER, TOURNOIS, JEU
	}
	
	private VueClassement vue;
	private Etat etat;
	
	public ControleurClassement(VueClassement vue) {
		this.vue = vue;
		this.initialiserListeClassement("IDJEU");
		this.initialiserListeJeux();
	}
	
	public void initialiserListeClassement(String idJeu) {
		this.vue.viderClassement();
		int place = 1;
		try {
			ResultSet rs = Connexion.getInstance().retournerRequete("SELECT NOMEQUIPE, NOMBREPOINTS FROM SAE_EQUIPE WHERE IDJEU = "+idJeu+" ORDER BY NOMBREPOINTS DESC");
			while (rs.next()) {
				if (place <= 3) {
					this.vue.setPodium(place,rs.getString(1),rs.getInt(2));
				} else {
					this.vue.ajouterEquipe(place + " | " + rs.getString(1) + "(" + rs.getString(2) + ")");
				}
				place++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeJeux() {
		this.vue.ajouterJeu("- Sélectionnez un jeu -");
		for (String nomJeu : ControleurConnexion.listeJeux.keySet()) {
			this.vue.ajouterJeu(nomJeu);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			this.etat = this.vue.getEtat(b);
		} else {
			this.etat = Etat.JEU;
		}
		switch (this.etat) {
		case ECURIE :
			VueERA fenEcurie = new VueERA();
			fenEcurie.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case EQUIPES :
			VueEquipe fenEquipe = new VueEquipe();
			fenEquipe.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case JOUEURS :
			VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case TOURNOIS :
			VueInscriptionTournoi fenTournois = new VueInscriptionTournoi();
			fenTournois.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueClassement.fermerFenetre(this.vue.fenetreClassement);
		break;
		case JEU:
			if (this.vue.getJeu().equals("- Sélectionnez un jeu -")) {
				this.initialiserListeClassement("IDJEU");
			} else {
				this.initialiserListeClassement(Integer.toString(ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID()));
			}
			break;
		default:
		}
		
	}
}
