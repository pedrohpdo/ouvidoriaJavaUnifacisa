package feedback;

/**
 * 
 * Classe Feedback. Representa um novo feedback que será salvo dentro da
 * estrutura de dados, é uma manifestação de um usuário dentro de um sistema de
 * ouvidoria. Nesta classe serão implementados todas as características de um
 * feedback, que será utilizado dentro do resto do sistema.
 * 
 * @author Pedro Henrique Pereira de Oliveira
 * 
 */

public class Feedback {

	private String description, author;
	private int id;

	/**
	 * 
	 * Construtor de um feedback. Inicia o construtor de um novo feedback para ser
	 * armazenado na sua lista específica das suas respectívas características. Todo
	 * feedback que é criado recebe uma String Feedback, correspondendo a descrição
	 * fornecida pelo usuário, uma String Type, que se refere ao tipo de feedback
	 * (Reclamação, Elogio ou Sugestão) uma String author, que corresponde ao autor
	 * do registro em questão. A ideia é que vários usuários possam fazer registros
	 * no sistema.
	 * 
	 * 
	 * @param description registro em texto da descrição do feedback
	 * @param author   nome do usuário que acabou de fazer o registro
	 * 
	 */

	public Feedback(String description, String author) {
		this.description = description;
		this.author = author;
	}

	/**
	 * 
	 * Métodos Auxiliares
	 */

	public String getDescription() {
		return description;
	}

	public void setDescription(String feedback) {
		this.description = feedback;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {

		String toString = String.format("Type: %s | Feedback: %s", getAuthor(), getDescription());
		return toString;
	}

}
