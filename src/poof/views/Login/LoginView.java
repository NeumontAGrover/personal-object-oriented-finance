package poof.views.Login;

import poof.controller.ViewControllers.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    private final JFrame frame = new JFrame("Login");

    private final JTextField usernameTbx = new JTextField();
    private final JPasswordField passwordTbx = new JPasswordField();

    private JButton loginBtn = new JButton("Login");
    private JButton registerBtn = new JButton("Register");
    private JLabel statusLbl = new JLabel("Status here");

    private final LoginController loginController;

    /* == Public interface == */
    public LoginView(LoginController loginController) {
        this.loginController = loginController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel userNameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");

        panel.add(userNameLabel);
        panel.add(usernameTbx);

        panel.add(passwordLabel);
        panel.add(passwordTbx);

        panel.add(loginBtn);
        panel.add(registerBtn);

        loginBtn.addActionListener(new LoginBtnCallback());
        registerBtn.addActionListener(new RegisterBtnCallback());

        frame.add(panel);
        openWindow();
    }

    /* == Public utility functions == */
    public void showMsg(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }

    public void openWindow() {
        frame.setVisible(true);
    }

    public void closeWindow() {
        frame.setVisible(false);
    }

    /* == Private interface == */
    private class LoginBtnCallback implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameTbx.getText();

            String password = "";
            for (char character : passwordTbx.getPassword())
                password += character;

            if (!loginController.handleLogin(username, password)) {
                showMsg("Invalid username or password!");
            }
        }
    }

    private class RegisterBtnCallback implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameTbx.getText();

            String password = "";
            for (char character : passwordTbx.getPassword())
                password += character;

            if (loginController.handleRegister(username, password)) {
                statusLbl.setText("Registration Successful!");
                statusLbl.setForeground(Color.GREEN);

                registerBtn.setVisible(false);

                return;
            }

            statusLbl.setText("Failure to register! Account already exists!");
            statusLbl.setForeground(Color.RED);
        }
    }
}
