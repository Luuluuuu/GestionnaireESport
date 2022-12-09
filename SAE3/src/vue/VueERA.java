package vue;
import java.awt.EventQueue;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

import controleur.ControleurERA;
import controleur.ControleurERA.Etat;

import java.awt.Color;

public class VueERA {
	public JFrame fenetreERA;
	private JTextField ERecherche;
	private JTextField RRecherche;
	private JTextField ARecherche;
	
	public JList<String> listeEcuries;
	private DefaultListModel<String> modeleEcuries;
	public JList<String> listeResponsables;
	private DefaultListModel<String> modeleResponsables;
	public JList<String> listeArbitres;
	private DefaultListModel<String> modeleArbitres;
	
	private JTextField nomEcurie;
	private JPasswordField mdpEcurie;
	private JTextField nomResponsable;
	private JPasswordField mdpResponsable;
	private JTextField nomArbitre;
	private JPasswordField mdpArbitre;
	
	private JButton EBtnRecherche;
	private JButton RBtnRecherche;
	private JButton ABtnRecherche;

	public enum Entite {ECURIE,RESPONSABLE,ARBITRE};
	
	public JFrame getFrame() {
		return this.fenetreERA;
	}

	public VueERA() {
		fenetreERA = new JFrame();
		fenetreERA.setResizable(false);
		fenetreERA.setBounds(100, 100, 1400, 900);
		fenetreERA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelHeader = new JPanel();
		fenetreERA.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		FlowLayout fl_panelMenu = (FlowLayout) panelMenu.getLayout();
		fl_panelMenu.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelMenu);
		
