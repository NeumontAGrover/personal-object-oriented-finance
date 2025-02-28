package poof.models.Transaction;

import java.time.LocalDateTime;

public class Transaction {
    private String name;
    private double amount;
    private String description;
    private TransactionType type;
    private LocalDateTime date;

    /* == Public interface == */
    public Transaction(
        String name,
        double amount,
        String description,
        TransactionType type,
        LocalDateTime date
    ) {
        this.setName(name);
        this.setAmount(amount);
        this.setDescription(description);
        this.setType(type);
        this.setDate(date);
    }

    /* == Getters & Setters ==*/
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    private void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }
}
