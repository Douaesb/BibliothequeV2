package utilitaire;

import java.util.regex.Pattern;

public class InputValidator {

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
}
