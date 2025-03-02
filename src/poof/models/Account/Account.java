package poof.models.Account;

import java.util.ArrayList;

import poof.models.Goal.Goal;
import poof.models.Transaction.Transaction;

public class Account {
    private String username;
    private String password;
    private final ArrayList<Goal> goals = new ArrayList<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    /* == Public interface == */
    public Account(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /* == Getters & setters == */
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /* == Private interface == */

    private void addGoal(Goal goal) {
        this.goals.add(goal);
    }

    private void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
