package controleur;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Equipe;
import modele.Etat;
import modele.Joueur;
import modele.Profil;
import modele.Utilisateur;
import vue.Vue;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;

public class ControleurEquipe implements ActionListener, ListSelectionListener {
	
	private VueEquipe vue;
	private Etat etat;
	
	public ControleurEquipe(VueEquipe vue) {
		this.vue = vue;
		this.initialiserListes();
		this.etat = Etat.RECHERCHER;
	}
	
	// Initialiser les listes
	public void initialiserListes() {
		this.initialiserListeEcuries();
		this.initialiserListeJeux();
		this.initialiserListeEquipes();
	}
	
	public void initialiserListeEquipes() {	
		if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
			for (String nomEquipe : ControleurConnexion.listeEquipesParEcurie) {
				this.vue.ajouterEquipe(nomEquipe);
			}
		}
		else {
			for (String nomEquipe : ControleurConnexion.listeEquipes.keySet()) {
				this.vue.ajouterEquipe(nomEquipe);
			}
		}
	}
	
	public void initialiserListeEcuries() {
		this.vue.ajouterEcurie("- Sélectionnez une écurie -");
		for (String nomEcurie : ControleurConnexion.listeEcuries.keySet()) {
			this.vue.ajouterEcurie(nomEcurie);
		}
	}
	
	public void initialiserListeJeux() {
		this.vue.ajouterJeu("- Sélectionnez un jeu -");
		for (String nomJeu : ControleurConnexion.listeJeux.keySet()) {
			this.vue.ajouterJeu(nomJeu);
		}
	}
	
	public void initialiserListeJoueurs(Equipe equipe) {
		this.vue.viderModeleJoueurs();
		for (Joueur joueur : equipe.getJoueurs()) {
			this.vue.ajouterJoueur(joueur.getPrenomPseudoNom());
		}
	}
	
	// Retourne vrai si le profil est identique à celui saisi
	public static boolean estProfil(String nom) {
		return ControleurConnexion.profilUtilisateur.toString().equals(nom);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.getFrame());
			break;
		
		case ECURIE :
			VueERA fenERA = new VueERA();
			fenERA.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.getFrame());
			break;
		
		case JOUEURS :
			VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.getFrame());
			break;
		
		case TOURNOIS:
			VueInscriptionTournoi fenTournoi = new VueInscriptionTournoi();
			fenTournoi.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.getFrame());			
			break;
			
		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.getFrame());
			break;
			
		case RECHERCHER:
			if (!this.vue.getRecherche().equals("")) {
				this.vue.filtrerRecherche();
			} else {
				this.vue.setDefaultListModel();
			}
			this.vue.creerEquipe();
			break;
			
		case ANNULER :
            this.vue.annulerEntreeEquipe();
            break;
            
		case VALIDER:
			if (this.vue.estFormulaireRempli()) {
					// Instancie l'équipe créée
					Equipe equipe = new Equipe(0, this.vue.getNomEquipe(), 0, this.vue.getNationalite(), 
							ControleurConnexion.listeJeux.get(this.vue.getJeu()),
							ControleurConnexion.listeEcuries.get(this.vue.getEcurie()));
					//Vérifie si c'est une création ou une modification
					if (this.vue.getTitreModification().getText().equals("Créer une équipe")) {
						// SI CREATION
						this.creerEquipe(equipe);
					} else {
						// SINON MODIFICATION
						this.modifierEquipe(equipe);
					}
					this.vue.creerEquipe();
			}
			break;
			
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.getFrame());
			break;
		
		case CREER:
			this.vue.creerEquipe();
			break;
			
		case SUPPRIMER:
			// Vérifie si l'équipe est bien sélectionnée
			if (this.vue.getEquipeSelectionne() != null) {
				// Récupération de l'équipe sélectionnée
				Equipe equipe = ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne());
				// Récupération de la connexion
				Connexion c = Connexion.getInstance();
				try {
					ResultSet rs = c.retournerRequete("SELECT * FROM SAE_INSCRIRE " + 
							"WHERE IDEQUIPE =" + equipe.getID());
					// Si l'équipe est déjà inscrite, la suppression est impossible
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "L'équipe sélectionnée ne peut pas être supprimée !",
							      "Erreur à la suppression", JOptionPane.ERROR_MESSAGE);
					// Demande la confirmation de l'utilisateur
					} else if (this.vue.confirmerSuppression() == 0) { 
						// Suppression de l'équipe dans la vue et les hashmap
						this.vue.supprimerEquipe();
						ControleurConnexion.listeEquipes.remove(equipe.getNom());
						// Suppression des joueurs appartenant à l'équipe
						c.executerRequete("DELETE SAE_JOUEUR WHERE IDEQUIPE =" + equipe.getID());	
						// Suppression de l'équipe
						c.executerRequete("DELETE SAE_EQUIPE WHERE IDEQUIPE = " + equipe.getID());	
						this.vue.creerEquipe();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			break;
			
		default:
			break;
		}
		//désactive le bouton lorsque aucun élément n'est séléctionné
		Vue.desactiverBouton(this.vue.getBtnSupprimer());
	}

	@Override
	public void valueChanged(ListSelectionEvent e){
		switch(this.etat) {
		case SUPPRIMER:
		default:
			@SuppressWarnings("unchecked")
			JList<String> list = (JList<String>) e.getSource();
			switch(list.getName()) {
			case "Equipe":
				if (!(list.isSelectionEmpty())) {
					VueEquipe.afficherTexte(this.vue.getTitreModification(), "Modifier une équipe");
					Equipe equipe = ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne());
					this.vue.setNomEquipe(equipe.getNom());
					this.vue.setJeu(equipe.getNomJeu());
					// Récupère l'écurie lorsque le profil est Gestionnaire
					if (ControleurConnexion.profilUtilisateur == Profil.GESTIONNAIRE) {
						this.vue.setEcurie(equipe.getEcurie().getNom());
					}
					this.vue.setNationalite(equipe.getNationalite());
					this.initialiserListeJoueurs(equipe);
				}
				break;
				
			case "Joueurs":
			    if (!e.getValueIsAdjusting()) {	// gere les doubles clics
					VueJoueur fenJoueur = new VueJoueur();
					fenJoueur.getFrame().setVisible(true);
					VueJoueur.afficherTexte(fenJoueur.titreModif, "Modifier un joueur");
					Joueur joueur = ControleurConnexion.listeJoueurs.get(this.vue.getJoueurSelectionne());
					fenJoueur.setEquipe(joueur.getEquipe().getNom());
					fenJoueur.setNomJoueur(joueur.getNom());
					fenJoueur.setPrenomJoueur(joueur.getPrenom());
					fenJoueur.setPseudoJoueur(joueur.getPseudo());
					fenJoueur.setDateNaissanceJoueur(joueur.getDateNaissance());
					fenJoueur.setNationaliteJoueur(joueur.getNationalite());
					VueEquipe.fermerFenetre(this.vue.getFrame());
			    }
			}
		}
		//réactive le bouton lorsque un élément de la liste est cliqué
		if (!e.getValueIsAdjusting()) {
			Vue.activerBouton(this.vue.getBtnSupprimer());
        } 
	}
	
	private void creerEquipe(Equipe equipe) {
		if (!(ControleurConnexion.listeEquipes.containsKey(equipe.getNom()))) {
			// En cas de creation, on recupere la prochaine valeur de la sequence, pour l'attribuer a l'equipe
			ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_equipeId.nextval FROM dual");
			try {
				if (rs.next()) {
					equipe.setID(rs.getInt(1));
				}
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			// On récupère l'ID de l'écurie courante ou sélectionnée
			int idEcurie;
			if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
				idEcurie = Utilisateur.IDCourant;
			} else {
				idEcurie = ControleurConnexion.listeEcuries.get(this.vue.getEcurie()).getID();
			}
			
			Connexion.getInstance().executerRequete("INSERT INTO sae_equipe VALUES (seq_equipeId.currval, '" 
					+ equipe.getNom() + "', "
				    + Year.now().getValue() 
				    + ", 0, 0, '"
				    + equipe.getNationalite() + "',"
				    + ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID() + ", "
				    + idEcurie + ")");
			
			// Mise à jour de la liste locale et de la vue
			ControleurConnexion.listeEquipes.put(equipe.getNom(),equipe);	
			ControleurConnexion.listeEquipesID.put(equipe.getID(),equipe);
			this.vue.ajouterEquipe(equipe.getNom());
			
		} else {
			JOptionPane.showMessageDialog(null, "L'équipe existe déjà !", "Insertion impossible", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void modifierEquipe(Equipe equipe) {
		// Récupère l'ID de l'équipe
		equipe.setID(ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne()).getID());
		
		// On ne modifie pas l'écurie de l'équipe lorsque le profil est Ecurie
		if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
			Connexion.getInstance().executerRequete("UPDATE SAE_EQUIPE SET NOMEQUIPE = '" + equipe.getNom() + 
					"', NATIONALITE = '" + this.vue.getNationalite() + 
					"', IDJEU = " + ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID() + 
					"WHERE IDEQUIPE = " + equipe.getID());
			
		} else {
			Connexion.getInstance().executerRequete("UPDATE SAE_EQUIPE SET NOMEQUIPE = '" + equipe.getNom() + 
					"', NATIONALITE = '" + this.vue.getNationalite() + 
					"', IDJEU = " + ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID() + 
					", IDECURIE = " + ControleurConnexion.listeEcuries.get(this.vue.getEcurie()).getID() + 
					"WHERE IDEQUIPE = " + equipe.getID());
		
		}
		
		// Mise à jour de la liste locale et de la vue
		ControleurConnexion.listeEquipes.remove(this.vue.getEquipeSelectionne());
		ControleurConnexion.listeEquipes.put(equipe.getNom(), equipe);
		ControleurConnexion.listeEquipesID.put(equipe.getID(), equipe);
		this.vue.modifierEquipe();
		
		
	}
}
