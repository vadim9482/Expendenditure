package com.expenditure.planner.formatter;

import static com.expenditure.planner.Planner.SEPARATOR_LINE;

import java.util.List;

public class FormatterInt implements Formater<Integer> {

    @Override
    public String listToString(List<Integer> input) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String intToString(String name, Integer input) {
        return formatedView(name, input);
    }

    private String formatedView(String name, int value) {
        return String.format("%-14s", name) + "|" + String.format("%10.2f", ((double) value) / 100) + "|"
                + SEPARATOR_LINE;
    }
}
