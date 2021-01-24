package com.expenditure.planner;

public class ListOfTransactions extends ListOf<Transaction> {

    public ListOfTransactions(String name) {
        super(name);
    }

    @Override
    public Payment getSum() {
        int sum = 0;
        for (Transaction transaction : getList()) {
            sum += transaction.getValue();
        }
        return new Payment("Summary", sum);
    }

    public String toString() {
        String output = "";
        for (Transaction transaction : getList()) {
            output += transaction.toString();
        }
        return output;
    }
}
