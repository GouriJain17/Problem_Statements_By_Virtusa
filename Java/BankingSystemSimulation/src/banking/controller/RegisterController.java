package banking.controller;

import banking.service.BankService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML private TextField regUsernameField;
    @FXML private PasswordField regPasswordField;
    @FXML private PasswordField regConfirmPasswordField;
    @FXML private Label statusLabel;

    private final BankService bankService = new BankService();

    @FXML
    private void handleRegister() {
        String username = regUsernameField.getText().trim();
        String password = regPasswordField.getText().trim();
        String confirmPassword = regConfirmPasswordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Username and password are required");
            return;
        }

        if (!password.equals(confirmPassword)) {
            statusLabel.setText("Passwords do not match");
            return;
        }

        if (password.length() < 4) {
            statusLabel.setText("Password must be at least 4 characters");
            return;
        }

        boolean success = bankService.registerUser(username, password);

        if (success) {
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Registration successful! Please login.");
            
            // Optional: Auto go back to login after 1.5 seconds
            new Thread(() -> {
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
                javafx.application.Platform.runLater(this::goBackToLogin);
            }).start();
        } else {
            statusLabel.setText("Username already exists. Try another one.");
        }
    }

    @FXML
    private void handleBackToLogin() {
        goBackToLogin();
    }

    private void goBackToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banking/fxml/login.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) regUsernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Banking System - Login");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}