package com.betelgeuse.recyclerviewonclickdata.main;

public class TickerModel {
    String fromCurrency;
    String toCurrency;

    public String getFromCurrency ( ) {
        return fromCurrency;
    }

    public void setFromCurrency (String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency ( ) {
        return toCurrency;
    }

    public void setToCurrency (String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public String getRsiResult ( ) {
        return rsiResult;
    }

    public void setRsiResult (String rsiResult) {
        this.rsiResult = rsiResult;
    }

    public String getBollingBandResult ( ) {
        return bollingBandResult;
    }

    public void setBollingBandResult (String bollingBandResult) {
        this.bollingBandResult = bollingBandResult;
    }

    public String getMacdResult ( ) {
        return macdResult;
    }

    public void setMacdResult (String macdResult) {
        this.macdResult = macdResult;
    }

    String rsiResult;
    String bollingBandResult;
    String macdResult;

}
