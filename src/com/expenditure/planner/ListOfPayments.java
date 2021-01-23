package com.expenditure.planner;

public class ListOfPayments extends ListOf<Payment> {

    public ListOfPayments(String name) {
        super(name);
    }

    @Override
    public int getSum() {
        int sum = 0;
        for (Payment payment : getList()) {
            sum += payment.getValue();
        }
        return sum;
    }
}
