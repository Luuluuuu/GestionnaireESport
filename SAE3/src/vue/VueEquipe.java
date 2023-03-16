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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;


import modele.Etat;
import modele.EtatFactory;
import modele.GridBagConstraintsBuilder;
import modele.GridBagLayoutBuilder;
import modele.JButtonBuilder;
import modele.JLabelBuilder;
import modele.JPanelBuilder;
import modele.JTextFieldBuilder;
import controleur.ControleurEquipe;

public class VueEquipe{
	
	public JFrame fenetreEquipe;

	public JLabel titreModif;
	
	public JPanel panelModif;
	private static JPanel panel_13;

	private JTextField entreeNom;
	private JTextField recherche;
	private JTextField entreeNationalite;
	
	private DefaultListModel<String> modeleEquipes= new DefaultListModel<String>();
	private JList<String> listeEquipes = new JList<String>(modeleEquipes);
	private DefaultListModel<String> modeleJoueurs;
	private JList<String> listeJoueurs;
	
	private JComboBox<String> entreeEcurie = new JComboBox<String>();
	private JComboBox<String> entreeJeu = new JComboBox<String>();
	
	private JButton btnSupprimer;

	private JButton btnRechercher;
	
	public JFrame getFrame() {
		return this.fenetreEquipe;
	}
	
