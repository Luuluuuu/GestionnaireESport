package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.ControleurJoueur.Etat;
import modele.Arbitre;
import modele.Connexion;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Tournoi;
import vue.VueCalendrier;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueJoueur;

public class ControleurEquipe implements ActionListener, ListSelectionListener {
	
	public enum Etat{RECHERCHER,VALIDER,ANNULER,CREER,SUPPRIMER,DECONNECTER,ECURIE,CALENDRIER,JOUEURS}
	private VueEquipe vue;
	private Etat etat;
	private	Map<String, Equipe> listeEquipes;
	//private Map<Integer, Arbitre> listeEquipesID;
	private Map<String, Ecurie> listeEcuries;
	private Map<String, Jeu> listeJeux;
	private List<Joueur> listeJoueurs = new ArrayList<Joueur>();
	
	public ControleurEquipe(VueEquipe vue) {
		this.vue = vue;
		this.initialiserListes();
		this.listeJoueurs.add(new Joueur("aa", "bb", "aa", "2000", "fr"));
		this.etat = Etat.RECHERCHER;
	}
	
	// Initialiser les listes
	public void initialiserListes() {
		this.initialiserListeEquipes();
		this.initialiserListeEcuries();
		this.initialiserListeJeux();
	}
	
	public void initialiserListeEquipes() {
		this.listeEquipes = new HashMap<String,Equipe>();
		try {
			Connexion c = Connexion.getInstance();
			//requette sql qui recupère le nom du jeu via l'id pour crée l'objet jeu
			//ResultSet rs1 = c.retournerRequete("select * from sae_jeu,sae_equipe where sae_jeu.idjeu = sae_equipe.idjeu");
			//new Jeu(rs1.getInt(1),rs1.getString(2)
			//changer les valeur fixe de jeu et de la liste de joueur
			ResultSet rs = c.retournerRequete("select * from sae_equipe");
			while (rs.next()) {
				Equipe e = new Equipe(rs.getString(2), rs.getInt(4), new Jeu(0,"aa",1), this.listeJoueurs);
				this.listeEquipes.put(e.getNom(),e);
				this.vue.ajouterEquipe(e.getNom()); 
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeEcuries() {
		this.listeEcuries = new HashMap<String,Ecurie>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("SELECT * FROM SAE_ECURIE");
			while (rs.next()) {
				Ecurie e = new Ecurie(rs.getInt(1),rs.getString(2));
				this.listeEcuries.put(e.getNom(),e);
				this.vue.ajouterEcurie(e.getNom()); 
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiserListeJeux() {
		this.listeJeux = new HashMap<String, Jeu>();
		Connexion c = Connexion.getInstance();
		ResultSet rs = c.retournerRequete("SELECT * FROM SAE_JEU");
		try {
			while (rs.next()) {
				Jeu j = new Jeu(rs.getInt(1),rs.getString(2),rs.getInt(3));
				this.listeJeux.put(j.getNom(),j);
				this.vue.ajouterJeu(j.getNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		case RECHERCHER:
			b.setForeground(Color.RED);
			String[] tabRecherche = {""};
			if(this.listeEquipes.containsKey(this.vue.getTextRecherche().toUpperCase())
					|| this.listeEquipes.containsKey(this.vue.getTextRecherche().toLowerCase())) {
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
						System.out.println(this.vue.getEquipeSelectionne());
						System.out.println(rs.getString(1));
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
			JList<String> list = (JList<String>) e.getSource();
			if (!(list.isSelectionEmpty())) {
				this.vue.setNomEquipe(this.vue.getEquipeSelectionne());
				this.vue.setJeu(this.getNomJeuByName(this.vue.getEquipeSelectionne()));
				this.vue.setEcurie(this.getNomEcurieByName(this.vue.getEquipeSelectionne()));
			}
		}
	}

}
