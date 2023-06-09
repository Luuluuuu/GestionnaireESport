package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Arbitre;
import modele.Connexion;
import modele.Ecurie;
import modele.Responsable;
import vue.Vue;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueJoueur;


public class ControleurERA implements ActionListener, ListSelectionListener, Vue {
	private static final String MODIFICATION = "modification";
	
	public enum Entite {ECURIE("Ecurie"),RESPONSABLE("Responsable"),ARBITRE("Arbitre") ;
		private String nom;
		Entite(String nom) {
			this.nom = nom;
		}
		public String getNom() {return this.nom;}
	}
	
	public enum Etat {
		CREER, SUPPRIMER, DECONNECTER, CALENDRIER, JOUEURS, CLASSEMENT, 
		RECHERCHER, VALIDER, ANNULER, EQUIPES 
	}
 
	private VueERA vue;
	private Etat etat;
	private Entite entite;
	
	public ControleurERA(VueERA vue) {
		this.vue = vue;
		this.etat = Etat.CREER;
		
		this.initialiserListeEcuries();
		this.initialiserListeResponsables();
		this.initialiserListeArbitres();
	}
	
	public Entite getEntite() {
		return this.entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}
	
	public void initialiserListeEcuries() {
		for (String nomEcurie : ControleurConnexion.listeEcuries.keySet()) {
			this.vue.ajouterEcurie(nomEcurie);
		}
	}
	
	public void initialiserListeResponsables() {
		for (String nomResponsable : ControleurConnexion.listeResponsables.keySet()) {
			this.vue.ajouterResponsable(nomResponsable);
		}
	}
	
