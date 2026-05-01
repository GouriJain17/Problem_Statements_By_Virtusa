package banking.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int accountId;
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    
    public Transaction(int id, int accountId, String type, double amount, LocalDateTime timestamp, String description) {
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public int getId() { return id; }
    public int getAccountId() { return accountId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
}