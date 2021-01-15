package com.expenditure.planner.formatter;

import java.util.Date;
import java.util.List;

import com.expenditure.planner.Transaction;

public class ShowStatusTransactions implements ShowStatus<Transaction> {

    @Override
    public void show(List<Transaction> input) {
        for (Transaction transaction : input) {
            formatedView(transaction.getName(), transaction.getValue(), transaction.getTimestamp());
        }
    }

    private void formatedView(String name, int value, Date date) {
        System.out.print(String.format("%-14s", name));
        System.out.print("|" + String.format("%9.2f", ((double) value) / 100) + "|");
        System.out.println(date);
    }
}
