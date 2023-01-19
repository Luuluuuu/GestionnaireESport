package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

import modele.Connexion;
import modele.Joueur;
import modele.Utilisateur;
import vue.VueClassementJoueur;
import vue.VueConnexion;
import vue.VueEquipesJoueur;
import vue.VueProfilJoueur;
import vue.VueTournoisJoueur;

public class ControleurClassementJoueur implements ActionListener {
public enum Etat{DECONNECTER, PROFIL, TOURNOIS, EQUIPES}
	
	private VueClassementJoueur vue;
	private Etat etat;
	private Joueur joueur;
	
	public ControleurClassementJoueur(VueClassementJoueur vue) {
		this.vue = vue;
		
		// INITIALISE LE JOUEUR CONNECTE
		for (Joueur j : ControleurConnexion.listeJoueurs.values()) {
			if (j.getID() == Utilisateur.IDCourant) {
				this.joueur = j;
			}
		}
		
		this.vue.setInfosJoueur(joueur.getPhoto(), joueur.getPseudo(), joueur.getEquipe().getNom());
		this.initialiserListeClassement("IDJEU");
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case PROFIL :
			VueProfilJoueur fenEquipe = new VueProfilJoueur();
			fenEquipe.getFrame().setVisible(true);
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		case TOURNOIS :
			VueTournoisJoueur fenTournois = new VueTournoisJoueur();
			fenTournois.getFrame().setVisible(true);
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		case EQUIPES :
			VueEquipesJoueur fenClassement = new VueEquipesJoueur();
			fenClassement.getFrame().setVisible(true);
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueClassementJoueur.fermerFenetre(this.vue.fenetreClassementJoueur);
		break;
		default:
		}
		
	}
}
