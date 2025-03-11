package poof.controller.ViewControllers;

import poof.views.HomePage.HomePage;
import poof.views.HomePage.TransactionView;
import poof.views.Login.LoginView;

import java.time.LocalDateTime;

import poof.controller.AuthController.AuthHandler;
import poof.models.Account.Account;
import poof.models.Transaction.Transaction;

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

    public void setGoal() {

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
