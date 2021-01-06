package com.expenditure.planner;

import java.util.LinkedList;
import java.util.List;

public class ParserPayments implements Parse<Payment> {

    @Override
    public List<Payment> parse(String inputString) {
        List<Payment> outlist = new LinkedList<Payment>();
        List<String[]> list = new LinkedList<String[]>();
        list = new CSVReader().csvRead(inputString, ",");
        for (String[] line : list) {
            outlist.add(parsePayment(line));
        }
        return outlist;
    }

    public Payment parsePayment(String[] incomeString) {
        String name  = incomeString[1];
        int value = 0;
        try {
            value = (int) (Double.parseDouble(incomeString[0]) * 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Payment payment = new Payment(name, value);
        return payment;

    }
}
