package poof.views.HomePage;

import poof.controller.ViewControllers.HomePageController;

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
    private static HomePage thisHomePage;

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

        setGoalBtn.addActionListener(new SetGoalBtnCallBack());

    }

    public void closeWindow() {
        frame.setVisible(false);
    }

    private class SetGoalBtnCallBack implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GoalSetPage goalSetter = new GoalSetPage();
            goalSetter.goalPage();
        }

    }

    private class GoalSetPage {

        final JFrame goalFrame = new JFrame("New Goal");

        final JTextField goalNameTxt = new JTextField();
        final JTextField descriptionTxt = new JTextField();
        final JTextField goalDateTxt = new JTextField();
        final JTextField targetTxt = new JTextField();
        final JTextField typeTxt = new JTextField();
        final JTextField timeFrameTxt = new JTextField();

        public void goalPage() {
            frame.setVisible(false);

            // goalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            goalFrame.setSize(600, 700);

            JPanel goalPanel = new JPanel();
            goalPanel.setLayout(new GridLayout(7, 2));

            JLabel goalName = new JLabel("Name of Goal: ");
            JLabel description = new JLabel("Goal description: ");
            JLabel goalDate = new JLabel("Date: (Year-Month-Day)");
            JLabel target = new JLabel("Target: ");
            JLabel goalType = new JLabel("Saving or Spending?");
            JLabel timeFrame = new JLabel("Daily, Weekly, Monthly, or Yearly? ");

            JButton go = new JButton("Go");
            JButton back = new JButton("Back");

            goalPanel.add(goalName);
            goalPanel.add(goalNameTxt);

            goalPanel.add(description);
            goalPanel.add(descriptionTxt);

            goalPanel.add(goalDate);
            goalPanel.add(goalDateTxt);

            goalPanel.add(target);
            goalPanel.add(targetTxt);

            goalPanel.add(goalType);
            goalPanel.add(typeTxt);

            goalPanel.add(timeFrame);
            goalPanel.add(timeFrameTxt);

            go.addActionListener(new goBtnCallback());
            back.addActionListener(new backBtnCallback());

            goalPanel.add(go);
            goalPanel.add(back);

            goalFrame.add(goalPanel);
            goalFrame.setVisible(true);

        }

        private void reopenHompage() {
            frame.setVisible(true);
        }

        public void showMsg(String msg) {
            JOptionPane.showMessageDialog(goalFrame, msg);
        }

        public void closeWindow() {
            goalFrame.setVisible(false);
        }

        private class goBtnCallback implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (homePageController.setGoal(goalNameTxt.getText(), descriptionTxt.getText(),
                        goalDateTxt.getText(), targetTxt.getText(), typeTxt.getText(), timeFrameTxt.getText())) {
                    showMsg("Goal Set!");
                } else {
                    showMsg("Error, one or more areas have incorrect formatting. Please try again.");
                }

            }

        }

        private class backBtnCallback implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
                frame.setVisible(true);
            }

        }

    }

}
