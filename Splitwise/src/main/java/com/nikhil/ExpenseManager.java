package com.nikhil;

import com.nikhil.Entities.Expense;
import com.nikhil.Entities.ExpenseType;
import com.nikhil.Entities.Split;
import com.nikhil.Entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<Expense>();
        userMap = new HashMap<String, User>();
        balanceSheet = new HashMap<String, Map<String, Double>>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<String, Double>());
    }

    void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits) {
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits);
        expenses.add(expense);
        assert expense != null;
        for (Split split : expense.getSplits()) {
            String paidTo = split.getUser().getId();
            Map<String, Double> balances = balanceSheet.get(paidTo);
            if (balances.isEmpty()) {
                balances = new HashMap<>();
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());
            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            // now update paidBy key
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }

    public void showBalance(String userId) {
        if (!balanceSheet.containsKey(userId)) {
            System.out.println("No balance sheet for user" + userId);
        }
        for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            printBalance(userId, userBalance.getKey(), userBalance.getValue());
        }
    }

    void showBalances() {
        if (balanceSheet.isEmpty()) {
            System.out.println("Balance sheet is empty");
        }
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
            }
        }
    }

    private void printBalance(String user1, String user2, Double amount) {
        if (amount > 0) {
            System.out.println(user2 + " owes " + user1 + " : " + Math.abs(amount));
        } else if (amount < 0) {
            System.out.println(user1 + " owes " + user2 + " : " + Math.abs(amount));
        }
    }
}
