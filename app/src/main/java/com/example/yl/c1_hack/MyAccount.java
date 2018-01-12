package com.example.yl.c1_hack;

/**
 * Created by YL on 1/12/2018.
 */

public class MyAccount {
    private double balance;
    private int id;

    public MyAccount() {}
    public MyAccount(double bal, int i) {
        balance = bal;
        id = i;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public void increaseBalance(double amount) {
        balance = balance + amount;
    }

    public void transferTo(MyAccount other, double amount) {
        balance = balance - amount;
        other.increaseBalance(amount);
    }
}
