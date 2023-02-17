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
import java.awt.Window;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import controleur.ControleurConsulterEquipes;
import controleur.ControleurConsulterEquipes.Etat;


public class VueConsulterEquipes {

	public JFrame fenetreConsulterEquipes;
	private DefaultListModel<String> modeleTournois;
	private JList<String> listeTournois;
	private DefaultListModel<String> modelePoules;
	private JList<String> listePoules;
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
		fenetreConsulterEquipes.setBounds(100, 100, 1400, 900);
		fenetreConsulterEquipes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Color.WHITE);
		fenetreConsulterEquipes.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelPDF = new JPanel();
		panelPDF.setBackground(Color.WHITE);
		FlowLayout fl_panelPDF = (FlowLayout) panelPDF.getLayout();
		fl_panelPDF.setAlignment(FlowLayout.LEFT);
		panelHeader.add(panelPDF);
		
		JButton btnPDF = new JButton("Télécharger le PDF");
		btnPDF.setBackground(Couleur.BLEU2);
		btnPDF.setForeground(Color.WHITE);
		btnPDF.setFont(new Font("Roboto", Font.BOLD, 13));
		panelPDF.add(btnPDF);
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelDeconnexion);
		
		JButton btnDeconnexion = new JButton("Se déconnecter");
		panelDeconnexion.add(btnDeconnexion);
		btnDeconnexion.setForeground(Color.WHITE);
		btnDeconnexion.setFont(new Font("Roboto", Font.BOLD, 13));
		btnDeconnexion.setBackground(Couleur.ROUGE);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreConsulterEquipes.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTournoi = new JPanel();
		panelTournoi.setBorder(new LineBorder(Color.WHITE));
		panelTournoi.setBackground(Couleur.BLEU1);
		panelContenu.add(panelTournoi);
		GridBagLayout gbl_panelTournoi = new GridBagLayout();
		gbl_panelTournoi.columnWidths = new int[]{692, 0};
		gbl_panelTournoi.rowHeights = new int[] {100, 722, 0};
		gbl_panelTournoi.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTournoi.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelTournoi.setLayout(gbl_panelTournoi);
		
		JPanel panelTitreTournoi = new JPanel();
		panelTitreTournoi.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitreTournoi = (FlowLayout) panelTitreTournoi.getLayout();
		fl_panelTitreTournoi.setVgap(0);
		fl_panelTitreTournoi.setHgap(50);
		fl_panelTitreTournoi.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreTournoi = new GridBagConstraints();
		gbc_panelTitreTournoi.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreTournoi.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreTournoi.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreTournoi.gridx = 0;
		gbc_panelTitreTournoi.gridy = 0;
		panelTournoi.add(panelTitreTournoi, gbc_panelTitreTournoi);
		
		JLabel titreTournoi = new JLabel("Tournois");
		titreTournoi.setForeground(Color.WHITE);
		titreTournoi.setFont(new Font("Roboto", Font.BOLD, 20));
		panelTitreTournoi.add(titreTournoi);
		
		JPanel panelListeTournois = new JPanel();
		panelListeTournois.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListeTournois = (FlowLayout) panelListeTournois.getLayout();
		fl_panelListeTournois.setHgap(50);
		fl_panelListeTournois.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListeTournois = new GridBagConstraints();
		gbc_panelListeTournois.fill = GridBagConstraints.BOTH;
		gbc_panelListeTournois.gridx = 0;
		gbc_panelListeTournois.gridy = 1;
		panelTournoi.add(panelListeTournois, gbc_panelListeTournois);
		
		// TOURNOIS //
		modeleTournois = new DefaultListModel<String>();
		listeTournois = new JList<String>(modeleTournois);
		listeTournois.setVisibleRowCount(13);
		listeTournois.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeTournois.setFixedCellHeight(50);
		listeTournois.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(listeTournois);
		panelListeTournois.add(scrollPane);
		
		JPanel panelGagnant = new JPanel();
		panelGagnant.setBackground(Couleur.BLEU1);
		panelGagnant.setBorder(new LineBorder(Color.WHITE));
		panelContenu.add(panelGagnant);
		panelGagnant.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelPoule = new JPanel();
		panelPoule.setBackground(Couleur.BLEU1);
		panelPoule.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelGagnant.add(panelPoule);
		GridBagLayout gbl_panelPoule = new GridBagLayout();
		gbl_panelPoule.columnWidths = new int[]{692, 0};
		gbl_panelPoule.rowHeights = new int[] {100, 312, 0};
		gbl_panelPoule.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelPoule.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelPoule.setLayout(gbl_panelPoule);
		
		JPanel panelTitrePoule = new JPanel();
		panelTitrePoule.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelTitrePoule = new GridBagConstraints();
		gbc_panelTitrePoule.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitrePoule.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitrePoule.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitrePoule.gridx = 0;
		gbc_panelTitrePoule.gridy = 0;
		panelPoule.add(panelTitrePoule, gbc_panelTitrePoule);
		panelTitrePoule.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Couleur.BLEU1);
		FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
		fl_panel_1.setHgap(50);
		fl_panel_1.setAlignment(FlowLayout.LEFT);
		fl_panel_1.setVgap(0);
		panelTitrePoule.add(panel_1);
		
		JLabel titrePoule = new JLabel("Poules");
		titrePoule.setForeground(Color.WHITE);
		titrePoule.setFont(new Font("Roboto", Font.BOLD, 20));
		panel_1.add(titrePoule);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Couleur.BLEU1);
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setHgap(50);
		fl_panel_2.setAlignment(FlowLayout.RIGHT);
		fl_panel_2.setVgap(0);
		panelTitrePoule.add(panel_2);
		
		selectionJeu = new JComboBox<String>();
		selectionJeu.setFont(new Font("Roboto", Font.PLAIN, 11));
		selectionJeu.setPreferredSize(new Dimension(205, 20));
		selectionJeu.addItem("- Sélectionnez un jeu -");
		panel_2.add(selectionJeu);
		
		JPanel panelListePoules = new JPanel();
		panelListePoules.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelListePoules = new GridBagConstraints();
		gbc_panelListePoules.fill = GridBagConstraints.BOTH;
		gbc_panelListePoules.gridx = 0;
		gbc_panelListePoules.gridy = 1;
		panelPoule.add(panelListePoules, gbc_panelListePoules);
		GridBagLayout gbl_panelListePoules = new GridBagLayout();
		gbl_panelListePoules.columnWidths = new int[]{688, 0};
		gbl_panelListePoules.rowHeights = new int[] {208, 104, 0};
		gbl_panelListePoules.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelListePoules.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelListePoules.setLayout(gbl_panelListePoules);
		
		JPanel panel = new JPanel();
		panel.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelListePoules.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Couleur.BLEU1);
		panel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 35));
		
		JButton btnPoule1 = new JButton("POULE 1");
		btnPoule1.setForeground(Color.WHITE);
		btnPoule1.setFont(new Font("Roboto", Font.BOLD, 15));
		btnPoule1.setBackground(Couleur.BLEU2);
		btnPoule1.setPreferredSize(new Dimension(200,50));
		panel_5.add(btnPoule1);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setHgap(20);
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(35);
		panel_6.setBackground(Couleur.BLEU1);
		panel.add(panel_6);
		
		JButton btnPoule2 = new JButton("POULE 2");
		btnPoule2.setForeground(Color.WHITE);
		btnPoule2.setFont(new Font("Roboto", Font.BOLD, 15));
		btnPoule2.setBackground(Couleur.BLEU2);
		btnPoule2.setPreferredSize(new Dimension(200,50));
		panel_6.add(btnPoule2);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setHgap(20);
		flowLayout_1.setVgap(20);
		panel_7.setBackground(Couleur.BLEU1);
		panel.add(panel_7);
		
		JButton btnPoule3 = new JButton("POULE 3");
		btnPoule3.setForeground(Color.WHITE);
		btnPoule3.setFont(new Font("Roboto", Font.BOLD, 15));
		btnPoule3.setBackground(Couleur.BLEU2);
		btnPoule3.setPreferredSize(new Dimension(200,50));
		panel_7.add(btnPoule3);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(20);
		flowLayout_2.setVgap(20);
		panel_4.setBackground(Couleur.BLEU1);
		panel.add(panel_4);
		
		JButton btnPoule4 = new JButton("POULE 4");
		btnPoule4.setForeground(Color.WHITE);
		btnPoule4.setFont(new Font("Roboto", Font.BOLD, 15));
		btnPoule4.setBackground(Couleur.BLEU2);
		btnPoule4.setPreferredSize(new Dimension(200,50));
		panel_4.add(btnPoule4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panelListePoules.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setVgap(0);
		panel_8.setBackground(Couleur.BLEU1);
		panel_3.add(panel_8, BorderLayout.NORTH);
		
		JButton btnPouleFinale = new JButton("POULE FINALE");
		btnPouleFinale.setForeground(Color.WHITE);
		btnPouleFinale.setFont(new Font("Roboto", Font.BOLD, 15));
		btnPouleFinale.setBackground(Couleur.BLEU2);
		btnPouleFinale.setPreferredSize(new Dimension(440,50));
		panel_8.add(btnPouleFinale);
		
		modelePoules = new DefaultListModel<String>();
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.setBackground(Couleur.BLEU1);
		panelEquipe.setBorder(new LineBorder(Color.WHITE));
		panelGagnant.add(panelEquipe);
		GridBagLayout gbl_panelEquipe = new GridBagLayout();
		gbl_panelEquipe.columnWidths = new int[]{688, 0};
		gbl_panelEquipe.rowHeights = new int[] {100, 310, 0};
		gbl_panelEquipe.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelEquipe.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelEquipe.setLayout(gbl_panelEquipe);
		
		JPanel panelTitreEquipe = new JPanel();
		panelTitreEquipe.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitreEquipe = (FlowLayout) panelTitreEquipe.getLayout();
		fl_panelTitreEquipe.setVgap(0);
		fl_panelTitreEquipe.setHgap(50);
		fl_panelTitreEquipe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreEquipe = new GridBagConstraints();
		gbc_panelTitreEquipe.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreEquipe.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreEquipe.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreEquipe.gridx = 0;
		gbc_panelTitreEquipe.gridy = 0;
		panelEquipe.add(panelTitreEquipe, gbc_panelTitreEquipe);
		
		JLabel titreEquipe = new JLabel("Equipes");
		titreEquipe.setForeground(Color.WHITE);
		titreEquipe.setFont(new Font("Roboto", Font.BOLD, 20));
		panelTitreEquipe.add(titreEquipe);
		
		modeleEquipes = new DefaultListModel<String>();
		
		JPanel panelListeEquipes = new JPanel();
		panelListeEquipes.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListeEquipes = (FlowLayout) panelListeEquipes.getLayout();
		fl_panelListeEquipes.setHgap(50);
		fl_panelListeEquipes.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListeEquipes = new GridBagConstraints();
		gbc_panelListeEquipes.fill = GridBagConstraints.BOTH;
		gbc_panelListeEquipes.gridx = 0;
		gbc_panelListeEquipes.gridy = 1;
		panelEquipe.add(panelListeEquipes, gbc_panelListeEquipes);
		listeEquipes = new JList<String>(modeleEquipes);
		listeEquipes.setVisibleRowCount(5);
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		JScrollPane scrollPane2 = new JScrollPane(listeEquipes);
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
	
	
	public Etat getEtat(JButton b) {
		if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} if (b.getText().equals("POULE 1")) {
			return Etat.POULE1;
		} if (b.getText().equals("POULE 2")) {
			return Etat.POULE2;
		} if (b.getText().equals("POULE 3")) {
			return Etat.POULE3;
		} if (b.getText().equals("POULE 4")) {
			return Etat.POULE4;
		} if (b.getText().equals("POULE FINALE")) {
			return Etat.POULEF;
		}
		
		return null;
	}
	
	// Ajouter le nom de tournoi Ã  la liste
	public void ajouterTournoi(String nomTournoi) {
		this.modeleTournois.addElement(nomTournoi);
	}
	
	// ajouter le nom du jeu au jcombobox
	public void ajouterJeu(String nomJeu) {
		this.selectionJeu.addItem(nomJeu);
	}

	// Supprime tous les Ã©lÃ©ments de la sÃ©lection
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
