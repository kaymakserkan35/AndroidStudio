package com.betelgeuse.blockchain.core.indicator;

import com.betelgeuse.blockchain.data.dto.TickerDTO;

public abstract class AData {
    public AData (TickerDTO tickerDTO) {
        this.calendar = tickerDTO.getCalendar();
        this.symbol = tickerDTO.getSymbol();
        this.price = tickerDTO.getPrice();
        this.open = tickerDTO.getOpen();
        this.high = tickerDTO.getHigh();
        this.low = tickerDTO.getLow();
        this.candle = tickerDTO.getCandle();
        this.timeStamp = tickerDTO.getTimeStamp();
    }

    double candle;
    String calendar;
    double high;
    double low;
    double price;
    public    String symbol;
    double timeStamp;
    double open;
}
