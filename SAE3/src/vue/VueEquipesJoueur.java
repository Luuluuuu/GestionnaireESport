package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controleur.ControleurEquipesJoueur;
import modele.EtatFactory;
import modele.Etat;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JList;

public class VueEquipesJoueur implements Vue{

	public JFrame fenetreEquipesJoueur;
	
	private JTextField texteRecherche;
	
	private JLabel pseudo;
	private JLabel photo;
	
	private JButton btnClassement;
	private JButton btnProfil;
	private JButton btnTournois;
	
	public JFrame getFrame() {
		return this.fenetreEquipesJoueur;
	}

	public VueEquipesJoueur() {
		fenetreEquipesJoueur = new JFrame();
		fenetreEquipesJoueur.getContentPane().setBackground(Couleur.BLEU1);
		fenetreEquipesJoueur.setResizable(false);
		fenetreEquipesJoueur.setBounds(100, 100, 1500, 880);
		fenetreEquipesJoueur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		fenetreEquipesJoueur.getContentPane().add(panelDeconnexion, BorderLayout.NORTH);
		panelDeconnexion.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnDeconnexion = creerBouton(panelDeconnexion, "Se déconnecter", Couleur.ROUGE, 13);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreEquipesJoueur.getContentPane().add(panelContenu, BorderLayout.CENTER);
		GridBagLayout gbl_panelContenu = new GridBagLayout();
		gbl_panelContenu.columnWidths = new int[]{1384, 0};
		gbl_panelContenu.rowHeights = new int[] {250, 576, 0};
		gbl_panelContenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelContenu.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelContenu.setLayout(gbl_panelContenu);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelHeader = new GridBagConstraints();
		gbc_panelHeader.fill = GridBagConstraints.BOTH;
		gbc_panelHeader.insets = new Insets(0, 0, 5, 0);
		gbc_panelHeader.gridx = 0;
		gbc_panelHeader.gridy = 0;
		panelContenu.add(panelHeader, gbc_panelHeader);
		GridBagLayout gbl_panelHeader = new GridBagLayout();
		gbl_panelHeader.columnWidths = new int[]{1384, 0};
		gbl_panelHeader.rowHeights = new int[] {204, 40, 0};
		gbl_panelHeader.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelHeader.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelHeader.setLayout(gbl_panelHeader);
		
		JPanel panelJoueur = new JPanel();
		panelJoueur.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelJoueur = new GridBagConstraints();
		gbc_panelJoueur.fill = GridBagConstraints.BOTH;
		gbc_panelJoueur.insets = new Insets(0, 0, 5, 0);
		gbc_panelJoueur.gridx = 0;
		gbc_panelJoueur.gridy = 0;
		panelHeader.add(panelJoueur, gbc_panelJoueur);
		GridBagLayout gbl_panelJoueur = new GridBagLayout();
		gbl_panelJoueur.columnWidths = new int[]{1384, 0};
		gbl_panelJoueur.rowHeights = new int[] {138, 60, 0};
		gbl_panelJoueur.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelJoueur.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelJoueur.setLayout(gbl_panelJoueur);
		
		photo = new JLabel();
		photo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_photo = new GridBagConstraints();
		gbc_photo.fill = GridBagConstraints.BOTH;
		gbc_photo.insets = new Insets(0, 0, 5, 0);
		gbc_photo.gridx = 0;
		gbc_photo.gridy = 0;
		panelJoueur.add(photo, gbc_photo);
		
		pseudo = new JLabel("PSEUDO");
		pseudo.setForeground(Color.WHITE);
		pseudo.setFont(new Font("Roboto", Font.BOLD, 24));
		pseudo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_pseudo = new GridBagConstraints();
		gbc_pseudo.fill = GridBagConstraints.BOTH;
		gbc_pseudo.gridx = 0;
		gbc_pseudo.gridy = 1;
		panelJoueur.add(pseudo, gbc_pseudo);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout = (FlowLayout) panelMenu.getLayout();
		flowLayout.setVgap(0);
		GridBagConstraints gbc_panelMenu = new GridBagConstraints();
		gbc_panelMenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelMenu.gridx = 0;
		gbc_panelMenu.gridy = 1;
		panelHeader.add(panelMenu, gbc_panelMenu);
		
		btnProfil =	creerBouton(panelMenu, "Mon profil", Couleur.BLEU2, 15);
		JButton btnEquipes = creerBouton(panelMenu, "Equipes", Couleur.BLEU2, 15);
		Vue.desactiverBouton(btnEquipes);
		btnTournois = creerBouton(panelMenu, "Tournois", Couleur.BLEU2, 15);
		btnClassement = creerBouton(panelMenu, "Classement", Couleur.BLEU2, 15);
		 
		JPanel panelEquipes = new JPanel();
		panelEquipes.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelEquipes = new GridBagConstraints();
		gbc_panelEquipes.fill = GridBagConstraints.BOTH;
		gbc_panelEquipes.gridx = 0;
		gbc_panelEquipes.gridy = 1;
		panelContenu.add(panelEquipes, gbc_panelEquipes);
		GridBagLayout gbl_panelEquipes = new GridBagLayout();
		gbl_panelEquipes.columnWidths = new int[]{1384, 0};
		gbl_panelEquipes.rowHeights = new int[] {75, 501, 0};
		gbl_panelEquipes.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelEquipes.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelEquipes.setLayout(gbl_panelEquipes);
		
		JPanel panelTri = new JPanel();
		panelTri.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelTri = new GridBagConstraints();
		gbc_panelTri.anchor = GridBagConstraints.SOUTH;
		gbc_panelTri.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTri.insets = new Insets(0, 0, 5, 0);
		gbc_panelTri.gridx = 0;
		gbc_panelTri.gridy = 0;
		panelEquipes.add(panelTri, gbc_panelTri);
		panelTri.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelBoutons = new JPanel();
		panelBoutons.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_2 = (FlowLayout) panelBoutons.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelTri.add(panelBoutons);
		
		JLabel tri = new JLabel("Trier par :");
		tri.setForeground(Color.WHITE);
		tri.setFont(new Font("Roboto", Font.BOLD, 13));
		panelBoutons.add(tri);
		
		JButton btnWinrate = new JButton("Winrate");
		btnWinrate.setForeground(Color.WHITE);
		btnWinrate.setBackground(Couleur.BLEU2);
		btnWinrate.setFont(new Font("Roboto", Font.BOLD, 13));
		panelBoutons.add(btnWinrate);
		
		JComboBox<String> triJeux = new JComboBox<String>();
		triJeux.setFont(new Font("Roboto", Font.PLAIN, 13));
		triJeux.setPreferredSize(new Dimension(205, 20));
		panelBoutons.add(triJeux);
		
		JComboBox<String> triEcurie = new JComboBox<String>();
		triEcurie.setFont(new Font("Roboto", Font.PLAIN, 13));
		triEcurie.setPreferredSize(new Dimension(205, 20));
		panelBoutons.add(triEcurie);
		
		JPanel panelRecherche = new JPanel();
		panelRecherche.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panelRecherche.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelTri.add(panelRecherche);
		
		texteRecherche = new JTextField();
		texteRecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelRecherche.add(texteRecherche);
		texteRecherche.setColumns(20);
		
		JButton btnRecherche = new JButton("Rechercher");
		btnRecherche.setForeground(Color.WHITE);
		btnRecherche.setBackground(Couleur.BLEU2);
		btnRecherche.setFont(new Font("Roboto", Font.BOLD, 13));
		panelRecherche.add(btnRecherche);
		
		JPanel panelListe = new JPanel();
		panelListe.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_3 = (FlowLayout) panelListe.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(50);
		flowLayout_3.setHgap(50);
		GridBagConstraints gbc_panelListe = new GridBagConstraints();
		gbc_panelListe.fill = GridBagConstraints.BOTH;
		gbc_panelListe.gridx = 0;
		gbc_panelListe.gridy = 1;
		panelEquipes.add(panelListe, gbc_panelListe);
		
		JList<String> listeEquipes = new JList<String>();
		panelListe.add(listeEquipes);
		
		// CONTROLEUR //
		ControleurEquipesJoueur controleur = new ControleurEquipesJoueur(this);
		
		btnProfil.addActionListener(controleur);
		btnTournois.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
		btnDeconnexion.addActionListener(controleur);
	}
	
	public void setInfosJoueur(String cheminImage, String pseudo, String nomEquipe) {
		try {
			Image img = ImageIO.read(this.getClass().getResource(cheminImage));
			this.photo.setIcon(new ImageIcon(img.getScaledInstance(150, -1,  Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pseudo.setText(pseudo);
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

}
