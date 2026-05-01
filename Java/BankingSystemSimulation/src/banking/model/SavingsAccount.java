package banking.model;

public class SavingsAccount extends Account {
    public SavingsAccount(int id, String accountNumber, double balance, int userId) {
        super(id, accountNumber, balance, userId);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}