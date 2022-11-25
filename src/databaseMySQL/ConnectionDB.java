package databaseMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Classe que faz conexão com um Banco de Dados MySQL.
 * 
 * @author Pedro Henrique Pereira de Oliveira
 * 
 */

public class ConnectionDB {
	
	/**
	 * 
	 * Método que abre a conexão com o Banco de Dados MySQL
	 * 
	 * @return retorna uma conexão bem sucedida
	 * 
	 */
	
	public static Connection openConnection() {
		Connection openedConnection;

		try {
			
			final String URL_DATABASE = "jdbc:mysql://localhost:3306/ouvidoriap2facisa?verifyServerCertificate=false&useSSL=true";
			final String USER = "root";
			final String PASSWORD = "root";
			openedConnection = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);

		} catch (SQLException e) {
			
			throw new RuntimeException(e);
			
		}

		return openedConnection;

	}

}