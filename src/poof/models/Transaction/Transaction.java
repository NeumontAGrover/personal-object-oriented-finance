package poof.models.Transaction;

import java.time.LocalDateTime;

import com.google.gson.annotations.Expose;

public class Transaction {
    @Expose private String name;
    @Expose private double amount;
    @Expose private String description;
    @Expose private final String dateString;
    private LocalDateTime date;

    /* == Public interface == */
    public Transaction(
        String name,
        double amount,
        String description,
        LocalDateTime date
    ) {
        this.setName(name);
        this.setAmount(amount);
        this.setDescription(description);
        this.setDate(date);
        dateString = date.toString();
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

    public LocalDateTime getDate() {
        return date;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }
}
