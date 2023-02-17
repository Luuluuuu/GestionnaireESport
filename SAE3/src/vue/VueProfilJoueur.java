package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.ControleurProfilJoueur;
import controleur.ControleurProfilJoueur.Etat;
import modele.Joueur;

import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.io.IOException;

public class VueProfilJoueur {

	public JFrame fenetreProfilJoueur;
	private JLabel titreEquipe;
	private JLabel pseudo;
	private JLabel photo;

	private DefaultListModel<String> modeleJoueursEquipe = new DefaultListModel<String>();
	
	public JFrame getFrame() {
		return this.fenetreProfilJoueur;
	}

	public VueProfilJoueur() {
		fenetreProfilJoueur = new JFrame();
		fenetreProfilJoueur.getContentPane().setBackground(Couleur.BLEU1);
		fenetreProfilJoueur.setResizable(false);
		fenetreProfilJoueur.setBounds(100, 100, 1400, 900);
		fenetreProfilJoueur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		fenetreProfilJoueur.getContentPane().add(panelDeconnexion, BorderLayout.NORTH);
		panelDeconnexion.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnDeconnexion = new JButton("Se déconnecter");
		btnDeconnexion.setForeground(Color.WHITE);
		btnDeconnexion.setFont(new Font("Roboto", Font.BOLD, 13));
		btnDeconnexion.setBackground(Couleur.ROUGE);
		panelDeconnexion.add(btnDeconnexion);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreProfilJoueur.getContentPane().add(panelContenu, BorderLayout.CENTER);
		GridBagLayout gbl_panelContenu = new GridBagLayout();
		gbl_panelContenu.columnWidths = new int[]{1384, 0};
		gbl_panelContenu.rowHeights = new int[] {250, 576, 0};
		gbl_panelContenu.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelContenu.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
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
		
		JButton btnProfil = new JButton("Mon profil");
		btnProfil.setForeground(Color.WHITE);
		btnProfil.setFont(new Font("Roboto", Font.BOLD, 15));
		btnProfil.setBackground(Couleur.BLEU2);
		panelMenu.add(btnProfil);
		
		JButton btnEquipes = new JButton("Equipes");
		btnEquipes.setForeground(Color.WHITE);
		btnEquipes.setFont(new Font("Roboto", Font.BOLD, 15));
		btnEquipes.setBackground(Couleur.BLEU2);
		panelMenu.add(btnEquipes);
		
		JButton btnTournois = new JButton("Tournois");
		btnTournois.setForeground(Color.WHITE);
		btnTournois.setFont(new Font("Roboto", Font.BOLD, 15));
		btnTournois.setBackground(Couleur.BLEU2);
		panelMenu.add(btnTournois);
		
		JButton btnClassement = new JButton("Classement");
		btnClassement.setForeground(Color.WHITE);
		btnClassement.setFont(new Font("Roboto", Font.BOLD, 15));
		btnClassement.setBackground(Couleur.BLEU2);
		panelMenu.add(btnClassement);
		
		JPanel panelProfil = new JPanel();
		panelProfil.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelProfil = new GridBagConstraints();
		gbc_panelProfil.fill = GridBagConstraints.BOTH;
		gbc_panelProfil.gridx = 0;
		gbc_panelProfil.gridy = 1;
		panelContenu.add(panelProfil, gbc_panelProfil);
		GridBagLayout gbl_panelProfil = new GridBagLayout();
		gbl_panelProfil.columnWidths = new int[] {534, 850, 0};
		gbl_panelProfil.rowHeights = new int[]{576, 0};
		gbl_panelProfil.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelProfil.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelProfil.setLayout(gbl_panelProfil);
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelEquipe = new GridBagConstraints();
		gbc_panelEquipe.fill = GridBagConstraints.BOTH;
		gbc_panelEquipe.insets = new Insets(0, 0, 0, 5);
		gbc_panelEquipe.gridx = 0;
		gbc_panelEquipe.gridy = 0;
		panelProfil.add(panelEquipe, gbc_panelEquipe);
		GridBagLayout gbl_panelEquipe = new GridBagLayout();
		gbl_panelEquipe.columnWidths = new int[] {534, 0};
		gbl_panelEquipe.rowHeights = new int[] {75, 501, 0};
		gbl_panelEquipe.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelEquipe.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelEquipe.setLayout(gbl_panelEquipe);
		
		JPanel panelTitreEquipe = new JPanel();
		panelTitreEquipe.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitreEquipe = (FlowLayout) panelTitreEquipe.getLayout();
		fl_panelTitreEquipe.setVgap(0);
		fl_panelTitreEquipe.setAlignment(FlowLayout.LEFT);
		fl_panelTitreEquipe.setHgap(50);
		GridBagConstraints gbc_panelTitreEquipe = new GridBagConstraints();
		gbc_panelTitreEquipe.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreEquipe.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreEquipe.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreEquipe.gridx = 0;
		gbc_panelTitreEquipe.gridy = 0;
		panelEquipe.add(panelTitreEquipe, gbc_panelTitreEquipe);
		
		titreEquipe = new JLabel("Mon équipe (insererNom)");
		titreEquipe.setForeground(Color.WHITE);
		titreEquipe.setFont(new Font("Roboto", Font.BOLD, 20));
		panelTitreEquipe.add(titreEquipe);
		
		JPanel panelJoueursEquipe = new JPanel();
		panelJoueursEquipe.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelJoueursEquipe = (FlowLayout) panelJoueursEquipe.getLayout();
		fl_panelJoueursEquipe.setVgap(20);
		fl_panelJoueursEquipe.setHgap(50);
		fl_panelJoueursEquipe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelJoueursEquipe = new GridBagConstraints();
		gbc_panelJoueursEquipe.fill = GridBagConstraints.BOTH;
		gbc_panelJoueursEquipe.gridx = 0;
		gbc_panelJoueursEquipe.gridy = 1;
		panelEquipe.add(panelJoueursEquipe, gbc_panelJoueursEquipe);
		
		JList<String> listeJoueursEquipe = new JList<String>(modeleJoueursEquipe);
		listeJoueursEquipe.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelJoueursEquipe.add(listeJoueursEquipe);
		
		JPanel panelStats = new JPanel();
		panelStats.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelStats = new GridBagConstraints();
		gbc_panelStats.fill = GridBagConstraints.BOTH;
		gbc_panelStats.gridx = 1;
		gbc_panelStats.gridy = 0;
		panelProfil.add(panelStats, gbc_panelStats);
		GridBagLayout gbl_panelStats = new GridBagLayout();
		gbl_panelStats.columnWidths = new int[] {850, 0};
		gbl_panelStats.rowHeights = new int[] {75, 501, 0};
		gbl_panelStats.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelStats.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelStats.setLayout(gbl_panelStats);
		
		JPanel panelTitreStats = new JPanel();
		panelTitreStats.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitreStats = (FlowLayout) panelTitreStats.getLayout();
		fl_panelTitreStats.setVgap(0);
		fl_panelTitreStats.setHgap(50);
		fl_panelTitreStats.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelTitreStats = new GridBagConstraints();
		gbc_panelTitreStats.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreStats.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreStats.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreStats.gridx = 0;
		gbc_panelTitreStats.gridy = 0;
		panelStats.add(panelTitreStats, gbc_panelTitreStats);
		
		JLabel titreStats = new JLabel("Statistiques équipe");
		titreStats.setForeground(Color.WHITE);
		titreStats.setFont(new Font("Roboto", Font.BOLD, 20));
		panelTitreStats.add(titreStats);
		
		JPanel panelListeStats = new JPanel();
		panelListeStats.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListeStats = (FlowLayout) panelListeStats.getLayout();
		fl_panelListeStats.setVgap(20);
		fl_panelListeStats.setAlignment(FlowLayout.LEFT);
		fl_panelListeStats.setHgap(50);
		GridBagConstraints gbc_panelListeStats = new GridBagConstraints();
		gbc_panelListeStats.fill = GridBagConstraints.BOTH;
		gbc_panelListeStats.gridx = 0;
		gbc_panelListeStats.gridy = 1;
		panelStats.add(panelListeStats, gbc_panelListeStats);
		
		JList listeStats = new JList();
		listeStats.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelListeStats.add(listeStats);
		
		// CONTROLEUR //
		ControleurProfilJoueur controleur = new ControleurProfilJoueur(this);
		
		btnEquipes.addActionListener(controleur);
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
		this.titreEquipe.setText("Mon équipe (" + nomEquipe + ")"); 
	}
	
	public static void fermerFenetre(JFrame f) {
		f.setVisible(false);
	}

	public Etat getEtat(JButton b) {
		if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Classement") {
			return Etat.CLASSEMENT;
		} else if (b.getText() == "Equipes") {
			return Etat.EQUIPES;
		} else if (b.getText() == "Tournois") {
			return Etat.TOURNOIS;
		}
		return null;
	}

	public void ajouterJoueur(String nomJoueur) {
		this.modeleJoueursEquipe.addElement(nomJoueur);
	}

}
