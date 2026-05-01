package banking.controller;

import banking.model.Account;
import banking.model.Transaction;
import banking.model.User;
import banking.service.BankService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label balanceLabel;
    @FXML private ComboBox<Account> accountComboBox;
    @FXML private TextField amountField;
    @FXML private TableView<Transaction> transactionTable;

    @FXML private TableColumn<Transaction, LocalDateTime> dateColumn;   // Changed to LocalDateTime
    @FXML private TableColumn<Transaction, String> typeColumn;
    @FXML private TableColumn<Transaction, Double> amountColumn;
    @FXML private TableColumn<Transaction, String> descColumn;

    private User currentUser;
    private Account selectedAccount;
    private final BankService bankService = new BankService();

    public void setCurrentUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Welcome, " + user.getUsername());
        loadUserAccounts();
    }

    private void loadUserAccounts() {
        List<Account> accounts = bankService.getUserAccounts(currentUser.getId());
        accountComboBox.setItems(FXCollections.observableArrayList(accounts));

        // No unused parameter warning
        accountComboBox.setOnAction(_ -> {
            selectedAccount = accountComboBox.getValue();
            if (selectedAccount != null) {
                updateBalanceDisplay();
                loadTransactionHistory();
            }
        });
    }

    private void updateBalanceDisplay() {
        balanceLabel.setText("Balance: ₹ " + String.format("%.2f", selectedAccount.getBalance()));
    }

    private void loadTransactionHistory() {
        if (selectedAccount == null) return;

        List<Transaction> transactions = bankService.getTransactionHistory(selectedAccount.getId());
        transactionTable.setItems(FXCollections.observableArrayList(transactions));

        // Set column mappings
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Clean Date Formatting
        dateColumn.setCellFactory(new Callback<TableColumn<Transaction, LocalDateTime>, TableCell<Transaction, LocalDateTime>>() {
            @Override
            public TableCell<Transaction, LocalDateTime> call(TableColumn<Transaction, LocalDateTime> param) {
                return new TableCell<>() {
                    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm");

                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(formatter.format(item));
                        }
                    }
                };
            }
        });
    }

    @FXML
    private void handleDeposit() {
        if (selectedAccount == null) {
            showAlert("Please select an account first");
            return;
        }
        double amount = getAmount();
        if (amount <= 0) return;

        boolean success = bankService.deposit(selectedAccount.getId(), amount);
        if (success) {
            selectedAccount.deposit(amount);
            bankService.addTransaction(selectedAccount.getId(), "Deposit", amount, "Deposited to account");

            updateBalanceDisplay();
            loadTransactionHistory();
            amountField.clear();
            showAlert("₹ " + amount + " deposited successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Deposit failed!");
        }
    }

    @FXML
    private void handleWithdraw() {
        if (selectedAccount == null) {
            showAlert("Please select an account first");
            return;
        }
        double amount = getAmount();
        if (amount <= 0) return;

        boolean success = bankService.withdraw(selectedAccount.getId(), amount);
        if (success) {
            selectedAccount.withdraw(amount);
            bankService.addTransaction(selectedAccount.getId(), "Withdraw", amount, "Withdrawn from account");

            updateBalanceDisplay();
            loadTransactionHistory();
            amountField.clear();
            showAlert("₹ " + amount + " withdrawn successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Insufficient balance!");
        }
    }

    @FXML
    private void handleCreateSavings() {
        Account acc = bankService.createAccount(currentUser.getId(), "Savings");
        if (acc != null) refreshAccounts();
    }

    @FXML
    private void handleCreateCurrent() {
        Account acc = bankService.createAccount(currentUser.getId(), "Current");
        if (acc != null) refreshAccounts();
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banking/fxml/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Banking System - Login");
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshAccounts() {
        loadUserAccounts();
        showAlert("New account created successfully!", Alert.AlertType.INFORMATION);
    }

    private double getAmount() {
        try {
            return Double.parseDouble(amountField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid amount");
            return 0;
        }
    }

    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type, message);
        alert.showAndWait();
    }

    private void showAlert(String message) {
        showAlert(message, Alert.AlertType.ERROR);
    }
}