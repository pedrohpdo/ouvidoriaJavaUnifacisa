package ouvidoria;

import validation.Validator;
import categories.Reclamacao;
import categories.Elogio;
import categories.Sugestao;
import formatter.Formatter;

public class Main {

	public static void main(String args[]) {

		boolean isRunning = true;

		Formatter formatter = new Formatter();
		Reclamacao claims = new Reclamacao();
		Elogio compliments = new Elogio();
		Sugestao suggestions = new Sugestao();
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
					claims.setClaim(newClaim);
					System.out.println();
					System.out.println(formatter.headline2("RESGISTRO ADICIONADO COM SUCESSO"));
					System.out.println();
					System.out.println();
					break;

				case ("2"):
					String newCompliment = validator.stringValidation("Digite seu elogio: ");
					compliments.setCompliment(newCompliment);
					System.out.println();
					System.out.println(formatter.headline2("RESGISTRO ADICIONADO COM SUCESSO"));
					System.out.println();
					System.out.println();
					break;

				case ("3"):
					String newSuggestion = validator.stringValidation("Digite sua sugestão: ");
					suggestions.setSuggestion(newSuggestion);
					System.out.println();
					System.out.println(formatter.headline2("RESGISTRO ADICIONADO COM SUCESSO"));
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
					System.out.println(claims.getClaims());
					break;

				case ("2"):
					System.out.println(formatter.headline2("ELOGIOS"));
					System.out.println(compliments.getCompliments());
					break;

				case ("3"):
					System.out.println(formatter.headline2("SUGESTÕES"));
					System.out.println(suggestions.getSuggestions());
					break;

				case ("4"):
					System.out.println(formatter.headline2("RECLAMAÇÕES"));
					System.out.println(claims.getClaims());
					System.out.println();

					System.out.println(formatter.headline2("ELOGIOS"));
					System.out.println(compliments.getCompliments());
					System.out.println();

					System.out.println(formatter.headline2("SUGESTÕES"));
					System.out.println(suggestions.getSuggestions());
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
					System.out.println(claims.getClaims());

					keyToDelete = validator.integerValidation("Deseja apagar: ");
					System.out.println(claims.deleteClaim(keyToDelete - 1)
							? formatter.headline2("Manifestação apagada com sucesso!")
							: formatter.headline2("Não foi possível deletar sua manifestação. Tente novamente!"));
					break;

				case ("2"):
					System.out.println(formatter.headline2("Elogios"));
					System.out.println(compliments.getCompliments());

					keyToDelete = validator.integerValidation("Deseja apagar: ");

					System.out.println(compliments.deleteCompliment(keyToDelete - 1)
							? formatter.headline2("Manifestação apagada com sucesso!")
							: formatter.headline2("Não foi possível deletar sua manifestação. Tente novamente!"));
					break;

				case ("3"):
					System.out.println(formatter.headline2("Sugestões"));
					System.out.println(suggestions.getSuggestions());

					keyToDelete = validator.integerValidation("Deseja apagar: ");

					System.out.println(suggestions.deleteSuggestion(keyToDelete - 1)
							? formatter.headline2("Manifestação apagada com sucesso!")
							: formatter.headline2("Não foi possível deletar sua manifestação. Tente novamente!"));

					break;

				case ("4"):
					claims.deleteAllFeedbacks();
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
					System.out.println(claims.getClaims());
					System.out.println();

					int indexOfClaimsToUpdate = validator.integerValidation("Posição a ser alterada: ");
					String newClaim = validator.stringValidation("Digite sua alteração: ");

					System.out.println(claims.updateClaim(indexOfClaimsToUpdate - 1, newClaim)
							? formatter.headline2("Dados alterados com sucesso!")
							: formatter.headline2("Não foi possível alterar os dados. Tente novamente!"));

					break;

				case ("2"):

					System.out.println(formatter.headline2("Sugestões"));
					System.out.println(compliments.getCompliments());
					System.out.println();

					int indexOfComplimentsToUpdate = validator.integerValidation("Posição a ser alterada: ");
					String newCompliment = validator.stringValidation("Digite sua alteração: ");

					System.out.println(compliments.updateCompliment(indexOfComplimentsToUpdate - 1, newCompliment)
							? formatter.headline2("Dados alterados com sucesso!")
							: formatter.headline2("Não foi possível alterar os dados. Tente novamente!"));

					break;

				case ("3"):

					System.out.println(formatter.headline2("Sugestões"));
					System.out.println(suggestions.getSuggestions());
					System.out.println();

					int indexOfSuggetionsToUpdate = validator.integerValidation("Posição a ser alterada: ");
					String newSuggestion = validator.stringValidation("Digite sua alteração: ");

					System.out.println(suggestions.updateSuggestion(indexOfSuggetionsToUpdate - 1, newSuggestion)
							? formatter.headline2("Dados alterados com sucesso!")
							: formatter.headline2("Não foi possível alterar os dados. Tente novamente!"));
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
