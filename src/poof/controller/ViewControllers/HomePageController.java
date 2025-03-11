package poof.controller.ViewControllers;

import poof.views.HomePage.HomePage;
import poof.views.HomePage.TransactionView;
import poof.views.Login.LoginView;

import java.time.LocalDateTime;

import poof.controller.AuthController.AuthHandler;
import poof.models.Account.Account;
import poof.models.Transaction.Transaction;
import poof.models.Goal.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.Scanner;

// Sign Out, Make a transaction, Set Goal, Balance

public class HomePageController {
    private final HomePage homePage;
    private final LoginView loginView;
    private final TransactionView transactionView;
    private final AuthHandler authenticator;
    private final Account account;

    public HomePageController(Account account, LoginView loginView, AuthHandler authenticator) {
        homePage = new HomePage(this, account);
        transactionView = new TransactionView(this);
        this.loginView = loginView;
        this.authenticator = authenticator;
        this.account = account;
    }

    public void signOut() {
        homePage.closeWindow();
        loginView.openWindow();
    }

    public boolean setGoal(String name, String description, String date, String target, String type, String timeFrame) {
        if (name == null || name.isBlank())
            return false;

        if (description == null)
            return false;

        LocalDate formatedDate;

        try {
            Scanner chopper = new Scanner(date.replace('-', ' '));
            LocalDate d = LocalDate.of(chopper.nextInt(), Month.of(chopper.nextInt()), chopper.nextInt());
            formatedDate = d;
            chopper.close();
        } catch (InputMismatchException e) {
            return false;
        }

        double targetAmmount;
        try {
            targetAmmount = Double.parseDouble(target);
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }

        GoalType goalType;
        switch (type.toLowerCase()) {
            case "spending":
                goalType = GoalType.SPENDING;
                break;
            case "saving":
                goalType = GoalType.SPENDING;
                break;

            default:
                return false;
        }

        GoalTimeFrame goalTimeFrame;
        switch (timeFrame.toLowerCase()) {
            case "daily":
                goalTimeFrame = GoalTimeFrame.DAILY;
                break;
            case "weekly":
                goalTimeFrame = GoalTimeFrame.WEEKLY;
                break;
            case "monthly":
                goalTimeFrame = GoalTimeFrame.MONTHLY;
                break;
            case "yearly":
                goalTimeFrame = GoalTimeFrame.YEARLY;
                break;

            default:
                return false;
        }

        account.addGoal(
                new Goal(name, description, formatedDate, targetAmmount, targetAmmount, goalType, goalTimeFrame));

        return true;
    }

    public void openTransactionWindow() {
        transactionView.openWindow();
    }

    public boolean makeTransaction(String username, float amount, String description) {
        Transaction receiverTransaction = new Transaction(account.getUsername(), amount, description, LocalDateTime.now());
        Transaction senderTransaction = new Transaction(username, -amount, description, LocalDateTime.now());
        Account receiver = authenticator.getAccount(username);

        if (receiver == null) {
            return false;
        }

        receiver.setBalance(receiver.getBalance() + amount);
        receiver.addTransaction(receiverTransaction);
        account.setBalance(account.getBalance() - amount);
        account.addTransaction(senderTransaction);
        authenticator.writeUserState();
        homePage.updateBalance();
        return true;
    }
}
