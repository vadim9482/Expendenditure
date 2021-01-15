package com.expenditure.planner.dao.parser;

import java.util.List;

public interface Parse<T> {

    public List <T> parse(String inputString);
}
