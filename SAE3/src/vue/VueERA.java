package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Arrays;
import java.awt.Font;
import javax.swing.SwingConstants;

import controleur.ControleurERA;
import controleur.ControleurERA.Entite;
import controleur.ControleurERA.Etat;
import modele.GridBagConstraintsBuilder;
import modele.GridBagLayoutBuilder;
import modele.JButtonBuilder;
import modele.JLabelBuilder;
import modele.JPanelBuilder;
import modele.JTextFieldBuilder;

import java.awt.Color;
import javax.swing.border.LineBorder;

public class VueERA implements Vue{
	private JFrame fenetreERA;
	private HeaderAdmin header;

	// LISTE
	private DefaultListModel<String> modeleEcuries;
	private JList<String> listeEcuries;
	
	private DefaultListModel<String> modeleResponsables;
	private JList<String> listeResponsables;
	
	private DefaultListModel<String> modeleArbitres;
	private JList<String> listeArbitres;
	
	// LABEL
	private JLabel lblNomEcurie;
	private JLabel lblMdpEcurie;
	
	private JLabel lblNomResponsable;
	private JLabel lblPrenomResponsable;
	private JLabel lblMdpResponsable;
	
	private JLabel lblNomArbitre;
	private JLabel lblPrenomArbitre;
	private JLabel lblMdpArbitre;
	
	// CHAMPS DE SAISIE
	private JTextField rechercheEcurie;
	private JTextField nomEcurie;
	private JPasswordField mdpEcurie;
	
	private JTextField rechercheResponsable;
	private JTextField nomResponsable;
	private JTextField prenomResponsable;
	private JPasswordField mdpResponsable;
	
	private JTextField rechercheArbitre;
	private JTextField nomArbitre;
	private JTextField prenomArbitre;
	private JPasswordField mdpArbitre;
	
	// BOUTONS
	private JButton btnRechercheEcurie;
	private JButton btnSupprimerEcurie;
	
	private JButton btnRechercheResponsable;
	private JButton btnSupprimerResponsable;
	
	private JButton btnRechercheArbitre;
	private JButton btnSupprimerArbitre;
	
	private ControleurERA controleur;
	
	public JFrame getFrame() {
		return this.fenetreERA;
	}