		JButton btnCalendrier = new JButton("Calendrier");
		btnCalendrier.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnCalendrier);
		
		JButton btnERA = new JButton("Ecuries / Responsables / Arbitres");
		btnERA.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnERA);
		
		JButton btnEquipes = new JButton("Equipes");
		btnEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnEquipes);
		
		JButton btnJoueurs = new JButton("Joueurs");
		btnJoueurs.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnJoueurs);
		
		JButton btnClassement = new JButton("Classement");
		btnClassement.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnClassement);
		
		JPanel panelDeconnexion = new JPanel();
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelDeconnexion);
		
		JButton btnDeconnexion = new JButton("Se d\u00E9connecter");
		btnDeconnexion.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelDeconnexion.add(btnDeconnexion);
		
		JPanel panelContenu = new JPanel();
		fenetreERA.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panelEcuries = new JPanel();
		panelEcuries.setBackground(Color.LIGHT_GRAY);
		panelContenu.add(panelEcuries);
		GridBagLayout gbl_panelEcuries = new GridBagLayout();
		gbl_panelEcuries.columnWidths = new int[]{461, 0};
		gbl_panelEcuries.rowHeights = new int[] {100, 574, 50, 100, 0};
		gbl_panelEcuries.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelEcuries.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelEcuries.setLayout(gbl_panelEcuries);
		
		JPanel EPanelHeaderContenu = new JPanel();
		GridBagConstraints gbc_EPanelHeaderContenu = new GridBagConstraints();
		gbc_EPanelHeaderContenu.anchor = GridBagConstraints.SOUTH;
		gbc_EPanelHeaderContenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_EPanelHeaderContenu.insets = new Insets(0, 0, 5, 0);
		gbc_EPanelHeaderContenu.gridx = 0;
		gbc_EPanelHeaderContenu.gridy = 0;
		panelEcuries.add(EPanelHeaderContenu, gbc_EPanelHeaderContenu);
		EPanelHeaderContenu.setLayout(new BoxLayout(EPanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel EPanelTitre = new JPanel();
		EPanelTitre.setBackground(Color.LIGHT_GRAY);
		EPanelHeaderContenu.add(EPanelTitre);
		
		JLabel ETitre = new JLabel("Ecuries inscrites");
		ETitre.setFont(new Font("Roboto", Font.PLAIN, 18));
		EPanelTitre.add(ETitre);
		
		JPanel EPanelRecherche = new JPanel();
		EPanelRecherche.setBackground(Color.LIGHT_GRAY);
		FlowLayout fl_EPanelRecherche = (FlowLayout) EPanelRecherche.getLayout();
		fl_EPanelRecherche.setAlignment(FlowLayout.RIGHT);
		EPanelHeaderContenu.add(EPanelRecherche);
		
		ERecherche = new JTextField();
		ERecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelRecherche.add(ERecherche);
		ERecherche.setColumns(10);
		
		JButton EBtnRecherche = new JButton("Rechercher");
		EBtnRecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelRecherche.add(EBtnRecherche);
		
		JPanel panelListeEcuries = new JPanel();
		panelListeEcuries.setBackground(Color.LIGHT_GRAY);
		FlowLayout fl_panelListeEcuries = (FlowLayout) panelListeEcuries.getLayout();
		fl_panelListeEcuries.setVgap(10);
		fl_panelListeEcuries.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListeEcuries = new GridBagConstraints();
		gbc_panelListeEcuries.fill = GridBagConstraints.BOTH;
		gbc_panelListeEcuries.insets = new Insets(0, 0, 5, 0);
		gbc_panelListeEcuries.gridx = 0;
		gbc_panelListeEcuries.gridy = 1;
		panelEcuries.add(panelListeEcuries, gbc_panelListeEcuries);
		
		this.modeleEcuries = new DefaultListModel<String>();
		listeEcuries = new JList<String>(modeleEcuries);
		listeEcuries.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelListeEcuries.add(listeEcuries);
		
		JPanel EPanelModification = new JPanel();
		EPanelModification.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_EPanelModification = new GridBagConstraints();
		gbc_EPanelModification.fill = GridBagConstraints.HORIZONTAL;
		gbc_EPanelModification.insets = new Insets(0, 0, 5, 0);
		gbc_EPanelModification.gridx = 0;
		gbc_EPanelModification.gridy = 2;
		panelEcuries.add(EPanelModification, gbc_EPanelModification);
		
		JPanel EPanelEntree = new JPanel();
		EPanelEntree.setBackground(Color.LIGHT_GRAY);
		EPanelModification.add(EPanelEntree);
		EPanelEntree.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNomEcurie = new JLabel("Nom \u00E9curie");
		lblNomEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelEntree.add(lblNomEcurie);
		
		nomEcurie = new JTextField();
		nomEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelEntree.add(nomEcurie);
		nomEcurie.setColumns(10);
		
		JLabel lblMdpEcurie = new JLabel("Mot de passe");
		lblMdpEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMdpEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelEntree.add(lblMdpEcurie);
		
		mdpEcurie = new JPasswordField();
		mdpEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelEntree.add(mdpEcurie);
		
		JPanel EPanelValider = new JPanel();
		EPanelModification.add(EPanelValider);
		EPanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton EBtnValider = new JButton("Valider");
		EBtnValider.setFont(new Font("Roboto", Font.PLAIN, 11));
		EPanelValider.add(EBtnValider);
		
		JButton EBtnAnnuler = new JButton("Annuler");
		EBtnAnnuler.setFont(new Font("Roboto", Font.PLAIN, 11));
		EPanelValider.add(EBtnAnnuler);
		
		JPanel EPanelBoutons = new JPanel();
		EPanelBoutons.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_EPanelBoutons = new GridBagConstraints();
		gbc_EPanelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_EPanelBoutons.gridx = 0;
		gbc_EPanelBoutons.gridy = 3;
		panelEcuries.add(EPanelBoutons, gbc_EPanelBoutons);
		EPanelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreerEcurie = new JButton("Cr\u00E9er une nouvelle \u00E9curie");
		btnCreerEcurie.setFont(new Font("Roboto", Font.PLAIN, 11));
		EPanelBoutons.add(btnCreerEcurie);
		
		JButton btnSupprimerEcurie = new JButton("Supprimer l'\u00E9curie s\u00E9lectionn\u00E9e");
		btnSupprimerEcurie.setFont(new Font("Roboto", Font.PLAIN, 11));
		EPanelBoutons.add(btnSupprimerEcurie);
		
		JPanel panelResponsables = new JPanel();
		panelContenu.add(panelResponsables);
		GridBagLayout gbl_panelResponsables = new GridBagLayout();
		gbl_panelResponsables.columnWidths = new int[]{461, 0};
		gbl_panelResponsables.rowHeights = new int[] {100, 574, 50, 100, 0};
		gbl_panelResponsables.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelResponsables.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelResponsables.setLayout(gbl_panelResponsables);
		
		JPanel RPanelHeaderContenu = new JPanel();
		GridBagConstraints gbc_RPanelHeaderContenu = new GridBagConstraints();
		gbc_RPanelHeaderContenu.anchor = GridBagConstraints.SOUTH;
		gbc_RPanelHeaderContenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_RPanelHeaderContenu.insets = new Insets(0, 0, 5, 0);
		gbc_RPanelHeaderContenu.gridx = 0;
		gbc_RPanelHeaderContenu.gridy = 0;
		panelResponsables.add(RPanelHeaderContenu, gbc_RPanelHeaderContenu);
		RPanelHeaderContenu.setLayout(new BoxLayout(RPanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel RPanelTitre = new JPanel();
		RPanelHeaderContenu.add(RPanelTitre);
		
		JLabel RTitre = new JLabel("Responsables inscrits");
		RTitre.setFont(new Font("Roboto", Font.PLAIN, 18));
		RPanelTitre.add(RTitre);
		
		JPanel RPanelRecherche = new JPanel();
		FlowLayout fl_RPanelRecherche = (FlowLayout) RPanelRecherche.getLayout();
		fl_RPanelRecherche.setAlignment(FlowLayout.RIGHT);
		RPanelHeaderContenu.add(RPanelRecherche);
		
		RRecherche = new JTextField();
		RRecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelRecherche.add(RRecherche);
		RRecherche.setColumns(10);
		
		this.RBtnRecherche = new JButton("Rechercher");
		this.RBtnRecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelRecherche.add(this.RBtnRecherche);
		
		JPanel panelListeResponsables = new JPanel();
		FlowLayout fl_panelListeResponsables = (FlowLayout) panelListeResponsables.getLayout();
		fl_panelListeResponsables.setVgap(10);
		fl_panelListeResponsables.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListeResponsables = new GridBagConstraints();
		gbc_panelListeResponsables.fill = GridBagConstraints.BOTH;
		gbc_panelListeResponsables.insets = new Insets(0, 0, 5, 0);
		gbc_panelListeResponsables.gridx = 0;
		gbc_panelListeResponsables.gridy = 1;
		panelResponsables.add(panelListeResponsables, gbc_panelListeResponsables);
		
		this.modeleResponsables = new DefaultListModel<String>();
		listeResponsables = new JList<String>(modeleResponsables);
		listeResponsables.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelListeResponsables.add(listeResponsables);
		
		JPanel RPanelModification = new JPanel();
		GridBagConstraints gbc_RPanelModification = new GridBagConstraints();
		gbc_RPanelModification.fill = GridBagConstraints.HORIZONTAL;
		gbc_RPanelModification.insets = new Insets(0, 0, 5, 0);
		gbc_RPanelModification.gridx = 0;
		gbc_RPanelModification.gridy = 2;
		panelResponsables.add(RPanelModification, gbc_RPanelModification);
		
		JPanel RPanelEntree = new JPanel();
		RPanelModification.add(RPanelEntree);
		RPanelEntree.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNomResponsable = new JLabel("Nom responsable");
		lblNomResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(lblNomResponsable);
		
		nomResponsable = new JTextField();
		nomResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(nomResponsable);
		nomResponsable.setColumns(10);
		
		JLabel lblMdpResponsable = new JLabel("Mot de passe");
		lblMdpResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMdpResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(lblMdpResponsable);
		
		mdpResponsable = new JPasswordField();
		mdpResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(mdpResponsable);
		
		JPanel RPanelValider = new JPanel();
		RPanelModification.add(RPanelValider);
		RPanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton RBtnValider = new JButton("Valider");
		RBtnValider.setFont(new Font("Roboto", Font.PLAIN, 11));
		RPanelValider.add(RBtnValider);
		
		JButton RBtnAnnuler = new JButton("Annuler");
		RBtnAnnuler.setFont(new Font("Roboto", Font.PLAIN, 11));
		RPanelValider.add(RBtnAnnuler);
		
		JPanel RPanelBoutons = new JPanel();
		GridBagConstraints gbc_RPanelBoutons = new GridBagConstraints();
		gbc_RPanelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_RPanelBoutons.gridx = 0;
		gbc_RPanelBoutons.gridy = 3;
		panelResponsables.add(RPanelBoutons, gbc_RPanelBoutons);
		
		JButton btnCreerResponsable = new JButton("Cr\u00E9er un nouveau responsable");
		btnCreerResponsable.setFont(new Font("Roboto", Font.PLAIN, 11));
		RPanelBoutons.add(btnCreerResponsable);
		
		JButton btnSupprimerResponsable = new JButton("Supprimer le responsable s\u00E9lectionn\u00E9");
		btnSupprimerResponsable.setFont(new Font("Roboto", Font.PLAIN, 11));
		RPanelBoutons.add(btnSupprimerResponsable);
		
		JPanel panelArbitres = new JPanel();
		panelArbitres.setBackground(new Color(192, 192, 192));
		panelContenu.add(panelArbitres);
		GridBagLayout gbl_panelArbitres = new GridBagLayout();
		gbl_panelArbitres.columnWidths = new int[]{461, 0};
		gbl_panelArbitres.rowHeights = new int[] {100, 574, 50, 100, 0};
		gbl_panelArbitres.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelArbitres.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelArbitres.setLayout(gbl_panelArbitres);
		
		JPanel APanelHeaderContenu = new JPanel();
		GridBagConstraints gbc_APanelHeaderContenu = new GridBagConstraints();
		gbc_APanelHeaderContenu.anchor = GridBagConstraints.SOUTH;
		gbc_APanelHeaderContenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_APanelHeaderContenu.insets = new Insets(0, 0, 5, 0);
		gbc_APanelHeaderContenu.gridx = 0;
		gbc_APanelHeaderContenu.gridy = 0;
		panelArbitres.add(APanelHeaderContenu, gbc_APanelHeaderContenu);
		APanelHeaderContenu.setLayout(new BoxLayout(APanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel APanelTitre = new JPanel();
		APanelTitre.setBackground(new Color(192, 192, 192));
		APanelHeaderContenu.add(APanelTitre);
		
		JLabel ATitre = new JLabel("Arbitres inscrits");
		ATitre.setFont(new Font("Roboto", Font.PLAIN, 18));
		APanelTitre.add(ATitre);
		
		JPanel APanelRecherche = new JPanel();
		APanelRecherche.setBackground(new Color(192, 192, 192));
		FlowLayout fl_APanelRecherche = (FlowLayout) APanelRecherche.getLayout();
		fl_APanelRecherche.setAlignment(FlowLayout.RIGHT);
		APanelHeaderContenu.add(APanelRecherche);
		
		ARecherche = new JTextField();
		ARecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelRecherche.add(ARecherche);
		ARecherche.setColumns(10);
		
		JButton ABtnRecherche = new JButton("Rechercher");
		ABtnRecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelRecherche.add(ABtnRecherche);
		
		JPanel panelListeArbitres = new JPanel();
		panelListeArbitres.setBackground(new Color(192, 192, 192));
		FlowLayout fl_panelListeArbitres = (FlowLayout) panelListeArbitres.getLayout();
		fl_panelListeArbitres.setAlignment(FlowLayout.LEFT);
		fl_panelListeArbitres.setVgap(10);
		GridBagConstraints gbc_panelListeArbitres = new GridBagConstraints();
		gbc_panelListeArbitres.fill = GridBagConstraints.BOTH;
		gbc_panelListeArbitres.insets = new Insets(0, 0, 5, 0);
		gbc_panelListeArbitres.gridx = 0;
		gbc_panelListeArbitres.gridy = 1;
		panelArbitres.add(panelListeArbitres, gbc_panelListeArbitres);
		
		this.modeleArbitres = new DefaultListModel<String>();
		listeArbitres = new JList<String>(modeleArbitres);
		listeArbitres.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelListeArbitres.add(listeArbitres);
		
		JPanel APanelModification = new JPanel();
		APanelModification.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_APanelModification = new GridBagConstraints();
		gbc_APanelModification.fill = GridBagConstraints.HORIZONTAL;
		gbc_APanelModification.insets = new Insets(0, 0, 5, 0);
		gbc_APanelModification.gridx = 0;
		gbc_APanelModification.gridy = 2;
		panelArbitres.add(APanelModification, gbc_APanelModification);
		
		JPanel APanelEntree = new JPanel();
		APanelEntree.setBackground(new Color(192, 192, 192));
		APanelModification.add(APanelEntree);
		APanelEntree.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNomArbitre = new JLabel("Nom arbitre");
		lblNomArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(lblNomArbitre);
		
		nomArbitre = new JTextField();
		nomArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(nomArbitre);
		nomArbitre.setColumns(10);
		
		JLabel lblMdpArbitre = new JLabel("Mot de passe");
		lblMdpArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMdpArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(lblMdpArbitre);
		
		mdpArbitre = new JPasswordField();
		mdpArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(mdpArbitre);
		
		JPanel APanelValider = new JPanel();
		APanelModification.add(APanelValider);
		APanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton ABtnValider = new JButton("Valider");
		ABtnValider.setFont(new Font("Roboto", Font.PLAIN, 11));
		APanelValider.add(ABtnValider);
		
		JButton ABtnAnnuler = new JButton("Annuler");
		ABtnAnnuler.setFont(new Font("Roboto", Font.PLAIN, 11));
		APanelValider.add(ABtnAnnuler);
		
		JPanel APanelBoutons = new JPanel();
		APanelBoutons.setBackground(new Color(192, 192, 192));
		GridBagConstraints gbc_APanelBoutons = new GridBagConstraints();
		gbc_APanelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_APanelBoutons.gridx = 0;
		gbc_APanelBoutons.gridy = 3;
		panelArbitres.add(APanelBoutons, gbc_APanelBoutons);
		
		JButton btnCreerArbitre = new JButton("Cr\u00E9er un nouvel arbitre");
		btnCreerArbitre.setFont(new Font("Roboto", Font.PLAIN, 11));
		APanelBoutons.add(btnCreerArbitre);
		
		JButton btnSupprimerArbitre = new JButton("Supprimer l'arbitre s\u00E9lectionn\u00E9");
		btnSupprimerArbitre.setFont(new Font("Roboto", Font.PLAIN, 11));
		APanelBoutons.add(btnSupprimerArbitre);
		
		ControleurERA controleur = new ControleurERA(this);
		btnCalendrier.addActionListener(controleur);
		btnEquipes.addActionListener(controleur);
		btnJoueurs.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
		btnDeconnexion.addActionListener(controleur);
		
		EBtnRecherche.addActionListener(controleur);
		btnCreerEcurie.addActionListener(controleur);
		btnSupprimerEcurie.addActionListener(controleur);
		EBtnValider.addActionListener(controleur);
		EBtnAnnuler.addActionListener(controleur);
		
		RBtnRecherche.addActionListener(controleur);
		btnCreerResponsable.addActionListener(controleur);
		btnSupprimerResponsable.addActionListener(controleur);
		RBtnValider.addActionListener(controleur);
		RBtnAnnuler.addActionListener(controleur);
		
		ABtnRecherche.addActionListener(controleur);
		btnCreerArbitre.addActionListener(controleur);
		btnSupprimerArbitre.addActionListener(controleur);
		ABtnValider.addActionListener(controleur);
		ABtnAnnuler.addActionListener(controleur);
		
		this.listeEcuries.addListSelectionListener(controleur);
		this.listeResponsables.addListSelectionListener(controleur);
		this.listeArbitres.addListSelectionListener(controleur);
	}
	
	public static void fermerFenetre(JFrame f) {
		f.setVisible(false);
	}
	
	// ECURIE
	public void ajouterEcurie(String e) {
		modeleEcuries.addElement(e);
	}
	
	public void supprimerEcurie() {
		modeleEcuries.removeElement(this.listeEcuries.getSelectedValue());
	}
	
	public void modifierEcurie() {
		modeleEcuries.set(this.listeEcuries.getSelectedIndex(),this.getNomEcurie());
		this.listeEcuries.clearSelection();
	}
	
	//RESPONSABLE
	public void ajouterResponsable(String r) {
		modeleResponsables.addElement(r);
	}
	
	public void supprimerResponsable() {
		modeleResponsables.removeElement(this.listeResponsables.getSelectedValue());
	}
	
	public void modifierResponsable() {
		modeleResponsables.set(this.listeResponsables.getSelectedIndex(),this.getNomResponsable());
		this.listeResponsables.clearSelection();
	}
	
	//ARBITRE
	public void ajouterArbitre(String a) {
		modeleArbitres.addElement(a);
	}
	
	public void supprimerArbitre() {
		modeleArbitres.removeElement(this.listeArbitres.getSelectedValue());
	}
	
	public void modifierArbitre() {
		modeleArbitres.set(this.listeArbitres.getSelectedIndex(),this.getNomArbitre());
		this.listeArbitres.clearSelection();
	}
	
	// GETTERS //
	public String getNomEcurie() {return this.nomEcurie.getText();}
	public String getNomResponsable() {return this.nomResponsable.getText();}
	public String getNomArbitre() {return this.nomArbitre.getText();}
	
	public String getRechercheEcurie() {return this.ERecherche.getText();}
	public String getRechercheResponsable() {return this.RRecherche.getText();}
	public String getRechercheArbitre() {return this.ARecherche.getText();}
	
	public String getNomSelectionne() {return this.listeEcuries.getSelectedValue();}
	
	public String getMotDePasseEcurie() {return String.valueOf(this.mdpEcurie.getPassword());}
		
	// SETTERS //
	public void setDefaultListModel() {
		this.listeEcuries.setModel(modeleEcuries);
	}

	public void setNomSelectionneEcurie() {
		this.nomEcurie.setText(this.listeEcuries.getSelectedValue());
	}
	
	public void setNomSelectionneResponsable() {
		this.nomResponsable.setText(this.listeResponsables.getSelectedValue());
	}
	
	public void setNomSelectionneArbitre() {
		this.nomArbitre.setText(this.listeArbitres.getSelectedValue());
	}
	
	public void setNomEcurie(String nom) {
		this.nomEcurie.setText(nom);
	}
	
	public void setNomResponsable(String nom) {
		this.nomResponsable.setText(nom);
	}
	
	public void setNomArbitre(String nom) {
		this.nomArbitre.setText(nom);
	}
	
	public void viderMotDePasse() {
		this.mdpEcurie.setText("");
		this.mdpResponsable.setText("");
		this.mdpArbitre.setText("");
	}
	
	// LISTE //
	public boolean estSelectionne() {
		return !(this.listeEcuries.isSelectionEmpty());
	}
	
	// ETATS
	public Etat getEtat(JButton b) {
		System.out.println(b.getActionCommand());
		
		if (b.getText() == "Créer une nouvelle écurie") {
			this.listeEcuries.clearSelection();
			return Etat.CREER;
		} else if (b.getText() == "Modifier l'écurie sélectionnée") {
			return Etat.MODIFIER;
		} else if (b.getText() == "Supprimer l'écurie sélectionnée") {
			return Etat.SUPPRIMER;
		} else if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Calendrier") {
			return Etat.CALENDRIER;
		} else if (b.getText() == "Joueurs") {
			return Etat.JOUEURS;
		} else if (b.getText() == "Classement") {
			return Etat.CLASSEMENT;
		} else if (b.getText() == "Rechercher") {
			return Etat.RECHERCHER;
		} else if (b.getText() == "Valider") {
			return Etat.VALIDER;
		} else if (b.getText() == "Annuler") {
			this.listeEcuries.clearSelection();
			return Etat.ANNULER;
		} 
		return null;
	}
	
	// FILTRES
	public void filtrerRechercheEcurie() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleEcuries.size(); i++) {
	    	if (this.modeleEcuries.get(i).contains(this.ERecherche.getText())){
	    		modeleFiltre.addElement(this.modeleEcuries.get(i));
	    	}
	    }
	    this.listeEcuries.setModel(modeleFiltre);
	}
	
	public void filtrerRechercheResponsable() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleResponsables.size(); i++) {
	    	if (this.modeleResponsables.get(i).contains(this.RRecherche.getText())){
	    		modeleFiltre.addElement(this.modeleResponsables.get(i));
	    	}
	    }
	    this.listeResponsables.setModel(modeleFiltre);
	}
	
	public void filtrerRechercheArbitre() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleArbitres.size(); i++) {
	    	if (this.modeleArbitres.get(i).contains(this.ARecherche.getText())){
	    		modeleFiltre.addElement(this.modeleArbitres.get(i));
	    	}
	    }
	    this.listeArbitres.setModel(modeleFiltre);
	}

	// MESSAGES
	public void estVide() {
        JOptionPane.showMessageDialog(null, "Veuillez compléter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
	
	public int confirmer(String operation) {
		return JOptionPane.showConfirmDialog(null, "Confirmez-vous la "+operation+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
	}
	
	public void tournoiExiste() {
        JOptionPane.showMessageDialog(null, "L'écurie existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
