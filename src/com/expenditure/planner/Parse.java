package com.expenditure.planner;

import java.util.List;

public interface Parse<T> {

    public List <T> parse(String inputString);
}
