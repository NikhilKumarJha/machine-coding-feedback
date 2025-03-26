package com.nikhil;

import com.nikhil.Entities.*;

import java.util.List;

public class ExpenseService {
    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits) {
        switch (expenseType) {
            case EQUAL -> {
                return new EqualExpense(amount, paidBy, splits);
            }
            case PERCENT -> {
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((percentSplit.getPercent() * amount) / 100.0);
                }
                return new PercentExpense(amount, paidBy, splits);
            }
            case EXACT -> {
                double splitAmount = amount / splits.size();
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                return new ExactExpense(amount, paidBy, splits);
            }
            default -> {
                return null;
            }
        }
    }
}
