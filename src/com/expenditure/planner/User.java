package com.expenditure.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.expenditure.planner.dao.csv.CSVMethodsPayment;

public class User {
    private UUID uuid;
    private String name;
    private String password;
    private List<Payment> plans;
    private List<Transaction> cash;
    private List<Transaction> card;
    CSVMethodsPayment csvMethods = new CSVMethodsPayment();

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
        plans = new ArrayList<>();
        cash = new ArrayList<>();
        card = new ArrayList<>();
    }

    User(String ID, String name, String password) {
        this.uuid = UUID.fromString(ID);
        this.name = name;
        this.password = password;
        plans = new ArrayList<>();
        cash = new ArrayList<>();
        card = new ArrayList<>();
    }

    public boolean checkPassword(String inputPass) {
        return inputPass.equals(password);
    }

    public void addPlan(Payment payment) {
        plans.add(payment);
    }

    public void addPlan(List<Payment> listPayments) {
        plans.addAll(listPayments);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Payment> getPlans() {
        return plans;
    }

    public List<Transaction> getCash() {
        return cash;
    }

    public List<Transaction> getCard() {
        return card;
    }

    public CSVMethodsPayment getDao() {
        return csvMethods;
    }
}
