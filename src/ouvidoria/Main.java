package ouvidoria;

import validation.Validator;
import formatter.Formatter;
import system.SACSystem;

public class Main {

	public static void main(String args[]) {

		boolean isRunning = true;
		SACSystem execute = new SACSystem();
		Formatter formatter = new Formatter();
		Validator validator = new Validator();

		while (isRunning) {

			System.out.println(formatter.headline("MENU PRINCIPAL"));
			System.out.println(formatter.principalMenu());
			System.out.println();

			String principalCommand = validator.stringValidation("Comando: ");

			switch (principalCommand) {

				case ("1"):

					System.out.println();
					System.out.println(formatter.headline2("Adicionar"));
					System.out.println(formatter.categoriesMenu());
					System.out.println();

					String categorieToAdd = validator.stringValidation("Selecione: ");

					switch (categorieToAdd) {

						case ("1"):
							String newClaim = validator.stringValidation("Digite sua reclamação: ");

							execute.setFeedback(newClaim, "Reclamação");

							System.out.println();
							System.out.println(formatter.headline2("REGISTRO ADICIONADO COM SUCESSO"));
							System.out.println();
							System.out.println();
							break;

						case ("2"):
							String newCompliment = validator.stringValidation("Digite seu elogio: ");

							execute.setFeedback(newCompliment, "Elogio");

							System.out.println();
							System.out.println(formatter.headline2("REGISTRO ADICIONADO COM SUCESSO"));
							System.out.println();
							System.out.println();
							break;

						case ("3"):
							String newSuggestion = validator.stringValidation("Digite sua sugestão: ");

							execute.setFeedback(newSuggestion, "Sugestão");

							System.out.println();
							System.out.println(formatter.headline2("REGISTRO ADICIONADO COM SUCESSO"));
							System.out.println();
							System.out.println();
							break;

						default:
							System.err.println("Opção inválida, tente novamente!");
							System.out.println();
							break;
					}

					break;

				case ("2"):
					System.out.println(formatter.headline2("LISTAR FEEDBACKS"));
					System.out.println(formatter.categoriesMenu());
					System.out.println("4 | Listar tudo");

					System.out.println();
					String listToPrint = validator.stringValidation("Digite: ");
					System.out.println();

					switch (listToPrint) {

						case ("1"):
							System.out.println(formatter.headline2("RECLAMAÇÕES"));
							System.out.println(execute.listFeedbacks("Reclamação"));
							break;

						case ("2"):
							System.out.println(formatter.headline2("ELOGIOS"));
							System.out.println(execute.listFeedbacks("Elogio"));
							break;

						case ("3"):
							System.out.println(formatter.headline2("SUGESTÕES"));
							System.out.println(execute.listFeedbacks("Sugestão"));
							break;

						case ("4"):
							System.out.println(formatter.headline2("RECLAMAÇÕES"));
							System.out.println(execute.listFeedbacks("Reclamação"));
							System.out.println();

							System.out.println(formatter.headline2("ELOGIOS"));
							System.out.println(execute.listFeedbacks("Elogio"));
							System.out.println();

							System.out.println(formatter.headline2("SUGESTÕES"));
							System.out.println(execute.listFeedbacks("Sugestão"));
							System.out.println();

							break;

						default:
							System.err.println("Opção inválida. Tente novamente!");
							System.out.println();
							break;
					}

					break;

				case ("3"):
					System.out.println(formatter.headline2("APAGAR:"));
					System.out.println(formatter.categoriesMenu());
					System.out.println();

					String listToDelete = validator.stringValidation("Deletar (1 | 2 | 3 | 4 [Deletar tudo]): ");
					System.out.println();

					int keyToDelete;

					switch (listToDelete) {

						case ("1"):
							System.out.println(formatter.headline2("Reclamações"));
							System.out.println(execute.listFeedbacks("Reclamação"));

							keyToDelete = validator.integerValidation("Deseja apagar: ");
							System.out.println(execute.removeFeedback("Reclamação", keyToDelete - 1)
									? formatter.headline2("Manifestação apagada com sucesso!")
									: formatter
											.headline2("Não foi possível deletar sua manifestação. Tente novamente!"));
							break;

						case ("2"):
							System.out.println(formatter.headline2("Elogios"));
							System.out.println(execute.listFeedbacks("Elogio"));

							keyToDelete = validator.integerValidation("Deseja apagar: ");

							System.out.println(execute.removeFeedback("Elogio", keyToDelete - 1)
									? formatter.headline2("Manifestação apagada com sucesso!")
									: formatter
											.headline2("Não foi possível deletar sua manifestação. Tente novamente!"));
							break;

						case ("3"):
							System.out.println(formatter.headline2("Sugestões"));
							System.out.println(execute.listFeedbacks("Sugestão"));

							keyToDelete = validator.integerValidation("Deseja apagar: ");

							System.out.println(execute.removeFeedback("Sugestão", keyToDelete - 1)
									? formatter.headline2("Manifestação apagada com sucesso!")
									: formatter
											.headline2("Não foi possível deletar sua manifestação. Tente novamente!"));

							break;

						case ("4"):
							execute.deleteAllfeedbacks();
							System.out.println(formatter.headline2("TODOS OS REGISTROS FORAM APAGADOS!"));
							System.out.println();

							break;

						default:
							System.err.println("Comando inválido. Tente novamente!");
							System.out.println();
							break;
					}

					break;

				case ("4"):

					System.out.println(formatter.categoriesMenu());
					System.out.println();
					String listToUpdate = validator.stringValidation("Alterar (1 | 2 | 3 | 4 ): ");
					System.out.println();

					switch (listToUpdate) {

						case ("1"):

							System.out.println(formatter.headline2("Reclamações"));
							System.out.println(execute.listFeedbacks("Reclamação"));
							System.out.println();

							int indexOfClaimsToUpdate = validator.integerValidation("Posição a ser alterada: ");
							String newClaim = validator.stringValidation("Digite sua alteração: ");

							System.out.println(execute.updateFeedback("Reclamação", indexOfClaimsToUpdate - 1, newClaim)
									? formatter.headline2("Dados alterados com sucesso!")
									: formatter.headline2("Não foi possível alterar os dados. Tente novamente!"));

							break;

						case ("2"):

							System.out.println(formatter.headline2("Elogios"));
							System.out.println(execute.listFeedbacks("Elogio"));
							System.out.println();

							int indexOfComplimentsToUpdate = validator.integerValidation("Posição a ser alterada: ");
							String newCompliment = validator.stringValidation("Digite sua alteração: ");

							System.out.println(
									execute.updateFeedback("Elogio", indexOfComplimentsToUpdate - 1, newCompliment)
											? formatter.headline2("Dados alterados com sucesso!")
											: formatter
													.headline2("Não foi possível alterar os dados. Tente novamente!"));

							break;

						case ("3"):

							System.out.println(formatter.headline2("Sugestões"));
							System.out.println(execute.listFeedbacks("Sugestão"));
							System.out.println();

							int indexOfSuggetionsToUpdate = validator.integerValidation("Posição a ser alterada: ");
							String newSuggestion = validator.stringValidation("Digite sua alteração: ");

							System.out.println(
									execute.updateFeedback("Sugestão", indexOfSuggetionsToUpdate - 1, newSuggestion)
											? formatter.headline2("Dados alterados com sucesso!")
											: formatter
													.headline2("Não foi possível alterar os dados. Tente novamente!"));
							break;

						default:
							System.err.println("Comando inválido. Tente novamente!");
							System.out.println();
							break;
					}
					System.out.println();
					break;

				case ("5"):
					isRunning = false;
					System.out.println();
					System.out.println("OBRIGADO POR UTILIZAR O SISTEMA!");
					System.out.println("Acesse:");
					System.out.println("Github.com/pedrohpdo");
					System.out.println("Instagram.com/ppedropereiraa");
					break;

				default:
					System.err.println("Comando inválido. Tente novamente!");
					System.out.println();
					break;

			}
		}
	}
}
