package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modele.EtatFactory;
import modele.GridBagConstraintsBuilder;
import modele.GridBagLayoutBuilder;
import modele.JLabelBuilder;
import modele.JPanelBuilder;
import modele.Etat;
import controleur.ControleurClassement;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class VueClassement implements Vue{

	public JFrame fenetreClassement;
	
	private JLabel nomPremier = new JLabel("1er");
	private JLabel nomDeuxieme = new JLabel("2e");
	private JLabel nomTroisieme = new JLabel("3e");
	private DefaultListModel<String> modelePodium = new DefaultListModel<String>();
	private JList<String> listePodium;
	private JComboBox<String> triJeux = new JComboBox<String>();
	
	public JFrame getFrame() {
		return this.fenetreClassement;
	}

	public VueClassement() {
		// CREATION DE LA FENETRE //
		fenetreClassement = new JFrame();
		fenetreClassement.getContentPane().setBackground(Couleur.BLEU1);
		fenetreClassement.setResizable(false);
		fenetreClassement.setBounds(100, 100, 1500, 880);
		fenetreClassement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// CONTROLEUR
		ControleurClassement controleur = new ControleurClassement(this);
		
		// HEADER //
		if (controleur.estProfil("Gestionnaire")) {
			HeaderAdmin header = new HeaderAdmin(this.getFrame());
			header.getBtnDeconnexion().addActionListener(controleur);
			header.getBtnEquipes().addActionListener(controleur);
			header.getBtnJoueurs().addActionListener(controleur);
			header.getBtnCalendrier().addActionListener(controleur);
			header.getBtnEcuries().addActionListener(controleur);
			Vue.desactiverBouton(header.getBtnClassement());
		}
		
		if (controleur.estProfil("Ecurie")) {
			HeaderEcurie header = new HeaderEcurie(this.getFrame());
			header.getBtnDeconnexion().addActionListener(controleur);
			header.getBtnEquipes().addActionListener(controleur);
			header.getBtnJoueurs().addActionListener(controleur);
			header.getBtnTournois().addActionListener(controleur);
			Vue.desactiverBouton(header.getBtnClassement());

		}

		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreClassement.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelPodium = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		GridBagLayout gblPanelPodium = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {100, 724, 0, 0}, new double[]{0.0, Double.MIN_VALUE}, new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE}).build();
		panelPodium.setLayout(gblPanelPodium);
		
		JPanel panelTitrePodium = new JPanelBuilder(panelPodium).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout = (FlowLayout) panelTitrePodium.getLayout();
		flowLayout.setVgap(0);
		GridBagConstraints gbcPanelTitrePodium = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelPodium.add(panelTitrePodium, gbcPanelTitrePodium);
		
		new JLabelBuilder(panelTitrePodium).setCustomLabel("PODIUM", new Font("Roboto", Font.BOLD, 20), Color.WHITE).build();
		
		JPanel panelImgPodium = new JPanelBuilder(panelPodium).setCustomPanel(Couleur.BLEU1).build();
		panelImgPodium.setLayout(null);
		GridBagConstraints gbcPanelImgPodium = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 1).build();
		panelPodium.add(panelImgPodium, gbcPanelImgPodium);
		
		JLabel imgPodium = new JLabelBuilder(panelImgPodium).setCustomLabel("", new Font("Roboto", Font.BOLD, 11), Color.WHITE).build();
		Image img = new ImageIcon(this.getClass().getResource("podium.png")).getImage();
		imgPodium.setBounds(80, 96, 535, 521);
		imgPodium.setIcon(new ImageIcon(img));
		
		nomPremier.setForeground(Color.WHITE);
		nomPremier.setHorizontalAlignment(SwingConstants.CENTER);
		nomPremier.setFont(new Font("Roboto", Font.BOLD, 14));
		nomPremier.setBounds(263, 293, 206, 32);
		panelImgPodium.add(nomPremier);
		
		nomDeuxieme.setForeground(Color.WHITE);
		nomDeuxieme.setHorizontalAlignment(SwingConstants.CENTER);
		nomDeuxieme.setFont(new Font("Roboto", Font.BOLD, 14));
		nomDeuxieme.setBounds(56, 374, 206, 32);
		panelImgPodium.add(nomDeuxieme);
		
		nomTroisieme.setForeground(Color.WHITE);
		nomTroisieme.setHorizontalAlignment(SwingConstants.CENTER);
		nomTroisieme.setFont(new Font("Roboto", Font.BOLD, 14));
		nomTroisieme.setBounds(451, 447, 164, 24);
		panelImgPodium.add(nomTroisieme);
		
		JPanel panelFinPodium = new JPanelBuilder(panelContenu).setCustomPanel(Couleur.BLEU1).build();
		GridBagLayout gblPanelFinPodium = new GridBagLayoutBuilder().setCustomGridBagLayout(new int[]{692, 0}, new int[] {100, 724, 0}, new double[]{0.0, Double.MIN_VALUE}, new double[]{0.0, 0.0}).build();
		panelFinPodium.setLayout(gblPanelFinPodium);
		
		JPanel panelTriJeu = new JPanelBuilder(panelFinPodium).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_1 = (FlowLayout) panelTriJeu.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setVgap(0);
		GridBagConstraints gbcPanelTriJeu = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0).build();
		panelFinPodium.add(panelTriJeu, gbcPanelTriJeu);
		
		triJeux.setPreferredSize(new Dimension(205, 20));
		panelTriJeu.add(triJeux);
		
		JPanel panelListe = new JPanelBuilder(panelFinPodium).setCustomPanel(Couleur.BLEU1).build();
		FlowLayout flowLayout_2 = (FlowLayout) panelListe.getLayout();
		flowLayout_2.setVgap(50);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(50);
		GridBagConstraints gbcPanelListe = new GridBagConstraintsBuilder().setCustomGridBagConstraints(GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 1).build();
		panelFinPodium.add(panelListe, gbcPanelListe);
		
		listePodium = new JList<String>(this.modelePodium);
		listePodium.setVisibleRowCount(14);
		listePodium.setFixedCellHeight(40);
		listePodium.setFixedCellWidth(600);
		listePodium.setEnabled(false);
		listePodium.setForeground(Color.red);
		JScrollPane scrollPane = new JScrollPane(listePodium);
		panelListe.add(scrollPane);

		// COMBOBOX DE JEUX
		this.triJeux.addActionListener(controleur);
	}
	
	public static void fermerFenetre(JFrame f) {
		f.setVisible(false);
	}
	
	public Etat getEtat(JButton b) {
		Etat etat = EtatFactory.creerEtat(b.getText());
		return etat;
	}
	
	public void ajouterEquipe(String nomEquipe) {
		this.modelePodium.addElement(nomEquipe);
	}

	public void setPodium(int place, String nomEquipe, int nbPoints) {
		switch (place) {
		case 1:
			this.nomPremier.setText("<html>" + 
									nomEquipe + " (" +
									nbPoints + ")</html>");
			break;
		case 2:
			this.nomDeuxieme.setText("<html>" + 
					nomEquipe + " (" +
					nbPoints + ")</html>");
			break;
		case 3:
			this.nomTroisieme.setText("<html>" + 
					nomEquipe + " (" +
					nbPoints + ")</html>");
			break;
		default:
			break;
		}
	}

	public String getJeu() {
		return (String) this.triJeux.getSelectedItem();
	}

	public void ajouterJeu(String nomJeu) {
		this.triJeux.addItem(nomJeu);
	}
	
	public void viderClassement() {
		this.modelePodium.clear();
		this.nomPremier.setText("1er");
		this.nomDeuxieme.setText("2e");
		this.nomTroisieme.setText("3e");
	}
}
