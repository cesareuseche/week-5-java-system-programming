package com.yourname.banking.controllers;

import com.yourname.banking.models.Account;
import com.yourname.banking.services.AccountService;
import java.util.Scanner;

/**
 * BankingController Class - Presentation/Control Layer
 *
 * This class serves as the main interface between users and the banking system.
 * It handles user interactions, coordinates banking operations, and demonstrates
 * the proper use of access control throughout the banking system hierarchy.
 *
 * Security Design Decisions:
 * - Public manageAccount(): Provides the main entry point for banking operations
 * - Extends AccountService: Inherits protected deposit/withdraw methods for secure operations
 * - Input validation: Ensures all user inputs are validated before processing
 * - Controlled access: Only exposes necessary functionality to external users
 * - Error handling: Provides user-friendly error messages without exposing system internals
 */
public class BankingController extends AccountService {

    private Scanner scanner;

    /**
     * Constructor for BankingController
     *
     * Initializes the controller with necessary resources for user interaction.
     * The scanner is used for interactive input when running in interactive mode.
     */
    public BankingController() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main account management method - Public Interface
     *
     * This public method serves as the primary interface for account management
     * operations. It demonstrates the complete banking workflow including:
     * - Account creation using inherited public methods
     * - Deposit operations using inherited protected methods (accessible due to inheritance)
     * - Withdrawal operations using inherited protected methods
     * - Balance inquiries and account information display
     *
     * The method is public because it represents the main entry point that
     * external systems or users would interact with to perform banking operations.
     * This demonstrates how inheritance allows the controller to access protected
     * methods from the service layer while maintaining security boundaries.
     */
    public void manageAccount() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         SECURE BANKING SYSTEM DEMONSTRATION");
        System.out.println("=".repeat(60));

        try {
            // Demonstrate account creation (public method)
            System.out.println("\n--- ACCOUNT CREATION ---");
            Account aliceAccount = createAccount("Alice Johnson");
            Account bobAccount = createAccount("Bob Smith");

            // Demonstrate deposit operations (protected method accessible due to inheritance)
            System.out.println("\n--- DEPOSIT OPERATIONS ---");
            deposit(aliceAccount, 1000.00);
            deposit(bobAccount, 750.50);

            // Demonstrate invalid deposit (negative amount)
            System.out.println("\n--- INVALID DEPOSIT DEMONSTRATION ---");
            deposit(aliceAccount, -100.00);  // This should fail with appropriate error message

            // Demonstrate withdrawal operations (protected method accessible due to inheritance)
            System.out.println("\n--- WITHDRAWAL OPERATIONS ---");
            withdraw(aliceAccount, 250.00);
            withdraw(bobAccount, 100.00);

            // Demonstrate insufficient funds scenario
            System.out.println("\n--- INSUFFICIENT FUNDS DEMONSTRATION ---");
            withdraw(bobAccount, 1000.00);  // This should fail due to insufficient funds

            // Demonstrate account transfer functionality
            System.out.println("\n--- TRANSFER OPERATIONS ---");
            transfer(aliceAccount, bobAccount, 200.00);

            // Display final account status
            System.out.println("\n--- FINAL ACCOUNT STATUS ---");
            displayAccountStatus(aliceAccount);
            displayAccountStatus(bobAccount);

            // Demonstrate additional banking operations
            System.out.println("\n--- ADDITIONAL OPERATIONS ---");
            performBalanceInquiry(aliceAccount);
            performBalanceInquiry(bobAccount);

        } catch (Exception e) {
            System.err.println("An error occurred during banking operations: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("         BANKING SYSTEM DEMONSTRATION COMPLETE");
        System.out.println("=".repeat(60));
    }

    /**
     * Interactive account management method
     *
     * This method provides an interactive interface for users to perform
     * banking operations through console input. It demonstrates real-time
     * user interaction with the banking system.
     */
    public void interactiveManageAccount() {
        System.out.println("\n--- INTERACTIVE BANKING SYSTEM ---");
        System.out.println("Welcome to the Secure Banking System!");

        Account currentAccount = null;

        while (true) {
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        currentAccount = createNewAccountInteractive();
                        break;
                    case 2:
                        if (currentAccount != null) {
                            performDepositInteractive(currentAccount);
                        } else {
                            System.out.println("Please create an account first.");
                        }
                        break;
                    case 3:
                        if (currentAccount != null) {
                            performWithdrawalInteractive(currentAccount);
                        } else {
                            System.out.println("Please create an account first.");
                        }
                        break;
                    case 4:
                        if (currentAccount != null) {
                            displayAccountStatus(currentAccount);
                        } else {
                            System.out.println("Please create an account first.");
                        }
                        break;
                    case 5:
                        System.out.println("Thank you for using the Secure Banking System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Displays the main menu for interactive banking
     */
    private void displayMenu() {
        System.out.println("\n--- BANKING MENU ---");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Creates a new account interactively
     */
    private Account createNewAccountInteractive() {
        System.out.print("Enter account owner name: ");
        String ownerName = scanner.nextLine().trim();

        if (ownerName.isEmpty()) {
            System.out.println("Owner name cannot be empty.");
            return null;
        }

        return createAccount(ownerName);
    }

    /**
     * Performs an interactive deposit operation
     */
    private void performDepositInteractive(Account account) {
        System.out.print("Enter deposit amount: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            deposit(account, amount);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        }
    }

    /**
     * Performs an interactive withdrawal operation
     */
    private void performWithdrawalInteractive(Account account) {
        System.out.print("Enter withdrawal amount: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            withdraw(account, amount);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        }
    }

    /**
     * Displays detailed account status information
     *
     * This method provides a comprehensive view of account information
     * using only public getter methods to demonstrate proper encapsulation.
     *
     * @param account The account to display information for
     */
    private void displayAccountStatus(Account account) {
        if (account == null) {
            System.out.println("Account is null - cannot display status");
            return;
        }

        System.out.println("\n--- ACCOUNT STATUS ---");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder: " + account.getOwnerName());
        System.out.println("Current Balance: $" + String.format("%.2f", account.getBalance()));
        System.out.println("Account Status: Active");
        System.out.println("----------------------");
    }

    /**
     * Performs a balance inquiry operation
     *
     * This method demonstrates how to safely access account balance
     * information using the service layer's public methods.
     *
     * @param account The account to check balance for
     */
    private void performBalanceInquiry(Account account) {
        double balance = getAccountBalance(account);
        if (balance >= 0) {
            System.out.println("Balance inquiry for " + account.getAccountNumber() +
                             ": $" + String.format("%.2f", balance));
        }
    }

    /**
     * Cleanup method to close resources
     *
     * This method should be called when the controller is no longer needed
     * to properly clean up system resources.
     */
    public void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Demonstrates access control violations
     *
     * This method shows what happens when unauthorized access is attempted.
     * It's commented out to prevent compilation errors, but demonstrates
     * the security features of our access control system.
     */
    private void demonstrateAccessControlViolations() {
        /*
         * The following code would cause compilation errors due to access control:
         *
         * Account account = createAccount("Test User");
         *
         * // This would fail - balance is private
         * account.balance = 1000.0;
         *
         * // This would fail - updateBalance is protected and we're not in same package
         * account.updateBalance(100.0);
         *
         * // This would fail - Utility.generateAccountNumber() has default access
         * // (if we hadn't made it public for cross-package access)
         * String accountNum = Utility.generateAccountNumber();
         */

        System.out.println("Access control demonstrations are commented out to prevent compilation errors.");
        System.out.println("These demonstrate how our security system prevents unauthorized access.");
    }
}