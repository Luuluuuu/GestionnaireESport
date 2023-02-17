package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controleur.ControleurInscriptionTournoi;
import controleur.ControleurInscriptionTournoi.Etat;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class VueInscriptionTournoi extends JFrame{
	
	public JFrame fenetreInscriptionTournoi;
	public JPanel panelModif;
	public JLabel titreModif;
	private DefaultListModel<String> modeleTournois = new DefaultListModel<String>();
	private JList<String> listeTournois;
	private DefaultListModel<String> modeleEquipes = new DefaultListModel<String>();
	private JList<String> listeEquipes;
	private JComboBox<String> selectionJeu;
	
	public JFrame getFrame() {
		return this.fenetreInscriptionTournoi;
	}
	
	public VueInscriptionTournoi() {
		// CREATION DE LA FENETRE //
		fenetreInscriptionTournoi = new JFrame();
		fenetreInscriptionTournoi.getContentPane().setBackground(Couleur.BLEU1);
		fenetreInscriptionTournoi.setResizable(false);
		fenetreInscriptionTournoi.setBounds(100, 100, 1400, 900);
		fenetreInscriptionTournoi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ControleurInscriptionTournoi controleur = new ControleurInscriptionTournoi(this);
		
		// HEADER //
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fenetreInscriptionTournoi.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelHeader.add(panelMenu);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnEquipes = new JButton("Equipes");
		btnEquipes.setForeground(Color.WHITE);
		btnEquipes.setFont(new Font("Roboto", Font.BOLD, 15));
		btnEquipes.setBackground(Couleur.BLEU2);
		panelMenu.add(btnEquipes);
		
		JButton btnJoueurs = new JButton("Joueurs");
		btnJoueurs.setForeground(Color.WHITE);
		btnJoueurs.setFont(new Font("Roboto", Font.BOLD, 15));
		btnJoueurs.setBackground(Couleur.BLEU2);
		panelMenu.add(btnJoueurs);
		
		JButton btnTournois = new JButton("Tournois");
		btnTournois.setForeground(Color.WHITE);
		btnTournois.setFont(new Font("Roboto", Font.BOLD, 15));
		btnTournois.setBackground(Couleur.BLEU2);
		panelMenu.add(btnTournois);
		
		JButton btnClassement = new JButton("Classement");
		btnClassement.setForeground(Color.WHITE);
		btnClassement.setFont(new Font("Roboto", Font.BOLD, 15));
		btnClassement.setBackground(Couleur.BLEU2);
		panelMenu.add(btnClassement);
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelDeconnexion);
		
		JButton btnDeconnexion = new JButton("Se déconnecter");
		btnDeconnexion.setForeground(Color.WHITE);
		btnDeconnexion.setFont(new Font("Roboto", Font.BOLD, 13));
		btnDeconnexion.setBackground(Couleur.ROUGE);
		panelDeconnexion.add(btnDeconnexion);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreInscriptionTournoi.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTournoi = new JPanel();
		panelTournoi.setBackground(Couleur.BLEU1);
		panelContenu.add(panelTournoi);
		GridBagLayout gbl_panelTournoi = new GridBagLayout();
		gbl_panelTournoi.columnWidths = new int[]{692, 0};
		gbl_panelTournoi.rowHeights = new int[] {100, 724, 0};
		gbl_panelTournoi.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTournoi.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelTournoi.setLayout(gbl_panelTournoi);
		
		JPanel panelTitreT = new JPanel();
		panelTitreT.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitreT = (FlowLayout) panelTitreT.getLayout();
		fl_panelTitreT.setVgap(0);
		fl_panelTitreT.setHgap(50);
		fl_panelTitreT.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreT = new GridBagConstraints();
		gbc_panelTitreT.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreT.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreT.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreT.gridx = 0;
		gbc_panelTitreT.gridy = 0;
		panelTournoi.add(panelTitreT, gbc_panelTitreT);
		
		// LISTE DES TOURNOIS //
		JLabel Tournois = new JLabel("Sélectionnez le tournoi");
		Tournois.setForeground(Color.WHITE);
		Tournois.setFont(new Font("Roboto", Font.BOLD, 20));
		Tournois.setHorizontalAlignment(SwingConstants.LEFT);
		panelTitreT.add(Tournois);
		
		JPanel panelListe = new JPanel();
		panelListe.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListe = (FlowLayout) panelListe.getLayout();
		fl_panelListe.setHgap(50);
		fl_panelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListe = new GridBagConstraints();
		gbc_panelListe.fill = GridBagConstraints.BOTH;
		gbc_panelListe.gridx = 0;
		gbc_panelListe.gridy = 1;
		panelTournoi.add(panelListe, gbc_panelListe);
		
		listeTournois = new JList<String>(modeleTournois);
		listeTournois.setVisibleRowCount(13);
		listeTournois.setName("Tournoi");
		listeTournois.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeTournois.setFixedCellHeight(50);
		listeTournois.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(listeTournois);
		panelListe.add(scrollPane);
		
		panelModif = new JPanel();
		panelModif.setBackground(Couleur.BLEU1);
		panelContenu.add(panelModif);
		GridBagLayout gbl_panelModif = new GridBagLayout();
		gbl_panelModif.columnWidths = new int[]{692, 0};
		gbl_panelModif.rowHeights = new int[] {100, 622, 100, 0};
		gbl_panelModif.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelModif.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelModif.setLayout(gbl_panelModif);
		
		JPanel panelTitreM = new JPanel();
		panelTitreM.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreM = new GridBagConstraints();
		gbc_panelTitreM.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreM.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreM.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreM.gridx = 0;
		gbc_panelTitreM.gridy = 0;
		panelModif.add(panelTitreM, gbc_panelTitreM);
		panelTitreM.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Couleur.BLEU1);
		FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
		fl_panel_1.setHgap(50);
		fl_panel_1.setAlignment(FlowLayout.LEFT);
		fl_panel_1.setVgap(0);
		panelTitreM.add(panel_1);
		
		JLabel titrePoule = new JLabel("Sélectionnez l'équipe à inscrire");
		titrePoule.setForeground(Color.WHITE);
		titrePoule.setFont(new Font("Roboto", Font.BOLD, 20));
		panel_1.add(titrePoule);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Couleur.BLEU1);
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setHgap(50);
		fl_panel_2.setAlignment(FlowLayout.RIGHT);
		fl_panel_2.setVgap(0);
		panelTitreM.add(panel_2);
		
		selectionJeu = new JComboBox<String>();
		selectionJeu.setFont(new Font("Roboto", Font.PLAIN, 11));
		selectionJeu.setPreferredSize(new Dimension(205, 20));
		selectionJeu.addItem("- Sélectionnez un jeu -");
		panel_2.add(selectionJeu);
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelEquipe = new GridBagConstraints();
		gbc_panelEquipe.fill = GridBagConstraints.BOTH;
		gbc_panelEquipe.insets = new Insets(0, 0, 5, 0);
		gbc_panelEquipe.gridx = 0;
		gbc_panelEquipe.gridy = 1;
		panelModif.add(panelEquipe, gbc_panelEquipe);
		panelEquipe.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 5));
		
		listeEquipes = new JList<String>(this.modeleEquipes);
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setVisibleRowCount(12);
		listeEquipes.setName("Equipe");
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		JScrollPane scrollPane2 = new JScrollPane(listeEquipes);
		panelEquipe.add(scrollPane2);
		
		JPanel panelValider = new JPanel();
		panelValider.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraints();
		gbc_panelValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelValider.gridx = 0;
		gbc_panelValider.gridy = 2;
		panelModif.add(panelValider, gbc_panelValider);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Roboto", Font.BOLD, 13));
		btnValider.setBackground(Couleur.VERT);
		panelValider.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Roboto", Font.BOLD, 13));
		btnAnnuler.setBackground(Couleur.GRIS);
		panelValider.add(btnAnnuler);
		
		// ECOUTE SUR LES COMPOSANTS //
		this.listeTournois.addListSelectionListener(controleur);
		this.listeEquipes.addListSelectionListener(controleur);
		this.selectionJeu.addActionListener(controleur);
		
		// BOUTONS //
		btnEquipes.addActionListener(controleur);
		btnJoueurs.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
		btnDeconnexion.addActionListener(controleur);
		btnValider.addActionListener(controleur);
	}

	// RECEVOIR L'ETAT //
	public Etat getEtat(JButton b) {
		if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText()=="Joueurs") {
			return Etat.JOUEURS;
		} else if (b.getText()=="Classement") {
			return Etat.CLASSEMENT;
		} else if (b.getText()=="Equipes") {
			return Etat.EQUIPES;
		} else if (b.getText()=="Valider") {
			return Etat.VALIDER;
		}
		return null;
	}
	
	public Etat getEtat(JList l) {
		if (l.getName().equals("Tournoi")) {
			return Etat.TOURNOI;
		} if (l.getName().equals("Equipe")) {
			return Etat.EQUIPE;
		}
		return null;
	}
	
	// AJOUTER LES COMPOSANTS A LA LISTE //
	public void ajouterTournoi(String nomTournoi) {
		this.modeleTournois.addElement(nomTournoi);
	}

	public void ajouterJeu(String nomJeu) {
		this.selectionJeu.addItem(nomJeu);
	}
	
	public void ajouterEquipe(String nomEquipe) {
		this.modeleEquipes.addElement(nomEquipe);
	}
	
	public String getTournoiSelectionne() {
		return this.listeTournois.getSelectedValue();
	}

	public String getJeuSelectionne() {
		return (String) this.selectionJeu.getSelectedItem();
	}

	public String getEquipeSelectionne() {
		return this.listeEquipes.getSelectedValue();
	}
	
	// VIDE LA LISTE DE JEUX AVANT DE LA REMPLIR //
	public void viderJeux() {
		this.selectionJeu.removeAllItems();
	}

	public void viderEquipes() {
		this.modeleEquipes.clear();
	}
	
	// DESELECTIONNE TOUS LES COMPOSANTS DE DONNEES DE LA PAGE //
	public void deselectionner() {
		this.listeEquipes.clearSelection();
		this.selectionJeu.setSelectedItem("- Sélectionnez un jeu -");
		this.listeTournois.clearSelection();
	}
	
	// VERIFIE QUE TOUS LES CHAMPS SONT REMPLIS //
	public boolean estRemplie() {
		return (!this.listeTournois.isSelectionEmpty()) && 
				(!this.listeEquipes.isSelectionEmpty()) && 
				(!this.selectionJeu.getSelectedItem().equals("- Sélectionnez un jeu -"));
	}
	
}