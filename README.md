# 🏦 Secure Banking System - Java Access Control Demonstration

## 📋 Project Overview

This project demonstrates the practical implementation of Java access modifiers in a real-world banking system scenario. It showcases how proper access control ensures data security, enforces business rules, and maintains system integrity in financial applications.

## 🎯 Learning Objectives

- **Understand Java Access Modifiers**: Learn how `private`, `protected`, `public`, and default access work in practice
- **Apply Security Principles**: See how access control prevents unauthorized data manipulation
- **Design Secure Systems**: Understand how to structure code for both functionality and security
- **Implement Business Logic**: Learn how to enforce banking rules through controlled access

## 🏗️ System Architecture

### Package Structure
```
com.yourname.banking/
├── models/
│   └── Account.java              # Core data model with encapsulated fields
├── services/
│   └── AccountService.java       # Business logic with controlled operations
├── controllers/
│   └── BankingController.java    # User interface with inheritance-based access
├── utils/
│   └── Utility.java              # Helper functions with controlled visibility
└── Main.java                     # Application entry point and demonstration
```

### Class Diagram
```
┌─────────────────────┐
│      Account        │
├─────────────────────┤
│ - accountNumber     │ ← Private: Only accessible within Account class
│ - balance           │ ← Private: Protects sensitive financial data
│ - ownerName         │ ← Private: Encapsulates customer information
├─────────────────────┤
│ + getAccountNumber()│ ← Public: Safe read access
│ + getOwnerName()    │ ← Public: Safe read access
│ + getBalance()      │ ← Public: Controlled balance access
│ # updateBalance()   │ ← Protected: Core balance modification
│ + processBalance... │ ← Public: Controlled service access
└─────────────────────┘
           ▲
           │ uses
┌─────────────────────┐
│   AccountService    │
├─────────────────────┤
│ + createAccount()   │ ← Public: Account creation interface
│ # deposit()         │ ← Protected: Controlled deposit operations
│ # withdraw()        │ ← Protected: Controlled withdrawal operations
│ # transfer()        │ ← Protected: Inter-account transfers
│ + getAccountBalance()│ ← Public: Safe balance inquiry
└─────────────────────┘
           ▲
           │ extends
┌─────────────────────┐
│  BankingController  │
├─────────────────────┤
│ + manageAccount()   │ ← Public: Main user interface
│ + interactive...()  │ ← Public: Interactive banking
│ - displayMenu()     │ ← Private: Internal UI methods
└─────────────────────┘

┌─────────────────────┐
│      Utility        │
├─────────────────────┤
│ + generateAccount...│ ← Public: Account number generation
│ + isValidAccount... │ ← Public: Validation helper
│ + generateTxnId()   │ ← Public: Transaction ID helper
│ - Utility()         │ ← Private: Prevents instantiation
└─────────────────────┘
```

## 🔒 Access Control Implementation

### 🔴 Private Access Modifiers

**Fields in Account Class:**
- `accountNumber`: Prevents direct modification of account identifiers
- `balance`: Protects sensitive financial data from unauthorized access
- `ownerName`: Encapsulates customer personal information

**Security Benefits:**
- Complete encapsulation of sensitive data
- Prevention of accidental data corruption
- Enforcement of controlled access patterns
- Protection against malicious manipulation

### 🟡 Protected Access Modifiers

**Methods in Account Class:**
- `updateBalance(double amount)`: Core balance modification method

**Methods in AccountService Class:**
- `deposit(Account, double)`: Controlled deposit operations
- `withdraw(Account, double)`: Controlled withdrawal operations
- `transfer(Account, Account, double)`: Inter-account transfer operations

**Security Benefits:**
- Allows inheritance-based access while maintaining control
- Enables BankingController to access banking operations through inheritance
- Prevents external classes from directly manipulating financial data
- Maintains business rule enforcement at the service layer

### 🟢 Public Access Modifiers

