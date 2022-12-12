package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Arbitre;
import modele.Connexion;
import modele.Ecurie;
import modele.Responsable;
import vue.VueCalendrier;
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
	static Map<String, Ecurie> listeEcuries;
	
	public ControleurERA(VueERA vue) {
		this.vue = vue;
		
		this.initialiserListeEcuries();
		this.initialiserListeResponsables();
		this.initialiserListeArbitres();
	}
	
	public void initialiserListeEcuries() {
		ControleurERA.listeEcuries = new HashMap<String,Ecurie>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT * FROM SAE_ECURIE");
			while (rs.next()) {
				Ecurie e = new Ecurie(rs.getInt(1),rs.getString(2));
				e.setAnneeDeCreation(rs.getInt(3));
				listeEcuries.put(e.getNom(),e);
				this.vue.ajouterEcurie(e.getNom());
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeResponsables() {
		for (String nomResponsable : ControleurCalendrier.listeResponsables.keySet()) {
			this.vue.ajouterResponsable(nomResponsable);
		}
	}
	
	public void initialiserListeArbitres() {
		for (String nomArbitres : ControleurCalendrier.listeArbitres.keySet()) {
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
		
		case ANNULER:
		case CREER :
			this.vue.setNom("");
			this.vue.viderMotDePasse();
		break;
		case SUPPRIMER :
			if (!(this.vue.getNom().isEmpty()) && this.vue.confirmer("suppression")==0) {
				switch (entite) {
				case ECURIE:
					Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDECURIE = "+ControleurERA.listeEcuries.get(this.vue.getNomSelectionneEcurie()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_ECURIE WHERE NOMECURIE = '"+this.vue.getNomSelectionneEcurie()+"'");
					ControleurERA.listeEcuries.remove(this.vue.getNomSelectionneEcurie());
					break;
				case RESPONSABLE:
					Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDRESPONSABLE = "+ControleurCalendrier.listeResponsables.get(this.vue.getNomSelectionneResponsable()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_RESPONSABLE WHERE NOMRESPONSABLE || ' ' || PRENOMRESPONSABLE = '"+this.vue.getNomSelectionneResponsable()+"'");
					ControleurCalendrier.listeResponsables.remove(this.vue.getNomSelectionneResponsable());
					break;
				case ARBITRE:
					Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDARBITRE = "+ControleurCalendrier.listeArbitres.get(this.vue.getNomSelectionneArbitre()).getID());
					Connexion.getInstance().executerRequete("DELETE SAE_ARBITRE WHERE NOMARBITRE || ' ' || PRENOMARBITRE = '"+this.vue.getNomSelectionneArbitre()+"'");
					ControleurCalendrier.listeArbitres.remove(this.vue.getNomSelectionneArbitre());
					break;
					
				}
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
			} else {
				this.vue.setDefaultListModel();
			}
		break;
		/*case VALIDER:
			if (!(this.vue.getNomEcurie().isEmpty())) {
					if (this.vue.estSelectionne()) {
						if (this.vue.confirmer("modification")==0) {
							Connexion.getInstance().executerRequete("UPDATE SAE_ECURIE SET NOMECURIE = '"+this.vue.getNomEcurie()+"' WHERE IDECURIE ="+this.listeEcuries.get(this.vue.getNomSelectionne()).getID());
							if (!(this.vue.getMotDePasseEcurie().isEmpty())) {
								Connexion.getInstance().executerRequete("UPDATE SAE_USER SET MOTDEPASSE='"+this.vue.getMotDePasseEcurie().hashCode()+"' WHERE IDECURIE = "+this.listeEcuries.get(this.vue.getNomSelectionne()).getID());
							}
							Ecurie ecurie = this.listeEcuries.get(this.vue.getNomSelectionne());
							this.listeEcuries.remove(ecurie.getNom());
							ecurie.setNom(this.vue.getNomEcurie());
							this.listeEcuries.put(ecurie.getNom(), ecurie);
							this.vue.modifierEcurie();
							this.vue.setNomEcurie("");
							this.vue.viderMotDePasse();
						}
					}else{
						if (!(this.vue.getMotDePasseEcurie().isEmpty())) {
							if (!(this.listeEcuries.containsKey(this.vue.getNomEcurie()))) {
								try {
									ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_ecurieid.NEXTVAL FROM dual");
									Ecurie ecurie = null;
									if (rs.next()) {
										ecurie = new Ecurie(rs.getInt(1),this.vue.getNomEcurie());
										this.listeEcuries.put(ecurie.getNom(), ecurie);
										this.vue.ajouterEcurie(ecurie.getNom());
										rs.close();
										Connexion.getInstance().executerRequete("INSERT INTO sae_ecurie VALUES(SEQ_ECURIEID.CURRVAL,'"+ecurie.getNom()+"', "+Year.now().getValue()+")");
									}
									ecurie.creerLogin(this.vue.getMotDePasseEcurie());
									this.vue.setNomEcurie("");
									this.vue.viderMotDePasse();
								} catch (SQLException e1) {e1.printStackTrace();}
							} else {this.vue.tournoiExiste();}
						} else {
							this.vue.estVide();
						}
					}
			} else {
				this.vue.estVide();
			}
			break;*/
		default:
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.vue.setEntite((JList<String>) e.getSource());
		this.vue.setNomSelectionne();
	}

}
