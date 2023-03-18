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

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controleur.ControleurInscriptionTournoi;
import modele.EtatFactory;
import modele.GridBagConstraintsBuilder;
import modele.GridBagLayoutBuilder;
import modele.JLabelBuilder;
import modele.JPanelBuilder;
import modele.Etat;

import javax.swing.JComboBox;

public class VueInscriptionTournoi implements Vue{
	
	public JFrame fenetreInscriptionTournoi;
	public JPanel panelModif;
	public JLabel titreModif;
	private static final String SELECTIONNER_UN_TOURNOIS = "Sélectionnez le tournoi";
	private static final String EQUIPE_A_INSCRIRE = "Equipe à inscrire";

	private static final String POLICE = "Roboto";

	private DefaultListModel<String> modeleTournois = new DefaultListModel<String>();
	private JList<String> listeTournois;
	private DefaultListModel<String> modeleEquipes = new DefaultListModel<String>();
	private JList<String> listeEquipes;
	private JComboBox<String> selectionJeu;
	
	public JFrame getFrame() {
		return this.fenetreInscriptionTournoi;
	}
	
	public VueInscriptionTournoi() {
		// CREATION DE LA FENETRE //
		fenetreInscriptionTournoi = new JFrame();
		fenetreInscriptionTournoi.getContentPane().setBackground(Couleur.BLEU1);
		fenetreInscriptionTournoi.setResizable(false);
		fenetreInscriptionTournoi.setBounds(100, 100, 1500, 880);
		fenetreInscriptionTournoi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ControleurInscriptionTournoi controleur = new ControleurInscriptionTournoi(this);
		
		// HEADER //
		HeaderEcurie header = new HeaderEcurie(this.getFrame());
		header.getBtnDeconnexion().addActionListener(controleur);
		header.getBtnEquipes().addActionListener(controleur);
		header.getBtnJoueurs().addActionListener(controleur);
		header.getBtnClassement().addActionListener(controleur);
		Vue.desactiverBouton(header.getBtnTournois());
		
		
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreInscriptionTournoi.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTournoi = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		
		GridBagLayout gbl_panelTournoi = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[]{692, 0}, 
				new int[] {100, 724, 0}, 
				new double[]{1.0, Double.MIN_VALUE}, 
				new double[]{0.0, 0.0, Double.MIN_VALUE}).build();
		panelTournoi.setLayout(gbl_panelTournoi);
		
