package vue;

import java.awt.BorderLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
import javax.swing.*;


import modele.Etat;
import modele.EtatFactory;
import controleur.ControleurEquipe;

public class VueEquipe implements Vue{
	
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
	private JButton btnCalendrier;
	private JButton btnERA;
	private JButton btnJoueurs;
	private JButton btnTournois;
	private JButton btnClassement;
	
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
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fenetreEquipe.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = creerJPanel(panelHeader, Color.WHITE);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		// Boutons du menu
		if (ControleurEquipe.estProfil("Gestionnaire")) {
			btnCalendrier = creerBouton(panelMenu, "Calendrier", Couleur.BLEU2, 15);
			btnCalendrier.addActionListener(controleur);

			btnERA = creerBouton(panelMenu, "Ecuries / Responsables / Arbitres", Couleur.BLEU2, 15);
			btnERA.addActionListener(controleur);
			
		}

		JButton btnEquipes =  creerBouton(panelMenu, "Equipes", Couleur.BLEU2, 15);
		Vue.desactiverBouton(btnEquipes);
		btnJoueurs = creerBouton(panelMenu, "Joueurs", Couleur.BLEU2, 15);
		if (ControleurEquipe.estProfil("Ecurie")) {
			btnTournois = creerBouton(panelMenu, "Tournois", Couleur.BLEU2, 15);
			btnTournois.addActionListener(controleur);
			
		}
		
		btnClassement = creerBouton(panelMenu, "Classement", Couleur.BLEU2, 15);
		
