package controleur;
import vue.VueCalendrier;
import vue.VueClassement;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.Connexion;
import modele.Jeu;
import modele.Tournoi;

public class ControleurCalendrier implements ActionListener, ListSelectionListener {
	public enum Etat {
		CREER, MODIFIER, ANNULER, DECONNECTER, SUPPRIMER, VALIDER, ECURIE, CLASSEMENT, EQUIPES, JOUEURS, CALENDRIER,
	}
	
	private VueCalendrier vue;
	private Etat etat;
	
	public ControleurCalendrier(VueCalendrier vue) {
		this.vue = vue;
		this.initialiserListes();
		this.etat = Etat.CREER;
	}
	
	// INTIALISER LES LISTES
	public void initialiserListes() {
		this.initialiserListeResponsables();
		this.initialiserListeArbitres();
		this.initialiserListeJeux();
		this.initialiserListeTournois();
	}
	
	public void initialiserListeTournois() {
		for (String nomTournoi : ControleurConnexion.listeTournois.keySet()) {
			this.vue.ajouterTournoi(nomTournoi);
		}
	}
	
	public void initialiserListeResponsables() {
		for (String nomResponsable : ControleurConnexion.listeResponsables.keySet()) {
			this.vue.ajouterResponsable(nomResponsable);
		}
	}
	
	public void initialiserListeArbitres() {
		for (String nomArbitre : ControleurConnexion.listeArbitres.keySet()) {
			this.vue.ajouterArbitre(nomArbitre);
		}
	}
	
