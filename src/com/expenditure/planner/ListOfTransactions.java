package com.expenditure.planner;

public class ListOfTransactions extends ListOf<Transaction> {

    public ListOfTransactions(String name) {
        super(name);
    }

    @Override
    public int getSum() {
        int sum = 0;
        for (Transaction transaction : getList()) {
            sum += transaction.getValue();
        }
        return sum;
    }

}
