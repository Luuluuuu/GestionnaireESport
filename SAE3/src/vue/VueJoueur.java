package vue;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

import controleur.ControleurCalendrier;
import controleur.ControleurCalendrier.Etat;
import controleur.ControleurEquipe;
import controleur.ControleurJoueur;
import modele.Connexion;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class VueJoueur extends JFrame{
	
	public JFrame fenetreJoueur;
	public JTextField entreePrenom;
	public JPanel panelModif;
	public JLabel titreModif;
	private DefaultListModel<String> modeleTournois;
	private JList<String> listeEquipes;
	private static List<JCheckBox> listeCheck = new ArrayList<JCheckBox>();
	private JTextField recherche;
	private JTextField entreeNom;
	private JTextField entreePseudo;
	private JTextField textField;
	private JTextField textField_1;
	
	public JFrame getFrame() {
		return this.fenetreJoueur;
	}
	
	public VueJoueur() {
		// CREATION DE LA FENETRE //
		fenetreJoueur = new JFrame();
		fenetreJoueur.getContentPane().setBackground(Couleur.BLEU1);
		fenetreJoueur.setResizable(false);
		fenetreJoueur.setBounds(100, 100, 1400, 900);
		fenetreJoueur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// CONTROLEUR
		ControleurJoueur controleur = new ControleurJoueur(this);
		
		// HEADER //
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fenetreJoueur.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelHeader.add(panelMenu);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCalendrier = new JButton("Calendrier");
		btnCalendrier.setForeground(Color.WHITE);
		btnCalendrier.setFont(new Font("Roboto", Font.BOLD, 15));
		btnCalendrier.setBackground(Couleur.BLEU2);
		panelMenu.add(btnCalendrier);
		
		JButton btnEcuries = new JButton("Ecuries / Responsables / Arbitres");
		btnEcuries.setForeground(Color.WHITE);
		btnEcuries.setFont(new Font("Roboto", Font.BOLD, 15));
		btnEcuries.setBackground(Couleur.BLEU2);
		panelMenu.add(btnEcuries);
		
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
		
		JButton btnDeconnexion = new JButton("Se d�connecter");
		btnDeconnexion.setForeground(Color.WHITE);
		btnDeconnexion.setFont(new Font("Roboto", Font.BOLD, 13));
		btnDeconnexion.setBackground(Couleur.ROUGE);
		panelDeconnexion.add(btnDeconnexion);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreJoueur.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.setBackground(Couleur.BLEU1);
		panelContenu.add(panelEquipe);
		GridBagLayout gbl_panelEquipe = new GridBagLayout();
		gbl_panelEquipe.columnWidths = new int[]{692, 0};
		gbl_panelEquipe.rowHeights = new int[] {100, 622, 100, 0};
		gbl_panelEquipe.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelEquipe.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelEquipe.setLayout(gbl_panelEquipe);
		
		JPanel panelTitreT = new JPanel();
		panelTitreT.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreT = new GridBagConstraints();
		gbc_panelTitreT.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreT.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreT.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreT.gridx = 0;
		gbc_panelTitreT.gridy = 0;
		panelEquipe.add(panelTitreT, gbc_panelTitreT);
		panelTitreT.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTitre = new JPanel();
		panelTitre.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_3 = (FlowLayout) panelTitre.getLayout();
		flowLayout_3.setHgap(50);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelTitreT.add(panelTitre);
		
		// LISTE DES TOURNOIS //
		JLabel Joueurs = new JLabel("Joueurs");
		Joueurs.setForeground(Color.WHITE);
		panelTitre.add(Joueurs);
		Joueurs.setFont(new Font("Roboto", Font.BOLD, 20));
		Joueurs.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panelRecherche = new JPanel();
		panelRecherche.setBackground(Couleur.BLEU1);
		panelTitreT.add(panelRecherche);
		
		recherche = new JTextField();
		recherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelRecherche.add(recherche);
		recherche.setColumns(15);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setForeground(Color.WHITE);
		btnRechercher.setFont(new Font("Roboto", Font.BOLD, 13));
		btnRechercher.setBackground(Couleur.BLEU2);
		panelRecherche.add(btnRechercher);
		
		JPanel panelListe = new JPanel();
		panelListe.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListe = (FlowLayout) panelListe.getLayout();
		fl_panelListe.setHgap(50);
		fl_panelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListe = new GridBagConstraints();
		gbc_panelListe.insets = new Insets(0, 0, 5, 0);
		gbc_panelListe.fill = GridBagConstraints.BOTH;
		gbc_panelListe.gridx = 0;
		gbc_panelListe.gridy = 1;
		panelEquipe.add(panelListe, gbc_panelListe);
		
		modeleTournois = new DefaultListModel<String>();
		listeEquipes = new JList<String>(modeleTournois);
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		panelListe.add(listeEquipes);
		
		JPanel panelBoutons = new JPanel();
		panelBoutons.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelBoutons = new GridBagConstraints();
		gbc_panelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBoutons.gridx = 0;
		gbc_panelBoutons.gridy = 2;
		panelEquipe.add(panelBoutons, gbc_panelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		JButton btnCreer = new JButton("Cr\u00E9er un nouveau joueur");
		btnCreer.setForeground(Color.WHITE);
		btnCreer.setFont(new Font("Roboto", Font.BOLD, 13));
		btnCreer.setBackground(Couleur.BLEU2);
		panelBoutons.add(btnCreer);
		
		JButton btnSupprimer = new JButton("Supprimer le joueur s\u00E9lectionn\u00E9");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Roboto", Font.BOLD, 13));
		btnSupprimer.setBackground(Couleur.GRIS);
		panelBoutons.add(btnSupprimer);
		
		// CREER OU MODIFIER UN TOURNOI
		panelModif = new JPanel();
		panelModif.setBackground(Couleur.BLEU1);
		panelContenu.add(panelModif);
		GridBagLayout gbl_panelModif = new GridBagLayout();
		gbl_panelModif.columnWidths = new int[]{692, 0};
		gbl_panelModif.rowHeights = new int[] {224, 100, 100, 100, 100, 100, 100, 0};
		gbl_panelModif.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelModif.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JPanel panelT = new JPanel();
		panelT.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panelT.getLayout();
		flowLayout_1.setVgap(40);
		flowLayout_1.setHgap(40);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelTitreM.add(panelT);
		
		titreModif = new JLabel("Cr\u00E9er un joueur");
		titreModif.setForeground(Color.WHITE);
		panelT.add(titreModif);
		titreModif.setFont(new Font("Roboto", Font.BOLD, 20));
		
		JPanel panelPhoto = new JPanel();
		panelPhoto.setBackground(Couleur.BLEU1);
		panelTitreM.add(panelPhoto);
		
		JLabel photo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("photo.jpg")).getImage();
		panelPhoto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		photo.setIcon(new ImageIcon(img));
		panelPhoto.add(photo);
		
		JPanel panelNom = new JPanel();
		panelNom.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelNom = new GridBagConstraints();
		gbc_panelNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNom.insets = new Insets(0, 0, 5, 0);
		gbc_panelNom.gridx = 0;
		gbc_panelNom.gridy = 1;
		panelModif.add(panelNom, gbc_panelNom);
		GridBagLayout gbl_panelNom = new GridBagLayout();
		gbl_panelNom.columnWidths = new int[] {250, 442, 0};
		gbl_panelNom.rowHeights = new int[]{30, 0};
		gbl_panelNom.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelNom.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelNom.setLayout(gbl_panelNom);
		
		JPanel panel = new JPanel();
		panel.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelNom.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 55, 5));
		
		JLabel nom = new JLabel("Nom");
		nom.setFont(new Font("Roboto", Font.BOLD, 14));
		panel.add(nom);
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panelNom.add(panel_1, gbc_panel_1);
		
		entreeNom = new JTextField();
		entreeNom.setToolTipText("");
		entreeNom.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeNom.setColumns(20);
		panel_1.add(entreeNom);
		
		JPanel panelPrenom = new JPanel();
		panelPrenom.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelPrenom = new GridBagConstraints();
		gbc_panelPrenom.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPrenom.insets = new Insets(0, 0, 5, 0);
		gbc_panelPrenom.gridx = 0;
		gbc_panelPrenom.gridy = 2;
		panelModif.add(panelPrenom, gbc_panelPrenom);
		GridBagLayout gbl_panelPrenom = new GridBagLayout();
		gbl_panelPrenom.columnWidths = new int[] {250, 442, 0};
		gbl_panelPrenom.rowHeights = new int[]{30, 0};
		gbl_panelPrenom.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPrenom.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelPrenom.setLayout(gbl_panelPrenom);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_5 = (FlowLayout) panel_4.getLayout();
		flowLayout_5.setHgap(55);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panelPrenom.add(panel_4, gbc_panel_4);
		
		JLabel prenom = new JLabel("Pr\u00E9nom");
		prenom.setHorizontalAlignment(SwingConstants.CENTER);
		prenom.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_4.add(prenom);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_6 = (FlowLayout) panel_5.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 0;
		panelPrenom.add(panel_5, gbc_panel_5);
		
		entreePrenom = new JTextField();
		entreePrenom.setToolTipText("");
		entreePrenom.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_5.add(entreePrenom);
		entreePrenom.setColumns(20);
		
		JPanel panelPseudo = new JPanel();
		panelPseudo.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelPseudo = new GridBagConstraints();
		gbc_panelPseudo.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelPseudo.insets = new Insets(0, 0, 5, 0);
		gbc_panelPseudo.gridx = 0;
		gbc_panelPseudo.gridy = 3;
		panelModif.add(panelPseudo, gbc_panelPseudo);
		GridBagLayout gbl_panelPseudo = new GridBagLayout();
		gbl_panelPseudo.columnWidths = new int[] {250, 442, 0};
		gbl_panelPseudo.rowHeights = new int[]{30, 0};
		gbl_panelPseudo.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPseudo.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelPseudo.setLayout(gbl_panelPseudo);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_7 = (FlowLayout) panel_6.getLayout();
		flowLayout_7.setHgap(55);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panelPseudo.add(panel_6, gbc_panel_6);
		
		JLabel pseudo = new JLabel("Pseudo");
		pseudo.setHorizontalAlignment(SwingConstants.CENTER);
		pseudo.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_6.add(pseudo);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_8 = (FlowLayout) panel_7.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panelPseudo.add(panel_7, gbc_panel_7);
		
		entreePseudo = new JTextField();
		entreePseudo.setToolTipText("");
		entreePseudo.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreePseudo.setColumns(20);
		panel_7.add(entreePseudo);
		
		JPanel panelDateN = new JPanel();
		panelDateN.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelDateN = new GridBagConstraints();
		gbc_panelDateN.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDateN.insets = new Insets(0, 0, 5, 0);
		gbc_panelDateN.gridx = 0;
		gbc_panelDateN.gridy = 4;
		panelModif.add(panelDateN, gbc_panelDateN);
		GridBagLayout gbl_panelDateN = new GridBagLayout();
		gbl_panelDateN.columnWidths = new int[] {250, 442, 0};
		gbl_panelDateN.rowHeights = new int[] {30, 0};
		gbl_panelDateN.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDateN.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDateN.setLayout(gbl_panelDateN);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
		flowLayout_4.setHgap(55);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panelDateN.add(panel_2, gbc_panel_2);
		
		JLabel dateNaissance = new JLabel("Date de naissance");
		dateNaissance.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_2.add(dateNaissance);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_9 = (FlowLayout) panel_3.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panelDateN.add(panel_3, gbc_panel_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_3.add(textField);
		textField.setColumns(20);
		
		JPanel panelNationalite = new JPanel();
		panelNationalite.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelNationalite = new GridBagConstraints();
		gbc_panelNationalite.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNationalite.insets = new Insets(0, 0, 5, 0);
		gbc_panelNationalite.gridx = 0;
		gbc_panelNationalite.gridy = 5;
		panelModif.add(panelNationalite, gbc_panelNationalite);
		GridBagLayout gbl_panelNationalite = new GridBagLayout();
		gbl_panelNationalite.columnWidths = new int[] {250, 442, 0};
		gbl_panelNationalite.rowHeights = new int[] {30, 0};
		gbl_panelNationalite.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelNationalite.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelNationalite.setLayout(gbl_panelNationalite);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_2 = (FlowLayout) panel_9.getLayout();
		flowLayout_2.setHgap(55);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 0;
		panelNationalite.add(panel_9, gbc_panel_9);
		
		JLabel nationalite = new JLabel("Nationalit\u00E9");
		nationalite.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_9.add(nationalite);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_10 = (FlowLayout) panel_10.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 1;
		gbc_panel_10.gridy = 0;
		panelNationalite.add(panel_10, gbc_panel_10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_10.add(textField_1);
		textField_1.setColumns(20);
		
		JPanel panelValider = new JPanel();
		panelValider.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraints();
		gbc_panelValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelValider.gridx = 0;
		gbc_panelValider.gridy = 6;
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
		// VALIDER OU ANNULER INFORMATIONS SUR LE TOURNOI
		btnAnnuler.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		
		
		// DECONNEXION
		btnDeconnexion.addActionListener(controleur);
		// GESTION DES TOURNOIS
		btnCreer.addActionListener(controleur);
		btnSupprimer.addActionListener(controleur);
		
		btnEcuries.addActionListener(controleur);
	}
	
	public static void afficherPanel(JPanel p) {
		p.setVisible(true);
	}
	
	public static void fermerFenetre(JFrame f) {
		f.setVisible(false);
	}
	
	public static void afficherTexte(JLabel l, String s) {
		l.setText(s);
	}
	
	public static void supprimerTexte(JTextField t) {
		t.setText(null);
	}
	
	public static Etat getEtat(JButton b) {
		if (b.getText() == "Créer une nouvelle équipe") {
			return Etat.CREER;
		} else if (b.getText() == "Annuler") {
			return Etat.ANNULER;
		} else if (b.getText() == "Se dÃ©connecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Supprimer l'équipe sélectionnée") {
			return Etat.SUPPRIMER;
		} else if (b.getText() == "Ecuries") {
			return Etat.ECURIE;
		} else if (b.getText() == "Valider") {
			return Etat.VALIDER;
		}		else if (b.getText() == "Calendrier") {
			return Etat.CALENDRIER;
		}
		else if (b.getText()=="Joueurs") {
			return Etat.JOUEURS;
		}
		
		return null;
	}
	
}