**Methods in Account Class:**
- `getAccountNumber()`: Safe read access to account identifier
- `getOwnerName()`: Safe read access to customer name
- `getBalance()`: Controlled read access to current balance
- `processBalanceUpdate()`: Controlled balance modification gateway

**Methods in AccountService Class:**
- `createAccount(String)`: Account creation interface
- `getAccountBalance(Account)`: Safe balance inquiry method

**Methods in BankingController Class:**
- `manageAccount()`: Main banking interface
- `interactiveManageAccount()`: Interactive user interface

**Security Benefits:**
- Provides necessary external access points
- Maintains clear API boundaries
- Enables legitimate banking operations
- Supports user interface requirements

### 🔵 Default (Package-Private) Access

**Original Design:**
- `Utility.generateAccountNumber()`: Originally intended as package-private

**Note:** Modified to public in this implementation to demonstrate cross-package access patterns while maintaining controlled access to utility functions.

## 🛡️ Security Design Decisions

### 1. **Data Encapsulation**
- All sensitive fields (`balance`, `accountNumber`, `ownerName`) are private
- Only controlled access through public getter methods
- No direct setters for critical financial data

### 2. **Business Logic Protection**
- Deposit/withdrawal operations are protected methods
- Only accessible through inheritance or controlled interfaces
- Prevents unauthorized external manipulation

### 3. **Inheritance-Based Access**
- `BankingController` extends `AccountService` to access protected methods
- Demonstrates how inheritance can provide controlled access
- Maintains security while enabling necessary functionality

### 4. **Validation and Error Handling**
- All operations include input validation
- Business rules enforced at multiple levels
- Clear error messages without exposing system internals

### 5. **Audit Trail**
- All balance modifications are logged
- Transaction details provided for transparency
- Security violations are detected and reported

## 🚀 Running the Application

### Prerequisites
- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Compilation and Execution

1. **Navigate to the source directory:**
   ```bash
   cd src
   ```

2. **Compile the project:**
   ```bash
   javac com/yourname/banking/Main.java
   ```

3. **Run the automated demonstration:**
   ```bash
   java com.yourname.banking.Main
   ```

4. **Run with interactive mode:**
   ```bash
   java com.yourname.banking.Main --interactive
   ```

### Expected Output

The application will demonstrate:
- Account creation with auto-generated account numbers
- Successful deposit operations with validation
- Successful withdrawal operations with balance checking
- Transfer operations between accounts
- Error handling for invalid operations (negative amounts, insufficient funds)
- Detailed transaction reporting and account status

## 📚 Educational Value

### Key Concepts Demonstrated

1. **Encapsulation**: Private fields with controlled access
2. **Inheritance**: Protected method access through class hierarchy
3. **Polymorphism**: Method overriding and interface implementation
4. **Security**: Access control as a security mechanism
5. **Business Logic**: Rule enforcement through controlled access

### Real-World Applications

This pattern is commonly used in:
- Financial systems and banking applications
- Healthcare systems with patient data
- E-commerce platforms with payment processing
- Enterprise systems with sensitive business data
- Security-critical applications requiring audit trails

## 🧪 Testing the System

### Access Control Validation

The system prevents these unauthorized operations:
```java
// ❌ Compilation errors - demonstrates access control effectiveness
Account account = new Account("123", "John");
account.balance = 1000.0;           // Error: balance is private
account.updateBalance(100.0);       // Error: updateBalance is protected
```

### Business Rule Validation

The system enforces these business rules:
- Only positive amounts can be deposited
- Withdrawals cannot exceed account balance
- Account names cannot be empty
- All balance modifications are logged
- Only authorized classes can modify balances

## 🔍 Advanced Features

### Transaction Security
- Balance updates include caller class validation
- Security exceptions thrown for unauthorized access attempts
- Audit logging for all financial operations

### Error Handling
- Comprehensive input validation
- User-friendly error messages
- Graceful handling of edge cases

### Interactive Mode
- Menu-driven user interface
- Real-time input validation
- Step-by-step banking operations
