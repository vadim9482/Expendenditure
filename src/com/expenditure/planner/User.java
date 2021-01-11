package com.expenditure.planner;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String password;
    private ListPayments listPlans;
    private ListTransaction listCash;
    private ListTransaction listCard;

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
        listPlans = new ListPayments();
        listCash = new ListTransaction();
        listCard = new ListTransaction();
    }

    User(String ID, String name, String password) {
        this.uuid = UUID.fromString(ID);
        this.name = name;
        this.password = password;
        listPlans = new ListPayments();
        listCash = new ListTransaction();
        listCard = new ListTransaction();
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
        return listPlans;
    }

    public ListTransaction getListCash() {
        return listCash;
    }

    public ListTransaction getListCard() {
        return listCard;
    }

}
