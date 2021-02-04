package com.expenditure.planner;

public class ListOfPayments extends ListOf<Payment> {

    
    public ListOfPayments(String name) {
        super(name);
    }

    @Override
    public Payment getSum() {
        int sum = 0;
        for (Payment payment : getList()) {
            sum += payment.getValue();
        }
        return new Payment("Summary",sum);
    }

    public String toString() {
        String output = "";
        for (Payment payment : getList()) {
            output += payment.toString();
        }
        return output;
    }
}
