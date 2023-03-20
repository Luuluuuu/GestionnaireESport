package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import controleur.ControleurConsulterEquipes;
import modele.EtatFactory;
import modele.GridBagConstraintsBuilder;
import modele.GridBagLayoutBuilder;
import modele.JButtonBuilder;
import modele.JLabelBuilder;
import modele.JPanelBuilder;
import modele.Etat;


public class VueConsulterEquipes implements Vue{

	public JFrame fenetreConsulterEquipes;
	private DefaultListModel<String> modeleTournois;
	private JList<String> listeTournois;
	private DefaultListModel<String> modeleEquipes;
	private JList<String> listeEquipes;
	private JComboBox<String> selectionJeu;
	
	public JFrame getFrame() {
		return this.fenetreConsulterEquipes;
	}

	public VueConsulterEquipes() {
		fenetreConsulterEquipes = new JFrame();
		fenetreConsulterEquipes.getContentPane().setBackground(Couleur.BLEU1);
		fenetreConsulterEquipes.setResizable(false);
		fenetreConsulterEquipes.setBounds(100, 100, 1500, 880);
		fenetreConsulterEquipes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		fenetreConsulterEquipes.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelPDF = new JPanelBuilder(panelHeader).setCustomPanel(Color.WHITE).build();
		FlowLayout flPanelPDF = (FlowLayout) panelPDF.getLayout();
		flPanelPDF.setAlignment(FlowLayout.LEFT);
		
		JPanel panelDeconnexion = new JPanelBuilder(panelHeader).setCustomPanel(Color.WHITE).build();
		FlowLayout flPanelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		flPanelDeconnexion.setAlignment(FlowLayout.RIGHT);
		
		JButton btnDeconnexion = creerBouton(panelDeconnexion, "Se déconnecter", Couleur.ROUGE, 13);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreConsulterEquipes.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTournoi = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelTournoi.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelTournoi = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {100, 722, 0}, new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 1.0, Double.MIN_VALUE}).build();
		panelTournoi.setLayout(gblPanelTournoi);
		
		JPanel panelTitreTournoi = new JPanelBuilder(panelTournoi).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelTitreTournoi = (FlowLayout) panelTitreTournoi.getLayout();
		flPanelTitreTournoi.setVgap(0);
		flPanelTitreTournoi.setHgap(50);
		flPanelTitreTournoi.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelTitreTournoi = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelTournoi.add(panelTitreTournoi, gbcPanelTitreTournoi);
		
		new JLabelBuilder(panelTitreTournoi).setCustomLabel("Tournois", new Font("Roboto", Font.BOLD, 36), Color.WHITE).build();
		
		JPanel panelListeTournois = new JPanelBuilder(panelTournoi).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeTournois = (FlowLayout) panelListeTournois.getLayout();
		flPanelListeTournois.setHgap(50);
		flPanelListeTournois.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelListeTournois = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 1).build();
		panelTournoi.add(panelListeTournois, gbcPanelListeTournois);
		
		// TOURNOIS //
		modeleTournois = new DefaultListModel<String>();
		listeTournois = new JList<String>(modeleTournois);
		listeTournois.setVisibleRowCount(13);
		listeTournois.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeTournois.setFixedCellHeight(50);
		listeTournois.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(listeTournois);
		panelListeTournois.add(scrollPane);
		
		JPanel panelGagnant = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		panelGagnant.setBorder(new LineBorder(Color.WHITE));
		panelGagnant.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelPoule = new JPanelBuilder(panelGagnant).setCustomPanel(Couleur.BLEU1).build();
		panelPoule.setBorder(new LineBorder(new Color(255, 255, 255)));
		GridBagLayout gblPanelPoule = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {100, 312, 0}, new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, Double.MIN_VALUE}).build();
		panelPoule.setLayout(gblPanelPoule);
		
		JPanel panelTitrePoule = new JPanelBuilder(panelPoule).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelTitrePoule = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelPoule.add(panelTitrePoule, gbcPanelTitrePoule);
		panelTitrePoule.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel1 = new JPanelBuilder(panelTitrePoule).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanel1 = (FlowLayout) panel1.getLayout();
		flPanel1.setHgap(50);
		flPanel1.setAlignment(FlowLayout.LEFT);
		flPanel1.setVgap(0);
		
		new JLabelBuilder(panel1).setCustomLabel("Poules", new Font("Roboto", Font.BOLD, 30), Color.WHITE).build();
		
		JPanel panel2 = new JPanelBuilder(panelTitrePoule).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanel2 = (FlowLayout) panel2.getLayout();
		flPanel2.setHgap(50);
		flPanel2.setAlignment(FlowLayout.RIGHT);
		flPanel2.setVgap(0);
		
		selectionJeu = new JComboBox<String>();
		selectionJeu.setFont(new Font("Roboto", Font.PLAIN, 11));
		selectionJeu.setPreferredSize(new Dimension(205, 20));
		selectionJeu.addItem("- Sélectionnez un jeu -");
		panel2.add(selectionJeu);
		
		JPanel panelListePoules = new JPanelBuilder(panelPoule).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanelListePoules = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 1).build();
		panelPoule.add(panelListePoules, gbcPanelListePoules);
		GridBagLayout gblPanelListePoules = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{688, 0}, new int[] {208, 104, 0}, new double[]{0.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, Double.MIN_VALUE}).build();
		panelListePoules.setLayout(gblPanelListePoules);
		
		JPanel panel = new JPanelBuilder(panelListePoules).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanel = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0).build();
		panelListePoules.add(panel, gbcPanel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		// POULES //
		// TODO
		JPanel panel5 = new JPanelBuilder(panel).setCustomPanel(Couleur.BLEU1).build();
		panel5.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 35));
		
		JButton btnPoule1 = new JButtonBuilder(panel5).setCustomButton("POULE 1", Color.WHITE, new Font("Roboto", Font.BOLD, 15), Couleur.BLEU2).build();
		btnPoule1.setPreferredSize(new Dimension(200,50));
		
		JPanel panel6 = new JPanelBuilder(panel).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout = (FlowLayout) panel6.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(35);
		
		JButton btnPoule2 = new JButtonBuilder(panel6).setCustomButton("POULE 2", Color.WHITE, new Font("Roboto", Font.BOLD, 15), Couleur.BLEU2).build();
		btnPoule2.setPreferredSize(new Dimension(200,50));
		
		JPanel panel7 = new JPanelBuilder(panel).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_1 = (FlowLayout) panel7.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(20);
		
		JButton btnPoule3 = new JButtonBuilder(panel7).setCustomButton("POULE 3", Color.WHITE, new Font("Roboto", Font.BOLD, 15), Couleur.BLEU2).build();
		btnPoule3.setPreferredSize(new Dimension(200,50));
		
		JPanel panel4 = new JPanelBuilder(panel).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_2 = (FlowLayout) panel4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(20);
		flowLayout_2.setVgap(20);
		
		JButton btnPoule4 = new JButtonBuilder(panel4).setCustomButton("POULE 4", Color.WHITE, new Font("Roboto", Font.BOLD, 15), Couleur.BLEU2).build();
		btnPoule4.setPreferredSize(new Dimension(200,50));
		
		JPanel panel3 = new JPanelBuilder(panelListePoules).setCustomPanel(Couleur.BLEU1).build();
		GridBagConstraints gbcPanel3 = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 1).build();
		panelListePoules.add(panel3, gbcPanel3);
		panel3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setVgap(0);
		panel_8.setBackground(Couleur.BLEU1);
		panel3.add(panel_8, BorderLayout.NORTH);
		
		JButton btnPouleFinale = new JButtonBuilder(panel_8).setCustomButton("POULE FINALE", Color.WHITE, new Font("Roboto", Font.BOLD, 15), Couleur.BLEU2).build();
		btnPouleFinale.setPreferredSize(new Dimension(440,50));
		
		JPanel panelEquipe = new JPanelBuilder(panelGagnant).setCustomPanel(Couleur.BLEU1).build();
		panelEquipe.setBorder(new LineBorder(Color.WHITE));
		GridBagLayout gblPanelEquipe = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{688, 0}, new int[] {100, 310, 0}, new double[]{1.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, Double.MIN_VALUE}).build();
		panelEquipe.setLayout(gblPanelEquipe);
		
		JPanel panelTitreEquipe = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelTitreEquipe = (FlowLayout) panelTitreEquipe.getLayout();
		flPanelTitreEquipe.setVgap(0);
		flPanelTitreEquipe.setHgap(50);
		flPanelTitreEquipe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelTitreEquipe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelEquipe.add(panelTitreEquipe, gbcPanelTitreEquipe);
		
		new JLabelBuilder(panelTitreEquipe).setCustomLabel("Equipes", new Font("Roboto", Font.BOLD, 30), Color.WHITE).build();
		
		modeleEquipes = new DefaultListModel<String>();
		
		JPanel panelListeEquipes = new JPanelBuilder(panelEquipe).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flPanelListeEquipes = (FlowLayout) panelListeEquipes.getLayout();
		flPanelListeEquipes.setHgap(50);
		flPanelListeEquipes.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbcPanelListeEquipes = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 1).build();
		panelEquipe.add(panelListeEquipes, gbcPanelListeEquipes);
		
		listeEquipes = new JList<String>(modeleEquipes);
		listeEquipes.setVisibleRowCount(5);
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		JScrollPane scrollPane2 = new JScrollPane(listeEquipes,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelListeEquipes.add(scrollPane2);
		
		// CONTROLEUR
		ControleurConsulterEquipes controleur = new ControleurConsulterEquipes(this);
		
		// BOUTONS
		btnDeconnexion.addActionListener(controleur);
		btnPoule1.addActionListener(controleur);
		btnPoule2.addActionListener(controleur);
		btnPoule3.addActionListener(controleur);
		btnPoule4.addActionListener(controleur);
		btnPouleFinale.addActionListener(controleur);
		
		// COMPOSANTS DE DONNEES
		this.listeTournois.addListSelectionListener(controleur);
		this.selectionJeu.addActionListener(controleur);
	}
	 
	public static void fermerFenetre(JFrame f) {
		f.setVisible(false);
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
	
	// Ajouter le nom de tournoi à la liste
	public void ajouterTournoi(String nomTournoi) {
		this.modeleTournois.addElement(nomTournoi);
	}
	
	// ajouter le nom du jeu au jcombobox
	public void ajouterJeu(String nomJeu) {
		this.selectionJeu.addItem(nomJeu);
	}

	// Supprime tous les éléments de la sélection
	public void viderJeux() {
		this.selectionJeu.removeAllItems();
	}

	public String getTournoiSelectionne() {
		return this.listeTournois.getSelectedValue();
	}

	public void viderEquipes() {
		this.modeleEquipes.clear();
	}

	public String getJeuSelectionne() {
		return (String) this.selectionJeu.getSelectedItem();
	}

	public void ajouterEquipe(String nomEquipe) {
		this.modeleEquipes.addElement(nomEquipe);
	}
}
