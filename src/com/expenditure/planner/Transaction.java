package com.expenditure.planner;

import java.util.Date;

public class Transaction extends Payment {

    private Date timestamp;

    public Transaction(String name, int value, Date date) {
        super(name, value);
        this.timestamp = date;
    }

    public Transaction(String name, int value) {
        super(name, value);
        this.timestamp = new Date(System.currentTimeMillis());
    }
    
    public Transaction (Payment payment) {
        super(payment.getName(),payment.getValue());
        this.timestamp = new Date(System.currentTimeMillis());
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
