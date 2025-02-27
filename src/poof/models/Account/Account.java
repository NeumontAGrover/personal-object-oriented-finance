package poof.models.Account;

import java.util.ArrayList;

import poof.models.Goal.Goal;
import poof.models.Transaction.Transaction;

public class Account {
    private String user;
    private String password;
    private final ArrayList<Goal> goals = new ArrayList<>();
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(String user, String password) {
        this.setUser(user);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
