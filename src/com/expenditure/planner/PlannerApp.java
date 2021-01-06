package com.expenditure.planner;

import java.io.IOException;
import java.text.ParseException;

public class PlannerApp {

    public static void main(String[] args) throws IOException, ParseException {
        Planner planner = new Planner();
        planner.createUser("vadim9482", "pass");
    }
}