	public VueERA() {
		fenetreERA = new JFrame();
		fenetreERA.getContentPane().setBackground(Couleur.BLEU1);
		fenetreERA.setResizable(false);
		fenetreERA.setBounds(100, 100, 1500, 880);
		fenetreERA.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		header = new HeaderAdmin(this.getFrame());
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreERA.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 3, 0, 0));
		
		// ----------------------------- ECURIE ----------------------------- //
		JPanel panelEcuries = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelEcuries.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelEcuries = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[]{461, 0}, 
				new int[] {100, 549, 100, 100, 0}, 
				new double[]{1.0, Double.MIN_VALUE}, 
				new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE}).build();
		panelEcuries.setLayout(gblPanelEcuries);
		
		JPanel panelHeaderContenuEcurie = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcEPanelHeaderContenu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.SOUTH, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 0).build();
		panelEcuries.add(panelHeaderContenuEcurie, gbcEPanelHeaderContenu);
		panelHeaderContenuEcurie.setLayout(new BoxLayout(panelHeaderContenuEcurie, BoxLayout.X_AXIS));
		
		JPanel panelTitreEcurie = new JPanelBuilder(panelHeaderContenuEcurie).setCustomPanel(Couleur.BLEU1).build();
		
		new JLabelBuilder(panelTitreEcurie).setCustomLabel("Ecuries", new Font(Vue.POLICE, Font.BOLD, 30), Color.WHITE).build();
		
		JPanel panelRechercheEcurie = new JPanelBuilder(panelHeaderContenuEcurie).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flEPanelRecherche = (FlowLayout) panelRechercheEcurie.getLayout();
		flEPanelRecherche.setAlignment(FlowLayout.RIGHT);
		
		rechercheEcurie = new JTextFieldBuilder(panelRechercheEcurie).setCustomTextField(new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();

		btnRechercheEcurie = new JButtonBuilder(panelRechercheEcurie).setCustomButton(
				Vue.RECHERCHER, 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.BLEU2).build();
		Vue.desactiverBouton(btnRechercheEcurie);
		rechercheEcurie(rechercheEcurie);
		
		JPanel panelListeEcuries = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeEcuries = (FlowLayout) panelListeEcuries.getLayout();
		flPanelListeEcuries.setVgap(10);
		GridBagConstraints gbcPanelListeEcuries = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 5, 0), 0, 1).build();
		panelEcuries.add(panelListeEcuries, gbcPanelListeEcuries);
		
		this.modeleEcuries = new DefaultListModel<>();
		listeEcuries = new JList<>(modeleEcuries);
		listeEcuries.setVisibleRowCount(10);
		listeEcuries.setFont(new Font(Vue.POLICE, Font.PLAIN, 13));
		listeEcuries.setFixedCellHeight(50);
		listeEcuries.setFixedCellWidth(400);
		JScrollPane scrollPane = new JScrollPane(listeEcuries);
		panelListeEcuries.add(scrollPane);
		
		JPanel panelModificationEcurie = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcEPanelModification = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 2).build();
		panelEcuries.add(panelModificationEcurie, gbcEPanelModification);
		
		JPanel panelEntreeEcurie = new JPanelBuilder(panelModificationEcurie).setCustomPanel(Couleur.BLEU1).build();
		panelEntreeEcurie.setLayout(new GridLayout(0, 2, 10, 10));
		
		lblNomEcurie = new JLabelBuilder(panelEntreeEcurie).setCustomLabel(
				"Nom écurie", 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblNomEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		
		nomEcurie = new JTextFieldBuilder(panelEntreeEcurie).setCustomTextField(new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();
		
		lblMdpEcurie = new JLabelBuilder(panelEntreeEcurie).setCustomLabel(
				Vue.MOT_DE_PASSE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblMdpEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		
		mdpEcurie = new JPasswordField();
		mdpEcurie.setFont(new Font(Vue.POLICE, Font.PLAIN, 13));
		panelEntreeEcurie.add(mdpEcurie);
		
		JPanel panelValiderEcurie = new JPanelBuilder(panelModificationEcurie).setCustomPanel(Couleur.BLEU1).build();
		panelValiderEcurie.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnValiderEcurie = new JButtonBuilder(panelValiderEcurie).setCustomButton(
				Vue.VALIDER, 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.VERT).build();
		JButton btnAnnulerEcurie = new JButtonBuilder(panelValiderEcurie).setCustomButton(
				Vue.ANNULER, 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.GRIS).build();
		
		JPanel panelBoutonsEcurie = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcEPanelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 3).build();
		panelEcuries.add(panelBoutonsEcurie, gbcEPanelBoutons);
		panelBoutonsEcurie.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreerEcurie = new JButtonBuilder(panelBoutonsEcurie).setCustomButton(
				"Créer une nouvelle écurie", 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 12), 
				Couleur.BLEU2).build();
		btnSupprimerEcurie = new JButtonBuilder(panelBoutonsEcurie).setCustomButton(
				"Supprimer l'écurie sélectionnée", 
				Color.WHITE, new Font(Vue.POLICE, Font.BOLD, 12), 
				Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimerEcurie);

		// ----------------------------- RESPONSABLE ----------------------------- //
		JPanel panelResponsables = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelResponsables.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelResponsables = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[]{461, 0}, 
				new int[] {100, 549, 100, 100, 0}, 
				new double[]{1.0, Double.MIN_VALUE}, 
				new double[]{0.0, 0.0, 0.0, 1.0}).build();
		panelResponsables.setLayout(gblPanelResponsables);
		
		JPanel panelHeaderContenuResponsable = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcRPanelHeaderContenu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.SOUTH, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 0).build();
		panelResponsables.add(panelHeaderContenuResponsable, gbcRPanelHeaderContenu);
		panelHeaderContenuResponsable.setLayout(new BoxLayout(panelHeaderContenuResponsable, BoxLayout.X_AXIS));
		
		JPanel panelTitreResponsable = new JPanelBuilder(panelHeaderContenuResponsable).setCustomPanel(Couleur.BLEU1).build();
		
		new JLabelBuilder(panelTitreResponsable).setCustomLabel("Responsables", new Font(Vue.POLICE, Font.BOLD, 30), Color.WHITE).build();
		
		JPanel panelRechercheResponsable = new JPanelBuilder(panelHeaderContenuResponsable).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flRPanelRecherche = (FlowLayout) panelRechercheResponsable.getLayout();
		flRPanelRecherche.setAlignment(FlowLayout.RIGHT);
		
		rechercheResponsable = new JTextFieldBuilder(panelRechercheResponsable).setCustomTextField(
				new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();

		btnRechercheResponsable = new JButtonBuilder(panelRechercheResponsable).setCustomButton(
				Vue.RECHERCHER, 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.BLEU2).build();
		Vue.desactiverBouton(btnRechercheResponsable);
		rechercheResponsable(rechercheResponsable);
		
		JPanel panelListeResponsables = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeResponsables = (FlowLayout) panelListeResponsables.getLayout();
		flPanelListeResponsables.setVgap(10);
		GridBagConstraints gbcPanelListeResponsables = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 5, 0), 0, 1).build();
		panelResponsables.add(panelListeResponsables, gbcPanelListeResponsables);
		
		this.modeleResponsables = new DefaultListModel<>();
		listeResponsables = new JList<>(modeleResponsables);
		listeResponsables.setVisibleRowCount(10);
		listeResponsables.setFont(new Font(Vue.POLICE, Font.PLAIN, 13));
		listeResponsables.setFixedCellHeight(50);
		listeResponsables.setFixedCellWidth(400);
		JScrollPane scrollPane2 = new JScrollPane(listeResponsables);
		panelListeResponsables.add(scrollPane2);
		
		JPanel panelModificationResponsable = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcRPanelModification = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 2).build();
		panelResponsables.add(panelModificationResponsable, gbcRPanelModification);
		
		JPanel panelEntreeResponsable = new JPanelBuilder(panelModificationResponsable).setCustomPanel(Couleur.BLEU1).build();
		panelEntreeResponsable.setLayout(new GridLayout(0, 2, 10, 5));
		
		lblNomResponsable = new JLabelBuilder(panelEntreeResponsable).setCustomLabel(
				"Nom responsable", 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblNomResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		
		nomResponsable = new JTextFieldBuilder(panelEntreeResponsable).setCustomTextField(new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();
		
		lblPrenomResponsable = new JLabelBuilder(panelEntreeResponsable).setCustomLabel(
				"Prénom responsable", 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblPrenomResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		
		prenomResponsable = new JTextFieldBuilder(panelEntreeResponsable).setCustomTextField(new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();
		
		lblMdpResponsable = new JLabelBuilder(panelEntreeResponsable).setCustomLabel(
				Vue.MOT_DE_PASSE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblMdpResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		
		mdpResponsable = new JPasswordField();
		mdpResponsable.setFont(new Font(Vue.POLICE, Font.PLAIN, 13));
		panelEntreeResponsable.add(mdpResponsable);
		
		JPanel panelValiderResponsable = new JPanelBuilder(panelModificationResponsable).setCustomPanel(Couleur.BLEU1).build();
		panelValiderResponsable.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnValiderResponsable = new JButtonBuilder(panelValiderResponsable).setCustomButton(
				Vue.VALIDER, 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.VERT).build();
		JButton btnAnnulerResponsable = new JButtonBuilder(panelValiderResponsable).setCustomButton(
				Vue.ANNULER, 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.GRIS).build();
		
		JPanel panelBoutonsResponsable = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcRPanelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 3).build();
		panelResponsables.add(panelBoutonsResponsable, gbcRPanelBoutons);
		
		JButton btnCreerResponsable = new JButtonBuilder(panelBoutonsResponsable).setCustomButton(
				"Créer un nouveau responsable", 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 12), 
				Couleur.BLEU2).build();
		
		btnSupprimerResponsable = new JButtonBuilder(panelBoutonsResponsable).setCustomButton(
				"Supprimer le responsable sélectionné", 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 12), 
				Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimerResponsable);

		// ----------------------------- ARBITRE ----------------------------- //
		JPanel panelArbitres = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelArbitres.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelArbitres = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[]{461, 0}, 
				new int[] {100, 549, 100, 100, 0}, 
				new double[]{1.0, Double.MIN_VALUE}, 
				new double[]{0.0, 0.0, 0.0, 1.0, 
						Double.MIN_VALUE}).build();
		panelArbitres.setLayout(gblPanelArbitres);
		
		JPanel panelHeaderContenuArbitre = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcAPanelHeaderContenu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.SOUTH, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 0).build();
		panelArbitres.add(panelHeaderContenuArbitre, gbcAPanelHeaderContenu);
		panelHeaderContenuArbitre.setLayout(new BoxLayout(panelHeaderContenuArbitre, BoxLayout.X_AXIS));
		
		JPanel panelTitreArbitre = new JPanelBuilder(panelHeaderContenuArbitre).setCustomPanel(Couleur.BLEU1).build();
		
		new JLabelBuilder(panelTitreArbitre).setCustomLabel("Arbitres", new Font(Vue.POLICE, Font.BOLD, 30), Color.WHITE).build();
		
		JPanel panelRechercheArbitre = new JPanelBuilder(panelHeaderContenuArbitre).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelRechercheArbitre = (FlowLayout) panelRechercheArbitre.getLayout();
		flPanelRechercheArbitre.setAlignment(FlowLayout.RIGHT);
		
		rechercheArbitre = new JTextFieldBuilder(panelRechercheArbitre).setCustomTextField(new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();
		
		btnRechercheArbitre = new JButtonBuilder(panelRechercheArbitre).setCustomButton(
				Vue.RECHERCHER, Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.BLEU2).build();
		Vue.desactiverBouton(btnRechercheArbitre);
		rechercheArbitre(rechercheArbitre);
		
		JPanel panelListeArbitres = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeArbitres = (FlowLayout) panelListeArbitres.getLayout();
		flPanelListeArbitres.setVgap(10);
		GridBagConstraints gbcPanelListeArbitres = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 5, 0), 0, 1).build();
		panelArbitres.add(panelListeArbitres, gbcPanelListeArbitres);
		
		this.modeleArbitres = new DefaultListModel<>();
		listeArbitres = new JList<>(modeleArbitres);
		listeArbitres.setVisibleRowCount(10);
		listeArbitres.setFont(new Font(Vue.POLICE, Font.PLAIN, 13));
		listeArbitres.setFixedCellHeight(50);
		listeArbitres.setFixedCellWidth(400);
		JScrollPane scrollPane3 = new JScrollPane(listeArbitres);
		panelListeArbitres.add(scrollPane3);
		
		JPanel panelModificationArbitre = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelModificationArbitre = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 2).build();
		panelArbitres.add(panelModificationArbitre, gbcPanelModificationArbitre);
		
		JPanel panelEntreeArbitre = new JPanelBuilder(panelModificationArbitre).setCustomPanel(Couleur.BLEU1).build();
		panelEntreeArbitre.setLayout(new GridLayout(0, 2, 10, 5));
		
		lblNomArbitre = new JLabelBuilder(panelEntreeArbitre).setCustomLabel(
				"Nom arbitre", 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblNomArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		nomArbitre = new JTextFieldBuilder(panelEntreeArbitre).setCustomTextField(new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();
		
		lblPrenomArbitre = new JLabelBuilder(panelEntreeArbitre).setCustomLabel(
				"Prénom arbitre", 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblPrenomArbitre.setHorizontalAlignment(SwingConstants.RIGHT);

		prenomArbitre = new JTextFieldBuilder(panelEntreeArbitre).setCustomTextField(new Font(Vue.POLICE, Font.PLAIN, 13), 10).build();
		
		lblMdpArbitre = new JLabelBuilder(panelEntreeArbitre).setCustomLabel(
				Vue.MOT_DE_PASSE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Color.WHITE).build();
		lblMdpArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		mdpArbitre = new JPasswordField();
		mdpArbitre.setFont(new Font(Vue.POLICE, Font.PLAIN, 13));
		panelEntreeArbitre.add(mdpArbitre);
		
		JPanel panelValiderArbitre = new JPanelBuilder(panelModificationArbitre).setCustomPanel(Couleur.BLEU1).build();
		panelValiderArbitre.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnValiderArbitre = new JButtonBuilder(panelValiderArbitre).setCustomButton(
				Vue.VALIDER, Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.VERT).build();
		JButton btnAnnulerArbitre = new JButtonBuilder(panelValiderArbitre).setCustomButton(
				Vue.ANNULER, 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 13), 
				Couleur.GRIS).build();
		
		JPanel panelBoutonsArbitre = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelBoutonsArbitre = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 3).build();
		panelArbitres.add(panelBoutonsArbitre, gbcPanelBoutonsArbitre);
		
		JButton btnCreerArbitre = new JButtonBuilder(panelBoutonsArbitre).setCustomButton(
				"Créer un nouvel arbitre", 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 12), 
				Couleur.BLEU2).build();
		btnSupprimerArbitre = new JButtonBuilder(panelBoutonsArbitre).setCustomButton(
				"Supprimer l'arbitre sélectionné", 
				Color.WHITE, 
				new Font(Vue.POLICE, Font.BOLD, 12), 
				Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimerArbitre);
		
		this.controleur = new ControleurERA(this);
		header.getBtnDeconnexion().addActionListener(controleur);
		header.getBtnEquipes().addActionListener(controleur);
		header.getBtnJoueurs().addActionListener(controleur);
		header.getBtnCalendrier().addActionListener(controleur);
		header.getBtnEcuries().addActionListener(controleur);
		header.getBtnClassement().addActionListener(controleur);

		Vue.desactiverBouton(header.getBtnEcuries());	
		
		// ECURIE //
		btnRechercheEcurie.addActionListener(controleur);
		btnCreerEcurie.addActionListener(controleur);
		btnSupprimerEcurie.addActionListener(controleur);
		btnValiderEcurie.addActionListener(controleur);
		btnAnnulerEcurie.addActionListener(controleur);
		
		btnRechercheEcurie.setName(Entite.ECURIE.getNom());
		btnCreerEcurie.setName(Entite.ECURIE.getNom());
		btnSupprimerEcurie.setName(Entite.ECURIE.getNom());
		btnValiderEcurie.setName(Entite.ECURIE.getNom());
		btnAnnulerEcurie.setName(Entite.ECURIE.getNom());
		
		// RESPONSABLE //
		btnRechercheResponsable.addActionListener(controleur);
		btnCreerResponsable.addActionListener(controleur);
		btnSupprimerResponsable.addActionListener(controleur);
		btnValiderResponsable.addActionListener(controleur);
		btnAnnulerResponsable.addActionListener(controleur);
		
		btnRechercheResponsable.setName(Entite.RESPONSABLE.getNom());
		btnCreerResponsable.setName(Entite.RESPONSABLE.getNom());
		btnSupprimerResponsable.setName(Entite.RESPONSABLE.getNom());
		btnValiderResponsable.setName(Entite.RESPONSABLE.getNom());
		btnAnnulerResponsable.setName(Entite.RESPONSABLE.getNom());
		
		// ARBITRE
		btnRechercheArbitre.addActionListener(controleur);
		btnCreerArbitre.addActionListener(controleur);
		btnSupprimerArbitre.addActionListener(controleur);
		btnValiderArbitre.addActionListener(controleur);
		btnAnnulerArbitre.addActionListener(controleur);

		btnRechercheArbitre.setName(Entite.ARBITRE.getNom());
		btnCreerArbitre.setName(Entite.ARBITRE.getNom());
		btnSupprimerArbitre.setName(Entite.ARBITRE.getNom());
		btnValiderArbitre.setName(Entite.ARBITRE.getNom());
		btnAnnulerArbitre.setName(Entite.ARBITRE.getNom());
		
		this.listeEcuries.addListSelectionListener(controleur);
		this.listeEcuries.setName(Entite.ECURIE.getNom());
		this.listeResponsables.addListSelectionListener(controleur);
		this.listeResponsables.setName(Entite.RESPONSABLE.getNom());
		this.listeArbitres.addListSelectionListener(controleur);
		this.listeArbitres.setName(Entite.ARBITRE.getNom());
	}
	
	public static void fermerFenetre(JFrame f) {f.setVisible(false);}
	
	// AJOUTER UNE ENTITE //
	public void ajouterEcurie(String e) {
		modeleEcuries.addElement(e);
	}
	public void ajouterResponsable(String r) {
		modeleResponsables.addElement(r);
	}
	public void ajouterArbitre(String a) {
		modeleArbitres.addElement(a);
	}
	
	// SUPPRIMER UNE ENTITE //
	public void supprimerEntite() {
		switch (this.controleur.getEntite()) {
		case ECURIE:
			modeleEcuries.removeElement(this.listeEcuries.getSelectedValue());
			break;
		case RESPONSABLE:
			modeleResponsables.removeElement(this.listeResponsables.getSelectedValue());
			break;
		case ARBITRE:
			modeleArbitres.removeElement(this.listeArbitres.getSelectedValue());
			break;
		default:
		}
		// Recharge la liste en cas de liste en état de recherche
		this.filtrerRecherche();
	}
	
	// MODIFIER UNE ENTITE //
	public void modifierEcurie() {
		modeleEcuries.set(this.listeEcuries.getSelectedIndex(),this.getNomEcurie());
		this.listeEcuries.clearSelection();
	}
	public void modifierResponsable() {
		modeleResponsables.set(this.listeResponsables.getSelectedIndex(),
				this.getPrenomResponsable() + " " + this.getNomResponsable());
		this.listeResponsables.clearSelection();
	}
	public void modifierArbitre() {
		modeleArbitres.set(this.listeArbitres.getSelectedIndex(),
				this.getPrenomArbitre() + " " + this.getNomArbitre());
		this.listeArbitres.clearSelection();
	}
	
	// GETTERS //
	public String getNomListe(JList<String> j) {
		return j.getName();
	}
	
	public JList<String> getListeEcurie() {
		return this.listeEcuries;
	}
	
	public JButton getBtnSupprimerEcurie() {
		return btnSupprimerEcurie;
	}
	public JButton getbtnSupprimerResponsable() {
		return btnSupprimerResponsable;
	}
	public JButton getBtnSupprimerArbitre() {
		return btnSupprimerArbitre;
	}
	
	public String getNom() {
		String nom;
		switch (this.controleur.getEntite()) {
		case ECURIE:
			nom = getNomEcurie();
			break;
		case RESPONSABLE:
			nom = getNomResponsable();
			break;
		case ARBITRE:
			nom = getNomArbitre();
			break;
		default:
			nom = null;
		}
		return nom;
	}
	public String getNomEcurie() {return this.nomEcurie.getText();}
	public String getNomResponsable() {return this.nomResponsable.getText();}
	public String getNomArbitre() {return this.nomArbitre.getText();}
	
	public String getPrenomResponsable() {return this.prenomResponsable.getText();}
	public String getPrenomArbitre() {return this.prenomArbitre.getText();}
	
	public String getRecherche() {
		String recherche;
		switch (this.controleur.getEntite()) {
		case ECURIE:
			recherche = getRechercheEcurie();
			break;
		case RESPONSABLE:
			recherche = getRechercheResponsable();
			break;
		case ARBITRE:
			recherche = getRechercheArbitre();
			break;
		default:
			recherche = null;
		}
		return recherche;
	}
	public String getRechercheEcurie() {return this.rechercheEcurie.getText();}
	public String getRechercheResponsable() {return this.rechercheResponsable.getText();}
	public String getRechercheArbitre() {return this.rechercheArbitre.getText();}
	
	public String getNomSelectionne() {
		String nomSelectionne;
		switch (this.controleur.getEntite()) {
		case ECURIE:
			nomSelectionne = getNomSelectionneEcurie();
			break;
		case RESPONSABLE:
			nomSelectionne = getNomSelectionneResponsable();
			break;
		case ARBITRE:
			nomSelectionne = getNomSelectionneArbitre();
			break;
		default:
			nomSelectionne = null;
		}
		return nomSelectionne;
	}
	public String getNomSelectionneEcurie() {return this.listeEcuries.getSelectedValue();}
	public String getNomSelectionneResponsable() {return this.listeResponsables.getSelectedValue();}
	public String getNomSelectionneArbitre() {return this.listeArbitres.getSelectedValue();}
	
	public String getMotDePasse() {
		String mdp;
		switch (this.controleur.getEntite()) {
		case ECURIE:
			mdp = getMotDePasseEcurie();
			break;
		case RESPONSABLE:
			mdp = getMotDePasseResponsable();
			break;
		case ARBITRE:
			mdp = getMotDePasseArbitre();
			break;
		default:
			mdp = null;
		}
		return mdp;
	}
	public String getMotDePasseEcurie() {return String.valueOf(this.mdpEcurie.getPassword());}
	public String getMotDePasseResponsable() {return String.valueOf(this.mdpResponsable.getPassword());}
	public String getMotDePasseArbitre() {return String.valueOf(this.mdpArbitre.getPassword());}
		
	// SETTERS //
	public void setDefaultListModel() {
		this.listeEcuries.setModel(modeleEcuries);
		this.listeResponsables.setModel(modeleResponsables);
		this.listeArbitres.setModel(modeleArbitres);
	}
	
	public void setNomSelectionneEcurie() {this.nomEcurie.setText(this.listeEcuries.getSelectedValue());}
	public void setNomSelectionneResponsable() {
		this.nomResponsable.setText(this.listeResponsables.getSelectedValue());
		}
	public void setNomSelectionneArbitre() {this.nomArbitre.setText(this.listeArbitres.getSelectedValue());}
	
	public void setNom(String nom,String prenom) {
		switch (this.controleur.getEntite()) {
		case ECURIE:
			setNomEcurie(nom);
			break;
		case RESPONSABLE:
			setNomResponsable(nom,prenom);
			break;
		case ARBITRE:
			setNomArbitre(nom,prenom);
			break;
		default:
		}
	}
	public void setNomEcurie(String nom) {this.nomEcurie.setText(nom);}
	public void setNomResponsable(String nom,String prenom) {
		this.nomResponsable.setText(nom);
		this.prenomResponsable.setText(prenom);
	}
	public void setNomArbitre(String nom,String prenom) {
		this.nomArbitre.setText(nom);
		this.prenomArbitre.setText(prenom);
	}
	
	public void viderMotDePasse() {
		this.mdpEcurie.setText("");
		this.mdpResponsable.setText("");
		this.mdpArbitre.setText("");
	}
	
	public void deselectionner() {
		switch (this.controleur.getEntite()) {
		case ECURIE:
			this.listeEcuries.clearSelection();
			break;
		case RESPONSABLE:
			this.listeResponsables.clearSelection();
			break;
		case ARBITRE:
			this.listeArbitres.clearSelection();
			break;
		default:
		}
	}
	
	public void setCouleurSaisiesEcurieANoir() {
		this.lblNomEcurie.setForeground(Color.WHITE);
		this.nomEcurie.setForeground(Color.BLACK);
		this.lblMdpEcurie.setForeground(Color.WHITE);
		this.mdpEcurie.setForeground(Color.BLACK);
	}
	
	public void setCouleurSaisiesResponsableANoir() {
		this.lblNomResponsable.setForeground(Color.WHITE);
		this.nomResponsable.setForeground(Color.BLACK);
		this.lblPrenomResponsable.setForeground(Color.WHITE);
		this.prenomResponsable.setForeground(Color.BLACK);
		this.lblMdpResponsable.setForeground(Color.WHITE);
		this.mdpResponsable.setForeground(Color.BLACK);
	}
	
	public void setCouleurSaisiesArbitreANoir() {
		this.lblNomArbitre.setForeground(Color.WHITE);
		this.nomArbitre.setForeground(Color.BLACK);
		this.lblPrenomArbitre.setForeground(Color.WHITE);
		this.prenomArbitre.setForeground(Color.BLACK);
		this.lblMdpArbitre.setForeground(Color.WHITE);
		this.mdpArbitre.setForeground(Color.BLACK);
	}
	
	// LISTE //
	public boolean estSelectionneEcurie() {return !(this.listeEcuries.isSelectionEmpty());}
	public boolean estSelectionneResponsable() {return !(this.listeResponsables.isSelectionEmpty());}
	public boolean estSelectionneArbitre() {return !(this.listeArbitres.isSelectionEmpty());}
	
	
	// ETATS //
	public Etat getEtat(JButton b) {
		if (b.getText().contains("Créer")) {
			this.setEntite(b);
			this.deselectionner();
			return Etat.CREER;
			
		} else if (b.getText().contains("Supprimer")) {
			return Etat.SUPPRIMER;
			
		} else if (b.getText().equals("Se déconnecter")) {
			return Etat.DECONNECTER;
			
		} else if (b.getText().equals("Calendrier")) {
			Vue.desactiverBouton(header.getBtnCalendrier());
			return Etat.CALENDRIER;
			
		} else if (b.getText().equals("Joueurs")) {
			Vue.desactiverBouton(header.getBtnJoueurs());
			return Etat.JOUEURS;
			
		} else if (b.getText().equals("Equipes")) {
			Vue.desactiverBouton(header.getBtnEquipes());
			return Etat.EQUIPES ;
			
		} else if (b.getText().equals("Classement")) {
		 	Vue.desactiverBouton(header.getBtnClassement());
			return Etat.CLASSEMENT;
			
		} else if (b.getText().equals(Vue.RECHERCHER)) {
			this.setEntite(b);
			return Etat.RECHERCHER;
			
		} else if (b.getText().equals(Vue.VALIDER)) {
			this.setEntite(b);
			return Etat.VALIDER;
			
		} else if (b.getText().equals(Vue.ANNULER)) {
			this.setEntite(b);
			this.deselectionner();
			return Etat.ANNULER;
			
		}
		return null;
	}

	// SET L'ENTITE COURANTE 
	private void setEntite(JButton b) {
		if (b.getName().equals("Ecurie")) {
			this.controleur.setEntite(Entite.ECURIE);
		} else if (b.getName().equals("Responsable")) {
			this.controleur.setEntite(Entite.RESPONSABLE);
		} else if (b.getName().equals("Arbitre")) {
			this.controleur.setEntite(Entite.ARBITRE);
		}
	}
	
	public void setEntite(JList<String> l) {
		switch (l.getName()) {
		case "Ecurie":
			this.controleur.setEntite(Entite.ECURIE);
			break;
		case "Responsable":
			this.controleur.setEntite(Entite.RESPONSABLE);
			break;
		case "Arbitre":
			this.controleur.setEntite(Entite.ARBITRE);
			break;
		default:
		}
	}
	
	public void rechercheEcurie(JTextField recherche) {
		recherche.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // ne pas utiliser
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (recherche.getText().isEmpty()) {
                	Vue.desactiverBouton(btnRechercheEcurie);
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if (!recherche.getText().isEmpty()) {
                	Vue.activerBouton(btnRechercheEcurie);
                }
            }
        });
	}
	
	public void rechercheResponsable(JTextField recherche) {
		recherche.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // ne pas utiliser
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (recherche.getText().isEmpty()) {
                    Vue.desactiverBouton(btnRechercheResponsable);
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if (!recherche.getText().isEmpty()) {
                	Vue.activerBouton(btnRechercheResponsable);
                }
            }
        });
	}
	
	public void rechercheArbitre(JTextField recherche) {
		recherche.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // ne pas utiliser
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (recherche.getText().isEmpty()) {
                	Vue.desactiverBouton(btnRechercheArbitre);
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if (!recherche.getText().isEmpty()) {
                	Vue.activerBouton(btnRechercheArbitre);
                }
            }
        });
	}
	
	// FILTRES
	public void filtrerRecherche() {
		switch (this.controleur.getEntite()) {
		case ECURIE:
			filtrerRechercheEcurie();
			break;
		case RESPONSABLE:
			filtrerRechercheResponsable();
			break;
		case ARBITRE:
			filtrerRechercheArbitre();
			break;
		default:
			break;
		}
	}

	public void filtrerRechercheEcurie() {
		if (this.rechercheEcurie.getText().equals("")) {
			this.listeEcuries.setModel(this.modeleEcuries);
		} else {
			DefaultListModel<String> modeleFiltre = new DefaultListModel<>();
		    for (int i = 0; i < this.modeleEcuries.size(); i++) {
		    	if (this.modeleEcuries.get(i).contains(this.rechercheEcurie.getText())){
		    		modeleFiltre.addElement(this.modeleEcuries.get(i));
		    	}
		    }
		    this.listeEcuries.setModel(modeleFiltre);
		}
	}

	public void filtrerRechercheResponsable() {
		if (this.rechercheResponsable.getText().equals("")) {
			this.listeResponsables.setModel(this.modeleResponsables);
		} else {
			DefaultListModel<String> modeleFiltre = new DefaultListModel<>();
		    for (int i = 0; i < this.modeleResponsables.size(); i++) {
		    	if (this.modeleResponsables.get(i).contains(this.rechercheResponsable.getText())){
		    		modeleFiltre.addElement(this.modeleResponsables.get(i));
		    	}
		    }
		    this.listeResponsables.setModel(modeleFiltre);
		}
	}
	
	public void filtrerRechercheArbitre() {
		if (this.rechercheArbitre.getText().equals("")) {
			this.listeArbitres.setModel(this.modeleArbitres);
		} else {
			DefaultListModel<String> modeleFiltre = new DefaultListModel<>();
		    for (int i = 0; i < this.modeleArbitres.size(); i++) {
		    	if (this.modeleArbitres.get(i).contains(this.rechercheArbitre.getText())){
		    		modeleFiltre.addElement(this.modeleArbitres.get(i));
		    	}
		    }
		    this.listeArbitres.setModel(modeleFiltre);
		}
	}
	
	// VERIFIE SI LES CHAMPS ONT ETE SAISIS
	public boolean estFormulaireRempliEcurie() {
		Boolean[] resultat = new Boolean[2];
		resultat[0] = Vue.estSaisiRempli(this.getNomEcurie(), "", this.lblNomEcurie, this.nomEcurie);
		if (this.listeResponsables.isSelectionEmpty()) {
			resultat[1] = Vue.estSaisiRempli(this.getPrenomResponsable(), "", this.lblMdpEcurie, this.mdpEcurie);
			
		}
		
		if (!Arrays.stream(resultat).allMatch(Boolean::valueOf)) {
			Vue.estVide();
			return false;
			
		}
		return true;
	}
	
	public boolean estFormulaireRempliResponsable() {
		Boolean[] resultat = new Boolean[3];
		resultat[0] = Vue.estSaisiRempli(this.getNomResponsable(), "", this.lblNomResponsable, this.nomResponsable);
		resultat[1] = Vue.estSaisiRempli(this.getPrenomResponsable(), "", this.lblPrenomResponsable, this.prenomResponsable);
		
		if (this.listeResponsables.isSelectionEmpty()) {
			resultat[2] = Vue.estSaisiRempli(this.getPrenomResponsable(), "", this.lblMdpResponsable, this.mdpResponsable);
			
		}
		
		if (!Arrays.stream(resultat).allMatch(Boolean::valueOf)) {
			Vue.estVide();
			return false;
			
		}
		return true;
	}
	
	public boolean estFormulaireRempliArbitre() {
		Boolean[] resultat = new Boolean[3];
		resultat[0] = Vue.estSaisiRempli(this.getNomArbitre(), "", this.lblNomArbitre, this.nomArbitre);
		resultat[1] = Vue.estSaisiRempli(this.getPrenomArbitre(), "", this.lblPrenomArbitre, this.prenomArbitre);
		
		if (this.listeResponsables.isSelectionEmpty()) {
			resultat[2] = Vue.estSaisiRempli(this.getPrenomResponsable(), "", this.lblMdpArbitre, this.mdpArbitre);
			
		}
		
		if (!Arrays.stream(resultat).allMatch(Boolean::valueOf)) {
			Vue.estVide();
			return false;
			
		}
		return true;
	}

	// MESSAGES
	public int confirmer(String operation) {
		return JOptionPane.showConfirmDialog(null, "Confirmez-vous la "+operation+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
	}
	
	public void existe() {
        JOptionPane.showMessageDialog(null, "Cette entité existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
