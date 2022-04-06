package com.betelgeuse.apihttprequest;


public class PriceEvent {

    String symbol;
    int price_24h;
    int volume_24h;
    int last_trade_pricenumber;

    public String getSymbol ( ) {
        return symbol;
    }

    public void setSymbol (String symbol) {
        this.symbol = symbol;
    }

    public int getPrice_24h ( ) {
        return price_24h;
    }

    public void setPrice_24h (int price_24h) {
        this.price_24h = price_24h;
    }

    public int getVolume_24h ( ) {
        return volume_24h;
    }

    public void setVolume_24h (int volume_24h) {
        this.volume_24h = volume_24h;
    }

    public int getLast_trade_pricenumber ( ) {
        return last_trade_pricenumber;
    }

    public void setLast_trade_pricenumber (int last_trade_pricenumber) {
        this.last_trade_pricenumber = last_trade_pricenumber;
    }


}
