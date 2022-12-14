package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Equipe;
import vue.VueCalendrier;
import vue.VueERA;
import vue.VueEquipe;

public class ControleurEquipe implements ActionListener, ListSelectionListener {
	
	public enum Etat{RECHERCHER,VALIDER,ANNULER,CREER,SUPPRIMER,DECONNECTER,ECURIE,CALENDRIER,JOUEURS}
	private VueEquipe vue;
	private Etat etat;
	
	public ControleurEquipe(VueEquipe vue) {
		this.vue = vue;
		this.initialiserListes();
		this.etat = Etat.RECHERCHER;
	}
	
	// Initialiser les listes
	public void initialiserListes() {
		this.initialiserListeEquipes();
		this.initialiserListeEcuries();
		this.initialiserListeJeux();
	}
	
	public void initialiserListeEquipes() {
		/*this.listeEquipes = new HashMap<String,Equipe>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("select * from sae_equipe ");
			while (rs.next()) {
				Equipe e = new Equipe(rs.getString(2), rs.getInt(4), ControleurConnexion.listeJeuxID.get(rs.getInt(7)));
				e.ajouterJoueur(null);
				this.listeEquipes.put(e.getNom(),e);
				this.vue.ajouterEquipe(e.getNom()); 
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		for (String nomEquipe : ControleurConnexion.listeEquipes.keySet()) {
			this.vue.ajouterEquipe(nomEquipe);
		}
	}
	
	public void initialiserListeEcuries() {
		for (String nomEcurie : ControleurConnexion.listeEcuries.keySet()) {
			this.vue.ajouterEcurie(nomEcurie);
		}
	}
	
	public void initialiserListeJeux() {
		for (String nomJeu : ControleurConnexion.listeJeux.keySet()) {
			this.vue.ajouterJeu(nomJeu);
		}
	}
	
	public String getNomJeuByName(String nj) {
		String result;
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("select sae_jeu.nomjeu, sae_equipe.nomequipe  from sae_jeu, sae_equipe where sae_jeu.idjeu = sae_equipe.idjeu");
		try {
			while (rs.next()) {
				if(rs.getString(2).equals(nj)) {
					return result = rs.getString(1);
				}
				
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getNomEcurieByName(String ec) {
		String result;
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("select sae_ecurie.nomecurie, sae_equipe.nomequipe  from sae_ecurie, sae_equipe where sae_ecurie.idecurie = sae_equipe.idecurie");
		try {
			while (rs.next()) {
				if(rs.getString(2).equals(ec)) {
					return result = rs.getString(1);
				}
				
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case ECURIE :
			VueERA fenERA = new VueERA();
			fenERA.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueEquipe.fermerFenetre(this.vue.fenetreEquipe);
		case RECHERCHER:
			String[] tabRecherche = {""};
			if(ControleurConnexion.listeEquipes.containsKey(this.vue.getTextRecherche().toUpperCase())
					|| ControleurConnexion.listeEquipes.containsKey(this.vue.getTextRecherche().toLowerCase())) {
				tabRecherche[0] = this.vue.getTextRecherche().toUpperCase(); 
				this.vue.filtrageListeEquipe(tabRecherche);
			}
			if(this.vue.getTextRecherche()=="") {
				//trouver un moyen de remettre la liste de base
			}
			break;
		case VALIDER:
			//Vérifie que tout les champs sont remplie
			if(this.vue.getNom().equals("")
					|| this.vue.getEcurie().equals("- Sélectionnez une écurie -")
					|| this.vue.getJeu().equals("- Sélectionnez un jeu -")) {
				this.vue.setEcurie("- Sélectionnez une écurie -");
				this.vue.setNomEquipe("");
				this.vue.setJeu("- Sélectionnez un jeu -");
			} else {
				//valeur test drx
				Connexion.getInstance().executerRequete("INSERT INTO sae_equipe (idEquipe, nomequipe, anneeDeCreation,nombrePoints,nombreJoueurs,nationalité,idjeu,idecurie) VALUES (1,'DRX','2021',12,2,'France',1,18)");
				this.vue.ajouterEquipe(this.vue.getNom());
			}
			break;
		case SUPPRIMER:
			//gerer le faite que la deuxieme requete se lance avant que la premire
			//soit terminé
			if ((this.vue.getEquipeSelectionne()!=null && this.vue.confirmerSuppression()==0)) {
				Connexion c = Connexion.getInstance();
				ResultSet rs = c.retournerRequete("select sae_joueur.nomjoueur from sae_joueur,sae_equipe where sae_joueur.idequipe=sae_equipe.idequipe and sae_equipe.nomequipe='"+this.vue.getEquipeSelectionne()+"'");
				try {
					while (rs.next()) {
						Connexion.getInstance().executerRequete("delete sae_joueur where nomjoueur='"+rs.getString(1)+"'	");
					}
				} catch (SQLException z) {
					z.printStackTrace();
				}
				
				Connexion h = Connexion.getInstance();
				ResultSet rs1 = h.retournerRequete("SELECT * FROM SAE_EQUIPE WHERE nomequipe='"+this.vue.getEquipeSelectionne()+"'");

				try {
					while (rs1.next()) {
						Connexion.getInstance().executerRequete("DELETE SAE_EQUIPE WHERE IDEQUIPE ="+rs1.getInt(1));
					}
				} catch (SQLException z) {	
					z.printStackTrace();
				}
			
				this.vue.supprimerEquipe(this.vue.getEquipeSelectionne());
				//Connexion.getInstance().executerRequete("DELETE SAE_TOURNOI WHERE IDTOURNOI = "+t.getID());
				//this.vue.creerTournoi();
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
			if (!(list.isSelectionEmpty())) {
				Equipe ecurie = ControleurConnexion.listeEquipes.get(this.vue.getEquipeSelectionne());
				this.vue.setNomEquipe(ecurie.getNom());
				this.vue.setJeu(ecurie.getNomJeu());
				this.vue.setEcurie(this.getNomEcurieByName(this.vue.getEquipeSelectionne()));
			}
		}
	}

}
