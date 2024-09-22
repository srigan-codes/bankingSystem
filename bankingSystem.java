import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }
}

public class bankingSystem {
    private static HashMap<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over from nextInt()

            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using the banking system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account with this number already exists.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, initialDeposit);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully!");
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Current Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
