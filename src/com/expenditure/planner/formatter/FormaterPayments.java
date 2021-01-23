package com.expenditure.planner.formatter;

import java.util.List;

import com.expenditure.planner.Payment;
import static com.expenditure.planner.Planner.SEPARATOR_LINE;

public class FormaterPayments implements Formater<Payment> {

    @Override
    public String toString(List<Payment> input) {
        String outString;
        StringBuilder stringBuilder = new StringBuilder();
        if (input != null) {
            for (Payment payment : input) {
                stringBuilder.append(formatedView(payment.getName(), payment.getValue()));
            }
        }
        outString = stringBuilder.toString() != null ? stringBuilder.toString() : "No data";
        return outString;
    }

    private String formatedView(String name, int value) {
        return String.format("%-14s", name) + "|" + String.format("%10.2f", ((double) value) / 100) + "|"
                + SEPARATOR_LINE;
    }
}