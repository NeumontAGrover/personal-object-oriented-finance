package poof.views.HomePage;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import poof.controller.ViewControllers.HomePageController;

public class TransactionView {
  private JFrame frame = new JFrame("Make a Transaction");
  private JPanel panel = new JPanel();

  private JLabel title = new JLabel("Make a Transaction");
  private JTextField usernameTbx = new JTextField();
  private JTextField amountTbx = new JTextField();
  private JTextField descriptionTbx = new JTextField();
  private JLabel errorMessage = new JLabel("Please enter a valid username and amount");
  
  private JButton backBtn = new JButton("Back");
  private JButton sendBtn = new JButton("Send");

  private HomePageController homePageController;

  public TransactionView(HomePageController homePageController) {
    frame.setSize(300, 200);
    panel.setLayout(new GridLayout(10, 1));

    frame.add(panel);

    panel.add(backBtn);
    panel.add(title);

    panel.add(new JLabel("Username"));
    panel.add(usernameTbx);
    panel.add(new JLabel("Amount to Send"));
    panel.add(amountTbx);
    panel.add(new JLabel("Description"));
    panel.add(descriptionTbx);

    sendBtn.setMargin(new Insets(10, 0, 10, 0));
    panel.add(sendBtn);
    panel.add(errorMessage);
  
    backBtn.addActionListener(new BackBtnCallack());
    sendBtn.addActionListener(new SendBtnCallack());
    errorMessage.setVisible(false);

    this.homePageController = homePageController;
  }

  public void openWindow() {
    frame.setVisible(true);
  }

  public void closeWindow() {
    frame.setVisible(false);
  }

  private class BackBtnCallack implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      closeWindow();
    }
  }
  
  private class SendBtnCallack implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String username = usernameTbx.getText();
      String description = descriptionTbx.getText();
      int amount = 0;

      if (description == null) {
        description = "";
      }
      
      if (username == null || username.isEmpty()) {
        errorMessage.setVisible(true);
        return;
      }
      
      try {
        amount = Integer.parseInt(amountTbx.getText());
      } catch (NumberFormatException exception) {
        errorMessage.setVisible(true);
        return;
      }

      if (amount <= 0) {
        errorMessage.setVisible(true);
        return;
      }
      
      boolean succeeded = homePageController.makeTransaction(username, amount, description);

      if (succeeded) {
        closeWindow();
      } else {
        errorMessage.setVisible(true);
      }
    }
  }
}
