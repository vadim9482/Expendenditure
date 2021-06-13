package com.expenditure.planner.dao.csv.csvparser;

import static com.expenditure.planner.Planner.FORMAT_DATE;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.expenditure.planner.Transaction;
import com.expenditure.planner.dao.csv.FileToString;

public class ParserTransactions implements Parse<Transaction> {

    @Override
    public List<Transaction> parse(String inputString) {
        List<String[]> list = new LinkedList<String[]>();
        list = new FileToString().csvToList(inputString, ",");
        List<Transaction> expenceList = new LinkedList<Transaction>();
        for (String[] expenceString : list) {
            Transaction transaction = parseTransaction(expenceString);
            expenceList.add(transaction);
        }
        return expenceList;
    }

    public Transaction parseTransaction(String[] incomeString) {
        int value = (int) (Double.parseDouble(incomeString[0]) * 100);
        String name = incomeString[1];
        String dateString = incomeString[2];
        Locale locale = new Locale("en", "US");
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE, locale);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Transaction(name, value, date);
    }
}
