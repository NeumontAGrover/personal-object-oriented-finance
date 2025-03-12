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
    private JButton transactionBtn = new JButton("Make a Transaction");
    private JButton setGoalBtn = new JButton("Set Goal");

    private JButton viewTransactions = new JButton("View All Transactions");
    private JButton viewGoals = new JButton("View All Goals");

    private JLabel homePageLbl = new JLabel("Home Page");
    private JLabel balanceLbl = new JLabel("Balance: ");

    private HomePageController homePageController;
    private final Account account;
    private static HomePage thisHomePage;

    public HomePage(HomePageController homePageController, Account account) {
        this.homePageController = homePageController;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);

        JPanel flexColumn = new JPanel();
        flexColumn.setLayout(new GridLayout(7, 1));
        flexColumn.add(homePageLbl);

        JPanel accountRow = new JPanel();
        accountRow.setLayout(new GridLayout(1, 2));
        accountRow.add(balanceLbl);
        accountRow.add(signOutBtn);

        JPanel transactionRow = new JPanel();
        transactionRow.setLayout(new GridLayout(1, 2));
        transactionRow.add(transactionBtn);
        transactionRow.add(viewTransactions);

        JPanel goalRow = new JPanel();
        goalRow.setLayout(new GridLayout(1, 2));
        goalRow.add(setGoalBtn);
        goalRow.add(viewGoals);

        flexColumn.add(homePageLbl);
        flexColumn.add(new JLabel());
        flexColumn.add(accountRow);
        flexColumn.add(new JLabel());
        flexColumn.add(transactionRow);
        flexColumn.add(new JLabel());
        flexColumn.add(goalRow);

        homePageLbl.setHorizontalAlignment(JLabel.CENTER);
        balanceLbl.setText("Balance: " + account.getBalance());
        Insets regularInsets = new Insets(10, 2, 10, 2);
        signOutBtn.setMargin(regularInsets);
        transactionBtn.setMargin(regularInsets);
        viewTransactions.setMargin(regularInsets);
        setGoalBtn.setMargin(regularInsets);
        viewGoals.setMargin(regularInsets);

        signOutBtn.addActionListener(new SignOutBtnCallBack());
        setGoalBtn.addActionListener(new SetGoalBtnCallBack());
        transactionBtn.addActionListener(new MakeTransactionBtnCallBack());
        viewTransactions.addActionListener(new ViewTransactionsBtnCallBack());
        viewGoals.addActionListener(new ViewGoalsBtnCallBack());

        frame.add(flexColumn);
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
        public void actionPerformed(ActionEvent ignored) {
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
