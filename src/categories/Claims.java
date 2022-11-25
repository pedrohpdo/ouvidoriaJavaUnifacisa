package categories;

import java.util.ArrayList;
import feedback.Feedback;
import databaseMySQL.FeedbackDB;

/**
 * 
 * @author Pedro Henrique Pereira de Olivera
 *
 */

public class Claims {

	FeedbackDB execute;
	ArrayList<Feedback> claimsList;

	public Claims() {
		execute = new FeedbackDB();
	}

	/**
	 * Registro de uma nova manifestação do usuário dentro do Sistema de ouvidoria
	 * que será salvo num banco de dados
	 * 
	 * @param feedback String que corresponde a manifestação do usuário
	 */

	public void setClaim(String feedback) {
		Feedback newClaim = new Feedback(feedback, "Reclamação", "Pedro");
		execute.setFeedback(newClaim);
	}

	/**
	 * 
	 * 
	 * @return uma String que corresponde a todos os objetos Feedback que tem o
	 *         atributo type = "Reclamação"
	 * 
	 * 
	 */

	public String getClaims() {
		claimsList = execute.getFeedbacks("Reclamação");

		if (claimsList.isEmpty()) {

			return "NÃO EXISTEM RECLAMAÇÕES CADASTRADAS";

		} else {

			String claims = "";

			for (int i = 0; i < claimsList.size(); i++) {
				claims += String.format("Id: %d | %s", i + 1, claimsList.get(i).toString()) + System.lineSeparator();
			}
			
			return claims;
		}
	}

	/**
	 * 
	 * Deleta um objeto Feedback que tenha um atributo type == "Reclamação" do Banco
	 * de Dados
	 * 
	 * @param idKeyToDelete
	 * @return
	 */

	public boolean deleteClaim(int idKeyToDelete) {

		claimsList = execute.getFeedbacks("Reclamação");

		if (claimsList.isEmpty() && !containsIndex(claimsList, idKeyToDelete - 1)) {
			return false;

		} else {
			execute.deleteFeedbacks(claimsList.get(idKeyToDelete - 1).getId());
			return true;

		}

	}

	/**
	 * 
	 * @param idKeyToUpdate
	 * @param newFeedback
	 * @return
	 */

	public boolean updateClaim(int idKeyToUpdate, String newFeedback) {

		claimsList = execute.getFeedbacks("Reclamação");

		if (claimsList.isEmpty() && !containsIndex(claimsList, idKeyToUpdate - 1)) {
			return false;

		} else {
			execute.updateFeedback(newFeedback, claimsList.get(idKeyToUpdate - 1).getId());
			return true;

		}

	}

	public void deleteAllFeedbacks() {
		execute.deleteAllfeedbacks();
	}

	private static boolean containsIndex(ArrayList<Feedback> list, int keyToCheck) {

		for (int i = 0; i < list.size(); i++) {
			if (keyToCheck == i) {
				return true;
			}
		}
		return false;

	}

}
