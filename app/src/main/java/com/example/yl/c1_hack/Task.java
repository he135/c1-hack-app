package com.example.yl.c1_hack;

/**
 * Created by YL on 1/11/2018.
 */

public class Task {
    private String name;
    private String description;
    private double value;

    public Task(String taskName, String descript, double val) {
        name = taskName;
        description = descript;
        value = val;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
