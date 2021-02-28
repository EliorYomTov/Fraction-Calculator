import java.util.Scanner;

public class FractionCalculator {
	private static Scanner scanner = new Scanner(System.in);
	private static String numerator;
	private static String denominator;

	public static void main(String[] args) {
		Calculator();
	}

	public static String getOperation() {
		boolean validOperation = false;
		System.out.print("Please enter an operation (+, -, /, *, =, or Q to quit): ");
		String operation = scanner.nextLine();
		while (validOperation == false) {
			operation = operation.toUpperCase();
			if (operation.equalsIgnoreCase("+") || operation.equalsIgnoreCase("-") || operation.equalsIgnoreCase("/")
					|| operation.equalsIgnoreCase("*") || operation.equalsIgnoreCase("=")
					|| operation.equalsIgnoreCase("Q"))
				validOperation = true;
			else
				System.out.print("Invalid input (+, -, /, *, =, or Q to quit): ");
			if (validOperation != true)
				operation = scanner.nextLine();
		}
		return operation;
	}

	public static boolean validFraction() {
		int slashIndex = -1;
		String parameter = scanner.nextLine();
		if (parameter.length() == 0)
			return false;
		if (parameter.indexOf("-") != 0 && parameter.indexOf("-") != -1)
			return false;
		if (parameter.contains("/"))
			slashIndex = parameter.indexOf("/");

		for (int i = parameter.contains("-") ? 1 : 0; i < parameter.length(); i++) {
			if ((!Character.isDigit(parameter.charAt(i)) && i != slashIndex) || Character.isLetter(parameter.charAt(i))
					|| parameter.charAt(slashIndex + 1) == '0')
				return false;
		}
		if (slashIndex != -1) {
			numerator = parameter.substring(0, slashIndex);
			denominator = parameter.substring(slashIndex + 1);
		} else {
			numerator = parameter;
			denominator = "1";
		}
		return true;
	}

	public static Fraction getFraction() {
		System.out.print("Please enter a fraction(a/b) or integer (a): ");
		while (validFraction() != true) {
			System.out.print("Invalid fraction. please enter (a/b) or (a), where a and b are integers and b is not zero: ");
		}
		int x = Integer.parseInt(numerator);
		int y = Integer.parseInt(denominator);
		Fraction tmp = new Fraction(x, y);
		return tmp;
	}

	public static void Calculator() {
		System.out.println("This is a fraction calculator");
		System.out.println(
				  " _____________________\r\n"
				+ "|  _________________  |\r\n"
				+ "| |  1/2+ 1/3 = 5/6 | |\r\n"
				+ "| |_________________| |\r\n"
				+ "|  ___ ___ ___   ___  |\r\n"
				+ "| | 7 | 8 | 9 | | + | |\r\n"
				+ "| |___|___|___| |___| |\r\n"
				+ "| | 4 | 5 | 6 | | - | |\r\n"
				+ "| |___|___|___| |___| |\r\n"
				+ "| | 1 | 2 | 3 | | x | |\r\n"
				+ "| |___|___|___| |___| |\r\n"
				+ "| | . | 0 | = | | / | |\r\n"
				+ "| |___|___|___| |___| |\r\n"
				+ "|_____________________|\r\n"
				
				+ "");
		System.out.println("it will add, subtract, multiply and divide fractions until you type Q to quit.");
		System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
		System.out.println("------------------------------------------------------------------------------");
		String operation = getOperation();
		
		while (operation.charAt(0) != 'Q') {
			Fraction f1 = getFraction();
			Fraction f2 = getFraction();
			Fraction answer;
			switch (operation.charAt(0)) {
			case '+':
				answer = f1.add(f2);
				System.out.println(
						f1 + " + " + f2 + " = " + (answer.getM_denominator() > 1 ? answer : answer.getM_denominator()));
				System.out.println("-----------------------------------------------------------------------------------");
				break;
			case '-':
				answer = f1.subtract(f2);
				System.out.println(
						f1 + " - " + f2 + " = " + (answer.getM_denominator() > 1 ? answer : answer.getM_denominator()));
				System.out.println("-----------------------------------------------------------------------------------");
				break;
			case '/':
				answer = f1.divide(f2);
				System.out.println(
						f1 + " / " + f2 + " = " + (answer.getM_denominator() > 1 ? answer : answer.getM_denominator()));
				System.out.println("-----------------------------------------------------------------------------------");
				break;
			case '*':
				answer = f1.multiply(f2);
				System.out.println(
						f1 + " * " + f2 + " = " + (answer.getM_denominator() > 1 ? answer : answer.getM_denominator()));
				System.out.println("-----------------------------------------------------------------------------------");
				break;
			case '=':
				System.out.println(f1 + " = " + f2 + " is " + f1.equals(f2));
				System.out.println("-----------------------------------------------------------------------------------");
				break;
			}
			 operation = getOperation();
		}
	System.out.println("Goodbye :)");
	}
}
