package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Arbitre;
import modele.Connexion;
import modele.Ecurie;
import modele.Responsable;
import vue.VueERA;


public class ControleurERA implements ActionListener, ListSelectionListener {
	
	public enum Etat {
		CREER, MODIFIER, SUPPRIMER, DECONNECTER, CALENDRIER, JOUEURS, CLASSEMENT, RECHERCHER, VALIDER, ANNULER
	}

	private VueERA vue;
	private Etat etat;
	private Map<String, Ecurie> listeEcuries;
	private Map<String, Responsable> listeResponsables;
	private Map<String, Arbitre> listeArbitres;
	
	public ControleurERA(VueERA vue) {
		this.vue = vue;
		this.initialiserListeEcuries();
		this.initialiserListeResponsables(ControleurCalendrier.listeResponsables);
		this.initialiserListeArbitres(ControleurCalendrier.listeArbitres);
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
	
	public void initialiserListeResponsables(Map<String,Responsable> listeResponsables) {
		this.listeResponsables = listeResponsables;
		for (String nomResponsable : listeResponsables.keySet()) {
			this.vue.ajouterResponsable(nomResponsable);
		}
	}
	
	public void initialiserListeArbitres(Map<String,Arbitre> listeArbitres) {
		this.listeArbitres = listeArbitres;
		for (String nomArbitres : listeArbitres.keySet()) {
			this.vue.ajouterArbitre(nomArbitres);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		this.vue.setNomSelectionne();
	}

}
