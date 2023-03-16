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
import modele.Etat;

import javax.swing.JComboBox;

public class VueInscriptionTournoi implements Vue{
	
	public JFrame fenetreInscriptionTournoi;
	public JPanel panelModif;
	public JLabel titreModif;
	
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
		
		JPanel panelTournoi = new JPanel();
		panelTournoi.setBackground(Couleur.BLEU1);
		panelContenu.add(panelTournoi);
		GridBagLayout gbl_panelTournoi = new GridBagLayout();
		gbl_panelTournoi.columnWidths = new int[]{692, 0};
		gbl_panelTournoi.rowHeights = new int[] {100, 724, 0};
		gbl_panelTournoi.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTournoi.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
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
		JLabel Tournois = new JLabel("Sélectionnez le tournoi");
		Tournois.setForeground(Color.WHITE);
		Tournois.setFont(new Font("Roboto", Font.BOLD, 36));
		Tournois.setHorizontalAlignment(SwingConstants.LEFT);
		panelTitreT.add(Tournois);
		
		JPanel panelListe = new JPanel();
		panelListe.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelListe = (FlowLayout) panelListe.getLayout();
		fl_panelListe.setHgap(50);
		fl_panelListe.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListe = new GridBagConstraints();
		gbc_panelListe.fill = GridBagConstraints.BOTH;
		gbc_panelListe.gridx = 0;
		gbc_panelListe.gridy = 1;
		panelTournoi.add(panelListe, gbc_panelListe);
		
		listeTournois = new JList<String>(modeleTournois);
		listeTournois.setVisibleRowCount(13);
		listeTournois.setName("Tournoi");
		listeTournois.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeTournois.setFixedCellHeight(50);
		listeTournois.setFixedCellWidth(600);
		JScrollPane scrollPane = new JScrollPane(listeTournois);
		panelListe.add(scrollPane);
		
		panelModif = new JPanel();
		panelModif.setBackground(Couleur.BLEU1);
		panelContenu.add(panelModif);
		GridBagLayout gbl_panelModif = new GridBagLayout();
		gbl_panelModif.columnWidths = new int[]{692, 0};
		gbl_panelModif.rowHeights = new int[] {100, 622, 100, 0};
		gbl_panelModif.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelModif.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelModif.setLayout(gbl_panelModif);
		
		JPanel panelTitreM = new JPanel();
		panelTitreM.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelTitreM = new GridBagConstraints();
		gbc_panelTitreM.anchor = GridBagConstraints.SOUTH;
		gbc_panelTitreM.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTitreM.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitreM.gridx = 0;
		gbc_panelTitreM.gridy = 0;
		panelModif.add(panelTitreM, gbc_panelTitreM);
		panelTitreM.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Couleur.BLEU1);
		panelTitreM.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel titrePoule = new JLabel("Equipe à inscrire");
		titrePoule.setForeground(Color.WHITE);
		titrePoule.setFont(new Font("Roboto", Font.BOLD, 30));
		panel_1.add(titrePoule);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Couleur.BLEU1);
		FlowLayout fl_panel_2 = (FlowLayout) panel_2.getLayout();
		fl_panel_2.setHgap(50);
		fl_panel_2.setAlignment(FlowLayout.RIGHT);
		fl_panel_2.setVgap(0);
		panelTitreM.add(panel_2);
		
		selectionJeu = new JComboBox<String>();
		selectionJeu.setFont(new Font("Roboto", Font.PLAIN, 11));
		selectionJeu.setPreferredSize(new Dimension(205, 20));
		selectionJeu.addItem("- Sélectionnez un jeu -");
		panel_2.add(selectionJeu);
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.setBackground(Couleur.BLEU1);
		GridBagConstraints gbc_panelEquipe = new GridBagConstraints();
		gbc_panelEquipe.fill = GridBagConstraints.BOTH;
		gbc_panelEquipe.insets = new Insets(0, 0, 5, 0);
		gbc_panelEquipe.gridx = 0;
		gbc_panelEquipe.gridy = 1;
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
		
		JPanel panelValider = new JPanel();
		panelValider.setBackground(Couleur.BLEU1);
		FlowLayout fl_panelValider = (FlowLayout) panelValider.getLayout();
		fl_panelValider.setVgap(0);
		fl_panelValider.setHgap(150);
		GridBagConstraints gbc_panelValider = new GridBagConstraints();
		gbc_panelValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelValider.gridx = 0;
		gbc_panelValider.gridy = 2;
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