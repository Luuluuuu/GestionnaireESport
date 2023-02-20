package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Poule;
import modele.Tournoi;
import modele.Utilisateur;
import vue.VueConnexion;
import vue.VueEquipe;
import vue.VueJoueur;
import vue.VueRentrerPoints;

public class ControleurRentrerPoints implements ActionListener, ListSelectionListener{
	public enum Etat {DECONNECTER, JEU, POULE1, POULE2, POULE3, POULE4, POULEF, VALIDER}
	
	private VueRentrerPoints vue;
	private Etat etat;
	private Etat etatPoule;
	
	public ControleurRentrerPoints(VueRentrerPoints vue) {
		this.vue = vue;
		this.initialiserListeTournois();
	}
	
	public void initialiserListeTournois() {
		for (String nomTournoi : ControleurConnexion.listeTournois.keySet()) {
			this.vue.ajouterTournoi(nomTournoi);
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
		case DECONNECTER:
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreRentrerPoints);
		break;
		case JEU:
			this.etatPoule = Etat.JEU;
			this.vue.viderEquipes();
			Jeu j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			Jeu j2 = t.getJeu(j);	
			break;
		case POULE1:
			this.etatPoule = Etat.POULE1;
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(1)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULE2:
			this.etatPoule = Etat.POULE2;
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(2)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULE3:
			this.etatPoule = Etat.POULE3;
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(3)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULE4:
			this.etatPoule = Etat.POULE4;
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(4)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULEF:
			this.etatPoule = Etat.POULEF;
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);

			if (j2.existeEquipe(5)) {
				for (Equipe equipe : j2.getEquipePouleI(5)) {
					this.vue.ajouterEquipe(equipe.getNom());
				}
			}
			break;
		case VALIDER:
			int confirme = JOptionPane.showConfirmDialog(null, "Confirmez-vous l'�quipe gagnante ?","Confirmation",JOptionPane.YES_NO_OPTION);
			if (confirme == 0) {
				j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
				t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
				j2 = t.getJeu(j);
				Poule p = null;
				Equipe equipe = ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne());
				switch (this.etatPoule) {
				case POULE1:
					p = j2.getPoule(1);;
					j2.setGagnant(1,equipe);
					break;
				case POULE2:
					p = j2.getPoule(2);
					j2.setGagnant(2,equipe);
					break;
				case POULE3:
					p = j2.getPoule(3);
					j2.setGagnant(3,equipe);
					break;
				case POULE4:
					p = j2.getPoule(4);
					j2.setGagnant(4,equipe);
					break;
				case POULEF:
					p = j2.getPoule(5);
					j2.setGagnant(5,equipe);
					break;
				default:
					break;
				}
				Connexion.getInstance().executerRequete("UPDATE SAE_POULE SET GAGNANT = " + equipe.getID() + "WHERE IDPOULE = " +p.getID());
				CallableStatement cst = Connexion.getInstance().getCallableStatement("{? = call GENERER_POULE_FINALE(?,?)}");
				try {
					cst.registerOutParameter(1, java.sql.Types.INTEGER);
					cst.setInt(2, j2.getID());
					cst.setInt(3, t.getID());
					
					cst.execute();
					int estGenere = cst.getInt(1);
					
					if (estGenere == 1) {
						
						ResultSet rs = Connexion.getInstance().retournerRequete("SELECT IDPOULE FROM SAE_POULE WHERE IDPOULE = SEQ_POULEID.CURRVAL");
						Poule pouleFinale = new Poule(rs.getInt(1));
						
						Statement st = Connexion.getInstance().getStatement();
						ResultSet rs2 = st.executeQuery("SELECT IDEQUIPE FROM SAE_COMPOSER WHERE IDPOULE = "+pouleFinale.getID());
						while (rs2.next()) {
							pouleFinale.ajouterEquipe(ControleurConnexion.listeEquipesID.get(rs2.getInt(1)));
						}
						ControleurConnexion.listePoulesID.put(rs.getInt(1), pouleFinale);
						j2.ajouterPoule(pouleFinale);
					}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			break;
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		JList<String> list = (JList<String>) e.getSource();
	    if (!list.getValueIsAdjusting()) {	// gere les doubles clics
			Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			this.vue.setJeux(t.getNomJeux());
	    }
	}
}
