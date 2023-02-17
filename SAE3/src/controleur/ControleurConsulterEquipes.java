package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Equipe;
import modele.Jeu;
import modele.Responsable;
import modele.Tournoi;
import modele.Utilisateur;
import vue.VueConnexion;
import vue.VueConsulterEquipes;

public class ControleurConsulterEquipes implements ActionListener, ListSelectionListener {

	public enum Etat {DECONNECTER, PDF, JEU, POULE1, POULE2, POULE3, POULE4, POULEF}
	
	private VueConsulterEquipes vue;
	private Etat etat;
	
	public ControleurConsulterEquipes(VueConsulterEquipes vue) {
		this.vue = vue;
		this.initialiserListeTournois();
	}
	
	public void initialiserListeTournois() {
		for (Tournoi t : ControleurConnexion.listeTournois.values()) {
			this.vue.ajouterTournoi(t.getNom());
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
		Jeu j;
		Tournoi t;
		Jeu j2;
		switch (this.etat) {
		case DECONNECTER:
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueConsulterEquipes.fermerFenetre(this.vue.fenetreConsulterEquipes);
		break;
		case JEU:
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : ControleurConnexion.listeEquipes.values()) {
				if (equipe.getJeu().getNom().equals(this.vue.getJeuSelectionne())) {
					this.vue.ajouterEquipe(equipe.getNom());
				}
			}
			break;
		case POULE1:
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(1)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULE2:
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(2)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULE3:
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(3)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULE4:
			this.vue.viderEquipes();
			j = ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne());
			t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			j2 = t.getJeu(j);
			
			for (Equipe equipe : j2.getEquipePouleI(4)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
			break;
		case POULEF:
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
		default:
			break;
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		JList<String> l = (JList<String>) e.getSource();
		if (!l.isSelectionEmpty()) {
			this.vue.viderJeux();
			this.vue.ajouterJeu("- S�lectionnez un jeu -");
			// On récupère le tournoi sélectionné
			Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			// On récupère les jeux du tournoi
			List<Jeu> j = t.getJeux();
			for (Jeu jeu : j) {
				this.vue.ajouterJeu(jeu.getNom());
			}
		}
	}
	
}
