package com.expenditure.planner.formatter;

import java.util.List;

public interface Formater<T> {

    public String intToString(String desscription, T input);

    public String listToString(List<T> input);

}
