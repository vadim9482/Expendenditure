package com.expenditure.planner.dao.csv.csvparser;

import java.util.List;

public interface Parse<T> {

    public List <T> parse(String inputString);
}
