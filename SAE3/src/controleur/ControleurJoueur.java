package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modele.Connexion;
import modele.Equipe;
import modele.Joueur;
import vue.VueCalendrier;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueJoueur;



public class ControleurJoueur implements ActionListener, ListSelectionListener {
	
	public enum Etat{RECHERCHER,VALIDER,ANNULER,CREER,SUPPRIMER,DECONNECTER,ECURIE,CALENDRIER,JOUEURS,EQUIPES}
	private VueJoueur vue;
	private Etat etat;

	public ControleurJoueur(VueJoueur vue) {
		this.vue = vue;
		this.initialiserListes();
		this.etat = Etat.RECHERCHER;
	}
	
	// Initialiser les listes
	public void initialiserListes() {
		this.initialiserListeJoueurs();
	}
	
	public void initialiserListeJoueurs() {		
		for (String nomJoueur : ControleurConnexion.listeJoueurs.keySet()) {
			this.vue.ajouterJoueur(nomJoueur);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case EQUIPES :
			VueEquipe fenEQUIPE = new VueEquipe();
			fenEQUIPE.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.fenetreJoueur);
		break;
		case JOUEURS :
			/*VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.fenetreJoueur);*/
		break;
		case ECURIE :
			VueERA fenERA = new VueERA();
			fenERA.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.fenetreJoueur);
		break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.fenetreJoueur);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.fenetreJoueur);
		break;
		case CREER:
			this.vue.creerJoueur();
		break;
		case RECHERCHER:
			if (this.vue.getTextRecherche()!="") {
				this.vue.filtrerRecherche();
			} else {
				this.vue.setDefaultListModel();
			}
			this.vue.creerJoueur();
			break;
		case VALIDER:
			//Vérifie que tous les champs sont remplis
			if(this.vue.getNom().equals("")
					|| this.vue.getPrenom().equals("")
					|| this.vue.getPseudo().equals("")
					|| this.vue.getDateNaissance().equals("")
					|| this.vue.getNationalite().equals("")) {
				this.vue.estVide();
				b.setForeground(Color.RED);
			} else {
				// Instancie un tournoi
				Joueur joueur = new Joueur(0,this.vue.getNom(),this.vue.getPrenom(),this.vue.getPseudo(), this.vue.getDateNaissance(),
						this.vue.getNationalite());
				//Vérifie si c'est une creation ou une modification
				if (this.vue.titreModif.getText().equals("Créer un joueur")) {
					// SI CREATION
					if (!(ControleurConnexion.listeEquipes.containsKey(joueur.getPrenomPseudoNom()))) {
						// En cas de creation, on recupere la prochaine valeur de la sequence, pour l'attribuer au joueur
						try {
							ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_joueurid.nextval FROM dual");
							if (rs.next()) {
								joueur.setID(rs.getInt(1));
							}
							rs.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						Connexion.getInstance().executerRequete("INSERT INTO sae_joueur VALUES (seq_joueurid.currval, '"+joueur.getNom()
						+"', '"+joueur.getPrenom()+"', '"+joueur.getPseudo()+"', TO_DATE('"+joueur.getDateNaissance()+"','DD-MM-YYYY'), '"
						+joueur.getNationalite()+"', 1, '')");
						
						ControleurConnexion.listeJoueurs.put(joueur.getPrenomPseudoNom(),joueur);	
						this.vue.ajouterJoueur(joueur.getPrenomPseudoNom());
					}
				} else {
					// SINON MODIFICATION
					joueur.setID(ControleurConnexion.listeJoueurs.get(this.vue.getJoueurSelectionne()).getID());
					Connexion.getInstance().executerRequete("UPDATE SAE_JOUEUR SET NOMJOUEUR = '"+joueur.getNom()+
							"', PRENOMJOUEUR = '"+joueur.getPrenom()+"', PSEUDOJOUEUR = '"+joueur.getPseudo()+
							"', DATENAISSANCE = TO_DATE('"+joueur.getDateNaissance()+"','DD-MM-YYYY'), NATIONALITE = '"+
							joueur.getNationalite()+"' WHERE IDJOUEUR = "+joueur.getID());
					ControleurConnexion.listeJoueurs.remove(this.vue.getJoueurSelectionne());
					ControleurConnexion.listeJoueurs.put(joueur.getPrenomPseudoNom(), joueur);
					this.vue.modifierEquipe();
					
				}
					this.vue.creerJoueur();
					b.setForeground(Color.BLACK);
			}
			break;
		case SUPPRIMER:
			if ((this.vue.getJoueurSelectionne()!=null && this.vue.confirmerSuppression()==0)) {
				Connexion.getInstance().executerRequete("delete sae_joueur where prenomJoueur||' ('||pseudoJoueur||') '||nomJoueur='"
														+this.vue.getJoueurSelectionne()+"'");
				this.vue.supprimerJoueur();
			}
			this.vue.creerJoueur();
		default:
			
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch(this.etat) {
		case SUPPRIMER:
		break;
		default:
			@SuppressWarnings("unchecked")
			JList<String> list = (JList<String>) e.getSource();
			if (!(list.isSelectionEmpty())) {
				VueJoueur.afficherTexte(this.vue.titreModif, "Modifier un joueur");
				Joueur joueur = ControleurConnexion.listeJoueurs.get(this.vue.getJoueurSelectionne());
				this.vue.setNomJoueur(joueur.getNom());
				this.vue.setPrenomJoueur(joueur.getPrenom());
				this.vue.setPseudoJoueur(joueur.getPseudo());
				this.vue.setDateNaissanceJoueur(joueur.getDateNaissance());
				this.vue.setNationaliteJoueur(joueur.getNationalite());
			}
		}
		
	}

}