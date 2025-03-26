package com.nikhil.Entities;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, List<Split> splits) {
        super(amount, paidBy, splits);
    }

    @Override
    public boolean validate() {

        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }
        double totalSplit = getAmount();
        double sumSplitAmount = 0.0;
        for (Split split : getSplits()) {
            sumSplitAmount += split.getAmount();
        }
        return totalSplit == sumSplitAmount;
    }
}
