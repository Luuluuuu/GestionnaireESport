package controleur;

import java.awt.Color;
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
import modele.Connexion;
import modele.Joueur;
import vue.VueJoueur;



public class ControleurJoueur implements ActionListener, ListSelectionListener {
	
	public enum Etat{RECHERCHER,VALIDER,ANNULER,CREER,SUPPRIMER,DECONNECTER,ECURIE,CALENDRIER,JOUEURS}
	private VueJoueur vue;
	private Etat etat;
	private	Map<String, Joueur> listeJoueurs;

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
		this.listeJoueurs = new HashMap<String,Joueur>();
		try {
			Connexion c = Connexion.getInstance();
			ResultSet rs = c.retournerRequete("select * from sae_joueur");
			while (rs.next()) {
				Joueur j = new Joueur(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				this.listeJoueurs.put(j.getNom(),j);
				this.vue.ajouterJoueur(j.getNom()); 
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
		case RECHERCHER:
			String[] tabRecherche = {""};
			if(this.listeJoueurs.containsKey(this.vue.getTextRecherche().toUpperCase())
					|| this.listeJoueurs.containsKey(this.vue.getTextRecherche().toLowerCase())) {
				tabRecherche[0] = this.vue.getTextRecherche().toUpperCase(); 
				this.vue.filtrageListeJoueur(tabRecherche);
			}
			if(this.vue.getTextRecherche()=="") {
				//trouver un moyen de remettre la liste de base
			}
			
			break;
		case VALIDER:
			b.setForeground(Color.RED);
			//bouton click 2 foi
			//Vérifie que tout les champs sont remplie
			if(this.vue.getNom().equals("")
					|| this.vue.getPrenom().equals("")
					|| this.vue.getPseudo().equals("")
					|| this.vue.getDateNaissance().equals("")
					|| this.vue.getNationalite().equals("")) {
				this.vue.setNomJoueur("");
				this.vue.setPrenomJoueur("");
				this.vue.setPseudoJoueur("");
				this.vue.setDateNaissanceJoueur("");
				this.vue.setNationaliteJoueur("");
			} else {
				Connexion.getInstance().executerRequete("insert into SAE_Joueur (idJoueur,nomjoueur,prenomjoueur,pseudojoueur,dateNaissance,nationalité,idequipe) values (2,'perbost','théo','darkThéo','01/02/95','Allemagne',1)");
				this.vue.ajouterJoueur(this.vue.getNom());
			}
			break;
		case SUPPRIMER:
			if ((this.vue.getJoueurSelectionne()!=null && this.vue.confirmerSuppression()==0)) {
				Connexion.getInstance().executerRequete("delete sae_joueur where nomjoueur='"+this.vue.getJoueurSelectionne()+"'	");
				this.vue.supprimerJoueur(this.vue.getJoueurSelectionne());
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
				Joueur joueur = this.listeJoueurs.get(this.vue.getJoueurSelectionne());
				this.vue.setNomJoueur(joueur.getNom());
				this.vue.setPrenomJoueur(joueur.getPrenom());
				this.vue.setPseudoJoueur(joueur.getPseudo());
				this.vue.setDateNaissanceJoueur(joueur.getDateNaissance());
				this.vue.setNationaliteJoueur(joueur.getNationalite());
			}
		}
		
	}

}