package com.expenditure.planner;

import java.util.List;

public class ShowStatusPayments implements ShowStatus<Payment> {

    @Override
    public void show(List<Payment> input) {
        for (Payment payment : input) {
            formatedView(payment.getName(), payment.getValue());
        }
    }

    private void formatedView(String name, int value) {
        System.out.print(String.format("%-14s", name));
        System.out.println("|" + String.format("%10.2f", ((double) value) / 100) + "|");
    }
}