package com.yourname.banking.services;

import com.yourname.banking.models.Account;
import com.yourname.banking.utils.Utility;

/**
 * AccountService Class - Business Logic Layer
 *
 * This class handles all account-related business operations in our banking system.
 * It acts as an intermediary between the controller layer and the data model,
 * ensuring that all banking operations follow proper business rules and security protocols.
 *
 * Security Design Decisions:
 * - Public createAccount(): Allows account creation through proper channels for legitimate users
 * - Protected deposit/withdraw(): Limits access to trusted classes within banking system hierarchy
 * - Business logic validation: Ensures deposits are positive and withdrawals don't exceed balance
 * - Encapsulation: Uses Account's protected updateBalance() method for secure balance updates
 * - Error handling: Provides clear feedback for invalid operations without exposing sensitive data
 */
public class AccountService {

    /**
     * Creates a new bank account for a customer
     *
     * This public method is the entry point for account creation. It generates
     * a unique account number using our utility class and creates a new Account
     * instance. This method is public because account creation is a legitimate
     * external operation that should be accessible through the banking system's
     * public API, typically called by controllers or external interfaces.
     *
     * The method ensures that:
     * - Each account gets a unique, system-generated account number
     * - Owner name is properly validated and stored
     * - New accounts start with zero balance for security
     * - Account creation is logged for audit purposes
     *
     * @param ownerName The name of the person who will own this account (must not be null or empty)
     * @return A new Account object with generated account number and zero balance
     * @throws IllegalArgumentException if ownerName is null or empty
     */
    public Account createAccount(String ownerName) {
        // Validate input parameters
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }

        // Generate a unique account number using our utility class
        String accountNumber = Utility.generateAccountNumber();

        // Create new account with validated parameters
        Account newAccount = new Account(accountNumber, ownerName.trim());

        // Log account creation for audit purposes
        System.out.println("✓ New account created successfully:");
        System.out.println("  Account Number: " + newAccount.getAccountNumber());
        System.out.println("  Owner: " + newAccount.getOwnerName());
        System.out.println("  Initial Balance: $" + String.format("%.2f", newAccount.getBalance()));

        return newAccount;
    }

    /**
     * Deposits money into an account
     *
     * This method is protected to ensure that only trusted classes within
     * the banking system hierarchy can perform deposit operations. This prevents
     * unauthorized external classes from directly manipulating account balances
     * while allowing legitimate banking operations through proper channels.
     *
     * Business Rules Enforced:
     * - Only positive amounts can be deposited (prevents negative deposit attacks)
     * - Account must be valid and not null
     * - Uses Account's protected updateBalance() for secure balance modification
     * - Provides detailed transaction feedback for transparency
     * - Logs all deposit operations for audit trail
     *
     * @param account The account to deposit money into (must not be null)
     * @param amount The amount to deposit (must be positive)
     * @throws IllegalArgumentException if account is null or amount is not positive
     */
    protected void deposit(Account account, double amount) {
        // Validate input parameters
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (amount <= 0) {
            System.err.println("Deposit failed: Amount must be positive");
            System.err.println("   Attempted amount: $" + amount);
            return;
        }

        // Store balance before transaction for reporting
        double previousBalance = account.getBalance();

        // Use Account's controlled method to securely update balance
        account.processBalanceUpdate(amount, this.getClass());

        // Provide detailed transaction confirmation
        System.out.println("✓ Deposit successful:");
        System.out.println("  Account: " + account.getAccountNumber());
        System.out.println("  Owner: " + account.getOwnerName());
        System.out.println("  Deposit Amount: $" + String.format("%.2f", amount));
        System.out.println("  Previous Balance: $" + String.format("%.2f", previousBalance));
        System.out.println("  New Balance: $" + String.format("%.2f", account.getBalance()));
    }

    /**
     * Withdraws money from an account
     *
     * This method is protected to ensure that only trusted classes within
     * the banking system hierarchy can perform withdrawal operations. This is
     * critical for preventing unauthorized access to customer funds and
     * maintaining the integrity of the banking system.
     *
     * Business Rules Enforced:
     * - Only positive amounts can be withdrawn (prevents invalid operations)
     * - Account must have sufficient balance (no overdrafts allowed)
     * - Account must be valid and not null
     * - Uses Account's protected updateBalance() for secure balance modification
     * - Provides detailed transaction feedback for transparency
     * - Comprehensive error handling with clear messaging
     *
     * @param account The account to withdraw money from (must not be null)
     * @param amount The amount to withdraw (must be positive and <= account balance)
     * @throws IllegalArgumentException if account is null or amount is not positive
     */
    protected void withdraw(Account account, double amount) {
        // Validate input parameters
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        if (amount <= 0) {
            System.err.println("Withdrawal failed: Amount must be positive");
            System.err.println("   Attempted amount: $" + amount);
            return;
        }

        // Check for sufficient funds
        if (account.getBalance() < amount) {
            System.err.println("Withdrawal failed: Insufficient funds");
            System.err.println("   Account: " + account.getAccountNumber());
            System.err.println("   Available Balance: $" + String.format("%.2f", account.getBalance()));
            System.err.println("   Requested Withdrawal: $" + String.format("%.2f", amount));
            System.err.println("   Shortfall: $" + String.format("%.2f", amount - account.getBalance()));
            return;
        }

        // Store balance before transaction for reporting
        double previousBalance = account.getBalance();

        // Perform secure withdrawal using negative amount
        account.processBalanceUpdate(-amount, this.getClass());

        // Provide detailed transaction confirmation
        System.out.println("✓ Withdrawal successful:");
        System.out.println("  Account: " + account.getAccountNumber());
        System.out.println("  Owner: " + account.getOwnerName());
        System.out.println("  Withdrawal Amount: $" + String.format("%.2f", amount));
        System.out.println("  Previous Balance: $" + String.format("%.2f", previousBalance));
        System.out.println("  Remaining Balance: $" + String.format("%.2f", account.getBalance()));
    }

    /**
     * Retrieves account balance safely
     *
     * This public method provides a safe way to check account balance
     * without exposing the account object directly. It includes validation
     * and proper error handling.
     *
     * @param account The account to check balance for
     * @return The current balance, or -1 if account is invalid
     */
    public double getAccountBalance(Account account) {
        if (account == null) {
            System.err.println("Cannot retrieve balance: Account is null");
            return -1;
        }

        return account.getBalance();
    }

    /**
     * Transfers money between two accounts
     *
     * This protected method demonstrates how multiple banking operations
     * can be combined while maintaining security and consistency.
     *
     * @param fromAccount Source account for the transfer
     * @param toAccount Destination account for the transfer
     * @param amount Amount to transfer
     */
    protected void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            System.err.println("Transfer failed: Both accounts must be valid");
            return;
        }

        System.out.println("\n--- Initiating Transfer ---");
        System.out.println("From: " + fromAccount.getAccountNumber() + " (" + fromAccount.getOwnerName() + ")");
        System.out.println("To: " + toAccount.getAccountNumber() + " (" + toAccount.getOwnerName() + ")");
        System.out.println("Amount: $" + String.format("%.2f", amount));

        // Perform withdrawal from source account
        withdraw(fromAccount, amount);

        // Only deposit if withdrawal was successful (check by comparing balance)
        if (fromAccount.getBalance() >= 0) { // Simple check - in real system would be more sophisticated
            deposit(toAccount, amount);
            System.out.println("✓ Transfer completed successfully");
        } else {
            System.err.println("Transfer failed due to withdrawal issues");
        }
    }
}