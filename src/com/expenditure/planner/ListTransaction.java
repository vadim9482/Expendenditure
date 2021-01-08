package com.expenditure.planner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListTransaction {
    private List<Transaction> listTransactions = new ArrayList<Transaction>();

    public void addCash(Payment payment) {
        Date currentTime = new Date(System.currentTimeMillis());
        listTransactions.add(new Transaction(payment.getName(), payment.getValue(), currentTime));
    }

    public void addCash(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            listTransactions.add(transaction);
        }
    }

    public List<Transaction> getCashList() {
        return listTransactions;
    }
}
