package banking.service;

import banking.dao.AccountDAO;
import banking.dao.TransactionDAO;
import banking.dao.UserDAO;
import banking.model.Account;
import banking.model.Transaction;
import banking.model.User;

import java.util.List;

public class BankService {

    private final UserDAO userDAO = new UserDAO();
    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    // Login
    public User login(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    // Register
    public boolean registerUser(String username, String password) {
        return userDAO.registerUser(username, password);
    }

    // Account Operations
    public Account createAccount(int userId, String accountType) {
        return accountDAO.createAccount(userId, accountType);
    }

    public List<Account> getUserAccounts(int userId) {
        return accountDAO.getAccountsByUser(userId);
    }

    // Deposit & Withdraw
    public boolean deposit(int accountId, double amount) {
        return accountDAO.updateBalanceAfterTransaction(accountId, amount, true);
    }

    public boolean withdraw(int accountId, double amount) {
        return accountDAO.updateBalanceAfterTransaction(accountId, amount, false);
    }

    // Transaction
    public void addTransaction(int accountId, String type, double amount, String description) {
        transactionDAO.addTransaction(accountId, type, amount, description);
    }

    public List<Transaction> getTransactionHistory(int accountId) {
        return transactionDAO.getTransactionsByAccount(accountId);
    }
}