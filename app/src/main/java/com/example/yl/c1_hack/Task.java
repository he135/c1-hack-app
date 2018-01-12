package com.example.yl.c1_hack;

/**
 * Created by YL on 1/11/2018.
 */

public class Task {
    private String name;
    private String description;
    private double value;
    private int status; //1 means uncompleted task, 2 means completed, 3 means completed and money was transferred
    private int id;

    public Task() {}

    public Task(String taskName, String descript, double val) {
        name = taskName;
        description = descript;
        value = val;
        status = 1;
        id = Data.tasks.size() + 1; //have to change this later
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

    public int getStatus() {
        return status;
    }

    public void updateStatus(int stat) {
        status = stat;
    }

    public int getId() {
        return id;
    }


}
