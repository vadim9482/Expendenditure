package com.expenditure.planner.dao.parser;

import java.util.LinkedList;
import java.util.List;

import com.expenditure.planner.Payment;
import com.expenditure.planner.dao.csv.FileToString;

public class ParserPayments implements Parse<Payment> {

    @Override
    public List<Payment> parse(String inputString) {
        List<Payment> outlist = new LinkedList<Payment>();
        List<String[]> list = new LinkedList<String[]>();
        list = new FileToString().csvToList(inputString, ",");
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
