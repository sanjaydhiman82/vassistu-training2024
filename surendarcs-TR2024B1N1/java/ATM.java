import java.util.Scanner;

// Class to represent User
class User {
    private String userId;
    private String password;
    
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    
    // Method to authenticate user
    public boolean authenticate(String userId, String password) {
        return this.userId.equals(userId) && this.password.equals(password);
    }
}

// Class to represent ATM Transactions
class Transaction {
    private double balance;
    private StringBuilder transactionHistory;

    public Transaction() {
        this.balance = 0.0;
        this.transactionHistory = new StringBuilder();
    }

    // Deposit money to account (negative amounts allowed)
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.append(amount >= 0 ? "Deposited: " : "Deposited (Negative): ");
        transactionHistory.append(amount).append("\n");
    }

    // Withdraw money from account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdrew: ").append(amount).append("\n");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    // Show balance
    public double getBalance() {
        return balance;
    }

    // Show transaction history for the current session
    public String getTransactionHistory() {
        return transactionHistory.toString();
    }
}

// ATM class that contains the main logic for ATM system
public class ATM {
    private static Scanner scanner = new Scanner(System.in);
    private static User user = new User("admin", "admin");
    private static Transaction transaction = new Transaction();

    // Method to show the menu and process the user input
    public static void showMenu() {
        while (true) {
            System.out.println("\n--- ATM System ---");
            System.out.println("1. Withdrawal");
            System.out.println("2. Deposit");
            System.out.println("3. Balance Statement");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: 
                    handleWithdrawal();
                    break;
                case 2: 
                    handleDeposit();
                    break;
                case 3: 
                    showBalanceStatement();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM system!");
                    return;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    // Method to handle withdrawal
    public static void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        transaction.withdraw(amount);
    }

    // Method to handle deposit
    public static void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        transaction.deposit(amount);
    }

    // Method to show balance statement (transaction history for the session)
    public static void showBalanceStatement() {
        System.out.println("\nBalance: " + transaction.getBalance());
        System.out.println("Transaction History: ");
        System.out.println(transaction.getTransactionHistory());
    }

    // Main method to handle login and start the ATM system
    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter user ID: ");
        String userId = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (user.authenticate(userId, password)) {
            System.out.println("Login successful!");
            showMenu();
        } else {
            System.out.println("Invalid credentials! Exiting...");
        }
    }
}
