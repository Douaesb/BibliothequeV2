package utilitaire;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

    // Existing validation methods
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    public static boolean isValidAge(int age) {
        return age > 0 && age < 150;
    }

    public static boolean isValidCNE(String cne) {
        return cne.matches("\\d{8}");
    }

    public static boolean isValidCIN(String cin) {
        return cin.matches("[A-Z]\\d{6}");
    }

    public static boolean isValidTitre(String titre) {
        return titre != null && !titre.trim().isEmpty();
    }

    public static boolean isValidAuteur(String auteur) {
        return auteur != null && !auteur.trim().isEmpty();
    }

    // New method to get valid input from the user
    public static String getValidInput(String prompt, InputValidatorFunction<String> validator) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (validator.isValid(input)) {
                break;
            } else {
                System.out.println("Entrée invalide. Veuillez réessayer.");
            }
        }
        return input;
    }

    public static int getValidAge(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int age;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (isValidAge(age)) {
                    break;
                } else {
                    System.out.println("Âge invalide. Veuillez entrer un âge entre 1 et 149.");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                scanner.next(); // Clear the invalid input
            }
        }
        return age;
    }

    public static String getValidEmail(String prompt) {
        return getValidInput(prompt, InputValidator::isValidEmail);
    }

    public static String getValidTitre(String prompt) {
        return getValidInput(prompt, InputValidator::isValidTitre);
    }

    public static String getValidAuteur(String prompt) {
        return getValidInput(prompt, InputValidator::isValidAuteur);
    }

    public static String getValidCNE(String prompt) {
        return getValidInput(prompt, InputValidator::isValidCNE);
    }

    public static String getValidCIN(String prompt) {
        return getValidInput(prompt, InputValidator::isValidCIN);
    }

    // Functional interface for validation
    @FunctionalInterface
    public interface InputValidatorFunction<T> {
        boolean isValid(T input);
    }
}