		JPanel panelDeconnexion = creerJPanel(panelHeader, Color.WHITE);
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		
		JButton btnDeconnexion = creerBouton(panelDeconnexion, "Se déconnecter", Couleur.ROUGE, 13);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreEquipe.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelEquipe = creerJPanel(panelContenu, Couleur.BLEU1);
		GridBagLayout gbl_panelEquipe = creerGridBagLayout(new int[]{692, 0}, new int[] {100, 622, 100, 0}, 
										new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE});
		panelEquipe.setLayout(gbl_panelEquipe);
		
		JPanel panelTitreT = creerJPanel(panelEquipe, Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreT = creerGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0);
		panelEquipe.add(panelTitreT, gbc_panelTitreT);
		panelTitreT.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTitre = creerJPanel(panelTitreT, Couleur.BLEU1);
		FlowLayout flowLayout_3 = (FlowLayout) panelTitre.getLayout();
		flowLayout_3.setHgap(50);
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		
		// LISTE DES TOURNOIS //
		creerJLabel(panelTitre, "Equipes", 36);
		
		JPanel panelRecherche = creerJPanel(panelTitreT, Couleur.BLEU1);
		
		recherche = creerJTextField(panelRecherche, 13, 15);
		
		btnRechercher = creerBouton(panelRecherche, "Rechercher", Couleur.BLEU2, 13);
		desactiverBouton(btnRechercher);
		rechercheEquipe(recherche);

		JPanel panelListe = creerJPanel(panelEquipe, Couleur.BLEU1);
		FlowLayout fl_panelListe = (FlowLayout) panelListe.getLayout();
		fl_panelListe.setHgap(50);
		fl_panelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListe = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 1);
		panelEquipe.add(panelListe, gbc_panelListe);
		
		listeEquipes.setVisibleRowCount(12);
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		listeEquipes.setName("Equipe");
		JScrollPane scrollPane = new JScrollPane(this.listeEquipes);
		panelListe.add(scrollPane);

		JPanel panelBoutons = creerJPanel(panelEquipe, Couleur.BLEU1);
		GridBagConstraints gbc_panelBoutons = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 2);
		panelEquipe.add(panelBoutons, gbc_panelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		// Création des boutons de gestion
		JButton btnCreer = creerBouton(panelBoutons, "Créer une nouvelle équipe", Couleur.BLEU2, 13);
		btnSupprimer = creerBouton(panelBoutons, "Supprimer l'équipe sélectionnée", Couleur.GRIS, 13);
		this.desactiverBouton(btnSupprimer);
		
		// CREER OU MODIFIER UN TOURNOI
		panelModif = creerJPanel(panelContenu, Couleur.BLEU1);
		
		GridBagLayout gbl_panelModif = creerGridBagLayout(new int[]{692, 0}, new int[] {100, 70, 70, 70, 70, 344, 100},
										new double[]{0.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
		panelModif.setLayout(gbl_panelModif);
		
		JPanel panelTitreM = creerJPanel(panelModif, Couleur.BLEU1);
		FlowLayout fl_panelTitreM = (FlowLayout) panelTitreM.getLayout();
		fl_panelTitreM.setVgap(0);
		fl_panelTitreM.setHgap(40);
		fl_panelTitreM.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreM = creerGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0);
		panelModif.add(panelTitreM, gbc_panelTitreM);
		
		titreModif = creerJLabel(panelTitreM, "Créer une équipe", 30);
		
		JPanel panelNomEcurie = creerJPanel(panelModif, Couleur.BLEU1);
		GridBagConstraints gbc_panelNomEcurie = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 1);
		panelModif.add(panelNomEcurie, gbc_panelNomEcurie);
		GridBagLayout gbl_panelNomEcurie = creerGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE});
		panelNomEcurie.setLayout(gbl_panelNomEcurie);
		
		JPanel panel = creerJPanel(panelNomEcurie, Couleur.BLEU1);
		GridBagConstraints gbc_panel = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0);
		panelNomEcurie.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 55, 5));

		if (ControleurEquipe.estProfil("Gestionnaire")) {
			JLabel nomEcurie = creerJLabel(panel, "Sélectionner l'écurie", 14);
			nomEcurie.setHorizontalAlignment(SwingConstants.CENTER);
	
			JPanel panel_1 = creerJPanel(panelNomEcurie, Couleur.BLEU1);
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			GridBagConstraints gbc_panel_1 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0);
			panelNomEcurie.add(panel_1, gbc_panel_1);
			
			entreeEcurie.setToolTipText("");
			entreeEcurie.setFont(new Font("Roboto", Font.PLAIN, 11));
			entreeEcurie.setPreferredSize(new Dimension(205, 20));
			panel_1.add(entreeEcurie);
		}
		
		JPanel panelNom = creerJPanel(panelModif, Couleur.BLEU1);
		GridBagConstraints gbc_panelNom = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 2);
		panelModif.add(panelNom, gbc_panelNom);
		GridBagLayout gbl_panelNom = creerGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE});
		panelNom.setLayout(gbl_panelNom);
		
		JPanel panel_4 = creerJPanel(panelNom, Couleur.BLEU1);
		FlowLayout flowLayout_5 = (FlowLayout) panel_4.getLayout();
		flowLayout_5.setHgap(55);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_4 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0);
		panelNom.add(panel_4, gbc_panel_4);
		
		JLabel nom = creerJLabel(panel_4, "Nom de l'équipe", 14);
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_5 = creerJPanel(panelNom, Couleur.BLEU1);
		FlowLayout flowLayout_6 = (FlowLayout) panel_5.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_5 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0);
		panelNom.add(panel_5, gbc_panel_5);
		
		entreeNom = creerJTextField(panel_5, 11, 20);
		
		JPanel panelJeu = creerJPanel(panelModif, Couleur.BLEU1);
		GridBagConstraints gbc_panelJeu = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 3);
		panelModif.add(panelJeu, gbc_panelJeu);
		GridBagLayout gbl_panelJeu = creerGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE});
		panelJeu.setLayout(gbl_panelJeu);
		
		JPanel panel_6 = creerJPanel(panelJeu, Couleur.BLEU1);
		FlowLayout flowLayout_7 = (FlowLayout) panel_6.getLayout();
		flowLayout_7.setHgap(55);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_6 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0);
		panelJeu.add(panel_6, gbc_panel_6);
		
		JLabel jeuS = creerJLabel(panel_6, "Jeu spécialisé", 14);
		jeuS.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_7 = creerJPanel(panelJeu, Couleur.BLEU1);
		FlowLayout flowLayout_8 = (FlowLayout) panel_7.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_7 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0);
		panelJeu.add(panel_7, gbc_panel_7);
		
		entreeJeu.setFont(new Font("Dialog", Font.PLAIN, 11));
		entreeJeu.setPreferredSize(new Dimension(205, 20));
		panel_7.add(entreeJeu);
		this.entreeJeu.addItem("- Sélectionnez un jeu -");
		
		JPanel panelNationalite = creerJPanel(panelModif, Couleur.BLEU1);
		GridBagConstraints gbc_panelNationalite = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 4);
		panelModif.add(panelNationalite, gbc_panelNationalite);
		GridBagLayout gbl_panelNationalite = creerGridBagLayout(new int[] {250, 442, 0}, new int[]{30, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE});
		panelNationalite.setLayout(gbl_panelNationalite);
		
		JPanel panel_2 = creerJPanel(panelNationalite, Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(55);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0);
		panelNationalite.add(panel_2, gbc_panel_2);
		
		creerJLabel(panel_2, "Nationalité", 14);
		
		JPanel panel_3 = creerJPanel(panelNationalite, Couleur.BLEU1);
		FlowLayout flowLayout_4 = (FlowLayout) panel_3.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_3 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0);
		panelNationalite.add(panel_3, gbc_panel_3);
		
		entreeNationalite = creerJTextField(panel_3, 11, 20);
		
		JPanel panelJoueurs = creerJPanel(panelModif, Couleur.BLEU1);
		GridBagConstraints gbc_panelJoueurs = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 5);
		panelModif.add(panelJoueurs, gbc_panelJoueurs);
		GridBagLayout gbl_panelJoueurs = creerGridBagLayout(new int[] {250, 442, 0}, new int[]{335, 0}, new double[]{0.0, 0.0, Double.MIN_VALUE}, new double[]{0.0, Double.MIN_VALUE});
		panelJoueurs.setLayout(gbl_panelJoueurs);
		
