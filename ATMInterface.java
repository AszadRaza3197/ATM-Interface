import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private double balance;
    private ArrayList<String> transactionHistory = new ArrayList<>();

    // Constructor to initialize balance
    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit amount into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid amount! Please enter a positive number.");
        }
    }

    // Withdraw amount from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid amount! Please enter a positive number.");
        }
    }

    // Transfer amount to another ATM account
    public void transfer(double amount, ATM targetAccount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            targetAccount.deposit(amount); // Transfer to target account
            transactionHistory.add("Transferred: $" + amount + " to another account");
            System.out.println("Successfully transferred $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid amount! Please enter a positive number.");
        }
    }

    // View balance
    public double getBalance() {
        return balance;
    }

    // Display transaction history
    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

public class ATMInterface {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        ATM userATM = new ATM(1000); // User starts with an initial balance of $1000
        ATM secondAccount = new ATM(500); // Another account for transfer
        boolean authenticated = login();

        if (authenticated) {
            int choice;
            do {
                displayMenu();
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        userATM.displayTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        userATM.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        userATM.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = sc.nextDouble();
                        userATM.transfer(transferAmount, secondAccount);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    // Display ATM menu
    public static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Choose an option: ");
    }

    // Simulate user login with a static user ID and PIN
    public static boolean login() {
        String userId = "user123";
        String userPin = "1234";

        System.out.println("Welcome to the ATM");
        System.out.print("Enter User ID: ");
        String enteredId = sc.next();
        System.out.print("Enter PIN: ");
        String enteredPin = sc.next();

        return enteredId.equals(userId) && enteredPin.equals(userPin);
    }
}
