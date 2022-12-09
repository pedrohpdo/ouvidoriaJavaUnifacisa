package databaseMySQL;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import feedback.Category;

/**
 * 
 * Representação das operaçãoes do sistema CRUD dentro de um banco de dados
 * MySQL
 * 
 * @author Pedro Henrique Pereira de Oliveira
 *
 */

public class FeedbackDB implements interfaceDB {

	Connection connection;
	String sqlCommand;

	/**
	 * 
	 * Adicionar um novo feedback dentro do Banco de Dados MySQL, um objeto Feedback
	 * é usado como parâmetro, sendo extraído através dos seus Getters e Setters, os
	 * parâmetros para serem usados dentro do banco de dados (String feedback,
	 * String type, String author)
	 * 
	 * @param feedback um objeto Feedback que no qual serão extraídas informações
	 *                 para serem adicionadas no Banco de Dados
	 */

	@Override
	public void addData(Category feedback) {

		try {
			connection = ConnectionDB.openConnection();
			sqlCommand = "INSERT INTO feedbacks (type, author, description) VALUES (?, ?, ?)";

			PreparedStatement cursor = connection.prepareStatement(sqlCommand);

			cursor.setString(1, feedback.getType());
			cursor.setString(2, feedback.getAuthor());
			cursor.setString(3, feedback.getDescription());

			cursor.execute();
			cursor.close();
			connection.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * Método que captura e retorna todos as informações salvas no banco de dados
	 * MySql. As informações podem ser capturadas pelo argumento (type varchar) ou
	 * podem ser requisitadas todas as linhas salvas até então.
	 * 
	 * 
	 * @param typeToPrint tipo de manifestação que o usuário queira consultar no
	 *                    sistema de ouvidoria (Reclamação, Elogio, Sugestão ou
	 *                    All).
	 * 
	 * @return retorna uma lista de objetos Feedback, no qual seram tratados das
	 *         respectivas classes
	 * 
	 */
	@Override
	public ArrayList<Category> getData(String typeToPrint) {

		ArrayList<Category> feedbacksReturned = new ArrayList<Category>();

		switch (typeToPrint) {

			case ("Reclamação"):
				sqlCommand = "SELECT * FROM feedbacks WHERE type = 'Reclamação'";
				break;
			case ("Elogio"):
				sqlCommand = "SELECT * FROM feedbacks WHERE type = 'Elogio'";
				break;

			case ("Sugestão"):
				sqlCommand = "SELECT * FROM feedbacks WHERE type = 'Sugestão'";
				break;

			case ("All"):
				sqlCommand = "SELECT * FROM feedbacks";
				break;
		}

		try {

			connection = ConnectionDB.openConnection();
			Statement cursor = connection.createStatement();
			ResultSet dataFeedbacks = cursor.executeQuery(sqlCommand);

			while (dataFeedbacks.next()) {

				String feedback = dataFeedbacks.getString("description");
				String author = dataFeedbacks.getString("author");
				String type = dataFeedbacks.getString("type");
				int id = dataFeedbacks.getInt("id");

				Category newFeedback = new Category(feedback, type, author);
				newFeedback.setId(id);

				feedbacksReturned.add(newFeedback);
			}

			cursor.close();
			connection.close();

			return feedbacksReturned;

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * Atualização da coluna "description" no Banco de Dados MySQL através da id
	 * primary key id.
	 * 
	 * @param newFeedback nova manifestão inserida pelo usuário, na qual será
	 *                    sobreposta a uma já existente no Bando de Dados
	 * @param idKey       campo "id" no qual representa a linha que vai ser alterada
	 */
	@Override
	public void updateData(String newFeedback, int idKey) {
		sqlCommand = "UPDATE feedbacks SET description = ? where id = ?";

		try {
			connection = ConnectionDB.openConnection();
			PreparedStatement cursor = connection.prepareStatement(sqlCommand);

			cursor.setString(1, newFeedback);
			cursor.setInt(2, idKey);
			cursor.execute();

			cursor.close();
			connection.close();

		} catch (SQLException e) {

		}
	}

	/**
	 * 
	 * Método que apaga uma linha do Banco de Dados tendo como parametro o primary
	 * key id
	 *
	 * @param idKey id referente a linha que será apagada
	 */

	@Override
	public void deleteData(int idKey) {

		sqlCommand = "DELETE FROM feedbacks WHERE id = ?";

		try {
			connection = ConnectionDB.openConnection();
			PreparedStatement cursor = connection.prepareStatement(sqlCommand);

			cursor.setInt(1, idKey);
			cursor.execute();

			cursor.close();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void deleteAllData() {

		sqlCommand = "DELETE FROM feedbacks";

		try {
			connection = ConnectionDB.openConnection();
			PreparedStatement cursor = connection.prepareStatement(sqlCommand);

			cursor.execute();

			cursor.close();
			connection.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}

}
