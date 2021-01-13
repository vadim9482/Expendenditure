package com.expenditure.planner;

public class PlannerApp {

    public static final String RESOURCE_PATH = "resources";
    public static final String EXPENCES_FILENAME = "expences.txt";
    public static final String CARD_EXPENCES_FILENAME = "cardExpences.txt";
    public static final String PLANS_FILENAME = "plans.txt";

    public static void main(String[] args) {
        Planner planner = new Planner();
        planner.createUser("vadim9482_fake", "pass");
        planner.importCSVPlan("vadim9482", RESOURCE_PATH,PLANS_FILENAME);
        
    }
}
