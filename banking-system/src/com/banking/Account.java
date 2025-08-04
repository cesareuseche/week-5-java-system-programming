package com.banking;

/**
 * Account Class - Core Banking Entity
 *
 * This class represents a bank account in our banking system. It encapsulates
 * the essential account information while maintaining security through proper
 * access control. The private fields ensure that sensitive data like balance
 * can only be modified through controlled methods.
 *
 * Security Design Decisions:
 * - Private fields: Prevent direct access to sensitive account data
 * - Protected updateBalance(): Allows only trusted classes in the same package/subclasses to modify balance
 * - Public getters: Allow controlled read access to necessary information
 *
 */
public class Account {
    // Private fields ensure encapsulation and data security
    // Only this class can directly access these fields
    private String accountNumber;
    private double balance;
    private String ownerName;

    /**
     * Constructor to create a new bank account
     *
     * @param accountNumber Unique identifier for this account
     * @param ownerName Name of the account holder
     */
    public Account(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }

    /**
     * Public getter for account number
     * Safe to expose as account numbers are used for identification
     *
     * @return The account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Public getter for owner name
     * Safe to expose for account identification and customer service
     *
     * @return The name of the account owner
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Protected method to update account balance
     *
     * This method is protected to ensure that only trusted classes within
     * the banking package or its subclasses can modify the balance. This
     * prevents unauthorized balance modifications while allowing necessary
     * operations through the AccountService and other banking components.
     *
     * @param amount The amount to add to the balance (can be negative for withdrawals)
     */
    protected void updateBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Public getter for current balance
     * Allows authorized parties to check account balance
     *
     * @return The current account balance
     */
    public double getBalance() {
        return balance;
    }
}