	public void initialiserListeArbitres() {
		for (String nomArbitres : ControleurConnexion.listeArbitres.keySet()) {
			this.vue.ajouterArbitre(nomArbitres);
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
			VueERA.fermerFenetre(this.vue.getFrame());
			break;
		case JOUEURS :
			VueJoueur fenJOUEUR = new VueJoueur();
			fenJOUEUR.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.getFrame());
			break;
		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.getFrame());
			break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.getFrame());
			break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.getFrame());
			break;
		case ANNULER:
		case CREER :
			this.vue.setNom("","");
			this.vue.viderMotDePasse();
			switch(this.entite) {
			case ECURIE:
				this.vue.setCouleurSaisiesEcurieANoir();
				break;
			case RESPONSABLE:
				this.vue.setCouleurSaisiesResponsableANoir();
				break;
			case ARBITRE:
				this.vue.setCouleurSaisiesArbitreANoir();
				break;
			}
			break;
		case SUPPRIMER :
			if (!(this.vue.getNom().isEmpty()) && this.vue.confirmer("suppression") == 0) {
				this.supprimerEntite(); // Appelle les requêtes de DELETE
				this.vue.supprimerEntite();
				
				// Remets la vue sur 
				this.vue.setNom("","");
				this.vue.viderMotDePasse();
			}
			break;
		case RECHERCHER:
			if (!this.vue.getRecherche().equals("")) {
				this.vue.filtrerRecherche();
				this.vue.setNom("","");
				
			} else {
				this.vue.setDefaultListModel();
				
			}
			break;
		case VALIDER:
			this.creerModifierEntite();
			break;
		default:
			break;
		}
		Vue.desactiverBouton(this.vue.getBtnSupprimerEcurie());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {		
		if (this.etat == Etat.SUPPRIMER) {
			this.etat = Etat.CREER;
			this.vue.setNom("","");
			this.vue.viderMotDePasse();
			
		} else {
			JList<String> liste = (JList<String>) e.getSource();
			this.vue.setEntite(liste);
			if (!(liste.isSelectionEmpty())) {
				switch (this.entite) {
				case ECURIE:
					this.vue.setNomSelectionneEcurie();
					this.vue.setCouleurSaisiesEcurieANoir();
					break;
				case RESPONSABLE:
					Responsable r = ControleurConnexion.listeResponsables.get(
						this.vue.getNomSelectionneResponsable()
					);
					this.vue.setNomResponsable(r.getNom(),r.getPrenom());
					this.vue.setCouleurSaisiesResponsableANoir();
					break;
				case ARBITRE:
					Arbitre a = ControleurConnexion.listeArbitres.get(
						this.vue.getNomSelectionneArbitre()
					);
					this.vue.setNomArbitre(a.getNom(),a.getPrenom());
					this.vue.setCouleurSaisiesArbitreANoir();
					break;
				default:
					break;
				}
				
			}
		}
		Vue.activerBouton(this.vue.getBtnSupprimerEcurie());
	}
	
	private void creerModifierEntite() {
		switch (this.entite) {
		case ECURIE:
			if (this.vue.estFormulaireRempliEcurie()) {
				if (this.vue.estSelectionneEcurie()) {
					this.modifierEcurie();
					
				}else{
					this.creerEcurie();
					
				}
			}
			break;
		case RESPONSABLE:
			if (this.vue.estFormulaireRempliResponsable()) {
				if (this.vue.estSelectionneResponsable()) {
					this.modifierResponsable();
					
				}else{
					this.creerResponsable();
					
				}
			}
			break;
		case ARBITRE:
			if (this.vue.estFormulaireRempliArbitre()) {
				if (this.vue.estSelectionneArbitre()) {
					this.modifierArbitre();
					
				}else{
					this.creerArbitre();
					
				}
			}
			break;
		default:
			break;
		}
	}
	
	// Crée une écurie en locale et sur la BDD
	private void creerEcurie() {
		if (!(ControleurConnexion.listeEcuries.containsKey(this.vue.getNomEcurie()))) {
			try {
				ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_ecurieid.NEXTVAL FROM dual");
				Ecurie ecurie = null;
				if (rs.next()) {
					// Récupère l'écurie créée
					ecurie = new Ecurie(rs.getInt(1),this.vue.getNomEcurie());
											
					Connexion.getInstance().executerRequete("INSERT INTO sae_ecurie "
							+ "VALUES(SEQ_ECURIEID.CURRVAL,'"+ecurie.getNom()+"', "+Year.now().getValue()+")");

					// Ajoute l'écurie localement
					ControleurConnexion.listeEcuries.put(ecurie.getNom(), ecurie);
					ecurie.creerLogin(this.vue.getMotDePasseEcurie());
					this.vue.ajouterEcurie(ecurie.getNom());
					
				}
				rs.close();
				
				// Vide les champs de saisie
				this.vue.setNomEcurie("");
				this.vue.viderMotDePasse();
				
			} catch (SQLException e1) {e1.printStackTrace();}
			
		} else {this.vue.existe();}
	}
	
	// Modifie une écurie en locale et sur la BDD
	private void modifierEcurie() {
		if (this.vue.confirmer(MODIFICATION)==0) {
			// Récupère l'ID de l'écurie sélectionné
			int idEcurie =  ControleurConnexion.listeEcuries.get(this.vue.getNomSelectionne()).getID();
			Connexion.getInstance().executerRequete("UPDATE SAE_ECURIE SET "
					+ "NOMECURIE = '" + this.vue.getNomEcurie()
					+ "' WHERE IDECURIE =" + idEcurie);
			
			String requete = "UPDATE SAE_USER SET LOGIN = '" + this.vue.getNom();
			if (!(this.vue.getMotDePasseEcurie().isEmpty())) { // Si la saisie du mot est remplie, on modifie aussi le mot de passe
				requete += "', MOTDEPASSE='" + this.vue.getMotDePasseEcurie().hashCode();
				
			}
			Connexion.getInstance().executerRequete(requete + "' WHERE IDECURIE = " + idEcurie);
			
			// Supprime l'ancienne écurie de la liste
			Ecurie ecurie = ControleurConnexion.listeEcuries.get(this.vue.getNomSelectionne());
			ControleurConnexion.listeEcuries.remove(ecurie.getNom());
			
			// Ajoute la nouvelle écurie à la liste
			ecurie.setNom(this.vue.getNomEcurie());
			ControleurConnexion.listeEcuries.put(ecurie.getNom(), ecurie);
			this.vue.modifierEcurie();
			
			// Vide les champs de saisie
			this.vue.setNomEcurie("");
			this.vue.viderMotDePasse();
		}
	}
	
	// Crée un responsable en locale et sur la BDD
	private void creerResponsable() {
		if (!(ControleurConnexion.listeResponsables.containsKey(
				this.vue.getPrenomResponsable() +
				" " + this.vue.getNomResponsable()))) {
			try {
				ResultSet rs = Connexion.getInstance().retournerRequete(
						"SELECT seq_responsableid.NEXTVAL FROM dual");
				Responsable responsable = null;
				if (rs.next()) {
					// Récupère le responsable créé
					responsable = new Responsable(rs.getInt(1),this.vue.getNomResponsable(),this.vue.getPrenomResponsable());
											
					Connexion.getInstance().executerRequete("INSERT INTO sae_responsable "
							+ "VALUES(seq_responsableid.CURRVAL,'"+responsable.getNom() + 
							"', '"+responsable.getPrenom() + "', 0)");

					// Ajoute le responsable localement
					ControleurConnexion.listeResponsables.put(responsable.getPrenomNom(), responsable);
					this.vue.ajouterResponsable(responsable.getPrenomNom());
					responsable.creerLogin(this.vue.getMotDePasseResponsable());
					
				}
				rs.close();
				
				// Vide les champs de saisie
				this.vue.setNomResponsable("","");
				this.vue.viderMotDePasse();
				
			} catch (SQLException e1) {e1.printStackTrace();}
			
		} else {this.vue.existe();}
	}

	// Modifie un responsable en locale et sur la BDD
	private void modifierResponsable() {
		if (this.vue.confirmer(MODIFICATION)==0) {
			// Récupère l'ID du responsable sélectionné
			int idResponsable = ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID();
			Connexion.getInstance().executerRequete("UPDATE SAE_RESPONSABLE SET "
					+ "NOMRESPONSABLE = '" + this.vue.getNomResponsable() 
					+ "', PRENOMRESPONSABLE = '" + this.vue.getPrenomResponsable() 
					+ "' WHERE IDRESPONSABLE =" + idResponsable);

			// Crée le login
			String login = this.vue.getNomResponsable() + "." + this.vue.getPrenomResponsable();
			String requete = "UPDATE SAE_USER SET LOGIN = '" + login;
			// Avec mdp
			if (!(this.vue.getMotDePasseResponsable().isEmpty())) {
				requete += "', MOTDEPASSE='" + this.vue.getMotDePasseResponsable().hashCode();
			
			}
			Connexion.getInstance().executerRequete(requete + "' WHERE IDRESPONSABLE = " + idResponsable);
			// Supprime le responsable de la liste locale
			Responsable r = ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable());
			ControleurConnexion.listeResponsables.remove(r.getPrenomNom());
			
			// Ajoute le responsable à la liste locale
			r.setNom(this.vue.getNomResponsable());
			r.setPrenom(this.vue.getPrenomResponsable());
			ControleurConnexion.listeResponsables.put(r.getPrenomNom(), r);
			this.vue.modifierResponsable();
			
			// Vide les champs de saisie
			this.vue.setNomResponsable("","");
			this.vue.viderMotDePasse();
		}
	}
	
	// Crée un arbitre en locale et sur la BDD
	private void creerArbitre() {
		if (!(ControleurConnexion.listeArbitres.containsKey(this.vue.getPrenomArbitre() +
				" " + this.vue.getNomArbitre()))) {
			try {
				ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_arbitreid.NEXTVAL FROM dual");
				Arbitre arbitre = null;
				if (rs.next()) {
					// Récupère l'arbitre créé
					arbitre = new Arbitre(rs.getInt(1),this.vue.getNomArbitre(),this.vue.getPrenomArbitre());
					arbitre.setPseudo(arbitre.getPrenom());

					Connexion.getInstance().executerRequete("INSERT INTO sae_arbitre "
							+ "VALUES('"+arbitre.getNom() + "', '"+arbitre.getPrenom()
							+ "', '"+arbitre.getPseudo() + "', 0,seq_arbitreid.CURRVAL)");
					
					// Ajoute l'arbitre localement
					ControleurConnexion.listeArbitres.put(arbitre.getPrenomNom(), arbitre);
					this.vue.ajouterArbitre(arbitre.getPrenomNom());
					arbitre.creerLogin(this.vue.getMotDePasseArbitre());
					
				}
				rs.close();
				
				// Vide les champs de saisie
				this.vue.setNomArbitre("","");
				this.vue.viderMotDePasse();
				
			} catch (SQLException e1) {e1.printStackTrace();}
			
		} else {this.vue.existe();}
	}
	
	// Modifie un arbitre en locale et sur la BDD
	private void modifierArbitre() {
		if (this.vue.confirmer(MODIFICATION)==0) {
			// Récupère l'id de l'arbitre sélectionné
			int idArbitre = ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID();
			
			Connexion.getInstance().executerRequete("UPDATE SAE_ARBITRE SET "
					+ "NOMARBITRE = '" + this.vue.getNomArbitre() +
					"', PRENOMARBITRE = '" + this.vue.getPrenomArbitre() +
					"' WHERE IDARBITRE =" + idArbitre);
			
			// Créer un login
			String login = this.vue.getNomArbitre() + "." + this.vue.getPrenomArbitre();
			String requete = "UPDATE SAE_USER SET LOGIN='" + login; // Construction de la requête
			// Modifie avec le mot de passe
			if (!(this.vue.getMotDePasseArbitre().isEmpty())) {
				requete += "', MOTDEPASSE='" + this.vue.getMotDePasseArbitre().hashCode();
			
			// Modifie sans le mot de passe
			}
			Connexion.getInstance().executerRequete(requete	+ "' WHERE IDARBITRE = " + idArbitre);
				
			// Supprime l'ancien arbitre
			Arbitre a = ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre());
			ControleurConnexion.listeResponsables.remove(a.getPrenomNom());
			
			// Ajoute l'ancien 
			a.setNom(this.vue.getNomArbitre());
			a.setPrenom(this.vue.getPrenomArbitre());
			ControleurConnexion.listeArbitres.put(a.getPrenomNom(), a);
			this.vue.modifierArbitre();
			
			// Vide les champs de saisie
			this.vue.setNomArbitre("","");
			this.vue.viderMotDePasse();
			
		}
	}

	private void supprimerEntite() {
		switch (entite) {
		case ECURIE:
			Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDECURIE = " 
					+ ControleurConnexion.listeEcuries.get(this.vue.getNomSelectionneEcurie()).getID());
			Connexion.getInstance().executerRequete("DELETE SAE_ECURIE WHERE NOMECURIE = '" +
					this.vue.getNomSelectionneEcurie() + "'");
			
			ControleurConnexion.listeEcuries.remove(this.vue.getNomSelectionneEcurie());
		break;
		
		case RESPONSABLE:
			Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDRESPONSABLE = " 
					+ ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID());
			Connexion.getInstance().executerRequete("DELETE SAE_RESPONSABLE WHERE IDRESPONSABLE = '"
					+ ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID() + "'");
			
			ControleurConnexion.listeResponsables.remove(this.vue.getNomSelectionneResponsable());
			break;
			
		case ARBITRE:
			Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDARBITRE = " 
					+ ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID());
			Connexion.getInstance().executerRequete("DELETE SAE_ARBITRE WHERE IDARBITRE = '" 
					+ ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID()+"'");
			
			ControleurConnexion.listeArbitres.remove(this.vue.getNomSelectionneArbitre());
			break;
			
		default:
			break;
			
		}
	}
	
}
