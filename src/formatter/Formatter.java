package formatter;

public class Formatter {

	public String principalMenu() {

		String menu = "1 | Adicionar Feedback\n"
				+ "2 | Listar Feedbacks\n"
				+ "3 | Apagar Feedback\n"
				+ "4 | Editar Feedback\n"
				+ "5 | Sair";

		return menu;

	}

	public String categoriesMenu() {
		String menu = "1 | Reclamações\n" + "2 | Elogios\n" + "3 | Sugestões";
		return menu;

	}

	public String headline(String placeholder) {

		String header = "";
		String line = "=".repeat(30);

		header += String.format(line + " " + placeholder + " " + line);

		return header;
	}

	public String headline2(String placeholder) {
		String line = "-".repeat(25);
		String header = String.format("%s %s", line, placeholder);

		return header;
	}

}
