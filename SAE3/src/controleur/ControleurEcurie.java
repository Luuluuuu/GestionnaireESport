package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Ecurie;
import vue.VueCalendrier;
import vue.VueConnexion;
import vue.VueEcurie;

public class ControleurEcurie implements ActionListener, ListSelectionListener  {
	
	public enum Etat {
		CREER, MODIFIER, SUPPRIMER, DECONNECTER, CALENDRIER, JOUEURS, CLASSEMENT, RECHERCHER, VALIDER, ANNULER
	}
	
	private VueEcurie vue;
	private Etat etat;
	private Map<String, Ecurie> listeEcuries;
	
	public ControleurEcurie(VueEcurie vue) {
		this.vue = vue;
		this.initialiserListeEcuries();
	}

	public void initialiserListeEcuries() {
		this.listeEcuries = new HashMap<String,Ecurie>();
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

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case ANNULER:
		case CREER :
			this.vue.setNom("");
			this.vue.viderMotDePasse();
		break;
		case SUPPRIMER :
			if (!(this.vue.getNomEcurie().isEmpty()) && this.vue.confirmer("suppression")==0) {
				Connexion.getInstance().executerRequete("DELETE SAE_USER WHERE IDECURIE = "+this.listeEcuries.get(this.vue.getNomSelectionne()).getID());
				Connexion.getInstance().executerRequete("DELETE SAE_ECURIE WHERE NOMECURIE = '"+this.vue.getNomSelectionne()+"'");
				this.listeEcuries.remove(this.vue.getNomSelectionne());
				this.vue.supprimerEcurie();
				this.vue.viderMotDePasse();
			}
			break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueEcurie.fermerFenetre(this.vue.fenetreEcurie);
		break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueEcurie.fermerFenetre(this.vue.fenetreEcurie);
		break;
		case RECHERCHER:
			if (this.vue.recherche.getText()!="") {
				this.vue.filtrerRecherche();
			} else {
				this.vue.setDefaultListModel();
			}
		break;
		case VALIDER:
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
							this.vue.setNom("");
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
									this.vue.setNom("");
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
			break;
		default:
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.vue.setNomSelectionne();
	}
}
