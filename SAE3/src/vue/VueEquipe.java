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
import java.util.Arrays;

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
	private static final String GESTIONNAIRE = "Gestionnaire";
	private static final String POLICE = "Roboto";
	private static final String CREER_UNE_EQUIPE = "Créer une équipe";
	private static final String SELECTIONNER_UN_JEU = "- Sélectionnez un jeu -";
	
	private JFrame fenetreEquipe;

	private JLabel titreModif;
	private JLabel nomEcurie;
	private JLabel nomEquipe;
	private JLabel jeuS;
	private JLabel libelleNationalite;
	
	private JPanel panelModif;

	private JTextField entreeNomEquipe;
	private JTextField recherche;
	private JTextField entreeNationalite;
	
	private DefaultListModel<String> modeleEquipes= new DefaultListModel<>();
	private JList<String> listeEquipes = new JList<>(modeleEquipes);
	private DefaultListModel<String> modeleJoueurs;
	private JList<String> listeJoueurs;
	
	private JComboBox<String> entreeEcurie = new JComboBox<>();
	private JComboBox<String> entreeJeu = new JComboBox<>();
	
	private JButton btnSupprimer;
	private JButton btnRechercher;
	
	public JFrame getFrame() {
		return this.fenetreEquipe;
	}
	
	public JLabel getTitreModification() {
		return titreModif;
	}
	
	public VueEquipe() {
		// CREATION DE LA FENETRE //
		fenetreEquipe = new JFrame();
		fenetreEquipe.getContentPane().setBackground(Couleur.BLEU1);
		fenetreEquipe.setResizable(false);
		fenetreEquipe.setBounds(100, 100, 1500, 880);
		fenetreEquipe.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		// CONTROLEUR
		ControleurEquipe controleur = new ControleurEquipe(this);
		
		// HEADER //
		// Boutons du menu
		if (ControleurEquipe.estProfil(GESTIONNAIRE)) {
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
		GridBagLayout gblPanelEquipe = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[]{692, 0}, 
				new int[] {100, 622, 100, 0}, 
				new double[]{1.0, Double.MIN_VALUE}, 
				new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE}).build();
		panelEquipe.setLayout(gblPanelEquipe);
		
		JPanel panelTitreT = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelTitreT = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.SOUTH, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 0).build();
		panelEquipe.add(panelTitreT, gbcPanelTitreT);
		panelTitreT.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTitre = new JPanelBuilder(panelTitreT).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayoutTitre = (FlowLayout) panelTitre.getLayout();
		flowLayoutTitre.setHgap(50);
		flowLayoutTitre.setAlignment(FlowLayout.LEFT);
		
		// LISTE DES EQUIPES //
		new JLabelBuilder(panelTitre).setCustomLabel("Equipes", new Font(POLICE, Font.BOLD, 36), Color.WHITE).build();
		
		JPanel panelRecherche = new JPanelBuilder(panelTitreT).setCustomPanel(Couleur.BLEU1).build();
		recherche = new JTextFieldBuilder(panelRecherche).setCustomTextField(new Font(POLICE,Font.PLAIN, 13), 15).build();
		btnRechercher = new JButtonBuilder(panelRecherche).setCustomButton(
				"Rechercher", 
				Color.WHITE, 
				new Font(POLICE, Font.BOLD, 13), 
				Couleur.BLEU2).build();
		Vue.desactiverBouton(btnRechercher);
		rechercheEquipe(recherche);

		JPanel panelListe = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListe = (FlowLayout) panelListe.getLayout();
		flPanelListe.setHgap(50);
		flPanelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelListe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 5, 0), 0, 1).build();
		panelEquipe.add(panelListe, gbcPanelListe);
		
		listeEquipes.setVisibleRowCount(12);
		listeEquipes.setFont(new Font(POLICE, Font.PLAIN, 15));
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		listeEquipes.setName("Equipe");
		JScrollPane scrollPane = new JScrollPane(this.listeEquipes);
		panelListe.add(scrollPane);

		JPanel panelBoutons = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelBoutons = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 2).build();
		panelEquipe.add(panelBoutons, gbcPanelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		// Création des boutons de gestion
		JButton btnCreer = new JButtonBuilder(panelBoutons).setCustomButton(
				"Créer une nouvelle équipe", 
				Color.WHITE, 
				new Font(POLICE, Font.BOLD, 13), 
				Couleur.BLEU2).build();
		btnSupprimer = new JButtonBuilder(panelBoutons).setCustomButton(
				"Supprimer l'équipe sélectionnée", 
				Color.WHITE, 
				new Font(POLICE, Font.BOLD, 13), 
				Couleur.GRIS).build();
		Vue.desactiverBouton(btnSupprimer);
		
		// CREER OU MODIFIER UNE EQUIPE
		panelModif = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		
		GridBagLayout gblPanelModif = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[]{692, 0}, 
				new int[] {100, 70, 70, 70, 70, 344, 100},
				new double[]{0.0, Double.MIN_VALUE}, 
				new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}).build();
		panelModif.setLayout(gblPanelModif);
		
		JPanel panelTitreM = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelTitreM = (FlowLayout) panelTitreM.getLayout();
		flPanelTitreM.setVgap(0);
		flPanelTitreM.setHgap(40);
		flPanelTitreM.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelTitreM = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.SOUTH, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 0).build();
		panelModif.add(panelTitreM, gbcPanelTitreM);
		
		titreModif = new JLabelBuilder(panelTitreM).setCustomLabel(
				CREER_UNE_EQUIPE, 
				new Font(POLICE, Font.BOLD, 30), 
				Color.WHITE).build();
		
		JPanel panelNomEcurie = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelNomEcurie = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 1).build();
		panelModif.add(panelNomEcurie, gbcPanelNomEcurie);
		GridBagLayout gblPanelNomEcurie = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[] {250, 442, 0}, 
				new int[]{30, 0}, 
				new double[]{0.0, 0.0, Double.MIN_VALUE}, 
				new double[]{0.0, Double.MIN_VALUE}).build();
		panelNomEcurie.setLayout(gblPanelNomEcurie);
		
		JPanel panel = new JPanelBuilder(panelNomEcurie).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanel = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 5), 0, 0).build();
		panelNomEcurie.add(panel, gbcPanel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 55, 5));

		if (ControleurEquipe.estProfil(GESTIONNAIRE)) {
			nomEcurie = new JLabelBuilder(panel).setCustomLabel(
					"Nom de l'écurie", 
					new Font(POLICE, Font.BOLD, 14), 
					Color.WHITE).build();
			nomEcurie.setHorizontalAlignment(SwingConstants.CENTER);
	
			JPanel panelEcurie = new JPanelBuilder(panelNomEcurie).setCustomPanel(Couleur.BLEU1).build();
			FlowLayout flowLayout = (FlowLayout) panelEcurie.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbcPanelEcurie = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
					GridBagConstraints.CENTER, 
					GridBagConstraints.BOTH, 
					new Insets(0, 0, 0, 0), 1, 0).build();
			panelNomEcurie.add(panelEcurie, gbcPanelEcurie);
			
			entreeEcurie.setToolTipText("");
			entreeEcurie.setFont(new Font(POLICE, Font.PLAIN, 11));
			entreeEcurie.setPreferredSize(new Dimension(205, 20));
			panelEcurie.add(entreeEcurie);
			
		}
		
		JPanel panelNom = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelNom = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 2).build();
		panelModif.add(panelNom, gbcPanelNom);
		GridBagLayout gblPanelNom = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[] {250, 442, 0}, 
				new int[]{30, 0}, 
				new double[]{0.0, 0.0, Double.MIN_VALUE}, 
				new double[]{0.0, Double.MIN_VALUE}).build();
		panelNom.setLayout(gblPanelNom);
		
		JPanel panel4 = new JPanelBuilder(panelNom).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout5 = (FlowLayout) panel4.getLayout();
		flowLayout5.setHgap(55);
		flowLayout5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel4 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 5), 0, 0).build();
		panelNom.add(panel4, gbcPanel4);
		
		nomEquipe = new JLabelBuilder(panel4).setCustomLabel("Nom de l'équipe", new Font(POLICE, Font.BOLD, 14), Color.WHITE).build();
		nomEquipe.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel5 = new JPanelBuilder(panelNom).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout6 = (FlowLayout) panel5.getLayout();
		flowLayout6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel5 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 1, 0).build();
		panelNom.add(panel5, gbcPanel5);
		
		entreeNomEquipe = new JTextFieldBuilder(panel5).setCustomTextField(new Font(POLICE,Font.PLAIN, 11), 20).build();
		
		JPanel panelJeu = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelJeu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 3).build();
		panelModif.add(panelJeu, gbcPanelJeu);
		GridBagLayout gblPanelJeu = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[] {250, 442, 0}, 
				new int[]{30, 0}, 
				new double[]{0.0, 0.0, Double.MIN_VALUE}, 
				new double[]{0.0, Double.MIN_VALUE}).build();
		panelJeu.setLayout(gblPanelJeu);
		
		JPanel paneLabelJeu = new JPanelBuilder(panelJeu).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout7 = (FlowLayout) paneLabelJeu.getLayout();
		flowLayout7.setHgap(55);
		flowLayout7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanel6 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 5), 0, 0).build();
		panelJeu.add(paneLabelJeu, gbcPanel6);
		
		jeuS = new JLabelBuilder(paneLabelJeu).setCustomLabel("Jeu spécialisé", new Font(POLICE, Font.BOLD, 14), Color.WHITE).build();
		jeuS.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelEntreeJeu = new JPanelBuilder(panelJeu).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayoutEntreeJeu = (FlowLayout) panelEntreeJeu.getLayout();
		flowLayoutEntreeJeu.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelEntreeJeu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 1, 0).build();
		panelJeu.add(panelEntreeJeu, gbcPanelEntreeJeu);
		
		entreeJeu.setFont(new Font("Dialog", Font.PLAIN, 11));
		entreeJeu.setPreferredSize(new Dimension(205, 20));
		panelEntreeJeu.add(entreeJeu);
		this.entreeJeu.addItem(SELECTIONNER_UN_JEU);
		
		JPanel panelNationalite = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelNationalite = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 4).build();
		panelModif.add(panelNationalite, gbcPanelNationalite);
		GridBagLayout gblPanelNationalite = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[] {250, 442, 0}, 
				new int[]{30, 0},
				new double[]{0.0, 0.0, Double.MIN_VALUE}, 
				new double[]{0.0, Double.MIN_VALUE}).build();
		panelNationalite.setLayout(gblPanelNationalite);
		
		JPanel panelLabelNationalite = new JPanelBuilder(panelNationalite).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayoutNationalite = (FlowLayout) panelLabelNationalite.getLayout();
		flowLayoutNationalite.setHgap(55);
		flowLayoutNationalite.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelLabelNationalite = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 5), 0, 0).build();
		panelNationalite.add(panelLabelNationalite, gbcPanelLabelNationalite);
		
		libelleNationalite = new JLabelBuilder(panelLabelNationalite).setCustomLabel("Nationalité", new Font(POLICE, Font.BOLD, 14), Color.WHITE).build();
		
		JPanel panelEntreeNationalite = new JPanelBuilder(panelNationalite).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayoutEntreeNationalite = (FlowLayout) panelEntreeNationalite.getLayout();
		flowLayoutEntreeNationalite.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelEntreeNationalite = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 1, 0).build();
		panelNationalite.add(panelEntreeNationalite, gbcPanelEntreeNationalite);
		
		entreeNationalite = new JTextFieldBuilder(panelEntreeNationalite).setCustomTextField(new Font(POLICE,Font.PLAIN, 11), 20).build();
		
		JPanel panelJoueurs = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelJoueurs = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 5).build();
		panelModif.add(panelJoueurs, gbcPanelJoueurs);
		GridBagLayout gblPanelJoueurs = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[] {250, 442, 0}, 
				new int[]{335, 0}, 
				new double[]{0.0, 0.0, Double.MIN_VALUE}, 
				new double[]{0.0, Double.MIN_VALUE}).build();
		panelJoueurs.setLayout(gblPanelJoueurs);
		
		JPanel panelListeJoueurs = new JPanelBuilder(panelJoueurs).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelListeJoueurs = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 1, 0).build();
		panelJoueurs.add(panelListeJoueurs, gbcPanelListeJoueurs);
		panelListeJoueurs.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));
		
		modeleJoueurs = new DefaultListModel<>();
		listeJoueurs = new JList<>(modeleJoueurs);
		listeJoueurs.setName("Joueurs");
		listeJoueurs.setBackground(Couleur.BLEU1);
		listeJoueurs.setForeground(Color.WHITE);
		panelListeJoueurs.add(listeJoueurs);
		
		JPanel panelValider = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayoutPanelValider = (FlowLayout) panelValider.getLayout();
		flowLayoutPanelValider.setVgap(0);
		flowLayoutPanelValider.setHgap(150);
		GridBagConstraints gbcPanelValider = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 0, 6).build();
		panelModif.add(panelValider, gbcPanelValider);
		
		JButton btnValider = new JButtonBuilder(panelValider).setCustomButton(
				"Valider", 
				Color.WHITE, 
				new Font(POLICE, Font.BOLD, 13), 
				Couleur.VERT).build();
		btnValider.setName("btnValider");
		JButton btnAnnuler = new JButtonBuilder(panelValider).setCustomButton(
				"Annuler", 
				Color.WHITE, 
				new Font(POLICE, Font.BOLD, 13), 
				Couleur.GRIS).build();

		// VALIDER OU ANNULER INFORMATIONS SUR L'EQUIPE
		btnAnnuler.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		
		// DECONNEXION
		
		// LISTE
		this.listeEquipes.addListSelectionListener(controleur);
		listeJoueurs.addListSelectionListener(controleur);
		
		
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
		this.modeleEquipes.set(this.listeEquipes.getSelectedIndex(),this.getNomEquipe());		
	}
	
	public void supprimerEquipe() {
		this.modeleEquipes.removeElement(this.entreeNomEquipe.getText());
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

	public String getNomEquipe() {
		return this.entreeNomEquipe.getText();
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
		this.entreeNomEquipe.setText(e);
	}
	
	public void setEcurie(String e) {
		if (ControleurEquipe.estProfil(GESTIONNAIRE)) {
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
		DefaultListModel<String> modeleFiltre = new DefaultListModel<>();
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
        VueEquipe.afficherTexte(this.titreModif, CREER_UNE_EQUIPE);
        VueEquipe.supprimerTexte(this.entreeNomEquipe);
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
	
	public Etat getEtat(JButton b) {
		return EtatFactory.creerEtat(b.getText());
	}
	
	public void creerEquipe() {
		this.deselectionner();
		VueEquipe.afficherPanel(panelModif);
		VueEquipe.afficherTexte(this.titreModif, CREER_UNE_EQUIPE);
		VueEquipe.supprimerTexte(this.entreeNomEquipe);
		VueEquipe.supprimerTexte(this.entreeNationalite);
		
		if (ControleurEquipe.estProfil(GESTIONNAIRE)) {
			this.setEcurie("- Sélectionnez une écurie -");
		}
		entreeEcurie.setForeground(new Color(0,0,0));
		this.setJeu(SELECTIONNER_UN_JEU);
		entreeJeu.setForeground(new Color(0,0,0));
		this.viderModeleJoueurs();
	}
	
	/* Vérifie si tous les champs ont été saisis*/
	public boolean estFormulaireRempli() {
		Boolean[] resultat = new Boolean[4];
		resultat[0] = Vue.estSaisiRempli(this.getEcurie(), "- Sélectionnez une écurie -", this.nomEcurie, this.entreeEcurie);
		resultat[1] = Vue.estSaisiRempli(this.getNomEquipe(), "", this.nomEquipe, this.entreeNomEquipe);
		resultat[2] = Vue.estSaisiRempli(this.getJeu(), SELECTIONNER_UN_JEU, this.jeuS, this.entreeJeu);
		resultat[3] = Vue.estSaisiRempli(this.getNationalite(), "", this.libelleNationalite, this.entreeNationalite);
		
		if (!Arrays.stream(resultat).allMatch(Boolean::valueOf)) {
			Vue.estVide();
			return false;
		}
		return true;
	}

}