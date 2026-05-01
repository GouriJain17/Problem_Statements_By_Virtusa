package banking.dao;

import banking.model.Account;
import banking.model.SavingsAccount;
import banking.model.CurrentAccount;
import banking.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public Account createAccount(int userId, String accountType) {
        String accountNumber = "ACC" + System.currentTimeMillis();
        String sql = "INSERT INTO accounts (user_id, account_number, account_type, balance) VALUES (?, ?, ?, 0.0)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, accountNumber);
            pstmt.setString(3, accountType);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        if ("Savings".equalsIgnoreCase(accountType)) {
                            return new SavingsAccount(id, accountNumber, 0.0, userId);
                        } else {
                            return new CurrentAccount(id, accountNumber, 0.0, userId);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getAccountsByUser(int userId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String type = rs.getString("account_type");
                    int id = rs.getInt("id");
                    String accNumber = rs.getString("account_number");
                    double bal = rs.getDouble("balance");

                    if ("Savings".equalsIgnoreCase(type)) {
                        accounts.add(new SavingsAccount(id, accNumber, bal, userId));
                    } else {
                        accounts.add(new CurrentAccount(id, accNumber, bal, userId));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }


    public boolean updateBalanceAfterTransaction(int accountId, double amount, boolean isDeposit) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        
      
        if (!isDeposit) {
            amount = -amount;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, amount);
            pstmt.setInt(2, accountId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountById(int accountId) {
        return null; 
    }
}