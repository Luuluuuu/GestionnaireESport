package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JList;

public class VueClassementJoueur {

	private JFrame fenetreClassementJoueur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VueClassementJoueur window = new VueClassementJoueur();
					window.fenetreClassementJoueur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VueClassementJoueur() {
		fenetreClassementJoueur = new JFrame();
		fenetreClassementJoueur.getContentPane().setBackground(Couleur.BLEU1);
		fenetreClassementJoueur.setResizable(false);
		fenetreClassementJoueur.setBounds(100, 100, 1400, 900);
		fenetreClassementJoueur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelDeconnexion = new JPanel();
		panelDeconnexion.setBackground(Color.WHITE);
		fenetreClassementJoueur.getContentPane().add(panelDeconnexion, BorderLayout.NORTH);
		panelDeconnexion.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnDeconnexion = new JButton("Se d\u00E9connecter");
		btnDeconnexion.setForeground(Color.WHITE);
		btnDeconnexion.setFont(new Font("Roboto", Font.BOLD, 13));
		btnDeconnexion.setBackground(Couleur.ROUGE);
		panelDeconnexion.add(btnDeconnexion);
		
		JPanel panelContenu = new JPanel();
		panelContenu.setBackground(Couleur.BLEU1);
		fenetreClassementJoueur.getContentPane().add(panelContenu, BorderLayout.CENTER);
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
		
		JLabel photo = new JLabel("insererPhoto");
		photo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_photo = new GridBagConstraints();
		gbc_photo.fill = GridBagConstraints.BOTH;
		gbc_photo.insets = new Insets(0, 0, 5, 0);
		gbc_photo.gridx = 0;
		gbc_photo.gridy = 0;
		panelJoueur.add(photo, gbc_photo);
		
		JLabel pseudo = new JLabel("PSEUDO");
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
		
		JPanel panelClassement = new JPanel();
		panelClassement.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelClassement = new GridBagConstraints();
		gbc_panelClassement.fill = GridBagConstraints.BOTH;
		gbc_panelClassement.gridx = 0;
		gbc_panelClassement.gridy = 1;
		panelContenu.add(panelClassement, gbc_panelClassement);
		panelClassement.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelImgClassement = new JPanel();
		panelImgClassement.setBackground(Couleur.BLEU1);
		panelClassement.add(panelImgClassement);
		GridBagLayout gbl_panelImgClassement = new GridBagLayout();
		gbl_panelImgClassement.columnWidths = new int[]{692, 0};
		gbl_panelImgClassement.rowHeights = new int[] {75, 501, 0};
		gbl_panelImgClassement.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelImgClassement.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelImgClassement.setLayout(gbl_panelImgClassement);
		
		JPanel panelTitrePodium = new JPanel();
		panelTitrePodium.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelTitrePodium = (FlowLayout) panelTitrePodium.getLayout();
		fl_panelTitrePodium.setVgap(0);
		GridBagConstraints gbc_panelTitrePodium = new GridBagConstraints();
		gbc_panelTitrePodium.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitrePodium.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitrePodium.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitrePodium.gridx = 0;
		gbc_panelTitrePodium.gridy = 0;
		panelImgClassement.add(panelTitrePodium, gbc_panelTitrePodium);
		
		JLabel titrePodium = new JLabel("PODIUM");
		titrePodium.setForeground(Color.WHITE);
		titrePodium.setFont(new Font("Roboto", Font.BOLD, 20));
		panelTitrePodium.add(titrePodium);
		
		JPanel panelPodium = new JPanel();
		panelPodium.setBackground(Couleur.BLEU1);
		panelPodium.setLayout(null);
		GridBagConstraints gbc_panelPodium = new GridBagConstraints();
		gbc_panelPodium.fill = GridBagConstraints.BOTH;
		gbc_panelPodium.gridx = 0;
		gbc_panelPodium.gridy = 1;
		panelImgClassement.add(panelPodium, gbc_panelPodium);
		
		JLabel imgPodium = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("podiumPetit.png")).getImage();
		imgPodium.setBounds(140, 48, 417, 412);
		imgPodium.setIcon(new ImageIcon(img));
		panelPodium.add(imgPodium);
		
		JLabel nomPremier = new JLabel("1er");
		nomPremier.setHorizontalAlignment(SwingConstants.CENTER);
		nomPremier.setBounds(300, 205, 120, 28);
		nomPremier.setForeground(Color.WHITE);
		nomPremier.setFont(new Font("Roboto", Font.BOLD, 15));
		panelPodium.add(nomPremier);
		
		JLabel nomDeuxieme = new JLabel("2e");
		nomDeuxieme.setHorizontalAlignment(SwingConstants.CENTER);
		nomDeuxieme.setForeground(Color.WHITE);
		nomDeuxieme.setBounds(146, 266, 120, 28);
		nomDeuxieme.setFont(new Font("Roboto", Font.BOLD, 15));
		panelPodium.add(nomDeuxieme);
		
		JLabel nomTroisieme = new JLabel("3e");
		nomTroisieme.setHorizontalAlignment(SwingConstants.CENTER);
		nomTroisieme.setForeground(Color.WHITE);
		nomTroisieme.setBounds(431, 324, 120, 18);
		nomTroisieme.setFont(new Font("Roboto", Font.BOLD, 15));
		panelPodium.add(nomTroisieme);
		
		JPanel panelListe = new JPanel();
		panelListe.setBackground(Couleur.BLEU1);
		FlowLayout flowLayout_1 = (FlowLayout) panelListe.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(50);
		flowLayout_1.setHgap(50);
		panelClassement.add(panelListe);
		
		JList listePodium = new JList();
		panelListe.add(listePodium);
	}

}
