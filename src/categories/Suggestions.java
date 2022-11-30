package categories;

import java.util.ArrayList;
import validation.Validator;
import feedback.Feedback;
import databaseMySQL.FeedbackDB;

/**
 * 
 * @author Pedro Henrique Pereira de Oliveira
 *
 */

public class Suggestions {
	ArrayList<Feedback> suggestionsList;
	FeedbackDB execute;
	Validator validation = new Validator();

	public Suggestions() {
		execute = new FeedbackDB();
	}

	public void setSuggestion(String feedback) {
		Feedback newSuggestion = new Feedback(feedback, "Sugestão", "Pedro");
		execute.setFeedback(newSuggestion);
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getSuggestions() {
		suggestionsList = execute.getFeedbacks("Sugestão");

		if (suggestionsList.isEmpty()) {
			return "NÃO EXISTEM SUGESTÕES CADASTRADAS";

		} else {
			String dataSuggestions = "";

			for (int i = 0; i < suggestionsList.size(); i++) {
				dataSuggestions += String.format("Id: %d | %s", i + 1, suggestionsList.get(i).toString() + System.lineSeparator());
			}

			return dataSuggestions;
		}
	}

	/**
	 * 
	 * @param idKeyToDelete
	 * @return
	 */

	public boolean deleteSuggestion(int idKeyToDelete) {
		suggestionsList = execute.getFeedbacks("Sugestão");

		try {
			if (suggestionsList.isEmpty() && !containsIndex(suggestionsList, idKeyToDelete - 1)) {
				return false;

			} else {
				execute.deleteFeedbacks(suggestionsList.get(idKeyToDelete - 1).getId());
				return true;

			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * 
	 * @param idKeyToUpdate
	 * @param newFeedback
	 * @return
	 */

	public boolean updateSuggestion(int idKeyToUpdate, String newFeedback) {
		suggestionsList = execute.getFeedbacks("Sugestão");

		if (suggestionsList.isEmpty() && !containsIndex(suggestionsList, idKeyToUpdate - 1)) {
			return false;

		} else {
			execute.updateFeedback(newFeedback, suggestionsList.get(idKeyToUpdate - 1).getId());
			return true;

		}

	}

	/**
	 * 
	 * @param list
	 * @param keyToCheck
	 * @return
	 */

	private static boolean containsIndex(ArrayList<Feedback> list, int keyToCheck) {

		for (int i = 0; i < list.size(); i++) {
			if (keyToCheck == i) {
				return true;
			}
		}
		return false;

	}
}
