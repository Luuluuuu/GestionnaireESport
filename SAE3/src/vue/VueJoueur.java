
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

import modele.EtatFactory;
import modele.GridBagConstraintsBuilder;
import modele.GridBagLayoutBuilder;
import modele.JButtonBuilder;
import modele.JLabelBuilder;
import modele.JPanelBuilder;
import modele.JTextFieldBuilder;
import modele.Etat;
import controleur.ControleurJoueur;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class VueJoueur {
	
	public JFrame fenetreJoueur;
	public JPanel panelModif;
	
	public JLabel photo;
	public JLabel titreModif;
	
	private JTextField recherche;
	private JTextField entreeNom;
	public JTextField entreePrenom;
	private JTextField entreePseudo;
	private JTextField entreeNationalite;
	private JPasswordField entreeMdp;
	
	private DatePickerSettings paramDate = new DatePickerSettings();
	private DatePicker entreeDateNaissance = new DatePicker(paramDate);
		
	private DefaultListModel<String> modeleJoueurs = new DefaultListModel<String>();;
	private JList<String> listeJoueurs;
	private DefaultComboBoxModel<String> modeleEquipes = new DefaultComboBoxModel<String>();;
	private JComboBox<String> entreeEquipe;

	private JButton btnSupprimer;
	private JButton btnRechercher;
	private HeaderAdmin headerA;
	private HeaderEcurie header;
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
		// Boutons du Header
		if (ControleurJoueur.estProfil("Gestionnaire")) {
			headerA = new HeaderAdmin(this.getFrame());
			headerA.getBtnDeconnexion().addActionListener(controleur);
			headerA.getBtnEquipes().addActionListener(controleur);
			headerA.getBtnCalendrier().addActionListener(controleur);
			headerA.getBtnEcuries().addActionListener(controleur);
			headerA.getBtnClassement().addActionListener(controleur);
			Vue.desactiverBouton(headerA.getBtnJoueurs());
		}
		
		if (ControleurJoueur.estProfil("Ecurie")) {
			header = new HeaderEcurie(this.getFrame());
			header.getBtnDeconnexion().addActionListener(controleur);
			header.getBtnEquipes().addActionListener(controleur);
			header.getBtnTournois().addActionListener(controleur);
			header.getBtnClassement().addActionListener(controleur);
			Vue.desactiverBouton(header.getBtnJoueurs());
		}
		
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreJoueur.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelJoueur = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		GridBagLayout gblPanelJoueur = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {100, 622, 100, 0},
										new double[]{1.0, Double.MIN_VALUE},new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE}).build();
		panelJoueur.setLayout(gblPanelJoueur);
		
		JPanel panelTitreT = new JPanelBuilder(panelJoueur).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelTitreT = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelJoueur.add(panelTitreT, gbcPanelTitreT);
		panelTitreT.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTitre = new JPanelBuilder(panelTitreT).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout3 = (FlowLayout) panelTitre.getLayout();
		flowLayout3.setHgap(50);
		flowLayout3.setAlignment(FlowLayout.LEFT);
		
		// LISTE DES TOURNOIS //
		JLabel Joueurs = new JLabelBuilder(panelTitre).setCustomLabel("Joueurs", new Font("Roboto", Font.BOLD, 36), Color.WHITE).build();
		Joueurs.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panelRecherche = new JPanelBuilder(panelTitreT).setCustomPanel(Couleur.BLEU1).build();
		
		recherche = new JTextFieldBuilder(panelRecherche).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 15).build();
		
		btnRechercher = new JButtonBuilder(panelRecherche).setCustomButton("Rechercher", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		Vue.desactiverBouton(btnRechercher);
		rechercheEquipe(recherche);
		
		JPanel panelListe = new JPanelBuilder(panelJoueur).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListe = (FlowLayout) panelListe.getLayout();
		flPanelListe.setHgap(50);
		flPanelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelListe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 1).build();
		panelJoueur.add(panelListe, gbcPanelListe);
		
		listeJoueurs = new JList<String>(modeleJoueurs);
		listeJoueurs.setVisibleRowCount(12);
		listeJoueurs.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeJoueurs.setFixedCellHeight(50);
		listeJoueurs.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(this.listeJoueurs);
		panelListe.add(scrollPane);
		
		JPanel panelBoutons = new JPanelBuilder(panelJoueur).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 2).build();
		panelJoueur.add(panelBoutons, gbcPanelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		JButton btnCreer = new JButtonBuilder(panelBoutons).setCustomButton("Créer un nouveau joueur", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		
		btnSupprimer = new JButtonBuilder(panelBoutons).setCustomButton("Supprimer le joueur sélectionné", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimer);
		
		// CREER OU MODIFIER UN TOURNOI
		panelModif = new JPanel();
		panelModif.setBackground(Couleur.BLEU1);
		panelContenu.add(panelModif);
		GridBagLayout gblPanelModif = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {234, 70, 70, 70, 70, 70, 70, 70, 100, 0},
										new double[]{0.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE}).build();
		panelModif.setLayout(gblPanelModif);
		
		JPanel panelTitreM = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelTitreM = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelModif.add(panelTitreM, gbcPanelTitreM);
		panelTitreM.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelT = new JPanelBuilder(panelTitreM).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout1 = (FlowLayout) panelT.getLayout();
		flowLayout1.setVgap(80);
		flowLayout1.setHgap(40);
		flowLayout1.setAlignment(FlowLayout.LEFT);
		
		titreModif = new JLabelBuilder(panelT).setCustomLabel("Créer un joueur", new Font("Roboto", Font.BOLD, 30), Color.WHITE).build();
		
		JPanel panelPhoto = new JPanelBuilder(panelTitreM).setCustomPanel(Couleur.BLEU1).build();
		panelPhoto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnPhoto = new JButtonBuilder(panelPhoto).setCustomButton("Choisir une photo", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		btnPhoto.addActionListener(controleur);
		
		photo = new JLabelBuilder(panelPhoto).setCustomLabel( "", new Font("Roboto", Font.BOLD, 11), Color.WHITE).build();
		photo.setIcon(new ImageIcon(img)); //Image affichée a cotée
		
		JPanel panelEquipe = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelEquipe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 1).build();
		panelModif.add(panelEquipe, gbcPanelEquipe);
		GridBagLayout gblPanelEquipe = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[] {30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelEquipe.setLayout(gblPanelEquipe);
		
		JPanel panelLabelEquipe = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout11 = (FlowLayout) panelLabelEquipe.getLayout();
		flowLayout11.setHgap(55);
		flowLayout11.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel8 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelEquipe.add(panelLabelEquipe, gbcPanel8);
		
		// EQUIPE
		new JLabelBuilder(panelLabelEquipe).setCustomLabel("Equipe", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelTexteEquipe = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout12 = (FlowLayout) panelTexteEquipe.getLayout();
		flowLayout12.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel11 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelEquipe.add(panelTexteEquipe, gbcPanel11);
		
		entreeEquipe = new JComboBox<String>(modeleEquipes);
		entreeEquipe.setPreferredSize(new Dimension(205, 20));
		entreeEquipe.setFont(new Font("Roboto", Font.PLAIN, 11));
		panelTexteEquipe.add(entreeEquipe);
		
		JPanel panelNom = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelNom = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 2).build();
		panelModif.add(panelNom, gbcPanelNom);
		GridBagLayout gblPanelNom = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[] {30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelNom.setLayout(gblPanelNom);
		
		JPanel panelLabelNom = new JPanelBuilder(panelNom).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanel = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelNom.add(panelLabelNom, gbcPanel);
		panelLabelNom.setLayout(new FlowLayout(FlowLayout.LEFT, 55, 5));
		
		new JLabelBuilder(panelLabelNom).setCustomLabel("Nom", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelTexteNom = new JPanelBuilder(panelNom).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout = (FlowLayout) panelTexteNom.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel1 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelNom.add(panelTexteNom, gbcPanel1);
		
		entreeNom = new JTextFieldBuilder(panelTexteNom).setCustomTextField(new Font("Roboto", Font.PLAIN, 11), 20).build();
		
		JPanel panelPrenom = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelPrenom = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 3).build();
		panelModif.add(panelPrenom, gbcPanelPrenom);
		GridBagLayout gblPanelPrenom = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[] {30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelPrenom.setLayout(gblPanelPrenom);
		
		JPanel panelLabelPrenom = new JPanelBuilder(panelPrenom).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout5 = (FlowLayout) panelLabelPrenom.getLayout();
		flowLayout5.setHgap(55);
		flowLayout5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel4 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelPrenom.add(panelLabelPrenom, gbcPanel4);
		
		new JLabelBuilder(panelLabelPrenom).setCustomLabel("Prénom", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelTextePrenom = new JPanelBuilder(panelPrenom).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout6 = (FlowLayout) panelTextePrenom.getLayout();
		flowLayout6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel5 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelPrenom.add(panelTextePrenom, gbcPanel5);
		
		entreePrenom = new JTextFieldBuilder(panelTextePrenom).setCustomTextField(new Font("Roboto", Font.PLAIN, 11), 20).build();
		
		JPanel panelPseudo = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelPseudo = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 4).build();
		panelModif.add(panelPseudo, gbcPanelPseudo);
		GridBagLayout gblPanelPseudo = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[] {30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelPseudo.setLayout(gblPanelPseudo);
		
		JPanel panelLabelPseudo = new JPanelBuilder(panelPseudo).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout7 = (FlowLayout) panelLabelPseudo.getLayout();
		flowLayout7.setHgap(55);
		flowLayout7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel6 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelPseudo.add(panelLabelPseudo, gbcPanel6);
		
		new JLabelBuilder(panelLabelPseudo).setCustomLabel("Pseudo", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelTextePseudo = new JPanelBuilder(panelPseudo).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout8 = (FlowLayout) panelTextePseudo.getLayout();
		flowLayout8.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel7 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelPseudo.add(panelTextePseudo, gbcPanel7);
		
		entreePseudo = new JTextFieldBuilder(panelTextePseudo).setCustomTextField(new Font("Roboto", Font.PLAIN, 11), 20).build();
		
		JPanel panelDateN = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelDateN = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 5).build();
		panelModif.add(panelDateN, gbcPanelDateN);
		GridBagLayout gblPanelDateN = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[] {30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelDateN.setLayout(gblPanelDateN);
		
		JPanel panelLabelDateN = new JPanelBuilder(panelDateN).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout4 = (FlowLayout) panelLabelDateN.getLayout();
		flowLayout4.setHgap(55);
		flowLayout4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel2 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelDateN.add(panelLabelDateN, gbcPanel2);
		
		new JLabelBuilder(panelLabelDateN).setCustomLabel("Date de naissance", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelEntreeDateN = new JPanelBuilder(panelDateN).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout9 = (FlowLayout) panelEntreeDateN.getLayout();
		flowLayout9.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel3 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelDateN.add(panelEntreeDateN, gbcPanel3);
		
		entreeDateNaissance.getComponentDateTextField().setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeDateNaissance.getComponentToggleCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeDateNaissance.setFont(new Font("Roboto", Font.PLAIN, 11));
		panelEntreeDateN.add(entreeDateNaissance);
		entreeDateNaissance.setPreferredSize(new Dimension(205,20));
		paramDate.setAllowEmptyDates(false);
		paramDate.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		JPanel panelNationalite = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelNationalite = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 6).build();
		panelModif.add(panelNationalite, gbcPanelNationalite);
		GridBagLayout gblPanelNationalite = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[] {30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelNationalite.setLayout(gblPanelNationalite);
		
		JPanel panelLabelNationalite = new JPanelBuilder(panelNationalite).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout2 = (FlowLayout) panelLabelNationalite.getLayout();
		flowLayout2.setHgap(55);
		flowLayout2.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel9 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelNationalite.add(panelLabelNationalite, gbcPanel9);
		
		new JLabelBuilder(panelLabelNationalite).setCustomLabel("Nationalité", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelTexteNationalite = new JPanelBuilder(panelNationalite).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout10 = (FlowLayout) panelTexteNationalite.getLayout();
		flowLayout10.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel10 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelNationalite.add(panelTexteNationalite, gbcPanel10);
		
		entreeNationalite = new JTextFieldBuilder(panelTexteNationalite).setCustomTextField(new Font("Roboto", Font.PLAIN, 11), 20).build();
		
		JPanel panelMdp = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelMdp = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 7).build();
		panelModif.add(panelMdp, gbcPanelMdp);
		GridBagLayout gblPanelMdp = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[] {30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelMdp.setLayout(gblPanelMdp);
		
		JPanel panelLabelMdp = new JPanelBuilder(panelMdp).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanel12 = (FlowLayout) panelLabelMdp.getLayout();
		flPanel12.setHgap(55);
		flPanel12.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel12 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelMdp.add(panelLabelMdp, gbcPanel12);
		
		new JLabelBuilder(panelLabelMdp).setCustomLabel("Mot de passe", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelTexteMdp = new JPanelBuilder(panelMdp).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout13 = (FlowLayout) panelTexteMdp.getLayout();
		flowLayout13.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel13 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelMdp.add(panelTexteMdp, gbcPanel13);
		
		entreeMdp = new JPasswordField();
		entreeMdp.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeMdp.setColumns(20);
		panelTexteMdp.add(entreeMdp);
		
		JPanel panelValider = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelValider = (FlowLayout) panelValider.getLayout();
		flPanelValider.setVgap(0);
		flPanelValider.setHgap(150);
		GridBagConstraints gbcPanelValider = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 8).build();
		panelModif.add(panelValider, gbcPanelValider);
		
		JButton btnValider = new JButtonBuilder(panelValider).setCustomButton("Valider", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.VERT).build();
		btnValider.setName("btnValider");
		
		JButton btnAnnuler = new JButtonBuilder(panelValider).setCustomButton("Annuler", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.GRIS).build();
		
		// VALIDER OU ANNULER INFORMATIONS SUR LE JOUEUR
		btnAnnuler.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		btnCreer.addActionListener(controleur);
		btnRechercher.addActionListener(controleur);
		// DECONNEXION
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
	
	public void rechercheEquipe(JTextField recherche) {
		recherche.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // ne pas utiliser
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (recherche.getText().isEmpty()) {
                	Vue.desactiverBouton(btnRechercher);
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if (!recherche.getText().isEmpty()) {
                	Vue.activerBouton(btnRechercher);
                }
            }
        });
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

	public Etat getEtat(JButton b) {
		if (b.getText().equals("Créer un nouveau joueur")) {
			return Etat.CREER;
		} else if (b.getText().equals("Annuler")) {
			return Etat.ANNULER;
		} else if (b.getText().equals("Se déconnecter")) {
			return Etat.DECONNECTER;
		} else if (b.getText().equals("Supprimer le joueur sélectionné")) {
			return Etat.SUPPRIMER;
		} else if (b.getText().equals("Ecuries / Responsables / Arbitres")) {
			Vue.desactiverBouton(headerA.getBtnEcuries());
			return Etat.ECURIE; 
		} else if (b.getText().equals("Valider")) {
			return Etat.VALIDER;
		} else if (b.getText().equals("Calendrier")) {
			Vue.desactiverBouton(headerA.getBtnCalendrier());
			return Etat.CALENDRIER;
		} else if (b.getText().equals("Tournois")) {
			Vue.desactiverBouton(header.getBtnTournois());
			return Etat.TOURNOIS;
		}else if (b.getText().equals("Rechercher")) {
			return Etat.RECHERCHER;
		}else if (b.getText().equals("Equipes")) {
			if (ControleurJoueur.estProfil("Gestionnaire")) {
				Vue.desactiverBouton(headerA.getBtnEquipes());
				}else {
				Vue.desactiverBouton(header.getBtnEquipes());
				}
		 	return Etat.EQUIPES;
		}else if (b.getText().equals("Choisir une photo")) {
			return Etat.PHOTO;
		} else if (b.getText().equals("Classement")) {
			if (ControleurJoueur.estProfil("Gestionnaire")) {
				Vue.desactiverBouton(headerA.getBtnClassement());
				}else {
				Vue.desactiverBouton(header.getBtnClassement());
				}
			return Etat.CLASSEMENT;
		}
		
		return null;
	}

	private void deselectionner() {
		this.listeJoueurs.clearSelection();
	}	
	
	public void afficherCreationJoueur() {
		this.deselectionner();
		VueJoueur.afficherPanel(panelModif);
		VueJoueur.afficherTexte(this.titreModif, "Créer un joueur");
		VueJoueur.supprimerTexte(this.entreeNom);
		VueJoueur.supprimerTexte(this.entreePrenom);
		VueJoueur.supprimerTexte(this.entreePseudo);
		entreeDateNaissance.setDate(null);
		VueJoueur.supprimerTexte(this.entreeNationalite);
		this.entreeEquipe.setSelectedItem("- Sélectionnez une équipe -");
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