package com.expenditure.planner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoneyHolder {
    private List<Transaction> cashList = new ArrayList<Transaction>();

    public void addCash(Payment payment) {
        Date currentTime = new Date(System.currentTimeMillis());
        cashList.add(new Transaction(payment.getName(), payment.getValue(), currentTime));
    }

    public void addCash(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            cashList.add(transaction);
        }
    }

    public List<Transaction> getCashList() {
        return cashList;
    }
    
}