		JPanel panelTitreT = new JPanelBuilder(panelTournoi).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout fl_panelTitreT = (FlowLayout) panelTitreT.getLayout();
		fl_panelTitreT.setVgap(0);
		fl_panelTitreT.setHgap(50);
		fl_panelTitreT.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreT = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.SOUTH, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 0).build();
		panelTournoi.add(panelTitreT, gbc_panelTitreT);
		
		// LISTE DES TOURNOIS //
		new JLabelBuilder(panelTitreT).setCustomLabel(SELECTIONNER_UN_TOURNOIS, new Font(POLICE, Font.BOLD, 30),Color.WHITE).build();
		
		JPanel panelListe = new JPanelBuilder(panelTournoi).setCustomPanel(Couleur.BLEU1).build();

		FlowLayout fl_panelListe = (FlowLayout) panelListe.getLayout();
		fl_panelListe.setHgap(50);
		fl_panelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 0, 1).build();
		panelTournoi.add(panelListe, gbc_panelListe);
		
		listeTournois = new JList<String>(modeleTournois);
		listeTournois.setVisibleRowCount(13);
		listeTournois.setName("Tournoi");
		listeTournois.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeTournois.setFixedCellHeight(50);
		listeTournois.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(listeTournois);
		panelListe.add(scrollPane);
		
		JPanel panelModif = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		GridBagLayout gbl_panelModif = new GridBagLayoutBuilder().setCustomGridBagLayout(
				new int[] {692, 0}, 
				new int[]{100, 622, 100, 0}, 
				new double[]{1.0, Double.MIN_VALUE}, 
				new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE}).build();
		panelModif.setLayout(gbl_panelModif);
		
		JPanel panelTitreM = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelTitreM = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.SOUTH, 
				GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 5, 0), 0, 0).build();
		panelModif.add(panelTitreM, gbc_panelTitreM);
		panelTitreM.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanelBuilder(panelTitreM).setCustomPanel(Couleur.BLEU1).build();

		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		new JLabelBuilder(panel_1).setCustomLabel(EQUIPE_A_INSCRIRE, new Font(POLICE, Font.BOLD, 30),Color.WHITE).build();
		
		JPanel panel_2 = new JPanelBuilder(panelTitreM).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setHgap(50);
		fl_panel_2.setAlignment(FlowLayout.RIGHT);
		fl_panel_2.setVgap(0);
		
		selectionJeu = new JComboBox<String>();
		selectionJeu.setFont(new Font("Roboto", Font.PLAIN, 11));
		selectionJeu.setPreferredSize(new Dimension(205, 20));
		selectionJeu.addItem("- Sélectionnez un jeu -");
		panel_2.add(selectionJeu);
		JPanel panelEquipe = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbc_panelEquipe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 0), 0, 1).build();
		panelModif.add(panelEquipe, gbc_panelEquipe);	
		panelEquipe.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 5));
		
		listeEquipes = new JList<String>(this.modeleEquipes);
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setVisibleRowCount(12);
		listeEquipes.setName("Equipe");
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		JScrollPane scrollPane2 = new JScrollPane(listeEquipes);
		panelEquipe.add(scrollPane2);
		JPanel panelValider = new JPanelBuilder(panelModif).setCustomPanel(Couleur.BLEU1).build();

		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraintsBuilder().setCustomGridBagConstraints(
				GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 2).build();
		panelModif.add(panelValider, gbc_panelValider);

		// Bouton de validation/annulation
		JButton btnValider = creerBouton(panelValider, "Valider", Couleur.VERT, 13);
		JButton btnAnnuler = creerBouton(panelValider, "Annuler", Couleur.GRIS, 13);
		
		// ECOUTE SUR LES COMPOSANTS //
		this.listeTournois.addListSelectionListener(controleur);
		this.listeEquipes.addListSelectionListener(controleur);
		this.selectionJeu.addActionListener(controleur);
		
		// BOUTONS //
		btnValider.addActionListener(controleur);
		btnAnnuler.addActionListener(controleur);
	}
	
	public void activerBouton(JButton j) {
        j.setEnabled(true);
    }
	
	public void desactiverBouton(JButton j) {
        j.setEnabled(false);
    }

	// RECEVOIR L'ETAT //
	public Etat getEtat(JButton b) {
		Etat etat = EtatFactory.creerEtat(b.getText());
		return etat;
	}
	
	public Etat getEtat(JList<String> l) {
		if (l.getName().equals("Tournoi")) {
			return Etat.TOURNOIS;
		} if (l.getName().equals("Equipe")) {
			return Etat.EQUIPE;
		}
		return null;
	}
	
	// AJOUTER LES COMPOSANTS A LA LISTE //
	public void ajouterTournoi(String nomTournoi) {
		this.modeleTournois.addElement(nomTournoi);
	}

	public void ajouterJeu(String nomJeu) {
		this.selectionJeu.addItem(nomJeu);
	}
	
	public void ajouterEquipe(String nomEquipe) {
		this.modeleEquipes.addElement(nomEquipe);
	}
	
	public String getTournoiSelectionne() {
		return this.listeTournois.getSelectedValue();
	}

	public String getJeuSelectionne() {
		return (String) this.selectionJeu.getSelectedItem();
	}

	public String getEquipeSelectionne() {
		return this.listeEquipes.getSelectedValue();
	}
	
	// VIDE LA LISTE DE JEUX AVANT DE LA REMPLIR //
	public void viderJeux() {
		this.selectionJeu.removeAllItems();
	}

	public void viderEquipes() {
		this.modeleEquipes.clear();
	}
	
	// DESELECTIONNE TOUS LES COMPOSANTS DE DONNEES DE LA PAGE //
	public void deselectionner() {
		this.listeEquipes.clearSelection();
		this.selectionJeu.setSelectedItem("- Sélectionnez un jeu -");
		this.listeTournois.clearSelection();
	}
	
	// VERIFIE QUE TOUS LES CHAMPS SONT REMPLIS //
	public boolean estRemplie() {
		return (!this.listeTournois.isSelectionEmpty()) && 
				(!this.listeEquipes.isSelectionEmpty()) && 
				(!this.selectionJeu.getSelectedItem().equals("- Sélectionnez un jeu -"));
	}
	
}
