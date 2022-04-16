package com.betelgeuse.sqlite;

import java.io.Serializable;

public class Option implements Serializable {
    private int id;
    private  String symbol;
    private String email;
    private Period period;

    public String getSymbol ( ) {
        return symbol;
    }

    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }



    public Option ( ) {
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Period getPeriod ( ) {
        return period;
    }

    public void setPeriod (Period period) {
        this.period = period;
    }
}
