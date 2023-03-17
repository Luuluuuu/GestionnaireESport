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
	public JFrame fenetreERA;
	private JTextField rechercheEcurie;
	private JTextField rechercheResponsable;
	private JTextField rechercheArbitre;

	private DefaultListModel<String> modeleEcuries;
	public JList<String> listeEcuries;
	private DefaultListModel<String> modeleResponsables;
	public JList<String> listeResponsables;
	private DefaultListModel<String> modeleArbitres;
	public JList<String> listeArbitres;
	
	private JTextField nomEcurie;
	private JPasswordField mdpEcurie;
	private JTextField nomResponsable;
	private JTextField prenomResponsable;
	private JPasswordField mdpResponsable;
	private JTextField nomArbitre;
	private JTextField prenomArbitre;
	private JPasswordField mdpArbitre;
	
	private JButton btnSupprimerEcurie;
	private JButton btnSupprimerResponsable;
	private JButton btnSupprimerArbitre;
	private JButton btnRechercheEcurie;
	private JButton ABtnRecherche;
	private JButton btnRechercheResponsable;
	private HeaderAdmin header;
	
	public JFrame getFrame() {
		return this.fenetreERA;
	}

	public VueERA() {
		fenetreERA = new JFrame();
		fenetreERA.getContentPane().setBackground(Couleur.BLEU1);
		fenetreERA.setResizable(false);
		fenetreERA.setBounds(100, 100, 1500, 880);
		fenetreERA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		header = new HeaderAdmin(this.getFrame());
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreERA.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 3, 0, 0));
		
		// ----------------------------- ECURIE ----------------------------- //
		JPanel panelEcuries = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelEcuries.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelEcuries = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{461, 0}, new int[] {100, 549, 100, 100, 0}, new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE}).build();
		panelEcuries.setLayout(gblPanelEcuries);
		
		JPanel EPanelHeaderContenu = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcEPanelHeaderContenu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelEcuries.add(EPanelHeaderContenu, gbcEPanelHeaderContenu);
		EPanelHeaderContenu.setLayout(new BoxLayout(EPanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel EPanelTitre = new JPanelBuilder(EPanelHeaderContenu).setCustomPanel(Couleur.BLEU1).build();
		
		new JLabelBuilder(EPanelTitre).setCustomLabel("Ecuries", new Font("Roboto", Font.BOLD, 30), Color.WHITE).build();
		
		JPanel EPanelRecherche = new JPanelBuilder(EPanelHeaderContenu).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flEPanelRecherche = (FlowLayout) EPanelRecherche.getLayout();
		flEPanelRecherche.setAlignment(FlowLayout.RIGHT);
		
		rechercheEcurie = new JTextFieldBuilder(EPanelRecherche).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();

		btnRechercheEcurie = new JButtonBuilder(EPanelRecherche).setCustomButton("Rechercher", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		Vue.desactiverBouton(btnRechercheEcurie);
		rechercheEcurie(rechercheEcurie);
		
		JPanel panelListeEcuries = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeEcuries = (FlowLayout) panelListeEcuries.getLayout();
		flPanelListeEcuries.setVgap(10);
		GridBagConstraints gbcPanelListeEcuries = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 1).build();
		panelEcuries.add(panelListeEcuries, gbcPanelListeEcuries);
		
		this.modeleEcuries = new DefaultListModel<String>();
		listeEcuries = new JList<String>(modeleEcuries);
		listeEcuries.setVisibleRowCount(10);
		listeEcuries.setFont(new Font("Roboto", Font.PLAIN, 13));
		listeEcuries.setFixedCellHeight(50);
		listeEcuries.setFixedCellWidth(400);
		JScrollPane scrollPane = new JScrollPane(listeEcuries);
		panelListeEcuries.add(scrollPane);
		
		JPanel EPanelModification = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcEPanelModification = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 2).build();
		panelEcuries.add(EPanelModification, gbcEPanelModification);
		
		JPanel EPanelEntree = new JPanelBuilder(EPanelModification).setCustomPanel(Couleur.BLEU1).build();
		EPanelEntree.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNomEcurie = new JLabelBuilder(EPanelEntree).setCustomLabel("Nom écurie", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblNomEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		
		nomEcurie = new JTextFieldBuilder(EPanelEntree).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();
		
		JLabel lblMdpEcurie = new JLabelBuilder(EPanelEntree).setCustomLabel("Mot de passe", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblMdpEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		
		mdpEcurie = new JPasswordField();
		mdpEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelEntree.add(mdpEcurie);
		
		JPanel EPanelValider = new JPanelBuilder(EPanelModification).setCustomPanel(Couleur.BLEU1).build();
		EPanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton EBtnValider = new JButtonBuilder(EPanelValider).setCustomButton("Valider", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.VERT).build();
		JButton btnAnnulerEcurie = new JButtonBuilder(EPanelValider).setCustomButton("Annuler", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.GRIS).build();
		
		JPanel EPanelBoutons = new JPanelBuilder(panelEcuries).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcEPanelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 3).build();
		panelEcuries.add(EPanelBoutons, gbcEPanelBoutons);
		EPanelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreerEcurie = new JButtonBuilder(EPanelBoutons).setCustomButton("Créer une nouvelle écurie", Color.WHITE, new Font("Roboto", Font.BOLD, 12), Couleur.BLEU2).build();
		btnSupprimerEcurie = new JButtonBuilder(EPanelBoutons).setCustomButton("Supprimer l'écurie sélectionnée", Color.WHITE, new Font("Roboto", Font.BOLD, 12), Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimerEcurie);

		// ----------------------------- RESPONSABLE ----------------------------- //
		JPanel panelResponsables = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelResponsables.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelResponsables = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{461, 0}, new int[] {100, 549, 100, 100, 0}, new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, 0.0, 1.0}).build();
		panelResponsables.setLayout(gblPanelResponsables);
		
		JPanel RPanelHeaderContenu = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcRPanelHeaderContenu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelResponsables.add(RPanelHeaderContenu, gbcRPanelHeaderContenu);
		RPanelHeaderContenu.setLayout(new BoxLayout(RPanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel RPanelTitre = new JPanelBuilder(RPanelHeaderContenu).setCustomPanel(Couleur.BLEU1).build();
		
		new JLabelBuilder(RPanelTitre).setCustomLabel("Responsables", new Font("Roboto", Font.BOLD, 30), Color.WHITE).build();
		
		JPanel RPanelRecherche = new JPanelBuilder(RPanelHeaderContenu).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flRPanelRecherche = (FlowLayout) RPanelRecherche.getLayout();
		flRPanelRecherche.setAlignment(FlowLayout.RIGHT);
		
		rechercheResponsable = new JTextFieldBuilder(RPanelRecherche).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();

		btnRechercheResponsable = new JButtonBuilder(RPanelRecherche).setCustomButton("Rechercher", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		Vue.desactiverBouton(btnRechercheResponsable);
		rechercheResponsable(rechercheResponsable);
		
		JPanel panelListeResponsables = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeResponsables = (FlowLayout) panelListeResponsables.getLayout();
		flPanelListeResponsables.setVgap(10);
		GridBagConstraints gbcPanelListeResponsables = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 1).build();
		panelResponsables.add(panelListeResponsables, gbcPanelListeResponsables);
		
		this.modeleResponsables = new DefaultListModel<String>();
		listeResponsables = new JList<String>(modeleResponsables);
		listeResponsables.setVisibleRowCount(10);
		listeResponsables.setFont(new Font("Roboto", Font.PLAIN, 13));
		listeResponsables.setFixedCellHeight(50);
		listeResponsables.setFixedCellWidth(400);
		JScrollPane scrollPane2 = new JScrollPane(listeResponsables);
		panelListeResponsables.add(scrollPane2);
		
		JPanel RPanelModification = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcRPanelModification = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 2).build();
		panelResponsables.add(RPanelModification, gbcRPanelModification);
		
		JPanel RPanelEntree = new JPanelBuilder(RPanelModification).setCustomPanel(Couleur.BLEU1).build();
		RPanelEntree.setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblNomResponsable = new JLabelBuilder(RPanelEntree).setCustomLabel("Nom responsable", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblNomResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		
		nomResponsable = new JTextFieldBuilder(RPanelEntree).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();
		
		JLabel lblPrenomResponsable = new JLabelBuilder(RPanelEntree).setCustomLabel("Prénom responsable", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblPrenomResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		
		prenomResponsable = new JTextFieldBuilder(RPanelEntree).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();
		
		JLabel lblMdpResponsable = new JLabelBuilder(RPanelEntree).setCustomLabel("Mot de passe", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblMdpResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		
		mdpResponsable = new JPasswordField();
		mdpResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(mdpResponsable);
		
		JPanel RPanelValider = new JPanelBuilder(RPanelModification).setCustomPanel(Couleur.BLEU1).build();
		RPanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton RBtnValider = new JButtonBuilder(RPanelValider).setCustomButton("Valider", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.VERT).build();
		JButton RBtnAnnuler = new JButtonBuilder(RPanelValider).setCustomButton("Annuler", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.GRIS).build();
		
		JPanel RPanelBoutons = new JPanelBuilder(panelResponsables).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcRPanelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 3).build();
		panelResponsables.add(RPanelBoutons, gbcRPanelBoutons);
		
		JButton btnCreerResponsable = new JButtonBuilder(RPanelBoutons).setCustomButton("Créer un nouveau responsable", Color.WHITE, new Font("Roboto", Font.BOLD, 12), Couleur.BLEU2).build();
		
		btnSupprimerResponsable = new JButtonBuilder(RPanelBoutons).setCustomButton("Supprimer le responsable sélectionné", Color.WHITE, new Font("Roboto", Font.BOLD, 12), Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimerResponsable);

		// ----------------------------- ARBITRE ----------------------------- //
		JPanel panelArbitres = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelArbitres.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelArbitres = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{461, 0}, new int[] {100, 549, 100, 100, 0}, new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE}).build();
		panelArbitres.setLayout(gblPanelArbitres);
		
		JPanel APanelHeaderContenu = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcAPanelHeaderContenu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelArbitres.add(APanelHeaderContenu, gbcAPanelHeaderContenu);
		APanelHeaderContenu.setLayout(new BoxLayout(APanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel APanelTitre = new JPanelBuilder(APanelHeaderContenu).setCustomPanel(Couleur.BLEU1).build();
		
		new JLabelBuilder(APanelTitre).setCustomLabel("Arbitres", new Font("Roboto", Font.BOLD, 30), Color.WHITE).build();
		
		JPanel APanelRecherche = new JPanelBuilder(APanelHeaderContenu).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flAPanelRecherche = (FlowLayout) APanelRecherche.getLayout();
		flAPanelRecherche.setAlignment(FlowLayout.RIGHT);
		
		rechercheArbitre = new JTextFieldBuilder(APanelRecherche).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();
		
		ABtnRecherche = new JButtonBuilder(APanelRecherche).setCustomButton("Rechercher", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		Vue.desactiverBouton(ABtnRecherche);
		rechercheArbitre(rechercheArbitre);
		
		JPanel panelListeArbitres = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeArbitres = (FlowLayout) panelListeArbitres.getLayout();
		flPanelListeArbitres.setVgap(10);
		GridBagConstraints gbcPanelListeArbitres = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 1).build();
		panelArbitres.add(panelListeArbitres, gbcPanelListeArbitres);
		
		this.modeleArbitres = new DefaultListModel<String>();
		listeArbitres = new JList<String>(modeleArbitres);
		listeArbitres.setVisibleRowCount(10);
		listeArbitres.setFont(new Font("Roboto", Font.PLAIN, 13));
		listeArbitres.setFixedCellHeight(50);
		listeArbitres.setFixedCellWidth(400);
		JScrollPane scrollPane3 = new JScrollPane(listeArbitres);
		panelListeArbitres.add(scrollPane3);
		
		JPanel APanelModification = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcAPanelModification = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 2).build();
		panelArbitres.add(APanelModification, gbcAPanelModification);
		
		JPanel APanelEntree = new JPanelBuilder(APanelModification).setCustomPanel(Couleur.BLEU1).build();
		APanelEntree.setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblNomArbitre = new JLabelBuilder(APanelEntree).setCustomLabel("Nom arbitre", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblNomArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		nomArbitre = new JTextFieldBuilder(APanelEntree).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();
		
		JLabel lblNewLabel = new JLabelBuilder(APanelEntree).setCustomLabel("Prénom arbitre", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		prenomArbitre = new JTextFieldBuilder(APanelEntree).setCustomTextField(new Font("Roboto", Font.PLAIN, 13), 10).build();
		
		JLabel lblMdpArbitre = new JLabelBuilder(APanelEntree).setCustomLabel("Mot de passe", new Font("Roboto", Font.BOLD, 13), Color.WHITE).build();
		lblMdpArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		mdpArbitre = new JPasswordField();
		mdpArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(mdpArbitre);
		
		JPanel APanelValider = new JPanelBuilder(APanelModification).setCustomPanel(Couleur.BLEU1).build();
		APanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton ABtnValider = new JButtonBuilder(APanelValider).setCustomButton("Valider", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.VERT).build();
		JButton ABtnAnnuler = new JButtonBuilder(APanelValider).setCustomButton("Annuler", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.GRIS).build();
		
		JPanel APanelBoutons = new JPanelBuilder(panelArbitres).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcAPanelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 3).build();
		panelArbitres.add(APanelBoutons, gbcAPanelBoutons);
		
		JButton btnCreerArbitre = new JButtonBuilder(APanelBoutons).setCustomButton("Créer un nouvel arbitre", Color.WHITE, new Font("Roboto", Font.BOLD, 12), Couleur.BLEU2).build();
		btnSupprimerArbitre = new JButtonBuilder(APanelBoutons).setCustomButton("Supprimer l'arbitre sélectionné", Color.WHITE, new Font("Roboto", Font.BOLD, 12), Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimerArbitre);
		
		ControleurERA controleur = new ControleurERA(this);
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
		EBtnValider.addActionListener(controleur);
		btnAnnulerEcurie.addActionListener(controleur);
		
		btnRechercheEcurie.setName(Entite.ECURIE.getNom());
		btnCreerEcurie.setName(Entite.ECURIE.getNom());
		btnSupprimerEcurie.setName(Entite.ECURIE.getNom());
		EBtnValider.setName(Entite.ECURIE.getNom());
		btnAnnulerEcurie.setName(Entite.ECURIE.getNom());
		
		// RESPONSABLE //
		btnRechercheResponsable.addActionListener(controleur);
		btnCreerResponsable.addActionListener(controleur);
		btnSupprimerResponsable.addActionListener(controleur);
		RBtnValider.addActionListener(controleur);
		RBtnAnnuler.addActionListener(controleur);
		
		btnRechercheResponsable.setName(Entite.RESPONSABLE.getNom());
		btnCreerResponsable.setName(Entite.RESPONSABLE.getNom());
		btnSupprimerResponsable.setName(Entite.RESPONSABLE.getNom());
		RBtnValider.setName(Entite.RESPONSABLE.getNom());
		RBtnAnnuler.setName(Entite.RESPONSABLE.getNom());
		
		// ARBITRE
		ABtnRecherche.addActionListener(controleur);
		btnCreerArbitre.addActionListener(controleur);
		btnSupprimerArbitre.addActionListener(controleur);
		ABtnValider.addActionListener(controleur);
		ABtnAnnuler.addActionListener(controleur);

		ABtnRecherche.setName(Entite.ARBITRE.getNom());
		btnCreerArbitre.setName(Entite.ARBITRE.getNom());
		btnSupprimerArbitre.setName(Entite.ARBITRE.getNom());
		ABtnValider.setName(Entite.ARBITRE.getNom());
		ABtnAnnuler.setName(Entite.ARBITRE.getNom());
		
		
		
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
		switch (ControleurERA.entite) {
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
		switch (ControleurERA.entite) {
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
		switch (ControleurERA.entite) {
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
		switch (ControleurERA.entite) {
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
		switch (ControleurERA.entite) {
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
		switch (ControleurERA.entite) {
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
		switch (ControleurERA.entite) {
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
		} else if (b.getText().equals("Rechercher")) {
			this.setEntite(b);
			return Etat.RECHERCHER;
		} else if (b.getText().equals("Valider")) {
			this.setEntite(b);
			return Etat.VALIDER;
		} else if (b.getText().equals("Annuler")) {
			this.setEntite(b);
			this.deselectionner();
			return Etat.ANNULER;
		} 
		return null;
	}

	private void setEntite(JButton b) {
		if (b.getName().equals("Ecurie")) {
			ControleurERA.entite = Entite.ECURIE;
		} else if (b.getName().equals("Responsable")) {
			ControleurERA.entite = Entite.RESPONSABLE;
		} else if (b.getName().equals("Arbitre")) {
			ControleurERA.entite = Entite.ARBITRE;
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
                	Vue.desactiverBouton(ABtnRecherche);
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if (!recherche.getText().isEmpty()) {
                	Vue.activerBouton(ABtnRecherche);
                }
            }
        });
	}
	
	
	public void setEntite(JList<String> l) {
		switch (l.getName()) {
		case "Ecurie":
			ControleurERA.entite = Entite.ECURIE;
			break;
		case "Responsable":
			ControleurERA.entite = Entite.RESPONSABLE;
			break;
		case "Arbitre":
			ControleurERA.entite = Entite.ARBITRE;
			break;
		default:
		}
	}
	
	// FILTRES
	public void filtrerRecherche() {
		switch (ControleurERA.entite) {
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
			DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
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
			DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
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
			DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
		    for (int i = 0; i < this.modeleArbitres.size(); i++) {
		    	if (this.modeleArbitres.get(i).contains(this.rechercheArbitre.getText())){
		    		modeleFiltre.addElement(this.modeleArbitres.get(i));
		    	}
		    }
		    this.listeArbitres.setModel(modeleFiltre);
		}
	}

	// MESSAGES
	public void estVide() {
        JOptionPane.showMessageDialog(null, "Veuillez compléter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
	
	public int confirmer(String operation) {
		return JOptionPane.showConfirmDialog(null, "Confirmez-vous la "+operation+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
	}
	
	public void existe() {
        JOptionPane.showMessageDialog(null, "Cet entité existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
