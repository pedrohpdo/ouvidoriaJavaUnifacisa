package categories;

import java.util.ArrayList;
import feedback.Feedback;
import databaseMySQL.FeedbackDB;

/**
 * 
 * @author pedro
 *
 */

public class Compliments {

	ArrayList<Feedback> complimentsList;
	FeedbackDB execute;
	
	/**
	 * 
	 */
	
	public Compliments() {
		execute = new FeedbackDB();

	}

	public void setCompliment(String feedback) {
		Feedback newCompliment = new Feedback(feedback, "Elogio", "Pedro");
		execute.setFeedback(newCompliment);
	}

	/**
	 * 
	 * @return
	 */
	
	public String getCompliments() {
		complimentsList = execute.getFeedbacks("Elogio");

		if (complimentsList.isEmpty()) {

			return "NÃO EXISTEM ELOGIOS CADASTRADOS!";

		} else {

			String dataCompliments = "";

			for (int i = 0; i < complimentsList.size(); i++) {
				dataCompliments += String.format("Id: %d | %s", i + 1, complimentsList.get(i).toString() + System.lineSeparator());
			}

			return dataCompliments;
		}

	}
	
	/**
	 * 
	 * @param idKeyToDelete
	 * @return
	 */
	
	public boolean deleteCompliment(int idKeyToDelete) {
		complimentsList = execute.getFeedbacks("Elogio");

		try {
			if (complimentsList.isEmpty() && !containsIndex(complimentsList, idKeyToDelete - 1)) {
				return false;

			} else {
				execute.deleteFeedbacks(complimentsList.get(idKeyToDelete - 1).getId());
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
	
	public boolean updateCompliment(int idKeyToUpdate, String newFeedback) {
		complimentsList = execute.getFeedbacks("Elogio");

		try {
			if (complimentsList.isEmpty() && !containsIndex(complimentsList, idKeyToUpdate - 1)) {
				return false;
				
			} else {
				execute.updateFeedback(newFeedback, complimentsList.get(idKeyToUpdate - 1).getId());
				return true;
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
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
