package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controleur.ControleurConnexion;

public class VueConnexion extends JFrame {
	
	public JFrame fenetreConnexion;
	private JTextField entreeNomUtilisateur;
	private JPasswordField entreeMotDePasse;
	
	public JFrame getFrame() {
		return this.fenetreConnexion;
	}
	
	public VueConnexion() {
		
		fenetreConnexion = new JFrame();
		fenetreConnexion.getContentPane().setBackground(new Color(86,140,206));
		fenetreConnexion.setResizable(false);
		fenetreConnexion.setBounds(100, 100, 600, 700);
		fenetreConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ControleurConnexion controleur = new ControleurConnexion(this);
		
		JPanel panelLogo = new JPanel();
		fenetreConnexion.getContentPane().add(panelLogo, BorderLayout.NORTH);
		
		JLabel logo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("logo.png")).getImage();
		panelLogo.setBackground(new Color(86,140,206));
		panelLogo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		logo.setIcon(new ImageIcon(img));
		panelLogo.add(logo);
		
		JPanel panelConnexion = new JPanel();
		panelConnexion.setBackground(new Color(2,137,150));
		fenetreConnexion.getContentPane().add(panelConnexion, BorderLayout.CENTER);
		GridBagLayout gbl_panelConnexion = new GridBagLayout();
		gbl_panelConnexion.columnWidths = new int[]{603, 0};
		gbl_panelConnexion.rowHeights = new int[] {80, 50, 40, 50, 78, 0};
		gbl_panelConnexion.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelConnexion.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelConnexion.setLayout(gbl_panelConnexion);
		
		JPanel panelTxtUsername = new JPanel();
		panelTxtUsername.setBackground(new Color(2,137,150));
		GridBagConstraints gbc_panelTxtUsername = new GridBagConstraints();
		gbc_panelTxtUsername.anchor = GridBagConstraints.SOUTH;
		gbc_panelTxtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTxtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_panelTxtUsername.gridx = 0;
		gbc_panelTxtUsername.gridy = 0;
		panelConnexion.add(panelTxtUsername, gbc_panelTxtUsername);
		panelTxtUsername.setLayout(new FlowLayout(FlowLayout.LEFT, 135, 0));
		
		JLabel nomUtilisateur = new JLabel("Nom d'utilisateur");
		nomUtilisateur.setForeground(Color.WHITE);
		nomUtilisateur.setVerticalAlignment(SwingConstants.TOP);
		nomUtilisateur.setFont(new Font("Roboto", Font.PLAIN, 20));
		nomUtilisateur.setHorizontalAlignment(SwingConstants.LEFT);
		panelTxtUsername.add(nomUtilisateur);
		
		JPanel panelEntreeUsername = new JPanel();
		panelEntreeUsername.setBackground(new Color(2,137,150));
		FlowLayout fl_panelEntreeUsername = (FlowLayout) panelEntreeUsername.getLayout();
		fl_panelEntreeUsername.setVgap(0);
		fl_panelEntreeUsername.setHgap(0);
		GridBagConstraints gbc_panelEntreeUsername = new GridBagConstraints();
		gbc_panelEntreeUsername.anchor = GridBagConstraints.NORTH;
		gbc_panelEntreeUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelEntreeUsername.insets = new Insets(0, 0, 5, 0);
		gbc_panelEntreeUsername.gridx = 0;
		gbc_panelEntreeUsername.gridy = 1;
		panelConnexion.add(panelEntreeUsername, gbc_panelEntreeUsername);
		
		entreeNomUtilisateur = new JTextField();
		entreeNomUtilisateur.setFont(new Font("Roboto", Font.PLAIN, 15));
		panelEntreeUsername.add(entreeNomUtilisateur);
		entreeNomUtilisateur.setColumns(25);
		
		JPanel panelTxtMdp = new JPanel();
		panelTxtMdp.setBackground(new Color(2,137,150));
		FlowLayout fl_panelTxtMdp = (FlowLayout) panelTxtMdp.getLayout();
		fl_panelTxtMdp.setAlignment(FlowLayout.LEFT);
		fl_panelTxtMdp.setVgap(0);
		fl_panelTxtMdp.setHgap(135);
		GridBagConstraints gbc_panelTxtMdp = new GridBagConstraints();
		gbc_panelTxtMdp.anchor = GridBagConstraints.SOUTH;
		gbc_panelTxtMdp.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTxtMdp.insets = new Insets(0, 0, 5, 0);
		gbc_panelTxtMdp.gridx = 0;
		gbc_panelTxtMdp.gridy = 2;
		panelConnexion.add(panelTxtMdp, gbc_panelTxtMdp);
		
		JLabel motDePasse = new JLabel("Mot de passe");
		motDePasse.setForeground(Color.WHITE);
		motDePasse.setFont(new Font("Roboto", Font.PLAIN, 20));
		panelTxtMdp.add(motDePasse);
		
		JPanel panelEntreeMdp = new JPanel();
		panelEntreeMdp.setBackground(new Color(2,137,150));
		FlowLayout fl_panelEntreeMdp = (FlowLayout) panelEntreeMdp.getLayout();
		fl_panelEntreeMdp.setVgap(0);
		fl_panelEntreeMdp.setHgap(0);
		GridBagConstraints gbc_panelEntreeMdp = new GridBagConstraints();
		gbc_panelEntreeMdp.anchor = GridBagConstraints.NORTH;
		gbc_panelEntreeMdp.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelEntreeMdp.insets = new Insets(0, 0, 5, 0);
		gbc_panelEntreeMdp.gridx = 0;
		gbc_panelEntreeMdp.gridy = 3;
		panelConnexion.add(panelEntreeMdp, gbc_panelEntreeMdp);
		
		entreeMotDePasse = new JPasswordField();
		entreeMotDePasse.setFont(new Font("Roboto", Font.PLAIN, 15));
		entreeMotDePasse.setColumns(25);
		panelEntreeMdp.add(entreeMotDePasse);
		
		JPanel panelBtnConnexion = new JPanel();
		panelBtnConnexion.setBackground(new Color(2,137,150));
		GridBagConstraints gbc_panelBtnConnexion = new GridBagConstraints();
		gbc_panelBtnConnexion.fill = GridBagConstraints.BOTH;
		gbc_panelBtnConnexion.gridx = 0;
		gbc_panelBtnConnexion.gridy = 4;
		panelConnexion.add(panelBtnConnexion, gbc_panelBtnConnexion);
		panelBtnConnexion.setLayout(new FlowLayout(FlowLayout.RIGHT, 130, 0));
		
		JButton btnConnexion = new JButton("Se connecter");
		btnConnexion.setForeground(new Color(2,137,150));
		btnConnexion.setFont(new Font("Roboto", Font.BOLD, 16));
		btnConnexion.setPreferredSize(new Dimension(150, 40));
		btnConnexion.setBackground(Color.WHITE);
		panelBtnConnexion.add(btnConnexion);
		btnConnexion.addActionListener(controleur);
		
	}
	
	public static void fermerFenetre(JFrame f) {
		f.setVisible(false);
	}

	public String getLogin() {
		return entreeNomUtilisateur.getText();
	}
	
	public String getMotDePasse() {
		return String.valueOf(entreeMotDePasse.getPassword());
	}
	
	public boolean estRemplie() {
		return !(this.entreeNomUtilisateur.getText().isEmpty());
	}
	
	public void setCouleurLogin() {
		this.entreeNomUtilisateur.setSelectedTextColor(new Color(255,0,0));;
	}
	
	public void connexionEchoue()
    {
        JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}