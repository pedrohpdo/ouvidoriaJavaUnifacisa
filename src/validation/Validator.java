package validation;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import feedback.Category;

/**
 * 
 * Classe responsável por fazer a validação de Entradas do usuário, sejam elas
 * Strings ou Integers
 * 
 * @author Pedro Henrique Pereira de Oliveira
 *
 */

public class Validator {

	Scanner input = new Scanner(System.in);

	/**
	 * 
	 * Método que valida a entrada de Strings fornecidas para o usuário. Caso o
	 * usuário forneça dados vazios ou nulos, o método imprimirá uma mensagem
	 * alegando a entrada errônea de dados e irá pedir a inserção de novas
	 * informações até que um valor válido seja encontrado
	 * 
	 * @param placeholder mensagem que será mostrada ao usuário antes dele entrar
	 *                    com dados
	 * @return a String validada pelo método, que poderá ser usada ao longo do
	 *         sistema
	 */

	public String stringValidation(String placeholder) {

		while (true) {
			System.out.print(placeholder);

			String stringToRead = input.nextLine();

			if (stringToRead.isEmpty()) {
				System.err.println("Digite um valor válido!");

			} else {
				return stringToRead.trim();
			}
		}
	}

	/**
	 * 
	 * Método que valida a entrada de tipos primitivos int fornecidas pelo usuário.
	 * Caso o usuário forneça dados vazios ou nulos, o método imprimirá uma mensagem
	 * alegando a entrada errônea de dados e irá pedir a inserção de novas
	 * informações até que um valor válido seja encontrado.
	 * 
	 * @param placeholder mensagem que será mostrada ao usuário antes dele entrar
	 *                    com dados
	 * @return o Integer validado pelo método, que poderá ser usada ao longo do
	 *         sistema
	 */

	public int integerValidation(String placeholder) {

		while (true) {
			System.out.print(placeholder);

			try {
				Integer integerToRead = input.nextInt();
				input.nextLine();
				Integer formatedNumber = integerTrim(integerToRead);

				if (formatedNumber.getClass().getSimpleName().equals("Integer")) {
					return formatedNumber;

				} else {
					System.err.println("Digite um valor inteiro válido!");
				}

			} catch (InputMismatchException e) {
				System.err.println("Digite valor inteiro válido (Exception)");
				System.out.println(e.getLocalizedMessage());
			}
			input.nextLine();

		}
	}

	/**
	 * 
	 * Método auxiliar que remove os espaços em branco de um número inteiro passado
	 * pelo usuário
	 * 
	 * @param number numero que será verificado
	 * @return o número sem os espaços
	 * 
	 */




	private static int integerTrim(int number) {

		String numberCheck = Integer.toString(number).trim();

		if (numberCheck.isEmpty()) {
			throw new InputMismatchException("Número vázio, erro");
		} else {
			int numberChecked = Integer.parseInt(numberCheck);

			return numberChecked;
		}
	}

	public boolean containsIndex(ArrayList<Category> list, int keyToCheck) {

        for (int i = 0; i < list.size(); i++) {
            if (keyToCheck == i) {
                return true;
            }
        }
        return false;

    }
}
