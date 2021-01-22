package com.expenditure.planner;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TablePayments implements Table<Payment> {
    UUID uuid;
    String name;
    List<Payment> listPayments;

    public TablePayments(String name) {
        uuid = UUID.randomUUID();
        this.name = name;
        listPayments = new LinkedList<>();
    }

    public void addPayment(Payment payment) {
        listPayments.add(payment);
    }

    public void addPayment(List<Payment> payments) {
        listPayments.addAll(payments);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<Payment> getListPayments() {
        return listPayments;
    }

    @Override
    public Payment getTable(String name) {
        // TODO Auto-generated method stub
        return null;
    }
}
