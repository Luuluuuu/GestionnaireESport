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
import modele.Joueur;
import modele.Utilisateur.Profil;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;

public class ControleurEquipe implements ActionListener, ListSelectionListener {
	
	public enum Etat{RECHERCHER,VALIDER,ANNULER,CREER,SUPPRIMER,DECONNECTER,ECURIE,CALENDRIER,JOUEURS,EQUIPE,CLASSEMENT,TOURNOIS}
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
		this.vue.ajouterEcurie("- S�lectionnez une �curie -");
		for (String nomEcurie : ControleurConnexion.listeEcuries.keySet()) {
			this.vue.ajouterEcurie(nomEcurie);
		}
	}
	
	public void initialiserListeJeux() {
		this.vue.ajouterJeu("- S�lectionnez un jeu -");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		break;
		case ECURIE :
			VueERA fenERA = new VueERA();
			fenERA.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		break;
		case JOUEURS :
			VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		break;
		case TOURNOIS:
			VueInscriptionTournoi fenTournoi = new VueInscriptionTournoi();
			fenTournoi.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);			
			break;
		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		case RECHERCHER:
			if (this.vue.getRecherche()!="") {
				this.vue.filtrerRecherche();
			} else {
				this.vue.setDefaultListModel();
			}
			this.vue.creerEquipe();
			break;
		case VALIDER:
			//Vérifie que tous les champs sont remplis
			if (ControleurConnexion.profilUtilisateur == Profil.GESTIONNAIRE && this.vue.getEcurie().equals("- S�lectionnez une �curie -")) {
				this.vue.estVide();
			} else {
				if(this.vue.getNom().equals("") || this.vue.getJeu().equals("- S�lectionnez un jeu -")) {
					this.vue.estVide();
				} else {
					// Instancie un tournoi
					Equipe equipe = new Equipe(0,this.vue.getNom(),0,this.vue.getNationalite(),ControleurConnexion.listeJeux.get(this.vue.getJeu()),
							ControleurConnexion.listeEcuries.get(this.vue.getEcurie()));
					//Vérifie si c'est une creation ou une modification
					if (this.vue.titreModif.getText().equals("Cr�er une �quipe")) {
						// SI CREATION
						if (!(ControleurConnexion.listeEquipes.containsKey(equipe.getNom()))) {
							// En cas de creation, on recupere la prochaine valeur de la sequence, pour l'attribuer a l'equipe
							ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_equipeId.nextval FROM dual");
							try {
								if (rs.next()) {equipe.setID(rs.getInt(1));}
								rs.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
								Connexion.getInstance().executerRequete("INSERT INTO sae_equipe (idequipe,nomequipe,anneedecreation,nombrepoints,nombrejoueurs,nationalite,idjeu)"
										+ "VALUES (seq_equipeId.currval, '"+equipe.getNom()+"', "
								+Year.now().getValue()+", 0, 0, '"+equipe.getNationalite()+"',"+ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID()
								+")");
							}else {
								Connexion.getInstance().executerRequete("INSERT INTO sae_equipe VALUES (seq_equipeId.currval, '"+equipe.getNom()+"', "
								+Year.now().getValue()+", 0, 0, '"+equipe.getNationalite()+"',"+ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID()
								+","+ControleurConnexion.listeEcuries.get(this.vue.getEcurie()).getID()+")");
							}
							ControleurConnexion.listeEquipes.put(equipe.getNom(),equipe);	
							ControleurConnexion.listeEquipesID.put(equipe.getID(),equipe);
							this.vue.ajouterEquipe(equipe.getNom());
						}
					}else {
						// SINON MODIFICATION
						equipe.setID(ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne()).getID());
						if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
							Connexion.getInstance().executerRequete("UPDATE SAE_EQUIPE SET NOMEQUIPE = '"+equipe.getNom()+
									"', NATIONALITE = '"+this.vue.getNationalite()+"', IDJEU = "+ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID()
									+"WHERE IDEQUIPE = "+equipe.getID());
						} else {
							Connexion.getInstance().executerRequete("UPDATE SAE_EQUIPE SET NOMEQUIPE = '"+equipe.getNom()+
									"', NATIONALITE = '"+this.vue.getNationalite()+"', IDJEU = "+ControleurConnexion.listeJeux.get(this.vue.getJeu()).getID()
									+",IDECURIE =  "+ControleurConnexion.listeEcuries.get(this.vue.getEcurie()).getID()+"WHERE IDEQUIPE = "+equipe.getID());
						}
						ControleurConnexion.listeEquipes.remove(this.vue.getEquipeSelectionne());
						ControleurConnexion.listeEquipes.put(equipe.getNom(), equipe);
						ControleurConnexion.listeEquipesID.put(equipe.getID(), equipe);
						this.vue.modifierEquipe();
						}
					this.vue.creerEquipe();
				}
			}
			break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
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
					ResultSet rs = c.retournerRequete("SELECT * FROM SAE_INSCRIRE WHERE IDEQUIPE =" + equipe.getID());
					
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		default:
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch(this.etat) {
		case SUPPRIMER:
		default:
			@SuppressWarnings("unchecked")
			JList<String> list = (JList<String>) e.getSource();
			switch(list.getName()) {
			case "Equipe":
				if (!(list.isSelectionEmpty())) {
					VueEquipe.afficherTexte(this.vue.titreModif, "Modifier une �quipe");
					Equipe equipe = ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne());
					this.vue.setNomEquipe(equipe.getNom());
					this.vue.setJeu(equipe.getNomJeu());
					this.vue.setEcurie(equipe.getEcurie().getNom());
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
					VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
			    }
			}
		}
	}

}
