package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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


public class VueConsulterEquipes {

	private JFrame fenetreConsulterEquipes;
	private DefaultListModel<String> modeleTournois;
	private JList<String> listeTournois;
	private DefaultListModel<String> modelePoules;
	private JList<String> listePoules;
	private DefaultListModel<String> modeleEquipes;
	private JList<String> listeEquipes;
	
	public Window getFrame() {
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
		
		JButton btnPDF = new JButton("T\u00E9l\u00E9charger le PDF");
		btnPDF.setBackground(Couleur.BLEU2);
		btnPDF.setForeground(Color.WHITE);
		btnPDF.setFont(new Font("Roboto", Font.BOLD, 13));
		panelPDF.add(btnPDF);
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelDeconnexion);
		
		JButton btnDeconnexion = new JButton("Se d\u00E9connecter");
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
		
		modeleTournois = new DefaultListModel<String>();
		listeTournois = new JList<String>(modeleTournois);
		listeTournois.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeTournois.setFixedCellHeight(50);
		listeTournois.setFixedCellWidth(600);
		panelListeTournois.add(listeTournois);
		
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
		
		JComboBox selectionJeu = new JComboBox();
		selectionJeu.setFont(new Font("Roboto", Font.PLAIN, 11));
		selectionJeu.setPreferredSize(new Dimension(205, 20));
		selectionJeu.addItem("- Sélectionnez un jeu -");
		panel_2.add(selectionJeu);
		
		JPanel panelListePoules = new JPanel();
		panelListePoules.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListePoules = (FlowLayout) panelListePoules.getLayout();
		fl_panelListePoules.setAlignment(FlowLayout.LEFT);
		fl_panelListePoules.setHgap(50);
		GridBagConstraints gbc_panelListePoules = new GridBagConstraints();
		gbc_panelListePoules.fill = GridBagConstraints.BOTH;
		gbc_panelListePoules.gridx = 0;
		gbc_panelListePoules.gridy = 1;
		panelPoule.add(panelListePoules, gbc_panelListePoules);
		
		modelePoules = new DefaultListModel<String>();
		listePoules = new JList<String>(modelePoules);
		listePoules.setFont(new Font("Roboto", Font.PLAIN, 15));
		listePoules.setFixedCellHeight(50);
		listePoules.setFixedCellWidth(600);
		panelListePoules.add(listePoules);
		
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
		listeEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEquipes.setFixedCellHeight(50);
		listeEquipes.setFixedCellWidth(600);
		panelListeEquipes.add(listeEquipes);	
	}
}
