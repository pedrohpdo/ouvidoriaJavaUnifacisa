package categories;

import java.util.ArrayList;
import feedback.Feedback;
import databaseMySQL.FeedbackDB;

/**
 * 
 * @author Pedro Henrique Pereira de Olivera
 *
 */

public class Reclamacao {

	/**
	 * Dentro da classe serão instanciados dois atributos: um ArrayList do tipo
	 * Feedback e um Object do tipo FeedbackDB
	 * 
	 * {@value} claimsList: será uma lista do tipo Feedback que tem o atributo Type
	 * = "claim"/"Reclamação". Ela será usada principalmente para melhor
	 * apresentação do id para o usuário. Ao invés de ser mostrado o Id gerado pelo
	 * MySQL, será mostrado o índice do ArrayList, desta forma padronizando a
	 * vizualização do Usuário e melhorando sua experiência, e ainda sem comprometer
	 * a execução do programa.
	 * 
	 * {@value} execute: representa as funções do Banco de Dados que serão
	 * instanciadas dentro dessa classe. A ideia é que todas as funcionalidades do
	 * MySQL sejam executadas numa classe extra
	 * 
	 */

	FeedbackDB execute;
	ArrayList<Feedback> claimsList;

	/**
	 * Construtor de uma nova Reclamação. Aqui só será inicializado o atributo
	 * execute(tipo FeedbackDB) que será responsável por relacionar os Feedbacks com
	 * type = "claims"/"reclamação" e o banco de dados.
	 * 
	 * @author Pedro Henrique Pereira de Oliveira
	 * 
	 */

	public Reclamacao() {
		execute = new FeedbackDB();
	}

	/**
	 * Criar um novo Feedback com o type = "claims"/"reclamação". Um novo Feedback é
	 * criado recebendo apenas seu texto, tendo seus parametros author e type
	 * setados como "Pedro" e "Reclamação", respectivamente.
	 * 
	 * Depois que um novo Feedback é criado, é executada a operação no Banco de
	 * Dados onde ele será inserido.
	 * 
	 * @param feedback String que representa uma manifestação do Usuário.
	 */

	public void setClaim(String feedback) {
		Feedback newClaim = new Feedback(feedback, "Reclamação", "Pedro");
		execute.setFeedback(newClaim);
	}

	/**
	 * 
	 * Função que recebe as linhas do Banco de Dados MySQL e retorna para o usuário
	 * apenas os Feedbacks que possuem o type = "Reclamação".
	 * 
	 * ps: É interessante notar que o iD que será mostrado ao usuário será capturado
	 * do ArrayList<Feedback> ao invés do id gerado pela tabela MySQL que foi
	 * retornado a partir do objeto execute, de forma que a experiência do usuário
	 * seja otimizada.
	 * 
	 * @return uma String que representa todos os objetos Feedbacks que possuem o
	 *         type = "Reclamação"
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
	 * Apagar um Feedback do banco de dados a partir do seu IdKey.
	 * 
	 * ps: O id passado pelo usuário corresponde ao index do ArrayList<Feedback>
	 * gerado pelo getClaims(), entretanto o id que será executado pelo Banco de
	 * Dados será o Object.getId(), no qual foi setado anteriormente como o Id
	 * primaryKey do MySQL.
	 * 
	 * @param idKeyToDelete index referente ao ArrayList<Feedback>, onde será usado
	 *                      de referência para acessar o getId do objeto Feedback
	 * @return um boolean confiramento a exclusão da linha no Banco de Dados.
	 */

	public boolean deleteClaim(int idKeyToDelete) {

		claimsList = execute.getFeedbacks("Reclamação");

		try {

			if (claimsList.isEmpty() && !containsIndex(claimsList, idKeyToDelete)) {
				return false;

			} else {
				execute.deleteFeedbacks(claimsList.get(idKeyToDelete).getId());
				return true;

			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	/**
	 * 
	 * Edição/Atualização de um Feedback (setDescription()) no sistema de ouvidoria
	 * através de uma iDKey.
	 * 
	 * ps: É interessante notar que o iD que será mostrado ao usuário será capturado
	 * do ArrayList<Feedback> ao invés do id gerado pela tabela MySQL que foi
	 * retornado a partir do objeto execute, de forma que a experiência do usuário
	 * seja otimizada.
	 * 
	 * @param idKeyToUpdate Integer que representa o id do Feedback que será
	 *                      alterado
	 * @param newFeedback   String que representa a alteração da manifestação do
	 *                      usuário
	 * @return um boolean que confirma a alteração da manifestação do Usuário
	 */

	public boolean updateClaim(int idKeyToUpdate, String newFeedback) {

		claimsList = execute.getFeedbacks("Reclamação");

		try {
			if (claimsList.isEmpty() && !containsIndex(claimsList, idKeyToUpdate)) {
				return false;

			} else {
				execute.updateFeedback(newFeedback, claimsList.get(idKeyToUpdate).getId());
				return true;

			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	public void deleteAllFeedbacks() {
		execute.deleteAllfeedbacks();
	}

	/**
	 * 
	 * Método estático que tem a função de verificar a existência de um Id dentro de
	 * um ArrayList<Feedback>.
	 * 
	 * @param list       ArrayList<Feedback> que vai ser iterada.
	 * @param keyToCheck Integer que representa o index que será procurado na lista.
	 * @return um boolean que confirma a existência do index.
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
