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
    private JLabel homePage = new JLabel("Home Page");

    private JLabel balanceLbl = new JLabel("Balance: ");
    private JLabel blank = new JLabel("");

    private HomePageController homePageController;

    public HomePage(HomePageController homePageController) {
        this.homePageController = homePageController;

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

        frame.add(panel);
        frame.setVisible(true);

    }

    public void closeWindow() {
        frame.setVisible(false);
    }
}
