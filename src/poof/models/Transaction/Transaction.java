package poof.models.Transaction;

import java.time.LocalDateTime;
import com.google.gson.annotations.Expose;

public class Transaction {
    @Expose private String name;
    @Expose private double amount;
    @Expose private String description;
    @Expose private String date;

    /* == Public interface == */
    public Transaction(
        String name,
        double amount,
        String description,
        String date
    ) {
        this.setName(name);
        this.setAmount(amount);
        this.setDescription(description);
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

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }
}
