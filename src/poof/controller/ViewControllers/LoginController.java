package poof.controller.ViewControllers;

import poof.models.Account.Account;
import poof.views.Login.LoginView;
import poof.controller.AuthController.AuthHandler;

public class LoginController {
    private final LoginView loginView = new LoginView(this);
    private final AuthHandler authenticator = new AuthHandler();

    public boolean handleLogin(String username, String password) {
        if(authenticator.authenticate(username, password)) {
            Account account = authenticator.getAccount(username);
            if(account == null) {
                return false;
            }
        }

        loginView.closeWindow();
        // new HomePageController(account).showHomePage();

        return true;
    }

    public boolean handleRegister(String username, String password) {
        if(!authenticator.contains(username)) {
            return true;
        }

        return false;
    }
}