//		JPanel panel_12 = creerJPanel(panelJoueurs, Couleur.BLEU1);
//		FlowLayout flowLayout_2 = (FlowLayout) panel_12.getLayout();
//		flowLayout_2.setHgap(55);
//		flowLayout_2.setAlignment(FlowLayout.LEFT);
//		flowLayout_2.setVgap(20);
//		GridBagConstraints gbc_panel_12 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0);
//		panelJoueurs.add(panel_12, gbc_panel_12);
		
		//creerJLabel(panel_12, "Liste des joueurs", 14);
		
		panel_13 = creerJPanel(panelJoueurs, Couleur.BLEU1);
		GridBagConstraints gbc_panel_13 = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 1, 0);
		panelJoueurs.add(panel_13, gbc_panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));
		
		modeleJoueurs = new DefaultListModel<String>();
		listeJoueurs = new JList<String>(modeleJoueurs);
		listeJoueurs.setName("Joueurs");
		listeJoueurs.setBackground(Couleur.BLEU1);
		listeJoueurs.setForeground(Color.WHITE);
		panel_13.add(listeJoueurs);
		
		JPanel panelValider = creerJPanel(panelModif, Couleur.BLEU1);
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = creerGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 6);
		panelModif.add(panelValider, gbc_panelValider);
		
		JButton btnValider = creerBouton(panelValider, "Valider", Couleur.VERT, 13);
		btnValider.setName("btnValider");
		JButton btnAnnuler = creerBouton(panelValider, "Annuler", Couleur.GRIS, 13);

		// VALIDER OU ANNULER INFORMATIONS SUR L'EQUIPE
		btnAnnuler.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		
		// DECONNEXION
		btnDeconnexion.addActionListener(controleur);
		
		// LISTE
		this.listeEquipes.addListSelectionListener((ListSelectionListener) controleur);
		listeJoueurs.addListSelectionListener((ListSelectionListener) controleur);
		
		
		// GESTION DES EQUIPES
		btnCreer.addActionListener(controleur);
		btnSupprimer.addActionListener(controleur);
		btnRechercher.addActionListener(controleur);
		

		btnJoueurs.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
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