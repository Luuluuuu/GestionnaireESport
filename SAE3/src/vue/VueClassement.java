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

import controleur.ControleurClassement.Etat;
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
		fenetreClassement.setBounds(100, 100, 1400, 900);
		fenetreClassement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// CONTROLEUR
		ControleurClassement controleur = new ControleurClassement(this);
		
		// HEADER //
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		fenetreClassement.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelHeader.add(panelMenu);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		if (controleur.estProfil("Gestionnaire")) {
			JButton btnCalendrier = creerBouton(panelMenu, "Calendrier", Couleur.BLEU2, 15);
			btnCalendrier.addActionListener(controleur);
			
			JButton btnEcuries = creerBouton(panelMenu, "Ecuries / Responsables / Arbitres", Couleur.BLEU2, 15);
			btnEcuries.addActionListener(controleur);
		}
		
		if (controleur.estProfil("Ecurie")) {
			JButton btnTournois = creerBouton(panelMenu, "Tournois", Couleur.BLEU2, 15);
			btnTournois.addActionListener(controleur);
		}

		JButton btnEquipes = creerBouton(panelMenu, "Equipes", Couleur.BLEU2, 15);
		
		JButton btnJoueurs = creerBouton(panelMenu, "Joueurs", Couleur.BLEU2, 15);
	
		JButton btnClassement = creerBouton(panelMenu, "Classement", Couleur.BLEU2, 15);
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelDeconnexion);
		
		JButton btnDeconnexion = creerBouton(panelDeconnexion, "Se déconnecter", Couleur.ROUGE, 13);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreClassement.getContentPane().add(panelContenu, BorderLayout.CENTER);
		panelContenu.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelPodium = new JPanel();
		panelPodium.setBackground(Couleur.BLEU1);
		panelContenu.add(panelPodium);
		GridBagLayout gbl_panelPodium = new GridBagLayout();
		gbl_panelPodium.columnWidths = new int[]{692, 0};
		gbl_panelPodium.rowHeights = new int[] {100, 724, 0, 0};
		gbl_panelPodium.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelPodium.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPodium.setLayout(gbl_panelPodium);
		
		JPanel panelTitrePodium = new JPanel();
		panelTitrePodium.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout = (FlowLayout) panelTitrePodium.getLayout();
		flowLayout.setVgap(0);
		GridBagConstraints gbc_panelTitrePodium = new GridBagConstraints();
		gbc_panelTitrePodium.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitrePodium.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitrePodium.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitrePodium.gridx = 0;
		gbc_panelTitrePodium.gridy = 0;
		panelPodium.add(panelTitrePodium, gbc_panelTitrePodium);
		
		JLabel titrePodium = new JLabel("PODIUM");
		titrePodium.setForeground(Color.WHITE);
		titrePodium.setFont(new Font("Roboto", Font.BOLD, 20));
		panelTitrePodium.add(titrePodium);
		
		JPanel panelImgPodium = new JPanel();
		panelImgPodium.setBackground(Couleur.BLEU1);
		panelImgPodium.setLayout(null);
		GridBagConstraints gbc_panelImgPodium = new GridBagConstraints();
		gbc_panelImgPodium.insets = new Insets(0, 0, 5, 0);
		gbc_panelImgPodium.fill = GridBagConstraints.BOTH;
		gbc_panelImgPodium.gridx = 0;
		gbc_panelImgPodium.gridy = 1;
		panelPodium.add(panelImgPodium, gbc_panelImgPodium);
		
		JLabel imgPodium = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("podium.png")).getImage();
		imgPodium.setBounds(80, 96, 535, 521);
		imgPodium.setIcon(new ImageIcon(img));
		panelImgPodium.add(imgPodium);
		
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
		
		JPanel panelFinPodium = new JPanel();
		panelFinPodium.setBackground(Couleur.BLEU1);
		panelContenu.add(panelFinPodium);
		GridBagLayout gbl_panelFinPodium = new GridBagLayout();
		gbl_panelFinPodium.columnWidths = new int[]{692, 0};
		gbl_panelFinPodium.rowHeights = new int[] {100, 724, 0};
		gbl_panelFinPodium.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelFinPodium.rowWeights = new double[]{0.0, 0.0};
		panelFinPodium.setLayout(gbl_panelFinPodium);
		
		JPanel panelTriJeu = new JPanel();
		panelTriJeu.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panelTriJeu.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setVgap(0);
		GridBagConstraints gbc_panelTriJeu = new GridBagConstraints();
		gbc_panelTriJeu.anchor = GridBagConstraints.SOUTH;
		gbc_panelTriJeu.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTriJeu.insets = new Insets(0, 0, 5, 0);
		gbc_panelTriJeu.gridx = 0;
		gbc_panelTriJeu.gridy = 0;
		panelFinPodium.add(panelTriJeu, gbc_panelTriJeu);
		
		triJeux.setPreferredSize(new Dimension(205, 20));
		panelTriJeu.add(triJeux);
		
		JPanel panelListe = new JPanel();
		panelListe.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_2 = (FlowLayout) panelListe.getLayout();
		flowLayout_2.setVgap(50);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(50);
		GridBagConstraints gbc_panelListe = new GridBagConstraints();
		gbc_panelListe.fill = GridBagConstraints.BOTH;
		gbc_panelListe.gridx = 0;
		gbc_panelListe.gridy = 1;
		panelFinPodium.add(panelListe, gbc_panelListe);
		
		listePodium = new JList<String>(this.modelePodium);
		listePodium.setVisibleRowCount(14);
		listePodium.setFixedCellHeight(40);
		listePodium.setFixedCellWidth(600);
		listePodium.setEnabled(false);
		listePodium.setForeground(Color.red);
		JScrollPane scrollPane = new JScrollPane(listePodium);
		panelListe.add(scrollPane);
		
		// DECONNEXION
		btnDeconnexion.addActionListener(controleur);
		// BOUTONS MENU
		btnEquipes.addActionListener(controleur);
		btnJoueurs.addActionListener(controleur);

		// COMBOBOX DE JEUX
		this.triJeux.addActionListener(controleur);
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
		if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Ecuries / Responsables / Arbitres") {
			return Etat.ECURIE;
		} else if (b.getText() == "Calendrier") {
			return Etat.CALENDRIER;
		} else if (b.getText() == "Equipes") {
			return Etat.EQUIPES;
		} else if (b.getText() == "Joueurs") {
			return Etat.JOUEURS;
		} else if (b.getText() == "Tournois") {
			return Etat.TOURNOIS;
		}
		
		return null;
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
