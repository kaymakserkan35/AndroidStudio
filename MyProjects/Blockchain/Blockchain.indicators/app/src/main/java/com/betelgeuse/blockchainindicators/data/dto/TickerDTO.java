package com.betelgeuse.blockchainindicators.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TickerDTO {

    @SerializedName("candle")  @Expose
    double candle;
    @SerializedName("calendar")  @Expose
    String calendar;
    @SerializedName("high")  @Expose
    double high;
    @SerializedName("low")  @Expose
    double low;
    @SerializedName("price")  @Expose
    double price;
    @SerializedName("symbol")  @Expose
    String symbol;
    @SerializedName("timeStamp")  @Expose
    double timeStamp;
    @SerializedName("open")  @Expose
    double open;
    /*------------------------------------------------*/
    String fromCurrency;
    String toCurrency;

    public String getFromCurrency ( ) {
        return fromCurrency;
    }

    public String getToCurrency ( ) {
        return toCurrency;
    }
    /*-------------------------------------------------*/

    public double getCandle ( ) {
        return candle;
    }

    public TickerDTO setCandle (double candle) {
        this.candle = candle;
        return  this;
    }

    public String getCalendar ( ) {
        return calendar;
    }

    public TickerDTO setCalendar (String calendar) {
        this.calendar = calendar;
        return  this;
    }

    public double getHigh ( ) {
        return high;
    }

    public TickerDTO setHigh (double high) {
        this.high = high;
        return  this;
    }

    public double getLow ( ) {
        return low;
    }

    public void setLow (double low) {
        this.low = low;
    }

    public double getPrice ( ) {
        return price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public String getSymbol ( ) {
        return symbol;
    }

    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }

    public double getTimeStamp ( ) {
        return timeStamp;
    }

    public void setTimeStamp (double timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getOpen ( ) {
        return open;
    }

    public void setOpen (double open) {
        this.open = open;
    }






}
