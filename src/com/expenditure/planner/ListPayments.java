package com.expenditure.planner;

import java.util.ArrayList;
import java.util.List;
import static com.expenditure.planner.Planner.SEPARATOR_LINE;

public class ListPayments {
    List<Payment> payments = new ArrayList<Payment>();

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public void addPayment(List<Payment> listPayments) {
        for (Payment payment : listPayments) {
            payments.add(payment);
        }
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public int returnSum() {
        int sum = 0;
        for (Payment payment : payments) {
            sum += payment.getValue();
        }
        return sum;
    }

    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Payment payment : payments) {
            stringBuilder.append(formatedView(payment.getName(), payment.getValue()) + SEPARATOR_LINE);
        }
        return stringBuilder.toString();
    }

    private String formatedView(String name, int value) {
        return String.format("%-14s", name) + "|" + String.format("%10.2f", ((double) value) / 100) + "|";
    }
}
