package poof.views.HomePage;

import poof.controller.ViewControllers.HomePageController;
import poof.models.Account.Account;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Sign Out, Make a transaction, Set Goal, Balance
public class HomePage {
    private final JFrame frame = new JFrame("Home Page");

    private JButton signOutBtn = new JButton("Sign Out");
    private JButton transacrionBtn = new JButton("Make a Transaction");
    private JButton setGoalBtn = new JButton("Set Goal");

    private JButton viewTransactions = new JButton("View All Transactions");
    private JButton viewGoals = new JButton("View All Goals");

    private JLabel jLabel = new JLabel("Status");
    private JLabel homePage = new JLabel("Home Page");

    private JLabel balanceLbl = new JLabel("Balance: ");
    private JLabel blank = new JLabel("");

    private HomePageController homePageController;
    private final Account account;
    private static HomePage thisHomePage;

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

        panel.add(viewTransactions);
        panel.add(viewGoals);

        signOutBtn.addActionListener(new SignOutBtnCallBack());
        setGoalBtn.addActionListener(new SetGoalBtnCallBack());
        transacrionBtn.addActionListener(new MakeTransactionBtnCallBack());

        viewTransactions.addActionListener(new ViewTransactionsBtnCallBack());
        viewGoals.addActionListener(new ViewGoalsBtnCallBack());

        frame.add(panel);
        frame.setVisible(true);

        setGoalBtn.addActionListener(new SetGoalBtnCallBack());

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

    private class ViewTransactionsBtnCallBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ignored) {
            JDialog dialogPanel = new JDialog(frame, "Transactions", true);
            dialogPanel.setSize(600, 500);
            dialogPanel.setLayout(new BorderLayout());
       
            String[] transactionColumns = {"Name", "Description", "Date", "Amount", "Type"};
           
            Object[][] transactions = homePageController.getAllTransactions();
            if(transactions == null) {
                JOptionPane.showMessageDialog(frame, "No transactions available!", "Transaction Display", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
       
            JTable transactionsTable = new JTable(transactions, transactionColumns);
            JScrollPane scrollPane = new JScrollPane(transactionsTable);

            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(_ -> dialogPanel.dispose());

            dialogPanel.add(scrollPane, BorderLayout.CENTER);
            dialogPanel.add(closeBtn, BorderLayout.SOUTH);

            dialogPanel.setLocationRelativeTo(frame);
            dialogPanel.setVisible(true);
        }
    }

    private class ViewGoalsBtnCallBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog dialogPanel = new JDialog(frame, "Goals", true);
            dialogPanel.setSize(600, 500);
            dialogPanel.setLayout(new BorderLayout());

            String[] goalColumns = {"Name", "Description", "Date", "Target", "Time Frame", "Type"};
            
            Object[][] goals = homePageController.getAllGoals();
            if(goals == null) {
                JOptionPane.showMessageDialog(frame, "No goals available!", "Goals", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JTable goalsTable = new JTable(goals, goalColumns);
            JScrollPane scrollPane = new JScrollPane(goalsTable);

            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(_ -> dialogPanel.dispose());

            dialogPanel.add(scrollPane, BorderLayout.CENTER);
            dialogPanel.add(closeBtn, BorderLayout.SOUTH);

            dialogPanel.setLocationRelativeTo(frame);
            dialogPanel.setVisible(true);
        }
    }
}