	public void initialiserListeJeux() {
		for (String nomJeu : ControleurConnexion.listeJeux.keySet()) {
			this.vue.ajouterJeu(nomJeu);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case ECURIE :
			VueERA fenEcurie = new VueERA();
			fenEcurie.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		
		case EQUIPES :
			VueEquipe fenEquipe = new VueEquipe();
			fenEquipe.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		case JOUEURS :
			VueJoueur fenJoueur = new VueJoueur();
			fenJoueur.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueCalendrier.fermerFenetre(this.vue.fenetreCalendrier);
		break;
		case SUPPRIMER :
			if ((this.vue.getTournoiSelectionne()!=null && this.vue.confirmerSuppression()==0)) {
				Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
				this.vue.supprimerTournoi();
				ControleurConnexion.listeTournois.remove(t.getNom());
				Connexion.getInstance().executerRequete("DELETE SAE_DEFINIR WHERE IDTOURNOI ="+t.getID());
				Connexion.getInstance().executerRequete("DELETE SAE_TOURNOI WHERE IDTOURNOI = "+t.getID());
				this.vue.creerTournoi();
			}
			break;
		case VALIDER:
			if (this.vue.estRemplie()) {
				// Conversion d'une Date en String
				String strDate = this.vue.entreeDate.getDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
				
				String strHeure = this.vue.entreeHeure.getTime().format(DateTimeFormatter.ofPattern("HH:mm"));
				System.out.println(strHeure);
				
				// Instancie un tournoi
				Tournoi t = new Tournoi(2,this.vue.entreeNom.getText(),strDate,
						strHeure, this.vue.getEchelle());
				t.setArbitre(ControleurConnexion.listeArbitres.get(this.vue.getArbitre())); // On récupère l'arbitre
				t.setResponsable(ControleurConnexion.listeResponsables.get(this.vue.getResponsable()));
				
				if (estDateHeureValide()) {
					//Vérifie si c'est une creation ou une modification
					if (this.vue.titreModif.getText().equals("Créer un tournoi")) {
						if (!(ControleurConnexion.listeTournois.containsKey(t.getNom()))) {
							// En cas de creation, on recupere la prochaine valeur de la sequence, pour l'attribuer au tournoi
							ResultSet rs = Connexion.getInstance().retournerRequete("SELECT seq_tournoiId.nextval FROM dual");
							
							try {
								if (rs.next()) {
									t.setID(rs.getInt(1));
								}
								rs.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							Connexion.getInstance().executerRequete("INSERT INTO SAE_TOURNOI VALUES ("+t.getID()+", '"+t.getNom()+"', TO_DATE('"+t.getDate()+
									"','DD/MM/YYYY'), '"+ t.getHeureDebut()+"', "+t.getArbitre().getID()+", "+t.getResponsable().getID()+", '"+t.getEchelle()+"')");;
							
							for (String nomJeu : this.vue.getJeux()) {
								Jeu j = ControleurConnexion.listeJeux.get(nomJeu);
								t.ajouterJeu(j);
								Connexion.getInstance().executerRequete("INSERT INTO SAE_DEFINIR VALUES ("+t.getID()+","+j.getID()+")");
							}
							ControleurConnexion.listeTournois.put(t.getNom(), t);
							this.vue.ajouterTournoi(t.getNom());
						}else {
								this.vue.tournoiExiste();
						}
					} else {
						t.setID(ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne()).getID());
						Connexion.getInstance().executerRequete("DELETE SAE_DEFINIR WHERE IDTOURNOI ="+t.getID());
						for (String nomJeu : this.vue.getJeux()) {
							Jeu j = ControleurConnexion.listeJeux.get(nomJeu);
							t.ajouterJeu(j);
							Connexion.getInstance().executerRequete("INSERT INTO SAE_DEFINIR VALUES ("+t.getID()+","+j.getID()+")");
						}
						
						Connexion.getInstance().executerRequete("UPDATE SAE_TOURNOI SET NOMTOURNOI = '"+t.getNom()+"',"
								+ "DATETOURNOI = TO_DATE('"+t.getDate()+"','DD/MM/YYYY'), HEUREDEBUT='"+t.getHeureDebut()+"', ECHELLETOURNOI ='"+t.getEchelle()+"',"
										+ "IDARBITRE = "+t.getArbitre().getID()+", IDRESPONSABLE = "+t.getResponsable().getID()+
										"WHERE IDTOURNOI = "+t.getID());
						ControleurConnexion.listeTournois.remove(this.vue.getTournoiSelectionne());
						ControleurConnexion.listeTournois.put(t.getNom(), t);
						this.vue.modifierTournoi();
						}
					this.vue.creerTournoi();
				}
			}
			break;
			case ANNULER :
			case CREER :
				this.vue.creerTournoi();
			break;
		default:
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		switch (this.etat) {
		case SUPPRIMER:
			this.etat = Etat.CREER;
			VueCalendrier.afficherPanel(this.vue.panelModif);
			VueCalendrier.afficherTexte(this.vue.titreModif, "Créer un tournoi");
			VueCalendrier.supprimerTexte(this.vue.entreeNom);
			this.vue.entreeDate = null;
			this.vue.entreeHeure = null;
			break;
		default:
			@SuppressWarnings
			("unchecked") JList<String> list = (JList<String>) e.getSource();
			if (!(list.isSelectionEmpty())) {
				this.etat = Etat.MODIFIER;
				VueCalendrier.afficherPanel(this.vue.panelModif);
				VueCalendrier.afficherTexte(this.vue.titreModif, "Modifier un tournoi");
				
				Tournoi t = ControleurConnexion.listeTournois.get(this.vue.getTournoiSelectionne());
				this.vue.entreeNom.setText(t.getNom());
				
				// Formatte un String à une Date et le place dans la saisie de date
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = LocalDate.parse(t.getDate(), formatter);
				this.vue.entreeDate.setDate(date);

				// Formatte un String à un Time et le place dans la saisie de l'heure
				formatter = DateTimeFormatter.ofPattern("HH:mm");
				LocalTime heure = LocalTime.parse(t.getHeureDebut(), formatter);
				this.vue.entreeHeure.setTime(heure);
				
				this.vue.setEchelle(t.getEchelle());
				this.vue.setArbitre(t.getArbitre().getPrenomNom());
				this.vue.setResponsable(t.getResponsable().getPrenomNom());
				this.vue.setJeux(t.getNomJeux());
			}
		}
	}
	
	private boolean estDateHeureValide() {
		// Vérification de la date
		LocalDate dateTournoi = this.vue.entreeDate.getDate();  // Date saisie
		if (dateTournoi.isBefore(LocalDate.now())) { // Date postérieure à la date du jour
			JOptionPane.showMessageDialog(null, "Veuillez choisir une date postérieure à la date du jour.",
				      "Erreur à la saisie de la date", JOptionPane.ERROR_MESSAGE);
			return false;
			
		}
		
		int anneeCourante = LocalDate.now().getYear();
		if (dateTournoi.getYear() != anneeCourante) { // Si l'année différente à l'année courante
			JOptionPane.showMessageDialog(null, "Veuillez entrer un tournoi correspondant à la saison courante (" + anneeCourante + ").",
				      "Erreur à la saisie de la date", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Vérification de l'heure
		LocalTime heureTournoi = this.vue.entreeHeure.getTime(); // Heure Saisie
		if (dateTournoi.equals(LocalDate.now()) &&  // Si l'heure du tournoi est avant l'heure du jour (sachant que la date est égale
				heureTournoi.isBefore(LocalTime.now())) {
			JOptionPane.showMessageDialog(null, "Veuillez choisir une heure postérieure à celle du jour.",
				      "Erreur à la saisie de la date", JOptionPane.ERROR_MESSAGE);
			return false;
			
		}
		
		return true;
	}
}