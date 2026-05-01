package banking.model;

public class CurrentAccount extends Account {
    public CurrentAccount(int id, String accountNumber, double balance, int userId) {
        super(id, accountNumber, balance, userId);
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}