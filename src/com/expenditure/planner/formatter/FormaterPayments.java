package com.expenditure.planner.formatter;

import java.util.List;

import com.expenditure.planner.Payment;
import static com.expenditure.planner.Planner.SEPARATOR_LINE;

public class FormaterPayments implements Formater<Payment> {

    @Override
    public String toString(List<Payment> input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Payment payment : input) {
            stringBuilder.append(formatedView(payment.getName(), payment.getValue()));
        }
        return stringBuilder.toString();
    }

    private String formatedView(String name, int value) {
        return String.format("%-14s", name) + "|" + String.format("%10.2f", ((double) value) / 100) + "|"
                + SEPARATOR_LINE;
    }
}