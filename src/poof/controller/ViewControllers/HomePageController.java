package poof.controller.ViewControllers;

import poof.views.HomePage.HomePage;
import poof.models.Account.Account;
import poof.models.Goal.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.Scanner;

// Sign Out, Make a transaction, Set Goal, Balance

public class HomePageController {
    private final HomePage homePage = new HomePage(this);
    private Account account;

    public HomePageController(Account account) {
        this.account = account;

    }

    public boolean setGoal(String name, String description, String date, String target, String type, String timeFrame) {
        if (name == null || name.isBlank())
            return false;

        if (description == null)
            return false;

        LocalDate formatedDate;

        try {
            Scanner chopper = new Scanner(date.replace('-', ' '));
            LocalDate d = LocalDate.of(chopper.nextInt(), Month.of(chopper.nextInt()), chopper.nextInt());
            formatedDate = d;
            chopper.close();
        } catch (InputMismatchException e) {
            return false;
        }

        double targetAmmount;
        try {
            targetAmmount = Double.parseDouble(target);
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }

        GoalType goalType;
        switch (type.toLowerCase()) {
            case "spending":
                goalType = GoalType.SPENDING;
                break;
            case "saving":
                goalType = GoalType.SPENDING;
                break;

            default:
                return false;
        }

        GoalTimeFrame goalTimeFrame;
        switch (timeFrame.toLowerCase()) {
            case "daily":
                goalTimeFrame = GoalTimeFrame.DAILY;
                break;
            case "weekly":
                goalTimeFrame = GoalTimeFrame.WEEKLY;
                break;
            case "monthly":
                goalTimeFrame = GoalTimeFrame.MONTHLY;
                break;
            case "yearly":
                goalTimeFrame = GoalTimeFrame.YEARLY;
                break;

            default:
                return false;
        }

        account.addGoal(
                new Goal(name, description, formatedDate, targetAmmount, targetAmmount, goalType, goalTimeFrame));

        return true;
    }

}
