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
import modele.Tournoi;
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
		// Récupère l'état en cours
		if (e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			this.etat = this.vue.getEtat(b);
		} else {
			this.etat = Etat.JEU;
		}
		
		// Action
		switch (this.etat) {
		case DECONNECTER:
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueConsulterEquipes.fermerFenetre(this.vue.fenetreConsulterEquipes);
		break;
		
		case JEU:
			// Vide la liste des équipes de la vue
			this.vue.viderEquipes();
			
			for (Equipe equipe : ControleurConnexion.listeEquipes.values()) {
				if (equipe.getJeu().getNom().equals(this.vue.getJeuSelectionne())) {
					this.vue.ajouterEquipe(equipe.getNom());
				}
			}
			break;
		
		// Gestion du clic sur une poule
		case POULE1:
			majListeEquipes(1);
			break;
			
		case POULE2:
			majListeEquipes(2);
			break;
			
		case POULE3:
			majListeEquipes(3);
			break;
			
		case POULE4:
			majListeEquipes(4);
			break;
			
		case POULEF:
			majListeEquipes(5);
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
			this.vue.ajouterJeu("- Sélectionnez un jeu -");
			// On récupère le tournoi sélectionné
			Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
			// On récupère les jeux du tournoi
			List<Jeu> j = t.getJeux();
			for (Jeu jeu : j) {
				this.vue.ajouterJeu(jeu.getNom());
			}
		}
	}
	
	/* Met à jour visuellement la liste des équipes d'une poule donnée
	 * 	Entrée :
	 * 		numeroPoule	int	: Numéro de la poule souhaitée
	*/
	private void majListeEquipes(int numeroPoule) {
		// Vide la liste des équipes
		this.vue.viderEquipes();
		
		// Récupère le tournoi sélectionné
		Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
		
		// Récupère le jeu du tournoi à partir du jeu sélectionné
		// Permet de récupérer les équipes inscrites à ce jeu
		Jeu j = t.getJeu(ControleurConnexion.listeJeux.get(this.vue.getJeuSelectionne())); 
		
		// Ajoute les équipes inscrites à la liste
		if (j.existeEquipe(numeroPoule)) {
			for (Equipe equipe : j.getEquipePouleI(numeroPoule)) {
				this.vue.ajouterEquipe(equipe.getNom());
			}
		}
	}
	
}
