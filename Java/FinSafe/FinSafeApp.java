import java.util.*;

class InSufficientFundsException extends Exception {
    public InSufficientFundsException(String message) {
        super(message);
    }
}

class Account {
    private String accountHolder;
    private double balance;
    private double initialBalance;
    private ArrayList<Double> transactionHistory;

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.initialBalance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive!");
        }
        balance += amount;
        addTransaction(amount);
        System.out.println("Deposit successful!");
    }

    public void processTransaction(double amount) throws InSufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive!");
        }

        if (amount > balance) {
            throw new InSufficientFundsException("Insufficient balance!");
        }

        balance -= amount;
        addTransaction(-amount);
        System.out.println("Withdrawal successful!");
    }

    private void addTransaction(double amount) {
        if (transactionHistory.size() == 5) {
            transactionHistory.remove(0);
        }
        transactionHistory.add(amount);
    }

    public void printMiniStatement() {
        System.out.println("\n--- Mini Statement ---");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Initial Balance: " + initialBalance);

        for (double t : transactionHistory) {
            if (t > 0)
                System.out.println("Deposited: " + t);
            else
                System.out.println("Withdrawn: " + (-t));
        }

        System.out.println("Current Balance: " + balance);
    }
}

public class FinSafeApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        Account acc = new Account(name, balance);

        int choice;

        do {
            System.out.println("\n1.Deposit  2.Withdraw  3.Statement  4.Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Amount: ");
                        acc.deposit(sc.nextDouble());
                        break;

                    case 2:
                        System.out.print("Amount: ");
                        acc.processTransaction(sc.nextDouble());
                        break;

                    case 3:
                        acc.printMiniStatement();
                        break;

                    case 4:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            } catch (IllegalArgumentException | InSufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 4);

        sc.close();
    }
}