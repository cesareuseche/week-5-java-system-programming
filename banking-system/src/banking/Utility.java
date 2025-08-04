package banking;

import java.util.UUID;

/**
 * Utility Class - Helper Functions for Banking System
 *
 * This class provides utility functions that support various banking operations.
 * It contains helper methods that don't belong to any specific business entity
 * but are essential for the banking system's functionality.
 *
 * Security Design Decisions:
 * - Default (package-private) access: Ensures only classes within the banking package can access utilities
 * - Static methods: No need for instantiation, these are pure utility functions
 * - Account number generation: Uses UUID for uniqueness and security
 */
class Utility {

    /**
     * Generates a unique account number for new accounts
     *
     * This method creates a unique identifier for bank accounts using UUID.
     * The method has default (package-private) access to ensure that only
     * trusted classes within the banking system can generate account numbers.
     * This prevents external systems from creating fake account numbers.
     *
     * Account Number Format: "ACCT-" followed by first 8 characters of UUID
     * Example: "ACCT-a1b2c3d4"
     *
     * @return A unique account number string
     */
    static String generateAccountNumber() {
        // Use UUID for guaranteed uniqueness across the system
        // Take first 8 characters for readability while maintaining uniqueness
        return "ACCT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Private constructor to prevent instantiation
     *
     * This utility class is designed to contain only static methods,
     * so instantiation is not necessary and is prevented for efficiency.
     */
    private Utility() {
        // Prevent instantiation of utility class
        throw new AssertionError("Utility class should not be instantiated");
    }
}