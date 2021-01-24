package com.expenditure.planner;

import static com.expenditure.planner.Planner.SEPARATOR_LINE;

import java.util.UUID;

public class Payment {
    private UUID ID;
    private String name;
    private int value;

    public Payment(String name, int value) {
        this.ID = UUID.randomUUID();
        this.name = name;
        this.value = value;
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

    public String toString() {
        return String.format("%-14s", name) + "|" + String.format("%10.2f", ((double) value) / 100) + "|"
                + SEPARATOR_LINE; 
    }
}
