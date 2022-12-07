package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import controleur.ControleurEcurie.Etat;
import controleur.ControleurEcurie;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueEcurie {

	public JFrame fenetreEcurie;
	public JTextField recherche;
	public JList<String> listeEcuries;
	private DefaultListModel<String> modeleEcuries;
	private JTextField nomEcurie;
	private JPasswordField motDePasseEcurie;
	
	public JFrame getFrame() {
		return this.fenetreEcurie;
	}

	public VueEcurie() {
		fenetreEcurie = new JFrame();
		fenetreEcurie.setResizable(false);
		fenetreEcurie.setBounds(100, 100, 1400, 900);
		fenetreEcurie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel panelHeader = new JPanel();
		fenetreEcurie.getContentPane().add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.X_AXIS));
		
		JPanel panelMenu = new JPanel();
		panelHeader.add(panelMenu);
		panelMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCalendrier = new JButton("Calendrier");
		btnCalendrier.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnCalendrier);
		
		JButton btnEcuries = new JButton("Ecuries");
		btnEcuries.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnEcuries);
		
		JButton btnEquipes = new JButton("Equipes");
		btnEquipes.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnEquipes);
		
		JButton btnJoueurs = new JButton("Joueurs");
		btnJoueurs.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnJoueurs);
		
		JButton btnClassement = new JButton("Classement");
		btnClassement.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelMenu.add(btnClassement);
		
		JPanel panelDeconnexion = new JPanel();
		FlowLayout fl_panelDeconnexion = (FlowLayout) panelDeconnexion.getLayout();
		fl_panelDeconnexion.setAlignment(FlowLayout.RIGHT);
		panelHeader.add(panelDeconnexion);
		
		JButton btnDeconnexion = new JButton("Se d\u00E9connecter");
		btnDeconnexion.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelDeconnexion.add(btnDeconnexion);
		
		JPanel panelContenu = new JPanel();
		fenetreEcurie.getContentPane().add(panelContenu, BorderLayout.CENTER);
		GridBagLayout gbl_panelContenu = new GridBagLayout();
		gbl_panelContenu.columnWidths = new int[]{1384, 0};
		gbl_panelContenu.rowHeights = new int[] {100, 574, 50, 100, 0};
		gbl_panelContenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelContenu.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		panelContenu.setLayout(gbl_panelContenu);
		
		JPanel panelHeaderContenu = new JPanel();
		GridBagConstraints gbc_panelHeaderContenu = new GridBagConstraints();
		gbc_panelHeaderContenu.anchor = GridBagConstraints.SOUTH;
		gbc_panelHeaderContenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelHeaderContenu.insets = new Insets(0, 0, 5, 0);
		gbc_panelHeaderContenu.gridx = 0;
		gbc_panelHeaderContenu.gridy = 0;
		panelContenu.add(panelHeaderContenu, gbc_panelHeaderContenu);
		panelHeaderContenu.setLayout(new BoxLayout(panelHeaderContenu, BoxLayout.X_AXIS));
		
		JPanel panelTitre = new JPanel();
		panelHeaderContenu.add(panelTitre);
		panelTitre.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
		
		JLabel titre = new JLabel("Ecuries inscrites");
		titre.setHorizontalAlignment(SwingConstants.LEFT);
		titre.setFont(new Font("Roboto", Font.PLAIN, 18));
		panelTitre.add(titre);
		
		JPanel panelRecherche = new JPanel();
		FlowLayout fl_panelRecherche = (FlowLayout) panelRecherche.getLayout();
		fl_panelRecherche.setAlignment(FlowLayout.RIGHT);
		fl_panelRecherche.setVgap(0);
		panelHeaderContenu.add(panelRecherche);
		
		recherche = new JTextField();
		recherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelRecherche.add(recherche);
		recherche.setColumns(20);
		
		JButton btnRecherche = new JButton("Rechercher");
		btnRecherche.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelRecherche.add(btnRecherche);
		
		JPanel panelListeEcuries = new JPanel();
		FlowLayout fl_panelListeEcuries = (FlowLayout) panelListeEcuries.getLayout();
		fl_panelListeEcuries.setVgap(10);
		fl_panelListeEcuries.setHgap(50);
		fl_panelListeEcuries.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panelListeEcuries = new GridBagConstraints();
		gbc_panelListeEcuries.insets = new Insets(0, 0, 5, 0);
		gbc_panelListeEcuries.fill = GridBagConstraints.BOTH;
		gbc_panelListeEcuries.gridx = 0;
		gbc_panelListeEcuries.gridy = 1;
		panelContenu.add(panelListeEcuries, gbc_panelListeEcuries);
		
		this.modeleEcuries = new DefaultListModel<String>();
		listeEcuries = new JList<String>(modeleEcuries);
		listeEcuries.setFont(new Font("Roboto", Font.PLAIN, 15));
		listeEcuries.setFixedCellHeight(25);
		listeEcuries.setFixedCellWidth(1200);
		panelListeEcuries.add(listeEcuries);
		
		JPanel panelModification = new JPanel();
		GridBagConstraints gbc_panelModification = new GridBagConstraints();
		gbc_panelModification.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelModification.gridx = 0;
		gbc_panelModification.gridy = 2;
		panelContenu.add(panelModification, gbc_panelModification);
		panelModification.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panelModification.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNomEcurie = new JLabel("Nom \u00E9curie");
		lblNomEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNomEcurie);
		lblNomEcurie.setFont(new Font("Roboto", Font.PLAIN, 14));
		
		nomEcurie = new JTextField();
		panel.add(nomEcurie);
		nomEcurie.setColumns(15);
		
		JLabel lblMdpEcurie = new JLabel("Mot de passe");
		lblMdpEcurie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMdpEcurie.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel.add(lblMdpEcurie);
		
		motDePasseEcurie = new JPasswordField();
		motDePasseEcurie.setColumns(15);
		panel.add(motDePasseEcurie);
		
		JPanel panel_1 = new JPanel();
		panelModification.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnValider = new JButton("Valider");
		panel_1.add(btnValider);
		btnValider.setFont(new Font("Roboto", Font.PLAIN, 11));
		btnValider.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		
		JButton btnAnnuler = new JButton("Annuler");
		panel_1.add(btnAnnuler);
		btnAnnuler.setFont(new Font("Roboto", Font.PLAIN, 11));

		JPanel panelBoutons = new JPanel();
		FlowLayout fl_panelBoutons = (FlowLayout) panelBoutons.getLayout();
		fl_panelBoutons.setVgap(0);
		fl_panelBoutons.setHgap(200);
		GridBagConstraints gbc_panelBoutons = new GridBagConstraints();
		gbc_panelBoutons.insets = new Insets(0, 0, 5, 0);
		gbc_panelBoutons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBoutons.gridx = 0;
		gbc_panelBoutons.gridy = 3;
		panelContenu.add(panelBoutons, gbc_panelBoutons);
		
		JButton btnCreerEcurie = new JButton("Cr\u00E9er une nouvelle \u00E9curie");
		btnCreerEcurie.setFont(new Font("Roboto", Font.PLAIN, 11));
		panelBoutons.add(btnCreerEcurie);
		
		JButton btnSupprimerEcurie = new JButton("Supprimer l'\u00E9curie s\u00E9lectionn\u00E9e");
		btnSupprimerEcurie.setFont(new Font("Roboto", Font.PLAIN, 11));
		panelBoutons.add(btnSupprimerEcurie);

		ControleurEcurie controleur = new ControleurEcurie(this);
		btnCalendrier.addActionListener(controleur);
		btnEquipes.addActionListener(controleur);
		btnJoueurs.addActionListener(controleur);
		btnClassement.addActionListener(controleur);
		btnDeconnexion.addActionListener(controleur);
		btnRecherche.addActionListener(controleur);
		btnCreerEcurie.addActionListener(controleur);
		btnSupprimerEcurie.addActionListener(controleur);
		btnValider.addActionListener(controleur);
		btnAnnuler.addActionListener(controleur);
		this.listeEcuries.addListSelectionListener(controleur);
	}
	
	public static void fermerFenetre(JFrame f) {
		f.setVisible(false);
	}
	
	// ECURIE
	public void ajouterEcurie(String t) {
		modeleEcuries.addElement(t);
	}
	
	public void supprimerEcurie() {
		modeleEcuries.removeElement(this.listeEcuries.getSelectedValue());
	}
	
	public void modifierEcurie() {
		modeleEcuries.set(this.listeEcuries.getSelectedIndex(),this.getNomEcurie());
		this.listeEcuries.clearSelection();
	}
	
	// GETTERS //
	public String getNomEcurie() {return this.nomEcurie.getText();}
	
	public String getNomSelectionne() {return this.listeEcuries.getSelectedValue();}
	
	public String getMotDePasseEcurie() {return String.valueOf(this.motDePasseEcurie.getPassword());}
	
	// SETTERS //
	public void setDefaultListModel() {
		this.listeEcuries.setModel(modeleEcuries);
	}

	public void setNomSelectionne() {
		this.nomEcurie.setText(this.listeEcuries.getSelectedValue());
	}
	
	public void setNom(String nom) {
		this.nomEcurie.setText(nom);
	}
	
	public void viderMotDePasse() {
		this.motDePasseEcurie.setText("");
	}
	
	// LISTE //
	public boolean estSelectionne() {
		return !(this.listeEcuries.isSelectionEmpty());
	}
	// ETATS
	public Etat getEtat(JButton b) {
		if (b.getText() == "Créer une nouvelle écurie") {
			this.listeEcuries.clearSelection();
			return Etat.CREER;
		} else if (b.getText() == "Modifier l'écurie sélectionnée") {
			return Etat.MODIFIER;
		} else if (b.getText() == "Supprimer l'écurie sélectionnée") {
			return Etat.SUPPRIMER;
		} else if (b.getText() == "Se déconnecter") {
			return Etat.DECONNECTER;
		} else if (b.getText() == "Calendrier") {
			return Etat.CALENDRIER;
		} else if (b.getText() == "Joueurs") {
			return Etat.JOUEURS;
		} else if (b.getText() == "Classement") {
			return Etat.CLASSEMENT;
		} else if (b.getText() == "Rechercher") {
			return Etat.RECHERCHER;
		} else if (b.getText() == "Valider") {
			return Etat.VALIDER;
		} else if (b.getText() == "Annuler") {
			this.listeEcuries.clearSelection();
			return Etat.ANNULER;
		} 
		return null;
	}
	// FILTRE
	public void filtrerRecherche() {
		DefaultListModel<String> modeleFiltre = new DefaultListModel<String>();
	    for (int i = 0; i < this.modeleEcuries.size(); i++) {
	    	if (this.modeleEcuries.get(i).contains(this.recherche.getText())){
	    		modeleFiltre.addElement(this.modeleEcuries.get(i));
	    	}
	    }
	    this.listeEcuries.setModel(modeleFiltre);
	}

	// MESSAGES
	public void estVide() {
        JOptionPane.showMessageDialog(null, "Veuillez compléter tous les champs !", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
	
	public int confirmer(String operation) {
		return JOptionPane.showConfirmDialog(null, "Confirmez-vous la "+operation+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
	}
	
	public void tournoiExiste() {
        JOptionPane.showMessageDialog(null, "L'écurie existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
