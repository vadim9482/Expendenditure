package com.expenditure.planner;

import java.util.List;
import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String password;
    private ListPayments plans;
    private ListTransaction cash;
    private ListTransaction card;

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
        plans = new ListPayments();
        cash = new ListTransaction();
        card = new ListTransaction();
    }

    User(String ID, String name, String password) {
        this.uuid = UUID.fromString(ID);
        this.name = name;
        this.password = password;
        plans = new ListPayments();
        cash = new ListTransaction();
        card = new ListTransaction();
    }

    public void update() {
        
    }

    public boolean checkPassword(String inputPass) {
        return inputPass.equals(password);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public ListPayments getListPlans() {
        return plans;
    }

    public ListTransaction getListCash() {
        return cash;
    }

    public ListTransaction getListCard() {
        return card;
    }

    public void addPlan(Payment payment) {
        plans.addPayment(payment);
    }

    public void addPlan(List<Payment> listPayments) {
        plans.addPayment(listPayments);
    }
}
