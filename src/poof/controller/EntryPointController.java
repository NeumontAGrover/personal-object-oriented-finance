package poof.controller;

import poof.controller.ViewControllers.LoginController;
import poof.views.Login.LoginView;

public class EntryPointController {
    public void run() {
        new LoginController();
    }
}
