package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;


import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controleur.ControleurJoueur.Etat;
import modele.Utilisateur.Profil;
import controleur.ControleurConnexion;
import controleur.ControleurJoueur;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class VueJoueur extends JFrame{
	
	public JFrame fenetreJoueur;
	public JTextField entreePrenom;
	public JPanel panelModif;
	public JLabel photo;
	public JLabel titreModif;
	private DefaultListModel<String> modeleJoueurs = new DefaultListModel<String>();;
	private JList<String> listeJoueurs;
	private JTextField recherche;
	private JTextField entreeNom;
	private JTextField entreePseudo;
	private DatePickerSettings paramDate = new DatePickerSettings();
	private DatePicker entreeDateNaissance = new DatePicker(paramDate);
	private JTextField entreeNationalite = new JTextField();
	private JButton btnValider = new JButton("Valider");
	private JPasswordField entreeMdp;
	private DefaultComboBoxModel<String> modeleEquipes = new DefaultComboBoxModel<String>();;
	private JComboBox<String> entreeEquipe;
	
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
		
		Image img = new ImageIcon(this.getClass().getResource("photo.jpg")).getImage();
		
		// HEADER //
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fenetreJoueur.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelHeader.add(panelMenu);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		if (ControleurConnexion.profilUtilisateur == Profil.GESTIONNAIRE) {
			JButton btnCalendrier = new JButton("Calendrier");
			btnCalendrier.setForeground(Color.WHITE);
			btnCalendrier.setFont(new Font("Roboto", Font.BOLD, 15));
			btnCalendrier.setBackground(Couleur.BLEU2);
			panelMenu.add(btnCalendrier);
			btnCalendrier.addActionListener(controleur);
			
			JButton btnEcuries = new JButton("Ecuries / Responsables / Arbitres");
			btnEcuries.setForeground(Color.WHITE);
			btnEcuries.setFont(new Font("Roboto", Font.BOLD, 15));
			btnEcuries.setBackground(Couleur.BLEU2);
			panelMenu.add(btnEcuries);
			btnEcuries.addActionListener(controleur);
		}
		
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
		
		if (ControleurConnexion.profilUtilisateur == Profil.ECURIE) {
			JButton btnTournois = new JButton("Tournois");
			btnTournois.setForeground(Color.WHITE);
			btnTournois.setFont(new Font("Roboto", Font.BOLD, 15));
			btnTournois.setBackground(Couleur.BLEU2);
			panelMenu.add(btnTournois);
			btnTournois.addActionListener(controleur);
		}
		
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
		
		JPanel panelJoueur = new JPanel();
		panelJoueur.setBackground(Couleur.BLEU1);
		panelContenu.add(panelJoueur);
		GridBagLayout gbl_panelJoueur = new GridBagLayout();
		gbl_panelJoueur.columnWidths = new int[]{692, 0};
		gbl_panelJoueur.rowHeights = new int[] {100, 622, 100, 0};
		gbl_panelJoueur.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelJoueur.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelJoueur.setLayout(gbl_panelJoueur);
		
		JPanel panelTitreT = new JPanel();
		panelTitreT.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreT = new GridBagConstraints();
		gbc_panelTitreT.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreT.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreT.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreT.gridx = 0;
		gbc_panelTitreT.gridy = 0;
		panelJoueur.add(panelTitreT, gbc_panelTitreT);
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
		btnRechercher.setText("Rechercher");
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
		panelJoueur.add(panelListe, gbc_panelListe);
		
		listeJoueurs = new JList<String>(modeleJoueurs);
		listeJoueurs.setVisibleRowCount(12);
		listeJoueurs.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeJoueurs.setFixedCellHeight(50);
		listeJoueurs.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(this.listeJoueurs);
		panelListe.add(scrollPane);
		
		JPanel panelBoutons = new JPanel();
		panelBoutons.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelBoutons = new GridBagConstraints();
		gbc_panelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBoutons.gridx = 0;
		gbc_panelBoutons.gridy = 2;
		panelJoueur.add(panelBoutons, gbc_panelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		JButton btnCreer = new JButton("Cr�er un nouveau joueur");
		btnCreer.setForeground(Color.WHITE);
		btnCreer.setFont(new Font("Roboto", Font.BOLD, 13));
		btnCreer.setBackground(Couleur.BLEU2);
		panelBoutons.add(btnCreer);
		
		JButton btnSupprimer = new JButton("Supprimer le joueur s�lectionn�");
		btnSupprimer.setText("Supprimer le joueur s�lectionn�");
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
		gbl_panelModif.rowHeights = new int[] {234, 70, 70, 70, 70, 70, 70, 70, 100, 0};
		gbl_panelModif.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelModif.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelModif.setLayout(gbl_panelModif);
		
		JPanel panelTitreM = new JPanel();
		panelTitreM.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreM = new GridBagConstraints();
		gbc_panelTitreM.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreM.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreM.gridx = 0;
		gbc_panelTitreM.gridy = 0;
		panelModif.add(panelTitreM, gbc_panelTitreM);
		panelTitreM.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelT = new JPanel();
		panelT.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panelT.getLayout();
		flowLayout_1.setVgap(80);
		flowLayout_1.setHgap(40);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelTitreM.add(panelT);
		
		titreModif = new JLabel("Cr�er un joueur");
		titreModif.setForeground(Color.WHITE);
		panelT.add(titreModif);
		titreModif.setFont(new Font("Roboto", Font.BOLD, 20));
		
		JPanel panelPhoto = new JPanel();
		panelPhoto.setBackground(Couleur.BLEU1);
		panelTitreM.add(panelPhoto);
		
		photo = new JLabel("");
		panelPhoto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton photoBouton = new JButton("Choisir une photo");
		
		photoBouton.addActionListener(controleur);
		panelPhoto.add(photoBouton);
		photo.setIcon(new ImageIcon(img)); //Image affichée a cotée
		panelPhoto.add(photo);
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelEquipe = new GridBagConstraints();
		gbc_panelEquipe.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelEquipe.insets = new Insets(0, 0, 5, 0);
		gbc_panelEquipe.gridx = 0;
		gbc_panelEquipe.gridy = 1;
		panelModif.add(panelEquipe, gbc_panelEquipe);
		GridBagLayout gbl_panelEquipe = new GridBagLayout();
		gbl_panelEquipe.columnWidths = new int[] {250, 442, 0};
		gbl_panelEquipe.rowHeights = new int[] {30, 0};
		gbl_panelEquipe.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEquipe.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelEquipe.setLayout(gbl_panelEquipe);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_11 = (FlowLayout) panel_8.getLayout();
		flowLayout_11.setHgap(55);
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.insets = new Insets(0, 0, 0, 5);
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 0;
		panelEquipe.add(panel_8, gbc_panel_8);
		
		// EQUIPE
		JLabel equipe = new JLabel("Equipe");
		equipe.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_8.add(equipe);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_12 = (FlowLayout) panel_11.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 1;
		gbc_panel_11.gridy = 0;
		panelEquipe.add(panel_11, gbc_panel_11);
		
		entreeEquipe = new JComboBox<String>(modeleEquipes);
		entreeEquipe.setPreferredSize(new Dimension(205, 20));
		entreeEquipe.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_11.add(entreeEquipe);
		
		JPanel panelNom = new JPanel();
		panelNom.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelNom = new GridBagConstraints();
		gbc_panelNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNom.insets = new Insets(0, 0, 5, 0);
		gbc_panelNom.gridx = 0;
		gbc_panelNom.gridy = 2;
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
		gbc_panelPrenom.gridy = 3;
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
		
		JLabel prenom = new JLabel("Pr�nom");
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
		gbc_panelPseudo.gridy = 4;
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
		gbc_panelDateN.gridy = 5;
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
		entreeDateNaissance.getComponentDateTextField().setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeDateNaissance.getComponentToggleCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		
		entreeDateNaissance.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_3.add(entreeDateNaissance);
		entreeDateNaissance.setPreferredSize(new Dimension(205,20));
		paramDate.setAllowEmptyDates(false);
		paramDate.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		JPanel panelNationalite = new JPanel();
		panelNationalite.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelNationalite = new GridBagConstraints();
		gbc_panelNationalite.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNationalite.insets = new Insets(0, 0, 5, 0);
		gbc_panelNationalite.gridx = 0;
		gbc_panelNationalite.gridy = 6;
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
		
		JLabel nationalite = new JLabel("Nationalit�");
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
		
		entreeNationalite.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_10.add(entreeNationalite);
		entreeNationalite.setColumns(20);
		
		JPanel panelMdp = new JPanel();
		panelMdp.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelMdp = new GridBagConstraints();
		gbc_panelMdp.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelMdp.insets = new Insets(0, 0, 5, 0);
		gbc_panelMdp.gridx = 0;
		gbc_panelMdp.gridy = 7;
		panelModif.add(panelMdp, gbc_panelMdp);
		GridBagLayout gbl_panelMdp = new GridBagLayout();
		gbl_panelMdp.columnWidths = new int[] {250, 442, 0};
		gbl_panelMdp.rowHeights = new int[] {30, 0};
		gbl_panelMdp.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMdp.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelMdp.setLayout(gbl_panelMdp);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Couleur.BLEU1);
		FlowLayout fl_panel_12 = (FlowLayout) panel_12.getLayout();
		fl_panel_12.setHgap(55);
		fl_panel_12.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.insets = new Insets(0, 0, 0, 5);
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 0;
		panelMdp.add(panel_12, gbc_panel_12);
		
		JLabel mdp = new JLabel("Mot de passe");
		mdp.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_12.add(mdp);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_13 = (FlowLayout) panel_13.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.gridx = 1;
		gbc_panel_13.gridy = 0;
		panelMdp.add(panel_13, gbc_panel_13);
		
		entreeMdp = new JPasswordField();
		entreeMdp.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeMdp.setColumns(20);
		panel_13.add(entreeMdp);
		
		JPanel panelValider = new JPanel();
		panelValider.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraints();
		gbc_panelValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelValider.gridx = 0;
		gbc_panelValider.gridy = 8;
		panelModif.add(panelValider, gbc_panelValider);
		
		btnValider.setForeground(Color.WHITE);
		btnValider.setBackground(Couleur.VERT);
		btnValider.setFont(new Font("Roboto", Font.BOLD, 13));
		btnValider.setText("Valider");
		panelValider.add(btnValider);
		btnValider.setName("btnValider");
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Roboto", Font.BOLD, 13));
		btnAnnuler.setBackground(Couleur.GRIS);
		panelValider.add(btnAnnuler);
		
		// VALIDER OU ANNULER INFORMATIONS SUR LE JOUEUR
		btnAnnuler.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		btnCreer.addActionListener(controleur);
		btnRechercher.addActionListener(controleur);
		btnEquipes.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
		// DECONNEXION
		btnDeconnexion.addActionListener(controleur);
		// GESTION DES JOUEURS
		this.listeJoueurs.addListSelectionListener((ListSelectionListener) controleur);
		btnSupprimer.addActionListener(controleur);
	}
	
	//JOUEUR
	public void ajouterJoueur(String j) {
		this.modeleJoueurs.addElement(j);
	}
	
	public void modifierJoueur() {
		this.modeleJoueurs.set(this.listeJoueurs.getSelectedIndex(),this.getPrenom()+" ("+this.getPseudo()+") "+this.getNom());	
	}
	
	public void supprimerJoueur() {
		this.modeleJoueurs.removeElement(this.getJoueurSelectionne());
		this.deselectionner();
    }
	
	// GETTERS //
	public String getJoueurSelectionne() {
		return this.listeJoueurs.getSelectedValue();
	}
	
	public String getTextRecherche() {
		return recherche.getText();
	}
	
	public String getNom() {
		return this.entreeNom.getText();
	}
	
	public String getPrenom() {
		return this.entreePrenom.getText();
	}
	
	public String getPseudo() {
		return this.entreePseudo.getText();
	}
	
	public String getDateNaissance() {
		String date = entreeDateNaissance.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return date;
	}
	
	public String getNationalite() {
		return this.entreeNationalite.getText();
	}
	
	// SETTER //
	public void setNomJoueur(String j) {
		this.entreeNom.setText(j);
	}
	
	public void setPrenomJoueur(String j) {
		this.entreePrenom.setText(j);
	}
	
	public void setPseudoJoueur(String j) {
		this.entreePseudo.setText(j);
	}
	
	public void setDateNaissanceJoueur(String j) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(j, formatter);
        this.entreeDateNaissance.setDate(date);
	}
	
	public void setNationaliteJoueur(String j) {
		this.entreeNationalite.setText(j);
	}
	
	public void setDefaultListModel() {
		this.listeJoueurs.setModel(modeleJoueurs);
	}
	
	// FENETRE //
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
	
	//FILTRE
	public void filtrerRecherche() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleJoueurs.size(); i++) {
	    	if (this.modeleJoueurs.get(i).contains(this.recherche.getText())){
	    		modeleFiltre.addElement(this.modeleJoueurs.get(i));
	    	}
	    }
	    this.listeJoueurs.setModel(modeleFiltre);
	}
	
	public int confirmerSuppression() {
		return JOptionPane.showConfirmDialog(null, "Confirmez-vous la suppression ?","Confirmation",JOptionPane.YES_NO_OPTION);
	}
	
	public Etat getEtat(JButton b) {
		if (b.getText() == "Cr�er un nouveau joueur") {
			return Etat.CREER;
		} else if (b.getText() == "Annuler") {
			return Etat.ANNULER;
		} else if (b.getText() == "Se d�connecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Supprimer le joueur s�lectionn�") {
			return Etat.SUPPRIMER;
		} else if (b.getText() == "Ecuries / Responsables / Arbitres") {
			return Etat.ECURIE; 
		} else if (b.getText() == "Valider") {
			return Etat.VALIDER;
		}		else if (b.getText() == "Calendrier") {
			return Etat.CALENDRIER;
		}  else if (b.getText()=="Joueurs") {
			return Etat.JOUEURS;
		}  else if (b.getText()=="Tournois") {
				return Etat.TOURNOIS;
		}else if (b.getText()=="Rechercher") {
			return Etat.RECHERCHER;
		}else if (b.getText()=="Equipes") {
			return Etat.EQUIPES;
		}else if (b.getText()=="Choisir une photo") {
			return Etat.PHOTO;
		} else if (b.getText()=="Classement") {
			return Etat.CLASSEMENT;
		}
		
		return null;
	}

	private void deselectionner() {
		this.listeJoueurs.clearSelection();
	}	
	
	public void creerJoueur() {
		this.deselectionner();
		VueJoueur.afficherPanel(panelModif);
		VueJoueur.afficherTexte(this.titreModif, "Cr�er un joueur");
		VueJoueur.supprimerTexte(this.entreeNom);
		VueJoueur.supprimerTexte(this.entreePrenom);
		VueJoueur.supprimerTexte(this.entreePseudo);
		entreeDateNaissance.setDate(null);
		VueJoueur.supprimerTexte(this.entreeNationalite);
		this.entreeEquipe.setSelectedItem("- S�lectionnez une �quipe -");
	}

	public void estVide() {
        JOptionPane.showMessageDialog(null, "Veuillez compl�ter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mauvaiseDate() {
		JOptionPane.showMessageDialog(null, "La date n'est pas valide ! Le joueur doit avoir plus de 16 ans.", "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public String getNomEquipe() {
		return (String) this.entreeEquipe.getSelectedItem();
	}

	public void setEquipe(String nom) {
		this.entreeEquipe.setSelectedItem(nom);
	}

	public void ajouterEquipe(String nomEquipe) {
		this.modeleEquipes.addElement(nomEquipe);
	}

	public String getMotDePasse() {
		return String.valueOf(this.entreeMdp.getPassword());
	}

	public void viderMotDePasse() {
		this.entreeMdp.setText("");
	}
}
