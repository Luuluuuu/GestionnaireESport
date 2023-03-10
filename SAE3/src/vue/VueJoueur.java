
package vue;

import java.awt.BorderLayout;import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import controleur.ControleurJoueur;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class VueJoueur implements Vue{
	
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
	private JTextField entreeNationalite;
	private JButton btnValider;
	private JPasswordField entreeMdp;
	private DefaultComboBoxModel<String> modeleEquipes = new DefaultComboBoxModel<String>();;
	private JComboBox<String> entreeEquipe;
	private JButton btnClassement;
	private JButton btnSupprimer;
	
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
		
		JPanel panelMenu = creerJPanel(panelHeader, Color.WHITE);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		if (ControleurJoueur.estProfil("Gestionnaire")) {
			JButton btnCalendrier = creerBouton(panelMenu, "Calendrier", Couleur.BLEU2, 15);
			btnCalendrier.addActionListener(controleur);
			
			JButton btnEcuries = creerBouton(panelMenu, "Ecuries / Responsables / Arbitres", Couleur.BLEU2, 15);
			btnEcuries.addActionListener(controleur);
		}
		
		JButton btnEquipes = creerBouton(panelMenu, "Equipes", Couleur.BLEU2, 15);
		panelMenu.add(btnEquipes);
		
		JButton btnJoueurs = creerBouton(panelMenu, "Joueurs", Couleur.BLEU2, 15);
		panelMenu.add(btnJoueurs);
		
		if (ControleurJoueur.estProfil("Ecurie")) {
			JButton btnTournois = creerBouton(panelMenu, "Tournois", Couleur.BLEU2, 15);
			btnTournois.addActionListener(controleur);
		}
		
		btnClassement = creerBouton(panelMenu, "Classement", Couleur.BLEU2, 15);
		
		JPanel panelDeconnexion = creerJPanel(panelHeader, Color.WHITE);
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		
		JButton btnDeconnexion = creerBouton(panelDeconnexion, "Se déconnecter", Couleur.ROUGE, 13);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreJoueur.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelJoueur = creerJPanel(panelContenu, Couleur.BLEU1);
		GridBagLayout gbl_panelJoueur = new GridBagLayout();
		gbl_panelJoueur.columnWidths = new int[]{692, 0};
		gbl_panelJoueur.rowHeights = new int[] {100, 622, 100, 0};
		gbl_panelJoueur.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelJoueur.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelJoueur.setLayout(gbl_panelJoueur);
		
		JPanel panelTitreT = creerJPanel(panelJoueur, Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreT = new GridBagConstraints();
		gbc_panelTitreT.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreT.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreT.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreT.gridx = 0;
		gbc_panelTitreT.gridy = 0;
		panelJoueur.add(panelTitreT, gbc_panelTitreT);
		panelTitreT.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTitre = creerJPanel(panelTitreT, Couleur.BLEU1);
		FlowLayout flowLayout_3 = (FlowLayout) panelTitre.getLayout();
		flowLayout_3.setHgap(50);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		
		// LISTE DES TOURNOIS //
		JLabel Joueurs = creerJLabel(panelTitre, "Joueurs", 36);
		Joueurs.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panelRecherche = creerJPanel(panelTitreT, Couleur.BLEU1);
		
		recherche = creerJTextField(panelRecherche, 13, 15);
		
		JButton btnRechercher = creerBouton(panelRecherche, "Rechercher", Couleur.BLEU2, 13);
		
		JPanel panelListe = creerJPanel(panelJoueur, Couleur.BLEU1);
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
		
		JPanel panelBoutons = creerJPanel(panelJoueur, Couleur.BLEU1);
		GridBagConstraints gbc_panelBoutons = new GridBagConstraints();
		gbc_panelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBoutons.gridx = 0;
		gbc_panelBoutons.gridy = 2;
		panelJoueur.add(panelBoutons, gbc_panelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		JButton btnCreer = creerBouton(panelBoutons, "Créer un nouveau joueur", Couleur.BLEU2, 13);
		
		btnSupprimer = creerBouton(panelBoutons, "Supprimer le joueur sélectionné", Couleur.GRIS, 13);
		this.desactiverBouton(btnSupprimer);
		
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
		
		JPanel panelTitreM = creerJPanel(panelModif, Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreM = new GridBagConstraints();
		gbc_panelTitreM.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreM.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreM.gridx = 0;
		gbc_panelTitreM.gridy = 0;
		panelModif.add(panelTitreM, gbc_panelTitreM);
		panelTitreM.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelT = creerJPanel(panelTitreM, Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panelT.getLayout();
		flowLayout_1.setVgap(80);
		flowLayout_1.setHgap(40);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		titreModif = creerJLabel(panelT, "Créer un joueur", 30);
		
		JPanel panelPhoto = creerJPanel(panelTitreM, Couleur.BLEU1);
		panelPhoto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton photoBouton = creerBouton(panelPhoto, "Choisir une photo", Couleur.BLEU2, 13);
		photoBouton.addActionListener(controleur);
		
		photo = creerJLabel(panelPhoto, "", 11);
		photo.setIcon(new ImageIcon(img)); //Image affichée a cotée
		
		JPanel panelEquipe = creerJPanel(panelModif, Couleur.BLEU1);
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
		
		JPanel panelLabelEquipe = creerJPanel(panelEquipe, Couleur.BLEU1);
		FlowLayout flowLayout_11 = (FlowLayout) panelLabelEquipe.getLayout();
		flowLayout_11.setHgap(55);
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.insets = new Insets(0, 0, 0, 5);
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 0;
		panelEquipe.add(panelLabelEquipe, gbc_panel_8);
		
		// EQUIPE
		creerJLabel(panelLabelEquipe, "Equipe", 14);
		
		JPanel panelTexteEquipe = creerJPanel(panelEquipe, Couleur.BLEU1);
		FlowLayout flowLayout_12 = (FlowLayout) panelTexteEquipe.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 1;
		gbc_panel_11.gridy = 0;
		panelEquipe.add(panelTexteEquipe, gbc_panel_11);
		
		entreeEquipe = new JComboBox<String>(modeleEquipes);
		entreeEquipe.setPreferredSize(new Dimension(205, 20));
		entreeEquipe.setFont(new Font("Roboto", Font.PLAIN, 11));
		panelTexteEquipe.add(entreeEquipe);
		
		JPanel panelNom = creerJPanel(panelModif, Couleur.BLEU1);
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
		
		JPanel panelLabelNom = creerJPanel(panelNom, Couleur.BLEU1);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelNom.add(panelLabelNom, gbc_panel);
		panelLabelNom.setLayout(new FlowLayout(FlowLayout.LEFT, 55, 5));
		
		creerJLabel(panelLabelNom, "Nom", 14);
		
		JPanel panelTexteNom = creerJPanel(panelNom, Couleur.BLEU1);
		FlowLayout flowLayout = (FlowLayout) panelTexteNom.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panelNom.add(panelTexteNom, gbc_panel_1);
		
		entreeNom = creerJTextField(panelTexteNom, 11, 20);
		
		JPanel panelPrenom = creerJPanel(panelModif, Couleur.BLEU1);
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
		
		JPanel panelLabelPrenom = creerJPanel(panelPrenom, Couleur.BLEU1);
		FlowLayout flowLayout_5 = (FlowLayout) panelLabelPrenom.getLayout();
		flowLayout_5.setHgap(55);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panelPrenom.add(panelLabelPrenom, gbc_panel_4);
		
		creerJLabel(panelLabelPrenom, "Prénom", 14);
		
		JPanel panelTextePrenom = creerJPanel(panelPrenom, Couleur.BLEU1);
		FlowLayout flowLayout_6 = (FlowLayout) panelTextePrenom.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 0;
		panelPrenom.add(panelTextePrenom, gbc_panel_5);
		
		entreePrenom = creerJTextField(panelTextePrenom, 11, 20);
		
		JPanel panelPseudo = creerJPanel(panelModif, Couleur.BLEU1);
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
		
		JPanel panelLabelPseudo = creerJPanel(panelPseudo, Couleur.BLEU1);
		FlowLayout flowLayout_7 = (FlowLayout) panelLabelPseudo.getLayout();
		flowLayout_7.setHgap(55);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panelPseudo.add(panelLabelPseudo, gbc_panel_6);
		
		creerJLabel(panelLabelPseudo, "Pseudo", 14);
		
		JPanel panelTextePseudo = creerJPanel(panelPseudo, Couleur.BLEU1);
		FlowLayout flowLayout_8 = (FlowLayout) panelTextePseudo.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panelPseudo.add(panelTextePseudo, gbc_panel_7);
		
		entreePseudo = creerJTextField(panelTextePseudo, 11, 20);
		
		JPanel panelDateN = creerJPanel(panelModif, Couleur.BLEU1);
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
		
		JPanel panelLabelDateN = creerJPanel(panelDateN, Couleur.BLEU1);
		FlowLayout flowLayout_4 = (FlowLayout) panelLabelDateN.getLayout();
		flowLayout_4.setHgap(55);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panelDateN.add(panelLabelDateN, gbc_panel_2);
		
		creerJLabel(panelLabelDateN, "Date de naissance", 14);
		
		JPanel panelEntreeDateN = creerJPanel(panelDateN, Couleur.BLEU1);
		FlowLayout flowLayout_9 = (FlowLayout) panelEntreeDateN.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panelDateN.add(panelEntreeDateN, gbc_panel_3);
		entreeDateNaissance.getComponentDateTextField().setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeDateNaissance.getComponentToggleCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		
		entreeDateNaissance.setFont(new Font("Roboto", Font.PLAIN, 11));
		panelEntreeDateN.add(entreeDateNaissance);
		entreeDateNaissance.setPreferredSize(new Dimension(205,20));
		paramDate.setAllowEmptyDates(false);
		paramDate.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		JPanel panelNationalite = creerJPanel(panelModif, Couleur.BLEU1);
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
		
		JPanel panelLabelNationalite = creerJPanel(panelNationalite, Couleur.BLEU1);
		FlowLayout flowLayout_2 = (FlowLayout) panelLabelNationalite.getLayout();
		flowLayout_2.setHgap(55);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 0;
		panelNationalite.add(panelLabelNationalite, gbc_panel_9);
		
		creerJLabel(panelLabelNationalite, "Nationalité", 14);
		
		JPanel panelTexteNationalite = creerJPanel(panelNationalite, Couleur.BLEU1);
		FlowLayout flowLayout_10 = (FlowLayout) panelTexteNationalite.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 1;
		gbc_panel_10.gridy = 0;
		panelNationalite.add(panelTexteNationalite, gbc_panel_10);
		
		entreeNationalite = creerJTextField(panelTexteNationalite, 11, 20);
		
		JPanel panelMdp = creerJPanel(panelModif, Couleur.BLEU1);
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
		
		JPanel panelLabelMdp = creerJPanel(panelMdp, Couleur.BLEU1);
		FlowLayout fl_panel_12 = (FlowLayout) panelLabelMdp.getLayout();
		fl_panel_12.setHgap(55);
		fl_panel_12.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.insets = new Insets(0, 0, 0, 5);
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 0;
		panelMdp.add(panelLabelMdp, gbc_panel_12);
		
		creerJLabel(panelLabelMdp, "Mot de passe", 14);
		
		JPanel panelTexteMdp = creerJPanel(panelMdp, Couleur.BLEU1);
		FlowLayout flowLayout_13 = (FlowLayout) panelTexteMdp.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.gridx = 1;
		gbc_panel_13.gridy = 0;
		panelMdp.add(panelTexteMdp, gbc_panel_13);
		
		entreeMdp = new JPasswordField();
		entreeMdp.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeMdp.setColumns(20);
		panelTexteMdp.add(entreeMdp);
		
		JPanel panelValider = creerJPanel(panelModif, Couleur.BLEU1);
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraints();
		gbc_panelValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelValider.gridx = 0;
		gbc_panelValider.gridy = 8;
		panelModif.add(panelValider, gbc_panelValider);
		
		btnValider = creerBouton(panelValider, "Valider", Couleur.VERT, 13);
		btnValider.setName("btnValider");
		
		JButton btnAnnuler = creerBouton(panelValider, "Annuler", Couleur.GRIS, 13);
		
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
	public JButton getBtnSupprimer() {
		return this.btnSupprimer;
	}
	
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

	public String getPhoto() {
		return this.photo.getText();
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
	
    public void setPhotoJoueur(String chemin) {
       	 ImageIcon imageIcon = new ImageIcon(chemin);
       	 Image image = imageIcon.getImage();
       	 Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
       	 imageIcon = new ImageIcon(resizedImage);
       	 photo.setIcon(imageIcon);
       	 
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
	
	public void premierIndexEquipe() {
        this.entreeEquipe.setSelectedIndex(0);
    }
	
	public void annulerEntreeJoueur() {
        this.deselectionner();
        VueJoueur.afficherPanel(panelModif);
        VueJoueur.afficherTexte(this.titreModif, "Créer un joueur");
        VueJoueur.supprimerTexte(this.entreeNom);
        VueJoueur.supprimerTexte(this.entreePrenom);
        VueJoueur.supprimerTexte(this.entreeNationalite);
        VueJoueur.supprimerTexte(this.entreePseudo);
        VueJoueur.supprimerTexte(this.entreeMdp);
        this.premierIndexEquipe();
        entreeEquipe.setForeground(new Color(0,0,0));
    }
	
	public void activerBouton(JButton j) {
        j.setEnabled(true);
    }
	
	public void desactiverBouton(JButton j) {
        j.setEnabled(false);
    }
	
	public Etat getEtat(JButton b) {
		if (b.getText() == "Créer un nouveau joueur") {
			return Etat.CREER;
		} else if (b.getText() == "Annuler") {
			return Etat.ANNULER;
		} else if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Supprimer le joueur sélectionné") {
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
			this.desactiverBouton(btnClassement);
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
		VueJoueur.afficherTexte(this.titreModif, "Créer un joueur");
		VueJoueur.supprimerTexte(this.entreeNom);
		VueJoueur.supprimerTexte(this.entreePrenom);
		VueJoueur.supprimerTexte(this.entreePseudo);
		entreeDateNaissance.setDate(null);
		VueJoueur.supprimerTexte(this.entreeNationalite);
		this.entreeEquipe.setSelectedItem("- Sélectionnez une �quipe -");
	}

	public void estVide() {
        JOptionPane.showMessageDialog(null, "Veuillez compléter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
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