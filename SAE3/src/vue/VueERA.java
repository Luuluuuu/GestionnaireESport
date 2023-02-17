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

import java.awt.Color;
import javax.swing.border.LineBorder;

public class VueERA {
	public JFrame fenetreERA;
	private JTextField rechercheEcurie;
	private JTextField rechercheResponsable;
	private JTextField rechercheArbitre;
	
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
	private JTextField prenomResponsable;
	private JTextField prenomArbitre;

	public JFrame getFrame() {
		return this.fenetreERA;
	}

	public VueERA() {
		fenetreERA = new JFrame();
		fenetreERA.getContentPane().setBackground(Couleur.BLEU1);
		fenetreERA.setResizable(false);
		fenetreERA.setBounds(100, 100, 1400, 900);
		fenetreERA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fenetreERA.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		FlowLayout fl_panelMenu = (FlowLayout) panelMenu.getLayout();
		fl_panelMenu.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelMenu);
		
		JButton btnCalendrier = new JButton("Calendrier");
		btnCalendrier.setForeground(Color.WHITE);
		btnCalendrier.setFont(new Font("Roboto", Font.BOLD, 15));
		btnCalendrier.setBackground(Couleur.BLEU2);
		panelMenu.add(btnCalendrier);
		
		JButton btnERA = new JButton("Ecuries / Responsables / Arbitres");
		btnERA.setForeground(Color.WHITE);
		btnERA.setFont(new Font("Roboto", Font.BOLD, 15));
		btnERA.setBackground(Couleur.BLEU2);
		panelMenu.add(btnERA);
		
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
		
