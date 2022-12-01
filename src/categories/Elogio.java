package categories;

import java.util.ArrayList;
import feedback.Feedback;
import databaseMySQL.FeedbackDB;

public class Elogio {

	/**
	 * Dentro da classe serão instanciados dois atributos: um ArrayList do tipo
	 * Feedback e um Object do tipo FeedbackDB
	 * 
	 * {@value} complimentsList: será uma lista do tipo Feedback que tem o atributo
	 * Type = "compliment"/"Elogio". Ela será usada principalmente para melhor
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

	ArrayList<Feedback> complimentsList;
	FeedbackDB execute;

	/**
	 * Construtor de um novo Elogio. Aqui só será inicializado o atributo
	 * execute(tipo FeedbackDB) que será responsável por relacionar os Feedbacks com
	 * type = "compliment"/"elogio" e o banco de dados.
	 * 
	 * @author Pedro Henrique Pereira de Oliveira
	 * 
	 */

	public Elogio() {
		execute = new FeedbackDB();
	}

	/**
	 * Criar um novo Feedback com o type = "compliments"/"elogio". Um novo Feedback
	 * é criado recebendo apenas seu texto, tendo seus parametros author e type
	 * setados como "Pedro" e "Elogio", respectivamente.
	 * 
	 * Depois que um novo Feedback é criado, é executada a operação no Banco de
	 * Dados onde ele será inserido.
	 * 
	 * @param feedback String que representa uma manifestação do Usuário.
	 */

	public void setCompliment(String feedback) {
		Feedback newCompliment = new Feedback(feedback, "Elogio", "Pedro");
		execute.setFeedback(newCompliment);
	}

	/**
	 * 
	 * Função que recebe as linhas do Banco de Dados MySQL e retorna para o usuário
	 * apenas os Feedbacks que possuem o type = "Elogio".
	 * 
	 * ps: É interessante notar que o iD que será mostrado ao usuário será capturado
	 * do ArrayList<Feedback> ao invés do id gerado pela tabela MySQL que foi
	 * retornado a partir do objeto execute, de forma que a experiência do usuário
	 * seja otimizada.
	 * 
	 * @return uma String que representa todos os objetos Feedbacks que possuem o
	 *         type = "Elogio"
	 */

	public String getCompliments() {
		complimentsList = execute.getFeedbacks("Elogio");

		if (complimentsList.isEmpty()) {

			return "NÃO EXISTEM ELOGIOS CADASTRADOS!";

		} else {

			String dataCompliments = "";

			for (int i = 0; i < complimentsList.size(); i++) {
				dataCompliments += String.format("Id: %d | %s", i + 1,
						complimentsList.get(i).toString() + System.lineSeparator());
			}

			return dataCompliments;
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
	 * Edição/Atualização de um Feedback (setDescription()) no sistema de ouvidoria
	 * através de uma iDKey.
	 * 
	 * ps: É interessante notar que o iD que será mostrado ao usuário será capturado
	 * do ArrayList<Feedback> ao invés do id gerado pela tabela MySQL que foi
	 * retornado a partir do objeto execute, de forma que a experiência do usuário
	 * seja otimizada.
	 * 
	 * @param idKeyToUpdate Integer que representa o id do Feedback que será alterado
	 * @param newFeedback String que representa a alteração da manifestação do usuário
	 * @return um boolean que confirma a alteração da manifestação do Usuário
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
