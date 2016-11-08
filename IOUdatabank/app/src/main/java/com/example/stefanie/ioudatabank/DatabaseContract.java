package com.example.stefanie.ioudatabank;

import android.provider.BaseColumns;

/**
 * Created by Stefanie on 25/10/2016.
 */

public class DatabaseContract {

    private int id;
    private double amount;
    private String owedTo;
    private String owedBy;
    private String Context;

    public DatabaseContract() {}

    public DatabaseContract(int id, double amount, String owedTo, String owedBy, String context) {
        this.id = id;
        this.amount = amount;
        this.owedTo = owedTo;
        this.owedBy = owedBy;
        Context = context;
    }

    public DatabaseContract(String context, double amount, String owedTo, String owedBy) {
        Context = context;
        this.amount = amount;
        this.owedTo = owedTo;
        this.owedBy = owedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOwedTo() {
        return owedTo;
    }

    public void setOwedTo(String owedTo) {
        this.owedTo = owedTo;
    }

    public String getOwedBy() {
        return owedBy;
    }

    public void setOwedBy(String owedBy) {
        this.owedBy = owedBy;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }
}
