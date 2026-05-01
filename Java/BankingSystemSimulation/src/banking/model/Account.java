package banking.model;


public abstract class Account {
    protected int id;
    protected String accountNumber;
    protected double balance;
    protected int userId;

    public Account(int id, String accountNumber, double balance, int userId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.userId = userId;
    }

    public abstract String getAccountType();

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return getAccountType() + " Account - " + accountNumber;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public int getUserId() { return userId; }
    public void setBalance(double balance) { this.balance = balance; }
}