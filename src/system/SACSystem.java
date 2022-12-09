package system;

import databaseMySQL.FeedbackDB;
import java.util.ArrayList;
import feedback.Category;
import validation.Validator;

/**
 * 
 * @author Pedro Henrique Pereira de Olivera
 *
 */

public class SACSystem implements interfaceSAC {

    /**
     * Dentro da classe serão instanciados dois atributos: um ArrayList do tipo
     * Feedback e um Object do tipo FeedbackDB
     * 
     * {@value} list: será uma lista do tipo Category. Ela será usada principalmente
     * para melhor
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

    private Validator validate;
    private FeedbackDB execute;
    private ArrayList<Category> list;

    /**
     * Construtor de um novo Sistema. Aqui só será inicializado o atributo
     * execute(tipo FeedbackDB) que será responsável por relacionar os Feedbacks com
     * e o banco de dados.
     * 
     * @author Pedro Henrique Pereira de Oliveira
     * 
     */

    public SACSystem() {
        this.execute = new FeedbackDB();
    }

    /**
     * Criar um novo Feedback com o type fornecido pela classe Main. Um novo
     * Feedback é
     * criado recebendo apenas seu texto e tipo, tendo seus parametros author
     * definidos obrigadoriamente como "Pedro".
     * 
     * Depois que um novo Feedback é criado, é executada a operação no Banco de
     * Dados onde ele será inserido.
     * 
     * @param feedback String que representa uma manifestação do Usuário.
     */

    @Override
    public void setFeedback(String text, String type) {
        Category feedback = new Category(text, "Pedro", type);
        execute.addData(feedback);
    }

    /**
     * 
     * Função que recebe as linhas do Banco de Dados MySQL e retorna para o usuário
     * apenas os Feedbacks que possuem o atributo type fornecidos pela classe Main.
     * 
     * ps: É interessante notar que o iD que será mostrado ao usuário será capturado
     * do ArrayList<Feedback> ao invés do id gerado pela tabela MySQL que foi
     * retornado a partir do objeto execute, de forma que a experiência do usuário
     * seja otimizada.
     * 
     * @return uma String que representa todos os objetos Feedbacks que possuem o
     *         atributo Type fornecidos pelo Main.
     */

    @Override
    public String listFeedbacks(String type) {
        list = execute.getData(type);

        if (list.isEmpty()) {
            return "NÃO EXISTEM DADOS CADASTRADOS";

        } else {
            String data = "";

            for (int i = 0; i < list.size(); i++) {
                data += String.format("%d | %s\n", i + 1, list.get(i).toString());
            }

            return data;
        }
    }

    /**
     * 
     * Apagar um Feedback do banco de dados a partir do seu IdKey.
     * 
     * ps: O id passado pelo usuário corresponde ao index do ArrayList<Feedback>
     * gerado pelo listFeedbacks(), entretanto o id que será executado pelo Banco de
     * Dados será o Object.getId(), no qual foi setado anteriormente como o Id
     * primaryKey do MySQL.
     * 
     * 
     * 
     * @param idKeyToDelete index referente ao ArrayList<Feedback>, onde será usado
     *                      de referência para acessar o getId do objeto Feedback
     * @return um boolean confiramento a exclusão da linha no Banco de Dados.
     */

    @Override
    public boolean removeFeedback(String type, int idKeyToDelete) {
        list = execute.getData(type);

        try {
            if (list.isEmpty() && !validate.containsIndex(list, idKeyToDelete)) {
                return false;

            } else {
                execute.deleteData(list.get(idKeyToDelete).getId());
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

    @Override
    public boolean updateFeedback(String type, int idKeyToUpdate, String newFeedback) {
        list = execute.getData(type);

        try {
            if (list.isEmpty() && !validate.containsIndex(list, idKeyToUpdate)) {
                return false;

            } else {
                execute.updateData(newFeedback, list.get(idKeyToUpdate).getId());
                return true;
            }

        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Deleta todos os feedbacks dentro do Banco de Dados
     */

    @Override
    public void deleteAllfeedbacks() {
        execute.deleteAllData();
    }
}