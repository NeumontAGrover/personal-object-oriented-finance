package poof.views.HomePage;

import poof.controller.ViewControllers.HomePageController;
import poof.models.Account.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Sign Out, Make a transaction, Set Goal, Balance
public class HomePage {
    private final JFrame frame = new JFrame("Home Page");

    private JButton signOutBtn = new JButton("Sign Out");
    private JButton transacrionBtn = new JButton("Make a Transaction");
    private JButton setGoalBtn = new JButton("Set Goal");

    private JLabel jLabel = new JLabel("Status");
    private JLabel homePage = new JLabel("Home Page");

    private JLabel balanceLbl = new JLabel("Balance: ");
    private JLabel blank = new JLabel("");

    private HomePageController homePageController;
    private final Account account;

    public HomePage(HomePageController homePageController, Account account) {
        this.homePageController = homePageController;
        balanceLbl.setText("Balance: " + account.getBalance());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        panel.add(homePage);
        panel.add(blank);
        panel.add(signOutBtn);

        panel.add(balanceLbl);

        panel.add(transacrionBtn);
        panel.add(blank);
        panel.add(setGoalBtn);

        signOutBtn.addActionListener(new SignOutBtnCallBack());
        setGoalBtn.addActionListener(new SetGoalBtnCallBack());
        transacrionBtn.addActionListener(new MakeTransactionBtnCallBack());

        frame.add(panel);
        frame.setVisible(true);

        this.account = account;
    }

    public void closeWindow() {
        frame.setVisible(false);
    }

    public void updateBalance() {
        balanceLbl.setText(Float.toString(account.getBalance()));
    }

    private class MakeTransactionBtnCallBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homePageController.openTransactionWindow();
        }
    }

    private class SignOutBtnCallBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homePageController.signOut();
        }
    }

    private class SetGoalBtnCallBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            homePageController.setGoal();
        }

    }
}
