package modele;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.*;

public class Connexion {
	private static Connection connx;
	public static Connexion instance;
	private static Statement st;
	
	private Connexion() {
		String login = "llf4365a";
		String mdpasse = "$iutinfo";
		String connectString = "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:ETUPRE";
	
		// Chargement du driver Oracle et enregistrement
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			//Ouverture d'une connexion
			this.connx = DriverManager.getConnection(connectString, login, mdpasse);
			this.st = this.connx.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Connexion OK");		
	}
	
	public static synchronized Connexion getInstance() {
		if (instance == null) {
			instance = new Connexion();
		}
		return instance;
	}
	
	public ResultSet retournerRequete(String req) {
		try {
			return st.executeQuery(req);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void executerRequete(String req) {
		try {
			st.executeQuery(req);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void fermerConnexion() {
		try {
			connx.close();
			st.close();
			instance = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
