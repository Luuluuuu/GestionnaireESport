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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import controleur.ControleurCalendrier;
import controleur.ControleurCalendrier.Etat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class VueCalendrier extends JFrame{
	
	public JFrame fenetreCalendrier;
	public JTextField entreeNom;
	public DatePicker entreeDate;
	public TimePicker entreeHeure;
	public JPanel panelModif;
	public JLabel titreModif;
	private DefaultListModel<String> modeleTournois;
	private JList<String> listeTournois;
	private JComboBox<String> entreeEchelle;
	private JComboBox<String> entreeResponsable;
	private JComboBox<String> entreeArbitre;
	private static Map<String, JCheckBox> listeCheck = new HashMap<String, JCheckBox>();
	private static JPanel panel_13;
	
	public JFrame getFrame() {
		return this.fenetreCalendrier;
	}
	
	public VueCalendrier() {
		// CREATION DE LA FENETRE //
		fenetreCalendrier = new JFrame();
		fenetreCalendrier.getContentPane().setBackground(Couleur.BLEU1);
		fenetreCalendrier.setResizable(false);
		fenetreCalendrier.setBounds(100, 100, 1400, 900);
		fenetreCalendrier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// HEADER //
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fenetreCalendrier.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelHeader.add(panelMenu);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCalendrier = new JButton("Calendrier");
		btnCalendrier.setForeground(Color.WHITE);
		btnCalendrier.setFont(new Font("Roboto", Font.BOLD, 15));
		btnCalendrier.setBackground(Couleur.BLEU2);
		panelMenu.add(btnCalendrier);
		
		JButton btnEcuries = new JButton("Ecuries / Responsables / Arbitres");
		btnEcuries.setForeground(Color.WHITE);
		btnEcuries.setFont(new Font("Roboto", Font.BOLD, 15));
		btnEcuries.setBackground(Couleur.BLEU2);
		panelMenu.add(btnEcuries);
		
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
		fenetreCalendrier.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelTournoi = new JPanel();
		panelTournoi.setBackground(Couleur.BLEU1);
		panelContenu.add(panelTournoi);
		GridBagLayout gbl_panelTournoi = new GridBagLayout();
		gbl_panelTournoi.columnWidths = new int[]{692, 0};
		gbl_panelTournoi.rowHeights = new int[] {100, 622, 100, 0};
		gbl_panelTournoi.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTournoi.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelTournoi.setLayout(gbl_panelTournoi);
		
		JPanel panelTitreT = new JPanel();
		panelTitreT.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitreT = (FlowLayout) panelTitreT.getLayout();
		fl_panelTitreT.setVgap(0);
		fl_panelTitreT.setHgap(50);
		fl_panelTitreT.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreT = new GridBagConstraints();
		gbc_panelTitreT.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreT.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreT.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreT.gridx = 0;
		gbc_panelTitreT.gridy = 0;
		panelTournoi.add(panelTitreT, gbc_panelTitreT);
		
		// LISTE DES TOURNOIS //
		JLabel Tournois = new JLabel("Tournois");
		Tournois.setForeground(Color.WHITE);
		Tournois.setFont(new Font("Roboto", Font.BOLD, 20));
		Tournois.setHorizontalAlignment(SwingConstants.LEFT);
		panelTitreT.add(Tournois);
		
		JPanel panelListe = new JPanel();
		panelListe.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListe = (FlowLayout) panelListe.getLayout();
		fl_panelListe.setHgap(50);
		fl_panelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListe = new GridBagConstraints();
		gbc_panelListe.insets = new Insets(0, 0, 5, 0);
		gbc_panelListe.fill = GridBagConstraints.BOTH;
		gbc_panelListe.gridx = 0;
		gbc_panelListe.gridy = 1;
		panelTournoi.add(panelListe, gbc_panelListe);
		
		modeleTournois = new DefaultListModel<String>();
		listeTournois = new JList<String>(modeleTournois);
		listeTournois.setVisibleRowCount(12);
		listeTournois.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeTournois.setFixedCellHeight(50);
		listeTournois.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(listeTournois);
		panelListe.add(scrollPane);
		
		JPanel panelBoutons = new JPanel();
		panelBoutons.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelBoutons = new GridBagConstraints();
		gbc_panelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBoutons.gridx = 0;
		gbc_panelBoutons.gridy = 2;
		panelTournoi.add(panelBoutons, gbc_panelBoutons);
		panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		JButton btnCreer = new JButton("Créer un nouveau tournoi");
		btnCreer.setForeground(Color.WHITE);
		btnCreer.setFont(new Font("Roboto", Font.BOLD, 13));
		btnCreer.setBackground(Couleur.BLEU2);
		panelBoutons.add(btnCreer);
		
		JButton btnSupprimer = new JButton("Supprimer le tournoi sélectionné");
		btnSupprimer.setForeground(Color.WHITE);
		btnSupprimer.setFont(new Font("Roboto", Font.BOLD, 13));
		btnSupprimer.setBackground(Couleur.GRIS);
		panelBoutons.add(btnSupprimer);
		
		// CREER OU MODIFIER UN TOURNOI
		panelModif = new JPanel();
		panelModif.setBackground(Couleur.BLEU1);
		panelContenu.add(panelModif);
		GridBagLayout gbl_panelModif = new GridBagLayout();
		gbl_panelModif.columnWidths = new int[]{692, 0};
		gbl_panelModif.rowHeights = new int[] {100, 70, 70, 70, 70, 70, 70, 204, 100, 0};
		gbl_panelModif.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelModif.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelModif.setLayout(gbl_panelModif);
			
		JPanel panelTitreM = new JPanel();
		panelTitreM.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitreM = (FlowLayout) panelTitreM.getLayout();
		fl_panelTitreM.setVgap(0);
		fl_panelTitreM.setHgap(50);
		fl_panelTitreM.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreM = new GridBagConstraints();
		gbc_panelTitreM.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreM.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreM.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreM.gridx = 0;
		gbc_panelTitreM.gridy = 0;
		panelModif.add(panelTitreM, gbc_panelTitreM);
		
		titreModif = new JLabel("Créer un tournoi");
		titreModif.setForeground(Color.WHITE);
		titreModif.setFont(new Font("Roboto", Font.BOLD, 20));
		panelTitreM.add(titreModif);
		
		JPanel panelNom = new JPanel();
		panelNom.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelNom = new GridBagConstraints();
		gbc_panelNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNom.insets = new Insets(0, 0, 5, 0);
		gbc_panelNom.gridx = 0;
		gbc_panelNom.gridy = 1;
		panelModif.add(panelNom, gbc_panelNom);
		GridBagLayout gbl_panelNom = new GridBagLayout();
		gbl_panelNom.columnWidths = new int[] {250, 442, 0};
		gbl_panelNom.rowHeights = new int[]{30, 0};
		gbl_panelNom.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelNom.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelNom.setLayout(gbl_panelNom);
		
		JPanel panel = new JPanel();
		panel.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelNom.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 65, 5));
		
		JLabel nom = new JLabel("Nom du tournoi");
		nom.setFont(new Font("Roboto", Font.BOLD, 14));
		panel.add(nom);
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panelNom.add(panel_1, gbc_panel_1);
		
		entreeNom = new JTextField();
		entreeNom.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_1.add(entreeNom);
		entreeNom.setColumns(20);
		
		JPanel panelDate = new JPanel();
		panelDate.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelDate = new GridBagConstraints();
		gbc_panelDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDate.insets = new Insets(0, 0, 5, 0);
		gbc_panelDate.gridx = 0;
		gbc_panelDate.gridy = 2;
		panelModif.add(panelDate, gbc_panelDate);
		GridBagLayout gbl_panelDate = new GridBagLayout();
		gbl_panelDate.columnWidths = new int[] {250, 442, 0};
		gbl_panelDate.rowHeights = new int[]{30, 0};
		gbl_panelDate.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDate.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDate.setLayout(gbl_panelDate);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_5 = (FlowLayout) panel_4.getLayout();
		flowLayout_5.setHgap(65);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panelDate.add(panel_4, gbc_panel_4);
		
		JLabel date = new JLabel("Date du tournoi");
		date.setHorizontalAlignment(SwingConstants.CENTER);
		date.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_4.add(date);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_6 = (FlowLayout) panel_5.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 0;
		panelDate.add(panel_5, gbc_panel_5);
		
		DatePickerSettings paramDate = new DatePickerSettings();
		paramDate.setAllowEmptyDates(false);
		paramDate.setFormatForDatesCommonEra(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		
		entreeDate = new DatePicker(paramDate);
		entreeDate.setToolTipText("");
		entreeDate.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeDate.setPreferredSize(new Dimension(205,20));
		panel_5.add(entreeDate);
		
		JPanel panelHeure = new JPanel();
		panelHeure.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelHeure = new GridBagConstraints();
		gbc_panelHeure.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelHeure.insets = new Insets(0, 0, 5, 0);
		gbc_panelHeure.gridx = 0;
		gbc_panelHeure.gridy = 3;
		panelModif.add(panelHeure, gbc_panelHeure);
		GridBagLayout gbl_panelHeure = new GridBagLayout();
		gbl_panelHeure.columnWidths = new int[] {250, 442, 0};
		gbl_panelHeure.rowHeights = new int[]{30, 0};
		gbl_panelHeure.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelHeure.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelHeure.setLayout(gbl_panelHeure);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_7 = (FlowLayout) panel_6.getLayout();
		flowLayout_7.setHgap(65);
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panelHeure.add(panel_6, gbc_panel_6);
		
		JLabel heure = new JLabel("Heure du tournoi");
		heure.setHorizontalAlignment(SwingConstants.CENTER);
		heure.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_6.add(heure);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_8 = (FlowLayout) panel_7.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panelHeure.add(panel_7, gbc_panel_7);
		
		TimePickerSettings paramHeure = new TimePickerSettings();
		paramHeure.setAllowEmptyTimes(false);
		entreeHeure = new TimePicker(paramHeure);
		entreeHeure.setToolTipText("");
		entreeHeure.setFont(new Font("Roboto", Font.PLAIN, 11));
		panel_7.add(entreeHeure);
		entreeHeure.setPreferredSize(new Dimension(205,20));
		
		JPanel panelEchelle = new JPanel();
		panelEchelle.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelEchelle = new GridBagConstraints();
		gbc_panelEchelle.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelEchelle.insets = new Insets(0, 0, 5, 0);
		gbc_panelEchelle.gridx = 0;
		gbc_panelEchelle.gridy = 4;
		panelModif.add(panelEchelle, gbc_panelEchelle);
		GridBagLayout gbl_panelEchelle = new GridBagLayout();
		gbl_panelEchelle.columnWidths = new int[] {250, 442, 0};
		gbl_panelEchelle.rowHeights = new int[]{30, 0};
		gbl_panelEchelle.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEchelle.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelEchelle.setLayout(gbl_panelEchelle);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_9 = (FlowLayout) panel_2.getLayout();
		flowLayout_9.setHgap(65);
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panelEchelle.add(panel_2, gbc_panel_2);
		
		JLabel echelle = new JLabel("Echelle du tournoi");
		echelle.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_2.add(echelle);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panelEchelle.add(panel_3, gbc_panel_3);
		
		entreeEchelle = new JComboBox<String>();
		entreeEchelle.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeEchelle.setPreferredSize(new Dimension(205, 20));
		panel_3.add(entreeEchelle);
		entreeEchelle.addItem("- Sélectionnez une échelle -");
		entreeEchelle.addItem("locale");
		entreeEchelle.addItem("nationale");
		entreeEchelle.addItem("internationale");
		
		JPanel panelResponsable = new JPanel();
		panelResponsable.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelResponsable = new GridBagConstraints();
		gbc_panelResponsable.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelResponsable.insets = new Insets(0, 0, 5, 0);
		gbc_panelResponsable.gridx = 0;
		gbc_panelResponsable.gridy = 5;
		panelModif.add(panelResponsable, gbc_panelResponsable);
		GridBagLayout gbl_panelResponsable = new GridBagLayout();
		gbl_panelResponsable.columnWidths = new int[] {150, 542, 0};
		gbl_panelResponsable.rowHeights = new int[]{30, 0};
		gbl_panelResponsable.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelResponsable.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelResponsable.setLayout(gbl_panelResponsable);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_10 = (FlowLayout) panel_8.getLayout();
		flowLayout_10.setHgap(65);
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.insets = new Insets(0, 0, 0, 5);
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 0;
		panelResponsable.add(panel_8, gbc_panel_8);
		
		JLabel responsable = new JLabel("Responsable du tournoi");
		responsable.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_8.add(responsable);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_12 = (FlowLayout) panel_9.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 1;
		gbc_panel_9.gridy = 0;
		panelResponsable.add(panel_9, gbc_panel_9);
		
		entreeResponsable = new JComboBox<String>();
		entreeResponsable.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeResponsable.setPreferredSize(new Dimension(205, 20));
		panel_9.add(entreeResponsable);
		entreeResponsable.addItem("- Sélectionnez un responsable -");
		
		JPanel panelArbitre = new JPanel();
		panelArbitre.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelArbitre = new GridBagConstraints();
		gbc_panelArbitre.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelArbitre.insets = new Insets(0, 0, 5, 0);
		gbc_panelArbitre.gridx = 0;
		gbc_panelArbitre.gridy = 6;
		panelModif.add(panelArbitre, gbc_panelArbitre);
		GridBagLayout gbl_panelArbitre = new GridBagLayout();
		gbl_panelArbitre.columnWidths = new int[] {250, 442, 0};
		gbl_panelArbitre.rowHeights = new int[]{30, 0};
		gbl_panelArbitre.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelArbitre.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelArbitre.setLayout(gbl_panelArbitre);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_11 = (FlowLayout) panel_10.getLayout();
		flowLayout_11.setHgap(65);
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.insets = new Insets(0, 0, 0, 5);
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 0;
		panelArbitre.add(panel_10, gbc_panel_10);
		
		JLabel arbitre = new JLabel("Arbitre du tournoi");
		arbitre.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_10.add(arbitre);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_13 = (FlowLayout) panel_11.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 1;
		gbc_panel_11.gridy = 0;
		panelArbitre.add(panel_11, gbc_panel_11);

		entreeArbitre = new JComboBox<String>();
		entreeArbitre.setFont(new Font("Roboto", Font.PLAIN, 11));
		entreeArbitre.setPreferredSize(new Dimension(205, 20));
		panel_11.add(entreeArbitre);
		entreeArbitre.addItem("- Sélectionnez un arbitre -");
		
		JPanel panelJeu = new JPanel();
		panelJeu.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelJeu = new GridBagConstraints();
		gbc_panelJeu.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelJeu.insets = new Insets(0, 0, 5, 0);
		gbc_panelJeu.gridx = 0;
		gbc_panelJeu.gridy = 7;
		panelModif.add(panelJeu, gbc_panelJeu);
		GridBagLayout gbl_panelJeu = new GridBagLayout();
		gbl_panelJeu.columnWidths = new int[] {250, 442, 0};
		gbl_panelJeu.rowHeights = new int[] {199, 0};
		gbl_panelJeu.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelJeu.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelJeu.setLayout(gbl_panelJeu);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_2 = (FlowLayout) panel_12.getLayout();
		flowLayout_2.setHgap(65);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setVgap(20);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.insets = new Insets(0, 0, 0, 5);
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 0;
		panelJeu.add(panel_12, gbc_panel_12);
		
		JLabel jeu = new JLabel("Ajouter des jeux");
		jeu.setFont(new Font("Roboto", Font.BOLD, 14));
		panel_12.add(jeu);
		
		panel_13 = new JPanel();
		panel_13.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.gridx = 1;
		gbc_panel_13.gridy = 0;
		panelJeu.add(panel_13, gbc_panel_13);
		panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelValider = new JPanel();
		panelValider.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraints();
		gbc_panelValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelValider.gridx = 0;
		gbc_panelValider.gridy = 8;
		panelModif.add(panelValider, gbc_panelValider);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setForeground(Color.WHITE);
		btnValider.setFont(new Font("Roboto", Font.BOLD, 13));
		btnValider.setBackground(Couleur.VERT);
		panelValider.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Roboto", Font.BOLD, 13));
		btnAnnuler.setBackground(Couleur.GRIS);
		panelValider.add(btnAnnuler);
		
		// CONTROLEUR
		ControleurCalendrier controleur = new ControleurCalendrier(this);
		// DECONNEXION
		btnDeconnexion.addActionListener(controleur);
		// TOURNOIS
		listeTournois.addListSelectionListener((ListSelectionListener) controleur);
		// GESTION DES TOURNOIS
		btnCreer.addActionListener(controleur);
		btnSupprimer.addActionListener(controleur);
		// VALIDER OU ANNULER INFORMATIONS SUR LE TOURNOI
		btnAnnuler.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		// BOUTONS MENU
		btnEquipes.addActionListener(controleur);
		btnEcuries.addActionListener(controleur);
		btnJoueurs.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
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
	
	public void ajouterJeu(String nom) {
		JCheckBox cb = new JCheckBox(nom);
		cb.setFont(new Font("Roboto", Font.PLAIN, 13));
		cb.setBackground(new Color(123,149,184));
		panel_13.add(cb);
		listeCheck.put(nom,cb);
	}
	
	public void creerTournoi() {
		this.deselectionner();
		VueCalendrier.afficherPanel(panelModif);
		VueCalendrier.afficherTexte(this.titreModif, "Créer un tournoi");
		VueCalendrier.supprimerTexte(this.entreeNom);
		this.entreeDate.setDate(LocalDate.now());
		this.entreeHeure.setTime(LocalTime.now());
		this.setEchelle("- Sélectionnez une échelle -");
		entreeEchelle.setForeground(new Color(0,0,0));
		this.setArbitre("- Sélectionnez un arbitre -");
		entreeArbitre.setForeground(new Color(0,0,0));
		this.setResponsable("- Sélectionnez un responsable -");
		entreeResponsable.setForeground(new Color(0,0,0));
		this.setJeux(new ArrayList<String>());
	}
	
	public Etat getEtat(JButton b) {
		if (b.getText() == "Créer un nouveau tournoi") {
			return Etat.CREER;
		} else if (b.getText() == "Annuler") {
			return Etat.ANNULER;
		} else if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Supprimer le tournoi sélectionné") {
			return Etat.SUPPRIMER;
		} else if (b.getText() == "Ecuries / Responsables / Arbitres") {
			return Etat.ECURIE;
		} else if (b.getText() == "Valider") {
			return Etat.VALIDER;
		} else if (b.getText() == "Classement") {
			return Etat.CLASSEMENT;
		} else if (b.getText() == "Equipes") {
			return Etat.EQUIPES;
		} else if (b.getText() == "Joueurs") {
			return Etat.JOUEURS;
		}
		
		return null;
	}
	
	// GERER TOURNOI
	public void deselectionner() {
		this.listeTournois.clearSelection();
	}
	public void ajouterTournoi(String nomTournoi) {
		this.modeleTournois.addElement(nomTournoi);
	}
	
	public void supprimerTournoi() {
		this.modeleTournois.removeElement(this.entreeNom.getText());
		this.deselectionner();
	}
	
	public void modifierTournoi() {
		this.modeleTournois.set(this.listeTournois.getSelectedIndex(), this.entreeNom.getText());
	}
	
	// AJOUTER AUX COMBOS //
	public void ajouterResponsable(String nomResponsable) {
		this.entreeResponsable.addItem(nomResponsable);
	}
	
	public void ajouterArbitre(String nomArbitre) {
		this.entreeArbitre.addItem(nomArbitre);
	}
	
	// GETTERS //
	public String getTournoiSelectionne() {
		return this.listeTournois.getSelectedValue();
	}
	
	public String getResponsable() {
		return (String) this.entreeResponsable.getSelectedItem();
	}

	public String getArbitre() {
		return (String) this.entreeArbitre.getSelectedItem();
	}
	
	public List<String> getJeux() {
		List<String> jeuxSelectionnes = new ArrayList<String>();
		for (JCheckBox cb : VueCalendrier.listeCheck.values()) {
			if (cb.isSelected()) {
				jeuxSelectionnes.add(cb.getText());
			}
		}
		return jeuxSelectionnes;
	}

	public String getEchelle() {
		return (String) this.entreeEchelle.getSelectedItem();
	}
	
	// SETTERS //
	public void setArbitre(String nomArbitre) {
		this.entreeArbitre.setSelectedItem(nomArbitre);
	}
	
	public void setResponsable(String nomResponsable) {
		this.entreeResponsable.setSelectedItem(nomResponsable);
	}
	
	public void setEchelle(String echelle) {
		this.entreeEchelle.setSelectedItem(echelle);
	}
	
	public void setJeux(List<String> jeux) {
		for (JCheckBox checkBox : VueCalendrier.listeCheck.values()) {
			checkBox.setSelected(false);
		}
		for (String nomJeu : jeux) {
			VueCalendrier.listeCheck.get(nomJeu).setSelected(true);
		}
	}
	
	// VERIFICATION //
	public boolean texteEstRempli(JTextField t) {
		return !(t.getText()=="");
	}
	
	public boolean comboEstSelectionne(JComboBox<String> c) {
		if (c.getSelectedIndex()==0) {
			c.setForeground(new Color(255,0,0));
			return false;
		}
		c.setForeground(new Color(0,0,0));
		return true;
	}
	
	public boolean jeuEstSelectionne() {
		return this.getJeux().size()!=0;
	}
	
	public boolean estRemplie() {
		return this.texteEstRempli(entreeNom) && this.entreeDate != null && this.entreeHeure != null
				&& this.comboEstSelectionne(entreeEchelle) && this.comboEstSelectionne(entreeResponsable)
				&& this.comboEstSelectionne(entreeArbitre) && this.jeuEstSelectionne();
	}
	
	public int confirmerSuppression() {
		return JOptionPane.showConfirmDialog(null, "Confirmez-vous la suppression ?","Confirmation",JOptionPane.YES_NO_OPTION);
	}
	
	public void tournoiExiste() {
		JOptionPane.showMessageDialog(null, "Le tournoi existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}