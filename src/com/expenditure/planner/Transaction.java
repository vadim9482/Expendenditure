package com.expenditure.planner;

import static com.expenditure.planner.Planner.SEPARATOR_LINE;

import java.util.Date;

public class Transaction extends Payment {

    private Date date;

    public Transaction(String name, int value, Date date) {
        super(name, value);
        this.date = date;
    }

    public Transaction(String name, int value) {
        super(name, value);
        this.date = new Date(System.currentTimeMillis());
    }
    
    public Transaction (Payment payment) {
        super(payment.getName(),payment.getValue());
        this.date = new Date(System.currentTimeMillis());
    }

    public Date getDate() {
        return date;
    }
    
    public String toString() {
        return String.format("%-14s", getName()) + "|" + String.format("%10.2f", ((double) getValue()) / 100) + "|" + date + "|"
                + SEPARATOR_LINE;
    }

}
