import java.util.Scanner;

public class PasswordValidator {

    
    public static boolean checkPassword(String pwd) {

        boolean foundUpper = false;
        boolean foundNumber = false;

       
        if (pwd.length() < 8) {
            System.out.println("Password is too short! It must be at least 8 characters.");
            return false;
        }

        
        for (int i = 0; i < pwd.length(); i++) {
            char ch = pwd.charAt(i);

            // Check for uppercase letter
            if (Character.isUpperCase(ch)) {
                foundUpper = true;
            }

            
            if (Character.isDigit(ch)) {
                foundNumber = true;
            }
        }

        
        if (!foundUpper) {
            System.out.println("Password must include at least one uppercase letter.");
        }

        if (!foundNumber) {
            System.out.println("Password must include at least one number.");
        }

        
        return foundUpper && foundNumber;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String userPassword;

        
        while (true) {
            System.out.print("Enter your password: ");
            userPassword = input.nextLine();

            if (checkPassword(userPassword)) {
                System.out.println("Password created successfully!");
                break;
            } else {
                System.out.println("Please try again.\n");
            }
        }

        input.close();
    }
}