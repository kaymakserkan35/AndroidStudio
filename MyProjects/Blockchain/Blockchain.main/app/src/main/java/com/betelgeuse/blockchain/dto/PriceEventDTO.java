package com.betelgeuse.blockchain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceEventDTO {
    @SerializedName("day")  @Expose
    private String day;
    @SerializedName("base_currency")  @Expose
    private String baseCurrency;
    @SerializedName("to_currency")  @Expose
    private String toCurrency;
    @SerializedName("symbol")  @Expose
    private String symbol;
    @SerializedName("price_24h")  @Expose
    private double price24h;
    @SerializedName("volume_24h")  @Expose
    private double volume24h;
    @SerializedName("last_trade_price")  @Expose
    private double lastTradePrice;
    @SerializedName("hour")  @Expose
    private String hour;

    public String getHour ( ) {
        return hour;
    }
    public void setHour (String hour) {
        this.hour = hour;
    }
    public String getDay ( ) {
        return day;
    }
    public void setDay (String day) {
        this.day = day;
    }
    public String getBaseCurrency ( ) {
        return baseCurrency;
    }
    public void setBaseCurrency (String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
    public String getToCurrency ( ) {
        return toCurrency;
    }
    public void setToCurrency (String toCurrency) {
        this.toCurrency = toCurrency;
    }
    public String getSymbol ( ) {
        return symbol;
    }
    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }
    public double getPrice24h ( ) {
        return price24h;
    }
    public void setPrice24h (double price24h) {
        this.price24h = price24h;
    }
    public double getVolume24h ( ) {
        return volume24h;
    }
    public void setVolume24h (double volume24h) {
        this.volume24h = volume24h;
    }
    public double getLastTradePrice ( ) {
        return lastTradePrice;
    }
    public void setLastTradePrice (double lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }









}
