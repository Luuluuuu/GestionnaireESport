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
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueJoueur;


public class ControleurERA implements ActionListener, ListSelectionListener {
	public enum Entite {ECURIE("Ecurie"),RESPONSABLE("Responsable"),ARBITRE("Arbitre") ;
		private String nom;
		Entite(String nom) {
			this.nom = nom;
		}
		public String getNom() {return this.nom;}
	};
	public enum Etat {
		CREER, MODIFIER, SUPPRIMER, DECONNECTER, CALENDRIER, JOUEURS, CLASSEMENT, 
		RECHERCHER, VALIDER, ANNULER, EQUIPES 
	}
 
	private VueERA vue;
	private Etat etat;
	public static Entite entite;
	
	public ControleurERA(VueERA vue) {
		this.vue = vue;
		this.etat = Etat.CREER;
		
		this.initialiserListeEcuries();
		this.initialiserListeResponsables();
		this.initialiserListeArbitres();
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
			VueERA.fermerFenetre(this.vue.fenetreERA);
		break;
		case JOUEURS :
			VueJoueur fenJOUEUR = new VueJoueur();
			fenJOUEUR.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.fenetreERA);
		break;
		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.fenetreERA);
		break;
		case ANNULER:
		case CREER :
			this.vue.setNom("","");
			this.vue.viderMotDePasse();
		break;
		case SUPPRIMER :
			if (!(this.vue.getNom().isEmpty()) && this.vue.confirmer("suppression")==0) {
				switch (entite) {
				case ECURIE:
					Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDECURIE = "+ControleurConnexion.listeEcuries.get(this.vue.getNomSelectionneEcurie()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_ECURIE WHERE NOMECURIE = '"+this.vue.getNomSelectionneEcurie()+"'");
					ControleurConnexion.listeEcuries.remove(this.vue.getNomSelectionneEcurie());
					break;
				case RESPONSABLE:
					Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDRESPONSABLE = "+ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_RESPONSABLE WHERE IDRESPONSABLE = '"+ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID()+"'");
					ControleurConnexion.listeResponsables.remove(this.vue.getNomSelectionneResponsable());
					break;
				case ARBITRE:
					Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDARBITRE = "+ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_ARBITRE WHERE IDARBITRE = '"+ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID()+"'");
					ControleurConnexion.listeArbitres.remove(this.vue.getNomSelectionneArbitre());
					break;
					
				}
				this.vue.setNom("","");
				this.vue.supprimerEntite();
				this.vue.viderMotDePasse();
			}
			break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.fenetreERA);
		break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueERA.fermerFenetre(this.vue.fenetreERA);
		break;
		case RECHERCHER:
			if (this.vue.getRecherche()!="") {
				this.vue.filtrerRecherche();
				this.vue.setNom("","");
			} else {
				this.vue.setDefaultListModel();
			}
		break;
		case VALIDER:
			switch (ControleurERA.entite) {
			case ECURIE:
				if (!(this.vue.getNomEcurie().isEmpty())) {
					if (this.vue.estSelectionneEcurie()) {
						this.modifierEcurie();
					}else{
						this.creerEcurie();
					}
				} else {this.vue.estVide();}
				break;
			case RESPONSABLE:
				if (!(this.vue.getPrenomResponsable().isEmpty() && this.vue.getNomResponsable().isEmpty())) {
					if (this.vue.estSelectionneResponsable()) {
						this.modifierResponsable();
					}else{
						this.creerResponsable();
					}
				} else {this.vue.estVide();}
				break;
			case ARBITRE:
				if (!(this.vue.getPrenomArbitre().isEmpty() && this.vue.getNomArbitre().isEmpty())) {
					if (this.vue.estSelectionneArbitre()) {
						this.modifierArbitre();
					}else{
						this.creerArbitre();
					}
				} else {this.vue.estVide();}
				break;
			default:
			}
			break;
		default:
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch (this.etat) {
		case SUPPRIMER:
			this.etat = Etat.CREER;
			this.vue.setNom("","");
			this.vue.viderMotDePasse();
			break;
		default:
			JList<String> liste = (JList<String>) e.getSource();
			this.vue.setEntite(liste);
			if (!(liste.isSelectionEmpty())) {
				switch (ControleurERA.entite) {
				case ECURIE:
					this.vue.setNomSelectionneEcurie();
					break;
				case RESPONSABLE:
					Responsable r = ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable());
					this.vue.setNomResponsable(r.getNom(),r.getPrenom());
					break;
				case ARBITRE:
					Arbitre a = ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre());
					this.vue.setNomArbitre(a.getNom(),a.getPrenom());
					break;
				default:
					break;
				}
			}
		}
	}

	public void modifierEcurie() {
		if (this.vue.confirmer("modification")==0) {
			Connexion.getInstance().executerRequete("UPDATE SAE_ECURIE SET NOMECURIE = '"+this.vue.getNomEcurie()+"' WHERE IDECURIE ="+ControleurConnexion.listeEcuries.get(this.vue.getNomSelectionne()).getID());
			if (!(this.vue.getMotDePasseEcurie().isEmpty())) {
				Connexion.getInstance().executerRequete("UPDATE SAE_USER SET LOGIN = '"+this.vue.getMotDePasseEcurie()+"'MOTDEPASSE='"+this.vue.getMotDePasseEcurie().hashCode()+
						"' WHERE IDECURIE = "+ControleurConnexion.listeEcuries.get(this.vue.getNomSelectionne()).getID());
			}
			Ecurie ecurie = ControleurConnexion.listeEcuries.get(this.vue.getNomSelectionne());
			ControleurConnexion.listeEcuries.remove(ecurie.getNom());
			ecurie.setNom(this.vue.getNomEcurie());
			ControleurConnexion.listeEcuries.put(ecurie.getNom(), ecurie);
			this.vue.modifierEcurie();
			this.vue.setNomEcurie("");
			this.vue.viderMotDePasse();
		}
	}
	
	public void creerEcurie() {
		if (!(this.vue.getMotDePasseEcurie().isEmpty())) {
			if (!(ControleurConnexion.listeEcuries.containsKey(this.vue.getNomEcurie()))) {
				try {
					ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_ecurieid.NEXTVAL FROM dual");
					Ecurie ecurie = null;
					if (rs.next()) {
						ecurie = new Ecurie(rs.getInt(1),this.vue.getNomEcurie());
						ControleurConnexion.listeEcuries.put(ecurie.getNom(), ecurie);
						this.vue.ajouterEcurie(ecurie.getNom());
						rs.close();
						Connexion.getInstance().executerRequete("INSERT INTO sae_ecurie VALUES(SEQ_ECURIEID.CURRVAL,'"+ecurie.getNom()+"', "+Year.now().getValue()+")");
					}
					ecurie.creerLogin(this.vue.getMotDePasseEcurie());
					ControleurConnexion.listeEcuries.put(ecurie.getNom(), ecurie);
					this.vue.setNomEcurie("");
					this.vue.viderMotDePasse();
				} catch (SQLException e1) {e1.printStackTrace();}
			} else {this.vue.existe();}
		} else {
			this.vue.estVide();
		}
	}
	
	private void creerResponsable() {
		if (!(this.vue.getMotDePasseResponsable().isEmpty())) {
			if (!(ControleurConnexion.listeResponsables.containsKey(this.vue.getPrenomResponsable()+" "+this.vue.getNomResponsable()))) {
				try {
					ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_responsableid.NEXTVAL FROM dual");
					Responsable r = null;
					if (rs.next()) {
						r = new Responsable(rs.getInt(1),this.vue.getNomResponsable(),this.vue.getPrenomResponsable());
						ControleurConnexion.listeResponsables.put(r.getPrenomNom(), r);
						this.vue.ajouterResponsable(r.getPrenomNom());
						rs.close();
						Connexion.getInstance().executerRequete("INSERT INTO sae_responsable VALUES(seq_responsableid.CURRVAL,'"+r.getNom()+"', '"+r.getPrenom()+"', 0)");
					}
					r.creerLogin(this.vue.getMotDePasseResponsable());
					ControleurConnexion.listeResponsables.put(r.getPrenomNom(), r);
					this.vue.setNomResponsable("","");
					this.vue.viderMotDePasse();
				} catch (SQLException e1) {e1.printStackTrace();}
			} else {this.vue.existe();}
		} else {
			this.vue.estVide();
		}
	}

	private void modifierResponsable() {
		if (this.vue.confirmer("modification")==0) {
			Connexion.getInstance().executerRequete("UPDATE SAE_RESPONSABLE SET NOMRESPONSABLE = '"+this.vue.getNomResponsable()+"', PRENOMRESPONSABLE = '"+this.vue.getPrenomResponsable()+"' WHERE IDRESPONSABLE ="+ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID());
			if (!(this.vue.getMotDePasseResponsable().isEmpty())) {
				Connexion.getInstance().executerRequete("UPDATE SAE_USER SET LOGIN='"+this.vue.getNomResponsable()+"."+this.vue.getPrenomResponsable()+
						"' MOTDEPASSE='"+this.vue.getMotDePasseResponsable().hashCode()+"' WHERE IDRESPONSABLE = "+ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID());
			} else {
				Connexion.getInstance().executerRequete("UPDATE SAE_USER SET LOGIN='"+this.vue.getNomResponsable()+"."+this.vue.getPrenomResponsable()+
						"' WHERE IDRESPONSABLE = "+ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID());
			}
			Responsable r = ControleurConnexion.listeResponsables.get(this.vue.getNomSelectionneResponsable());
			ControleurConnexion.listeResponsables.remove(r.getPrenomNom());
			r.setNom(this.vue.getNomResponsable());
			r.setPrenom(this.vue.getPrenomResponsable());
			ControleurConnexion.listeResponsables.put(r.getPrenomNom(), r);
			this.vue.modifierResponsable();
			this.vue.setNomResponsable("","");
			this.vue.viderMotDePasse();
		}
	}
	
	private void creerArbitre() {
		if (!(this.vue.getMotDePasseArbitre().isEmpty())) {
			if (!(ControleurConnexion.listeArbitres.containsKey(this.vue.getPrenomArbitre()+" "+this.vue.getNomArbitre()))) {
				try {
					ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_arbitreid.NEXTVAL FROM dual");
					Arbitre a = null;
					if (rs.next()) {
						a = new Arbitre(rs.getInt(1),this.vue.getNomArbitre(),this.vue.getPrenomArbitre());
						a.setPseudo(a.getPrenom());
						ControleurConnexion.listeArbitres.put(a.getPrenomNom(), a);
						this.vue.ajouterArbitre(a.getPrenomNom());
						rs.close();
						Connexion.getInstance().executerRequete("INSERT INTO sae_arbitre VALUES('"+a.getNom()+"', '"+a.getPrenom()+"', '"+a.getPseudo()+"', 0,seq_arbitreid.CURRVAL)");
					}
					a.creerLogin(this.vue.getMotDePasseArbitre());
					ControleurConnexion.listeArbitres.put(a.getPrenomNom(), a);
					this.vue.setNomArbitre("","");
					this.vue.viderMotDePasse();
				} catch (SQLException e1) {e1.printStackTrace();}
			} else {this.vue.existe();}
		} else {
			this.vue.estVide();
		}
	}
	
	private void modifierArbitre() {
		if (this.vue.confirmer("modification")==0) {
			Connexion.getInstance().executerRequete("UPDATE SAE_ARBITRE SET NOMARBITRE = '"+this.vue.getNomArbitre()+
					"', PRENOMARBITRE = '"+this.vue.getPrenomArbitre()+
					"' WHERE IDARBITRE ="+ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID());
			if (!(this.vue.getMotDePasseArbitre().isEmpty())) {
				Connexion.getInstance().executerRequete("UPDATE SAE_USER SET LOGIN='"+this.vue.getNomArbitre()+"."+this.vue.getPrenomArbitre()
						+ "' MOTDEPASSE='"+this.vue.getMotDePasseArbitre().hashCode()+
						"' WHERE IDARBITRE = "+ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID());
			} else {
				Connexion.getInstance().executerRequete("UPDATE SAE_USER SET LOGIN='"+this.vue.getNomArbitre()+"."+this.vue.getPrenomArbitre()+
				"' WHERE IDARBITRE = "+ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID());
			}
			Arbitre a = ControleurConnexion.listeArbitres.get(this.vue.getNomSelectionneArbitre());
			ControleurConnexion.listeResponsables.remove(a.getPrenomNom());
			a.setNom(this.vue.getNomArbitre());
			a.setPrenom(this.vue.getPrenomArbitre());
			ControleurConnexion.listeArbitres.put(a.getPrenomNom(), a);
			this.vue.modifierArbitre();
			this.vue.setNomArbitre("","");
			this.vue.viderMotDePasse();
		}
	}
}
