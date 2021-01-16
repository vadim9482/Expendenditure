package com.expenditure.planner.formatter;

import java.util.List;

public interface Formater<T> {

    public String toString(List<T> input);

}
