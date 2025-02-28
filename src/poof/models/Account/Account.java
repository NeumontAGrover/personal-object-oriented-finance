package poof.models.Account;

import java.util.ArrayList;

import poof.models.Goal.Goal;
import poof.models.Transaction.Transaction;

public class Account {
    private String user;
    private String password;
    private final ArrayList<Goal> goals = new ArrayList<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    /* == Public interface == */
    public Account(String user, String password) {
        this.setUser(user);
        this.setPassword(password);
    }

    /* == Getters & setters == */
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    private void setUser(String user) {
        this.user = user;
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