	public VueEquipe() {
		// CREATION DE LA FENETRE //
		fenetreEquipe = new JFrame();
		fenetreEquipe.getContentPane().setBackground(Couleur.BLEU1);
		fenetreEquipe.setResizable(false);
		fenetreEquipe.setBounds(100, 100, 1500, 880);
		fenetreEquipe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// CONTROLEUR
		ControleurEquipe controleur = new ControleurEquipe(this);
		
		// HEADER //


		// Boutons du menu
		if (ControleurEquipe.estProfil("Gestionnaire")) {
			HeaderAdmin header = new HeaderAdmin(this.getFrame());
			header.getBtnDeconnexion().addActionListener(controleur);
			header.getBtnEquipes().addActionListener(controleur);
			header.getBtnJoueurs().addActionListener(controleur);
			header.getBtnClassement().addActionListener(controleur);

			header.getBtnCalendrier().addActionListener(controleur);
			header.getBtnEcuries().addActionListener(controleur);
			Vue.desactiverBouton(header.getBtnEquipes());
		}

		if (ControleurEquipe.estProfil("Ecurie")) {
			HeaderEcurie header = new HeaderEcurie(this.getFrame());
			header.getBtnDeconnexion().addActionListener(controleur);
			header.getBtnEquipes().addActionListener(controleur);
			header.getBtnJoueurs().addActionListener(controleur);
			header.getBtnClassement().addActionListener(controleur);
			
			header.getBtnTournois().addActionListener(controleur);
		}
		
		

		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreEquipe.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelEquipe = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		GridBagLayout gbl_panelEquipe = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {100, 622, 100, 0}, 
										new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE}).build();
		panelEquipe.setLayout(gbl_panelEquipe);
		
		JPanel panelTitreT = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelTitreT = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelEquipe.add(panelTitreT, gbc_panelTitreT);
		panelTitreT.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTitre = new JPanelBuilder(panelTitreT).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_3 = (FlowLayout) panelTitre.getLayout();
		flowLayout_3.setHgap(50);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		
		// LISTE DES TOURNOIS //
		new JLabelBuilder(panelTitre).setCustomLabel("Equipes", new Font("Roboto", Font.BOLD, 36), Color.WHITE).build();
		
		JPanel panelRecherche = new JPanelBuilder(panelTitreT).setCustomPanel(Couleur.BLEU1).build();
		
		recherche = new JTextFieldBuilder(panelRecherche).setCustomTextField(new Font("Roboto",Font.PLAIN, 13), 15).build();
		
		btnRechercher = new JButtonBuilder(panelRecherche).setCustomButton("Rechercher", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		desactiverBouton(btnRechercher);
		rechercheEquipe(recherche);

		JPanel panelListe = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout fl_panelListe = (FlowLayout) panelListe.getLayout();
		fl_panelListe.setHgap(50);
		fl_panelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 1).build();
		panelEquipe.add(panelListe, gbc_panelListe);
		
		listeEquipes.setVisibleRowCount(12);
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		listeEquipes.setName("Equipe");
		JScrollPane scrollPane = new JScrollPane(this.listeEquipes);
		panelListe.add(scrollPane);

		JPanel panelBoutons = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 2).build();
		panelEquipe.add(panelBoutons, gbc_panelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		// Création des boutons de gestion
		JButton btnCreer = new JButtonBuilder(panelBoutons).setCustomButton("Créer une nouvelle équipe", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.BLEU2).build();
		btnSupprimer = new JButtonBuilder(panelBoutons).setCustomButton("Supprimer l'équipe sélectionnée", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.GRIS).build();
		this.desactiverBouton(btnSupprimer);
		
		// CREER OU MODIFIER UN TOURNOI
		panelModif = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		
		GridBagLayout gbl_panelModif = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {100, 70, 70, 70, 70, 344, 100},
										new double[]{0.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}).build();
		panelModif.setLayout(gbl_panelModif);
		
		JPanel panelTitreM = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout fl_panelTitreM = (FlowLayout) panelTitreM.getLayout();
		fl_panelTitreM.setVgap(0);
		fl_panelTitreM.setHgap(40);
		fl_panelTitreM.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreM = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelModif.add(panelTitreM, gbc_panelTitreM);
		
		titreModif = new JLabelBuilder(panelTitreM).setCustomLabel("Créer une équipe", new Font("Roboto", Font.BOLD, 30), Color.WHITE).build();
		
		JPanel panelNomEcurie = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelNomEcurie = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 1).build();
		panelModif.add(panelNomEcurie, gbc_panelNomEcurie);
		GridBagLayout gbl_panelNomEcurie = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelNomEcurie.setLayout(gbl_panelNomEcurie);
		
		JPanel panel = new JPanelBuilder(panelNomEcurie).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panel = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelNomEcurie.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 55, 5));

		if (ControleurEquipe.estProfil("Gestionnaire")) {
			JLabel nomEcurie = new JLabelBuilder(panel).setCustomLabel("Sélectionner l'écurie", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
			nomEcurie.setHorizontalAlignment(SwingConstants.CENTER);
	
			JPanel panel_1 = new JPanelBuilder(panelNomEcurie).setCustomPanel(Couleur.BLEU1).build();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel_1 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
			panelNomEcurie.add(panel_1, gbc_panel_1);
			
			entreeEcurie.setToolTipText("");
			entreeEcurie.setFont(new Font("Roboto", Font.PLAIN, 11));
			entreeEcurie.setPreferredSize(new Dimension(205, 20));
			panel_1.add(entreeEcurie);
		}
		
		JPanel panelNom = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelNom = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 2).build();
		panelModif.add(panelNom, gbc_panelNom);
		GridBagLayout gbl_panelNom = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelNom.setLayout(gbl_panelNom);
		
		JPanel panel_4 = new JPanelBuilder(panelNom).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_5 = (FlowLayout) panel_4.getLayout();
		flowLayout_5.setHgap(55);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_4 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelNom.add(panel_4, gbc_panel_4);
		
		JLabel nom = new JLabelBuilder(panel_4).setCustomLabel("Nom de l'équipe", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_5 = new JPanelBuilder(panelNom).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_6 = (FlowLayout) panel_5.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_5 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelNom.add(panel_5, gbc_panel_5);
		
		entreeNom = new JTextFieldBuilder(panel_5).setCustomTextField(new Font("Roboto",Font.PLAIN, 11), 20).build();
		
		JPanel panelJeu = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelJeu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 3).build();
		panelModif.add(panelJeu, gbc_panelJeu);
		GridBagLayout gbl_panelJeu = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelJeu.setLayout(gbl_panelJeu);
		
		JPanel panel_6 = new JPanelBuilder(panelJeu).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_7 = (FlowLayout) panel_6.getLayout();
		flowLayout_7.setHgap(55);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_6 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelJeu.add(panel_6, gbc_panel_6);
		
		JLabel jeuS = new JLabelBuilder(panel_6).setCustomLabel("Jeu spécialisé", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		jeuS.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_7 = new JPanelBuilder(panelJeu).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_8 = (FlowLayout) panel_7.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_7 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelJeu.add(panel_7, gbc_panel_7);
		
		entreeJeu.setFont(new Font("Dialog", Font.PLAIN, 11));
		entreeJeu.setPreferredSize(new Dimension(205, 20));
		panel_7.add(entreeJeu);
		this.entreeJeu.addItem("- Sélectionnez un jeu -");
		
		JPanel panelNationalite = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelNationalite = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 4).build();
		panelModif.add(panelNationalite, gbc_panelNationalite);
		GridBagLayout gbl_panelNationalite = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelNationalite.setLayout(gbl_panelNationalite);
		
		JPanel panel_2 = new JPanelBuilder(panelNationalite).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(55);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0).build();
		panelNationalite.add(panel_2, gbc_panel_2);
		
		new JLabelBuilder(panel_2).setCustomLabel("Nationalité", new Font("Roboto", Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panel_3 = new JPanelBuilder(panelNationalite).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_4 = (FlowLayout) panel_3.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_3 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelNationalite.add(panel_3, gbc_panel_3);
		
		entreeNationalite = new JTextFieldBuilder(panel_3).setCustomTextField(new Font("Roboto",Font.PLAIN, 11), 20).build();
		
		JPanel panelJoueurs = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelJoueurs = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 5).build();
		panelModif.add(panelJoueurs, gbc_panelJoueurs);
		GridBagLayout gbl_panelJoueurs = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, new int[]{335, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE}).build();
		panelJoueurs.setLayout(gbl_panelJoueurs);
		
