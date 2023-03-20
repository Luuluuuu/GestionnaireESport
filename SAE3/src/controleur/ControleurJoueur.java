package controleur;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modele.Connexion;
import modele.Etat;
import modele.Joueur;
import modele.Profil;
import vue.Vue;
import vue.VueCalendrier;
import vue.VueConnexion;
import vue.VueERA;
import vue.VueEquipe;
import vue.VueInscriptionTournoi;
import vue.VueJoueur;
import vue.VueClassement;



public class ControleurJoueur implements ActionListener, ListSelectionListener {
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
		this.intialiserListeEquipes();
	}
	
	public void initialiserListeJoueurs() {		
		if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
			for (String nomJoueur : ControleurConnexion.listeJoueursParEcurie) {
				this.vue.ajouterJoueur(nomJoueur);
			}
		}
		else {
			for (String nomJoueur : ControleurConnexion.listeJoueurs.keySet()) {
				this.vue.ajouterJoueur(nomJoueur);
			}
		}
	}
	
	public void intialiserListeEquipes() {
		this.vue.ajouterEquipe("- Sélectionnez une équipe -");
		if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
			for (String nomEquipe : ControleurConnexion.listeEquipesParEcurie) {
				this.vue.ajouterEquipe(nomEquipe);
			}
		}
		else {
			for (String nomEquipe : ControleurConnexion.listeEquipes.keySet()) {
				this.vue.ajouterEquipe(nomEquipe);
			}
		}
	}
	
	// Retourne vrai si le profil est identique à celui saisi
	public static boolean estProfil(String nom) {
		return ControleurConnexion.profilUtilisateur.toString().equals(nom);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		this.etat = this.vue.getEtat(b);
		switch (this.etat) {
		case EQUIPES :
			VueEquipe fenEQUIPE = new VueEquipe();
			fenEQUIPE.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.getFrame());
		break;
		case CLASSEMENT :
			VueClassement fenClassement = new VueClassement();
			fenClassement.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.getFrame());
		break;
		case ECURIE :
			VueERA fenERA = new VueERA();
			fenERA.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.getFrame());
		break;
		case TOURNOIS :
			VueInscriptionTournoi fenTournois = new VueInscriptionTournoi();
			fenTournois.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.getFrame());
		break;
		case CALENDRIER :
			VueCalendrier fenCalendrier = new VueCalendrier();
			fenCalendrier.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.getFrame());
		break;
		case ANNULER :
            this.vue.annulerEntreeJoueur();
            break;
		case DECONNECTER :
			Connexion.fermerConnexion();
			VueConnexion fen = new VueConnexion();
			fen.getFrame().setVisible(true);
			VueJoueur.fermerFenetre(this.vue.getFrame());
		break;
		case CREER:
			this.vue.afficherCreationJoueur();
		break;
		case RECHERCHER:
			if (!this.vue.getTextRecherche().equals("")) {
				this.vue.filtrerRecherche();
			} else {
				this.vue.setDefaultListModel();
			}
			this.vue.afficherCreationJoueur();
			break;
		case VALIDER:
			Calendar dateAjd = Calendar.getInstance();
			dateAjd.set(Calendar.YEAR, dateAjd.get(Calendar.YEAR)-16);
			Date dateRentree = null;
			try {
				dateRentree = new SimpleDateFormat("dd/MM/yyyy").parse(this.vue.getDateNaissance());
				
			} catch (ParseException e2) {
				e2.printStackTrace();
				
			}
			
			//Vérifie que tous les champs sont remplis
			if(this.vue.getNom().equals("") 
					|| this.vue.getNomEquipe().equals(Vue.SELECTIONNER_UNE_EQUIPE)
					|| this.vue.getPrenom().equals("") 
					|| this.vue.getPseudo().equals("")
					|| this.vue.getDateNaissance() == null 
					|| this.vue.getNationalite().equals("")) {
				this.vue.estVide();
				b.setForeground(Color.RED);
				
			} else if (dateRentree.compareTo(dateAjd.getTime())>0) {
				this.vue.mauvaiseDate();
				
			} else {
				// Instancie un tournoi
				Joueur joueur = new Joueur(0,this.vue.getNom(),this.vue.getPrenom(),
						this.vue.getPseudo(), this.vue.getDateNaissance(),this.vue.getNationalite(),
						ControleurConnexion.listeEquipes.get(this.vue.getNomEquipe()),null);
				//Vérifie si c'est une creation ou une modification
				if (this.vue.getTitreModif().getText().equals("Créer un joueur")) {
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
						Connexion.getInstance().executerRequete(
							"INSERT INTO sae_joueur VALUES (seq_joueurid.currval, '" + joueur.getNom()+
							"', '" + joueur.getPrenom() + 
							"', '" + joueur.getPseudo() +
							"', TO_DATE('" + joueur.getDateNaissance() + 
							"','DD/MM/YYYY'), '" + joueur.getNationalite()+
							"', "+joueur.getEquipe().getID()+", '')");
						
						ControleurConnexion.listeJoueurs.put(joueur.getPrenomPseudoNom(),joueur);
						joueur.creerLogin(this.vue.getMotDePasse());
						this.vue.ajouterJoueur(joueur.getPrenomPseudoNom());
						
					}
				} else {
					// SINON MODIFICATION
					joueur.setID(ControleurConnexion.listeJoueurs.get(this.vue.getJoueurSelectionne()).getID());
					Connexion.getInstance().executerRequete("UPDATE SAE_JOUEUR SET NOMJOUEUR = '" + joueur.getNom() +
							"', PRENOMJOUEUR = '"+joueur.getPrenom() +
							"', PSEUDOJOUEUR = '"+joueur.getPseudo() +
							"', DATENAISSANCE = TO_DATE('"+joueur.getDateNaissance() +
							"','DD/MM/YYYY'), NATIONALITE = '" + joueur.getNationalite() +
							"', IDEQUIPE = " + ControleurConnexion.listeEquipes.get(this.vue.getNomEquipe()).getID()+
							" WHERE IDJOUEUR = "+joueur.getID());
					
					// CREER IDENTIFIANTS JOUEUR
					String login = ((this.vue.getNom()+"."+this.vue.getPrenom()).replaceAll("\\s+", "_")).toLowerCase();
					String req = "UPDATE SAE_USER SET LOGIN='" + login;
					if (!(this.vue.getMotDePasse().isEmpty())) {
						req += "', MOTDEPASSE='"+this.vue.getMotDePasse().hashCode();
						
					}
					Connexion.getInstance().executerRequete(req + "' WHERE IDJOUEUR = " + joueur.getID());
					
					ControleurConnexion.listeJoueurs.remove(this.vue.getJoueurSelectionne());
					ControleurConnexion.listeJoueurs.put(joueur.getPrenomPseudoNom(), joueur);
					this.vue.modifierJoueur();
					
				}
					this.vue.afficherCreationJoueur();
					this.vue.viderMotDePasse();
					b.setForeground(Color.WHITE);
					
			}
			break;
			
		case PHOTO:
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(null);
		        File file = fileChooser.getSelectedFile();
		        
		        if (result == JFileChooser.APPROVE_OPTION) {
		        	 String fileName = file.getName();
		        	 
		             if (fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
		                 // Le fichier sélectionné est une image au format PNG, JPEG ou JPG.
		                   String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		                   String newFileName = this.vue.getPseudo() + fileExtension; 
		                   
		                   //  ATTENTION SI LE PSEUDO PAS ENCORE REMPLI CREE UNE IMAGE SANS NOM 
		                   File targetDirectory = new File(System.getProperty("user.dir")+"\\src\\photos\\");
		                   if (!targetDirectory.exists()) { //Crée le repertoire si il existe pas
		                     targetDirectory.mkdirs();
		                     
		                   }
		                   File targetFile = new File(targetDirectory, newFileName);
		                   try { //Enregistrer l'image
		                	   Files.copy(file.toPath(), targetFile.toPath());
		                	   JOptionPane.showMessageDialog(
		                			   null, "Le fichier a été enregistré.", "Succès", JOptionPane.INFORMATION_MESSAGE);
		                	   
						   } 
		                   catch (Exception ex) { // Si l'image existe deja, la supprime pour ajouter la nouvelle
			                   File targetExisteFile = new File(targetDirectory, newFileName);
			                   
			                   // Suppression
			                   if (!targetExisteFile.delete()) {
			                	   JOptionPane.showMessageDialog(
			                			   null, "L'image n'a pas pu être supprimée.", "Erreur", JOptionPane.ERROR_MESSAGE);
			                	   
			                   }
			    			try {
								Files.copy(file.toPath(), targetFile.toPath());	//Ajout
								
							} catch (IOException e1) {
								e1.printStackTrace();
								
							}
			            	 JOptionPane.showMessageDialog(
			            			 null, "Le fichier a été modifié.", "Attention", JOptionPane.INFORMATION_MESSAGE);
			            	 
						}            	
		                 //Afficher l'image qui vient d'être ajoutée
		      	       	 ImageIcon imageIcon = new ImageIcon(targetFile.getAbsolutePath());
		    			 Image image = imageIcon.getImage();
		    			 Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		    			 imageIcon = new ImageIcon(resizedImage);
		    			 this.vue.getPhoto().setIcon(imageIcon);
		    			 
		             } else {
		            	 JOptionPane.showMessageDialog(
		            	     null, "Le fichier sélectionné n'est pas une image au format PNG, JPEG ou JPG", "Erreur", JOptionPane.ERROR_MESSAGE);
		             
		             }
		          // Le fichier sélectionné a été approuvé.    
		        }
			break;
		case SUPPRIMER:
			if ((this.vue.getJoueurSelectionne()!=null && this.vue.confirmerSuppression()==0)) {
				Connexion.getInstance().executerRequete("DELETE SAE_USER " + 
						"WHERE idjoueur = " + ControleurConnexion.listeJoueurs.get(this.vue.getJoueurSelectionne()).getID());
				Connexion.getInstance().executerRequete("DELETE SAE_JOUEUR WHERE IDJOUEUR=" +
							ControleurConnexion.listeJoueurs.get(this.vue.getJoueurSelectionne()).getID());
				this.vue.supprimerJoueur();
				
			}
			this.vue.afficherCreationJoueur();
			break;
			
		default:
			break;
			
		}
		//désactive le bouton lorsque aucun élément n'est séléctionné
		Vue.desactiverBouton(this.vue.getBtnSupprimer());
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			VueJoueur.afficherTexte(this.vue.getTitreModif(), "Modifier un joueur");
			Joueur joueur = ControleurConnexion.listeJoueurs.get(this.vue.getJoueurSelectionne());
			this.vue.setEquipe(joueur.getEquipe().getNom());
			this.vue.setNomJoueur(joueur.getNom());
			this.vue.setPrenomJoueur(joueur.getPrenom());
			this.vue.setPseudoJoueur(joueur.getPseudo());
			this.vue.setDateNaissanceJoueur(joueur.getDateNaissance());
			this.vue.setNationaliteJoueur(joueur.getNationalite());
			
			ImageIcon imageIcon = new ImageIcon(System.getProperty("user.dir")+"\\src\\photos\\"+joueur.getPhoto());
			Image image = imageIcon.getImage();
			Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(resizedImage);
			this.vue.getPhoto().setIcon(imageIcon);
 	       	
 	       	Vue.activerBouton(this.vue.getBtnSupprimer());
 	       	 
        }
		if (this.etat == Etat.SUPPRIMER) {
			this.etat = Etat.CREER;
			
		}
	}
}
