package poof.models.Goal;

import java.time.LocalDate;

public class Goal {
    private String name;
    private String description;
    private LocalDate date;
    private double target;
    private double balance;
    private GoalType type;
    private GoalTimeFrame timeFrame;

    /* == Public interface == */
    public Goal(
       String name,
       String description,
       LocalDate date,
       double target,
       double balance,
       GoalType type,
       GoalTimeFrame timeFrame
    ) {
        this.setName(name);
        this.setDescription(description);
        this.setDate(date);
        this.setTarget(target);
        this.setBalance(balance);
        this.setType(type);
        this.setTimeFrame(timeFrame);
    }

    /* == Getters & setters == */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public GoalType getType() {
        return type;
    }

    public void setType(GoalType type) {
        this.type = type;
    }

    public GoalTimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(GoalTimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }
}
