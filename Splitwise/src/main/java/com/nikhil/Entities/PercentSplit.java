package com.nikhil.Entities;

public class PercentSplit extends Split{

    protected double percent;

    public PercentSplit(User user,double percent) {
        super(user);
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
