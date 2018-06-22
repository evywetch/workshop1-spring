package rsvier.workshop.view;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import rsvier.workshop.dao.*;


public abstract class View {

	private Scanner userInput = new Scanner(System.in);

	public abstract void printHeaderMessage();

	public abstract void printMenuMessage();

	public void printAskUserYesOrNo() {
		System.out.println("Vul in \"J\" indien Ja en \"N\" indien Nee.");
	}

	public boolean printAskUserToEnableHikariOrNot() {

		System.out.println("\n=================================   Nevvo Meubels =================================\n");
		System.out.println("Wilt u hikari als connection pool gebruiken? Vul in \"J\" indien Ja en \"N\" indien Nee.");
		String userAnswer = userInput.nextLine();
		if (userAnswer.equalsIgnoreCase("J")) {
			System.out.println("Hikari connection pool word nu geactiveerd...");
			return true;
		}
		System.out.println("Jdbc connection pool word nu geactiveerd...");
		return false;

	}

	public void printAskUserToUseSQLOrMongo() {
		System.out.println("\nWelke database wilt u gebruiken?\n1- MySQL\n2- MongoDb");
		new DAOFactory(getIntInput());
	}

	public void printAskConfirmation() {
		System.out.println("Weet u het zeker?");
	}

	public void printMenuInputIsWrong() {
		System.out.println("De ingevoerde menu keuze is onjuist, probeer het nogmaals\n");
	}

	public void printExitApplicationMessage() {
		System.out.println("U verlaat het programma. Bedankt en tot ziens!");
	}

	public Integer getIntInput() {

		try {
			return Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException ex) {
			System.out.print("Verkeerde input. Vul aub een nummer in: ");
			return getIntInput();
		}
	}

	public String getStringInput() {

		String string = userInput.nextLine();

		if (string == null || (string.trim().isEmpty())) {
			System.out.print("U kunt dit gedeelte niet leeg laten. Vul aub iets in: ");
			return getStringInput();
		} else {
			return string;
		}
	}

	public String getStringInputCanBeNull() {
		return userInput.nextLine();
	}

	public BigDecimal getBigDecimalInput() {

		try {
			BigDecimal bigDecimal = userInput.nextBigDecimal();
			/*
			 * This is added because the scanner is not consuming \n so when you ask a new
			 * int value of the user after the bigdecimal input you will get an error
			 * message. With this call that \n is consumed and the error is fixed.
			 */
			userInput.nextLine();

			if (bigDecimal == null) {
				System.out.print("U kunt dit gedeelte niet leeg laten. Vul aub iets in: ");
				return getBigDecimalInput();
			}

			return bigDecimal;

		} catch (InputMismatchException ex) {
			System.out.print("Verkeerde input.");
			return getBigDecimalInput();
		}

	}

	public String confirmYesOrNo() {

		printAskConfirmation();
		printAskUserYesOrNo();

		return getStringInput();
	}

}
