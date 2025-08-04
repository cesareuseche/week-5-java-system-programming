package com.yourname.banking;

import com.yourname.banking.controllers.BankingController;

/**
 * Main Class - Application Entry Point
 *
 * This class serves as the entry point for the Secure Banking System demonstration.
 * It showcases the complete banking system functionality while demonstrating
 * the proper implementation and usage of Java access modifiers in a real-world scenario.
 *
 * Application Features Demonstrated:
 * - Account creation and management
 * - Secure deposit and withdrawal operations
 * - Access control implementation across different packages
 * - Error handling and business rule enforcement
 * - Interactive and automated banking operations
 *
 * Access Control Demonstration:
 * - Private fields in Account class (accountNumber, balance, ownerName)
 * - Protected methods in Account class (updateBalance) and AccountService (deposit, withdraw)
 * - Public methods for legitimate external access (getters, createAccount, manageAccount)
 * - Default access utility methods (generateAccountNumber)
 * - Package structure separation with controlled access across subpackages
 *
 */
public class Main {

    /**
     * Main method - Application entry point
     *
     * This method demonstrates the complete banking system functionality
     * including account creation, deposits, withdrawals, and balance inquiries.
     * It shows how the access control system works in practice and provides
     * a comprehensive example of secure banking operations.
     *
     * The demonstration includes:
     * 1. Automated banking operations showing normal workflow
     * 2. Error scenarios to demonstrate security and validation
     * 3. Interactive mode for user input (optional)
     *
     * @param args Command line arguments (not used in this demonstration)
     */
    public static void main(String[] args) {
        // Display welcome message and system information
        displayWelcomeMessage();

        // Create the banking controller (extends AccountService for access to protected methods)
        BankingController bankingController = new BankingController();

        try {
            // Run the automated banking demonstration
            System.out.println("\n🔹 Running Automated Banking System Demonstration...");
            bankingController.manageAccount();

            // Optional: Run interactive mode if requested
            if (args.length > 0 && args[0].equals("--interactive")) {
                System.out.println("\n🔹 Starting Interactive Mode...");
                bankingController.interactiveManageAccount();
            } else {
                displayInteractiveInstructions();
            }

        } catch (Exception e) {
            System.err.println("\n❌ System Error: " + e.getMessage());
            System.err.println("The banking system encountered an unexpected error.");
            e.printStackTrace();
        } finally {
            // Clean up resources
            bankingController.cleanup();
        }

        // Display completion message
        displayCompletionMessage();
    }

    /**
     * Displays the welcome message and system overview
     */
    private static void displayWelcomeMessage() {
        System.out.println("\n" + "█".repeat(70));
        System.out.println("█" + " ".repeat(68) + "█");
        System.out.println("█" + centerText("SECURE BANKING SYSTEM", 68) + "█");
        System.out.println("█" + centerText("Access Control Demonstration", 68) + "█");
        System.out.println("█" + " ".repeat(68) + "█");
        System.out.println("█".repeat(70));

        System.out.println("\n📋 SYSTEM OVERVIEW:");
        System.out.println("   This banking system demonstrates proper implementation of");
        System.out.println("   Java access modifiers in a real-world application scenario.");
        System.out.println();
        System.out.println("🔒 ACCESS CONTROL FEATURES:");
        System.out.println("   • Private fields: Account data protection");
        System.out.println("   • Protected methods: Controlled balance operations");
        System.out.println("   • Public methods: Legitimate external access");
        System.out.println("   • Default access: Package-level utility functions");
        System.out.println();
        System.out.println("🏗️  ARCHITECTURE:");
        System.out.println("   • Models: Account class with encapsulated data");
        System.out.println("   • Services: Business logic with protected operations");
        System.out.println("   • Controllers: User interface with inheritance-based access");
        System.out.println("   • Utils: Helper functions with controlled visibility");
    }

