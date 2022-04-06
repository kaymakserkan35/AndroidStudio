package com.betelgeuse.blockchain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceEventHistoryDTO {
    @SerializedName("base_currency")  @Expose
    private String baseCurrency;
    @SerializedName("to_currency")  @Expose
    private String toCurrency;
    @SerializedName("symbol")  @Expose
    private String symbol;
    /*-----------------------------------*/
    @SerializedName("date")  @Expose
    private String date;
    @SerializedName("price")  @Expose
    private double price;
    @SerializedName("open")  @Expose
    private double open;
    @SerializedName("high")  @Expose
    private double high;
    @SerializedName("low")  @Expose
    private String low;
    @SerializedName("vol")  @Expose
    private String vol;
    @SerializedName("change")  @Expose
    private String change;
}
