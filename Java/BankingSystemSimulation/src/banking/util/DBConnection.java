package banking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bankingdb";
    private static final String USER = "root";     
    private static final String PASSWORD = "Gouri@1704";  

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database connection failed! Check MySQL is running.");
            throw e;
        }
    }
}