package com.yourname.banking.utils;

import java.util.Random;

/**
 * Utility Class - Helper Functions for Banking System
 *
 * This class provides utility functions that support various banking operations.
 * It contains helper methods that don't belong to any specific business entity
 * but are essential for the banking system's functionality.
 *
 * Security Design Decisions:
 * - Package-private class with public static methods: Allows controlled access across banking subpackages
 * - Static methods: No need for instantiation, these are pure utility functions
 * - Account number generation: Uses systematic approach for uniqueness and readability
 * - No public constructor: Prevents instantiation of utility class
 *
 * @author Banking System Development Team
 * @version 1.0
 */
public class Utility {

    // Static counter for generating sequential account numbers
    private static int accountCounter = 1000;

    /**
     * Generates a unique account number for new accounts
     *
     * This method creates a unique identifier for bank accounts using a combination
     * of a prefix and an incremental counter. The method has public access to allow
     * banking service classes to generate account numbers while still maintaining
     * controlled access within the banking system.
     *
     * Account Number Format: "ACC" + 6-digit sequential number
     * Example: "ACC001000", "ACC001001", "ACC001002"
     *
     * The sequential approach ensures:
     * - Uniqueness across all accounts
     * - Readability for customer service
     * - Predictable format for system processing
     * - Easy validation and verification
     *
     * @return A unique account number string
     */
         public static String generateAccountNumber() {
        // Increment counter and format with leading zeros for consistency
        accountCounter++;
        return String.format("ACC%06d", accountCounter);
    }

    /**
     * Validates if an account number follows the expected format
     *
     * This helper method checks if a given string matches our account number
     * format. Useful for input validation and data integrity checks.
     *
     * @param accountNumber The account number to validate
     * @return true if the account number format is valid, false otherwise
     */
         public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() != 9) {
            return false;
        }

        // Check if it starts with "ACC" and remaining characters are digits
        return accountNumber.startsWith("ACC") &&
               accountNumber.substring(3).matches("\\d{6}");
    }

    /**
     * Generates a random transaction ID for banking operations
     *
     * Creates unique identifiers for transactions to help with tracking
     * and audit trails. This method demonstrates additional utility
     * functionality that might be needed in a banking system.
     *
     * @return A random transaction ID
     */
         public static String generateTransactionId() {
        Random random = new Random();
        return "TXN" + System.currentTimeMillis() + String.format("%04d", random.nextInt(10000));
    }

    /**
     * Private constructor to prevent instantiation
     *
     * This utility class is designed to contain only static methods,
     * so instantiation is not necessary and is prevented for efficiency
     * and to enforce the utility class pattern.
     */
    private Utility() {
        // Prevent instantiation of utility class
        throw new AssertionError("Utility class should not be instantiated");
    }
}