    /**
     * Displays instructions for interactive mode
     */
    private static void displayInteractiveInstructions() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("💡 TIP: Run with '--interactive' flag for hands-on experience");
        System.out.println("   Example: java Main --interactive");
        System.out.println("─".repeat(60));
    }

    /**
     * Displays the completion message and access control summary
     */
    private static void displayCompletionMessage() {
        System.out.println("\n" + "█".repeat(70));
        System.out.println("█" + " ".repeat(68) + "█");
        System.out.println("█" + centerText("ACCESS CONTROL SUMMARY", 68) + "█");
        System.out.println("█" + " ".repeat(68) + "█");
        System.out.println("█".repeat(70));

        System.out.println("\n✅ DEMONSTRATED ACCESS MODIFIERS:");
        System.out.println();
        System.out.println("🔴 PRIVATE:");
        System.out.println("   • Account.accountNumber - Only accessible within Account class");
        System.out.println("   • Account.balance - Protected sensitive financial data");
        System.out.println("   • Account.ownerName - Encapsulated customer information");
        System.out.println();
        System.out.println("🟡 PROTECTED:");
        System.out.println("   • Account.updateBalance() - Core balance modification method");
        System.out.println("   • AccountService.deposit() - Controlled deposit operations");
        System.out.println("   • AccountService.withdraw() - Controlled withdrawal operations");
        System.out.println("   • AccountService.transfer() - Inter-account transfer operations");
        System.out.println();
        System.out.println("🟢 PUBLIC:");
        System.out.println("   • Account getters - Safe read access to account information");
        System.out.println("   • AccountService.createAccount() - Account creation interface");
        System.out.println("   • BankingController.manageAccount() - Main user interface");
        System.out.println("   • Account.processBalanceUpdate() - Controlled balance updates");
        System.out.println();
        System.out.println("🔵 DEFAULT (Package-Private):");
        System.out.println("   • Originally intended for Utility methods (modified to public for demo)");
        System.out.println("   • Demonstrates package-level access control");
        System.out.println();
        System.out.println("🛡️  SECURITY BENEFITS:");
        System.out.println("   • Prevents unauthorized direct access to sensitive data");
        System.out.println("   • Enforces business rules through controlled methods");
        System.out.println("   • Maintains data integrity through encapsulation");
        System.out.println("   • Provides clear API boundaries for system components");
        System.out.println("   • Enables safe inheritance while maintaining security");
        System.out.println();
        System.out.println("📚 EDUCATIONAL VALUE:");
        System.out.println("   • Real-world application of access control principles");
        System.out.println("   • Demonstrates security-first design approach");
        System.out.println("   • Shows proper separation of concerns");
        System.out.println("   • Illustrates inheritance-based access patterns");

        System.out.println("\n" + "█".repeat(70));
        System.out.println("█" + centerText("DEMONSTRATION COMPLETE", 68) + "█");
        System.out.println("█" + centerText("Thank you for exploring secure banking!", 68) + "█");
        System.out.println("█".repeat(70));
    }

    /**
     * Centers text within a specified width
     *
     * @param text The text to center
     * @param width The total width to center within
     * @return The centered text string
     */
    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }

        int padding = (width - text.length()) / 2;
        StringBuilder result = new StringBuilder();

        // Add left padding
        for (int i = 0; i < padding; i++) {
            result.append(" ");
        }

        // Add text
        result.append(text);

        // Add right padding to reach full width
        while (result.length() < width) {
            result.append(" ");
        }

        return result.toString();
    }

    /**
     * Alternative main method for testing specific scenarios
     *
     * This method can be used during development to test specific
     * banking scenarios or access control features.
     */
    @SuppressWarnings("unused")
    private static void testSpecificScenarios() {
        System.out.println("🧪 Running Specific Test Scenarios...");

        BankingController controller = new BankingController();

        // Test scenario 1: Error handling
        System.out.println("\n--- Testing Error Scenarios ---");
        try {
            controller.createAccount("");  // Should fail
        } catch (Exception e) {
            System.out.println("✓ Caught expected error: " + e.getMessage());
        }

        // Test scenario 2: Access control validation
        System.out.println("\n--- Testing Access Control ---");
        // Note: Direct access violations would cause compilation errors,
        // demonstrating the effectiveness of our access control system

        controller.cleanup();
        System.out.println("✅ Test scenarios completed.");
    }
}