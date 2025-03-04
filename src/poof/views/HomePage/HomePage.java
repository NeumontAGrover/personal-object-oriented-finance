package poof.views.HomePage;

import poof.controller.ViewControllers.HomePageController;

import javax.swing.*;
import java.awt.*;

// Sign Out, Make a transaction, Set Goal, Balance
public class HomePage {

    private final JFrame frame = new JFrame("Home Page");

    private JButton signOutBtn = new JButton("Sign Out");
    private JButton transacrionBtn = new JButton("Make a Transaction");
    private JButton setGoalBtn = new JButton("Set Goal");

    private JLabel jLabel = new JLabel("Status");

    private JLabel balanceLbl = new JLabel("Balance: ");

    private HomePageController homePageController;

    public void HomePageView(HomePageController homePageController) {
        this.homePageController = homePageController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));

        panel.add(signOutBtn);
        panel.add(transacrionBtn);
        panel.add(setGoalBtn);

        panel.add(balanceLbl);

        signOutBtn.add(addActionListener());

    }
}
