package poof.controller.ViewControllers;

import poof.models.Account.Account;
import poof.views.Login.LoginView;
import poof.controller.AuthController.AuthHandler;

public class LoginController {
    private final AuthHandler authenticator = new AuthHandler();
    private final LoginView loginView = new LoginView(this);

    public boolean handleLogin(String username, String password) {
        if(authenticator.authenticate(username, password)) {
            Account account = authenticator.getAccount(username);
            if(account == null) {
                return false;
            }

            loginView.closeWindow();
            new HomePageController(account);
        }

        return true;
    }

    public boolean handleRegister(String username, String password) {
        if(!authenticator.contains(username)) {
            authenticator.addAccount(new Account(username, password));
            return true;
        }

        return false;
    }
}
