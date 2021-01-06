package com.expenditure.planner;

import java.util.UUID;

public class Payment {
    private UUID ID;
    private String name;
    private int value;
    
    public Payment (String name, int value) {
        this.name = name;
        this.value = value;
        this.ID = UUID.randomUUID();
    }

    public UUID getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
