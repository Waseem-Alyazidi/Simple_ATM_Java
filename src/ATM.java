import java.io.IOException; // For clear screen 
import java.util.Scanner;   // For user input

public class ATM {
    static Scanner input = new Scanner(System.in);

    //* Clear screen *//
    static void clear_screen() {
        try {
            String os = System.getProperty("os.name").toLowerCase(); // Get OS name

            if(os.contains("win")) { // For windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            } else { // For linux-based OS such as (linux, macOC, etc..)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("\033[31mError clearing screen: " + e.getMessage() + "\033[0m");

        }
    }


    //* Get currency category  *//
    static void get_currency_category(int amount) {
        // The valid categories
        final int[] categories = {500, 100, 50, 10, 5, 2, 1};

        System.out.println("-".repeat(50));
        // Loop for each category
        for(int category : categories) {
            if(amount >= category) {
                int count = amount / category;
                amount %= category;
                System.out.printf("%-5d : %d\n", category, count);
            }
        }
        System.out.println("-".repeat(50));
    }


    //* Start the program *//
    static void start_ATM() {
        clear_screen();
        while(true) { 
            System.out.print("Enter the amount: ");
            int amount = input.nextInt();

            // Check if valid amount
            if(amount <= 0) {
                clear_screen();
                System.out.println("\033[31mInvalid amount! Please enter an amount (1-inf).\033[0m\n");
                continue;
            }
            // Call get_currency_category
            get_currency_category(amount);
            input.nextLine(); // Clear the input

            System.out.print("\nDo you want to continue (Y/N): ");
            String userConfirm = input.nextLine().trim().toLowerCase();

            switch(userConfirm) {
                case "y" -> clear_screen(); // Go back
                default -> {
                    clear_screen();
                    System.exit(0); // Exit the program
                }
            }
        }
    }


    //* Main method *//
    public static void main(String[] args) {
        final String USER_ID = "0123456789";
        final String PASSWORD = "123";
        int countInvalidPasswords = 3;

        clear_screen();
        while(true) {
            System.out.println("\033[32m=\033[0m".repeat(36));
            System.out.println("\033[32m    Welcome to Waseem ATM System    \033[0m");
            System.out.println("\033[32m=\033[0m".repeat(36) + "\n");

            if(countInvalidPasswords == 0) { // Check the chances
                clear_screen();
                return;
            }

            //* User ID
            System.out.print("Enter student number: ");
            String pass1 = input.nextLine().trim();

            //* Password
            System.out.print("Enter the password: ");
            String pass2 = input.nextLine().trim();

            // Check the passwords
            if(!pass1.equals(USER_ID) || !pass2.equals(PASSWORD)) {
                clear_screen();
                countInvalidPasswords--;
                System.out.printf("\033[31mAccess denied! you have %d chances.\033[0m\n\n", countInvalidPasswords);
                continue;
            }

            // Start the ATM
            start_ATM();
        }
    }
}