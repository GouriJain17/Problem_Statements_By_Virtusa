package banking.controller;

import banking.model.User;
import banking.service.BankService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final BankService bankService = new BankService();

    @FXML
    private void handleLogin() {
        System.out.println("=== handleLogin() method called! ===");   // ← Add this line

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter username and password");
            return;
        }

        User user = bankService.login(username, password);
        if (user != null) {
            System.out.println("Login successful for user: " + username);
            loadDashboard(user);
        } else {
            statusLabel.setText("Invalid username or password");
            System.out.println("Login failed for user: " + username);
        }
    }

    @FXML
    private void handleRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banking/fxml/register.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register New User");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Error opening register screen");
        }
    }

    private void loadDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banking/fxml/dashboard.fxml"));
            Scene scene = new Scene(loader.load());

            DashboardController controller = loader.getController();
            controller.setCurrentUser(user);

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Welcome - " + user.getUsername());
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}