//		JPanel panel_12 = creerJPanel(panelJoueurs, Couleur.BLEU1);
//		FlowLayout flowLayout_2 = (FlowLayout) panel_12.getLayout();
//		flowLayout_2.setHgap(55);
//		flowLayout_2.setAlignment(FlowLayout.LEFT);
//		flowLayout_2.setVgap(20);
//		GridBagConstraints gbc_panel_12 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0);
//		panelJoueurs.add(panel_12, gbc_panel_12);
		
		//creerJLabel(panel_12, "Liste des joueurs", 14);
		
		panel_13 = new JPanelBuilder(panelJoueurs).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panel_13 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0).build();
		panelJoueurs.add(panel_13, gbc_panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));
		
		modeleJoueurs = new DefaultListModel<String>();
		listeJoueurs = new JList<String>(modeleJoueurs);
		listeJoueurs.setName("Joueurs");
		listeJoueurs.setBackground(Couleur.BLEU1);
		listeJoueurs.setForeground(Color.WHITE);
		panel_13.add(listeJoueurs);
		
		JPanel panelValider = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 6).build();
		panelModif.add(panelValider, gbc_panelValider);
		
		JButton btnValider = new JButtonBuilder(panelValider).setCustomButton("Valider", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.VERT).build();
		btnValider.setName("btnValider");
		JButton btnAnnuler = new JButtonBuilder(panelValider).setCustomButton("Annuler", Color.WHITE, new Font("Roboto", Font.BOLD, 13), Couleur.GRIS).build();

		// VALIDER OU ANNULER INFORMATIONS SUR L'EQUIPE
		btnAnnuler.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		
		// DECONNEXION
		
		// LISTE
		this.listeEquipes.addListSelectionListener((ListSelectionListener) controleur);
		listeJoueurs.addListSelectionListener((ListSelectionListener) controleur);
		
		
		// GESTION DES EQUIPES
		btnCreer.addActionListener(controleur);
	}
	
	public boolean listeVide(JList<String> list) {
		return list.isSelectionEmpty();
	}
	
	//JOUEURS
	public void ajouterJoueur(String nom) {
		this.modeleJoueurs.addElement(nom);
	}
	
	public void viderModeleJoueurs() {
		this.modeleJoueurs.clear();
	}
	
	//EQUIPE
	public void ajouterEquipe(String nomEquipe) {
		this.modeleEquipes.addElement(nomEquipe);
	}
	
	public void modifierEquipe() {
		this.modeleEquipes.set(this.listeEquipes.getSelectedIndex(),this.getNom());		
	}
	
	public void supprimerEquipe() {
		this.modeleEquipes.removeElement(this.entreeNom.getText());
		this.deselectionner();
    }
	
	
	//ECURIE
	public void ajouterEcurie(String e) {
		this.entreeEcurie.addItem(e);
	}

	//JEU
	public void ajouterJeu(String j) {
		this.entreeJeu.addItem(j);
	}
	
	// GETTERS //
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}
	
	public String getEquipeSelectionne() {
		return this.listeEquipes.getSelectedValue();
	}
	
	public String getJoueurSelectionne() {
		return this.listeJoueurs.getSelectedValue();
	}
	
	public String getEcurie() {
		return (String) this.entreeEcurie.getSelectedItem();
	}

	public String getNom() {
		return this.entreeNom.getText();
	}
	
	public String getJeu() {
		return (String) this.entreeJeu.getSelectedItem();
	}

	public String getNationalite() {
		return this.entreeNationalite.getText();
	}
	
	public String getRecherche() {
		return recherche.getText();
	}
	
	// SETTER //
	public void setNomEquipe(String e) {
		this.entreeNom.setText(e);
	}
	
	public void setEcurie(String e) {
		if (ControleurEquipe.estProfil("Gestionnaire")) {
			this.entreeEcurie.setSelectedItem(e);
		}	
	}
	
	public void setJeu(String e) {
		this.entreeJeu.setSelectedItem(e);
	}
	
	public void setNationalite(String nationalite) {
		this.entreeNationalite.setText(nationalite);
	}

	public void setDefaultListModel() {
		this.listeEquipes.setModel(modeleEquipes);
	}
	
	public void deselectionner() {
		this.listeEquipes.clearSelection();
	}
	
	public void filtrageListeEquipe(String[] tab) {
		this.listeEquipes.setListData(tab);
	}
	
	public void filtrerRecherche() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleEquipes.size(); i++) {
	    	if (this.modeleEquipes.get(i).contains(this.recherche.getText())){
	    		modeleFiltre.addElement(this.modeleEquipes.get(i));
	    	}
	    }
	    this.listeEquipes.setModel(modeleFiltre);
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
	
	public int confirmerSuppression() {
		return JOptionPane.showConfirmDialog(null, "Confirmez-vous la suppression ?","Confirmation",JOptionPane.YES_NO_OPTION);
	}
	
	public void premierIndexJeu() {
        this.entreeJeu.setSelectedIndex(0);
    }
	
	public void premierIndexEcurie() {
        this.entreeEcurie.setSelectedIndex(0);
    }
	
	public void annulerEntreeEquipe() {
        this.deselectionner();
        VueEquipe.afficherPanel(panelModif);
        VueEquipe.afficherTexte(this.titreModif, "Créer une équipe");
        VueEquipe.supprimerTexte(this.entreeNom);
        VueEquipe.supprimerTexte(this.entreeNationalite);
        this.premierIndexJeu();
        entreeJeu.setForeground(new Color(0,0,0));
        this.premierIndexEcurie();
        entreeEcurie.setForeground(new Color(0,0,0));
    }
	
	public void rechercheEquipe(JTextField recherche) {
		recherche.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // ne pas utiliser
            }
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                if (recherche.getText().isEmpty()) {
                    desactiverBouton(btnRechercher);
                }
            }
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                if (!recherche.getText().isEmpty()) {
                	activerBouton(btnRechercher);
                }
            }
        });
	}
	
	public void activerBouton(JButton j) {
        j.setEnabled(true);
    }
	
	public void desactiverBouton(JButton j) {
        j.setEnabled(false);
    }
	
	public Etat getEtat(JButton b) {
		Etat etat = EtatFactory.creerEtat(b.getText());
		return etat;
	}
	
	public void creerEquipe() {
		this.deselectionner();
		VueEquipe.afficherPanel(panelModif);
		VueEquipe.afficherTexte(this.titreModif, "Créer une équipe");
		VueEquipe.supprimerTexte(this.entreeNom);
		VueEquipe.supprimerTexte(this.entreeNationalite);
		
		if (ControleurEquipe.estProfil("Gestionnaire")) {
			this.setEcurie("- Sélectionnez une écurie -");
		}
		entreeEcurie.setForeground(new Color(0,0,0));
		this.setJeu("- Sélectionnez un jeu -");
		entreeJeu.setForeground(new Color(0,0,0));
		this.viderModeleJoueurs();
	}
	
	// MESSAGE //
	public void estVide() {
        JOptionPane.showMessageDialog(null, "Veuillez compléter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

}