package com.expenditure.planner.formatter;

import java.util.Date;
import java.util.List;

import com.expenditure.planner.Transaction;

public class FormaterTransactions implements Formater<Transaction> {

    @Override
    public String toString(List<Transaction> input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Transaction transaction : input) {
            stringBuilder
                    .append(formatedView(transaction.getName(), transaction.getValue(), transaction.getTimestamp()));
        }
        return stringBuilder.toString();
    }

    private String formatedView(String name, int value, Date date) {
        return String.format("%-14s", name) + "|" + String.format("%9.2f", ((double) value) / 100) + "|" + date;
    }
}