		JButton btnDeconnexion = new JButton("Se déconnecter");
		btnDeconnexion.setForeground(Color.WHITE);
		btnDeconnexion.setFont(new Font("Roboto", Font.BOLD, 13));
		btnDeconnexion.setBackground(Couleur.ROUGE);
		panelDeconnexion.add(btnDeconnexion);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreERA.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 3, 0, 0));
		
		// ----------------------------- ECURIE ----------------------------- //
		JPanel panelEcuries = new JPanel();
		panelEcuries.setBorder(new LineBorder(Color.WHITE));
		panelEcuries.setBackground(Couleur.BLEU1);
		panelContenu.add(panelEcuries);
		GridBagLayout gbl_panelEcuries = new GridBagLayout();
		gbl_panelEcuries.columnWidths = new int[]{461, 0};
		gbl_panelEcuries.rowHeights = new int[] {100, 549, 100, 100, 0};
		gbl_panelEcuries.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelEcuries.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelEcuries.setLayout(gbl_panelEcuries);
		
		JPanel EPanelHeaderContenu = new JPanel();
		EPanelHeaderContenu.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_EPanelHeaderContenu = new GridBagConstraints();
		gbc_EPanelHeaderContenu.anchor = GridBagConstraints.SOUTH;
		gbc_EPanelHeaderContenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_EPanelHeaderContenu.insets = new Insets(0, 0, 5, 0);
		gbc_EPanelHeaderContenu.gridx = 0;
		gbc_EPanelHeaderContenu.gridy = 0;
		panelEcuries.add(EPanelHeaderContenu, gbc_EPanelHeaderContenu);
		EPanelHeaderContenu.setLayout(new BoxLayout(EPanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel EPanelTitre = new JPanel();
		EPanelTitre.setBackground(Couleur.BLEU1);
		EPanelHeaderContenu.add(EPanelTitre);
		
		JLabel ETitre = new JLabel("Ecuries inscrites");
		ETitre.setForeground(Color.WHITE);
		ETitre.setFont(new Font("Roboto", Font.BOLD, 20));
		EPanelTitre.add(ETitre);
		
		JPanel EPanelRecherche = new JPanel();
		EPanelRecherche.setBackground(Couleur.BLEU1);
		FlowLayout fl_EPanelRecherche = (FlowLayout) EPanelRecherche.getLayout();
		fl_EPanelRecherche.setAlignment(FlowLayout.RIGHT);
		EPanelHeaderContenu.add(EPanelRecherche);
		
		rechercheEcurie = new JTextField();
		rechercheEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelRecherche.add(rechercheEcurie);
		rechercheEcurie.setColumns(10);
		
		JButton btnRechercheEcurie = new JButton("Rechercher");
		btnRechercheEcurie.setForeground(Color.WHITE);
		btnRechercheEcurie.setFont(new Font("Roboto", Font.BOLD, 13));
		btnRechercheEcurie.setBackground(Couleur.BLEU2);
		EPanelRecherche.add(btnRechercheEcurie);
		
		JPanel panelListeEcuries = new JPanel();
		panelListeEcuries.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListeEcuries = (FlowLayout) panelListeEcuries.getLayout();
		fl_panelListeEcuries.setVgap(10);
		GridBagConstraints gbc_panelListeEcuries = new GridBagConstraints();
		gbc_panelListeEcuries.fill = GridBagConstraints.BOTH;
		gbc_panelListeEcuries.insets = new Insets(0, 0, 5, 0);
		gbc_panelListeEcuries.gridx = 0;
		gbc_panelListeEcuries.gridy = 1;
		panelEcuries.add(panelListeEcuries, gbc_panelListeEcuries);
		
		this.modeleEcuries = new DefaultListModel<String>();
		listeEcuries = new JList<String>(modeleEcuries);
		listeEcuries.setVisibleRowCount(10);
		listeEcuries.setFont(new Font("Roboto", Font.PLAIN, 13));
		listeEcuries.setFixedCellHeight(50);
		listeEcuries.setFixedCellWidth(400);
		JScrollPane scrollPane = new JScrollPane(listeEcuries);
		panelListeEcuries.add(scrollPane);
		
		JPanel EPanelModification = new JPanel();
		EPanelModification.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_EPanelModification = new GridBagConstraints();
		gbc_EPanelModification.fill = GridBagConstraints.HORIZONTAL;
		gbc_EPanelModification.insets = new Insets(0, 0, 5, 0);
		gbc_EPanelModification.gridx = 0;
		gbc_EPanelModification.gridy = 2;
		panelEcuries.add(EPanelModification, gbc_EPanelModification);
		
		JPanel EPanelEntree = new JPanel();
		EPanelEntree.setBackground(Couleur.BLEU1);
		EPanelModification.add(EPanelEntree);
		EPanelEntree.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNomEcurie = new JLabel("Nom écurie");
		lblNomEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomEcurie.setFont(new Font("Roboto", Font.BOLD, 13));
		EPanelEntree.add(lblNomEcurie);
		
		nomEcurie = new JTextField();
		nomEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelEntree.add(nomEcurie);
		nomEcurie.setColumns(10);
		
		JLabel lblMdpEcurie = new JLabel("Mot de passe");
		lblMdpEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMdpEcurie.setFont(new Font("Roboto", Font.BOLD, 13));
		EPanelEntree.add(lblMdpEcurie);
		
		mdpEcurie = new JPasswordField();
		mdpEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		EPanelEntree.add(mdpEcurie);
		
		JPanel EPanelValider = new JPanel();
		EPanelModification.add(EPanelValider);
		EPanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton EBtnValider = new JButton("Valider");
		EBtnValider.setForeground(Color.WHITE);
		EBtnValider.setFont(new Font("Roboto", Font.BOLD, 13));
		EBtnValider.setBackground(Couleur.VERT);
		EPanelValider.add(EBtnValider);
		
		JButton btnAnnulerEcurie = new JButton("Annuler");
		btnAnnulerEcurie.setForeground(Color.WHITE);
		btnAnnulerEcurie.setFont(new Font("Roboto", Font.BOLD, 13));
		btnAnnulerEcurie.setBackground(Couleur.GRIS);
		EPanelValider.add(btnAnnulerEcurie);
		
		JPanel EPanelBoutons = new JPanel();
		EPanelBoutons.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_EPanelBoutons = new GridBagConstraints();
		gbc_EPanelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_EPanelBoutons.gridx = 0;
		gbc_EPanelBoutons.gridy = 3;
		panelEcuries.add(EPanelBoutons, gbc_EPanelBoutons);
		EPanelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCreerEcurie = new JButton("Créer une nouvelle écurie");
		btnCreerEcurie.setForeground(Color.WHITE);
		btnCreerEcurie.setFont(new Font("Roboto", Font.BOLD, 12));
		btnCreerEcurie.setBackground(Couleur.BLEU2);
		EPanelBoutons.add(btnCreerEcurie);
		
		JButton btnSupprimerEcurie = new JButton("Supprimer l'écurie sélectionnée");
		btnSupprimerEcurie.setForeground(Color.WHITE);
		btnSupprimerEcurie.setFont(new Font("Roboto", Font.BOLD, 12));
		btnSupprimerEcurie.setBackground(Couleur.GRIS);
		EPanelBoutons.add(btnSupprimerEcurie);

		// ----------------------------- RESPONSABLE ----------------------------- //
		JPanel panelResponsables = new JPanel();
		panelResponsables.setBorder(new LineBorder(Color.WHITE));
		panelResponsables.setBackground(Couleur.BLEU1);
		panelContenu.add(panelResponsables);
		GridBagLayout gbl_panelResponsables = new GridBagLayout();
		gbl_panelResponsables.columnWidths = new int[]{461, 0};
		gbl_panelResponsables.rowHeights = new int[] {100, 549, 100, 100, 0};
		gbl_panelResponsables.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelResponsables.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		panelResponsables.setLayout(gbl_panelResponsables);
		
		JPanel RPanelHeaderContenu = new JPanel();
		RPanelHeaderContenu.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_RPanelHeaderContenu = new GridBagConstraints();
		gbc_RPanelHeaderContenu.anchor = GridBagConstraints.SOUTH;
		gbc_RPanelHeaderContenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_RPanelHeaderContenu.insets = new Insets(0, 0, 5, 0);
		gbc_RPanelHeaderContenu.gridx = 0;
		gbc_RPanelHeaderContenu.gridy = 0;
		panelResponsables.add(RPanelHeaderContenu, gbc_RPanelHeaderContenu);
		RPanelHeaderContenu.setLayout(new BoxLayout(RPanelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel RPanelTitre = new JPanel();
		RPanelTitre.setBackground(Couleur.BLEU1);
		@SuppressWarnings("unused")
		FlowLayout flowLayout_1 = (FlowLayout) RPanelTitre.getLayout();
		RPanelHeaderContenu.add(RPanelTitre);
		
		JLabel RTitre = new JLabel("Responsables inscrits");
		RTitre.setForeground(Color.WHITE);
		RTitre.setFont(new Font("Roboto", Font.BOLD, 20));
		RPanelTitre.add(RTitre);
		
		JPanel RPanelRecherche = new JPanel();
		RPanelRecherche.setBackground(Couleur.BLEU1);
		FlowLayout fl_RPanelRecherche = (FlowLayout) RPanelRecherche.getLayout();
		fl_RPanelRecherche.setAlignment(FlowLayout.RIGHT);
		RPanelHeaderContenu.add(RPanelRecherche);
		
		rechercheResponsable = new JTextField();
		rechercheResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelRecherche.add(rechercheResponsable);
		rechercheResponsable.setColumns(10);
		
		JButton btnRechercheResponsable = new JButton("Rechercher");
		btnRechercheResponsable.setForeground(Color.WHITE);
		btnRechercheResponsable.setFont(new Font("Roboto", Font.BOLD, 13));
		btnRechercheResponsable.setBackground(Couleur.BLEU2);
		RPanelRecherche.add(btnRechercheResponsable);
		
		JPanel panelListeResponsables = new JPanel();
		panelListeResponsables.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListeResponsables = (FlowLayout) panelListeResponsables.getLayout();
		fl_panelListeResponsables.setVgap(10);
		GridBagConstraints gbc_panelListeResponsables = new GridBagConstraints();
		gbc_panelListeResponsables.fill = GridBagConstraints.BOTH;
		gbc_panelListeResponsables.insets = new Insets(0, 0, 5, 0);
		gbc_panelListeResponsables.gridx = 0;
		gbc_panelListeResponsables.gridy = 1;
		panelResponsables.add(panelListeResponsables, gbc_panelListeResponsables);
		
		this.modeleResponsables = new DefaultListModel<String>();
		listeResponsables = new JList<String>(modeleResponsables);
		listeResponsables.setVisibleRowCount(10);
		listeResponsables.setFont(new Font("Roboto", Font.PLAIN, 13));
		listeResponsables.setFixedCellHeight(50);
		listeResponsables.setFixedCellWidth(400);
		JScrollPane scrollPane2 = new JScrollPane(listeResponsables);
		panelListeResponsables.add(scrollPane2);
		
		JPanel RPanelModification = new JPanel();
		RPanelModification.setBackground(Couleur.BLEU1);
		@SuppressWarnings("unused")
		FlowLayout flowLayout = (FlowLayout) RPanelModification.getLayout();
		GridBagConstraints gbc_RPanelModification = new GridBagConstraints();
		gbc_RPanelModification.fill = GridBagConstraints.HORIZONTAL;
		gbc_RPanelModification.insets = new Insets(0, 0, 5, 0);
		gbc_RPanelModification.gridx = 0;
		gbc_RPanelModification.gridy = 2;
		panelResponsables.add(RPanelModification, gbc_RPanelModification);
		
		JPanel RPanelEntree = new JPanel();
		RPanelEntree.setBackground(Couleur.BLEU1);
		RPanelModification.add(RPanelEntree);
		RPanelEntree.setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblNomResponsable = new JLabel("Nom responsable");
		lblNomResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomResponsable.setFont(new Font("Roboto", Font.BOLD, 13));
		RPanelEntree.add(lblNomResponsable);
		
		nomResponsable = new JTextField();
		nomResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(nomResponsable);
		nomResponsable.setColumns(10);
		
		JLabel lblPrenomResponsable = new JLabel("Prénom responsable");
		lblPrenomResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrenomResponsable.setFont(new Font("Roboto", Font.BOLD, 13));
		RPanelEntree.add(lblPrenomResponsable);
		
		prenomResponsable = new JTextField();
		prenomResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(prenomResponsable);
		prenomResponsable.setColumns(10);
		
		JLabel lblMdpResponsable = new JLabel("Mot de passe");
		lblMdpResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMdpResponsable.setFont(new Font("Roboto", Font.BOLD, 13));
		RPanelEntree.add(lblMdpResponsable);
		
		mdpResponsable = new JPasswordField();
		mdpResponsable.setFont(new Font("Roboto", Font.PLAIN, 13));
		RPanelEntree.add(mdpResponsable);
		
		JPanel RPanelValider = new JPanel();
		RPanelModification.add(RPanelValider);
		RPanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton RBtnValider = new JButton("Valider");
		RBtnValider.setForeground(Color.WHITE);
		RBtnValider.setFont(new Font("Roboto", Font.BOLD, 13));
		RBtnValider.setBackground(Couleur.VERT);
		RPanelValider.add(RBtnValider);
		
		JButton RBtnAnnuler = new JButton("Annuler");
		RBtnAnnuler.setForeground(Color.WHITE);
		RBtnAnnuler.setFont(new Font("Roboto", Font.BOLD, 13));
		RBtnAnnuler.setBackground(Couleur.GRIS);
		RPanelValider.add(RBtnAnnuler);
		
		JPanel RPanelBoutons = new JPanel();
		@SuppressWarnings("unused")
		FlowLayout flowLayout_2 = (FlowLayout) RPanelBoutons.getLayout();
		RPanelBoutons.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_RPanelBoutons = new GridBagConstraints();
		gbc_RPanelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_RPanelBoutons.gridx = 0;
		gbc_RPanelBoutons.gridy = 3;
		panelResponsables.add(RPanelBoutons, gbc_RPanelBoutons);
		
		JButton btnCreerResponsable = new JButton("Créer un nouveau responsable");
		btnCreerResponsable.setForeground(Color.WHITE);
		btnCreerResponsable.setFont(new Font("Roboto", Font.BOLD, 12));
		btnCreerResponsable.setBackground(Couleur.BLEU2);
		RPanelBoutons.add(btnCreerResponsable);
		
		JButton btnSupprimerResponsable = new JButton("Supprimer le responsable sélectionné");
		btnSupprimerResponsable.setForeground(Color.WHITE);
		btnSupprimerResponsable.setFont(new Font("Roboto", Font.BOLD, 12));
		btnSupprimerResponsable.setBackground(Couleur.GRIS);
		RPanelBoutons.add(btnSupprimerResponsable);

		// ----------------------------- ARBITRE ----------------------------- //
		JPanel panelArbitres = new JPanel();
		panelArbitres.setBorder(new LineBorder(Color.WHITE));
		panelArbitres.setBackground(Couleur.BLEU1);
		panelContenu.add(panelArbitres);
		GridBagLayout gbl_panelArbitres = new GridBagLayout();
		gbl_panelArbitres.columnWidths = new int[]{461, 0};
		gbl_panelArbitres.rowHeights = new int[] {100, 549, 100, 100, 0};
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
		APanelTitre.setBackground(Couleur.BLEU1);
		APanelHeaderContenu.add(APanelTitre);
		
		JLabel ATitre = new JLabel("Arbitres inscrits");
		ATitre.setForeground(Color.WHITE);
		ATitre.setFont(new Font("Roboto", Font.BOLD, 20));
		APanelTitre.add(ATitre);
		
		JPanel APanelRecherche = new JPanel();
		APanelRecherche.setBackground(Couleur.BLEU1);
		FlowLayout fl_APanelRecherche = (FlowLayout) APanelRecherche.getLayout();
		fl_APanelRecherche.setAlignment(FlowLayout.RIGHT);
		APanelHeaderContenu.add(APanelRecherche);
		
		rechercheArbitre = new JTextField();
		rechercheArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelRecherche.add(rechercheArbitre);
		rechercheArbitre.setColumns(10);
		
		JButton ABtnRecherche = new JButton("Rechercher");
		ABtnRecherche.setForeground(Color.WHITE);
		ABtnRecherche.setFont(new Font("Roboto", Font.BOLD, 13));
		ABtnRecherche.setBackground(Couleur.BLEU2);
		APanelRecherche.add(ABtnRecherche);
		
		JPanel panelListeArbitres = new JPanel();
		panelListeArbitres.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListeArbitres = (FlowLayout) panelListeArbitres.getLayout();
		fl_panelListeArbitres.setVgap(10);
		GridBagConstraints gbc_panelListeArbitres = new GridBagConstraints();
		gbc_panelListeArbitres.fill = GridBagConstraints.BOTH;
		gbc_panelListeArbitres.insets = new Insets(0, 0, 5, 0);
		gbc_panelListeArbitres.gridx = 0;
		gbc_panelListeArbitres.gridy = 1;
		panelArbitres.add(panelListeArbitres, gbc_panelListeArbitres);
		
		this.modeleArbitres = new DefaultListModel<String>();
		listeArbitres = new JList<String>(modeleArbitres);
		listeArbitres.setVisibleRowCount(10);
		listeArbitres.setFont(new Font("Roboto", Font.PLAIN, 13));
		listeArbitres.setFixedCellHeight(50);
		listeArbitres.setFixedCellWidth(400);
		JScrollPane scrollPane3 = new JScrollPane(listeArbitres);
		panelListeArbitres.add(scrollPane3);
		
		JPanel APanelModification = new JPanel();
		APanelModification.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_APanelModification = new GridBagConstraints();
		gbc_APanelModification.fill = GridBagConstraints.HORIZONTAL;
		gbc_APanelModification.insets = new Insets(0, 0, 5, 0);
		gbc_APanelModification.gridx = 0;
		gbc_APanelModification.gridy = 2;
		panelArbitres.add(APanelModification, gbc_APanelModification);
		
		JPanel APanelEntree = new JPanel();
		APanelEntree.setBackground(Couleur.BLEU1);
		APanelModification.add(APanelEntree);
		APanelEntree.setLayout(new GridLayout(0, 2, 10, 5));
		
		JLabel lblNomArbitre = new JLabel("Nom arbitre");
		lblNomArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomArbitre.setFont(new Font("Roboto", Font.BOLD, 13));
		APanelEntree.add(lblNomArbitre);
		
		nomArbitre = new JTextField();
		nomArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(nomArbitre);
		nomArbitre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Prénom arbitre");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 13));
		APanelEntree.add(lblNewLabel);

		prenomArbitre = new JTextField();
		prenomArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(prenomArbitre);
		prenomArbitre.setColumns(10);
		
		JLabel lblMdpArbitre = new JLabel("Mot de passe");
		lblMdpArbitre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMdpArbitre.setFont(new Font("Roboto", Font.BOLD, 13));
		APanelEntree.add(lblMdpArbitre);
		
		mdpArbitre = new JPasswordField();
		mdpArbitre.setFont(new Font("Roboto", Font.PLAIN, 13));
		APanelEntree.add(mdpArbitre);
		
		JPanel APanelValider = new JPanel();
		APanelModification.add(APanelValider);
		APanelValider.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton ABtnValider = new JButton("Valider");
		ABtnValider.setForeground(Color.WHITE);
		ABtnValider.setFont(new Font("Roboto", Font.BOLD, 13));
		ABtnValider.setBackground(Couleur.VERT);
		APanelValider.add(ABtnValider);
		
		JButton ABtnAnnuler = new JButton("Annuler");
		ABtnAnnuler.setForeground(Color.WHITE);
		ABtnAnnuler.setFont(new Font("Roboto", Font.BOLD, 13));
		ABtnAnnuler.setBackground(Couleur.GRIS);
		APanelValider.add(ABtnAnnuler);
		
		JPanel APanelBoutons = new JPanel();
		APanelBoutons.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_APanelBoutons = new GridBagConstraints();
		gbc_APanelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_APanelBoutons.gridx = 0;
		gbc_APanelBoutons.gridy = 3;
		panelArbitres.add(APanelBoutons, gbc_APanelBoutons);
		
		JButton btnCreerArbitre = new JButton("Créer un nouvel arbitre");
		btnCreerArbitre.setForeground(Color.WHITE);
		btnCreerArbitre.setFont(new Font("Roboto", Font.BOLD, 12));
		btnCreerArbitre.setBackground(Couleur.BLEU2);
		APanelBoutons.add(btnCreerArbitre);
		
		JButton btnSupprimerArbitre = new JButton("Supprimer l'arbitre sélectionné");
		btnSupprimerArbitre.setForeground(Color.WHITE);
		btnSupprimerArbitre.setFont(new Font("Roboto", Font.BOLD, 12));
		btnSupprimerArbitre.setBackground(Couleur.GRIS);
		APanelBoutons.add(btnSupprimerArbitre);
		
		ControleurERA controleur = new ControleurERA(this);
		btnCalendrier.addActionListener(controleur);
		btnEquipes.addActionListener(controleur);
		btnJoueurs.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
		btnDeconnexion.addActionListener(controleur);		
		
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
	public void ajouterEntite(String e) {
		switch (ControleurERA.entite) {
		case ECURIE:
			ajouterEcurie(e);
			break;
		case RESPONSABLE:
			ajouterResponsable(e);
			break;
		case ARBITRE:
			ajouterArbitre(e);
			break;
		default:
		}
	}
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
			supprimerEcurie();
			break;
		case RESPONSABLE:
			supprimerResponsable();
			break;
		case ARBITRE:
			supprimerArbitre();
			break;
		default:
		}
	}
	public void supprimerEcurie() {
		modeleEcuries.removeElement(this.listeEcuries.getSelectedValue());
	}
	public void supprimerResponsable() {
		modeleResponsables.removeElement(this.listeResponsables.getSelectedValue());
	}
	public void supprimerArbitre() {
		modeleArbitres.removeElement(this.listeArbitres.getSelectedValue());
	}
	
	// MODIFIER UNE ENTITE //
	public void modifierEntite() {
		switch (ControleurERA.entite) {
		case ECURIE:
			modifierEcurie();
			break;
		case RESPONSABLE:
			modifierResponsable();
			break;
		case ARBITRE:
			modifierArbitre();
			break;
		default:
		}
	}
	public void modifierEcurie() {
		modeleEcuries.set(this.listeEcuries.getSelectedIndex(),this.getNomEcurie());
		this.listeEcuries.clearSelection();
	}
	public void modifierResponsable() {
		modeleResponsables.set(this.listeResponsables.getSelectedIndex(), this.getPrenomResponsable() + " " + this.getNomResponsable());
		this.listeResponsables.clearSelection();
	}
	public void modifierArbitre() {
		modeleArbitres.set(this.listeArbitres.getSelectedIndex(),this.getPrenomArbitre() + " " + this.getNomArbitre());
		this.listeArbitres.clearSelection();
	}
	
	// GETTERS //
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
		this.setEntite(b);
		if (b.getText().contains("CrÃ©er")) {
			this.deselectionner();
			return Etat.CREER;
		} else if (b.getText().contains("Modifier")) {
			return Etat.MODIFIER;
		} else if (b.getText().contains("Supprimer")) {
			return Etat.SUPPRIMER;
		} else if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Calendrier") {
			return Etat.CALENDRIER;
		} else if (b.getText() == "Joueurs") {
			return Etat.JOUEURS;
		} else if (b.getText() == "Equipes") {
				return Etat.EQUIPES ;
		} else if (b.getText() == "Classement") {
			return Etat.CLASSEMENT;
		} else if (b.getText() == "Rechercher") {
			return Etat.RECHERCHER;
		} else if (b.getText() == "Valider") {
			return Etat.VALIDER;
		} else if (b.getText() == "Annuler") {
			this.deselectionner();
			return Etat.ANNULER;
		} 
		return null;
	}

	private void setEntite(JButton b) {
		if (b.getName() == "Ecurie") {
			ControleurERA.entite = Entite.ECURIE;
		} else if (b.getName() == "Responsable") {
			ControleurERA.entite = Entite.RESPONSABLE;
		} else if (b.getName() == "Arbitre") {
			ControleurERA.entite = Entite.ARBITRE;
		}
		
		
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
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleEcuries.size(); i++) {
	    	if (this.modeleEcuries.get(i).contains(this.rechercheEcurie.getText())){
	    		modeleFiltre.addElement(this.modeleEcuries.get(i));
	    	}
	    }
	    this.listeEcuries.setModel(modeleFiltre);
	}
	public void filtrerRechercheResponsable() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleResponsables.size(); i++) {
	    	if (this.modeleResponsables.get(i).contains(this.rechercheResponsable.getText())){
	    		modeleFiltre.addElement(this.modeleResponsables.get(i));
	    	}
	    }
	    this.listeResponsables.setModel(modeleFiltre);
	}
	public void filtrerRechercheArbitre() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleArbitres.size(); i++) {
	    	if (this.modeleArbitres.get(i).contains(this.rechercheArbitre.getText())){
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
	
	public void existe() {
        JOptionPane.showMessageDialog(null, "Cet entité existe déjà  !", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
